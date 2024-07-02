package bsh;

/* loaded from: classes.dex */
class BSHForStatement extends SimpleNode implements ParserConstants {
    private SimpleNode expression;
    private SimpleNode forInit;
    private SimpleNode forUpdate;
    public boolean hasExpression;
    public boolean hasForInit;
    public boolean hasForUpdate;
    private boolean parsed;
    private SimpleNode statement;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHForStatement(int i2) {
        super(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0081 A[EDGE_INSN: B:43:0x0081->B:22:0x0081 BREAK  A[LOOP:0: B:17:0x0054->B:41:0x0054], SYNTHETIC] */
    @Override // bsh.SimpleNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object eval(bsh.CallStack r8, bsh.Interpreter r9) {
        /*
            r7 = this;
            boolean r0 = r7.hasForInit
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L10
            bsh.Node r0 = r7.jjtGetChild(r1)
            bsh.SimpleNode r0 = (bsh.SimpleNode) r0
            r7.forInit = r0
            r0 = 1
            goto L11
        L10:
            r0 = 0
        L11:
            boolean r3 = r7.hasExpression
            if (r3 == 0) goto L20
            int r3 = r0 + 1
            bsh.Node r0 = r7.jjtGetChild(r0)
            bsh.SimpleNode r0 = (bsh.SimpleNode) r0
            r7.expression = r0
            r0 = r3
        L20:
            boolean r3 = r7.hasForUpdate
            if (r3 == 0) goto L2f
            int r3 = r0 + 1
            bsh.Node r0 = r7.jjtGetChild(r0)
            bsh.SimpleNode r0 = (bsh.SimpleNode) r0
            r7.forUpdate = r0
            r0 = r3
        L2f:
            int r3 = r7.jjtGetNumChildren()
            if (r0 >= r3) goto L3d
            bsh.Node r0 = r7.jjtGetChild(r0)
            bsh.SimpleNode r0 = (bsh.SimpleNode) r0
            r7.statement = r0
        L3d:
            bsh.NameSpace r0 = r8.top()
            bsh.BlockNameSpace r3 = new bsh.BlockNameSpace
            r3.<init>(r0)
            r8.swap(r3)
            boolean r3 = r7.hasForInit
            if (r3 == 0) goto L52
            bsh.SimpleNode r3 = r7.forInit
            r3.eval(r8, r9)
        L52:
            bsh.Primitive r3 = bsh.Primitive.VOID
        L54:
            boolean r4 = r7.hasExpression
            if (r4 == 0) goto L61
            bsh.SimpleNode r4 = r7.expression
            boolean r4 = bsh.BSHIfStatement.evaluateCondition(r4, r8, r9)
            if (r4 != 0) goto L61
            goto L81
        L61:
            bsh.SimpleNode r4 = r7.statement
            if (r4 == 0) goto L7e
            java.lang.Object r4 = r4.eval(r8, r9)
            boolean r5 = r4 instanceof bsh.ReturnControl
            if (r5 == 0) goto L7e
            r5 = r4
            bsh.ReturnControl r5 = (bsh.ReturnControl) r5
            int r5 = r5.kind
            r6 = 12
            if (r5 == r6) goto L7c
            r6 = 46
            if (r5 == r6) goto L7b
            goto L7e
        L7b:
            r3 = r4
        L7c:
            r4 = 1
            goto L7f
        L7e:
            r4 = 0
        L7f:
            if (r4 == 0) goto L85
        L81:
            r8.swap(r0)
            return r3
        L85:
            boolean r4 = r7.hasForUpdate
            if (r4 == 0) goto L54
            bsh.SimpleNode r4 = r7.forUpdate
            r4.eval(r8, r9)
            goto L54
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.BSHForStatement.eval(bsh.CallStack, bsh.Interpreter):java.lang.Object");
    }
}
