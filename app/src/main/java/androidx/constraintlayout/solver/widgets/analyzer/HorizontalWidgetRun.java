package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.List;

/* loaded from: classes.dex */
public class HorizontalWidgetRun extends WidgetRun {
    private static int[] g = new int[2];

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

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.b = DependencyNode.a.LEFT;
        this.end.b = DependencyNode.a.RIGHT;
        this.orientation = 0;
    }

    private void m(int[] iArr, int i2, int i3, int i4, int i5, float f, int i6) {
        int i7 = i3 - i2;
        int i8 = i5 - i4;
        if (i6 != -1) {
            if (i6 == 0) {
                iArr[0] = (int) ((i8 * f) + 0.5f);
                iArr[1] = i8;
                return;
            } else {
                if (i6 != 1) {
                    return;
                }
                iArr[0] = i7;
                iArr[1] = (int) ((i7 * f) + 0.5f);
                return;
            }
        }
        int i9 = (int) ((i8 * f) + 0.5f);
        int i10 = (int) ((i7 / f) + 0.5f);
        if (i9 <= i7 && i8 <= i8) {
            iArr[0] = i9;
            iArr[1] = i8;
        } else {
            if (i7 > i7 || i10 > i8) {
                return;
            }
            iArr[0] = i7;
            iArr[1] = i10;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.a.setX(dependencyNode.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void c() {
        ConstraintWidget parent;
        DependencyNode dependencyNode;
        DependencyNode dependencyNode2;
        int x;
        DependencyNode dependencyNode3;
        ConstraintAnchor constraintAnchor;
        List<Dependency> list;
        Dependency dependency;
        DependencyNode dependencyNode4;
        DependencyNode dependencyNode5;
        DependencyNode dependencyNode6;
        int x2;
        DependencyNode dependencyNode7;
        DependencyNode dependencyNode8;
        int i2;
        ConstraintWidget parent2;
        ConstraintWidget constraintWidget = this.a;
        if (constraintWidget.measured) {
            this.f402d.resolve(constraintWidget.getWidth());
        }
        if (this.f402d.resolved) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.f401c;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            if (dimensionBehaviour == dimensionBehaviour2 && (((parent = this.a.getParent()) != null && parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) || parent.getHorizontalDimensionBehaviour() == dimensionBehaviour2)) {
                a(this.start, parent.horizontalRun.start, this.a.mLeft.getMargin());
                a(this.end, parent.horizontalRun.end, -this.a.mRight.getMargin());
                return;
            }
        } else {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = this.a.getHorizontalDimensionBehaviour();
            this.f401c = horizontalDimensionBehaviour;
            if (horizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if (horizontalDimensionBehaviour == dimensionBehaviour3 && (((parent2 = this.a.getParent()) != null && parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) || parent2.getHorizontalDimensionBehaviour() == dimensionBehaviour3)) {
                    int width = (parent2.getWidth() - this.a.mLeft.getMargin()) - this.a.mRight.getMargin();
                    a(this.start, parent2.horizontalRun.start, this.a.mLeft.getMargin());
                    a(this.end, parent2.horizontalRun.end, -this.a.mRight.getMargin());
                    this.f402d.resolve(width);
                    return;
                }
                if (this.f401c == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.f402d.resolve(this.a.getWidth());
                }
            }
        }
        b bVar = this.f402d;
        if (bVar.resolved) {
            ConstraintWidget constraintWidget2 = this.a;
            if (constraintWidget2.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget2.mListAnchors;
                if (constraintAnchorArr[0].mTarget != null && constraintAnchorArr[1].mTarget != null) {
                    if (constraintWidget2.isInHorizontalChain()) {
                        this.start.f392c = this.a.mListAnchors[0].getMargin();
                        dependencyNode3 = this.end;
                        constraintAnchor = this.a.mListAnchors[1];
                        dependencyNode3.f392c = -constraintAnchor.getMargin();
                        return;
                    }
                    DependencyNode f = f(this.a.mListAnchors[0]);
                    if (f != null) {
                        a(this.start, f, this.a.mListAnchors[0].getMargin());
                    }
                    DependencyNode f2 = f(this.a.mListAnchors[1]);
                    if (f2 != null) {
                        a(this.end, f2, -this.a.mListAnchors[1].getMargin());
                    }
                    this.start.delegateToWidgetRun = true;
                    this.end.delegateToWidgetRun = true;
                    return;
                }
                if (constraintAnchorArr[0].mTarget != null) {
                    dependencyNode5 = f(constraintAnchorArr[0]);
                    if (dependencyNode5 == null) {
                        return;
                    }
                    dependencyNode6 = this.start;
                    x2 = this.a.mListAnchors[0].getMargin();
                } else {
                    if (constraintAnchorArr[1].mTarget != null) {
                        DependencyNode f3 = f(constraintAnchorArr[1]);
                        if (f3 != null) {
                            a(this.end, f3, -this.a.mListAnchors[1].getMargin());
                            dependencyNode7 = this.start;
                            dependencyNode8 = this.end;
                            i2 = -this.f402d.value;
                            a(dependencyNode7, dependencyNode8, i2);
                            return;
                        }
                        return;
                    }
                    if ((constraintWidget2 instanceof Helper) || constraintWidget2.getParent() == null || this.a.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) {
                        return;
                    }
                    dependencyNode5 = this.a.getParent().horizontalRun.start;
                    dependencyNode6 = this.start;
                    x2 = this.a.getX();
                }
                a(dependencyNode6, dependencyNode5, x2);
                dependencyNode7 = this.end;
                dependencyNode8 = this.start;
                i2 = this.f402d.value;
                a(dependencyNode7, dependencyNode8, i2);
                return;
            }
        }
        if (this.f401c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            ConstraintWidget constraintWidget3 = this.a;
            int i3 = constraintWidget3.mMatchConstraintDefaultWidth;
            if (i3 == 2) {
                ConstraintWidget parent3 = constraintWidget3.getParent();
                if (parent3 != null) {
                    b bVar2 = parent3.verticalRun.f402d;
                    this.f402d.g.add(bVar2);
                    bVar2.f.add(this.f402d);
                    b bVar3 = this.f402d;
                    bVar3.delegateToWidgetRun = true;
                    bVar3.f.add(this.start);
                    list = this.f402d.f;
                    dependency = this.end;
                    list.add(dependency);
                }
            } else if (i3 == 3) {
                if (constraintWidget3.mMatchConstraintDefaultHeight == 3) {
                    this.start.updateDelegate = this;
                    this.end.updateDelegate = this;
                    VerticalWidgetRun verticalWidgetRun = constraintWidget3.verticalRun;
                    verticalWidgetRun.start.updateDelegate = this;
                    verticalWidgetRun.end.updateDelegate = this;
                    bVar.updateDelegate = this;
                    if (constraintWidget3.isInVerticalChain()) {
                        this.f402d.g.add(this.a.verticalRun.f402d);
                        this.a.verticalRun.f402d.f.add(this.f402d);
                        VerticalWidgetRun verticalWidgetRun2 = this.a.verticalRun;
                        verticalWidgetRun2.f402d.updateDelegate = this;
                        this.f402d.g.add(verticalWidgetRun2.start);
                        this.f402d.g.add(this.a.verticalRun.end);
                        this.a.verticalRun.start.f.add(this.f402d);
                        list = this.a.verticalRun.end.f;
                        dependency = this.f402d;
                        list.add(dependency);
                    } else if (this.a.isInHorizontalChain()) {
                        this.a.verticalRun.f402d.g.add(this.f402d);
                        list = this.f402d.f;
                        dependency = this.a.verticalRun.f402d;
                        list.add(dependency);
                    } else {
                        dependencyNode4 = this.a.verticalRun.f402d;
                    }
                } else {
                    b bVar4 = constraintWidget3.verticalRun.f402d;
                    bVar.g.add(bVar4);
                    bVar4.f.add(this.f402d);
                    this.a.verticalRun.start.f.add(this.f402d);
                    this.a.verticalRun.end.f.add(this.f402d);
                    b bVar5 = this.f402d;
                    bVar5.delegateToWidgetRun = true;
                    bVar5.f.add(this.start);
                    this.f402d.f.add(this.end);
                    this.start.g.add(this.f402d);
                    dependencyNode4 = this.end;
                }
                list = dependencyNode4.g;
                dependency = this.f402d;
                list.add(dependency);
            }
            dependencyNode3.f392c = -constraintAnchor.getMargin();
            return;
        }
        ConstraintWidget constraintWidget4 = this.a;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget4.mListAnchors;
        if (constraintAnchorArr2[0].mTarget != null && constraintAnchorArr2[1].mTarget != null) {
            if (constraintWidget4.isInHorizontalChain()) {
                this.start.f392c = this.a.mListAnchors[0].getMargin();
                dependencyNode3 = this.end;
                constraintAnchor = this.a.mListAnchors[1];
                dependencyNode3.f392c = -constraintAnchor.getMargin();
                return;
            }
            DependencyNode f4 = f(this.a.mListAnchors[0]);
            DependencyNode f5 = f(this.a.mListAnchors[1]);
            f4.addDependency(this);
            f5.addDependency(this);
            this.f = WidgetRun.b.CENTER;
            return;
        }
        if (constraintAnchorArr2[0].mTarget != null) {
            dependencyNode = f(constraintAnchorArr2[0]);
            if (dependencyNode == null) {
                return;
            }
            dependencyNode2 = this.start;
            x = this.a.mListAnchors[0].getMargin();
        } else {
            if (constraintAnchorArr2[1].mTarget != null) {
                DependencyNode f6 = f(constraintAnchorArr2[1]);
                if (f6 != null) {
                    a(this.end, f6, -this.a.mListAnchors[1].getMargin());
                    b(this.start, this.end, -1, this.f402d);
                    return;
                }
                return;
            }
            if ((constraintWidget4 instanceof Helper) || constraintWidget4.getParent() == null) {
                return;
            }
            dependencyNode = this.a.getParent().horizontalRun.start;
            dependencyNode2 = this.start;
            x = this.a.getX();
        }
        a(dependencyNode2, dependencyNode, x);
        b(this.end, this.start, 1, this.f402d);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        this.b = null;
        this.start.clear();
        this.end.clear();
        this.f402d.clear();
        this.e = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean i() {
        return this.f401c != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.a.mMatchConstraintDefaultWidth == 0;
    }

    public void n() {
        this.e = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.f402d.resolved = false;
    }

    public String toString() {
        return "HorizontalRun " + this.a.getDebugName();
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x0295, code lost:
    
        if (r14 != 1) goto L322;
     */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void update(androidx.constraintlayout.solver.widgets.analyzer.Dependency r17) {
        /*
            Method dump skipped, instructions count: 1040
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun.update(androidx.constraintlayout.solver.widgets.analyzer.Dependency):void");
    }
}
