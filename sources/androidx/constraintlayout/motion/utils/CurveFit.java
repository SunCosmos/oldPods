package androidx.constraintlayout.motion.utils;

/* loaded from: classes.dex */
public abstract class CurveFit {
    public static final int CONSTANT = 2;
    public static final int LINEAR = 1;
    public static final int SPLINE = 0;

    /* loaded from: classes.dex */
    static class a extends CurveFit {
        double a;
        double[] b;

        a(double d2, double[] dArr) {
            this.a = d2;
            this.b = dArr;
        }

        @Override // androidx.constraintlayout.motion.utils.CurveFit
        public double getPos(double d2, int i2) {
            return this.b[i2];
        }

        @Override // androidx.constraintlayout.motion.utils.CurveFit
        public void getPos(double d2, double[] dArr) {
            double[] dArr2 = this.b;
            System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        }

        @Override // androidx.constraintlayout.motion.utils.CurveFit
        public void getPos(double d2, float[] fArr) {
            int i2 = 0;
            while (true) {
                double[] dArr = this.b;
                if (i2 >= dArr.length) {
                    return;
                }
                fArr[i2] = (float) dArr[i2];
                i2++;
            }
        }

        @Override // androidx.constraintlayout.motion.utils.CurveFit
        public double getSlope(double d2, int i2) {
            return 0.0d;
        }

        @Override // androidx.constraintlayout.motion.utils.CurveFit
        public void getSlope(double d2, double[] dArr) {
            for (int i2 = 0; i2 < this.b.length; i2++) {
                dArr[i2] = 0.0d;
            }
        }

        @Override // androidx.constraintlayout.motion.utils.CurveFit
        public double[] getTimePoints() {
            return new double[]{this.a};
        }
    }

    public static CurveFit get(int i2, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i2 = 2;
        }
        return i2 != 0 ? i2 != 2 ? new LinearCurveFit(dArr, dArr2) : new a(dArr[0], dArr2[0]) : new MonotonicCurveFit(dArr, dArr2);
    }

    public static CurveFit getArc(int[] iArr, double[] dArr, double[][] dArr2) {
        return new androidx.constraintlayout.motion.utils.a(iArr, dArr, dArr2);
    }

    public abstract double getPos(double d2, int i2);

    public abstract void getPos(double d2, double[] dArr);

    public abstract void getPos(double d2, float[] fArr);

    public abstract double getSlope(double d2, int i2);

    public abstract void getSlope(double d2, double[] dArr);

    public abstract double[] getTimePoints();
}
