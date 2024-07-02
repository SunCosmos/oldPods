package c.a.a.x;

/* loaded from: classes.dex */
public final class c {
    private final byte[] a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private int f1302c;

    public c(byte[] bArr) {
        this.a = bArr;
    }

    public int a() {
        return ((this.a.length - this.b) * 8) - this.f1302c;
    }

    public int b() {
        return this.f1302c;
    }

    public int c() {
        return this.b;
    }

    public int d(int i2) {
        if (i2 < 1 || i2 > 32 || i2 > a()) {
            throw new IllegalArgumentException(String.valueOf(i2));
        }
        int i3 = this.f1302c;
        int i4 = 0;
        if (i3 > 0) {
            int i5 = 8 - i3;
            int i6 = i2 < i5 ? i2 : i5;
            int i7 = i5 - i6;
            byte[] bArr = this.a;
            int i8 = this.b;
            int i9 = (((255 >> (8 - i6)) << i7) & bArr[i8]) >> i7;
            i2 -= i6;
            int i10 = i3 + i6;
            this.f1302c = i10;
            if (i10 == 8) {
                this.f1302c = 0;
                this.b = i8 + 1;
            }
            i4 = i9;
        }
        if (i2 <= 0) {
            return i4;
        }
        while (i2 >= 8) {
            int i11 = i4 << 8;
            byte[] bArr2 = this.a;
            int i12 = this.b;
            i4 = (bArr2[i12] & 255) | i11;
            this.b = i12 + 1;
            i2 -= 8;
        }
        if (i2 <= 0) {
            return i4;
        }
        int i13 = 8 - i2;
        int i14 = (i4 << i2) | ((((255 >> i13) << i13) & this.a[this.b]) >> i13);
        this.f1302c += i2;
        return i14;
    }
}
