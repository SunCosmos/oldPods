package c.a.a.w.a;

/* loaded from: classes.dex */
public final class p extends u {
    @Override // c.a.a.w.a.u
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public o h(c.a.a.p pVar) {
        if (pVar.b() != c.a.a.a.EAN_13) {
            return null;
        }
        String b = u.b(pVar);
        if (b.length() != 13) {
            return null;
        }
        if (b.startsWith("978") || b.startsWith("979")) {
            return new o(b);
        }
        return null;
    }
}
