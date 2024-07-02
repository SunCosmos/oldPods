package androidx.collection;

/* loaded from: classes.dex */
class a {
    static final int[] a = new int[0];
    static final long[] b = new long[0];

    /* renamed from: c, reason: collision with root package name */
    static final Object[] f274c = new Object[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int[] iArr, int i2, int i3) {
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else {
                if (i7 <= i3) {
                    return i6;
                }
                i4 = i6 - 1;
            }
        }
        return i5 ^ (-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(long[] jArr, int i2, long j) {
        int i3 = i2 - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j2 = jArr[i5];
            if (j2 < j) {
                i4 = i5 + 1;
            } else {
                if (j2 <= j) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return i4 ^ (-1);
    }

    public static boolean c(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int d(int i2) {
        for (int i3 = 4; i3 < 32; i3++) {
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                return i4;
            }
        }
        return i2;
    }

    public static int e(int i2) {
        return d(i2 * 4) / 4;
    }

    public static int f(int i2) {
        return d(i2 * 8) / 8;
    }
}
