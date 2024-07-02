package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.google.android.material.badge.BadgeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: lib/Mus.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = BadgeDrawable.TOP_START;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i2, 0);
        int i3 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i3 >= 0) {
            setOrientation(i3);
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i4 >= 0) {
            setGravity(i4);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i2) {
        if (i2 != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i2;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            setWillNotDraw(drawable == null);
            requestLayout();
        }
    }

    public void setDividerPadding(int i2) {
        this.mDividerPadding = i2;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int bottom;
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i2)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i2)) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (!isLayoutRtl) {
                    left = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                } else {
                    left = virtualChildAt.getRight() + layoutParams.rightMargin;
                }
                drawVerticalDivider(canvas, left);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                right = isLayoutRtl ? getPaddingLeft() : (getWidth() - getPaddingRight()) - this.mDividerWidth;
            } else {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    right = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    right = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            }
            drawVerticalDivider(canvas, right);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i2) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i2, (getWidth() - getPaddingRight()) - this.mDividerPadding, i2 + this.mDividerHeight);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i2) {
        this.mDivider.setBounds(i2, getPaddingTop() + this.mDividerPadding, i2 + this.mDividerWidth, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i2;
        if (this.mBaselineAlignedChildIndex >= 0) {
            if (getChildCount() <= this.mBaselineAlignedChildIndex) {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
            }
            View childAt = getChildAt(this.mBaselineAlignedChildIndex);
            int baseline = childAt.getBaseline();
            if (baseline == -1) {
                if (this.mBaselineAlignedChildIndex == 0) {
                    return -1;
                }
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
            int i3 = this.mBaselineChildTop;
            if (this.mOrientation == 1 && (i2 = this.mGravity & 112) != 48) {
                switch (i2) {
                    case 16:
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                        break;
                    case 80:
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                        break;
                }
            }
            return i3 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
        }
        return super.getBaseline();
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 < 0 || i2 >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i2;
    }

    View getVirtualChildAt(int i2) {
        return getChildAt(i2);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.mOrientation == 1) {
            measureVertical(i2, i3);
        } else {
            measureHorizontal(i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean hasDividerBeforeChildAt(int i2) {
        if (i2 == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i2 == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) != 0) {
            boolean z = false;
            int i3 = i2 - 1;
            while (true) {
                if (i3 < 0) {
                    break;
                }
                if (getChildAt(i3).getVisibility() == 8) {
                    i3--;
                } else {
                    z = true;
                    break;
                }
            }
            return z;
        }
        return false;
    }

    void measureVertical(int i2, int i3) {
        float f;
        int i4;
        this.mTotalLength = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        boolean z = true;
        float f2 = 0.0f;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        boolean z2 = false;
        boolean z3 = false;
        int i9 = this.mBaselineAlignedChildIndex;
        boolean z4 = this.mUseLargestChild;
        int i10 = 0;
        int i11 = 0;
        while (i11 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i11);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(i11);
            } else if (virtualChildAt.getVisibility() == 8) {
                i11 += getChildrenSkipCount(virtualChildAt, i11);
            } else {
                if (hasDividerBeforeChildAt(i11)) {
                    this.mTotalLength += this.mDividerHeight;
                }
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                f2 += layoutParams.weight;
                if (mode2 == 1073741824 && layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                    int i12 = this.mTotalLength;
                    this.mTotalLength = Math.max(i12, i12 + layoutParams.topMargin + layoutParams.bottomMargin);
                    z3 = true;
                } else {
                    int i13 = Integer.MIN_VALUE;
                    if (layoutParams.height == 0 && layoutParams.weight > 0.0f) {
                        i13 = 0;
                        layoutParams.height = -2;
                    }
                    int i14 = i11;
                    if (f2 == 0.0f) {
                        i4 = this.mTotalLength;
                    } else {
                        i4 = 0;
                    }
                    measureChildBeforeLayout(virtualChildAt, i14, i2, 0, i3, i4);
                    if (i13 != Integer.MIN_VALUE) {
                        layoutParams.height = i13;
                    }
                    int measuredHeight = virtualChildAt.getMeasuredHeight();
                    int i15 = this.mTotalLength;
                    this.mTotalLength = Math.max(i15, i15 + measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt));
                    if (z4) {
                        i10 = Math.max(measuredHeight, i10);
                    }
                }
                if (i9 >= 0 && i9 == i11 + 1) {
                    this.mBaselineChildTop = this.mTotalLength;
                }
                if (i11 < i9 && layoutParams.weight > 0.0f) {
                    throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                }
                boolean z5 = false;
                if (mode != 1073741824 && layoutParams.width == -1) {
                    z2 = true;
                    z5 = true;
                }
                int i16 = layoutParams.leftMargin + layoutParams.rightMargin;
                int measuredWidth = virtualChildAt.getMeasuredWidth() + i16;
                i5 = Math.max(i5, measuredWidth);
                i6 = View.combineMeasuredStates(i6, virtualChildAt.getMeasuredState());
                z = z && layoutParams.width == -1;
                if (layoutParams.weight > 0.0f) {
                    i8 = Math.max(i8, z5 ? i16 : measuredWidth);
                } else {
                    i7 = Math.max(i7, z5 ? i16 : measuredWidth);
                }
                i11 += getChildrenSkipCount(virtualChildAt, i11);
            }
            i11++;
        }
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerHeight;
        }
        if (z4 && (mode2 == Integer.MIN_VALUE || mode2 == 0)) {
            this.mTotalLength = 0;
            int i17 = 0;
            while (i17 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(i17);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(i17);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    i17 += getChildrenSkipCount(virtualChildAt2, i17);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    int i18 = this.mTotalLength;
                    this.mTotalLength = Math.max(i18, i18 + i10 + layoutParams2.topMargin + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt2));
                }
                i17++;
            }
        }
        this.mTotalLength += getPaddingTop() + getPaddingBottom();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), i3, 0);
        int i19 = (resolveSizeAndState & 16777215) - this.mTotalLength;
        if (z3 || (i19 != 0 && f2 > 0.0f)) {
            if (this.mWeightSum > 0.0f) {
                f = this.mWeightSum;
            } else {
                f = f2;
            }
            float f3 = f;
            this.mTotalLength = 0;
            for (int i20 = 0; i20 < virtualChildCount; i20++) {
                View virtualChildAt3 = getVirtualChildAt(i20);
                if (virtualChildAt3.getVisibility() != 8) {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f4 = layoutParams3.weight;
                    if (f4 > 0.0f) {
                        int i21 = (int) ((f4 * i19) / f3);
                        f3 -= f4;
                        i19 -= i21;
                        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + layoutParams3.leftMargin + layoutParams3.rightMargin, layoutParams3.width);
                        if (layoutParams3.height != 0 || mode2 != 1073741824) {
                            int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i21;
                            if (measuredHeight2 < 0) {
                                measuredHeight2 = 0;
                            }
                            virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, BasicMeasure.EXACTLY));
                        } else {
                            virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i21 > 0 ? i21 : 0, BasicMeasure.EXACTLY));
                        }
                        i6 = View.combineMeasuredStates(i6, virtualChildAt3.getMeasuredState() & (-256));
                    }
                    int i22 = layoutParams3.leftMargin + layoutParams3.rightMargin;
                    int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i22;
                    i5 = Math.max(i5, measuredWidth2);
                    i7 = Math.max(i7, mode != 1073741824 && layoutParams3.width == -1 ? i22 : measuredWidth2);
                    z = z && layoutParams3.width == -1;
                    int i23 = this.mTotalLength;
                    this.mTotalLength = Math.max(i23, i23 + virtualChildAt3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt3));
                }
            }
            this.mTotalLength += getPaddingTop() + getPaddingBottom();
        } else {
            i7 = Math.max(i7, i8);
            if (z4 && mode2 != 1073741824) {
                for (int i24 = 0; i24 < virtualChildCount; i24++) {
                    View virtualChildAt4 = getVirtualChildAt(i24);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i10, BasicMeasure.EXACTLY));
                    }
                }
            }
        }
        if (!z && mode != 1073741824) {
            i5 = i7;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i5 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, i6), resolveSizeAndState);
        if (z2) {
            forceUniformWidth(virtualChildCount, i3);
        }
    }

    private void forceUniformWidth(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), BasicMeasure.EXACTLY);
        for (int i4 = 0; i4 < i2; i4++) {
            View virtualChildAt = getVirtualChildAt(i4);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i5 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i3, 0);
                    layoutParams.height = i5;
                }
            }
        }
    }

    void measureHorizontal(int i2, int i3) {
        float f;
        int baseline;
        int i4;
        int i5;
        int baseline2;
        int i6;
        this.mTotalLength = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        boolean z = true;
        float f2 = 0.0f;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        boolean z2 = false;
        boolean z3 = false;
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        boolean z4 = this.mBaselineAligned;
        boolean z5 = this.mUseLargestChild;
        boolean z6 = mode == 1073741824;
        int i11 = 0;
        int i12 = 0;
        while (i12 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i12);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(i12);
            } else if (virtualChildAt.getVisibility() == 8) {
                i12 += getChildrenSkipCount(virtualChildAt, i12);
            } else {
                if (hasDividerBeforeChildAt(i12)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                f2 += layoutParams.weight;
                if (mode == 1073741824 && layoutParams.width == 0 && layoutParams.weight > 0.0f) {
                    if (z6) {
                        this.mTotalLength += layoutParams.leftMargin + layoutParams.rightMargin;
                    } else {
                        int i13 = this.mTotalLength;
                        this.mTotalLength = Math.max(i13, i13 + layoutParams.leftMargin + layoutParams.rightMargin);
                    }
                    if (z4) {
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                        virtualChildAt.measure(makeMeasureSpec, makeMeasureSpec);
                    } else {
                        z3 = true;
                    }
                } else {
                    int i14 = Integer.MIN_VALUE;
                    if (layoutParams.width == 0 && layoutParams.weight > 0.0f) {
                        i14 = 0;
                        layoutParams.width = -2;
                    }
                    int i15 = i12;
                    if (f2 == 0.0f) {
                        i5 = this.mTotalLength;
                    } else {
                        i5 = 0;
                    }
                    measureChildBeforeLayout(virtualChildAt, i15, i2, i5, i3, 0);
                    if (i14 != Integer.MIN_VALUE) {
                        layoutParams.width = i14;
                    }
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    if (z6) {
                        this.mTotalLength += measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                    } else {
                        int i16 = this.mTotalLength;
                        this.mTotalLength = Math.max(i16, i16 + measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt));
                    }
                    if (z5) {
                        i11 = Math.max(measuredWidth, i11);
                    }
                }
                boolean z7 = false;
                if (mode2 != 1073741824 && layoutParams.height == -1) {
                    z2 = true;
                    z7 = true;
                }
                int i17 = layoutParams.topMargin + layoutParams.bottomMargin;
                int measuredHeight = virtualChildAt.getMeasuredHeight() + i17;
                i8 = View.combineMeasuredStates(i8, virtualChildAt.getMeasuredState());
                if (z4 && (baseline2 = virtualChildAt.getBaseline()) != -1) {
                    if (layoutParams.gravity < 0) {
                        i6 = this.mGravity;
                    } else {
                        i6 = layoutParams.gravity;
                    }
                    int i18 = (((i6 & 112) >> 4) & (-2)) >> 1;
                    iArr[i18] = Math.max(iArr[i18], baseline2);
                    iArr2[i18] = Math.max(iArr2[i18], measuredHeight - baseline2);
                }
                i7 = Math.max(i7, measuredHeight);
                z = z && layoutParams.height == -1;
                if (layoutParams.weight > 0.0f) {
                    i10 = Math.max(i10, z7 ? i17 : measuredHeight);
                } else {
                    i9 = Math.max(i9, z7 ? i17 : measuredHeight);
                }
                i12 += getChildrenSkipCount(virtualChildAt, i12);
            }
            i12++;
        }
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        if (iArr[1] != -1 || iArr[0] != -1 || iArr[2] != -1 || iArr[3] != -1) {
            i7 = Math.max(i7, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
        }
        if (z5 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int i19 = 0;
            while (i19 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(i19);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(i19);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    i19 += getChildrenSkipCount(virtualChildAt2, i19);
                } else {
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                    if (z6) {
                        this.mTotalLength += i11 + layoutParams2.leftMargin + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2);
                    } else {
                        int i20 = this.mTotalLength;
                        this.mTotalLength = Math.max(i20, i20 + i11 + layoutParams2.leftMargin + layoutParams2.rightMargin + getNextLocationOffset(virtualChildAt2));
                    }
                }
                i19++;
            }
        }
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), i2, 0);
        int i21 = (resolveSizeAndState & 16777215) - this.mTotalLength;
        if (z3 || (i21 != 0 && f2 > 0.0f)) {
            if (this.mWeightSum > 0.0f) {
                f = this.mWeightSum;
            } else {
                f = f2;
            }
            float f3 = f;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            i7 = -1;
            this.mTotalLength = 0;
            for (int i22 = 0; i22 < virtualChildCount; i22++) {
                View virtualChildAt3 = getVirtualChildAt(i22);
                if (virtualChildAt3 != null && virtualChildAt3.getVisibility() != 8) {
                    LayoutParams layoutParams3 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f4 = layoutParams3.weight;
                    if (f4 > 0.0f) {
                        int i23 = (int) ((f4 * i21) / f3);
                        f3 -= f4;
                        i21 -= i23;
                        int childMeasureSpec = getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + layoutParams3.topMargin + layoutParams3.bottomMargin, layoutParams3.height);
                        if (layoutParams3.width != 0 || mode != 1073741824) {
                            int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i23;
                            if (measuredWidth2 < 0) {
                                measuredWidth2 = 0;
                            }
                            virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, BasicMeasure.EXACTLY), childMeasureSpec);
                        } else {
                            virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i23 > 0 ? i23 : 0, BasicMeasure.EXACTLY), childMeasureSpec);
                        }
                        i8 = View.combineMeasuredStates(i8, virtualChildAt3.getMeasuredState() & (-16777216));
                    }
                    if (z6) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3);
                    } else {
                        int i24 = this.mTotalLength;
                        this.mTotalLength = Math.max(i24, i24 + virtualChildAt3.getMeasuredWidth() + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt3));
                    }
                    boolean z8 = mode2 != 1073741824 && layoutParams3.height == -1;
                    int i25 = layoutParams3.topMargin + layoutParams3.bottomMargin;
                    int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i25;
                    i7 = Math.max(i7, measuredHeight2);
                    i9 = Math.max(i9, z8 ? i25 : measuredHeight2);
                    z = z && layoutParams3.height == -1;
                    if (z4 && (baseline = virtualChildAt3.getBaseline()) != -1) {
                        if (layoutParams3.gravity < 0) {
                            i4 = this.mGravity;
                        } else {
                            i4 = layoutParams3.gravity;
                        }
                        int i26 = (((i4 & 112) >> 4) & (-2)) >> 1;
                        iArr[i26] = Math.max(iArr[i26], baseline);
                        iArr2[i26] = Math.max(iArr2[i26], measuredHeight2 - baseline);
                    }
                }
            }
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            if (iArr[1] != -1 || iArr[0] != -1 || iArr[2] != -1 || iArr[3] != -1) {
                i7 = Math.max(i7, Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))) + Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))));
            }
        } else {
            i9 = Math.max(i9, i10);
            if (z5 && mode != 1073741824) {
                for (int i27 = 0; i27 < virtualChildCount; i27++) {
                    View virtualChildAt4 = getVirtualChildAt(i27);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(i11, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), BasicMeasure.EXACTLY));
                    }
                }
            }
        }
        if (!z && mode2 != 1073741824) {
            i7 = i9;
        }
        setMeasuredDimension(resolveSizeAndState | (i8 & (-16777216)), View.resolveSizeAndState(Math.max(i7 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, i8 << 16));
        if (z2) {
            forceUniformHeight(virtualChildCount, i2);
        }
    }

    private void forceUniformHeight(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), BasicMeasure.EXACTLY);
        for (int i4 = 0; i4 < i2; i4++) {
            View virtualChildAt = getVirtualChildAt(i4);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i5 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i3, 0, makeMeasureSpec, 0);
                    layoutParams.width = i5;
                }
            }
        }
    }

    int getChildrenSkipCount(View view, int i2) {
        return 0;
    }

    int measureNullChild(int i2) {
        return 0;
    }

    void measureChildBeforeLayout(View view, int i2, int i3, int i4, int i5, int i6) {
        measureChildWithMargins(view, i3, i4, i5, i6);
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.mOrientation == 1) {
            layoutVertical(i2, i3, i4, i5);
        } else {
            layoutHorizontal(i2, i3, i4, i5);
        }
    }

    void layoutVertical(int i2, int i3, int i4, int i5) {
        int paddingTop;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i4 - i2;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i8 = this.mGravity & 112;
        int i9 = this.mGravity & 8388615;
        switch (i8) {
            case 16:
                paddingTop = getPaddingTop() + (((i5 - i3) - this.mTotalLength) / 2);
                break;
            case 80:
                paddingTop = ((getPaddingTop() + i5) - i3) - this.mTotalLength;
                break;
            default:
                paddingTop = getPaddingTop();
                break;
        }
        int i10 = 0;
        while (i10 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i10);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(i10);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i11 = layoutParams.gravity;
                if (i11 < 0) {
                    i11 = i9;
                }
                switch (GravityCompat.getAbsoluteGravity(i11, ViewCompat.getLayoutDirection(this)) & 7) {
                    case 1:
                        i6 = ((paddingLeft + ((paddingRight2 - measuredWidth) / 2)) + layoutParams.leftMargin) - layoutParams.rightMargin;
                        break;
                    case 5:
                        i6 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                        break;
                    default:
                        i6 = paddingLeft + layoutParams.leftMargin;
                        break;
                }
                if (hasDividerBeforeChildAt(i10)) {
                    paddingTop += this.mDividerHeight;
                }
                int i12 = paddingTop + layoutParams.topMargin;
                setChildFrame(virtualChildAt, i6, i12 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                paddingTop = i12 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
                i10 += getChildrenSkipCount(virtualChildAt, i10);
            }
            i10++;
        }
    }

    void layoutHorizontal(int i2, int i3, int i4, int i5) {
        int paddingLeft;
        int i6;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i7 = i5 - i3;
        int paddingBottom = i7 - getPaddingBottom();
        int paddingBottom2 = (i7 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i8 = this.mGravity & 8388615;
        int i9 = this.mGravity & 112;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        switch (GravityCompat.getAbsoluteGravity(i8, ViewCompat.getLayoutDirection(this))) {
            case 1:
                paddingLeft = getPaddingLeft() + (((i4 - i2) - this.mTotalLength) / 2);
                break;
            case 5:
                paddingLeft = ((getPaddingLeft() + i4) - i2) - this.mTotalLength;
                break;
            default:
                paddingLeft = getPaddingLeft();
                break;
        }
        int i10 = 0;
        int i11 = 1;
        if (isLayoutRtl) {
            i10 = virtualChildCount - 1;
            i11 = -1;
        }
        int i12 = 0;
        while (i12 < virtualChildCount) {
            int i13 = i10 + (i11 * i12);
            View virtualChildAt = getVirtualChildAt(i13);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i13);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                int i14 = -1;
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (z && layoutParams.height != -1) {
                    i14 = virtualChildAt.getBaseline();
                }
                int i15 = layoutParams.gravity;
                if (i15 < 0) {
                    i15 = i9;
                }
                switch (i15 & 112) {
                    case 16:
                        i6 = ((paddingTop + ((paddingBottom2 - measuredHeight) / 2)) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        break;
                    case 48:
                        i6 = paddingTop + layoutParams.topMargin;
                        if (i14 != -1) {
                            i6 += iArr[1] - i14;
                            break;
                        }
                        break;
                    case 80:
                        i6 = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
                        if (i14 != -1) {
                            i6 -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - i14);
                            break;
                        }
                        break;
                    default:
                        i6 = paddingTop;
                        break;
                }
                if (hasDividerBeforeChildAt(i13)) {
                    paddingLeft += this.mDividerWidth;
                }
                int i16 = paddingLeft + layoutParams.leftMargin;
                setChildFrame(virtualChildAt, i16 + getLocationOffset(virtualChildAt), i6, measuredWidth, measuredHeight);
                paddingLeft = i16 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                i12 += getChildrenSkipCount(virtualChildAt, i13);
            }
            i12++;
        }
    }

    private void setChildFrame(View view, int i2, int i3, int i4, int i5) {
        view.layout(i2, i3, i2 + i4, i3 + i5);
    }

    public void setOrientation(int i2) {
        if (this.mOrientation != i2) {
            this.mOrientation = i2;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i2) {
        int i3 = i2;
        if (this.mGravity != i3) {
            if ((i3 & 8388615) == 0) {
                i3 |= 8388611;
            }
            if ((i3 & 112) == 0) {
                i3 |= 48;
            }
            this.mGravity = i3;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & 8388615;
        if ((this.mGravity & 8388615) != i3) {
            this.mGravity = (this.mGravity & (-8388616)) | i3;
            requestLayout();
        }
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        if ((this.mGravity & 112) != i3) {
            this.mGravity = (this.mGravity & (-113)) | i3;
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        if (this.mOrientation == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    /* loaded from: lib/Mus.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int i2, int i3, float f) {
            super(i2, i3);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }
}
