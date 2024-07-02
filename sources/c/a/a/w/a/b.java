package c.a.a.w.a;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class b extends u {
    private static String[] n(String str, int i2, String str2, boolean z) {
        ArrayList arrayList = null;
        for (int i3 = 1; i3 <= i2; i3++) {
            String f = u.f(str + i3 + ':', str2, '\r', z);
            if (f == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(i2);
            }
            arrayList.add(f);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // c.a.a.w.a.u
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public d h(c.a.a.p pVar) {
        String b = u.b(pVar);
        if (!b.contains("MEMORY") || !b.contains("\r\n")) {
            return null;
        }
        String f = u.f("NAME1:", b, '\r', true);
        String f2 = u.f("NAME2:", b, '\r', true);
        String[] n = n("TEL", 3, b, true);
        String[] n2 = n("MAIL", 3, b, true);
        String f3 = u.f("MEMORY:", b, '\r', false);
        String f4 = u.f("ADD:", b, '\r', true);
        return new d(u.g(f), null, f2, n, null, n2, null, null, f3, f4 != null ? new String[]{f4} : null, null, null, null, null, null, null);
    }
}
