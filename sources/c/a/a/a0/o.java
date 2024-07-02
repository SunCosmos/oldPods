package c.a.a.a0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class o extends q {
    private final q[] a;

    public o(Map<c.a.a.e, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(c.a.a.e.POSSIBLE_FORMATS);
        boolean z = (map == null || map.get(c.a.a.e.ASSUME_CODE_39_CHECK_DIGIT) == null) ? false : true;
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(c.a.a.a.EAN_13) || collection.contains(c.a.a.a.UPC_A) || collection.contains(c.a.a.a.EAN_8) || collection.contains(c.a.a.a.UPC_E)) {
                arrayList.add(new p(map));
            }
            if (collection.contains(c.a.a.a.CODE_39)) {
                arrayList.add(new e(z));
            }
            if (collection.contains(c.a.a.a.CODE_93)) {
                arrayList.add(new g());
            }
            if (collection.contains(c.a.a.a.CODE_128)) {
                arrayList.add(new c());
            }
            if (collection.contains(c.a.a.a.ITF)) {
                arrayList.add(new m());
            }
            if (collection.contains(c.a.a.a.CODABAR)) {
                arrayList.add(new a());
            }
            if (collection.contains(c.a.a.a.RSS_14)) {
                arrayList.add(new c.a.a.a0.a0.e());
            }
            if (collection.contains(c.a.a.a.RSS_EXPANDED)) {
                arrayList.add(new c.a.a.a0.a0.g.d());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new p(map));
            arrayList.add(new e());
            arrayList.add(new a());
            arrayList.add(new g());
            arrayList.add(new c());
            arrayList.add(new m());
            arrayList.add(new c.a.a.a0.a0.e());
            arrayList.add(new c.a.a.a0.a0.g.d());
        }
        this.a = (q[]) arrayList.toArray(new q[arrayList.size()]);
    }

    @Override // c.a.a.a0.q, c.a.a.n
    public void b() {
        for (q qVar : this.a) {
            qVar.b();
        }
    }

    @Override // c.a.a.a0.q
    public c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        for (q qVar : this.a) {
            try {
                return qVar.c(i2, aVar, map);
            } catch (c.a.a.o unused) {
            }
        }
        throw c.a.a.l.a();
    }
}
