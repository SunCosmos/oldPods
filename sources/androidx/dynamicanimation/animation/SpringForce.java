package androidx.dynamicanimation.animation;

import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes.dex */
public final class SpringForce {
    public static final float DAMPING_RATIO_HIGH_BOUNCY = 0.2f;
    public static final float DAMPING_RATIO_LOW_BOUNCY = 0.75f;
    public static final float DAMPING_RATIO_MEDIUM_BOUNCY = 0.5f;
    public static final float DAMPING_RATIO_NO_BOUNCY = 1.0f;
    public static final float STIFFNESS_HIGH = 10000.0f;
    public static final float STIFFNESS_LOW = 200.0f;
    public static final float STIFFNESS_MEDIUM = 1500.0f;
    public static final float STIFFNESS_VERY_LOW = 50.0f;
    double a;
    double b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f694c;

    /* renamed from: d, reason: collision with root package name */
    private double f695d;
    private double e;
    private double f;
    private double g;
    private double h;

    /* renamed from: i, reason: collision with root package name */
    private double f696i;
    private final DynamicAnimation.p j;

    public SpringForce() {
        this.a = Math.sqrt(1500.0d);
        this.b = 0.5d;
        this.f694c = false;
        this.f696i = Double.MAX_VALUE;
        this.j = new DynamicAnimation.p();
    }

    public SpringForce(float f) {
        this.a = Math.sqrt(1500.0d);
        this.b = 0.5d;
        this.f694c = false;
        this.f696i = Double.MAX_VALUE;
        this.j = new DynamicAnimation.p();
        this.f696i = f;
    }

    private void a() {
        if (this.f694c) {
            return;
        }
        if (this.f696i == Double.MAX_VALUE) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        double d2 = this.b;
        if (d2 > 1.0d) {
            double d3 = this.a;
            this.f = ((-d2) * d3) + (d3 * Math.sqrt((d2 * d2) - 1.0d));
            double d4 = this.b;
            double d5 = this.a;
            this.g = ((-d4) * d5) - (d5 * Math.sqrt((d4 * d4) - 1.0d));
        } else if (d2 >= 0.0d && d2 < 1.0d) {
            this.h = this.a * Math.sqrt(1.0d - (d2 * d2));
        }
        this.f694c = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(double d2) {
        double abs = Math.abs(d2);
        this.f695d = abs;
        this.e = abs * 62.5d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DynamicAnimation.p c(double d2, double d3, long j) {
        double cos;
        double d4;
        a();
        double d5 = j;
        Double.isNaN(d5);
        double d6 = d5 / 1000.0d;
        double d7 = d2 - this.f696i;
        double d8 = this.b;
        if (d8 > 1.0d) {
            double d9 = this.g;
            double d10 = this.f;
            double d11 = d7 - (((d9 * d7) - d3) / (d9 - d10));
            double d12 = ((d7 * d9) - d3) / (d9 - d10);
            d4 = (Math.pow(2.718281828459045d, d9 * d6) * d11) + (Math.pow(2.718281828459045d, this.f * d6) * d12);
            double d13 = this.g;
            double pow = d11 * d13 * Math.pow(2.718281828459045d, d13 * d6);
            double d14 = this.f;
            cos = pow + (d12 * d14 * Math.pow(2.718281828459045d, d14 * d6));
        } else if (d8 == 1.0d) {
            double d15 = this.a;
            double d16 = d3 + (d15 * d7);
            double d17 = d7 + (d16 * d6);
            d4 = Math.pow(2.718281828459045d, (-d15) * d6) * d17;
            double pow2 = d17 * Math.pow(2.718281828459045d, (-this.a) * d6);
            double d18 = this.a;
            cos = (d16 * Math.pow(2.718281828459045d, (-d18) * d6)) + (pow2 * (-d18));
        } else {
            double d19 = 1.0d / this.h;
            double d20 = this.a;
            double d21 = d19 * ((d8 * d20 * d7) + d3);
            double pow3 = Math.pow(2.718281828459045d, (-d8) * d20 * d6) * ((Math.cos(this.h * d6) * d7) + (Math.sin(this.h * d6) * d21));
            double d22 = this.a;
            double d23 = this.b;
            double d24 = (-d22) * pow3 * d23;
            double pow4 = Math.pow(2.718281828459045d, (-d23) * d22 * d6);
            double d25 = this.h;
            double sin = (-d25) * d7 * Math.sin(d25 * d6);
            double d26 = this.h;
            cos = d24 + (pow4 * (sin + (d21 * d26 * Math.cos(d26 * d6))));
            d4 = pow3;
        }
        DynamicAnimation.p pVar = this.j;
        pVar.a = (float) (d4 + this.f696i);
        pVar.b = (float) cos;
        return pVar;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public float getAcceleration(float f, float f2) {
        float finalPosition = f - getFinalPosition();
        double d2 = this.a;
        double d3 = d2 * d2;
        double d4 = d2 * 2.0d * this.b;
        double d5 = finalPosition;
        Double.isNaN(d5);
        double d6 = f2;
        Double.isNaN(d6);
        return (float) (((-d3) * d5) - (d4 * d6));
    }

    public float getDampingRatio() {
        return (float) this.b;
    }

    public float getFinalPosition() {
        return (float) this.f696i;
    }

    public float getStiffness() {
        double d2 = this.a;
        return (float) (d2 * d2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean isAtEquilibrium(float f, float f2) {
        return ((double) Math.abs(f2)) < this.e && ((double) Math.abs(f - getFinalPosition())) < this.f695d;
    }

    public SpringForce setDampingRatio(@FloatRange(from = 0.0d) float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.b = f;
        this.f694c = false;
        return this;
    }

    public SpringForce setFinalPosition(float f) {
        this.f696i = f;
        return this;
    }

    public SpringForce setStiffness(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.a = Math.sqrt(f);
        this.f694c = false;
        return this;
    }
}
