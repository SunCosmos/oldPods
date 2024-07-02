package com.google.android.material.shape;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class InterpolateOnScrollPositionChangeHelper {
    private View a;
    private MaterialShapeDrawable b;

    /* renamed from: c, reason: collision with root package name */
    private ScrollView f1659c;

    /* renamed from: d, reason: collision with root package name */
    private final int[] f1660d = new int[2];
    private final int[] e = new int[2];
    private final ViewTreeObserver.OnScrollChangedListener f = new a();

    /* loaded from: classes.dex */
    class a implements ViewTreeObserver.OnScrollChangedListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            InterpolateOnScrollPositionChangeHelper.this.updateInterpolationForScreenPosition();
        }
    }

    public InterpolateOnScrollPositionChangeHelper(View view, MaterialShapeDrawable materialShapeDrawable, ScrollView scrollView) {
        this.a = view;
        this.b = materialShapeDrawable;
        this.f1659c = scrollView;
    }

    public void setContainingScrollView(ScrollView scrollView) {
        this.f1659c = scrollView;
    }

    public void setMaterialShapeDrawable(MaterialShapeDrawable materialShapeDrawable) {
        this.b = materialShapeDrawable;
    }

    public void startListeningForScrollChanges(@NonNull ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnScrollChangedListener(this.f);
    }

    public void stopListeningForScrollChanges(@NonNull ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.removeOnScrollChangedListener(this.f);
    }

    public void updateInterpolationForScreenPosition() {
        MaterialShapeDrawable materialShapeDrawable;
        float f;
        ScrollView scrollView = this.f1659c;
        if (scrollView == null) {
            return;
        }
        if (scrollView.getChildCount() == 0) {
            throw new IllegalStateException("Scroll bar must contain a child to calculate interpolation.");
        }
        this.f1659c.getLocationInWindow(this.f1660d);
        this.f1659c.getChildAt(0).getLocationInWindow(this.e);
        int top = (this.a.getTop() - this.f1660d[1]) + this.e[1];
        int height = this.a.getHeight();
        int height2 = this.f1659c.getHeight();
        if (top < 0) {
            materialShapeDrawable = this.b;
            f = (top / height) + 1.0f;
        } else {
            int i2 = top + height;
            if (i2 <= height2) {
                if (this.b.getInterpolation() != 1.0f) {
                    this.b.setInterpolation(1.0f);
                    this.a.invalidate();
                }
                return;
            }
            int i3 = i2 - height2;
            materialShapeDrawable = this.b;
            f = 1.0f - (i3 / height);
        }
        materialShapeDrawable.setInterpolation(Math.max(0.0f, Math.min(1.0f, f)));
        this.a.invalidate();
    }
}
