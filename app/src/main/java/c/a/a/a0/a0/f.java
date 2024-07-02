package c.a.a.a0.a0;

/* loaded from: classes.dex */
public final class f {
    private static int a(int i2, int i3) {
        int i4 = i2 - i3;
        if (i4 > i3) {
            i4 = i3;
            i3 = i4;
        }
        int i5 = 1;
        int i6 = 1;
        while (i2 > i3) {
            i5 *= i2;
            if (i6 <= i4) {
                i5 /= i6;
                i6++;
            }
            i2--;
        }
        while (i6 <= i4) {
            i5 /= i6;
            i6++;
        }
        return i5;
    }

    public static int b(int[] iArr, int i2, boolean z) {
        int[] iArr2 = iArr;
        int length = iArr2.length;
        int i3 = 0;
        for (int i4 : iArr2) {
            i3 += i4;
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            int i8 = length - 1;
            if (i5 >= i8) {
                return i6;
            }
            int i9 = 1 << i5;
            i7 |= i9;
            int i10 = 1;
            while (i10 < iArr2[i5]) {
                int i11 = i3 - i10;
                int i12 = length - i5;
                int i13 = i12 - 2;
                int a = a(i11 - 1, i13);
                if (z && i7 == 0) {
                    int i14 = i12 - 1;
                    if (i11 - i14 >= i14) {
                        a -= a(i11 - i12, i13);
                    }
                }
                if (i12 - 1 > 1) {
                    int i15 = 0;
                    for (int i16 = i11 - i13; i16 > i2; i16--) {
                        i15 += a((i11 - i16) - 1, i12 - 3);
                    }
                    a -= i15 * (i8 - i5);
                } else if (i11 > i2) {
                    a--;
                }
                i6 += a;
                i10++;
                i7 &= i9 ^ (-1);
                iArr2 = iArr;
            }
            i3 -= i10;
            i5++;
            iArr2 = iArr;
        }
    }
}
