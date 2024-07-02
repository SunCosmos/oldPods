package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph;
import androidx.constraintlayout.solver.widgets.analyzer.Direct;
import androidx.constraintlayout.solver.widgets.analyzer.Grouping;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintWidgetContainer extends WidgetContainer {
    BasicMeasure d0;
    protected BasicMeasure.Measurer e0;
    private boolean f0;
    protected LinearSystem g0;
    int h0;
    int i0;
    ChainHead[] j0;
    ChainHead[] k0;
    private int l0;
    private boolean m0;
    public DependencyGraph mDependencyGraph;
    public boolean mGroupsWrapOptimized;
    public int mHorizontalChainsSize;
    public boolean mHorizontalWrapOptimized;
    public BasicMeasure.Measure mMeasure;
    public Metrics mMetrics;
    public boolean mSkipSolver;
    public int mVerticalChainsSize;
    public boolean mVerticalWrapOptimized;
    public int mWrapFixedHeight;
    public int mWrapFixedWidth;
    private boolean n0;
    private WeakReference<ConstraintAnchor> o0;
    private WeakReference<ConstraintAnchor> p0;
    private WeakReference<ConstraintAnchor> q0;
    private WeakReference<ConstraintAnchor> r0;

    public ConstraintWidgetContainer() {
        this.d0 = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.e0 = null;
        this.f0 = false;
        this.g0 = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.j0 = new ChainHead[4];
        this.k0 = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.l0 = 257;
        this.mSkipSolver = false;
        this.m0 = false;
        this.n0 = false;
        this.o0 = null;
        this.p0 = null;
        this.q0 = null;
        this.r0 = null;
        this.mMeasure = new BasicMeasure.Measure();
    }

    public ConstraintWidgetContainer(int i2, int i3) {
        super(i2, i3);
        this.d0 = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.e0 = null;
        this.f0 = false;
        this.g0 = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.j0 = new ChainHead[4];
        this.k0 = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.l0 = 257;
        this.mSkipSolver = false;
        this.m0 = false;
        this.n0 = false;
        this.o0 = null;
        this.p0 = null;
        this.q0 = null;
        this.r0 = null;
        this.mMeasure = new BasicMeasure.Measure();
    }

    public ConstraintWidgetContainer(int i2, int i3, int i4, int i5) {
        super(i2, i3, i4, i5);
        this.d0 = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.e0 = null;
        this.f0 = false;
        this.g0 = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.j0 = new ChainHead[4];
        this.k0 = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.l0 = 257;
        this.mSkipSolver = false;
        this.m0 = false;
        this.n0 = false;
        this.o0 = null;
        this.p0 = null;
        this.q0 = null;
        this.r0 = null;
        this.mMeasure = new BasicMeasure.Measure();
    }

    public ConstraintWidgetContainer(String str, int i2, int i3) {
        super(i2, i3);
        this.d0 = new BasicMeasure(this);
        this.mDependencyGraph = new DependencyGraph(this);
        this.e0 = null;
        this.f0 = false;
        this.g0 = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.j0 = new ChainHead[4];
        this.k0 = new ChainHead[4];
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.l0 = 257;
        this.mSkipSolver = false;
        this.m0 = false;
        this.n0 = false;
        this.o0 = null;
        this.p0 = null;
        this.q0 = null;
        this.r0 = null;
        this.mMeasure = new BasicMeasure.Measure();
        setDebugName(str);
    }

    private void i(ConstraintWidget constraintWidget) {
        int i2 = this.mHorizontalChainsSize + 1;
        ChainHead[] chainHeadArr = this.k0;
        if (i2 >= chainHeadArr.length) {
            this.k0 = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.k0[this.mHorizontalChainsSize] = new ChainHead(constraintWidget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void j(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.g0.addGreaterThan(solverVariable, this.g0.createObjectVariable(constraintAnchor), 0, 5);
    }

    private void k(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.g0.addGreaterThan(this.g0.createObjectVariable(constraintAnchor), solverVariable, 0, 5);
    }

    private void l(ConstraintWidget constraintWidget) {
        int i2 = this.mVerticalChainsSize + 1;
        ChainHead[] chainHeadArr = this.j0;
        if (i2 >= chainHeadArr.length) {
            this.j0 = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.j0[this.mVerticalChainsSize] = new ChainHead(constraintWidget, 1, isRtl());
        this.mVerticalChainsSize++;
    }

    public static boolean measure(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, BasicMeasure.Measure measure, int i2) {
        int i3;
        int i4;
        if (measurer == null) {
            return false;
        }
        measure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        measure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        measure.horizontalDimension = constraintWidget.getWidth();
        measure.verticalDimension = constraintWidget.getHeight();
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z2 = measure.verticalBehavior == dimensionBehaviour2;
        boolean z3 = z && constraintWidget.mDimensionRatio > 0.0f;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        if (z && constraintWidget.hasDanglingDimension(0) && constraintWidget.mMatchConstraintDefaultWidth == 0 && !z3) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z2 && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z = false;
        }
        if (z2 && constraintWidget.hasDanglingDimension(1) && constraintWidget.mMatchConstraintDefaultHeight == 0 && !z4) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z2 = false;
        }
        if (constraintWidget.isResolvedHorizontally()) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            z = false;
        }
        if (constraintWidget.isResolvedVertically()) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            z2 = false;
        }
        if (z3) {
            if (constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z2) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = measure.verticalBehavior;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour3 == dimensionBehaviour4) {
                    i4 = measure.verticalDimension;
                } else {
                    measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.measure(constraintWidget, measure);
                    i4 = measure.measuredHeight;
                }
                measure.horizontalBehavior = dimensionBehaviour4;
                int i5 = constraintWidget.u;
                measure.horizontalDimension = (int) ((i5 == 0 || i5 == -1) ? constraintWidget.getDimensionRatio() * i4 : constraintWidget.getDimensionRatio() / i4);
            }
        }
        if (z4) {
            if (constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = measure.horizontalBehavior;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.FIXED;
                if (dimensionBehaviour5 == dimensionBehaviour6) {
                    i3 = measure.horizontalDimension;
                } else {
                    measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.measure(constraintWidget, measure);
                    i3 = measure.measuredWidth;
                }
                measure.verticalBehavior = dimensionBehaviour6;
                int i6 = constraintWidget.u;
                measure.verticalDimension = (int) ((i6 == 0 || i6 == -1) ? i3 / constraintWidget.getDimensionRatio() : i3 * constraintWidget.getDimensionRatio());
            }
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(measure.measuredWidth);
        constraintWidget.setHeight(measure.measuredHeight);
        constraintWidget.setHasBaseline(measure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(measure.measuredBaseline);
        measure.measureStrategy = BasicMeasure.Measure.SELF_DIMENSIONS;
        return measure.measuredNeedsSolverPass;
    }

    private void o() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        boolean optimizeFor = optimizeFor(64);
        addToSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i2);
            constraintWidget.g(0, false);
            constraintWidget.g(1, false);
            if (constraintWidget instanceof Barrier) {
                z = true;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintWidget constraintWidget2 = this.mChildren.get(i3);
                if (constraintWidget2 instanceof Barrier) {
                    ((Barrier) constraintWidget2).h();
                }
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            ConstraintWidget constraintWidget3 = this.mChildren.get(i4);
            if (constraintWidget3.b()) {
                constraintWidget3.addToSolver(linearSystem, optimizeFor);
            }
        }
        if (LinearSystem.USE_DEPENDENCY_ORDERING) {
            HashSet<ConstraintWidget> hashSet = new HashSet<>();
            for (int i5 = 0; i5 < size; i5++) {
                ConstraintWidget constraintWidget4 = this.mChildren.get(i5);
                if (!constraintWidget4.b()) {
                    hashSet.add(constraintWidget4);
                }
            }
            addChildrenToSolverByDependency(this, linearSystem, hashSet, getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? 0 : 1, false);
            Iterator<ConstraintWidget> it = hashSet.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                Optimizer.a(this, linearSystem, next);
                next.addToSolver(linearSystem, optimizeFor);
            }
        } else {
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget5 = this.mChildren.get(i6);
                if (constraintWidget5 instanceof ConstraintWidgetContainer) {
                    ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget5.mListDimensionBehaviors;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget5.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget5.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    constraintWidget5.addToSolver(linearSystem, optimizeFor);
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        constraintWidget5.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        constraintWidget5.setVerticalDimensionBehaviour(dimensionBehaviour2);
                    }
                } else {
                    Optimizer.a(this, linearSystem, constraintWidget5);
                    if (!constraintWidget5.b()) {
                        constraintWidget5.addToSolver(linearSystem, optimizeFor);
                    }
                }
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, null, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, null, 1);
        }
        return true;
    }

    public void addHorizontalWrapMaxVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.r0;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > this.r0.get().getFinalValue()) {
            this.r0 = new WeakReference<>(constraintAnchor);
        }
    }

    public void addHorizontalWrapMinVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.p0;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > this.p0.get().getFinalValue()) {
            this.p0 = new WeakReference<>(constraintAnchor);
        }
    }

    public void defineTerminalWidgets() {
        this.mDependencyGraph.defineTerminalWidgets(getHorizontalDimensionBehaviour(), getVerticalDimensionBehaviour());
    }

    public boolean directMeasure(boolean z) {
        return this.mDependencyGraph.directMeasure(z);
    }

    public boolean directMeasureSetup(boolean z) {
        return this.mDependencyGraph.directMeasureSetup(z);
    }

    public boolean directMeasureWithOrientation(boolean z, int i2) {
        return this.mDependencyGraph.directMeasureWithOrientation(z, i2);
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.g0.fillMetrics(metrics);
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i2);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public BasicMeasure.Measurer getMeasurer() {
        return this.e0;
    }

    public int getOptimizationLevel() {
        return this.l0;
    }

    public LinearSystem getSystem() {
        return this.g0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String getType() {
        return "ConstraintLayout";
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i2);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public void h(ConstraintWidget constraintWidget, int i2) {
        if (i2 == 0) {
            i(constraintWidget);
        } else if (i2 == 1) {
            l(constraintWidget);
        }
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void invalidateGraph() {
        this.mDependencyGraph.invalidateGraph();
    }

    public void invalidateMeasures() {
        this.mDependencyGraph.invalidateMeasures();
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.n0;
    }

    public boolean isRtl() {
        return this.f0;
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.m0;
    }

    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [boolean] */
    @Override // androidx.constraintlayout.solver.widgets.WidgetContainer
    public void layout() {
        int i2;
        int i3;
        boolean z;
        boolean z2;
        ?? r6;
        boolean z3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        this.v = 0;
        this.w = 0;
        this.m0 = false;
        this.n0 = false;
        int size = this.mChildren.size();
        int max = Math.max(0, getWidth());
        int max2 = Math.max(0, getHeight());
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = dimensionBehaviourArr[0];
        Metrics metrics = this.mMetrics;
        if (metrics != null) {
            metrics.layouts++;
        }
        if (Optimizer.enabled(this.l0, 1)) {
            Direct.solvingPass(this, getMeasurer());
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget = this.mChildren.get(i4);
                if (constraintWidget.isMeasureRequested() && !(constraintWidget instanceof Guideline) && !(constraintWidget instanceof Barrier) && !(constraintWidget instanceof VirtualLayout) && !constraintWidget.isInVirtualLayout()) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = constraintWidget.getDimensionBehaviour(0);
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = constraintWidget.getDimensionBehaviour(1);
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (!(dimensionBehaviour4 == dimensionBehaviour6 && constraintWidget.mMatchConstraintDefaultWidth != 1 && dimensionBehaviour5 == dimensionBehaviour6 && constraintWidget.mMatchConstraintDefaultHeight != 1)) {
                        measure(constraintWidget, this.e0, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                    }
                }
            }
        }
        if (size <= 2 || !((dimensionBehaviour3 == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour2 == dimensionBehaviour) && Optimizer.enabled(this.l0, 1024) && Grouping.simpleSolvingPass(this, getMeasurer()))) {
            i2 = max2;
            i3 = max;
            z = false;
        } else {
            if (dimensionBehaviour3 == dimensionBehaviour) {
                if (max >= getWidth() || max <= 0) {
                    max = getWidth();
                } else {
                    setWidth(max);
                    this.m0 = true;
                }
            }
            if (dimensionBehaviour2 == dimensionBehaviour) {
                if (max2 >= getHeight() || max2 <= 0) {
                    max2 = getHeight();
                } else {
                    setHeight(max2);
                    this.n0 = true;
                }
            }
            i2 = max2;
            i3 = max;
            z = true;
        }
        boolean z4 = optimizeFor(64) || optimizeFor(128);
        LinearSystem linearSystem = this.g0;
        linearSystem.graphOptimizer = false;
        linearSystem.newgraphOptimizer = false;
        if (this.l0 != 0 && z4) {
            linearSystem.newgraphOptimizer = true;
        }
        ArrayList<ConstraintWidget> arrayList = this.mChildren;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z5 = horizontalDimensionBehaviour == dimensionBehaviour7 || getVerticalDimensionBehaviour() == dimensionBehaviour7;
        o();
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget2 = this.mChildren.get(i5);
            if (constraintWidget2 instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget2).layout();
            }
        }
        boolean optimizeFor = optimizeFor(64);
        boolean z6 = z;
        int i6 = 0;
        boolean z7 = true;
        while (z7) {
            int i7 = i6 + 1;
            try {
                this.g0.reset();
                o();
                createObjectVariables(this.g0);
                for (int i8 = 0; i8 < size; i8++) {
                    this.mChildren.get(i8).createObjectVariables(this.g0);
                }
                z7 = addChildrenToSolver(this.g0);
                WeakReference<ConstraintAnchor> weakReference = this.o0;
                if (weakReference != null && weakReference.get() != null) {
                    k(this.o0.get(), this.g0.createObjectVariable(this.mTop));
                    this.o0 = null;
                }
                WeakReference<ConstraintAnchor> weakReference2 = this.q0;
                if (weakReference2 != null && weakReference2.get() != null) {
                    j(this.q0.get(), this.g0.createObjectVariable(this.mBottom));
                    this.q0 = null;
                }
                WeakReference<ConstraintAnchor> weakReference3 = this.p0;
                if (weakReference3 != null && weakReference3.get() != null) {
                    k(this.p0.get(), this.g0.createObjectVariable(this.mLeft));
                    this.p0 = null;
                }
                WeakReference<ConstraintAnchor> weakReference4 = this.r0;
                if (weakReference4 != null && weakReference4.get() != null) {
                    j(this.r0.get(), this.g0.createObjectVariable(this.mRight));
                    this.r0 = null;
                }
                if (z7) {
                    this.g0.minimize();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("EXCEPTION : " + e);
            }
            LinearSystem linearSystem2 = this.g0;
            if (z7) {
                updateChildrenFromSolver(linearSystem2, Optimizer.a);
            } else {
                updateFromSolver(linearSystem2, optimizeFor);
                for (int i9 = 0; i9 < size; i9++) {
                    this.mChildren.get(i9).updateFromSolver(this.g0, optimizeFor);
                }
            }
            if (z5 && i7 < 8 && Optimizer.a[2]) {
                int i10 = 0;
                int i11 = 0;
                for (int i12 = 0; i12 < size; i12++) {
                    ConstraintWidget constraintWidget3 = this.mChildren.get(i12);
                    i10 = Math.max(i10, constraintWidget3.v + constraintWidget3.getWidth());
                    i11 = Math.max(i11, constraintWidget3.w + constraintWidget3.getHeight());
                }
                int max3 = Math.max(this.C, i10);
                int max4 = Math.max(this.D, i11);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (dimensionBehaviour3 != dimensionBehaviour8 || getWidth() >= max3) {
                    z2 = false;
                } else {
                    setWidth(max3);
                    this.mListDimensionBehaviors[0] = dimensionBehaviour8;
                    z2 = true;
                    z6 = true;
                }
                if (dimensionBehaviour2 == dimensionBehaviour8 && getHeight() < max4) {
                    setHeight(max4);
                    this.mListDimensionBehaviors[1] = dimensionBehaviour8;
                    z2 = true;
                    z6 = true;
                }
            } else {
                z2 = false;
            }
            int max5 = Math.max(this.C, getWidth());
            if (max5 > getWidth()) {
                setWidth(max5);
                this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                z2 = true;
                z6 = true;
            }
            int max6 = Math.max(this.D, getHeight());
            if (max6 > getHeight()) {
                setHeight(max6);
                r6 = 1;
                this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.FIXED;
                z2 = true;
                z3 = true;
            } else {
                r6 = 1;
                z3 = z6;
            }
            if (!z3) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = this.mListDimensionBehaviors[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (dimensionBehaviour9 == dimensionBehaviour10 && i3 > 0 && getWidth() > i3) {
                    this.m0 = r6;
                    this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                    setWidth(i3);
                    z2 = true;
                    z3 = true;
                }
                if (this.mListDimensionBehaviors[r6] == dimensionBehaviour10 && i2 > 0 && getHeight() > i2) {
                    this.n0 = r6;
                    this.mListDimensionBehaviors[r6] = ConstraintWidget.DimensionBehaviour.FIXED;
                    setHeight(i2);
                    z6 = true;
                    z7 = true;
                    i6 = i7;
                }
            }
            z7 = z2;
            z6 = z3;
            i6 = i7;
        }
        this.mChildren = arrayList;
        if (z6) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = this.mListDimensionBehaviors;
            dimensionBehaviourArr2[0] = dimensionBehaviour3;
            dimensionBehaviourArr2[1] = dimensionBehaviour2;
        }
        resetSolverVariables(this.g0.getCache());
    }

    public void m(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.q0;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > this.q0.get().getFinalValue()) {
            this.q0 = new WeakReference<>(constraintAnchor);
        }
    }

    public long measure(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.h0 = i9;
        this.i0 = i10;
        return this.d0.solverMeasure(this, i2, i9, i10, i3, i4, i5, i6, i7, i8);
    }

    public void n(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.o0;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > this.o0.get().getFinalValue()) {
            this.o0 = new WeakReference<>(constraintAnchor);
        }
    }

    public boolean optimizeFor(int i2) {
        return (this.l0 & i2) == i2;
    }

    @Override // androidx.constraintlayout.solver.widgets.WidgetContainer, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void reset() {
        this.g0.reset();
        this.h0 = 0;
        this.i0 = 0;
        this.mSkipSolver = false;
        super.reset();
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.e0 = measurer;
        this.mDependencyGraph.setMeasurer(measurer);
    }

    public void setOptimizationLevel(int i2) {
        this.l0 = i2;
        LinearSystem.USE_DEPENDENCY_ORDERING = optimizeFor(512);
    }

    public void setPadding(int i2, int i3, int i4, int i5) {
        this.h0 = i2;
        this.i0 = i3;
    }

    public void setRtl(boolean z) {
        this.f0 = z;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        boolean optimizeFor = optimizeFor(64);
        updateFromSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mChildren.get(i2).updateFromSolver(linearSystem, optimizeFor);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void updateFromRuns(boolean z, boolean z2) {
        super.updateFromRuns(z, z2);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mChildren.get(i2).updateFromRuns(z, z2);
        }
    }

    public void updateHierarchy() {
        this.d0.updateHierarchy(this);
    }
}
