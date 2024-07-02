package android.support.v4.view.animation;

import android.view.animation.Interpolator;

/* loaded from: lib/Mus.dex */
abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize;
    private final float[] mValues;

    /* JADX INFO: Access modifiers changed from: protected */
    public LookupTableInterpolator(float[] fArr) {
        this.mValues = fArr;
        this.mStepSize = 1.0f / (this.mValues.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f > 0.0f) {
            int min = Math.min((int) (f * (this.mValues.length - 1)), this.mValues.length - 2);
            return this.mValues[min] + (((f - (min * this.mStepSize)) / this.mStepSize) * (this.mValues[min + 1] - this.mValues[min]));
        }
        return 0.0f;
    }
}
