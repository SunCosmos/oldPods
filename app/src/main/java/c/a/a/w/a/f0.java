package c.a.a.w.a;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class f0 extends u {
    private static final Pattern e = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern f = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
    private static final Pattern g = Pattern.compile("\r\n[ \t]");
    private static final Pattern h = Pattern.compile("\\\\[nN]");

    /* renamed from: i, reason: collision with root package name */
    private static final Pattern f1280i = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern j = Pattern.compile("=");
    private static final Pattern k = Pattern.compile(";");
    private static final Pattern l = Pattern.compile("(?<!\\\\);+");
    private static final Pattern m = Pattern.compile(",");
    private static final Pattern n = Pattern.compile("[;,]");

    private static String n(CharSequence charSequence, String str) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        while (i2 < length) {
            char charAt2 = charSequence.charAt(i2);
            if (charAt2 != '\n' && charAt2 != '\r') {
                if (charAt2 != '=') {
                    t(byteArrayOutputStream, str, sb);
                    sb.append(charAt2);
                } else if (i2 < length - 2 && (charAt = charSequence.charAt(i2 + 1)) != '\r' && charAt != '\n') {
                    i2 += 2;
                    char charAt3 = charSequence.charAt(i2);
                    int i3 = u.i(charAt);
                    int i4 = u.i(charAt3);
                    if (i3 >= 0 && i4 >= 0) {
                        byteArrayOutputStream.write((i3 << 4) + i4);
                    }
                }
            }
            i2++;
        }
        t(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    private static void o(Iterable<List<String>> iterable) {
        int indexOf;
        if (iterable != null) {
            for (List<String> list : iterable) {
                String str = list.get(0);
                String[] strArr = new String[5];
                int i2 = 0;
                int i3 = 0;
                while (i2 < 4 && (indexOf = str.indexOf(59, i3)) >= 0) {
                    strArr[i2] = str.substring(i3, indexOf);
                    i2++;
                    i3 = indexOf + 1;
                }
                strArr[i2] = str.substring(i3);
                StringBuilder sb = new StringBuilder(100);
                s(strArr, 3, sb);
                s(strArr, 1, sb);
                s(strArr, 2, sb);
                s(strArr, 0, sb);
                s(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    private static boolean p(CharSequence charSequence) {
        return charSequence == null || f.matcher(charSequence).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> q(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> r = r(charSequence, str, z, z2);
        if (r == null || r.isEmpty()) {
            return null;
        }
        return r.get(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00cd, code lost:
    
        r2 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.util.List<java.lang.String>> r(java.lang.CharSequence r16, java.lang.String r17, boolean r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.w.a.f0.r(java.lang.CharSequence, java.lang.String, boolean, boolean):java.util.List");
    }

    private static void s(String[] strArr, int i2, StringBuilder sb) {
        if (strArr[i2] == null || strArr[i2].isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(strArr[i2]);
    }

    private static void t(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, Charset.forName("UTF-8"));
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, Charset.forName("UTF-8"));
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    private static String v(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static String[] w(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<List<String>> it = collection.iterator();
        while (it.hasNext()) {
            String str = it.next().get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    private static String[] x(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            int i2 = 1;
            while (true) {
                if (i2 >= list.size()) {
                    str = null;
                    break;
                }
                str = list.get(i2);
                int indexOf = str.indexOf(61);
                if (indexOf >= 0) {
                    if ("TYPE".equalsIgnoreCase(str.substring(0, indexOf))) {
                        str = str.substring(indexOf + 1);
                        break;
                    }
                    i2++;
                }
            }
            arrayList.add(str);
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    @Override // c.a.a.w.a.u
    /* renamed from: u, reason: merged with bridge method [inline-methods] */
    public d h(c.a.a.p pVar) {
        String b = u.b(pVar);
        Matcher matcher = e.matcher(b);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List<List<String>> r = r("FN", b, true, false);
        if (r == null) {
            r = r("N", b, true, false);
            o(r);
        }
        List<String> q = q("NICKNAME", b, true, false);
        String[] split = q == null ? null : m.split(q.get(0));
        List<List<String>> r2 = r("TEL", b, true, false);
        List<List<String>> r3 = r("EMAIL", b, true, false);
        List<String> q2 = q("NOTE", b, false, false);
        List<List<String>> r4 = r("ADR", b, true, true);
        List<String> q3 = q("ORG", b, true, true);
        List<String> q4 = q("BDAY", b, true, false);
        List<String> list = (q4 == null || p(q4.get(0))) ? q4 : null;
        List<String> q5 = q("TITLE", b, true, false);
        List<List<String>> r5 = r("URL", b, true, false);
        List<String> q6 = q("IMPP", b, true, false);
        List<String> q7 = q("GEO", b, true, false);
        String[] split2 = q7 == null ? null : n.split(q7.get(0));
        return new d(w(r), split, null, w(r2), x(r2), w(r3), x(r3), v(q6), v(q2), w(r4), x(r4), v(q3), v(list), v(q5), w(r5), (split2 == null || split2.length == 2) ? split2 : null);
    }
}
