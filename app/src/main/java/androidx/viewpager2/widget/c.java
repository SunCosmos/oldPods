package androidx.viewpager2.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public final class c {
    private final ViewPager2 a;
    private final e b;

    /* renamed from: c */
    private final RecyclerView f1151c;

    /* renamed from: d */
    private VelocityTracker f1152d;
    private int e;
    private float f;
    private int g;
    private long h;

    public c(ViewPager2 viewPager2, e eVar, RecyclerView recyclerView) {
        this.a = viewPager2;
        this.b = eVar;
        this.f1151c = recyclerView;
    }

    private void a(long j, int i2, float f, float f2) {
        MotionEvent obtain = MotionEvent.obtain(this.h, j, i2, f, f2, 0);
        this.f1152d.addMovement(obtain);
        obtain.recycle();
    }

    private void c() {
        VelocityTracker velocityTracker = this.f1152d;
        if (velocityTracker != null) {
            velocityTracker.clear();
        } else {
            this.f1152d = VelocityTracker.obtain();
            this.e = ViewConfiguration.get(this.a.getContext()).getScaledMaximumFlingVelocity();
        }
    }

    @UiThread
    public boolean b() {
        if (this.b.g()) {
            return false;
        }
        this.g = 0;
        this.f = 0;
        this.h = SystemClock.uptimeMillis();
        c();
        this.b.k();
        if (!this.b.i()) {
            this.f1151c.stopScroll();
        }
        a(this.h, 0, 0.0f, 0.0f);
        return true;
    }

    @UiThread
    public boolean d() {
        if (!this.b.h()) {
            return false;
        }
        this.b.m();
        VelocityTracker velocityTracker = this.f1152d;
        velocityTracker.computeCurrentVelocity(1000, this.e);
        if (this.f1151c.fling((int) velocityTracker.getXVelocity(), (int) velocityTracker.getYVelocity())) {
            return true;
        }
        this.a.h();
        return true;
    }

    @UiThread
    public boolean e(float f) {
        if (!this.b.h()) {
            return false;
        }
        float f2 = this.f - f;
        this.f = f2;
        int round = Math.round(f2 - this.g);
        this.g += round;
        long uptimeMillis = SystemClock.uptimeMillis();
        boolean z = this.a.getOrientation() == 0;
        int i2 = z ? round : 0;
        int i3 = z ? 0 : round;
        float f3 = z ? this.f : 0.0f;
        float f4 = z ? 0.0f : this.f;
        this.f1151c.scrollBy(i2, i3);
        a(uptimeMillis, 2, f3, f4);
        return true;
    }

    public boolean f() {
        return this.b.h();
    }
}
