package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import java.util.Iterator;

/* loaded from: classes.dex */
public class d extends WidgetRun {
    public d(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    private void m(DependencyNode dependencyNode) {
        this.start.f.add(dependencyNode);
        dependencyNode.g.add(this.start);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        ConstraintWidget constraintWidget = this.a;
        if (constraintWidget instanceof Barrier) {
            int barrierType = ((Barrier) constraintWidget).getBarrierType();
            if (barrierType == 0 || barrierType == 1) {
                this.a.setX(this.start.value);
            } else {
                this.a.setY(this.start.value);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void c() {
        WidgetRun widgetRun;
        ConstraintWidget constraintWidget = this.a;
        if (constraintWidget instanceof Barrier) {
            this.start.delegateToWidgetRun = true;
            Barrier barrier = (Barrier) constraintWidget;
            int barrierType = barrier.getBarrierType();
            boolean allowsGoneWidget = barrier.allowsGoneWidget();
            int i2 = 0;
            if (barrierType == 0) {
                this.start.b = DependencyNode.a.LEFT;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget2 = barrier.mWidgets[i2];
                    if (allowsGoneWidget || constraintWidget2.getVisibility() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.horizontalRun.start;
                        dependencyNode.f.add(this.start);
                        this.start.g.add(dependencyNode);
                    }
                    i2++;
                }
            } else {
                if (barrierType != 1) {
                    if (barrierType == 2) {
                        this.start.b = DependencyNode.a.TOP;
                        while (i2 < barrier.mWidgetsCount) {
                            ConstraintWidget constraintWidget3 = barrier.mWidgets[i2];
                            if (allowsGoneWidget || constraintWidget3.getVisibility() != 8) {
                                DependencyNode dependencyNode2 = constraintWidget3.verticalRun.start;
                                dependencyNode2.f.add(this.start);
                                this.start.g.add(dependencyNode2);
                            }
                            i2++;
                        }
                    } else {
                        if (barrierType != 3) {
                            return;
                        }
                        this.start.b = DependencyNode.a.BOTTOM;
                        while (i2 < barrier.mWidgetsCount) {
                            ConstraintWidget constraintWidget4 = barrier.mWidgets[i2];
                            if (allowsGoneWidget || constraintWidget4.getVisibility() != 8) {
                                DependencyNode dependencyNode3 = constraintWidget4.verticalRun.end;
                                dependencyNode3.f.add(this.start);
                                this.start.g.add(dependencyNode3);
                            }
                            i2++;
                        }
                    }
                    m(this.a.verticalRun.start);
                    widgetRun = this.a.verticalRun;
                    m(widgetRun.end);
                }
                this.start.b = DependencyNode.a.RIGHT;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget5 = barrier.mWidgets[i2];
                    if (allowsGoneWidget || constraintWidget5.getVisibility() != 8) {
                        DependencyNode dependencyNode4 = constraintWidget5.horizontalRun.end;
                        dependencyNode4.f.add(this.start);
                        this.start.g.add(dependencyNode4);
                    }
                    i2++;
                }
            }
            m(this.a.horizontalRun.start);
            widgetRun = this.a.horizontalRun;
            m(widgetRun.end);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        this.b = null;
        this.start.clear();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean i() {
        return false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        Barrier barrier = (Barrier) this.a;
        int barrierType = barrier.getBarrierType();
        Iterator<DependencyNode> it = this.start.g.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = it.next().value;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (barrierType == 0 || barrierType == 2) {
            this.start.resolve(i3 + barrier.getMargin());
        } else {
            this.start.resolve(i2 + barrier.getMargin());
        }
    }
}
