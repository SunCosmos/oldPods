package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class CircularProgressDrawable extends Drawable implements Animatable {
    public static final int DEFAULT = 1;
    public static final int LARGE = 0;
    private static final Interpolator g = new LinearInterpolator();
    private static final Interpolator h = new FastOutSlowInInterpolator();

    /* renamed from: i, reason: collision with root package name */
    private static final int[] f1028i = {-16777216};
    private final c a;
    private float b;

    /* renamed from: c, reason: collision with root package name */
    private Resources f1029c;

    /* renamed from: d, reason: collision with root package name */
    private Animator f1030d;
    float e;
    boolean f;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ProgressDrawableSize {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ c a;

        a(c cVar) {
            this.a = cVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            CircularProgressDrawable.this.g(floatValue, this.a);
            CircularProgressDrawable.this.b(floatValue, this.a, false);
            CircularProgressDrawable.this.invalidateSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Animator.AnimatorListener {
        final /* synthetic */ c a;

        b(c cVar) {
            this.a = cVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            CircularProgressDrawable.this.b(1.0f, this.a, true);
            this.a.M();
            this.a.v();
            CircularProgressDrawable circularProgressDrawable = CircularProgressDrawable.this;
            if (!circularProgressDrawable.f) {
                circularProgressDrawable.e += 1.0f;
                return;
            }
            circularProgressDrawable.f = false;
            animator.cancel();
            animator.setDuration(1332L);
            animator.start();
            this.a.I(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            CircularProgressDrawable.this.e = 0.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {
        final RectF a = new RectF();
        final Paint b;

        /* renamed from: c, reason: collision with root package name */
        final Paint f1031c;

        /* renamed from: d, reason: collision with root package name */
        final Paint f1032d;
        float e;
        float f;
        float g;
        float h;

        /* renamed from: i, reason: collision with root package name */
        int[] f1033i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float p;
        float q;
        int r;
        int s;
        int t;
        int u;

        c() {
            Paint paint = new Paint();
            this.b = paint;
            Paint paint2 = new Paint();
            this.f1031c = paint2;
            Paint paint3 = new Paint();
            this.f1032d = paint3;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 5.0f;
            this.p = 1.0f;
            this.t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        void A(int i2) {
            this.f1032d.setColor(i2);
        }

        void B(float f) {
            this.q = f;
        }

        void C(int i2) {
            this.u = i2;
        }

        void D(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }

        void E(int i2) {
            this.j = i2;
            this.u = this.f1033i[i2];
        }

        void F(@NonNull int[] iArr) {
            this.f1033i = iArr;
            E(0);
        }

        void G(float f) {
            this.f = f;
        }

        void H(float f) {
            this.g = f;
        }

        void I(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        void J(float f) {
            this.e = f;
        }

        void K(Paint.Cap cap) {
            this.b.setStrokeCap(cap);
        }

        void L(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
        }

        void M() {
            this.k = this.e;
            this.l = this.f;
            this.m = this.g;
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.a;
            float f = this.q;
            float f2 = (this.h / 2.0f) + f;
            if (f <= 0.0f) {
                f2 = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.r * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(rect.centerX() - f2, rect.centerY() - f2, rect.centerX() + f2, rect.centerY() + f2);
            float f3 = this.e;
            float f4 = this.g;
            float f5 = (f3 + f4) * 360.0f;
            float f6 = ((this.f + f4) * 360.0f) - f5;
            this.b.setColor(this.u);
            this.b.setAlpha(this.t);
            float f7 = this.h / 2.0f;
            rectF.inset(f7, f7);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f1032d);
            float f8 = -f7;
            rectF.inset(f8, f8);
            canvas.drawArc(rectF, f5, f6, false, this.b);
            b(canvas, f5, f6, rectF);
        }

        void b(Canvas canvas, float f, float f2, RectF rectF) {
            if (this.n) {
                Path path = this.o;
                if (path == null) {
                    Path path2 = new Path();
                    this.o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float min = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f3 = (this.r * this.p) / 2.0f;
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(this.r * this.p, 0.0f);
                Path path3 = this.o;
                float f4 = this.r;
                float f5 = this.p;
                path3.lineTo((f4 * f5) / 2.0f, this.s * f5);
                this.o.offset((min + rectF.centerX()) - f3, rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.f1031c.setColor(this.u);
                this.f1031c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f + f2, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.f1031c);
                canvas.restore();
            }
        }

        int c() {
            return this.t;
        }

        float d() {
            return this.s;
        }

        float e() {
            return this.p;
        }

        float f() {
            return this.r;
        }

        int g() {
            return this.f1032d.getColor();
        }

        float h() {
            return this.q;
        }

        int[] i() {
            return this.f1033i;
        }

        float j() {
            return this.f;
        }

        int k() {
            return this.f1033i[l()];
        }

        int l() {
            return (this.j + 1) % this.f1033i.length;
        }

        float m() {
            return this.g;
        }

        boolean n() {
            return this.n;
        }

        float o() {
            return this.e;
        }

        int p() {
            return this.f1033i[this.j];
        }

        float q() {
            return this.l;
        }

        float r() {
            return this.m;
        }

        float s() {
            return this.k;
        }

        Paint.Cap t() {
            return this.b.getStrokeCap();
        }

        float u() {
            return this.h;
        }

        void v() {
            E(l());
        }

        void w() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            J(0.0f);
            G(0.0f);
            H(0.0f);
        }

        void x(int i2) {
            this.t = i2;
        }

        void y(float f, float f2) {
            this.r = (int) f;
            this.s = (int) f2;
        }

        void z(float f) {
            if (f != this.p) {
                this.p = f;
            }
        }
    }

    public CircularProgressDrawable(@NonNull Context context) {
        this.f1029c = ((Context) Preconditions.checkNotNull(context)).getResources();
        c cVar = new c();
        this.a = cVar;
        cVar.F(f1028i);
        setStrokeWidth(2.5f);
        f();
    }

    private void a(float f, c cVar) {
        g(f, cVar);
        float floor = (float) (Math.floor(cVar.r() / 0.8f) + 1.0d);
        cVar.J(cVar.s() + (((cVar.q() - 0.01f) - cVar.s()) * f));
        cVar.G(cVar.q());
        cVar.H(cVar.r() + ((floor - cVar.r()) * f));
    }

    private int c(float f, int i2, int i3) {
        return ((((i2 >> 24) & 255) + ((int) ((((i3 >> 24) & 255) - r0) * f))) << 24) | ((((i2 >> 16) & 255) + ((int) ((((i3 >> 16) & 255) - r1) * f))) << 16) | ((((i2 >> 8) & 255) + ((int) ((((i3 >> 8) & 255) - r2) * f))) << 8) | ((i2 & 255) + ((int) (f * ((i3 & 255) - r8))));
    }

    private void d(float f) {
        this.b = f;
    }

    private void e(float f, float f2, float f3, float f4) {
        c cVar = this.a;
        float f5 = this.f1029c.getDisplayMetrics().density;
        cVar.L(f2 * f5);
        cVar.B(f * f5);
        cVar.E(0);
        cVar.y(f3 * f5, f4 * f5);
    }

    private void f() {
        c cVar = this.a;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new a(cVar));
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(g);
        ofFloat.addListener(new b(cVar));
        this.f1030d = ofFloat;
    }

    void b(float f, c cVar, boolean z) {
        float interpolation;
        float f2;
        if (this.f) {
            a(f, cVar);
            return;
        }
        if (f != 1.0f || z) {
            float r = cVar.r();
            if (f < 0.5f) {
                interpolation = cVar.s();
                f2 = (h.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float s = cVar.s() + 0.79f;
                interpolation = s - (((1.0f - h.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                f2 = s;
            }
            float f3 = r + (0.20999998f * f);
            float f4 = (f + this.e) * 216.0f;
            cVar.J(interpolation);
            cVar.G(f2);
            cVar.H(f3);
            d(f4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.b, bounds.exactCenterX(), bounds.exactCenterY());
        this.a.a(canvas, bounds);
        canvas.restore();
    }

    void g(float f, c cVar) {
        cVar.C(f > 0.75f ? c((f - 0.75f) / 0.25f, cVar.p(), cVar.k()) : cVar.p());
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.a.c();
    }

    public boolean getArrowEnabled() {
        return this.a.n();
    }

    public float getArrowHeight() {
        return this.a.d();
    }

    public float getArrowScale() {
        return this.a.e();
    }

    public float getArrowWidth() {
        return this.a.f();
    }

    public int getBackgroundColor() {
        return this.a.g();
    }

    public float getCenterRadius() {
        return this.a.h();
    }

    @NonNull
    public int[] getColorSchemeColors() {
        return this.a.i();
    }

    public float getEndTrim() {
        return this.a.j();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getProgressRotation() {
        return this.a.m();
    }

    public float getStartTrim() {
        return this.a.o();
    }

    @NonNull
    public Paint.Cap getStrokeCap() {
        return this.a.t();
    }

    public float getStrokeWidth() {
        return this.a.u();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f1030d.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.a.x(i2);
        invalidateSelf();
    }

    public void setArrowDimensions(float f, float f2) {
        this.a.y(f, f2);
        invalidateSelf();
    }

    public void setArrowEnabled(boolean z) {
        this.a.I(z);
        invalidateSelf();
    }

    public void setArrowScale(float f) {
        this.a.z(f);
        invalidateSelf();
    }

    public void setBackgroundColor(int i2) {
        this.a.A(i2);
        invalidateSelf();
    }

    public void setCenterRadius(float f) {
        this.a.B(f);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.a.D(colorFilter);
        invalidateSelf();
    }

    public void setColorSchemeColors(@NonNull int... iArr) {
        this.a.F(iArr);
        this.a.E(0);
        invalidateSelf();
    }

    public void setProgressRotation(float f) {
        this.a.H(f);
        invalidateSelf();
    }

    public void setStartEndTrim(float f, float f2) {
        this.a.J(f);
        this.a.G(f2);
        invalidateSelf();
    }

    public void setStrokeCap(@NonNull Paint.Cap cap) {
        this.a.K(cap);
        invalidateSelf();
    }

    public void setStrokeWidth(float f) {
        this.a.L(f);
        invalidateSelf();
    }

    public void setStyle(int i2) {
        float f;
        float f2;
        float f3;
        float f4;
        if (i2 == 0) {
            f = 11.0f;
            f2 = 3.0f;
            f3 = 12.0f;
            f4 = 6.0f;
        } else {
            f = 7.5f;
            f2 = 2.5f;
            f3 = 10.0f;
            f4 = 5.0f;
        }
        e(f, f2, f3, f4);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Animator animator;
        long j;
        this.f1030d.cancel();
        this.a.M();
        if (this.a.j() != this.a.o()) {
            this.f = true;
            animator = this.f1030d;
            j = 666;
        } else {
            this.a.E(0);
            this.a.w();
            animator = this.f1030d;
            j = 1332;
        }
        animator.setDuration(j);
        this.f1030d.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f1030d.cancel();
        d(0.0f);
        this.a.I(false);
        this.a.E(0);
        this.a.w();
        invalidateSelf();
    }
}
