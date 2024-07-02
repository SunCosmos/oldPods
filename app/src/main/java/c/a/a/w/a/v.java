package c.a.a.w.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class v extends u {
    private static void n(Collection<String> collection, Collection<String> collection2, String str) {
        int indexOf = str.indexOf(59);
        String str2 = null;
        if (indexOf < 0) {
            collection.add(str);
        } else {
            collection.add(str.substring(0, indexOf));
            String substring = str.substring(indexOf + 1);
            if (substring.startsWith("via=")) {
                str2 = substring.substring(4);
            }
        }
        collection2.add(str2);
    }

    @Override // c.a.a.w.a.u
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public w h(c.a.a.p pVar) {
        String str;
        String b = u.b(pVar);
        String str2 = null;
        if (!b.startsWith("sms:") && !b.startsWith("SMS:") && !b.startsWith("mms:") && !b.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> j = u.j(b);
        boolean z = false;
        if (j == null || j.isEmpty()) {
            str = null;
        } else {
            str2 = j.get("subject");
            str = j.get("body");
            z = true;
        }
        int indexOf = b.indexOf(63, 4);
        String substring = (indexOf < 0 || !z) ? b.substring(4) : b.substring(4, indexOf);
        int i2 = -1;
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        while (true) {
            int i3 = i2 + 1;
            int indexOf2 = substring.indexOf(44, i3);
            if (indexOf2 <= i2) {
                n(arrayList, arrayList2, substring.substring(i3));
                return new w((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str2, str);
            }
            n(arrayList, arrayList2, substring.substring(i3, indexOf2));
            i2 = indexOf2;
        }
    }
}
