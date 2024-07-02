package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class LinearSmoothScroller extends RecyclerView.SmoothScroller {
    public static final int SNAP_TO_ANY = 0;
    public static final int SNAP_TO_END = 1;
    public static final int SNAP_TO_START = -1;
    protected PointF k;
    private final DisplayMetrics l;
    private float n;

    /* renamed from: i, reason: collision with root package name */
    protected final LinearInterpolator f950i = new LinearInterpolator();
    protected final DecelerateInterpolator j = new DecelerateInterpolator();
    private boolean m = false;
    protected int o = 0;
    protected int p = 0;

    public LinearSmoothScroller(Context context) {
        this.l = context.getResources().getDisplayMetrics();
    }

    private int m(int i2, int i3) {
        int i4 = i2 - i3;
        if (i2 * i4 <= 0) {
            return 0;
        }
        return i4;
    }

    private float o() {
        if (!this.m) {
            this.n = j(this.l);
            this.m = true;
        }
        return this.n;
    }

    public int calculateDtToFit(int i2, int i3, int i4, int i5, int i6) {
        if (i6 == -1) {
            return i4 - i2;
        }
        if (i6 != 0) {
            if (i6 == 1) {
                return i5 - i3;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i7 = i4 - i2;
        if (i7 > 0) {
            return i7;
        }
        int i8 = i5 - i3;
        if (i8 < 0) {
            return i8;
        }
        return 0;
    }

    public int calculateDxToMakeVisible(View view, int i2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, layoutManager.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), i2);
    }

    public int calculateDyToMakeVisible(View view, int i2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutManager.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutManager.getPaddingTop(), layoutManager.getHeight() - layoutManager.getPaddingBottom(), i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void d(int i2, int i3, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        if (getChildCount() == 0) {
            i();
            return;
        }
        this.o = m(this.o, i2);
        int m = m(this.p, i3);
        this.p = m;
        if (this.o == 0 && m == 0) {
            q(action);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void e() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void f() {
        this.p = 0;
        this.o = 0;
        this.k = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void g(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        int calculateDxToMakeVisible = calculateDxToMakeVisible(view, n());
        int calculateDyToMakeVisible = calculateDyToMakeVisible(view, p());
        int k = k((int) Math.sqrt((calculateDxToMakeVisible * calculateDxToMakeVisible) + (calculateDyToMakeVisible * calculateDyToMakeVisible)));
        if (k > 0) {
            action.update(-calculateDxToMakeVisible, -calculateDyToMakeVisible, k, this.j);
        }
    }

    protected float j(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int k(int i2) {
        double l = l(i2);
        Double.isNaN(l);
        return (int) Math.ceil(l / 0.3356d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int l(int i2) {
        return (int) Math.ceil(Math.abs(i2) * o());
    }

    protected int n() {
        PointF pointF = this.k;
        if (pointF != null) {
            float f = pointF.x;
            if (f != 0.0f) {
                return f > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }

    protected int p() {
        PointF pointF = this.k;
        if (pointF != null) {
            float f = pointF.y;
            if (f != 0.0f) {
                return f > 0.0f ? 1 : -1;
            }
        }
        return 0;
    }

    protected void q(RecyclerView.SmoothScroller.Action action) {
        PointF computeScrollVectorForPosition = computeScrollVectorForPosition(getTargetPosition());
        if (computeScrollVectorForPosition == null || (computeScrollVectorForPosition.x == 0.0f && computeScrollVectorForPosition.y == 0.0f)) {
            action.jumpTo(getTargetPosition());
            i();
            return;
        }
        a(computeScrollVectorForPosition);
        this.k = computeScrollVectorForPosition;
        this.o = (int) (computeScrollVectorForPosition.x * 10000.0f);
        this.p = (int) (computeScrollVectorForPosition.y * 10000.0f);
        action.update((int) (this.o * 1.2f), (int) (this.p * 1.2f), (int) (l(10000) * 1.2f), this.f950i);
    }
}
