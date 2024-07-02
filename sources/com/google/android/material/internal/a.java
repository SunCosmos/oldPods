package com.google.android.material.internal;

import android.os.Build;
import android.support.v7.widget.ActivityChooserView;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.lang.reflect.Constructor;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
final class a {
    static final int n;
    private static boolean o;

    @Nullable
    private static Constructor<StaticLayout> p;

    @Nullable
    private static Object q;
    private CharSequence a;
    private final TextPaint b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1612c;
    private int e;
    private boolean l;

    /* renamed from: d, reason: collision with root package name */
    private int f1613d = 0;
    private Layout.Alignment f = Layout.Alignment.ALIGN_NORMAL;
    private int g = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private float h = 0.0f;

    /* renamed from: i, reason: collision with root package name */
    private float f1614i = 1.0f;
    private int j = n;
    private boolean k = true;

    @Nullable
    private TextUtils.TruncateAt m = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.android.material.internal.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0056a extends Exception {
        C0056a(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    static {
        n = Build.VERSION.SDK_INT >= 23 ? 1 : 0;
    }

    private a(CharSequence charSequence, TextPaint textPaint, int i2) {
        this.a = charSequence;
        this.b = textPaint;
        this.f1612c = i2;
        this.e = charSequence.length();
    }

    private void b() {
        Class<?> cls;
        int i2 = Build.VERSION.SDK_INT;
        if (o) {
            return;
        }
        try {
            boolean z = this.l && i2 >= 23;
            if (i2 >= 18) {
                cls = TextDirectionHeuristic.class;
                q = z ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            } else {
                ClassLoader classLoader = a.class.getClassLoader();
                String str = this.l ? "RTL" : "LTR";
                Class<?> loadClass = classLoader.loadClass("android.text.TextDirectionHeuristic");
                Class<?> loadClass2 = classLoader.loadClass("android.text.TextDirectionHeuristics");
                q = loadClass2.getField(str).get(loadClass2);
                cls = loadClass;
            }
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2);
            p = declaredConstructor;
            declaredConstructor.setAccessible(true);
            o = true;
        } catch (Exception e) {
            throw new C0056a(e);
        }
    }

    @NonNull
    public static a c(@NonNull CharSequence charSequence, @NonNull TextPaint textPaint, @IntRange(from = 0) int i2) {
        return new a(charSequence, textPaint, i2);
    }

    public StaticLayout a() {
        if (this.a == null) {
            this.a = "";
        }
        int max = Math.max(0, this.f1612c);
        CharSequence charSequence = this.a;
        if (this.g == 1) {
            charSequence = TextUtils.ellipsize(charSequence, this.b, max, this.m);
        }
        int min = Math.min(charSequence.length(), this.e);
        this.e = min;
        if (Build.VERSION.SDK_INT < 23) {
            b();
            try {
                return (StaticLayout) ((Constructor) Preconditions.checkNotNull(p)).newInstance(charSequence, Integer.valueOf(this.f1613d), Integer.valueOf(this.e), this.b, Integer.valueOf(max), this.f, Preconditions.checkNotNull(q), Float.valueOf(1.0f), Float.valueOf(0.0f), Boolean.valueOf(this.k), null, Integer.valueOf(max), Integer.valueOf(this.g));
            } catch (Exception e) {
                throw new C0056a(e);
            }
        }
        if (this.l && this.g == 1) {
            this.f = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, this.f1613d, min, this.b, max);
        obtain.setAlignment(this.f);
        obtain.setIncludePad(this.k);
        obtain.setTextDirection(this.l ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.m;
        if (truncateAt != null) {
            obtain.setEllipsize(truncateAt);
        }
        obtain.setMaxLines(this.g);
        float f = this.h;
        if (f != 0.0f || this.f1614i != 1.0f) {
            obtain.setLineSpacing(f, this.f1614i);
        }
        if (this.g > 1) {
            obtain.setHyphenationFrequency(this.j);
        }
        return obtain.build();
    }

    @NonNull
    public a d(@NonNull Layout.Alignment alignment) {
        this.f = alignment;
        return this;
    }

    @NonNull
    public a e(@Nullable TextUtils.TruncateAt truncateAt) {
        this.m = truncateAt;
        return this;
    }

    @NonNull
    public a f(int i2) {
        this.j = i2;
        return this;
    }

    @NonNull
    public a g(boolean z) {
        this.k = z;
        return this;
    }

    public a h(boolean z) {
        this.l = z;
        return this;
    }

    @NonNull
    public a i(float f, float f2) {
        this.h = f;
        this.f1614i = f2;
        return this;
    }

    @NonNull
    public a j(@IntRange(from = 0) int i2) {
        this.g = i2;
        return this;
    }
}
