package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

/* loaded from: lib/Mus.dex */
class ViewOffsetHelper {
    private int mLayoutLeft;
    private int mLayoutTop;
    private int mOffsetLeft;
    private int mOffsetTop;
    private final View mView;

    public ViewOffsetHelper(View view) {
        this.mView = view;
    }

    public void onViewLayout() {
        this.mLayoutTop = this.mView.getTop();
        this.mLayoutLeft = this.mView.getLeft();
        updateOffsets();
    }

    private void updateOffsets() {
        ViewCompat.offsetTopAndBottom(this.mView, this.mOffsetTop - (this.mView.getTop() - this.mLayoutTop));
        ViewCompat.offsetLeftAndRight(this.mView, this.mOffsetLeft - (this.mView.getLeft() - this.mLayoutLeft));
    }

    public boolean setTopAndBottomOffset(int i2) {
        if (this.mOffsetTop == i2) {
            return false;
        }
        this.mOffsetTop = i2;
        updateOffsets();
        return true;
    }

    public boolean setLeftAndRightOffset(int i2) {
        if (this.mOffsetLeft == i2) {
            return false;
        }
        this.mOffsetLeft = i2;
        updateOffsets();
        return true;
    }

    public int getTopAndBottomOffset() {
        return this.mOffsetTop;
    }

    public int getLeftAndRightOffset() {
        return this.mOffsetLeft;
    }

    public int getLayoutTop() {
        return this.mLayoutTop;
    }

    public int getLayoutLeft() {
        return this.mLayoutLeft;
    }
}
