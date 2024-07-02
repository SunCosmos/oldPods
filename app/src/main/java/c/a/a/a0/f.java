package c.a.a.a0;

import java.util.Map;

/* loaded from: classes.dex */
public final class f extends r {
    private static void f(int i2, int[] iArr) {
        for (int i3 = 0; i3 < 9; i3++) {
            int i4 = 1;
            if (((1 << (8 - i3)) & i2) != 0) {
                i4 = 2;
            }
            iArr[i3] = i4;
        }
    }

    @Override // c.a.a.a0.r, c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<c.a.a.g, ?> map) {
        if (aVar == c.a.a.a.CODE_39) {
            return super.a(str, aVar, i2, i3, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_39, but got " + aVar);
    }

    @Override // c.a.a.a0.r
    public boolean[] c(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        }
        int[] iArr = new int[9];
        int i2 = length + 25;
        for (int i3 = 0; i3 < length; i3++) {
            int indexOf = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i3));
            if (indexOf < 0) {
                throw new IllegalArgumentException("Bad contents: " + str);
            }
            f(e.f[indexOf], iArr);
            for (int i4 = 0; i4 < 9; i4++) {
                i2 += iArr[i4];
            }
        }
        boolean[] zArr = new boolean[i2];
        f(e.f[39], iArr);
        int b = r.b(zArr, 0, iArr, true);
        int[] iArr2 = {1};
        int b2 = b + r.b(zArr, b, iArr2, false);
        for (int i5 = 0; i5 < length; i5++) {
            f(e.f["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i5))], iArr);
            int b3 = b2 + r.b(zArr, b2, iArr, true);
            b2 = b3 + r.b(zArr, b3, iArr2, false);
        }
        f(e.f[39], iArr);
        r.b(zArr, b2, iArr, true);
        return zArr;
    }
}
