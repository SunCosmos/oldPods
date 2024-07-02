package androidx.constraintlayout.motion.utils;

/* loaded from: classes.dex */
public class LinearCurveFit extends CurveFit {
    private double[] a;
    private double[][] b;

    public LinearCurveFit(double[] dArr, double[][] dArr2) {
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.a = dArr;
        this.b = dArr2;
        if (length2 > 2) {
            double d2 = 0.0d;
            double d3 = 0.0d;
            int i2 = 0;
            while (i2 < dArr.length) {
                double d4 = dArr2[i2][0];
                double d5 = dArr2[i2][0];
                if (i2 > 0) {
                    Math.hypot(d4 - d2, d5 - d3);
                }
                i2++;
                d2 = d4;
                d3 = d5;
            }
        }
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
                double d3 = (d2 - dArr2[i3]) / (dArr2[i5] - dArr2[i3]);
                double[][] dArr3 = this.b;
                return (dArr3[i3][i2] * (1.0d - d3)) + (dArr3[i5][i2] * d3);
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
                double d3 = (d2 - dArr3[i5]) / (dArr3[i7] - dArr3[i5]);
                while (i2 < length2) {
                    double[][] dArr4 = this.b;
                    dArr[i2] = (dArr4[i5][i2] * (1.0d - d3)) + (dArr4[i7][i2] * d3);
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
                double d3 = (d2 - dArr2[i5]) / (dArr2[i7] - dArr2[i5]);
                while (i2 < length2) {
                    double[][] dArr3 = this.b;
                    fArr[i2] = (float) ((dArr3[i5][i2] * (1.0d - d3)) + (dArr3[i7][i2] * d3));
                    i2++;
                }
                return;
            }
            i5 = i7;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getSlope(double d2, int i2) {
        double[] dArr = this.a;
        int length = dArr.length;
        int i3 = 0;
        if (d2 < dArr[0]) {
            d2 = dArr[0];
        } else {
            int i4 = length - 1;
            if (d2 >= dArr[i4]) {
                d2 = dArr[i4];
            }
        }
        while (i3 < length - 1) {
            double[] dArr2 = this.a;
            int i5 = i3 + 1;
            if (d2 <= dArr2[i5]) {
                double d3 = dArr2[i5] - dArr2[i3];
                double d4 = dArr2[i3];
                double[][] dArr3 = this.b;
                return (dArr3[i5][i2] - dArr3[i3][i2]) / d3;
            }
            i3 = i5;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getSlope(double d2, double[] dArr) {
        double[] dArr2 = this.a;
        int length = dArr2.length;
        int length2 = this.b[0].length;
        if (d2 <= dArr2[0]) {
            d2 = dArr2[0];
        } else {
            int i2 = length - 1;
            if (d2 >= dArr2[i2]) {
                d2 = dArr2[i2];
            }
        }
        int i3 = 0;
        while (i3 < length - 1) {
            double[] dArr3 = this.a;
            int i4 = i3 + 1;
            if (d2 <= dArr3[i4]) {
                double d3 = dArr3[i4] - dArr3[i3];
                double d4 = dArr3[i3];
                for (int i5 = 0; i5 < length2; i5++) {
                    double[][] dArr4 = this.b;
                    dArr[i5] = (dArr4[i4][i5] - dArr4[i3][i5]) / d3;
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
