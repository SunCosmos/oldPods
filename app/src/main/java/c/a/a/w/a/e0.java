package c.a.a.w.a;

/* loaded from: classes.dex */
public final class e0 extends u {
    @Override // c.a.a.w.a.u
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public c0 h(c.a.a.p pVar) {
        int indexOf;
        String b = u.b(pVar);
        if ((b.startsWith("urlto:") || b.startsWith("URLTO:")) && (indexOf = b.indexOf(58, 6)) >= 0) {
            return new c0(b.substring(indexOf + 1), indexOf > 6 ? b.substring(6, indexOf) : null);
        }
        return null;
    }
}
