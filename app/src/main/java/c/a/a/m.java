package c.a.a;

/* loaded from: classes.dex */
public final class m extends i {

    /* renamed from: c, reason: collision with root package name */
    private final byte[] f1254c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1255d;
    private final int e;
    private final int f;
    private final int g;

    public m(byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        super(i6, i7);
        if (i4 + i6 > i2 || i5 + i7 > i3) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.f1254c = bArr;
        this.f1255d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        if (z) {
            j(i6, i7);
        }
    }

    private void j(int i2, int i3) {
        byte[] bArr = this.f1254c;
        int i4 = (this.g * this.f1255d) + this.f;
        int i5 = 0;
        while (i5 < i3) {
            int i6 = (i2 / 2) + i4;
            int i7 = (i4 + i2) - 1;
            int i8 = i4;
            while (i8 < i6) {
                byte b = bArr[i8];
                bArr[i8] = bArr[i7];
                bArr[i7] = b;
                i8++;
                i7--;
            }
            i5++;
            i4 += this.f1255d;
        }
    }

    @Override // c.a.a.i
    public byte[] b() {
        int d2 = d();
        int a = a();
        int i2 = this.f1255d;
        if (d2 == i2 && a == this.e) {
            return this.f1254c;
        }
        int i3 = d2 * a;
        byte[] bArr = new byte[i3];
        int i4 = (this.g * i2) + this.f;
        if (d2 == i2) {
            System.arraycopy(this.f1254c, i4, bArr, 0, i3);
            return bArr;
        }
        byte[] bArr2 = this.f1254c;
        for (int i5 = 0; i5 < a; i5++) {
            System.arraycopy(bArr2, i4, bArr, i5 * d2, d2);
            i4 += this.f1255d;
        }
        return bArr;
    }

    @Override // c.a.a.i
    public byte[] c(int i2, byte[] bArr) {
        if (i2 < 0 || i2 >= a()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i2);
        }
        int d2 = d();
        if (bArr == null || bArr.length < d2) {
            bArr = new byte[d2];
        }
        System.arraycopy(this.f1254c, ((i2 + this.g) * this.f1255d) + this.f, bArr, 0, d2);
        return bArr;
    }

    public int g() {
        return a() / 2;
    }

    public int h() {
        return d() / 2;
    }

    public int[] i() {
        int d2 = d() / 2;
        int a = a() / 2;
        int[] iArr = new int[d2 * a];
        byte[] bArr = this.f1254c;
        int i2 = (this.g * this.f1255d) + this.f;
        for (int i3 = 0; i3 < a; i3++) {
            int i4 = i3 * d2;
            for (int i5 = 0; i5 < d2; i5++) {
                iArr[i4 + i5] = ((bArr[(i5 * 2) + i2] & 255) * 65793) | (-16777216);
            }
            i2 += this.f1255d * 2;
        }
        return iArr;
    }
}
