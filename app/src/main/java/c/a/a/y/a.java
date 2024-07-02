package c.a.a.y;

import c.a.a.c;
import c.a.a.e;
import c.a.a.l;
import c.a.a.n;
import c.a.a.p;
import c.a.a.q;
import c.a.a.r;
import c.a.a.x.g;
import c.a.a.y.c.d;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class a implements n {
    private static final r[] b = new r[0];
    private final d a = new d();

    private static c.a.a.x.b c(c.a.a.x.b bVar) {
        int[] i2 = bVar.i();
        int[] e = bVar.e();
        if (i2 == null || e == null) {
            throw l.a();
        }
        int d2 = d(i2, bVar);
        int i3 = i2[1];
        int i4 = e[1];
        int i5 = i2[0];
        int i6 = ((e[0] - i5) + 1) / d2;
        int i7 = ((i4 - i3) + 1) / d2;
        if (i6 <= 0 || i7 <= 0) {
            throw l.a();
        }
        int i8 = d2 / 2;
        int i9 = i3 + i8;
        int i10 = i5 + i8;
        c.a.a.x.b bVar2 = new c.a.a.x.b(i6, i7);
        for (int i11 = 0; i11 < i7; i11++) {
            int i12 = (i11 * d2) + i9;
            for (int i13 = 0; i13 < i6; i13++) {
                if (bVar.d((i13 * d2) + i10, i12)) {
                    bVar2.l(i13, i11);
                }
            }
        }
        return bVar2;
    }

    private static int d(int[] iArr, c.a.a.x.b bVar) {
        int j = bVar.j();
        int i2 = iArr[0];
        int i3 = iArr[1];
        while (i2 < j && bVar.d(i2, i3)) {
            i2++;
        }
        if (i2 == j) {
            throw l.a();
        }
        int i4 = i2 - iArr[0];
        if (i4 != 0) {
            return i4;
        }
        throw l.a();
    }

    @Override // c.a.a.n
    public p a(c cVar, Map<e, ?> map) {
        r[] b2;
        c.a.a.x.e eVar;
        if (map == null || !map.containsKey(e.PURE_BARCODE)) {
            g c2 = new c.a.a.y.d.a(cVar.a()).c();
            c.a.a.x.e b3 = this.a.b(c2.a());
            b2 = c2.b();
            eVar = b3;
        } else {
            eVar = this.a.b(c(cVar.a()));
            b2 = b;
        }
        p pVar = new p(eVar.g(), eVar.d(), b2, c.a.a.a.DATA_MATRIX);
        List<byte[]> a = eVar.a();
        if (a != null) {
            pVar.h(q.BYTE_SEGMENTS, a);
        }
        String b4 = eVar.b();
        if (b4 != null) {
            pVar.h(q.ERROR_CORRECTION_LEVEL, b4);
        }
        return pVar;
    }

    @Override // c.a.a.n
    public void b() {
    }
}
