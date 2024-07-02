package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class StateListAnimator {
    private final ArrayList<b> a = new ArrayList<>();

    @Nullable
    private b b = null;

    /* renamed from: c, reason: collision with root package name */
    @Nullable
    ValueAnimator f1606c = null;

    /* renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f1607d = new a();

    /* loaded from: classes.dex */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            StateListAnimator stateListAnimator = StateListAnimator.this;
            if (stateListAnimator.f1606c == animator) {
                stateListAnimator.f1606c = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        final int[] a;
        final ValueAnimator b;

        b(int[] iArr, ValueAnimator valueAnimator) {
            this.a = iArr;
            this.b = valueAnimator;
        }
    }

    private void a() {
        ValueAnimator valueAnimator = this.f1606c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f1606c = null;
        }
    }

    private void b(@NonNull b bVar) {
        ValueAnimator valueAnimator = bVar.b;
        this.f1606c = valueAnimator;
        valueAnimator.start();
    }

    public void addState(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f1607d);
        this.a.add(bVar);
    }

    public void jumpToCurrentState() {
        ValueAnimator valueAnimator = this.f1606c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f1606c = null;
        }
    }

    public void setState(int[] iArr) {
        b bVar;
        int size = this.a.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                bVar = null;
                break;
            }
            bVar = this.a.get(i2);
            if (StateSet.stateSetMatches(bVar.a, iArr)) {
                break;
            } else {
                i2++;
            }
        }
        b bVar2 = this.b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            a();
        }
        this.b = bVar;
        if (bVar != null) {
            b(bVar);
        }
    }
}
