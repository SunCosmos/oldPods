package c.a.a.a0;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class x extends q {

    /* renamed from: d */
    static final int[] f1191d = {1, 1, 1};
    static final int[] e = {1, 1, 1, 1, 1};
    static final int[][] f;
    static final int[][] g;
    private final StringBuilder a = new StringBuilder(20);
    private final w b = new w();

    /* renamed from: c */
    private final l f1192c = new l();

    static {
        int[][] iArr = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        f = iArr;
        int[][] iArr2 = new int[20];
        g = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, 10);
        for (int i2 = 10; i2 < 20; i2++) {
            int[] iArr3 = f[i2 - 10];
            int[] iArr4 = new int[iArr3.length];
            for (int i3 = 0; i3 < iArr3.length; i3++) {
                iArr4[i3] = iArr3[(iArr3.length - i3) - 1];
            }
            g[i2] = iArr4;
        }
    }

    public static boolean i(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i2 = 0;
        for (int i3 = length - 2; i3 >= 0; i3 -= 2) {
            int charAt = charSequence.charAt(i3) - '0';
            if (charAt < 0 || charAt > 9) {
                throw c.a.a.h.a();
            }
            i2 += charAt;
        }
        int i4 = i2 * 3;
        for (int i5 = length - 1; i5 >= 0; i5 -= 2) {
            int charAt2 = charSequence.charAt(i5) - '0';
            if (charAt2 < 0 || charAt2 > 9) {
                throw c.a.a.h.a();
            }
            i4 += charAt2;
        }
        return i4 % 10 == 0;
    }

    public static int j(c.a.a.x.a aVar, int[] iArr, int i2, int[][] iArr2) {
        q.f(aVar, i2, iArr);
        int length = iArr2.length;
        float f2 = 0.48f;
        int i3 = -1;
        for (int i4 = 0; i4 < length; i4++) {
            float e2 = q.e(iArr, iArr2[i4], 0.7f);
            if (e2 < f2) {
                i3 = i4;
                f2 = e2;
            }
        }
        if (i3 >= 0) {
            return i3;
        }
        throw c.a.a.l.a();
    }

    public static int[] n(c.a.a.x.a aVar, int i2, boolean z, int[] iArr) {
        return o(aVar, i2, z, iArr, new int[iArr.length]);
    }

    private static int[] o(c.a.a.x.a aVar, int i2, boolean z, int[] iArr, int[] iArr2) {
        int length = iArr.length;
        int k = aVar.k();
        int j = z ? aVar.j(i2) : aVar.i(i2);
        boolean z2 = z;
        int i3 = 0;
        int i4 = j;
        while (j < k) {
            if (aVar.g(j) ^ z2) {
                iArr2[i3] = iArr2[i3] + 1;
            } else {
                int i5 = length - 1;
                if (i3 != i5) {
                    i3++;
                } else {
                    if (q.e(iArr2, iArr, 0.7f) < 0.48f) {
                        return new int[]{i4, j};
                    }
                    i4 += iArr2[0] + iArr2[1];
                    int i6 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i6);
                    iArr2[i6] = 0;
                    iArr2[i5] = 0;
                    i3--;
                }
                iArr2[i3] = 1;
                z2 = !z2;
            }
            j++;
        }
        throw c.a.a.l.a();
    }

    public static int[] p(c.a.a.x.a aVar) {
        int[] iArr = new int[f1191d.length];
        int[] iArr2 = null;
        boolean z = false;
        int i2 = 0;
        while (!z) {
            int[] iArr3 = f1191d;
            Arrays.fill(iArr, 0, iArr3.length, 0);
            iArr2 = o(aVar, i2, false, iArr3, iArr);
            int i3 = iArr2[0];
            int i4 = iArr2[1];
            int i5 = i3 - (i4 - i3);
            if (i5 >= 0) {
                z = aVar.m(i5, i3, false);
            }
            i2 = i4;
        }
        return iArr2;
    }

    @Override // c.a.a.a0.q
    public c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        return m(i2, aVar, p(aVar), map);
    }

    public boolean h(String str) {
        return i(str);
    }

    int[] k(c.a.a.x.a aVar, int i2) {
        return n(aVar, i2, false, f1191d);
    }

    public abstract int l(c.a.a.x.a aVar, int[] iArr, StringBuilder sb);

    public c.a.a.p m(int i2, c.a.a.x.a aVar, int[] iArr, Map<c.a.a.e, ?> map) {
        int i3;
        String c2;
        c.a.a.s sVar = map == null ? null : (c.a.a.s) map.get(c.a.a.e.NEED_RESULT_POINT_CALLBACK);
        boolean z = true;
        if (sVar != null) {
            sVar.a(new c.a.a.r((iArr[0] + iArr[1]) / 2.0f, i2));
        }
        StringBuilder sb = this.a;
        sb.setLength(0);
        int l = l(aVar, iArr, sb);
        if (sVar != null) {
            sVar.a(new c.a.a.r(l, i2));
        }
        int[] k = k(aVar, l);
        if (sVar != null) {
            sVar.a(new c.a.a.r((k[0] + k[1]) / 2.0f, i2));
        }
        int i4 = k[1];
        int i5 = (i4 - k[0]) + i4;
        if (i5 >= aVar.k() || !aVar.m(i4, i5, false)) {
            throw c.a.a.l.a();
        }
        String sb2 = sb.toString();
        if (sb2.length() < 8) {
            throw c.a.a.h.a();
        }
        if (!h(sb2)) {
            throw c.a.a.d.a();
        }
        c.a.a.a q = q();
        float f2 = i2;
        c.a.a.p pVar = new c.a.a.p(sb2, null, new c.a.a.r[]{new c.a.a.r((iArr[1] + iArr[0]) / 2.0f, f2), new c.a.a.r((k[1] + k[0]) / 2.0f, f2)}, q);
        try {
            c.a.a.p a = this.b.a(i2, aVar, k[1]);
            pVar.h(c.a.a.q.UPC_EAN_EXTENSION, a.f());
            pVar.g(a.d());
            pVar.a(a.e());
            i3 = a.f().length();
        } catch (c.a.a.o unused) {
            i3 = 0;
        }
        int[] iArr2 = map != null ? (int[]) map.get(c.a.a.e.ALLOWED_EAN_EXTENSIONS) : null;
        if (iArr2 != null) {
            int length = iArr2.length;
            int i6 = 0;
            while (true) {
                if (i6 >= length) {
                    z = false;
                    break;
                }
                if (i3 == iArr2[i6]) {
                    break;
                }
                i6++;
            }
            if (!z) {
                throw c.a.a.l.a();
            }
        }
        if ((q == c.a.a.a.EAN_13 || q == c.a.a.a.UPC_A) && (c2 = this.f1192c.c(sb2)) != null) {
            pVar.h(c.a.a.q.POSSIBLE_COUNTRY, c2);
        }
        return pVar;
    }

    abstract c.a.a.a q();
}
