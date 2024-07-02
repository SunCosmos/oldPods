package androidx.recyclerview.widget;

import android.content.Context;
import android.support.v7.widget.ActivityChooserView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class SnapHelper extends RecyclerView.OnFlingListener {
    RecyclerView a;
    private Scroller b;

    /* renamed from: c */
    private final RecyclerView.OnScrollListener f978c = new a();

    /* loaded from: classes.dex */
    public class a extends RecyclerView.OnScrollListener {
        boolean a = false;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
            if (i2 == 0 && this.a) {
                this.a = false;
                SnapHelper.this.f();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            if (i2 == 0 && i3 == 0) {
                return;
            }
            this.a = true;
        }
    }

    /* loaded from: classes.dex */
    public class b extends LinearSmoothScroller {
        b(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void g(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            SnapHelper snapHelper = SnapHelper.this;
            RecyclerView recyclerView = snapHelper.a;
            if (recyclerView == null) {
                return;
            }
            int[] calculateDistanceToFinalSnap = snapHelper.calculateDistanceToFinalSnap(recyclerView.getLayoutManager(), view);
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
    }

    private void c() {
        this.a.removeOnScrollListener(this.f978c);
        this.a.setOnFlingListener(null);
    }

    private void d() {
        if (this.a.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.a.addOnScrollListener(this.f978c);
        this.a.setOnFlingListener(this);
    }

    private boolean e(@NonNull RecyclerView.LayoutManager layoutManager, int i2, int i3) {
        RecyclerView.SmoothScroller a2;
        int findTargetSnapPosition;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (a2 = a(layoutManager)) == null || (findTargetSnapPosition = findTargetSnapPosition(layoutManager, i2, i3)) == -1) {
            return false;
        }
        a2.setTargetPosition(findTargetSnapPosition);
        layoutManager.startSmoothScroll(a2);
        return true;
    }

    @Nullable
    protected RecyclerView.SmoothScroller a(RecyclerView.LayoutManager layoutManager) {
        return b(layoutManager);
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            c();
        }
        this.a = recyclerView;
        if (recyclerView != null) {
            d();
            this.b = new Scroller(this.a.getContext(), new DecelerateInterpolator());
            f();
        }
    }

    @Nullable
    @Deprecated
    protected LinearSmoothScroller b(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new b(this.a.getContext());
        }
        return null;
    }

    @Nullable
    public abstract int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view);

    public int[] calculateScrollDistance(int i2, int i3) {
        this.b.fling(0, 0, i2, i3, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        return new int[]{this.b.getFinalX(), this.b.getFinalY()};
    }

    void f() {
        RecyclerView.LayoutManager layoutManager;
        View findSnapView;
        RecyclerView recyclerView = this.a;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (findSnapView = findSnapView(layoutManager)) == null) {
            return;
        }
        int[] calculateDistanceToFinalSnap = calculateDistanceToFinalSnap(layoutManager, findSnapView);
        if (calculateDistanceToFinalSnap[0] == 0 && calculateDistanceToFinalSnap[1] == 0) {
            return;
        }
        this.a.smoothScrollBy(calculateDistanceToFinalSnap[0], calculateDistanceToFinalSnap[1]);
    }

    @Nullable
    public abstract View findSnapView(RecyclerView.LayoutManager layoutManager);

    public abstract int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i2, int i3);

    @Override // androidx.recyclerview.widget.RecyclerView.OnFlingListener
    public boolean onFling(int i2, int i3) {
        RecyclerView.LayoutManager layoutManager = this.a.getLayoutManager();
        if (layoutManager == null || this.a.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.a.getMinFlingVelocity();
        return (Math.abs(i3) > minFlingVelocity || Math.abs(i2) > minFlingVelocity) && e(layoutManager, i2, i3);
    }
}
