package com.google.android.material.shape;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public final class MarkerEdgeTreatment extends EdgeTreatment {
    private final float a;

    public MarkerEdgeTreatment(float f) {
        this.a = f - 0.001f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.shape.EdgeTreatment
    public boolean a() {
        return true;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, @NonNull ShapePath shapePath) {
        double d2 = this.a;
        double sqrt = Math.sqrt(2.0d);
        Double.isNaN(d2);
        float f4 = (float) ((d2 * sqrt) / 2.0d);
        float sqrt2 = (float) Math.sqrt(Math.pow(this.a, 2.0d) - Math.pow(f4, 2.0d));
        double d3 = this.a;
        double sqrt3 = Math.sqrt(2.0d);
        Double.isNaN(d3);
        double d4 = d3 * sqrt3;
        double d5 = this.a;
        Double.isNaN(d5);
        shapePath.reset(f2 - f4, ((float) (-(d4 - d5))) + sqrt2);
        double d6 = this.a;
        double sqrt4 = Math.sqrt(2.0d);
        Double.isNaN(d6);
        double d7 = d6 * sqrt4;
        double d8 = this.a;
        Double.isNaN(d8);
        shapePath.lineTo(f2, (float) (-(d7 - d8)));
        double d9 = this.a;
        double sqrt5 = Math.sqrt(2.0d);
        Double.isNaN(d9);
        double d10 = d9 * sqrt5;
        double d11 = this.a;
        Double.isNaN(d11);
        shapePath.lineTo(f2 + f4, ((float) (-(d10 - d11))) + sqrt2);
    }
}
