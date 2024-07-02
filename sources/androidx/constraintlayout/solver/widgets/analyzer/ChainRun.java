package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ChainRun extends WidgetRun {
    ArrayList<WidgetRun> g;
    private int h;

    public ChainRun(ConstraintWidget constraintWidget, int i2) {
        super(constraintWidget);
        this.g = new ArrayList<>();
        this.orientation = i2;
        m();
    }

    private void m() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.a;
        do {
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget2.getPreviousChainMember(this.orientation);
        } while (constraintWidget2 != null);
        this.a = constraintWidget;
        this.g.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.g.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.g.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            int i2 = this.orientation;
            if (i2 == 0) {
                next.a.horizontalChainRun = this;
            } else if (i2 == 1) {
                next.a.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.a.getParent()).isRtl()) && this.g.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.g;
            this.a = arrayList.get(arrayList.size() - 1).a;
        }
        this.h = this.orientation == 0 ? this.a.getHorizontalChainStyle() : this.a.getVerticalChainStyle();
    }

    private ConstraintWidget n() {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            WidgetRun widgetRun = this.g.get(i2);
            if (widgetRun.a.getVisibility() != 8) {
                return widgetRun.a;
            }
        }
        return null;
    }

    private ConstraintWidget o() {
        for (int size = this.g.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.g.get(size);
            if (widgetRun.a.getVisibility() != 8) {
                return widgetRun.a;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            this.g.get(i2).applyToWidget();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        if (r1 != null) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a5, code lost:
    
        a(r5.end, r1, -r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a3, code lost:
    
        if (r1 != null) goto L98;
     */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c() {
        /*
            r5 = this;
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r0 = r5.g
            java.util.Iterator r0 = r0.iterator()
        L6:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L16
            java.lang.Object r1 = r0.next()
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r1
            r1.c()
            goto L6
        L16:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r0 = r5.g
            int r0 = r0.size()
            r1 = 1
            if (r0 >= r1) goto L20
            return
        L20:
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r2 = r5.g
            r3 = 0
            java.lang.Object r2 = r2.get(r3)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r2 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r2
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r2.a
            java.util.ArrayList<androidx.constraintlayout.solver.widgets.analyzer.WidgetRun> r4 = r5.g
            int r0 = r0 - r1
            java.lang.Object r0 = r4.get(r0)
            androidx.constraintlayout.solver.widgets.analyzer.WidgetRun r0 = (androidx.constraintlayout.solver.widgets.analyzer.WidgetRun) r0
            androidx.constraintlayout.solver.widgets.ConstraintWidget r0 = r0.a
            int r4 = r5.orientation
            if (r4 != 0) goto L70
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r2.mLeft
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r5.g(r1, r3)
            int r1 = r1.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r5.n()
            if (r4 == 0) goto L52
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r1 = r4.mLeft
            int r1 = r1.getMargin()
        L52:
            if (r2 == 0) goto L59
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r5.start
            r5.a(r4, r2, r1)
        L59:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r5.g(r0, r3)
            int r0 = r0.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r5.o()
            if (r2 == 0) goto L6d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r2.mRight
            int r0 = r0.getMargin()
        L6d:
            if (r1 == 0) goto Lab
            goto La5
        L70:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r2.mTop
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r3 = r5.g(r2, r1)
            int r2 = r2.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r4 = r5.n()
            if (r4 == 0) goto L88
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r2 = r4.mTop
            int r2 = r2.getMargin()
        L88:
            if (r3 == 0) goto L8f
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r4 = r5.start
            r5.a(r4, r3, r2)
        L8f:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r1 = r5.g(r0, r1)
            int r0 = r0.getMargin()
            androidx.constraintlayout.solver.widgets.ConstraintWidget r2 = r5.o()
            if (r2 == 0) goto La3
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r0 = r2.mBottom
            int r0 = r0.getMargin()
        La3:
            if (r1 == 0) goto Lab
        La5:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r2 = r5.end
            int r0 = -r0
            r5.a(r2, r1, r0)
        Lab:
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r5.start
            r0.updateDelegate = r5
            androidx.constraintlayout.solver.widgets.analyzer.DependencyNode r0 = r5.end
            r0.updateDelegate = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.ChainRun.c():void");
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void d() {
        this.b = null;
        Iterator<WidgetRun> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int size = this.g.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            j = j + r4.start.f392c + this.g.get(i2).getWrapDimension() + r4.end.f392c;
        }
        return j;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean i() {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.g.get(i2).i()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        String sb2 = sb.toString();
        Iterator<WidgetRun> it = this.g.iterator();
        while (it.hasNext()) {
            String str = sb2 + "<";
            sb2 = (str + it.next()) + "> ";
        }
        return sb2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x01a0, code lost:
    
        if (r1 != r7) goto L806;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01cb, code lost:
    
        r9.f402d.resolve(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01c8, code lost:
    
        r13 = r13 + 1;
        r7 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x01c6, code lost:
    
        if (r1 != r7) goto L806;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x03eb, code lost:
    
        r7 = r7 - r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00eb  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void update(androidx.constraintlayout.solver.widgets.analyzer.Dependency r26) {
        /*
            Method dump skipped, instructions count: 1034
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.ChainRun.update(androidx.constraintlayout.solver.widgets.analyzer.Dependency):void");
    }
}
