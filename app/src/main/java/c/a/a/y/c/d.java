package c.a.a.y.c;

/* loaded from: classes.dex */
public final class d {
    private final c.a.a.x.n.c a = new c.a.a.x.n.c(c.a.a.x.n.a.m);

    private void a(byte[] bArr, int i2) {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        try {
            this.a.a(iArr, bArr.length - i2);
            for (int i4 = 0; i4 < i2; i4++) {
                bArr[i4] = (byte) iArr[i4];
            }
        } catch (c.a.a.x.n.e unused) {
            throw c.a.a.d.a();
        }
    }

    public c.a.a.x.e b(c.a.a.x.b bVar) {
        a aVar = new a(bVar);
        b[] b = b.b(aVar.c(), aVar.b());
        int length = b.length;
        int i2 = 0;
        for (b bVar2 : b) {
            i2 += bVar2.c();
        }
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < length; i3++) {
            b bVar3 = b[i3];
            byte[] a = bVar3.a();
            int c2 = bVar3.c();
            a(a, c2);
            for (int i4 = 0; i4 < c2; i4++) {
                bArr[(i4 * length) + i3] = a[i4];
            }
        }
        return c.a(bArr);
    }
}
