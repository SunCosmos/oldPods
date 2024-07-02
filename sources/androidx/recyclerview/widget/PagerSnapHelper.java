package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.ActivityChooserView;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class PagerSnapHelper extends SnapHelper {

    /* renamed from: d */
    @Nullable
    private OrientationHelper f954d;

    @Nullable
    private OrientationHelper e;

    /* loaded from: classes.dex */
    class a extends LinearSmoothScroller {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void g(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            PagerSnapHelper pagerSnapHelper = PagerSnapHelper.this;
            int[] calculateDistanceToFinalSnap = pagerSnapHelper.calculateDistanceToFinalSnap(pagerSnapHelper.a.getLayoutManager(), view);
            int i2 = calculateDistanceToFinalSnap[0];
            int i3 = calculateDistanceToFinalSnap[1];
            int k = k(Math.max(Math.abs(i2), Math.abs(i3)));
            if (k > 0) {
                action.update(i2, i3, k, this.j);
            }
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected float j(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int l(int i2) {
            return Math.min(100, super.l(i2));
        }
    }

    private int g(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view, OrientationHelper orientationHelper) {
        return (orientationHelper.getDecoratedStart(view) + (orientationHelper.getDecoratedMeasurement(view) / 2)) - (orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2));
    }

    @Nullable
    private View h(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int startAfterPadding = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = layoutManager.getChildAt(i3);
            int abs = Math.abs((orientationHelper.getDecoratedStart(childAt) + (orientationHelper.getDecoratedMeasurement(childAt) / 2)) - startAfterPadding);
            if (abs < i2) {
                view = childAt;
                i2 = abs;
            }
        }
        return view;
    }

    @NonNull
    private OrientationHelper i(@NonNull RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.e;
        if (orientationHelper == null || orientationHelper.a != layoutManager) {
            this.e = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.e;
    }

    @Nullable
    private OrientationHelper j(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return k(layoutManager);
        }
        if (layoutManager.canScrollHorizontally()) {
            return i(layoutManager);
        }
        return null;
    }

    @NonNull
    private OrientationHelper k(@NonNull RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.f954d;
        if (orientationHelper == null || orientationHelper.a != layoutManager) {
            this.f954d = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.f954d;
    }

    private boolean l(RecyclerView.LayoutManager layoutManager, int i2, int i3) {
        return layoutManager.canScrollHorizontally() ? i2 > 0 : i3 > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean m(RecyclerView.LayoutManager layoutManager) {
        PointF computeScrollVectorForPosition;
        int itemCount = layoutManager.getItemCount();
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (computeScrollVectorForPosition = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return false;
        }
        return computeScrollVectorForPosition.x < 0.0f || computeScrollVectorForPosition.y < 0.0f;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    protected LinearSmoothScroller b(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new a(this.a.getContext());
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = g(layoutManager, view, i(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = g(layoutManager, view, k(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    @Nullable
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper i2;
        if (layoutManager.canScrollVertically()) {
            i2 = k(layoutManager);
        } else {
            if (!layoutManager.canScrollHorizontally()) {
                return null;
            }
            i2 = i(layoutManager);
        }
        return h(layoutManager, i2);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i2, int i3) {
        OrientationHelper j;
        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0 || (j = j(layoutManager)) == null) {
            return -1;
        }
        int i4 = Integer.MIN_VALUE;
        int i5 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int childCount = layoutManager.getChildCount();
        View view = null;
        View view2 = null;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = layoutManager.getChildAt(i6);
            if (childAt != null) {
                int g = g(layoutManager, childAt, j);
                if (g <= 0 && g > i4) {
                    view2 = childAt;
                    i4 = g;
                }
                if (g >= 0 && g < i5) {
                    view = childAt;
                    i5 = g;
                }
            }
        }
        boolean l = l(layoutManager, i2, i3);
        if (l && view != null) {
            return layoutManager.getPosition(view);
        }
        if (!l && view2 != null) {
            return layoutManager.getPosition(view2);
        }
        if (l) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int position = layoutManager.getPosition(view) + (m(layoutManager) == l ? -1 : 1);
        if (position < 0 || position >= itemCount) {
            return -1;
        }
        return position;
    }
}
