package bsh;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BSHAmbiguousName extends SimpleNode {
    public String text;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHAmbiguousName(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        throw new InterpreterError("Don't know how to eval an ambiguous name!  Use toObject() if you want an object.");
    }

    public Name getName(NameSpace nameSpace) {
        return nameSpace.getNameResolver(this.text);
    }

    public Class toClass(CallStack callStack, Interpreter interpreter) {
        try {
            return getName(callStack.top()).toClass();
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callStack);
        } catch (ClassNotFoundException e2) {
            throw new EvalError(e2.getMessage(), this, callStack);
        }
    }

    public LHS toLHS(CallStack callStack, Interpreter interpreter) {
        try {
            return getName(callStack.top()).toLHS(callStack, interpreter);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callStack);
        }
    }

    public Object toObject(CallStack callStack, Interpreter interpreter) {
        return toObject(callStack, interpreter, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object toObject(CallStack callStack, Interpreter interpreter, boolean z) {
        try {
            return getName(callStack.top()).toObject(callStack, interpreter, z);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callStack);
        }
    }

    @Override // bsh.SimpleNode
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("AmbigousName: ");
        stringBuffer.append(this.text);
        return stringBuffer.toString();
    }
}
