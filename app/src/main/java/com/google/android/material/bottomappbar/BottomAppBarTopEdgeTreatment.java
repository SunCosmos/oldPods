package com.google.android.material.bottomappbar;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

/* loaded from: classes.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private float a;
    private float b;

    /* renamed from: c, reason: collision with root package name */
    private float f1488c;

    /* renamed from: d, reason: collision with root package name */
    private float f1489d;
    private float e;
    private float f = -1.0f;

    public BottomAppBarTopEdgeTreatment(float f, float f2, float f3) {
        this.b = f;
        this.a = f2;
        e(f3);
        this.e = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float b() {
        return this.f1489d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float d() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@FloatRange(from = 0.0d) float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f1489d = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(float f) {
        this.b = f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(float f) {
        this.a = f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, @NonNull ShapePath shapePath) {
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12 = this.f1488c;
        if (f12 == 0.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f13 = ((this.b * 2.0f) + f12) / 2.0f;
        float f14 = f3 * this.a;
        float f15 = f2 + this.e;
        float f16 = (this.f1489d * f3) + ((1.0f - f3) * f13);
        if (f16 / f13 >= 1.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f17 = this.f;
        float f18 = f17 * f3;
        boolean z = f17 == -1.0f || Math.abs((f17 * 2.0f) - f12) < 0.1f;
        if (z) {
            f4 = f16;
            f5 = 0.0f;
        } else {
            f5 = 1.75f;
            f4 = 0.0f;
        }
        float f19 = f13 + f14;
        float f20 = f4 + f14;
        float sqrt = (float) Math.sqrt((f19 * f19) - (f20 * f20));
        float f21 = f15 - sqrt;
        float f22 = f15 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan(sqrt / f20));
        float f23 = (90.0f - degrees) + f5;
        shapePath.lineTo(f21, 0.0f);
        float f24 = f14 * 2.0f;
        shapePath.addArc(f21 - f14, 0.0f, f21 + f14, f24, 270.0f, degrees);
        if (z) {
            f7 = f15 - f13;
            f8 = (-f13) - f4;
            f6 = f15 + f13;
            f9 = f13 - f4;
            f10 = 180.0f - f23;
            f11 = (f23 * 2.0f) - 180.0f;
        } else {
            float f25 = this.b;
            float f26 = f18 * 2.0f;
            float f27 = f15 - f13;
            shapePath.addArc(f27, -(f18 + f25), f27 + f25 + f26, f25 + f18, 180.0f - f23, ((f23 * 2.0f) - 180.0f) / 2.0f);
            f6 = f15 + f13;
            float f28 = this.b;
            shapePath.lineTo(f6 - ((f28 / 2.0f) + f18), f28 + f18);
            float f29 = this.b;
            f7 = f6 - (f26 + f29);
            f8 = -(f18 + f29);
            f9 = f29 + f18;
            f10 = 90.0f;
            f11 = f23 - 90.0f;
        }
        shapePath.addArc(f7, f8, f6, f9, f10, f11);
        shapePath.addArc(f22 - f14, 0.0f, f22 + f14, f24, 270.0f - degrees, degrees);
        shapePath.lineTo(f, 0.0f);
    }

    public float getFabCornerRadius() {
        return this.f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getFabDiameter() {
        return this.f1488c;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getHorizontalOffset() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(float f) {
        this.e = f;
    }

    public void setFabCornerSize(float f) {
        this.f = f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setFabDiameter(float f) {
        this.f1488c = f;
    }
}
