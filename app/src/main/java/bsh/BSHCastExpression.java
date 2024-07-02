package bsh;

/* loaded from: classes.dex */
class BSHCastExpression extends SimpleNode {
    public BSHCastExpression(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        callStack.top();
        Class type = ((BSHType) jjtGetChild(0)).getType(callStack, interpreter);
        Object eval = ((SimpleNode) jjtGetChild(1)).eval(callStack, interpreter);
        eval.getClass();
        try {
            return Types.castObject(eval, type, 0);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callStack);
        }
    }
}
