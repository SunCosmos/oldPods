package c.a.a.a0;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public final class g extends q {

    /* renamed from: c, reason: collision with root package name */
    private static final char[] f1184c = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f1185d;
    private static final int e;
    private final StringBuilder a = new StringBuilder(20);
    private final int[] b = new int[6];

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        f1185d = iArr;
        e = iArr[47];
    }

    private static void h(CharSequence charSequence) {
        int length = charSequence.length();
        i(charSequence, length - 2, 20);
        i(charSequence, length - 1, 15);
    }

    private static void i(CharSequence charSequence, int i2, int i3) {
        int i4 = 0;
        int i5 = 1;
        for (int i6 = i2 - 1; i6 >= 0; i6--) {
            i4 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charSequence.charAt(i6)) * i5;
            i5++;
            if (i5 > i3) {
                i5 = 1;
            }
        }
        if (charSequence.charAt(i2) != f1184c[i4 % 47]) {
            throw c.a.a.d.a();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0027. Please report as an issue. */
    private static String j(CharSequence charSequence) {
        int i2;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt >= 'a' && charAt <= 'd') {
                if (i3 >= length - 1) {
                    throw c.a.a.h.a();
                }
                i3++;
                char charAt2 = charSequence.charAt(i3);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            i2 = charAt2 - '@';
                            charAt = (char) i2;
                            break;
                        } else {
                            throw c.a.a.h.a();
                        }
                        break;
                    case 'b':
                        if (charAt2 >= 'A' && charAt2 <= 'E') {
                            i2 = charAt2 - '&';
                        } else {
                            if (charAt2 < 'F' || charAt2 > 'W') {
                                throw c.a.a.h.a();
                            }
                            i2 = charAt2 - 11;
                        }
                        charAt = (char) i2;
                        break;
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            i2 = charAt2 - ' ';
                            charAt = (char) i2;
                            break;
                        } else {
                            if (charAt2 != 'Z') {
                                throw c.a.a.h.a();
                            }
                            charAt = ':';
                            break;
                        }
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            i2 = charAt2 + ' ';
                            charAt = (char) i2;
                            break;
                        } else {
                            throw c.a.a.h.a();
                        }
                    default:
                        charAt = 0;
                        break;
                }
            }
            sb.append(charAt);
            i3++;
        }
        return sb.toString();
    }

    private int[] k(c.a.a.x.a aVar) {
        int k = aVar.k();
        int i2 = aVar.i(0);
        Arrays.fill(this.b, 0);
        int[] iArr = this.b;
        int length = iArr.length;
        int i3 = i2;
        boolean z = false;
        int i4 = 0;
        while (i2 < k) {
            if (aVar.g(i2) ^ z) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                int i5 = length - 1;
                if (i4 != i5) {
                    i4++;
                } else {
                    if (m(iArr) == e) {
                        return new int[]{i3, i2};
                    }
                    i3 += iArr[0] + iArr[1];
                    int i6 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i6);
                    iArr[i6] = 0;
                    iArr[i5] = 0;
                    i4--;
                }
                iArr[i4] = 1;
                z = !z;
            }
            i2++;
        }
        throw c.a.a.l.a();
    }

    private static char l(int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = f1185d;
            if (i3 >= iArr.length) {
                throw c.a.a.l.a();
            }
            if (iArr[i3] == i2) {
                return f1184c[i3];
            }
            i3++;
        }
    }

    private static int m(int[] iArr) {
        int length = iArr.length;
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += i3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            int round = Math.round((iArr[i5] * 9.0f) / i2);
            if (round < 1 || round > 4) {
                return -1;
            }
            if ((i5 & 1) == 0) {
                for (int i6 = 0; i6 < round; i6++) {
                    i4 = (i4 << 1) | 1;
                }
            } else {
                i4 <<= round;
            }
        }
        return i4;
    }

    @Override // c.a.a.a0.q
    public c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        int i3 = aVar.i(k(aVar)[1]);
        int k = aVar.k();
        int[] iArr = this.b;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.a;
        sb.setLength(0);
        while (true) {
            q.f(aVar, i3, iArr);
            int m = m(iArr);
            if (m < 0) {
                throw c.a.a.l.a();
            }
            char l = l(m);
            sb.append(l);
            int i4 = i3;
            for (int i5 : iArr) {
                i4 += i5;
            }
            int i6 = aVar.i(i4);
            if (l == '*') {
                sb.deleteCharAt(sb.length() - 1);
                int i7 = 0;
                for (int i8 : iArr) {
                    i7 += i8;
                }
                if (i6 == k || !aVar.g(i6)) {
                    throw c.a.a.l.a();
                }
                if (sb.length() < 2) {
                    throw c.a.a.l.a();
                }
                h(sb);
                sb.setLength(sb.length() - 2);
                float f = i2;
                return new c.a.a.p(j(sb), null, new c.a.a.r[]{new c.a.a.r((r14[1] + r14[0]) / 2.0f, f), new c.a.a.r(i3 + (i7 / 2.0f), f)}, c.a.a.a.CODE_93);
            }
            i3 = i6;
        }
    }
}
