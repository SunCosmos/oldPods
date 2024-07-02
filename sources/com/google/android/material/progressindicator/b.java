package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.color.MaterialColors;

/* loaded from: classes.dex */
public final class b extends e<ObjectAnimator> {
    private static final int[] l = {0, 1350, 2700, 4050};
    private static final int[] m = {667, 2017, 3367, 4717};
    private static final int[] n = {1000, 2350, 3700, 5050};
    private static final Property<b, Float> o = new c(Float.class, "animationFraction");
    private static final Property<b, Float> p = new d(Float.class, "completeEndFraction");

    /* renamed from: d */
    private ObjectAnimator f1634d;
    private ObjectAnimator e;
    private final FastOutSlowInInterpolator f;
    private final BaseProgressIndicatorSpec g;
    private int h;

    /* renamed from: i */
    private float f1635i;
    private float j;
    Animatable2Compat.AnimationCallback k;

    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            b bVar = b.this;
            bVar.h = (bVar.h + 4) % b.this.g.indicatorColors.length;
        }
    }

    /* renamed from: com.google.android.material.progressindicator.b$b */
    /* loaded from: classes.dex */
    public class C0057b extends AnimatorListenerAdapter {
        C0057b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            b.this.a();
            b bVar = b.this;
            bVar.k.onAnimationEnd(bVar.a);
        }
    }

    /* loaded from: classes.dex */
    static class c extends Property<b, Float> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(b bVar) {
            return Float.valueOf(bVar.o());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(b bVar, Float f) {
            bVar.t(f.floatValue());
        }
    }

    /* loaded from: classes.dex */
    static class d extends Property<b, Float> {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(b bVar) {
            return Float.valueOf(bVar.p());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(b bVar, Float f) {
            bVar.u(f.floatValue());
        }
    }

    public b(@NonNull CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(1);
        this.h = 0;
        this.k = null;
        this.g = circularProgressIndicatorSpec;
        this.f = new FastOutSlowInInterpolator();
    }

    public float o() {
        return this.f1635i;
    }

    public float p() {
        return this.j;
    }

    private void q() {
        if (this.f1634d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, o, 0.0f, 1.0f);
            this.f1634d = ofFloat;
            ofFloat.setDuration(5400L);
            this.f1634d.setInterpolator(null);
            this.f1634d.setRepeatCount(-1);
            this.f1634d.addListener(new a());
        }
        if (this.e == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, p, 0.0f, 1.0f);
            this.e = ofFloat2;
            ofFloat2.setDuration(333L);
            this.e.setInterpolator(this.f);
            this.e.addListener(new C0057b());
        }
    }

    private void r(int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            float b = b(i2, n[i3], 333);
            if (b >= 0.0f && b <= 1.0f) {
                int i4 = i3 + this.h;
                int[] iArr = this.g.indicatorColors;
                int length = i4 % iArr.length;
                int length2 = (length + 1) % iArr.length;
                int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(iArr[length], this.a.getAlpha());
                int compositeARGBWithAlpha2 = MaterialColors.compositeARGBWithAlpha(this.g.indicatorColors[length2], this.a.getAlpha());
                this.f1639c[0] = ArgbEvaluatorCompat.getInstance().evaluate(this.f.getInterpolation(b), Integer.valueOf(compositeARGBWithAlpha), Integer.valueOf(compositeARGBWithAlpha2)).intValue();
                return;
            }
        }
    }

    public void u(float f) {
        this.j = f;
    }

    private void v(int i2) {
        float[] fArr = this.b;
        float f = this.f1635i;
        fArr[0] = (f * 1520.0f) - 20.0f;
        fArr[1] = f * 1520.0f;
        for (int i3 = 0; i3 < 4; i3++) {
            float b = b(i2, l[i3], 667);
            float[] fArr2 = this.b;
            fArr2[1] = fArr2[1] + (this.f.getInterpolation(b) * 250.0f);
            float b2 = b(i2, m[i3], 667);
            float[] fArr3 = this.b;
            fArr3[0] = fArr3[0] + (this.f.getInterpolation(b2) * 250.0f);
        }
        float[] fArr4 = this.b;
        fArr4[0] = fArr4[0] + ((fArr4[1] - fArr4[0]) * this.j);
        fArr4[0] = fArr4[0] / 360.0f;
        fArr4[1] = fArr4[1] / 360.0f;
    }

    @Override // com.google.android.material.progressindicator.e
    public void a() {
        ObjectAnimator objectAnimator = this.f1634d;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void c() {
        s();
    }

    @Override // com.google.android.material.progressindicator.e
    public void d(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        this.k = animationCallback;
    }

    @Override // com.google.android.material.progressindicator.e
    public void f() {
        if (this.e.isRunning()) {
            return;
        }
        if (this.a.isVisible()) {
            this.e.start();
        } else {
            a();
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void g() {
        q();
        s();
        this.f1634d.start();
    }

    @Override // com.google.android.material.progressindicator.e
    public void h() {
        this.k = null;
    }

    @VisibleForTesting
    void s() {
        this.h = 0;
        this.f1639c[0] = MaterialColors.compositeARGBWithAlpha(this.g.indicatorColors[0], this.a.getAlpha());
        this.j = 0.0f;
    }

    @VisibleForTesting
    void t(float f) {
        this.f1635i = f;
        int i2 = (int) (f * 5400.0f);
        v(i2);
        r(i2);
        this.a.invalidateSelf();
    }
}
