package c.a.a.w.a;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public abstract class u {
    private static final u[] a = {new f(), new c(), new j(), new b(), new f0(), new e(), new g0(), new i(), new y(), new a0(), new v(), new x(), new n(), new k0(), new e0(), new d0(), new p(), new t(), new l(), new i0()};
    private static final Pattern b = Pattern.compile("\\d+");

    /* renamed from: c */
    private static final Pattern f1297c = Pattern.compile("&");

    /* renamed from: d */
    private static final Pattern f1298d = Pattern.compile("=");

    private static void a(CharSequence charSequence, Map<String, String> map) {
        String[] split = f1298d.split(charSequence, 2);
        if (split.length == 2) {
            try {
                map.put(split[0], m(split[1]));
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public static String b(c.a.a.p pVar) {
        String f = pVar.f();
        return f.startsWith("\ufeff") ? f.substring(1) : f;
    }

    public static boolean c(CharSequence charSequence, int i2) {
        return charSequence != null && i2 > 0 && i2 == charSequence.length() && b.matcher(charSequence).matches();
    }

    public static boolean d(CharSequence charSequence, int i2, int i3) {
        int i4;
        return charSequence != null && i3 > 0 && charSequence.length() >= (i4 = i3 + i2) && b.matcher(charSequence.subSequence(i2, i4)).matches();
    }

    public static String[] e(String str, String str2, char c2, boolean z) {
        int length = str2.length();
        ArrayList arrayList = null;
        int i2 = 0;
        while (i2 < length) {
            int indexOf = str2.indexOf(str, i2);
            if (indexOf < 0) {
                break;
            }
            int length2 = indexOf + str.length();
            ArrayList arrayList2 = arrayList;
            boolean z2 = true;
            int i3 = length2;
            while (z2) {
                int indexOf2 = str2.indexOf(c2, i3);
                if (indexOf2 < 0) {
                    i3 = str2.length();
                } else if (str2.charAt(indexOf2 - 1) == '\\') {
                    i3 = indexOf2 + 1;
                } else {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(3);
                    }
                    String l = l(str2.substring(length2, indexOf2));
                    if (z) {
                        l = l.trim();
                    }
                    if (!l.isEmpty()) {
                        arrayList2.add(l);
                    }
                    i3 = indexOf2 + 1;
                }
                z2 = false;
            }
            i2 = i3;
            arrayList = arrayList2;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String f(String str, String str2, char c2, boolean z) {
        String[] e = e(str, str2, c2, z);
        if (e == null) {
            return null;
        }
        return e[0];
    }

    public static String[] g(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    public static int i(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    public static Map<String, String> j(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return null;
        }
        HashMap hashMap = new HashMap(3);
        for (String str2 : f1297c.split(str.substring(indexOf + 1))) {
            a(str2, hashMap);
        }
        return hashMap;
    }

    public static q k(c.a.a.p pVar) {
        for (u uVar : a) {
            q h = uVar.h(pVar);
            if (h != null) {
                return h;
            }
        }
        return new b0(pVar.f(), null);
    }

    protected static String l(String str) {
        int indexOf = str.indexOf(92);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length - 1);
        sb.append(str.toCharArray(), 0, indexOf);
        boolean z = false;
        while (indexOf < length) {
            char charAt = str.charAt(indexOf);
            if (z || charAt != '\\') {
                sb.append(charAt);
                z = false;
            } else {
                z = true;
            }
            indexOf++;
        }
        return sb.toString();
    }

    public static String m(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public abstract q h(c.a.a.p pVar);
}
