package c.a.a.w.a;

import androidx.core.net.MailTo;
import java.util.List;

/* loaded from: classes.dex */
public final class g0 extends u {
    private static String n(CharSequence charSequence, String str, boolean z) {
        List<String> q = f0.q(charSequence, str, z, false);
        if (q == null || q.isEmpty()) {
            return null;
        }
        return q.get(0);
    }

    private static String[] o(CharSequence charSequence, String str, boolean z) {
        List<List<String>> r = f0.r(charSequence, str, z, false);
        if (r == null || r.isEmpty()) {
            return null;
        }
        int size = r.size();
        String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = r.get(i2).get(0);
        }
        return strArr;
    }

    private static String q(String str) {
        return str != null ? (str.startsWith(MailTo.MAILTO_SCHEME) || str.startsWith("MAILTO:")) ? str.substring(7) : str : str;
    }

    @Override // c.a.a.w.a.u
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public g h(c.a.a.p pVar) {
        double parseDouble;
        String b = u.b(pVar);
        if (b.indexOf("BEGIN:VEVENT") < 0) {
            return null;
        }
        String n = n("SUMMARY", b, true);
        String n2 = n("DTSTART", b, true);
        if (n2 == null) {
            return null;
        }
        String n3 = n("DTEND", b, true);
        String n4 = n("DURATION", b, true);
        String n5 = n("LOCATION", b, true);
        String q = q(n("ORGANIZER", b, true));
        String[] o = o("ATTENDEE", b, true);
        if (o != null) {
            for (int i2 = 0; i2 < o.length; i2++) {
                o[i2] = q(o[i2]);
            }
        }
        String n6 = n("DESCRIPTION", b, true);
        String n7 = n("GEO", b, true);
        double d2 = Double.NaN;
        if (n7 == null) {
            parseDouble = Double.NaN;
        } else {
            int indexOf = n7.indexOf(59);
            if (indexOf < 0) {
                return null;
            }
            try {
                d2 = Double.parseDouble(n7.substring(0, indexOf));
                parseDouble = Double.parseDouble(n7.substring(indexOf + 1));
            } catch (NumberFormatException | IllegalArgumentException unused) {
                return null;
            }
        }
        return new g(n, n2, n3, n4, n5, q, o, n6, d2, parseDouble);
    }
}
