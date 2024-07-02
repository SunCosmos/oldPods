package androidx.transition;

import android.animation.TypeEvaluator;

/* loaded from: classes.dex */
class c implements TypeEvaluator<float[]> {
    private float[] a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(float[] fArr) {
        this.a = fArr;
    }

    @Override // android.animation.TypeEvaluator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public float[] evaluate(float f, float[] fArr, float[] fArr2) {
        float[] fArr3 = this.a;
        if (fArr3 == null) {
            fArr3 = new float[fArr.length];
        }
        for (int i2 = 0; i2 < fArr3.length; i2++) {
            float f2 = fArr[i2];
            fArr3[i2] = f2 + ((fArr2[i2] - f2) * f);
        }
        return fArr3;
    }
}
