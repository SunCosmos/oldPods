package c.a.a.c0.c;

import android.support.v7.widget.ActivityChooserView;

/* loaded from: classes.dex */
final class g {

    /* renamed from: c, reason: collision with root package name */
    private static final int[][] f1227c = {new int[]{21522, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f1228d = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
    private final f a;
    private final byte b;

    private g(int i2) {
        this.a = f.a((i2 >> 3) & 3);
        this.b = (byte) (i2 & 7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g a(int i2, int i3) {
        g b = b(i2, i3);
        return b != null ? b : b(i2 ^ 21522, i3 ^ 21522);
    }

    private static g b(int i2, int i3) {
        int e;
        int[][] iArr = f1227c;
        int i4 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i5 = 0;
        for (int[] iArr2 : iArr) {
            int i6 = iArr2[0];
            if (i6 == i2 || i6 == i3) {
                return new g(iArr2[1]);
            }
            int e2 = e(i2, i6);
            if (e2 < i4) {
                i5 = iArr2[1];
                i4 = e2;
            }
            if (i2 != i3 && (e = e(i3, i6)) < i4) {
                i5 = iArr2[1];
                i4 = e;
            }
        }
        if (i4 <= 3) {
            return new g(i5);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(int i2, int i3) {
        int i4 = i2 ^ i3;
        int[] iArr = f1228d;
        return iArr[i4 & 15] + iArr[(i4 >>> 4) & 15] + iArr[(i4 >>> 8) & 15] + iArr[(i4 >>> 12) & 15] + iArr[(i4 >>> 16) & 15] + iArr[(i4 >>> 20) & 15] + iArr[(i4 >>> 24) & 15] + iArr[(i4 >>> 28) & 15];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte c() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f d() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return this.a == gVar.a && this.b == gVar.b;
    }

    public int hashCode() {
        return (this.a.ordinal() << 3) | this.b;
    }
}
