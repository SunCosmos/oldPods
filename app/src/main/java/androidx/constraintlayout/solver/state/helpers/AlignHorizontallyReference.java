package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;

/* loaded from: classes.dex */
public class AlignHorizontallyReference extends HelperReference {
    private float e;
    private Object f;
    private Object g;
    private Object h;

    /* renamed from: i, reason: collision with root package name */
    private Object f372i;

    public AlignHorizontallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
        this.e = 0.5f;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0046 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0006 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030  */
    @Override // androidx.constraintlayout.solver.state.HelperReference
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void apply() {
        /*
            r4 = this;
            java.util.ArrayList<java.lang.Object> r0 = r4.f369c
            java.util.Iterator r0 = r0.iterator()
        L6:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L4a
            java.lang.Object r1 = r0.next()
            androidx.constraintlayout.solver.state.State r2 = r4.a
            androidx.constraintlayout.solver.state.ConstraintReference r1 = r2.constraints(r1)
            r1.clearHorizontal()
            java.lang.Object r2 = r4.f
            if (r2 == 0) goto L21
        L1d:
            r1.startToStart(r2)
            goto L2c
        L21:
            java.lang.Object r2 = r4.g
            if (r2 == 0) goto L29
            r1.startToEnd(r2)
            goto L2c
        L29:
            java.lang.Integer r2 = androidx.constraintlayout.solver.state.State.PARENT
            goto L1d
        L2c:
            java.lang.Object r2 = r4.h
            if (r2 == 0) goto L34
            r1.endToStart(r2)
            goto L3e
        L34:
            java.lang.Object r2 = r4.f372i
            if (r2 == 0) goto L39
            goto L3b
        L39:
            java.lang.Integer r2 = androidx.constraintlayout.solver.state.State.PARENT
        L3b:
            r1.endToEnd(r2)
        L3e:
            float r2 = r4.e
            r3 = 1056964608(0x3f000000, float:0.5)
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 == 0) goto L6
            r1.horizontalBias(r2)
            goto L6
        L4a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.state.helpers.AlignHorizontallyReference.apply():void");
    }

    public void bias(float f) {
        this.e = f;
    }

    public void endToEnd(Object obj) {
        this.f372i = obj;
    }

    public void endToStart(Object obj) {
        this.h = obj;
    }

    public void startToEnd(Object obj) {
        this.g = obj;
    }

    public void startToStart(Object obj) {
        this.f = obj;
    }
}
