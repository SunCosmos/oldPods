package androidx.core.text;

import java.nio.CharBuffer;
import java.util.Locale;

/* loaded from: classes.dex */
public final class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE;
    public static final TextDirectionHeuristicCompat LTR = new e(null, false);
    public static final TextDirectionHeuristicCompat RTL = new e(null, true);

    /* loaded from: classes.dex */
    private static class a implements c {
        static final a b = new a(true);
        private final boolean a;

        private a(boolean z) {
            this.a = z;
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.c
        public int a(CharSequence charSequence, int i2, int i3) {
            int i4 = i3 + i2;
            boolean z = false;
            while (i2 < i4) {
                int a = TextDirectionHeuristicsCompat.a(Character.getDirectionality(charSequence.charAt(i2)));
                if (a != 0) {
                    if (a != 1) {
                        continue;
                        i2++;
                    } else if (!this.a) {
                        return 1;
                    }
                } else if (this.a) {
                    return 0;
                }
                z = true;
                i2++;
            }
            if (z) {
                return this.a ? 1 : 0;
            }
            return 2;
        }
    }

    /* loaded from: classes.dex */
    private static class b implements c {
        static final b a = new b();

        private b() {
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.c
        public int a(CharSequence charSequence, int i2, int i3) {
            int i4 = i3 + i2;
            int i5 = 2;
            while (i2 < i4 && i5 == 2) {
                i5 = TextDirectionHeuristicsCompat.b(Character.getDirectionality(charSequence.charAt(i2)));
                i2++;
            }
            return i5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface c {
        int a(CharSequence charSequence, int i2, int i3);
    }

    /* loaded from: classes.dex */
    private static abstract class d implements TextDirectionHeuristicCompat {
        private final c a;

        d(c cVar) {
            this.a = cVar;
        }

        private boolean b(CharSequence charSequence, int i2, int i3) {
            int a = this.a.a(charSequence, i2, i3);
            if (a == 0) {
                return true;
            }
            if (a != 1) {
                return a();
            }
            return false;
        }

        protected abstract boolean a();

        @Override // androidx.core.text.TextDirectionHeuristicCompat
        public boolean isRtl(CharSequence charSequence, int i2, int i3) {
            if (charSequence == null || i2 < 0 || i3 < 0 || charSequence.length() - i3 < i2) {
                throw new IllegalArgumentException();
            }
            return this.a == null ? a() : b(charSequence, i2, i3);
        }

        @Override // androidx.core.text.TextDirectionHeuristicCompat
        public boolean isRtl(char[] cArr, int i2, int i3) {
            return isRtl(CharBuffer.wrap(cArr), i2, i3);
        }
    }

    /* loaded from: classes.dex */
    private static class e extends d {
        private final boolean b;

        e(c cVar, boolean z) {
            super(cVar);
            this.b = z;
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.d
        protected boolean a() {
            return this.b;
        }
    }

    /* loaded from: classes.dex */
    private static class f extends d {
        static final f b = new f();

        f() {
            super(null);
        }

        @Override // androidx.core.text.TextDirectionHeuristicsCompat.d
        protected boolean a() {
            return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }
    }

    static {
        b bVar = b.a;
        FIRSTSTRONG_LTR = new e(bVar, false);
        FIRSTSTRONG_RTL = new e(bVar, true);
        ANYRTL_LTR = new e(a.b, false);
        LOCALE = f.b;
    }

    private TextDirectionHeuristicsCompat() {
    }

    static int a(int i2) {
        if (i2 != 0) {
            return (i2 == 1 || i2 == 2) ? 0 : 2;
        }
        return 1;
    }

    static int b(int i2) {
        if (i2 != 0) {
            if (i2 == 1 || i2 == 2) {
                return 0;
            }
            switch (i2) {
                case 14:
                case 15:
                    break;
                case 16:
                case 17:
                    return 0;
                default:
                    return 2;
            }
        }
        return 1;
    }
}
