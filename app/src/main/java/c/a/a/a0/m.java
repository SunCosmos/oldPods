package c.a.a.a0;

import java.util.Map;

/* loaded from: classes.dex */
public final class m extends q {
    private static final int[] b = {6, 8, 10, 12, 14};

    /* renamed from: c, reason: collision with root package name */
    private static final int[] f1187c = {1, 1, 1, 1};

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f1188d = {1, 1, 3};
    static final int[][] e = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private int a = -1;

    private static int h(int[] iArr) {
        int length = e.length;
        float f = 0.38f;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float e2 = q.e(iArr, e[i3], 0.78f);
            if (e2 < f) {
                i2 = i3;
                f = e2;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw c.a.a.l.a();
    }

    private static void j(c.a.a.x.a aVar, int i2, int i3, StringBuilder sb) {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i2 < i3) {
            q.f(aVar, i2, iArr);
            for (int i4 = 0; i4 < 5; i4++) {
                int i5 = i4 * 2;
                iArr2[i4] = iArr[i5];
                iArr3[i4] = iArr[i5 + 1];
            }
            sb.append((char) (h(iArr2) + 48));
            sb.append((char) (h(iArr3) + 48));
            for (int i6 = 0; i6 < 10; i6++) {
                i2 += iArr[i6];
            }
        }
    }

    private static int[] l(c.a.a.x.a aVar, int i2, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int k = aVar.k();
        int i3 = i2;
        boolean z = false;
        int i4 = 0;
        while (i2 < k) {
            if (aVar.g(i2) ^ z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                int i5 = length - 1;
                if (i4 != i5) {
                    i4++;
                } else {
                    if (q.e(iArr2, iArr, 0.78f) < 0.38f) {
                        return new int[]{i3, i2};
                    }
                    i3 += iArr2[0] + iArr2[1];
                    int i6 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i6);
                    iArr2[i6] = 0;
                    iArr2[i5] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                z = !z;
            }
            i2++;
        }
        throw c.a.a.l.a();
    }

    private static int m(c.a.a.x.a aVar) {
        int k = aVar.k();
        int i2 = aVar.i(0);
        if (i2 != k) {
            return i2;
        }
        throw c.a.a.l.a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001c, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void n(c.a.a.x.a r3, int r4) {
        /*
            r2 = this;
            int r0 = r2.a
            int r0 = r0 * 10
            if (r0 >= r4) goto L7
            goto L8
        L7:
            r0 = r4
        L8:
            int r4 = r4 + (-1)
        La:
            if (r0 <= 0) goto L1a
            if (r4 < 0) goto L1a
            boolean r1 = r3.g(r4)
            if (r1 == 0) goto L15
            goto L1a
        L15:
            int r0 = r0 + (-1)
            int r4 = r4 + (-1)
            goto La
        L1a:
            if (r0 != 0) goto L1d
            return
        L1d:
            c.a.a.l r3 = c.a.a.l.a()
            goto L23
        L22:
            throw r3
        L23:
            goto L22
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a0.m.n(c.a.a.x.a, int):void");
    }

    @Override // c.a.a.a0.q
    public c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        boolean z;
        int[] k = k(aVar);
        int[] i3 = i(aVar);
        StringBuilder sb = new StringBuilder(20);
        j(aVar, k[1], i3[0], sb);
        String sb2 = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(c.a.a.e.ALLOWED_LENGTHS) : null;
        if (iArr == null) {
            iArr = b;
        }
        int length = sb2.length();
        int length2 = iArr.length;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i4 >= length2) {
                z = false;
                break;
            }
            int i6 = iArr[i4];
            if (length == i6) {
                z = true;
                break;
            }
            if (i6 > i5) {
                i5 = i6;
            }
            i4++;
        }
        if (!z && length > i5) {
            z = true;
        }
        if (!z) {
            throw c.a.a.h.a();
        }
        float f = i2;
        return new c.a.a.p(sb2, null, new c.a.a.r[]{new c.a.a.r(k[1], f), new c.a.a.r(i3[0], f)}, c.a.a.a.ITF);
    }

    int[] i(c.a.a.x.a aVar) {
        aVar.o();
        try {
            int[] l = l(aVar, m(aVar), f1188d);
            n(aVar, l[0]);
            int i2 = l[0];
            l[0] = aVar.k() - l[1];
            l[1] = aVar.k() - i2;
            return l;
        } finally {
            aVar.o();
        }
    }

    int[] k(c.a.a.x.a aVar) {
        int[] l = l(aVar, m(aVar), f1187c);
        this.a = (l[1] - l[0]) / 4;
        n(aVar, l[0]);
        return l;
    }
}
