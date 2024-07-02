package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Property;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class c extends Drawable implements Animatable2Compat {
    private static final Property<c, Float> o = new C0058c(Float.class, "growFraction");
    final Context a;
    final BaseProgressIndicatorSpec b;

    /* renamed from: d, reason: collision with root package name */
    private ValueAnimator f1637d;
    private ValueAnimator e;
    private boolean f;
    private boolean g;
    private float h;

    /* renamed from: i, reason: collision with root package name */
    private List<Animatable2Compat.AnimationCallback> f1638i;
    private Animatable2Compat.AnimationCallback j;
    private boolean k;
    private float l;
    private int n;
    final Paint m = new Paint();

    /* renamed from: c, reason: collision with root package name */
    AnimatorDurationScaleProvider f1636c = new AnimatorDurationScaleProvider();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            c.this.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            c.super.setVisible(false, false);
            c.this.d();
        }
    }

    /* renamed from: com.google.android.material.progressindicator.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0058c extends Property<c, Float> {
        C0058c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(c cVar) {
            return Float.valueOf(cVar.g());
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(c cVar, Float f) {
            cVar.i(f.floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(@NonNull Context context, @NonNull BaseProgressIndicatorSpec baseProgressIndicatorSpec) {
        this.a = context;
        this.b = baseProgressIndicatorSpec;
        setAlpha(255);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Animatable2Compat.AnimationCallback animationCallback = this.j;
        if (animationCallback != null) {
            animationCallback.onAnimationEnd(this);
        }
        List<Animatable2Compat.AnimationCallback> list = this.f1638i;
        if (list == null || this.k) {
            return;
        }
        Iterator<Animatable2Compat.AnimationCallback> it = list.iterator();
        while (it.hasNext()) {
            it.next().onAnimationEnd(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Animatable2Compat.AnimationCallback animationCallback = this.j;
        if (animationCallback != null) {
            animationCallback.onAnimationStart(this);
        }
        List<Animatable2Compat.AnimationCallback> list = this.f1638i;
        if (list == null || this.k) {
            return;
        }
        Iterator<Animatable2Compat.AnimationCallback> it = list.iterator();
        while (it.hasNext()) {
            it.next().onAnimationStart(this);
        }
    }

    private void f(@NonNull ValueAnimator... valueAnimatorArr) {
        boolean z = this.k;
        this.k = true;
        for (ValueAnimator valueAnimator : valueAnimatorArr) {
            valueAnimator.end();
        }
        this.k = z;
    }

    private void h() {
        if (this.f1637d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, o, 0.0f, 1.0f);
            this.f1637d = ofFloat;
            ofFloat.setDuration(500L);
            this.f1637d.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            k(this.f1637d);
        }
        if (this.e == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, o, 1.0f, 0.0f);
            this.e = ofFloat2;
            ofFloat2.setDuration(500L);
            this.e.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            j(this.e);
        }
    }

    private void j(@NonNull ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.e;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
        }
        this.e = valueAnimator;
        valueAnimator.addListener(new b());
    }

    private void k(@NonNull ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.f1637d;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
        }
        this.f1637d = valueAnimator;
        valueAnimator.addListener(new a());
    }

    public void clearAnimationCallbacks() {
        this.f1638i.clear();
        this.f1638i = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float g() {
        if (this.b.isShowAnimationEnabled() || this.b.isHideAnimationEnabled()) {
            return (this.g || this.f) ? this.h : this.l;
        }
        return 1.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.n;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public boolean hideNow() {
        return setVisible(false, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.l != f) {
            this.l = f;
            invalidateSelf();
        }
    }

    public boolean isHiding() {
        ValueAnimator valueAnimator = this.e;
        return (valueAnimator != null && valueAnimator.isRunning()) || this.g;
    }

    public boolean isRunning() {
        return isShowing() || isHiding();
    }

    public boolean isShowing() {
        ValueAnimator valueAnimator = this.f1637d;
        return (valueAnimator != null && valueAnimator.isRunning()) || this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean l(boolean z, boolean z2, boolean z3) {
        h();
        if (!isVisible() && !z) {
            return false;
        }
        ValueAnimator valueAnimator = z ? this.f1637d : this.e;
        if (!z3) {
            if (valueAnimator.isRunning()) {
                valueAnimator.end();
            } else {
                f(valueAnimator);
            }
            return super.setVisible(z, false);
        }
        if (z3 && valueAnimator.isRunning()) {
            return false;
        }
        boolean z4 = !z || super.setVisible(z, false);
        if (!(z ? this.b.isShowAnimationEnabled() : this.b.isHideAnimationEnabled())) {
            f(valueAnimator);
            return z4;
        }
        if (z2 || Build.VERSION.SDK_INT < 19 || !valueAnimator.isPaused()) {
            valueAnimator.start();
        } else {
            valueAnimator.resume();
        }
        return z4;
    }

    public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (this.f1638i == null) {
            this.f1638i = new ArrayList();
        }
        if (this.f1638i.contains(animationCallback)) {
            return;
        }
        this.f1638i.add(animationCallback);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.n = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.m.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return setVisible(z, z2, true);
    }

    public boolean setVisible(boolean z, boolean z2, boolean z3) {
        return l(z, z2, z3 && this.f1636c.getSystemAnimatorDurationScale(this.a.getContentResolver()) > 0.0f);
    }

    public void start() {
        l(true, true, false);
    }

    public void stop() {
        l(false, true, false);
    }

    public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        List<Animatable2Compat.AnimationCallback> list = this.f1638i;
        if (list == null || !list.contains(animationCallback)) {
            return false;
        }
        this.f1638i.remove(animationCallback);
        if (!this.f1638i.isEmpty()) {
            return true;
        }
        this.f1638i = null;
        return true;
    }
}
