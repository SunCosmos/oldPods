package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class DependencyGraph {
    private ConstraintWidgetContainer a;

    /* renamed from: d, reason: collision with root package name */
    private ConstraintWidgetContainer f391d;
    private BasicMeasure.Measurer f;
    private BasicMeasure.Measure g;
    ArrayList<e> h;
    private boolean b = true;

    /* renamed from: c, reason: collision with root package name */
    private boolean f390c = true;
    private ArrayList<WidgetRun> e = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        new ArrayList();
        this.f = null;
        this.g = new BasicMeasure.Measure();
        this.h = new ArrayList<>();
        this.a = constraintWidgetContainer;
        this.f391d = constraintWidgetContainer;
    }

    private void a(DependencyNode dependencyNode, int i2, int i3, DependencyNode dependencyNode2, ArrayList<e> arrayList, e eVar) {
        WidgetRun widgetRun = dependencyNode.a;
        if (widgetRun.b == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.a;
            if (widgetRun == constraintWidgetContainer.horizontalRun || widgetRun == constraintWidgetContainer.verticalRun) {
                return;
            }
            if (eVar == null) {
                eVar = new e(widgetRun, i3);
                arrayList.add(eVar);
            }
            widgetRun.b = eVar;
            eVar.a(widgetRun);
            for (Dependency dependency : widgetRun.start.f) {
                if (dependency instanceof DependencyNode) {
                    a((DependencyNode) dependency, i2, 0, dependencyNode2, arrayList, eVar);
                }
            }
            for (Dependency dependency2 : widgetRun.end.f) {
                if (dependency2 instanceof DependencyNode) {
                    a((DependencyNode) dependency2, i2, 1, dependencyNode2, arrayList, eVar);
                }
            }
            if (i2 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.f) {
                    if (dependency3 instanceof DependencyNode) {
                        a((DependencyNode) dependency3, i2, 2, dependencyNode2, arrayList, eVar);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.start.g) {
                if (dependencyNode3 == dependencyNode2) {
                    eVar.a = true;
                }
                a(dependencyNode3, i2, 0, dependencyNode2, arrayList, eVar);
            }
            for (DependencyNode dependencyNode4 : widgetRun.end.g) {
                if (dependencyNode4 == dependencyNode2) {
                    eVar.a = true;
                }
                a(dependencyNode4, i2, 1, dependencyNode2, arrayList, eVar);
            }
            if (i2 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                Iterator<DependencyNode> it = ((VerticalWidgetRun) widgetRun).baseline.g.iterator();
                while (it.hasNext()) {
                    a(it.next(), i2, 2, dependencyNode2, arrayList, eVar);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0074, code lost:
    
        if (r2.mMatchConstraintDefaultHeight == 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean b(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r17) {
        /*
            Method dump skipped, instructions count: 625
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph.b(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer):boolean");
    }

    private int c(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        int size = this.h.size();
        long j = 0;
        for (int i3 = 0; i3 < size; i3++) {
            j = Math.max(j, this.h.get(i3).b(constraintWidgetContainer, i2));
        }
        return (int) j;
    }

    private void d(WidgetRun widgetRun, int i2, ArrayList<e> arrayList) {
        for (Dependency dependency : widgetRun.start.f) {
            if (dependency instanceof DependencyNode) {
                a((DependencyNode) dependency, i2, 0, widgetRun.end, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                a(((WidgetRun) dependency).start, i2, 0, widgetRun.end, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.f) {
            if (dependency2 instanceof DependencyNode) {
                a((DependencyNode) dependency2, i2, 1, widgetRun.start, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                a(((WidgetRun) dependency2).end, i2, 1, widgetRun.start, arrayList, null);
            }
        }
        if (i2 == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.f) {
                if (dependency3 instanceof DependencyNode) {
                    a((DependencyNode) dependency3, i2, 2, null, arrayList, null);
                }
            }
        }
    }

    private void e(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        BasicMeasure.Measure measure = this.g;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i2;
        measure.verticalDimension = i3;
        this.f.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.g.measuredWidth);
        constraintWidget.setHeight(this.g.measuredHeight);
        constraintWidget.setHasBaseline(this.g.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.g.measuredBaseline);
    }

    public void buildGraph() {
        buildGraph(this.e);
        this.h.clear();
        e.f405d = 0;
        d(this.a.horizontalRun, 0, this.h);
        d(this.a.verticalRun, 1, this.h);
        this.b = false;
    }

    public void buildGraph(ArrayList<WidgetRun> arrayList) {
        WidgetRun cVar;
        arrayList.clear();
        this.f391d.horizontalRun.d();
        this.f391d.verticalRun.d();
        arrayList.add(this.f391d.horizontalRun);
        arrayList.add(this.f391d.verticalRun);
        Iterator<ConstraintWidget> it = this.f391d.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (next instanceof Guideline) {
                cVar = new c(next);
            } else {
                if (next.isInHorizontalChain()) {
                    if (next.horizontalChainRun == null) {
                        next.horizontalChainRun = new ChainRun(next, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.horizontalChainRun);
                } else {
                    arrayList.add(next.horizontalRun);
                }
                if (next.isInVerticalChain()) {
                    if (next.verticalChainRun == null) {
                        next.verticalChainRun = new ChainRun(next, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.verticalChainRun);
                } else {
                    arrayList.add(next.verticalRun);
                }
                if (next instanceof HelperWidget) {
                    cVar = new d(next);
                }
            }
            arrayList.add(cVar);
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().d();
        }
        Iterator<WidgetRun> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.a != this.f391d) {
                next2.c();
            }
        }
    }

    public void defineTerminalWidgets(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2) {
        if (this.b) {
            buildGraph();
            Iterator<ConstraintWidget> it = this.a.mChildren.iterator();
            boolean z = false;
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                boolean[] zArr = next.isTerminalWidget;
                zArr[0] = true;
                zArr[1] = true;
                if (next instanceof Barrier) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            Iterator<e> it2 = this.h.iterator();
            while (it2.hasNext()) {
                e next2 = it2.next();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                next2.d(dimensionBehaviour == dimensionBehaviour3, dimensionBehaviour2 == dimensionBehaviour3);
            }
        }
    }

    public boolean directMeasure(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.b || this.f390c) {
            Iterator<ConstraintWidget> it = this.a.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                next.horizontalRun.n();
                next.verticalRun.m();
            }
            this.a.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.a;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.horizontalRun.n();
            this.a.verticalRun.m();
            this.f390c = false;
        }
        if (b(this.f391d)) {
            return false;
        }
        this.a.setX(0);
        this.a.setY(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.a.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.a.getDimensionBehaviour(1);
        if (this.b) {
            buildGraph();
        }
        int x = this.a.getX();
        int y = this.a.getY();
        this.a.horizontalRun.start.resolve(x);
        this.a.verticalRun.start.resolve(y);
        measureWidgets();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour == dimensionBehaviour3 || dimensionBehaviour2 == dimensionBehaviour3) {
            if (z4) {
                Iterator<WidgetRun> it2 = this.e.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    if (!it2.next().i()) {
                        z4 = false;
                        break;
                    }
                }
            }
            if (z4 && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.a.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.a;
                constraintWidgetContainer2.setWidth(c(constraintWidgetContainer2, 0));
                ConstraintWidgetContainer constraintWidgetContainer3 = this.a;
                constraintWidgetContainer3.horizontalRun.f402d.resolve(constraintWidgetContainer3.getWidth());
            }
            if (z4 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.a.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer4 = this.a;
                constraintWidgetContainer4.setHeight(c(constraintWidgetContainer4, 1));
                ConstraintWidgetContainer constraintWidgetContainer5 = this.a;
                constraintWidgetContainer5.verticalRun.f402d.resolve(constraintWidgetContainer5.getHeight());
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer6 = this.a;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer6.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour4 == dimensionBehaviour5 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int width = constraintWidgetContainer6.getWidth() + x;
            this.a.horizontalRun.end.resolve(width);
            this.a.horizontalRun.f402d.resolve(width - x);
            measureWidgets();
            ConstraintWidgetContainer constraintWidgetContainer7 = this.a;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer7.mListDimensionBehaviors;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour5 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer7.getHeight() + y;
                this.a.verticalRun.end.resolve(height);
                this.a.verticalRun.f402d.resolve(height - y);
            }
            measureWidgets();
            z2 = true;
        } else {
            z2 = false;
        }
        Iterator<WidgetRun> it3 = this.e.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.a != this.a || next2.e) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it4 = this.e.iterator();
        while (it4.hasNext()) {
            WidgetRun next3 = it4.next();
            if (z2 || next3.a != this.a) {
                if (!next3.start.resolved || ((!next3.end.resolved && !(next3 instanceof c)) || (!next3.f402d.resolved && !(next3 instanceof ChainRun) && !(next3 instanceof c)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.a.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.a.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z3;
    }

    public boolean directMeasureSetup(boolean z) {
        if (this.b) {
            Iterator<ConstraintWidget> it = this.a.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.f402d.resolved = false;
                horizontalWidgetRun.e = false;
                horizontalWidgetRun.n();
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.f402d.resolved = false;
                verticalWidgetRun.e = false;
                verticalWidgetRun.m();
            }
            this.a.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.a;
            constraintWidgetContainer.measured = false;
            HorizontalWidgetRun horizontalWidgetRun2 = constraintWidgetContainer.horizontalRun;
            horizontalWidgetRun2.f402d.resolved = false;
            horizontalWidgetRun2.e = false;
            horizontalWidgetRun2.n();
            VerticalWidgetRun verticalWidgetRun2 = this.a.verticalRun;
            verticalWidgetRun2.f402d.resolved = false;
            verticalWidgetRun2.e = false;
            verticalWidgetRun2.m();
            buildGraph();
        }
        if (b(this.f391d)) {
            return false;
        }
        this.a.setX(0);
        this.a.setY(0);
        this.a.horizontalRun.start.resolve(0);
        this.a.verticalRun.start.resolve(0);
        return true;
    }

    public boolean directMeasureWithOrientation(boolean z, int i2) {
        boolean z2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        b bVar;
        int height;
        boolean z3 = true;
        boolean z4 = z & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.a.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = this.a.getDimensionBehaviour(1);
        int x = this.a.getX();
        int y = this.a.getY();
        if (z4 && (dimensionBehaviour2 == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour3 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.orientation == i2 && !next.i()) {
                    z4 = false;
                    break;
                }
            }
            if (i2 == 0) {
                if (z4 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.a.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    ConstraintWidgetContainer constraintWidgetContainer = this.a;
                    constraintWidgetContainer.setWidth(c(constraintWidgetContainer, 0));
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.a;
                    bVar = constraintWidgetContainer2.horizontalRun.f402d;
                    height = constraintWidgetContainer2.getWidth();
                    bVar.resolve(height);
                }
            } else if (z4 && dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.a.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer3 = this.a;
                constraintWidgetContainer3.setHeight(c(constraintWidgetContainer3, 1));
                ConstraintWidgetContainer constraintWidgetContainer4 = this.a;
                bVar = constraintWidgetContainer4.verticalRun.f402d;
                height = constraintWidgetContainer4.getHeight();
                bVar.resolve(height);
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer5 = this.a;
        if (i2 == 0) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer5.mListDimensionBehaviors;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int width = constraintWidgetContainer5.getWidth() + x;
                this.a.horizontalRun.end.resolve(width);
                this.a.horizontalRun.f402d.resolve(width - x);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer5.mListDimensionBehaviors;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height2 = constraintWidgetContainer5.getHeight() + y;
                this.a.verticalRun.end.resolve(height2);
                this.a.verticalRun.f402d.resolve(height2 - y);
                z2 = true;
            }
            z2 = false;
        }
        measureWidgets();
        Iterator<WidgetRun> it2 = this.e.iterator();
        while (it2.hasNext()) {
            WidgetRun next2 = it2.next();
            if (next2.orientation == i2 && (next2.a != this.a || next2.e)) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it3 = this.e.iterator();
        while (it3.hasNext()) {
            WidgetRun next3 = it3.next();
            if (next3.orientation == i2 && (z2 || next3.a != this.a)) {
                if (!next3.start.resolved || !next3.end.resolved || (!(next3 instanceof ChainRun) && !next3.f402d.resolved)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.a.setHorizontalDimensionBehaviour(dimensionBehaviour2);
        this.a.setVerticalDimensionBehaviour(dimensionBehaviour3);
        return z3;
    }

    public void invalidateGraph() {
        this.b = true;
    }

    public void invalidateMeasures() {
        this.f390c = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00b2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0008 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void measureWidgets() {
        /*
            r12 = this;
            androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r0 = r12.a
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.ConstraintWidget> r0 = r0.mChildren
            java.util.Iterator r0 = r0.iterator()
        L8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lc1
            java.lang.Object r1 = r0.next()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r1 = (androidx.constraintlayout.solver.widgets.ConstraintWidget) r1
            boolean r2 = r1.measured
            if (r2 == 0) goto L19
            goto L8
        L19:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r1.mListDimensionBehaviors
            r3 = 0
            r8 = r2[r3]
            r9 = 1
            r10 = r2[r9]
            int r2 = r1.mMatchConstraintDefaultWidth
            int r4 = r1.mMatchConstraintDefaultHeight
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r8 == r6) goto L32
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r5) goto L30
            if (r2 != r9) goto L30
            goto L32
        L30:
            r2 = 0
            goto L33
        L32:
            r2 = 1
        L33:
            if (r10 == r6) goto L3b
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 != r5) goto L3c
            if (r4 != r9) goto L3c
        L3b:
            r3 = 1
        L3c:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r4 = r1.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.b r4 = r4.f402d
            boolean r5 = r4.resolved
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r7 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.b r7 = r7.f402d
            boolean r11 = r7.resolved
            if (r5 == 0) goto L5b
            if (r11 == 0) goto L5b
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            int r5 = r4.value
            int r7 = r7.value
            r2 = r12
            r3 = r1
            r4 = r6
            r2.e(r3, r4, r5, r6, r7)
        L58:
            r1.measured = r9
            goto Lae
        L5b:
            if (r5 == 0) goto L87
            if (r3 == 0) goto L87
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            int r8 = r4.value
            int r7 = r7.value
            r2 = r12
            r3 = r1
            r4 = r5
            r5 = r8
            r2.e(r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 != r2) goto L7b
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.b r2 = r2.f402d
            int r3 = r1.getHeight()
        L78:
            r2.h = r3
            goto Lae
        L7b:
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.b r2 = r2.f402d
            int r3 = r1.getHeight()
        L83:
            r2.resolve(r3)
            goto L58
        L87:
            if (r11 == 0) goto Lae
            if (r2 == 0) goto Lae
            int r5 = r4.value
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            int r7 = r7.value
            r2 = r12
            r3 = r1
            r4 = r6
            r6 = r10
            r2.e(r3, r4, r5, r6, r7)
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r2) goto La5
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r1.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.b r2 = r2.f402d
            int r3 = r1.getWidth()
            goto L78
        La5:
            androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun r2 = r1.horizontalRun
            androidx.constraintlayout.solver.widgets.analyzer.b r2 = r2.f402d
            int r3 = r1.getWidth()
            goto L83
        Lae:
            boolean r2 = r1.measured
            if (r2 == 0) goto L8
            androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun r2 = r1.verticalRun
            androidx.constraintlayout.solver.widgets.analyzer.b r2 = r2.g
            if (r2 == 0) goto L8
            int r1 = r1.getBaselineDistance()
            r2.resolve(r1)
            goto L8
        Lc1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph.measureWidgets():void");
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.f = measurer;
    }
}
