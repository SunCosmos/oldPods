package bsh;

/* loaded from: classes.dex */
class BSHFormalParameters extends SimpleNode {
    int numArgs;
    private String[] paramNames;
    Class[] paramTypes;
    String[] typeDescriptors;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHFormalParameters(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        Class[] clsArr = this.paramTypes;
        if (clsArr != null) {
            return clsArr;
        }
        insureParsed();
        Class[] clsArr2 = new Class[this.numArgs];
        for (int i2 = 0; i2 < this.numArgs; i2++) {
            clsArr2[i2] = (Class) ((BSHFormalParameter) jjtGetChild(i2)).eval(callStack, interpreter);
        }
        this.paramTypes = clsArr2;
        return clsArr2;
    }

    public String[] getParamNames() {
        insureParsed();
        return this.paramNames;
    }

    public String[] getTypeDescriptors(CallStack callStack, Interpreter interpreter, String str) {
        String[] strArr = this.typeDescriptors;
        if (strArr != null) {
            return strArr;
        }
        insureParsed();
        String[] strArr2 = new String[this.numArgs];
        for (int i2 = 0; i2 < this.numArgs; i2++) {
            strArr2[i2] = ((BSHFormalParameter) jjtGetChild(i2)).getTypeDescriptor(callStack, interpreter, str);
        }
        this.typeDescriptors = strArr2;
        return strArr2;
    }

    void insureParsed() {
        if (this.paramNames != null) {
            return;
        }
        int jjtGetNumChildren = jjtGetNumChildren();
        this.numArgs = jjtGetNumChildren;
        String[] strArr = new String[jjtGetNumChildren];
        for (int i2 = 0; i2 < this.numArgs; i2++) {
            strArr[i2] = ((BSHFormalParameter) jjtGetChild(i2)).name;
        }
        this.paramNames = strArr;
    }
}
