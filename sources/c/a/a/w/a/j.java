package c.a.a.w.a;

import androidx.core.net.MailTo;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class j extends a {
    private static final Pattern e = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean p(String str) {
        return str != null && e.matcher(str).matches() && str.indexOf(64) >= 0;
    }

    @Override // c.a.a.w.a.u
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public h h(c.a.a.p pVar) {
        String[] n;
        String b = u.b(pVar);
        if (!b.startsWith("MATMSG:") || (n = a.n("TO:", b, true)) == null) {
            return null;
        }
        String str = n[0];
        if (!p(str)) {
            return null;
        }
        return new h(str, a.o("SUB:", b, false), a.o("BODY:", b, false), MailTo.MAILTO_SCHEME + str);
    }
}
