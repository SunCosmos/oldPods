package c.a.a.y.e;

import android.support.v7.widget.ActivityChooserView;
import bsh.org.objectweb.asm.Constants;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class j {
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0005, code lost:
    
        if (r5 < r0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:
    
        r2 = r4.charAt(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000f, code lost:
    
        if (f(r2) == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0011, code lost:
    
        if (r5 >= r0) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        r1 = r1 + 1;
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        if (r5 >= r0) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(java.lang.CharSequence r4, int r5) {
        /*
            int r0 = r4.length()
            r1 = 0
            if (r5 >= r0) goto L1a
        L7:
            char r2 = r4.charAt(r5)
        Lb:
            boolean r3 = f(r2)
            if (r3 == 0) goto L1a
            if (r5 >= r0) goto L1a
            int r1 = r1 + 1
            int r5 = r5 + 1
            if (r5 >= r0) goto Lb
            goto L7
        L1a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.y.e.j.a(java.lang.CharSequence, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b6 A[LOOP:1: B:27:0x00b0->B:29:0x00b6, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.lang.String r6, c.a.a.y.e.l r7, c.a.a.f r8, c.a.a.f r9) {
        /*
            r0 = 6
            c.a.a.y.e.g[] r0 = new c.a.a.y.e.g[r0]
            c.a.a.y.e.a r1 = new c.a.a.y.e.a
            r1.<init>()
            r2 = 0
            r0[r2] = r1
            c.a.a.y.e.c r1 = new c.a.a.y.e.c
            r1.<init>()
            r3 = 1
            r0[r3] = r1
            c.a.a.y.e.m r1 = new c.a.a.y.e.m
            r1.<init>()
            r4 = 2
            r0[r4] = r1
            c.a.a.y.e.n r1 = new c.a.a.y.e.n
            r1.<init>()
            r5 = 3
            r0[r5] = r1
            c.a.a.y.e.f r1 = new c.a.a.y.e.f
            r1.<init>()
            r5 = 4
            r0[r5] = r1
            c.a.a.y.e.b r1 = new c.a.a.y.e.b
            r1.<init>()
            r5 = 5
            r0[r5] = r1
            c.a.a.y.e.h r1 = new c.a.a.y.e.h
            r1.<init>(r6)
            r1.n(r7)
            r1.l(r8, r9)
            java.lang.String r7 = "[)>\u001e05\u001d"
            boolean r7 = r6.startsWith(r7)
            java.lang.String r8 = "\u001e\u0004"
            if (r7 == 0) goto L5d
            boolean r7 = r6.endsWith(r8)
            if (r7 == 0) goto L5d
            r6 = 236(0xec, float:3.31E-43)
        L50:
            r1.r(r6)
            r1.m(r4)
            int r6 = r1.f
            int r6 = r6 + 7
            r1.f = r6
            goto L6e
        L5d:
            java.lang.String r7 = "[)>\u001e06\u001d"
            boolean r7 = r6.startsWith(r7)
            if (r7 == 0) goto L6e
            boolean r6 = r6.endsWith(r8)
            if (r6 == 0) goto L6e
            r6 = 237(0xed, float:3.32E-43)
            goto L50
        L6e:
            boolean r6 = r1.i()
            if (r6 == 0) goto L87
            r6 = r0[r2]
            r6.a(r1)
            int r6 = r1.e()
            if (r6 < 0) goto L6e
            int r2 = r1.e()
            r1.j()
            goto L6e
        L87:
            int r6 = r1.a()
            r1.p()
            c.a.a.y.e.k r7 = r1.g()
            int r7 = r7.a()
            if (r6 >= r7) goto La1
            if (r2 == 0) goto La1
            if (r2 == r5) goto La1
            r6 = 254(0xfe, float:3.56E-43)
            r1.r(r6)
        La1:
            java.lang.StringBuilder r6 = r1.b()
            int r8 = r6.length()
            r9 = 129(0x81, float:1.81E-43)
            if (r8 >= r7) goto Lb0
            r6.append(r9)
        Lb0:
            int r8 = r6.length()
            if (r8 >= r7) goto Lc3
            int r8 = r6.length()
            int r8 = r8 + r3
            char r8 = o(r9, r8)
            r6.append(r8)
            goto Lb0
        Lc3:
            java.lang.StringBuilder r6 = r1.b()
            java.lang.String r6 = r6.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.y.e.j.b(java.lang.String, c.a.a.y.e.l, c.a.a.f, c.a.a.f):java.lang.String");
    }

    private static int c(float[] fArr, int[] iArr, int i2, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        for (int i3 = 0; i3 < 6; i3++) {
            iArr[i3] = (int) Math.ceil(fArr[i3]);
            int i4 = iArr[i3];
            if (i2 > i4) {
                Arrays.fill(bArr, (byte) 0);
                i2 = i4;
            }
            if (i2 == i4) {
                bArr[i3] = (byte) (bArr[i3] + 1);
            }
        }
        return i2;
    }

    private static int d(byte[] bArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 += bArr[i3];
        }
        return i2;
    }

    public static void e(char c2) {
        String hexString = Integer.toHexString(c2);
        throw new IllegalArgumentException("Illegal character: " + c2 + " (0x" + ("0000".substring(0, 4 - hexString.length()) + hexString) + ')');
    }

    public static boolean f(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    public static boolean g(char c2) {
        return c2 >= 128 && c2 <= 255;
    }

    private static boolean h(char c2) {
        return c2 == ' ' || (c2 >= '0' && c2 <= '9') || (c2 >= 'A' && c2 <= 'Z');
    }

    private static boolean i(char c2) {
        return c2 >= ' ' && c2 <= '^';
    }

    private static boolean j(char c2) {
        return c2 == ' ' || (c2 >= '0' && c2 <= '9') || (c2 >= 'a' && c2 <= 'z');
    }

    private static boolean k(char c2) {
        return m(c2) || c2 == ' ' || (c2 >= '0' && c2 <= '9') || (c2 >= 'A' && c2 <= 'Z');
    }

    private static boolean l(char c2) {
        return false;
    }

    private static boolean m(char c2) {
        return c2 == '\r' || c2 == '*' || c2 == '>';
    }

    public static int n(CharSequence charSequence, int i2, int i3) {
        char c2;
        if (i2 >= charSequence.length()) {
            return i3;
        }
        float[] fArr = {1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
        if (i3 == 0) {
            // fill-array-data instruction
            fArr[0] = 0.0f;
            fArr[1] = 1.0f;
            fArr[2] = 1.0f;
            fArr[3] = 1.0f;
            fArr[4] = 1.0f;
            fArr[5] = 1.25f;
        } else {
            fArr[i3] = 0.0f;
        }
        int i4 = 0;
        while (true) {
            int i5 = i2 + i4;
            if (i5 == charSequence.length()) {
                byte[] bArr = new byte[6];
                int[] iArr = new int[6];
                int c3 = c(fArr, iArr, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, bArr);
                int d2 = d(bArr);
                if (iArr[0] == c3) {
                    return 0;
                }
                if (d2 == 1 && bArr[5] > 0) {
                    return 5;
                }
                if (d2 == 1 && bArr[4] > 0) {
                    return 4;
                }
                if (d2 != 1 || bArr[2] <= 0) {
                    return (d2 != 1 || bArr[3] <= 0) ? 1 : 3;
                }
                return 2;
            }
            char charAt = charSequence.charAt(i5);
            i4++;
            if (f(charAt)) {
                double d3 = fArr[0];
                Double.isNaN(d3);
                fArr[0] = (float) (d3 + 0.5d);
            } else if (g(charAt)) {
                fArr[0] = (int) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 2.0f;
            } else {
                fArr[0] = (int) Math.ceil(fArr[0]);
                fArr[0] = fArr[0] + 1.0f;
            }
            if (h(charAt)) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (g(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (j(charAt)) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (g(charAt)) {
                fArr[2] = fArr[2] + 2.6666667f;
            } else {
                fArr[2] = fArr[2] + 1.3333334f;
            }
            if (k(charAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (g(charAt)) {
                fArr[3] = fArr[3] + 4.3333335f;
            } else {
                fArr[3] = fArr[3] + 3.3333333f;
            }
            if (i(charAt)) {
                fArr[4] = fArr[4] + 0.75f;
            } else if (g(charAt)) {
                fArr[4] = fArr[4] + 4.25f;
            } else {
                fArr[4] = fArr[4] + 3.25f;
            }
            if (l(charAt)) {
                c2 = 5;
                fArr[5] = fArr[5] + 4.0f;
            } else {
                c2 = 5;
                fArr[5] = fArr[5] + 1.0f;
            }
            if (i4 >= 4) {
                int[] iArr2 = new int[6];
                byte[] bArr2 = new byte[6];
                c(fArr, iArr2, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, bArr2);
                int d4 = d(bArr2);
                if (iArr2[0] < iArr2[c2] && iArr2[0] < iArr2[1] && iArr2[0] < iArr2[2] && iArr2[0] < iArr2[3] && iArr2[0] < iArr2[4]) {
                    return 0;
                }
                if (iArr2[5] < iArr2[0] || bArr2[1] + bArr2[2] + bArr2[3] + bArr2[4] == 0) {
                    return 5;
                }
                if (d4 == 1 && bArr2[4] > 0) {
                    return 4;
                }
                if (d4 == 1 && bArr2[2] > 0) {
                    return 2;
                }
                if (d4 == 1 && bArr2[3] > 0) {
                    return 3;
                }
                if (iArr2[1] + 1 < iArr2[0] && iArr2[1] + 1 < iArr2[5] && iArr2[1] + 1 < iArr2[4] && iArr2[1] + 1 < iArr2[2]) {
                    if (iArr2[1] < iArr2[3]) {
                        return 1;
                    }
                    if (iArr2[1] == iArr2[3]) {
                        for (int i6 = i2 + i4 + 1; i6 < charSequence.length(); i6++) {
                            char charAt2 = charSequence.charAt(i6);
                            if (m(charAt2)) {
                                return 3;
                            }
                            if (!k(charAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
    }

    private static char o(char c2, int i2) {
        int i3 = c2 + ((i2 * Constants.FCMPL) % 253) + 1;
        if (i3 > 254) {
            i3 -= 254;
        }
        return (char) i3;
    }
}
