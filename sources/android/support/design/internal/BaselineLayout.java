package android.support.design.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: lib/Mus.dex */
public class BaselineLayout extends ViewGroup {
    private int mBaseline;

    public BaselineLayout(Context context) {
        super(context, null, 0);
        this.mBaseline = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mBaseline = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mBaseline = -1;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int childCount = getChildCount();
        int i4 = 0;
        int i5 = 0;
        int i6 = -1;
        int i7 = -1;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i2, i3);
                int baseline = childAt.getBaseline();
                if (baseline != -1) {
                    i6 = Math.max(i6, baseline);
                    i7 = Math.max(i7, childAt.getMeasuredHeight() - baseline);
                }
                i4 = Math.max(i4, childAt.getMeasuredWidth());
                i5 = Math.max(i5, childAt.getMeasuredHeight());
                i8 = View.combineMeasuredStates(i8, childAt.getMeasuredState());
            }
        }
        if (i6 != -1) {
            i5 = Math.max(i5, i6 + Math.max(i7, getPaddingBottom()));
            this.mBaseline = i6;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i4, getSuggestedMinimumWidth()), i2, i8), View.resolveSizeAndState(Math.max(i5, getSuggestedMinimumHeight()), i3, i8 << 16));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = ((i4 - i2) - getPaddingRight()) - paddingLeft;
        int paddingTop = getPaddingTop();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i8 = paddingLeft + ((paddingRight - measuredWidth) / 2);
                if (this.mBaseline != -1 && childAt.getBaseline() != -1) {
                    i6 = (paddingTop + this.mBaseline) - childAt.getBaseline();
                } else {
                    i6 = paddingTop;
                }
                childAt.layout(i8, i6, i8 + measuredWidth, i6 + measuredHeight);
            }
        }
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.mBaseline;
    }
}
