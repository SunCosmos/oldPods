package bsh;

/* loaded from: classes.dex */
public class EvalError extends Exception {
    CallStack callstack;
    String message;
    SimpleNode node;

    public EvalError(String str, SimpleNode simpleNode, CallStack callStack) {
        setMessage(str);
        this.node = simpleNode;
        if (callStack != null) {
            this.callstack = callStack.copy();
        }
    }

    public int getErrorLineNumber() {
        SimpleNode simpleNode = this.node;
        if (simpleNode != null) {
            return simpleNode.getLineNumber();
        }
        return -1;
    }

    public String getErrorSourceFile() {
        SimpleNode simpleNode = this.node;
        return simpleNode != null ? simpleNode.getSourceFile() : "<unknown file>";
    }

    public String getErrorText() {
        SimpleNode simpleNode = this.node;
        return simpleNode != null ? simpleNode.getText() : "<unknown error>";
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public SimpleNode getNode() {
        return this.node;
    }

    public String getScriptStackTrace() {
        CallStack callStack = this.callstack;
        if (callStack == null) {
            return "<Unknown>";
        }
        CallStack copy = callStack.copy();
        String str = "";
        while (copy.depth() > 0) {
            NameSpace pop = copy.pop();
            SimpleNode node = pop.getNode();
            if (pop.isMethod) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str);
                stringBuffer.append("\nCalled from method: ");
                stringBuffer.append(pop.getName());
                str = stringBuffer.toString();
                if (node != null) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(str);
                    stringBuffer2.append(" : at Line: ");
                    stringBuffer2.append(node.getLineNumber());
                    stringBuffer2.append(" : in file: ");
                    stringBuffer2.append(node.getSourceFile());
                    stringBuffer2.append(" : ");
                    stringBuffer2.append(node.getText());
                    str = stringBuffer2.toString();
                }
            }
        }
        return str;
    }

    protected void prependMessage(String str) {
        if (str == null) {
            return;
        }
        if (this.message != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(" : ");
            stringBuffer.append(this.message);
            str = stringBuffer.toString();
        }
        this.message = str;
    }

    public void reThrow(String str) {
        prependMessage(str);
        throw this;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setNode(SimpleNode simpleNode) {
        this.node = simpleNode;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String str;
        if (this.node != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" : at Line: ");
            stringBuffer.append(this.node.getLineNumber());
            stringBuffer.append(" : in file: ");
            stringBuffer.append(this.node.getSourceFile());
            stringBuffer.append(" : ");
            stringBuffer.append(this.node.getText());
            str = stringBuffer.toString();
        } else {
            str = ": <at unknown location>";
        }
        if (this.callstack != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(str);
            stringBuffer2.append("\n");
            stringBuffer2.append(getScriptStackTrace());
            str = stringBuffer2.toString();
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append(getMessage());
        stringBuffer3.append(str);
        return stringBuffer3.toString();
    }
}
