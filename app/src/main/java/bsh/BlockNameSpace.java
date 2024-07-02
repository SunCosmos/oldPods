package bsh;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BlockNameSpace extends NameSpace {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public BlockNameSpace(bsh.NameSpace r3) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = r3.getName()
            r0.append(r1)
            java.lang.String r1 = "/BlockNameSpace"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.BlockNameSpace.<init>(bsh.NameSpace):void");
    }

    private NameSpace getNonBlockParent() {
        NameSpace parent = super.getParent();
        return parent instanceof BlockNameSpace ? ((BlockNameSpace) parent).getNonBlockParent() : parent;
    }

    private boolean weHaveVar(String str) {
        try {
            return super.getVariableImpl(str, false) != null;
        } catch (UtilEvalError unused) {
            return false;
        }
    }

    @Override // bsh.NameSpace
    public This getSuper(Interpreter interpreter) {
        return getNonBlockParent().getSuper(interpreter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // bsh.NameSpace
    public This getThis(Interpreter interpreter) {
        return getNonBlockParent().getThis(interpreter);
    }

    @Override // bsh.NameSpace
    public void importClass(String str) {
        getParent().importClass(str);
    }

    @Override // bsh.NameSpace
    public void importPackage(String str) {
        getParent().importPackage(str);
    }

    public void setBlockVariable(String str, Object obj) {
        super.setVariable(str, obj, false, false);
    }

    @Override // bsh.NameSpace
    public void setMethod(String str, BshMethod bshMethod) {
        getParent().setMethod(str, bshMethod);
    }

    @Override // bsh.NameSpace
    public void setVariable(String str, Object obj, boolean z, boolean z2) {
        if (weHaveVar(str)) {
            super.setVariable(str, obj, z, false);
        } else {
            getParent().setVariable(str, obj, z, z2);
        }
    }
}
