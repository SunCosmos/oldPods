package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ViewDragHelper {
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final Interpolator w = new a();
    private int a;
    private int b;

    /* renamed from: d, reason: collision with root package name */
    private float[] f680d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;

    /* renamed from: i, reason: collision with root package name */
    private int[] f681i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private OverScroller q;
    private final Callback r;
    private View s;
    private boolean t;
    private final ViewGroup u;

    /* renamed from: c, reason: collision with root package name */
    private int f679c = -1;
    private final Runnable v = new b();

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public int clampViewPositionHorizontal(@NonNull View view, int i2, int i3) {
            return 0;
        }

        public int clampViewPositionVertical(@NonNull View view, int i2, int i3) {
            return 0;
        }

        public int getOrderedChildIndex(int i2) {
            return i2;
        }

        public int getViewHorizontalDragRange(@NonNull View view) {
            return 0;
        }

        public int getViewVerticalDragRange(@NonNull View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i2, int i3) {
        }

        public boolean onEdgeLock(int i2) {
            return false;
        }

        public void onEdgeTouched(int i2, int i3) {
        }

        public void onViewCaptured(@NonNull View view, int i2) {
        }

        public void onViewDragStateChanged(int i2) {
        }

        public void onViewPositionChanged(@NonNull View view, int i2, int i3, @Px int i4, @Px int i5) {
        }

        public void onViewReleased(@NonNull View view, float f, float f2) {
        }

        public abstract boolean tryCaptureView(@NonNull View view, int i2);
    }

    /* loaded from: classes.dex */
    static class a implements Interpolator {
        a() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper.this.t(0);
        }
    }

    private ViewDragHelper(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.u = viewGroup;
        this.r = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.b = viewConfiguration.getScaledTouchSlop();
        this.m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.q = new OverScroller(context, w);
    }

    private boolean a(float f, float f2, int i2, int i3) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.h[i2] & i3) != i3 || (this.p & i3) == 0 || (this.j[i2] & i3) == i3 || (this.f681i[i2] & i3) == i3) {
            return false;
        }
        int i4 = this.b;
        if (abs <= i4 && abs2 <= i4) {
            return false;
        }
        if (abs >= abs2 * 0.5f || !this.r.onEdgeLock(i3)) {
            return (this.f681i[i2] & i3) == 0 && abs > ((float) this.b);
        }
        int[] iArr = this.j;
        iArr[i2] = iArr[i2] | i3;
        return false;
    }

    private boolean b(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.r.getViewHorizontalDragRange(view) > 0;
        boolean z2 = this.r.getViewVerticalDragRange(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.b) : z2 && Math.abs(f2) > ((float) this.b);
        }
        float f3 = (f * f) + (f2 * f2);
        int i2 = this.b;
        return f3 > ((float) (i2 * i2));
    }

    private float c(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        return abs > f3 ? f > 0.0f ? f3 : -f3 : f;
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, float f, @NonNull Callback callback) {
        ViewDragHelper create = create(viewGroup, callback);
        create.b = (int) (create.b * (1.0f / f));
        return create;
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    private int d(int i2, int i3, int i4) {
        int abs = Math.abs(i2);
        if (abs < i3) {
            return 0;
        }
        return abs > i4 ? i2 > 0 ? i4 : -i4 : i2;
    }

    private void e() {
        float[] fArr = this.f680d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.e, 0.0f);
        Arrays.fill(this.f, 0.0f);
        Arrays.fill(this.g, 0.0f);
        Arrays.fill(this.h, 0);
        Arrays.fill(this.f681i, 0);
        Arrays.fill(this.j, 0);
        this.k = 0;
    }

    private void f(int i2) {
        if (this.f680d == null || !isPointerDown(i2)) {
            return;
        }
        this.f680d[i2] = 0.0f;
        this.e[i2] = 0.0f;
        this.f[i2] = 0.0f;
        this.g[i2] = 0.0f;
        this.h[i2] = 0;
        this.f681i[i2] = 0;
        this.j[i2] = 0;
        this.k = ((1 << i2) ^ (-1)) & this.k;
    }

    private int g(int i2, int i3, int i4) {
        if (i2 == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        float f = width / 2;
        float j = f + (j(Math.min(1.0f, Math.abs(i2) / width)) * f);
        int abs = Math.abs(i3);
        return Math.min(abs > 0 ? Math.round(Math.abs(j / abs) * 1000.0f) * 4 : (int) (((Math.abs(i2) / i4) + 1.0f) * 256.0f), 600);
    }

    private int h(View view, int i2, int i3, int i4, int i5) {
        float f;
        float f2;
        float f3;
        float f4;
        int d2 = d(i4, (int) this.n, (int) this.m);
        int d3 = d(i5, (int) this.n, (int) this.m);
        int abs = Math.abs(i2);
        int abs2 = Math.abs(i3);
        int abs3 = Math.abs(d2);
        int abs4 = Math.abs(d3);
        int i6 = abs3 + abs4;
        int i7 = abs + abs2;
        if (d2 != 0) {
            f = abs3;
            f2 = i6;
        } else {
            f = abs;
            f2 = i7;
        }
        float f5 = f / f2;
        if (d3 != 0) {
            f3 = abs4;
            f4 = i6;
        } else {
            f3 = abs2;
            f4 = i7;
        }
        return (int) ((g(i2, d2, this.r.getViewHorizontalDragRange(view)) * f5) + (g(i3, d3, this.r.getViewVerticalDragRange(view)) * (f3 / f4)));
    }

    private void i(float f, float f2) {
        this.t = true;
        this.r.onViewReleased(this.s, f, f2);
        this.t = false;
        if (this.a == 1) {
            t(0);
        }
    }

    private float j(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    private void k(int i2, int i3, int i4, int i5) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        if (i4 != 0) {
            i2 = this.r.clampViewPositionHorizontal(this.s, i2, i4);
            ViewCompat.offsetLeftAndRight(this.s, i2 - left);
        }
        int i6 = i2;
        if (i5 != 0) {
            i3 = this.r.clampViewPositionVertical(this.s, i3, i5);
            ViewCompat.offsetTopAndBottom(this.s, i3 - top);
        }
        int i7 = i3;
        if (i4 == 0 && i5 == 0) {
            return;
        }
        this.r.onViewPositionChanged(this.s, i6, i7, i6 - left, i7 - top);
    }

    private void l(int i2) {
        float[] fArr = this.f680d;
        if (fArr == null || fArr.length <= i2) {
            int i3 = i2 + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f681i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f680d = fArr2;
            this.e = fArr3;
            this.f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.f681i = iArr2;
            this.j = iArr3;
        }
    }

    private boolean m(int i2, int i3, int i4, int i5) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        int i6 = i2 - left;
        int i7 = i3 - top;
        if (i6 == 0 && i7 == 0) {
            this.q.abortAnimation();
            t(0);
            return false;
        }
        this.q.startScroll(left, top, i6, i7, h(this.s, i6, i7, i4, i5));
        t(2);
        return true;
    }

    private int n(int i2, int i3) {
        int i4 = i2 < this.u.getLeft() + this.o ? 1 : 0;
        if (i3 < this.u.getTop() + this.o) {
            i4 |= 4;
        }
        if (i2 > this.u.getRight() - this.o) {
            i4 |= 2;
        }
        return i3 > this.u.getBottom() - this.o ? i4 | 8 : i4;
    }

    private boolean o(int i2) {
        if (isPointerDown(i2)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i2 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    private void p() {
        this.l.computeCurrentVelocity(1000, this.m);
        i(c(this.l.getXVelocity(this.f679c), this.n, this.m), c(this.l.getYVelocity(this.f679c), this.n, this.m));
    }

    private void q(float f, float f2, int i2) {
        int i3 = a(f, f2, i2, 1) ? 1 : 0;
        if (a(f2, f, i2, 4)) {
            i3 |= 4;
        }
        if (a(f, f2, i2, 2)) {
            i3 |= 2;
        }
        if (a(f2, f, i2, 8)) {
            i3 |= 8;
        }
        if (i3 != 0) {
            int[] iArr = this.f681i;
            iArr[i2] = iArr[i2] | i3;
            this.r.onEdgeDragStarted(i3, i2);
        }
    }

    private void r(float f, float f2, int i2) {
        l(i2);
        float[] fArr = this.f680d;
        this.f[i2] = f;
        fArr[i2] = f;
        float[] fArr2 = this.e;
        this.g[i2] = f2;
        fArr2[i2] = f2;
        this.h[i2] = n((int) f, (int) f2);
        this.k |= 1 << i2;
    }

    private void s(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i2 = 0; i2 < pointerCount; i2++) {
            int pointerId = motionEvent.getPointerId(i2);
            if (o(pointerId)) {
                float x = motionEvent.getX(i2);
                float y = motionEvent.getY(i2);
                this.f[pointerId] = x;
                this.g[pointerId] = y;
            }
        }
    }

    public void abort() {
        cancel();
        if (this.a == 2) {
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            this.q.abortAnimation();
            int currX2 = this.q.getCurrX();
            int currY2 = this.q.getCurrY();
            this.r.onViewPositionChanged(this.s, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        t(0);
    }

    public void cancel() {
        this.f679c = -1;
        e();
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    public void captureChildView(@NonNull View view, int i2) {
        if (view.getParent() == this.u) {
            this.s = view;
            this.f679c = i2;
            this.r.onViewCaptured(view, i2);
            t(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
    }

    public boolean checkTouchSlop(int i2) {
        int length = this.f680d.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (checkTouchSlop(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTouchSlop(int i2, int i3) {
        if (!isPointerDown(i3)) {
            return false;
        }
        boolean z = (i2 & 1) == 1;
        boolean z2 = (i2 & 2) == 2;
        float f = this.f[i3] - this.f680d[i3];
        float f2 = this.g[i3] - this.e[i3];
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.b) : z2 && Math.abs(f2) > ((float) this.b);
        }
        float f3 = (f * f) + (f2 * f2);
        int i4 = this.b;
        return f3 > ((float) (i4 * i4));
    }

    public boolean continueSettling(boolean z) {
        if (this.a == 2) {
            boolean computeScrollOffset = this.q.computeScrollOffset();
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            int left = currX - this.s.getLeft();
            int top = currY - this.s.getTop();
            if (left != 0) {
                ViewCompat.offsetLeftAndRight(this.s, left);
            }
            if (top != 0) {
                ViewCompat.offsetTopAndBottom(this.s, top);
            }
            if (left != 0 || top != 0) {
                this.r.onViewPositionChanged(this.s, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.q.getFinalX() && currY == this.q.getFinalY()) {
                this.q.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.u.post(this.v);
                } else {
                    t(0);
                }
            }
        }
        return this.a == 2;
    }

    @Nullable
    public View findTopChildUnder(int i2, int i3) {
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.u.getChildAt(this.r.getOrderedChildIndex(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i3 >= childAt.getTop() && i3 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public void flingCapturedView(int i2, int i3, int i4, int i5) {
        if (!this.t) {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
        this.q.fling(this.s.getLeft(), this.s.getTop(), (int) this.l.getXVelocity(this.f679c), (int) this.l.getYVelocity(this.f679c), i2, i4, i3, i5);
        t(2);
    }

    public int getActivePointerId() {
        return this.f679c;
    }

    @Nullable
    public View getCapturedView() {
        return this.s;
    }

    @Px
    public int getEdgeSize() {
        return this.o;
    }

    public float getMinVelocity() {
        return this.n;
    }

    @Px
    public int getTouchSlop() {
        return this.b;
    }

    public int getViewDragState() {
        return this.a;
    }

    public boolean isCapturedViewUnder(int i2, int i3) {
        return isViewUnder(this.s, i2, i3);
    }

    public boolean isEdgeTouched(int i2) {
        int length = this.h.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (isEdgeTouched(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEdgeTouched(int i2, int i3) {
        return isPointerDown(i3) && (i2 & this.h[i3]) != 0;
    }

    public boolean isPointerDown(int i2) {
        return ((1 << i2) & this.k) != 0;
    }

    public boolean isViewUnder(@Nullable View view, int i2, int i3) {
        return view != null && i2 >= view.getLeft() && i2 < view.getRight() && i3 >= view.getTop() && i3 < view.getBottom();
    }

    public void processTouchEvent(@NonNull MotionEvent motionEvent) {
        int i2;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i3 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View findTopChildUnder = findTopChildUnder((int) x, (int) y);
            r(x, y, pointerId);
            u(findTopChildUnder, pointerId);
            int i4 = this.h[pointerId];
            int i5 = this.p;
            if ((i4 & i5) != 0) {
                this.r.onEdgeTouched(i4 & i5, pointerId);
                return;
            }
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (this.a != 1) {
                    int pointerCount = motionEvent.getPointerCount();
                    while (i3 < pointerCount) {
                        int pointerId2 = motionEvent.getPointerId(i3);
                        if (o(pointerId2)) {
                            float x2 = motionEvent.getX(i3);
                            float y2 = motionEvent.getY(i3);
                            float f = x2 - this.f680d[pointerId2];
                            float f2 = y2 - this.e[pointerId2];
                            q(f, f2, pointerId2);
                            if (this.a != 1) {
                                View findTopChildUnder2 = findTopChildUnder((int) x2, (int) y2);
                                if (b(findTopChildUnder2, f, f2) && u(findTopChildUnder2, pointerId2)) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        i3++;
                    }
                } else {
                    if (!o(this.f679c)) {
                        return;
                    }
                    int findPointerIndex = motionEvent.findPointerIndex(this.f679c);
                    float x3 = motionEvent.getX(findPointerIndex);
                    float y3 = motionEvent.getY(findPointerIndex);
                    float[] fArr = this.f;
                    int i6 = this.f679c;
                    int i7 = (int) (x3 - fArr[i6]);
                    int i8 = (int) (y3 - this.g[i6]);
                    k(this.s.getLeft() + i7, this.s.getTop() + i8, i7, i8);
                }
                s(motionEvent);
                return;
            }
            if (actionMasked != 3) {
                if (actionMasked == 5) {
                    int pointerId3 = motionEvent.getPointerId(actionIndex);
                    float x4 = motionEvent.getX(actionIndex);
                    float y4 = motionEvent.getY(actionIndex);
                    r(x4, y4, pointerId3);
                    if (this.a != 0) {
                        if (isCapturedViewUnder((int) x4, (int) y4)) {
                            u(this.s, pointerId3);
                            return;
                        }
                        return;
                    } else {
                        u(findTopChildUnder((int) x4, (int) y4), pointerId3);
                        int i9 = this.h[pointerId3];
                        int i10 = this.p;
                        if ((i9 & i10) != 0) {
                            this.r.onEdgeTouched(i9 & i10, pointerId3);
                            return;
                        }
                        return;
                    }
                }
                if (actionMasked != 6) {
                    return;
                }
                int pointerId4 = motionEvent.getPointerId(actionIndex);
                if (this.a == 1 && pointerId4 == this.f679c) {
                    int pointerCount2 = motionEvent.getPointerCount();
                    while (true) {
                        if (i3 >= pointerCount2) {
                            i2 = -1;
                            break;
                        }
                        int pointerId5 = motionEvent.getPointerId(i3);
                        if (pointerId5 != this.f679c) {
                            View findTopChildUnder3 = findTopChildUnder((int) motionEvent.getX(i3), (int) motionEvent.getY(i3));
                            View view = this.s;
                            if (findTopChildUnder3 == view && u(view, pointerId5)) {
                                i2 = this.f679c;
                                break;
                            }
                        }
                        i3++;
                    }
                    if (i2 == -1) {
                        p();
                    }
                }
                f(pointerId4);
                return;
            }
            if (this.a == 1) {
                i(0.0f, 0.0f);
            }
        } else if (this.a == 1) {
            p();
        }
        cancel();
    }

    public void setEdgeTrackingEnabled(int i2) {
        this.p = i2;
    }

    public void setMinVelocity(float f) {
        this.n = f;
    }

    public boolean settleCapturedViewAt(int i2, int i3) {
        if (this.t) {
            return m(i2, i3, (int) this.l.getXVelocity(this.f679c), (int) this.l.getYVelocity(this.f679c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00dd, code lost:
    
        if (r12 != r11) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean shouldInterceptTouchEvent(@androidx.annotation.NonNull android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean smoothSlideViewTo(@NonNull View view, int i2, int i3) {
        this.s = view;
        this.f679c = -1;
        boolean m = m(i2, i3, 0, 0);
        if (!m && this.a == 0 && this.s != null) {
            this.s = null;
        }
        return m;
    }

    void t(int i2) {
        this.u.removeCallbacks(this.v);
        if (this.a != i2) {
            this.a = i2;
            this.r.onViewDragStateChanged(i2);
            if (this.a == 0) {
                this.s = null;
            }
        }
    }

    boolean u(View view, int i2) {
        if (view == this.s && this.f679c == i2) {
            return true;
        }
        if (view == null || !this.r.tryCaptureView(view, i2)) {
            return false;
        }
        this.f679c = i2;
        captureChildView(view, i2);
        return true;
    }
}
