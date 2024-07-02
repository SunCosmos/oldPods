package androidx.constraintlayout.solver.widgets.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DependencyNode implements Dependency {
    WidgetRun a;

    /* renamed from: c */
    int f392c;
    public int value;
    public Dependency updateDelegate = null;
    public boolean delegateToWidgetRun = false;
    public boolean readyToSolve = false;
    a b = a.UNKNOWN;

    /* renamed from: d */
    int f393d = 1;
    b e = null;
    public boolean resolved = false;
    List<Dependency> f = new ArrayList();
    List<DependencyNode> g = new ArrayList();

    /* loaded from: classes.dex */
    public enum a {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public DependencyNode(WidgetRun widgetRun) {
        this.a = widgetRun;
    }

    public void addDependency(Dependency dependency) {
        this.f.add(dependency);
        if (this.resolved) {
            dependency.update(dependency);
        }
    }

    public void clear() {
        this.g.clear();
        this.f.clear();
        this.resolved = false;
        this.value = 0;
        this.readyToSolve = false;
        this.delegateToWidgetRun = false;
    }

    public String name() {
        StringBuilder sb;
        String str;
        String debugName = this.a.a.getDebugName();
        a aVar = this.b;
        if (aVar == a.LEFT || aVar == a.RIGHT) {
            sb = new StringBuilder();
            sb.append(debugName);
            str = "_HORIZONTAL";
        } else {
            sb = new StringBuilder();
            sb.append(debugName);
            str = "_VERTICAL";
        }
        sb.append(str);
        return sb.toString() + ":" + this.b.name();
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a.a.getDebugName());
        sb.append(":");
        sb.append(this.b);
        sb.append("(");
        sb.append(this.resolved ? Integer.valueOf(this.value) : "unresolved");
        sb.append(") <t=");
        sb.append(this.g.size());
        sb.append(":d=");
        sb.append(this.f.size());
        sb.append(">");
        return sb.toString();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        Iterator<DependencyNode> it = this.g.iterator();
        while (it.hasNext()) {
            if (!it.next().resolved) {
                return;
            }
        }
        this.readyToSolve = true;
        Dependency dependency2 = this.updateDelegate;
        if (dependency2 != null) {
            dependency2.update(this);
        }
        if (this.delegateToWidgetRun) {
            this.a.update(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i2 = 0;
        for (DependencyNode dependencyNode2 : this.g) {
            if (!(dependencyNode2 instanceof b)) {
                i2++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i2 == 1 && dependencyNode.resolved) {
            b bVar = this.e;
            if (bVar != null) {
                if (!bVar.resolved) {
                    return;
                } else {
                    this.f392c = this.f393d * bVar.value;
                }
            }
            resolve(dependencyNode.value + this.f392c);
        }
        Dependency dependency3 = this.updateDelegate;
        if (dependency3 != null) {
            dependency3.update(this);
        }
    }
}
