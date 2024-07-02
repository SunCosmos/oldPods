package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public abstract class AutoScrollHelper implements View.OnTouchListener {
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    public static final float NO_MAX = Float.MAX_VALUE;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;
    private static final int r = ViewConfiguration.getTapTimeout();

    /* renamed from: c, reason: collision with root package name */
    final View f658c;

    /* renamed from: d, reason: collision with root package name */
    private Runnable f659d;
    private int g;
    private int h;
    private boolean l;
    boolean m;
    boolean n;
    boolean o;
    private boolean p;
    private boolean q;
    final a a = new a();
    private final Interpolator b = new AccelerateInterpolator();
    private float[] e = {0.0f, 0.0f};
    private float[] f = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: i, reason: collision with root package name */
    private float[] f660i = {0.0f, 0.0f};
    private float[] j = {0.0f, 0.0f};
    private float[] k = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        private int a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private float f661c;

        /* renamed from: d, reason: collision with root package name */
        private float f662d;
        private float j;
        private int k;
        private long e = Long.MIN_VALUE;

        /* renamed from: i, reason: collision with root package name */
        private long f663i = -1;
        private long f = 0;
        private int g = 0;
        private int h = 0;

        a() {
        }

        private float e(long j) {
            long j2 = this.e;
            if (j < j2) {
                return 0.0f;
            }
            long j3 = this.f663i;
            if (j3 < 0 || j < j3) {
                return AutoScrollHelper.c(((float) (j - j2)) / this.a, 0.0f, 1.0f) * 0.5f;
            }
            float f = this.j;
            return (1.0f - f) + (f * AutoScrollHelper.c(((float) (j - j3)) / this.k, 0.0f, 1.0f));
        }

        private float g(float f) {
            return ((-4.0f) * f * f) + (f * 4.0f);
        }

        public void a() {
            if (this.f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float g = g(e(currentAnimationTimeMillis));
            long j = currentAnimationTimeMillis - this.f;
            this.f = currentAnimationTimeMillis;
            float f = ((float) j) * g;
            this.g = (int) (this.f661c * f);
            this.h = (int) (f * this.f662d);
        }

        public int b() {
            return this.g;
        }

        public int c() {
            return this.h;
        }

        public int d() {
            float f = this.f661c;
            return (int) (f / Math.abs(f));
        }

        public int f() {
            float f = this.f662d;
            return (int) (f / Math.abs(f));
        }

        public boolean h() {
            return this.f663i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f663i + ((long) this.k);
        }

        public void i() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = AutoScrollHelper.d((int) (currentAnimationTimeMillis - this.e), 0, this.b);
            this.j = e(currentAnimationTimeMillis);
            this.f663i = currentAnimationTimeMillis;
        }

        public void j(int i2) {
            this.b = i2;
        }

        public void k(int i2) {
            this.a = i2;
        }

        public void l(float f, float f2) {
            this.f661c = f;
            this.f662d = f2;
        }

        public void m() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.e = currentAnimationTimeMillis;
            this.f663i = -1L;
            this.f = currentAnimationTimeMillis;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AutoScrollHelper autoScrollHelper = AutoScrollHelper.this;
            if (autoScrollHelper.o) {
                if (autoScrollHelper.m) {
                    autoScrollHelper.m = false;
                    autoScrollHelper.a.m();
                }
                a aVar = AutoScrollHelper.this.a;
                if (aVar.h() || !AutoScrollHelper.this.h()) {
                    AutoScrollHelper.this.o = false;
                    return;
                }
                AutoScrollHelper autoScrollHelper2 = AutoScrollHelper.this;
                if (autoScrollHelper2.n) {
                    autoScrollHelper2.n = false;
                    autoScrollHelper2.a();
                }
                aVar.a();
                AutoScrollHelper.this.scrollTargetBy(aVar.b(), aVar.c());
                ViewCompat.postOnAnimation(AutoScrollHelper.this.f658c, this);
            }
        }
    }

    public AutoScrollHelper(@NonNull View view) {
        this.f658c = view;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float f2 = (int) ((1575.0f * f) + 0.5f);
        setMaximumVelocity(f2, f2);
        float f3 = (int) ((f * 315.0f) + 0.5f);
        setMinimumVelocity(f3, f3);
        setEdgeType(1);
        setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
        setRelativeEdges(0.2f, 0.2f);
        setRelativeVelocity(1.0f, 1.0f);
        setActivationDelay(r);
        setRampUpDuration(500);
        setRampDownDuration(500);
    }

    private float b(int i2, float f, float f2, float f3) {
        float f4 = f(this.e[i2], f2, this.f[i2], f);
        if (f4 == 0.0f) {
            return 0.0f;
        }
        float f5 = this.f660i[i2];
        float f6 = this.j[i2];
        float f7 = this.k[i2];
        float f8 = f5 * f3;
        return f4 > 0.0f ? c(f4 * f8, f6, f7) : -c((-f4) * f8, f6, f7);
    }

    static float c(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    static int d(int i2, int i3, int i4) {
        return i2 > i4 ? i4 : i2 < i3 ? i3 : i2;
    }

    private float e(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i2 = this.g;
        if (i2 == 0 || i2 == 1) {
            if (f < f2) {
                if (f >= 0.0f) {
                    return 1.0f - (f / f2);
                }
                if (this.o && i2 == 1) {
                    return 1.0f;
                }
            }
        } else if (i2 == 2 && f < 0.0f) {
            return f / (-f2);
        }
        return 0.0f;
    }

    private float f(float f, float f2, float f3, float f4) {
        float interpolation;
        float c2 = c(f * f2, 0.0f, f3);
        float e = e(f2 - f4, c2) - e(f4, c2);
        if (e < 0.0f) {
            interpolation = -this.b.getInterpolation(-e);
        } else {
            if (e <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.b.getInterpolation(e);
        }
        return c(interpolation, -1.0f, 1.0f);
    }

    private void g() {
        if (this.m) {
            this.o = false;
        } else {
            this.a.i();
        }
    }

    private void i() {
        int i2;
        if (this.f659d == null) {
            this.f659d = new b();
        }
        this.o = true;
        this.m = true;
        if (this.l || (i2 = this.h) <= 0) {
            this.f659d.run();
        } else {
            ViewCompat.postOnAnimationDelayed(this.f658c, this.f659d, i2);
        }
        this.l = true;
    }

    void a() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f658c.onTouchEvent(obtain);
        obtain.recycle();
    }

    public abstract boolean canTargetScrollHorizontally(int i2);

    public abstract boolean canTargetScrollVertically(int i2);

    boolean h() {
        a aVar = this.a;
        int f = aVar.f();
        int d2 = aVar.d();
        return (f != 0 && canTargetScrollVertically(f)) || (d2 != 0 && canTargetScrollHorizontally(d2));
    }

    public boolean isEnabled() {
        return this.p;
    }

    public boolean isExclusive() {
        return this.q;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        if (r0 != 3) goto L20;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.p
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.g()
            goto L58
        L1a:
            r5.n = r2
            r5.l = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f658c
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.b(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f658c
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.b(r2, r7, r6, r3)
            androidx.core.widget.AutoScrollHelper$a r7 = r5.a
            r7.l(r0, r6)
            boolean r6 = r5.o
            if (r6 != 0) goto L58
            boolean r6 = r5.h()
            if (r6 == 0) goto L58
            r5.i()
        L58:
            boolean r6 = r5.q
            if (r6 == 0) goto L61
            boolean r6 = r5.o
            if (r6 == 0) goto L61
            r1 = 1
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.AutoScrollHelper.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract void scrollTargetBy(int i2, int i3);

    @NonNull
    public AutoScrollHelper setActivationDelay(int i2) {
        this.h = i2;
        return this;
    }

    @NonNull
    public AutoScrollHelper setEdgeType(int i2) {
        this.g = i2;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean z) {
        if (this.p && !z) {
            g();
        }
        this.p = z;
        return this;
    }

    public AutoScrollHelper setExclusive(boolean z) {
        this.q = z;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumEdges(float f, float f2) {
        float[] fArr = this.f;
        fArr[0] = f;
        fArr[1] = f2;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMaximumVelocity(float f, float f2) {
        float[] fArr = this.k;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setMinimumVelocity(float f, float f2) {
        float[] fArr = this.j;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampDownDuration(int i2) {
        this.a.j(i2);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRampUpDuration(int i2) {
        this.a.k(i2);
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeEdges(float f, float f2) {
        float[] fArr = this.e;
        fArr[0] = f;
        fArr[1] = f2;
        return this;
    }

    @NonNull
    public AutoScrollHelper setRelativeVelocity(float f, float f2) {
        float[] fArr = this.f660i;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }
}
