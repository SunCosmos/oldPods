package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;

/* loaded from: classes.dex */
public class VirtualLayout extends HelperWidget {
    private int d0 = 0;
    private int e0 = 0;
    private int f0 = 0;
    private int g0 = 0;
    private int h0 = 0;
    private int i0 = 0;
    private boolean j0 = false;
    private int k0 = 0;
    private int l0 = 0;
    protected BasicMeasure.Measure m0 = new BasicMeasure.Measure();
    BasicMeasure.Measurer n0 = null;

    public void applyRtl(boolean z) {
        int i2 = this.f0;
        if (i2 > 0 || this.g0 > 0) {
            if (z) {
                this.h0 = this.g0;
                this.i0 = i2;
            } else {
                this.h0 = i2;
                this.i0 = this.g0;
            }
        }
    }

    public void captureWidgets() {
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            if (constraintWidget != null) {
                constraintWidget.setInVirtualLayout(true);
            }
        }
    }

    public int getMeasuredHeight() {
        return this.l0;
    }

    public int getMeasuredWidth() {
        return this.k0;
    }

    public int getPaddingBottom() {
        return this.e0;
    }

    public int getPaddingLeft() {
        return this.h0;
    }

    public int getPaddingRight() {
        return this.i0;
    }

    public int getPaddingTop() {
        return this.d0;
    }

    public void h(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i2, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i3) {
        while (this.n0 == null && getParent() != null) {
            this.n0 = ((ConstraintWidgetContainer) getParent()).getMeasurer();
        }
        BasicMeasure.Measure measure = this.m0;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i2;
        measure.verticalDimension = i3;
        this.n0.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.m0.measuredWidth);
        constraintWidget.setHeight(this.m0.measuredHeight);
        constraintWidget.setHasBaseline(this.m0.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.m0.measuredBaseline);
    }

    public boolean i() {
        ConstraintWidget constraintWidget = this.mParent;
        BasicMeasure.Measurer measurer = constraintWidget != null ? ((ConstraintWidgetContainer) constraintWidget).getMeasurer() : null;
        if (measurer == null) {
            return false;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.mWidgetsCount) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.mWidgets[i2];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof Guideline)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget2.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget2.getDimensionBehaviour(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (!(dimensionBehaviour == dimensionBehaviour3 && constraintWidget2.mMatchConstraintDefaultWidth != 1 && dimensionBehaviour2 == dimensionBehaviour3 && constraintWidget2.mMatchConstraintDefaultHeight != 1)) {
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    BasicMeasure.Measure measure = this.m0;
                    measure.horizontalBehavior = dimensionBehaviour;
                    measure.verticalBehavior = dimensionBehaviour2;
                    measure.horizontalDimension = constraintWidget2.getWidth();
                    this.m0.verticalDimension = constraintWidget2.getHeight();
                    measurer.measure(constraintWidget2, this.m0);
                    constraintWidget2.setWidth(this.m0.measuredWidth);
                    constraintWidget2.setHeight(this.m0.measuredHeight);
                    constraintWidget2.setBaselineDistance(this.m0.measuredBaseline);
                }
            }
            i2++;
        }
    }

    public void j(boolean z) {
        this.j0 = z;
    }

    public void measure(int i2, int i3, int i4, int i5) {
    }

    public boolean needSolverPass() {
        return this.j0;
    }

    public void setMeasure(int i2, int i3) {
        this.k0 = i2;
        this.l0 = i3;
    }

    public void setPadding(int i2) {
        this.d0 = i2;
        this.e0 = i2;
        this.f0 = i2;
        this.g0 = i2;
    }

    public void setPaddingBottom(int i2) {
        this.e0 = i2;
    }

    public void setPaddingEnd(int i2) {
        this.g0 = i2;
    }

    public void setPaddingLeft(int i2) {
        this.h0 = i2;
    }

    public void setPaddingRight(int i2) {
        this.i0 = i2;
    }

    public void setPaddingStart(int i2) {
        this.f0 = i2;
        this.h0 = i2;
        this.i0 = i2;
    }

    public void setPaddingTop(int i2) {
        this.d0 = i2;
    }

    @Override // androidx.constraintlayout.solver.widgets.HelperWidget, androidx.constraintlayout.solver.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer) {
        captureWidgets();
    }
}
