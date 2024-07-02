package c.a.a.a0.a0.g;

import bsh.org.objectweb.asm.Constants;
import c.a.a.a0.a0.f;
import c.a.a.a0.a0.g.e.j;
import c.a.a.a0.q;
import c.a.a.e;
import c.a.a.l;
import c.a.a.p;
import c.a.a.r;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class d extends c.a.a.a0.a0.a {
    private static final int[] k = {7, 5, 4, 3, 1};
    private static final int[] l = {4, 20, 52, 104, 204};
    private static final int[] m = {0, 348, 1388, 2948, 3988};
    private static final int[][] n = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] o = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, Constants.GETFIELD, 118, Constants.D2L, 7, 21, 63}, new int[]{Constants.ANEWARRAY, Constants.I2B, 13, 39, 117, Constants.F2L, 209, 205}, new int[]{Constants.INSTANCEOF, Constants.IFGT, 49, Constants.I2S, 19, 57, Constants.LOOKUPSWITCH, 91}, new int[]{62, 186, Constants.L2I, Constants.MULTIANEWARRAY, Constants.RET, 85, 44, 132}, new int[]{Constants.INVOKEINTERFACE, 133, Constants.NEWARRAY, Constants.D2I, 4, 12, 36, 108}, new int[]{113, 128, Constants.LRETURN, 97, 80, 29, 87, 50}, new int[]{Constants.FCMPG, 28, 84, 41, 123, Constants.IFLE, 52, Constants.IFGE}, new int[]{46, Constants.L2D, 203, Constants.NEW, Constants.F2I, 206, 196, Constants.IF_ACMPNE}, new int[]{76, 17, 51, Constants.IFEQ, 37, 111, 122, Constants.IFLT}, new int[]{43, 129, Constants.ARETURN, 106, 107, 110, 119, Constants.I2C}, new int[]{16, 48, Constants.D2F, 10, 30, 90, 59, Constants.RETURN}, new int[]{109, 116, Constants.L2F, 200, Constants.GETSTATIC, 112, 125, Constants.IF_ICMPLE}, new int[]{70, 210, 208, 202, Constants.INVOKESTATIC, 130, Constants.PUTSTATIC, 115}, new int[]{Constants.I2F, Constants.ATHROW, Constants.DCMPL, 31, 93, 68, 204, Constants.ARRAYLENGTH}, new int[]{Constants.LCMP, 22, 66, Constants.IFNULL, Constants.IRETURN, 94, 71, 2}, new int[]{6, 18, 54, Constants.IF_ICMPGE, 64, Constants.CHECKCAST, Constants.IFNE, 40}, new int[]{120, Constants.FCMPL, 25, 75, 14, 42, 126, Constants.GOTO}, new int[]{79, 26, 78, 23, 69, 207, Constants.IFNONNULL, Constants.DRETURN}, new int[]{103, 98, 83, 38, 114, 131, Constants.INVOKEVIRTUAL, 124}, new int[]{Constants.IF_ICMPLT, 61, Constants.INVOKESPECIAL, 127, Constants.TABLESWITCH, 88, 53, Constants.IF_ICMPEQ}, new int[]{55, Constants.IF_ACMPEQ, 73, 8, 24, 72, 5, 15}, new int[]{45, Constants.I2D, Constants.MONITORENTER, Constants.IF_ICMPNE, 58, Constants.FRETURN, 100, 89}};
    private static final int[][] p = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private final List<b> g = new ArrayList(11);
    private final List<c> h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private final int[] f1170i = new int[2];
    private boolean j = false;

    private static int A(c.a.a.x.a aVar, int i2) {
        return aVar.g(i2) ? aVar.i(aVar.j(i2)) : aVar.j(aVar.i(i2));
    }

    private static boolean B(c.a.a.a0.a0.c cVar, boolean z, boolean z2) {
        return (cVar.c() == 0 && z && z2) ? false : true;
    }

    private static boolean C(Iterable<b> iterable, Iterable<c> iterable2) {
        boolean z;
        boolean z2;
        Iterator<c> it = iterable2.iterator();
        do {
            z = false;
            if (!it.hasNext()) {
                return false;
            }
            c next = it.next();
            Iterator<b> it2 = iterable.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    break;
                }
                b next2 = it2.next();
                Iterator<b> it3 = next.a().iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z2 = false;
                        break;
                    }
                    if (next2.equals(it3.next())) {
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    break;
                }
            }
        } while (!z);
        return true;
    }

    private static boolean D(List<b> list) {
        boolean z;
        for (int[] iArr : p) {
            if (list.size() <= iArr.length) {
                int i2 = 0;
                while (true) {
                    if (i2 >= list.size()) {
                        z = true;
                        break;
                    }
                    if (list.get(i2).b().c() != iArr[i2]) {
                        z = false;
                        break;
                    }
                    i2++;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private c.a.a.a0.a0.c E(c.a.a.x.a aVar, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        if (z) {
            int i6 = this.f1170i[0] - 1;
            while (i6 >= 0 && !aVar.g(i6)) {
                i6--;
            }
            int i7 = i6 + 1;
            int[] iArr = this.f1170i;
            i5 = iArr[0] - i7;
            i3 = iArr[1];
            i4 = i7;
        } else {
            int[] iArr2 = this.f1170i;
            int i8 = iArr2[0];
            int j = aVar.j(iArr2[1] + 1);
            i3 = j;
            i4 = i8;
            i5 = j - this.f1170i[1];
        }
        int[] k2 = k();
        System.arraycopy(k2, 0, k2, 1, k2.length - 1);
        k2[0] = i5;
        try {
            return new c.a.a.a0.a0.c(c.a.a.a0.a0.a.r(k2, n), new int[]{i4, i3}, i4, i3, i2);
        } catch (l unused) {
            return null;
        }
    }

    private static void F(List<b> list, List<c> list2) {
        boolean z;
        Iterator<c> it = list2.iterator();
        while (it.hasNext()) {
            c next = it.next();
            if (next.a().size() != list.size()) {
                Iterator<b> it2 = next.a().iterator();
                while (true) {
                    z = false;
                    boolean z2 = true;
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    b next2 = it2.next();
                    Iterator<b> it3 = list.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z2 = false;
                            break;
                        } else if (next2.equals(it3.next())) {
                            break;
                        }
                    }
                    if (!z2) {
                        break;
                    }
                }
                if (z) {
                    it.remove();
                }
            }
        }
    }

    private static void H(int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length / 2; i2++) {
            int i3 = iArr[i2];
            int i4 = (length - i2) - 1;
            iArr[i2] = iArr[i4];
            iArr[i4] = i3;
        }
    }

    private void I(int i2, boolean z) {
        boolean z2 = false;
        int i3 = 0;
        boolean z3 = false;
        while (true) {
            if (i3 >= this.h.size()) {
                break;
            }
            c cVar = this.h.get(i3);
            if (cVar.b() > i2) {
                z2 = cVar.c(this.g);
                break;
            } else {
                z3 = cVar.c(this.g);
                i3++;
            }
        }
        if (z2 || z3 || C(this.g, this.h)) {
            return;
        }
        this.h.add(i3, new c(this.g, i2, z));
        F(this.g, this.h);
    }

    private void s(int i2) {
        boolean z;
        boolean z2;
        boolean z3;
        int h = c.a.a.a0.a0.a.h(n());
        int h2 = c.a.a.a0.a0.a.h(l());
        int i3 = (h + h2) - i2;
        boolean z4 = true;
        boolean z5 = (h & 1) == 1;
        boolean z6 = (h2 & 1) == 0;
        if (h > 13) {
            z = false;
            z2 = true;
        } else {
            z = h < 4;
            z2 = false;
        }
        if (h2 > 13) {
            z3 = true;
        } else {
            r3 = h2 < 4;
            z3 = false;
        }
        if (i3 == 1) {
            if (z5) {
                if (z6) {
                    throw l.a();
                }
                z4 = z;
                z2 = true;
            } else {
                if (!z6) {
                    throw l.a();
                }
                z4 = z;
                z3 = true;
            }
        } else if (i3 == -1) {
            if (z5) {
                if (z6) {
                    throw l.a();
                }
            } else {
                if (!z6) {
                    throw l.a();
                }
                z4 = z;
                r3 = true;
            }
        } else {
            if (i3 != 0) {
                throw l.a();
            }
            if (z5) {
                if (!z6) {
                    throw l.a();
                }
                if (h >= h2) {
                    z4 = z;
                    r3 = true;
                    z2 = true;
                }
                z3 = true;
            } else {
                if (z6) {
                    throw l.a();
                }
                z4 = z;
            }
        }
        if (z4) {
            if (z2) {
                throw l.a();
            }
            c.a.a.a0.a0.a.p(n(), o());
        }
        if (z2) {
            c.a.a.a0.a0.a.i(n(), o());
        }
        if (r3) {
            if (z3) {
                throw l.a();
            }
            c.a.a.a0.a0.a.p(l(), o());
        }
        if (z3) {
            c.a.a.a0.a0.a.i(l(), m());
        }
    }

    private boolean t() {
        b bVar = this.g.get(0);
        c.a.a.a0.a0.b c2 = bVar.c();
        c.a.a.a0.a0.b d2 = bVar.d();
        if (d2 == null) {
            return false;
        }
        int a = d2.a();
        int i2 = 2;
        for (int i3 = 1; i3 < this.g.size(); i3++) {
            b bVar2 = this.g.get(i3);
            a += bVar2.c().a();
            i2++;
            c.a.a.a0.a0.b d3 = bVar2.d();
            if (d3 != null) {
                a += d3.a();
                i2++;
            }
        }
        return ((i2 + (-4)) * 211) + (a % 211) == c2.b();
    }

    private List<b> u(List<c> list, int i2) {
        while (i2 < this.h.size()) {
            c cVar = this.h.get(i2);
            this.g.clear();
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.g.addAll(list.get(i3).a());
            }
            this.g.addAll(cVar.a());
            if (D(this.g)) {
                if (t()) {
                    return this.g;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(cVar);
                try {
                    return u(arrayList, i2 + 1);
                } catch (l unused) {
                    continue;
                }
            }
            i2++;
        }
        throw l.a();
    }

    private List<b> v(boolean z) {
        List<b> list = null;
        if (this.h.size() > 25) {
            this.h.clear();
            return null;
        }
        this.g.clear();
        if (z) {
            Collections.reverse(this.h);
        }
        try {
            list = u(new ArrayList(), 0);
        } catch (l unused) {
        }
        if (z) {
            Collections.reverse(this.h);
        }
        return list;
    }

    static p w(List<b> list) {
        String d2 = j.a(a.a(list)).d();
        r[] a = list.get(0).b().a();
        r[] a2 = list.get(list.size() - 1).b().a();
        return new p(d2, null, new r[]{a[0], a[1], a2[0], a2[1]}, c.a.a.a.RSS_EXPANDED);
    }

    private void z(c.a.a.x.a aVar, List<b> list, int i2) {
        int[] k2 = k();
        k2[0] = 0;
        k2[1] = 0;
        k2[2] = 0;
        k2[3] = 0;
        int k3 = aVar.k();
        if (i2 < 0) {
            i2 = list.isEmpty() ? 0 : list.get(list.size() - 1).b().b()[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.j) {
            z = !z;
        }
        boolean z2 = false;
        while (i2 < k3) {
            z2 = !aVar.g(i2);
            if (!z2) {
                break;
            } else {
                i2++;
            }
        }
        boolean z3 = z2;
        int i3 = 0;
        int i4 = i2;
        while (i2 < k3) {
            if (aVar.g(i2) ^ z3) {
                k2[i3] = k2[i3] + 1;
            } else {
                if (i3 == 3) {
                    if (z) {
                        H(k2);
                    }
                    if (c.a.a.a0.a0.a.q(k2)) {
                        int[] iArr = this.f1170i;
                        iArr[0] = i4;
                        iArr[1] = i2;
                        return;
                    }
                    if (z) {
                        H(k2);
                    }
                    i4 += k2[0] + k2[1];
                    k2[0] = k2[2];
                    k2[1] = k2[3];
                    k2[2] = 0;
                    k2[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                k2[i3] = 1;
                z3 = !z3;
            }
            i2++;
        }
        throw l.a();
    }

    b G(c.a.a.x.a aVar, List<b> list, int i2) {
        c.a.a.a0.a0.c E;
        c.a.a.a0.a0.b bVar;
        boolean z = list.size() % 2 == 0;
        if (this.j) {
            z = !z;
        }
        int i3 = -1;
        boolean z2 = true;
        do {
            z(aVar, list, i3);
            E = E(aVar, i2, z);
            if (E == null) {
                i3 = A(aVar, this.f1170i[0]);
            } else {
                z2 = false;
            }
        } while (z2);
        c.a.a.a0.a0.b x = x(aVar, E, z, true);
        if (!list.isEmpty() && list.get(list.size() - 1).f()) {
            throw l.a();
        }
        try {
            bVar = x(aVar, E, z, false);
        } catch (l unused) {
            bVar = null;
        }
        return new b(x, bVar, E, true);
    }

    @Override // c.a.a.a0.q, c.a.a.n
    public void b() {
        this.g.clear();
        this.h.clear();
    }

    @Override // c.a.a.a0.q
    public p c(int i2, c.a.a.x.a aVar, Map<e, ?> map) {
        this.g.clear();
        this.j = false;
        try {
            return w(y(i2, aVar));
        } catch (l unused) {
            this.g.clear();
            this.j = true;
            return w(y(i2, aVar));
        }
    }

    c.a.a.a0.a0.b x(c.a.a.x.a aVar, c.a.a.a0.a0.c cVar, boolean z, boolean z2) {
        int[] j = j();
        j[0] = 0;
        j[1] = 0;
        j[2] = 0;
        j[3] = 0;
        j[4] = 0;
        j[5] = 0;
        j[6] = 0;
        j[7] = 0;
        int[] b = cVar.b();
        if (z2) {
            q.g(aVar, b[0], j);
        } else {
            q.f(aVar, b[1], j);
            int i2 = 0;
            for (int length = j.length - 1; i2 < length; length--) {
                int i3 = j[i2];
                j[i2] = j[length];
                j[length] = i3;
                i2++;
            }
        }
        float h = c.a.a.a0.a0.a.h(j) / 17;
        float f = (cVar.b()[1] - cVar.b()[0]) / 15.0f;
        if (Math.abs(h - f) / f > 0.3f) {
            throw l.a();
        }
        int[] n2 = n();
        int[] l2 = l();
        float[] o2 = o();
        float[] m2 = m();
        for (int i4 = 0; i4 < j.length; i4++) {
            float f2 = (j[i4] * 1.0f) / h;
            int i5 = (int) (0.5f + f2);
            if (i5 < 1) {
                if (f2 < 0.3f) {
                    throw l.a();
                }
                i5 = 1;
            } else if (i5 > 8) {
                if (f2 > 8.7f) {
                    throw l.a();
                }
                i5 = 8;
            }
            int i6 = i4 / 2;
            if ((i4 & 1) == 0) {
                n2[i6] = i5;
                o2[i6] = f2 - i5;
            } else {
                l2[i6] = i5;
                m2[i6] = f2 - i5;
            }
        }
        s(17);
        int c2 = (((cVar.c() * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
        int i7 = 0;
        int i8 = 0;
        for (int length2 = n2.length - 1; length2 >= 0; length2--) {
            if (B(cVar, z, z2)) {
                i7 += n2[length2] * o[c2][length2 * 2];
            }
            i8 += n2[length2];
        }
        int i9 = 0;
        for (int length3 = l2.length - 1; length3 >= 0; length3--) {
            if (B(cVar, z, z2)) {
                i9 += l2[length3] * o[c2][(length3 * 2) + 1];
            }
        }
        int i10 = i7 + i9;
        if ((i8 & 1) != 0 || i8 > 13 || i8 < 4) {
            throw l.a();
        }
        int i11 = (13 - i8) / 2;
        int i12 = k[i11];
        return new c.a.a.a0.a0.b((f.b(n2, i12, true) * l[i11]) + f.b(l2, 9 - i12, false) + m[i11], i10);
    }

    List<b> y(int i2, c.a.a.x.a aVar) {
        while (true) {
            try {
                this.g.add(G(aVar, this.g, i2));
            } catch (l e) {
                if (this.g.isEmpty()) {
                    throw e;
                }
                if (t()) {
                    return this.g;
                }
                boolean z = !this.h.isEmpty();
                I(i2, false);
                if (z) {
                    List<b> v = v(false);
                    if (v != null) {
                        return v;
                    }
                    List<b> v2 = v(true);
                    if (v2 != null) {
                        return v2;
                    }
                }
                throw l.a();
            }
        }
    }
}
