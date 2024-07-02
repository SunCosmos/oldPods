package c.a.a.w.a;

/* loaded from: classes.dex */
public final class t extends u {
    @Override // c.a.a.w.a.u
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public s h(c.a.a.p pVar) {
        c.a.a.a b = pVar.b();
        if (b != c.a.a.a.UPC_A && b != c.a.a.a.UPC_E && b != c.a.a.a.EAN_8 && b != c.a.a.a.EAN_13) {
            return null;
        }
        String b2 = u.b(pVar);
        if (u.c(b2, b2.length())) {
            return new s(b2, (b == c.a.a.a.UPC_E && b2.length() == 8) ? c.a.a.a0.z.r(b2) : b2);
        }
        return null;
    }
}
