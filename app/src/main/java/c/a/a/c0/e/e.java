package c.a.a.c0.e;

import bsh.org.objectweb.asm.Constants;
import c.a.a.c0.c.j;
import c.a.a.u;

/* loaded from: classes.dex */
final class e {
    private static final int[][] a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};

    /* renamed from: c, reason: collision with root package name */
    private static final int[][] f1244c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, Constants.I2F, -1}, new int[]{6, 34, 60, 86, 112, Constants.L2D, -1}, new int[]{6, 30, 58, 86, 114, Constants.D2I, -1}, new int[]{6, 34, 62, 90, 118, Constants.I2C, -1}, new int[]{6, 30, 54, 78, 102, 126, Constants.FCMPG}, new int[]{6, 24, 50, 76, 102, 128, Constants.IFNE}, new int[]{6, 28, 54, 80, 106, 132, Constants.IFLE}, new int[]{6, 32, 58, 84, 110, Constants.L2I, Constants.IF_ICMPGE}, new int[]{6, 26, 54, 82, 110, Constants.L2D, Constants.IF_ACMPNE}, new int[]{6, 30, 58, 86, 114, Constants.D2I, Constants.TABLESWITCH}};

    /* renamed from: d, reason: collision with root package name */
    private static final int[][] f1245d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(c.a.a.x.a aVar, c.a.a.c0.c.f fVar, j jVar, int i2, b bVar) {
        c(bVar);
        d(jVar, bVar);
        l(fVar, i2, bVar);
        s(jVar, bVar);
        f(aVar, i2, bVar);
    }

    static int b(int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int n = n(i3);
        int i4 = i2 << (n - 1);
        while (n(i4) >= n) {
            i4 ^= i3 << (n(i4) - n);
        }
        return i4;
    }

    static void c(b bVar) {
        bVar.a((byte) -1);
    }

    static void d(j jVar, b bVar) {
        j(bVar);
        e(bVar);
        r(jVar, bVar);
        k(bVar);
    }

    private static void e(b bVar) {
        if (bVar.b(8, bVar.d() - 8) == 0) {
            throw new u();
        }
        bVar.f(8, bVar.d() - 8, 1);
    }

    static void f(c.a.a.x.a aVar, int i2, b bVar) {
        boolean z;
        int e = bVar.e() - 1;
        int d2 = bVar.d() - 1;
        int i3 = 0;
        int i4 = -1;
        while (e > 0) {
            if (e == 6) {
                e--;
            }
            while (d2 >= 0 && d2 < bVar.d()) {
                for (int i5 = 0; i5 < 2; i5++) {
                    int i6 = e - i5;
                    if (o(bVar.b(i6, d2))) {
                        if (i3 < aVar.k()) {
                            z = aVar.g(i3);
                            i3++;
                        } else {
                            z = false;
                        }
                        if (i2 != -1 && d.f(i2, i6, d2)) {
                            z = !z;
                        }
                        bVar.g(i6, d2, z);
                    }
                }
                d2 += i4;
            }
            i4 = -i4;
            d2 += i4;
            e -= 2;
        }
        if (i3 == aVar.k()) {
            return;
        }
        throw new u("Not all bits consumed: " + i3 + '/' + aVar.k());
    }

    private static void g(int i2, int i3, b bVar) {
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = i2 + i4;
            if (!o(bVar.b(i5, i3))) {
                throw new u();
            }
            bVar.f(i5, i3, 0);
        }
    }

    private static void h(int i2, int i3, b bVar) {
        for (int i4 = 0; i4 < 5; i4++) {
            for (int i5 = 0; i5 < 5; i5++) {
                bVar.f(i2 + i5, i3 + i4, b[i4][i5]);
            }
        }
    }

    private static void i(int i2, int i3, b bVar) {
        for (int i4 = 0; i4 < 7; i4++) {
            for (int i5 = 0; i5 < 7; i5++) {
                bVar.f(i2 + i5, i3 + i4, a[i4][i5]);
            }
        }
    }

    private static void j(b bVar) {
        int length = a[0].length;
        i(0, 0, bVar);
        i(bVar.e() - length, 0, bVar);
        i(0, bVar.e() - length, bVar);
        g(0, 7, bVar);
        g(bVar.e() - 8, 7, bVar);
        g(0, bVar.e() - 8, bVar);
        m(7, 0, bVar);
        m((bVar.d() - 7) - 1, 0, bVar);
        m(7, bVar.d() - 7, bVar);
    }

    private static void k(b bVar) {
        int i2 = 8;
        while (i2 < bVar.e() - 8) {
            int i3 = i2 + 1;
            int i4 = i3 % 2;
            if (o(bVar.b(i2, 6))) {
                bVar.f(i2, 6, i4);
            }
            if (o(bVar.b(6, i2))) {
                bVar.f(6, i2, i4);
            }
            i2 = i3;
        }
    }

    static void l(c.a.a.c0.c.f fVar, int i2, b bVar) {
        c.a.a.x.a aVar = new c.a.a.x.a();
        p(fVar, i2, aVar);
        for (int i3 = 0; i3 < aVar.k(); i3++) {
            boolean g = aVar.g((aVar.k() - 1) - i3);
            int[][] iArr = f1245d;
            bVar.g(iArr[i3][0], iArr[i3][1], g);
            if (i3 < 8) {
                bVar.g((bVar.e() - i3) - 1, 8, g);
            } else {
                bVar.g(8, (bVar.d() - 7) + (i3 - 8), g);
            }
        }
    }

    private static void m(int i2, int i3, b bVar) {
        for (int i4 = 0; i4 < 7; i4++) {
            int i5 = i3 + i4;
            if (!o(bVar.b(i2, i5))) {
                throw new u();
            }
            bVar.f(i2, i5, 0);
        }
    }

    static int n(int i2) {
        int i3 = 0;
        while (i2 != 0) {
            i2 >>>= 1;
            i3++;
        }
        return i3;
    }

    private static boolean o(int i2) {
        return i2 == -1;
    }

    static void p(c.a.a.c0.c.f fVar, int i2, c.a.a.x.a aVar) {
        if (!f.b(i2)) {
            throw new u("Invalid mask pattern");
        }
        int b2 = (fVar.b() << 3) | i2;
        aVar.c(b2, 5);
        aVar.c(b(b2, 1335), 10);
        c.a.a.x.a aVar2 = new c.a.a.x.a();
        aVar2.c(21522, 15);
        aVar.s(aVar2);
        if (aVar.k() == 15) {
            return;
        }
        throw new u("should not happen but we got: " + aVar.k());
    }

    static void q(j jVar, c.a.a.x.a aVar) {
        aVar.c(jVar.j(), 6);
        aVar.c(b(jVar.j(), 7973), 12);
        if (aVar.k() == 18) {
            return;
        }
        throw new u("should not happen but we got: " + aVar.k());
    }

    private static void r(j jVar, b bVar) {
        if (jVar.j() < 2) {
            return;
        }
        int j = jVar.j() - 1;
        int[][] iArr = f1244c;
        int[] iArr2 = iArr[j];
        int length = iArr[j].length;
        for (int i2 = 0; i2 < length; i2++) {
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = iArr2[i2];
                int i5 = iArr2[i3];
                if (i5 != -1 && i4 != -1 && o(bVar.b(i5, i4))) {
                    h(i5 - 2, i4 - 2, bVar);
                }
            }
        }
    }

    static void s(j jVar, b bVar) {
        if (jVar.j() < 7) {
            return;
        }
        c.a.a.x.a aVar = new c.a.a.x.a();
        q(jVar, aVar);
        int i2 = 17;
        for (int i3 = 0; i3 < 6; i3++) {
            for (int i4 = 0; i4 < 3; i4++) {
                boolean g = aVar.g(i2);
                i2--;
                bVar.g(i3, (bVar.d() - 11) + i4, g);
                bVar.g((bVar.d() - 11) + i4, i3, g);
            }
        }
    }
}
