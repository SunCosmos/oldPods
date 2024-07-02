package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int START = 16;
    public static final int UP = 1;
    private f A;
    private Rect C;
    private long D;

    /* renamed from: d, reason: collision with root package name */
    float f936d;
    float e;
    private float f;
    private float g;
    float h;

    /* renamed from: i, reason: collision with root package name */
    float f937i;
    private float j;
    private float k;

    @NonNull
    Callback m;
    int o;
    private int q;
    RecyclerView r;
    VelocityTracker t;
    private List<RecyclerView.ViewHolder> u;
    private List<Integer> v;
    GestureDetectorCompat z;
    final List<View> a = new ArrayList();
    private final float[] b = new float[2];

    /* renamed from: c, reason: collision with root package name */
    RecyclerView.ViewHolder f935c = null;
    int l = -1;
    private int n = 0;
    List<g> p = new ArrayList();
    final Runnable s = new a();
    private RecyclerView.ChildDrawingOrderCallback w = null;
    View x = null;
    int y = -1;
    private final RecyclerView.OnItemTouchListener B = new b();

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final Interpolator b = new a();

        /* renamed from: c, reason: collision with root package name */
        private static final Interpolator f938c = new b();
        private int a = -1;

        /* loaded from: classes.dex */
        static class a implements Interpolator {
            a() {
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return f * f * f * f * f;
            }
        }

        /* loaded from: classes.dex */
        static class b implements Interpolator {
            b() {
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        }

        private int b(RecyclerView recyclerView) {
            if (this.a == -1) {
                this.a = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.a;
        }

        public static int convertToRelativeDirection(int i2, int i3) {
            int i4;
            int i5 = i2 & 789516;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (i5 ^ (-1));
            if (i3 == 0) {
                i4 = i5 << 2;
            } else {
                int i7 = i5 << 1;
                i6 |= (-789517) & i7;
                i4 = (i7 & 789516) << 2;
            }
            return i6 | i4;
        }

        @NonNull
        public static ItemTouchUIUtil getDefaultUIUtil() {
            return androidx.recyclerview.widget.e.a;
        }

        public static int makeFlag(int i2, int i3) {
            return i3 << (i2 * 8);
        }

        public static int makeMovementFlags(int i2, int i3) {
            return makeFlag(2, i2) | makeFlag(1, i3) | makeFlag(0, i3 | i2);
        }

        final int a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection(recyclerView));
        }

        boolean c(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (a(recyclerView, viewHolder) & 16711680) != 0;
        }

        public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
            return true;
        }

        public RecyclerView.ViewHolder chooseDropTarget(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<RecyclerView.ViewHolder> list, int i2, int i3) {
            int bottom;
            int abs;
            int top;
            int abs2;
            int left;
            int abs3;
            int right;
            int abs4;
            int width = i2 + viewHolder.itemView.getWidth();
            int height = i3 + viewHolder.itemView.getHeight();
            int left2 = i2 - viewHolder.itemView.getLeft();
            int top2 = i3 - viewHolder.itemView.getTop();
            int size = list.size();
            RecyclerView.ViewHolder viewHolder2 = null;
            int i4 = -1;
            for (int i5 = 0; i5 < size; i5++) {
                RecyclerView.ViewHolder viewHolder3 = list.get(i5);
                if (left2 > 0 && (right = viewHolder3.itemView.getRight() - width) < 0 && viewHolder3.itemView.getRight() > viewHolder.itemView.getRight() && (abs4 = Math.abs(right)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs4;
                }
                if (left2 < 0 && (left = viewHolder3.itemView.getLeft() - i2) > 0 && viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft() && (abs3 = Math.abs(left)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs3;
                }
                if (top2 < 0 && (top = viewHolder3.itemView.getTop() - i3) > 0 && viewHolder3.itemView.getTop() < viewHolder.itemView.getTop() && (abs2 = Math.abs(top)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs2;
                }
                if (top2 > 0 && (bottom = viewHolder3.itemView.getBottom() - height) < 0 && viewHolder3.itemView.getBottom() > viewHolder.itemView.getBottom() && (abs = Math.abs(bottom)) > i4) {
                    viewHolder2 = viewHolder3;
                    i4 = abs;
                }
            }
            return viewHolder2;
        }

        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            androidx.recyclerview.widget.e.a.clearView(viewHolder.itemView);
        }

        public int convertToAbsoluteDirection(int i2, int i3) {
            int i4;
            int i5 = i2 & 3158064;
            if (i5 == 0) {
                return i2;
            }
            int i6 = i2 & (i5 ^ (-1));
            if (i3 == 0) {
                i4 = i5 >> 2;
            } else {
                int i7 = i5 >> 1;
                i6 |= (-3158065) & i7;
                i4 = (i7 & 3158064) >> 2;
            }
            return i6 | i4;
        }

        boolean d(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return (a(recyclerView, viewHolder) & 65280) != 0;
        }

        void e(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<g> list, int i2, float f, float f2) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                g gVar = list.get(i3);
                gVar.e();
                int save = canvas.save();
                onChildDraw(canvas, recyclerView, gVar.e, gVar.f943i, gVar.j, gVar.f, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDraw(canvas, recyclerView, viewHolder, f, f2, i2, true);
                canvas.restoreToCount(save2);
            }
        }

        void f(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<g> list, int i2, float f, float f2) {
            int size = list.size();
            boolean z = false;
            for (int i3 = 0; i3 < size; i3++) {
                g gVar = list.get(i3);
                int save = canvas.save();
                onChildDrawOver(canvas, recyclerView, gVar.e, gVar.f943i, gVar.j, gVar.f, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, viewHolder, f, f2, i2, true);
                canvas.restoreToCount(save2);
            }
            for (int i4 = size - 1; i4 >= 0; i4--) {
                g gVar2 = list.get(i4);
                boolean z2 = gVar2.l;
                if (z2 && !gVar2.h) {
                    list.remove(i4);
                } else if (!z2) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }

        public long getAnimationDuration(@NonNull RecyclerView recyclerView, int i2, float f, float f2) {
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            return itemAnimator == null ? i2 == 8 ? 200L : 250L : i2 == 8 ? itemAnimator.getMoveDuration() : itemAnimator.getRemoveDuration();
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public abstract int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder);

        public float getSwipeEscapeVelocity(float f) {
            return f;
        }

        public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public float getSwipeVelocityThreshold(float f) {
            return f;
        }

        public int interpolateOutOfBoundsScroll(@NonNull RecyclerView recyclerView, int i2, int i3, int i4, long j) {
            int signum = (int) (((int) (((int) Math.signum(i3)) * b(recyclerView) * f938c.getInterpolation(Math.min(1.0f, (Math.abs(i3) * 1.0f) / i2)))) * b.getInterpolation(j <= 2000 ? ((float) j) / 2000.0f : 1.0f));
            return signum == 0 ? i3 > 0 ? 1 : -1 : signum;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float f, float f2, int i2, boolean z) {
            androidx.recyclerview.widget.e.a.onDraw(canvas, recyclerView, viewHolder.itemView, f, f2, i2, z);
        }

        public void onChildDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i2, boolean z) {
            androidx.recyclerview.widget.e.a.onDrawOver(canvas, recyclerView, viewHolder.itemView, f, f2, i2, z);
        }

        public abstract boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2);

        /* JADX WARN: Multi-variable type inference failed */
        public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int i2, @NonNull RecyclerView.ViewHolder viewHolder2, int i3, int i4, int i5) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler) layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, i4, i5);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i3);
                }
                if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i3);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i3);
                }
                if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i3);
                }
            }
        }

        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i2) {
            if (viewHolder != null) {
                androidx.recyclerview.widget.e.a.onSelected(viewHolder.itemView);
            }
        }

        public abstract void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i2);
    }

    /* loaded from: classes.dex */
    public static abstract class SimpleCallback extends Callback {

        /* renamed from: d, reason: collision with root package name */
        private int f939d;
        private int e;

        public SimpleCallback(int i2, int i3) {
            this.f939d = i3;
            this.e = i2;
        }

        public int getDragDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return this.e;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return Callback.makeMovementFlags(getDragDirs(recyclerView, viewHolder), getSwipeDirs(recyclerView, viewHolder));
        }

        public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return this.f939d;
        }

        public void setDefaultDragDirs(int i2) {
            this.e = i2;
        }

        public void setDefaultSwipeDirs(int i2) {
            this.f939d = i2;
        }
    }

    /* loaded from: classes.dex */
    public interface ViewDropHandler {
        void prepareForDrop(@NonNull View view, @NonNull View view2, int i2, int i3);
    }

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.f935c == null || !itemTouchHelper.s()) {
                return;
            }
            ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
            RecyclerView.ViewHolder viewHolder = itemTouchHelper2.f935c;
            if (viewHolder != null) {
                itemTouchHelper2.n(viewHolder);
            }
            ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
            itemTouchHelper3.r.removeCallbacks(itemTouchHelper3.s);
            ViewCompat.postOnAnimation(ItemTouchHelper.this.r, this);
        }
    }

    /* loaded from: classes.dex */
    class b implements RecyclerView.OnItemTouchListener {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            int findPointerIndex;
            g g;
            ItemTouchHelper.this.z.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                ItemTouchHelper.this.l = motionEvent.getPointerId(0);
                ItemTouchHelper.this.f936d = motionEvent.getX();
                ItemTouchHelper.this.e = motionEvent.getY();
                ItemTouchHelper.this.o();
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                if (itemTouchHelper.f935c == null && (g = itemTouchHelper.g(motionEvent)) != null) {
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.f936d -= g.f943i;
                    itemTouchHelper2.e -= g.j;
                    itemTouchHelper2.f(g.e, true);
                    if (ItemTouchHelper.this.a.remove(g.e.itemView)) {
                        ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                        itemTouchHelper3.m.clearView(itemTouchHelper3.r, g.e);
                    }
                    ItemTouchHelper.this.t(g.e, g.f);
                    ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                    itemTouchHelper4.y(motionEvent, itemTouchHelper4.o, 0);
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                ItemTouchHelper itemTouchHelper5 = ItemTouchHelper.this;
                itemTouchHelper5.l = -1;
                itemTouchHelper5.t(null, 0);
            } else {
                int i2 = ItemTouchHelper.this.l;
                if (i2 != -1 && (findPointerIndex = motionEvent.findPointerIndex(i2)) >= 0) {
                    ItemTouchHelper.this.c(actionMasked, motionEvent, findPointerIndex);
                }
            }
            VelocityTracker velocityTracker = ItemTouchHelper.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            return ItemTouchHelper.this.f935c != null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            if (z) {
                ItemTouchHelper.this.t(null, 0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            ItemTouchHelper.this.z.onTouchEvent(motionEvent);
            VelocityTracker velocityTracker = ItemTouchHelper.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (ItemTouchHelper.this.l == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int findPointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.l);
            if (findPointerIndex >= 0) {
                ItemTouchHelper.this.c(actionMasked, motionEvent, findPointerIndex);
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            RecyclerView.ViewHolder viewHolder = itemTouchHelper.f935c;
            if (viewHolder == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (findPointerIndex >= 0) {
                        itemTouchHelper.y(motionEvent, itemTouchHelper.o, findPointerIndex);
                        ItemTouchHelper.this.n(viewHolder);
                        ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                        itemTouchHelper2.r.removeCallbacks(itemTouchHelper2.s);
                        ItemTouchHelper.this.s.run();
                        ItemTouchHelper.this.r.invalidate();
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    int pointerId = motionEvent.getPointerId(actionIndex);
                    ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                    if (pointerId == itemTouchHelper3.l) {
                        itemTouchHelper3.l = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                        itemTouchHelper4.y(motionEvent, itemTouchHelper4.o, actionIndex);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = itemTouchHelper.t;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            ItemTouchHelper.this.t(null, 0);
            ItemTouchHelper.this.l = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends g {
        final /* synthetic */ int n;
        final /* synthetic */ RecyclerView.ViewHolder o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(RecyclerView.ViewHolder viewHolder, int i2, int i3, float f, float f2, float f3, float f4, int i4, RecyclerView.ViewHolder viewHolder2) {
            super(viewHolder, i2, i3, f, f2, f3, f4);
            this.n = i4;
            this.o = viewHolder2;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.g, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (this.k) {
                return;
            }
            if (this.n <= 0) {
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                itemTouchHelper.m.clearView(itemTouchHelper.r, this.o);
            } else {
                ItemTouchHelper.this.a.add(this.o.itemView);
                this.h = true;
                int i2 = this.n;
                if (i2 > 0) {
                    ItemTouchHelper.this.p(this, i2);
                }
            }
            ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
            View view = itemTouchHelper2.x;
            View view2 = this.o.itemView;
            if (view == view2) {
                itemTouchHelper2.r(view2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements Runnable {
        final /* synthetic */ g a;
        final /* synthetic */ int b;

        d(g gVar, int i2) {
            this.a = gVar;
            this.b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = ItemTouchHelper.this.r;
            if (recyclerView == null || !recyclerView.isAttachedToWindow()) {
                return;
            }
            g gVar = this.a;
            if (gVar.k || gVar.e.getAdapterPosition() == -1) {
                return;
            }
            RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.r.getItemAnimator();
            if ((itemAnimator == null || !itemAnimator.isRunning(null)) && !ItemTouchHelper.this.l()) {
                ItemTouchHelper.this.m.onSwiped(this.a.e, this.b);
            } else {
                ItemTouchHelper.this.r.post(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements RecyclerView.ChildDrawingOrderCallback {
        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ChildDrawingOrderCallback
        public int onGetChildDrawingOrder(int i2, int i3) {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            View view = itemTouchHelper.x;
            if (view == null) {
                return i3;
            }
            int i4 = itemTouchHelper.y;
            if (i4 == -1) {
                i4 = itemTouchHelper.r.indexOfChild(view);
                ItemTouchHelper.this.y = i4;
            }
            return i3 == i2 + (-1) ? i4 : i3 < i4 ? i3 : i3 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class f extends GestureDetector.SimpleOnGestureListener {
        private boolean a = true;

        f() {
        }

        void a() {
            this.a = false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View h;
            RecyclerView.ViewHolder childViewHolder;
            if (!this.a || (h = ItemTouchHelper.this.h(motionEvent)) == null || (childViewHolder = ItemTouchHelper.this.r.getChildViewHolder(h)) == null) {
                return;
            }
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.m.c(itemTouchHelper.r, childViewHolder)) {
                int pointerId = motionEvent.getPointerId(0);
                int i2 = ItemTouchHelper.this.l;
                if (pointerId == i2) {
                    int findPointerIndex = motionEvent.findPointerIndex(i2);
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.f936d = x;
                    itemTouchHelper2.e = y;
                    itemTouchHelper2.f937i = 0.0f;
                    itemTouchHelper2.h = 0.0f;
                    if (itemTouchHelper2.m.isLongPressDragEnabled()) {
                        ItemTouchHelper.this.t(childViewHolder, 2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class g implements Animator.AnimatorListener {
        final float a;
        final float b;

        /* renamed from: c, reason: collision with root package name */
        final float f941c;

        /* renamed from: d, reason: collision with root package name */
        final float f942d;
        final RecyclerView.ViewHolder e;
        final int f;
        private final ValueAnimator g;
        boolean h;

        /* renamed from: i, reason: collision with root package name */
        float f943i;
        float j;
        boolean k = false;
        boolean l = false;
        private float m;

        /* loaded from: classes.dex */
        class a implements ValueAnimator.AnimatorUpdateListener {
            a() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                g.this.c(valueAnimator.getAnimatedFraction());
            }
        }

        g(RecyclerView.ViewHolder viewHolder, int i2, int i3, float f, float f2, float f3, float f4) {
            this.f = i3;
            this.e = viewHolder;
            this.a = f;
            this.b = f2;
            this.f941c = f3;
            this.f942d = f4;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.g = ofFloat;
            ofFloat.addUpdateListener(new a());
            ofFloat.setTarget(viewHolder.itemView);
            ofFloat.addListener(this);
            c(0.0f);
        }

        public void a() {
            this.g.cancel();
        }

        public void b(long j) {
            this.g.setDuration(j);
        }

        public void c(float f) {
            this.m = f;
        }

        public void d() {
            this.e.setIsRecyclable(false);
            this.g.start();
        }

        public void e() {
            float f = this.a;
            float f2 = this.f941c;
            this.f943i = f == f2 ? this.e.itemView.getTranslationX() : f + (this.m * (f2 - f));
            float f3 = this.b;
            float f4 = this.f942d;
            this.j = f3 == f4 ? this.e.itemView.getTranslationY() : f3 + (this.m * (f4 - f3));
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            c(1.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.l) {
                this.e.setIsRecyclable(true);
            }
            this.l = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    public ItemTouchHelper(@NonNull Callback callback) {
        this.m = callback;
    }

    private void a() {
        if (Build.VERSION.SDK_INT >= 21) {
            return;
        }
        if (this.w == null) {
            this.w = new e();
        }
        this.r.setChildDrawingOrderCallback(this.w);
    }

    private int b(RecyclerView.ViewHolder viewHolder, int i2) {
        if ((i2 & 12) == 0) {
            return 0;
        }
        int i3 = this.h > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.m.getSwipeVelocityThreshold(this.g));
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i4 = xVelocity <= 0.0f ? 4 : 8;
            float abs = Math.abs(xVelocity);
            if ((i4 & i2) != 0 && i3 == i4 && abs >= this.m.getSwipeEscapeVelocity(this.f) && abs > Math.abs(yVelocity)) {
                return i4;
            }
        }
        float width = this.r.getWidth() * this.m.getSwipeThreshold(viewHolder);
        if ((i2 & i3) == 0 || Math.abs(this.h) <= width) {
            return 0;
        }
        return i3;
    }

    private int d(RecyclerView.ViewHolder viewHolder, int i2) {
        if ((i2 & 3) == 0) {
            return 0;
        }
        int i3 = this.f937i > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.m.getSwipeVelocityThreshold(this.g));
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i4 = yVelocity <= 0.0f ? 1 : 2;
            float abs = Math.abs(yVelocity);
            if ((i4 & i2) != 0 && i4 == i3 && abs >= this.m.getSwipeEscapeVelocity(this.f) && abs > Math.abs(xVelocity)) {
                return i4;
            }
        }
        float height = this.r.getHeight() * this.m.getSwipeThreshold(viewHolder);
        if ((i2 & i3) == 0 || Math.abs(this.f937i) <= height) {
            return 0;
        }
        return i3;
    }

    private void e() {
        this.r.removeItemDecoration(this);
        this.r.removeOnItemTouchListener(this.B);
        this.r.removeOnChildAttachStateChangeListener(this);
        for (int size = this.p.size() - 1; size >= 0; size--) {
            this.m.clearView(this.r, this.p.get(0).e);
        }
        this.p.clear();
        this.x = null;
        this.y = -1;
        q();
        w();
    }

    private List<RecyclerView.ViewHolder> i(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        List<RecyclerView.ViewHolder> list = this.u;
        if (list == null) {
            this.u = new ArrayList();
            this.v = new ArrayList();
        } else {
            list.clear();
            this.v.clear();
        }
        int boundingBoxMargin = this.m.getBoundingBoxMargin();
        int round = Math.round(this.j + this.h) - boundingBoxMargin;
        int round2 = Math.round(this.k + this.f937i) - boundingBoxMargin;
        int i2 = boundingBoxMargin * 2;
        int width = viewHolder2.itemView.getWidth() + round + i2;
        int height = viewHolder2.itemView.getHeight() + round2 + i2;
        int i3 = (round + width) / 2;
        int i4 = (round2 + height) / 2;
        RecyclerView.LayoutManager layoutManager = this.r.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        int i5 = 0;
        while (i5 < childCount) {
            View childAt = layoutManager.getChildAt(i5);
            if (childAt != viewHolder2.itemView && childAt.getBottom() >= round2 && childAt.getTop() <= height && childAt.getRight() >= round && childAt.getLeft() <= width) {
                RecyclerView.ViewHolder childViewHolder = this.r.getChildViewHolder(childAt);
                if (this.m.canDropOver(this.r, this.f935c, childViewHolder)) {
                    int abs = Math.abs(i3 - ((childAt.getLeft() + childAt.getRight()) / 2));
                    int abs2 = Math.abs(i4 - ((childAt.getTop() + childAt.getBottom()) / 2));
                    int i6 = (abs * abs) + (abs2 * abs2);
                    int size = this.u.size();
                    int i7 = 0;
                    for (int i8 = 0; i8 < size && i6 > this.v.get(i8).intValue(); i8++) {
                        i7++;
                    }
                    this.u.add(i7, childViewHolder);
                    this.v.add(i7, Integer.valueOf(i6));
                }
            }
            i5++;
            viewHolder2 = viewHolder;
        }
        return this.u;
    }

    private RecyclerView.ViewHolder j(MotionEvent motionEvent) {
        View h;
        RecyclerView.LayoutManager layoutManager = this.r.getLayoutManager();
        int i2 = this.l;
        if (i2 == -1) {
            return null;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        float x = motionEvent.getX(findPointerIndex) - this.f936d;
        float y = motionEvent.getY(findPointerIndex) - this.e;
        float abs = Math.abs(x);
        float abs2 = Math.abs(y);
        int i3 = this.q;
        if (abs < i3 && abs2 < i3) {
            return null;
        }
        if (abs > abs2 && layoutManager.canScrollHorizontally()) {
            return null;
        }
        if ((abs2 <= abs || !layoutManager.canScrollVertically()) && (h = h(motionEvent)) != null) {
            return this.r.getChildViewHolder(h);
        }
        return null;
    }

    private void k(float[] fArr) {
        if ((this.o & 12) != 0) {
            fArr[0] = (this.j + this.h) - this.f935c.itemView.getLeft();
        } else {
            fArr[0] = this.f935c.itemView.getTranslationX();
        }
        if ((this.o & 3) != 0) {
            fArr[1] = (this.k + this.f937i) - this.f935c.itemView.getTop();
        } else {
            fArr[1] = this.f935c.itemView.getTranslationY();
        }
    }

    private static boolean m(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    private void q() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.t = null;
        }
    }

    private void u() {
        this.q = ViewConfiguration.get(this.r.getContext()).getScaledTouchSlop();
        this.r.addItemDecoration(this);
        this.r.addOnItemTouchListener(this.B);
        this.r.addOnChildAttachStateChangeListener(this);
        v();
    }

    private void v() {
        this.A = new f();
        this.z = new GestureDetectorCompat(this.r.getContext(), this.A);
    }

    private void w() {
        f fVar = this.A;
        if (fVar != null) {
            fVar.a();
            this.A = null;
        }
        if (this.z != null) {
            this.z = null;
        }
    }

    private int x(RecyclerView.ViewHolder viewHolder) {
        if (this.n == 2) {
            return 0;
        }
        int movementFlags = this.m.getMovementFlags(this.r, viewHolder);
        int convertToAbsoluteDirection = (this.m.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection(this.r)) & 65280) >> 8;
        if (convertToAbsoluteDirection == 0) {
            return 0;
        }
        int i2 = (movementFlags & 65280) >> 8;
        if (Math.abs(this.h) > Math.abs(this.f937i)) {
            int b2 = b(viewHolder, convertToAbsoluteDirection);
            if (b2 > 0) {
                return (i2 & b2) == 0 ? Callback.convertToRelativeDirection(b2, ViewCompat.getLayoutDirection(this.r)) : b2;
            }
            int d2 = d(viewHolder, convertToAbsoluteDirection);
            if (d2 > 0) {
                return d2;
            }
        } else {
            int d3 = d(viewHolder, convertToAbsoluteDirection);
            if (d3 > 0) {
                return d3;
            }
            int b3 = b(viewHolder, convertToAbsoluteDirection);
            if (b3 > 0) {
                return (i2 & b3) == 0 ? Callback.convertToRelativeDirection(b3, ViewCompat.getLayoutDirection(this.r)) : b3;
            }
        }
        return 0;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.r;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            e();
        }
        this.r = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.f = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
            this.g = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
            u();
        }
    }

    void c(int i2, MotionEvent motionEvent, int i3) {
        RecyclerView.ViewHolder j;
        int a2;
        if (this.f935c != null || i2 != 2 || this.n == 2 || !this.m.isItemViewSwipeEnabled() || this.r.getScrollState() == 1 || (j = j(motionEvent)) == null || (a2 = (this.m.a(this.r, j) & 65280) >> 8) == 0) {
            return;
        }
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.f936d;
        float f3 = y - this.e;
        float abs = Math.abs(f2);
        float abs2 = Math.abs(f3);
        int i4 = this.q;
        if (abs >= i4 || abs2 >= i4) {
            if (abs > abs2) {
                if (f2 < 0.0f && (a2 & 4) == 0) {
                    return;
                }
                if (f2 > 0.0f && (a2 & 8) == 0) {
                    return;
                }
            } else {
                if (f3 < 0.0f && (a2 & 1) == 0) {
                    return;
                }
                if (f3 > 0.0f && (a2 & 2) == 0) {
                    return;
                }
            }
            this.f937i = 0.0f;
            this.h = 0.0f;
            this.l = motionEvent.getPointerId(0);
            t(j, 1);
        }
    }

    void f(RecyclerView.ViewHolder viewHolder, boolean z) {
        for (int size = this.p.size() - 1; size >= 0; size--) {
            g gVar = this.p.get(size);
            if (gVar.e == viewHolder) {
                gVar.k |= z;
                if (!gVar.l) {
                    gVar.a();
                }
                this.p.remove(size);
                return;
            }
        }
    }

    g g(MotionEvent motionEvent) {
        if (this.p.isEmpty()) {
            return null;
        }
        View h = h(motionEvent);
        for (int size = this.p.size() - 1; size >= 0; size--) {
            g gVar = this.p.get(size);
            if (gVar.e.itemView == h) {
                return gVar;
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.setEmpty();
    }

    View h(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.ViewHolder viewHolder = this.f935c;
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            if (m(view, x, y, this.j + this.h, this.k + this.f937i)) {
                return view;
            }
        }
        for (int size = this.p.size() - 1; size >= 0; size--) {
            g gVar = this.p.get(size);
            View view2 = gVar.e.itemView;
            if (m(view2, x, y, gVar.f943i, gVar.j)) {
                return view2;
            }
        }
        return this.r.findChildViewUnder(x, y);
    }

    boolean l() {
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.p.get(i2).l) {
                return true;
            }
        }
        return false;
    }

    void n(RecyclerView.ViewHolder viewHolder) {
        if (!this.r.isLayoutRequested() && this.n == 2) {
            float moveThreshold = this.m.getMoveThreshold(viewHolder);
            int i2 = (int) (this.j + this.h);
            int i3 = (int) (this.k + this.f937i);
            if (Math.abs(i3 - viewHolder.itemView.getTop()) >= viewHolder.itemView.getHeight() * moveThreshold || Math.abs(i2 - viewHolder.itemView.getLeft()) >= viewHolder.itemView.getWidth() * moveThreshold) {
                List<RecyclerView.ViewHolder> i4 = i(viewHolder);
                if (i4.size() == 0) {
                    return;
                }
                RecyclerView.ViewHolder chooseDropTarget = this.m.chooseDropTarget(viewHolder, i4, i2, i3);
                if (chooseDropTarget == null) {
                    this.u.clear();
                    this.v.clear();
                    return;
                }
                int adapterPosition = chooseDropTarget.getAdapterPosition();
                int adapterPosition2 = viewHolder.getAdapterPosition();
                if (this.m.onMove(this.r, viewHolder, chooseDropTarget)) {
                    this.m.onMoved(this.r, viewHolder, adapterPosition2, chooseDropTarget, adapterPosition, i2, i3);
                }
            }
        }
    }

    void o() {
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.t = VelocityTracker.obtain();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewAttachedToWindow(@NonNull View view) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        r(view);
        RecyclerView.ViewHolder childViewHolder = this.r.getChildViewHolder(view);
        if (childViewHolder == null) {
            return;
        }
        RecyclerView.ViewHolder viewHolder = this.f935c;
        if (viewHolder != null && childViewHolder == viewHolder) {
            t(null, 0);
            return;
        }
        f(childViewHolder, false);
        if (this.a.remove(childViewHolder.itemView)) {
            this.m.clearView(this.r, childViewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f2;
        float f3;
        this.y = -1;
        if (this.f935c != null) {
            k(this.b);
            float[] fArr = this.b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.m.e(canvas, recyclerView, this.f935c, this.p, this.n, f2, f3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f2;
        float f3;
        if (this.f935c != null) {
            k(this.b);
            float[] fArr = this.b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        this.m.f(canvas, recyclerView, this.f935c, this.p, this.n, f2, f3);
    }

    void p(g gVar, int i2) {
        this.r.post(new d(gVar, i2));
    }

    void r(View view) {
        if (view == this.x) {
            this.x = null;
            if (this.w != null) {
                this.r.setChildDrawingOrderCallback(null);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c1, code lost:
    
        if (r1 > 0) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0100 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean s() {
        /*
            Method dump skipped, instructions count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.ItemTouchHelper.s():boolean");
    }

    public void startDrag(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (!this.m.c(this.r, viewHolder)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            return;
        }
        if (viewHolder.itemView.getParent() != this.r) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        }
        o();
        this.f937i = 0.0f;
        this.h = 0.0f;
        t(viewHolder, 2);
    }

    public void startSwipe(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (!this.m.d(this.r, viewHolder)) {
            Log.e("ItemTouchHelper", "Start swipe has been called but swiping is not enabled");
            return;
        }
        if (viewHolder.itemView.getParent() != this.r) {
            Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
            return;
        }
        o();
        this.f937i = 0.0f;
        this.h = 0.0f;
        t(viewHolder, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void t(@androidx.annotation.Nullable androidx.recyclerview.widget.RecyclerView.ViewHolder r24, int r25) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.ItemTouchHelper.t(androidx.recyclerview.widget.RecyclerView$ViewHolder, int):void");
    }

    void y(MotionEvent motionEvent, int i2, int i3) {
        float x = motionEvent.getX(i3);
        float y = motionEvent.getY(i3);
        float f2 = x - this.f936d;
        this.h = f2;
        this.f937i = y - this.e;
        if ((i2 & 4) == 0) {
            this.h = Math.max(0.0f, f2);
        }
        if ((i2 & 8) == 0) {
            this.h = Math.min(0.0f, this.h);
        }
        if ((i2 & 1) == 0) {
            this.f937i = Math.max(0.0f, this.f937i);
        }
        if ((i2 & 2) == 0) {
            this.f937i = Math.min(0.0f, this.f937i);
        }
    }
}
