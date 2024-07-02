package bsh;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SimpleNode implements Node {
    public static SimpleNode JAVACODE = new SimpleNode(-1) { // from class: bsh.SimpleNode.1
        @Override // bsh.SimpleNode
        public int getLineNumber() {
            return -1;
        }

        @Override // bsh.SimpleNode
        public String getSourceFile() {
            return "<Called from Java Code>";
        }

        @Override // bsh.SimpleNode
        public String getText() {
            return "<Compiled Java Code>";
        }
    };
    protected Node[] children;
    Token firstToken;
    protected int id;
    Token lastToken;
    protected Node parent;
    String sourceFile;

    public SimpleNode(int i2) {
        this.id = i2;
    }

    public void dump(String str) {
        System.out.println(toString(str));
        if (this.children == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            Node[] nodeArr = this.children;
            if (i2 >= nodeArr.length) {
                return;
            }
            SimpleNode simpleNode = (SimpleNode) nodeArr[i2];
            if (simpleNode != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str);
                stringBuffer.append(" ");
                simpleNode.dump(stringBuffer.toString());
            }
            i2++;
        }
    }

    public Object eval(CallStack callStack, Interpreter interpreter) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unimplemented or inappropriate for ");
        stringBuffer.append(getClass().getName());
        throw new InterpreterError(stringBuffer.toString());
    }

    public SimpleNode getChild(int i2) {
        return (SimpleNode) jjtGetChild(i2);
    }

    public int getLineNumber() {
        return this.firstToken.beginLine;
    }

    public String getSourceFile() {
        String str = this.sourceFile;
        if (str != null) {
            return str;
        }
        Node node = this.parent;
        return node != null ? ((SimpleNode) node).getSourceFile() : "<unknown file>";
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Token token = this.firstToken; token != null; token = token.next) {
            stringBuffer.append(token.image);
            if (!token.image.equals(".")) {
                stringBuffer.append(" ");
            }
            if (token == this.lastToken || token.image.equals("{") || token.image.equals(";")) {
                break;
            }
        }
        return stringBuffer.toString();
    }

    @Override // bsh.Node
    public void jjtAddChild(Node node, int i2) {
        Node[] nodeArr = this.children;
        if (nodeArr == null) {
            this.children = new Node[i2 + 1];
        } else if (i2 >= nodeArr.length) {
            Node[] nodeArr2 = new Node[i2 + 1];
            System.arraycopy(nodeArr, 0, nodeArr2, 0, nodeArr.length);
            this.children = nodeArr2;
        }
        this.children[i2] = node;
    }

    @Override // bsh.Node
    public void jjtClose() {
    }

    @Override // bsh.Node
    public Node jjtGetChild(int i2) {
        return this.children[i2];
    }

    @Override // bsh.Node
    public int jjtGetNumChildren() {
        Node[] nodeArr = this.children;
        if (nodeArr == null) {
            return 0;
        }
        return nodeArr.length;
    }

    @Override // bsh.Node
    public Node jjtGetParent() {
        return this.parent;
    }

    @Override // bsh.Node
    public void jjtOpen() {
    }

    @Override // bsh.Node
    public void jjtSetParent(Node node) {
        this.parent = node;
    }

    public void prune() {
        jjtSetParent(null);
    }

    public void setSourceFile(String str) {
        this.sourceFile = str;
    }

    public String toString() {
        return ParserTreeConstants.jjtNodeName[this.id];
    }

    public String toString(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(toString());
        return stringBuffer.toString();
    }
}
