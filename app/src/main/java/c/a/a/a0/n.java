package c.a.a.a0;

import java.util.Map;

/* loaded from: classes.dex */
public final class n extends r {
    private static final int[] a = {1, 1, 1, 1};
    private static final int[] b = {3, 1, 1};

    @Override // c.a.a.a0.r, c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<c.a.a.g, ?> map) {
        if (aVar == c.a.a.a.ITF) {
            return super.a(str, aVar, i2, i3, map);
        }
        throw new IllegalArgumentException("Can only encode ITF, but got " + aVar);
    }

    @Override // c.a.a.a0.r
    public boolean[] c(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The lenght of the input should be even");
        }
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        }
        boolean[] zArr = new boolean[(length * 9) + 9];
        int b2 = r.b(zArr, 0, a, true);
        for (int i2 = 0; i2 < length; i2 += 2) {
            int digit = Character.digit(str.charAt(i2), 10);
            int digit2 = Character.digit(str.charAt(i2 + 1), 10);
            int[] iArr = new int[18];
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                int[][] iArr2 = m.e;
                iArr[i4] = iArr2[digit][i3];
                iArr[i4 + 1] = iArr2[digit2][i3];
            }
            b2 += r.b(zArr, b2, iArr, true);
        }
        r.b(zArr, b2, b, true);
        return zArr;
    }
}
