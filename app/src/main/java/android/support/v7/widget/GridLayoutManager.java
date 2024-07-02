package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: lib/Mus.dex */
public class GridLayoutManager extends LinearLayoutManager {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View[] mSet;
    int mSpanCount;
    SpanSizeLookup mSpanSizeLookup;

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        setSpanCount(getProperties(context, attributeSet, i2, i3).spanCount);
    }

    public GridLayoutManager(Context context, int i2) {
        super(context);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        setSpanCount(i2);
    }

    public GridLayoutManager(Context context, int i2, int i3, boolean z) {
        super(context, i3, z);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        setSpanCount(i2);
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public void setStackFromEnd(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, layoutParams2.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
        } else {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
        }
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        clearPreLayoutSpanMappingCache();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }

    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    private void cachePreLayoutSpanMapping() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i2, int i3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i3, Object obj) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i2, int i3, int i4) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
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

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    private void updateMeasurements() {
        calculateItemBorders(getOrientation() == 1 ? (getWidth() - getPaddingRight()) - getPaddingLeft() : (getHeight() - getPaddingBottom()) - getPaddingTop());
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect rect, int i2, int i3) {
        int chooseSize;
        int chooseSize2;
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(rect, i2, i3);
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            chooseSize2 = chooseSize(i3, rect.height() + paddingTop, getMinimumHeight());
            chooseSize = chooseSize(i2, this.mCachedBorders[this.mCachedBorders.length - 1] + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = chooseSize(i2, rect.width() + paddingLeft, getMinimumWidth());
            chooseSize2 = chooseSize(i3, this.mCachedBorders[this.mCachedBorders.length - 1] + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    private void calculateItemBorders(int i2) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, i2);
    }

    static int[] calculateItemBorders(int[] iArr, int i2, int i3) {
        int[] iArr2 = iArr;
        if (iArr2 == null || iArr2.length != i2 + 1 || iArr2[iArr2.length - 1] != i3) {
            iArr2 = new int[i2 + 1];
        }
        iArr2[0] = 0;
        int i4 = i3 / i2;
        int i5 = i3 % i2;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 1; i8 <= i2; i8++) {
            int i9 = i4;
            i7 += i5;
            if (i7 > 0 && i2 - i7 < i5) {
                i9++;
                i7 -= i2;
            }
            i6 += i9;
            iArr2[i8] = i6;
        }
        return iArr2;
    }

    int getSpaceForSpanRange(int i2, int i3) {
        return (this.mOrientation == 1 && isLayoutRTL()) ? this.mCachedBorders[this.mSpanCount - i2] - this.mCachedBorders[(this.mSpanCount - i2) - i3] : this.mCachedBorders[i2 + i3] - this.mCachedBorders[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.widget.LinearLayoutManager
    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i2) {
        super.onAnchorReady(recycler, state, anchorInfo, i2);
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, i2);
        }
        ensureViewSet();
    }

    private void ensureViewSet() {
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(i2, recycler, state);
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(i2, recycler, state);
    }

    private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i2) {
        boolean z = i2 == 1;
        int spanIndex = getSpanIndex(recycler, state, anchorInfo.mPosition);
        if (z) {
            while (spanIndex > 0 && anchorInfo.mPosition > 0) {
                anchorInfo.mPosition--;
                spanIndex = getSpanIndex(recycler, state, anchorInfo.mPosition);
            }
            return;
        }
        int itemCount = state.getItemCount() - 1;
        int i3 = anchorInfo.mPosition;
        int i4 = spanIndex;
        while (true) {
            int i5 = i4;
            if (i3 >= itemCount) {
                break;
            }
            int spanIndex2 = getSpanIndex(recycler, state, i3 + 1);
            if (spanIndex2 <= i5) {
                break;
            }
            i3++;
            i4 = spanIndex2;
        }
        anchorInfo.mPosition = i3;
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, int i3, int i4) {
        ensureLayoutState();
        View view = null;
        View view2 = null;
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i5 = i3 > i2 ? 1 : -1;
        int i6 = i2;
        while (true) {
            int i7 = i6;
            if (i7 == i3) {
                return view2 != null ? view2 : view;
            }
            View childAt = getChildAt(i7);
            int position = getPosition(childAt);
            if (position >= 0 && position < i4 && getSpanIndex(recycler, state, position) == 0) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view == null) {
                        view = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) >= endAfterPadding || this.mOrientationHelper.getDecoratedEnd(childAt) < startAfterPadding) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    return childAt;
                }
            }
            i6 = i7 + i5;
        }
    }

    private int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(i2, this.mSpanCount);
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mSpanSizeLookup.getSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        Log.w(TAG, "Cannot find span size for pre layout position. " + i2);
        return 0;
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanIndex(i2, this.mSpanCount);
        }
        int i3 = this.mPreLayoutSpanIndexCache.get(i2, -1);
        if (i3 != -1) {
            return i3;
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mSpanSizeLookup.getCachedSpanIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        Log.w(TAG, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
        return 0;
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanSize(i2);
        }
        int i3 = this.mPreLayoutSpanSizeCache.get(i2, -1);
        if (i3 != -1) {
            return i3;
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
        if (convertPreLayoutPositionToPostLayout == -1) {
            Log.w(TAG, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i2);
            return 1;
        }
        return this.mSpanSizeLookup.getSpanSize(convertPreLayoutPositionToPostLayout);
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = this.mSpanCount;
        for (int i3 = 0; i3 < this.mSpanCount && layoutState.hasMore(state) && i2 > 0; i3++) {
            int i4 = layoutState.mCurrentPosition;
            layoutPrefetchRegistry.addPosition(i4, Math.max(0, layoutState.mScrollingOffset));
            i2 -= this.mSpanSizeLookup.getSpanSize(i4);
            layoutState.mCurrentPosition += layoutState.mItemDirection;
        }
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, LinearLayoutManager.LayoutChunkResult layoutChunkResult) {
        int i2;
        int makeMeasureSpec;
        int childMeasureSpec;
        View next;
        int modeInOther = this.mOrientationHelper.getModeInOther();
        boolean z = modeInOther != 1073741824;
        if (getChildCount() > 0) {
            i2 = this.mCachedBorders[this.mSpanCount];
        } else {
            i2 = 0;
        }
        int i3 = i2;
        if (z) {
            updateMeasurements();
        }
        boolean z2 = layoutState.mItemDirection == 1;
        int i4 = 0;
        int i5 = 0;
        int i6 = this.mSpanCount;
        if (!z2) {
            i6 = getSpanIndex(recycler, state, layoutState.mCurrentPosition) + getSpanSize(recycler, state, layoutState.mCurrentPosition);
        }
        while (i4 < this.mSpanCount && layoutState.hasMore(state) && i6 > 0) {
            int i7 = layoutState.mCurrentPosition;
            int spanSize = getSpanSize(recycler, state, i7);
            if (spanSize > this.mSpanCount) {
                throw new IllegalArgumentException("Item at position " + i7 + " requires " + spanSize + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
            i6 -= spanSize;
            if (i6 < 0 || (next = layoutState.next(recycler)) == null) {
                break;
            }
            i5 += spanSize;
            this.mSet[i4] = next;
            i4++;
        }
        if (i4 == 0) {
            layoutChunkResult.mFinished = true;
            return;
        }
        int i8 = 0;
        float f = 0.0f;
        assignSpans(recycler, state, i4, i5, z2);
        for (int i9 = 0; i9 < i4; i9++) {
            View view = this.mSet[i9];
            if (layoutState.mScrapList == null) {
                if (z2) {
                    addView(view);
                } else {
                    addView(view, 0);
                }
            } else if (z2) {
                addDisappearingView(view);
            } else {
                addDisappearingView(view, 0);
            }
            calculateItemDecorationsForChild(view, this.mDecorInsets);
            measureChild(view, modeInOther, false);
            int decoratedMeasurement = this.mOrientationHelper.getDecoratedMeasurement(view);
            if (decoratedMeasurement > i8) {
                i8 = decoratedMeasurement;
            }
            float decoratedMeasurementInOther = (1.0f * this.mOrientationHelper.getDecoratedMeasurementInOther(view)) / ((LayoutParams) view.getLayoutParams()).mSpanSize;
            if (decoratedMeasurementInOther > f) {
                f = decoratedMeasurementInOther;
            }
        }
        if (z) {
            guessMeasurement(f, i3);
            i8 = 0;
            for (int i10 = 0; i10 < i4; i10++) {
                View view2 = this.mSet[i10];
                measureChild(view2, BasicMeasure.EXACTLY, true);
                int decoratedMeasurement2 = this.mOrientationHelper.getDecoratedMeasurement(view2);
                if (decoratedMeasurement2 > i8) {
                    i8 = decoratedMeasurement2;
                }
            }
        }
        for (int i11 = 0; i11 < i4; i11++) {
            View view3 = this.mSet[i11];
            if (this.mOrientationHelper.getDecoratedMeasurement(view3) != i8) {
                LayoutParams layoutParams = (LayoutParams) view3.getLayoutParams();
                Rect rect = layoutParams.mDecorInsets;
                int i12 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
                int i13 = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
                int spaceForSpanRange = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
                if (this.mOrientation == 1) {
                    makeMeasureSpec = getChildMeasureSpec(spaceForSpanRange, BasicMeasure.EXACTLY, i13, layoutParams.width, false);
                    childMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - i12, BasicMeasure.EXACTLY);
                } else {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - i13, BasicMeasure.EXACTLY);
                    childMeasureSpec = getChildMeasureSpec(spaceForSpanRange, BasicMeasure.EXACTLY, i12, layoutParams.height, false);
                }
                measureChildWithDecorationsAndMargin(view3, makeMeasureSpec, childMeasureSpec, true);
            }
        }
        layoutChunkResult.mConsumed = i8;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        if (this.mOrientation == 1) {
            if (layoutState.mLayoutDirection == -1) {
                i17 = layoutState.mOffset;
                i16 = i17 - i8;
            } else {
                i16 = layoutState.mOffset;
                i17 = i16 + i8;
            }
        } else if (layoutState.mLayoutDirection == -1) {
            i15 = layoutState.mOffset;
            i14 = i15 - i8;
        } else {
            i14 = layoutState.mOffset;
            i15 = i14 + i8;
        }
        for (int i18 = 0; i18 < i4; i18++) {
            View view4 = this.mSet[i18];
            LayoutParams layoutParams2 = (LayoutParams) view4.getLayoutParams();
            if (this.mOrientation != 1) {
                i16 = getPaddingTop() + this.mCachedBorders[layoutParams2.mSpanIndex];
                i17 = i16 + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
            } else if (isLayoutRTL()) {
                i15 = getPaddingLeft() + this.mCachedBorders[this.mSpanCount - layoutParams2.mSpanIndex];
                i14 = i15 - this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
            } else {
                i14 = getPaddingLeft() + this.mCachedBorders[layoutParams2.mSpanIndex];
                i15 = i14 + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
            }
            layoutDecoratedWithMargins(view4, i14, i16, i15, i17);
            if (layoutParams2.isItemRemoved() || layoutParams2.isItemChanged()) {
                layoutChunkResult.mIgnoreConsumed = true;
            }
            layoutChunkResult.mFocusable |= view4.hasFocusable();
        }
        Arrays.fill(this.mSet, (Object) null);
    }

    private void measureChild(View view, int i2, boolean z) {
        int childMeasureSpec;
        int childMeasureSpec2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.mDecorInsets;
        int i3 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        int i4 = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
        int spaceForSpanRange = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        if (this.mOrientation == 1) {
            childMeasureSpec2 = getChildMeasureSpec(spaceForSpanRange, i2, i4, layoutParams.width, false);
            childMeasureSpec = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), i3, layoutParams.height, true);
        } else {
            childMeasureSpec = getChildMeasureSpec(spaceForSpanRange, i2, i3, layoutParams.height, false);
            childMeasureSpec2 = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), i4, layoutParams.width, true);
        }
        measureChildWithDecorationsAndMargin(view, childMeasureSpec2, childMeasureSpec, z);
    }

    private void guessMeasurement(float f, int i2) {
        calculateItemBorders(Math.max(Math.round(f * this.mSpanCount), i2));
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i3, boolean z) {
        boolean shouldMeasureChild;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z) {
            shouldMeasureChild = shouldReMeasureChild(view, i2, i3, layoutParams);
        } else {
            shouldMeasureChild = shouldMeasureChild(view, i2, i3, layoutParams);
        }
        if (shouldMeasureChild) {
            view.measure(i2, i3);
        }
    }

    private void assignSpans(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, int i3, boolean z) {
        int i4;
        int i5;
        int i6;
        if (z) {
            i4 = 0;
            i5 = i2;
            i6 = 1;
        } else {
            i4 = i2 - 1;
            i5 = -1;
            i6 = -1;
        }
        int i7 = 0;
        int i8 = i4;
        while (true) {
            int i9 = i8;
            if (i9 != i5) {
                View view = this.mSet[i9];
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.mSpanSize = getSpanSize(recycler, state, getPosition(view));
                layoutParams.mSpanIndex = i7;
                i7 += layoutParams.mSpanSize;
                i8 = i9 + i6;
            } else {
                return;
            }
        }
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void setSpanCount(int i2) {
        if (i2 != this.mSpanCount) {
            this.mPendingSpanCountChange = true;
            if (i2 < 1) {
                throw new IllegalArgumentException("Span count should be at least 1. Provided " + i2);
            }
            this.mSpanCount = i2;
            this.mSpanSizeLookup.invalidateSpanIndexCache();
            requestLayout();
        }
    }

    /* loaded from: lib/Mus.dex */
    public static abstract class SpanSizeLookup {
        final SparseIntArray mSpanIndexCache = new SparseIntArray();
        private boolean mCacheSpanIndices = false;

        public abstract int getSpanSize(int i2);

        public void setSpanIndexCacheEnabled(boolean z) {
            this.mCacheSpanIndices = z;
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        int getCachedSpanIndex(int i2, int i3) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(i2, i3);
            }
            int i4 = this.mSpanIndexCache.get(i2, -1);
            if (i4 != -1) {
                return i4;
            }
            int spanIndex = getSpanIndex(i2, i3);
            this.mSpanIndexCache.put(i2, spanIndex);
            return spanIndex;
        }

        public int getSpanIndex(int i2, int i3) {
            int findReferenceIndexFromCache;
            int spanSize = getSpanSize(i2);
            if (spanSize == i3) {
                return 0;
            }
            int i4 = 0;
            int i5 = 0;
            if (this.mCacheSpanIndices && this.mSpanIndexCache.size() > 0 && (findReferenceIndexFromCache = findReferenceIndexFromCache(i2)) >= 0) {
                i4 = this.mSpanIndexCache.get(findReferenceIndexFromCache) + getSpanSize(findReferenceIndexFromCache);
                i5 = findReferenceIndexFromCache + 1;
            }
            for (int i6 = i5; i6 < i2; i6++) {
                int spanSize2 = getSpanSize(i6);
                i4 += spanSize2;
                if (i4 == i3) {
                    i4 = 0;
                } else if (i4 > i3) {
                    i4 = spanSize2;
                }
            }
            if (i4 + spanSize <= i3) {
                return i4;
            }
            return 0;
        }

        int findReferenceIndexFromCache(int i2) {
            int i3 = 0;
            int size = this.mSpanIndexCache.size() - 1;
            while (i3 <= size) {
                int i4 = (i3 + size) >>> 1;
                if (this.mSpanIndexCache.keyAt(i4) < i2) {
                    i3 = i4 + 1;
                } else {
                    size = i4 - 1;
                }
            }
            int i5 = i3 - 1;
            if (i5 < 0 || i5 >= this.mSpanIndexCache.size()) {
                return -1;
            }
            return this.mSpanIndexCache.keyAt(i5);
        }

        public int getSpanGroupIndex(int i2, int i3) {
            int i4 = 0;
            int i5 = 0;
            int spanSize = getSpanSize(i2);
            for (int i6 = 0; i6 < i2; i6++) {
                int spanSize2 = getSpanSize(i6);
                i4 += spanSize2;
                if (i4 == i3) {
                    i4 = 0;
                    i5++;
                } else if (i4 > i3) {
                    i4 = spanSize2;
                    i5++;
                }
            }
            if (i4 + spanSize > i3) {
                i5++;
            }
            return i5;
        }
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i3;
        int i4;
        int childCount;
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
        int i5 = layoutParams.mSpanIndex;
        int i6 = layoutParams.mSpanIndex + layoutParams.mSpanSize;
        if (super.onFocusSearchFailed(view, i2, recycler, state) == null) {
            return null;
        }
        if ((convertFocusDirectionToLayoutDirection(i2) == 1) != this.mShouldReverseLayout) {
            i3 = getChildCount() - 1;
            i4 = -1;
            childCount = -1;
        } else {
            i3 = 0;
            i4 = 1;
            childCount = getChildCount();
        }
        boolean z = this.mOrientation == 1 && isLayoutRTL();
        View view2 = null;
        int i7 = -1;
        int i8 = 0;
        View view3 = null;
        int i9 = -1;
        int i10 = 0;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, i3);
        int i11 = i3;
        while (true) {
            int i12 = i11;
            if (i12 == childCount) {
                break;
            }
            int spanGroupIndex2 = getSpanGroupIndex(recycler, state, i12);
            View childAt = getChildAt(i12);
            if (childAt == findContainingItemView) {
                break;
            }
            if (childAt.hasFocusable() && spanGroupIndex2 != spanGroupIndex) {
                if (view2 != null) {
                    break;
                }
            } else {
                LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                int i13 = layoutParams2.mSpanIndex;
                int i14 = layoutParams2.mSpanIndex + layoutParams2.mSpanSize;
                if (childAt.hasFocusable() && i13 == i5 && i14 == i6) {
                    return childAt;
                }
                boolean z2 = false;
                if ((childAt.hasFocusable() && view2 == null) || (!childAt.hasFocusable() && view3 == null)) {
                    z2 = true;
                } else {
                    int min = Math.min(i14, i6) - Math.max(i13, i5);
                    if (childAt.hasFocusable()) {
                        if (min > i8) {
                            z2 = true;
                        } else if (min == i8) {
                            if (z == (i13 > i7)) {
                                z2 = true;
                            }
                        }
                    } else if (view2 == null && isViewPartiallyVisible(childAt, false, true)) {
                        if (min > i10) {
                            z2 = true;
                        } else if (min == i10) {
                            if (z == (i13 > i9)) {
                                z2 = true;
                            }
                        }
                    }
                }
                if (z2) {
                    if (childAt.hasFocusable()) {
                        view2 = childAt;
                        i7 = layoutParams2.mSpanIndex;
                        i8 = Math.min(i14, i6) - Math.max(i13, i5);
                    } else {
                        view3 = childAt;
                        i9 = layoutParams2.mSpanIndex;
                        i10 = Math.min(i14, i6) - Math.max(i13, i5);
                    }
                }
            }
            i11 = i12 + i4;
        }
        return view2 != null ? view2 : view3;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }

    /* loaded from: lib/Mus.dex */
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i2) {
            return 1;
        }

        @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanIndex(int i2, int i3) {
            return i2 % i3;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        int mSpanIndex;
        int mSpanSize;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public int getSpanIndex() {
            return this.mSpanIndex;
        }

        public int getSpanSize() {
            return this.mSpanSize;
        }
    }
}
