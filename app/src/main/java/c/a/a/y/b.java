package c.a.a.y;

import c.a.a.f;
import c.a.a.g;
import c.a.a.t;
import c.a.a.y.e.e;
import c.a.a.y.e.i;
import c.a.a.y.e.j;
import c.a.a.y.e.k;
import c.a.a.y.e.l;
import java.util.Map;

/* loaded from: classes.dex */
public final class b implements t {
    private static c.a.a.x.b b(c.a.a.c0.e.b bVar) {
        int e = bVar.e();
        int d2 = bVar.d();
        c.a.a.x.b bVar2 = new c.a.a.x.b(e, d2);
        bVar2.a();
        for (int i2 = 0; i2 < e; i2++) {
            for (int i3 = 0; i3 < d2; i3++) {
                if (bVar.b(i2, i3) == 1) {
                    bVar2.l(i2, i3);
                }
            }
        }
        return bVar2;
    }

    private static c.a.a.x.b c(e eVar, k kVar) {
        int h = kVar.h();
        int g = kVar.g();
        c.a.a.c0.e.b bVar = new c.a.a.c0.e.b(kVar.j(), kVar.i());
        int i2 = 0;
        for (int i3 = 0; i3 < g; i3++) {
            if (i3 % kVar.e == 0) {
                int i4 = 0;
                for (int i5 = 0; i5 < kVar.j(); i5++) {
                    bVar.g(i4, i2, i5 % 2 == 0);
                    i4++;
                }
                i2++;
            }
            int i6 = 0;
            for (int i7 = 0; i7 < h; i7++) {
                if (i7 % kVar.f1335d == 0) {
                    bVar.g(i6, i2, true);
                    i6++;
                }
                bVar.g(i6, i2, eVar.e(i7, i3));
                i6++;
                int i8 = kVar.f1335d;
                if (i7 % i8 == i8 - 1) {
                    bVar.g(i6, i2, i3 % 2 == 0);
                    i6++;
                }
            }
            i2++;
            int i9 = kVar.e;
            if (i3 % i9 == i9 - 1) {
                int i10 = 0;
                for (int i11 = 0; i11 < kVar.j(); i11++) {
                    bVar.g(i10, i2, true);
                    i10++;
                }
                i2++;
            }
        }
        return b(bVar);
    }

    @Override // c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<g, ?> map) {
        f fVar;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (aVar != c.a.a.a.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got " + aVar);
        }
        if (i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i2 + 'x' + i3);
        }
        l lVar = l.FORCE_NONE;
        f fVar2 = null;
        if (map != null) {
            l lVar2 = (l) map.get(g.DATA_MATRIX_SHAPE);
            if (lVar2 != null) {
                lVar = lVar2;
            }
            f fVar3 = (f) map.get(g.MIN_SIZE);
            if (fVar3 == null) {
                fVar3 = null;
            }
            fVar = (f) map.get(g.MAX_SIZE);
            if (fVar == null) {
                fVar = null;
            }
            fVar2 = fVar3;
        } else {
            fVar = null;
        }
        String b = j.b(str, lVar, fVar2, fVar);
        k l = k.l(b.length(), lVar, fVar2, fVar, true);
        e eVar = new e(i.c(b, l), l.h(), l.g());
        eVar.h();
        return c(eVar, l);
    }
}
