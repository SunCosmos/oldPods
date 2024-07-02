package c.a.a.c0.c;

import c.a.a.c0.c.j;

/* loaded from: classes.dex */
final class b {
    private final int a;
    private final byte[] b;

    private b(int i2, byte[] bArr) {
        this.a = i2;
        this.b = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b[] b(byte[] bArr, j jVar, f fVar) {
        if (bArr.length != jVar.h()) {
            throw new IllegalArgumentException();
        }
        j.b f = jVar.f(fVar);
        j.a[] a = f.a();
        int i2 = 0;
        for (j.a aVar : a) {
            i2 += aVar.a();
        }
        b[] bVarArr = new b[i2];
        int i3 = 0;
        for (j.a aVar2 : a) {
            int i4 = 0;
            while (i4 < aVar2.a()) {
                int b = aVar2.b();
                bVarArr[i3] = new b(b, new byte[f.b() + b]);
                i4++;
                i3++;
            }
        }
        int length = bVarArr[0].b.length;
        int i5 = i2 - 1;
        while (i5 >= 0 && bVarArr[i5].b.length != length) {
            i5--;
        }
        int i6 = i5 + 1;
        int b2 = length - f.b();
        int i7 = 0;
        for (int i8 = 0; i8 < b2; i8++) {
            int i9 = 0;
            while (i9 < i3) {
                bVarArr[i9].b[i8] = bArr[i7];
                i9++;
                i7++;
            }
        }
        int i10 = i6;
        while (i10 < i3) {
            bVarArr[i10].b[b2] = bArr[i7];
            i10++;
            i7++;
        }
        int length2 = bVarArr[0].b.length;
        while (b2 < length2) {
            int i11 = 0;
            while (i11 < i3) {
                bVarArr[i11].b[i11 < i6 ? b2 : b2 + 1] = bArr[i7];
                i11++;
                i7++;
            }
            b2++;
        }
        return bVarArr;
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
