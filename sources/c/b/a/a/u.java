package c.b.a.a;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class u {
    static String a(String str, int i2, int i3) {
        return str.substring(i2, i3);
    }

    private static String b(String str, int i2, int i3) {
        return str.substring(i2, i3);
    }

    private static int c(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    private static int d(String str, int i2, int i3, char c2) {
        while (i2 < i3) {
            if (str.charAt(i2) == c2) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    private static int e(String str, int i2, int i3, String str2) {
        while (i2 < i3) {
            if (str2.indexOf(str.charAt(i2)) != -1) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    private static List<String> f(String str, String str2) {
        int indexOf = str.indexOf(47, str2.length() + 3);
        int e = e(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < e) {
            int i2 = indexOf + 1;
            int d2 = d(str, i2, e, '/');
            arrayList.add(str.substring(i2, d2));
            indexOf = d2;
        }
        return arrayList;
    }

    private static boolean g(String str) {
        return str.equals(".") || str.equalsIgnoreCase("%2e");
    }

    private static boolean h(String str) {
        return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
    }

    public static String i(URI uri, String str) {
        String str2;
        int e;
        int c2;
        String str3;
        String str4;
        String scheme = uri.getScheme();
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("");
        int q = q(str, 0, str.length());
        int r = r(str, q, str.length());
        int p = p(str, q, r);
        if (p == -1) {
            if (scheme == null) {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
            }
            str2 = scheme;
        } else if (str.regionMatches(true, q, "https:", 0, 6)) {
            q += 6;
            str2 = "https";
        } else {
            if (!str.regionMatches(true, q, "http:", 0, 5)) {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + str.substring(0, p) + "'");
            }
            q += 5;
            str2 = "http";
        }
        int s = s(str, q, r);
        if (s >= 2 || !scheme.equals(str2)) {
            int i2 = q + s;
            while (true) {
                e = e(str, i2, r, "@/\\?#");
                char charAt = e != r ? str.charAt(e) : (char) 65535;
                if (charAt == 65535 || charAt == '#' || charAt == '/' || charAt == '\\' || charAt == '?') {
                    break;
                }
                if (charAt == '@') {
                    i2 = e + 1;
                }
            }
            int l = l(str, i2, e);
            int i3 = l + 1;
            if (i3 < e) {
                str3 = b(str, i2, l);
                int j = j(str, i3, e);
                if (j == -1) {
                    throw new IllegalArgumentException("Invalid URL port: \"" + str.substring(i3, e) + '\"');
                }
                c2 = j;
            } else {
                String b = b(str, i2, l);
                c2 = c(str2);
                str3 = b;
            }
            if (str3 == null) {
                throw new IllegalArgumentException("Invalid URL host: \"" + str.substring(i2, l) + '\"');
            }
            q = e;
            str4 = str3;
        } else {
            str4 = uri.getHost();
            c2 = uri.getPort();
            if (c2 == -1) {
                c2 = c(str2);
            }
            arrayList.clear();
            arrayList.addAll(f(uri.toString(), scheme));
        }
        int e2 = e(str, q, r, "?#");
        o(arrayList, str, q, e2);
        List<String> list = null;
        if (e2 < r && str.charAt(e2) == '?') {
            list = n(a(str, e2 + 1, d(str, e2, r, '#')));
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append("://");
        stringBuffer.append(str4);
        if ((c2 != 80 || !str2.equals("http")) && (c2 != 443 || !str2.equals("https"))) {
            stringBuffer.append(':');
            stringBuffer.append(c2);
        }
        for (String str5 : arrayList) {
            stringBuffer.append('/');
            stringBuffer.append(str5);
        }
        if (list != null) {
            char c3 = 0;
            for (String str6 : list) {
                if (c3 == 0) {
                    stringBuffer.append('?');
                    stringBuffer.append(str6);
                    c3 = 1;
                } else if (c3 == 1) {
                    stringBuffer.append('=');
                    stringBuffer.append(str6);
                    c3 = 2;
                } else if (c3 == 2) {
                    stringBuffer.append('&');
                    stringBuffer.append(str6);
                    c3 = 1;
                }
            }
        }
        return stringBuffer.toString();
    }

    private static int j(String str, int i2, int i3) {
        int parseInt;
        try {
            parseInt = Integer.parseInt(str.substring(i2, i3));
        } catch (NumberFormatException unused) {
        }
        if (parseInt <= 0 || parseInt > 65535) {
            return -1;
        }
        return parseInt;
    }

    private static void k(List<String> list) {
        if (!list.remove(list.size() - 1).isEmpty() || list.isEmpty()) {
            list.add("");
        } else {
            list.set(list.size() - 1, "");
        }
    }

    private static int l(String str, int i2, int i3) {
        while (i2 < i3) {
            char charAt = str.charAt(i2);
            if (charAt == ':') {
                return i2;
            }
            if (charAt != '[') {
                i2++;
            }
            do {
                i2++;
                if (i2 < i3) {
                }
                i2++;
            } while (str.charAt(i2) != ']');
            i2++;
        }
        return i3;
    }

    private static void m(List<String> list, String str, int i2, int i3, boolean z, boolean z2) {
        String a = a(str, i2, i3);
        if (g(a)) {
            return;
        }
        if (h(a)) {
            k(list);
            return;
        }
        if (list.get(list.size() - 1).isEmpty()) {
            list.set(list.size() - 1, a);
        } else {
            list.add(a);
        }
        if (z) {
            list.add("");
        }
    }

    static List<String> n(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int indexOf = str.indexOf(38, i2);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i2);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i2, indexOf));
                str2 = null;
            } else {
                arrayList.add(str.substring(i2, indexOf2));
                str2 = str.substring(indexOf2 + 1, indexOf);
            }
            arrayList.add(str2);
            i2 = indexOf + 1;
        }
        return arrayList;
    }

    private static void o(List<String> list, String str, int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        char charAt = str.charAt(i2);
        if (charAt == '/' || charAt == '\\') {
            list.clear();
            list.add("");
            i2++;
        } else {
            list.set(list.size() - 1, "");
        }
        while (true) {
            int i4 = i2;
            if (i4 >= i3) {
                return;
            }
            i2 = e(str, i4, i3, "/\\");
            boolean z = i2 < i3;
            m(list, str, i4, i2, z, true);
            if (z) {
                i2++;
            }
        }
    }

    private static int p(String str, int i2, int i3) {
        if (i3 - i2 < 2) {
            return -1;
        }
        char charAt = str.charAt(i2);
        if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
            while (true) {
                i2++;
                if (i2 >= i3) {
                    break;
                }
                char charAt2 = str.charAt(i2);
                if (charAt2 < 'a' || charAt2 > 'z') {
                    if (charAt2 < 'A' || charAt2 > 'Z') {
                        if (charAt2 < '0' || charAt2 > '9') {
                            if (charAt2 != '+' && charAt2 != '-' && charAt2 != '.') {
                                if (charAt2 == ':') {
                                    return i2;
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static int q(String str, int i2, int i3) {
        while (i2 < i3) {
            char charAt = str.charAt(i2);
            if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    private static int r(String str, int i2, int i3) {
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            char charAt = str.charAt(i4);
            if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                return i4 + 1;
            }
        }
        return i2;
    }

    private static int s(String str, int i2, int i3) {
        int i4 = 0;
        while (i2 < i3) {
            char charAt = str.charAt(i2);
            if (charAt != '\\' && charAt != '/') {
                break;
            }
            i4++;
            i2++;
        }
        return i4;
    }
}
