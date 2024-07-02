package c.a.a.c0.c;

import c.a.a.r;

/* loaded from: classes.dex */
public final class i {
    private final boolean a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(boolean z) {
        this.a = z;
    }

    public void a(r[] rVarArr) {
        if (!this.a || rVarArr == null || rVarArr.length < 3) {
            return;
        }
        r rVar = rVarArr[0];
        rVarArr[0] = rVarArr[2];
        rVarArr[2] = rVar;
    }
}
