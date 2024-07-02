package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b extends DependencyNode {
    public int h;

    public b(WidgetRun widgetRun) {
        super(widgetRun);
        this.b = widgetRun instanceof HorizontalWidgetRun ? DependencyNode.a.HORIZONTAL_DIMENSION : DependencyNode.a.VERTICAL_DIMENSION;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.DependencyNode
    public void resolve(int i2) {
        if (this.resolved) {
            return;
        }
        this.resolved = true;
        this.value = i2;
        for (Dependency dependency : this.f) {
            dependency.update(dependency);
        }
    }
}
