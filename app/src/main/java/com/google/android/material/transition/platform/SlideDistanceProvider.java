package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresApi(21)
/* loaded from: classes.dex */
public final class SlideDistanceProvider implements VisibilityAnimatorProvider {
    private int a;

    @Px
    private int b = -1;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface GravityFlag {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends AnimatorListenerAdapter {
        final /* synthetic */ View a;
        final /* synthetic */ float b;

        a(View view, float f) {
            this.a = view;
            this.b = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.setTranslationX(this.b);
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
            this.a.setTranslationY(this.b);
        }
    }

    public SlideDistanceProvider(int i2) {
        this.a = i2;
    }

    private static Animator a(View view, View view2, int i2, @Px int i3) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i2 == 3) {
            return c(view2, i3 + translationX, translationX, translationX);
        }
        if (i2 == 5) {
            return c(view2, translationX - i3, translationX, translationX);
        }
        if (i2 == 48) {
            return d(view2, translationY - i3, translationY, translationY);
        }
        if (i2 == 80) {
            return d(view2, i3 + translationY, translationY, translationY);
        }
        if (i2 == 8388611) {
            return c(view2, f(view) ? i3 + translationX : translationX - i3, translationX, translationX);
        }
        if (i2 == 8388613) {
            return c(view2, f(view) ? translationX - i3 : i3 + translationX, translationX, translationX);
        }
        throw new IllegalArgumentException("Invalid slide direction: " + i2);
    }

    private static Animator b(View view, View view2, int i2, @Px int i3) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i2 == 3) {
            return c(view2, translationX, translationX - i3, translationX);
        }
        if (i2 == 5) {
            return c(view2, translationX, i3 + translationX, translationX);
        }
        if (i2 == 48) {
            return d(view2, translationY, i3 + translationY, translationY);
        }
        if (i2 == 80) {
            return d(view2, translationY, translationY - i3, translationY);
        }
        if (i2 == 8388611) {
            return c(view2, translationX, f(view) ? translationX - i3 : i3 + translationX, translationX);
        }
        if (i2 == 8388613) {
            return c(view2, translationX, f(view) ? i3 + translationX : translationX - i3, translationX);
        }
        throw new IllegalArgumentException("Invalid slide direction: " + i2);
    }

    private static Animator c(View view, float f, float f2, float f3) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f, f2));
        ofPropertyValuesHolder.addListener(new a(view, f3));
        return ofPropertyValuesHolder;
    }

    private static Animator d(View view, float f, float f2, float f3) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f, f2));
        ofPropertyValuesHolder.addListener(new b(view, f3));
        return ofPropertyValuesHolder;
    }

    private int e(Context context) {
        int i2 = this.b;
        return i2 != -1 ? i2 : context.getResources().getDimensionPixelSize(R.dimen.mtrl_transition_shared_axis_slide_distance);
    }

    private static boolean f(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    @Nullable
    public Animator createAppear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        return a(viewGroup, view, this.a, e(view.getContext()));
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    @Nullable
    public Animator createDisappear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        return b(viewGroup, view, this.a, e(view.getContext()));
    }

    @Px
    public int getSlideDistance() {
        return this.b;
    }

    public int getSlideEdge() {
        return this.a;
    }

    public void setSlideDistance(@Px int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Slide distance must be positive. If attempting to reverse the direction of the slide, use setSlideEdge(int) instead.");
        }
        this.b = i2;
    }

    public void setSlideEdge(int i2) {
        this.a = i2;
    }
}
