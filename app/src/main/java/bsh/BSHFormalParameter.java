package bsh;

/* loaded from: classes.dex */
class BSHFormalParameter extends SimpleNode {
    public static final Class UNTYPED = null;
    public String name;
    public Class type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHFormalParameter(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        this.type = jjtGetNumChildren() > 0 ? ((BSHType) jjtGetChild(0)).getType(callStack, interpreter) : UNTYPED;
        return this.type;
    }

    public String getTypeDescriptor(CallStack callStack, Interpreter interpreter, String str) {
        return jjtGetNumChildren() > 0 ? ((BSHType) jjtGetChild(0)).getTypeDescriptor(callStack, interpreter, str) : "Ljava/lang/Object;";
    }
}
