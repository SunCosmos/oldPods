package bsh;

/* loaded from: classes.dex */
class BSHTypedVariableDeclaration extends SimpleNode {
    public Modifiers modifiers;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHTypedVariableDeclaration(int i2) {
        super(i2);
    }

    private BSHType getTypeNode() {
        return (BSHType) jjtGetChild(0);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        try {
            NameSpace pVar = callStack.top();
            BSHType typeNode = getTypeNode();
            Class type = typeNode.getType(callStack, interpreter);
            for (BSHVariableDeclarator bSHVariableDeclarator : getDeclarators()) {
                try {
                    pVar.setTypedVariable(bSHVariableDeclarator.name, type, bSHVariableDeclarator.eval(typeNode, callStack, interpreter), this.modifiers);
                } catch (UtilEvalError e) {
                    throw e.toEvalError(this, callStack);
                }
            }
        } catch (EvalError e2) {
            e2.reThrow("Typed variable declaration");
        }
        return Primitive.VOID;
    }

    Class evalType(CallStack callStack, Interpreter interpreter) {
        return getTypeNode().getType(callStack, interpreter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHVariableDeclarator[] getDeclarators() {
        int jjtGetNumChildren = jjtGetNumChildren();
        BSHVariableDeclarator[] bSHVariableDeclaratorArr = new BSHVariableDeclarator[jjtGetNumChildren - 1];
        for (int i2 = 1; i2 < jjtGetNumChildren; i2++) {
            bSHVariableDeclaratorArr[i2 - 1] = (BSHVariableDeclarator) jjtGetChild(i2);
        }
        return bSHVariableDeclaratorArr;
    }

    public String getTypeDescriptor(CallStack callStack, Interpreter interpreter, String str) {
        return getTypeNode().getTypeDescriptor(callStack, interpreter, str);
    }
}
