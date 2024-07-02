package androidx.constraintlayout.motion.utils;

import java.util.Arrays;

/* loaded from: classes.dex */
public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;

    /* renamed from: c, reason: collision with root package name */
    double[] f287c;

    /* renamed from: d, reason: collision with root package name */
    int f288d;
    float[] a = new float[0];
    double[] b = new double[0];
    double e = 6.283185307179586d;

    double a(double d2) {
        if (d2 <= 0.0d) {
            d2 = 1.0E-5d;
        } else if (d2 >= 1.0d) {
            d2 = 0.999999d;
        }
        int binarySearch = Arrays.binarySearch(this.b, d2);
        if (binarySearch > 0 || binarySearch == 0) {
            return 0.0d;
        }
        int i2 = (-binarySearch) - 1;
        float[] fArr = this.a;
        int i3 = i2 - 1;
        double d3 = fArr[i2] - fArr[i3];
        double[] dArr = this.b;
        double d4 = dArr[i2] - dArr[i3];
        Double.isNaN(d3);
        double d5 = d3 / d4;
        double d6 = d2 * d5;
        double d7 = fArr[i3];
        double d8 = d5 * dArr[i3];
        Double.isNaN(d7);
        return (d7 - d8) + d6;
    }

    public void addPoint(double d2, float f) {
        int length = this.a.length + 1;
        int binarySearch = Arrays.binarySearch(this.b, d2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        this.b = Arrays.copyOf(this.b, length);
        this.a = Arrays.copyOf(this.a, length);
        this.f287c = new double[length];
        double[] dArr = this.b;
        System.arraycopy(dArr, binarySearch, dArr, binarySearch + 1, (length - binarySearch) - 1);
        this.b[binarySearch] = d2;
        this.a[binarySearch] = f;
    }

    double b(double d2) {
        if (d2 < 0.0d) {
            d2 = 0.0d;
        } else if (d2 > 1.0d) {
            d2 = 1.0d;
        }
        int binarySearch = Arrays.binarySearch(this.b, d2);
        if (binarySearch > 0) {
            return 1.0d;
        }
        if (binarySearch == 0) {
            return 0.0d;
        }
        int i2 = (-binarySearch) - 1;
        float[] fArr = this.a;
        int i3 = i2 - 1;
        double d3 = fArr[i2] - fArr[i3];
        double[] dArr = this.b;
        double d4 = dArr[i2] - dArr[i3];
        Double.isNaN(d3);
        double d5 = d3 / d4;
        double d6 = this.f287c[i3];
        double d7 = fArr[i3];
        double d8 = dArr[i3] * d5;
        Double.isNaN(d7);
        return d6 + ((d7 - d8) * (d2 - dArr[i3])) + ((d5 * ((d2 * d2) - (dArr[i3] * dArr[i3]))) / 2.0d);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    public double getSlope(double d2) {
        double a;
        double signum;
        double a2;
        double a3;
        double sin;
        switch (this.f288d) {
            case 1:
                return 0.0d;
            case 2:
                a = a(d2) * 4.0d;
                signum = Math.signum((((b(d2) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return a * signum;
            case 3:
                a2 = a(d2);
                return a2 * 2.0d;
            case 4:
                a2 = -a(d2);
                return a2 * 2.0d;
            case 5:
                a3 = (-this.e) * a(d2);
                sin = Math.sin(this.e * b(d2));
                return a3 * sin;
            case 6:
                a = a(d2) * 4.0d;
                signum = (((b(d2) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return a * signum;
            default:
                a3 = this.e * a(d2);
                sin = Math.cos(this.e * b(d2));
                return a3 * sin;
        }
    }

    public double getValue(double d2) {
        double abs;
        switch (this.f288d) {
            case 1:
                return Math.signum(0.5d - (b(d2) % 1.0d));
            case 2:
                abs = Math.abs((((b(d2) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((b(d2) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                abs = ((b(d2) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.e * b(d2));
            case 6:
                double abs2 = 1.0d - Math.abs(((b(d2) * 4.0d) % 4.0d) - 2.0d);
                abs = abs2 * abs2;
                break;
            default:
                return Math.sin(this.e * b(d2));
        }
        return 1.0d - abs;
    }

    public void normalize() {
        double d2 = 0.0d;
        int i2 = 0;
        while (true) {
            float[] fArr = this.a;
            if (i2 >= fArr.length) {
                break;
            }
            double d3 = fArr[i2];
            Double.isNaN(d3);
            d2 += d3;
            i2++;
        }
        int i3 = 1;
        double d4 = 0.0d;
        int i4 = 1;
        while (true) {
            float[] fArr2 = this.a;
            if (i4 >= fArr2.length) {
                break;
            }
            int i5 = i4 - 1;
            float f = (fArr2[i5] + fArr2[i4]) / 2.0f;
            double[] dArr = this.b;
            double d5 = dArr[i4] - dArr[i5];
            double d6 = f;
            Double.isNaN(d6);
            d4 += d5 * d6;
            i4++;
        }
        int i6 = 0;
        while (true) {
            float[] fArr3 = this.a;
            if (i6 >= fArr3.length) {
                break;
            }
            double d7 = fArr3[i6];
            Double.isNaN(d7);
            fArr3[i6] = (float) (d7 * (d2 / d4));
            i6++;
        }
        this.f287c[0] = 0.0d;
        while (true) {
            float[] fArr4 = this.a;
            if (i3 >= fArr4.length) {
                return;
            }
            int i7 = i3 - 1;
            float f2 = (fArr4[i7] + fArr4[i3]) / 2.0f;
            double[] dArr2 = this.b;
            double d8 = dArr2[i3] - dArr2[i7];
            double[] dArr3 = this.f287c;
            double d9 = dArr3[i7];
            double d10 = f2;
            Double.isNaN(d10);
            dArr3[i3] = d9 + (d8 * d10);
            i3++;
        }
    }

    public void setType(int i2) {
        this.f288d = i2;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.b) + " period=" + Arrays.toString(this.a);
    }
}
