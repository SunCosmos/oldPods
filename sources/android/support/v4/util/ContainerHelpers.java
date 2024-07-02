package android.support.v4.util;

/* loaded from: lib/Mus.dex */
class ContainerHelpers {
    static final int[] EMPTY_INTS = new int[0];
    static final long[] EMPTY_LONGS = new long[0];
    static final Object[] EMPTY_OBJECTS = new Object[0];

    ContainerHelpers() {
    }

    public static int idealIntArraySize(int i2) {
        return idealByteArraySize(i2 * 4) / 4;
    }

    public static int idealLongArraySize(int i2) {
        return idealByteArraySize(i2 * 8) / 8;
    }

    public static int idealByteArraySize(int i2) {
        for (int i3 = 4; i3 < 32; i3++) {
            if (i2 <= (1 << i3) - 12) {
                return (1 << i3) - 12;
            }
        }
        return i2;
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int binarySearch(int[] iArr, int i2, int i3) {
        int i4 = 0;
        int i5 = i2 - 1;
        while (i4 <= i5) {
            int i6 = (i4 + i5) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i4 = i6 + 1;
            } else if (i7 > i3) {
                i5 = i6 - 1;
            } else {
                return i6;
            }
        }
        return i4 ^ (-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int binarySearch(long[] jArr, int i2, long j) {
        int i3 = 0;
        int i4 = i2 - 1;
        while (i3 <= i4) {
            int i5 = (i3 + i4) >>> 1;
            long j2 = jArr[i5];
            if (j2 < j) {
                i3 = i5 + 1;
            } else if (j2 > j) {
                i4 = i5 - 1;
            } else {
                return i5;
            }
        }
        return i3 ^ (-1);
    }
}
