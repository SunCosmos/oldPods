package c.a.a.w.a;

/* loaded from: classes.dex */
public final class a0 extends u {
    @Override // c.a.a.w.a.u
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public z h(c.a.a.p pVar) {
        String str;
        String b = u.b(pVar);
        if (!b.startsWith("tel:") && !b.startsWith("TEL:")) {
            return null;
        }
        if (b.startsWith("TEL:")) {
            str = "tel:" + b.substring(4);
        } else {
            str = b;
        }
        int indexOf = b.indexOf(63, 4);
        return new z(indexOf < 0 ? b.substring(4) : b.substring(4, indexOf), str, null);
    }
}
