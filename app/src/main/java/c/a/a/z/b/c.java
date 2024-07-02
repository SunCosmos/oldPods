package c.a.a.z.b;

import c.a.a.d;
import c.a.a.h;
import c.a.a.x.n.e;
import java.util.Map;

/* loaded from: classes.dex */
public final class c {
    private final c.a.a.x.n.c a = new c.a.a.x.n.c(c.a.a.x.n.a.o);

    private void a(byte[] bArr, int i2, int i3, int i4, int i5) {
        int i6 = i3 + i4;
        int i7 = i5 == 0 ? 1 : 2;
        int[] iArr = new int[i6 / i7];
        for (int i8 = 0; i8 < i6; i8++) {
            if (i5 == 0 || i8 % 2 == i5 - 1) {
                iArr[i8 / i7] = bArr[i8 + i2] & 255;
            }
        }
        try {
            this.a.a(iArr, i4 / i7);
            for (int i9 = 0; i9 < i3; i9++) {
                if (i5 == 0 || i9 % 2 == i5 - 1) {
                    bArr[i9 + i2] = (byte) iArr[i9 / i7];
                }
            }
        } catch (e unused) {
            throw d.a();
        }
    }

    public c.a.a.x.e b(c.a.a.x.b bVar, Map<c.a.a.e, ?> map) {
        int i2;
        byte[] a = new a(bVar).a();
        a(a, 0, 10, 10, 0);
        int i3 = a[0] & 15;
        if (i3 == 2 || i3 == 3 || i3 == 4) {
            a(a, 20, 84, 40, 1);
            a(a, 20, 84, 40, 2);
            i2 = 94;
        } else {
            if (i3 != 5) {
                throw h.a();
            }
            a(a, 20, 68, 56, 1);
            a(a, 20, 68, 56, 2);
            i2 = 78;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(a, 0, bArr, 0, 10);
        System.arraycopy(a, 20, bArr, 10, bArr.length - 10);
        return b.a(bArr, i3);
    }
}
