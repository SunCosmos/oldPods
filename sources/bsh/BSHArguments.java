package bsh;

/* loaded from: classes.dex */
class BSHArguments extends SimpleNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHArguments(int i2) {
        super(i2);
    }

    public Object[] getArguments(CallStack callStack, Interpreter interpreter) {
        int jjtGetNumChildren = jjtGetNumChildren();
        Object[] objArr = new Object[jjtGetNumChildren];
        for (int i2 = 0; i2 < jjtGetNumChildren; i2++) {
            objArr[i2] = ((SimpleNode) jjtGetChild(i2)).eval(callStack, interpreter);
            if (objArr[i2] == Primitive.VOID) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Undefined argument: ");
                stringBuffer.append(((SimpleNode) jjtGetChild(i2)).getText());
                throw new EvalError(stringBuffer.toString(), this, callStack);
            }
        }
        return objArr;
    }
}
