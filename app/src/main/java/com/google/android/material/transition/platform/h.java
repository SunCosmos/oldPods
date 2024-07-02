package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.transition.platform.VisibilityAnimatorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
/* loaded from: classes.dex */
abstract class h<P extends VisibilityAnimatorProvider> extends Visibility {
    private final P a;

    @Nullable
    private VisibilityAnimatorProvider b;

    /* renamed from: c, reason: collision with root package name */
    private final List<VisibilityAnimatorProvider> f1812c = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    public h(P p, @Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.a = p;
        this.b = visibilityAnimatorProvider;
    }

    private static void a(List<Animator> list, @Nullable VisibilityAnimatorProvider visibilityAnimatorProvider, ViewGroup viewGroup, View view, boolean z) {
        if (visibilityAnimatorProvider == null) {
            return;
        }
        Animator createAppear = z ? visibilityAnimatorProvider.createAppear(viewGroup, view) : visibilityAnimatorProvider.createDisappear(viewGroup, view);
        if (createAppear != null) {
            list.add(createAppear);
        }
    }

    private Animator b(@NonNull ViewGroup viewGroup, @NonNull View view, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        a(arrayList, this.a, viewGroup, view, z);
        a(arrayList, this.b, viewGroup, view, z);
        Iterator<VisibilityAnimatorProvider> it = this.f1812c.iterator();
        while (it.hasNext()) {
            a(arrayList, it.next(), viewGroup, view, z);
        }
        f(viewGroup.getContext(), z);
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    private void f(@NonNull Context context, boolean z) {
        j.s(this, context, d(z));
        j.t(this, context, e(z), c(z));
    }

    public void addAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.f1812c.add(visibilityAnimatorProvider);
    }

    @NonNull
    TimeInterpolator c(boolean z) {
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public void clearAdditionalAnimatorProvider() {
        this.f1812c.clear();
    }

    @AttrRes
    int d(boolean z) {
        return 0;
    }

    @AttrRes
    int e(boolean z) {
        return 0;
    }

    @NonNull
    public P getPrimaryAnimatorProvider() {
        return this.a;
    }

    @Nullable
    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return this.b;
    }

    @Override // android.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return b(viewGroup, view, true);
    }

    @Override // android.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return b(viewGroup, view, false);
    }

    public boolean removeAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        return this.f1812c.remove(visibilityAnimatorProvider);
    }

    public void setSecondaryAnimatorProvider(@Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.b = visibilityAnimatorProvider;
    }
}
