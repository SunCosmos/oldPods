package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* loaded from: lib/Mus.dex */
public class Explode extends Visibility {
    private static final String PROPNAME_SCREEN_BOUNDS = "android:explode:screenBounds";
    private int[] mTempLoc;
    private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
    private static final TimeInterpolator sAccelerate = new AccelerateInterpolator();

    public Explode() {
        this.mTempLoc = new int[2];
        setPropagation(new CircularPropagation());
    }

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTempLoc = new int[2];
        setPropagation(new CircularPropagation());
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        view.getLocationOnScreen(this.mTempLoc);
        int i2 = this.mTempLoc[0];
        int i3 = this.mTempLoc[1];
        transitionValues.values.put(PROPNAME_SCREEN_BOUNDS, new Rect(i2, i3, i2 + view.getWidth(), i3 + view.getHeight()));
    }

    @Override // android.support.transition.Visibility, android.support.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        captureValues(transitionValues);
    }

    @Override // android.support.transition.Visibility, android.support.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        captureValues(transitionValues);
    }

    @Override // android.support.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues2.values.get(PROPNAME_SCREEN_BOUNDS);
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        calculateOut(viewGroup, rect, this.mTempLoc);
        return TranslationAnimationCreator.createAnimation(view, transitionValues2, rect.left, rect.top, translationX + this.mTempLoc[0], translationY + this.mTempLoc[1], translationX, translationY, sDecelerate);
    }

    @Override // android.support.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues.values.get(PROPNAME_SCREEN_BOUNDS);
        int i2 = rect.left;
        int i3 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        float f = translationX;
        float f2 = translationY;
        int[] iArr = (int[]) transitionValues.view.getTag(R.id.transition_position);
        if (iArr != null) {
            f += iArr[0] - rect.left;
            f2 += iArr[1] - rect.top;
            rect.offsetTo(iArr[0], iArr[1]);
        }
        calculateOut(viewGroup, rect, this.mTempLoc);
        return TranslationAnimationCreator.createAnimation(view, transitionValues, i2, i3, translationX, translationY, f + this.mTempLoc[0], f2 + this.mTempLoc[1], sAccelerate);
    }

    private void calculateOut(View view, Rect rect, int[] iArr) {
        int centerX;
        int centerY;
        view.getLocationOnScreen(this.mTempLoc);
        int i2 = this.mTempLoc[0];
        int i3 = this.mTempLoc[1];
        Rect epicenter = getEpicenter();
        if (epicenter == null) {
            centerX = i2 + (view.getWidth() / 2) + Math.round(view.getTranslationX());
            centerY = i3 + (view.getHeight() / 2) + Math.round(view.getTranslationY());
        } else {
            centerX = epicenter.centerX();
            centerY = epicenter.centerY();
        }
        float centerX2 = rect.centerX() - centerX;
        float centerY2 = rect.centerY() - centerY;
        if (centerX2 == 0.0f && centerY2 == 0.0f) {
            centerX2 = ((float) (Math.random() * 2.0d)) - 1.0f;
            centerY2 = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float calculateDistance = calculateDistance(centerX2, centerY2);
        float f = centerX2 / calculateDistance;
        float f2 = centerY2 / calculateDistance;
        float calculateMaxDistance = calculateMaxDistance(view, centerX - i2, centerY - i3);
        iArr[0] = Math.round(calculateMaxDistance * f);
        iArr[1] = Math.round(calculateMaxDistance * f2);
    }

    private static float calculateMaxDistance(View view, int i2, int i3) {
        return calculateDistance(Math.max(i2, view.getWidth() - i2), Math.max(i3, view.getHeight() - i3));
    }

    private static float calculateDistance(float f, float f2) {
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }
}
