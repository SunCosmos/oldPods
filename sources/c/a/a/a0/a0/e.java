package c.a.a.a0.a0;

import bsh.org.objectweb.asm.Constants;
import c.a.a.a0.q;
import c.a.a.l;
import c.a.a.p;
import c.a.a.r;
import c.a.a.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class e extends a {

    /* renamed from: i, reason: collision with root package name */
    private static final int[] f1167i = {1, 10, 34, 70, 126};
    private static final int[] j = {4, 20, 48, 81};
    private static final int[] k = {0, Constants.IF_ICMPLT, 961, 2015, 2715};
    private static final int[] l = {0, 336, 1036, 1516};
    private static final int[] m = {8, 6, 4, 3, 1};
    private static final int[] n = {2, 4, 6, 8};
    private static final int[][] o = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private final List<d> g = new ArrayList();
    private final List<d> h = new ArrayList();

    private static void s(Collection<d> collection, d dVar) {
        if (dVar == null) {
            return;
        }
        boolean z = false;
        Iterator<d> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            d next = it.next();
            if (next.b() == dVar.b()) {
                next.e();
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        collection.add(dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
    
        if (r1 < 4) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
    
        r10 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x004f, code lost:
    
        r10 = false;
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x004d, code lost:
    
        if (r1 < 4) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void t(boolean r10, int r11) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a0.a0.e.t(boolean, int):void");
    }

    private static boolean u(d dVar, d dVar2) {
        int a = (dVar.a() + (dVar2.a() * 16)) % 79;
        int c2 = (dVar.d().c() * 9) + dVar2.d().c();
        if (c2 > 72) {
            c2--;
        }
        if (c2 > 8) {
            c2--;
        }
        return a == c2;
    }

    private static p v(d dVar, d dVar2) {
        String valueOf = String.valueOf((dVar.b() * 4537077) + dVar2.b());
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int charAt = sb.charAt(i3) - '0';
            if ((i3 & 1) == 0) {
                charAt *= 3;
            }
            i2 += charAt;
        }
        int i4 = 10 - (i2 % 10);
        if (i4 == 10) {
            i4 = 0;
        }
        sb.append(i4);
        r[] a = dVar.d().a();
        r[] a2 = dVar2.d().a();
        return new p(String.valueOf(sb.toString()), null, new r[]{a[0], a[1], a2[0], a2[1]}, c.a.a.a.RSS_14);
    }

    private b w(c.a.a.x.a aVar, c cVar, boolean z) {
        int[] j2 = j();
        j2[0] = 0;
        j2[1] = 0;
        j2[2] = 0;
        j2[3] = 0;
        j2[4] = 0;
        j2[5] = 0;
        j2[6] = 0;
        j2[7] = 0;
        int[] b = cVar.b();
        if (z) {
            q.g(aVar, b[0], j2);
        } else {
            q.f(aVar, b[1] + 1, j2);
            int i2 = 0;
            for (int length = j2.length - 1; i2 < length; length--) {
                int i3 = j2[i2];
                j2[i2] = j2[length];
                j2[length] = i3;
                i2++;
            }
        }
        int i4 = z ? 16 : 15;
        float h = a.h(j2) / i4;
        int[] n2 = n();
        int[] l2 = l();
        float[] o2 = o();
        float[] m2 = m();
        for (int i5 = 0; i5 < j2.length; i5++) {
            float f = j2[i5] / h;
            int i6 = (int) (0.5f + f);
            if (i6 < 1) {
                i6 = 1;
            } else if (i6 > 8) {
                i6 = 8;
            }
            int i7 = i5 / 2;
            if ((i5 & 1) == 0) {
                n2[i7] = i6;
                o2[i7] = f - i6;
            } else {
                l2[i7] = i6;
                m2[i7] = f - i6;
            }
        }
        t(z, i4);
        int i8 = 0;
        int i9 = 0;
        for (int length2 = n2.length - 1; length2 >= 0; length2--) {
            i8 = (i8 * 9) + n2[length2];
            i9 += n2[length2];
        }
        int i10 = 0;
        int i11 = 0;
        for (int length3 = l2.length - 1; length3 >= 0; length3--) {
            i10 = (i10 * 9) + l2[length3];
            i11 += l2[length3];
        }
        int i12 = i8 + (i10 * 3);
        if (!z) {
            if ((i11 & 1) != 0 || i11 > 10 || i11 < 4) {
                throw l.a();
            }
            int i13 = (10 - i11) / 2;
            int i14 = n[i13];
            return new b((f.b(l2, 9 - i14, false) * j[i13]) + f.b(n2, i14, true) + l[i13], i12);
        }
        if ((i9 & 1) != 0 || i9 > 12 || i9 < 4) {
            throw l.a();
        }
        int i15 = (12 - i9) / 2;
        int i16 = m[i15];
        return new b((f.b(n2, i16, false) * f1167i[i15]) + f.b(l2, 9 - i16, true) + k[i15], i12);
    }

    private d x(c.a.a.x.a aVar, boolean z, int i2, Map<c.a.a.e, ?> map) {
        try {
            c z2 = z(aVar, i2, z, y(aVar, 0, z));
            s sVar = map == null ? null : (s) map.get(c.a.a.e.NEED_RESULT_POINT_CALLBACK);
            if (sVar != null) {
                float f = (r2[0] + r2[1]) / 2.0f;
                if (z) {
                    f = (aVar.k() - 1) - f;
                }
                sVar.a(new r(f, i2));
            }
            b w = w(aVar, z2, true);
            b w2 = w(aVar, z2, false);
            return new d((w.b() * 1597) + w2.b(), w.a() + (w2.a() * 4), z2);
        } catch (l unused) {
            return null;
        }
    }

    private int[] y(c.a.a.x.a aVar, int i2, boolean z) {
        int[] k2 = k();
        k2[0] = 0;
        k2[1] = 0;
        k2[2] = 0;
        k2[3] = 0;
        int k3 = aVar.k();
        boolean z2 = false;
        while (i2 < k3) {
            z2 = !aVar.g(i2);
            if (z == z2) {
                break;
            }
            i2++;
        }
        int i3 = i2;
        int i4 = 0;
        while (i2 < k3) {
            if (aVar.g(i2) ^ z2) {
                k2[i4] = k2[i4] + 1;
            } else {
                if (i4 != 3) {
                    i4++;
                } else {
                    if (a.q(k2)) {
                        return new int[]{i3, i2};
                    }
                    i3 += k2[0] + k2[1];
                    k2[0] = k2[2];
                    k2[1] = k2[3];
                    k2[2] = 0;
                    k2[3] = 0;
                    i4--;
                }
                k2[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw l.a();
    }

    private c z(c.a.a.x.a aVar, int i2, boolean z, int[] iArr) {
        int i3;
        int i4;
        boolean g = aVar.g(iArr[0]);
        int i5 = iArr[0] - 1;
        while (i5 >= 0 && (aVar.g(i5) ^ g)) {
            i5--;
        }
        int i6 = i5 + 1;
        int i7 = iArr[0] - i6;
        int[] k2 = k();
        System.arraycopy(k2, 0, k2, 1, k2.length - 1);
        k2[0] = i7;
        int r = a.r(k2, o);
        int i8 = iArr[1];
        if (z) {
            int k3 = (aVar.k() - 1) - i6;
            i3 = (aVar.k() - 1) - i8;
            i4 = k3;
        } else {
            i3 = i8;
            i4 = i6;
        }
        return new c(r, new int[]{i6, iArr[1]}, i4, i3, i2);
    }

    @Override // c.a.a.a0.q, c.a.a.n
    public void b() {
        this.g.clear();
        this.h.clear();
    }

    @Override // c.a.a.a0.q
    public p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        s(this.g, x(aVar, false, i2, map));
        aVar.o();
        s(this.h, x(aVar, true, i2, map));
        aVar.o();
        int size = this.g.size();
        for (int i3 = 0; i3 < size; i3++) {
            d dVar = this.g.get(i3);
            if (dVar.c() > 1) {
                int size2 = this.h.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    d dVar2 = this.h.get(i4);
                    if (dVar2.c() > 1 && u(dVar, dVar2)) {
                        return v(dVar, dVar2);
                    }
                }
            }
        }
        throw l.a();
    }
}
