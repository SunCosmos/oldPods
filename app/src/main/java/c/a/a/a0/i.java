package c.a.a.a0;

import java.util.Map;

/* loaded from: classes.dex */
public final class i extends y {
    @Override // c.a.a.a0.r, c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<c.a.a.g, ?> map) {
        if (aVar == c.a.a.a.EAN_13) {
            return super.a(str, aVar, i2, i3, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got " + aVar);
    }

    @Override // c.a.a.a0.r
    public boolean[] c(String str) {
        if (str.length() != 13) {
            throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + str.length());
        }
        try {
            if (!x.i(str)) {
                throw new IllegalArgumentException("Contents do not pass checksum");
            }
            int i2 = h.f1186i[Integer.parseInt(str.substring(0, 1))];
            boolean[] zArr = new boolean[95];
            int b = r.b(zArr, 0, x.f1191d, true) + 0;
            int i3 = 1;
            while (i3 <= 6) {
                int i4 = i3 + 1;
                int parseInt = Integer.parseInt(str.substring(i3, i4));
                if (((i2 >> (6 - i3)) & 1) == 1) {
                    parseInt += 10;
                }
                b += r.b(zArr, b, x.g[parseInt], false);
                i3 = i4;
            }
            int b2 = b + r.b(zArr, b, x.e, false);
            int i5 = 7;
            while (i5 <= 12) {
                int i6 = i5 + 1;
                b2 += r.b(zArr, b2, x.f[Integer.parseInt(str.substring(i5, i6))], true);
                i5 = i6;
            }
            r.b(zArr, b2, x.f1191d, true);
            return zArr;
        } catch (c.a.a.h unused) {
            throw new IllegalArgumentException("Illegal contents");
        }
    }
}
