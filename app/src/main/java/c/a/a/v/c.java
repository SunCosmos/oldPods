package c.a.a.v;

import c.a.a.g;
import c.a.a.t;
import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes.dex */
public final class c implements t {
    private static final Charset a = Charset.forName("ISO-8859-1");

    private static c.a.a.x.b b(String str, c.a.a.a aVar, int i2, int i3, Charset charset, int i4, int i5) {
        if (aVar == c.a.a.a.AZTEC) {
            return c(c.a.a.v.f.c.d(str.getBytes(charset), i4, i5), i2, i3);
        }
        throw new IllegalArgumentException("Can only encode AZTEC, but got " + aVar);
    }

    private static c.a.a.x.b c(c.a.a.v.f.a aVar, int i2, int i3) {
        c.a.a.x.b a2 = aVar.a();
        if (a2 == null) {
            throw new IllegalStateException();
        }
        int j = a2.j();
        int g = a2.g();
        int max = Math.max(i2, j);
        int max2 = Math.max(i3, g);
        int min = Math.min(max / j, max2 / g);
        int i4 = (max - (j * min)) / 2;
        int i5 = (max2 - (g * min)) / 2;
        c.a.a.x.b bVar = new c.a.a.x.b(max, max2);
        int i6 = 0;
        while (i6 < g) {
            int i7 = i4;
            int i8 = 0;
            while (i8 < j) {
                if (a2.d(i8, i6)) {
                    bVar.m(i7, i5, min, min);
                }
                i8++;
                i7 += min;
            }
            i6++;
            i5 += min;
        }
        return bVar;
    }

    @Override // c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<g, ?> map) {
        String str2 = map == null ? null : (String) map.get(g.CHARACTER_SET);
        Number number = map == null ? null : (Number) map.get(g.ERROR_CORRECTION);
        Number number2 = map != null ? (Number) map.get(g.AZTEC_LAYERS) : null;
        return b(str, aVar, i2, i3, str2 == null ? a : Charset.forName(str2), number == null ? 33 : number.intValue(), number2 == null ? 0 : number2.intValue());
    }
}
