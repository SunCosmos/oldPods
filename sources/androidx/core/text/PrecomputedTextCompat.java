package androidx.core.text;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v7.widget.ActivityChooserView;
import android.text.Layout;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.core.os.TraceCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* loaded from: classes.dex */
public class PrecomputedTextCompat implements Spannable {
    private static final Object e = new Object();

    @NonNull
    @GuardedBy("sLock")
    private static Executor f;

    @NonNull
    private final Spannable a;

    @NonNull
    private final Params b;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    private final int[] f593c;

    /* renamed from: d, reason: collision with root package name */
    @Nullable
    private final PrecomputedText f594d;

    /* loaded from: classes.dex */
    public static final class Params {

        @NonNull
        private final TextPaint a;

        @Nullable
        private final TextDirectionHeuristic b;

        /* renamed from: c, reason: collision with root package name */
        private final int f595c;

        /* renamed from: d, reason: collision with root package name */
        private final int f596d;
        final PrecomputedText.Params e;

        /* loaded from: classes.dex */
        public static class Builder {

            @NonNull
            private final TextPaint a;
            private TextDirectionHeuristic b;

            /* renamed from: c, reason: collision with root package name */
            private int f597c;

            /* renamed from: d, reason: collision with root package name */
            private int f598d;

            public Builder(@NonNull TextPaint textPaint) {
                this.a = textPaint;
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 23) {
                    this.f597c = 1;
                    this.f598d = 1;
                } else {
                    this.f598d = 0;
                    this.f597c = 0;
                }
                this.b = i2 >= 18 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : null;
            }

            @NonNull
            public Params build() {
                return new Params(this.a, this.b, this.f597c, this.f598d);
            }

            @RequiresApi(23)
            public Builder setBreakStrategy(int i2) {
                this.f597c = i2;
                return this;
            }

            @RequiresApi(23)
            public Builder setHyphenationFrequency(int i2) {
                this.f598d = i2;
                return this;
            }

            @RequiresApi(18)
            public Builder setTextDirection(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
                this.b = textDirectionHeuristic;
                return this;
            }
        }

        @RequiresApi(28)
        public Params(@NonNull PrecomputedText.Params params) {
            this.a = params.getTextPaint();
            this.b = params.getTextDirection();
            this.f595c = params.getBreakStrategy();
            this.f596d = params.getHyphenationFrequency();
            this.e = Build.VERSION.SDK_INT < 29 ? null : params;
        }

        @SuppressLint({"NewApi"})
        Params(@NonNull TextPaint textPaint, @NonNull TextDirectionHeuristic textDirectionHeuristic, int i2, int i3) {
            this.e = Build.VERSION.SDK_INT >= 29 ? new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i2).setHyphenationFrequency(i3).setTextDirection(textDirectionHeuristic).build() : null;
            this.a = textPaint;
            this.b = textDirectionHeuristic;
            this.f595c = i2;
            this.f596d = i3;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Params)) {
                return false;
            }
            Params params = (Params) obj;
            if (equalsWithoutTextDirection(params)) {
                return Build.VERSION.SDK_INT < 18 || this.b == params.getTextDirection();
            }
            return false;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean equalsWithoutTextDirection(@NonNull Params params) {
            int i2 = Build.VERSION.SDK_INT;
            if ((i2 >= 23 && (this.f595c != params.getBreakStrategy() || this.f596d != params.getHyphenationFrequency())) || this.a.getTextSize() != params.getTextPaint().getTextSize() || this.a.getTextScaleX() != params.getTextPaint().getTextScaleX() || this.a.getTextSkewX() != params.getTextPaint().getTextSkewX()) {
                return false;
            }
            if ((i2 >= 21 && (this.a.getLetterSpacing() != params.getTextPaint().getLetterSpacing() || !TextUtils.equals(this.a.getFontFeatureSettings(), params.getTextPaint().getFontFeatureSettings()))) || this.a.getFlags() != params.getTextPaint().getFlags()) {
                return false;
            }
            if (i2 >= 24) {
                if (!this.a.getTextLocales().equals(params.getTextPaint().getTextLocales())) {
                    return false;
                }
            } else if (i2 >= 17 && !this.a.getTextLocale().equals(params.getTextPaint().getTextLocale())) {
                return false;
            }
            return this.a.getTypeface() == null ? params.getTextPaint().getTypeface() == null : this.a.getTypeface().equals(params.getTextPaint().getTypeface());
        }

        @RequiresApi(23)
        public int getBreakStrategy() {
            return this.f595c;
        }

        @RequiresApi(23)
        public int getHyphenationFrequency() {
            return this.f596d;
        }

        @Nullable
        @RequiresApi(18)
        public TextDirectionHeuristic getTextDirection() {
            return this.b;
        }

        @NonNull
        public TextPaint getTextPaint() {
            return this.a;
        }

        public int hashCode() {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 24) {
                return ObjectsCompat.hash(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Float.valueOf(this.a.getLetterSpacing()), Integer.valueOf(this.a.getFlags()), this.a.getTextLocales(), this.a.getTypeface(), Boolean.valueOf(this.a.isElegantTextHeight()), this.b, Integer.valueOf(this.f595c), Integer.valueOf(this.f596d));
            }
            if (i2 >= 21) {
                return ObjectsCompat.hash(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Float.valueOf(this.a.getLetterSpacing()), Integer.valueOf(this.a.getFlags()), this.a.getTextLocale(), this.a.getTypeface(), Boolean.valueOf(this.a.isElegantTextHeight()), this.b, Integer.valueOf(this.f595c), Integer.valueOf(this.f596d));
            }
            if (i2 < 18 && i2 < 17) {
                return ObjectsCompat.hash(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Integer.valueOf(this.a.getFlags()), this.a.getTypeface(), this.b, Integer.valueOf(this.f595c), Integer.valueOf(this.f596d));
            }
            return ObjectsCompat.hash(Float.valueOf(this.a.getTextSize()), Float.valueOf(this.a.getTextScaleX()), Float.valueOf(this.a.getTextSkewX()), Integer.valueOf(this.a.getFlags()), this.a.getTextLocale(), this.a.getTypeface(), this.b, Integer.valueOf(this.f595c), Integer.valueOf(this.f596d));
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x00df  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String toString() {
            /*
                Method dump skipped, instructions count: 325
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.text.PrecomputedTextCompat.Params.toString():java.lang.String");
        }
    }

    /* loaded from: classes.dex */
    private static class a extends FutureTask<PrecomputedTextCompat> {

        /* renamed from: androidx.core.text.PrecomputedTextCompat$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class CallableC0020a implements Callable<PrecomputedTextCompat> {
            private Params a;
            private CharSequence b;

            CallableC0020a(@NonNull Params params, @NonNull CharSequence charSequence) {
                this.a = params;
                this.b = charSequence;
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public PrecomputedTextCompat call() {
                return PrecomputedTextCompat.create(this.b, this.a);
            }
        }

        a(@NonNull Params params, @NonNull CharSequence charSequence) {
            super(new CallableC0020a(params, charSequence));
        }
    }

    @RequiresApi(28)
    private PrecomputedTextCompat(@NonNull PrecomputedText precomputedText, @NonNull Params params) {
        this.a = precomputedText;
        this.b = params;
        this.f593c = null;
        this.f594d = Build.VERSION.SDK_INT < 29 ? null : precomputedText;
    }

    private PrecomputedTextCompat(@NonNull CharSequence charSequence, @NonNull Params params, @NonNull int[] iArr) {
        this.a = new SpannableString(charSequence);
        this.b = params;
        this.f593c = iArr;
        this.f594d = null;
    }

    @SuppressLint({"NewApi"})
    public static PrecomputedTextCompat create(@NonNull CharSequence charSequence, @NonNull Params params) {
        PrecomputedText.Params params2;
        int i2 = Build.VERSION.SDK_INT;
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(params);
        try {
            TraceCompat.beginSection("PrecomputedText");
            if (i2 >= 29 && (params2 = params.e) != null) {
                return new PrecomputedTextCompat(PrecomputedText.create(charSequence, params2), params);
            }
            ArrayList arrayList = new ArrayList();
            int length = charSequence.length();
            int i3 = 0;
            while (i3 < length) {
                int indexOf = TextUtils.indexOf(charSequence, '\n', i3, length);
                i3 = indexOf < 0 ? length : indexOf + 1;
                arrayList.add(Integer.valueOf(i3));
            }
            int[] iArr = new int[arrayList.size()];
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
            }
            if (i2 >= 23) {
                StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), params.getTextPaint(), ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED).setBreakStrategy(params.getBreakStrategy()).setHyphenationFrequency(params.getHyphenationFrequency()).setTextDirection(params.getTextDirection()).build();
            } else if (i2 >= 21) {
                new StaticLayout(charSequence, params.getTextPaint(), ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            }
            return new PrecomputedTextCompat(charSequence, params, iArr);
        } finally {
            TraceCompat.endSection();
        }
    }

    @UiThread
    public static Future<PrecomputedTextCompat> getTextFuture(@NonNull CharSequence charSequence, @NonNull Params params, @Nullable Executor executor) {
        a aVar = new a(params, charSequence);
        if (executor == null) {
            synchronized (e) {
                if (f == null) {
                    f = Executors.newFixedThreadPool(1);
                }
                executor = f;
            }
        }
        executor.execute(aVar);
        return aVar;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i2) {
        return this.a.charAt(i2);
    }

    @IntRange(from = 0)
    @SuppressLint({"NewApi"})
    public int getParagraphCount() {
        return Build.VERSION.SDK_INT >= 29 ? this.f594d.getParagraphCount() : this.f593c.length;
    }

    @IntRange(from = 0)
    @SuppressLint({"NewApi"})
    public int getParagraphEnd(@IntRange(from = 0) int i2) {
        Preconditions.checkArgumentInRange(i2, 0, getParagraphCount(), "paraIndex");
        return Build.VERSION.SDK_INT >= 29 ? this.f594d.getParagraphEnd(i2) : this.f593c[i2];
    }

    @IntRange(from = 0)
    @SuppressLint({"NewApi"})
    public int getParagraphStart(@IntRange(from = 0) int i2) {
        Preconditions.checkArgumentInRange(i2, 0, getParagraphCount(), "paraIndex");
        if (Build.VERSION.SDK_INT >= 29) {
            return this.f594d.getParagraphStart(i2);
        }
        if (i2 == 0) {
            return 0;
        }
        return this.f593c[i2 - 1];
    }

    @NonNull
    public Params getParams() {
        return this.b;
    }

    @Nullable
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PrecomputedText getPrecomputedText() {
        Spannable spannable = this.a;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.a.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.a.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.a.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i2, int i3, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f594d.getSpans(i2, i3, cls) : (T[]) this.a.getSpans(i2, i3, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.a.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i2, int i3, Class cls) {
        return this.a.nextSpanTransition(i2, i3, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f594d.removeSpan(obj);
        } else {
            this.a.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i2, int i3, int i4) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f594d.setSpan(obj, i2, i3, i4);
        } else {
            this.a.setSpan(obj, i2, i3, i4);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i2, int i3) {
        return this.a.subSequence(i2, i3);
    }

    @Override // java.lang.CharSequence
    @NonNull
    public String toString() {
        return this.a.toString();
    }
}
