package androidx.core.content.res;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
final class b {
    public static int[] a(int[] iArr, int i2, int i3) {
        if (i2 + 1 > iArr.length) {
            int[] iArr2 = new int[c(i2)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            iArr = iArr2;
        }
        iArr[i2] = i3;
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object[], java.lang.Object] */
    public static <T> T[] b(T[] tArr, int i2, T t) {
        if (i2 + 1 > tArr.length) {
            ?? r0 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), c(i2));
            System.arraycopy(tArr, 0, r0, 0, i2);
            tArr = r0;
        }
        tArr[i2] = t;
        return tArr;
    }

    public static int c(int i2) {
        if (i2 <= 4) {
            return 8;
        }
        return i2 * 2;
    }
}
