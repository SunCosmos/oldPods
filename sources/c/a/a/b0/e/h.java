package c.a.a.b0.e;

import c.a.a.r;

/* loaded from: classes.dex */
final class h extends g {

    /* renamed from: c, reason: collision with root package name */
    private final boolean f1207c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(c cVar, boolean z) {
        super(cVar);
        this.f1207c = z;
    }

    private void l(d[] dVarArr, a aVar) {
        for (int i2 = 0; i2 < dVarArr.length; i2++) {
            d dVar = dVarArr[i2];
            if (dVarArr[i2] != null) {
                int e = dVar.e() % 30;
                int c2 = dVar.c();
                if (c2 > aVar.c()) {
                    dVarArr[i2] = null;
                } else {
                    if (!this.f1207c) {
                        c2 += 2;
                    }
                    int i3 = c2 % 3;
                    if (i3 != 0) {
                        if (i3 != 1) {
                            if (i3 == 2 && e + 1 != aVar.a()) {
                                dVarArr[i2] = null;
                            }
                        } else if (e / 3 != aVar.b() || e % 3 != aVar.d()) {
                            dVarArr[i2] = null;
                        }
                    } else if ((e * 3) + 1 != aVar.e()) {
                        dVarArr[i2] = null;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g(a aVar) {
        d[] d2 = d();
        m();
        l(d2, aVar);
        c a = a();
        r i2 = this.f1207c ? a.i() : a.j();
        r c2 = this.f1207c ? a.c() : a.d();
        int e = e((int) i2.d());
        int e2 = e((int) c2.d());
        float c3 = (e2 - e) / aVar.c();
        int i3 = -1;
        int i4 = 0;
        int i5 = 1;
        while (e < e2) {
            if (d2[e] != null) {
                d dVar = d2[e];
                int c4 = dVar.c() - i3;
                if (c4 == 0) {
                    i4++;
                } else {
                    if (c4 == 1) {
                        i5 = Math.max(i5, i4);
                    } else if (c4 < 0 || dVar.c() >= aVar.c() || c4 > e) {
                        d2[e] = null;
                    } else {
                        if (i5 > 2) {
                            c4 *= i5 - 2;
                        }
                        boolean z = c4 >= e;
                        for (int i6 = 1; i6 <= c4 && !z; i6++) {
                            z = d2[e - i6] != null;
                        }
                        if (z) {
                            d2[e] = null;
                        }
                    }
                    i3 = dVar.c();
                    i4 = 1;
                }
            }
            e++;
        }
        double d3 = c3;
        Double.isNaN(d3);
        return (int) (d3 + 0.5d);
    }

    int h(a aVar) {
        c a = a();
        r i2 = this.f1207c ? a.i() : a.j();
        r c2 = this.f1207c ? a.c() : a.d();
        int e = e((int) i2.d());
        int e2 = e((int) c2.d());
        float c3 = (e2 - e) / aVar.c();
        d[] d2 = d();
        int i3 = -1;
        int i4 = 0;
        int i5 = 1;
        while (e < e2) {
            if (d2[e] != null) {
                d dVar = d2[e];
                dVar.j();
                int c4 = dVar.c() - i3;
                if (c4 == 0) {
                    i4++;
                } else {
                    if (c4 == 1) {
                        i5 = Math.max(i5, i4);
                    } else if (dVar.c() >= aVar.c()) {
                        d2[e] = null;
                    }
                    i3 = dVar.c();
                    i4 = 1;
                }
            }
            e++;
        }
        double d3 = c3;
        Double.isNaN(d3);
        return (int) (d3 + 0.5d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a i() {
        d[] d2 = d();
        b bVar = new b();
        b bVar2 = new b();
        b bVar3 = new b();
        b bVar4 = new b();
        for (d dVar : d2) {
            if (dVar != null) {
                dVar.j();
                int e = dVar.e() % 30;
                int c2 = dVar.c();
                if (!this.f1207c) {
                    c2 += 2;
                }
                int i2 = c2 % 3;
                if (i2 == 0) {
                    bVar2.b((e * 3) + 1);
                } else if (i2 == 1) {
                    bVar4.b(e / 3);
                    bVar3.b(e % 3);
                } else if (i2 == 2) {
                    bVar.b(e + 1);
                }
            }
        }
        if (bVar.a().length == 0 || bVar2.a().length == 0 || bVar3.a().length == 0 || bVar4.a().length == 0 || bVar.a()[0] < 1 || bVar2.a()[0] + bVar3.a()[0] < 3 || bVar2.a()[0] + bVar3.a()[0] > 90) {
            return null;
        }
        a aVar = new a(bVar.a()[0], bVar2.a()[0], bVar3.a()[0], bVar4.a()[0]);
        l(d2, aVar);
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] j() {
        a i2 = i();
        if (i2 == null) {
            return null;
        }
        h(i2);
        int c2 = i2.c();
        int[] iArr = new int[c2];
        for (d dVar : d()) {
            if (dVar != null) {
                int c3 = dVar.c();
                if (c3 >= c2) {
                    throw c.a.a.h.a();
                }
                iArr[c3] = iArr[c3] + 1;
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return this.f1207c;
    }

    void m() {
        for (d dVar : d()) {
            if (dVar != null) {
                dVar.j();
            }
        }
    }

    @Override // c.a.a.b0.e.g
    public String toString() {
        return "IsLeft: " + this.f1207c + '\n' + super.toString();
    }
}
