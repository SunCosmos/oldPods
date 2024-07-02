package bsh;

/* loaded from: classes.dex */
class BSHSwitchLabel extends SimpleNode {
    boolean isDefault;

    public BSHSwitchLabel(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        if (this.isDefault) {
            return null;
        }
        return ((SimpleNode) jjtGetChild(0)).eval(callStack, interpreter);
    }
}
