package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.annotation.NonNull;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

/* loaded from: classes.dex */
public abstract class e<T extends Animator> {
    protected IndeterminateDrawable a;
    protected final float[] b;

    /* renamed from: c */
    protected final int[] f1639c;

    public e(int i2) {
        this.b = new float[i2 * 2];
        this.f1639c = new int[i2];
    }

    public abstract void a();

    public float b(int i2, int i3, int i4) {
        return (i2 - i3) / i4;
    }

    public abstract void c();

    public abstract void d(@NonNull Animatable2Compat.AnimationCallback animationCallback);

    public void e(@NonNull IndeterminateDrawable indeterminateDrawable) {
        this.a = indeterminateDrawable;
    }

    public abstract void f();

    public abstract void g();

    public abstract void h();
}
