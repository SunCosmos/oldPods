package c.a.a.x;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class b implements Cloneable {
    private final int a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1300c;

    /* renamed from: d, reason: collision with root package name */
    private final int[] f1301d;

    public b(int i2) {
        this(i2, i2);
    }

    public b(int i2, int i3) {
        if (i2 < 1 || i3 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.a = i2;
        this.b = i3;
        int i4 = (i2 + 31) / 32;
        this.f1300c = i4;
        this.f1301d = new int[i4 * i3];
    }

    private b(int i2, int i3, int i4, int[] iArr) {
        this.a = i2;
        this.b = i3;
        this.f1300c = i4;
        this.f1301d = iArr;
    }

    public void a() {
        int length = this.f1301d.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.f1301d[i2] = 0;
        }
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public b clone() {
        return new b(this.a, this.b, this.f1300c, (int[]) this.f1301d.clone());
    }

    public void c(int i2, int i3) {
        int i4 = (i3 * this.f1300c) + (i2 / 32);
        int[] iArr = this.f1301d;
        iArr[i4] = (1 << (i2 & 31)) ^ iArr[i4];
    }

    public boolean d(int i2, int i3) {
        return ((this.f1301d[(i3 * this.f1300c) + (i2 / 32)] >>> (i2 & 31)) & 1) != 0;
    }

    public int[] e() {
        int length = this.f1301d.length - 1;
        while (length >= 0 && this.f1301d[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i2 = this.f1300c;
        int i3 = length / i2;
        int i4 = (length % i2) * 32;
        int i5 = 31;
        while ((this.f1301d[length] >>> i5) == 0) {
            i5--;
        }
        return new int[]{i4 + i5, i3};
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.a == bVar.a && this.b == bVar.b && this.f1300c == bVar.f1300c && Arrays.equals(this.f1301d, bVar.f1301d);
    }

    public int[] f() {
        int i2 = this.a;
        int i3 = this.b;
        int i4 = -1;
        int i5 = -1;
        for (int i6 = 0; i6 < this.b; i6++) {
            int i7 = 0;
            while (true) {
                int i8 = this.f1300c;
                if (i7 < i8) {
                    int i9 = this.f1301d[(i8 * i6) + i7];
                    if (i9 != 0) {
                        if (i6 < i3) {
                            i3 = i6;
                        }
                        if (i6 > i5) {
                            i5 = i6;
                        }
                        int i10 = i7 * 32;
                        if (i10 < i2) {
                            int i11 = 0;
                            while ((i9 << (31 - i11)) == 0) {
                                i11++;
                            }
                            int i12 = i11 + i10;
                            if (i12 < i2) {
                                i2 = i12;
                            }
                        }
                        if (i10 + 31 > i4) {
                            int i13 = 31;
                            while ((i9 >>> i13) == 0) {
                                i13--;
                            }
                            int i14 = i10 + i13;
                            if (i14 > i4) {
                                i4 = i14;
                            }
                        }
                    }
                    i7++;
                }
            }
        }
        int i15 = i4 - i2;
        int i16 = i5 - i3;
        if (i15 < 0 || i16 < 0) {
            return null;
        }
        return new int[]{i2, i3, i15, i16};
    }

    public int g() {
        return this.b;
    }

    public a h(int i2, a aVar) {
        if (aVar == null || aVar.k() < this.a) {
            aVar = new a(this.a);
        } else {
            aVar.d();
        }
        int i3 = i2 * this.f1300c;
        for (int i4 = 0; i4 < this.f1300c; i4++) {
            aVar.q(i4 * 32, this.f1301d[i3 + i4]);
        }
        return aVar;
    }

    public int hashCode() {
        int i2 = this.a;
        return (((((((i2 * 31) + i2) * 31) + this.b) * 31) + this.f1300c) * 31) + Arrays.hashCode(this.f1301d);
    }

    public int[] i() {
        int[] iArr;
        int i2 = 0;
        while (true) {
            iArr = this.f1301d;
            if (i2 >= iArr.length || iArr[i2] != 0) {
                break;
            }
            i2++;
        }
        if (i2 == iArr.length) {
            return null;
        }
        int i3 = this.f1300c;
        int i4 = i2 / i3;
        int i5 = (i2 % i3) * 32;
        int i6 = iArr[i2];
        int i7 = 0;
        while ((i6 << (31 - i7)) == 0) {
            i7++;
        }
        return new int[]{i5 + i7, i4};
    }

    public int j() {
        return this.a;
    }

    public void k() {
        int j = j();
        int g = g();
        a aVar = new a(j);
        a aVar2 = new a(j);
        for (int i2 = 0; i2 < (g + 1) / 2; i2++) {
            aVar = h(i2, aVar);
            int i3 = (g - 1) - i2;
            aVar2 = h(i3, aVar2);
            aVar.o();
            aVar2.o();
            n(i2, aVar2);
            n(i3, aVar);
        }
    }

    public void l(int i2, int i3) {
        int i4 = (i3 * this.f1300c) + (i2 / 32);
        int[] iArr = this.f1301d;
        iArr[i4] = (1 << (i2 & 31)) | iArr[i4];
    }

    public void m(int i2, int i3, int i4, int i5) {
        if (i3 < 0 || i2 < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i5 < 1 || i4 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int i6 = i4 + i2;
        int i7 = i5 + i3;
        if (i7 > this.b || i6 > this.a) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        while (i3 < i7) {
            int i8 = this.f1300c * i3;
            for (int i9 = i2; i9 < i6; i9++) {
                int[] iArr = this.f1301d;
                int i10 = (i9 / 32) + i8;
                iArr[i10] = iArr[i10] | (1 << (i9 & 31));
            }
            i3++;
        }
    }

    public void n(int i2, a aVar) {
        int[] h = aVar.h();
        int[] iArr = this.f1301d;
        int i3 = this.f1300c;
        System.arraycopy(h, 0, iArr, i2 * i3, i3);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.b * (this.a + 1));
        for (int i2 = 0; i2 < this.b; i2++) {
            for (int i3 = 0; i3 < this.a; i3++) {
                sb.append(d(i3, i2) ? "X " : "  ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
