package c.a.a.a0.a0;

import android.support.v7.widget.ActivityChooserView;
import c.a.a.a0.q;
import c.a.a.l;

/* loaded from: classes.dex */
public abstract class a extends q {
    private final int[] b;
    private final int[] e;
    private final int[] f;
    private final int[] a = new int[4];

    /* renamed from: c, reason: collision with root package name */
    private final float[] f1162c = new float[4];

    /* renamed from: d, reason: collision with root package name */
    private final float[] f1163d = new float[4];

    /* JADX INFO: Access modifiers changed from: protected */
    public a() {
        int[] iArr = new int[8];
        this.b = iArr;
        this.e = new int[iArr.length / 2];
        this.f = new int[iArr.length / 2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int h(int[] iArr) {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += i3;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void i(int[] iArr, float[] fArr) {
        int i2 = 0;
        float f = fArr[0];
        for (int i3 = 1; i3 < iArr.length; i3++) {
            if (fArr[i3] < f) {
                f = fArr[i3];
                i2 = i3;
            }
        }
        iArr[i2] = iArr[i2] - 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void p(int[] iArr, float[] fArr) {
        int i2 = 0;
        float f = fArr[0];
        for (int i3 = 1; i3 < iArr.length; i3++) {
            if (fArr[i3] > f) {
                f = fArr[i3];
                i2 = i3;
            }
        }
        iArr[i2] = iArr[i2] + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean q(int[] iArr) {
        float f = (iArr[0] + iArr[1]) / ((iArr[2] + r1) + iArr[3]);
        if (f < 0.7916667f || f > 0.89285713f) {
            return false;
        }
        int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i3 = Integer.MIN_VALUE;
        for (int i4 : iArr) {
            if (i4 > i3) {
                i3 = i4;
            }
            if (i4 < i2) {
                i2 = i4;
            }
        }
        return i3 < i2 * 10;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int r(int[] iArr, int[][] iArr2) {
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            if (q.e(iArr, iArr2[i2], 0.45f) < 0.2f) {
                return i2;
            }
        }
        throw l.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] j() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] k() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] l() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float[] m() {
        return this.f1163d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] n() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float[] o() {
        return this.f1162c;
    }
}
