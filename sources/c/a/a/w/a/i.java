package c.a.a.w.a;

import androidx.core.net.MailTo;
import java.util.Map;

/* loaded from: classes.dex */
public final class i extends u {
    @Override // c.a.a.w.a.u
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public h h(c.a.a.p pVar) {
        String str;
        String b = u.b(pVar);
        String str2 = null;
        if (!b.startsWith(MailTo.MAILTO_SCHEME) && !b.startsWith("MAILTO:")) {
            if (!j.p(b)) {
                return null;
            }
            return new h(b, null, null, MailTo.MAILTO_SCHEME + b);
        }
        String substring = b.substring(7);
        int indexOf = substring.indexOf(63);
        if (indexOf >= 0) {
            substring = substring.substring(0, indexOf);
        }
        String m = u.m(substring);
        Map<String, String> j = u.j(b);
        if (j != null) {
            if (m.isEmpty()) {
                m = j.get("to");
            }
            str2 = j.get("subject");
            str = j.get("body");
        } else {
            str = null;
        }
        return new h(m, str2, str, b);
    }
}
