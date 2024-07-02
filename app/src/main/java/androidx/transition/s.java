package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Transition;

/* loaded from: classes.dex */
class s {

    /* loaded from: classes.dex */
    private static class a extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private final View a;
        private final View b;

        /* renamed from: c, reason: collision with root package name */
        private final int f1091c;

        /* renamed from: d, reason: collision with root package name */
        private final int f1092d;
        private int[] e;
        private float f;
        private float g;
        private final float h;

        /* renamed from: i, reason: collision with root package name */
        private final float f1093i;

        a(View view, View view2, int i2, int i3, float f, float f2) {
            this.b = view;
            this.a = view2;
            this.f1091c = i2 - Math.round(view.getTranslationX());
            this.f1092d = i3 - Math.round(view.getTranslationY());
            this.h = f;
            this.f1093i = f2;
            int i4 = R.id.transition_position;
            int[] iArr = (int[]) view2.getTag(i4);
            this.e = iArr;
            if (iArr != null) {
                view2.setTag(i4, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.e == null) {
                this.e = new int[2];
            }
            this.e[0] = Math.round(this.f1091c + this.b.getTranslationX());
            this.e[1] = Math.round(this.f1092d + this.b.getTranslationY());
            this.a.setTag(R.id.transition_position, this.e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            this.f = this.b.getTranslationX();
            this.g = this.b.getTranslationY();
            this.b.setTranslationX(this.h);
            this.b.setTranslationY(this.f1093i);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            this.b.setTranslationX(this.f);
            this.b.setTranslationY(this.g);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(@NonNull Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            this.b.setTranslationX(this.h);
            this.b.setTranslationY(this.f1093i);
            transition.removeListener(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(@NonNull Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(@NonNull Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Animator a(@NonNull View view, @NonNull TransitionValues transitionValues, int i2, int i3, float f, float f2, float f3, float f4, @Nullable TimeInterpolator timeInterpolator, @NonNull Transition transition) {
        float f5;
        float f6;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        if (((int[]) transitionValues.view.getTag(R.id.transition_position)) != null) {
            f5 = (r4[0] - i2) + translationX;
            f6 = (r4[1] - i3) + translationY;
        } else {
            f5 = f;
            f6 = f2;
        }
        int round = i2 + Math.round(f5 - translationX);
        int round2 = i3 + Math.round(f6 - translationY);
        view.setTranslationX(f5);
        view.setTranslationY(f6);
        if (f5 == f3 && f6 == f4) {
            return null;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f5, f3), PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f6, f4));
        a aVar = new a(view, transitionValues.view, round, round2, translationX, translationY);
        transition.addListener(aVar);
        ofPropertyValuesHolder.addListener(aVar);
        androidx.transition.a.a(ofPropertyValuesHolder, aVar);
        ofPropertyValuesHolder.setInterpolator(timeInterpolator);
        return ofPropertyValuesHolder;
    }
}
