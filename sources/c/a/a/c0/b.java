package c.a.a.c0;

import c.a.a.c0.e.c;
import c.a.a.c0.e.f;
import c.a.a.g;
import c.a.a.t;
import java.util.Map;

/* loaded from: classes.dex */
public final class b implements t {
    private static c.a.a.x.b b(f fVar, int i2, int i3, int i4) {
        c.a.a.c0.e.b a = fVar.a();
        if (a == null) {
            throw new IllegalStateException();
        }
        int e = a.e();
        int d2 = a.d();
        int i5 = i4 * 2;
        int i6 = e + i5;
        int i7 = i5 + d2;
        int max = Math.max(i2, i6);
        int max2 = Math.max(i3, i7);
        int min = Math.min(max / i6, max2 / i7);
        int i8 = (max - (e * min)) / 2;
        int i9 = (max2 - (d2 * min)) / 2;
        c.a.a.x.b bVar = new c.a.a.x.b(max, max2);
        int i10 = 0;
        while (i10 < d2) {
            int i11 = i8;
            int i12 = 0;
            while (i12 < e) {
                if (a.b(i12, i10) == 1) {
                    bVar.m(i11, i9, min, min);
                }
                i12++;
                i11 += min;
            }
            i10++;
            i9 += min;
        }
        return bVar;
    }

    @Override // c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<g, ?> map) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (aVar != c.a.a.a.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + aVar);
        }
        if (i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i2 + 'x' + i3);
        }
        c.a.a.c0.c.f fVar = c.a.a.c0.c.f.L;
        int i4 = 4;
        if (map != null) {
            c.a.a.c0.c.f fVar2 = (c.a.a.c0.c.f) map.get(g.ERROR_CORRECTION);
            if (fVar2 != null) {
                fVar = fVar2;
            }
            Integer num = (Integer) map.get(g.MARGIN);
            if (num != null) {
                i4 = num.intValue();
            }
        }
        return b(c.m(str, fVar, map), i2, i3, i4);
    }
}
