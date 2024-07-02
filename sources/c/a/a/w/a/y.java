package c.a.a.w.a;

import androidx.core.net.MailTo;

/* loaded from: classes.dex */
public final class y extends u {
    @Override // c.a.a.w.a.u
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public h h(c.a.a.p pVar) {
        String str;
        String b = u.b(pVar);
        String str2 = null;
        if (!b.startsWith("smtp:") && !b.startsWith("SMTP:")) {
            return null;
        }
        String substring = b.substring(5);
        int indexOf = substring.indexOf(58);
        if (indexOf >= 0) {
            String substring2 = substring.substring(indexOf + 1);
            substring = substring.substring(0, indexOf);
            int indexOf2 = substring2.indexOf(58);
            if (indexOf2 >= 0) {
                String substring3 = substring2.substring(indexOf2 + 1);
                str2 = substring2.substring(0, indexOf2);
                str = substring3;
            } else {
                str = null;
                str2 = substring2;
            }
        } else {
            str = null;
        }
        return new h(substring, str2, str, MailTo.MAILTO_SCHEME + substring);
    }
}
