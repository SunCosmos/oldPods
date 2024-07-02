package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class RadialViewGroup extends ConstraintLayout {
    private final Runnable t;
    private int u;
    private MaterialShapeDrawable v;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RadialViewGroup.this.s();
        }
    }

    public RadialViewGroup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadialViewGroup(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        LayoutInflater.from(context).inflate(R.layout.material_radial_view_group, this);
        ViewCompat.setBackground(this, o());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RadialViewGroup, i2, 0);
        this.u = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RadialViewGroup_materialCircleRadius, 0);
        this.t = new a();
        obtainStyledAttributes.recycle();
    }

    private Drawable o() {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.v = materialShapeDrawable;
        materialShapeDrawable.setCornerSize(new RelativeCornerSize(0.5f));
        this.v.setFillColor(ColorStateList.valueOf(-1));
        return this.v;
    }

    private static boolean r(View view) {
        return "skip".equals(view.getTag());
    }

    private void t() {
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.t);
            handler.post(this.t);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (view.getId() == -1) {
            view.setId(ViewCompat.generateViewId());
        }
        t();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        s();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        t();
    }

    @Dimension
    public int p() {
        return this.u;
    }

    public void q(@Dimension int i2) {
        this.u = i2;
        s();
    }

    protected void s() {
        int childCount = getChildCount();
        int i2 = 1;
        for (int i3 = 0; i3 < childCount; i3++) {
            if (r(getChildAt(i3))) {
                i2++;
            }
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        float f = 0.0f;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            int id = childAt.getId();
            int i5 = R.id.circle_center;
            if (id != i5 && !r(childAt)) {
                constraintSet.constrainCircle(childAt.getId(), i5, this.u, f);
                f += 360.0f / (childCount - i2);
            }
        }
        constraintSet.applyTo(this);
    }

    @Override // android.view.View
    public void setBackgroundColor(@ColorInt int i2) {
        this.v.setFillColor(ColorStateList.valueOf(i2));
    }
}
