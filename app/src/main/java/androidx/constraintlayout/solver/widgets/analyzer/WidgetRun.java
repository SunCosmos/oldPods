package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* loaded from: classes.dex */
public abstract class WidgetRun implements Dependency {
    ConstraintWidget a;
    e b;

    /* renamed from: c */
    protected ConstraintWidget.DimensionBehaviour f401c;
    public int matchConstraintsType;

    /* renamed from: d */
    androidx.constraintlayout.solver.widgets.analyzer.b f402d = new androidx.constraintlayout.solver.widgets.analyzer.b(this);
    public int orientation = 0;
    boolean e = false;
    public DependencyNode start = new DependencyNode(this);
    public DependencyNode end = new DependencyNode(this);
    protected b f = b.NONE;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum b {
        NONE,
        START,
        END,
        CENTER
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.a = constraintWidget;
    }

    private void h(int i2, int i3) {
        androidx.constraintlayout.solver.widgets.analyzer.b bVar;
        int e;
        int i4 = this.matchConstraintsType;
        if (i4 != 0) {
            if (i4 == 1) {
                int e2 = e(this.f402d.h, i2);
                bVar = this.f402d;
                e = Math.min(e2, i3);
                bVar.resolve(e);
            }
            if (i4 != 2) {
                if (i4 != 3) {
                    return;
                }
                ConstraintWidget constraintWidget = this.a;
                WidgetRun widgetRun = constraintWidget.horizontalRun;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.f401c;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.matchConstraintsType == 3) {
                    VerticalWidgetRun verticalWidgetRun = constraintWidget.verticalRun;
                    if (verticalWidgetRun.f401c == dimensionBehaviour2 && verticalWidgetRun.matchConstraintsType == 3) {
                        return;
                    }
                }
                if (i2 == 0) {
                    widgetRun = constraintWidget.verticalRun;
                }
                if (widgetRun.f402d.resolved) {
                    float dimensionRatio = constraintWidget.getDimensionRatio();
                    this.f402d.resolve(i2 == 1 ? (int) ((widgetRun.f402d.value / dimensionRatio) + 0.5f) : (int) ((dimensionRatio * widgetRun.f402d.value) + 0.5f));
                    return;
                }
                return;
            }
            ConstraintWidget parent = this.a.getParent();
            if (parent == null) {
                return;
            }
            if (!(i2 == 0 ? parent.horizontalRun : parent.verticalRun).f402d.resolved) {
                return;
            }
            ConstraintWidget constraintWidget2 = this.a;
            i3 = (int) ((r9.value * (i2 == 0 ? constraintWidget2.mMatchConstraintPercentWidth : constraintWidget2.mMatchConstraintPercentHeight)) + 0.5f);
        }
        bVar = this.f402d;
        e = e(i3, i2);
        bVar.resolve(e);
    }

    public final void a(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2) {
        dependencyNode.g.add(dependencyNode2);
        dependencyNode.f392c = i2;
        dependencyNode2.f.add(dependencyNode);
    }

    public abstract void applyToWidget();

    public final void b(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i2, androidx.constraintlayout.solver.widgets.analyzer.b bVar) {
        dependencyNode.g.add(dependencyNode2);
        dependencyNode.g.add(this.f402d);
        dependencyNode.f393d = i2;
        dependencyNode.e = bVar;
        dependencyNode2.f.add(dependencyNode);
        bVar.f.add(dependencyNode);
    }

    public abstract void c();

    public abstract void d();

    public final int e(int i2, int i3) {
        int max;
        if (i3 == 0) {
            ConstraintWidget constraintWidget = this.a;
            int i4 = constraintWidget.mMatchConstraintMaxWidth;
            max = Math.max(constraintWidget.mMatchConstraintMinWidth, i2);
            if (i4 > 0) {
                max = Math.min(i4, i2);
            }
            if (max == i2) {
                return i2;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.a;
            int i5 = constraintWidget2.mMatchConstraintMaxHeight;
            max = Math.max(constraintWidget2.mMatchConstraintMinHeight, i2);
            if (i5 > 0) {
                max = Math.min(i5, i2);
            }
            if (max == i2) {
                return i2;
            }
        }
        return max;
    }

    public final DependencyNode f(ConstraintAnchor constraintAnchor) {
        WidgetRun widgetRun;
        WidgetRun widgetRun2;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        int i2 = a.a[constraintAnchor2.mType.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                widgetRun2 = constraintWidget.horizontalRun;
            } else if (i2 == 3) {
                widgetRun = constraintWidget.verticalRun;
            } else {
                if (i2 == 4) {
                    return constraintWidget.verticalRun.baseline;
                }
                if (i2 != 5) {
                    return null;
                }
                widgetRun2 = constraintWidget.verticalRun;
            }
            return widgetRun2.end;
        }
        widgetRun = constraintWidget.horizontalRun;
        return widgetRun.start;
    }

    public final DependencyNode g(ConstraintAnchor constraintAnchor, int i2) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        WidgetRun widgetRun = i2 == 0 ? constraintWidget.horizontalRun : constraintWidget.verticalRun;
        int i3 = a.a[constraintAnchor2.mType.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.end;
        }
        return widgetRun.start;
    }

    public long getWrapDimension() {
        if (this.f402d.resolved) {
            return r0.value;
        }
        return 0L;
    }

    public abstract boolean i();

    public boolean isCenterConnection() {
        int size = this.start.g.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (this.start.g.get(i3).a != this) {
                i2++;
            }
        }
        int size2 = this.end.g.size();
        for (int i4 = 0; i4 < size2; i4++) {
            if (this.end.g.get(i4).a != this) {
                i2++;
            }
        }
        return i2 >= 2;
    }

    public boolean isDimensionResolved() {
        return this.f402d.resolved;
    }

    public boolean isResolved() {
        return this.e;
    }

    public void j(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        DependencyNode dependencyNode;
        DependencyNode f = f(constraintAnchor);
        DependencyNode f2 = f(constraintAnchor2);
        if (f.resolved && f2.resolved) {
            int margin = f.value + constraintAnchor.getMargin();
            int margin2 = f2.value - constraintAnchor2.getMargin();
            int i3 = margin2 - margin;
            if (!this.f402d.resolved && this.f401c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                h(i2, i3);
            }
            androidx.constraintlayout.solver.widgets.analyzer.b bVar = this.f402d;
            if (bVar.resolved) {
                if (bVar.value == i3) {
                    this.start.resolve(margin);
                    dependencyNode = this.end;
                } else {
                    ConstraintWidget constraintWidget = this.a;
                    float horizontalBiasPercent = i2 == 0 ? constraintWidget.getHorizontalBiasPercent() : constraintWidget.getVerticalBiasPercent();
                    if (f == f2) {
                        margin = f.value;
                        margin2 = f2.value;
                        horizontalBiasPercent = 0.5f;
                    }
                    this.start.resolve((int) (margin + 0.5f + (((margin2 - margin) - this.f402d.value) * horizontalBiasPercent)));
                    dependencyNode = this.end;
                    margin2 = this.start.value + this.f402d.value;
                }
                dependencyNode.resolve(margin2);
            }
        }
    }

    public void k(Dependency dependency) {
    }

    public void l(Dependency dependency) {
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
    }

    public long wrapSize(int i2) {
        int i3;
        androidx.constraintlayout.solver.widgets.analyzer.b bVar = this.f402d;
        if (!bVar.resolved) {
            return 0L;
        }
        long j = bVar.value;
        if (isCenterConnection()) {
            i3 = this.start.f392c - this.end.f392c;
        } else {
            if (i2 != 0) {
                return j - this.end.f392c;
            }
            i3 = this.start.f392c;
        }
        return j + i3;
    }
}
