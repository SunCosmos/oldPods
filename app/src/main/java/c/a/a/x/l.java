package c.a.a.x;

import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes.dex */
public final class l {
    private static final String a;
    private static final boolean b;

    static {
        String name = Charset.defaultCharset().name();
        a = name;
        b = "SJIS".equalsIgnoreCase(name) || "EUC_JP".equalsIgnoreCase(name);
    }

    public static String a(byte[] bArr, Map<c.a.a.e, ?> map) {
        String str;
        byte[] bArr2 = bArr;
        if (map != null && (str = (String) map.get(c.a.a.e.CHARACTER_SET)) != null) {
            return str;
        }
        int length = bArr2.length;
        boolean z = true;
        int i2 = 0;
        boolean z2 = bArr2.length > 3 && bArr2[0] == -17 && bArr2[1] == -69 && bArr2[2] == -65;
        int i3 = 0;
        boolean z3 = true;
        boolean z4 = true;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i4 < length && (z || z3 || z4)) {
            int i14 = bArr2[i4] & 255;
            if (z4) {
                int i15 = i14 & 128;
                if (i5 > 0) {
                    if (i15 != 0) {
                        i5--;
                    }
                    z4 = false;
                } else if (i15 != 0) {
                    if ((i14 & 64) != 0) {
                        i5++;
                        if ((i14 & 32) == 0) {
                            i7++;
                        } else {
                            i5++;
                            if ((i14 & 16) == 0) {
                                i8++;
                            } else {
                                i5++;
                                if ((i14 & 8) == 0) {
                                    i9++;
                                }
                            }
                        }
                    }
                    z4 = false;
                }
            }
            if (z) {
                if (i14 > 127 && i14 < 160) {
                    z = false;
                } else if (i14 > 159 && (i14 < 192 || i14 == 215 || i14 == 247)) {
                    i11++;
                }
            }
            if (z3) {
                if (i6 > 0) {
                    if (i14 >= 64 && i14 != 127 && i14 <= 252) {
                        i6--;
                    }
                    z3 = false;
                } else {
                    if (i14 != 128 && i14 != 160 && i14 <= 239) {
                        if (i14 <= 160 || i14 >= 224) {
                            if (i14 > 127) {
                                i6++;
                                int i16 = i12 + 1;
                                if (i16 > i2) {
                                    i2 = i16;
                                    i12 = i2;
                                } else {
                                    i12 = i16;
                                }
                            } else {
                                i12 = 0;
                            }
                            i13 = 0;
                        } else {
                            i3++;
                            int i17 = i13 + 1;
                            if (i17 > i10) {
                                i10 = i17;
                                i13 = i10;
                            } else {
                                i13 = i17;
                            }
                            i12 = 0;
                        }
                    }
                    z3 = false;
                }
            }
            i4++;
            bArr2 = bArr;
        }
        if (z4 && i5 > 0) {
            z4 = false;
        }
        if (z3 && i6 > 0) {
            z3 = false;
        }
        return (!z4 || (!z2 && (i7 + i8) + i9 <= 0)) ? (!z3 || (!b && i10 < 3 && i2 < 3)) ? (z && z3) ? (!(i10 == 2 && i3 == 2) && i11 * 10 < length) ? "ISO8859_1" : "SJIS" : z ? "ISO8859_1" : z3 ? "SJIS" : z4 ? "UTF8" : a : "SJIS" : "UTF8";
    }
}
