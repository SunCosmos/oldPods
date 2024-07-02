package bsh;

/* loaded from: classes.dex */
public class DelayedEvalBshMethod extends BshMethod {
    transient CallStack callstack;
    transient Interpreter interpreter;
    String[] paramTypeDescriptors;
    BSHFormalParameters paramTypesNode;
    String returnTypeDescriptor;
    BSHReturnType returnTypeNode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DelayedEvalBshMethod(String str, String str2, BSHReturnType bSHReturnType, String[] strArr, String[] strArr2, BSHFormalParameters bSHFormalParameters, BSHBlock bSHBlock, NameSpace nameSpace, Modifiers modifiers, CallStack callStack, Interpreter interpreter) {
        super(str, null, strArr, null, bSHBlock, nameSpace, modifiers);
        this.returnTypeDescriptor = str2;
        this.returnTypeNode = bSHReturnType;
        this.paramTypeDescriptors = strArr2;
        this.paramTypesNode = bSHFormalParameters;
        this.callstack = callStack;
        this.interpreter = interpreter;
    }

    public String[] getParamTypeDescriptors() {
        return this.paramTypeDescriptors;
    }

    @Override // bsh.BshMethod
    public Class[] getParameterTypes() {
        try {
            return (Class[]) this.paramTypesNode.eval(this.callstack, this.interpreter);
        } catch (EvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("can't eval param types: ");
            stringBuffer.append(e);
            throw new InterpreterError(stringBuffer.toString());
        }
    }

    @Override // bsh.BshMethod
    public Class getReturnType() {
        BSHReturnType bSHReturnType = this.returnTypeNode;
        if (bSHReturnType == null) {
            return null;
        }
        try {
            return bSHReturnType.evalReturnType(this.callstack, this.interpreter);
        } catch (EvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("can't eval return type: ");
            stringBuffer.append(e);
            throw new InterpreterError(stringBuffer.toString());
        }
    }

    public String getReturnTypeDescriptor() {
        return this.returnTypeDescriptor;
    }
}
