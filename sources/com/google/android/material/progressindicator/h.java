package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class h extends e<ObjectAnimator> {
    private static final int[] l = {533, 567, 850, 750};
    private static final int[] m = {1267, 1000, 333, 0};
    private static final Property<h, Float> n = new b(Float.class, "animationFraction");

    /* renamed from: d */
    private ObjectAnimator f1644d;
    private final Interpolator[] e;
    private final BaseProgressIndicatorSpec f;
    private int g;
    private boolean h;

    /* renamed from: i */
    private float f1645i;
    private boolean j;
    Animatable2Compat.AnimationCallback k;

    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (h.this.j) {
                h.this.f1644d.setRepeatCount(-1);
                h hVar = h.this;
                hVar.k.onAnimationEnd(hVar.a);
                h.this.j = false;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            h hVar = h.this;
            hVar.g = (hVar.g + 1) % h.this.f.indicatorColors.length;
            h.this.h = true;
        }
    }

    /* loaded from: classes.dex */
    static class b extends Property<h, Float> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(h hVar) {
            return Float.valueOf(hVar.q());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(h hVar, Float f) {
            hVar.u(f.floatValue());
        }
    }

    public h(@NonNull Context context, @NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.g = 0;
        this.k = null;
        this.f = linearProgressIndicatorSpec;
        this.e = new Interpolator[]{AnimationUtilsCompat.loadInterpolator(context, R.animator.linear_indeterminate_line1_head_interpolator), AnimationUtilsCompat.loadInterpolator(context, R.animator.linear_indeterminate_line1_tail_interpolator), AnimationUtilsCompat.loadInterpolator(context, R.animator.linear_indeterminate_line2_head_interpolator), AnimationUtilsCompat.loadInterpolator(context, R.animator.linear_indeterminate_line2_tail_interpolator)};
    }

    public float q() {
        return this.f1645i;
    }

    private void r() {
        if (this.f1644d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, n, 0.0f, 1.0f);
            this.f1644d = ofFloat;
            ofFloat.setDuration(1800L);
            this.f1644d.setInterpolator(null);
            this.f1644d.setRepeatCount(-1);
            this.f1644d.addListener(new a());
        }
    }

    private void s() {
        if (this.h) {
            Arrays.fill(this.f1639c, MaterialColors.compositeARGBWithAlpha(this.f.indicatorColors[this.g], this.a.getAlpha()));
            this.h = false;
        }
    }

    private void v(int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            this.b[i3] = Math.max(0.0f, Math.min(1.0f, this.e[i3].getInterpolation(b(i2, m[i3], l[i3]))));
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void a() {
        ObjectAnimator objectAnimator = this.f1644d;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void c() {
        t();
    }

    @Override // com.google.android.material.progressindicator.e
    public void d(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        this.k = animationCallback;
    }

    @Override // com.google.android.material.progressindicator.e
    public void f() {
        if (!this.a.isVisible()) {
            a();
        } else {
            this.j = true;
            this.f1644d.setRepeatCount(0);
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void g() {
        r();
        t();
        this.f1644d.start();
    }

    @Override // com.google.android.material.progressindicator.e
    public void h() {
        this.k = null;
    }

    @VisibleForTesting
    void t() {
        this.g = 0;
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(this.f.indicatorColors[0], this.a.getAlpha());
        int[] iArr = this.f1639c;
        iArr[0] = compositeARGBWithAlpha;
        iArr[1] = compositeARGBWithAlpha;
    }

    @VisibleForTesting
    void u(float f) {
        this.f1645i = f;
        v((int) (f * 1800.0f));
        s();
        this.a.invalidateSelf();
    }
}
