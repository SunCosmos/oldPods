package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
@VisibleForTesting
/* loaded from: classes.dex */
public class c extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    private static final int[] D = {R.attr.state_pressed};
    private static final int[] E = new int[0];
    int A;
    private final Runnable B;
    private final RecyclerView.OnScrollListener C;
    private final int a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    final StateListDrawable f999c;

    /* renamed from: d, reason: collision with root package name */
    final Drawable f1000d;
    private final int e;
    private final int f;
    private final StateListDrawable g;
    private final Drawable h;

    /* renamed from: i, reason: collision with root package name */
    private final int f1001i;
    private final int j;

    @VisibleForTesting
    int k;

    @VisibleForTesting
    int l;

    @VisibleForTesting
    float m;

    @VisibleForTesting
    int n;

    @VisibleForTesting
    int o;

    @VisibleForTesting
    float p;
    private RecyclerView s;
    final ValueAnimator z;
    private int q = 0;
    private int r = 0;
    private boolean t = false;
    private boolean u = false;
    private int v = 0;
    private int w = 0;
    private final int[] x = new int[2];
    private final int[] y = new int[2];

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.g(500);
        }
    }

    /* loaded from: classes.dex */
    class b extends RecyclerView.OnScrollListener {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            c.this.r(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* renamed from: androidx.recyclerview.widget.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private class C0036c extends AnimatorListenerAdapter {
        private boolean a = false;

        C0036c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.a) {
                this.a = false;
                return;
            }
            if (((Float) c.this.z.getAnimatedValue()).floatValue() == 0.0f) {
                c cVar = c.this;
                cVar.A = 0;
                cVar.o(0);
            } else {
                c cVar2 = c.this;
                cVar2.A = 2;
                cVar2.l();
            }
        }
    }

    /* loaded from: classes.dex */
    private class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            c.this.f999c.setAlpha(floatValue);
            c.this.f1000d.setAlpha(floatValue);
            c.this.l();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i2, int i3, int i4) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.z = ofFloat;
        this.A = 0;
        this.B = new a();
        this.C = new b();
        this.f999c = stateListDrawable;
        this.f1000d = drawable;
        this.g = stateListDrawable2;
        this.h = drawable2;
        this.e = Math.max(i2, stateListDrawable.getIntrinsicWidth());
        this.f = Math.max(i2, drawable.getIntrinsicWidth());
        this.f1001i = Math.max(i2, stateListDrawable2.getIntrinsicWidth());
        this.j = Math.max(i2, drawable2.getIntrinsicWidth());
        this.a = i3;
        this.b = i4;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        ofFloat.addListener(new C0036c());
        ofFloat.addUpdateListener(new d());
        attachToRecyclerView(recyclerView);
    }

    private void a() {
        this.s.removeCallbacks(this.B);
    }

    private void b() {
        this.s.removeItemDecoration(this);
        this.s.removeOnItemTouchListener(this);
        this.s.removeOnScrollListener(this.C);
        a();
    }

    private void c(Canvas canvas) {
        int i2 = this.r;
        int i3 = this.f1001i;
        int i4 = this.o;
        int i5 = this.n;
        this.g.setBounds(0, 0, i5, i3);
        this.h.setBounds(0, 0, this.q, this.j);
        canvas.translate(0.0f, i2 - i3);
        this.h.draw(canvas);
        canvas.translate(i4 - (i5 / 2), 0.0f);
        this.g.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    private void d(Canvas canvas) {
        int i2 = this.q;
        int i3 = this.e;
        int i4 = i2 - i3;
        int i5 = this.l;
        int i6 = this.k;
        int i7 = i5 - (i6 / 2);
        this.f999c.setBounds(0, 0, i3, i6);
        this.f1000d.setBounds(0, 0, this.f, this.r);
        if (i()) {
            this.f1000d.draw(canvas);
            canvas.translate(this.e, i7);
            canvas.scale(-1.0f, 1.0f);
            this.f999c.draw(canvas);
            canvas.scale(1.0f, 1.0f);
            i4 = this.e;
        } else {
            canvas.translate(i4, 0.0f);
            this.f1000d.draw(canvas);
            canvas.translate(0.0f, i7);
            this.f999c.draw(canvas);
        }
        canvas.translate(-i4, -i7);
    }

    private int[] e() {
        int[] iArr = this.y;
        int i2 = this.b;
        iArr[0] = i2;
        iArr[1] = this.q - i2;
        return iArr;
    }

    private int[] f() {
        int[] iArr = this.x;
        int i2 = this.b;
        iArr[0] = i2;
        iArr[1] = this.r - i2;
        return iArr;
    }

    private void h(float f) {
        int[] e = e();
        float max = Math.max(e[0], Math.min(e[1], f));
        if (Math.abs(this.o - max) < 2.0f) {
            return;
        }
        int n = n(this.p, max, e, this.s.computeHorizontalScrollRange(), this.s.computeHorizontalScrollOffset(), this.q);
        if (n != 0) {
            this.s.scrollBy(n, 0);
        }
        this.p = max;
    }

    private boolean i() {
        return ViewCompat.getLayoutDirection(this.s) == 1;
    }

    private void m(int i2) {
        a();
        this.s.postDelayed(this.B, i2);
    }

    private int n(float f, float f2, int[] iArr, int i2, int i3, int i4) {
        int i5 = iArr[1] - iArr[0];
        if (i5 == 0) {
            return 0;
        }
        int i6 = i2 - i4;
        int i7 = (int) (((f2 - f) / i5) * i6);
        int i8 = i3 + i7;
        if (i8 >= i6 || i8 < 0) {
            return 0;
        }
        return i7;
    }

    private void p() {
        this.s.addItemDecoration(this);
        this.s.addOnItemTouchListener(this);
        this.s.addOnScrollListener(this.C);
    }

    private void s(float f) {
        int[] f2 = f();
        float max = Math.max(f2[0], Math.min(f2[1], f));
        if (Math.abs(this.l - max) < 2.0f) {
            return;
        }
        int n = n(this.m, max, f2, this.s.computeVerticalScrollRange(), this.s.computeVerticalScrollOffset(), this.r);
        if (n != 0) {
            this.s.scrollBy(0, n);
        }
        this.m = max;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            b();
        }
        this.s = recyclerView;
        if (recyclerView != null) {
            p();
        }
    }

    @VisibleForTesting
    void g(int i2) {
        int i3 = this.A;
        if (i3 == 1) {
            this.z.cancel();
        } else if (i3 != 2) {
            return;
        }
        this.A = 3;
        ValueAnimator valueAnimator = this.z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.z.setDuration(i2);
        this.z.start();
    }

    @VisibleForTesting
    boolean j(float f, float f2) {
        if (f2 >= this.r - this.f1001i) {
            int i2 = this.o;
            int i3 = this.n;
            if (f >= i2 - (i3 / 2) && f <= i2 + (i3 / 2)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    boolean k(float f, float f2) {
        if (!i() ? f >= this.q - this.e : f <= this.e / 2) {
            int i2 = this.l;
            int i3 = this.k;
            if (f2 >= i2 - (i3 / 2) && f2 <= i2 + (i3 / 2)) {
                return true;
            }
        }
        return false;
    }

    void l() {
        this.s.invalidate();
    }

    void o(int i2) {
        int i3;
        if (i2 == 2 && this.v != 2) {
            this.f999c.setState(D);
            a();
        }
        if (i2 == 0) {
            l();
        } else {
            q();
        }
        if (this.v != 2 || i2 == 2) {
            i3 = i2 == 1 ? 1500 : 1200;
            this.v = i2;
        }
        this.f999c.setState(E);
        m(i3);
        this.v = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.q != this.s.getWidth() || this.r != this.s.getHeight()) {
            this.q = this.s.getWidth();
            this.r = this.s.getHeight();
            o(0);
        } else if (this.A != 0) {
            if (this.t) {
                d(canvas);
            }
            if (this.u) {
                c(canvas);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        int i2 = this.v;
        if (i2 == 1) {
            boolean k = k(motionEvent.getX(), motionEvent.getY());
            boolean j = j(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!k && !j) {
                return false;
            }
            if (j) {
                this.w = 1;
                this.p = (int) motionEvent.getX();
            } else if (k) {
                this.w = 2;
                this.m = (int) motionEvent.getY();
            }
            o(2);
        } else if (i2 != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if (this.v == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean k = k(motionEvent.getX(), motionEvent.getY());
            boolean j = j(motionEvent.getX(), motionEvent.getY());
            if (k || j) {
                if (j) {
                    this.w = 1;
                    this.p = (int) motionEvent.getX();
                } else if (k) {
                    this.w = 2;
                    this.m = (int) motionEvent.getY();
                }
                o(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.v == 2) {
            this.m = 0.0f;
            this.p = 0.0f;
            o(1);
            this.w = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.v == 2) {
            q();
            if (this.w == 1) {
                h(motionEvent.getX());
            }
            if (this.w == 2) {
                s(motionEvent.getY());
            }
        }
    }

    public void q() {
        int i2 = this.A;
        if (i2 != 0) {
            if (i2 != 3) {
                return;
            } else {
                this.z.cancel();
            }
        }
        this.A = 1;
        ValueAnimator valueAnimator = this.z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.z.setDuration(500L);
        this.z.setStartDelay(0L);
        this.z.start();
    }

    void r(int i2, int i3) {
        int computeVerticalScrollRange = this.s.computeVerticalScrollRange();
        int i4 = this.r;
        this.t = computeVerticalScrollRange - i4 > 0 && i4 >= this.a;
        int computeHorizontalScrollRange = this.s.computeHorizontalScrollRange();
        int i5 = this.q;
        boolean z = computeHorizontalScrollRange - i5 > 0 && i5 >= this.a;
        this.u = z;
        boolean z2 = this.t;
        if (!z2 && !z) {
            if (this.v != 0) {
                o(0);
                return;
            }
            return;
        }
        if (z2) {
            float f = i4;
            this.l = (int) ((f * (i3 + (f / 2.0f))) / computeVerticalScrollRange);
            this.k = Math.min(i4, (i4 * i4) / computeVerticalScrollRange);
        }
        if (this.u) {
            float f2 = i5;
            this.o = (int) ((f2 * (i2 + (f2 / 2.0f))) / computeHorizontalScrollRange);
            this.n = Math.min(i5, (i5 * i5) / computeHorizontalScrollRange);
        }
        int i6 = this.v;
        if (i6 == 0 || i6 == 1) {
            o(1);
        }
    }
}
