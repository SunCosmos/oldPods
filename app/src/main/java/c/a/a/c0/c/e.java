package c.a.a.c0.c;

import java.util.Map;

/* loaded from: classes.dex */
public final class e {
    private final c.a.a.x.n.c a = new c.a.a.x.n.c(c.a.a.x.n.a.l);

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

    private c.a.a.x.e c(a aVar, Map<c.a.a.e, ?> map) {
        j e = aVar.e();
        f d2 = aVar.d().d();
        b[] b = b.b(aVar.c(), e, d2);
        int i2 = 0;
        for (b bVar : b) {
            i2 += bVar.c();
        }
        byte[] bArr = new byte[i2];
        int i3 = 0;
        for (b bVar2 : b) {
            byte[] a = bVar2.a();
            int c2 = bVar2.c();
            a(a, c2);
            int i4 = 0;
            while (i4 < c2) {
                bArr[i3] = a[i4];
                i4++;
                i3++;
            }
        }
        return d.a(bArr, e, d2, map);
    }

    public c.a.a.x.e b(c.a.a.x.b bVar, Map<c.a.a.e, ?> map) {
        c.a.a.d e;
        a aVar = new a(bVar);
        c.a.a.h hVar = null;
        try {
            return c(aVar, map);
        } catch (c.a.a.d e2) {
            e = e2;
            try {
                aVar.f();
                aVar.g(true);
                aVar.e();
                aVar.d();
                aVar.b();
                c.a.a.x.e c2 = c(aVar, map);
                c2.k(new i(true));
                return c2;
            } catch (c.a.a.d | c.a.a.h e3) {
                if (hVar != null) {
                    throw hVar;
                }
                if (e != null) {
                    throw e;
                }
                throw e3;
            }
        } catch (c.a.a.h e4) {
            e = null;
            hVar = e4;
            aVar.f();
            aVar.g(true);
            aVar.e();
            aVar.d();
            aVar.b();
            c.a.a.x.e c22 = c(aVar, map);
            c22.k(new i(true));
            return c22;
        }
    }
}
