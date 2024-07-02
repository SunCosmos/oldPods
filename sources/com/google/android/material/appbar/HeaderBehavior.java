package com.google.android.material.appbar;

import android.content.Context;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {

    @Nullable
    private Runnable a;
    OverScroller b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1457c;

    /* renamed from: d, reason: collision with root package name */
    private int f1458d;
    private int e;
    private int f;

    @Nullable
    private VelocityTracker g;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Field signature parse error: b
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: TV at position 1 ('V'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.visitors.SignatureProcessor.parseFieldSignature(SignatureProcessor.java:128)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:36)
     */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        private final CoordinatorLayout a;
        private final View b;

        a(CoordinatorLayout coordinatorLayout, V v) {
            this.a = coordinatorLayout;
            this.b = v;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.b == null || (overScroller = HeaderBehavior.this.b) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                HeaderBehavior.this.g(this.a, this.b);
                return;
            }
            HeaderBehavior headerBehavior = HeaderBehavior.this;
            headerBehavior.i(this.a, this.b, headerBehavior.b.getCurrY());
            ViewCompat.postOnAnimation(this.b, this);
        }
    }

    public HeaderBehavior() {
        this.f1458d = -1;
        this.f = -1;
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1458d = -1;
        this.f = -1;
    }

    private void b() {
        if (this.g == null) {
            this.g = VelocityTracker.obtain();
        }
    }

    boolean a(V v) {
        return false;
    }

    final boolean c(CoordinatorLayout coordinatorLayout, @NonNull V v, int i2, int i3, float f) {
        Runnable runnable = this.a;
        if (runnable != null) {
            v.removeCallbacks(runnable);
            this.a = null;
        }
        if (this.b == null) {
            this.b = new OverScroller(v.getContext());
        }
        this.b.fling(0, getTopAndBottomOffset(), 0, Math.round(f), 0, 0, i2, i3);
        if (!this.b.computeScrollOffset()) {
            g(coordinatorLayout, v);
            return false;
        }
        a aVar = new a(coordinatorLayout, v);
        this.a = aVar;
        ViewCompat.postOnAnimation(v, aVar);
        return true;
    }

    int d(@NonNull V v) {
        return -v.getHeight();
    }

    int e(@NonNull V v) {
        return v.getHeight();
    }

    int f() {
        return getTopAndBottomOffset();
    }

    void g(CoordinatorLayout coordinatorLayout, V v) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int h(CoordinatorLayout coordinatorLayout, V v, int i2, int i3, int i4) {
        return j(coordinatorLayout, v, f() - i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i(CoordinatorLayout coordinatorLayout, V v, int i2) {
        return j(coordinatorLayout, v, i2, Integer.MIN_VALUE, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    int j(CoordinatorLayout coordinatorLayout, V v, int i2, int i3, int i4) {
        int clamp;
        int topAndBottomOffset = getTopAndBottomOffset();
        if (i3 == 0 || topAndBottomOffset < i3 || topAndBottomOffset > i4 || topAndBottomOffset == (clamp = MathUtils.clamp(i2, i3, i4))) {
            return 0;
        }
        setTopAndBottomOffset(clamp);
        return topAndBottomOffset - clamp;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        int findPointerIndex;
        if (this.f < 0) {
            this.f = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f1457c) {
            int i2 = this.f1458d;
            if (i2 == -1 || (findPointerIndex = motionEvent.findPointerIndex(i2)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(findPointerIndex);
            if (Math.abs(y - this.e) > this.f) {
                this.e = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f1458d = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            boolean z = a(v) && coordinatorLayout.isPointInChildBounds(v, x, y2);
            this.f1457c = z;
            if (z) {
                this.e = y2;
                this.f1458d = motionEvent.getPointerId(0);
                b();
                OverScroller overScroller = this.b;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.b.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.g;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007b  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(@androidx.annotation.NonNull androidx.coordinatorlayout.widget.CoordinatorLayout r12, @androidx.annotation.NonNull V r13, @androidx.annotation.NonNull android.view.MotionEvent r14) {
        /*
            r11 = this;
            int r0 = r14.getActionMasked()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L4e
            r4 = 2
            if (r0 == r4) goto L2d
            r12 = 3
            if (r0 == r12) goto L72
            r12 = 6
            if (r0 == r12) goto L13
            goto L4c
        L13:
            int r12 = r14.getActionIndex()
            if (r12 != 0) goto L1b
            r12 = 1
            goto L1c
        L1b:
            r12 = 0
        L1c:
            int r13 = r14.getPointerId(r12)
            r11.f1458d = r13
            float r12 = r14.getY(r12)
            r13 = 1056964608(0x3f000000, float:0.5)
            float r12 = r12 + r13
            int r12 = (int) r12
            r11.e = r12
            goto L4c
        L2d:
            int r0 = r11.f1458d
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r1) goto L36
            return r3
        L36:
            float r0 = r14.getY(r0)
            int r0 = (int) r0
            int r1 = r11.e
            int r7 = r1 - r0
            r11.e = r0
            int r8 = r11.d(r13)
            r9 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r4.h(r5, r6, r7, r8, r9)
        L4c:
            r12 = 0
            goto L81
        L4e:
            android.view.VelocityTracker r0 = r11.g
            if (r0 == 0) goto L72
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.g
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.g
            int r4 = r11.f1458d
            float r10 = r0.getYVelocity(r4)
            int r0 = r11.e(r13)
            int r8 = -r0
            r9 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r5.c(r6, r7, r8, r9, r10)
            r12 = 1
            goto L73
        L72:
            r12 = 0
        L73:
            r11.f1457c = r3
            r11.f1458d = r1
            android.view.VelocityTracker r13 = r11.g
            if (r13 == 0) goto L81
            r13.recycle()
            r13 = 0
            r11.g = r13
        L81:
            android.view.VelocityTracker r13 = r11.g
            if (r13 == 0) goto L88
            r13.addMovement(r14)
        L88:
            boolean r13 = r11.f1457c
            if (r13 != 0) goto L90
            if (r12 == 0) goto L8f
            goto L90
        L8f:
            r2 = 0
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }
}
