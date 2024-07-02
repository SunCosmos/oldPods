package c.a.a.y.c;

import c.a.a.h;

/* loaded from: classes.dex */
final class a {
    private final c.a.a.x.b a;
    private final c.a.a.x.b b;

    /* renamed from: c, reason: collision with root package name */
    private final e f1318c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(c.a.a.x.b bVar) {
        int g = bVar.g();
        if (g < 8 || g > 144 || (g & 1) != 0) {
            throw h.a();
        }
        this.f1318c = j(bVar);
        c.a.a.x.b a = a(bVar);
        this.a = a;
        this.b = new c.a.a.x.b(a.j(), a.g());
    }

    private static e j(c.a.a.x.b bVar) {
        return e.h(bVar.g(), bVar.j());
    }

    c.a.a.x.b a(c.a.a.x.b bVar) {
        int f = this.f1318c.f();
        int e = this.f1318c.e();
        if (bVar.g() != f) {
            throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
        }
        int c2 = this.f1318c.c();
        int b = this.f1318c.b();
        int i2 = f / c2;
        int i3 = e / b;
        c.a.a.x.b bVar2 = new c.a.a.x.b(i3 * b, i2 * c2);
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 * c2;
            for (int i6 = 0; i6 < i3; i6++) {
                int i7 = i6 * b;
                for (int i8 = 0; i8 < c2; i8++) {
                    int i9 = ((c2 + 2) * i4) + 1 + i8;
                    int i10 = i5 + i8;
                    for (int i11 = 0; i11 < b; i11++) {
                        if (bVar.d(((b + 2) * i6) + 1 + i11, i9)) {
                            bVar2.l(i7 + i11, i10);
                        }
                    }
                }
            }
        }
        return bVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e b() {
        return this.f1318c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] c() {
        byte[] bArr = new byte[this.f1318c.g()];
        int g = this.a.g();
        int j = this.a.j();
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i4 = 4;
        while (true) {
            if (i4 == g && i2 == 0 && !z) {
                bArr[i3] = (byte) d(g, j);
                i4 -= 2;
                i2 += 2;
                i3++;
                z = true;
            } else {
                int i5 = g - 2;
                if (i4 == i5 && i2 == 0 && (j & 3) != 0 && !z2) {
                    bArr[i3] = (byte) e(g, j);
                    i4 -= 2;
                    i2 += 2;
                    i3++;
                    z2 = true;
                } else if (i4 == g + 4 && i2 == 2 && (j & 7) == 0 && !z3) {
                    bArr[i3] = (byte) f(g, j);
                    i4 -= 2;
                    i2 += 2;
                    i3++;
                    z3 = true;
                } else if (i4 == i5 && i2 == 0 && (j & 7) == 4 && !z4) {
                    bArr[i3] = (byte) g(g, j);
                    i4 -= 2;
                    i2 += 2;
                    i3++;
                    z4 = true;
                } else {
                    do {
                        if (i4 < g && i2 >= 0 && !this.b.d(i2, i4)) {
                            bArr[i3] = (byte) i(i4, i2, g, j);
                            i3++;
                        }
                        i4 -= 2;
                        i2 += 2;
                        if (i4 < 0) {
                            break;
                        }
                    } while (i2 < j);
                    int i6 = i4 + 1;
                    int i7 = i2 + 3;
                    do {
                        if (i6 >= 0 && i7 < j && !this.b.d(i7, i6)) {
                            bArr[i3] = (byte) i(i6, i7, g, j);
                            i3++;
                        }
                        i6 += 2;
                        i7 -= 2;
                        if (i6 >= g) {
                            break;
                        }
                    } while (i7 >= 0);
                    i4 = i6 + 3;
                    i2 = i7 + 1;
                }
            }
            if (i4 >= g && i2 >= j) {
                break;
            }
        }
        if (i3 == this.f1318c.g()) {
            return bArr;
        }
        throw h.a();
    }

    int d(int i2, int i3) {
        int i4 = i2 - 1;
        int i5 = (h(i4, 0, i2, i3) ? 1 : 0) << 1;
        if (h(i4, 1, i2, i3)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (h(i4, 2, i2, i3)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (h(0, i3 - 2, i2, i3)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        int i9 = i3 - 1;
        if (h(0, i9, i2, i3)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        if (h(1, i9, i2, i3)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        if (h(2, i9, i2, i3)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        return h(3, i9, i2, i3) ? i12 | 1 : i12;
    }

    int e(int i2, int i3) {
        int i4 = (h(i2 + (-3), 0, i2, i3) ? 1 : 0) << 1;
        if (h(i2 - 2, 0, i2, i3)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (h(i2 - 1, 0, i2, i3)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (h(0, i3 - 4, i2, i3)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (h(0, i3 - 3, i2, i3)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        if (h(0, i3 - 2, i2, i3)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        int i10 = i3 - 1;
        if (h(0, i10, i2, i3)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        return h(1, i10, i2, i3) ? i11 | 1 : i11;
    }

    int f(int i2, int i3) {
        int i4 = i2 - 1;
        int i5 = (h(i4, 0, i2, i3) ? 1 : 0) << 1;
        int i6 = i3 - 1;
        if (h(i4, i6, i2, i3)) {
            i5 |= 1;
        }
        int i7 = i5 << 1;
        int i8 = i3 - 3;
        if (h(0, i8, i2, i3)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        int i10 = i3 - 2;
        if (h(0, i10, i2, i3)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        if (h(0, i6, i2, i3)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (h(1, i8, i2, i3)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (h(1, i10, i2, i3)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        return h(1, i6, i2, i3) ? i14 | 1 : i14;
    }

    int g(int i2, int i3) {
        int i4 = (h(i2 + (-3), 0, i2, i3) ? 1 : 0) << 1;
        if (h(i2 - 2, 0, i2, i3)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (h(i2 - 1, 0, i2, i3)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (h(0, i3 - 2, i2, i3)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        int i8 = i3 - 1;
        if (h(0, i8, i2, i3)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        if (h(1, i8, i2, i3)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        if (h(2, i8, i2, i3)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        return h(3, i8, i2, i3) ? i11 | 1 : i11;
    }

    boolean h(int i2, int i3, int i4, int i5) {
        if (i2 < 0) {
            i2 += i4;
            i3 += 4 - ((i4 + 4) & 7);
        }
        if (i3 < 0) {
            i3 += i5;
            i2 += 4 - ((i5 + 4) & 7);
        }
        this.b.l(i3, i2);
        return this.a.d(i3, i2);
    }

    int i(int i2, int i3, int i4, int i5) {
        int i6 = i2 - 2;
        int i7 = i3 - 2;
        int i8 = (h(i6, i7, i4, i5) ? 1 : 0) << 1;
        int i9 = i3 - 1;
        if (h(i6, i9, i4, i5)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        int i11 = i2 - 1;
        if (h(i11, i7, i4, i5)) {
            i10 |= 1;
        }
        int i12 = i10 << 1;
        if (h(i11, i9, i4, i5)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (h(i11, i3, i4, i5)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (h(i2, i7, i4, i5)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        if (h(i2, i9, i4, i5)) {
            i15 |= 1;
        }
        int i16 = i15 << 1;
        return h(i2, i3, i4, i5) ? i16 | 1 : i16;
    }
}
