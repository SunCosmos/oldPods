package androidx.constraintlayout.motion.utils;

import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a extends CurveFit {
    private final double[] a;
    C0010a[] b;

    /* renamed from: androidx.constraintlayout.motion.utils.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0010a {
        private static double[] s = new double[91];
        double[] a;
        double b;

        /* renamed from: c, reason: collision with root package name */
        double f294c;

        /* renamed from: d, reason: collision with root package name */
        double f295d;
        double e;
        double f;
        double g;
        double h;

        /* renamed from: i, reason: collision with root package name */
        double f296i;
        double j;
        double k;
        double l;
        double m;
        double n;
        double o;
        double p;
        boolean q;
        boolean r;

        C0010a(int i2, double d2, double d3, double d4, double d5, double d6, double d7) {
            this.r = false;
            this.q = i2 == 1;
            this.f294c = d2;
            this.f295d = d3;
            this.f296i = 1.0d / (d3 - d2);
            if (3 == i2) {
                this.r = true;
            }
            double d8 = d6 - d4;
            double d9 = d7 - d5;
            if (this.r || Math.abs(d8) < 0.001d || Math.abs(d9) < 0.001d) {
                this.r = true;
                this.e = d4;
                this.f = d6;
                this.g = d5;
                this.h = d7;
                double hypot = Math.hypot(d9, d8);
                this.b = hypot;
                this.n = hypot * this.f296i;
                double d10 = this.f295d;
                double d11 = this.f294c;
                this.l = d8 / (d10 - d11);
                this.m = d9 / (d10 - d11);
                return;
            }
            this.a = new double[101];
            boolean z = this.q;
            double d12 = z ? -1 : 1;
            Double.isNaN(d12);
            this.j = d8 * d12;
            double d13 = z ? 1 : -1;
            Double.isNaN(d13);
            this.k = d9 * d13;
            this.l = z ? d6 : d4;
            this.m = z ? d5 : d7;
            a(d4, d5, d6, d7);
            this.n = this.b * this.f296i;
        }

        private void a(double d2, double d3, double d4, double d5) {
            double d6;
            double d7 = d4 - d2;
            double d8 = d3 - d5;
            int i2 = 0;
            double d9 = 0.0d;
            double d10 = 0.0d;
            double d11 = 0.0d;
            while (true) {
                if (i2 >= s.length) {
                    break;
                }
                double d12 = i2;
                Double.isNaN(d12);
                double d13 = d9;
                double length = r15.length - 1;
                Double.isNaN(length);
                double radians = Math.toRadians((d12 * 90.0d) / length);
                double sin = Math.sin(radians) * d7;
                double cos = Math.cos(radians) * d8;
                if (i2 > 0) {
                    d6 = d13 + Math.hypot(sin - d10, cos - d11);
                    s[i2] = d6;
                } else {
                    d6 = d13;
                }
                i2++;
                d11 = cos;
                d9 = d6;
                d10 = sin;
            }
            double d14 = d9;
            this.b = d14;
            int i3 = 0;
            while (true) {
                double[] dArr = s;
                if (i3 >= dArr.length) {
                    break;
                }
                dArr[i3] = dArr[i3] / d14;
                i3++;
            }
            int i4 = 0;
            while (true) {
                if (i4 >= this.a.length) {
                    return;
                }
                double d15 = i4;
                double length2 = r1.length - 1;
                Double.isNaN(d15);
                Double.isNaN(length2);
                double d16 = d15 / length2;
                int binarySearch = Arrays.binarySearch(s, d16);
                if (binarySearch >= 0) {
                    this.a[i4] = binarySearch / (s.length - 1);
                } else if (binarySearch == -1) {
                    this.a[i4] = 0.0d;
                } else {
                    int i5 = -binarySearch;
                    int i6 = i5 - 2;
                    double d17 = i6;
                    double[] dArr2 = s;
                    double d18 = (d16 - dArr2[i6]) / (dArr2[i5 - 1] - dArr2[i6]);
                    Double.isNaN(d17);
                    double length3 = dArr2.length - 1;
                    Double.isNaN(length3);
                    this.a[i4] = (d17 + d18) / length3;
                }
                i4++;
            }
        }

        double b() {
            double d2 = this.j * this.p;
            double hypot = this.n / Math.hypot(d2, (-this.k) * this.o);
            if (this.q) {
                d2 = -d2;
            }
            return d2 * hypot;
        }

        double c() {
            double d2 = this.j * this.p;
            double d3 = (-this.k) * this.o;
            double hypot = this.n / Math.hypot(d2, d3);
            return this.q ? (-d3) * hypot : d3 * hypot;
        }

        public double d(double d2) {
            return this.l;
        }

        public double e(double d2) {
            return this.m;
        }

        public double f(double d2) {
            double d3 = (d2 - this.f294c) * this.f296i;
            double d4 = this.e;
            return d4 + (d3 * (this.f - d4));
        }

        public double g(double d2) {
            double d3 = (d2 - this.f294c) * this.f296i;
            double d4 = this.g;
            return d4 + (d3 * (this.h - d4));
        }

        double h() {
            return this.l + (this.j * this.o);
        }

        double i() {
            return this.m + (this.k * this.p);
        }

        double j(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.a;
            double length = dArr.length - 1;
            Double.isNaN(length);
            double d3 = d2 * length;
            int i2 = (int) d3;
            double d4 = i2;
            Double.isNaN(d4);
            return dArr[i2] + ((d3 - d4) * (dArr[i2 + 1] - dArr[i2]));
        }

        void k(double d2) {
            double j = j((this.q ? this.f295d - d2 : d2 - this.f294c) * this.f296i) * 1.5707963267948966d;
            this.o = Math.sin(j);
            this.p = Math.cos(j);
        }
    }

    public a(int[] iArr, double[] dArr, double[][] dArr2) {
        this.a = dArr;
        this.b = new C0010a[dArr.length - 1];
        int i2 = 0;
        int i3 = 1;
        int i4 = 1;
        while (true) {
            C0010a[] c0010aArr = this.b;
            if (i2 >= c0010aArr.length) {
                return;
            }
            int i5 = iArr[i2];
            if (i5 == 0) {
                i4 = 3;
            } else if (i5 == 1) {
                i3 = 1;
                i4 = 1;
            } else if (i5 == 2) {
                i3 = 2;
                i4 = 2;
            } else if (i5 == 3) {
                i3 = i3 == 1 ? 2 : 1;
                i4 = i3;
            }
            int i6 = i2 + 1;
            c0010aArr[i2] = new C0010a(i4, dArr[i2], dArr[i6], dArr2[i2][0], dArr2[i2][1], dArr2[i6][0], dArr2[i6][1]);
            i2 = i6;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getPos(double d2, int i2) {
        C0010a[] c0010aArr = this.b;
        int i3 = 0;
        if (d2 < c0010aArr[0].f294c) {
            d2 = c0010aArr[0].f294c;
        } else if (d2 > c0010aArr[c0010aArr.length - 1].f295d) {
            d2 = c0010aArr[c0010aArr.length - 1].f295d;
        }
        while (true) {
            C0010a[] c0010aArr2 = this.b;
            if (i3 >= c0010aArr2.length) {
                return Double.NaN;
            }
            if (d2 <= c0010aArr2[i3].f295d) {
                if (c0010aArr2[i3].r) {
                    return i2 == 0 ? c0010aArr2[i3].f(d2) : c0010aArr2[i3].g(d2);
                }
                c0010aArr2[i3].k(d2);
                C0010a[] c0010aArr3 = this.b;
                return i2 == 0 ? c0010aArr3[i3].h() : c0010aArr3[i3].i();
            }
            i3++;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getPos(double d2, double[] dArr) {
        C0010a[] c0010aArr = this.b;
        if (d2 < c0010aArr[0].f294c) {
            d2 = c0010aArr[0].f294c;
        }
        if (d2 > c0010aArr[c0010aArr.length - 1].f295d) {
            d2 = c0010aArr[c0010aArr.length - 1].f295d;
        }
        int i2 = 0;
        while (true) {
            C0010a[] c0010aArr2 = this.b;
            if (i2 >= c0010aArr2.length) {
                return;
            }
            if (d2 <= c0010aArr2[i2].f295d) {
                if (c0010aArr2[i2].r) {
                    dArr[0] = c0010aArr2[i2].f(d2);
                    dArr[1] = this.b[i2].g(d2);
                    return;
                } else {
                    c0010aArr2[i2].k(d2);
                    dArr[0] = this.b[i2].h();
                    dArr[1] = this.b[i2].i();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getPos(double d2, float[] fArr) {
        C0010a[] c0010aArr = this.b;
        if (d2 < c0010aArr[0].f294c) {
            d2 = c0010aArr[0].f294c;
        } else if (d2 > c0010aArr[c0010aArr.length - 1].f295d) {
            d2 = c0010aArr[c0010aArr.length - 1].f295d;
        }
        int i2 = 0;
        while (true) {
            C0010a[] c0010aArr2 = this.b;
            if (i2 >= c0010aArr2.length) {
                return;
            }
            if (d2 <= c0010aArr2[i2].f295d) {
                if (c0010aArr2[i2].r) {
                    fArr[0] = (float) c0010aArr2[i2].f(d2);
                    fArr[1] = (float) this.b[i2].g(d2);
                    return;
                } else {
                    c0010aArr2[i2].k(d2);
                    fArr[0] = (float) this.b[i2].h();
                    fArr[1] = (float) this.b[i2].i();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getSlope(double d2, int i2) {
        C0010a[] c0010aArr = this.b;
        int i3 = 0;
        if (d2 < c0010aArr[0].f294c) {
            d2 = c0010aArr[0].f294c;
        }
        if (d2 > c0010aArr[c0010aArr.length - 1].f295d) {
            d2 = c0010aArr[c0010aArr.length - 1].f295d;
        }
        while (true) {
            C0010a[] c0010aArr2 = this.b;
            if (i3 >= c0010aArr2.length) {
                return Double.NaN;
            }
            if (d2 <= c0010aArr2[i3].f295d) {
                if (c0010aArr2[i3].r) {
                    return i2 == 0 ? c0010aArr2[i3].d(d2) : c0010aArr2[i3].e(d2);
                }
                c0010aArr2[i3].k(d2);
                C0010a[] c0010aArr3 = this.b;
                return i2 == 0 ? c0010aArr3[i3].b() : c0010aArr3[i3].c();
            }
            i3++;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getSlope(double d2, double[] dArr) {
        C0010a[] c0010aArr = this.b;
        if (d2 < c0010aArr[0].f294c) {
            d2 = c0010aArr[0].f294c;
        } else if (d2 > c0010aArr[c0010aArr.length - 1].f295d) {
            d2 = c0010aArr[c0010aArr.length - 1].f295d;
        }
        int i2 = 0;
        while (true) {
            C0010a[] c0010aArr2 = this.b;
            if (i2 >= c0010aArr2.length) {
                return;
            }
            if (d2 <= c0010aArr2[i2].f295d) {
                if (c0010aArr2[i2].r) {
                    dArr[0] = c0010aArr2[i2].d(d2);
                    dArr[1] = this.b[i2].e(d2);
                    return;
                } else {
                    c0010aArr2[i2].k(d2);
                    dArr[0] = this.b[i2].b();
                    dArr[1] = this.b[i2].c();
                    return;
                }
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.a;
    }
}
