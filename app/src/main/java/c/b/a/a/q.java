package c.b.a.a;

import java.util.ArrayList;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class q {
    public static final String[] a = new String[0];

    static {
        Pattern.compile("(?: |\\u00A0|\\s|[\\s&&[^ ]])\\s*");
    }

    public static String[] a(String str) {
        return c(str, null, -1);
    }

    public static String[] b(String str, char c2) {
        return g(str, c2, false);
    }

    public static String[] c(String str, String str2, int i2) {
        return h(str, str2, i2, false);
    }

    public static String[] d(String str, String str2) {
        return f(str, str2, -1, false);
    }

    public static String[] e(String str, String str2) {
        return f(str, str2, -1, true);
    }

    private static String[] f(String str, String str2, int i2, boolean z) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return a;
        }
        if (str2 == null || "".equals(str2)) {
            return h(str, null, i2, z);
        }
        int length2 = str2.length();
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            i3 = str.indexOf(str2, i4);
            if (i3 > -1) {
                if (i3 > i4) {
                    i5++;
                    if (i5 != i2) {
                        arrayList.add(str.substring(i4, i3));
                    }
                } else if (z) {
                    i5++;
                    if (i5 == i2) {
                        arrayList.add(str.substring(i4));
                        i3 = length;
                    } else {
                        arrayList.add("");
                    }
                }
                i4 = i3 + length2;
            }
            arrayList.add(str.substring(i4));
            i3 = length;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static String[] g(String str, char c2, boolean z) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return a;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = 0;
        while (i2 < length) {
            if (str.charAt(i2) == c2) {
                if (z2 || z) {
                    arrayList.add(str.substring(i3, i2));
                    z2 = false;
                    z3 = true;
                }
                i3 = i2 + 1;
                i2 = i3;
            } else {
                i2++;
                z2 = true;
                z3 = false;
            }
        }
        if (z2 || (z && z3)) {
            arrayList.add(str.substring(i3, i2));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static String[] h(String str, String str2, int i2, boolean z) {
        int i3;
        boolean z2;
        boolean z3;
        int i4;
        int i5;
        boolean z4;
        boolean z5;
        int i6;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return a;
        }
        ArrayList arrayList = new ArrayList();
        if (str2 == null) {
            i5 = 0;
            z4 = false;
            z5 = false;
            i6 = 0;
            int i7 = 1;
            while (i5 < length) {
                if (Character.isWhitespace(str.charAt(i5))) {
                    if (z4 || z) {
                        int i8 = i7 + 1;
                        if (i7 == i2) {
                            i5 = length;
                            z5 = false;
                        } else {
                            z5 = true;
                        }
                        arrayList.add(str.substring(i6, i5));
                        i7 = i8;
                        z4 = false;
                    }
                    i6 = i5 + 1;
                    i5 = i6;
                } else {
                    i5++;
                    z4 = true;
                    z5 = false;
                }
            }
        } else {
            if (str2.length() == 1) {
                char charAt = str2.charAt(0);
                i3 = 0;
                z2 = false;
                z3 = false;
                i4 = 0;
                int i9 = 1;
                while (i3 < length) {
                    if (str.charAt(i3) == charAt) {
                        if (z2 || z) {
                            int i10 = i9 + 1;
                            if (i9 == i2) {
                                i3 = length;
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            arrayList.add(str.substring(i4, i3));
                            i9 = i10;
                            z2 = false;
                        }
                        i4 = i3 + 1;
                        i3 = i4;
                    } else {
                        i3++;
                        z2 = true;
                        z3 = false;
                    }
                }
            } else {
                i3 = 0;
                z2 = false;
                z3 = false;
                i4 = 0;
                int i11 = 1;
                while (i3 < length) {
                    if (str2.indexOf(str.charAt(i3)) >= 0) {
                        if (z2 || z) {
                            int i12 = i11 + 1;
                            if (i11 == i2) {
                                i3 = length;
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            arrayList.add(str.substring(i4, i3));
                            i11 = i12;
                            z2 = false;
                        }
                        i4 = i3 + 1;
                        i3 = i4;
                    } else {
                        i3++;
                        z2 = true;
                        z3 = false;
                    }
                }
            }
            i5 = i3;
            z4 = z2;
            z5 = z3;
            i6 = i4;
        }
        if (z4 || (z && z5)) {
            arrayList.add(str.substring(i6, i5));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
