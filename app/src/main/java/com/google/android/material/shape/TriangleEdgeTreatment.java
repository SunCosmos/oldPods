package com.google.android.material.shape;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class TriangleEdgeTreatment extends EdgeTreatment {
    private final float a;
    private final boolean b;

    public TriangleEdgeTreatment(float f, boolean z) {
        this.a = f;
        this.b = z;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, @NonNull ShapePath shapePath) {
        shapePath.lineTo(f2 - (this.a * f3), 0.0f);
        shapePath.lineTo(f2, (this.b ? this.a : -this.a) * f3);
        shapePath.lineTo(f2 + (this.a * f3), 0.0f);
        shapePath.lineTo(f, 0.0f);
    }
}
