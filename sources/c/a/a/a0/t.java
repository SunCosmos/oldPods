package c.a.a.a0;

import java.util.Map;

/* loaded from: classes.dex */
public final class t implements c.a.a.t {
    private final i a = new i();

    private static String b(String str) {
        int length = str.length();
        if (length == 11) {
            int i2 = 0;
            for (int i3 = 0; i3 < 11; i3++) {
                i2 += (str.charAt(i3) - '0') * (i3 % 2 == 0 ? 3 : 1);
            }
            str = str + ((1000 - i2) % 10);
        } else if (length != 12) {
            throw new IllegalArgumentException("Requested contents should be 11 or 12 digits long, but got " + str.length());
        }
        return '0' + str;
    }

    @Override // c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<c.a.a.g, ?> map) {
        if (aVar == c.a.a.a.UPC_A) {
            return this.a.a(b(str), c.a.a.a.EAN_13, i2, i3, map);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got " + aVar);
    }
}
