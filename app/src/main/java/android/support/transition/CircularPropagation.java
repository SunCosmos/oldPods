package android.support.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

/* loaded from: lib/Mus.dex */
public class CircularPropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;

    public void setPropagationSpeed(float f) {
        if (f == 0.0f) {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        }
        this.mPropagationSpeed = f;
    }

    @Override // android.support.transition.TransitionPropagation
    public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        TransitionValues transitionValues3;
        int round;
        int round2;
        if (transitionValues == null && transitionValues2 == null) {
            return 0L;
        }
        int i2 = 1;
        if (transitionValues2 == null || getViewVisibility(transitionValues) == 0) {
            transitionValues3 = transitionValues;
            i2 = -1;
        } else {
            transitionValues3 = transitionValues2;
        }
        int viewX = getViewX(transitionValues3);
        int viewY = getViewY(transitionValues3);
        Rect epicenter = transition.getEpicenter();
        if (epicenter != null) {
            round = epicenter.centerX();
            round2 = epicenter.centerY();
        } else {
            viewGroup.getLocationOnScreen(new int[2]);
            round = Math.round(r0[0] + (viewGroup.getWidth() / 2) + viewGroup.getTranslationX());
            round2 = Math.round(r0[1] + (viewGroup.getHeight() / 2) + viewGroup.getTranslationY());
        }
        float distance = distance(viewX, viewY, round, round2) / distance(0.0f, 0.0f, viewGroup.getWidth(), viewGroup.getHeight());
        long duration = transition.getDuration();
        if (duration < 0) {
            duration = 300;
        }
        return Math.round((((float) (duration * i2)) / this.mPropagationSpeed) * distance);
    }

    private static float distance(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        return (float) Math.sqrt((f5 * f5) + (f6 * f6));
    }
}
