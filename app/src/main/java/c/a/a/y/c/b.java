package c.a.a.y.c;

import c.a.a.y.c.e;

/* loaded from: classes.dex */
final class b {
    private final int a;
    private final byte[] b;

    private b(int i2, byte[] bArr) {
        this.a = i2;
        this.b = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b[] b(byte[] bArr, e eVar) {
        e.c d2 = eVar.d();
        e.b[] a = d2.a();
        int i2 = 0;
        for (e.b bVar : a) {
            i2 += bVar.a();
        }
        b[] bVarArr = new b[i2];
        int i3 = 0;
        for (e.b bVar2 : a) {
            int i4 = 0;
            while (i4 < bVar2.a()) {
                int b = bVar2.b();
                bVarArr[i3] = new b(b, new byte[d2.b() + b]);
                i4++;
                i3++;
            }
        }
        int length = bVarArr[0].b.length - d2.b();
        int i5 = length - 1;
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            int i8 = 0;
            while (i8 < i3) {
                bVarArr[i8].b[i7] = bArr[i6];
                i8++;
                i6++;
            }
        }
        boolean z = eVar.i() == 24;
        int i9 = z ? 8 : i3;
        int i10 = 0;
        while (i10 < i9) {
            bVarArr[i10].b[i5] = bArr[i6];
            i10++;
            i6++;
        }
        int length2 = bVarArr[0].b.length;
        while (length < length2) {
            int i11 = 0;
            while (i11 < i3) {
                bVarArr[i11].b[(!z || i11 <= 7) ? length : length - 1] = bArr[i6];
                i11++;
                i6++;
            }
            length++;
        }
        if (i6 == bArr.length) {
            return bVarArr;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.a;
    }
}
