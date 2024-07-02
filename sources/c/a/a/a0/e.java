package c.a.a.a0;

import android.support.v7.widget.ActivityChooserView;
import bsh.org.objectweb.asm.Constants;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public final class e extends q {
    private static final char[] e = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
    static final int[] f;
    private static final int g;
    private final boolean a;
    private final boolean b;

    /* renamed from: c, reason: collision with root package name */
    private final StringBuilder f1182c;

    /* renamed from: d, reason: collision with root package name */
    private final int[] f1183d;

    static {
        int[] iArr = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, Constants.INSTANCEOF, 448, Constants.I2B, 400, 208, 133, 388, 196, Constants.LCMP, Constants.JSR, Constants.IF_ICMPGE, Constants.L2D, 42};
        f = iArr;
        g = iArr[39];
    }

    public e() {
        this(false);
    }

    public e(boolean z) {
        this(z, false);
    }

    public e(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
        this.f1182c = new StringBuilder(20);
        this.f1183d = new int[9];
    }

    private static String h(CharSequence charSequence) {
        int i2;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt == '+' || charAt == '$' || charAt == '%' || charAt == '/') {
                i3++;
                char charAt2 = charSequence.charAt(i3);
                if (charAt != '$') {
                    if (charAt != '%') {
                        if (charAt != '+') {
                            if (charAt != '/') {
                                charAt = 0;
                            } else if (charAt2 >= 'A' && charAt2 <= 'O') {
                                i2 = charAt2 - ' ';
                            } else {
                                if (charAt2 != 'Z') {
                                    throw c.a.a.h.a();
                                }
                                charAt = ':';
                            }
                        } else {
                            if (charAt2 < 'A' || charAt2 > 'Z') {
                                throw c.a.a.h.a();
                            }
                            i2 = charAt2 + ' ';
                        }
                    } else if (charAt2 >= 'A' && charAt2 <= 'E') {
                        i2 = charAt2 - '&';
                    } else {
                        if (charAt2 < 'F' || charAt2 > 'W') {
                            throw c.a.a.h.a();
                        }
                        i2 = charAt2 - 11;
                    }
                } else {
                    if (charAt2 < 'A' || charAt2 > 'Z') {
                        throw c.a.a.h.a();
                    }
                    i2 = charAt2 - '@';
                }
                charAt = (char) i2;
            }
            sb.append(charAt);
            i3++;
        }
        return sb.toString();
    }

    private static int[] i(c.a.a.x.a aVar, int[] iArr) {
        int k = aVar.k();
        int i2 = aVar.i(0);
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
                    if (k(iArr) == g && aVar.m(Math.max(0, i3 - ((i2 - i3) / 2)), i3, false)) {
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

    private static char j(int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = f;
            if (i3 >= iArr.length) {
                throw c.a.a.l.a();
            }
            if (iArr[i3] == i2) {
                return e[i3];
            }
            i3++;
        }
    }

    private static int k(int[] iArr) {
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            for (int i4 : iArr) {
                if (i4 < i3 && i4 > i2) {
                    i3 = i4;
                }
            }
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < length; i8++) {
                int i9 = iArr[i8];
                if (i9 > i3) {
                    i6 |= 1 << ((length - 1) - i8);
                    i5++;
                    i7 += i9;
                }
            }
            if (i5 == 3) {
                for (int i10 = 0; i10 < length && i5 > 0; i10++) {
                    int i11 = iArr[i10];
                    if (i11 > i3) {
                        i5--;
                        if (i11 * 2 >= i7) {
                            return -1;
                        }
                    }
                }
                return i6;
            }
            if (i5 <= 3) {
                return -1;
            }
            i2 = i3;
        }
    }

    @Override // c.a.a.a0.q
    public c.a.a.p c(int i2, c.a.a.x.a aVar, Map<c.a.a.e, ?> map) {
        int[] iArr = this.f1183d;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.f1182c;
        sb.setLength(0);
        int i3 = aVar.i(i(aVar, iArr)[1]);
        int k = aVar.k();
        while (true) {
            q.f(aVar, i3, iArr);
            int k2 = k(iArr);
            if (k2 < 0) {
                throw c.a.a.l.a();
            }
            char j = j(k2);
            sb.append(j);
            int i4 = i3;
            for (int i5 : iArr) {
                i4 += i5;
            }
            int i6 = aVar.i(i4);
            if (j == '*') {
                sb.setLength(sb.length() - 1);
                int i7 = 0;
                for (int i8 : iArr) {
                    i7 += i8;
                }
                int i9 = (i6 - i3) - i7;
                if (i6 != k && i9 * 2 < i7) {
                    throw c.a.a.l.a();
                }
                if (this.a) {
                    int length = sb.length() - 1;
                    int i10 = 0;
                    for (int i11 = 0; i11 < length; i11++) {
                        i10 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(this.f1182c.charAt(i11));
                    }
                    if (sb.charAt(length) != e[i10 % 43]) {
                        throw c.a.a.d.a();
                    }
                    sb.setLength(length);
                }
                if (sb.length() == 0) {
                    throw c.a.a.l.a();
                }
                float f2 = i2;
                return new c.a.a.p(this.b ? h(sb) : sb.toString(), null, new c.a.a.r[]{new c.a.a.r((r2[1] + r2[0]) / 2.0f, f2), new c.a.a.r(i3 + (i7 / 2.0f), f2)}, c.a.a.a.CODE_39);
            }
            i3 = i6;
        }
    }
}
