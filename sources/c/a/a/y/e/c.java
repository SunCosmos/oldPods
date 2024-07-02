package c.a.a.y.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements g {
    private int b(h hVar, StringBuilder sb, StringBuilder sb2, int i2) {
        int length = sb.length();
        sb.delete(length - i2, length);
        hVar.f--;
        int c2 = c(hVar.c(), sb2);
        hVar.k();
        return c2;
    }

    private static String d(CharSequence charSequence, int i2) {
        int charAt = (charSequence.charAt(i2) * 1600) + (charSequence.charAt(i2 + 1) * '(') + charSequence.charAt(i2 + 2) + 1;
        return new String(new char[]{(char) (charAt / 256), (char) (charAt % 256)});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(h hVar, StringBuilder sb) {
        hVar.s(d(sb, 0));
        sb.delete(0, 3);
    }

    @Override // c.a.a.y.e.g
    public void a(h hVar) {
        int n;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!hVar.i()) {
                break;
            }
            char c2 = hVar.c();
            hVar.f++;
            int c3 = c(c2, sb);
            int a = hVar.a() + ((sb.length() / 3) * 2);
            hVar.q(a);
            int a2 = hVar.g().a() - a;
            if (!hVar.i()) {
                StringBuilder sb2 = new StringBuilder();
                if (sb.length() % 3 == 2 && (a2 < 2 || a2 > 2)) {
                    c3 = b(hVar, sb, sb2, c3);
                }
                while (sb.length() % 3 == 1) {
                    if (c3 <= 3 && a2 != 1) {
                        c3 = b(hVar, sb, sb2, c3);
                    } else if (c3 <= 3) {
                        break;
                    } else {
                        c3 = b(hVar, sb, sb2, c3);
                    }
                }
            } else if (sb.length() % 3 == 0 && (n = j.n(hVar.d(), hVar.f, e())) != e()) {
                hVar.o(n);
                break;
            }
        }
        f(hVar, sb);
    }

    int c(char c2, StringBuilder sb) {
        int i2;
        int i3;
        char c3;
        if (c2 == ' ') {
            c3 = 3;
        } else {
            if (c2 >= '0' && c2 <= '9') {
                i3 = (c2 - '0') + 4;
            } else {
                if (c2 < 'A' || c2 > 'Z') {
                    if (c2 < 0 || c2 > 31) {
                        if (c2 >= '!' && c2 <= '/') {
                            sb.append((char) 1);
                            i2 = c2 - '!';
                        } else if (c2 >= ':' && c2 <= '@') {
                            sb.append((char) 1);
                            i2 = (c2 - ':') + 15;
                        } else if (c2 >= '[' && c2 <= '_') {
                            sb.append((char) 1);
                            i2 = (c2 - '[') + 22;
                        } else {
                            if (c2 < '`' || c2 > 127) {
                                if (c2 >= 128) {
                                    sb.append("\u0001\u001e");
                                    return c((char) (c2 - 128), sb) + 2;
                                }
                                throw new IllegalArgumentException("Illegal character: " + c2);
                            }
                            sb.append((char) 2);
                            i2 = c2 - '`';
                        }
                        c2 = (char) i2;
                    } else {
                        sb.append((char) 0);
                    }
                    sb.append(c2);
                    return 2;
                }
                i3 = (c2 - 'A') + 14;
            }
            c3 = (char) i3;
        }
        sb.append(c3);
        return 1;
    }

    public int e() {
        return 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x006b, code lost:
    
        if (r7.i() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0035, code lost:
    
        if (r7.i() != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void f(c.a.a.y.e.h r7, java.lang.StringBuilder r8) {
        /*
            r6 = this;
            int r0 = r8.length()
            r1 = 3
            int r0 = r0 / r1
            r2 = 2
            int r0 = r0 * 2
            int r3 = r8.length()
            int r3 = r3 % r1
            int r4 = r7.a()
            int r4 = r4 + r0
            r7.q(r4)
            c.a.a.y.e.k r0 = r7.g()
            int r0 = r0.a()
            int r0 = r0 - r4
            r4 = 0
            r5 = 254(0xfe, float:3.56E-43)
            if (r3 != r2) goto L3b
            r8.append(r4)
        L27:
            int r0 = r8.length()
            if (r0 < r1) goto L31
            g(r7, r8)
            goto L27
        L31:
            boolean r8 = r7.i()
            if (r8 == 0) goto L6e
        L37:
            r7.r(r5)
            goto L6e
        L3b:
            r2 = 1
            if (r0 != r2) goto L59
            if (r3 != r2) goto L59
        L40:
            int r0 = r8.length()
            if (r0 < r1) goto L4a
            g(r7, r8)
            goto L40
        L4a:
            boolean r8 = r7.i()
            if (r8 == 0) goto L53
            r7.r(r5)
        L53:
            int r8 = r7.f
            int r8 = r8 - r2
            r7.f = r8
            goto L6e
        L59:
            if (r3 != 0) goto L72
        L5b:
            int r2 = r8.length()
            if (r2 < r1) goto L65
            g(r7, r8)
            goto L5b
        L65:
            if (r0 > 0) goto L37
            boolean r8 = r7.i()
            if (r8 == 0) goto L6e
            goto L37
        L6e:
            r7.o(r4)
            return
        L72:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Unexpected case. Please report!"
            r7.<init>(r8)
            goto L7b
        L7a:
            throw r7
        L7b:
            goto L7a
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.y.e.c.f(c.a.a.y.e.h, java.lang.StringBuilder):void");
    }
}
