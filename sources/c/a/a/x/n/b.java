package c.a.a.x.n;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b {
    private final a a;
    private final int[] b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.a = aVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.b = iArr;
            return;
        }
        int i2 = 1;
        while (i2 < length && iArr[i2] == 0) {
            i2++;
        }
        if (i2 == length) {
            this.b = new int[]{0};
            return;
        }
        int[] iArr2 = new int[length - i2];
        this.b = iArr2;
        System.arraycopy(iArr, i2, iArr2, 0, iArr2.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b a(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (g()) {
            return bVar;
        }
        if (bVar.g()) {
            return this;
        }
        int[] iArr = this.b;
        int[] iArr2 = bVar.b;
        if (iArr.length <= iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr.length];
        int length = iArr.length - iArr2.length;
        System.arraycopy(iArr, 0, iArr3, 0, length);
        for (int i2 = length; i2 < iArr.length; i2++) {
            iArr3[i2] = a.a(iArr2[i2 - length], iArr[i2]);
        }
        return new b(this.a, iArr3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b[] b(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (bVar.g()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        b g = this.a.g();
        int h = this.a.h(bVar.d(bVar.f()));
        b bVar2 = this;
        while (bVar2.f() >= bVar.f() && !bVar2.g()) {
            int f = bVar2.f() - bVar.f();
            int j = this.a.j(bVar2.d(bVar2.f()), h);
            b j2 = bVar.j(f, j);
            g = g.a(this.a.b(f, j));
            bVar2 = bVar2.a(j2);
        }
        return new b[]{g, bVar2};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(int i2) {
        if (i2 == 0) {
            return d(0);
        }
        int[] iArr = this.b;
        int length = iArr.length;
        if (i2 != 1) {
            int i3 = iArr[0];
            for (int i4 = 1; i4 < length; i4++) {
                i3 = a.a(this.a.j(i2, i3), this.b[i4]);
            }
            return i3;
        }
        int i5 = 0;
        for (int i6 : iArr) {
            i5 = a.a(i5, i6);
        }
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d(int i2) {
        return this.b[(r0.length - 1) - i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] e() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.b.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        return this.b[0] == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b h(int i2) {
        if (i2 == 0) {
            return this.a.g();
        }
        if (i2 == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.a.j(this.b[i3], i2);
        }
        return new b(this.a, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b i(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (g() || bVar.g()) {
            return this.a.g();
        }
        int[] iArr = this.b;
        int length = iArr.length;
        int[] iArr2 = bVar.b;
        int length2 = iArr2.length;
        int[] iArr3 = new int[(length + length2) - 1];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            for (int i4 = 0; i4 < length2; i4++) {
                int i5 = i2 + i4;
                iArr3[i5] = a.a(iArr3[i5], this.a.j(i3, iArr2[i4]));
            }
        }
        return new b(this.a, iArr3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b j(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i3 == 0) {
            return this.a.g();
        }
        int length = this.b.length;
        int[] iArr = new int[i2 + length];
        for (int i4 = 0; i4 < length; i4++) {
            iArr[i4] = this.a.j(this.b[i4], i3);
        }
        return new b(this.a, iArr);
    }

    public String toString() {
        char c2;
        StringBuilder sb = new StringBuilder(f() * 8);
        for (int f = f(); f >= 0; f--) {
            int d2 = d(f);
            if (d2 != 0) {
                if (d2 < 0) {
                    sb.append(" - ");
                    d2 = -d2;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (f == 0 || d2 != 1) {
                    int i2 = this.a.i(d2);
                    if (i2 == 0) {
                        c2 = '1';
                    } else if (i2 == 1) {
                        c2 = 'a';
                    } else {
                        sb.append("a^");
                        sb.append(i2);
                    }
                    sb.append(c2);
                }
                if (f != 0) {
                    if (f == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(f);
                    }
                }
            }
        }
        return sb.toString();
    }
}
