package c.a.a.a0;

import android.support.v7.widget.ActivityChooserView;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public final class a extends q {

    /* renamed from: d, reason: collision with root package name */
    static final char[] f1160d = "0123456789-$:/.+ABCD".toCharArray();
    static final int[] e = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final char[] f = {'A', 'B', 'C', 'D'};
    private final StringBuilder a = new StringBuilder(20);
    private int[] b = new int[80];

    /* renamed from: c, reason: collision with root package name */
    private int f1161c = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean h(char[] cArr, char c2) {
        if (cArr != null) {
            for (char c3 : cArr) {
                if (c3 == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    private void i(int i2) {
        int[] iArr = this.b;
        int i3 = this.f1161c;
        iArr[i3] = i2;
        int i4 = i3 + 1;
        this.f1161c = i4;
        if (i4 >= iArr.length) {
            int[] iArr2 = new int[i4 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i4);
            this.b = iArr2;
        }
    }

    private int j() {
        for (int i2 = 1; i2 < this.f1161c; i2 += 2) {
            int l = l(i2);
            if (l != -1 && h(f, f1160d[l])) {
                int i3 = 0;
                for (int i4 = i2; i4 < i2 + 7; i4++) {
                    i3 += this.b[i4];
                }
                if (i2 == 1 || this.b[i2 - 1] >= i3 / 2) {
                    return i2;
                }
            }
        }
        throw c.a.a.l.a();
    }

    private void k(c.a.a.x.a aVar) {
        int i2 = 0;
        this.f1161c = 0;
        int j = aVar.j(0);
        int k = aVar.k();
        if (j >= k) {
            throw c.a.a.l.a();
        }
        boolean z = true;
        while (j < k) {
            if (aVar.g(j) ^ z) {
                i2++;
            } else {
                i(i2);
                z = !z;
                i2 = 1;
            }
            j++;
        }
        i(i2);
    }

    private int l(int i2) {
        int i3 = i2 + 7;
        if (i3 >= this.f1161c) {
            return -1;
        }
        int[] iArr = this.b;
        int i4 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i5 = 0;
        int i6 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i7 = 0;
        for (int i8 = i2; i8 < i3; i8 += 2) {
            int i9 = iArr[i8];
            if (i9 < i6) {
                i6 = i9;
            }
            if (i9 > i7) {
                i7 = i9;
            }
        }
        int i10 = (i6 + i7) / 2;
        int i11 = 0;
        for (int i12 = i2 + 1; i12 < i3; i12 += 2) {
            int i13 = iArr[i12];
            if (i13 < i4) {
                i4 = i13;
            }
            if (i13 > i11) {
                i11 = i13;
            }
        }
        int i14 = (i4 + i11) / 2;
        int i15 = 128;
        int i16 = 0;
        for (int i17 = 0; i17 < 7; i17++) {
            i15 >>= 1;
            if (iArr[i2 + i17] > ((i17 & 1) == 0 ? i10 : i14)) {
                i16 |= i15;
            }
        }
        while (true) {
            int[] iArr2 = e;
            if (i5 >= iArr2.length) {
                return -1;
            }
            if (iArr2[i5] == i16) {
                return i5;
            }
            i5++;
        }
    }

    @Override // c.a.a.a0.q
    public c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        Arrays.fill(this.b, 0);
        k(aVar);
        int j = j();
        this.a.setLength(0);
        int i3 = j;
        do {
            int l = l(i3);
            if (l == -1) {
                throw c.a.a.l.a();
            }
            this.a.append((char) l);
            i3 += 8;
            if (this.a.length() > 1 && h(f, f1160d[l])) {
                break;
            }
        } while (i3 < this.f1161c);
        int i4 = i3 - 1;
        int i5 = this.b[i4];
        int i6 = 0;
        for (int i7 = -8; i7 < -1; i7++) {
            i6 += this.b[i3 + i7];
        }
        if (i3 < this.f1161c && i5 < i6 / 2) {
            throw c.a.a.l.a();
        }
        m(j);
        for (int i8 = 0; i8 < this.a.length(); i8++) {
            StringBuilder sb = this.a;
            sb.setCharAt(i8, f1160d[sb.charAt(i8)]);
        }
        char charAt = this.a.charAt(0);
        char[] cArr = f;
        if (!h(cArr, charAt)) {
            throw c.a.a.l.a();
        }
        StringBuilder sb2 = this.a;
        if (!h(cArr, sb2.charAt(sb2.length() - 1))) {
            throw c.a.a.l.a();
        }
        if (this.a.length() <= 3) {
            throw c.a.a.l.a();
        }
        if (map == null || !map.containsKey(c.a.a.e.RETURN_CODABAR_START_END)) {
            StringBuilder sb3 = this.a;
            sb3.deleteCharAt(sb3.length() - 1);
            this.a.deleteCharAt(0);
        }
        int i9 = 0;
        for (int i10 = 0; i10 < j; i10++) {
            i9 += this.b[i10];
        }
        float f2 = i9;
        while (j < i4) {
            i9 += this.b[j];
            j++;
        }
        float f3 = i2;
        return new c.a.a.p(this.a.toString(), null, new c.a.a.r[]{new c.a.a.r(f2, f3), new c.a.a.r(i9, f3)}, c.a.a.a.CODABAR);
    }

    void m(int i2) {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.a.length() - 1;
        int i3 = 0;
        int i4 = i2;
        int i5 = 0;
        while (true) {
            int i6 = e[this.a.charAt(i5)];
            for (int i7 = 6; i7 >= 0; i7--) {
                int i8 = (i7 & 1) + ((i6 & 1) * 2);
                iArr[i8] = iArr[i8] + this.b[i4 + i7];
                iArr2[i8] = iArr2[i8] + 1;
                i6 >>= 1;
            }
            if (i5 >= length) {
                break;
            }
            i4 += 8;
            i5++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i9 = 0; i9 < 2; i9++) {
            fArr2[i9] = 0.0f;
            int i10 = i9 + 2;
            fArr2[i10] = ((iArr[i9] / iArr2[i9]) + (iArr[i10] / iArr2[i10])) / 2.0f;
            fArr[i9] = fArr2[i10];
            fArr[i10] = ((iArr[i10] * 2.0f) + 1.5f) / iArr2[i10];
        }
        loop3: while (true) {
            int i11 = e[this.a.charAt(i3)];
            for (int i12 = 6; i12 >= 0; i12--) {
                int i13 = (i12 & 1) + ((i11 & 1) * 2);
                float f2 = this.b[i2 + i12];
                if (f2 < fArr2[i13] || f2 > fArr[i13]) {
                    break loop3;
                }
                i11 >>= 1;
            }
            if (i3 >= length) {
                return;
            }
            i2 += 8;
            i3++;
        }
        throw c.a.a.l.a();
    }
}
