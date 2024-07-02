package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.Reference;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Guideline;

/* loaded from: classes.dex */
public class GuidelineReference implements Reference {
    final State a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private Guideline f374c;

    /* renamed from: d, reason: collision with root package name */
    private int f375d = -1;
    private int e = -1;
    private float f = 0.0f;
    private Object g;

    public GuidelineReference(State state) {
        this.a = state;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public void apply() {
        this.f374c.setOrientation(this.b);
        int i2 = this.f375d;
        if (i2 != -1) {
            this.f374c.setGuideBegin(i2);
            return;
        }
        int i3 = this.e;
        if (i3 != -1) {
            this.f374c.setGuideEnd(i3);
        } else {
            this.f374c.setGuidePercent(this.f);
        }
    }

    public void end(Object obj) {
        this.f375d = -1;
        this.e = this.a.convertDimension(obj);
        this.f = 0.0f;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public ConstraintWidget getConstraintWidget() {
        if (this.f374c == null) {
            this.f374c = new Guideline();
        }
        return this.f374c;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public Object getKey() {
        return this.g;
    }

    public int getOrientation() {
        return this.b;
    }

    public void percent(float f) {
        this.f375d = -1;
        this.e = -1;
        this.f = f;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public void setConstraintWidget(ConstraintWidget constraintWidget) {
        this.f374c = constraintWidget instanceof Guideline ? (Guideline) constraintWidget : null;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public void setKey(Object obj) {
        this.g = obj;
    }

    public void setOrientation(int i2) {
        this.b = i2;
    }

    public void start(Object obj) {
        this.f375d = this.a.convertDimension(obj);
        this.e = -1;
        this.f = 0.0f;
    }
}
