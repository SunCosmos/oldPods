package c.a.a.b0;

import c.a.a.b0.g.e;
import c.a.a.g;
import c.a.a.t;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes.dex */
public final class d implements t {
    private static c.a.a.x.b b(e eVar, String str, int i2, int i3, int i4) {
        boolean z;
        eVar.e(str, 2);
        byte[][] b = eVar.f().b(2, 8);
        if ((i3 > i2) ^ (b[0].length < b.length)) {
            b = d(b);
            z = true;
        } else {
            z = false;
        }
        int length = i2 / b[0].length;
        int length2 = i3 / b.length;
        if (length >= length2) {
            length = length2;
        }
        if (length <= 1) {
            return c(b, i4);
        }
        byte[][] b2 = eVar.f().b(length * 2, length * 4 * 2);
        if (z) {
            b2 = d(b2);
        }
        return c(b2, i4);
    }

    private static c.a.a.x.b c(byte[][] bArr, int i2) {
        int i3 = i2 * 2;
        c.a.a.x.b bVar = new c.a.a.x.b(bArr[0].length + i3, bArr.length + i3);
        bVar.a();
        int g = (bVar.g() - i2) - 1;
        int i4 = 0;
        while (i4 < bArr.length) {
            for (int i5 = 0; i5 < bArr[0].length; i5++) {
                if (bArr[i4][i5] == 1) {
                    bVar.l(i5 + i2, g);
                }
            }
            i4++;
            g--;
        }
        return bVar;
    }

    private static byte[][] d(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) byte.class, bArr[0].length, bArr.length);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int length = (bArr.length - i2) - 1;
            for (int i3 = 0; i3 < bArr[0].length; i3++) {
                bArr2[i3][length] = bArr[i2][i3];
            }
        }
        return bArr2;
    }

    @Override // c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<g, ?> map) {
        if (aVar != c.a.a.a.PDF_417) {
            throw new IllegalArgumentException("Can only encode PDF_417, but got " + aVar);
        }
        e eVar = new e();
        if (map != null) {
            g gVar = g.PDF417_COMPACT;
            if (map.containsKey(gVar)) {
                eVar.h(((Boolean) map.get(gVar)).booleanValue());
            }
            g gVar2 = g.PDF417_COMPACTION;
            if (map.containsKey(gVar2)) {
                eVar.i((c.a.a.b0.g.c) map.get(gVar2));
            }
            g gVar3 = g.PDF417_DIMENSIONS;
            if (map.containsKey(gVar3)) {
                c.a.a.b0.g.d dVar = (c.a.a.b0.g.d) map.get(gVar3);
                eVar.j(dVar.a(), dVar.c(), dVar.b(), dVar.d());
            }
            g gVar4 = g.MARGIN;
            r0 = map.containsKey(gVar4) ? ((Number) map.get(gVar4)).intValue() : 30;
            g gVar5 = g.CHARACTER_SET;
            if (map.containsKey(gVar5)) {
                eVar.k(Charset.forName((String) map.get(gVar5)));
            }
        }
        return b(eVar, str, i2, i3, r0);
    }
}
