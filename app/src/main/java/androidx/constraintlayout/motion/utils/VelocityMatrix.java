package androidx.constraintlayout.motion.utils;

import androidx.constraintlayout.motion.widget.KeyCycleOscillator;
import androidx.constraintlayout.motion.widget.SplineSet;

/* loaded from: classes.dex */
public class VelocityMatrix {
    float a;
    float b;

    /* renamed from: c, reason: collision with root package name */
    float f292c;

    /* renamed from: d, reason: collision with root package name */
    float f293d;
    float e;
    float f;

    public void applyTransform(float f, float f2, int i2, int i3, float[] fArr) {
        float f3 = fArr[0];
        float f4 = fArr[1];
        float f5 = (f - 0.5f) * 2.0f;
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = f3 + this.f292c;
        float f8 = f4 + this.f293d;
        float f9 = f7 + (this.a * f5);
        float f10 = f8 + (this.b * f6);
        float radians = (float) Math.toRadians(this.f);
        float radians2 = (float) Math.toRadians(this.e);
        double d2 = (-i2) * f5;
        double d3 = radians;
        double sin = Math.sin(d3);
        Double.isNaN(d2);
        double d4 = d2 * sin;
        double d5 = i3 * f6;
        double cos = Math.cos(d3);
        Double.isNaN(d5);
        float f11 = f9 + (((float) (d4 - (cos * d5))) * radians2);
        double d6 = i2 * f5;
        double cos2 = Math.cos(d3);
        Double.isNaN(d6);
        double d7 = d6 * cos2;
        double sin2 = Math.sin(d3);
        Double.isNaN(d5);
        fArr[0] = f11;
        fArr[1] = f10 + (radians2 * ((float) (d7 - (d5 * sin2))));
    }

    public void clear() {
        this.e = 0.0f;
        this.f293d = 0.0f;
        this.f292c = 0.0f;
        this.b = 0.0f;
        this.a = 0.0f;
    }

    public void setRotationVelocity(KeyCycleOscillator keyCycleOscillator, float f) {
        if (keyCycleOscillator != null) {
            this.e = keyCycleOscillator.getSlope(f);
        }
    }

    public void setRotationVelocity(SplineSet splineSet, float f) {
        if (splineSet != null) {
            this.e = splineSet.getSlope(f);
            this.f = splineSet.get(f);
        }
    }

    public void setScaleVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f) {
        if (keyCycleOscillator == null && keyCycleOscillator2 == null) {
            return;
        }
        if (keyCycleOscillator == null) {
            this.a = keyCycleOscillator.getSlope(f);
        }
        if (keyCycleOscillator2 == null) {
            this.b = keyCycleOscillator2.getSlope(f);
        }
    }

    public void setScaleVelocity(SplineSet splineSet, SplineSet splineSet2, float f) {
        if (splineSet != null) {
            this.a = splineSet.getSlope(f);
        }
        if (splineSet2 != null) {
            this.b = splineSet2.getSlope(f);
        }
    }

    public void setTranslationVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f) {
        if (keyCycleOscillator != null) {
            this.f292c = keyCycleOscillator.getSlope(f);
        }
        if (keyCycleOscillator2 != null) {
            this.f293d = keyCycleOscillator2.getSlope(f);
        }
    }

    public void setTranslationVelocity(SplineSet splineSet, SplineSet splineSet2, float f) {
        if (splineSet != null) {
            this.f292c = splineSet.getSlope(f);
        }
        if (splineSet2 != null) {
            this.f293d = splineSet2.getSlope(f);
        }
    }
}
