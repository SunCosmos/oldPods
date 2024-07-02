package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.List;

/* loaded from: classes.dex */
public interface f {
    void a(@NonNull Animator.AnimatorListener animatorListener);

    @Nullable
    MotionSpec b();

    AnimatorSet c();

    void d();

    void e();

    List<Animator.AnimatorListener> f();

    void g(@Nullable ExtendedFloatingActionButton.OnChangedCallback onChangedCallback);

    boolean h();

    void i(@Nullable MotionSpec motionSpec);

    @AnimatorRes
    int j();

    void k(@NonNull Animator.AnimatorListener animatorListener);

    void l();

    void onAnimationStart(Animator animator);
}
