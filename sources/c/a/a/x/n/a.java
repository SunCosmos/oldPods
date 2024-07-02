package c.a.a.x.n;

/* loaded from: classes.dex */
public final class a {
    public static final a h = new a(4201, 4096, 1);

    /* renamed from: i */
    public static final a f1315i = new a(1033, 1024, 1);
    public static final a j;
    public static final a k;
    public static final a l;
    public static final a m;
    public static final a n;
    public static final a o;
    private final int[] a;
    private final int[] b;

    /* renamed from: c */
    private final b f1316c;

    /* renamed from: d */
    private final b f1317d;
    private final int e;
    private final int f;
    private final int g;

    static {
        a aVar = new a(67, 64, 1);
        j = aVar;
        k = new a(19, 16, 1);
        l = new a(285, 256, 0);
        a aVar2 = new a(301, 256, 1);
        m = aVar2;
        n = aVar2;
        o = aVar;
    }

    public a(int i2, int i3, int i4) {
        this.f = i2;
        this.e = i3;
        this.g = i4;
        this.a = new int[i3];
        this.b = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            this.a[i6] = i5;
            i5 *= 2;
            if (i5 >= i3) {
                i5 = (i5 ^ i2) & (i3 - 1);
            }
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            this.b[this.a[i7]] = i7;
        }
        this.f1316c = new b(this, new int[]{0});
        this.f1317d = new b(this, new int[]{1});
    }

    public static int a(int i2, int i3) {
        return i2 ^ i3;
    }

    public b b(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i3 == 0) {
            return this.f1316c;
        }
        int[] iArr = new int[i2 + 1];
        iArr[0] = i3;
        return new b(this, iArr);
    }

    public int c(int i2) {
        return this.a[i2];
    }

    public int d() {
        return this.g;
    }

    public b e() {
        return this.f1317d;
    }

    public int f() {
        return this.e;
    }

    public b g() {
        return this.f1316c;
    }

    public int h(int i2) {
        if (i2 != 0) {
            return this.a[(this.e - this.b[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    public int i(int i2) {
        if (i2 != 0) {
            return this.b[i2];
        }
        throw new IllegalArgumentException();
    }

    public int j(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        int[] iArr = this.a;
        int[] iArr2 = this.b;
        return iArr[(iArr2[i2] + iArr2[i3]) % (this.e - 1)];
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.f) + ',' + this.e + ')';
    }
}
