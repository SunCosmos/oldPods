package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* loaded from: classes.dex */
public class VerticalWidgetRun extends WidgetRun {
    public DependencyNode baseline;
    b g;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[WidgetRun.b.values().length];
            a = iArr;
            try {
                iArr[WidgetRun.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[WidgetRun.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[WidgetRun.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public VerticalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.baseline = dependencyNode;
        this.g = null;
        this.start.b = DependencyNode.a.TOP;
        this.end.b = DependencyNode.a.BOTTOM;
        dependencyNode.b = DependencyNode.a.BASELINE;
        this.orientation = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.a.setY(dependencyNode.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x02e3, code lost:
    
        if (r9.a.hasBaseline() != false) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02e5, code lost:
    
        r0 = r9.baseline;
        r1 = r9.start;
        r2 = r9.g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x033e, code lost:
    
        if (r0.f401c == r1) goto L320;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x03e9, code lost:
    
        r0.f402d.f.add(r9.f402d);
        r9.f402d.g.add(r9.a.horizontalRun.f402d);
        r9.f402d.updateDelegate = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0370, code lost:
    
        if (r9.a.hasBaseline() != false) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x03e7, code lost:
    
        if (r0.f401c == r1) goto L320;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c() {
        /*
            Method dump skipped, instructions count: 1042
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun.c():void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        this.b = null;
        this.start.clear();
        this.end.clear();
        this.baseline.clear();
        this.f402d.clear();
        this.e = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean i() {
        return this.f401c != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.a.mMatchConstraintDefaultHeight == 0;
    }

    public void m() {
        this.e = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.baseline.clear();
        this.baseline.resolved = false;
        this.f402d.resolved = false;
    }

    public String toString() {
        return "VerticalRun " + this.a.getDebugName();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        int i2;
        float dimensionRatio;
        int i3 = a.a[this.f.ordinal()];
        if (i3 == 1) {
            l(dependency);
        } else if (i3 == 2) {
            k(dependency);
        } else if (i3 == 3) {
            ConstraintWidget constraintWidget = this.a;
            j(dependency, constraintWidget.mTop, constraintWidget.mBottom, 1);
            return;
        }
        b bVar = this.f402d;
        if (bVar.readyToSolve && !bVar.resolved && this.f401c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget2 = this.a;
            int i4 = constraintWidget2.mMatchConstraintDefaultHeight;
            if (i4 == 2) {
                ConstraintWidget parent = constraintWidget2.getParent();
                if (parent != null) {
                    if (parent.verticalRun.f402d.resolved) {
                        i2 = (int) ((r7.value * this.a.mMatchConstraintPercentHeight) + 0.5f);
                        this.f402d.resolve(i2);
                    }
                }
            } else if (i4 == 3 && constraintWidget2.horizontalRun.f402d.resolved) {
                int dimensionRatioSide = constraintWidget2.getDimensionRatioSide();
                if (dimensionRatioSide != -1) {
                    if (dimensionRatioSide == 0) {
                        dimensionRatio = r7.horizontalRun.f402d.value * this.a.getDimensionRatio();
                        i2 = (int) (dimensionRatio + 0.5f);
                        this.f402d.resolve(i2);
                    } else if (dimensionRatioSide != 1) {
                        i2 = 0;
                        this.f402d.resolve(i2);
                    }
                }
                dimensionRatio = r7.horizontalRun.f402d.value / this.a.getDimensionRatio();
                i2 = (int) (dimensionRatio + 0.5f);
                this.f402d.resolve(i2);
            }
        }
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve) {
            DependencyNode dependencyNode2 = this.end;
            if (dependencyNode2.readyToSolve) {
                if (dependencyNode.resolved && dependencyNode2.resolved && this.f402d.resolved) {
                    return;
                }
                if (!this.f402d.resolved && this.f401c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    ConstraintWidget constraintWidget3 = this.a;
                    if (constraintWidget3.mMatchConstraintDefaultWidth == 0 && !constraintWidget3.isInVerticalChain()) {
                        DependencyNode dependencyNode3 = this.start.g.get(0);
                        DependencyNode dependencyNode4 = this.end.g.get(0);
                        int i5 = dependencyNode3.value;
                        DependencyNode dependencyNode5 = this.start;
                        int i6 = i5 + dependencyNode5.f392c;
                        int i7 = dependencyNode4.value + this.end.f392c;
                        dependencyNode5.resolve(i6);
                        this.end.resolve(i7);
                        this.f402d.resolve(i7 - i6);
                        return;
                    }
                }
                if (!this.f402d.resolved && this.f401c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.matchConstraintsType == 1 && this.start.g.size() > 0 && this.end.g.size() > 0) {
                    DependencyNode dependencyNode6 = this.start.g.get(0);
                    int i8 = (this.end.g.get(0).value + this.end.f392c) - (dependencyNode6.value + this.start.f392c);
                    b bVar2 = this.f402d;
                    int i9 = bVar2.h;
                    if (i8 < i9) {
                        bVar2.resolve(i8);
                    } else {
                        bVar2.resolve(i9);
                    }
                }
                if (this.f402d.resolved && this.start.g.size() > 0 && this.end.g.size() > 0) {
                    DependencyNode dependencyNode7 = this.start.g.get(0);
                    DependencyNode dependencyNode8 = this.end.g.get(0);
                    int i10 = dependencyNode7.value + this.start.f392c;
                    int i11 = dependencyNode8.value + this.end.f392c;
                    float verticalBiasPercent = this.a.getVerticalBiasPercent();
                    if (dependencyNode7 == dependencyNode8) {
                        i10 = dependencyNode7.value;
                        i11 = dependencyNode8.value;
                        verticalBiasPercent = 0.5f;
                    }
                    this.start.resolve((int) (i10 + 0.5f + (((i11 - i10) - this.f402d.value) * verticalBiasPercent)));
                    this.end.resolve(this.start.value + this.f402d.value);
                }
            }
        }
    }
}
