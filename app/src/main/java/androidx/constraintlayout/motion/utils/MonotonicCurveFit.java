package androidx.constraintlayout.motion.utils;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class MonotonicCurveFit extends CurveFit {
    private double[] a;
    private double[][] b;

    /* renamed from: c, reason: collision with root package name */
    private double[][] f286c;

    public MonotonicCurveFit(double[] dArr, double[][] dArr2) {
        int length = dArr.length;
        int length2 = dArr2[0].length;
        int i2 = length - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) double.class, i2, length2);
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
        for (int i3 = 0; i3 < length2; i3++) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i4 + 1;
                dArr3[i4][i3] = (dArr2[i5][i3] - dArr2[i4][i3]) / (dArr[i5] - dArr[i4]);
                if (i4 == 0) {
                    dArr4[i4][i3] = dArr3[i4][i3];
                } else {
                    dArr4[i4][i3] = (dArr3[i4 - 1][i3] + dArr3[i4][i3]) * 0.5d;
                }
                i4 = i5;
            }
            dArr4[i2][i3] = dArr3[length - 2][i3];
        }
        for (int i6 = 0; i6 < i2; i6++) {
            for (int i7 = 0; i7 < length2; i7++) {
                if (dArr3[i6][i7] == 0.0d) {
                    dArr4[i6][i7] = 0.0d;
                    dArr4[i6 + 1][i7] = 0.0d;
                } else {
                    double d2 = dArr4[i6][i7] / dArr3[i6][i7];
                    int i8 = i6 + 1;
                    double d3 = dArr4[i8][i7] / dArr3[i6][i7];
                    double hypot = Math.hypot(d2, d3);
                    if (hypot > 9.0d) {
                        double d4 = 3.0d / hypot;
                        dArr4[i6][i7] = d2 * d4 * dArr3[i6][i7];
                        dArr4[i8][i7] = d4 * d3 * dArr3[i6][i7];
                    }
                }
            }
        }
        this.a = dArr;
        this.b = dArr2;
        this.f286c = dArr4;
    }

    private static double a(double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = d3 * d3;
        double d9 = d3 * 6.0d;
        double d10 = 3.0d * d2;
        return ((((((((((-6.0d) * d8) * d5) + (d9 * d5)) + ((6.0d * d8) * d4)) - (d9 * d4)) + ((d10 * d7) * d8)) + ((d10 * d6) * d8)) - (((2.0d * d2) * d7) * d3)) - (((4.0d * d2) * d6) * d3)) + (d2 * d6);
    }

    private static double b(double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = d3 * d3;
        double d9 = d8 * d3;
        double d10 = 3.0d * d8;
        double d11 = ((((((-2.0d) * d9) * d5) + (d10 * d5)) + ((d9 * 2.0d) * d4)) - (d10 * d4)) + d4;
        double d12 = d2 * d7;
        double d13 = d2 * d6;
        return ((((d11 + (d12 * d9)) + (d9 * d13)) - (d12 * d8)) - (((d2 * 2.0d) * d6) * d8)) + (d13 * d3);
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getPos(double d2, int i2) {
        double[] dArr = this.a;
        int length = dArr.length;
        int i3 = 0;
        if (d2 <= dArr[0]) {
            return this.b[0][i2];
        }
        int i4 = length - 1;
        if (d2 >= dArr[i4]) {
            return this.b[i4][i2];
        }
        while (i3 < i4) {
            double[] dArr2 = this.a;
            if (d2 == dArr2[i3]) {
                return this.b[i3][i2];
            }
            int i5 = i3 + 1;
            if (d2 < dArr2[i5]) {
                double d3 = dArr2[i5] - dArr2[i3];
                double d4 = (d2 - dArr2[i3]) / d3;
                double[][] dArr3 = this.b;
                double d5 = dArr3[i3][i2];
                double d6 = dArr3[i5][i2];
                double[][] dArr4 = this.f286c;
                return b(d3, d4, d5, d6, dArr4[i3][i2], dArr4[i5][i2]);
            }
            i3 = i5;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getPos(double d2, double[] dArr) {
        double[] dArr2 = this.a;
        int length = dArr2.length;
        int i2 = 0;
        int length2 = this.b[0].length;
        if (d2 <= dArr2[0]) {
            for (int i3 = 0; i3 < length2; i3++) {
                dArr[i3] = this.b[0][i3];
            }
            return;
        }
        int i4 = length - 1;
        if (d2 >= dArr2[i4]) {
            while (i2 < length2) {
                dArr[i2] = this.b[i4][i2];
                i2++;
            }
            return;
        }
        int i5 = 0;
        while (i5 < i4) {
            if (d2 == this.a[i5]) {
                for (int i6 = 0; i6 < length2; i6++) {
                    dArr[i6] = this.b[i5][i6];
                }
            }
            double[] dArr3 = this.a;
            int i7 = i5 + 1;
            if (d2 < dArr3[i7]) {
                double d3 = dArr3[i7] - dArr3[i5];
                double d4 = (d2 - dArr3[i5]) / d3;
                while (i2 < length2) {
                    double[][] dArr4 = this.b;
                    double d5 = dArr4[i5][i2];
                    double d6 = dArr4[i7][i2];
                    double[][] dArr5 = this.f286c;
                    dArr[i2] = b(d3, d4, d5, d6, dArr5[i5][i2], dArr5[i7][i2]);
                    i2++;
                }
                return;
            }
            i5 = i7;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getPos(double d2, float[] fArr) {
        double[] dArr = this.a;
        int length = dArr.length;
        int i2 = 0;
        int length2 = this.b[0].length;
        if (d2 <= dArr[0]) {
            for (int i3 = 0; i3 < length2; i3++) {
                fArr[i3] = (float) this.b[0][i3];
            }
            return;
        }
        int i4 = length - 1;
        if (d2 >= dArr[i4]) {
            while (i2 < length2) {
                fArr[i2] = (float) this.b[i4][i2];
                i2++;
            }
            return;
        }
        int i5 = 0;
        while (i5 < i4) {
            if (d2 == this.a[i5]) {
                for (int i6 = 0; i6 < length2; i6++) {
                    fArr[i6] = (float) this.b[i5][i6];
                }
            }
            double[] dArr2 = this.a;
            int i7 = i5 + 1;
            if (d2 < dArr2[i7]) {
                double d3 = dArr2[i7] - dArr2[i5];
                double d4 = (d2 - dArr2[i5]) / d3;
                while (i2 < length2) {
                    double[][] dArr3 = this.b;
                    double d5 = dArr3[i5][i2];
                    double d6 = dArr3[i7][i2];
                    double[][] dArr4 = this.f286c;
                    fArr[i2] = (float) b(d3, d4, d5, d6, dArr4[i5][i2], dArr4[i7][i2]);
                    i2++;
                }
                return;
            }
            i5 = i7;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getSlope(double d2, int i2) {
        double d3;
        double[] dArr = this.a;
        int length = dArr.length;
        int i3 = 0;
        if (d2 < dArr[0]) {
            d3 = dArr[0];
        } else {
            int i4 = length - 1;
            d3 = d2 >= dArr[i4] ? dArr[i4] : d2;
        }
        while (i3 < length - 1) {
            double[] dArr2 = this.a;
            int i5 = i3 + 1;
            if (d3 <= dArr2[i5]) {
                double d4 = dArr2[i5] - dArr2[i3];
                double d5 = (d3 - dArr2[i3]) / d4;
                double[][] dArr3 = this.b;
                double d6 = dArr3[i3][i2];
                double d7 = dArr3[i5][i2];
                double[][] dArr4 = this.f286c;
                return a(d4, d5, d6, d7, dArr4[i3][i2], dArr4[i5][i2]) / d4;
            }
            i3 = i5;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getSlope(double d2, double[] dArr) {
        double d3;
        double[] dArr2 = this.a;
        int length = dArr2.length;
        int length2 = this.b[0].length;
        if (d2 <= dArr2[0]) {
            d3 = dArr2[0];
        } else {
            int i2 = length - 1;
            d3 = d2 >= dArr2[i2] ? dArr2[i2] : d2;
        }
        int i3 = 0;
        while (i3 < length - 1) {
            double[] dArr3 = this.a;
            int i4 = i3 + 1;
            if (d3 <= dArr3[i4]) {
                double d4 = dArr3[i4] - dArr3[i3];
                double d5 = (d3 - dArr3[i3]) / d4;
                for (int i5 = 0; i5 < length2; i5++) {
                    double[][] dArr4 = this.b;
                    double d6 = dArr4[i3][i5];
                    double d7 = dArr4[i4][i5];
                    double[][] dArr5 = this.f286c;
                    dArr[i5] = a(d4, d5, d6, d7, dArr5[i3][i5], dArr5[i4][i5]) / d4;
                }
                return;
            }
            i3 = i4;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.a;
    }
}
