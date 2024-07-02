package c.a.a.a0;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class q implements c.a.a.n {
    /* JADX WARN: Removed duplicated region for block: B:36:0x007d A[Catch: o -> 0x00c6, TryCatch #0 {o -> 0x00c6, blocks: (B:34:0x0077, B:36:0x007d, B:38:0x008e), top: B:33:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00c5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private c.a.a.p d(c.a.a.c r22, java.util.Map<c.a.a.e, ?> r23) {
        /*
            Method dump skipped, instructions count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a0.q.d(c.a.a.c, java.util.Map):c.a.a.p");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float e(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i2;
        float f3 = f2 / i3;
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            float f6 = iArr2[i5] * f3;
            float f7 = iArr[i5];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void f(c.a.a.x.a aVar, int i2, int[] iArr) {
        int length = iArr.length;
        int i3 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int k = aVar.k();
        if (i2 >= k) {
            throw c.a.a.l.a();
        }
        boolean z = !aVar.g(i2);
        while (i2 < k) {
            if (aVar.g(i2) ^ z) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                i3++;
                if (i3 == length) {
                    break;
                }
                iArr[i3] = 1;
                z = !z;
            }
            i2++;
        }
        if (i3 != length) {
            if (i3 != length - 1 || i2 != k) {
                throw c.a.a.l.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void g(c.a.a.x.a aVar, int i2, int[] iArr) {
        int length = iArr.length;
        boolean g = aVar.g(i2);
        while (i2 > 0 && length >= 0) {
            i2--;
            if (aVar.g(i2) != g) {
                length--;
                g = !g;
            }
        }
        if (length >= 0) {
            throw c.a.a.l.a();
        }
        f(aVar, i2 + 1, iArr);
    }

    @Override // c.a.a.n
    public c.a.a.p a(c.a.a.c cVar, Map<c.a.a.e, ?> map) {
        try {
            return d(cVar, map);
        } catch (c.a.a.l e) {
            if (!(map != null && map.containsKey(c.a.a.e.TRY_HARDER)) || !cVar.e()) {
                throw e;
            }
            cVar.f();
            throw null;
        }
    }

    @Override // c.a.a.n
    public void b() {
    }

    public abstract c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map);
}
