package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.transition.VisibilityAnimatorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
abstract class h<P extends VisibilityAnimatorProvider> extends Visibility {
    private final P L;

    @Nullable
    private VisibilityAnimatorProvider M;
    private final List<VisibilityAnimatorProvider> N = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    public h(P p, @Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.L = p;
        this.M = visibilityAnimatorProvider;
    }

    private static void K(List<Animator> list, @Nullable VisibilityAnimatorProvider visibilityAnimatorProvider, ViewGroup viewGroup, View view, boolean z) {
        if (visibilityAnimatorProvider == null) {
            return;
        }
        Animator createAppear = z ? visibilityAnimatorProvider.createAppear(viewGroup, view) : visibilityAnimatorProvider.createDisappear(viewGroup, view);
        if (createAppear != null) {
            list.add(createAppear);
        }
    }

    private Animator L(@NonNull ViewGroup viewGroup, @NonNull View view, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        K(arrayList, this.L, viewGroup, view, z);
        K(arrayList, this.M, viewGroup, view, z);
        Iterator<VisibilityAnimatorProvider> it = this.N.iterator();
        while (it.hasNext()) {
            K(arrayList, it.next(), viewGroup, view, z);
        }
        P(viewGroup.getContext(), z);
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    private void P(@NonNull Context context, boolean z) {
        j.r(this, context, N(z));
        j.s(this, context, O(z), M(z));
    }

    @NonNull
    TimeInterpolator M(boolean z) {
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    @AttrRes
    int N(boolean z) {
        return 0;
    }

    @AttrRes
    int O(boolean z) {
        return 0;
    }

    public void addAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.N.add(visibilityAnimatorProvider);
    }

    public void clearAdditionalAnimatorProvider() {
        this.N.clear();
    }

    @NonNull
    public P getPrimaryAnimatorProvider() {
        return this.L;
    }

    @Nullable
    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return this.M;
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return L(viewGroup, view, true);
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return L(viewGroup, view, false);
    }

    public boolean removeAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        return this.N.remove(visibilityAnimatorProvider);
    }

    public void setSecondaryAnimatorProvider(@Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        this.M = visibilityAnimatorProvider;
    }
}
