package c.a.a.w.a;

/* loaded from: classes.dex */
public final class k0 extends u {
    @Override // c.a.a.w.a.u
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public j0 h(c.a.a.p pVar) {
        String f;
        String b = u.b(pVar);
        if (!b.startsWith("WIFI:") || (f = u.f("S:", b, ';', false)) == null || f.isEmpty()) {
            return null;
        }
        String f2 = u.f("P:", b, ';', false);
        String f3 = u.f("T:", b, ';', false);
        if (f3 == null) {
            f3 = "nopass";
        }
        return new j0(f3, f, f2, Boolean.parseBoolean(u.f("H:", b, ';', false)));
    }
}
