package androidx.core.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;

/* loaded from: classes.dex */
public final class BidiFormatter {

    /* renamed from: d, reason: collision with root package name */
    static final TextDirectionHeuristicCompat f588d;
    private static final String e;
    private static final String f;
    static final BidiFormatter g;
    static final BidiFormatter h;
    private final boolean a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final TextDirectionHeuristicCompat f589c;

    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private TextDirectionHeuristicCompat f590c;

        public Builder() {
            b(BidiFormatter.c(Locale.getDefault()));
        }

        public Builder(Locale locale) {
            b(BidiFormatter.c(locale));
        }

        public Builder(boolean z) {
            b(z);
        }

        private static BidiFormatter a(boolean z) {
            return z ? BidiFormatter.h : BidiFormatter.g;
        }

        private void b(boolean z) {
            this.a = z;
            this.f590c = BidiFormatter.f588d;
            this.b = 2;
        }

        public BidiFormatter build() {
            return (this.b == 2 && this.f590c == BidiFormatter.f588d) ? a(this.a) : new BidiFormatter(this.a, this.b, this.f590c);
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
            this.f590c = textDirectionHeuristicCompat;
            return this;
        }

        public Builder stereoReset(boolean z) {
            this.b = z ? this.b | 2 : this.b & (-3);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        private static final byte[] f = new byte[1792];
        private final CharSequence a;
        private final boolean b;

        /* renamed from: c, reason: collision with root package name */
        private final int f591c;

        /* renamed from: d, reason: collision with root package name */
        private int f592d;
        private char e;

        static {
            for (int i2 = 0; i2 < 1792; i2++) {
                f[i2] = Character.getDirectionality(i2);
            }
        }

        a(CharSequence charSequence, boolean z) {
            this.a = charSequence;
            this.b = z;
            this.f591c = charSequence.length();
        }

        private static byte c(char c2) {
            return c2 < 1792 ? f[c2] : Character.getDirectionality(c2);
        }

        private byte f() {
            char charAt;
            int i2 = this.f592d;
            do {
                int i3 = this.f592d;
                if (i3 <= 0) {
                    break;
                }
                CharSequence charSequence = this.a;
                int i4 = i3 - 1;
                this.f592d = i4;
                charAt = charSequence.charAt(i4);
                this.e = charAt;
                if (charAt == '&') {
                    return (byte) 12;
                }
            } while (charAt != ';');
            this.f592d = i2;
            this.e = ';';
            return (byte) 13;
        }

        private byte g() {
            char charAt;
            do {
                int i2 = this.f592d;
                if (i2 >= this.f591c) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.a;
                this.f592d = i2 + 1;
                charAt = charSequence.charAt(i2);
                this.e = charAt;
            } while (charAt != ';');
            return (byte) 12;
        }

        private byte h() {
            char charAt;
            int i2 = this.f592d;
            while (true) {
                int i3 = this.f592d;
                if (i3 <= 0) {
                    break;
                }
                CharSequence charSequence = this.a;
                int i4 = i3 - 1;
                this.f592d = i4;
                char charAt2 = charSequence.charAt(i4);
                this.e = charAt2;
                if (charAt2 == '<') {
                    return (byte) 12;
                }
                if (charAt2 == '>') {
                    break;
                }
                if (charAt2 == '\"' || charAt2 == '\'') {
                    do {
                        int i5 = this.f592d;
                        if (i5 > 0) {
                            CharSequence charSequence2 = this.a;
                            int i6 = i5 - 1;
                            this.f592d = i6;
                            charAt = charSequence2.charAt(i6);
                            this.e = charAt;
                        }
                    } while (charAt != charAt2);
                }
            }
            this.f592d = i2;
            this.e = '>';
            return (byte) 13;
        }

        private byte i() {
            char charAt;
            int i2 = this.f592d;
            while (true) {
                int i3 = this.f592d;
                if (i3 >= this.f591c) {
                    this.f592d = i2;
                    this.e = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.a;
                this.f592d = i3 + 1;
                char charAt2 = charSequence.charAt(i3);
                this.e = charAt2;
                if (charAt2 == '>') {
                    return (byte) 12;
                }
                if (charAt2 == '\"' || charAt2 == '\'') {
                    do {
                        int i4 = this.f592d;
                        if (i4 < this.f591c) {
                            CharSequence charSequence2 = this.a;
                            this.f592d = i4 + 1;
                            charAt = charSequence2.charAt(i4);
                            this.e = charAt;
                        }
                    } while (charAt != charAt2);
                }
            }
        }

        byte a() {
            char charAt = this.a.charAt(this.f592d - 1);
            this.e = charAt;
            if (Character.isLowSurrogate(charAt)) {
                int codePointBefore = Character.codePointBefore(this.a, this.f592d);
                this.f592d -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.f592d--;
            byte c2 = c(this.e);
            if (!this.b) {
                return c2;
            }
            char c3 = this.e;
            return c3 == '>' ? h() : c3 == ';' ? f() : c2;
        }

        byte b() {
            char charAt = this.a.charAt(this.f592d);
            this.e = charAt;
            if (Character.isHighSurrogate(charAt)) {
                int codePointAt = Character.codePointAt(this.a, this.f592d);
                this.f592d += Character.charCount(codePointAt);
                return Character.getDirectionality(codePointAt);
            }
            this.f592d++;
            byte c2 = c(this.e);
            if (!this.b) {
                return c2;
            }
            char c3 = this.e;
            return c3 == '<' ? i() : c3 == '&' ? g() : c2;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:46:0x0045. Please report as an issue. */
        int d() {
            this.f592d = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (this.f592d < this.f591c && i2 == 0) {
                byte b = b();
                if (b != 0) {
                    if (b == 1 || b == 2) {
                        if (i4 == 0) {
                            return 1;
                        }
                    } else if (b != 9) {
                        switch (b) {
                            case 14:
                            case 15:
                                i4++;
                                i3 = -1;
                                continue;
                            case 16:
                            case 17:
                                i4++;
                                i3 = 1;
                                continue;
                            case 18:
                                i4--;
                                i3 = 0;
                                continue;
                        }
                    }
                } else if (i4 == 0) {
                    return -1;
                }
                i2 = i4;
            }
            if (i2 == 0) {
                return 0;
            }
            if (i3 != 0) {
                return i3;
            }
            while (this.f592d > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i2 == i4) {
                            return -1;
                        }
                        i4--;
                    case 16:
                    case 17:
                        if (i2 == i4) {
                            return 1;
                        }
                        i4--;
                    case 18:
                        i4++;
                }
            }
            return 0;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:33:0x001c. Please report as an issue. */
        int e() {
            this.f592d = this.f591c;
            int i2 = 0;
            int i3 = 0;
            while (this.f592d > 0) {
                byte a = a();
                if (a != 0) {
                    if (a == 1 || a == 2) {
                        if (i2 == 0) {
                            return 1;
                        }
                        if (i3 == 0) {
                            i3 = i2;
                        }
                    } else if (a != 9) {
                        switch (a) {
                            case 14:
                            case 15:
                                if (i3 == i2) {
                                    return -1;
                                }
                                i2--;
                                break;
                            case 16:
                            case 17:
                                if (i3 == i2) {
                                    return 1;
                                }
                                i2--;
                                break;
                            case 18:
                                i2++;
                                break;
                            default:
                                if (i3 != 0) {
                                    break;
                                } else {
                                    i3 = i2;
                                    break;
                                }
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (i2 == 0) {
                        return -1;
                    }
                    if (i3 == 0) {
                        i3 = i2;
                    }
                }
            }
            return 0;
        }
    }

    static {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        f588d = textDirectionHeuristicCompat;
        e = Character.toString((char) 8206);
        f = Character.toString((char) 8207);
        g = new BidiFormatter(false, 2, textDirectionHeuristicCompat);
        h = new BidiFormatter(true, 2, textDirectionHeuristicCompat);
    }

    BidiFormatter(boolean z, int i2, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.a = z;
        this.b = i2;
        this.f589c = textDirectionHeuristicCompat;
    }

    private static int a(CharSequence charSequence) {
        return new a(charSequence, false).d();
    }

    private static int b(CharSequence charSequence) {
        return new a(charSequence, false).e();
    }

    static boolean c(Locale locale) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
    }

    private String d(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        return (this.a || !(isRtl || b(charSequence) == 1)) ? this.a ? (!isRtl || b(charSequence) == -1) ? f : "" : "" : e;
    }

    private String e(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        return (this.a || !(isRtl || a(charSequence) == 1)) ? this.a ? (!isRtl || a(charSequence) == -1) ? f : "" : "" : e;
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static BidiFormatter getInstance(Locale locale) {
        return new Builder(locale).build();
    }

    public static BidiFormatter getInstance(boolean z) {
        return new Builder(z).build();
    }

    public boolean getStereoReset() {
        return (this.b & 2) != 0;
    }

    public boolean isRtl(CharSequence charSequence) {
        return this.f589c.isRtl(charSequence, 0, charSequence.length());
    }

    public boolean isRtl(String str) {
        return isRtl((CharSequence) str);
    }

    public boolean isRtlContext() {
        return this.a;
    }

    public CharSequence unicodeWrap(CharSequence charSequence) {
        return unicodeWrap(charSequence, this.f589c, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(charSequence, textDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (getStereoReset() && z) {
            spannableStringBuilder.append((CharSequence) e(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        if (isRtl != this.a) {
            spannableStringBuilder.append(isRtl ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) d(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        return spannableStringBuilder;
    }

    public CharSequence unicodeWrap(CharSequence charSequence, boolean z) {
        return unicodeWrap(charSequence, this.f589c, z);
    }

    public String unicodeWrap(String str) {
        return unicodeWrap(str, this.f589c, true);
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(str, textDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (str == null) {
            return null;
        }
        return unicodeWrap((CharSequence) str, textDirectionHeuristicCompat, z).toString();
    }

    public String unicodeWrap(String str, boolean z) {
        return unicodeWrap(str, this.f589c, z);
    }
}
