package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Guideline;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c extends WidgetRun {
    public c(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.horizontalRun.d();
        constraintWidget.verticalRun.d();
        this.orientation = ((Guideline) constraintWidget).getOrientation();
    }

    private void m(DependencyNode dependencyNode) {
        this.start.f.add(dependencyNode);
        dependencyNode.g.add(this.start);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if (((Guideline) this.a).getOrientation() == 1) {
            this.a.setX(this.start.value);
        } else {
            this.a.setY(this.start.value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void c() {
        DependencyNode dependencyNode;
        WidgetRun widgetRun;
        DependencyNode dependencyNode2;
        Guideline guideline = (Guideline) this.a;
        int relativeBegin = guideline.getRelativeBegin();
        int relativeEnd = guideline.getRelativeEnd();
        guideline.getRelativePercent();
        if (guideline.getOrientation() == 1) {
            DependencyNode dependencyNode3 = this.start;
            if (relativeBegin != -1) {
                dependencyNode3.g.add(this.a.mParent.horizontalRun.start);
                this.a.mParent.horizontalRun.start.f.add(this.start);
                dependencyNode2 = this.start;
            } else if (relativeEnd != -1) {
                dependencyNode3.g.add(this.a.mParent.horizontalRun.end);
                this.a.mParent.horizontalRun.end.f.add(this.start);
                dependencyNode2 = this.start;
                relativeBegin = -relativeEnd;
            } else {
                dependencyNode3.delegateToWidgetRun = true;
                dependencyNode3.g.add(this.a.mParent.horizontalRun.end);
                this.a.mParent.horizontalRun.end.f.add(this.start);
                m(this.a.horizontalRun.start);
                widgetRun = this.a.horizontalRun;
            }
            dependencyNode2.f392c = relativeBegin;
            m(this.a.horizontalRun.start);
            widgetRun = this.a.horizontalRun;
        } else {
            DependencyNode dependencyNode4 = this.start;
            if (relativeBegin != -1) {
                dependencyNode4.g.add(this.a.mParent.verticalRun.start);
                this.a.mParent.verticalRun.start.f.add(this.start);
                dependencyNode = this.start;
            } else if (relativeEnd != -1) {
                dependencyNode4.g.add(this.a.mParent.verticalRun.end);
                this.a.mParent.verticalRun.end.f.add(this.start);
                dependencyNode = this.start;
                relativeBegin = -relativeEnd;
            } else {
                dependencyNode4.delegateToWidgetRun = true;
                dependencyNode4.g.add(this.a.mParent.verticalRun.end);
                this.a.mParent.verticalRun.end.f.add(this.start);
                m(this.a.verticalRun.start);
                widgetRun = this.a.verticalRun;
            }
            dependencyNode.f392c = relativeBegin;
            m(this.a.verticalRun.start);
            widgetRun = this.a.verticalRun;
        }
        m(widgetRun.end);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        this.start.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean i() {
        return false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve && !dependencyNode.resolved) {
            this.start.resolve((int) ((dependencyNode.g.get(0).value * ((Guideline) this.a).getRelativePercent()) + 0.5f));
        }
    }
}
