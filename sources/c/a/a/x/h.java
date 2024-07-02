package c.a.a.x;

/* loaded from: classes.dex */
public class h extends c.a.a.b {

    /* renamed from: d, reason: collision with root package name */
    private static final byte[] f1308d = new byte[0];
    private byte[] b;

    /* renamed from: c, reason: collision with root package name */
    private final int[] f1309c;

    public h(c.a.a.i iVar) {
        super(iVar);
        this.b = f1308d;
        this.f1309c = new int[32];
    }

    private static int f(int[] iArr) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            if (iArr[i5] > i2) {
                i2 = iArr[i5];
                i4 = i5;
            }
            if (iArr[i5] > i3) {
                i3 = iArr[i5];
            }
        }
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            int i9 = i8 - i4;
            int i10 = iArr[i8] * i9 * i9;
            if (i10 > i7) {
                i6 = i8;
                i7 = i10;
            }
        }
        if (i4 <= i6) {
            int i11 = i4;
            i4 = i6;
            i6 = i11;
        }
        if (i4 - i6 <= length / 16) {
            throw c.a.a.l.a();
        }
        int i12 = i4 - 1;
        int i13 = i12;
        int i14 = -1;
        while (i12 > i6) {
            int i15 = i12 - i6;
            int i16 = i15 * i15 * (i4 - i12) * (i3 - iArr[i12]);
            if (i16 > i14) {
                i13 = i12;
                i14 = i16;
            }
            i12--;
        }
        return i13 << 3;
    }

    private void g(int i2) {
        if (this.b.length < i2) {
            this.b = new byte[i2];
        }
        for (int i3 = 0; i3 < 32; i3++) {
            this.f1309c[i3] = 0;
        }
    }

    @Override // c.a.a.b
    public b a() {
        c.a.a.i d2 = d();
        int d3 = d2.d();
        int a = d2.a();
        b bVar = new b(d3, a);
        g(d3);
        int[] iArr = this.f1309c;
        for (int i2 = 1; i2 < 5; i2++) {
            byte[] c2 = d2.c((a * i2) / 5, this.b);
            int i3 = (d3 * 4) / 5;
            for (int i4 = d3 / 5; i4 < i3; i4++) {
                int i5 = (c2[i4] & 255) >> 3;
                iArr[i5] = iArr[i5] + 1;
            }
        }
        int f = f(iArr);
        byte[] b = d2.b();
        for (int i6 = 0; i6 < a; i6++) {
            int i7 = i6 * d3;
            for (int i8 = 0; i8 < d3; i8++) {
                if ((b[i7 + i8] & 255) < f) {
                    bVar.l(i8, i6);
                }
            }
        }
        return bVar;
    }

    @Override // c.a.a.b
    public a b(int i2, a aVar) {
        c.a.a.i d2 = d();
        int d3 = d2.d();
        if (aVar == null || aVar.k() < d3) {
            aVar = new a(d3);
        } else {
            aVar.d();
        }
        g(d3);
        byte[] c2 = d2.c(i2, this.b);
        int[] iArr = this.f1309c;
        for (int i3 = 0; i3 < d3; i3++) {
            int i4 = (c2[i3] & 255) >> 3;
            iArr[i4] = iArr[i4] + 1;
        }
        int f = f(iArr);
        int i5 = 1;
        int i6 = c2[0] & 255;
        int i7 = c2[1] & 255;
        while (i5 < d3 - 1) {
            int i8 = i5 + 1;
            int i9 = c2[i8] & 255;
            if ((((i7 * 4) - i6) - i9) / 2 < f) {
                aVar.p(i5);
            }
            i6 = i7;
            i5 = i8;
            i7 = i9;
        }
        return aVar;
    }
}
