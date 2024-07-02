package c.a.a.w.a;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class e extends a {
    private static String p(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        return str + ' ' + str2;
    }

    private static String[] q(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList(3);
        if (str != null) {
            arrayList.add(str);
        }
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (str3 != null) {
            arrayList.add(str3);
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[size]);
    }

    @Override // c.a.a.w.a.u
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public d h(c.a.a.p pVar) {
        String b = u.b(pVar);
        if (!b.startsWith("BIZCARD:")) {
            return null;
        }
        String p = p(a.o("N:", b, true), a.o("X:", b, true));
        String o = a.o("T:", b, true);
        String o2 = a.o("C:", b, true);
        return new d(u.g(p), null, null, q(a.o("B:", b, true), a.o("M:", b, true), a.o("F:", b, true)), null, u.g(a.o("E:", b, true)), null, null, null, a.n("A:", b, true), null, o2, null, o, null, null);
    }
}
