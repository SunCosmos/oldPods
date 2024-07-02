package c.a.a.b0.e.k;

import c.a.a.d;

/* loaded from: classes.dex */
public final class a {
    private final b a = b.f;

    private int[] b(c cVar) {
        int d2 = cVar.d();
        int[] iArr = new int[d2];
        int i2 = 0;
        for (int i3 = 1; i3 < this.a.e() && i2 < d2; i3++) {
            if (cVar.b(i3) == 0) {
                iArr[i2] = this.a.g(i3);
                i2++;
            }
        }
        if (i2 == d2) {
            return iArr;
        }
        throw d.a();
    }

    private int[] c(c cVar, c cVar2, int[] iArr) {
        int d2 = cVar2.d();
        int[] iArr2 = new int[d2];
        for (int i2 = 1; i2 <= d2; i2++) {
            iArr2[d2 - i2] = this.a.i(i2, cVar2.c(i2));
        }
        c cVar3 = new c(this.a, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            int g = this.a.g(iArr[i3]);
            iArr3[i3] = this.a.i(this.a.j(0, cVar.b(g)), this.a.g(cVar3.b(g)));
        }
        return iArr3;
    }

    private c[] d(c cVar, c cVar2, int i2) {
        if (cVar.d() < cVar2.d()) {
            cVar2 = cVar;
            cVar = cVar2;
        }
        c f = this.a.f();
        c d2 = this.a.d();
        while (true) {
            c cVar3 = cVar2;
            cVar2 = cVar;
            cVar = cVar3;
            c cVar4 = d2;
            c cVar5 = f;
            f = cVar4;
            if (cVar.d() < i2 / 2) {
                int c2 = f.c(0);
                if (c2 == 0) {
                    throw d.a();
                }
                int g = this.a.g(c2);
                return new c[]{f.f(g), cVar.f(g)};
            }
            if (cVar.e()) {
                throw d.a();
            }
            c f2 = this.a.f();
            int g2 = this.a.g(cVar.c(cVar.d()));
            while (cVar2.d() >= cVar.d() && !cVar2.e()) {
                int d3 = cVar2.d() - cVar.d();
                int i3 = this.a.i(cVar2.c(cVar2.d()), g2);
                f2 = f2.a(this.a.b(d3, i3));
                cVar2 = cVar2.j(cVar.h(d3, i3));
            }
            d2 = f2.g(f).j(cVar5).i();
        }
    }

    public int a(int[] iArr, int i2, int[] iArr2) {
        c cVar = new c(this.a, iArr);
        int[] iArr3 = new int[i2];
        boolean z = false;
        for (int i3 = i2; i3 > 0; i3--) {
            int b = cVar.b(this.a.c(i3));
            iArr3[i2 - i3] = b;
            if (b != 0) {
                z = true;
            }
        }
        if (!z) {
            return 0;
        }
        c d2 = this.a.d();
        if (iArr2 != null) {
            for (int i4 : iArr2) {
                int c2 = this.a.c((iArr.length - 1) - i4);
                b bVar = this.a;
                d2 = d2.g(new c(bVar, new int[]{bVar.j(0, c2), 1}));
            }
        }
        c[] d3 = d(this.a.b(i2, 1), new c(this.a, iArr3), i2);
        c cVar2 = d3[0];
        c cVar3 = d3[1];
        int[] b2 = b(cVar2);
        int[] c3 = c(cVar3, cVar2, b2);
        for (int i5 = 0; i5 < b2.length; i5++) {
            int length = (iArr.length - 1) - this.a.h(b2[i5]);
            if (length < 0) {
                throw d.a();
            }
            iArr[length] = this.a.j(iArr[length], c3[i5]);
        }
        return b2.length;
    }
}
