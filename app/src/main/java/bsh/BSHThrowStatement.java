package bsh;

/* loaded from: classes.dex */
class BSHThrowStatement extends SimpleNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHThrowStatement(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        Object eval = ((SimpleNode) jjtGetChild(0)).eval(callStack, interpreter);
        if (eval instanceof Exception) {
            throw new TargetError((Exception) eval, this, callStack);
        }
        throw new EvalError("Expression in 'throw' must be Exception type", this, callStack);
    }
}
