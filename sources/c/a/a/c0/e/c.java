package c.a.a.c0.e;

import android.support.v7.widget.ActivityChooserView;
import c.a.a.c0.c.h;
import c.a.a.c0.c.j;
import c.a.a.g;
import c.a.a.u;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public final class c {
    private static final int[] a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[h.values().length];
            a = iArr;
            try {
                iArr[h.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[h.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[h.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[h.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static void a(String str, c.a.a.x.a aVar, String str2) {
        try {
            for (byte b : str.getBytes(str2)) {
                aVar.c(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new u(e);
        }
    }

    static void b(CharSequence charSequence, c.a.a.x.a aVar) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int o = o(charSequence.charAt(i2));
            if (o == -1) {
                throw new u();
            }
            int i3 = i2 + 1;
            if (i3 < length) {
                int o2 = o(charSequence.charAt(i3));
                if (o2 == -1) {
                    throw new u();
                }
                aVar.c((o * 45) + o2, 11);
                i2 += 2;
            } else {
                aVar.c(o, 6);
                i2 = i3;
            }
        }
    }

    static void c(String str, h hVar, c.a.a.x.a aVar, String str2) {
        int i2 = a.a[hVar.ordinal()];
        if (i2 == 1) {
            h(str, aVar);
            return;
        }
        if (i2 == 2) {
            b(str, aVar);
            return;
        }
        if (i2 == 3) {
            a(str, aVar, str2);
        } else {
            if (i2 == 4) {
                e(str, aVar);
                return;
            }
            throw new u("Invalid mode: " + hVar);
        }
    }

    private static void d(c.a.a.x.d dVar, c.a.a.x.a aVar) {
        aVar.c(h.ECI.b(), 4);
        aVar.c(dVar.c(), 8);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035 A[LOOP:0: B:4:0x0008->B:11:0x0035, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0044 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void e(java.lang.String r6, c.a.a.x.a r7) {
        /*
            java.lang.String r0 = "Shift_JIS"
            byte[] r6 = r6.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L4d
            int r0 = r6.length
            r1 = 0
        L8:
            if (r1 >= r0) goto L4c
            r2 = r6[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r1 + 1
            r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r2 = r2 | r3
            r3 = 33088(0x8140, float:4.6366E-41)
            r4 = -1
            if (r2 < r3) goto L24
            r5 = 40956(0x9ffc, float:5.7392E-41)
            if (r2 > r5) goto L24
        L22:
            int r2 = r2 - r3
            goto L33
        L24:
            r3 = 57408(0xe040, float:8.0446E-41)
            if (r2 < r3) goto L32
            r3 = 60351(0xebbf, float:8.457E-41)
            if (r2 > r3) goto L32
            r3 = 49472(0xc140, float:6.9325E-41)
            goto L22
        L32:
            r2 = -1
        L33:
            if (r2 == r4) goto L44
            int r3 = r2 >> 8
            int r3 = r3 * 192
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r2
            r2 = 13
            r7.c(r3, r2)
            int r1 = r1 + 2
            goto L8
        L44:
            c.a.a.u r6 = new c.a.a.u
            java.lang.String r7 = "Invalid byte sequence"
            r6.<init>(r7)
            throw r6
        L4c:
            return
        L4d:
            r6 = move-exception
            c.a.a.u r7 = new c.a.a.u
            r7.<init>(r6)
            goto L55
        L54:
            throw r7
        L55:
            goto L54
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.c0.e.c.e(java.lang.String, c.a.a.x.a):void");
    }

    static void f(int i2, j jVar, h hVar, c.a.a.x.a aVar) {
        int c2 = hVar.c(jVar);
        int i3 = 1 << c2;
        if (i2 < i3) {
            aVar.c(i2, c2);
            return;
        }
        throw new u(i2 + " is bigger than " + (i3 - 1));
    }

    static void g(h hVar, c.a.a.x.a aVar) {
        aVar.c(hVar.b(), 4);
    }

    static void h(CharSequence charSequence, c.a.a.x.a aVar) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int charAt = charSequence.charAt(i2) - '0';
            int i3 = i2 + 2;
            if (i3 < length) {
                aVar.c((charAt * 100) + ((charSequence.charAt(i2 + 1) - '0') * 10) + (charSequence.charAt(i3) - '0'), 10);
                i2 += 3;
            } else {
                i2++;
                if (i2 < length) {
                    aVar.c((charAt * 10) + (charSequence.charAt(i2) - '0'), 7);
                    i2 = i3;
                } else {
                    aVar.c(charAt, 4);
                }
            }
        }
    }

    private static int i(b bVar) {
        return d.a(bVar) + d.c(bVar) + d.d(bVar) + d.e(bVar);
    }

    private static int j(c.a.a.x.a aVar, c.a.a.c0.c.f fVar, j jVar, b bVar) {
        int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i3 = -1;
        for (int i4 = 0; i4 < 8; i4++) {
            e.a(aVar, fVar, jVar, i4, bVar);
            int i5 = i(bVar);
            if (i5 < i2) {
                i3 = i4;
                i2 = i5;
            }
        }
        return i3;
    }

    private static h k(String str, String str2) {
        if ("Shift_JIS".equals(str2)) {
            return r(str) ? h.KANJI : h.BYTE;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else {
                if (o(charAt) == -1) {
                    return h.BYTE;
                }
                z = true;
            }
        }
        return z ? h.ALPHANUMERIC : z2 ? h.NUMERIC : h.BYTE;
    }

    private static j l(int i2, c.a.a.c0.c.f fVar) {
        for (int i3 = 1; i3 <= 40; i3++) {
            j i4 = j.i(i3);
            if (i4.h() - i4.f(fVar).d() >= (i2 + 7) / 8) {
                return i4;
            }
        }
        throw new u("Data too big");
    }

    public static f m(String str, c.a.a.c0.c.f fVar, Map<g, ?> map) {
        c.a.a.x.d a2;
        String str2 = map == null ? null : (String) map.get(g.CHARACTER_SET);
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        h k = k(str, str2);
        c.a.a.x.a aVar = new c.a.a.x.a();
        h hVar = h.BYTE;
        if (k == hVar && !"ISO-8859-1".equals(str2) && (a2 = c.a.a.x.d.a(str2)) != null) {
            d(a2, aVar);
        }
        g(k, aVar);
        c.a.a.x.a aVar2 = new c.a.a.x.a();
        c(str, k, aVar2, str2);
        j l = l(aVar.k() + k.c(l(aVar.k() + k.c(j.i(1)) + aVar2.k(), fVar)) + aVar2.k(), fVar);
        c.a.a.x.a aVar3 = new c.a.a.x.a();
        aVar3.b(aVar);
        f(k == hVar ? aVar2.l() : str.length(), l, k, aVar3);
        aVar3.b(aVar2);
        j.b f = l.f(fVar);
        int h = l.h() - f.d();
        s(h, aVar3);
        c.a.a.x.a q = q(aVar3, l.h(), h, f.c());
        f fVar2 = new f();
        fVar2.c(fVar);
        fVar2.f(k);
        fVar2.g(l);
        int e = l.e();
        b bVar = new b(e, e);
        int j = j(q, fVar, l, bVar);
        fVar2.d(j);
        e.a(q, fVar, l, j, bVar);
        fVar2.e(bVar);
        return fVar2;
    }

    static byte[] n(byte[] bArr, int i2) {
        int length = bArr.length;
        int[] iArr = new int[length + i2];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        new c.a.a.x.n.d(c.a.a.x.n.a.l).b(iArr, i2);
        byte[] bArr2 = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr2[i4] = (byte) iArr[length + i4];
        }
        return bArr2;
    }

    static int o(int i2) {
        int[] iArr = a;
        if (i2 < iArr.length) {
            return iArr[i2];
        }
        return -1;
    }

    static void p(int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2) {
        if (i5 >= i4) {
            throw new u("Block ID too large");
        }
        int i6 = i2 % i4;
        int i7 = i4 - i6;
        int i8 = i2 / i4;
        int i9 = i8 + 1;
        int i10 = i3 / i4;
        int i11 = i10 + 1;
        int i12 = i8 - i10;
        int i13 = i9 - i11;
        if (i12 != i13) {
            throw new u("EC bytes mismatch");
        }
        if (i4 != i7 + i6) {
            throw new u("RS blocks mismatch");
        }
        if (i2 != ((i10 + i12) * i7) + ((i11 + i13) * i6)) {
            throw new u("Total bytes mismatch");
        }
        if (i5 < i7) {
            iArr[0] = i10;
            iArr2[0] = i12;
        } else {
            iArr[0] = i11;
            iArr2[0] = i13;
        }
    }

    static c.a.a.x.a q(c.a.a.x.a aVar, int i2, int i3, int i4) {
        if (aVar.l() != i3) {
            throw new u("Number of bits and data bytes does not match");
        }
        ArrayList arrayList = new ArrayList(i4);
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < i4; i8++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            p(i2, i3, i4, i8, iArr, iArr2);
            int i9 = iArr[0];
            byte[] bArr = new byte[i9];
            aVar.r(i5 * 8, bArr, 0, i9);
            byte[] n = n(bArr, iArr2[0]);
            arrayList.add(new c.a.a.c0.e.a(bArr, n));
            i6 = Math.max(i6, i9);
            i7 = Math.max(i7, n.length);
            i5 += iArr[0];
        }
        if (i3 != i5) {
            throw new u("Data bytes does not match offset");
        }
        c.a.a.x.a aVar2 = new c.a.a.x.a();
        for (int i10 = 0; i10 < i6; i10++) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] a2 = ((c.a.a.c0.e.a) it.next()).a();
                if (i10 < a2.length) {
                    aVar2.c(a2[i10], 8);
                }
            }
        }
        for (int i11 = 0; i11 < i7; i11++) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                byte[] b = ((c.a.a.c0.e.a) it2.next()).b();
                if (i11 < b.length) {
                    aVar2.c(b[i11], 8);
                }
            }
        }
        if (i2 == aVar2.l()) {
            return aVar2;
        }
        throw new u("Interleaving error: " + i2 + " and " + aVar2.l() + " differ.");
    }

    private static boolean r(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i2 = 0; i2 < length; i2 += 2) {
                int i3 = bytes[i2] & 255;
                if ((i3 < 129 || i3 > 159) && (i3 < 224 || i3 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    static void s(int i2, c.a.a.x.a aVar) {
        int i3 = i2 * 8;
        if (aVar.k() > i3) {
            throw new u("data bits cannot fit in the QR Code" + aVar.k() + " > " + i3);
        }
        for (int i4 = 0; i4 < 4 && aVar.k() < i3; i4++) {
            aVar.a(false);
        }
        int k = aVar.k() & 7;
        if (k > 0) {
            while (k < 8) {
                aVar.a(false);
                k++;
            }
        }
        int l = i2 - aVar.l();
        for (int i5 = 0; i5 < l; i5++) {
            aVar.c((i5 & 1) == 0 ? 236 : 17, 8);
        }
        if (aVar.k() != i3) {
            throw new u("Bits size does not equal capacity");
        }
    }
}
