package c.a.a.a0;

import java.util.Map;

/* loaded from: classes.dex */
public final class s extends x {
    private final x h = new h();

    private static c.a.a.p r(c.a.a.p pVar) {
        String f = pVar.f();
        if (f.charAt(0) == '0') {
            return new c.a.a.p(f.substring(1), null, pVar.e(), c.a.a.a.UPC_A);
        }
        throw c.a.a.h.a();
    }

    @Override // c.a.a.a0.q, c.a.a.n
    public c.a.a.p a(c.a.a.c cVar, Map<c.a.a.e, ?> map) {
        return r(this.h.a(cVar, map));
    }

    @Override // c.a.a.a0.x, c.a.a.a0.q
    public c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        return r(this.h.c(i2, aVar, map));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // c.a.a.a0.x
    public int l(c.a.a.x.a aVar, int[] iArr, StringBuilder sb) {
        return this.h.l(aVar, iArr, sb);
    }

    @Override // c.a.a.a0.x
    public c.a.a.p m(int i2, c.a.a.x.a aVar, int[] iArr, Map<c.a.a.e, ?> map) {
        return r(this.h.m(i2, aVar, iArr, map));
    }

    @Override // c.a.a.a0.x
    c.a.a.a q() {
        return c.a.a.a.UPC_A;
    }
}
