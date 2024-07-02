package c.a.a.b0.g;

import c.a.a.u;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class g {

    /* renamed from: c, reason: collision with root package name */
    private static final byte[] f1221c;
    private static final byte[] a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] b = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};

    /* renamed from: d, reason: collision with root package name */
    private static final byte[] f1222d = new byte[128];
    private static final List<String> e = Arrays.asList("Cp437", "IBM437");

    static {
        byte[] bArr = new byte[128];
        f1221c = bArr;
        byte b2 = 0;
        Arrays.fill(bArr, (byte) -1);
        byte b3 = 0;
        while (true) {
            byte[] bArr2 = a;
            if (b3 >= bArr2.length) {
                break;
            }
            byte b4 = bArr2[b3];
            if (b4 > 0) {
                f1221c[b4] = b3;
            }
            b3 = (byte) (b3 + 1);
        }
        Arrays.fill(f1222d, (byte) -1);
        while (true) {
            byte[] bArr3 = b;
            if (b2 >= bArr3.length) {
                return;
            }
            byte b5 = bArr3[b2];
            if (b5 > 0) {
                f1222d[b5] = b2;
            }
            b2 = (byte) (b2 + 1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x003f, code lost:
    
        return r1 - r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0026, code lost:
    
        return r1 - r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int a(java.lang.CharSequence r7, byte[] r8, int r9) {
        /*
            int r0 = r7.length()
            r1 = r9
        L5:
            if (r1 >= r0) goto L74
            char r2 = r7.charAt(r1)
            r3 = 0
            r4 = 0
        Ld:
            r5 = 13
            if (r4 >= r5) goto L23
            boolean r6 = k(r2)
            if (r6 == 0) goto L23
            int r4 = r4 + 1
            int r6 = r1 + r4
            if (r6 < r0) goto L1e
            goto L23
        L1e:
            char r2 = r7.charAt(r6)
            goto Ld
        L23:
            if (r4 < r5) goto L27
            int r1 = r1 - r9
            return r1
        L27:
            r4 = 5
            if (r3 >= r4) goto L3c
            boolean r2 = n(r2)
            if (r2 == 0) goto L3c
            int r3 = r3 + 1
            int r2 = r1 + r3
            if (r2 < r0) goto L37
            goto L3c
        L37:
            char r2 = r7.charAt(r2)
            goto L27
        L3c:
            if (r3 < r4) goto L40
            int r1 = r1 - r9
            return r1
        L40:
            char r2 = r7.charAt(r1)
            r3 = r8[r1]
            r4 = 63
            if (r3 != r4) goto L71
            if (r2 != r4) goto L4d
            goto L71
        L4d:
            c.a.a.u r7 = new c.a.a.u
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Non-encodable character detected: "
            r8.append(r9)
            r8.append(r2)
            java.lang.String r9 = " (Unicode: "
            r8.append(r9)
            r8.append(r2)
            r9 = 41
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L71:
            int r1 = r1 + 1
            goto L5
        L74:
            int r1 = r1 - r9
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.g.g.a(java.lang.CharSequence, byte[], int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0005, code lost:
    
        if (r5 < r0) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:
    
        r2 = r4.charAt(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000f, code lost:
    
        if (k(r2) == false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0011, code lost:
    
        if (r5 >= r0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        r1 = r1 + 1;
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        if (r5 >= r0) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int b(java.lang.CharSequence r4, int r5) {
        /*
            int r0 = r4.length()
            r1 = 0
            if (r5 >= r0) goto L1a
        L7:
            char r2 = r4.charAt(r5)
        Lb:
            boolean r3 = k(r2)
            if (r3 == 0) goto L1a
            if (r5 >= r0) goto L1a
            int r1 = r1 + 1
            int r5 = r5 + 1
            if (r5 >= r0) goto Lb
            goto L7
        L1a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.g.g.b(java.lang.CharSequence, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0027, code lost:
    
        return (r1 - r7) - r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int c(java.lang.CharSequence r6, int r7) {
        /*
            int r0 = r6.length()
            r1 = r7
        L5:
            if (r1 >= r0) goto L39
            char r2 = r6.charAt(r1)
            r3 = 0
        Lc:
            r4 = 13
            if (r3 >= r4) goto L23
            boolean r5 = k(r2)
            if (r5 == 0) goto L23
            if (r1 >= r0) goto L23
            int r3 = r3 + 1
            int r1 = r1 + 1
            if (r1 >= r0) goto Lc
            char r2 = r6.charAt(r1)
            goto Lc
        L23:
            if (r3 < r4) goto L28
            int r1 = r1 - r7
            int r1 = r1 - r3
            return r1
        L28:
            if (r3 <= 0) goto L2b
            goto L5
        L2b:
            char r2 = r6.charAt(r1)
            boolean r2 = n(r2)
            if (r2 != 0) goto L36
            goto L39
        L36:
            int r1 = r1 + 1
            goto L5
        L39:
            int r1 = r1 - r7
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.g.g.c(java.lang.CharSequence, int):int");
    }

    private static void d(byte[] bArr, int i2, int i3, int i4, StringBuilder sb) {
        int i5;
        sb.append((i3 == 1 && i4 == 0) ? (char) 913 : i3 % 6 == 0 ? (char) 924 : (char) 901);
        if (i3 >= 6) {
            char[] cArr = new char[5];
            i5 = i2;
            while ((i2 + i3) - i5 >= 6) {
                long j = 0;
                for (int i6 = 0; i6 < 6; i6++) {
                    j = (j << 8) + (bArr[i5 + i6] & 255);
                }
                for (int i7 = 0; i7 < 5; i7++) {
                    cArr[i7] = (char) (j % 900);
                    j /= 900;
                }
                for (int i8 = 4; i8 >= 0; i8--) {
                    sb.append(cArr[i8]);
                }
                i5 += 6;
            }
        } else {
            i5 = i2;
        }
        while (i5 < i2 + i3) {
            sb.append((char) (bArr[i5] & 255));
            i5++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e(String str, c cVar, Charset charset) {
        c.a.a.x.d a2;
        StringBuilder sb = new StringBuilder(str.length());
        if (charset != null && !e.contains(charset.name()) && (a2 = c.a.a.x.d.a(charset.name())) != null) {
            h(a2.c(), sb);
        }
        int length = str.length();
        byte[] bArr = null;
        if (cVar == c.TEXT) {
            g(str, 0, length, sb, 0);
        } else if (cVar == c.BYTE) {
            byte[] o = o(str, charset);
            d(o, 0, o.length, 1, sb);
        } else if (cVar == c.NUMERIC) {
            sb.append((char) 902);
            f(str, 0, length, sb);
        } else {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < length) {
                int b2 = b(str, i2);
                if (b2 >= 13) {
                    sb.append((char) 902);
                    f(str, i2, b2, sb);
                    i2 += b2;
                    i3 = 0;
                    i4 = 2;
                } else {
                    int c2 = c(str, i2);
                    if (c2 >= 5 || b2 == length) {
                        if (i4 != 0) {
                            sb.append((char) 900);
                            i3 = 0;
                            i4 = 0;
                        }
                        i3 = g(str, i2, c2, sb, i3);
                        i2 += c2;
                    } else {
                        if (bArr == null) {
                            bArr = o(str, charset);
                        }
                        int a3 = a(str, bArr, i2);
                        if (a3 == 0) {
                            a3 = 1;
                        }
                        if (a3 == 1 && i4 == 0) {
                            d(bArr, i2, 1, 0, sb);
                        } else {
                            d(bArr, i2, a3, i4, sb);
                            i3 = 0;
                            i4 = 1;
                        }
                        i2 += a3;
                    }
                }
            }
        }
        return sb.toString();
    }

    private static void f(String str, int i2, int i3, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i3 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900L);
        BigInteger valueOf2 = BigInteger.valueOf(0L);
        int i4 = 0;
        while (i4 < i3 - 1) {
            sb2.setLength(0);
            int min = Math.min(44, i3 - i4);
            StringBuilder sb3 = new StringBuilder();
            sb3.append('1');
            int i5 = i2 + i4;
            sb3.append(str.substring(i5, i5 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i4 += min;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:99:0x00a0, code lost:
    
        if (r10 == ' ') goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int g(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.g.g.g(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void h(int i2, StringBuilder sb) {
        char c2;
        if (i2 >= 0 && i2 < 900) {
            sb.append((char) 927);
        } else {
            if (i2 >= 810900) {
                if (i2 < 811800) {
                    sb.append((char) 925);
                    c2 = (char) (810900 - i2);
                    sb.append(c2);
                } else {
                    throw new u("ECI number not in valid range from 0..811799, but was " + i2);
                }
            }
            sb.append((char) 926);
            sb.append((char) ((i2 / 900) - 1));
            i2 %= 900;
        }
        c2 = (char) i2;
        sb.append(c2);
    }

    private static boolean i(char c2) {
        return c2 == ' ' || (c2 >= 'a' && c2 <= 'z');
    }

    private static boolean j(char c2) {
        return c2 == ' ' || (c2 >= 'A' && c2 <= 'Z');
    }

    private static boolean k(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    private static boolean l(char c2) {
        return f1221c[c2] != -1;
    }

    private static boolean m(char c2) {
        return f1222d[c2] != -1;
    }

    private static boolean n(char c2) {
        return c2 == '\t' || c2 == '\n' || c2 == '\r' || (c2 >= ' ' && c2 <= '~');
    }

    private static byte[] o(String str, Charset charset) {
        if (charset == null) {
            Iterator<String> it = e.iterator();
            while (it.hasNext()) {
                try {
                    charset = Charset.forName(it.next());
                } catch (UnsupportedCharsetException unused) {
                }
            }
            if (charset == null) {
                throw new u("No support for any encoding: " + e);
            }
        }
        return str.getBytes(charset);
    }
}
