package bsh;

/* loaded from: classes.dex */
class BSHTernaryExpression extends SimpleNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHTernaryExpression(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        return BSHIfStatement.evaluateCondition((SimpleNode) jjtGetChild(0), callStack, interpreter) ? ((SimpleNode) jjtGetChild(1)).eval(callStack, interpreter) : ((SimpleNode) jjtGetChild(2)).eval(callStack, interpreter);
    }
}
