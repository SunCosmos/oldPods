package c.a.a.c0.c;

import bsh.org.objectweb.asm.Constants;
import c.a.a.x.l;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
final class d {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', '.', '/', ':'};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ea A[LOOP:0: B:2:0x001d->B:46:0x00ea, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static c.a.a.x.e a(byte[] r17, c.a.a.c0.c.j r18, c.a.a.c0.c.f r19, java.util.Map<c.a.a.e, ?> r20) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.c0.c.d.a(byte[], c.a.a.c0.c.j, c.a.a.c0.c.f, java.util.Map):c.a.a.x.e");
    }

    private static void b(c.a.a.x.c cVar, StringBuilder sb, int i2, boolean z) {
        while (i2 > 1) {
            if (cVar.a() < 11) {
                throw c.a.a.h.a();
            }
            int d2 = cVar.d(11);
            sb.append(h(d2 / 45));
            sb.append(h(d2 % 45));
            i2 -= 2;
        }
        if (i2 == 1) {
            if (cVar.a() < 6) {
                throw c.a.a.h.a();
            }
            sb.append(h(cVar.d(6)));
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i3 = length + 1;
                        if (sb.charAt(i3) == '%') {
                            sb.deleteCharAt(i3);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }

    private static void c(c.a.a.x.c cVar, StringBuilder sb, int i2, c.a.a.x.d dVar, Collection<byte[]> collection, Map<c.a.a.e, ?> map) {
        if (i2 * 8 > cVar.a()) {
            throw c.a.a.h.a();
        }
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) cVar.d(8);
        }
        try {
            sb.append(new String(bArr, dVar == null ? l.a(bArr, map) : dVar.name()));
            collection.add(bArr);
        } catch (UnsupportedEncodingException unused) {
            throw c.a.a.h.a();
        }
    }

    private static void d(c.a.a.x.c cVar, StringBuilder sb, int i2) {
        if (i2 * 13 > cVar.a()) {
            throw c.a.a.h.a();
        }
        byte[] bArr = new byte[i2 * 2];
        int i3 = 0;
        while (i2 > 0) {
            int d2 = cVar.d(13);
            int i4 = (d2 % 96) | ((d2 / 96) << 8);
            int i5 = i4 + (i4 < 959 ? 41377 : 42657);
            bArr[i3] = (byte) ((i5 >> 8) & 255);
            bArr[i3 + 1] = (byte) (i5 & 255);
            i3 += 2;
            i2--;
        }
        try {
            sb.append(new String(bArr, "GB2312"));
        } catch (UnsupportedEncodingException unused) {
            throw c.a.a.h.a();
        }
    }

    private static void e(c.a.a.x.c cVar, StringBuilder sb, int i2) {
        if (i2 * 13 > cVar.a()) {
            throw c.a.a.h.a();
        }
        byte[] bArr = new byte[i2 * 2];
        int i3 = 0;
        while (i2 > 0) {
            int d2 = cVar.d(13);
            int i4 = (d2 % Constants.CHECKCAST) | ((d2 / Constants.CHECKCAST) << 8);
            int i5 = i4 + (i4 < 7936 ? 33088 : 49472);
            bArr[i3] = (byte) (i5 >> 8);
            bArr[i3 + 1] = (byte) i5;
            i3 += 2;
            i2--;
        }
        try {
            sb.append(new String(bArr, "SJIS"));
        } catch (UnsupportedEncodingException unused) {
            throw c.a.a.h.a();
        }
    }

    private static void f(c.a.a.x.c cVar, StringBuilder sb, int i2) {
        int d2;
        while (i2 >= 3) {
            if (cVar.a() < 10) {
                throw c.a.a.h.a();
            }
            int d3 = cVar.d(10);
            if (d3 >= 1000) {
                throw c.a.a.h.a();
            }
            sb.append(h(d3 / 100));
            sb.append(h((d3 / 10) % 10));
            sb.append(h(d3 % 10));
            i2 -= 3;
        }
        if (i2 == 2) {
            if (cVar.a() < 7) {
                throw c.a.a.h.a();
            }
            int d4 = cVar.d(7);
            if (d4 >= 100) {
                throw c.a.a.h.a();
            }
            sb.append(h(d4 / 10));
            d2 = d4 % 10;
        } else {
            if (i2 != 1) {
                return;
            }
            if (cVar.a() < 4) {
                throw c.a.a.h.a();
            }
            d2 = cVar.d(4);
            if (d2 >= 10) {
                throw c.a.a.h.a();
            }
        }
        sb.append(h(d2));
    }

    private static int g(c.a.a.x.c cVar) {
        int d2 = cVar.d(8);
        if ((d2 & 128) == 0) {
            return d2 & 127;
        }
        if ((d2 & Constants.CHECKCAST) == 128) {
            return cVar.d(8) | ((d2 & 63) << 8);
        }
        if ((d2 & 224) == 192) {
            return cVar.d(16) | ((d2 & 31) << 16);
        }
        throw c.a.a.h.a();
    }

    private static char h(int i2) {
        char[] cArr = a;
        if (i2 < cArr.length) {
            return cArr[i2];
        }
        throw c.a.a.h.a();
    }
}
