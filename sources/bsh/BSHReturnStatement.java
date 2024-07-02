package bsh;

/* loaded from: classes.dex */
class BSHReturnStatement extends SimpleNode implements ParserConstants {
    public int kind;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHReturnStatement(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        return new ReturnControl(this.kind, jjtGetNumChildren() > 0 ? ((SimpleNode) jjtGetChild(0)).eval(callStack, interpreter) : Primitive.VOID, this);
    }
}
