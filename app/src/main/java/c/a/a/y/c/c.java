package c.a.a.y.c;

import bsh.org.objectweb.asm.Constants;
import c.a.a.h;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
final class c {
    private static final char[] a = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] b = {'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};

    /* renamed from: c, reason: collision with root package name */
    private static final char[] f1319c = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /* renamed from: d, reason: collision with root package name */
    private static final char[] f1320d = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};

    /* loaded from: classes.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.values().length];
            a = iArr;
            try {
                iArr[b.C40_ENCODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.TEXT_ENCODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[b.ANSIX12_ENCODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[b.EDIFACT_ENCODE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[b.BASE256_ENCODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum b {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c.a.a.x.e a(byte[] bArr) {
        c.a.a.x.c cVar = new c.a.a.x.c(bArr);
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        b bVar = b.ASCII_ENCODE;
        do {
            b bVar2 = b.ASCII_ENCODE;
            if (bVar == bVar2) {
                bVar = c(cVar, sb, sb2);
            } else {
                int i2 = a.a[bVar.ordinal()];
                if (i2 == 1) {
                    e(cVar, sb);
                } else if (i2 == 2) {
                    g(cVar, sb);
                } else if (i2 == 3) {
                    b(cVar, sb);
                } else if (i2 == 4) {
                    f(cVar, sb);
                } else {
                    if (i2 != 5) {
                        throw h.a();
                    }
                    d(cVar, sb, arrayList);
                }
                bVar = bVar2;
            }
            if (bVar == b.PAD_ENCODE) {
                break;
            }
        } while (cVar.a() > 0);
        if (sb2.length() > 0) {
            sb.append((CharSequence) sb2);
        }
        String sb3 = sb.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new c.a.a.x.e(bArr, sb3, arrayList, null);
    }

    private static void b(c.a.a.x.c cVar, StringBuilder sb) {
        int d2;
        int i2;
        char c2;
        int[] iArr = new int[3];
        while (cVar.a() != 8 && (d2 = cVar.d(8)) != 254) {
            h(d2, cVar.d(8), iArr);
            for (int i3 = 0; i3 < 3; i3++) {
                int i4 = iArr[i3];
                if (i4 == 0) {
                    c2 = '\r';
                } else if (i4 == 1) {
                    c2 = '*';
                } else if (i4 == 2) {
                    c2 = '>';
                } else if (i4 == 3) {
                    c2 = ' ';
                } else {
                    if (i4 < 14) {
                        i2 = i4 + 44;
                    } else {
                        if (i4 >= 40) {
                            throw h.a();
                        }
                        i2 = i4 + 51;
                    }
                    c2 = (char) i2;
                }
                sb.append(c2);
            }
            if (cVar.a() <= 0) {
                return;
            }
        }
    }

    private static b c(c.a.a.x.c cVar, StringBuilder sb, StringBuilder sb2) {
        String str;
        boolean z = false;
        do {
            int d2 = cVar.d(8);
            if (d2 == 0) {
                throw h.a();
            }
            if (d2 <= 128) {
                if (z) {
                    d2 += 128;
                }
                sb.append((char) (d2 - 1));
                return b.ASCII_ENCODE;
            }
            if (d2 == 129) {
                return b.PAD_ENCODE;
            }
            if (d2 <= 229) {
                int i2 = d2 - 130;
                if (i2 < 10) {
                    sb.append('0');
                }
                sb.append(i2);
            } else {
                if (d2 == 230) {
                    return b.C40_ENCODE;
                }
                if (d2 == 231) {
                    return b.BASE256_ENCODE;
                }
                if (d2 == 232) {
                    sb.append((char) 29);
                } else if (d2 != 233 && d2 != 234) {
                    if (d2 == 235) {
                        z = true;
                    } else {
                        if (d2 == 236) {
                            str = "[)>\u001e05\u001d";
                        } else if (d2 == 237) {
                            str = "[)>\u001e06\u001d";
                        } else {
                            if (d2 == 238) {
                                return b.ANSIX12_ENCODE;
                            }
                            if (d2 == 239) {
                                return b.TEXT_ENCODE;
                            }
                            if (d2 == 240) {
                                return b.EDIFACT_ENCODE;
                            }
                            if (d2 != 241 && d2 >= 242 && (d2 != 254 || cVar.a() != 0)) {
                                throw h.a();
                            }
                        }
                        sb.append(str);
                        sb2.insert(0, "\u001e\u0004");
                    }
                }
            }
        } while (cVar.a() > 0);
        return b.ASCII_ENCODE;
    }

    private static void d(c.a.a.x.c cVar, StringBuilder sb, Collection<byte[]> collection) {
        int c2 = cVar.c() + 1;
        int i2 = c2 + 1;
        int i3 = i(cVar.d(8), c2);
        if (i3 == 0) {
            i3 = cVar.a() / 8;
        } else if (i3 >= 250) {
            i3 = ((i3 - 249) * 250) + i(cVar.d(8), i2);
            i2++;
        }
        if (i3 < 0) {
            throw h.a();
        }
        byte[] bArr = new byte[i3];
        int i4 = 0;
        while (i4 < i3) {
            if (cVar.a() < 8) {
                throw h.a();
            }
            bArr[i4] = (byte) i(cVar.d(8), i2);
            i4++;
            i2++;
        }
        collection.add(bArr);
        try {
            sb.append(new String(bArr, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Platform does not support required encoding: " + e);
        }
    }

    private static void e(c.a.a.x.c cVar, StringBuilder sb) {
        int d2;
        int i2;
        char c2;
        char c3;
        int[] iArr = new int[3];
        boolean z = false;
        int i3 = 0;
        while (cVar.a() != 8 && (d2 = cVar.d(8)) != 254) {
            h(d2, cVar.d(8), iArr);
            for (int i4 = 0; i4 < 3; i4++) {
                int i5 = iArr[i4];
                if (i3 != 0) {
                    if (i3 == 1) {
                        if (z) {
                            i2 = i5 + 128;
                            c3 = (char) i2;
                            sb.append(c3);
                            z = false;
                        }
                        c2 = (char) i5;
                        sb.append(c2);
                    } else if (i3 == 2) {
                        char[] cArr = b;
                        if (i5 < cArr.length) {
                            c2 = cArr[i5];
                            if (z) {
                                c3 = (char) (c2 + 128);
                                sb.append(c3);
                                z = false;
                            }
                        } else if (i5 == 27) {
                            c2 = 29;
                        } else {
                            if (i5 != 30) {
                                throw h.a();
                            }
                            z = true;
                        }
                        sb.append(c2);
                    } else {
                        if (i3 != 3) {
                            throw h.a();
                        }
                        if (z) {
                            i2 = i5 + 224;
                            c3 = (char) i2;
                            sb.append(c3);
                            z = false;
                        } else {
                            i5 += 96;
                            c2 = (char) i5;
                            sb.append(c2);
                        }
                    }
                    i3 = 0;
                } else if (i5 < 3) {
                    i3 = i5 + 1;
                } else {
                    char[] cArr2 = a;
                    if (i5 >= cArr2.length) {
                        throw h.a();
                    }
                    char c4 = cArr2[i5];
                    if (z) {
                        sb.append((char) (c4 + 128));
                        z = false;
                    } else {
                        sb.append(c4);
                    }
                }
            }
            if (cVar.a() <= 0) {
                return;
            }
        }
    }

    private static void f(c.a.a.x.c cVar, StringBuilder sb) {
        while (cVar.a() > 16) {
            for (int i2 = 0; i2 < 4; i2++) {
                int d2 = cVar.d(6);
                if (d2 == 31) {
                    int b2 = 8 - cVar.b();
                    if (b2 != 8) {
                        cVar.d(b2);
                        return;
                    }
                    return;
                }
                if ((d2 & 32) == 0) {
                    d2 |= 64;
                }
                sb.append((char) d2);
            }
            if (cVar.a() <= 0) {
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0035, code lost:
    
        if (r3 != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0037, code lost:
    
        r3 = (char) (r4 + 128);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004c, code lost:
    
        if (r3 != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void g(c.a.a.x.c r9, java.lang.StringBuilder r10) {
        /*
            r0 = 3
            int[] r1 = new int[r0]
            r2 = 0
            r3 = 0
            r4 = 0
        L6:
            int r5 = r9.a()
            r6 = 8
            if (r5 != r6) goto Lf
            return
        Lf:
            int r5 = r9.d(r6)
            r7 = 254(0xfe, float:3.56E-43)
            if (r5 != r7) goto L18
            return
        L18:
            int r6 = r9.d(r6)
            h(r5, r6, r1)
            r5 = 0
        L20:
            if (r5 >= r0) goto L93
            r6 = r1[r5]
            if (r4 == 0) goto L71
            r7 = 1
            if (r4 == r7) goto L61
            r8 = 2
            if (r4 == r8) goto L45
            if (r4 != r0) goto L40
            char[] r4 = c.a.a.y.c.c.f1320d
            int r7 = r4.length
            if (r6 >= r7) goto L3b
            char r4 = r4[r6]
            if (r3 == 0) goto L6c
        L37:
            int r4 = r4 + 128
            char r3 = (char) r4
            goto L66
        L3b:
            c.a.a.h r9 = c.a.a.h.a()
            throw r9
        L40:
            c.a.a.h r9 = c.a.a.h.a()
            throw r9
        L45:
            char[] r4 = c.a.a.y.c.c.b
            int r8 = r4.length
            if (r6 >= r8) goto L4f
            char r4 = r4[r6]
            if (r3 == 0) goto L6c
            goto L37
        L4f:
            r4 = 27
            if (r6 != r4) goto L56
            r4 = 29
            goto L6c
        L56:
            r3 = 30
            if (r6 != r3) goto L5c
            r3 = 1
            goto L6f
        L5c:
            c.a.a.h r9 = c.a.a.h.a()
            throw r9
        L61:
            if (r3 == 0) goto L6b
            int r6 = r6 + 128
            char r3 = (char) r6
        L66:
            r10.append(r3)
            r3 = 0
            goto L6f
        L6b:
            char r4 = (char) r6
        L6c:
            r10.append(r4)
        L6f:
            r4 = 0
            goto L8b
        L71:
            if (r6 >= r0) goto L77
            int r6 = r6 + 1
            r4 = r6
            goto L8b
        L77:
            char[] r7 = c.a.a.y.c.c.f1319c
            int r8 = r7.length
            if (r6 >= r8) goto L8e
            char r6 = r7[r6]
            if (r3 == 0) goto L88
            int r6 = r6 + 128
            char r3 = (char) r6
            r10.append(r3)
            r3 = 0
            goto L8b
        L88:
            r10.append(r6)
        L8b:
            int r5 = r5 + 1
            goto L20
        L8e:
            c.a.a.h r9 = c.a.a.h.a()
            throw r9
        L93:
            int r5 = r9.a()
            if (r5 > 0) goto L6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.y.c.c.g(c.a.a.x.c, java.lang.StringBuilder):void");
    }

    private static void h(int i2, int i3, int[] iArr) {
        int i4 = ((i2 << 8) + i3) - 1;
        int i5 = i4 / 1600;
        iArr[0] = i5;
        int i6 = i4 - (i5 * 1600);
        int i7 = i6 / 40;
        iArr[1] = i7;
        iArr[2] = i6 - (i7 * 40);
    }

    private static int i(int i2, int i3) {
        int i4 = i2 - (((i3 * Constants.FCMPL) % 255) + 1);
        return i4 >= 0 ? i4 : i4 + 256;
    }
}
