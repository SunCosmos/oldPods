package c.a.a.z;

import c.a.a.e;
import c.a.a.l;
import c.a.a.n;
import c.a.a.p;
import c.a.a.q;
import c.a.a.r;
import c.a.a.x.b;
import c.a.a.z.b.c;
import java.util.Map;

/* loaded from: classes.dex */
public final class a implements n {
    private static final r[] b = new r[0];
    private final c a = new c();

    private static b c(b bVar) {
        int[] f = bVar.f();
        if (f == null) {
            throw l.a();
        }
        int i2 = f[0];
        int i3 = f[1];
        int i4 = f[2];
        int i5 = f[3];
        b bVar2 = new b(30, 33);
        for (int i6 = 0; i6 < 33; i6++) {
            int i7 = (((i6 * i5) + (i5 / 2)) / 33) + i3;
            for (int i8 = 0; i8 < 30; i8++) {
                if (bVar.d(((((i8 * i4) + (i4 / 2)) + (((i6 & 1) * i4) / 2)) / 30) + i2, i7)) {
                    bVar2.l(i8, i6);
                }
            }
        }
        return bVar2;
    }

    @Override // c.a.a.n
    public p a(c.a.a.c cVar, Map<e, ?> map) {
        if (map == null || !map.containsKey(e.PURE_BARCODE)) {
            throw l.a();
        }
        c.a.a.x.e b2 = this.a.b(c(cVar.a()), map);
        p pVar = new p(b2.g(), b2.d(), b, c.a.a.a.MAXICODE);
        String b3 = b2.b();
        if (b3 != null) {
            pVar.h(q.ERROR_CORRECTION_LEVEL, b3);
        }
        return pVar;
    }

    @Override // c.a.a.n
    public void b() {
    }
}
