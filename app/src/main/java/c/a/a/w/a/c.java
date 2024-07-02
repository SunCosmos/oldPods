package c.a.a.w.a;

/* loaded from: classes.dex */
public final class c extends a {
    private static String q(String str) {
        int indexOf = str.indexOf(44);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(indexOf + 1) + ' ' + str.substring(0, indexOf);
    }

    @Override // c.a.a.w.a.u
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public d h(c.a.a.p pVar) {
        String[] n;
        String b = u.b(pVar);
        if (!b.startsWith("MECARD:") || (n = a.n("N:", b, true)) == null) {
            return null;
        }
        String q = q(n[0]);
        String o = a.o("SOUND:", b, true);
        String[] n2 = a.n("TEL:", b, true);
        String[] n3 = a.n("EMAIL:", b, true);
        String o2 = a.o("NOTE:", b, false);
        String[] n4 = a.n("ADR:", b, true);
        String o3 = a.o("BDAY:", b, true);
        return new d(u.g(q), null, o, n2, null, n3, null, null, o2, n4, null, a.o("ORG:", b, true), !u.c(o3, 8) ? null : o3, null, a.n("URL:", b, true), null);
    }
}
