package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Guideline extends ConstraintWidget {
    public static final int HORIZONTAL = 0;
    public static final int RELATIVE_BEGIN = 1;
    public static final int RELATIVE_END = 2;
    public static final int RELATIVE_PERCENT = 0;
    public static final int RELATIVE_UNKNWON = -1;
    public static final int VERTICAL = 1;
    protected float d0 = -1.0f;
    protected int e0 = -1;
    protected int f0 = -1;
    private ConstraintAnchor g0 = this.mTop;
    private int h0 = 0;
    private boolean i0;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            a = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public Guideline() {
        this.q.clear();
        this.q.add(this.g0);
        int length = this.mListAnchors.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.mListAnchors[i2] = this.g0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) getParent();
        if (constraintWidgetContainer == null) {
            return;
        }
        ConstraintAnchor anchor = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget constraintWidget = this.mParent;
        boolean z2 = constraintWidget != null && constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (this.h0 == 0) {
            anchor = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.TOP);
            anchor2 = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintWidget constraintWidget2 = this.mParent;
            z2 = constraintWidget2 != null && constraintWidget2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.i0 && this.g0.hasFinalValue()) {
            SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.g0);
            linearSystem.addEquality(createObjectVariable, this.g0.getFinalValue());
            if (this.e0 != -1) {
                if (z2) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(anchor2), createObjectVariable, 0, 5);
                }
            } else if (this.f0 != -1 && z2) {
                SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(anchor2);
                linearSystem.addGreaterThan(createObjectVariable, linearSystem.createObjectVariable(anchor), 0, 5);
                linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, 0, 5);
            }
            this.i0 = false;
            return;
        }
        if (this.e0 != -1) {
            SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.g0);
            linearSystem.addEquality(createObjectVariable3, linearSystem.createObjectVariable(anchor), this.e0, 8);
            if (z2) {
                linearSystem.addGreaterThan(linearSystem.createObjectVariable(anchor2), createObjectVariable3, 0, 5);
                return;
            }
            return;
        }
        if (this.f0 == -1) {
            if (this.d0 != -1.0f) {
                linearSystem.addConstraint(LinearSystem.createRowDimensionPercent(linearSystem, linearSystem.createObjectVariable(this.g0), linearSystem.createObjectVariable(anchor2), this.d0));
                return;
            }
            return;
        }
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.g0);
        SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(anchor2);
        linearSystem.addEquality(createObjectVariable4, createObjectVariable5, -this.f0, 8);
        if (z2) {
            linearSystem.addGreaterThan(createObjectVariable4, linearSystem.createObjectVariable(anchor), 0, 5);
            linearSystem.addGreaterThan(createObjectVariable5, createObjectVariable4, 0, 5);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Guideline guideline = (Guideline) constraintWidget;
        this.d0 = guideline.d0;
        this.e0 = guideline.e0;
        this.f0 = guideline.f0;
        setOrientation(guideline.h0);
    }

    public void cyclePosition() {
        if (this.e0 != -1) {
            j();
        } else if (this.d0 != -1.0f) {
            i();
        } else if (this.f0 != -1) {
            h();
        }
    }

    public ConstraintAnchor getAnchor() {
        return this.g0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (a.a[type.ordinal()]) {
            case 1:
            case 2:
                if (this.h0 == 1) {
                    return this.g0;
                }
                break;
            case 3:
            case 4:
                if (this.h0 == 0) {
                    return this.g0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
        }
        throw new AssertionError(type.name());
    }

    public int getOrientation() {
        return this.h0;
    }

    public int getRelativeBegin() {
        return this.e0;
    }

    public int getRelativeBehaviour() {
        if (this.d0 != -1.0f) {
            return 0;
        }
        if (this.e0 != -1) {
            return 1;
        }
        return this.f0 != -1 ? 2 : -1;
    }

    public int getRelativeEnd() {
        return this.f0;
    }

    public float getRelativePercent() {
        return this.d0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String getType() {
        return "Guideline";
    }

    void h() {
        int x = getX();
        if (this.h0 == 0) {
            x = getY();
        }
        setGuideBegin(x);
    }

    void i() {
        int width = getParent().getWidth() - getX();
        if (this.h0 == 0) {
            width = getParent().getHeight() - getY();
        }
        setGuideEnd(width);
    }

    public boolean isPercent() {
        return this.d0 != -1.0f && this.e0 == -1 && this.f0 == -1;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.i0;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.i0;
    }

    void j() {
        float x = getX() / getParent().getWidth();
        if (this.h0 == 0) {
            x = getY() / getParent().getHeight();
        }
        setGuidePercent(x);
    }

    public void setFinalValue(int i2) {
        this.g0.setFinalValue(i2);
        this.i0 = true;
    }

    public void setGuideBegin(int i2) {
        if (i2 > -1) {
            this.d0 = -1.0f;
            this.e0 = i2;
            this.f0 = -1;
        }
    }

    public void setGuideEnd(int i2) {
        if (i2 > -1) {
            this.d0 = -1.0f;
            this.e0 = -1;
            this.f0 = i2;
        }
    }

    public void setGuidePercent(float f) {
        if (f > -1.0f) {
            this.d0 = f;
            this.e0 = -1;
            this.f0 = -1;
        }
    }

    public void setGuidePercent(int i2) {
        setGuidePercent(i2 / 100.0f);
    }

    public void setMinimumPosition(int i2) {
    }

    public void setOrientation(int i2) {
        if (this.h0 == i2) {
            return;
        }
        this.h0 = i2;
        this.q.clear();
        this.g0 = this.h0 == 1 ? this.mLeft : this.mTop;
        this.q.add(this.g0);
        int length = this.mListAnchors.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.mListAnchors[i3] = this.g0;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        if (getParent() == null) {
            return;
        }
        int objectVariableValue = linearSystem.getObjectVariableValue(this.g0);
        if (this.h0 == 1) {
            setX(objectVariableValue);
            setY(0);
            setHeight(getParent().getHeight());
            setWidth(0);
            return;
        }
        setX(0);
        setY(objectVariableValue);
        setWidth(getParent().getWidth());
        setHeight(0);
    }
}
