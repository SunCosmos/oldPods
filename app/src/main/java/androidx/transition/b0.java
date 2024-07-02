package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class b0 {
    private static final h0 a;
    static final Property<View, Float> b;

    /* renamed from: c */
    static final Property<View, Rect> f1073c;

    /* loaded from: classes.dex */
    static class a extends Property<View, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(View view) {
            return Float.valueOf(b0.c(view));
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, Float f) {
            b0.h(view, f.floatValue());
        }
    }

    /* loaded from: classes.dex */
    static class b extends Property<View, Rect> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Rect get(View view) {
            return ViewCompat.getClipBounds(view);
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, Rect rect) {
            ViewCompat.setClipBounds(view, rect);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        a = i2 >= 29 ? new g0() : i2 >= 23 ? new f0() : i2 >= 22 ? new e0() : i2 >= 21 ? new d0() : i2 >= 19 ? new c0() : new h0();
        b = new a(Float.class, "translationAlpha");
        f1073c = new b(Rect.class, "clipBounds");
    }

    public static void a(@NonNull View view) {
        a.a(view);
    }

    public static a0 b(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 18 ? new z(view) : y.c(view);
    }

    public static float c(@NonNull View view) {
        return a.c(view);
    }

    public static k0 d(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 18 ? new j0(view) : new i0(view.getWindowToken());
    }

    public static void e(@NonNull View view) {
        a.d(view);
    }

    public static void f(@NonNull View view, @Nullable Matrix matrix) {
        a.e(view, matrix);
    }

    public static void g(@NonNull View view, int i2, int i3, int i4, int i5) {
        a.f(view, i2, i3, i4, i5);
    }

    public static void h(@NonNull View view, float f) {
        a.g(view, f);
    }

    public static void i(@NonNull View view, int i2) {
        a.h(view, i2);
    }

    public static void j(@NonNull View view, @NonNull Matrix matrix) {
        a.i(view, matrix);
    }

    public static void k(@NonNull View view, @NonNull Matrix matrix) {
        a.j(view, matrix);
    }
}
