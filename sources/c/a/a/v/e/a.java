package c.a.a.v.e;

import c.a.a.l;
import c.a.a.r;
import c.a.a.x.b;
import c.a.a.x.i;
import c.a.a.x.n.c;
import c.a.a.x.n.e;

/* loaded from: classes.dex */
public final class a {
    private static final int[] g = {3808, 476, 2107, 1799};
    private final b a;
    private boolean b;

    /* renamed from: c, reason: collision with root package name */
    private int f1267c;

    /* renamed from: d, reason: collision with root package name */
    private int f1268d;
    private int e;
    private int f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c.a.a.v.e.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0045a {
        private final int a;
        private final int b;

        C0045a(int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }

        int a() {
            return this.a;
        }

        int b() {
            return this.b;
        }

        r c() {
            return new r(a(), b());
        }

        public String toString() {
            return "<" + this.a + ' ' + this.b + '>';
        }
    }

    public a(b bVar) {
        this.a = bVar;
    }

    private static float b(r rVar, r rVar2) {
        return c.a.a.x.m.a.a(rVar.c(), rVar.d(), rVar2.c(), rVar2.d());
    }

    private static float c(C0045a c0045a, C0045a c0045a2) {
        return c.a.a.x.m.a.b(c0045a.a(), c0045a.b(), c0045a2.a(), c0045a2.b());
    }

    private static r[] d(r[] rVarArr, float f, float f2) {
        float f3 = f2 / (f * 2.0f);
        float c2 = rVarArr[0].c() - rVarArr[2].c();
        float d2 = rVarArr[0].d() - rVarArr[2].d();
        float c3 = (rVarArr[0].c() + rVarArr[2].c()) / 2.0f;
        float d3 = (rVarArr[0].d() + rVarArr[2].d()) / 2.0f;
        float f4 = c2 * f3;
        float f5 = d2 * f3;
        r rVar = new r(c3 + f4, d3 + f5);
        r rVar2 = new r(c3 - f4, d3 - f5);
        float c4 = rVarArr[1].c() - rVarArr[3].c();
        float d4 = rVarArr[1].d() - rVarArr[3].d();
        float c5 = (rVarArr[1].c() + rVarArr[3].c()) / 2.0f;
        float d5 = (rVarArr[1].d() + rVarArr[3].d()) / 2.0f;
        float f6 = c4 * f3;
        float f7 = f3 * d4;
        return new r[]{rVar, new r(c5 + f6, d5 + f7), rVar2, new r(c5 - f6, d5 - f7)};
    }

    private void e(r[] rVarArr) {
        int i2;
        long j;
        long j2;
        if (!o(rVarArr[0]) || !o(rVarArr[1]) || !o(rVarArr[2]) || !o(rVarArr[3])) {
            throw l.a();
        }
        int i3 = this.e * 2;
        int[] iArr = {r(rVarArr[0], rVarArr[1], i3), r(rVarArr[1], rVarArr[2], i3), r(rVarArr[2], rVarArr[3], i3), r(rVarArr[3], rVarArr[0], i3)};
        this.f = m(iArr, i3);
        long j3 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            int i5 = iArr[(this.f + i4) % 4];
            if (this.b) {
                j = j3 << 7;
                j2 = (i5 >> 1) & 127;
            } else {
                j = j3 << 10;
                j2 = ((i5 >> 2) & 992) + ((i5 >> 1) & 31);
            }
            j3 = j + j2;
        }
        int h = h(j3, this.b);
        if (this.b) {
            this.f1267c = (h >> 6) + 1;
            i2 = h & 63;
        } else {
            this.f1267c = (h >> 11) + 1;
            i2 = h & 2047;
        }
        this.f1268d = i2 + 1;
    }

    private r[] f(C0045a c0045a) {
        this.e = 1;
        C0045a c0045a2 = c0045a;
        C0045a c0045a3 = c0045a2;
        C0045a c0045a4 = c0045a3;
        C0045a c0045a5 = c0045a4;
        boolean z = true;
        while (this.e < 9) {
            C0045a j = j(c0045a2, z, 1, -1);
            C0045a j2 = j(c0045a3, z, 1, 1);
            C0045a j3 = j(c0045a4, z, -1, 1);
            C0045a j4 = j(c0045a5, z, -1, -1);
            if (this.e > 2) {
                double c2 = (c(j4, j) * this.e) / (c(c0045a5, c0045a2) * (this.e + 2));
                if (c2 < 0.75d || c2 > 1.25d || !p(j, j2, j3, j4)) {
                    break;
                }
            }
            z = !z;
            this.e++;
            c0045a5 = j4;
            c0045a2 = j;
            c0045a3 = j2;
            c0045a4 = j3;
        }
        int i2 = this.e;
        if (i2 != 5 && i2 != 7) {
            throw l.a();
        }
        this.b = i2 == 5;
        r[] rVarArr = {new r(c0045a2.a() + 0.5f, c0045a2.b() - 0.5f), new r(c0045a3.a() + 0.5f, c0045a3.b() + 0.5f), new r(c0045a4.a() - 0.5f, c0045a4.b() + 0.5f), new r(c0045a5.a() - 0.5f, c0045a5.b() - 0.5f)};
        int i3 = this.e;
        return d(rVarArr, (i3 * 2) - 3, i3 * 2);
    }

    private int g(C0045a c0045a, C0045a c0045a2) {
        float c2 = c(c0045a, c0045a2);
        float a = (c0045a2.a() - c0045a.a()) / c2;
        float b = (c0045a2.b() - c0045a.b()) / c2;
        float a2 = c0045a.a();
        float b2 = c0045a.b();
        boolean d2 = this.a.d(c0045a.a(), c0045a.b());
        int i2 = 0;
        for (int i3 = 0; i3 < c2; i3++) {
            a2 += a;
            b2 += b;
            if (this.a.d(c.a.a.x.m.a.c(a2), c.a.a.x.m.a.c(b2)) != d2) {
                i2++;
            }
        }
        float f = i2 / c2;
        if (f <= 0.1f || f >= 0.9f) {
            return (f <= 0.1f) == d2 ? 1 : -1;
        }
        return 0;
    }

    private static int h(long j, boolean z) {
        int i2;
        int i3;
        if (z) {
            i2 = 7;
            i3 = 2;
        } else {
            i2 = 10;
            i3 = 4;
        }
        int i4 = i2 - i3;
        int[] iArr = new int[i2];
        for (int i5 = i2 - 1; i5 >= 0; i5--) {
            iArr[i5] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new c(c.a.a.x.n.a.k).a(iArr, i4);
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                i6 = (i6 << 4) + iArr[i7];
            }
            return i6;
        } catch (e unused) {
            throw l.a();
        }
    }

    private int i() {
        if (this.b) {
            return (this.f1267c * 4) + 11;
        }
        int i2 = this.f1267c;
        return i2 <= 4 ? (i2 * 4) + 15 : (i2 * 4) + ((((i2 - 4) / 8) + 1) * 2) + 15;
    }

    private C0045a j(C0045a c0045a, boolean z, int i2, int i3) {
        int a = c0045a.a() + i2;
        int b = c0045a.b();
        while (true) {
            b += i3;
            if (!n(a, b) || this.a.d(a, b) != z) {
                break;
            }
            a += i2;
        }
        int i4 = a - i2;
        int i5 = b - i3;
        while (n(i4, i5) && this.a.d(i4, i5) == z) {
            i4 += i2;
        }
        int i6 = i4 - i2;
        while (n(i6, i5) && this.a.d(i6, i5) == z) {
            i5 += i3;
        }
        return new C0045a(i6, i5 - i3);
    }

    private C0045a k() {
        r c2;
        r rVar;
        r rVar2;
        r rVar3;
        r c3;
        r c4;
        r c5;
        r c6;
        try {
            r[] c7 = new c.a.a.x.m.b(this.a).c();
            rVar2 = c7[0];
            rVar3 = c7[1];
            rVar = c7[2];
            c2 = c7[3];
        } catch (l unused) {
            int j = this.a.j() / 2;
            int g2 = this.a.g() / 2;
            int i2 = j + 7;
            int i3 = g2 - 7;
            r c8 = j(new C0045a(i2, i3), false, 1, -1).c();
            int i4 = g2 + 7;
            r c9 = j(new C0045a(i2, i4), false, 1, 1).c();
            int i5 = j - 7;
            r c10 = j(new C0045a(i5, i4), false, -1, 1).c();
            c2 = j(new C0045a(i5, i3), false, -1, -1).c();
            rVar = c10;
            rVar2 = c8;
            rVar3 = c9;
        }
        int c11 = c.a.a.x.m.a.c((((rVar2.c() + c2.c()) + rVar3.c()) + rVar.c()) / 4.0f);
        int c12 = c.a.a.x.m.a.c((((rVar2.d() + c2.d()) + rVar3.d()) + rVar.d()) / 4.0f);
        try {
            r[] c13 = new c.a.a.x.m.b(this.a, 15, c11, c12).c();
            c3 = c13[0];
            c4 = c13[1];
            c5 = c13[2];
            c6 = c13[3];
        } catch (l unused2) {
            int i6 = c11 + 7;
            int i7 = c12 - 7;
            c3 = j(new C0045a(i6, i7), false, 1, -1).c();
            int i8 = c12 + 7;
            c4 = j(new C0045a(i6, i8), false, 1, 1).c();
            int i9 = c11 - 7;
            c5 = j(new C0045a(i9, i8), false, -1, 1).c();
            c6 = j(new C0045a(i9, i7), false, -1, -1).c();
        }
        return new C0045a(c.a.a.x.m.a.c((((c3.c() + c6.c()) + c4.c()) + c5.c()) / 4.0f), c.a.a.x.m.a.c((((c3.d() + c6.d()) + c4.d()) + c5.d()) / 4.0f));
    }

    private r[] l(r[] rVarArr) {
        return d(rVarArr, this.e * 2, i());
    }

    private static int m(int[] iArr, int i2) {
        int i3 = 0;
        for (int i4 : iArr) {
            i3 = (i3 << 3) + ((i4 >> (i2 - 2)) << 1) + (i4 & 1);
        }
        int i5 = ((i3 & 1) << 11) + (i3 >> 1);
        for (int i6 = 0; i6 < 4; i6++) {
            if (Integer.bitCount(g[i6] ^ i5) <= 2) {
                return i6;
            }
        }
        throw l.a();
    }

    private boolean n(int i2, int i3) {
        return i2 >= 0 && i2 < this.a.j() && i3 > 0 && i3 < this.a.g();
    }

    private boolean o(r rVar) {
        return n(c.a.a.x.m.a.c(rVar.c()), c.a.a.x.m.a.c(rVar.d()));
    }

    private boolean p(C0045a c0045a, C0045a c0045a2, C0045a c0045a3, C0045a c0045a4) {
        C0045a c0045a5 = new C0045a(c0045a.a() - 3, c0045a.b() + 3);
        C0045a c0045a6 = new C0045a(c0045a2.a() - 3, c0045a2.b() - 3);
        C0045a c0045a7 = new C0045a(c0045a3.a() + 3, c0045a3.b() - 3);
        C0045a c0045a8 = new C0045a(c0045a4.a() + 3, c0045a4.b() + 3);
        int g2 = g(c0045a8, c0045a5);
        return g2 != 0 && g(c0045a5, c0045a6) == g2 && g(c0045a6, c0045a7) == g2 && g(c0045a7, c0045a8) == g2;
    }

    private b q(b bVar, r rVar, r rVar2, r rVar3, r rVar4) {
        i b = i.b();
        int i2 = i();
        float f = i2 / 2.0f;
        int i3 = this.e;
        float f2 = f - i3;
        float f3 = f + i3;
        return b.c(bVar, i2, i2, f2, f2, f3, f2, f3, f3, f2, f3, rVar.c(), rVar.d(), rVar2.c(), rVar2.d(), rVar3.c(), rVar3.d(), rVar4.c(), rVar4.d());
    }

    private int r(r rVar, r rVar2, int i2) {
        float b = b(rVar, rVar2);
        float f = b / i2;
        float c2 = rVar.c();
        float d2 = rVar.d();
        float c3 = ((rVar2.c() - rVar.c()) * f) / b;
        float d3 = (f * (rVar2.d() - rVar.d())) / b;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            float f2 = i4;
            if (this.a.d(c.a.a.x.m.a.c((f2 * c3) + c2), c.a.a.x.m.a.c((f2 * d3) + d2))) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    public c.a.a.v.a a(boolean z) {
        r[] f = f(k());
        if (z) {
            r rVar = f[0];
            f[0] = f[2];
            f[2] = rVar;
        }
        e(f);
        b bVar = this.a;
        int i2 = this.f;
        return new c.a.a.v.a(q(bVar, f[i2 % 4], f[(i2 + 1) % 4], f[(i2 + 2) % 4], f[(i2 + 3) % 4]), l(f), this.b, this.f1268d, this.f1267c);
    }
}
