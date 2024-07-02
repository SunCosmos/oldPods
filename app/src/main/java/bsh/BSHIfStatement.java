package bsh;

/* loaded from: classes.dex */
class BSHIfStatement extends SimpleNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHIfStatement(int i2) {
        super(i2);
    }

    public static boolean evaluateCondition(SimpleNode simpleNode, CallStack callStack, Interpreter interpreter) {
        Object eval = simpleNode.eval(callStack, interpreter);
        if (eval instanceof Primitive) {
            if (eval == Primitive.VOID) {
                throw new EvalError("Condition evaluates to void type", simpleNode, callStack);
            }
            eval = ((Primitive) eval).getValue();
        }
        if (eval instanceof Boolean) {
            return ((Boolean) eval).booleanValue();
        }
        throw new EvalError("Condition must evaluate to a Boolean or boolean.", simpleNode, callStack);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    @Override // bsh.SimpleNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object eval(bsh.CallStack r3, bsh.Interpreter r4) {
        /*
            r2 = this;
            r0 = 0
            bsh.Node r0 = r2.jjtGetChild(r0)
            bsh.SimpleNode r0 = (bsh.SimpleNode) r0
            boolean r0 = evaluateCondition(r0, r3, r4)
            if (r0 == 0) goto L19
            r0 = 1
            bsh.Node r0 = r2.jjtGetChild(r0)
        L12:
            bsh.SimpleNode r0 = (bsh.SimpleNode) r0
            java.lang.Object r3 = r0.eval(r3, r4)
            goto L26
        L19:
            int r0 = r2.jjtGetNumChildren()
            r1 = 2
            if (r0 <= r1) goto L25
            bsh.Node r0 = r2.jjtGetChild(r1)
            goto L12
        L25:
            r3 = 0
        L26:
            boolean r4 = r3 instanceof bsh.ReturnControl
            if (r4 == 0) goto L2b
            return r3
        L2b:
            bsh.Primitive r3 = bsh.Primitive.VOID
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.BSHIfStatement.eval(bsh.CallStack, bsh.Interpreter):java.lang.Object");
    }
}
