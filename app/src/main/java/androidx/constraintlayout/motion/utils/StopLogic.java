package androidx.constraintlayout.motion.utils;

import android.util.Log;
import androidx.constraintlayout.motion.widget.MotionInterpolator;

/* loaded from: classes.dex */
public class StopLogic extends MotionInterpolator {
    private float a;
    private float b;

    /* renamed from: c, reason: collision with root package name */
    private float f289c;

    /* renamed from: d, reason: collision with root package name */
    private float f290d;
    private float e;
    private float f;
    private float g;
    private float h;

    /* renamed from: i, reason: collision with root package name */
    private float f291i;
    private int j;
    private String k;
    private boolean l = false;
    private float m;
    private float n;

    private float a(float f) {
        float f2 = this.f290d;
        if (f <= f2) {
            float f3 = this.a;
            return (f3 * f) + ((((this.b - f3) * f) * f) / (f2 * 2.0f));
        }
        int i2 = this.j;
        if (i2 == 1) {
            return this.g;
        }
        float f4 = f - f2;
        float f5 = this.e;
        if (f4 < f5) {
            float f6 = this.g;
            float f7 = this.b;
            return f6 + (f7 * f4) + ((((this.f289c - f7) * f4) * f4) / (f5 * 2.0f));
        }
        if (i2 == 2) {
            return this.h;
        }
        float f8 = f4 - f5;
        float f9 = this.f;
        if (f8 >= f9) {
            return this.f291i;
        }
        float f10 = this.h;
        float f11 = this.f289c;
        return (f10 + (f11 * f8)) - (((f11 * f8) * f8) / (f9 * 2.0f));
    }

    private void b(float f, float f2, float f3, float f4, float f5) {
        if (f == 0.0f) {
            f = 1.0E-4f;
        }
        this.a = f;
        float f6 = f / f3;
        float f7 = (f6 * f) / 2.0f;
        if (f < 0.0f) {
            float sqrt = (float) Math.sqrt((f2 - ((((-f) / f3) * f) / 2.0f)) * f3);
            if (sqrt < f4) {
                this.k = "backward accelerate, decelerate";
                this.j = 2;
                this.a = f;
                this.b = sqrt;
                this.f289c = 0.0f;
                float f8 = (sqrt - f) / f3;
                this.f290d = f8;
                this.e = sqrt / f3;
                this.g = ((f + sqrt) * f8) / 2.0f;
                this.h = f2;
                this.f291i = f2;
                return;
            }
            this.k = "backward accelerate cruse decelerate";
            this.j = 3;
            this.a = f;
            this.b = f4;
            this.f289c = f4;
            float f9 = (f4 - f) / f3;
            this.f290d = f9;
            float f10 = f4 / f3;
            this.f = f10;
            float f11 = ((f + f4) * f9) / 2.0f;
            float f12 = (f10 * f4) / 2.0f;
            this.e = ((f2 - f11) - f12) / f4;
            this.g = f11;
            this.h = f2 - f12;
            this.f291i = f2;
            return;
        }
        if (f7 >= f2) {
            this.k = "hard stop";
            this.j = 1;
            this.a = f;
            this.b = 0.0f;
            this.g = f2;
            this.f290d = (2.0f * f2) / f;
            return;
        }
        float f13 = f2 - f7;
        float f14 = f13 / f;
        if (f14 + f6 < f5) {
            this.k = "cruse decelerate";
            this.j = 2;
            this.a = f;
            this.b = f;
            this.f289c = 0.0f;
            this.g = f13;
            this.h = f2;
            this.f290d = f14;
            this.e = f6;
            return;
        }
        float sqrt2 = (float) Math.sqrt((f3 * f2) + ((f * f) / 2.0f));
        float f15 = (sqrt2 - f) / f3;
        this.f290d = f15;
        float f16 = sqrt2 / f3;
        this.e = f16;
        if (sqrt2 < f4) {
            this.k = "accelerate decelerate";
            this.j = 2;
            this.a = f;
            this.b = sqrt2;
            this.f289c = 0.0f;
            this.f290d = f15;
            this.e = f16;
            this.g = ((f + sqrt2) * f15) / 2.0f;
            this.h = f2;
            return;
        }
        this.k = "accelerate cruse decelerate";
        this.j = 3;
        this.a = f;
        this.b = f4;
        this.f289c = f4;
        float f17 = (f4 - f) / f3;
        this.f290d = f17;
        float f18 = f4 / f3;
        this.f = f18;
        float f19 = ((f + f4) * f17) / 2.0f;
        float f20 = (f18 * f4) / 2.0f;
        this.e = ((f2 - f19) - f20) / f4;
        this.g = f19;
        this.h = f2 - f20;
        this.f291i = f2;
    }

    public void config(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7;
        StopLogic stopLogic;
        float f8;
        this.m = f;
        boolean z = f > f2;
        this.l = z;
        if (z) {
            f8 = -f3;
            f7 = f - f2;
            stopLogic = this;
        } else {
            f7 = f2 - f;
            stopLogic = this;
            f8 = f3;
        }
        stopLogic.b(f8, f7, f5, f6, f4);
    }

    public void debug(String str, String str2, float f) {
        StringBuilder sb;
        String str3;
        Log.v(str, str2 + " ===== " + this.k);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(this.l ? "backwards" : "forward ");
        sb2.append(" time = ");
        sb2.append(f);
        sb2.append("  stages ");
        sb2.append(this.j);
        Log.v(str, sb2.toString());
        Log.v(str, str2 + " dur " + this.f290d + " vel " + this.a + " pos " + this.g);
        if (this.j > 1) {
            Log.v(str, str2 + " dur " + this.e + " vel " + this.b + " pos " + this.h);
        }
        if (this.j > 2) {
            Log.v(str, str2 + " dur " + this.f + " vel " + this.f289c + " pos " + this.f291i);
        }
        float f2 = this.f290d;
        if (f <= f2) {
            sb = new StringBuilder();
            sb.append(str2);
            str3 = "stage 0";
        } else {
            int i2 = this.j;
            if (i2 == 1) {
                sb = new StringBuilder();
                sb.append(str2);
                str3 = "end stage 0";
            } else {
                float f3 = f - f2;
                float f4 = this.e;
                if (f3 < f4) {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str3 = " stage 1";
                } else if (i2 == 2) {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str3 = "end stage 1";
                } else if (f3 - f4 < this.f) {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str3 = " stage 2";
                } else {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str3 = " end stage 2";
                }
            }
        }
        sb.append(str3);
        Log.v(str, sb.toString());
    }

    @Override // androidx.constraintlayout.motion.widget.MotionInterpolator, android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        float a = a(f);
        this.n = f;
        return this.l ? this.m - a : this.m + a;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
    public float getVelocity() {
        return this.l ? -getVelocity(this.n) : getVelocity(this.n);
    }

    public float getVelocity(float f) {
        float f2 = this.f290d;
        if (f <= f2) {
            float f3 = this.a;
            return f3 + (((this.b - f3) * f) / f2);
        }
        int i2 = this.j;
        if (i2 == 1) {
            return 0.0f;
        }
        float f4 = f - f2;
        float f5 = this.e;
        if (f4 < f5) {
            float f6 = this.b;
            return f6 + (((this.f289c - f6) * f4) / f5);
        }
        if (i2 == 2) {
            return this.h;
        }
        float f7 = f4 - f5;
        float f8 = this.f;
        if (f7 >= f8) {
            return this.f291i;
        }
        float f9 = this.f289c;
        return f9 - ((f7 * f9) / f8);
    }
}
