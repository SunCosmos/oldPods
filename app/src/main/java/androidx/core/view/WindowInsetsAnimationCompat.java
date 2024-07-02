package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class WindowInsetsAnimationCompat {
    private c a;

    /* loaded from: classes.dex */
    public static final class BoundsCompat {
        private final Insets a;
        private final Insets b;

        @RequiresApi(30)
        private BoundsCompat(@NonNull WindowInsetsAnimation.Bounds bounds) {
            this.a = b.k(bounds);
            this.b = b.j(bounds);
        }

        public BoundsCompat(@NonNull Insets insets, @NonNull Insets insets2) {
            this.a = insets;
            this.b = insets2;
        }

        @NonNull
        @RequiresApi(30)
        public static BoundsCompat toBoundsCompat(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return new BoundsCompat(bounds);
        }

        @NonNull
        public Insets getLowerBound() {
            return this.a;
        }

        @NonNull
        public Insets getUpperBound() {
            return this.b;
        }

        @NonNull
        public BoundsCompat inset(@NonNull Insets insets) {
            return new BoundsCompat(WindowInsetsCompat.b(this.a, insets.left, insets.top, insets.right, insets.bottom), WindowInsetsCompat.b(this.b, insets.left, insets.top, insets.right, insets.bottom));
        }

        @NonNull
        @RequiresApi(30)
        public WindowInsetsAnimation.Bounds toBounds() {
            return b.i(this);
        }

        public String toString() {
            return "Bounds{lower=" + this.a + " upper=" + this.b + "}";
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
        public static final int DISPATCH_MODE_STOP = 0;
        WindowInsets a;
        private final int b;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes.dex */
        public @interface DispatchMode {
        }

        public Callback(int i2) {
            this.b = i2;
        }

        public final int getDispatchMode() {
            return this.b;
        }

        public void onEnd(@NonNull WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }

        public void onPrepare(@NonNull WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }

        @NonNull
        public abstract WindowInsetsCompat onProgress(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull List<WindowInsetsAnimationCompat> list);

        @NonNull
        public BoundsCompat onStart(@NonNull WindowInsetsAnimationCompat windowInsetsAnimationCompat, @NonNull BoundsCompat boundsCompat) {
            return boundsCompat;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class a extends c {

        /* JADX INFO: Access modifiers changed from: private */
        @RequiresApi(21)
        /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class ViewOnApplyWindowInsetsListenerC0022a implements View.OnApplyWindowInsetsListener {
            final Callback a;
            private WindowInsetsCompat b;

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$a$a$a, reason: collision with other inner class name */
            /* loaded from: classes.dex */
            class C0023a implements ValueAnimator.AnimatorUpdateListener {
                final /* synthetic */ WindowInsetsAnimationCompat a;
                final /* synthetic */ WindowInsetsCompat b;

                /* renamed from: c, reason: collision with root package name */
                final /* synthetic */ WindowInsetsCompat f636c;

                /* renamed from: d, reason: collision with root package name */
                final /* synthetic */ int f637d;
                final /* synthetic */ View e;

                C0023a(ViewOnApplyWindowInsetsListenerC0022a viewOnApplyWindowInsetsListenerC0022a, WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, int i2, View view) {
                    this.a = windowInsetsAnimationCompat;
                    this.b = windowInsetsCompat;
                    this.f636c = windowInsetsCompat2;
                    this.f637d = i2;
                    this.e = view;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.a.setFraction(valueAnimator.getAnimatedFraction());
                    a.n(this.e, a.r(this.b, this.f636c, this.a.getInterpolatedFraction(), this.f637d), Collections.singletonList(this.a));
                }
            }

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$a$a$b */
            /* loaded from: classes.dex */
            class b extends AnimatorListenerAdapter {
                final /* synthetic */ WindowInsetsAnimationCompat a;
                final /* synthetic */ View b;

                b(ViewOnApplyWindowInsetsListenerC0022a viewOnApplyWindowInsetsListenerC0022a, WindowInsetsAnimationCompat windowInsetsAnimationCompat, View view) {
                    this.a = windowInsetsAnimationCompat;
                    this.b = view;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    this.a.setFraction(1.0f);
                    a.l(this.b, this.a);
                }
            }

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$a$a$c */
            /* loaded from: classes.dex */
            class c implements Runnable {
                final /* synthetic */ View a;
                final /* synthetic */ WindowInsetsAnimationCompat b;

                /* renamed from: c, reason: collision with root package name */
                final /* synthetic */ BoundsCompat f638c;

                /* renamed from: d, reason: collision with root package name */
                final /* synthetic */ ValueAnimator f639d;

                c(ViewOnApplyWindowInsetsListenerC0022a viewOnApplyWindowInsetsListenerC0022a, View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat, ValueAnimator valueAnimator) {
                    this.a = view;
                    this.b = windowInsetsAnimationCompat;
                    this.f638c = boundsCompat;
                    this.f639d = valueAnimator;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.o(this.a, this.b, this.f638c);
                    this.f639d.start();
                }
            }

            ViewOnApplyWindowInsetsListenerC0022a(@NonNull View view, @NonNull Callback callback) {
                this.a = callback;
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
                this.b = rootWindowInsets != null ? new WindowInsetsCompat.Builder(rootWindowInsets).build() : null;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                int i2;
                if (view.isLaidOut()) {
                    WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                    if (this.b == null) {
                        this.b = ViewCompat.getRootWindowInsets(view);
                    }
                    if (this.b != null) {
                        Callback q = a.q(view);
                        if ((q == null || !defpackage.b.a(q.a, windowInsets)) && (i2 = a.i(windowInsetsCompat, this.b)) != 0) {
                            WindowInsetsCompat windowInsetsCompat2 = this.b;
                            WindowInsetsAnimationCompat windowInsetsAnimationCompat = new WindowInsetsAnimationCompat(i2, new DecelerateInterpolator(), 160L);
                            windowInsetsAnimationCompat.setFraction(0.0f);
                            ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(windowInsetsAnimationCompat.getDurationMillis());
                            BoundsCompat j = a.j(windowInsetsCompat, windowInsetsCompat2, i2);
                            a.m(view, windowInsetsAnimationCompat, windowInsets, false);
                            duration.addUpdateListener(new C0023a(this, windowInsetsAnimationCompat, windowInsetsCompat, windowInsetsCompat2, i2, view));
                            duration.addListener(new b(this, windowInsetsAnimationCompat, view));
                            OneShotPreDrawListener.add(view, new c(this, view, windowInsetsAnimationCompat, j, duration));
                        }
                        return a.p(view, windowInsets);
                    }
                    this.b = windowInsetsCompat;
                } else {
                    this.b = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                }
                return a.p(view, windowInsets);
            }
        }

        a(int i2, @Nullable Interpolator interpolator, long j) {
            super(i2, interpolator, j);
        }

        @SuppressLint({"WrongConstant"})
        static int i(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsetsCompat windowInsetsCompat2) {
            int i2 = 0;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if (!windowInsetsCompat.getInsets(i3).equals(windowInsetsCompat2.getInsets(i3))) {
                    i2 |= i3;
                }
            }
            return i2;
        }

        @NonNull
        static BoundsCompat j(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsetsCompat windowInsetsCompat2, int i2) {
            Insets insets = windowInsetsCompat.getInsets(i2);
            Insets insets2 = windowInsetsCompat2.getInsets(i2);
            return new BoundsCompat(Insets.of(Math.min(insets.left, insets2.left), Math.min(insets.top, insets2.top), Math.min(insets.right, insets2.right), Math.min(insets.bottom, insets2.bottom)), Insets.of(Math.max(insets.left, insets2.left), Math.max(insets.top, insets2.top), Math.max(insets.right, insets2.right), Math.max(insets.bottom, insets2.bottom)));
        }

        @NonNull
        private static View.OnApplyWindowInsetsListener k(@NonNull View view, @NonNull Callback callback) {
            return new ViewOnApplyWindowInsetsListenerC0022a(view, callback);
        }

        static void l(@NonNull View view, @NonNull WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
            Callback q = q(view);
            if (q != null) {
                q.onEnd(windowInsetsAnimationCompat);
                if (q.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    l(viewGroup.getChildAt(i2), windowInsetsAnimationCompat);
                }
            }
        }

        static void m(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsets windowInsets, boolean z) {
            Callback q = q(view);
            if (q != null) {
                q.a = windowInsets;
                if (!z) {
                    q.onPrepare(windowInsetsAnimationCompat);
                    z = q.getDispatchMode() == 0;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    m(viewGroup.getChildAt(i2), windowInsetsAnimationCompat, windowInsets, z);
                }
            }
        }

        static void n(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull List<WindowInsetsAnimationCompat> list) {
            Callback q = q(view);
            if (q != null) {
                windowInsetsCompat = q.onProgress(windowInsetsCompat, list);
                if (q.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    n(viewGroup.getChildAt(i2), windowInsetsCompat, list);
                }
            }
        }

        static void o(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat) {
            Callback q = q(view);
            if (q != null) {
                q.onStart(windowInsetsAnimationCompat, boundsCompat);
                if (q.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    o(viewGroup.getChildAt(i2), windowInsetsAnimationCompat, boundsCompat);
                }
            }
        }

        @NonNull
        static WindowInsets p(@NonNull View view, @NonNull WindowInsets windowInsets) {
            return view.getTag(R.id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
        }

        @Nullable
        static Callback q(View view) {
            Object tag = view.getTag(R.id.tag_window_insets_animation_callback);
            if (tag instanceof ViewOnApplyWindowInsetsListenerC0022a) {
                return ((ViewOnApplyWindowInsetsListenerC0022a) tag).a;
            }
            return null;
        }

        @SuppressLint({"WrongConstant"})
        static WindowInsetsCompat r(WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, float f, int i2) {
            Insets b;
            WindowInsetsCompat.Builder builder = new WindowInsetsCompat.Builder(windowInsetsCompat);
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) == 0) {
                    b = windowInsetsCompat.getInsets(i3);
                } else {
                    Insets insets = windowInsetsCompat.getInsets(i3);
                    Insets insets2 = windowInsetsCompat2.getInsets(i3);
                    float f2 = 1.0f - f;
                    double d2 = (insets.left - insets2.left) * f2;
                    Double.isNaN(d2);
                    int i4 = (int) (d2 + 0.5d);
                    double d3 = (insets.top - insets2.top) * f2;
                    Double.isNaN(d3);
                    double d4 = (insets.right - insets2.right) * f2;
                    Double.isNaN(d4);
                    int i5 = (int) (d4 + 0.5d);
                    double d5 = (insets.bottom - insets2.bottom) * f2;
                    Double.isNaN(d5);
                    b = WindowInsetsCompat.b(insets, i4, (int) (d3 + 0.5d), i5, (int) (d5 + 0.5d));
                }
                builder.setInsets(i3, b);
            }
            return builder.build();
        }

        static void s(@NonNull View view, @Nullable Callback callback) {
            Object tag = view.getTag(R.id.tag_on_apply_window_listener);
            if (callback == null) {
                view.setTag(R.id.tag_window_insets_animation_callback, null);
                if (tag == null) {
                    view.setOnApplyWindowInsetsListener(null);
                    return;
                }
                return;
            }
            View.OnApplyWindowInsetsListener k = k(view, callback);
            view.setTag(R.id.tag_window_insets_animation_callback, k);
            if (tag == null) {
                view.setOnApplyWindowInsetsListener(k);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class b extends c {

        @NonNull
        private final WindowInsetsAnimation f;

        /* JADX INFO: Access modifiers changed from: private */
        @RequiresApi(30)
        /* loaded from: classes.dex */
        public static class a extends WindowInsetsAnimation.Callback {
            private final Callback a;
            private List<WindowInsetsAnimationCompat> b;

            /* renamed from: c, reason: collision with root package name */
            private ArrayList<WindowInsetsAnimationCompat> f640c;

            /* renamed from: d, reason: collision with root package name */
            private final HashMap<WindowInsetsAnimation, WindowInsetsAnimationCompat> f641d;

            a(@NonNull Callback callback) {
                super(callback.getDispatchMode());
                this.f641d = new HashMap<>();
                this.a = callback;
            }

            @NonNull
            private WindowInsetsAnimationCompat a(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat = this.f641d.get(windowInsetsAnimation);
                if (windowInsetsAnimationCompat != null) {
                    return windowInsetsAnimationCompat;
                }
                WindowInsetsAnimationCompat b = WindowInsetsAnimationCompat.b(windowInsetsAnimation);
                this.f641d.put(windowInsetsAnimation, b);
                return b;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onEnd(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                this.a.onEnd(a(windowInsetsAnimation));
                this.f641d.remove(windowInsetsAnimation);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onPrepare(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                this.a.onPrepare(a(windowInsetsAnimation));
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            @NonNull
            public WindowInsets onProgress(@NonNull WindowInsets windowInsets, @NonNull List<WindowInsetsAnimation> list) {
                ArrayList<WindowInsetsAnimationCompat> arrayList = this.f640c;
                if (arrayList == null) {
                    ArrayList<WindowInsetsAnimationCompat> arrayList2 = new ArrayList<>(list.size());
                    this.f640c = arrayList2;
                    this.b = Collections.unmodifiableList(arrayList2);
                } else {
                    arrayList.clear();
                }
                for (int size = list.size() - 1; size >= 0; size--) {
                    WindowInsetsAnimation windowInsetsAnimation = list.get(size);
                    WindowInsetsAnimationCompat a = a(windowInsetsAnimation);
                    a.setFraction(windowInsetsAnimation.getFraction());
                    this.f640c.add(a);
                }
                return this.a.onProgress(WindowInsetsCompat.toWindowInsetsCompat(windowInsets), this.b).toWindowInsets();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            @NonNull
            public WindowInsetsAnimation.Bounds onStart(@NonNull WindowInsetsAnimation windowInsetsAnimation, @NonNull WindowInsetsAnimation.Bounds bounds) {
                return this.a.onStart(a(windowInsetsAnimation), BoundsCompat.toBoundsCompat(bounds)).toBounds();
            }
        }

        b(int i2, Interpolator interpolator, long j) {
            this(new WindowInsetsAnimation(i2, interpolator, j));
        }

        b(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
            super(0, null, 0L);
            this.f = windowInsetsAnimation;
        }

        @NonNull
        public static WindowInsetsAnimation.Bounds i(@NonNull BoundsCompat boundsCompat) {
            return new WindowInsetsAnimation.Bounds(boundsCompat.getLowerBound().toPlatformInsets(), boundsCompat.getUpperBound().toPlatformInsets());
        }

        @NonNull
        public static Insets j(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getUpperBound());
        }

        @NonNull
        public static Insets k(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getLowerBound());
        }

        public static void l(@NonNull View view, @Nullable Callback callback) {
            view.setWindowInsetsAnimationCallback(callback != null ? new a(callback) : null);
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public long b() {
            return this.f.getDurationMillis();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public float c() {
            return this.f.getFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public float d() {
            return this.f.getInterpolatedFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        @Nullable
        public Interpolator e() {
            return this.f.getInterpolator();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public int f() {
            return this.f.getTypeMask();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public void h(float f) {
            this.f.setFraction(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {
        private final int a;
        private float b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        private final Interpolator f642c;

        /* renamed from: d, reason: collision with root package name */
        private final long f643d;
        private float e;

        c(int i2, @Nullable Interpolator interpolator, long j) {
            this.a = i2;
            this.f642c = interpolator;
            this.f643d = j;
        }

        public float a() {
            return this.e;
        }

        public long b() {
            return this.f643d;
        }

        public float c() {
            return this.b;
        }

        public float d() {
            Interpolator interpolator = this.f642c;
            return interpolator != null ? interpolator.getInterpolation(this.b) : this.b;
        }

        @Nullable
        public Interpolator e() {
            return this.f642c;
        }

        public int f() {
            return this.a;
        }

        public void g(float f) {
            this.e = f;
        }

        public void h(float f) {
            this.b = f;
        }
    }

    public WindowInsetsAnimationCompat(int i2, @Nullable Interpolator interpolator, long j) {
        c aVar;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 30) {
            aVar = new b(i2, interpolator, j);
        } else {
            if (i3 < 21) {
                this.a = new c(0, interpolator, j);
                return;
            }
            aVar = new a(i2, interpolator, j);
        }
        this.a = aVar;
    }

    @RequiresApi(30)
    private WindowInsetsAnimationCompat(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
        this(0, null, 0L);
        if (Build.VERSION.SDK_INT >= 30) {
            this.a = new b(windowInsetsAnimation);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull View view, @Nullable Callback callback) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            b.l(view, callback);
        } else if (i2 >= 21) {
            a.s(view, callback);
        }
    }

    @RequiresApi(30)
    static WindowInsetsAnimationCompat b(WindowInsetsAnimation windowInsetsAnimation) {
        return new WindowInsetsAnimationCompat(windowInsetsAnimation);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAlpha() {
        return this.a.a();
    }

    public long getDurationMillis() {
        return this.a.b();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getFraction() {
        return this.a.c();
    }

    public float getInterpolatedFraction() {
        return this.a.d();
    }

    @Nullable
    public Interpolator getInterpolator() {
        return this.a.e();
    }

    public int getTypeMask() {
        return this.a.f();
    }

    public void setAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.a.g(f);
    }

    public void setFraction(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.a.h(f);
    }
}
