package c.a.a.w.a;

import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class c0 extends q {
    private final String a;
    private final String b;

    static {
        Pattern.compile(":/*([^/@]+)@[^/]+");
    }

    public c0(String str, String str2) {
        super(r.URI);
        this.a = e(str);
        this.b = str2;
    }

    private static boolean d(String str, int i2) {
        int i3 = i2 + 1;
        int indexOf = str.indexOf(47, i3);
        if (indexOf < 0) {
            indexOf = str.length();
        }
        return u.d(str, i3, indexOf - i3);
    }

    private static String e(String str) {
        StringBuilder sb;
        String trim = str.trim();
        int indexOf = trim.indexOf(58);
        if (indexOf < 0) {
            sb = new StringBuilder();
        } else {
            if (!d(trim, indexOf)) {
                return trim;
            }
            sb = new StringBuilder();
        }
        sb.append("http://");
        sb.append(trim);
        return sb.toString();
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(30);
        q.b(this.b, sb);
        q.b(this.a, sb);
        return sb.toString();
    }
}
