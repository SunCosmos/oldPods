package c.a.a.v.f;

/* loaded from: classes.dex */
public final class c {
    private static final int[] a = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int[] a(c.a.a.x.a aVar, int i2, int i3) {
        int[] iArr = new int[i3];
        int k = aVar.k() / i2;
        for (int i4 = 0; i4 < k; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 |= aVar.g((i4 * i2) + i6) ? 1 << ((i2 - i6) - 1) : 0;
            }
            iArr[i4] = i5;
        }
        return iArr;
    }

    private static void b(c.a.a.x.b bVar, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4 += 2) {
            int i5 = i2 - i4;
            int i6 = i5;
            while (true) {
                int i7 = i2 + i4;
                if (i6 <= i7) {
                    bVar.l(i6, i5);
                    bVar.l(i6, i7);
                    bVar.l(i5, i6);
                    bVar.l(i7, i6);
                    i6++;
                }
            }
        }
        int i8 = i2 - i3;
        bVar.l(i8, i8);
        int i9 = i8 + 1;
        bVar.l(i9, i8);
        bVar.l(i8, i9);
        int i10 = i2 + i3;
        bVar.l(i10, i8);
        bVar.l(i10, i9);
        bVar.l(i10, i10 - 1);
    }

    private static void c(c.a.a.x.b bVar, boolean z, int i2, c.a.a.x.a aVar) {
        int i3 = i2 / 2;
        int i4 = 0;
        if (z) {
            while (i4 < 7) {
                int i5 = (i3 - 3) + i4;
                if (aVar.g(i4)) {
                    bVar.l(i5, i3 - 5);
                }
                if (aVar.g(i4 + 7)) {
                    bVar.l(i3 + 5, i5);
                }
                if (aVar.g(20 - i4)) {
                    bVar.l(i5, i3 + 5);
                }
                if (aVar.g(27 - i4)) {
                    bVar.l(i3 - 5, i5);
                }
                i4++;
            }
            return;
        }
        while (i4 < 10) {
            int i6 = (i3 - 5) + i4 + (i4 / 5);
            if (aVar.g(i4)) {
                bVar.l(i6, i3 - 7);
            }
            if (aVar.g(i4 + 10)) {
                bVar.l(i3 + 7, i6);
            }
            if (aVar.g(29 - i4)) {
                bVar.l(i6, i3 + 7);
            }
            if (aVar.g(39 - i4)) {
                bVar.l(i3 - 7, i6);
            }
            i4++;
        }
    }

    public static a d(byte[] bArr, int i2, int i3) {
        c.a.a.x.a aVar;
        int i4;
        boolean z;
        int i5;
        int i6;
        int i7;
        c.a.a.x.a a2 = new d(bArr).a();
        int k = ((a2.k() * i2) / 100) + 11;
        int k2 = a2.k() + k;
        int i8 = 0;
        int i9 = 1;
        if (i3 == 0) {
            c.a.a.x.a aVar2 = null;
            int i10 = 0;
            int i11 = 0;
            while (i10 <= 32) {
                boolean z2 = i10 <= 3;
                int i12 = z2 ? i10 + 1 : i10;
                int i13 = i(i12, z2);
                if (k2 <= i13) {
                    int[] iArr = a;
                    if (i11 != iArr[i12]) {
                        int i14 = iArr[i12];
                        i11 = i14;
                        aVar2 = h(a2, i14);
                    }
                    int i15 = i13 - (i13 % i11);
                    if ((!z2 || aVar2.k() <= i11 * 64) && aVar2.k() + k <= i15) {
                        aVar = aVar2;
                        i4 = i11;
                        z = z2;
                        i5 = i12;
                        i6 = i13;
                    }
                }
                i10++;
                i8 = 0;
                i9 = 1;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        z = i3 < 0;
        i5 = Math.abs(i3);
        if (i5 > (z ? 4 : 32)) {
            throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i3)));
        }
        i6 = i(i5, z);
        i4 = a[i5];
        int i16 = i6 - (i6 % i4);
        aVar = h(a2, i4);
        if (aVar.k() + k > i16) {
            throw new IllegalArgumentException("Data to large for user specified layer");
        }
        if (z && aVar.k() > i4 * 64) {
            throw new IllegalArgumentException("Data to large for user specified layer");
        }
        c.a.a.x.a e = e(aVar, i6, i4);
        int k3 = aVar.k() / i4;
        c.a.a.x.a f = f(z, i5, k3);
        int i17 = z ? (i5 * 4) + 11 : (i5 * 4) + 14;
        int[] iArr2 = new int[i17];
        int i18 = 2;
        if (z) {
            for (int i19 = 0; i19 < i17; i19++) {
                iArr2[i19] = i19;
            }
            i7 = i17;
        } else {
            int i20 = i17 / 2;
            i7 = i17 + 1 + (((i20 - 1) / 15) * 2);
            int i21 = i7 / 2;
            for (int i22 = 0; i22 < i20; i22++) {
                iArr2[(i20 - i22) - 1] = (i21 - r15) - 1;
                iArr2[i20 + i22] = (i22 / 15) + i22 + i21 + i9;
            }
        }
        c.a.a.x.b bVar = new c.a.a.x.b(i7);
        int i23 = 0;
        int i24 = 0;
        while (i23 < i5) {
            int i25 = (i5 - i23) * 4;
            int i26 = z ? i25 + 9 : i25 + 12;
            int i27 = 0;
            while (i27 < i26) {
                int i28 = i27 * 2;
                while (i8 < i18) {
                    if (e.g(i24 + i28 + i8)) {
                        int i29 = i23 * 2;
                        bVar.l(iArr2[i29 + i8], iArr2[i29 + i27]);
                    }
                    if (e.g((i26 * 2) + i24 + i28 + i8)) {
                        int i30 = i23 * 2;
                        bVar.l(iArr2[i30 + i27], iArr2[((i17 - 1) - i30) - i8]);
                    }
                    if (e.g((i26 * 4) + i24 + i28 + i8)) {
                        int i31 = (i17 - 1) - (i23 * 2);
                        bVar.l(iArr2[i31 - i8], iArr2[i31 - i27]);
                    }
                    if (e.g((i26 * 6) + i24 + i28 + i8)) {
                        int i32 = i23 * 2;
                        bVar.l(iArr2[((i17 - 1) - i32) - i27], iArr2[i32 + i8]);
                    }
                    i8++;
                    i18 = 2;
                }
                i27++;
                i8 = 0;
                i18 = 2;
            }
            i24 += i26 * 8;
            i23++;
            i8 = 0;
            i18 = 2;
        }
        c(bVar, z, i7, f);
        int i33 = i7 / 2;
        if (z) {
            b(bVar, i33, 5);
        } else {
            b(bVar, i33, 7);
            int i34 = 0;
            int i35 = 0;
            while (i35 < (i17 / 2) - 1) {
                for (int i36 = i33 & 1; i36 < i7; i36 += 2) {
                    int i37 = i33 - i34;
                    bVar.l(i37, i36);
                    int i38 = i33 + i34;
                    bVar.l(i38, i36);
                    bVar.l(i36, i37);
                    bVar.l(i36, i38);
                }
                i35 += 15;
                i34 += 16;
            }
        }
        a aVar3 = new a();
        aVar3.c(z);
        aVar3.f(i7);
        aVar3.d(i5);
        aVar3.b(k3);
        aVar3.e(bVar);
        return aVar3;
    }

    private static c.a.a.x.a e(c.a.a.x.a aVar, int i2, int i3) {
        int k = aVar.k() / i3;
        c.a.a.x.n.d dVar = new c.a.a.x.n.d(g(i3));
        int i4 = i2 / i3;
        int[] a2 = a(aVar, i3, i4);
        dVar.b(a2, i4 - k);
        c.a.a.x.a aVar2 = new c.a.a.x.a();
        aVar2.c(0, i2 % i3);
        for (int i5 : a2) {
            aVar2.c(i5, i3);
        }
        return aVar2;
    }

    static c.a.a.x.a f(boolean z, int i2, int i3) {
        int i4;
        c.a.a.x.a aVar = new c.a.a.x.a();
        int i5 = i2 - 1;
        if (z) {
            aVar.c(i5, 2);
            aVar.c(i3 - 1, 6);
            i4 = 28;
        } else {
            aVar.c(i5, 5);
            aVar.c(i3 - 1, 11);
            i4 = 40;
        }
        return e(aVar, i4, 4);
    }

    private static c.a.a.x.n.a g(int i2) {
        if (i2 == 4) {
            return c.a.a.x.n.a.k;
        }
        if (i2 == 6) {
            return c.a.a.x.n.a.j;
        }
        if (i2 == 8) {
            return c.a.a.x.n.a.n;
        }
        if (i2 == 10) {
            return c.a.a.x.n.a.f1315i;
        }
        if (i2 != 12) {
            return null;
        }
        return c.a.a.x.n.a.h;
    }

    static c.a.a.x.a h(c.a.a.x.a aVar, int i2) {
        c.a.a.x.a aVar2 = new c.a.a.x.a();
        int k = aVar.k();
        int i3 = (1 << i2) - 2;
        int i4 = 0;
        while (i4 < k) {
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = i4 + i6;
                if (i7 >= k || aVar.g(i7)) {
                    i5 |= 1 << ((i2 - 1) - i6);
                }
            }
            int i8 = i5 & i3;
            if (i8 != i3) {
                if (i8 == 0) {
                    i8 = i5 | 1;
                } else {
                    aVar2.c(i5, i2);
                    i4 += i2;
                }
            }
            aVar2.c(i8, i2);
            i4--;
            i4 += i2;
        }
        return aVar2;
    }

    private static int i(int i2, boolean z) {
        return ((z ? 88 : 112) + (i2 * 16)) * i2;
    }
}
