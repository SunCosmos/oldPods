package bsh;

/* loaded from: classes.dex */
class BSHReturnType extends SimpleNode {
    public boolean isVoid;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHReturnType(int i2) {
        super(i2);
    }

    public Class evalReturnType(CallStack callStack, Interpreter interpreter) {
        return this.isVoid ? Void.TYPE : getTypeNode().getType(callStack, interpreter);
    }

    public String getTypeDescriptor(CallStack callStack, Interpreter interpreter, String str) {
        return this.isVoid ? "V" : getTypeNode().getTypeDescriptor(callStack, interpreter, str);
    }

    BSHType getTypeNode() {
        return (BSHType) jjtGetChild(0);
    }
}
