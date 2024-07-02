package c.a.a.a0;

import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class p extends q {
    private final x[] a;

    /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public p(java.util.Map<c.a.a.e, ?> r3) {
        /*
            r2 = this;
            r2.<init>()
            if (r3 != 0) goto L7
            r3 = 0
            goto Lf
        L7:
            c.a.a.e r0 = c.a.a.e.POSSIBLE_FORMATS
            java.lang.Object r3 = r3.get(r0)
            java.util.Collection r3 = (java.util.Collection) r3
        Lf:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r3 == 0) goto L55
            c.a.a.a r1 = c.a.a.a.EAN_13
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L27
            c.a.a.a0.h r1 = new c.a.a.a0.h
            r1.<init>()
        L23:
            r0.add(r1)
            goto L35
        L27:
            c.a.a.a r1 = c.a.a.a.UPC_A
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L35
            c.a.a.a0.s r1 = new c.a.a.a0.s
            r1.<init>()
            goto L23
        L35:
            c.a.a.a r1 = c.a.a.a.EAN_8
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto L45
            c.a.a.a0.j r1 = new c.a.a.a0.j
            r1.<init>()
            r0.add(r1)
        L45:
            c.a.a.a r1 = c.a.a.a.UPC_E
            boolean r3 = r3.contains(r1)
            if (r3 == 0) goto L55
            c.a.a.a0.z r3 = new c.a.a.a0.z
            r3.<init>()
            r0.add(r3)
        L55:
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L73
            c.a.a.a0.h r3 = new c.a.a.a0.h
            r3.<init>()
            r0.add(r3)
            c.a.a.a0.j r3 = new c.a.a.a0.j
            r3.<init>()
            r0.add(r3)
            c.a.a.a0.z r3 = new c.a.a.a0.z
            r3.<init>()
            r0.add(r3)
        L73:
            int r3 = r0.size()
            c.a.a.a0.x[] r3 = new c.a.a.a0.x[r3]
            java.lang.Object[] r3 = r0.toArray(r3)
            c.a.a.a0.x[] r3 = (c.a.a.a0.x[]) r3
            r2.a = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a0.p.<init>(java.util.Map):void");
    }

    @Override // c.a.a.a0.q, c.a.a.n
    public void b() {
        for (x xVar : this.a) {
            xVar.b();
        }
    }

    @Override // c.a.a.a0.q
    public c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        int[] p = x.p(aVar);
        for (x xVar : this.a) {
            try {
                c.a.a.p m = xVar.m(i2, aVar, p, map);
                boolean z = m.b() == c.a.a.a.EAN_13 && m.f().charAt(0) == '0';
                Collection collection = map == null ? null : (Collection) map.get(c.a.a.e.POSSIBLE_FORMATS);
                boolean z2 = collection == null || collection.contains(c.a.a.a.UPC_A);
                if (!z || !z2) {
                    return m;
                }
                c.a.a.p pVar = new c.a.a.p(m.f().substring(1), m.c(), m.e(), c.a.a.a.UPC_A);
                pVar.g(m.d());
                return pVar;
            } catch (c.a.a.o unused) {
            }
        }
        throw c.a.a.l.a();
    }
}
