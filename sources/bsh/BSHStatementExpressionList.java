package bsh;

/* loaded from: classes.dex */
class BSHStatementExpressionList extends SimpleNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHStatementExpressionList(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        int jjtGetNumChildren = jjtGetNumChildren();
        for (int i2 = 0; i2 < jjtGetNumChildren; i2++) {
            ((SimpleNode) jjtGetChild(i2)).eval(callStack, interpreter);
        }
        return Primitive.VOID;
    }
}
