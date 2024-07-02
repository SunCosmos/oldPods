package c.a.a.a0;

import java.util.Map;

/* loaded from: classes.dex */
public abstract class r implements c.a.a.t {
    /* JADX INFO: Access modifiers changed from: protected */
    public static int b(boolean[] zArr, int i2, int[] iArr, boolean z) {
        int i3 = 0;
        for (int i4 : iArr) {
            int i5 = 0;
            while (i5 < i4) {
                zArr[i2] = z;
                i5++;
                i2++;
            }
            i3 += i4;
            z = !z;
        }
        return i3;
    }

    private static c.a.a.x.b e(boolean[] zArr, int i2, int i3, int i4) {
        int length = zArr.length;
        int i5 = i4 + length;
        int max = Math.max(i2, i5);
        int max2 = Math.max(1, i3);
        int i6 = max / i5;
        int i7 = (max - (length * i6)) / 2;
        c.a.a.x.b bVar = new c.a.a.x.b(max, max2);
        int i8 = 0;
        while (i8 < length) {
            if (zArr[i8]) {
                bVar.m(i7, 0, i6, max2);
            }
            i8++;
            i7 += i6;
        }
        return bVar;
    }

    @Override // c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<c.a.a.g, ?> map) {
        Integer num;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (i2 >= 0 && i3 >= 0) {
            int d2 = d();
            if (map != null && (num = (Integer) map.get(c.a.a.g.MARGIN)) != null) {
                d2 = num.intValue();
            }
            return e(c(str), i2, i3, d2);
        }
        throw new IllegalArgumentException("Negative size is not allowed. Input: " + i2 + 'x' + i3);
    }

    public abstract boolean[] c(String str);

    public int d() {
        return 10;
    }
}
