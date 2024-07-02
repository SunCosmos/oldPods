package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;

/* loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    private boolean A;
    private float j;
    private float k;
    private float l;
    ConstraintLayout m;
    private float n;
    private float o;
    protected float p;
    protected float q;
    protected float r;
    protected float s;
    protected float t;
    protected float u;
    boolean v;
    View[] w;
    private float x;
    private float y;
    private boolean z;

    public Layer(Context context) {
        super(context);
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.n = 1.0f;
        this.o = 1.0f;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = true;
        this.w = null;
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.n = 1.0f;
        this.o = 1.0f;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = true;
        this.w = null;
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.n = 1.0f;
        this.o = 1.0f;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = true;
        this.w = null;
        this.x = 0.0f;
        this.y = 0.0f;
    }

    private void l() {
        int i2;
        if (this.m == null || (i2 = this.b) == 0) {
            return;
        }
        View[] viewArr = this.w;
        if (viewArr == null || viewArr.length != i2) {
            this.w = new View[i2];
        }
        for (int i3 = 0; i3 < this.b; i3++) {
            this.w[i3] = this.m.getViewById(this.a[i3]);
        }
    }

    private void m() {
        if (this.m == null) {
            return;
        }
        if (this.w == null) {
            l();
        }
        k();
        double radians = Float.isNaN(this.l) ? 0.0d : Math.toRadians(this.l);
        float sin = (float) Math.sin(radians);
        float cos = (float) Math.cos(radians);
        float f = this.n;
        float f2 = f * cos;
        float f3 = this.o;
        float f4 = (-f3) * sin;
        float f5 = f * sin;
        float f6 = f3 * cos;
        for (int i2 = 0; i2 < this.b; i2++) {
            View view = this.w[i2];
            int left = (view.getLeft() + view.getRight()) / 2;
            int top = (view.getTop() + view.getBottom()) / 2;
            float f7 = left - this.p;
            float f8 = top - this.q;
            float f9 = (((f2 * f7) + (f4 * f8)) - f7) + this.x;
            float f10 = (((f7 * f5) + (f6 * f8)) - f8) + this.y;
            view.setTranslationX(f9);
            view.setTranslationY(f10);
            view.setScaleY(this.o);
            view.setScaleX(this.n);
            if (!Float.isNaN(this.l)) {
                view.setRotation(this.l);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void j(AttributeSet attributeSet) {
        super.j(attributeSet);
        this.e = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_android_visibility) {
                    this.z = true;
                } else if (index == R.styleable.ConstraintLayout_Layout_android_elevation) {
                    this.A = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    protected void k() {
        if (this.m == null) {
            return;
        }
        if (this.v || Float.isNaN(this.p) || Float.isNaN(this.q)) {
            if (!Float.isNaN(this.j) && !Float.isNaN(this.k)) {
                this.q = this.k;
                this.p = this.j;
                return;
            }
            View[] i2 = i(this.m);
            int left = i2[0].getLeft();
            int top = i2[0].getTop();
            int right = i2[0].getRight();
            int bottom = i2[0].getBottom();
            for (int i3 = 0; i3 < this.b; i3++) {
                View view = i2[i3];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.r = right;
            this.s = bottom;
            this.t = left;
            this.u = top;
            this.p = Float.isNaN(this.j) ? (left + right) / 2 : this.j;
            this.q = Float.isNaN(this.k) ? (top + bottom) / 2 : this.k;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        int i2 = Build.VERSION.SDK_INT;
        super.onAttachedToWindow();
        this.m = (ConstraintLayout) getParent();
        if (this.z || this.A) {
            int visibility = getVisibility();
            float elevation = i2 >= 21 ? getElevation() : 0.0f;
            for (int i3 = 0; i3 < this.b; i3++) {
                View viewById = this.m.getViewById(this.a[i3]);
                if (viewById != null) {
                    if (this.z) {
                        viewById.setVisibility(visibility);
                    }
                    if (this.A && elevation > 0.0f && i2 >= 21) {
                        viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        d();
    }

    @Override // android.view.View
    public void setPivotX(float f) {
        this.j = f;
        m();
    }

    @Override // android.view.View
    public void setPivotY(float f) {
        this.k = f;
        m();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.l = f;
        m();
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.n = f;
        m();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        this.o = f;
        m();
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.x = f;
        m();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.y = f;
        m();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        d();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePostLayout(ConstraintLayout constraintLayout) {
        l();
        this.p = Float.NaN;
        this.q = Float.NaN;
        ConstraintWidget constraintWidget = ((ConstraintLayout.LayoutParams) getLayoutParams()).getConstraintWidget();
        constraintWidget.setWidth(0);
        constraintWidget.setHeight(0);
        k();
        layout(((int) this.t) - getPaddingLeft(), ((int) this.u) - getPaddingTop(), ((int) this.r) + getPaddingRight(), ((int) this.s) + getPaddingBottom());
        m();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePreDraw(ConstraintLayout constraintLayout) {
        this.m = constraintLayout;
        float rotation = getRotation();
        if (rotation == 0.0f && Float.isNaN(this.l)) {
            return;
        }
        this.l = rotation;
    }
}
