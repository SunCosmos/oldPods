package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
/* loaded from: lib/Mus.dex */
class AnimatorUtilsApi19 implements AnimatorUtilsImpl {
    @Override // android.support.transition.AnimatorUtilsImpl
    public void addPauseListener(@NonNull Animator animator, @NonNull AnimatorListenerAdapter animatorListenerAdapter) {
        animator.addPauseListener(animatorListenerAdapter);
    }

    @Override // android.support.transition.AnimatorUtilsImpl
    public void pause(@NonNull Animator animator) {
        animator.pause();
    }

    @Override // android.support.transition.AnimatorUtilsImpl
    public void resume(@NonNull Animator animator) {
        animator.resume();
    }
}
