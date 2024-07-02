package c.a.a.a0;

import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class v {

    /* renamed from: c, reason: collision with root package name */
    private static final int[] f1189c = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] a = new int[4];
    private final StringBuilder b = new StringBuilder();

    private static int c(int i2) {
        for (int i3 = 0; i3 < 10; i3++) {
            if (i2 == f1189c[i3]) {
                return i3;
            }
        }
        throw c.a.a.l.a();
    }

    private static int d(CharSequence charSequence) {
        int length = charSequence.length();
        int i2 = 0;
        for (int i3 = length - 2; i3 >= 0; i3 -= 2) {
            i2 += charSequence.charAt(i3) - '0';
        }
        int i4 = i2 * 3;
        for (int i5 = length - 1; i5 >= 0; i5 -= 2) {
            i4 += charSequence.charAt(i5) - '0';
        }
        return (i4 * 3) % 10;
    }

    private static String e(String str) {
        String valueOf;
        char charAt = str.charAt(0);
        String str2 = "";
        if (charAt == '0') {
            str2 = "£";
        } else if (charAt == '5') {
            str2 = "$";
        } else if (charAt == '9') {
            if ("90000".equals(str)) {
                return null;
            }
            if ("99991".equals(str)) {
                return "0.00";
            }
            if ("99990".equals(str)) {
                return "Used";
            }
        }
        int parseInt = Integer.parseInt(str.substring(1));
        String valueOf2 = String.valueOf(parseInt / 100);
        int i2 = parseInt % 100;
        if (i2 < 10) {
            valueOf = "0" + i2;
        } else {
            valueOf = String.valueOf(i2);
        }
        return str2 + valueOf2 + '.' + valueOf;
    }

    private static Map<c.a.a.q, Object> f(String str) {
        String e;
        if (str.length() != 5 || (e = e(str)) == null) {
            return null;
        }
        EnumMap enumMap = new EnumMap(c.a.a.q.class);
        enumMap.put((EnumMap) c.a.a.q.SUGGESTED_PRICE, (c.a.a.q) e);
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
        for (int i4 = 0; i4 < 5 && i2 < k; i4++) {
            int j = x.j(aVar, iArr2, i2, x.g);
            sb.append((char) ((j % 10) + 48));
            for (int i5 : iArr2) {
                i2 += i5;
            }
            if (j >= 10) {
                i3 |= 1 << (4 - i4);
            }
            if (i4 != 4) {
                i2 = aVar.j(aVar.i(i2));
            }
        }
        if (sb.length() != 5) {
            throw c.a.a.l.a();
        }
        if (d(sb.toString()) == c(i3)) {
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
        Map<c.a.a.q, Object> f = f(sb2);
        float f2 = i2;
        c.a.a.p pVar = new c.a.a.p(sb2, null, new c.a.a.r[]{new c.a.a.r((iArr[0] + iArr[1]) / 2.0f, f2), new c.a.a.r(a, f2)}, c.a.a.a.UPC_EAN_EXTENSION);
        if (f != null) {
            pVar.g(f);
        }
        return pVar;
    }
}
