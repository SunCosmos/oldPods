package c.a.a.b0.f;

import c.a.a.c;
import c.a.a.e;
import c.a.a.r;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class a {
    private static final int[] a = {0, 4, 1, 5};
    private static final int[] b = {6, 2, 7, 3};

    /* renamed from: c, reason: collision with root package name */
    private static final int[] f1210c = {8, 1, 1, 1, 1, 1, 1, 3};

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f1211d = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private static void a(r[] rVarArr, r[] rVarArr2, int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            rVarArr[iArr[i2]] = rVarArr2[i2];
        }
    }

    public static b b(c cVar, Map<e, ?> map, boolean z) {
        c.a.a.x.b a2 = cVar.a();
        List<r[]> c2 = c(z, a2);
        if (c2.isEmpty()) {
            a2 = a2.clone();
            a2.k();
            c2 = c(z, a2);
        }
        return new b(a2, c2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001d, code lost:
    
        if (r5 != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
    
        r4 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
    
        if (r4.hasNext() == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002a, code lost:
    
        r5 = (c.a.a.r[]) r4.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
    
        if (r5[1] == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
    
        r3 = (int) java.lang.Math.max(r3, r5[1].d());
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
    
        if (r5[3] == null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0044, code lost:
    
        r3 = java.lang.Math.max(r3, (int) r5[3].d());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<c.a.a.r[]> c(boolean r8, c.a.a.x.b r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 1
            r2 = 0
            r3 = 0
        L8:
            r4 = 0
            r5 = 0
        La:
            int r6 = r9.g()
            if (r3 >= r6) goto L7a
            c.a.a.r[] r4 = f(r9, r3, r4)
            r6 = r4[r2]
            if (r6 != 0) goto L53
            r6 = 3
            r7 = r4[r6]
            if (r7 != 0) goto L53
            if (r5 != 0) goto L20
            goto L7a
        L20:
            java.util.Iterator r4 = r0.iterator()
        L24:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L50
            java.lang.Object r5 = r4.next()
            c.a.a.r[] r5 = (c.a.a.r[]) r5
            r7 = r5[r1]
            if (r7 == 0) goto L40
            float r3 = (float) r3
            r7 = r5[r1]
            float r7 = r7.d()
            float r3 = java.lang.Math.max(r3, r7)
            int r3 = (int) r3
        L40:
            r7 = r5[r6]
            if (r7 == 0) goto L24
            r5 = r5[r6]
            float r5 = r5.d()
            int r5 = (int) r5
            int r3 = java.lang.Math.max(r3, r5)
            goto L24
        L50:
            int r3 = r3 + 5
            goto L8
        L53:
            r0.add(r4)
            if (r8 != 0) goto L59
            goto L7a
        L59:
            r3 = 2
            r5 = r4[r3]
            if (r5 == 0) goto L68
            r5 = r4[r3]
            float r5 = r5.c()
            int r5 = (int) r5
            r3 = r4[r3]
            goto L72
        L68:
            r3 = 4
            r5 = r4[r3]
            float r5 = r5.c()
            int r5 = (int) r5
            r3 = r4[r3]
        L72:
            float r3 = r3.d()
            int r3 = (int) r3
            r4 = r5
            r5 = 1
            goto La
        L7a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.f.a.c(boolean, c.a.a.x.b):java.util.List");
    }

    private static int[] d(c.a.a.x.b bVar, int i2, int i3, int i4, boolean z, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int length = iArr.length;
        int i5 = 0;
        while (bVar.d(i2, i3) && i2 > 0) {
            int i6 = i5 + 1;
            if (i5 >= 3) {
                break;
            }
            i2--;
            i5 = i6;
        }
        boolean z2 = z;
        int i7 = 0;
        int i8 = i2;
        while (i2 < i4) {
            if (bVar.d(i2, i3) ^ z2) {
                iArr2[i7] = iArr2[i7] + 1;
            } else {
                int i9 = length - 1;
                if (i7 != i9) {
                    i7++;
                } else {
                    if (g(iArr2, iArr, 0.8f) < 0.42f) {
                        return new int[]{i8, i2};
                    }
                    i8 += iArr2[0] + iArr2[1];
                    int i10 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i10);
                    iArr2[i10] = 0;
                    iArr2[i9] = 0;
                    i7--;
                }
                iArr2[i7] = 1;
                z2 = !z2;
            }
            i2++;
        }
        if (i7 != length - 1 || g(iArr2, iArr, 0.8f) >= 0.42f) {
            return null;
        }
        return new int[]{i8, i2 - 1};
    }

    private static r[] e(c.a.a.x.b bVar, int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        boolean z;
        int i7;
        int i8;
        int i9;
        r[] rVarArr = new r[4];
        int[] iArr2 = new int[iArr.length];
        int i10 = i4;
        while (true) {
            if (i10 >= i2) {
                z = false;
                break;
            }
            int[] d2 = d(bVar, i5, i10, i3, false, iArr, iArr2);
            if (d2 != null) {
                int i11 = i10;
                int[] iArr3 = d2;
                int i12 = i11;
                while (true) {
                    if (i12 <= 0) {
                        i9 = i12;
                        break;
                    }
                    int i13 = i12 - 1;
                    int[] d3 = d(bVar, i5, i13, i3, false, iArr, iArr2);
                    if (d3 == null) {
                        i9 = i13 + 1;
                        break;
                    }
                    iArr3 = d3;
                    i12 = i13;
                }
                float f = i9;
                rVarArr[0] = new r(iArr3[0], f);
                rVarArr[1] = new r(iArr3[1], f);
                i10 = i9;
                z = true;
            } else {
                i10 += 5;
            }
        }
        int i14 = i10 + 1;
        if (z) {
            int[] iArr4 = {(int) rVarArr[0].c(), (int) rVarArr[1].c()};
            int i15 = i14;
            int i16 = 0;
            while (true) {
                if (i15 >= i2) {
                    i7 = i16;
                    i8 = i15;
                    break;
                }
                i7 = i16;
                i8 = i15;
                int[] d4 = d(bVar, iArr4[0], i15, i3, false, iArr, iArr2);
                if (d4 != null && Math.abs(iArr4[0] - d4[0]) < 5 && Math.abs(iArr4[1] - d4[1]) < 5) {
                    iArr4 = d4;
                    i16 = 0;
                } else {
                    if (i7 > 25) {
                        break;
                    }
                    i16 = i7 + 1;
                }
                i15 = i8 + 1;
            }
            i14 = i8 - (i7 + 1);
            float f2 = i14;
            rVarArr[2] = new r(iArr4[0], f2);
            rVarArr[3] = new r(iArr4[1], f2);
        }
        if (i14 - i10 < 10) {
            for (i6 = 0; i6 < 4; i6++) {
                rVarArr[i6] = null;
            }
        }
        return rVarArr;
    }

    private static r[] f(c.a.a.x.b bVar, int i2, int i3) {
        int g = bVar.g();
        int j = bVar.j();
        r[] rVarArr = new r[8];
        a(rVarArr, e(bVar, g, j, i2, i3, f1210c), a);
        if (rVarArr[4] != null) {
            i3 = (int) rVarArr[4].c();
            i2 = (int) rVarArr[4].d();
        }
        a(rVarArr, e(bVar, g, j, i2, i3, f1211d), b);
        return rVarArr;
    }

    private static float g(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i2;
        float f3 = f2 / i3;
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            float f6 = iArr2[i5] * f3;
            float f7 = iArr[i5];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }
}
