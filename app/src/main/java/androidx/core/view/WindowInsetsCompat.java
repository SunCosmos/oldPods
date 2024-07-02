package androidx.core.view;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class WindowInsetsCompat {

    @NonNull
    public static final WindowInsetsCompat CONSUMED;
    private final k a;

    /* loaded from: classes.dex */
    public static final class Builder {
        private final e a;

        public Builder() {
            int i2 = Build.VERSION.SDK_INT;
            this.a = i2 >= 30 ? new d() : i2 >= 29 ? new c() : i2 >= 20 ? new b() : new e();
        }

        public Builder(@NonNull WindowInsetsCompat windowInsetsCompat) {
            int i2 = Build.VERSION.SDK_INT;
            this.a = i2 >= 30 ? new d(windowInsetsCompat) : i2 >= 29 ? new c(windowInsetsCompat) : i2 >= 20 ? new b(windowInsetsCompat) : new e(windowInsetsCompat);
        }

        @NonNull
        public WindowInsetsCompat build() {
            return this.a.b();
        }

        @NonNull
        public Builder setDisplayCutout(@Nullable DisplayCutoutCompat displayCutoutCompat) {
            this.a.c(displayCutoutCompat);
            return this;
        }

        @NonNull
        public Builder setInsets(int i2, @NonNull Insets insets) {
            this.a.d(i2, insets);
            return this;
        }

        @NonNull
        public Builder setInsetsIgnoringVisibility(int i2, @NonNull Insets insets) {
            this.a.e(i2, insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setMandatorySystemGestureInsets(@NonNull Insets insets) {
            this.a.f(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setStableInsets(@NonNull Insets insets) {
            this.a.g(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSystemGestureInsets(@NonNull Insets insets) {
            this.a.h(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSystemWindowInsets(@NonNull Insets insets) {
            this.a.i(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setTappableElementInsets(@NonNull Insets insets) {
            this.a.j(insets);
            return this;
        }

        @NonNull
        public Builder setVisible(int i2, boolean z) {
            this.a.k(i2, z);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class Type {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes.dex */
        public @interface InsetsType {
        }

        private Type() {
        }

        @SuppressLint({"WrongConstant"})
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        static int a() {
            return -1;
        }

        static int b(int i2) {
            if (i2 == 1) {
                return 0;
            }
            if (i2 == 2) {
                return 1;
            }
            if (i2 == 4) {
                return 2;
            }
            if (i2 == 8) {
                return 3;
            }
            if (i2 == 16) {
                return 4;
            }
            if (i2 == 32) {
                return 5;
            }
            if (i2 == 64) {
                return 6;
            }
            if (i2 == 128) {
                return 7;
            }
            if (i2 == 256) {
                return 8;
            }
            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i2);
        }

        public static int captionBar() {
            return 4;
        }

        public static int displayCutout() {
            return 128;
        }

        public static int ime() {
            return 8;
        }

        public static int mandatorySystemGestures() {
            return 32;
        }

        public static int navigationBars() {
            return 2;
        }

        public static int statusBars() {
            return 1;
        }

        public static int systemBars() {
            return 7;
        }

        public static int systemGestures() {
            return 16;
        }

        public static int tappableElement() {
            return 64;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class a {
        private static Field a;
        private static Field b;

        /* renamed from: c, reason: collision with root package name */
        private static Field f644c;

        /* renamed from: d, reason: collision with root package name */
        private static boolean f645d;

        static {
            try {
                Field declaredField = View.class.getDeclaredField("mAttachInfo");
                a = declaredField;
                declaredField.setAccessible(true);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                Field declaredField2 = cls.getDeclaredField("mStableInsets");
                b = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = cls.getDeclaredField("mContentInsets");
                f644c = declaredField3;
                declaredField3.setAccessible(true);
                f645d = true;
            } catch (ReflectiveOperationException e) {
                Log.w("WindowInsetsCompat", "Failed to get visible insets from AttachInfo " + e.getMessage(), e);
            }
        }

        @Nullable
        public static WindowInsetsCompat a(@NonNull View view) {
            if (f645d && view.isAttachedToWindow()) {
                try {
                    Object obj = a.get(view.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) b.get(obj);
                        Rect rect2 = (Rect) f644c.get(obj);
                        if (rect != null && rect2 != null) {
                            WindowInsetsCompat build = new Builder().setStableInsets(Insets.of(rect)).setSystemWindowInsets(Insets.of(rect2)).build();
                            build.e(build);
                            build.a(view.getRootView());
                            return build;
                        }
                    }
                } catch (IllegalAccessException e) {
                    Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + e.getMessage(), e);
                }
            }
            return null;
        }
    }

    @RequiresApi(api = 20)
    /* loaded from: classes.dex */
    private static class b extends e {
        private static Field e = null;
        private static boolean f = false;
        private static Constructor<WindowInsets> g = null;
        private static boolean h = false;

        /* renamed from: c, reason: collision with root package name */
        private WindowInsets f646c;

        /* renamed from: d, reason: collision with root package name */
        private Insets f647d;

        b() {
            this.f646c = l();
        }

        b(@NonNull WindowInsetsCompat windowInsetsCompat) {
            this.f646c = windowInsetsCompat.toWindowInsets();
        }

        @Nullable
        private static WindowInsets l() {
            if (!f) {
                try {
                    e = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f = true;
            }
            Field field = e;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e3) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
                }
            }
            if (!h) {
                try {
                    g = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                h = true;
            }
            Constructor<WindowInsets> constructor = g;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        @NonNull
        WindowInsetsCompat b() {
            a();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.f646c);
            windowInsetsCompat.c(this.b);
            windowInsetsCompat.f(this.f647d);
            return windowInsetsCompat;
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void g(@Nullable Insets insets) {
            this.f647d = insets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void i(@NonNull Insets insets) {
            WindowInsets windowInsets = this.f646c;
            if (windowInsets != null) {
                this.f646c = windowInsets.replaceSystemWindowInsets(insets.left, insets.top, insets.right, insets.bottom);
            }
        }
    }

    @RequiresApi(api = 29)
    /* loaded from: classes.dex */
    private static class c extends e {

        /* renamed from: c, reason: collision with root package name */
        final WindowInsets.Builder f648c;

        c() {
            this.f648c = new WindowInsets.Builder();
        }

        c(@NonNull WindowInsetsCompat windowInsetsCompat) {
            WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
            this.f648c = windowInsets != null ? new WindowInsets.Builder(windowInsets) : new WindowInsets.Builder();
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        @NonNull
        WindowInsetsCompat b() {
            a();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.f648c.build());
            windowInsetsCompat.c(this.b);
            return windowInsetsCompat;
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void c(@Nullable DisplayCutoutCompat displayCutoutCompat) {
            this.f648c.setDisplayCutout(displayCutoutCompat != null ? displayCutoutCompat.b() : null);
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void f(@NonNull Insets insets) {
            this.f648c.setMandatorySystemGestureInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void g(@NonNull Insets insets) {
            this.f648c.setStableInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void h(@NonNull Insets insets) {
            this.f648c.setSystemGestureInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void i(@NonNull Insets insets) {
            this.f648c.setSystemWindowInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void j(@NonNull Insets insets) {
            this.f648c.setTappableElementInsets(insets.toPlatformInsets());
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    private static class d extends c {
        d() {
        }

        d(@NonNull WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void d(int i2, @NonNull Insets insets) {
            this.f648c.setInsets(l.a(i2), insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void e(int i2, @NonNull Insets insets) {
            this.f648c.setInsetsIgnoringVisibility(l.a(i2), insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        void k(int i2, boolean z) {
            this.f648c.setVisible(l.a(i2), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class e {
        private final WindowInsetsCompat a;
        Insets[] b;

        e() {
            this(new WindowInsetsCompat((WindowInsetsCompat) null));
        }

        e(@NonNull WindowInsetsCompat windowInsetsCompat) {
            this.a = windowInsetsCompat;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
        /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected final void a() {
            /*
                r3 = this;
                androidx.core.graphics.Insets[] r0 = r3.b
                if (r0 == 0) goto L55
                r1 = 1
                int r1 = androidx.core.view.WindowInsetsCompat.Type.b(r1)
                r0 = r0[r1]
                androidx.core.graphics.Insets[] r1 = r3.b
                r2 = 2
                int r2 = androidx.core.view.WindowInsetsCompat.Type.b(r2)
                r1 = r1[r2]
                if (r0 == 0) goto L1d
                if (r1 == 0) goto L1d
                androidx.core.graphics.Insets r0 = androidx.core.graphics.Insets.max(r0, r1)
                goto L1f
            L1d:
                if (r0 == 0) goto L23
            L1f:
                r3.i(r0)
                goto L28
            L23:
                if (r1 == 0) goto L28
                r3.i(r1)
            L28:
                androidx.core.graphics.Insets[] r0 = r3.b
                r1 = 16
                int r1 = androidx.core.view.WindowInsetsCompat.Type.b(r1)
                r0 = r0[r1]
                if (r0 == 0) goto L37
                r3.h(r0)
            L37:
                androidx.core.graphics.Insets[] r0 = r3.b
                r1 = 32
                int r1 = androidx.core.view.WindowInsetsCompat.Type.b(r1)
                r0 = r0[r1]
                if (r0 == 0) goto L46
                r3.f(r0)
            L46:
                androidx.core.graphics.Insets[] r0 = r3.b
                r1 = 64
                int r1 = androidx.core.view.WindowInsetsCompat.Type.b(r1)
                r0 = r0[r1]
                if (r0 == 0) goto L55
                r3.j(r0)
            L55:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.WindowInsetsCompat.e.a():void");
        }

        @NonNull
        WindowInsetsCompat b() {
            a();
            return this.a;
        }

        void c(@Nullable DisplayCutoutCompat displayCutoutCompat) {
        }

        void d(int i2, @NonNull Insets insets) {
            if (this.b == null) {
                this.b = new Insets[9];
            }
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    this.b[Type.b(i3)] = insets;
                }
            }
        }

        void e(int i2, @NonNull Insets insets) {
            if (i2 == 8) {
                throw new IllegalArgumentException("Ignoring visibility inset not available for IME");
            }
        }

        void f(@NonNull Insets insets) {
        }

        void g(@NonNull Insets insets) {
        }

        void h(@NonNull Insets insets) {
        }

        void i(@NonNull Insets insets) {
        }

        void j(@NonNull Insets insets) {
        }

        void k(int i2, boolean z) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(20)
    /* loaded from: classes.dex */
    public static class f extends k {
        private static boolean h = false;

        /* renamed from: i, reason: collision with root package name */
        private static Method f649i;
        private static Class<?> j;
        private static Class<?> k;
        private static Field l;
        private static Field m;

        /* renamed from: c, reason: collision with root package name */
        @NonNull
        final WindowInsets f650c;

        /* renamed from: d, reason: collision with root package name */
        private Insets[] f651d;
        private Insets e;
        private WindowInsetsCompat f;
        Insets g;

        f(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat);
            this.e = null;
            this.f650c = windowInsets;
        }

        f(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull f fVar) {
            this(windowInsetsCompat, new WindowInsets(fVar.f650c));
        }

        @SuppressLint({"PrivateApi"})
        private static void A() {
            try {
                f649i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                j = Class.forName("android.view.ViewRootImpl");
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                k = cls;
                l = cls.getDeclaredField("mVisibleInsets");
                m = j.getDeclaredField("mAttachInfo");
                l.setAccessible(true);
                m.setAccessible(true);
            } catch (ReflectiveOperationException e) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            }
            h = true;
        }

        @NonNull
        @SuppressLint({"WrongConstant"})
        private Insets v(int i2, boolean z) {
            Insets insets = Insets.NONE;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    insets = Insets.max(insets, w(i3, z));
                }
            }
            return insets;
        }

        private Insets x() {
            WindowInsetsCompat windowInsetsCompat = this.f;
            return windowInsetsCompat != null ? windowInsetsCompat.getStableInsets() : Insets.NONE;
        }

        @Nullable
        private Insets y(@NonNull View view) {
            if (Build.VERSION.SDK_INT >= 30) {
                throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            }
            if (!h) {
                A();
            }
            Method method = f649i;
            if (method != null && k != null && l != null) {
                try {
                    Object invoke = method.invoke(view, new Object[0]);
                    if (invoke == null) {
                        Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                        return null;
                    }
                    Rect rect = (Rect) l.get(m.get(invoke));
                    if (rect != null) {
                        return Insets.of(rect);
                    }
                    return null;
                } catch (ReflectiveOperationException e) {
                    Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
                }
            }
            return null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        void d(@NonNull View view) {
            Insets y = y(view);
            if (y == null) {
                y = Insets.NONE;
            }
            s(y);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        void e(@NonNull WindowInsetsCompat windowInsetsCompat) {
            windowInsetsCompat.e(this.f);
            windowInsetsCompat.d(this.g);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return defpackage.b.a(this.g, ((f) obj).g);
            }
            return false;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets g(int i2) {
            return v(i2, false);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets h(int i2) {
            return v(i2, true);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        final Insets l() {
            if (this.e == null) {
                this.e = Insets.of(this.f650c.getSystemWindowInsetLeft(), this.f650c.getSystemWindowInsetTop(), this.f650c.getSystemWindowInsetRight(), this.f650c.getSystemWindowInsetBottom());
            }
            return this.e;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        WindowInsetsCompat n(int i2, int i3, int i4, int i5) {
            Builder builder = new Builder(WindowInsetsCompat.toWindowInsetsCompat(this.f650c));
            builder.setSystemWindowInsets(WindowInsetsCompat.b(l(), i2, i3, i4, i5));
            builder.setStableInsets(WindowInsetsCompat.b(j(), i2, i3, i4, i5));
            return builder.build();
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        boolean p() {
            return this.f650c.isRound();
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @SuppressLint({"WrongConstant"})
        boolean q(int i2) {
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0 && !z(i3)) {
                    return false;
                }
            }
            return true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public void r(Insets[] insetsArr) {
            this.f651d = insetsArr;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        void s(@NonNull Insets insets) {
            this.g = insets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        void t(@Nullable WindowInsetsCompat windowInsetsCompat) {
            this.f = windowInsetsCompat;
        }

        @NonNull
        protected Insets w(int i2, boolean z) {
            Insets stableInsets;
            int i3;
            if (i2 == 1) {
                return z ? Insets.of(0, Math.max(x().top, l().top), 0, 0) : Insets.of(0, l().top, 0, 0);
            }
            if (i2 == 2) {
                if (z) {
                    Insets x = x();
                    Insets j2 = j();
                    return Insets.of(Math.max(x.left, j2.left), 0, Math.max(x.right, j2.right), Math.max(x.bottom, j2.bottom));
                }
                Insets l2 = l();
                WindowInsetsCompat windowInsetsCompat = this.f;
                stableInsets = windowInsetsCompat != null ? windowInsetsCompat.getStableInsets() : null;
                int i4 = l2.bottom;
                if (stableInsets != null) {
                    i4 = Math.min(i4, stableInsets.bottom);
                }
                return Insets.of(l2.left, 0, l2.right, i4);
            }
            if (i2 != 8) {
                if (i2 == 16) {
                    return k();
                }
                if (i2 == 32) {
                    return i();
                }
                if (i2 == 64) {
                    return m();
                }
                if (i2 != 128) {
                    return Insets.NONE;
                }
                WindowInsetsCompat windowInsetsCompat2 = this.f;
                DisplayCutoutCompat displayCutout = windowInsetsCompat2 != null ? windowInsetsCompat2.getDisplayCutout() : f();
                return displayCutout != null ? Insets.of(displayCutout.getSafeInsetLeft(), displayCutout.getSafeInsetTop(), displayCutout.getSafeInsetRight(), displayCutout.getSafeInsetBottom()) : Insets.NONE;
            }
            Insets[] insetsArr = this.f651d;
            stableInsets = insetsArr != null ? insetsArr[Type.b(8)] : null;
            if (stableInsets != null) {
                return stableInsets;
            }
            Insets l3 = l();
            Insets x2 = x();
            int i5 = l3.bottom;
            if (i5 > x2.bottom) {
                return Insets.of(0, 0, 0, i5);
            }
            Insets insets = this.g;
            return (insets == null || insets.equals(Insets.NONE) || (i3 = this.g.bottom) <= x2.bottom) ? Insets.NONE : Insets.of(0, 0, 0, i3);
        }

        protected boolean z(int i2) {
            if (i2 != 1 && i2 != 2) {
                if (i2 == 4) {
                    return false;
                }
                if (i2 != 8 && i2 != 128) {
                    return true;
                }
            }
            return !w(i2, false).equals(Insets.NONE);
        }
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    private static class g extends f {
        private Insets n;

        g(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
            this.n = null;
        }

        g(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull g gVar) {
            super(windowInsetsCompat, gVar);
            this.n = null;
            this.n = gVar.n;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        WindowInsetsCompat b() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.f650c.consumeStableInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        WindowInsetsCompat c() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.f650c.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        final Insets j() {
            if (this.n == null) {
                this.n = Insets.of(this.f650c.getStableInsetLeft(), this.f650c.getStableInsetTop(), this.f650c.getStableInsetRight(), this.f650c.getStableInsetBottom());
            }
            return this.n;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        boolean o() {
            return this.f650c.isConsumed();
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public void u(@Nullable Insets insets) {
            this.n = insets;
        }
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    private static class h extends g {
        h(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        h(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull h hVar) {
            super(windowInsetsCompat, hVar);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        WindowInsetsCompat a() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.f650c.consumeDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof h)) {
                return false;
            }
            h hVar = (h) obj;
            return defpackage.b.a(this.f650c, hVar.f650c) && defpackage.b.a(this.g, hVar.g);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @Nullable
        DisplayCutoutCompat f() {
            return DisplayCutoutCompat.c(this.f650c.getDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public int hashCode() {
            return this.f650c.hashCode();
        }
    }

    @RequiresApi(29)
    /* loaded from: classes.dex */
    private static class i extends h {
        private Insets o;
        private Insets p;
        private Insets q;

        i(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
            this.o = null;
            this.p = null;
            this.q = null;
        }

        i(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull i iVar) {
            super(windowInsetsCompat, iVar);
            this.o = null;
            this.p = null;
            this.q = null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        Insets i() {
            if (this.p == null) {
                this.p = Insets.toCompatInsets(this.f650c.getMandatorySystemGestureInsets());
            }
            return this.p;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        Insets k() {
            if (this.o == null) {
                this.o = Insets.toCompatInsets(this.f650c.getSystemGestureInsets());
            }
            return this.o;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        Insets m() {
            if (this.q == null) {
                this.q = Insets.toCompatInsets(this.f650c.getTappableElementInsets());
            }
            return this.q;
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        @NonNull
        WindowInsetsCompat n(int i2, int i3, int i4, int i5) {
            return WindowInsetsCompat.toWindowInsetsCompat(this.f650c.inset(i2, i3, i4, i5));
        }

        @Override // androidx.core.view.WindowInsetsCompat.g, androidx.core.view.WindowInsetsCompat.k
        public void u(@Nullable Insets insets) {
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    private static class j extends i {

        @NonNull
        static final WindowInsetsCompat r = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);

        j(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        j(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull j jVar) {
            super(windowInsetsCompat, jVar);
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        final void d(@NonNull View view) {
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets g(int i2) {
            return Insets.toCompatInsets(this.f650c.getInsets(l.a(i2)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets h(int i2) {
            return Insets.toCompatInsets(this.f650c.getInsetsIgnoringVisibility(l.a(i2)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        public boolean q(int i2) {
            return this.f650c.isVisible(l.a(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class k {

        @NonNull
        static final WindowInsetsCompat b = new Builder().build().consumeDisplayCutout().consumeStableInsets().consumeSystemWindowInsets();
        final WindowInsetsCompat a;

        k(@NonNull WindowInsetsCompat windowInsetsCompat) {
            this.a = windowInsetsCompat;
        }

        @NonNull
        WindowInsetsCompat a() {
            return this.a;
        }

        @NonNull
        WindowInsetsCompat b() {
            return this.a;
        }

        @NonNull
        WindowInsetsCompat c() {
            return this.a;
        }

        void d(@NonNull View view) {
        }

        void e(@NonNull WindowInsetsCompat windowInsetsCompat) {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof k)) {
                return false;
            }
            k kVar = (k) obj;
            return p() == kVar.p() && o() == kVar.o() && ObjectsCompat.equals(l(), kVar.l()) && ObjectsCompat.equals(j(), kVar.j()) && ObjectsCompat.equals(f(), kVar.f());
        }

        @Nullable
        DisplayCutoutCompat f() {
            return null;
        }

        @NonNull
        Insets g(int i2) {
            return Insets.NONE;
        }

        @NonNull
        Insets h(int i2) {
            if ((i2 & 8) == 0) {
                return Insets.NONE;
            }
            throw new IllegalArgumentException("Unable to query the maximum insets for IME");
        }

        public int hashCode() {
            return ObjectsCompat.hash(Boolean.valueOf(p()), Boolean.valueOf(o()), l(), j(), f());
        }

        @NonNull
        Insets i() {
            return l();
        }

        @NonNull
        Insets j() {
            return Insets.NONE;
        }

        @NonNull
        Insets k() {
            return l();
        }

        @NonNull
        Insets l() {
            return Insets.NONE;
        }

        @NonNull
        Insets m() {
            return l();
        }

        @NonNull
        WindowInsetsCompat n(int i2, int i3, int i4, int i5) {
            return b;
        }

        boolean o() {
            return false;
        }

        boolean p() {
            return false;
        }

        boolean q(int i2) {
            return true;
        }

        public void r(Insets[] insetsArr) {
        }

        void s(@NonNull Insets insets) {
        }

        void t(@Nullable WindowInsetsCompat windowInsetsCompat) {
        }

        public void u(Insets insets) {
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    private static final class l {
        static int a(int i2) {
            int statusBars;
            int i3 = 0;
            for (int i4 = 1; i4 <= 256; i4 <<= 1) {
                if ((i2 & i4) != 0) {
                    if (i4 == 1) {
                        statusBars = WindowInsets.Type.statusBars();
                    } else if (i4 == 2) {
                        statusBars = WindowInsets.Type.navigationBars();
                    } else if (i4 == 4) {
                        statusBars = WindowInsets.Type.captionBar();
                    } else if (i4 == 8) {
                        statusBars = WindowInsets.Type.ime();
                    } else if (i4 == 16) {
                        statusBars = WindowInsets.Type.systemGestures();
                    } else if (i4 == 32) {
                        statusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i4 == 64) {
                        statusBars = WindowInsets.Type.tappableElement();
                    } else if (i4 == 128) {
                        statusBars = WindowInsets.Type.displayCutout();
                    }
                    i3 |= statusBars;
                }
            }
            return i3;
        }
    }

    static {
        CONSUMED = Build.VERSION.SDK_INT >= 30 ? j.r : k.b;
    }

    @RequiresApi(20)
    private WindowInsetsCompat(@NonNull WindowInsets windowInsets) {
        k fVar;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            fVar = new j(this, windowInsets);
        } else if (i2 >= 29) {
            fVar = new i(this, windowInsets);
        } else if (i2 >= 28) {
            fVar = new h(this, windowInsets);
        } else if (i2 >= 21) {
            fVar = new g(this, windowInsets);
        } else {
            if (i2 < 20) {
                this.a = new k(this);
                return;
            }
            fVar = new f(this, windowInsets);
        }
        this.a = fVar;
    }

    public WindowInsetsCompat(@Nullable WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat == null) {
            this.a = new k(this);
            return;
        }
        k kVar = windowInsetsCompat.a;
        int i2 = Build.VERSION.SDK_INT;
        this.a = (i2 < 30 || !(kVar instanceof j)) ? (i2 < 29 || !(kVar instanceof i)) ? (i2 < 28 || !(kVar instanceof h)) ? (i2 < 21 || !(kVar instanceof g)) ? (i2 < 20 || !(kVar instanceof f)) ? new k(this) : new f(this, (f) kVar) : new g(this, (g) kVar) : new h(this, (h) kVar) : new i(this, (i) kVar) : new j(this, (j) kVar);
        kVar.e(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Insets b(@NonNull Insets insets, int i2, int i3, int i4, int i5) {
        int max = Math.max(0, insets.left - i2);
        int max2 = Math.max(0, insets.top - i3);
        int max3 = Math.max(0, insets.right - i4);
        int max4 = Math.max(0, insets.bottom - i5);
        return (max == i2 && max2 == i3 && max3 == i4 && max4 == i5) ? insets : Insets.of(max, max2, max3, max4);
    }

    @NonNull
    @RequiresApi(20)
    public static WindowInsetsCompat toWindowInsetsCompat(@NonNull WindowInsets windowInsets) {
        return toWindowInsetsCompat(windowInsets, null);
    }

    @NonNull
    @RequiresApi(20)
    public static WindowInsetsCompat toWindowInsetsCompat(@NonNull WindowInsets windowInsets, @Nullable View view) {
        WindowInsetsCompat windowInsetsCompat = new WindowInsetsCompat((WindowInsets) Preconditions.checkNotNull(windowInsets));
        if (view != null && view.isAttachedToWindow()) {
            windowInsetsCompat.e(ViewCompat.getRootWindowInsets(view));
            windowInsetsCompat.a(view.getRootView());
        }
        return windowInsetsCompat;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@NonNull View view) {
        this.a.d(view);
    }

    void c(Insets[] insetsArr) {
        this.a.r(insetsArr);
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeDisplayCutout() {
        return this.a.a();
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeStableInsets() {
        return this.a.b();
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return this.a.c();
    }

    void d(@NonNull Insets insets) {
        this.a.s(insets);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@Nullable WindowInsetsCompat windowInsetsCompat) {
        this.a.t(windowInsetsCompat);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WindowInsetsCompat) {
            return ObjectsCompat.equals(this.a, ((WindowInsetsCompat) obj).a);
        }
        return false;
    }

    void f(@Nullable Insets insets) {
        this.a.u(insets);
    }

    @Nullable
    public DisplayCutoutCompat getDisplayCutout() {
        return this.a.f();
    }

    @NonNull
    public Insets getInsets(int i2) {
        return this.a.g(i2);
    }

    @NonNull
    public Insets getInsetsIgnoringVisibility(int i2) {
        return this.a.h(i2);
    }

    @NonNull
    @Deprecated
    public Insets getMandatorySystemGestureInsets() {
        return this.a.i();
    }

    @Deprecated
    public int getStableInsetBottom() {
        return this.a.j().bottom;
    }

    @Deprecated
    public int getStableInsetLeft() {
        return this.a.j().left;
    }

    @Deprecated
    public int getStableInsetRight() {
        return this.a.j().right;
    }

    @Deprecated
    public int getStableInsetTop() {
        return this.a.j().top;
    }

    @NonNull
    @Deprecated
    public Insets getStableInsets() {
        return this.a.j();
    }

    @NonNull
    @Deprecated
    public Insets getSystemGestureInsets() {
        return this.a.k();
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return this.a.l().bottom;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return this.a.l().left;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return this.a.l().right;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return this.a.l().top;
    }

    @NonNull
    @Deprecated
    public Insets getSystemWindowInsets() {
        return this.a.l();
    }

    @NonNull
    @Deprecated
    public Insets getTappableElementInsets() {
        return this.a.m();
    }

    public boolean hasInsets() {
        Insets insets = getInsets(Type.a());
        Insets insets2 = Insets.NONE;
        return (insets.equals(insets2) && getInsetsIgnoringVisibility(Type.a() ^ Type.ime()).equals(insets2) && getDisplayCutout() == null) ? false : true;
    }

    @Deprecated
    public boolean hasStableInsets() {
        return !this.a.j().equals(Insets.NONE);
    }

    @Deprecated
    public boolean hasSystemWindowInsets() {
        return !this.a.l().equals(Insets.NONE);
    }

    public int hashCode() {
        k kVar = this.a;
        if (kVar == null) {
            return 0;
        }
        return kVar.hashCode();
    }

    @NonNull
    public WindowInsetsCompat inset(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3, @IntRange(from = 0) int i4, @IntRange(from = 0) int i5) {
        return this.a.n(i2, i3, i4, i5);
    }

    @NonNull
    public WindowInsetsCompat inset(@NonNull Insets insets) {
        return inset(insets.left, insets.top, insets.right, insets.bottom);
    }

    public boolean isConsumed() {
        return this.a.o();
    }

    public boolean isRound() {
        return this.a.p();
    }

    public boolean isVisible(int i2) {
        return this.a.q(i2);
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(int i2, int i3, int i4, int i5) {
        return new Builder(this).setSystemWindowInsets(Insets.of(i2, i3, i4, i5)).build();
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(@NonNull Rect rect) {
        return new Builder(this).setSystemWindowInsets(Insets.of(rect)).build();
    }

    @Nullable
    @RequiresApi(20)
    public WindowInsets toWindowInsets() {
        k kVar = this.a;
        if (kVar instanceof f) {
            return ((f) kVar).f650c;
        }
        return null;
    }
}
