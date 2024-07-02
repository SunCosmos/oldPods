package c.a.a.b0.e;

import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: classes.dex */
final class e {
    private static final char[] a = {';', '<', '>', '@', '[', '\\', ']', '_', '`', '~', '!', '\r', '\t', ',', ':', '\n', '-', '.', '$', '/', '\"', '|', '*', '(', ')', '?', '{', '}', '\''};
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', '\r', '\t', ',', ':', '#', '-', '.', '$', '/', '+', '%', '*', '=', '^'};

    /* renamed from: c, reason: collision with root package name */
    private static final BigInteger[] f1202c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.values().length];
            a = iArr;
            try {
                iArr[b.ALPHA.ordinal()] = 1;
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
                a[b.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[b.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum b {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        f1202c = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = valueOf;
        int i2 = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = f1202c;
            if (i2 >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i2] = bigIntegerArr2[i2 - 1].multiply(valueOf);
            i2++;
        }
    }

    private static int a(int i2, int[] iArr, int i3, StringBuilder sb) {
        int i4;
        int i5;
        int i6 = 922;
        int i7 = 923;
        long j = 900;
        int i8 = 6;
        if (i2 != 901) {
            if (i2 != 924) {
                return i3;
            }
            int i9 = i3;
            boolean z = false;
            int i10 = 0;
            long j2 = 0;
            while (i9 < iArr[0] && !z) {
                int i11 = i9 + 1;
                int i12 = iArr[i9];
                if (i12 < 900) {
                    i10++;
                    j2 = (j2 * 900) + i12;
                } else if (i12 == 900 || i12 == 901 || i12 == 902 || i12 == 924 || i12 == 928 || i12 == i7 || i12 == i6) {
                    i9 = i11 - 1;
                    z = true;
                    if (i10 % 5 != 0 && i10 > 0) {
                        char[] cArr = new char[6];
                        for (int i13 = 0; i13 < 6; i13++) {
                            cArr[5 - i13] = (char) (j2 & 255);
                            j2 >>= 8;
                        }
                        sb.append(cArr);
                        i10 = 0;
                    }
                    i6 = 922;
                    i7 = 923;
                }
                i9 = i11;
                if (i10 % 5 != 0) {
                }
                i6 = 922;
                i7 = 923;
            }
            return i9;
        }
        char[] cArr2 = new char[6];
        int[] iArr2 = new int[6];
        int i14 = i3 + 1;
        int i15 = iArr[i3];
        long j3 = 0;
        boolean z2 = false;
        loop0: while (true) {
            i4 = 0;
            while (i14 < iArr[0] && !z2) {
                int i16 = i4 + 1;
                iArr2[i4] = i15;
                j3 = (j3 * j) + i15;
                int i17 = i14 + 1;
                i15 = iArr[i14];
                if (i15 == 900 || i15 == 901 || i15 == 902 || i15 == 924 || i15 == 928 || i15 == 923 || i15 == 922) {
                    i14 = i17 - 1;
                    i15 = i15;
                    i4 = i16;
                    j = 900;
                    i8 = 6;
                    z2 = true;
                } else if (i16 % 5 != 0 || i16 <= 0) {
                    i15 = i15;
                    i4 = i16;
                    i14 = i17;
                    j = 900;
                    i8 = 6;
                } else {
                    int i18 = 0;
                    while (i18 < i8) {
                        cArr2[5 - i18] = (char) (j3 % 256);
                        j3 >>= 8;
                        i18++;
                        i15 = i15;
                        i8 = 6;
                    }
                    sb.append(cArr2);
                    i14 = i17;
                    j = 900;
                    i8 = 6;
                }
            }
        }
        if (i14 != iArr[0] || i15 >= 900) {
            i5 = i4;
        } else {
            i5 = i4 + 1;
            iArr2[i4] = i15;
        }
        for (int i19 = 0; i19 < i5; i19++) {
            sb.append((char) iArr2[i19]);
        }
        return i14;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x001e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042 A[LOOP:0: B:2:0x0011->B:18:0x0042, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0047 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static c.a.a.x.e b(int[] r5, java.lang.String r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r5.length
            r2 = 2
            int r1 = r1 * 2
            r0.<init>(r1)
            r1 = 1
            r1 = r5[r1]
            c.a.a.b0.c r3 = new c.a.a.b0.c
            r3.<init>()
        L11:
            r4 = 0
            r4 = r5[r4]
            if (r2 >= r4) goto L4c
            r4 = 913(0x391, float:1.28E-42)
            if (r1 == r4) goto L3b
            r4 = 928(0x3a0, float:1.3E-42)
            if (r1 == r4) goto L36
            switch(r1) {
                case 900: goto L31;
                case 901: goto L3b;
                case 902: goto L2c;
                default: goto L21;
            }
        L21:
            switch(r1) {
                case 922: goto L27;
                case 923: goto L27;
                case 924: goto L3b;
                default: goto L24;
            }
        L24:
            int r2 = r2 + (-1)
            goto L31
        L27:
            c.a.a.h r5 = c.a.a.h.a()
            throw r5
        L2c:
            int r1 = f(r5, r2, r0)
            goto L3f
        L31:
            int r1 = g(r5, r2, r0)
            goto L3f
        L36:
            int r1 = d(r5, r2, r3)
            goto L3f
        L3b:
            int r1 = a(r1, r5, r2, r0)
        L3f:
            int r2 = r5.length
            if (r1 >= r2) goto L47
            int r2 = r1 + 1
            r1 = r5[r1]
            goto L11
        L47:
            c.a.a.h r5 = c.a.a.h.a()
            throw r5
        L4c:
            int r5 = r0.length()
            if (r5 == 0) goto L60
            c.a.a.x.e r5 = new c.a.a.x.e
            java.lang.String r0 = r0.toString()
            r1 = 0
            r5.<init>(r1, r0, r1, r6)
            r5.k(r3)
            return r5
        L60:
            c.a.a.h r5 = c.a.a.h.a()
            goto L66
        L65:
            throw r5
        L66:
            goto L65
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.e.e.b(int[], java.lang.String):c.a.a.x.e");
    }

    private static String c(int[] iArr, int i2) {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i3 = 0; i3 < i2; i3++) {
            bigInteger = bigInteger.add(f1202c[(i2 - i3) - 1].multiply(BigInteger.valueOf(iArr[i3])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw c.a.a.h.a();
    }

    private static int d(int[] iArr, int i2, c.a.a.b0.c cVar) {
        if (i2 + 2 > iArr[0]) {
            throw c.a.a.h.a();
        }
        int[] iArr2 = new int[2];
        int i3 = 0;
        while (i3 < 2) {
            iArr2[i3] = iArr[i2];
            i3++;
            i2++;
        }
        cVar.d(Integer.parseInt(c(iArr2, 2)));
        StringBuilder sb = new StringBuilder();
        int g = g(iArr, i2, sb);
        cVar.a(sb.toString());
        if (iArr[g] != 923) {
            if (iArr[g] != 922) {
                return g;
            }
            cVar.b(true);
            return g + 1;
        }
        int i4 = g + 1;
        int[] iArr3 = new int[iArr[0] - i4];
        boolean z = false;
        int i5 = 0;
        while (i4 < iArr[0] && !z) {
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            if (i7 < 900) {
                iArr3[i5] = i7;
                i4 = i6;
                i5++;
            } else {
                if (i7 != 922) {
                    throw c.a.a.h.a();
                }
                cVar.b(true);
                i4 = i6 + 1;
                z = true;
            }
        }
        cVar.c(Arrays.copyOf(iArr3, i5));
        return i4;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
    
        if (r6 == 900) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0049, code lost:
    
        if (r6 == 900) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0067, code lost:
    
        if (r6 == 900) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008c, code lost:
    
        if (r6 == 900) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ab, code lost:
    
        if (r6 == 900) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00cb, code lost:
    
        if (r6 == 900) goto L26;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0023. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void e(int[] r16, int[] r17, int r18, java.lang.StringBuilder r19) {
        /*
            Method dump skipped, instructions count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.e.e.e(int[], int[], int, java.lang.StringBuilder):void");
    }

    private static int f(int[] iArr, int i2, StringBuilder sb) {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i3 = 0;
        while (i2 < iArr[0] && !z) {
            int i4 = i2 + 1;
            int i5 = iArr[i2];
            if (i4 == iArr[0]) {
                z = true;
            }
            if (i5 < 900) {
                iArr2[i3] = i5;
                i3++;
            } else if (i5 == 900 || i5 == 901 || i5 == 924 || i5 == 928 || i5 == 923 || i5 == 922) {
                i4--;
                z = true;
            }
            if (i3 % 15 == 0 || i5 == 902 || z) {
                sb.append(c(iArr2, i3));
                i3 = 0;
            }
            i2 = i4;
        }
        return i2;
    }

    private static int g(int[] iArr, int i2, StringBuilder sb) {
        int[] iArr2 = new int[(iArr[0] - i2) * 2];
        int[] iArr3 = new int[(iArr[0] - i2) * 2];
        boolean z = false;
        int i3 = 0;
        while (i2 < iArr[0] && !z) {
            int i4 = i2 + 1;
            int i5 = iArr[i2];
            if (i5 < 900) {
                iArr2[i3] = i5 / 30;
                iArr2[i3 + 1] = i5 % 30;
                i3 += 2;
            } else if (i5 != 913) {
                if (i5 != 928) {
                    switch (i5) {
                        case 900:
                            iArr2[i3] = 900;
                            i3++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i5) {
                            }
                            break;
                    }
                }
                i4--;
                z = true;
            } else {
                iArr2[i3] = 913;
                i2 = i4 + 1;
                iArr3[i3] = iArr[i4];
                i3++;
            }
            i2 = i4;
        }
        e(iArr2, iArr3, i3, sb);
        return i2;
    }
}
