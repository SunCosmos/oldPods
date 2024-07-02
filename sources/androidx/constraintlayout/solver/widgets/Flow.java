package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private ConstraintWidget[] L0;
    private int o0 = -1;
    private int p0 = -1;
    private int q0 = -1;
    private int r0 = -1;
    private int s0 = -1;
    private int t0 = -1;
    private float u0 = 0.5f;
    private float v0 = 0.5f;
    private float w0 = 0.5f;
    private float x0 = 0.5f;
    private float y0 = 0.5f;
    private float z0 = 0.5f;
    private int A0 = 0;
    private int B0 = 0;
    private int C0 = 2;
    private int D0 = 2;
    private int E0 = 0;
    private int F0 = -1;
    private int G0 = 0;
    private ArrayList<a> H0 = new ArrayList<>();
    private ConstraintWidget[] I0 = null;
    private ConstraintWidget[] J0 = null;
    private int[] K0 = null;
    private int M0 = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a {
        private int a;

        /* renamed from: d, reason: collision with root package name */
        private ConstraintAnchor f387d;
        private ConstraintAnchor e;
        private ConstraintAnchor f;
        private ConstraintAnchor g;
        private int h;

        /* renamed from: i, reason: collision with root package name */
        private int f388i;
        private int j;
        private int k;
        private int q;
        private ConstraintWidget b = null;

        /* renamed from: c, reason: collision with root package name */
        int f386c = 0;
        private int l = 0;
        private int m = 0;
        private int n = 0;
        private int o = 0;
        private int p = 0;

        public a(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i3) {
            this.a = 0;
            this.h = 0;
            this.f388i = 0;
            this.j = 0;
            this.k = 0;
            this.q = 0;
            this.a = i2;
            this.f387d = constraintAnchor;
            this.e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = Flow.this.getPaddingLeft();
            this.f388i = Flow.this.getPaddingTop();
            this.j = Flow.this.getPaddingRight();
            this.k = Flow.this.getPaddingBottom();
            this.q = i3;
        }

        private void h() {
            this.l = 0;
            this.m = 0;
            this.b = null;
            this.f386c = 0;
            int i2 = this.o;
            for (int i3 = 0; i3 < i2 && this.n + i3 < Flow.this.M0; i3++) {
                ConstraintWidget constraintWidget = Flow.this.L0[this.n + i3];
                if (this.a == 0) {
                    int width = constraintWidget.getWidth();
                    int i4 = Flow.this.A0;
                    if (constraintWidget.getVisibility() == 8) {
                        i4 = 0;
                    }
                    this.l += width + i4;
                    int F = Flow.this.F(constraintWidget, this.q);
                    if (this.b == null || this.f386c < F) {
                        this.b = constraintWidget;
                        this.f386c = F;
                        this.m = F;
                    }
                } else {
                    int G = Flow.this.G(constraintWidget, this.q);
                    int F2 = Flow.this.F(constraintWidget, this.q);
                    int i5 = Flow.this.B0;
                    if (constraintWidget.getVisibility() == 8) {
                        i5 = 0;
                    }
                    this.m += F2 + i5;
                    if (this.b == null || this.f386c < G) {
                        this.b = constraintWidget;
                        this.f386c = G;
                        this.l = G;
                    }
                }
            }
        }

        public void b(ConstraintWidget constraintWidget) {
            if (this.a == 0) {
                int G = Flow.this.G(constraintWidget, this.q);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    G = 0;
                }
                this.l += G + (constraintWidget.getVisibility() != 8 ? Flow.this.A0 : 0);
                int F = Flow.this.F(constraintWidget, this.q);
                if (this.b == null || this.f386c < F) {
                    this.b = constraintWidget;
                    this.f386c = F;
                    this.m = F;
                }
            } else {
                int G2 = Flow.this.G(constraintWidget, this.q);
                int F2 = Flow.this.F(constraintWidget, this.q);
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.p++;
                    F2 = 0;
                }
                this.m += F2 + (constraintWidget.getVisibility() != 8 ? Flow.this.B0 : 0);
                if (this.b == null || this.f386c < G2) {
                    this.b = constraintWidget;
                    this.f386c = G2;
                    this.l = G2;
                }
            }
            this.o++;
        }

        public void c() {
            this.f386c = 0;
            this.b = null;
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.o = 0;
            this.p = 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:138:0x024c  */
        /* JADX WARN: Removed duplicated region for block: B:150:0x029a  */
        /* JADX WARN: Removed duplicated region for block: B:152:0x02a5  */
        /* JADX WARN: Removed duplicated region for block: B:159:0x02d0  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void d(boolean r17, int r18, boolean r19) {
            /*
                Method dump skipped, instructions count: 812
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.a.d(boolean, int, boolean):void");
        }

        public int e() {
            return this.a == 1 ? this.m - Flow.this.B0 : this.m;
        }

        public int f() {
            return this.a == 0 ? this.l - Flow.this.A0 : this.l;
        }

        public void g(int i2) {
            Flow flow;
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour;
            int width;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour;
            int i3;
            int i4 = this.p;
            if (i4 == 0) {
                return;
            }
            int i5 = this.o;
            int i6 = i2 / i4;
            for (int i7 = 0; i7 < i5 && this.n + i7 < Flow.this.M0; i7++) {
                ConstraintWidget constraintWidget = Flow.this.L0[this.n + i7];
                if (this.a == 0) {
                    if (constraintWidget != null && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                        flow = Flow.this;
                        horizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                        dimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
                        i3 = constraintWidget.getHeight();
                        width = i6;
                        flow.h(constraintWidget, horizontalDimensionBehaviour, width, dimensionBehaviour, i3);
                    }
                } else {
                    if (constraintWidget != null && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                        flow = Flow.this;
                        horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
                        width = constraintWidget.getWidth();
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                        i3 = i6;
                        flow.h(constraintWidget, horizontalDimensionBehaviour, width, dimensionBehaviour, i3);
                    }
                }
            }
            h();
        }

        public void i(int i2) {
            this.n = i2;
        }

        public void j(int i2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i3, int i4, int i5, int i6, int i7) {
            this.a = i2;
            this.f387d = constraintAnchor;
            this.e = constraintAnchor2;
            this.f = constraintAnchor3;
            this.g = constraintAnchor4;
            this.h = i3;
            this.f388i = i4;
            this.j = i5;
            this.k = i6;
            this.q = i7;
        }
    }

    private void E(boolean z) {
        ConstraintWidget constraintWidget;
        if (this.K0 == null || this.J0 == null || this.I0 == null) {
            return;
        }
        for (int i2 = 0; i2 < this.M0; i2++) {
            this.L0[i2].resetAnchors();
        }
        int[] iArr = this.K0;
        int i3 = iArr[0];
        int i4 = iArr[1];
        ConstraintWidget constraintWidget2 = null;
        for (int i5 = 0; i5 < i3; i5++) {
            ConstraintWidget constraintWidget3 = this.J0[z ? (i3 - i5) - 1 : i5];
            if (constraintWidget3 != null && constraintWidget3.getVisibility() != 8) {
                if (i5 == 0) {
                    constraintWidget3.connect(constraintWidget3.mLeft, this.mLeft, getPaddingLeft());
                    constraintWidget3.setHorizontalChainStyle(this.o0);
                    constraintWidget3.setHorizontalBiasPercent(this.u0);
                }
                if (i5 == i3 - 1) {
                    constraintWidget3.connect(constraintWidget3.mRight, this.mRight, getPaddingRight());
                }
                if (i5 > 0) {
                    constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget2.mRight, this.A0);
                    constraintWidget2.connect(constraintWidget2.mRight, constraintWidget3.mLeft, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
        }
        for (int i6 = 0; i6 < i4; i6++) {
            ConstraintWidget constraintWidget4 = this.I0[i6];
            if (constraintWidget4 != null && constraintWidget4.getVisibility() != 8) {
                if (i6 == 0) {
                    constraintWidget4.connect(constraintWidget4.mTop, this.mTop, getPaddingTop());
                    constraintWidget4.setVerticalChainStyle(this.p0);
                    constraintWidget4.setVerticalBiasPercent(this.v0);
                }
                if (i6 == i4 - 1) {
                    constraintWidget4.connect(constraintWidget4.mBottom, this.mBottom, getPaddingBottom());
                }
                if (i6 > 0) {
                    constraintWidget4.connect(constraintWidget4.mTop, constraintWidget2.mBottom, this.B0);
                    constraintWidget2.connect(constraintWidget2.mBottom, constraintWidget4.mTop, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = (i8 * i3) + i7;
                if (this.G0 == 1) {
                    i9 = (i7 * i4) + i8;
                }
                ConstraintWidget[] constraintWidgetArr = this.L0;
                if (i9 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i9]) != null && constraintWidget.getVisibility() != 8) {
                    ConstraintWidget constraintWidget5 = this.J0[i7];
                    ConstraintWidget constraintWidget6 = this.I0[i8];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.connect(constraintWidget.mLeft, constraintWidget5.mLeft, 0);
                        constraintWidget.connect(constraintWidget.mRight, constraintWidget5.mRight, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.connect(constraintWidget.mTop, constraintWidget6.mTop, 0);
                        constraintWidget.connect(constraintWidget.mBottom, constraintWidget6.mBottom, 0);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int F(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i3 = constraintWidget.mMatchConstraintDefaultHeight;
            if (i3 == 0) {
                return 0;
            }
            if (i3 == 2) {
                int i4 = (int) (constraintWidget.mMatchConstraintPercentHeight * i2);
                if (i4 != constraintWidget.getHeight()) {
                    constraintWidget.setMeasureRequested(true);
                    h(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
                return i4;
            }
            if (i3 == 1) {
                return constraintWidget.getHeight();
            }
            if (i3 == 3) {
                return (int) ((constraintWidget.getWidth() * constraintWidget.mDimensionRatio) + 0.5f);
            }
        }
        return constraintWidget.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int G(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i3 = constraintWidget.mMatchConstraintDefaultWidth;
            if (i3 == 0) {
                return 0;
            }
            if (i3 == 2) {
                int i4 = (int) (constraintWidget.mMatchConstraintPercentWidth * i2);
                if (i4 != constraintWidget.getWidth()) {
                    constraintWidget.setMeasureRequested(true);
                    h(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                }
                return i4;
            }
            if (i3 == 1) {
                return constraintWidget.getWidth();
            }
            if (i3 == 3) {
                return (int) ((constraintWidget.getHeight() * constraintWidget.mDimensionRatio) + 0.5f);
            }
        }
        return constraintWidget.getWidth();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:76:0x0119 -> B:21:0x0061). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:77:0x011b -> B:21:0x0061). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:79:0x0121 -> B:21:0x0061). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:80:0x0123 -> B:21:0x0061). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void H(androidx.constraintlayout.solver.widgets.ConstraintWidget[] r17, int r18, int r19, int r20, int[] r21) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.H(androidx.constraintlayout.solver.widgets.ConstraintWidget[], int, int, int, int[]):void");
    }

    private void I(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6;
        ConstraintAnchor constraintAnchor;
        int paddingRight;
        ConstraintAnchor constraintAnchor2;
        int paddingBottom;
        int i7;
        if (i2 == 0) {
            return;
        }
        this.H0.clear();
        a aVar = new a(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, i4);
        this.H0.add(aVar);
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        if (i3 == 0) {
            while (i10 < i2) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i10];
                int G = G(constraintWidget, i4);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i8++;
                }
                int i11 = i8;
                boolean z = (i9 == i4 || (this.A0 + i9) + G > i4) && aVar.b != null;
                if (!z && i10 > 0 && (i7 = this.F0) > 0 && i10 % i7 == 0) {
                    z = true;
                }
                if (z) {
                    aVar = new a(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, i4);
                    aVar.i(i10);
                    this.H0.add(aVar);
                } else if (i10 > 0) {
                    i9 += this.A0 + G;
                    aVar.b(constraintWidget);
                    i10++;
                    i8 = i11;
                }
                i9 = G;
                aVar.b(constraintWidget);
                i10++;
                i8 = i11;
            }
        } else {
            while (i10 < i2) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i10];
                int F = F(constraintWidget2, i4);
                if (constraintWidget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i8++;
                }
                int i12 = i8;
                boolean z2 = (i9 == i4 || (this.B0 + i9) + F > i4) && aVar.b != null;
                if (!z2 && i10 > 0 && (i5 = this.F0) > 0 && i10 % i5 == 0) {
                    z2 = true;
                }
                if (z2) {
                    aVar = new a(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, i4);
                    aVar.i(i10);
                    this.H0.add(aVar);
                } else if (i10 > 0) {
                    i9 += this.B0 + F;
                    aVar.b(constraintWidget2);
                    i10++;
                    i8 = i12;
                }
                i9 = F;
                aVar.b(constraintWidget2);
                i10++;
                i8 = i12;
            }
        }
        int size = this.H0.size();
        ConstraintAnchor constraintAnchor3 = this.mLeft;
        ConstraintAnchor constraintAnchor4 = this.mTop;
        ConstraintAnchor constraintAnchor5 = this.mRight;
        ConstraintAnchor constraintAnchor6 = this.mBottom;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight2 = getPaddingRight();
        int paddingBottom2 = getPaddingBottom();
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z3 = horizontalDimensionBehaviour == dimensionBehaviour || getVerticalDimensionBehaviour() == dimensionBehaviour;
        if (i8 > 0 && z3) {
            for (int i13 = 0; i13 < size; i13++) {
                a aVar2 = this.H0.get(i13);
                aVar2.g(i4 - (i3 == 0 ? aVar2.f() : aVar2.e()));
            }
        }
        int i14 = paddingTop;
        int i15 = paddingRight2;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = paddingLeft;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor3;
        int i20 = paddingBottom2;
        while (i18 < size) {
            a aVar3 = this.H0.get(i18);
            if (i3 == 0) {
                if (i18 < size - 1) {
                    constraintAnchor2 = this.H0.get(i18 + 1).b.mTop;
                    paddingBottom = 0;
                } else {
                    constraintAnchor2 = this.mBottom;
                    paddingBottom = getPaddingBottom();
                }
                ConstraintAnchor constraintAnchor9 = aVar3.b.mBottom;
                ConstraintAnchor constraintAnchor10 = constraintAnchor8;
                ConstraintAnchor constraintAnchor11 = constraintAnchor8;
                int i21 = i16;
                ConstraintAnchor constraintAnchor12 = constraintAnchor7;
                int i22 = i17;
                ConstraintAnchor constraintAnchor13 = constraintAnchor5;
                ConstraintAnchor constraintAnchor14 = constraintAnchor5;
                i6 = i18;
                aVar3.j(i3, constraintAnchor10, constraintAnchor12, constraintAnchor13, constraintAnchor2, i19, i14, i15, paddingBottom, i4);
                int max = Math.max(i22, aVar3.f());
                i16 = i21 + aVar3.e();
                if (i6 > 0) {
                    i16 += this.B0;
                }
                constraintAnchor8 = constraintAnchor11;
                i17 = max;
                constraintAnchor7 = constraintAnchor9;
                i14 = 0;
                constraintAnchor = constraintAnchor14;
                int i23 = paddingBottom;
                constraintAnchor6 = constraintAnchor2;
                i20 = i23;
            } else {
                ConstraintAnchor constraintAnchor15 = constraintAnchor8;
                int i24 = i16;
                int i25 = i17;
                i6 = i18;
                if (i6 < size - 1) {
                    constraintAnchor = this.H0.get(i6 + 1).b.mLeft;
                    paddingRight = 0;
                } else {
                    constraintAnchor = this.mRight;
                    paddingRight = getPaddingRight();
                }
                ConstraintAnchor constraintAnchor16 = aVar3.b.mRight;
                aVar3.j(i3, constraintAnchor15, constraintAnchor7, constraintAnchor, constraintAnchor6, i19, i14, paddingRight, i20, i4);
                i17 = i25 + aVar3.f();
                int max2 = Math.max(i24, aVar3.e());
                if (i6 > 0) {
                    i17 += this.A0;
                }
                i16 = max2;
                i15 = paddingRight;
                constraintAnchor8 = constraintAnchor16;
                i19 = 0;
            }
            i18 = i6 + 1;
            constraintAnchor5 = constraintAnchor;
        }
        iArr[0] = i17;
        iArr[1] = i16;
    }

    private void J(ConstraintWidget[] constraintWidgetArr, int i2, int i3, int i4, int[] iArr) {
        a aVar;
        if (i2 == 0) {
            return;
        }
        if (this.H0.size() == 0) {
            aVar = new a(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, i4);
            this.H0.add(aVar);
        } else {
            a aVar2 = this.H0.get(0);
            aVar2.c();
            aVar = aVar2;
            aVar.j(i3, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), i4);
        }
        for (int i5 = 0; i5 < i2; i5++) {
            aVar.b(constraintWidgetArr[i5]);
        }
        iArr[0] = aVar.f();
        iArr[1] = aVar.e();
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        super.addToSolver(linearSystem, z);
        boolean isRtl = getParent() != null ? ((ConstraintWidgetContainer) getParent()).isRtl() : false;
        int i2 = this.E0;
        if (i2 != 0) {
            if (i2 == 1) {
                int size = this.H0.size();
                int i3 = 0;
                while (i3 < size) {
                    this.H0.get(i3).d(isRtl, i3, i3 == size + (-1));
                    i3++;
                }
            } else if (i2 == 2) {
                E(isRtl);
            }
        } else if (this.H0.size() > 0) {
            this.H0.get(0).d(isRtl, 0, true);
        }
        j(false);
    }

    @Override // androidx.constraintlayout.solver.widgets.HelperWidget, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Flow flow = (Flow) constraintWidget;
        this.o0 = flow.o0;
        this.p0 = flow.p0;
        this.q0 = flow.q0;
        this.r0 = flow.r0;
        this.s0 = flow.s0;
        this.t0 = flow.t0;
        this.u0 = flow.u0;
        this.v0 = flow.v0;
        this.w0 = flow.w0;
        this.x0 = flow.x0;
        this.y0 = flow.y0;
        this.z0 = flow.z0;
        this.A0 = flow.A0;
        this.B0 = flow.B0;
        this.C0 = flow.C0;
        this.D0 = flow.D0;
        this.E0 = flow.E0;
        this.F0 = flow.F0;
        this.G0 = flow.G0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
    
        if (r18.p0 == (-1)) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0054, code lost:
    
        r18.p0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0052, code lost:
    
        if (r18.p0 == (-1)) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00da  */
    @Override // androidx.constraintlayout.solver.widgets.VirtualLayout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void measure(int r19, int r20, int r21, int r22) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Flow.measure(int, int, int, int):void");
    }

    public void setFirstHorizontalBias(float f) {
        this.w0 = f;
    }

    public void setFirstHorizontalStyle(int i2) {
        this.q0 = i2;
    }

    public void setFirstVerticalBias(float f) {
        this.x0 = f;
    }

    public void setFirstVerticalStyle(int i2) {
        this.r0 = i2;
    }

    public void setHorizontalAlign(int i2) {
        this.C0 = i2;
    }

    public void setHorizontalBias(float f) {
        this.u0 = f;
    }

    public void setHorizontalGap(int i2) {
        this.A0 = i2;
    }

    public void setHorizontalStyle(int i2) {
        this.o0 = i2;
    }

    public void setLastHorizontalBias(float f) {
        this.y0 = f;
    }

    public void setLastHorizontalStyle(int i2) {
        this.s0 = i2;
    }

    public void setLastVerticalBias(float f) {
        this.z0 = f;
    }

    public void setLastVerticalStyle(int i2) {
        this.t0 = i2;
    }

    public void setMaxElementsWrap(int i2) {
        this.F0 = i2;
    }

    public void setOrientation(int i2) {
        this.G0 = i2;
    }

    public void setVerticalAlign(int i2) {
        this.D0 = i2;
    }

    public void setVerticalBias(float f) {
        this.v0 = f;
    }

    public void setVerticalGap(int i2) {
        this.B0 = i2;
    }

    public void setVerticalStyle(int i2) {
        this.p0 = i2;
    }

    public void setWrapMode(int i2) {
        this.E0 = i2;
    }
}
