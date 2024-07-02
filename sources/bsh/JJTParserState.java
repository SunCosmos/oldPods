package bsh;

import java.util.Stack;

/* loaded from: classes.dex */
class JJTParserState {
    private boolean node_created;
    private Stack nodes = new Stack();
    private Stack marks = new Stack();
    private int sp = 0;
    private int mk = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearNodeScope(Node node) {
        while (this.sp > this.mk) {
            popNode();
        }
        this.mk = ((Integer) this.marks.pop()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeNodeScope(Node node, int i2) {
        this.mk = ((Integer) this.marks.pop()).intValue();
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                node.jjtClose();
                pushNode(node);
                this.node_created = true;
                return;
            } else {
                Node popNode = popNode();
                popNode.jjtSetParent(node);
                node.jjtAddChild(popNode, i3);
                i2 = i3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeNodeScope(Node node, boolean z) {
        boolean z2;
        if (z) {
            int nodeArity = nodeArity();
            this.mk = ((Integer) this.marks.pop()).intValue();
            while (true) {
                int i2 = nodeArity - 1;
                if (nodeArity <= 0) {
                    break;
                }
                Node popNode = popNode();
                popNode.jjtSetParent(node);
                node.jjtAddChild(popNode, i2);
                nodeArity = i2;
            }
            node.jjtClose();
            pushNode(node);
            z2 = true;
        } else {
            this.mk = ((Integer) this.marks.pop()).intValue();
            z2 = false;
        }
        this.node_created = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nodeArity() {
        return this.sp - this.mk;
    }

    boolean nodeCreated() {
        return this.node_created;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void openNodeScope(Node node) {
        this.marks.push(new Integer(this.mk));
        this.mk = this.sp;
        node.jjtOpen();
    }

    Node peekNode() {
        return (Node) this.nodes.peek();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Node popNode() {
        int i2 = this.sp - 1;
        this.sp = i2;
        if (i2 < this.mk) {
            this.mk = ((Integer) this.marks.pop()).intValue();
        }
        return (Node) this.nodes.pop();
    }

    void pushNode(Node node) {
        this.nodes.push(node);
        this.sp++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.nodes.removeAllElements();
        this.marks.removeAllElements();
        this.sp = 0;
        this.mk = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Node rootNode() {
        return (Node) this.nodes.elementAt(0);
    }
}
