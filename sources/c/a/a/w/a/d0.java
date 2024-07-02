package c.a.a.w.a;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class d0 extends u {
    private static final Pattern e = Pattern.compile("[a-zA-Z0-9]{2,}:");
    private static final Pattern f = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean n(String str) {
        if (str.contains(" ")) {
            return false;
        }
        Matcher matcher = e.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        Matcher matcher2 = f.matcher(str);
        return matcher2.find() && matcher2.start() == 0;
    }

    @Override // c.a.a.w.a.u
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public c0 h(c.a.a.p pVar) {
        String b = u.b(pVar);
        if (b.startsWith("URL:") || b.startsWith("URI:")) {
            return new c0(b.substring(4).trim(), null);
        }
        String trim = b.trim();
        if (n(trim)) {
            return new c0(trim, null);
        }
        return null;
    }
}
