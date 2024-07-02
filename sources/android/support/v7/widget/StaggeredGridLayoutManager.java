package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: lib/Mus.dex */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    static final boolean DEBUG = false;

    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "StaggeredGridLManager";
    public static final int VERTICAL = 1;
    private int mFullSizeSpec;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;

    @NonNull
    private final LayoutState mLayoutState;
    private int mOrientation;
    private SavedState mPendingSavedState;
    private int[] mPrefetchDistances;

    @NonNull
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;

    @NonNull
    OrientationHelper mSecondaryOrientation;
    private int mSizePerSpan;
    Span[] mSpans;
    private int mSpanCount = -1;
    boolean mReverseLayout = false;
    boolean mShouldReverseLayout = false;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    LazySpanLookup mLazySpanLookup = new LazySpanLookup();
    private int mGapStrategy = 2;
    private final Rect mTmpRect = new Rect();
    private final AnchorInfo mAnchorInfo = new AnchorInfo();
    private boolean mLaidOutInvalidFullSpan = false;
    private boolean mSmoothScrollbarEnabled = true;
    private final Runnable mCheckForGapsRunnable = new Runnable() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.1
        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.checkForGaps();
        }
    };

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attributeSet, i2, i3);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    public StaggeredGridLayoutManager(int i2, int i3) {
        this.mOrientation = i3;
        setSpanCount(i2);
        this.mLayoutState = new LayoutState();
        createOrientationHelpers();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    private void createOrientationHelpers() {
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    boolean checkForGaps() {
        int firstChildPosition;
        int lastChildPosition;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            firstChildPosition = getLastChildPosition();
            lastChildPosition = getFirstChildPosition();
        } else {
            firstChildPosition = getFirstChildPosition();
            lastChildPosition = getLastChildPosition();
        }
        if (firstChildPosition == 0 && hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
        if (!this.mLaidOutInvalidFullSpan) {
            return false;
        }
        int i2 = this.mShouldReverseLayout ? -1 : 1;
        LazySpanLookup.FullSpanItem firstFullSpanItemInRange = this.mLazySpanLookup.getFirstFullSpanItemInRange(firstChildPosition, lastChildPosition + 1, i2, true);
        if (firstFullSpanItemInRange == null) {
            this.mLaidOutInvalidFullSpan = false;
            this.mLazySpanLookup.forceInvalidateAfter(lastChildPosition + 1);
            return false;
        }
        LazySpanLookup.FullSpanItem firstFullSpanItemInRange2 = this.mLazySpanLookup.getFirstFullSpanItemInRange(firstChildPosition, firstFullSpanItemInRange.mPosition, i2 * (-1), true);
        if (firstFullSpanItemInRange2 == null) {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange.mPosition);
        } else {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange2.mPosition + 1);
        }
        requestSimpleAnimationsInNextLayout();
        requestLayout();
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i2) {
        if (i2 == 0) {
            checkForGaps();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        removeCallbacks(this.mCheckForGapsRunnable);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].clear();
        }
        recyclerView.requestLayout();
    }

    View hasGapsToFix() {
        int i2;
        int i3;
        int childCount = getChildCount() - 1;
        BitSet bitSet = new BitSet(this.mSpanCount);
        bitSet.set(0, this.mSpanCount, true);
        char c2 = (this.mOrientation == 1 && isLayoutRTL()) ? (char) 1 : (char) 65535;
        if (this.mShouldReverseLayout) {
            i2 = childCount;
            i3 = 0 - 1;
        } else {
            i2 = 0;
            i3 = childCount + 1;
        }
        int i4 = i2 < i3 ? 1 : -1;
        int i5 = i2;
        while (true) {
            int i6 = i5;
            if (i6 != i3) {
                View childAt = getChildAt(i6);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (bitSet.get(layoutParams.mSpan.mIndex)) {
                    if (checkSpanForGap(layoutParams.mSpan)) {
                        return childAt;
                    }
                    bitSet.clear(layoutParams.mSpan.mIndex);
                }
                if (!layoutParams.mFullSpan && i6 + i4 != i3) {
                    View childAt2 = getChildAt(i6 + i4);
                    boolean z = false;
                    if (this.mShouldReverseLayout) {
                        int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
                        int decoratedEnd2 = this.mPrimaryOrientation.getDecoratedEnd(childAt2);
                        if (decoratedEnd < decoratedEnd2) {
                            return childAt;
                        }
                        if (decoratedEnd == decoratedEnd2) {
                            z = true;
                        }
                    } else {
                        int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
                        int decoratedStart2 = this.mPrimaryOrientation.getDecoratedStart(childAt2);
                        if (decoratedStart > decoratedStart2) {
                            return childAt;
                        }
                        if (decoratedStart == decoratedStart2) {
                            z = true;
                        }
                    }
                    if (z) {
                        if ((layoutParams.mSpan.mIndex - ((LayoutParams) childAt2.getLayoutParams()).mSpan.mIndex < 0) != (c2 < 0)) {
                            return childAt;
                        }
                    } else {
                        continue;
                    }
                }
                i5 = i6 + i4;
            } else {
                return null;
            }
        }
    }

    private boolean checkSpanForGap(Span span) {
        if (this.mShouldReverseLayout) {
            if (span.getEndLine() < this.mPrimaryOrientation.getEndAfterPadding()) {
                return !span.getLayoutParams(span.mViews.get(span.mViews.size() + (-1))).mFullSpan;
            }
        } else if (span.getStartLine() > this.mPrimaryOrientation.getStartAfterPadding()) {
            return !span.getLayoutParams(span.mViews.get(0)).mFullSpan;
        }
        return false;
    }

    public void setSpanCount(int i2) {
        assertNotInLayoutOrScroll(null);
        if (i2 != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = i2;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                this.mSpans[i3] = new Span(i3);
            }
            requestLayout();
        }
    }

    public void setOrientation(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i2 != this.mOrientation) {
            this.mOrientation = i2;
            OrientationHelper orientationHelper = this.mPrimaryOrientation;
            this.mPrimaryOrientation = this.mSecondaryOrientation;
            this.mSecondaryOrientation = orientationHelper;
            requestLayout();
        }
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.mPendingSavedState != null && this.mPendingSavedState.mReverseLayout != z) {
            this.mPendingSavedState.mReverseLayout = z;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public int getGapStrategy() {
        return this.mGapStrategy;
    }

    public void setGapStrategy(int i2) {
        assertNotInLayoutOrScroll(null);
        if (i2 != this.mGapStrategy) {
            if (i2 != 0 && i2 != 2) {
                throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
            }
            this.mGapStrategy = i2;
            requestLayout();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect rect, int i2, int i3) {
        int chooseSize;
        int chooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            chooseSize2 = chooseSize(i3, rect.height() + paddingTop, getMinimumHeight());
            chooseSize = chooseSize(i2, (this.mSizePerSpan * this.mSpanCount) + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = chooseSize(i2, rect.width() + paddingLeft, getMinimumWidth());
            chooseSize2 = chooseSize(i3, (this.mSizePerSpan * this.mSpanCount) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    private void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        AnchorInfo anchorInfo = this.mAnchorInfo;
        if ((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            anchorInfo.reset();
            return;
        }
        boolean z2 = (anchorInfo.mValid && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null) ? false : true;
        if (z2) {
            anchorInfo.reset();
            if (this.mPendingSavedState != null) {
                applyPendingSavedState(anchorInfo);
            } else {
                resolveShouldLayoutReverse();
                anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
            }
            updateAnchorInfoForLayout(state, anchorInfo);
            anchorInfo.mValid = true;
        }
        if (this.mPendingSavedState == null && this.mPendingScrollPosition == -1 && (anchorInfo.mLayoutFromEnd != this.mLastLayoutFromEnd || isLayoutRTL() != this.mLastLayoutRTL)) {
            this.mLazySpanLookup.clear();
            anchorInfo.mInvalidateOffsets = true;
        }
        if (getChildCount() > 0 && (this.mPendingSavedState == null || this.mPendingSavedState.mSpanOffsetsSize < 1)) {
            if (anchorInfo.mInvalidateOffsets) {
                for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                    this.mSpans[i2].clear();
                    if (anchorInfo.mOffset != Integer.MIN_VALUE) {
                        this.mSpans[i2].setLine(anchorInfo.mOffset);
                    }
                }
            } else if (z2 || this.mAnchorInfo.mSpanReferenceLines == null) {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    this.mSpans[i3].cacheReferenceLineAndClear(this.mShouldReverseLayout, anchorInfo.mOffset);
                }
                this.mAnchorInfo.saveSpanReferenceLines(this.mSpans);
            } else {
                for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                    Span span = this.mSpans[i4];
                    span.clear();
                    span.setLine(this.mAnchorInfo.mSpanReferenceLines[i4]);
                }
            }
        }
        detachAndScrapAttachedViews(recycler);
        this.mLayoutState.mRecycle = false;
        this.mLaidOutInvalidFullSpan = false;
        updateMeasureSpecs(this.mSecondaryOrientation.getTotalSpace());
        updateLayoutState(anchorInfo.mPosition, state);
        if (anchorInfo.mLayoutFromEnd) {
            setLayoutStateDirection(-1);
            fill(recycler, this.mLayoutState, state);
            setLayoutStateDirection(1);
            this.mLayoutState.mCurrentPosition = anchorInfo.mPosition + this.mLayoutState.mItemDirection;
            fill(recycler, this.mLayoutState, state);
        } else {
            setLayoutStateDirection(1);
            fill(recycler, this.mLayoutState, state);
            setLayoutStateDirection(-1);
            this.mLayoutState.mCurrentPosition = anchorInfo.mPosition + this.mLayoutState.mItemDirection;
            fill(recycler, this.mLayoutState, state);
        }
        repositionToWrapContentIfNecessary();
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout) {
                fixEndGap(recycler, state, true);
                fixStartGap(recycler, state, false);
            } else {
                fixStartGap(recycler, state, true);
                fixEndGap(recycler, state, false);
            }
        }
        boolean z3 = false;
        if (z && !state.isPreLayout()) {
            if (this.mGapStrategy != 0 && getChildCount() > 0 && (this.mLaidOutInvalidFullSpan || hasGapsToFix() != null)) {
                removeCallbacks(this.mCheckForGapsRunnable);
                if (checkForGaps()) {
                    z3 = true;
                }
            }
        }
        if (state.isPreLayout()) {
            this.mAnchorInfo.reset();
        }
        this.mLastLayoutFromEnd = anchorInfo.mLayoutFromEnd;
        this.mLastLayoutRTL = isLayoutRTL();
        if (z3) {
            this.mAnchorInfo.reset();
            onLayoutChildren(recycler, state, false);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.getMode() != 1073741824) {
            float f = 0.0f;
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                float decoratedMeasurement = this.mSecondaryOrientation.getDecoratedMeasurement(childAt);
                if (decoratedMeasurement >= f) {
                    if (((LayoutParams) childAt.getLayoutParams()).isFullSpan()) {
                        decoratedMeasurement = (1.0f * decoratedMeasurement) / this.mSpanCount;
                    }
                    f = Math.max(f, decoratedMeasurement);
                }
            }
            int i3 = this.mSizePerSpan;
            int round = Math.round(f * this.mSpanCount);
            if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
                round = Math.min(round, this.mSecondaryOrientation.getTotalSpace());
            }
            updateMeasureSpecs(round);
            if (this.mSizePerSpan != i3) {
                for (int i4 = 0; i4 < childCount; i4++) {
                    View childAt2 = getChildAt(i4);
                    LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
                    if (!layoutParams.mFullSpan) {
                        if (!isLayoutRTL() || this.mOrientation != 1) {
                            int i5 = layoutParams.mSpan.mIndex * this.mSizePerSpan;
                            int i6 = layoutParams.mSpan.mIndex * i3;
                            if (this.mOrientation == 1) {
                                childAt2.offsetLeftAndRight(i5 - i6);
                            } else {
                                childAt2.offsetTopAndBottom(i5 - i6);
                            }
                        } else {
                            childAt2.offsetLeftAndRight(((-((this.mSpanCount - 1) - layoutParams.mSpan.mIndex)) * this.mSizePerSpan) - ((-((this.mSpanCount - 1) - layoutParams.mSpan.mIndex)) * i3));
                        }
                    }
                }
            }
        }
    }

    private void applyPendingSavedState(AnchorInfo anchorInfo) {
        if (this.mPendingSavedState.mSpanOffsetsSize > 0) {
            if (this.mPendingSavedState.mSpanOffsetsSize == this.mSpanCount) {
                for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                    this.mSpans[i2].clear();
                    int i3 = this.mPendingSavedState.mSpanOffsets[i2];
                    if (i3 != Integer.MIN_VALUE) {
                        if (this.mPendingSavedState.mAnchorLayoutFromEnd) {
                            i3 += this.mPrimaryOrientation.getEndAfterPadding();
                        } else {
                            i3 += this.mPrimaryOrientation.getStartAfterPadding();
                        }
                    }
                    this.mSpans[i2].setLine(i3);
                }
            } else {
                this.mPendingSavedState.invalidateSpanInfo();
                this.mPendingSavedState.mAnchorPosition = this.mPendingSavedState.mVisibleAnchorPosition;
            }
        }
        this.mLastLayoutRTL = this.mPendingSavedState.mLastLayoutRTL;
        setReverseLayout(this.mPendingSavedState.mReverseLayout);
        resolveShouldLayoutReverse();
        if (this.mPendingSavedState.mAnchorPosition != -1) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            anchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
        } else {
            anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        if (this.mPendingSavedState.mSpanLookupSize > 1) {
            this.mLazySpanLookup.mData = this.mPendingSavedState.mSpanLookup;
            this.mLazySpanLookup.mFullSpanItems = this.mPendingSavedState.mFullSpanItems;
        }
    }

    void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (!updateAnchorFromPendingData(state, anchorInfo) && !updateAnchorFromChildren(state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = 0;
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorInfo) {
        int findFirstReferenceChildPosition;
        if (this.mLastLayoutFromEnd) {
            findFirstReferenceChildPosition = findLastReferenceChildPosition(state.getItemCount());
        } else {
            findFirstReferenceChildPosition = findFirstReferenceChildPosition(state.getItemCount());
        }
        anchorInfo.mPosition = findFirstReferenceChildPosition;
        anchorInfo.mOffset = Integer.MIN_VALUE;
        return true;
    }

    boolean updateAnchorFromPendingData(RecyclerView.State state, AnchorInfo anchorInfo) {
        int firstChildPosition;
        int startAfterPadding;
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        if (this.mPendingSavedState == null || this.mPendingSavedState.mAnchorPosition == -1 || this.mPendingSavedState.mSpanOffsetsSize < 1) {
            View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
            if (findViewByPosition != null) {
                if (this.mShouldReverseLayout) {
                    firstChildPosition = getLastChildPosition();
                } else {
                    firstChildPosition = getFirstChildPosition();
                }
                anchorInfo.mPosition = firstChildPosition;
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    if (anchorInfo.mLayoutFromEnd) {
                        anchorInfo.mOffset = (this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedEnd(findViewByPosition);
                    } else {
                        anchorInfo.mOffset = (this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.getDecoratedStart(findViewByPosition);
                    }
                    return true;
                }
                if (this.mPrimaryOrientation.getDecoratedMeasurement(findViewByPosition) > this.mPrimaryOrientation.getTotalSpace()) {
                    if (anchorInfo.mLayoutFromEnd) {
                        startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                    } else {
                        startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                    }
                    anchorInfo.mOffset = startAfterPadding;
                    return true;
                }
                int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(findViewByPosition) - this.mPrimaryOrientation.getStartAfterPadding();
                if (decoratedStart < 0) {
                    anchorInfo.mOffset = -decoratedStart;
                    return true;
                }
                int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(findViewByPosition);
                if (endAfterPadding < 0) {
                    anchorInfo.mOffset = endAfterPadding;
                    return true;
                }
                anchorInfo.mOffset = Integer.MIN_VALUE;
            } else {
                anchorInfo.mPosition = this.mPendingScrollPosition;
                if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                    anchorInfo.mLayoutFromEnd = calculateScrollDirectionForPosition(anchorInfo.mPosition) == 1;
                    anchorInfo.assignCoordinateFromPadding();
                } else {
                    anchorInfo.assignCoordinateFromPadding(this.mPendingScrollPositionOffset);
                }
                anchorInfo.mInvalidateOffsets = true;
            }
        } else {
            anchorInfo.mOffset = Integer.MIN_VALUE;
            anchorInfo.mPosition = this.mPendingScrollPosition;
        }
        return true;
    }

    void updateMeasureSpecs(int i2) {
        this.mSizePerSpan = i2 / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(i2, this.mSecondaryOrientation.getMode());
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        int[] iArr2 = iArr;
        if (iArr2 != null) {
            if (iArr2.length < this.mSpanCount) {
                throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr2.length);
            }
        } else {
            iArr2 = new int[this.mSpanCount];
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr2[i2] = this.mSpans[i2].findFirstVisibleItemPosition();
        }
        return iArr2;
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] iArr) {
        int[] iArr2 = iArr;
        if (iArr2 != null) {
            if (iArr2.length < this.mSpanCount) {
                throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr2.length);
            }
        } else {
            iArr2 = new int[this.mSpanCount];
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr2[i2] = this.mSpans[i2].findFirstCompletelyVisibleItemPosition();
        }
        return iArr2;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        int[] iArr2 = iArr;
        if (iArr2 != null) {
            if (iArr2.length < this.mSpanCount) {
                throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr2.length);
            }
        } else {
            iArr2 = new int[this.mSpanCount];
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr2[i2] = this.mSpans[i2].findLastVisibleItemPosition();
        }
        return iArr2;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        int[] iArr2 = iArr;
        if (iArr2 != null) {
            if (iArr2.length < this.mSpanCount) {
                throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr2.length);
            }
        } else {
            iArr2 = new int[this.mSpanCount];
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr2[i2] = this.mSpans[i2].findLastCompletelyVisibleItemPosition();
        }
        return iArr2;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollOffset(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollExtent(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ScrollbarHelper.computeScrollRange(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private void measureChildWithDecorationsAndMargin(View view, LayoutParams layoutParams, boolean z) {
        if (layoutParams.mFullSpan) {
            if (this.mOrientation == 1) {
                measureChildWithDecorationsAndMargin(view, this.mFullSizeSpec, getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), layoutParams.height, true), z);
                return;
            } else {
                measureChildWithDecorationsAndMargin(view, getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), layoutParams.width, true), this.mFullSizeSpec, z);
                return;
            }
        }
        if (this.mOrientation == 1) {
            measureChildWithDecorationsAndMargin(view, getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, layoutParams.width, false), getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), layoutParams.height, true), z);
        } else {
            measureChildWithDecorationsAndMargin(view, getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), layoutParams.width, true), getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, layoutParams.height, false), z);
        }
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i3, boolean z) {
        boolean shouldMeasureChild;
        calculateItemDecorationsForChild(view, this.mTmpRect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int updateSpecWithExtra = updateSpecWithExtra(i2, layoutParams.leftMargin + this.mTmpRect.left, layoutParams.rightMargin + this.mTmpRect.right);
        int updateSpecWithExtra2 = updateSpecWithExtra(i3, layoutParams.topMargin + this.mTmpRect.top, layoutParams.bottomMargin + this.mTmpRect.bottom);
        if (z) {
            shouldMeasureChild = shouldReMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        } else {
            shouldMeasureChild = shouldMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams);
        }
        if (shouldMeasureChild) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }

    private int updateSpecWithExtra(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i3) - i4), mode);
        }
        return i2;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        int firstChildPosition;
        int startLine;
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        savedState.mReverseLayout = this.mReverseLayout;
        savedState.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        savedState.mLastLayoutRTL = this.mLastLayoutRTL;
        if (this.mLazySpanLookup != null && this.mLazySpanLookup.mData != null) {
            savedState.mSpanLookup = this.mLazySpanLookup.mData;
            savedState.mSpanLookupSize = savedState.mSpanLookup.length;
            savedState.mFullSpanItems = this.mLazySpanLookup.mFullSpanItems;
        } else {
            savedState.mSpanLookupSize = 0;
        }
        if (getChildCount() > 0) {
            if (this.mLastLayoutFromEnd) {
                firstChildPosition = getLastChildPosition();
            } else {
                firstChildPosition = getFirstChildPosition();
            }
            savedState.mAnchorPosition = firstChildPosition;
            savedState.mVisibleAnchorPosition = findFirstVisibleItemPositionInt();
            savedState.mSpanOffsetsSize = this.mSpanCount;
            savedState.mSpanOffsets = new int[this.mSpanCount];
            for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                if (this.mLastLayoutFromEnd) {
                    startLine = this.mSpans[i2].getEndLine(Integer.MIN_VALUE);
                    if (startLine != Integer.MIN_VALUE) {
                        startLine -= this.mPrimaryOrientation.getEndAfterPadding();
                    }
                } else {
                    startLine = this.mSpans[i2].getStartLine(Integer.MIN_VALUE);
                    if (startLine != Integer.MIN_VALUE) {
                        startLine -= this.mPrimaryOrientation.getStartAfterPadding();
                    }
                }
                savedState.mSpanOffsets[i2] = startLine;
            }
        } else {
            savedState.mAnchorPosition = -1;
            savedState.mVisibleAnchorPosition = -1;
            savedState.mSpanOffsetsSize = 0;
        }
        return savedState;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int i2;
        int i3;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.mOrientation == 0) {
            int spanIndex = layoutParams2.getSpanIndex();
            if (layoutParams2.mFullSpan) {
                i3 = this.mSpanCount;
            } else {
                i3 = 1;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanIndex, i3, -1, -1, layoutParams2.mFullSpan, false));
            return;
        }
        int spanIndex2 = layoutParams2.getSpanIndex();
        if (layoutParams2.mFullSpan) {
            i2 = this.mSpanCount;
        } else {
            i2 = 1;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, spanIndex2, i2, layoutParams2.mFullSpan, false));
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(false);
            View findFirstVisibleItemClosestToEnd = findFirstVisibleItemClosestToEnd(false);
            if (findFirstVisibleItemClosestToStart != null && findFirstVisibleItemClosestToEnd != null) {
                int position = getPosition(findFirstVisibleItemClosestToStart);
                int position2 = getPosition(findFirstVisibleItemClosestToEnd);
                if (position < position2) {
                    accessibilityEvent.setFromIndex(position);
                    accessibilityEvent.setToIndex(position2);
                } else {
                    accessibilityEvent.setFromIndex(position2);
                    accessibilityEvent.setToIndex(position);
                }
            }
        }
    }

    int findFirstVisibleItemPositionInt() {
        View findFirstVisibleItemClosestToStart;
        if (this.mShouldReverseLayout) {
            findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToEnd(true);
        } else {
            findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(true);
        }
        View view = findFirstVisibleItemClosestToStart;
        return view == null ? -1 : getPosition(view);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation != 0) {
            return super.getRowCountForAccessibility(recycler, state);
        }
        return this.mSpanCount;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation != 1) {
            return super.getColumnCountForAccessibility(recycler, state);
        }
        return this.mSpanCount;
    }

    View findFirstVisibleItemClosestToStart(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    View findFirstVisibleItemClosestToEnd(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd) > 0) {
            int i2 = endAfterPadding - (-scrollBy(-endAfterPadding, recycler, state));
            if (z && i2 > 0) {
                this.mPrimaryOrientation.offsetChildren(i2);
            }
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int minStart = getMinStart(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (minStart != Integer.MAX_VALUE && (startAfterPadding = minStart - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int scrollBy = startAfterPadding - scrollBy(startAfterPadding, recycler, state);
            if (z && scrollBy > 0) {
                this.mPrimaryOrientation.offsetChildren(-scrollBy);
            }
        }
    }

    private void updateLayoutState(int i2, RecyclerView.State state) {
        int targetScrollPosition;
        this.mLayoutState.mAvailable = 0;
        this.mLayoutState.mCurrentPosition = i2;
        int i3 = 0;
        int i4 = 0;
        if (isSmoothScrolling() && (targetScrollPosition = state.getTargetScrollPosition()) != -1) {
            if (this.mShouldReverseLayout == (targetScrollPosition < i2)) {
                i4 = this.mPrimaryOrientation.getTotalSpace();
            } else {
                i3 = this.mPrimaryOrientation.getTotalSpace();
            }
        }
        if (getClipToPadding()) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - i3;
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + i4;
        } else {
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + i4;
            this.mLayoutState.mStartLine = -i3;
        }
        this.mLayoutState.mStopInFocusable = false;
        this.mLayoutState.mRecycle = true;
        this.mLayoutState.mInfinite = this.mPrimaryOrientation.getMode() == 0 && this.mPrimaryOrientation.getEnd() == 0;
    }

    private void setLayoutStateDirection(int i2) {
        this.mLayoutState.mLayoutDirection = i2;
        this.mLayoutState.mItemDirection = this.mShouldReverseLayout == (i2 == -1) ? 1 : -1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void offsetChildrenHorizontal(int i2) {
        super.offsetChildrenHorizontal(i2);
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            this.mSpans[i3].onOffset(i2);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void offsetChildrenVertical(int i2) {
        super.offsetChildrenVertical(i2);
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            this.mSpans[i3].onOffset(i2);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i3) {
        handleUpdate(i2, i3, 2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i2, int i3) {
        handleUpdate(i2, i3, 1);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i2, int i3, int i4) {
        handleUpdate(i2, i3, 8);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i3, Object obj) {
        handleUpdate(i2, i3, 4);
    }

    private void handleUpdate(int i2, int i3, int i4) {
        int i5;
        int i6;
        int lastChildPosition;
        int lastChildPosition2 = this.mShouldReverseLayout ? getLastChildPosition() : getFirstChildPosition();
        if (i4 == 8) {
            if (i2 < i3) {
                i6 = i3 + 1;
                i5 = i2;
            } else {
                i6 = i2 + 1;
                i5 = i3;
            }
        } else {
            i5 = i2;
            i6 = i2 + i3;
        }
        this.mLazySpanLookup.invalidateAfter(i5);
        switch (i4) {
            case 1:
                this.mLazySpanLookup.offsetForAddition(i2, i3);
                break;
            case 2:
                this.mLazySpanLookup.offsetForRemoval(i2, i3);
                break;
            case 8:
                this.mLazySpanLookup.offsetForRemoval(i2, 1);
                this.mLazySpanLookup.offsetForAddition(i3, 1);
                break;
        }
        if (i6 > lastChildPosition2) {
            if (this.mShouldReverseLayout) {
                lastChildPosition = getFirstChildPosition();
            } else {
                lastChildPosition = getLastChildPosition();
            }
            if (i5 <= lastChildPosition) {
                requestLayout();
            }
        }
    }

    private int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state) {
        int i2;
        int startAfterPadding;
        boolean z;
        int maxEnd;
        Span span;
        int startLine;
        int i3;
        int decoratedMeasurement;
        int startAfterPadding2;
        int i4;
        int decoratedMeasurement2;
        boolean z2;
        int endLine;
        Span nextSpan;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        if (this.mLayoutState.mInfinite) {
            if (layoutState.mLayoutDirection == 1) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = Integer.MIN_VALUE;
            }
        } else if (layoutState.mLayoutDirection == 1) {
            i2 = layoutState.mEndLine + layoutState.mAvailable;
        } else {
            i2 = layoutState.mStartLine - layoutState.mAvailable;
        }
        updateAllRemainingSpans(layoutState.mLayoutDirection, i2);
        if (this.mShouldReverseLayout) {
            startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        } else {
            startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        }
        int i5 = startAfterPadding;
        boolean z3 = false;
        while (true) {
            z = z3;
            if (!layoutState.hasMore(state) || (!this.mLayoutState.mInfinite && this.mRemainingSpans.isEmpty())) {
                break;
            }
            View next = layoutState.next(recycler);
            LayoutParams layoutParams = (LayoutParams) next.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            int span2 = this.mLazySpanLookup.getSpan(viewLayoutPosition);
            boolean z4 = span2 == -1;
            if (z4) {
                if (layoutParams.mFullSpan) {
                    nextSpan = this.mSpans[0];
                } else {
                    nextSpan = getNextSpan(layoutState);
                }
                span = nextSpan;
                this.mLazySpanLookup.setSpan(viewLayoutPosition, span);
            } else {
                span = this.mSpans[span2];
            }
            layoutParams.mSpan = span;
            if (layoutState.mLayoutDirection == 1) {
                addView(next);
            } else {
                addView(next, 0);
            }
            measureChildWithDecorationsAndMargin(next, layoutParams, false);
            if (layoutState.mLayoutDirection == 1) {
                if (layoutParams.mFullSpan) {
                    endLine = getMaxEnd(i5);
                } else {
                    endLine = span.getEndLine(i5);
                }
                decoratedMeasurement = endLine;
                i3 = decoratedMeasurement + this.mPrimaryOrientation.getDecoratedMeasurement(next);
                if (z4 && layoutParams.mFullSpan) {
                    LazySpanLookup.FullSpanItem createFullSpanItemFromEnd = createFullSpanItemFromEnd(decoratedMeasurement);
                    createFullSpanItemFromEnd.mGapDir = -1;
                    createFullSpanItemFromEnd.mPosition = viewLayoutPosition;
                    this.mLazySpanLookup.addFullSpanItem(createFullSpanItemFromEnd);
                }
            } else {
                if (layoutParams.mFullSpan) {
                    startLine = getMinStart(i5);
                } else {
                    startLine = span.getStartLine(i5);
                }
                i3 = startLine;
                decoratedMeasurement = i3 - this.mPrimaryOrientation.getDecoratedMeasurement(next);
                if (z4 && layoutParams.mFullSpan) {
                    LazySpanLookup.FullSpanItem createFullSpanItemFromStart = createFullSpanItemFromStart(i3);
                    createFullSpanItemFromStart.mGapDir = 1;
                    createFullSpanItemFromStart.mPosition = viewLayoutPosition;
                    this.mLazySpanLookup.addFullSpanItem(createFullSpanItemFromStart);
                }
            }
            if (layoutParams.mFullSpan && layoutState.mItemDirection == -1) {
                if (z4) {
                    this.mLaidOutInvalidFullSpan = true;
                } else {
                    if (layoutState.mLayoutDirection == 1) {
                        z2 = !areAllEndsEqual();
                    } else {
                        z2 = !areAllStartsEqual();
                    }
                    if (z2) {
                        LazySpanLookup.FullSpanItem fullSpanItem = this.mLazySpanLookup.getFullSpanItem(viewLayoutPosition);
                        if (fullSpanItem != null) {
                            fullSpanItem.mHasUnwantedGapAfter = true;
                        }
                        this.mLaidOutInvalidFullSpan = true;
                    }
                }
            }
            attachViewToSpans(next, layoutParams, layoutState);
            if (isLayoutRTL() && this.mOrientation == 1) {
                decoratedMeasurement2 = layoutParams.mFullSpan ? this.mSecondaryOrientation.getEndAfterPadding() : this.mSecondaryOrientation.getEndAfterPadding() - (((this.mSpanCount - 1) - span.mIndex) * this.mSizePerSpan);
                i4 = decoratedMeasurement2 - this.mSecondaryOrientation.getDecoratedMeasurement(next);
            } else {
                if (layoutParams.mFullSpan) {
                    startAfterPadding2 = this.mSecondaryOrientation.getStartAfterPadding();
                } else {
                    startAfterPadding2 = (span.mIndex * this.mSizePerSpan) + this.mSecondaryOrientation.getStartAfterPadding();
                }
                i4 = startAfterPadding2;
                decoratedMeasurement2 = i4 + this.mSecondaryOrientation.getDecoratedMeasurement(next);
            }
            if (this.mOrientation == 1) {
                layoutDecoratedWithMargins(next, i4, decoratedMeasurement, decoratedMeasurement2, i3);
            } else {
                layoutDecoratedWithMargins(next, decoratedMeasurement, i4, i3, decoratedMeasurement2);
            }
            if (layoutParams.mFullSpan) {
                updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, i2);
            } else {
                updateRemainingSpans(span, this.mLayoutState.mLayoutDirection, i2);
            }
            recycle(recycler, this.mLayoutState);
            if (this.mLayoutState.mStopInFocusable && next.hasFocusable()) {
                if (layoutParams.mFullSpan) {
                    this.mRemainingSpans.clear();
                } else {
                    this.mRemainingSpans.set(span.mIndex, false);
                }
            }
            z3 = true;
        }
        if (!z) {
            recycle(recycler, this.mLayoutState);
        }
        if (this.mLayoutState.mLayoutDirection == -1) {
            maxEnd = this.mPrimaryOrientation.getStartAfterPadding() - getMinStart(this.mPrimaryOrientation.getStartAfterPadding());
        } else {
            maxEnd = getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        }
        return maxEnd > 0 ? Math.min(layoutState.mAvailable, maxEnd) : 0;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            fullSpanItem.mGapPerSpan[i3] = i2 - this.mSpans[i3].getEndLine(i2);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            fullSpanItem.mGapPerSpan[i3] = this.mSpans[i3].getStartLine(i2) - i2;
        }
        return fullSpanItem;
    }

    private void attachViewToSpans(View view, LayoutParams layoutParams, LayoutState layoutState) {
        if (layoutState.mLayoutDirection == 1) {
            if (layoutParams.mFullSpan) {
                appendViewToAllSpans(view);
                return;
            } else {
                layoutParams.mSpan.appendToSpan(view);
                return;
            }
        }
        if (layoutParams.mFullSpan) {
            prependViewToAllSpans(view);
        } else {
            layoutParams.mSpan.prependToSpan(view);
        }
    }

    private void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        int min;
        int min2;
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            if (layoutState.mAvailable == 0) {
                if (layoutState.mLayoutDirection == -1) {
                    recycleFromEnd(recycler, layoutState.mEndLine);
                    return;
                } else {
                    recycleFromStart(recycler, layoutState.mStartLine);
                    return;
                }
            }
            if (layoutState.mLayoutDirection == -1) {
                int maxStart = layoutState.mStartLine - getMaxStart(layoutState.mStartLine);
                if (maxStart < 0) {
                    min2 = layoutState.mEndLine;
                } else {
                    min2 = layoutState.mEndLine - Math.min(maxStart, layoutState.mAvailable);
                }
                recycleFromEnd(recycler, min2);
                return;
            }
            int minEnd = getMinEnd(layoutState.mEndLine) - layoutState.mEndLine;
            if (minEnd < 0) {
                min = layoutState.mStartLine;
            } else {
                min = layoutState.mStartLine + Math.min(minEnd, layoutState.mAvailable);
            }
            recycleFromStart(recycler, min);
        }
    }

    private void appendViewToAllSpans(View view) {
        for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
            this.mSpans[i2].appendToSpan(view);
        }
    }

    private void prependViewToAllSpans(View view) {
        for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
            this.mSpans[i2].prependToSpan(view);
        }
    }

    private void updateAllRemainingSpans(int i2, int i3) {
        for (int i4 = 0; i4 < this.mSpanCount; i4++) {
            if (!this.mSpans[i4].mViews.isEmpty()) {
                updateRemainingSpans(this.mSpans[i4], i2, i3);
            }
        }
    }

    private void updateRemainingSpans(Span span, int i2, int i3) {
        int deletedSize = span.getDeletedSize();
        if (i2 == -1) {
            if (span.getStartLine() + deletedSize <= i3) {
                this.mRemainingSpans.set(span.mIndex, false);
            }
        } else if (span.getEndLine() - deletedSize >= i3) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
    }

    private int getMaxStart(int i2) {
        int startLine = this.mSpans[0].getStartLine(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int startLine2 = this.mSpans[i3].getStartLine(i2);
            if (startLine2 > startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    private int getMinStart(int i2) {
        int startLine = this.mSpans[0].getStartLine(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int startLine2 = this.mSpans[i3].getStartLine(i2);
            if (startLine2 < startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    boolean areAllEndsEqual() {
        int endLine = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            if (this.mSpans[i2].getEndLine(Integer.MIN_VALUE) != endLine) {
                return false;
            }
        }
        return true;
    }

    boolean areAllStartsEqual() {
        int startLine = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            if (this.mSpans[i2].getStartLine(Integer.MIN_VALUE) != startLine) {
                return false;
            }
        }
        return true;
    }

    private int getMaxEnd(int i2) {
        int endLine = this.mSpans[0].getEndLine(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int endLine2 = this.mSpans[i3].getEndLine(i2);
            if (endLine2 > endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private int getMinEnd(int i2) {
        int endLine = this.mSpans[0].getEndLine(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int endLine2 = this.mSpans[i3].getEndLine(i2);
            if (endLine2 < endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i2) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) <= i2 && this.mPrimaryOrientation.getTransformedEndWithDecoration(childAt) <= i2) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.mFullSpan) {
                    for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                        if (this.mSpans[i3].mViews.size() == 1) {
                            return;
                        }
                    }
                    for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                        this.mSpans[i4].popStart();
                    }
                } else if (layoutParams.mSpan.mViews.size() != 1) {
                    layoutParams.mSpan.popStart();
                } else {
                    return;
                }
                removeAndRecycleView(childAt, recycler);
            } else {
                return;
            }
        }
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i2) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.mPrimaryOrientation.getDecoratedStart(childAt) >= i2 && this.mPrimaryOrientation.getTransformedStartWithDecoration(childAt) >= i2) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.mFullSpan) {
                    for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                        if (this.mSpans[i3].mViews.size() == 1) {
                            return;
                        }
                    }
                    for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                        this.mSpans[i4].popEnd();
                    }
                } else if (layoutParams.mSpan.mViews.size() != 1) {
                    layoutParams.mSpan.popEnd();
                } else {
                    return;
                }
                removeAndRecycleView(childAt, recycler);
            } else {
                return;
            }
        }
    }

    private boolean preferLastSpan(int i2) {
        if (this.mOrientation == 0) {
            return (i2 == -1) != this.mShouldReverseLayout;
        }
        return ((i2 == -1) == this.mShouldReverseLayout) == isLayoutRTL();
    }

    private Span getNextSpan(LayoutState layoutState) {
        int i2;
        int i3;
        int i4;
        if (preferLastSpan(layoutState.mLayoutDirection)) {
            i2 = this.mSpanCount - 1;
            i3 = -1;
            i4 = -1;
        } else {
            i2 = 0;
            i3 = this.mSpanCount;
            i4 = 1;
        }
        if (layoutState.mLayoutDirection == 1) {
            Span span = null;
            int i5 = Integer.MAX_VALUE;
            int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
            int i6 = i2;
            while (true) {
                int i7 = i6;
                if (i7 != i3) {
                    Span span2 = this.mSpans[i7];
                    int endLine = span2.getEndLine(startAfterPadding);
                    if (endLine < i5) {
                        span = span2;
                        i5 = endLine;
                    }
                    i6 = i7 + i4;
                } else {
                    return span;
                }
            }
        } else {
            Span span3 = null;
            int i8 = Integer.MIN_VALUE;
            int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
            int i9 = i2;
            while (true) {
                int i10 = i9;
                if (i10 != i3) {
                    Span span4 = this.mSpans[i10];
                    int startLine = span4.getStartLine(endAfterPadding);
                    if (startLine > i8) {
                        span3 = span4;
                        i8 = startLine;
                    }
                    i9 = i10 + i4;
                } else {
                    return span3;
                }
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i2, recycler, state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i2, recycler, state);
    }

    private int calculateScrollDirectionForPosition(int i2) {
        if (getChildCount() != 0) {
            return (i2 < getFirstChildPosition()) != this.mShouldReverseLayout ? -1 : 1;
        }
        return this.mShouldReverseLayout ? 1 : -1;
    }

    @Override // android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i2) {
        int calculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i2);
        PointF pointF = new PointF();
        if (calculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = calculateScrollDirectionForPosition;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = calculateScrollDirectionForPosition;
        }
        return pointF;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i2) {
        if (this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != i2) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i2, int i3) {
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = i3;
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void collectAdjacentPrefetchPositions(int i2, int i3, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int endLine;
        int i4 = this.mOrientation == 0 ? i2 : i3;
        if (getChildCount() != 0 && i4 != 0) {
            prepareLayoutStateForDelta(i4, state);
            if (this.mPrefetchDistances == null || this.mPrefetchDistances.length < this.mSpanCount) {
                this.mPrefetchDistances = new int[this.mSpanCount];
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.mSpanCount; i6++) {
                if (this.mLayoutState.mItemDirection != -1) {
                    endLine = this.mSpans[i6].getEndLine(this.mLayoutState.mEndLine) - this.mLayoutState.mEndLine;
                } else {
                    endLine = this.mLayoutState.mStartLine - this.mSpans[i6].getStartLine(this.mLayoutState.mStartLine);
                }
                int i7 = endLine;
                if (i7 >= 0) {
                    this.mPrefetchDistances[i5] = i7;
                    i5++;
                }
            }
            Arrays.sort(this.mPrefetchDistances, 0, i5);
            for (int i8 = 0; i8 < i5 && this.mLayoutState.hasMore(state); i8++) {
                layoutPrefetchRegistry.addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[i8]);
                this.mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            }
        }
    }

    void prepareLayoutStateForDelta(int i2, RecyclerView.State state) {
        int i3;
        int firstChildPosition;
        if (i2 > 0) {
            i3 = 1;
            firstChildPosition = getLastChildPosition();
        } else {
            i3 = -1;
            firstChildPosition = getFirstChildPosition();
        }
        this.mLayoutState.mRecycle = true;
        updateLayoutState(firstChildPosition, state);
        setLayoutStateDirection(i3);
        this.mLayoutState.mCurrentPosition = firstChildPosition + this.mLayoutState.mItemDirection;
        this.mLayoutState.mAvailable = Math.abs(i2);
    }

    int scrollBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i3;
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(i2, state);
        int fill = fill(recycler, this.mLayoutState, state);
        if (this.mLayoutState.mAvailable < fill) {
            i3 = i2;
        } else if (i2 < 0) {
            i3 = -fill;
        } else {
            i3 = fill;
        }
        this.mPrimaryOrientation.offsetChildren(-i3);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        this.mLayoutState.mAvailable = 0;
        recycle(recycler, this.mLayoutState);
        return i3;
    }

    int getLastChildPosition() {
        int childCount = getChildCount();
        return childCount == 0 ? 0 : getPosition(getChildAt(childCount - 1));
    }

    int getFirstChildPosition() {
        return getChildCount() == 0 ? 0 : getPosition(getChildAt(0));
    }

    private int findFirstReferenceChildPosition(int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            int position = getPosition(getChildAt(i3));
            if (position >= 0 && position < i2) {
                return position;
            }
        }
        return 0;
    }

    private int findLastReferenceChildPosition(int i2) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i2) {
                return position;
            }
        }
        return 0;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    @Nullable
    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findContainingItemView;
        int firstChildPosition;
        int findLastPartiallyVisibleItemPosition;
        int findLastPartiallyVisibleItemPosition2;
        int findLastPartiallyVisibleItemPosition3;
        View focusableViewAfter;
        if (getChildCount() != 0 && (findContainingItemView = findContainingItemView(view)) != null) {
            resolveShouldLayoutReverse();
            int convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i2);
            if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
                return null;
            }
            LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
            boolean z = layoutParams.mFullSpan;
            Span span = layoutParams.mSpan;
            if (convertFocusDirectionToLayoutDirection == 1) {
                firstChildPosition = getLastChildPosition();
            } else {
                firstChildPosition = getFirstChildPosition();
            }
            updateLayoutState(firstChildPosition, state);
            setLayoutStateDirection(convertFocusDirectionToLayoutDirection);
            this.mLayoutState.mCurrentPosition = firstChildPosition + this.mLayoutState.mItemDirection;
            this.mLayoutState.mAvailable = (int) (MAX_SCROLL_FACTOR * this.mPrimaryOrientation.getTotalSpace());
            this.mLayoutState.mStopInFocusable = true;
            this.mLayoutState.mRecycle = false;
            fill(recycler, this.mLayoutState, state);
            this.mLastLayoutFromEnd = this.mShouldReverseLayout;
            if (!z && (focusableViewAfter = span.getFocusableViewAfter(firstChildPosition, convertFocusDirectionToLayoutDirection)) != null && focusableViewAfter != findContainingItemView) {
                return focusableViewAfter;
            }
            if (preferLastSpan(convertFocusDirectionToLayoutDirection)) {
                for (int i3 = this.mSpanCount - 1; i3 >= 0; i3--) {
                    View focusableViewAfter2 = this.mSpans[i3].getFocusableViewAfter(firstChildPosition, convertFocusDirectionToLayoutDirection);
                    if (focusableViewAfter2 != null && focusableViewAfter2 != findContainingItemView) {
                        return focusableViewAfter2;
                    }
                }
            } else {
                for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                    View focusableViewAfter3 = this.mSpans[i4].getFocusableViewAfter(firstChildPosition, convertFocusDirectionToLayoutDirection);
                    if (focusableViewAfter3 != null && focusableViewAfter3 != findContainingItemView) {
                        return focusableViewAfter3;
                    }
                }
            }
            boolean z2 = (!this.mReverseLayout) == (convertFocusDirectionToLayoutDirection == -1);
            if (!z) {
                if (z2) {
                    findLastPartiallyVisibleItemPosition3 = span.findFirstPartiallyVisibleItemPosition();
                } else {
                    findLastPartiallyVisibleItemPosition3 = span.findLastPartiallyVisibleItemPosition();
                }
                View findViewByPosition = findViewByPosition(findLastPartiallyVisibleItemPosition3);
                if (findViewByPosition != null && findViewByPosition != findContainingItemView) {
                    return findViewByPosition;
                }
            }
            if (preferLastSpan(convertFocusDirectionToLayoutDirection)) {
                for (int i5 = this.mSpanCount - 1; i5 >= 0; i5--) {
                    if (i5 != span.mIndex) {
                        if (z2) {
                            findLastPartiallyVisibleItemPosition2 = this.mSpans[i5].findFirstPartiallyVisibleItemPosition();
                        } else {
                            findLastPartiallyVisibleItemPosition2 = this.mSpans[i5].findLastPartiallyVisibleItemPosition();
                        }
                        View findViewByPosition2 = findViewByPosition(findLastPartiallyVisibleItemPosition2);
                        if (findViewByPosition2 != null && findViewByPosition2 != findContainingItemView) {
                            return findViewByPosition2;
                        }
                    }
                }
            } else {
                for (int i6 = 0; i6 < this.mSpanCount; i6++) {
                    if (z2) {
                        findLastPartiallyVisibleItemPosition = this.mSpans[i6].findFirstPartiallyVisibleItemPosition();
                    } else {
                        findLastPartiallyVisibleItemPosition = this.mSpans[i6].findLastPartiallyVisibleItemPosition();
                    }
                    View findViewByPosition3 = findViewByPosition(findLastPartiallyVisibleItemPosition);
                    if (findViewByPosition3 != null && findViewByPosition3 != findContainingItemView) {
                        return findViewByPosition3;
                    }
                }
            }
            return null;
        }
        return null;
    }

    private int convertFocusDirectionToLayoutDirection(int i2) {
        switch (i2) {
            case 1:
                if (this.mOrientation != 1 && isLayoutRTL()) {
                    return 1;
                }
                return -1;
            case 2:
                if (this.mOrientation != 1 && isLayoutRTL()) {
                    return -1;
                }
                return 1;
            case 17:
                return this.mOrientation == 0 ? -1 : Integer.MIN_VALUE;
            case 33:
                return this.mOrientation == 1 ? -1 : Integer.MIN_VALUE;
            case 66:
                return this.mOrientation == 0 ? 1 : Integer.MIN_VALUE;
            case 130:
                return this.mOrientation == 1 ? 1 : Integer.MIN_VALUE;
            default:
                return Integer.MIN_VALUE;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        boolean mFullSpan;
        Span mSpan;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public void setFullSpan(boolean z) {
            this.mFullSpan = z;
        }

        public boolean isFullSpan() {
            return this.mFullSpan;
        }

        public final int getSpanIndex() {
            if (this.mSpan != null) {
                return this.mSpan.mIndex;
            }
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public class Span {
        static final int INVALID_LINE = Integer.MIN_VALUE;
        final int mIndex;
        ArrayList<View> mViews = new ArrayList<>();
        int mCachedStart = Integer.MIN_VALUE;
        int mCachedEnd = Integer.MIN_VALUE;
        int mDeletedSize = 0;

        Span(int i2) {
            this.mIndex = i2;
        }

        int getStartLine(int i2) {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                return this.mCachedStart;
            }
            if (this.mViews.size() == 0) {
                return i2;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        void calculateCachedStart() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            View view = this.mViews.get(0);
            LayoutParams layoutParams = getLayoutParams(view);
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            if (layoutParams.mFullSpan && (fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition())) != null && fullSpanItem.mGapDir == -1) {
                this.mCachedStart -= fullSpanItem.getGapForSpan(this.mIndex);
            }
        }

        int getStartLine() {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                return this.mCachedStart;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        int getEndLine(int i2) {
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                return this.mCachedEnd;
            }
            if (this.mViews.size() == 0) {
                return i2;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        void calculateCachedEnd() {
            LazySpanLookup.FullSpanItem fullSpanItem;
            View view = this.mViews.get(this.mViews.size() - 1);
            LayoutParams layoutParams = getLayoutParams(view);
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            if (layoutParams.mFullSpan && (fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(layoutParams.getViewLayoutPosition())) != null && fullSpanItem.mGapDir == 1) {
                this.mCachedEnd += fullSpanItem.getGapForSpan(this.mIndex);
            }
        }

        int getEndLine() {
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                return this.mCachedEnd;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        void prependToSpan(View view) {
            LayoutParams layoutParams = getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(0, view);
            this.mCachedStart = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }

        void appendToSpan(View view) {
            LayoutParams layoutParams = getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(view);
            this.mCachedEnd = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }

        void cacheReferenceLineAndClear(boolean z, int i2) {
            int startLine;
            if (z) {
                startLine = getEndLine(Integer.MIN_VALUE);
            } else {
                startLine = getStartLine(Integer.MIN_VALUE);
            }
            clear();
            if (startLine != Integer.MIN_VALUE) {
                if (!z || startLine >= StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding()) {
                    if (z || startLine <= StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding()) {
                        if (i2 != Integer.MIN_VALUE) {
                            startLine += i2;
                        }
                        int i3 = startLine;
                        this.mCachedEnd = i3;
                        this.mCachedStart = i3;
                    }
                }
            }
        }

        void clear() {
            this.mViews.clear();
            invalidateCache();
            this.mDeletedSize = 0;
        }

        void invalidateCache() {
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        void setLine(int i2) {
            this.mCachedStart = i2;
            this.mCachedEnd = i2;
        }

        void popEnd() {
            int size = this.mViews.size();
            View remove = this.mViews.remove(size - 1);
            LayoutParams layoutParams = getLayoutParams(remove);
            layoutParams.mSpan = null;
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(remove);
            }
            if (size == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            this.mCachedEnd = Integer.MIN_VALUE;
        }

        void popStart() {
            View remove = this.mViews.remove(0);
            LayoutParams layoutParams = getLayoutParams(remove);
            layoutParams.mSpan = null;
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(remove);
            }
            this.mCachedStart = Integer.MIN_VALUE;
        }

        public int getDeletedSize() {
            return this.mDeletedSize;
        }

        LayoutParams getLayoutParams(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        void onOffset(int i2) {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                this.mCachedStart += i2;
            }
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                this.mCachedEnd += i2;
            }
        }

        public int findFirstVisibleItemPosition() {
            int findOneVisibleChild;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                findOneVisibleChild = findOneVisibleChild(this.mViews.size() - 1, -1, false);
            } else {
                findOneVisibleChild = findOneVisibleChild(0, this.mViews.size(), false);
            }
            return findOneVisibleChild;
        }

        public int findFirstPartiallyVisibleItemPosition() {
            int findOnePartiallyVisibleChild;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                findOnePartiallyVisibleChild = findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
            } else {
                findOnePartiallyVisibleChild = findOnePartiallyVisibleChild(0, this.mViews.size(), true);
            }
            return findOnePartiallyVisibleChild;
        }

        public int findFirstCompletelyVisibleItemPosition() {
            int findOneVisibleChild;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                findOneVisibleChild = findOneVisibleChild(this.mViews.size() - 1, -1, true);
            } else {
                findOneVisibleChild = findOneVisibleChild(0, this.mViews.size(), true);
            }
            return findOneVisibleChild;
        }

        public int findLastVisibleItemPosition() {
            int findOneVisibleChild;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                findOneVisibleChild = findOneVisibleChild(0, this.mViews.size(), false);
            } else {
                findOneVisibleChild = findOneVisibleChild(this.mViews.size() - 1, -1, false);
            }
            return findOneVisibleChild;
        }

        public int findLastPartiallyVisibleItemPosition() {
            int findOnePartiallyVisibleChild;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                findOnePartiallyVisibleChild = findOnePartiallyVisibleChild(0, this.mViews.size(), true);
            } else {
                findOnePartiallyVisibleChild = findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return findOnePartiallyVisibleChild;
        }

        public int findLastCompletelyVisibleItemPosition() {
            int findOneVisibleChild;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                findOneVisibleChild = findOneVisibleChild(0, this.mViews.size(), true);
            } else {
                findOneVisibleChild = findOneVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return findOneVisibleChild;
        }

        int findOnePartiallyOrCompletelyVisibleChild(int i2, int i3, boolean z, boolean z2, boolean z3) {
            View view;
            boolean z4;
            boolean z5;
            int startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            int endAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            int i4 = i3 > i2 ? 1 : -1;
            int i5 = i2;
            while (true) {
                int i6 = i5;
                if (i6 != i3) {
                    view = this.mViews.get(i6);
                    int decoratedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
                    int decoratedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
                    if (z3) {
                        z4 = decoratedStart <= endAfterPadding;
                    } else {
                        z4 = decoratedStart < endAfterPadding;
                    }
                    boolean z6 = z4;
                    if (z3) {
                        z5 = decoratedEnd >= startAfterPadding;
                    } else {
                        z5 = decoratedEnd > startAfterPadding;
                    }
                    boolean z7 = z5;
                    if (z6 && z7) {
                        if (z && z2) {
                            if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                                return StaggeredGridLayoutManager.this.getPosition(view);
                            }
                        } else {
                            if (z2) {
                                return StaggeredGridLayoutManager.this.getPosition(view);
                            }
                            if (decoratedStart < startAfterPadding || decoratedEnd > endAfterPadding) {
                                break;
                            }
                        }
                    }
                    i5 = i6 + i4;
                } else {
                    return -1;
                }
            }
            return StaggeredGridLayoutManager.this.getPosition(view);
        }

        int findOneVisibleChild(int i2, int i3, boolean z) {
            return findOnePartiallyOrCompletelyVisibleChild(i2, i3, z, true, false);
        }

        int findOnePartiallyVisibleChild(int i2, int i3, boolean z) {
            return findOnePartiallyOrCompletelyVisibleChild(i2, i3, false, false, z);
        }

        public View getFocusableViewAfter(int i2, int i3) {
            View view = null;
            if (i3 == -1) {
                int size = this.mViews.size();
                for (int i4 = 0; i4 < size; i4++) {
                    View view2 = this.mViews.get(i4);
                    if ((StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view2) <= i2) || ((!StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view2) >= i2) || !view2.hasFocusable())) {
                        break;
                    }
                    view = view2;
                }
            } else {
                for (int size2 = this.mViews.size() - 1; size2 >= 0; size2--) {
                    View view3 = this.mViews.get(size2);
                    if ((StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view3) >= i2) || ((!StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.this.getPosition(view3) <= i2) || !view3.hasFocusable())) {
                        break;
                    }
                    view = view3;
                }
            }
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public static class LazySpanLookup {
        private static final int MIN_SIZE = 10;
        int[] mData;
        List<FullSpanItem> mFullSpanItems;

        LazySpanLookup() {
        }

        int forceInvalidateAfter(int i2) {
            if (this.mFullSpanItems != null) {
                for (int size = this.mFullSpanItems.size() - 1; size >= 0; size--) {
                    if (this.mFullSpanItems.get(size).mPosition >= i2) {
                        this.mFullSpanItems.remove(size);
                    }
                }
            }
            return invalidateAfter(i2);
        }

        int invalidateAfter(int i2) {
            if (this.mData != null && i2 < this.mData.length) {
                int invalidateFullSpansAfter = invalidateFullSpansAfter(i2);
                if (invalidateFullSpansAfter == -1) {
                    Arrays.fill(this.mData, i2, this.mData.length, -1);
                    return this.mData.length;
                }
                Arrays.fill(this.mData, i2, invalidateFullSpansAfter + 1, -1);
                return invalidateFullSpansAfter + 1;
            }
            return -1;
        }

        int getSpan(int i2) {
            if (this.mData != null && i2 < this.mData.length) {
                return this.mData[i2];
            }
            return -1;
        }

        void setSpan(int i2, Span span) {
            ensureSize(i2);
            this.mData[i2] = span.mIndex;
        }

        int sizeForPosition(int i2) {
            int length = this.mData.length;
            while (true) {
                int i3 = length;
                if (i3 <= i2) {
                    length = i3 * 2;
                } else {
                    return i3;
                }
            }
        }

        void ensureSize(int i2) {
            if (this.mData == null) {
                this.mData = new int[Math.max(i2, 10) + 1];
                Arrays.fill(this.mData, -1);
            } else if (i2 >= this.mData.length) {
                int[] iArr = this.mData;
                this.mData = new int[sizeForPosition(i2)];
                System.arraycopy(iArr, 0, this.mData, 0, iArr.length);
                Arrays.fill(this.mData, iArr.length, this.mData.length, -1);
            }
        }

        void clear() {
            if (this.mData != null) {
                Arrays.fill(this.mData, -1);
            }
            this.mFullSpanItems = null;
        }

        void offsetForRemoval(int i2, int i3) {
            if (this.mData != null && i2 < this.mData.length) {
                ensureSize(i2 + i3);
                System.arraycopy(this.mData, i2 + i3, this.mData, i2, (this.mData.length - i2) - i3);
                Arrays.fill(this.mData, this.mData.length - i3, this.mData.length, -1);
                offsetFullSpansForRemoval(i2, i3);
            }
        }

        private void offsetFullSpansForRemoval(int i2, int i3) {
            if (this.mFullSpanItems != null) {
                int i4 = i2 + i3;
                for (int size = this.mFullSpanItems.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                    if (fullSpanItem.mPosition >= i2) {
                        if (fullSpanItem.mPosition < i4) {
                            this.mFullSpanItems.remove(size);
                        } else {
                            fullSpanItem.mPosition -= i3;
                        }
                    }
                }
            }
        }

        void offsetForAddition(int i2, int i3) {
            if (this.mData != null && i2 < this.mData.length) {
                ensureSize(i2 + i3);
                System.arraycopy(this.mData, i2, this.mData, i2 + i3, (this.mData.length - i2) - i3);
                Arrays.fill(this.mData, i2, i2 + i3, -1);
                offsetFullSpansForAddition(i2, i3);
            }
        }

        private void offsetFullSpansForAddition(int i2, int i3) {
            if (this.mFullSpanItems != null) {
                for (int size = this.mFullSpanItems.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                    if (fullSpanItem.mPosition >= i2) {
                        fullSpanItem.mPosition += i3;
                    }
                }
            }
        }

        private int invalidateFullSpansAfter(int i2) {
            if (this.mFullSpanItems == null) {
                return -1;
            }
            FullSpanItem fullSpanItem = getFullSpanItem(i2);
            if (fullSpanItem != null) {
                this.mFullSpanItems.remove(fullSpanItem);
            }
            int i3 = -1;
            int size = this.mFullSpanItems.size();
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    break;
                }
                if (this.mFullSpanItems.get(i4).mPosition < i2) {
                    i4++;
                } else {
                    i3 = i4;
                    break;
                }
            }
            if (i3 != -1) {
                FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i3);
                this.mFullSpanItems.remove(i3);
                return fullSpanItem2.mPosition;
            }
            return -1;
        }

        public void addFullSpanItem(FullSpanItem fullSpanItem) {
            if (this.mFullSpanItems == null) {
                this.mFullSpanItems = new ArrayList();
            }
            int size = this.mFullSpanItems.size();
            for (int i2 = 0; i2 < size; i2++) {
                FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i2);
                if (fullSpanItem2.mPosition == fullSpanItem.mPosition) {
                    this.mFullSpanItems.remove(i2);
                }
                if (fullSpanItem2.mPosition >= fullSpanItem.mPosition) {
                    this.mFullSpanItems.add(i2, fullSpanItem);
                    return;
                }
            }
            this.mFullSpanItems.add(fullSpanItem);
        }

        public FullSpanItem getFullSpanItem(int i2) {
            if (this.mFullSpanItems == null) {
                return null;
            }
            for (int size = this.mFullSpanItems.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(size);
                if (fullSpanItem.mPosition == i2) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem getFirstFullSpanItemInRange(int i2, int i3, int i4, boolean z) {
            if (this.mFullSpanItems == null) {
                return null;
            }
            int size = this.mFullSpanItems.size();
            for (int i5 = 0; i5 < size; i5++) {
                FullSpanItem fullSpanItem = this.mFullSpanItems.get(i5);
                if (fullSpanItem.mPosition >= i3) {
                    return null;
                }
                if (fullSpanItem.mPosition >= i2 && (i4 == 0 || fullSpanItem.mGapDir == i4 || (z && fullSpanItem.mHasUnwantedGapAfter))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: lib/Mus.dex */
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new Parcelable.Creator<FullSpanItem>() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FullSpanItem[] newArray(int i2) {
                    return new FullSpanItem[i2];
                }
            };
            int mGapDir;
            int[] mGapPerSpan;
            boolean mHasUnwantedGapAfter;
            int mPosition;

            FullSpanItem(Parcel parcel) {
                this.mPosition = parcel.readInt();
                this.mGapDir = parcel.readInt();
                this.mHasUnwantedGapAfter = parcel.readInt() == 1;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    this.mGapPerSpan = new int[readInt];
                    parcel.readIntArray(this.mGapPerSpan);
                }
            }

            FullSpanItem() {
            }

            int getGapForSpan(int i2) {
                return this.mGapPerSpan == null ? 0 : this.mGapPerSpan[i2];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.mPosition);
                parcel.writeInt(this.mGapDir);
                parcel.writeInt(this.mHasUnwantedGapAfter ? 1 : 0);
                if (this.mGapPerSpan != null && this.mGapPerSpan.length > 0) {
                    parcel.writeInt(this.mGapPerSpan.length);
                    parcel.writeIntArray(this.mGapPerSpan);
                } else {
                    parcel.writeInt(0);
                }
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v7.widget.StaggeredGridLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List<LazySpanLookup.FullSpanItem> mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mVisibleAnchorPosition = parcel.readInt();
            this.mSpanOffsetsSize = parcel.readInt();
            if (this.mSpanOffsetsSize > 0) {
                this.mSpanOffsets = new int[this.mSpanOffsetsSize];
                parcel.readIntArray(this.mSpanOffsets);
            }
            this.mSpanLookupSize = parcel.readInt();
            if (this.mSpanLookupSize > 0) {
                this.mSpanLookup = new int[this.mSpanLookupSize];
                parcel.readIntArray(this.mSpanLookup);
            }
            this.mReverseLayout = parcel.readInt() == 1;
            this.mAnchorLayoutFromEnd = parcel.readInt() == 1;
            this.mLastLayoutRTL = parcel.readInt() == 1;
            this.mFullSpanItems = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
            this.mSpanOffsets = savedState.mSpanOffsets;
            this.mSpanLookupSize = savedState.mSpanLookupSize;
            this.mSpanLookup = savedState.mSpanLookup;
            this.mReverseLayout = savedState.mReverseLayout;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = savedState.mLastLayoutRTL;
            this.mFullSpanItems = savedState.mFullSpanItems;
        }

        void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }

        void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mVisibleAnchorPosition);
            parcel.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                parcel.writeIntArray(this.mSpanOffsets);
            }
            parcel.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                parcel.writeIntArray(this.mSpanLookup);
            }
            parcel.writeInt(this.mReverseLayout ? 1 : 0);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            parcel.writeInt(this.mLastLayoutRTL ? 1 : 0);
            parcel.writeList(this.mFullSpanItems);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public class AnchorInfo {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        int[] mSpanReferenceLines;
        boolean mValid;

        AnchorInfo() {
            reset();
        }

        void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            if (this.mSpanReferenceLines != null) {
                Arrays.fill(this.mSpanReferenceLines, -1);
            }
        }

        void saveSpanReferenceLines(Span[] spanArr) {
            int length = spanArr.length;
            if (this.mSpanReferenceLines == null || this.mSpanReferenceLines.length < length) {
                this.mSpanReferenceLines = new int[StaggeredGridLayoutManager.this.mSpans.length];
            }
            for (int i2 = 0; i2 < length; i2++) {
                this.mSpanReferenceLines[i2] = spanArr[i2].getStartLine(Integer.MIN_VALUE);
            }
        }

        void assignCoordinateFromPadding() {
            int startAfterPadding;
            if (this.mLayoutFromEnd) {
                startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            } else {
                startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            }
            this.mOffset = startAfterPadding;
        }

        void assignCoordinateFromPadding(int i2) {
            if (this.mLayoutFromEnd) {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - i2;
            } else {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding() + i2;
            }
        }
    }
}
