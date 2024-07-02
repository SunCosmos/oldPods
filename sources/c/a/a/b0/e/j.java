package c.a.a.b0.e;

import c.a.a.l;
import c.a.a.r;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class j {
    private static final c.a.a.b0.e.k.a a = new c.a.a.b0.e.k.a();

    private static c a(h hVar) {
        int[] j;
        if (hVar == null || (j = hVar.j()) == null) {
            return null;
        }
        int p = p(j);
        int i2 = 0;
        int i3 = 0;
        for (int i4 : j) {
            i3 += p - i4;
            if (i4 > 0) {
                break;
            }
        }
        d[] d2 = hVar.d();
        for (int i5 = 0; i3 > 0 && d2[i5] == null; i5++) {
            i3--;
        }
        for (int length = j.length - 1; length >= 0; length--) {
            i2 += p - j[length];
            if (j[length] > 0) {
                break;
            }
        }
        for (int length2 = d2.length - 1; i2 > 0 && d2[length2] == null; length2--) {
            i2--;
        }
        return hVar.a().a(i3, i2, hVar.k());
    }

    private static void b(f fVar, b[][] bVarArr) {
        b bVar;
        int[] a2 = bVarArr[0][1].a();
        int j = (fVar.j() * fVar.l()) - r(fVar.k());
        if (a2.length == 0) {
            if (j < 1 || j > 928) {
                throw l.a();
            }
            bVar = bVarArr[0][1];
        } else if (a2[0] == j) {
            return;
        } else {
            bVar = bVarArr[0][1];
        }
        bVar.b(j);
    }

    private static int c(c.a.a.x.b bVar, int i2, int i3, boolean z, int i4, int i5) {
        int i6 = z ? -1 : 1;
        int i7 = i4;
        for (int i8 = 0; i8 < 2; i8++) {
            while (true) {
                if (((z && i7 >= i2) || (!z && i7 < i3)) && z == bVar.d(i7, i5)) {
                    if (Math.abs(i4 - i7) > 2) {
                        return i4;
                    }
                    i7 += i6;
                }
            }
            i6 = -i6;
            z = !z;
        }
        return i7;
    }

    private static boolean d(int i2, int i3, int i4) {
        return i3 + (-2) <= i2 && i2 <= i4 + 2;
    }

    private static int e(int[] iArr, int[] iArr2, int i2) {
        if ((iArr2 == null || iArr2.length <= (i2 / 2) + 3) && i2 >= 0 && i2 <= 512) {
            return a.a(iArr, i2, iArr2);
        }
        throw c.a.a.d.a();
    }

    private static b[][] f(f fVar) {
        int c2;
        b[][] bVarArr = (b[][]) Array.newInstance((Class<?>) b.class, fVar.l(), fVar.j() + 2);
        for (int i2 = 0; i2 < bVarArr.length; i2++) {
            for (int i3 = 0; i3 < bVarArr[i2].length; i3++) {
                bVarArr[i2][i3] = new b();
            }
        }
        int i4 = 0;
        for (g gVar : fVar.o()) {
            if (gVar != null) {
                for (d dVar : gVar.d()) {
                    if (dVar != null && (c2 = dVar.c()) >= 0) {
                        if (c2 >= bVarArr.length) {
                            throw c.a.a.h.a();
                        }
                        bVarArr[c2][i4].b(dVar.e());
                    }
                }
            }
            i4++;
        }
        return bVarArr;
    }

    private static c.a.a.x.e g(f fVar) {
        b[][] f = f(fVar);
        b(fVar, f);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[fVar.l() * fVar.j()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < fVar.l(); i2++) {
            int i3 = 0;
            while (i3 < fVar.j()) {
                int i4 = i3 + 1;
                int[] a2 = f[i2][i4].a();
                int j = (fVar.j() * i2) + i3;
                if (a2.length == 0) {
                    arrayList.add(Integer.valueOf(j));
                } else if (a2.length == 1) {
                    iArr[j] = a2[0];
                } else {
                    arrayList3.add(Integer.valueOf(j));
                    arrayList2.add(a2);
                }
                i3 = i4;
            }
        }
        int size = arrayList2.size();
        int[][] iArr2 = new int[size];
        for (int i5 = 0; i5 < size; i5++) {
            iArr2[i5] = (int[]) arrayList2.get(i5);
        }
        return h(fVar.k(), iArr, c.a.a.b0.a.c(arrayList), c.a.a.b0.a.c(arrayList3), iArr2);
    }

    private static c.a.a.x.e h(int i2, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) {
        int length = iArr3.length;
        int[] iArr5 = new int[length];
        int i3 = 100;
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                throw c.a.a.d.a();
            }
            for (int i5 = 0; i5 < length; i5++) {
                iArr[iArr3[i5]] = iArr4[i5][iArr5[i5]];
            }
            try {
                return j(iArr, i2, iArr2);
            } catch (c.a.a.d unused) {
                if (length == 0) {
                    throw c.a.a.d.a();
                }
                int i6 = 0;
                while (true) {
                    if (i6 >= length) {
                        break;
                    }
                    if (iArr5[i6] < iArr4[i6].length - 1) {
                        iArr5[i6] = iArr5[i6] + 1;
                        break;
                    }
                    iArr5[i6] = 0;
                    if (i6 == length - 1) {
                        throw c.a.a.d.a();
                    }
                    i6++;
                }
                i3 = i4;
            }
        }
    }

    public static c.a.a.x.e i(c.a.a.x.b bVar, r rVar, r rVar2, r rVar3, r rVar4, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        h hVar = null;
        h hVar2 = null;
        f fVar = null;
        c cVar = new c(bVar, rVar, rVar2, rVar3, rVar4);
        for (int i7 = 0; i7 < 2; i7++) {
            if (rVar != null) {
                hVar = s(bVar, cVar, rVar, true, i2, i3);
            }
            if (rVar3 != null) {
                hVar2 = s(bVar, cVar, rVar3, false, i2, i3);
            }
            fVar = v(hVar, hVar2);
            if (fVar == null) {
                throw l.a();
            }
            if (i7 != 0 || fVar.m() == null || (fVar.m().h() >= cVar.h() && fVar.m().f() <= cVar.f())) {
                fVar.p(cVar);
                break;
            }
            cVar = fVar.m();
        }
        int j = fVar.j() + 1;
        fVar.q(0, hVar);
        fVar.q(j, hVar2);
        boolean z = hVar != null;
        int i8 = i2;
        int i9 = i3;
        for (int i10 = 1; i10 <= j; i10++) {
            int i11 = z ? i10 : j - i10;
            if (fVar.n(i11) == null) {
                g hVar3 = (i11 == 0 || i11 == j) ? new h(cVar, i11 == 0) : new g(cVar);
                fVar.q(i11, hVar3);
                int i12 = -1;
                int h = cVar.h();
                int i13 = -1;
                while (h <= cVar.f()) {
                    int t = t(fVar, i11, h, z);
                    if (t >= 0 && t <= cVar.e()) {
                        i4 = t;
                    } else if (i13 == i12) {
                        i5 = i13;
                        i6 = h;
                        i13 = i5;
                        h = i6 + 1;
                        i12 = -1;
                    } else {
                        i4 = i13;
                    }
                    i5 = i13;
                    int i14 = h;
                    d k = k(bVar, cVar.g(), cVar.e(), z, i4, i14, i8, i9);
                    i6 = i14;
                    if (k != null) {
                        hVar3.f(i6, k);
                        i8 = Math.min(i8, k.f());
                        i9 = Math.max(i9, k.f());
                        i13 = i4;
                        h = i6 + 1;
                        i12 = -1;
                    }
                    i13 = i5;
                    h = i6 + 1;
                    i12 = -1;
                }
            }
        }
        return g(fVar);
    }

    private static c.a.a.x.e j(int[] iArr, int i2, int[] iArr2) {
        if (iArr.length == 0) {
            throw c.a.a.h.a();
        }
        int i3 = 1 << (i2 + 1);
        int e = e(iArr, iArr2, i3);
        w(iArr, i3);
        c.a.a.x.e b = e.b(iArr, String.valueOf(i2));
        b.j(Integer.valueOf(e));
        b.i(Integer.valueOf(iArr2.length));
        return b;
    }

    private static d k(c.a.a.x.b bVar, int i2, int i3, boolean z, int i4, int i5, int i6, int i7) {
        int i8;
        int d2;
        int b;
        int c2 = c(bVar, i2, i3, z, i4, i5);
        int[] q = q(bVar, i2, i3, z, c2, i5);
        if (q == null) {
            return null;
        }
        int a2 = c.a.a.b0.a.a(q);
        if (z) {
            i8 = c2 + a2;
        } else {
            for (int i9 = 0; i9 < q.length / 2; i9++) {
                int i10 = q[i9];
                q[i9] = q[(q.length - 1) - i9];
                q[(q.length - 1) - i9] = i10;
            }
            c2 -= a2;
            i8 = c2;
        }
        if (d(a2, i6, i7) && (b = c.a.a.b0.a.b((d2 = i.d(q)))) != -1) {
            return new d(c2, i8, n(d2), b);
        }
        return null;
    }

    private static a l(h hVar, h hVar2) {
        a i2;
        a i3;
        if (hVar == null || (i2 = hVar.i()) == null) {
            if (hVar2 == null) {
                return null;
            }
            return hVar2.i();
        }
        if (hVar2 == null || (i3 = hVar2.i()) == null || i2.a() == i3.a() || i2.b() == i3.b() || i2.c() == i3.c()) {
            return i2;
        }
        return null;
    }

    private static int[] m(int i2) {
        int[] iArr = new int[8];
        int i3 = 0;
        int i4 = 7;
        while (true) {
            int i5 = i2 & 1;
            if (i5 != i3) {
                i4--;
                if (i4 < 0) {
                    return iArr;
                }
                i3 = i5;
            }
            iArr[i4] = iArr[i4] + 1;
            i2 >>= 1;
        }
    }

    private static int n(int i2) {
        return o(m(i2));
    }

    private static int o(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    private static int p(int[] iArr) {
        int i2 = -1;
        for (int i3 : iArr) {
            i2 = Math.max(i2, i3);
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002a, code lost:
    
        if (r10 == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002c, code lost:
    
        if (r11 == r9) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0033, code lost:
    
        if (r4 != 7) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x002e, code lost:
    
        if (r10 != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0030, code lost:
    
        if (r11 != r8) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0036, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] q(c.a.a.x.b r7, int r8, int r9, boolean r10, int r11, int r12) {
        /*
            r0 = 8
            int[] r1 = new int[r0]
            r2 = 1
            if (r10 == 0) goto L9
            r3 = 1
            goto La
        L9:
            r3 = -1
        La:
            r4 = 0
            r5 = r10
        Lc:
            if (r10 == 0) goto L10
            if (r11 < r9) goto L14
        L10:
            if (r10 != 0) goto L28
            if (r11 < r8) goto L28
        L14:
            if (r4 >= r0) goto L28
            boolean r6 = r7.d(r11, r12)
            if (r6 != r5) goto L23
            r6 = r1[r4]
            int r6 = r6 + r2
            r1[r4] = r6
            int r11 = r11 + r3
            goto Lc
        L23:
            int r4 = r4 + 1
            r5 = r5 ^ 1
            goto Lc
        L28:
            if (r4 == r0) goto L38
            if (r10 == 0) goto L2e
            if (r11 == r9) goto L32
        L2e:
            if (r10 != 0) goto L36
            if (r11 != r8) goto L36
        L32:
            r7 = 7
            if (r4 != r7) goto L36
            goto L38
        L36:
            r7 = 0
            return r7
        L38:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.e.j.q(c.a.a.x.b, int, int, boolean, int, int):int[]");
    }

    private static int r(int i2) {
        return 2 << i2;
    }

    private static h s(c.a.a.x.b bVar, c cVar, r rVar, boolean z, int i2, int i3) {
        h hVar = new h(cVar, z);
        int i4 = 0;
        while (i4 < 2) {
            int i5 = i4 == 0 ? 1 : -1;
            int c2 = (int) rVar.c();
            for (int d2 = (int) rVar.d(); d2 <= cVar.f() && d2 >= cVar.h(); d2 += i5) {
                d k = k(bVar, 0, bVar.j(), z, c2, d2, i2, i3);
                if (k != null) {
                    hVar.f(d2, k);
                    c2 = z ? k.d() : k.b();
                }
            }
            i4++;
        }
        return hVar;
    }

    private static int t(f fVar, int i2, int i3, boolean z) {
        int i4 = z ? 1 : -1;
        int i5 = i2 - i4;
        d b = u(fVar, i5) ? fVar.n(i5).b(i3) : null;
        if (b != null) {
            return z ? b.b() : b.d();
        }
        d c2 = fVar.n(i2).c(i3);
        if (c2 != null) {
            return z ? c2.d() : c2.b();
        }
        if (u(fVar, i5)) {
            c2 = fVar.n(i5).c(i3);
        }
        if (c2 != null) {
            return z ? c2.b() : c2.d();
        }
        int i6 = 0;
        while (true) {
            i2 -= i4;
            if (!u(fVar, i2)) {
                c m = fVar.m();
                return z ? m.g() : m.e();
            }
            for (d dVar : fVar.n(i2).d()) {
                if (dVar != null) {
                    return (z ? dVar.b() : dVar.d()) + (i4 * i6 * (dVar.b() - dVar.d()));
                }
            }
            i6++;
        }
    }

    private static boolean u(f fVar, int i2) {
        return i2 >= 0 && i2 <= fVar.j() + 1;
    }

    private static f v(h hVar, h hVar2) {
        a l;
        if ((hVar == null && hVar2 == null) || (l = l(hVar, hVar2)) == null) {
            return null;
        }
        return new f(l, c.l(a(hVar), a(hVar2)));
    }

    private static void w(int[] iArr, int i2) {
        if (iArr.length < 4) {
            throw c.a.a.h.a();
        }
        int i3 = iArr[0];
        if (i3 > iArr.length) {
            throw c.a.a.h.a();
        }
        if (i3 == 0) {
            if (i2 >= iArr.length) {
                throw c.a.a.h.a();
            }
            iArr[0] = iArr.length - i2;
        }
    }
}
