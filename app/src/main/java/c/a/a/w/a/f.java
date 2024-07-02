package c.a.a.w.a;

/* loaded from: classes.dex */
public final class f extends a {
    @Override // c.a.a.w.a.u
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public c0 h(c.a.a.p pVar) {
        String f = pVar.f();
        if (!f.startsWith("MEBKM:")) {
            return null;
        }
        String o = a.o("TITLE:", f, true);
        String[] n = a.n("URL:", f, true);
        if (n == null) {
            return null;
        }
        String str = n[0];
        if (d0.n(str)) {
            return new c0(str, o);
        }
        return null;
    }
}
