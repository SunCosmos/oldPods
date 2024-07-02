package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class ChangeClipBounds extends Transition {
    private static final String[] J = {"android:clipBounds:clip"};

    /* loaded from: classes.dex */
    class a extends AnimatorListenerAdapter {
        final /* synthetic */ View a;

        a(ChangeClipBounds changeClipBounds, View view) {
            this.a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ViewCompat.setClipBounds(this.a, null);
        }
    }

    public ChangeClipBounds() {
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void I(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.getVisibility() == 8) {
            return;
        }
        Rect clipBounds = ViewCompat.getClipBounds(view);
        transitionValues.values.put("android:clipBounds:clip", clipBounds);
        if (clipBounds == null) {
            transitionValues.values.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
        }
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        I(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        I(transitionValues);
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ObjectAnimator objectAnimator = null;
        if (transitionValues != null && transitionValues2 != null && transitionValues.values.containsKey("android:clipBounds:clip") && transitionValues2.values.containsKey("android:clipBounds:clip")) {
            Rect rect = (Rect) transitionValues.values.get("android:clipBounds:clip");
            Rect rect2 = (Rect) transitionValues2.values.get("android:clipBounds:clip");
            boolean z = rect2 == null;
            if (rect == null && rect2 == null) {
                return null;
            }
            if (rect == null) {
                rect = (Rect) transitionValues.values.get("android:clipBounds:bounds");
            } else if (rect2 == null) {
                rect2 = (Rect) transitionValues2.values.get("android:clipBounds:bounds");
            }
            if (rect.equals(rect2)) {
                return null;
            }
            ViewCompat.setClipBounds(transitionValues2.view, rect);
            objectAnimator = ObjectAnimator.ofObject(transitionValues2.view, (Property<View, V>) b0.f1073c, (TypeEvaluator) new n(new Rect()), (Object[]) new Rect[]{rect, rect2});
            if (z) {
                objectAnimator.addListener(new a(this, transitionValues2.view));
            }
        }
        return objectAnimator;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return J;
    }
}
