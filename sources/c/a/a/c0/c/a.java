package c.a.a.c0.c;

/* loaded from: classes.dex */
final class a {
    private final c.a.a.x.b a;
    private j b;

    /* renamed from: c, reason: collision with root package name */
    private g f1223c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1224d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(c.a.a.x.b bVar) {
        int g = bVar.g();
        if (g < 21 || (g & 3) != 1) {
            throw c.a.a.h.a();
        }
        this.a = bVar;
    }

    private int a(int i2, int i3, int i4) {
        return this.f1224d ? this.a.d(i3, i2) : this.a.d(i2, i3) ? (i4 << 1) | 1 : i4 << 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        int i2 = 0;
        while (i2 < this.a.j()) {
            int i3 = i2 + 1;
            for (int i4 = i3; i4 < this.a.g(); i4++) {
                if (this.a.d(i2, i4) != this.a.d(i4, i2)) {
                    this.a.c(i4, i2);
                    this.a.c(i2, i4);
                }
            }
            i2 = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] c() {
        g d2 = d();
        j e = e();
        c a = c.a(d2.c());
        int g = this.a.g();
        a.c(this.a, g);
        c.a.a.x.b a2 = e.a();
        byte[] bArr = new byte[e.h()];
        int i2 = g - 1;
        boolean z = true;
        int i3 = i2;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 > 0) {
            if (i3 == 6) {
                i3--;
            }
            for (int i7 = 0; i7 < g; i7++) {
                int i8 = z ? i2 - i7 : i7;
                for (int i9 = 0; i9 < 2; i9++) {
                    int i10 = i3 - i9;
                    if (!a2.d(i10, i8)) {
                        i5++;
                        i6 <<= 1;
                        if (this.a.d(i10, i8)) {
                            i6 |= 1;
                        }
                        if (i5 == 8) {
                            bArr[i4] = (byte) i6;
                            i4++;
                            i5 = 0;
                            i6 = 0;
                        }
                    }
                }
            }
            z = !z;
            i3 -= 2;
        }
        if (i4 == e.h()) {
            return bArr;
        }
        throw c.a.a.h.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g d() {
        g gVar = this.f1223c;
        if (gVar != null) {
            return gVar;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 6; i4++) {
            i3 = a(i4, 8, i3);
        }
        int a = a(8, 7, a(8, 8, a(7, 8, i3)));
        for (int i5 = 5; i5 >= 0; i5--) {
            a = a(8, i5, a);
        }
        int g = this.a.g();
        int i6 = g - 7;
        for (int i7 = g - 1; i7 >= i6; i7--) {
            i2 = a(8, i7, i2);
        }
        for (int i8 = g - 8; i8 < g; i8++) {
            i2 = a(i8, 8, i2);
        }
        g a2 = g.a(a, i2);
        this.f1223c = a2;
        if (a2 != null) {
            return a2;
        }
        throw c.a.a.h.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j e() {
        j jVar = this.b;
        if (jVar != null) {
            return jVar;
        }
        int g = this.a.g();
        int i2 = (g - 17) / 4;
        if (i2 <= 6) {
            return j.i(i2);
        }
        int i3 = g - 11;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 5; i6 >= 0; i6--) {
            for (int i7 = g - 9; i7 >= i3; i7--) {
                i5 = a(i7, i6, i5);
            }
        }
        j c2 = j.c(i5);
        if (c2 != null && c2.e() == g) {
            this.b = c2;
            return c2;
        }
        for (int i8 = 5; i8 >= 0; i8--) {
            for (int i9 = g - 9; i9 >= i3; i9--) {
                i4 = a(i8, i9, i4);
            }
        }
        j c3 = j.c(i4);
        if (c3 == null || c3.e() != g) {
            throw c.a.a.h.a();
        }
        this.b = c3;
        return c3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        g gVar = this.f1223c;
        if (gVar == null) {
            return;
        }
        c.a(gVar.c()).c(this.a, this.a.g());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(boolean z) {
        this.b = null;
        this.f1223c = null;
        this.f1224d = z;
    }
}
