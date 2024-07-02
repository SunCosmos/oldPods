package c.a.a.w.a;

/* loaded from: classes.dex */
public final class x extends u {
    @Override // c.a.a.w.a.u
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public w h(c.a.a.p pVar) {
        String str;
        String b = u.b(pVar);
        if (!b.startsWith("smsto:") && !b.startsWith("SMSTO:") && !b.startsWith("mmsto:") && !b.startsWith("MMSTO:")) {
            return null;
        }
        String substring = b.substring(6);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            str = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
        } else {
            str = null;
        }
        return new w(substring, (String) null, (String) null, str);
    }
}
