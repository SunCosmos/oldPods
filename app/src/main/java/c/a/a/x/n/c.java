package c.a.a.x.n;

/* loaded from: classes.dex */
public final class c {
    private final a a;

    public c(a aVar) {
        this.a = aVar;
    }

    private int[] b(b bVar) {
        int f = bVar.f();
        int i2 = 0;
        if (f == 1) {
            return new int[]{bVar.d(1)};
        }
        int[] iArr = new int[f];
        for (int i3 = 1; i3 < this.a.f() && i2 < f; i3++) {
            if (bVar.c(i3) == 0) {
                iArr[i2] = this.a.h(i3);
                i2++;
            }
        }
        if (i2 == f) {
            return iArr;
        }
        throw new e("Error locator degree does not match number of roots");
    }

    private int[] c(b bVar, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int h = this.a.h(iArr[i2]);
            int i3 = 1;
            for (int i4 = 0; i4 < length; i4++) {
                if (i2 != i4) {
                    int j = this.a.j(iArr[i4], h);
                    i3 = this.a.j(i3, (j & 1) == 0 ? j | 1 : j & (-2));
                }
            }
            iArr2[i2] = this.a.j(bVar.c(h), this.a.h(i3));
            if (this.a.d() != 0) {
                iArr2[i2] = this.a.j(iArr2[i2], h);
            }
        }
        return iArr2;
    }

    private b[] d(b bVar, b bVar2, int i2) {
        if (bVar.f() < bVar2.f()) {
            bVar2 = bVar;
            bVar = bVar2;
        }
        b g = this.a.g();
        b e = this.a.e();
        do {
            b bVar3 = bVar2;
            bVar2 = bVar;
            bVar = bVar3;
            b bVar4 = e;
            b bVar5 = g;
            g = bVar4;
            if (bVar.f() < i2 / 2) {
                int d2 = g.d(0);
                if (d2 == 0) {
                    throw new e("sigmaTilde(0) was zero");
                }
                int h = this.a.h(d2);
                return new b[]{g.h(h), bVar.h(h)};
            }
            if (bVar.g()) {
                throw new e("r_{i-1} was zero");
            }
            b g2 = this.a.g();
            int h2 = this.a.h(bVar.d(bVar.f()));
            while (bVar2.f() >= bVar.f() && !bVar2.g()) {
                int f = bVar2.f() - bVar.f();
                int j = this.a.j(bVar2.d(bVar2.f()), h2);
                g2 = g2.a(this.a.b(f, j));
                bVar2 = bVar2.a(bVar.j(f, j));
            }
            e = g2.i(g).a(bVar5);
        } while (bVar2.f() < bVar.f());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    }

    public void a(int[] iArr, int i2) {
        b bVar = new b(this.a, iArr);
        int[] iArr2 = new int[i2];
        boolean z = true;
        for (int i3 = 0; i3 < i2; i3++) {
            a aVar = this.a;
            int c2 = bVar.c(aVar.c(aVar.d() + i3));
            iArr2[(i2 - 1) - i3] = c2;
            if (c2 != 0) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        b[] d2 = d(this.a.b(i2, 1), new b(this.a, iArr2), i2);
        b bVar2 = d2[0];
        b bVar3 = d2[1];
        int[] b = b(bVar2);
        int[] c3 = c(bVar3, b);
        for (int i4 = 0; i4 < b.length; i4++) {
            int length = (iArr.length - 1) - this.a.i(b[i4]);
            if (length < 0) {
                throw new e("Bad error location");
            }
            iArr[length] = a.a(iArr[length], c3[i4]);
        }
    }
}
