package bsh;

/* loaded from: classes.dex */
public class BSHMethodDeclaration extends SimpleNode {
    BSHBlock blockNode;
    int firstThrowsClause;
    public Modifiers modifiers;
    public String name;
    int numThrows;
    BSHFormalParameters paramsNode;
    Class returnType;
    BSHReturnType returnTypeNode;

    public BSHMethodDeclaration(int i2) {
        super(i2);
        this.numThrows = 0;
    }

    private void evalNodes(CallStack callStack, Interpreter interpreter) {
        insureNodesParsed();
        for (int i2 = this.firstThrowsClause; i2 < this.numThrows + this.firstThrowsClause; i2++) {
            ((BSHAmbiguousName) jjtGetChild(i2)).toClass(callStack, interpreter);
        }
        this.paramsNode.eval(callStack, interpreter);
        if (!interpreter.getStrictJava()) {
            return;
        }
        int i3 = 0;
        while (true) {
            Class[] clsArr = this.paramsNode.paramTypes;
            if (i3 >= clsArr.length) {
                if (this.returnType != null) {
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("(Strict Java Mode) Undeclared return type for method: ");
                stringBuffer.append(this.name);
                throw new EvalError(stringBuffer.toString(), this, null);
            }
            if (clsArr[i3] == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("(Strict Java Mode) Undeclared argument type, parameter: ");
                stringBuffer2.append(this.paramsNode.getParamNames()[i3]);
                stringBuffer2.append(" in method: ");
                stringBuffer2.append(this.name);
                throw new EvalError(stringBuffer2.toString(), this, null);
            }
            i3++;
        }
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        this.returnType = evalReturnType(callStack, interpreter);
        evalNodes(callStack, interpreter);
        NameSpace pVar = callStack.top();
        try {
            pVar.setMethod(this.name, new BshMethod(this, pVar, this.modifiers));
            return Primitive.VOID;
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callStack);
        }
    }

    Class evalReturnType(CallStack callStack, Interpreter interpreter) {
        insureNodesParsed();
        BSHReturnType bSHReturnType = this.returnTypeNode;
        if (bSHReturnType != null) {
            return bSHReturnType.evalReturnType(callStack, interpreter);
        }
        return null;
    }

    public String getReturnTypeDescriptor(CallStack callStack, Interpreter interpreter, String str) {
        insureNodesParsed();
        BSHReturnType bSHReturnType = this.returnTypeNode;
        if (bSHReturnType == null) {
            return null;
        }
        return bSHReturnType.getTypeDescriptor(callStack, interpreter, str);
    }

    public BSHReturnType getReturnTypeNode() {
        insureNodesParsed();
        return this.returnTypeNode;
    }

    public synchronized void insureNodesParsed() {
        if (this.paramsNode != null) {
            return;
        }
        Node jjtGetChild = jjtGetChild(0);
        this.firstThrowsClause = 1;
        if (jjtGetChild instanceof BSHReturnType) {
            this.returnTypeNode = (BSHReturnType) jjtGetChild;
            this.paramsNode = (BSHFormalParameters) jjtGetChild(1);
            int jjtGetNumChildren = jjtGetNumChildren();
            int i2 = this.numThrows;
            if (jjtGetNumChildren > i2 + 2) {
                this.blockNode = (BSHBlock) jjtGetChild(i2 + 2);
            }
            this.firstThrowsClause++;
        } else {
            this.paramsNode = (BSHFormalParameters) jjtGetChild(0);
            this.blockNode = (BSHBlock) jjtGetChild(this.numThrows + 1);
        }
    }

    @Override // bsh.SimpleNode
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("MethodDeclaration: ");
        stringBuffer.append(this.name);
        return stringBuffer.toString();
    }
}
