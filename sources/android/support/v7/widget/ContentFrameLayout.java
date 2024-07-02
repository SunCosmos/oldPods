package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: lib/Mus.dex */
public class ContentFrameLayout extends FrameLayout {
    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    /* loaded from: lib/Mus.dex */
    public interface OnAttachListener {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDecorPadding = new Rect();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void dispatchFitSystemWindows(Rect rect) {
        fitSystemWindows(rect);
    }

    public void setAttachListener(OnAttachListener onAttachListener) {
        this.mAttachListener = onAttachListener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDecorPadding(int i2, int i3, int i4, int i5) {
        this.mDecorPadding.set(i2, i3, i4, i5);
        if (ViewCompat.isLaidOut(this)) {
            requestLayout();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        TypedValue typedValue;
        TypedValue typedValue2;
        TypedValue typedValue3;
        int i4 = i2;
        int i5 = i3;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        boolean z = displayMetrics.widthPixels < displayMetrics.heightPixels;
        int mode = View.MeasureSpec.getMode(i4);
        int mode2 = View.MeasureSpec.getMode(i5);
        boolean z2 = false;
        if (mode == Integer.MIN_VALUE) {
            if (z) {
                typedValue3 = this.mFixedWidthMinor;
            } else {
                typedValue3 = this.mFixedWidthMajor;
            }
            TypedValue typedValue4 = typedValue3;
            if (typedValue4 != null && typedValue4.type != 0) {
                int i6 = 0;
                if (typedValue4.type == 5) {
                    i6 = (int) typedValue4.getDimension(displayMetrics);
                } else if (typedValue4.type == 6) {
                    i6 = (int) typedValue4.getFraction(displayMetrics.widthPixels, displayMetrics.widthPixels);
                }
                if (i6 > 0) {
                    i4 = View.MeasureSpec.makeMeasureSpec(Math.min(i6 - (this.mDecorPadding.left + this.mDecorPadding.right), View.MeasureSpec.getSize(i4)), BasicMeasure.EXACTLY);
                    z2 = true;
                }
            }
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (z) {
                typedValue2 = this.mFixedHeightMajor;
            } else {
                typedValue2 = this.mFixedHeightMinor;
            }
            TypedValue typedValue5 = typedValue2;
            if (typedValue5 != null && typedValue5.type != 0) {
                int i7 = 0;
                if (typedValue5.type == 5) {
                    i7 = (int) typedValue5.getDimension(displayMetrics);
                } else if (typedValue5.type == 6) {
                    i7 = (int) typedValue5.getFraction(displayMetrics.heightPixels, displayMetrics.heightPixels);
                }
                if (i7 > 0) {
                    i5 = View.MeasureSpec.makeMeasureSpec(Math.min(i7 - (this.mDecorPadding.top + this.mDecorPadding.bottom), View.MeasureSpec.getSize(i5)), BasicMeasure.EXACTLY);
                }
            }
        }
        super.onMeasure(i4, i5);
        int measuredWidth = getMeasuredWidth();
        boolean z3 = false;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, BasicMeasure.EXACTLY);
        if (!z2 && mode == Integer.MIN_VALUE) {
            if (z) {
                typedValue = this.mMinWidthMinor;
            } else {
                typedValue = this.mMinWidthMajor;
            }
            TypedValue typedValue6 = typedValue;
            if (typedValue6 != null && typedValue6.type != 0) {
                int i8 = 0;
                if (typedValue6.type == 5) {
                    i8 = (int) typedValue6.getDimension(displayMetrics);
                } else if (typedValue6.type == 6) {
                    i8 = (int) typedValue6.getFraction(displayMetrics.widthPixels, displayMetrics.widthPixels);
                }
                if (i8 > 0) {
                    i8 -= this.mDecorPadding.left + this.mDecorPadding.right;
                }
                if (measuredWidth < i8) {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8, BasicMeasure.EXACTLY);
                    z3 = true;
                }
            }
        }
        if (z3) {
            super.onMeasure(makeMeasureSpec, i5);
        }
    }

    public TypedValue getMinWidthMajor() {
        if (this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if (this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }
        return this.mMinWidthMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }
        return this.mFixedWidthMinor;
    }

    public TypedValue getFixedHeightMajor() {
        if (this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }
        return this.mFixedHeightMinor;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onAttachedFromWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onDetachedFromWindow();
        }
    }
}
