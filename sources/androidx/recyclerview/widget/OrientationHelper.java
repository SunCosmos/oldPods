package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class OrientationHelper {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    protected final RecyclerView.LayoutManager a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    final Rect f953c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends OrientationHelper {
        a(RecyclerView.LayoutManager layoutManager) {
            super(layoutManager, null);
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedEnd(View view) {
            return this.a.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurement(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.a.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurementInOther(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.a.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedStart(View view) {
            return this.a.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEnd() {
            return this.a.getWidth();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndAfterPadding() {
            return this.a.getWidth() - this.a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndPadding() {
            return this.a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getMode() {
            return this.a.getWidthMode();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getModeInOther() {
            return this.a.getHeightMode();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getStartAfterPadding() {
            return this.a.getPaddingLeft();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTotalSpace() {
            return (this.a.getWidth() - this.a.getPaddingLeft()) - this.a.getPaddingRight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedEndWithDecoration(View view) {
            this.a.getTransformedBoundingBox(view, true, this.f953c);
            return this.f953c.right;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedStartWithDecoration(View view) {
            this.a.getTransformedBoundingBox(view, true, this.f953c);
            return this.f953c.left;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public void offsetChild(View view, int i2) {
            view.offsetLeftAndRight(i2);
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public void offsetChildren(int i2) {
            this.a.offsetChildrenHorizontal(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends OrientationHelper {
        b(RecyclerView.LayoutManager layoutManager) {
            super(layoutManager, null);
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedEnd(View view) {
            return this.a.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurement(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.a.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedMeasurementInOther(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return this.a.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getDecoratedStart(View view) {
            return this.a.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEnd() {
            return this.a.getHeight();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndAfterPadding() {
            return this.a.getHeight() - this.a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getEndPadding() {
            return this.a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getMode() {
            return this.a.getHeightMode();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getModeInOther() {
            return this.a.getWidthMode();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getStartAfterPadding() {
            return this.a.getPaddingTop();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTotalSpace() {
            return (this.a.getHeight() - this.a.getPaddingTop()) - this.a.getPaddingBottom();
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedEndWithDecoration(View view) {
            this.a.getTransformedBoundingBox(view, true, this.f953c);
            return this.f953c.bottom;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public int getTransformedStartWithDecoration(View view) {
            this.a.getTransformedBoundingBox(view, true, this.f953c);
            return this.f953c.top;
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public void offsetChild(View view, int i2) {
            view.offsetTopAndBottom(i2);
        }

        @Override // androidx.recyclerview.widget.OrientationHelper
        public void offsetChildren(int i2) {
            this.a.offsetChildrenVertical(i2);
        }
    }

    private OrientationHelper(RecyclerView.LayoutManager layoutManager) {
        this.b = Integer.MIN_VALUE;
        this.f953c = new Rect();
        this.a = layoutManager;
    }

    /* synthetic */ OrientationHelper(RecyclerView.LayoutManager layoutManager, a aVar) {
        this(layoutManager);
    }

    public static OrientationHelper createHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        return new a(layoutManager);
    }

    public static OrientationHelper createOrientationHelper(RecyclerView.LayoutManager layoutManager, int i2) {
        if (i2 == 0) {
            return createHorizontalHelper(layoutManager);
        }
        if (i2 == 1) {
            return createVerticalHelper(layoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static OrientationHelper createVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        return new b(layoutManager);
    }

    public abstract int getDecoratedEnd(View view);

    public abstract int getDecoratedMeasurement(View view);

    public abstract int getDecoratedMeasurementInOther(View view);

    public abstract int getDecoratedStart(View view);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public RecyclerView.LayoutManager getLayoutManager() {
        return this.a;
    }

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public int getTotalSpaceChange() {
        if (Integer.MIN_VALUE == this.b) {
            return 0;
        }
        return getTotalSpace() - this.b;
    }

    public abstract int getTransformedEndWithDecoration(View view);

    public abstract int getTransformedStartWithDecoration(View view);

    public abstract void offsetChild(View view, int i2);

    public abstract void offsetChildren(int i2);

    public void onLayoutComplete() {
        this.b = getTotalSpace();
    }
}
