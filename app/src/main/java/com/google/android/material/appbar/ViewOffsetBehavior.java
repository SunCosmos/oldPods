package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private int tempLeftRightOffset;
    private int tempTopBottomOffset;
    private a viewOffsetHelper;

    public ViewOffsetBehavior() {
        this.tempTopBottomOffset = 0;
        this.tempLeftRightOffset = 0;
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tempTopBottomOffset = 0;
        this.tempLeftRightOffset = 0;
    }

    public int getLeftAndRightOffset() {
        a aVar = this.viewOffsetHelper;
        if (aVar != null) {
            return aVar.c();
        }
        return 0;
    }

    public int getTopAndBottomOffset() {
        a aVar = this.viewOffsetHelper;
        if (aVar != null) {
            return aVar.d();
        }
        return 0;
    }

    public boolean isHorizontalOffsetEnabled() {
        a aVar = this.viewOffsetHelper;
        return aVar != null && aVar.e();
    }

    public boolean isVerticalOffsetEnabled() {
        a aVar = this.viewOffsetHelper;
        return aVar != null && aVar.f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void layoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i2) {
        coordinatorLayout.onLayoutChild(v, i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i2) {
        layoutChild(coordinatorLayout, v, i2);
        if (this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new a(v);
        }
        this.viewOffsetHelper.g();
        this.viewOffsetHelper.a();
        int i3 = this.tempTopBottomOffset;
        if (i3 != 0) {
            this.viewOffsetHelper.j(i3);
            this.tempTopBottomOffset = 0;
        }
        int i4 = this.tempLeftRightOffset;
        if (i4 == 0) {
            return true;
        }
        this.viewOffsetHelper.i(i4);
        this.tempLeftRightOffset = 0;
        return true;
    }

    public void setHorizontalOffsetEnabled(boolean z) {
        a aVar = this.viewOffsetHelper;
        if (aVar != null) {
            aVar.h(z);
        }
    }

    public boolean setLeftAndRightOffset(int i2) {
        a aVar = this.viewOffsetHelper;
        if (aVar != null) {
            return aVar.i(i2);
        }
        this.tempLeftRightOffset = i2;
        return false;
    }

    public boolean setTopAndBottomOffset(int i2) {
        a aVar = this.viewOffsetHelper;
        if (aVar != null) {
            return aVar.j(i2);
        }
        this.tempTopBottomOffset = i2;
        return false;
    }

    public void setVerticalOffsetEnabled(boolean z) {
        a aVar = this.viewOffsetHelper;
        if (aVar != null) {
            aVar.k(z);
        }
    }
}
