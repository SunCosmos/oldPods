package c.a.a.v.d;

import c.a.a.h;
import c.a.a.x.n.c;
import c.a.a.x.n.e;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class a {
    private static final String[] b = {"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* renamed from: c, reason: collision with root package name */
    private static final String[] f1263c = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* renamed from: d, reason: collision with root package name */
    private static final String[] f1264d = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] e = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] f = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private c.a.a.v.a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c.a.a.v.d.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C0044a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.values().length];
            a = iArr;
            try {
                iArr[b.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[b.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[b.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[b.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum b {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private boolean[] a(boolean[] zArr) {
        c.a.a.x.n.a aVar;
        int i2 = 8;
        if (this.a.d() <= 2) {
            i2 = 6;
            aVar = c.a.a.x.n.a.j;
        } else if (this.a.d() <= 8) {
            aVar = c.a.a.x.n.a.n;
        } else if (this.a.d() <= 22) {
            i2 = 10;
            aVar = c.a.a.x.n.a.f1315i;
        } else {
            i2 = 12;
            aVar = c.a.a.x.n.a.h;
        }
        int c2 = this.a.c();
        int length = zArr.length / i2;
        if (length < c2) {
            throw h.a();
        }
        int length2 = zArr.length % i2;
        int i3 = length - c2;
        int[] iArr = new int[length];
        int i4 = 0;
        while (i4 < length) {
            iArr[i4] = g(zArr, length2, i2);
            i4++;
            length2 += i2;
        }
        try {
            new c(aVar).a(iArr, i3);
            int i5 = (1 << i2) - 1;
            int i6 = 0;
            for (int i7 = 0; i7 < c2; i7++) {
                int i8 = iArr[i7];
                if (i8 == 0 || i8 == i5) {
                    throw h.a();
                }
                if (i8 == 1 || i8 == i5 - 1) {
                    i6++;
                }
            }
            boolean[] zArr2 = new boolean[(c2 * i2) - i6];
            int i9 = 0;
            for (int i10 = 0; i10 < c2; i10++) {
                int i11 = iArr[i10];
                if (i11 == 1 || i11 == i5 - 1) {
                    Arrays.fill(zArr2, i9, (i9 + i2) - 1, i11 > 1);
                    i9 += i2 - 1;
                } else {
                    int i12 = i2 - 1;
                    while (i12 >= 0) {
                        int i13 = i9 + 1;
                        zArr2[i9] = ((1 << i12) & i11) != 0;
                        i12--;
                        i9 = i13;
                    }
                }
            }
            return zArr2;
        } catch (e unused) {
            throw h.a();
        }
    }

    private static String d(b bVar, int i2) {
        int i3 = C0044a.a[bVar.ordinal()];
        if (i3 == 1) {
            return b[i2];
        }
        if (i3 == 2) {
            return f1263c[i2];
        }
        if (i3 == 3) {
            return f1264d[i2];
        }
        if (i3 == 4) {
            return e[i2];
        }
        if (i3 == 5) {
            return f[i2];
        }
        throw new IllegalStateException("Bad table");
    }

    private static String e(boolean[] zArr) {
        int length = zArr.length;
        b bVar = b.UPPER;
        StringBuilder sb = new StringBuilder(20);
        b bVar2 = bVar;
        int i2 = 0;
        while (i2 < length) {
            if (bVar != b.BINARY) {
                int i3 = bVar == b.DIGIT ? 4 : 5;
                if (length - i2 < i3) {
                    break;
                }
                int g = g(zArr, i2, i3);
                i2 += i3;
                String d2 = d(bVar, g);
                if (d2.startsWith("CTRL_")) {
                    b f2 = f(d2.charAt(5));
                    if (d2.charAt(6) == 'L') {
                        bVar = f2;
                        bVar2 = bVar;
                    } else {
                        bVar = f2;
                    }
                } else {
                    sb.append(d2);
                    bVar = bVar2;
                }
            } else {
                if (length - i2 < 5) {
                    break;
                }
                int g2 = g(zArr, i2, 5);
                i2 += 5;
                if (g2 == 0) {
                    if (length - i2 < 11) {
                        break;
                    }
                    g2 = g(zArr, i2, 11) + 31;
                    i2 += 11;
                }
                int i4 = 0;
                while (true) {
                    if (i4 >= g2) {
                        break;
                    }
                    if (length - i2 < 8) {
                        i2 = length;
                        break;
                    }
                    sb.append((char) g(zArr, i2, 8));
                    i2 += 8;
                    i4++;
                }
                bVar = bVar2;
            }
        }
        return sb.toString();
    }

    private static b f(char c2) {
        return c2 != 'B' ? c2 != 'D' ? c2 != 'P' ? c2 != 'L' ? c2 != 'M' ? b.UPPER : b.MIXED : b.LOWER : b.PUNCT : b.DIGIT : b.BINARY;
    }

    private static int g(boolean[] zArr, int i2, int i3) {
        int i4 = 0;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 <<= 1;
            if (zArr[i5]) {
                i4 |= 1;
            }
        }
        return i4;
    }

    private static int h(int i2, boolean z) {
        return ((z ? 88 : 112) + (i2 * 16)) * i2;
    }

    public c.a.a.x.e b(c.a.a.v.a aVar) {
        this.a = aVar;
        return new c.a.a.x.e(null, e(a(c(aVar.a()))), null, null);
    }

    boolean[] c(c.a.a.x.b bVar) {
        boolean e2 = this.a.e();
        int d2 = this.a.d();
        int i2 = e2 ? (d2 * 4) + 11 : (d2 * 4) + 14;
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[h(d2, e2)];
        int i3 = 2;
        if (e2) {
            for (int i4 = 0; i4 < i2; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                iArr[(i5 - i7) - 1] = (i6 - r12) - 1;
                iArr[i5 + i7] = (i7 / 15) + i7 + i6 + 1;
            }
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < d2) {
            int i10 = (d2 - i8) * 4;
            int i11 = e2 ? i10 + 9 : i10 + 12;
            int i12 = i8 * 2;
            int i13 = (i2 - 1) - i12;
            int i14 = 0;
            while (i14 < i11) {
                int i15 = i14 * 2;
                int i16 = 0;
                while (i16 < i3) {
                    int i17 = i12 + i16;
                    int i18 = i12 + i14;
                    zArr[i9 + i15 + i16] = bVar.d(iArr[i17], iArr[i18]);
                    int i19 = iArr[i18];
                    int i20 = i13 - i16;
                    zArr[(i11 * 2) + i9 + i15 + i16] = bVar.d(i19, iArr[i20]);
                    int i21 = i13 - i14;
                    zArr[(i11 * 4) + i9 + i15 + i16] = bVar.d(iArr[i20], iArr[i21]);
                    zArr[(i11 * 6) + i9 + i15 + i16] = bVar.d(iArr[i21], iArr[i17]);
                    i16++;
                    d2 = d2;
                    e2 = e2;
                    i3 = 2;
                }
                i14++;
                i3 = 2;
            }
            i9 += i11 * 8;
            i8++;
            i3 = 2;
        }
        return zArr;
    }
}
