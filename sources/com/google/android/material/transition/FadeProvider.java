package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public final class FadeProvider implements VisibilityAnimatorProvider {
    private float a = 1.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ View a;
        final /* synthetic */ float b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f1759c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ float f1760d;
        final /* synthetic */ float e;

        a(View view, float f, float f2, float f3, float f4) {
            this.a = view;
            this.b = f;
            this.f1759c = f2;
            this.f1760d = f3;
            this.e = f4;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.setAlpha(j.n(this.b, this.f1759c, this.f1760d, this.e, ((Float) valueAnimator.getAnimatedValue()).floatValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends AnimatorListenerAdapter {
        final /* synthetic */ View a;
        final /* synthetic */ float b;

        b(View view, float f) {
            this.a = view;
            this.b = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.setAlpha(this.b);
        }
    }

    private static Animator a(View view, float f, float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @FloatRange(from = 0.0d, to = 1.0d) float f4, float f5) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new a(view, f, f2, f3, f4));
        ofFloat.addListener(new b(view, f5));
        return ofFloat;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    @Nullable
    public Animator createAppear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        float alpha = view.getAlpha() == 0.0f ? 1.0f : view.getAlpha();
        return a(view, 0.0f, alpha, 0.0f, this.a, alpha);
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    @Nullable
    public Animator createDisappear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        float alpha = view.getAlpha() == 0.0f ? 1.0f : view.getAlpha();
        return a(view, alpha, 0.0f, 0.0f, 1.0f, alpha);
    }

    public float getIncomingEndThreshold() {
        return this.a;
    }

    public void setIncomingEndThreshold(float f) {
        this.a = f;
    }
}
