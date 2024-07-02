package c.a.a.x;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public final class j extends h {
    private b e;

    public j(c.a.a.i iVar) {
        super(iVar);
    }

    private static int[][] h(byte[] bArr, int i2, int i3, int i4, int i5) {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, i3, i2);
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i6 << 3;
            int i8 = i5 - 8;
            if (i7 > i8) {
                i7 = i8;
            }
            for (int i9 = 0; i9 < i2; i9++) {
                int i10 = i9 << 3;
                int i11 = i4 - 8;
                if (i10 > i11) {
                    i10 = i11;
                }
                int i12 = (i7 * i4) + i10;
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                int i16 = 255;
                while (i13 < 8) {
                    for (int i17 = 0; i17 < 8; i17++) {
                        int i18 = bArr[i12 + i17] & 255;
                        i14 += i18;
                        if (i18 < i16) {
                            i16 = i18;
                        }
                        if (i18 > i15) {
                            i15 = i18;
                        }
                    }
                    if (i15 - i16 <= 24) {
                        i13++;
                        i12 += i4;
                    }
                    while (true) {
                        i13++;
                        i12 += i4;
                        if (i13 < 8) {
                            for (int i19 = 0; i19 < 8; i19++) {
                                i14 += bArr[i12 + i19] & 255;
                            }
                        }
                    }
                    i13++;
                    i12 += i4;
                }
                int i20 = i14 >> 6;
                if (i15 - i16 <= 24) {
                    i20 = i16 / 2;
                    if (i6 > 0 && i9 > 0) {
                        int i21 = i6 - 1;
                        int i22 = i9 - 1;
                        int i23 = ((iArr[i21][i9] + (iArr[i6][i22] * 2)) + iArr[i21][i22]) / 4;
                        if (i16 < i23) {
                            i20 = i23;
                        }
                        iArr[i6][i9] = i20;
                    }
                }
                iArr[i6][i9] = i20;
            }
        }
        return iArr;
    }

    private static void i(byte[] bArr, int i2, int i3, int i4, int i5, int[][] iArr, b bVar) {
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i6 << 3;
            int i8 = i5 - 8;
            if (i7 > i8) {
                i7 = i8;
            }
            for (int i9 = 0; i9 < i2; i9++) {
                int i10 = i9 << 3;
                int i11 = i4 - 8;
                if (i10 <= i11) {
                    i11 = i10;
                }
                int j = j(i9, 2, i2 - 3);
                int j2 = j(i6, 2, i3 - 3);
                int i12 = 0;
                for (int i13 = -2; i13 <= 2; i13++) {
                    int[] iArr2 = iArr[j2 + i13];
                    i12 += iArr2[j - 2] + iArr2[j - 1] + iArr2[j] + iArr2[j + 1] + iArr2[j + 2];
                }
                k(bArr, i11, i7, i12 / 25, i4, bVar);
            }
        }
    }

    private static int j(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    private static void k(byte[] bArr, int i2, int i3, int i4, int i5, b bVar) {
        int i6 = (i3 * i5) + i2;
        int i7 = 0;
        while (i7 < 8) {
            for (int i8 = 0; i8 < 8; i8++) {
                if ((bArr[i6 + i8] & 255) <= i4) {
                    bVar.l(i2 + i8, i3 + i7);
                }
            }
            i7++;
            i6 += i5;
        }
    }

    @Override // c.a.a.x.h, c.a.a.b
    public b a() {
        b a;
        b bVar = this.e;
        if (bVar != null) {
            return bVar;
        }
        c.a.a.i d2 = d();
        int d3 = d2.d();
        int a2 = d2.a();
        if (d3 < 40 || a2 < 40) {
            a = super.a();
        } else {
            byte[] b = d2.b();
            int i2 = d3 >> 3;
            if ((d3 & 7) != 0) {
                i2++;
            }
            int i3 = i2;
            int i4 = a2 >> 3;
            if ((a2 & 7) != 0) {
                i4++;
            }
            int i5 = i4;
            int[][] h = h(b, i3, i5, d3, a2);
            a = new b(d3, a2);
            i(b, i3, i5, d3, a2, h, a);
        }
        this.e = a;
        return this.e;
    }
}
