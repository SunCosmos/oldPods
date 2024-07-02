package bsh;

/* loaded from: classes.dex */
class BSHImportDeclaration extends SimpleNode {
    public boolean importPackage;
    public boolean staticImport;
    public boolean superImport;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHImportDeclaration(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        NameSpace pVar = callStack.top();
        if (this.superImport) {
            try {
                pVar.doSuperImport();
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callStack);
            }
        } else if (!this.staticImport) {
            String str = ((BSHAmbiguousName) jjtGetChild(0)).text;
            if (this.importPackage) {
                pVar.importPackage(str);
            } else {
                pVar.importClass(str);
            }
        } else {
            if (!this.importPackage) {
                throw new EvalError("static field imports not supported yet", this, callStack);
            }
            pVar.importStatic(((BSHAmbiguousName) jjtGetChild(0)).toClass(callStack, interpreter));
        }
        return Primitive.VOID;
    }
}
