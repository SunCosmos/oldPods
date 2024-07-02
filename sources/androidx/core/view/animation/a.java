package androidx.core.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

/* loaded from: classes.dex */
class a implements Interpolator {
    private final float[] a;
    private final float[] b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(float f, float f2) {
        this(b(f, f2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(float f, float f2, float f3, float f4) {
        this(a(f, f2, f3, f4));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int i2 = ((int) (length / 0.002f)) + 1;
        this.a = new float[i2];
        this.b = new float[i2];
        float[] fArr = new float[2];
        for (int i3 = 0; i3 < i2; i3++) {
            pathMeasure.getPosTan((i3 * length) / (i2 - 1), fArr, null);
            this.a[i3] = fArr[0];
            this.b[i3] = fArr[1];
        }
    }

    private static Path a(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        return path;
    }

    private static Path b(float f, float f2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f, f2, 1.0f, 1.0f);
        return path;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int i2 = 0;
        int length = this.a.length - 1;
        while (length - i2 > 1) {
            int i3 = (i2 + length) / 2;
            if (f < this.a[i3]) {
                length = i3;
            } else {
                i2 = i3;
            }
        }
        float[] fArr = this.a;
        float f2 = fArr[length] - fArr[i2];
        if (f2 == 0.0f) {
            return this.b[i2];
        }
        float f3 = (f - fArr[i2]) / f2;
        float[] fArr2 = this.b;
        float f4 = fArr2[i2];
        return f4 + (f3 * (fArr2[length] - f4));
    }
}
