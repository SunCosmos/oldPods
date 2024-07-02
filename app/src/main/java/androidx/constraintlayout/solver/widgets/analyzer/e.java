package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e {

    /* renamed from: d, reason: collision with root package name */
    public static int f405d;
    public boolean a;
    WidgetRun b;

    /* renamed from: c, reason: collision with root package name */
    ArrayList<WidgetRun> f406c = new ArrayList<>();

    public e(WidgetRun widgetRun, int i2) {
        this.b = null;
        f405d++;
        this.b = widgetRun;
    }

    private boolean c(WidgetRun widgetRun, int i2) {
        DependencyNode dependencyNode;
        WidgetRun widgetRun2;
        DependencyNode dependencyNode2;
        WidgetRun widgetRun3;
        if (!widgetRun.a.isTerminalWidget[i2]) {
            return false;
        }
        for (Dependency dependency : widgetRun.start.f) {
            if ((dependency instanceof DependencyNode) && (widgetRun3 = (dependencyNode2 = (DependencyNode) dependency).a) != widgetRun && dependencyNode2 == widgetRun3.start) {
                if (widgetRun instanceof ChainRun) {
                    Iterator<WidgetRun> it = ((ChainRun) widgetRun).g.iterator();
                    while (it.hasNext()) {
                        c(it.next(), i2);
                    }
                } else if (!(widgetRun instanceof d)) {
                    widgetRun.a.isTerminalWidget[i2] = false;
                }
                c(dependencyNode2.a, i2);
            }
        }
        for (Dependency dependency2 : widgetRun.end.f) {
            if ((dependency2 instanceof DependencyNode) && (widgetRun2 = (dependencyNode = (DependencyNode) dependency2).a) != widgetRun && dependencyNode == widgetRun2.start) {
                if (widgetRun instanceof ChainRun) {
                    Iterator<WidgetRun> it2 = ((ChainRun) widgetRun).g.iterator();
                    while (it2.hasNext()) {
                        c(it2.next(), i2);
                    }
                } else if (!(widgetRun instanceof d)) {
                    widgetRun.a.isTerminalWidget[i2] = false;
                }
                c(dependencyNode.a, i2);
            }
        }
        return false;
    }

    private long e(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.a;
        if (widgetRun instanceof d) {
            return j;
        }
        int size = dependencyNode.f.size();
        long j2 = j;
        for (int i2 = 0; i2 < size; i2++) {
            Dependency dependency = dependencyNode.f.get(i2);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.a != widgetRun) {
                    j2 = Math.min(j2, e(dependencyNode2, dependencyNode2.f392c + j));
                }
            }
        }
        if (dependencyNode != widgetRun.end) {
            return j2;
        }
        long wrapDimension = j - widgetRun.getWrapDimension();
        return Math.min(Math.min(j2, e(widgetRun.start, wrapDimension)), wrapDimension - widgetRun.start.f392c);
    }

    private long f(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.a;
        if (widgetRun instanceof d) {
            return j;
        }
        int size = dependencyNode.f.size();
        long j2 = j;
        for (int i2 = 0; i2 < size; i2++) {
            Dependency dependency = dependencyNode.f.get(i2);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.a != widgetRun) {
                    j2 = Math.max(j2, f(dependencyNode2, dependencyNode2.f392c + j));
                }
            }
        }
        if (dependencyNode != widgetRun.start) {
            return j2;
        }
        long wrapDimension = j + widgetRun.getWrapDimension();
        return Math.max(Math.max(j2, f(widgetRun.end, wrapDimension)), wrapDimension - widgetRun.end.f392c);
    }

    public void a(WidgetRun widgetRun) {
        this.f406c.add(widgetRun);
    }

    public long b(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        long wrapDimension;
        WidgetRun widgetRun;
        long j;
        long j2;
        WidgetRun widgetRun2 = this.b;
        if (widgetRun2 instanceof ChainRun) {
            if (((ChainRun) widgetRun2).orientation != i2) {
                return 0L;
            }
        } else if (i2 == 0) {
            if (!(widgetRun2 instanceof HorizontalWidgetRun)) {
                return 0L;
            }
        } else if (!(widgetRun2 instanceof VerticalWidgetRun)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i2 == 0 ? constraintWidgetContainer.horizontalRun : constraintWidgetContainer.verticalRun).start;
        DependencyNode dependencyNode2 = (i2 == 0 ? constraintWidgetContainer.horizontalRun : constraintWidgetContainer.verticalRun).end;
        boolean contains = widgetRun2.start.g.contains(dependencyNode);
        boolean contains2 = this.b.end.g.contains(dependencyNode2);
        long wrapDimension2 = this.b.getWrapDimension();
        if (!contains || !contains2) {
            if (contains) {
                j2 = f(this.b.start, r13.f392c);
                j = this.b.start.f392c + wrapDimension2;
            } else if (contains2) {
                j = (-this.b.end.f392c) + wrapDimension2;
                j2 = -e(this.b.end, r13.f392c);
            } else {
                wrapDimension = r13.start.f392c + this.b.getWrapDimension();
                widgetRun = this.b;
            }
            return Math.max(j2, j);
        }
        long f = f(this.b.start, 0L);
        long e = e(this.b.end, 0L);
        long j3 = f - wrapDimension2;
        WidgetRun widgetRun3 = this.b;
        int i3 = widgetRun3.end.f392c;
        if (j3 >= (-i3)) {
            j3 += i3;
        }
        int i4 = widgetRun3.start.f392c;
        long j4 = ((-e) - wrapDimension2) - i4;
        if (j4 >= i4) {
            j4 -= i4;
        }
        float f2 = (float) (widgetRun3.a.getBiasPercent(i2) > 0.0f ? (((float) j4) / r13) + (((float) j3) / (1.0f - r13)) : 0L);
        long j5 = (f2 * r13) + 0.5f + wrapDimension2 + (f2 * (1.0f - r13)) + 0.5f;
        widgetRun = this.b;
        wrapDimension = widgetRun.start.f392c + j5;
        return wrapDimension - widgetRun.end.f392c;
    }

    public void d(boolean z, boolean z2) {
        if (z) {
            WidgetRun widgetRun = this.b;
            if (widgetRun instanceof HorizontalWidgetRun) {
                c(widgetRun, 0);
            }
        }
        if (z2) {
            WidgetRun widgetRun2 = this.b;
            if (widgetRun2 instanceof VerticalWidgetRun) {
                c(widgetRun2, 1);
            }
        }
    }
}
