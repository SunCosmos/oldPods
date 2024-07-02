package c.a.a.w.a;

/* loaded from: classes.dex */
public final class l extends u {
    private static String n(int i2, String str) {
        char charAt;
        if (str.charAt(i2) != '(') {
            return null;
        }
        String substring = str.substring(i2 + 1);
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < substring.length() && (charAt = substring.charAt(i3)) != ')'; i3++) {
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String o(int i2, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i2);
        for (int i3 = 0; i3 < substring.length(); i3++) {
            char charAt = substring.charAt(i3);
            if (charAt != '(') {
                sb.append(charAt);
            } else {
                if (n(i3, substring) != null) {
                    break;
                }
                sb.append('(');
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:140:0x020e, code lost:
    
        if (r1.equals("10") == false) goto L13;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x022b. Please report as an issue. */
    @Override // c.a.a.w.a.u
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c.a.a.w.a.k h(c.a.a.p r24) {
        /*
            Method dump skipped, instructions count: 870
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.w.a.l.h(c.a.a.p):c.a.a.w.a.k");
    }
}
