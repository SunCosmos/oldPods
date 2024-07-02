package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public final class ScaleProvider implements VisibilityAnimatorProvider {
    private float a;
    private float b;

    /* renamed from: c, reason: collision with root package name */
    private float f1770c;

    /* renamed from: d, reason: collision with root package name */
    private float f1771d;
    private boolean e;
    private boolean f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends AnimatorListenerAdapter {
        final /* synthetic */ View a;
        final /* synthetic */ float b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f1772c;

        a(View view, float f, float f2) {
            this.a = view;
            this.b = f;
            this.f1772c = f2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.setScaleX(this.b);
            this.a.setScaleY(this.f1772c);
        }
    }

    public ScaleProvider() {
        this(true);
    }

    public ScaleProvider(boolean z) {
        this.a = 1.0f;
        this.b = 1.1f;
        this.f1770c = 0.8f;
        this.f1771d = 1.0f;
        this.f = true;
        this.e = z;
    }

    private static Animator a(View view, float f, float f2) {
        float scaleX = view.getScaleX();
        float scaleY = view.getScaleY();
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, scaleX * f, scaleX * f2), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, f * scaleY, f2 * scaleY));
        ofPropertyValuesHolder.addListener(new a(view, scaleX, scaleY));
        return ofPropertyValuesHolder;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    @Nullable
    public Animator createAppear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        float f;
        float f2;
        if (this.e) {
            f = this.f1770c;
            f2 = this.f1771d;
        } else {
            f = this.b;
            f2 = this.a;
        }
        return a(view, f, f2);
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    @Nullable
    public Animator createDisappear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        float f;
        float f2;
        if (!this.f) {
            return null;
        }
        if (this.e) {
            f = this.a;
            f2 = this.b;
        } else {
            f = this.f1771d;
            f2 = this.f1770c;
        }
        return a(view, f, f2);
    }

    public float getIncomingEndScale() {
        return this.f1771d;
    }

    public float getIncomingStartScale() {
        return this.f1770c;
    }

    public float getOutgoingEndScale() {
        return this.b;
    }

    public float getOutgoingStartScale() {
        return this.a;
    }

    public boolean isGrowing() {
        return this.e;
    }

    public boolean isScaleOnDisappear() {
        return this.f;
    }

    public void setGrowing(boolean z) {
        this.e = z;
    }

    public void setIncomingEndScale(float f) {
        this.f1771d = f;
    }

    public void setIncomingStartScale(float f) {
        this.f1770c = f;
    }

    public void setOutgoingEndScale(float f) {
        this.b = f;
    }

    public void setOutgoingStartScale(float f) {
        this.a = f;
    }

    public void setScaleOnDisappear(boolean z) {
        this.f = z;
    }
}
