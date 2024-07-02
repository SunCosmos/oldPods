package c.a.a.c0;

import c.a.a.c;
import c.a.a.c0.c.e;
import c.a.a.c0.c.i;
import c.a.a.l;
import c.a.a.n;
import c.a.a.p;
import c.a.a.q;
import c.a.a.r;
import c.a.a.x.g;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class a implements n {
    private static final r[] b = new r[0];
    private final e a = new e();

    private static c.a.a.x.b c(c.a.a.x.b bVar) {
        int[] i2 = bVar.i();
        int[] e = bVar.e();
        if (i2 == null || e == null) {
            throw l.a();
        }
        float d2 = d(i2, bVar);
        int i3 = i2[1];
        int i4 = e[1];
        int i5 = i2[0];
        int i6 = e[0];
        if (i5 >= i6 || i3 >= i4) {
            throw l.a();
        }
        int i7 = i4 - i3;
        if (i7 != i6 - i5) {
            i6 = i5 + i7;
        }
        int round = Math.round(((i6 - i5) + 1) / d2);
        int round2 = Math.round((i7 + 1) / d2);
        if (round <= 0 || round2 <= 0) {
            throw l.a();
        }
        if (round2 != round) {
            throw l.a();
        }
        int i8 = (int) (d2 / 2.0f);
        int i9 = i3 + i8;
        int i10 = i5 + i8;
        int i11 = (((int) ((round - 1) * d2)) + i10) - (i6 - 1);
        if (i11 > 0) {
            if (i11 > i8) {
                throw l.a();
            }
            i10 -= i11;
        }
        int i12 = (((int) ((round2 - 1) * d2)) + i9) - (i4 - 1);
        if (i12 > 0) {
            if (i12 > i8) {
                throw l.a();
            }
            i9 -= i12;
        }
        c.a.a.x.b bVar2 = new c.a.a.x.b(round, round2);
        for (int i13 = 0; i13 < round2; i13++) {
            int i14 = ((int) (i13 * d2)) + i9;
            for (int i15 = 0; i15 < round; i15++) {
                if (bVar.d(((int) (i15 * d2)) + i10, i14)) {
                    bVar2.l(i15, i13);
                }
            }
        }
        return bVar2;
    }

    private static float d(int[] iArr, c.a.a.x.b bVar) {
        int g = bVar.g();
        int j = bVar.j();
        int i2 = iArr[0];
        boolean z = true;
        int i3 = iArr[1];
        int i4 = 0;
        while (i2 < j && i3 < g) {
            if (z != bVar.d(i2, i3)) {
                i4++;
                if (i4 == 5) {
                    break;
                }
                z = !z;
            }
            i2++;
            i3++;
        }
        if (i2 == j || i3 == g) {
            throw l.a();
        }
        return (i2 - iArr[0]) / 7.0f;
    }

    @Override // c.a.a.n
    public final p a(c cVar, Map<c.a.a.e, ?> map) {
        r[] b2;
        c.a.a.x.e eVar;
        if (map == null || !map.containsKey(c.a.a.e.PURE_BARCODE)) {
            g e = new c.a.a.c0.d.c(cVar.a()).e(map);
            c.a.a.x.e b3 = this.a.b(e.a(), map);
            b2 = e.b();
            eVar = b3;
        } else {
            eVar = this.a.b(c(cVar.a()), map);
            b2 = b;
        }
        if (eVar.c() instanceof i) {
            ((i) eVar.c()).a(b2);
        }
        p pVar = new p(eVar.g(), eVar.d(), b2, c.a.a.a.QR_CODE);
        List<byte[]> a = eVar.a();
        if (a != null) {
            pVar.h(q.BYTE_SEGMENTS, a);
        }
        String b4 = eVar.b();
        if (b4 != null) {
            pVar.h(q.ERROR_CORRECTION_LEVEL, b4);
        }
        if (eVar.h()) {
            pVar.h(q.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(eVar.f()));
            pVar.h(q.STRUCTURED_APPEND_PARITY, Integer.valueOf(eVar.e()));
        }
        return pVar;
    }

    @Override // c.a.a.n
    public void b() {
    }
}
