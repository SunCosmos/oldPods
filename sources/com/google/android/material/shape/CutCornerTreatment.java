package com.google.android.material.shape;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class CutCornerTreatment extends CornerTreatment {
    float a;

    public CutCornerTreatment() {
        this.a = -1.0f;
    }

    @Deprecated
    public CutCornerTreatment(float f) {
        this.a = -1.0f;
        this.a = f;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(@NonNull ShapePath shapePath, float f, float f2, float f3) {
        shapePath.reset(0.0f, f3 * f2, 180.0f, 180.0f - f);
        double sin = Math.sin(Math.toRadians(f));
        double d2 = f3;
        Double.isNaN(d2);
        double d3 = f2;
        Double.isNaN(d3);
        double sin2 = Math.sin(Math.toRadians(90.0f - f));
        Double.isNaN(d2);
        Double.isNaN(d3);
        shapePath.lineTo((float) (sin * d2 * d3), (float) (sin2 * d2 * d3));
    }
}
