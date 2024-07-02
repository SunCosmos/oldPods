package bsh;

/* loaded from: classes.dex */
class BSHWhileStatement extends SimpleNode implements ParserConstants {
    public boolean isDoStatement;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHWhileStatement(int i2) {
        super(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0058 A[EDGE_INSN: B:28:0x0058->B:9:0x0058 BREAK  A[LOOP:0: B:5:0x002c->B:27:0x002c], SYNTHETIC] */
    @Override // bsh.SimpleNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object eval(bsh.CallStack r10, bsh.Interpreter r11) {
        /*
            r9 = this;
            int r0 = r9.jjtGetNumChildren()
            boolean r1 = r9.isDoStatement
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L17
            bsh.Node r0 = r9.jjtGetChild(r2)
            bsh.SimpleNode r0 = (bsh.SimpleNode) r0
            bsh.Node r1 = r9.jjtGetChild(r3)
            bsh.SimpleNode r1 = (bsh.SimpleNode) r1
            goto L2a
        L17:
            bsh.Node r1 = r9.jjtGetChild(r3)
            bsh.SimpleNode r1 = (bsh.SimpleNode) r1
            if (r0 <= r2) goto L26
            bsh.Node r0 = r9.jjtGetChild(r2)
            bsh.SimpleNode r0 = (bsh.SimpleNode) r0
            goto L27
        L26:
            r0 = 0
        L27:
            r8 = r1
            r1 = r0
            r0 = r8
        L2a:
            boolean r4 = r9.isDoStatement
        L2c:
            if (r4 != 0) goto L35
            boolean r5 = bsh.BSHIfStatement.evaluateCondition(r0, r10, r11)
            if (r5 != 0) goto L35
            goto L58
        L35:
            if (r1 != 0) goto L38
            goto L2c
        L38:
            java.lang.Object r5 = r1.eval(r10, r11)
            boolean r6 = r5 instanceof bsh.ReturnControl
            if (r6 == 0) goto L55
            r6 = r5
            bsh.ReturnControl r6 = (bsh.ReturnControl) r6
            int r6 = r6.kind
            r7 = 12
            if (r6 == r7) goto L53
            r7 = 19
            if (r6 == r7) goto L2c
            r4 = 46
            if (r6 == r4) goto L52
            goto L55
        L52:
            return r5
        L53:
            r4 = 1
            goto L56
        L55:
            r4 = 0
        L56:
            if (r4 == 0) goto L5b
        L58:
            bsh.Primitive r10 = bsh.Primitive.VOID
            return r10
        L5b:
            r4 = 0
            goto L2c
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.BSHWhileStatement.eval(bsh.CallStack, bsh.Interpreter):java.lang.Object");
    }
}
