package c.a.a.a0;

import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class u {
    private final int[] a = new int[4];
    private final StringBuilder b = new StringBuilder();

    private static Map<c.a.a.q, Object> c(String str) {
        if (str.length() != 2) {
            return null;
        }
        EnumMap enumMap = new EnumMap(c.a.a.q.class);
        enumMap.put((EnumMap) c.a.a.q.ISSUE_NUMBER, (c.a.a.q) Integer.valueOf(str));
        return enumMap;
    }

    int a(c.a.a.x.a aVar, int[] iArr, StringBuilder sb) {
        int[] iArr2 = this.a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int k = aVar.k();
        int i2 = iArr[1];
        int i3 = 0;
        for (int i4 = 0; i4 < 2 && i2 < k; i4++) {
            int j = x.j(aVar, iArr2, i2, x.g);
            sb.append((char) ((j % 10) + 48));
            for (int i5 : iArr2) {
                i2 += i5;
            }
            if (j >= 10) {
                i3 |= 1 << (1 - i4);
            }
            if (i4 != 1) {
                i2 = aVar.j(aVar.i(i2));
            }
        }
        if (sb.length() != 2) {
            throw c.a.a.l.a();
        }
        if (Integer.parseInt(sb.toString()) % 4 == i3) {
            return i2;
        }
        throw c.a.a.l.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c.a.a.p b(int i2, c.a.a.x.a aVar, int[] iArr) {
        StringBuilder sb = this.b;
        sb.setLength(0);
        int a = a(aVar, iArr, sb);
        String sb2 = sb.toString();
        Map<c.a.a.q, Object> c2 = c(sb2);
        float f = i2;
        c.a.a.p pVar = new c.a.a.p(sb2, null, new c.a.a.r[]{new c.a.a.r((iArr[0] + iArr[1]) / 2.0f, f), new c.a.a.r(a, f)}, c.a.a.a.UPC_EAN_EXTENSION);
        if (c2 != null) {
            pVar.g(c2);
        }
        return pVar;
    }
}
