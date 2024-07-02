package c.a.a.b0.e.k;

/* loaded from: classes.dex */
public final class b {
    public static final b f = new b(929, 3);
    private final int[] a;
    private final int[] b;

    /* renamed from: c */
    private final c f1208c;

    /* renamed from: d */
    private final c f1209d;
    private final int e;

    private b(int i2, int i3) {
        this.e = i2;
        this.a = new int[i2];
        this.b = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.a[i5] = i4;
            i4 = (i4 * i3) % i2;
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            this.b[this.a[i6]] = i6;
        }
        this.f1208c = new c(this, new int[]{0});
        this.f1209d = new c(this, new int[]{1});
    }

    public int a(int i2, int i3) {
        return (i2 + i3) % this.e;
    }

    public c b(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i3 == 0) {
            return this.f1208c;
        }
        int[] iArr = new int[i2 + 1];
        iArr[0] = i3;
        return new c(this, iArr);
    }

    public int c(int i2) {
        return this.a[i2];
    }

    public c d() {
        return this.f1209d;
    }

    public int e() {
        return this.e;
    }

    public c f() {
        return this.f1208c;
    }

    public int g(int i2) {
        if (i2 != 0) {
            return this.a[(this.e - this.b[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    public int h(int i2) {
        if (i2 != 0) {
            return this.b[i2];
        }
        throw new IllegalArgumentException();
    }

    public int i(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        int[] iArr = this.a;
        int[] iArr2 = this.b;
        return iArr[(iArr2[i2] + iArr2[i3]) % (this.e - 1)];
    }

    public int j(int i2, int i3) {
        int i4 = this.e;
        return ((i2 + i4) - i3) % i4;
    }
}
