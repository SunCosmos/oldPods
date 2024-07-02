package c.a.a.y.d;

import c.a.a.l;
import c.a.a.r;
import c.a.a.x.g;
import c.a.a.x.i;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class a {
    private final c.a.a.x.b a;
    private final c.a.a.x.m.b b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b {
        private final r a;
        private final r b;

        /* renamed from: c, reason: collision with root package name */
        private final int f1325c;

        private b(r rVar, r rVar2, int i2) {
            this.a = rVar;
            this.b = rVar2;
            this.f1325c = i2;
        }

        r a() {
            return this.a;
        }

        r b() {
            return this.b;
        }

        public int c() {
            return this.f1325c;
        }

        public String toString() {
            return this.a + "/" + this.b + '/' + this.f1325c;
        }
    }

    /* loaded from: classes.dex */
    private static final class c implements Comparator<b>, Serializable {
        private c() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            return bVar.c() - bVar2.c();
        }
    }

    public a(c.a.a.x.b bVar) {
        this.a = bVar;
        this.b = new c.a.a.x.m.b(bVar);
    }

    private r a(r rVar, r rVar2, r rVar3, r rVar4, int i2) {
        float f = i2;
        float d2 = d(rVar, rVar2) / f;
        float d3 = d(rVar3, rVar4);
        r rVar5 = new r(rVar4.c() + (((rVar4.c() - rVar3.c()) / d3) * d2), rVar4.d() + (d2 * ((rVar4.d() - rVar3.d()) / d3)));
        float d4 = d(rVar, rVar3) / f;
        float d5 = d(rVar2, rVar4);
        r rVar6 = new r(rVar4.c() + (((rVar4.c() - rVar2.c()) / d5) * d4), rVar4.d() + (d4 * ((rVar4.d() - rVar2.d()) / d5)));
        if (f(rVar5)) {
            return (f(rVar6) && Math.abs(h(rVar3, rVar5).c() - h(rVar2, rVar5).c()) > Math.abs(h(rVar3, rVar6).c() - h(rVar2, rVar6).c())) ? rVar6 : rVar5;
        }
        if (f(rVar6)) {
            return rVar6;
        }
        return null;
    }

    private r b(r rVar, r rVar2, r rVar3, r rVar4, int i2, int i3) {
        float d2 = d(rVar, rVar2) / i2;
        float d3 = d(rVar3, rVar4);
        r rVar5 = new r(rVar4.c() + (((rVar4.c() - rVar3.c()) / d3) * d2), rVar4.d() + (d2 * ((rVar4.d() - rVar3.d()) / d3)));
        float d4 = d(rVar, rVar3) / i3;
        float d5 = d(rVar2, rVar4);
        r rVar6 = new r(rVar4.c() + (((rVar4.c() - rVar2.c()) / d5) * d4), rVar4.d() + (d4 * ((rVar4.d() - rVar2.d()) / d5)));
        if (f(rVar5)) {
            return (f(rVar6) && Math.abs(i2 - h(rVar3, rVar5).c()) + Math.abs(i3 - h(rVar2, rVar5).c()) > Math.abs(i2 - h(rVar3, rVar6).c()) + Math.abs(i3 - h(rVar2, rVar6).c())) ? rVar6 : rVar5;
        }
        if (f(rVar6)) {
            return rVar6;
        }
        return null;
    }

    private static int d(r rVar, r rVar2) {
        return c.a.a.x.m.a.c(r.b(rVar, rVar2));
    }

    private static void e(Map<r, Integer> map, r rVar) {
        Integer num = map.get(rVar);
        map.put(rVar, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    private boolean f(r rVar) {
        return rVar.c() >= 0.0f && rVar.c() < ((float) this.a.j()) && rVar.d() > 0.0f && rVar.d() < ((float) this.a.g());
    }

    private static c.a.a.x.b g(c.a.a.x.b bVar, r rVar, r rVar2, r rVar3, r rVar4, int i2, int i3) {
        float f = i2 - 0.5f;
        float f2 = i3 - 0.5f;
        return i.b().c(bVar, i2, i3, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, rVar.c(), rVar.d(), rVar4.c(), rVar4.d(), rVar3.c(), rVar3.d(), rVar2.c(), rVar2.d());
    }

    private b h(r rVar, r rVar2) {
        int c2 = (int) rVar.c();
        int d2 = (int) rVar.d();
        int c3 = (int) rVar2.c();
        int d3 = (int) rVar2.d();
        int i2 = 0;
        boolean z = Math.abs(d3 - d2) > Math.abs(c3 - c2);
        if (z) {
            d2 = c2;
            c2 = d2;
            d3 = c3;
            c3 = d3;
        }
        int abs = Math.abs(c3 - c2);
        int abs2 = Math.abs(d3 - d2);
        int i3 = (-abs) / 2;
        int i4 = d2 < d3 ? 1 : -1;
        int i5 = c2 >= c3 ? -1 : 1;
        boolean d4 = this.a.d(z ? d2 : c2, z ? c2 : d2);
        while (c2 != c3) {
            boolean d5 = this.a.d(z ? d2 : c2, z ? c2 : d2);
            if (d5 != d4) {
                i2++;
                d4 = d5;
            }
            i3 += abs2;
            if (i3 > 0) {
                if (d2 == d3) {
                    break;
                }
                d2 += i4;
                i3 -= abs;
            }
            c2 += i5;
        }
        return new b(rVar, rVar2, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v3, types: [c.a.a.r] */
    /* JADX WARN: Type inference failed for: r16v3, types: [c.a.a.r] */
    /* JADX WARN: Type inference failed for: r22v0, types: [c.a.a.r] */
    /* JADX WARN: Type inference failed for: r23v0, types: [c.a.a.y.d.a] */
    /* JADX WARN: Type inference failed for: r2v4, types: [c.a.a.r[]] */
    /* JADX WARN: Type inference failed for: r4v6, types: [c.a.a.r[]] */
    /* JADX WARN: Type inference failed for: r6v2, types: [c.a.a.r] */
    public g c() {
        r rVar;
        c.a.a.x.b g;
        r[] c2 = this.b.c();
        r rVar2 = c2[0];
        r rVar3 = c2[1];
        r rVar4 = c2[2];
        r rVar5 = c2[3];
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(h(rVar2, rVar3));
        arrayList.add(h(rVar2, rVar4));
        arrayList.add(h(rVar3, rVar5));
        arrayList.add(h(rVar4, rVar5));
        C0046a c0046a = null;
        Collections.sort(arrayList, new c());
        b bVar = (b) arrayList.get(0);
        b bVar2 = (b) arrayList.get(1);
        HashMap hashMap = new HashMap();
        e(hashMap, bVar.a());
        e(hashMap, bVar.b());
        e(hashMap, bVar2.a());
        e(hashMap, bVar2.b());
        Object obj = null;
        Object obj2 = null;
        for (Map.Entry entry : hashMap.entrySet()) {
            ?? r16 = (r) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                obj = r16;
            } else if (c0046a == null) {
                c0046a = r16;
            } else {
                obj2 = r16;
            }
        }
        if (c0046a == null || obj == null || obj2 == null) {
            throw l.a();
        }
        ?? r4 = {c0046a, obj, obj2};
        r.e(r4);
        ?? r14 = r4[0];
        ?? r22 = r4[1];
        ?? r6 = r4[2];
        r rVar6 = !hashMap.containsKey(rVar2) ? rVar2 : !hashMap.containsKey(rVar3) ? rVar3 : !hashMap.containsKey(rVar4) ? rVar4 : rVar5;
        int c3 = h(r6, rVar6).c();
        int c4 = h(r14, rVar6).c();
        if ((c3 & 1) == 1) {
            c3++;
        }
        int i2 = c3 + 2;
        if ((c4 & 1) == 1) {
            c4++;
        }
        int i3 = c4 + 2;
        if (i2 * 4 >= i3 * 7 || i3 * 4 >= i2 * 7) {
            rVar = r6;
            r b2 = b(r22, r14, r6, rVar6, i2, i3);
            if (b2 != null) {
                rVar6 = b2;
            }
            int c5 = h(rVar, rVar6).c();
            int c6 = h(r14, rVar6).c();
            if ((c5 & 1) == 1) {
                c5++;
            }
            int i4 = c5;
            if ((c6 & 1) == 1) {
                c6++;
            }
            g = g(this.a, rVar, r22, r14, rVar6, i4, c6);
        } else {
            r a = a(r22, r14, r6, rVar6, Math.min(i3, i2));
            if (a != null) {
                rVar6 = a;
            }
            int max = Math.max(h(r6, rVar6).c(), h(r14, rVar6).c()) + 1;
            if ((max & 1) == 1) {
                max++;
            }
            int i5 = max;
            g = g(this.a, r6, r22, r14, rVar6, i5, i5);
            rVar = r6;
        }
        return new g(g, new r[]{rVar, r22, r14, rVar6});
    }
}
