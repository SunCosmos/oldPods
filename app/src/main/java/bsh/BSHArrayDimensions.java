package bsh;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
class BSHArrayDimensions extends SimpleNode {
    public Class baseType;
    public int[] definedDimensions;
    public int numDefinedDims;
    public int numUndefinedDims;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHArrayDimensions(int i2) {
        super(i2);
    }

    public void addDefinedDimension() {
        this.numDefinedDims++;
    }

    public void addUndefinedDimension() {
        this.numUndefinedDims++;
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        SimpleNode simpleNode = (SimpleNode) jjtGetChild(0);
        if (!(simpleNode instanceof BSHArrayInitializer)) {
            this.definedDimensions = new int[this.numDefinedDims];
            for (int i2 = 0; i2 < this.numDefinedDims; i2++) {
                try {
                    this.definedDimensions[i2] = ((Primitive) ((SimpleNode) jjtGetChild(i2)).eval(callStack, interpreter)).intValue();
                } catch (Exception unused) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Array index: ");
                    stringBuffer.append(i2);
                    stringBuffer.append(" does not evaluate to an integer");
                    throw new EvalError(stringBuffer.toString(), this, callStack);
                }
            }
            return Primitive.VOID;
        }
        Class cls = this.baseType;
        if (cls == null) {
            throw new EvalError("Internal Array Eval err:  unknown base type", this, callStack);
        }
        Object eval = ((BSHArrayInitializer) simpleNode).eval(cls, this.numUndefinedDims, callStack, interpreter);
        int arrayDimensions = Reflect.getArrayDimensions(eval.getClass());
        int[] iArr = new int[arrayDimensions];
        this.definedDimensions = iArr;
        if (iArr.length != this.numUndefinedDims) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Incompatible initializer. Allocation calls for a ");
            stringBuffer2.append(this.numUndefinedDims);
            stringBuffer2.append(" dimensional array, but initializer is a ");
            stringBuffer2.append(arrayDimensions);
            stringBuffer2.append(" dimensional array");
            throw new EvalError(stringBuffer2.toString(), this, callStack);
        }
        Object obj = eval;
        int i3 = 0;
        while (true) {
            int[] iArr2 = this.definedDimensions;
            if (i3 >= iArr2.length) {
                return eval;
            }
            iArr2[i3] = Array.getLength(obj);
            if (this.definedDimensions[i3] > 0) {
                obj = Array.get(obj, 0);
            }
            i3++;
        }
    }

    public Object eval(Class cls, CallStack callStack, Interpreter interpreter) {
        if (Interpreter.DEBUG) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("array base type = ");
            stringBuffer.append(cls);
            Interpreter.debug(stringBuffer.toString());
        }
        this.baseType = cls;
        return eval(callStack, interpreter);
    }
}
