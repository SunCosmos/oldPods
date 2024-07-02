package c.a.a.b0.e;

import java.util.Formatter;

/* loaded from: classes.dex */
final class f {
    private final a a;
    private final g[] b;

    /* renamed from: c, reason: collision with root package name */
    private c f1205c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1206d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(a aVar, c cVar) {
        this.a = aVar;
        int a = aVar.a();
        this.f1206d = a;
        this.f1205c = cVar;
        this.b = new g[a + 2];
    }

    private void a(g gVar) {
        if (gVar != null) {
            ((h) gVar).g(this.a);
        }
    }

    private static boolean b(d dVar, d dVar2) {
        if (dVar2 == null || !dVar2.g() || dVar2.a() != dVar.a()) {
            return false;
        }
        dVar.i(dVar2.c());
        return true;
    }

    private static int c(int i2, int i3, d dVar) {
        if (dVar == null || dVar.g()) {
            return i3;
        }
        if (!dVar.h(i2)) {
            return i3 + 1;
        }
        dVar.i(i2);
        return 0;
    }

    private int d() {
        int f = f();
        if (f == 0) {
            return 0;
        }
        for (int i2 = 1; i2 < this.f1206d + 1; i2++) {
            d[] d2 = this.b[i2].d();
            for (int i3 = 0; i3 < d2.length; i3++) {
                if (d2[i3] != null && !d2[i3].g()) {
                    e(i2, i3, d2);
                }
            }
        }
        return f;
    }

    private void e(int i2, int i3, d[] dVarArr) {
        d dVar = dVarArr[i3];
        d[] d2 = this.b[i2 - 1].d();
        g[] gVarArr = this.b;
        int i4 = i2 + 1;
        d[] d3 = gVarArr[i4] != null ? gVarArr[i4].d() : d2;
        d[] dVarArr2 = new d[14];
        dVarArr2[2] = d2[i3];
        dVarArr2[3] = d3[i3];
        if (i3 > 0) {
            int i5 = i3 - 1;
            dVarArr2[0] = dVarArr[i5];
            dVarArr2[4] = d2[i5];
            dVarArr2[5] = d3[i5];
        }
        if (i3 > 1) {
            int i6 = i3 - 2;
            dVarArr2[8] = dVarArr[i6];
            dVarArr2[10] = d2[i6];
            dVarArr2[11] = d3[i6];
        }
        if (i3 < dVarArr.length - 1) {
            int i7 = i3 + 1;
            dVarArr2[1] = dVarArr[i7];
            dVarArr2[6] = d2[i7];
            dVarArr2[7] = d3[i7];
        }
        if (i3 < dVarArr.length - 2) {
            int i8 = i3 + 2;
            dVarArr2[9] = dVarArr[i8];
            dVarArr2[12] = d2[i8];
            dVarArr2[13] = d3[i8];
        }
        for (int i9 = 0; i9 < 14 && !b(dVar, dVarArr2[i9]); i9++) {
        }
    }

    private int f() {
        g();
        return h() + i();
    }

    private void g() {
        g[] gVarArr = this.b;
        if (gVarArr[0] == null || gVarArr[this.f1206d + 1] == null) {
            return;
        }
        d[] d2 = gVarArr[0].d();
        d[] d3 = this.b[this.f1206d + 1].d();
        for (int i2 = 0; i2 < d2.length; i2++) {
            if (d2[i2] != null && d3[i2] != null && d2[i2].c() == d3[i2].c()) {
                for (int i3 = 1; i3 <= this.f1206d; i3++) {
                    d dVar = this.b[i3].d()[i2];
                    if (dVar != null) {
                        dVar.i(d2[i2].c());
                        if (!dVar.g()) {
                            this.b[i3].d()[i2] = null;
                        }
                    }
                }
            }
        }
    }

    private int h() {
        g[] gVarArr = this.b;
        if (gVarArr[0] == null) {
            return 0;
        }
        d[] d2 = gVarArr[0].d();
        int i2 = 0;
        for (int i3 = 0; i3 < d2.length; i3++) {
            if (d2[i3] != null) {
                int c2 = d2[i3].c();
                int i4 = 0;
                for (int i5 = 1; i5 < this.f1206d + 1 && i4 < 2; i5++) {
                    d dVar = this.b[i5].d()[i3];
                    if (dVar != null) {
                        i4 = c(c2, i4, dVar);
                        if (!dVar.g()) {
                            i2++;
                        }
                    }
                }
            }
        }
        return i2;
    }

    private int i() {
        g[] gVarArr = this.b;
        int i2 = this.f1206d;
        if (gVarArr[i2 + 1] == null) {
            return 0;
        }
        d[] d2 = gVarArr[i2 + 1].d();
        int i3 = 0;
        for (int i4 = 0; i4 < d2.length; i4++) {
            if (d2[i4] != null) {
                int c2 = d2[i4].c();
                int i5 = 0;
                for (int i6 = this.f1206d + 1; i6 > 0 && i5 < 2; i6--) {
                    d dVar = this.b[i6].d()[i4];
                    if (dVar != null) {
                        i5 = c(c2, i5, dVar);
                        if (!dVar.g()) {
                            i3++;
                        }
                    }
                }
            }
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j() {
        return this.f1206d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k() {
        return this.a.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int l() {
        return this.a.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c m() {
        return this.f1205c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g n(int i2) {
        return this.b[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g[] o() {
        a(this.b[0]);
        a(this.b[this.f1206d + 1]);
        int i2 = 928;
        while (true) {
            int d2 = d();
            if (d2 <= 0 || d2 >= i2) {
                break;
            }
            i2 = d2;
        }
        return this.b;
    }

    public void p(c cVar) {
        this.f1205c = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(int i2, g gVar) {
        this.b[i2] = gVar;
    }

    public String toString() {
        g[] gVarArr = this.b;
        g gVar = gVarArr[0];
        if (gVar == null) {
            gVar = gVarArr[this.f1206d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i2 = 0; i2 < gVar.d().length; i2++) {
            formatter.format("CW %3d:", Integer.valueOf(i2));
            for (int i3 = 0; i3 < this.f1206d + 2; i3++) {
                g[] gVarArr2 = this.b;
                if (gVarArr2[i3] == null) {
                    formatter.format("    |   ", new Object[0]);
                } else {
                    d dVar = gVarArr2[i3].d()[i2];
                    if (dVar == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        formatter.format(" %3d|%3d", Integer.valueOf(dVar.c()), Integer.valueOf(dVar.e()));
                    }
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
