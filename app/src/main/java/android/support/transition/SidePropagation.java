package android.support.transition;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: lib/Mus.dex */
public class SidePropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;
    private int mSide = 80;

    public void setSide(int i2) {
        this.mSide = i2;
    }

    public void setPropagationSpeed(float f) {
        if (f == 0.0f) {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        }
        this.mPropagationSpeed = f;
    }

    @Override // android.support.transition.TransitionPropagation
    public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        TransitionValues transitionValues3;
        int i2;
        int i3;
        if (transitionValues == null && transitionValues2 == null) {
            return 0L;
        }
        int i4 = 1;
        Rect epicenter = transition.getEpicenter();
        if (transitionValues2 == null || getViewVisibility(transitionValues) == 0) {
            transitionValues3 = transitionValues;
            i4 = -1;
        } else {
            transitionValues3 = transitionValues2;
        }
        int viewX = getViewX(transitionValues3);
        int viewY = getViewY(transitionValues3);
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        int round = iArr[0] + Math.round(viewGroup.getTranslationX());
        int round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
        int width = round + viewGroup.getWidth();
        int height = round2 + viewGroup.getHeight();
        if (epicenter != null) {
            i2 = epicenter.centerX();
            i3 = epicenter.centerY();
        } else {
            i2 = (round + width) / 2;
            i3 = (round2 + height) / 2;
        }
        float distance = distance(viewGroup, viewX, viewY, i2, i3, round, round2, width, height) / getMaxDistance(viewGroup);
        long duration = transition.getDuration();
        if (duration < 0) {
            duration = 300;
        }
        return Math.round((((float) (duration * i4)) / this.mPropagationSpeed) * distance);
    }

    private int distance(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        if (this.mSide == 8388611) {
            i10 = ViewCompat.getLayoutDirection(view) == 1 ? 5 : 3;
        } else if (this.mSide == 8388613) {
            i10 = ViewCompat.getLayoutDirection(view) == 1 ? 3 : 5;
        } else {
            i10 = this.mSide;
        }
        int i11 = 0;
        switch (i10) {
            case 3:
                i11 = (i8 - i2) + Math.abs(i5 - i3);
                break;
            case 5:
                i11 = (i2 - i6) + Math.abs(i5 - i3);
                break;
            case 48:
                i11 = (i9 - i3) + Math.abs(i4 - i2);
                break;
            case 80:
                i11 = (i3 - i7) + Math.abs(i4 - i2);
                break;
        }
        return i11;
    }

    private int getMaxDistance(ViewGroup viewGroup) {
        switch (this.mSide) {
            case 3:
            case 5:
            case 8388611:
            case 8388613:
                return viewGroup.getWidth();
            default:
                return viewGroup.getHeight();
        }
    }
}
