package bsh;

/* loaded from: classes.dex */
public class BSHBlock extends SimpleNode {
    public boolean isSynchronized;

    /* loaded from: classes.dex */
    public interface NodeFilter {
        boolean isVisible(SimpleNode simpleNode);
    }

    public BSHBlock(int i2) {
        super(i2);
        this.isSynchronized = false;
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        return eval(callStack, interpreter, false);
    }

    public Object eval(CallStack callStack, Interpreter interpreter, boolean z) {
        Object evalBlock;
        Object eval = this.isSynchronized ? ((SimpleNode) jjtGetChild(0)).eval(callStack, interpreter) : null;
        if (!this.isSynchronized) {
            return evalBlock(callStack, interpreter, z, null);
        }
        synchronized (eval) {
            evalBlock = evalBlock(callStack, interpreter, z, null);
        }
        return evalBlock;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r7v0, types: [bsh.BSHBlock, bsh.SimpleNode] */
    public Object evalBlock(CallStack callStack, Interpreter interpreter, boolean z, NodeFilter nodeFilter) {
        NameSpace nameSpace;
        Object obj = Primitive.VOID;
        if (z) {
            nameSpace = null;
        } else {
            nameSpace = callStack.top();
            callStack.swap(new BlockNameSpace(nameSpace));
        }
        ?? r2 = this.isSynchronized;
        int jjtGetNumChildren = jjtGetNumChildren();
        for (int i2 = r2; i2 < jjtGetNumChildren; i2++) {
            try {
                SimpleNode simpleNode = (SimpleNode) jjtGetChild(i2);
                if ((nodeFilter == null || nodeFilter.isVisible(simpleNode)) && (simpleNode instanceof BSHClassDeclaration)) {
                    simpleNode.eval(callStack, interpreter);
                }
            } finally {
                if (!z) {
                    callStack.swap(nameSpace);
                }
            }
        }
        while (r2 < jjtGetNumChildren) {
            SimpleNode simpleNode2 = (SimpleNode) jjtGetChild(r2);
            if (!(simpleNode2 instanceof BSHClassDeclaration) && (nodeFilter == null || nodeFilter.isVisible(simpleNode2))) {
                obj = simpleNode2.eval(callStack, interpreter);
                if (obj instanceof ReturnControl) {
                    break;
                }
            }
            r2++;
        }
        return obj;
    }
}
