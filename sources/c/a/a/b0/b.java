package c.a.a.b0;

import android.support.v7.widget.ActivityChooserView;
import c.a.a.b0.e.j;
import c.a.a.e;
import c.a.a.l;
import c.a.a.n;
import c.a.a.p;
import c.a.a.q;
import c.a.a.r;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public final class b implements n {
    private static p[] c(c.a.a.c cVar, Map<e, ?> map, boolean z) {
        ArrayList arrayList = new ArrayList();
        c.a.a.b0.f.b b = c.a.a.b0.f.a.b(cVar, map, z);
        for (r[] rVarArr : b.b()) {
            c.a.a.x.e i2 = j.i(b.a(), rVarArr[4], rVarArr[5], rVarArr[6], rVarArr[7], f(rVarArr), d(rVarArr));
            p pVar = new p(i2.g(), i2.d(), rVarArr, c.a.a.a.PDF_417);
            pVar.h(q.ERROR_CORRECTION_LEVEL, i2.b());
            c cVar2 = (c) i2.c();
            if (cVar2 != null) {
                pVar.h(q.PDF417_EXTRA_METADATA, cVar2);
            }
            arrayList.add(pVar);
        }
        return (p[]) arrayList.toArray(new p[arrayList.size()]);
    }

    private static int d(r[] rVarArr) {
        return Math.max(Math.max(e(rVarArr[0], rVarArr[4]), (e(rVarArr[6], rVarArr[2]) * 17) / 18), Math.max(e(rVarArr[1], rVarArr[5]), (e(rVarArr[7], rVarArr[3]) * 17) / 18));
    }

    private static int e(r rVar, r rVar2) {
        if (rVar == null || rVar2 == null) {
            return 0;
        }
        return (int) Math.abs(rVar.c() - rVar2.c());
    }

    private static int f(r[] rVarArr) {
        return Math.min(Math.min(g(rVarArr[0], rVarArr[4]), (g(rVarArr[6], rVarArr[2]) * 17) / 18), Math.min(g(rVarArr[1], rVarArr[5]), (g(rVarArr[7], rVarArr[3]) * 17) / 18));
    }

    private static int g(r rVar, r rVar2) {
        return (rVar == null || rVar2 == null) ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) Math.abs(rVar.c() - rVar2.c());
    }

    @Override // c.a.a.n
    public p a(c.a.a.c cVar, Map<e, ?> map) {
        p[] c2 = c(cVar, map, false);
        if (c2 == null || c2.length == 0 || c2[0] == null) {
            throw l.a();
        }
        return c2[0];
    }

    @Override // c.a.a.n
    public void b() {
    }
}
