package c.a.a.a0;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class b extends r {
    private static final char[] a = {'A', 'B', 'C', 'D'};
    private static final char[] b = {'T', 'N', '*', 'E'};

    /* renamed from: c, reason: collision with root package name */
    private static final char[] f1181c = {'/', ':', '+', '.'};

    @Override // c.a.a.a0.r
    public boolean[] c(String str) {
        int i2;
        if (str.length() < 2) {
            throw new IllegalArgumentException("Codabar should start/end with start/stop symbols");
        }
        char upperCase = Character.toUpperCase(str.charAt(0));
        char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
        char[] cArr = a;
        boolean z = a.h(cArr, upperCase) && a.h(cArr, upperCase2);
        char[] cArr2 = b;
        boolean z2 = a.h(cArr2, upperCase) && a.h(cArr2, upperCase2);
        if (!z && !z2) {
            throw new IllegalArgumentException("Codabar should start/end with " + Arrays.toString(cArr) + ", or start/end with " + Arrays.toString(cArr2));
        }
        int i3 = 20;
        for (int i4 = 1; i4 < str.length() - 1; i4++) {
            if (Character.isDigit(str.charAt(i4)) || str.charAt(i4) == '-' || str.charAt(i4) == '$') {
                i3 += 9;
            } else {
                if (!a.h(f1181c, str.charAt(i4))) {
                    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i4) + '\'');
                }
                i3 += 10;
            }
        }
        boolean[] zArr = new boolean[i3 + (str.length() - 1)];
        int i5 = 0;
        for (int i6 = 0; i6 < str.length(); i6++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i6));
            if (i6 == 0 || i6 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int i7 = 0;
            while (true) {
                char[] cArr3 = a.f1160d;
                if (i7 >= cArr3.length) {
                    i2 = 0;
                    break;
                }
                if (upperCase3 == cArr3[i7]) {
                    i2 = a.e[i7];
                    break;
                }
                i7++;
            }
            int i8 = 0;
            boolean z3 = true;
            while (true) {
                int i9 = 0;
                while (i8 < 7) {
                    zArr[i5] = z3;
                    i5++;
                    if (((i2 >> (6 - i8)) & 1) == 0 || i9 == 1) {
                        z3 = !z3;
                        i8++;
                    } else {
                        i9++;
                    }
                }
                break;
            }
            if (i6 < str.length() - 1) {
                zArr[i5] = false;
                i5++;
            }
        }
        return zArr;
    }
}
