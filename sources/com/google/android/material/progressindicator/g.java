package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.color.MaterialColors;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class g extends e<ObjectAnimator> {
    private static final Property<g, Float> j = new b(Float.class, "animationFraction");

    /* renamed from: d */
    private ObjectAnimator f1642d;
    private FastOutSlowInInterpolator e;
    private final BaseProgressIndicatorSpec f;
    private int g;
    private boolean h;

    /* renamed from: i */
    private float f1643i;

    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            g gVar = g.this;
            gVar.g = (gVar.g + 1) % g.this.f.indicatorColors.length;
            g.this.h = true;
        }
    }

    /* loaded from: classes.dex */
    static class b extends Property<g, Float> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(g gVar) {
            return Float.valueOf(gVar.n());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(g gVar, Float f) {
            gVar.r(f.floatValue());
        }
    }

    public g(@NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.g = 1;
        this.f = linearProgressIndicatorSpec;
        this.e = new FastOutSlowInInterpolator();
    }

    public float n() {
        return this.f1643i;
    }

    private void o() {
        if (this.f1642d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, j, 0.0f, 1.0f);
            this.f1642d = ofFloat;
            ofFloat.setDuration(333L);
            this.f1642d.setInterpolator(null);
            this.f1642d.setRepeatCount(-1);
            this.f1642d.addListener(new a());
        }
    }

    private void p() {
        if (!this.h || this.b[3] >= 1.0f) {
            return;
        }
        int[] iArr = this.f1639c;
        iArr[2] = iArr[1];
        iArr[1] = iArr[0];
        iArr[0] = MaterialColors.compositeARGBWithAlpha(this.f.indicatorColors[this.g], this.a.getAlpha());
        this.h = false;
    }

    private void s(int i2) {
        this.b[0] = 0.0f;
        float b2 = b(i2, 0, 667);
        float[] fArr = this.b;
        float interpolation = this.e.getInterpolation(b2);
        fArr[2] = interpolation;
        fArr[1] = interpolation;
        float[] fArr2 = this.b;
        float interpolation2 = this.e.getInterpolation(b2 + 0.49925038f);
        fArr2[4] = interpolation2;
        fArr2[3] = interpolation2;
        this.b[5] = 1.0f;
    }

    @Override // com.google.android.material.progressindicator.e
    public void a() {
        ObjectAnimator objectAnimator = this.f1642d;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void c() {
        q();
    }

    @Override // com.google.android.material.progressindicator.e
    public void d(@Nullable Animatable2Compat.AnimationCallback animationCallback) {
    }

    @Override // com.google.android.material.progressindicator.e
    public void f() {
    }

    @Override // com.google.android.material.progressindicator.e
    public void g() {
        o();
        q();
        this.f1642d.start();
    }

    @Override // com.google.android.material.progressindicator.e
    public void h() {
    }

    @VisibleForTesting
    void q() {
        this.h = true;
        this.g = 1;
        Arrays.fill(this.f1639c, MaterialColors.compositeARGBWithAlpha(this.f.indicatorColors[0], this.a.getAlpha()));
    }

    @VisibleForTesting
    void r(float f) {
        this.f1643i = f;
        s((int) (f * 333.0f));
        p();
        this.a.invalidateSelf();
    }
}
