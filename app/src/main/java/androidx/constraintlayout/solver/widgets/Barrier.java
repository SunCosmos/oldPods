package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Barrier extends HelperWidget {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private int d0 = 0;
    private boolean e0 = true;
    private int f0 = 0;
    boolean g0 = false;

    public Barrier() {
    }

    public Barrier(String str) {
        setDebugName(str);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z2;
        SolverVariable solverVariable;
        ConstraintAnchor constraintAnchor;
        int i2;
        int i3;
        int i4;
        SolverVariable solverVariable2;
        int i5;
        ConstraintAnchor[] constraintAnchorArr2 = this.mListAnchors;
        constraintAnchorArr2[0] = this.mLeft;
        constraintAnchorArr2[2] = this.mTop;
        constraintAnchorArr2[1] = this.mRight;
        constraintAnchorArr2[3] = this.mBottom;
        int i6 = 0;
        while (true) {
            constraintAnchorArr = this.mListAnchors;
            if (i6 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i6].e = linearSystem.createObjectVariable(constraintAnchorArr[i6]);
            i6++;
        }
        int i7 = this.d0;
        if (i7 < 0 || i7 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor2 = constraintAnchorArr[i7];
        if (!this.g0) {
            allSolved();
        }
        if (this.g0) {
            this.g0 = false;
            int i8 = this.d0;
            if (i8 == 0 || i8 == 1) {
                linearSystem.addEquality(this.mLeft.e, this.v);
                solverVariable2 = this.mRight.e;
                i5 = this.v;
            } else {
                if (i8 != 2 && i8 != 3) {
                    return;
                }
                linearSystem.addEquality(this.mTop.e, this.w);
                solverVariable2 = this.mBottom.e;
                i5 = this.w;
            }
            linearSystem.addEquality(solverVariable2, i5);
            return;
        }
        for (int i9 = 0; i9 < this.mWidgetsCount; i9++) {
            ConstraintWidget constraintWidget = this.mWidgets[i9];
            if ((this.e0 || constraintWidget.allowedInBarrier()) && ((((i3 = this.d0) == 0 || i3 == 1) && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget != null) || (((i4 = this.d0) == 2 || i4 == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null))) {
                z2 = true;
                break;
            }
        }
        z2 = false;
        boolean z3 = this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents();
        boolean z4 = this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents();
        int i10 = !z2 && (((i2 = this.d0) == 0 && z3) || ((i2 == 2 && z4) || ((i2 == 1 && z3) || (i2 == 3 && z4)))) ? 5 : 4;
        for (int i11 = 0; i11 < this.mWidgetsCount; i11++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i11];
            if (this.e0 || constraintWidget2.allowedInBarrier()) {
                SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.d0]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.mListAnchors;
                int i12 = this.d0;
                constraintAnchorArr3[i12].e = createObjectVariable;
                int i13 = (constraintAnchorArr3[i12].mTarget == null || constraintAnchorArr3[i12].mTarget.mOwner != this) ? 0 : constraintAnchorArr3[i12].mMargin + 0;
                if (i12 == 0 || i12 == 2) {
                    linearSystem.addLowerBarrier(constraintAnchor2.e, createObjectVariable, this.f0 - i13, z2);
                } else {
                    linearSystem.addGreaterBarrier(constraintAnchor2.e, createObjectVariable, this.f0 + i13, z2);
                }
                linearSystem.addEquality(constraintAnchor2.e, createObjectVariable, this.f0 + i13, i10);
            }
        }
        int i14 = this.d0;
        if (i14 == 0) {
            linearSystem.addEquality(this.mRight.e, this.mLeft.e, 0, 8);
            linearSystem.addEquality(this.mLeft.e, this.mParent.mRight.e, 0, 4);
            solverVariable = this.mLeft.e;
            constraintAnchor = this.mParent.mLeft;
        } else if (i14 == 1) {
            linearSystem.addEquality(this.mLeft.e, this.mRight.e, 0, 8);
            linearSystem.addEquality(this.mLeft.e, this.mParent.mLeft.e, 0, 4);
            solverVariable = this.mLeft.e;
            constraintAnchor = this.mParent.mRight;
        } else if (i14 == 2) {
            linearSystem.addEquality(this.mBottom.e, this.mTop.e, 0, 8);
            linearSystem.addEquality(this.mTop.e, this.mParent.mBottom.e, 0, 4);
            solverVariable = this.mTop.e;
            constraintAnchor = this.mParent.mTop;
        } else {
            if (i14 != 3) {
                return;
            }
            linearSystem.addEquality(this.mTop.e, this.mBottom.e, 0, 8);
            linearSystem.addEquality(this.mTop.e, this.mParent.mTop.e, 0, 4);
            solverVariable = this.mTop.e;
            constraintAnchor = this.mParent.mBottom;
        }
        linearSystem.addEquality(solverVariable, constraintAnchor.e, 0, 0);
    }

    public boolean allSolved() {
        int i2;
        ConstraintAnchor.Type type;
        ConstraintAnchor.Type type2;
        ConstraintAnchor.Type type3;
        int i3;
        int i4;
        int i5 = 0;
        boolean z = true;
        while (true) {
            i2 = this.mWidgetsCount;
            if (i5 >= i2) {
                break;
            }
            ConstraintWidget constraintWidget = this.mWidgets[i5];
            if ((this.e0 || constraintWidget.allowedInBarrier()) && ((((i3 = this.d0) == 0 || i3 == 1) && !constraintWidget.isResolvedHorizontally()) || (((i4 = this.d0) == 2 || i4 == 3) && !constraintWidget.isResolvedVertically()))) {
                z = false;
            }
            i5++;
        }
        if (!z || i2 <= 0) {
            return false;
        }
        int i6 = 0;
        boolean z2 = false;
        for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i7];
            if (this.e0 || constraintWidget2.allowedInBarrier()) {
                if (!z2) {
                    int i8 = this.d0;
                    if (i8 == 0) {
                        type3 = ConstraintAnchor.Type.LEFT;
                    } else if (i8 == 1) {
                        type3 = ConstraintAnchor.Type.RIGHT;
                    } else if (i8 == 2) {
                        type3 = ConstraintAnchor.Type.TOP;
                    } else {
                        if (i8 == 3) {
                            type3 = ConstraintAnchor.Type.BOTTOM;
                        }
                        z2 = true;
                    }
                    i6 = constraintWidget2.getAnchor(type3).getFinalValue();
                    z2 = true;
                }
                int i9 = this.d0;
                if (i9 == 0) {
                    type2 = ConstraintAnchor.Type.LEFT;
                } else {
                    if (i9 == 1) {
                        type = ConstraintAnchor.Type.RIGHT;
                    } else if (i9 == 2) {
                        type2 = ConstraintAnchor.Type.TOP;
                    } else if (i9 == 3) {
                        type = ConstraintAnchor.Type.BOTTOM;
                    }
                    i6 = Math.max(i6, constraintWidget2.getAnchor(type).getFinalValue());
                }
                i6 = Math.min(i6, constraintWidget2.getAnchor(type2).getFinalValue());
            }
        }
        int i10 = i6 + this.f0;
        int i11 = this.d0;
        if (i11 == 0 || i11 == 1) {
            setFinalHorizontal(i10, i10);
        } else {
            setFinalVertical(i10, i10);
        }
        this.g0 = true;
        return true;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    public boolean allowsGoneWidget() {
        return this.e0;
    }

    @Override // androidx.constraintlayout.solver.widgets.HelperWidget, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Barrier barrier = (Barrier) constraintWidget;
        this.d0 = barrier.d0;
        this.e0 = barrier.e0;
        this.f0 = barrier.f0;
    }

    public int getBarrierType() {
        return this.d0;
    }

    public int getMargin() {
        return this.f0;
    }

    public int getOrientation() {
        int i2 = this.d0;
        if (i2 == 0 || i2 == 1) {
            return 0;
        }
        return (i2 == 2 || i2 == 3) ? 1 : -1;
    }

    public void h() {
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            int i3 = this.d0;
            if (i3 == 0 || i3 == 1) {
                constraintWidget.g(0, true);
            } else if (i3 == 2 || i3 == 3) {
                constraintWidget.g(1, true);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.g0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.g0;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.e0 = z;
    }

    public void setBarrierType(int i2) {
        this.d0 = i2;
    }

    public void setMargin(int i2) {
        this.f0 = i2;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String toString() {
        String str = "[Barrier] " + getDebugName() + " {";
        for (int i2 = 0; i2 < this.mWidgetsCount; i2++) {
            ConstraintWidget constraintWidget = this.mWidgets[i2];
            if (i2 > 0) {
                str = str + ", ";
            }
            str = str + constraintWidget.getDebugName();
        }
        return str + "}";
    }
}
