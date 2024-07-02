package c.a.a.a0;

import java.util.Map;

/* loaded from: classes.dex */
public final class k extends y {
    @Override // c.a.a.a0.r, c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<c.a.a.g, ?> map) {
        if (aVar == c.a.a.a.EAN_8) {
            return super.a(str, aVar, i2, i3, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_8, but got " + aVar);
    }

    @Override // c.a.a.a0.r
    public boolean[] c(String str) {
        if (str.length() != 8) {
            throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + str.length());
        }
        boolean[] zArr = new boolean[67];
        int b = r.b(zArr, 0, x.f1191d, true) + 0;
        int i2 = 0;
        while (i2 <= 3) {
            int i3 = i2 + 1;
            b += r.b(zArr, b, x.f[Integer.parseInt(str.substring(i2, i3))], false);
            i2 = i3;
        }
        int b2 = b + r.b(zArr, b, x.e, false);
        int i4 = 4;
        while (i4 <= 7) {
            int i5 = i4 + 1;
            b2 += r.b(zArr, b2, x.f[Integer.parseInt(str.substring(i4, i5))], true);
            i4 = i5;
        }
        r.b(zArr, b2, x.f1191d, true);
        return zArr;
    }
}
