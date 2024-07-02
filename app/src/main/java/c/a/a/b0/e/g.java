package c.a.a.b0.e;

import java.util.Formatter;

/* loaded from: classes.dex */
class g {
    private final c a;
    private final d[] b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(c cVar) {
        this.a = new c(cVar);
        this.b = new d[(cVar.f() - cVar.h()) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final c a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final d b(int i2) {
        return this.b[e(i2)];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final d c(int i2) {
        d dVar;
        d dVar2;
        d b = b(i2);
        if (b != null) {
            return b;
        }
        for (int i3 = 1; i3 < 5; i3++) {
            int e = e(i2) - i3;
            if (e >= 0 && (dVar2 = this.b[e]) != null) {
                return dVar2;
            }
            int e2 = e(i2) + i3;
            d[] dVarArr = this.b;
            if (e2 < dVarArr.length && (dVar = dVarArr[e2]) != null) {
                return dVar;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final d[] d() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int e(int i2) {
        return i2 - this.a.h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f(int i2, d dVar) {
        this.b[e(i2)] = dVar;
    }

    public String toString() {
        Formatter formatter = new Formatter();
        int i2 = 0;
        for (d dVar : this.b) {
            if (dVar == null) {
                formatter.format("%3d:    |   %n", Integer.valueOf(i2));
                i2++;
            } else {
                formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i2), Integer.valueOf(dVar.c()), Integer.valueOf(dVar.e()));
                i2++;
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
