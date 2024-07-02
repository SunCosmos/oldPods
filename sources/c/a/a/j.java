package c.a.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class j implements n {
    private Map<e, ?> a;
    private n[] b;

    private p c(c cVar) {
        n[] nVarArr = this.b;
        if (nVarArr != null) {
            for (n nVar : nVarArr) {
                try {
                    return nVar.a(cVar, this.a);
                } catch (o unused) {
                }
            }
        }
        throw l.a();
    }

    @Override // c.a.a.n
    public p a(c cVar, Map<e, ?> map) {
        e(map);
        return c(cVar);
    }

    @Override // c.a.a.n
    public void b() {
        n[] nVarArr = this.b;
        if (nVarArr != null) {
            for (n nVar : nVarArr) {
                nVar.b();
            }
        }
    }

    public p d(c cVar) {
        if (this.b == null) {
            e(null);
        }
        return c(cVar);
    }

    public void e(Map<e, ?> map) {
        this.a = map;
        boolean z = true;
        boolean z2 = map != null && map.containsKey(e.TRY_HARDER);
        Collection collection = map == null ? null : (Collection) map.get(e.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (!collection.contains(a.UPC_A) && !collection.contains(a.UPC_E) && !collection.contains(a.EAN_13) && !collection.contains(a.EAN_8) && !collection.contains(a.CODABAR) && !collection.contains(a.CODE_39) && !collection.contains(a.CODE_93) && !collection.contains(a.CODE_128) && !collection.contains(a.ITF) && !collection.contains(a.RSS_14) && !collection.contains(a.RSS_EXPANDED)) {
                z = false;
            }
            if (z && !z2) {
                arrayList.add(new c.a.a.a0.o(map));
            }
            if (collection.contains(a.QR_CODE)) {
                arrayList.add(new c.a.a.c0.a());
            }
            if (collection.contains(a.DATA_MATRIX)) {
                arrayList.add(new c.a.a.y.a());
            }
            if (collection.contains(a.AZTEC)) {
                arrayList.add(new c.a.a.v.b());
            }
            if (collection.contains(a.PDF_417)) {
                arrayList.add(new c.a.a.b0.b());
            }
            if (collection.contains(a.MAXICODE)) {
                arrayList.add(new c.a.a.z.a());
            }
            if (z && z2) {
                arrayList.add(new c.a.a.a0.o(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (!z2) {
                arrayList.add(new c.a.a.a0.o(map));
            }
            arrayList.add(new c.a.a.c0.a());
            arrayList.add(new c.a.a.y.a());
            arrayList.add(new c.a.a.v.b());
            arrayList.add(new c.a.a.b0.b());
            arrayList.add(new c.a.a.z.a());
            if (z2) {
                arrayList.add(new c.a.a.a0.o(map));
            }
        }
        this.b = (n[]) arrayList.toArray(new n[arrayList.size()]);
    }
}
