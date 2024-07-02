package c.a.a.x;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class a implements Cloneable {
    private int[] a;
    private int b;

    public a() {
        this.b = 0;
        this.a = new int[1];
    }

    public a(int i2) {
        this.b = i2;
        this.a = n(i2);
    }

    a(int[] iArr, int i2) {
        this.a = iArr;
        this.b = i2;
    }

    private void f(int i2) {
        if (i2 > this.a.length * 32) {
            int[] n = n(i2);
            int[] iArr = this.a;
            System.arraycopy(iArr, 0, n, 0, iArr.length);
            this.a = n;
        }
    }

    private static int[] n(int i2) {
        return new int[(i2 + 31) / 32];
    }

    public void a(boolean z) {
        f(this.b + 1);
        if (z) {
            int[] iArr = this.a;
            int i2 = this.b;
            int i3 = i2 / 32;
            iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
        }
        this.b++;
    }

    public void b(a aVar) {
        int i2 = aVar.b;
        f(this.b + i2);
        for (int i3 = 0; i3 < i2; i3++) {
            a(aVar.g(i3));
        }
    }

    public void c(int i2, int i3) {
        if (i3 < 0 || i3 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        f(this.b + i3);
        while (i3 > 0) {
            boolean z = true;
            if (((i2 >> (i3 - 1)) & 1) != 1) {
                z = false;
            }
            a(z);
            i3--;
        }
    }

    public void d() {
        int length = this.a.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.a[i2] = 0;
        }
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public a clone() {
        return new a((int[]) this.a.clone(), this.b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.b == aVar.b && Arrays.equals(this.a, aVar.a);
    }

    public boolean g(int i2) {
        return ((1 << (i2 & 31)) & this.a[i2 / 32]) != 0;
    }

    public int[] h() {
        return this.a;
    }

    public int hashCode() {
        return (this.b * 31) + Arrays.hashCode(this.a);
    }

    public int i(int i2) {
        int i3 = this.b;
        if (i2 >= i3) {
            return i3;
        }
        int i4 = i2 / 32;
        int i5 = (((1 << (i2 & 31)) - 1) ^ (-1)) & this.a[i4];
        while (i5 == 0) {
            i4++;
            int[] iArr = this.a;
            if (i4 == iArr.length) {
                return this.b;
            }
            i5 = iArr[i4];
        }
        int numberOfTrailingZeros = (i4 * 32) + Integer.numberOfTrailingZeros(i5);
        int i6 = this.b;
        return numberOfTrailingZeros > i6 ? i6 : numberOfTrailingZeros;
    }

    public int j(int i2) {
        int i3 = this.b;
        if (i2 >= i3) {
            return i3;
        }
        int i4 = i2 / 32;
        int i5 = (((1 << (i2 & 31)) - 1) ^ (-1)) & (this.a[i4] ^ (-1));
        while (i5 == 0) {
            i4++;
            int[] iArr = this.a;
            if (i4 == iArr.length) {
                return this.b;
            }
            i5 = iArr[i4] ^ (-1);
        }
        int numberOfTrailingZeros = (i4 * 32) + Integer.numberOfTrailingZeros(i5);
        int i6 = this.b;
        return numberOfTrailingZeros > i6 ? i6 : numberOfTrailingZeros;
    }

    public int k() {
        return this.b;
    }

    public int l() {
        return (this.b + 7) / 8;
    }

    public boolean m(int i2, int i3, boolean z) {
        int i4;
        if (i3 < i2) {
            throw new IllegalArgumentException();
        }
        if (i3 == i2) {
            return true;
        }
        int i5 = i3 - 1;
        int i6 = i2 / 32;
        int i7 = i5 / 32;
        int i8 = i6;
        while (i8 <= i7) {
            int i9 = i8 > i6 ? 0 : i2 & 31;
            int i10 = i8 < i7 ? 31 : i5 & 31;
            if (i9 == 0 && i10 == 31) {
                i4 = -1;
            } else {
                i4 = 0;
                while (i9 <= i10) {
                    i4 |= 1 << i9;
                    i9++;
                }
            }
            int i11 = this.a[i8] & i4;
            if (!z) {
                i4 = 0;
            }
            if (i11 != i4) {
                return false;
            }
            i8++;
        }
        return true;
    }

    public void o() {
        int[] iArr = new int[this.a.length];
        int i2 = (this.b - 1) / 32;
        int i3 = i2 + 1;
        for (int i4 = 0; i4 < i3; i4++) {
            long j = this.a[i4];
            long j2 = ((j & 1431655765) << 1) | ((j >> 1) & 1431655765);
            long j3 = ((j2 & 858993459) << 2) | ((j2 >> 2) & 858993459);
            long j4 = ((j3 & 252645135) << 4) | ((j3 >> 4) & 252645135);
            long j5 = ((j4 & 16711935) << 8) | ((j4 >> 8) & 16711935);
            iArr[i2 - i4] = (int) (((j5 & 65535) << 16) | ((j5 >> 16) & 65535));
        }
        int i5 = this.b;
        int i6 = i3 * 32;
        if (i5 != i6) {
            int i7 = i6 - i5;
            int i8 = 1;
            for (int i9 = 0; i9 < 31 - i7; i9++) {
                i8 = (i8 << 1) | 1;
            }
            int i10 = (iArr[0] >> i7) & i8;
            for (int i11 = 1; i11 < i3; i11++) {
                int i12 = iArr[i11];
                iArr[i11 - 1] = i10 | (i12 << (32 - i7));
                i10 = (i12 >> i7) & i8;
            }
            iArr[i3 - 1] = i10;
        }
        this.a = iArr;
    }

    public void p(int i2) {
        int[] iArr = this.a;
        int i3 = i2 / 32;
        iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
    }

    public void q(int i2, int i3) {
        this.a[i2 / 32] = i3;
    }

    public void r(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = 0;
            for (int i7 = 0; i7 < 8; i7++) {
                if (g(i2)) {
                    i6 |= 1 << (7 - i7);
                }
                i2++;
            }
            bArr[i3 + i5] = (byte) i6;
        }
    }

    public void s(a aVar) {
        if (this.a.length != aVar.a.length) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i2 = 0;
        while (true) {
            int[] iArr = this.a;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = iArr[i2] ^ aVar.a[i2];
            i2++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.b);
        for (int i2 = 0; i2 < this.b; i2++) {
            if ((i2 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(g(i2) ? 'X' : '.');
        }
        return sb.toString();
    }
}
