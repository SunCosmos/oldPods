package com.google.android.material.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public interface VisibilityAnimatorProvider {
    @Nullable
    Animator createAppear(@NonNull ViewGroup viewGroup, @NonNull View view);

    @Nullable
    Animator createDisappear(@NonNull ViewGroup viewGroup, @NonNull View view);
}
