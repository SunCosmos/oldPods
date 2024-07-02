package bsh;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
class BSHArrayInitializer extends SimpleNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHArrayInitializer(int i2) {
        super(i2);
    }

    private void throwTypeError(Class cls, Object obj, int i2, CallStack callStack) {
        String name = obj instanceof Primitive ? ((Primitive) obj).getType().getName() : Reflect.normalizeClassName(obj.getClass());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Incompatible type: ");
        stringBuffer.append(name);
        stringBuffer.append(" in initializer of array type: ");
        stringBuffer.append(cls);
        stringBuffer.append(" at position: ");
        stringBuffer.append(i2);
        throw new EvalError(stringBuffer.toString(), this, callStack);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        throw new EvalError("Array initializer has no base type.", this, callStack);
    }

    public Object eval(Class cls, int i2, CallStack callStack, Interpreter interpreter) {
        Object eval;
        Object unwrap;
        StringBuffer stringBuffer;
        String str;
        int jjtGetNumChildren = jjtGetNumChildren();
        int[] iArr = new int[i2];
        iArr[0] = jjtGetNumChildren;
        Object newInstance = Array.newInstance((Class<?>) cls, iArr);
        for (int i3 = 0; i3 < jjtGetNumChildren; i3++) {
            SimpleNode simpleNode = (SimpleNode) jjtGetChild(i3);
            if (!(simpleNode instanceof BSHArrayInitializer)) {
                eval = simpleNode.eval(callStack, interpreter);
            } else {
                if (i2 < 2) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Invalid Location for Intializer, position: ");
                    stringBuffer2.append(i3);
                    throw new EvalError(stringBuffer2.toString(), this, callStack);
                }
                eval = ((BSHArrayInitializer) simpleNode).eval(cls, i2 - 1, callStack, interpreter);
            }
            if (eval == Primitive.VOID) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Void in array initializer, position");
                stringBuffer3.append(i3);
                throw new EvalError(stringBuffer3.toString(), this, callStack);
            }
            if (i2 == 1) {
                try {
                    unwrap = Primitive.unwrap(Types.castObject(eval, cls, 0));
                } catch (UtilEvalError e) {
                    throw e.toEvalError("Error in array initializer", this, callStack);
                }
            } else {
                unwrap = eval;
            }
            try {
                Array.set(newInstance, i3, unwrap);
            } catch (ArrayStoreException e2) {
                e = e2;
                stringBuffer = new StringBuffer();
                str = "arraystore";
                stringBuffer.append(str);
                stringBuffer.append(e);
                Interpreter.debug(stringBuffer.toString());
                throwTypeError(cls, eval, i3, callStack);
            } catch (IllegalArgumentException e3) {
                e = e3;
                stringBuffer = new StringBuffer();
                str = "illegal arg";
                stringBuffer.append(str);
                stringBuffer.append(e);
                Interpreter.debug(stringBuffer.toString());
                throwTypeError(cls, eval, i3, callStack);
            }
        }
        return newInstance;
    }
}
