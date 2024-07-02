package bsh;

/* loaded from: classes.dex */
class BSHEnhancedForStatement extends SimpleNode implements ParserConstants {
    String varName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHEnhancedForStatement(int i2) {
        super(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0098 A[EDGE_INSN: B:27:0x0098->B:28:0x0098 BREAK  A[LOOP:0: B:12:0x0059->B:31:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[LOOP:0: B:12:0x0059->B:31:?, LOOP_END, SYNTHETIC] */
    @Override // bsh.SimpleNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object eval(bsh.CallStack r12, bsh.Interpreter r13) {
        /*
            r11 = this;
            bsh.NameSpace r0 = r12.top()
            r1 = 0
            bsh.Node r2 = r11.jjtGetChild(r1)
            bsh.SimpleNode r2 = (bsh.SimpleNode) r2
            int r3 = r11.jjtGetNumChildren()
            boolean r4 = r2 instanceof bsh.BSHType
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L2f
            bsh.BSHType r2 = (bsh.BSHType) r2
            java.lang.Class r2 = r2.getType(r12, r13)
            bsh.Node r4 = r11.jjtGetChild(r6)
            bsh.SimpleNode r4 = (bsh.SimpleNode) r4
            r7 = 2
            if (r3 <= r7) goto L2b
            bsh.Node r3 = r11.jjtGetChild(r7)
            r5 = r3
            bsh.SimpleNode r5 = (bsh.SimpleNode) r5
        L2b:
            r3 = r5
            r5 = r2
            r2 = r4
            goto L39
        L2f:
            if (r3 <= r6) goto L38
            bsh.Node r3 = r11.jjtGetChild(r6)
            bsh.SimpleNode r3 = (bsh.SimpleNode) r3
            goto L39
        L38:
            r3 = r5
        L39:
            bsh.BlockNameSpace r4 = new bsh.BlockNameSpace
            r4.<init>(r0)
            r12.swap(r4)
            java.lang.Object r2 = r2.eval(r12, r13)
            bsh.Primitive r7 = bsh.Primitive.NULL
            if (r2 == r7) goto Ld0
            bsh.CollectionManager r7 = bsh.CollectionManager.getCollectionManager()
            boolean r8 = r7.isBshIterable(r2)
            if (r8 == 0) goto Lb5
            bsh.BshIterator r2 = r7.getBshIterator(r2)
            bsh.Primitive r7 = bsh.Primitive.VOID
        L59:
            boolean r8 = r2.hasNext()
            if (r8 != 0) goto L60
            goto L98
        L60:
            if (r5 == 0) goto L71
            java.lang.String r8 = r11.varName     // Catch: bsh.UtilEvalError -> L9c
            java.lang.Object r9 = r2.next()     // Catch: bsh.UtilEvalError -> L9c
            bsh.Modifiers r10 = new bsh.Modifiers     // Catch: bsh.UtilEvalError -> L9c
            r10.<init>()     // Catch: bsh.UtilEvalError -> L9c
            r4.setTypedVariable(r8, r5, r9, r10)     // Catch: bsh.UtilEvalError -> L9c
            goto L7a
        L71:
            java.lang.String r8 = r11.varName     // Catch: bsh.UtilEvalError -> L9c
            java.lang.Object r9 = r2.next()     // Catch: bsh.UtilEvalError -> L9c
            r4.setVariable(r8, r9, r1)     // Catch: bsh.UtilEvalError -> L9c
        L7a:
            if (r3 == 0) goto L95
            java.lang.Object r8 = r3.eval(r12, r13)
            boolean r9 = r8 instanceof bsh.ReturnControl
            if (r9 == 0) goto L95
            r9 = r8
            bsh.ReturnControl r9 = (bsh.ReturnControl) r9
            int r9 = r9.kind
            r10 = 12
            if (r9 == r10) goto L93
            r10 = 46
            if (r9 == r10) goto L92
            goto L95
        L92:
            r7 = r8
        L93:
            r8 = 1
            goto L96
        L95:
            r8 = 0
        L96:
            if (r8 == 0) goto L59
        L98:
            r12.swap(r0)
            return r7
        L9c:
            r13 = move-exception
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "for loop iterator variable:"
            r0.append(r1)
            java.lang.String r1 = r11.varName
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            bsh.EvalError r12 = r13.toEvalError(r0, r11, r12)
            throw r12
        Lb5:
            bsh.EvalError r13 = new bsh.EvalError
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Can't iterate over type: "
            r0.append(r1)
            java.lang.Class r1 = r2.getClass()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r13.<init>(r0, r11, r12)
            throw r13
        Ld0:
            bsh.EvalError r13 = new bsh.EvalError
            java.lang.String r0 = "The collection, array, map, iterator, or enumeration portion of a for statement cannot be null."
            r13.<init>(r0, r11, r12)
            goto Ld9
        Ld8:
            throw r13
        Ld9:
            goto Ld8
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.BSHEnhancedForStatement.eval(bsh.CallStack, bsh.Interpreter):java.lang.Object");
    }
}
