package androidx.core.view;

import android.R;
import android.os.Build;
import android.os.CancellationSignal;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;

/* loaded from: classes.dex */
public final class WindowInsetsControllerCompat {
    public static final int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;
    public static final int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;
    public static final int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;
    private final e a;

    /* loaded from: classes.dex */
    public interface OnControllableInsetsChangedListener {
        void onControllableInsetsChanged(@NonNull WindowInsetsControllerCompat windowInsetsControllerCompat, int i2);
    }

    @RequiresApi(20)
    /* loaded from: classes.dex */
    private static class a extends e {

        @NonNull
        protected final Window a;

        @Nullable
        private final View b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: androidx.core.view.WindowInsetsControllerCompat$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0024a implements Runnable {
            final /* synthetic */ View a;

            RunnableC0024a(a aVar, View view) {
                this.a = view;
            }

            @Override // java.lang.Runnable
            public void run() {
                ((InputMethodManager) this.a.getContext().getSystemService("input_method")).showSoftInput(this.a, 0);
            }
        }

        a(@NonNull Window window, @Nullable View view) {
            this.a = window;
            this.b = view;
        }

        private void l(int i2) {
            if (i2 == 1) {
                m(4);
            } else if (i2 == 2) {
                m(2);
            } else {
                if (i2 != 8) {
                    return;
                }
                ((InputMethodManager) this.a.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.a.getDecorView().getWindowToken(), 0);
            }
        }

        private void o(int i2) {
            if (i2 == 1) {
                p(4);
                q(1024);
                return;
            }
            if (i2 == 2) {
                p(2);
                return;
            }
            if (i2 != 8) {
                return;
            }
            View view = this.b;
            if (view == null || !(view.isInEditMode() || view.onCheckIsTextEditor())) {
                view = this.a.getCurrentFocus();
            } else {
                view.requestFocus();
            }
            if (view == null) {
                view = this.a.findViewById(R.id.content);
            }
            if (view == null || !view.hasWindowFocus()) {
                return;
            }
            view.post(new RunnableC0024a(this, view));
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void a(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void b(int i2, long j, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        int c() {
            return 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void d(int i2) {
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    l(i3);
                }
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void g(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void j(int i2) {
            if (i2 == 0) {
                p(6144);
                return;
            }
            if (i2 == 1) {
                p(4096);
                m(2048);
            } else {
                if (i2 != 2) {
                    return;
                }
                p(2048);
                m(4096);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void k(int i2) {
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    o(i3);
                }
            }
        }

        protected void m(int i2) {
            View decorView = this.a.getDecorView();
            decorView.setSystemUiVisibility(i2 | decorView.getSystemUiVisibility());
        }

        protected void n(int i2) {
            this.a.addFlags(i2);
        }

        protected void p(int i2) {
            View decorView = this.a.getDecorView();
            decorView.setSystemUiVisibility((i2 ^ (-1)) & decorView.getSystemUiVisibility());
        }

        protected void q(int i2) {
            this.a.clearFlags(i2);
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    private static class b extends a {
        b(@NonNull Window window, @Nullable View view) {
            super(window, view);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public boolean f() {
            return (this.a.getDecorView().getSystemUiVisibility() & 8192) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void i(boolean z) {
            if (!z) {
                p(8192);
                return;
            }
            q(67108864);
            n(Integer.MIN_VALUE);
            m(8192);
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    private static class c extends b {
        c(@NonNull Window window, @Nullable View view) {
            super(window, view);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public boolean e() {
            return (this.a.getDecorView().getSystemUiVisibility() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void h(boolean z) {
            if (!z) {
                p(16);
                return;
            }
            q(134217728);
            n(Integer.MIN_VALUE);
            m(16);
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    private static class d extends e {
        final WindowInsetsControllerCompat a;
        final WindowInsetsController b;

        /* renamed from: c, reason: collision with root package name */
        private final SimpleArrayMap<OnControllableInsetsChangedListener, WindowInsetsController.OnControllableInsetsChangedListener> f652c;

        /* loaded from: classes.dex */
        class a implements WindowInsetsAnimationControlListener {
            private WindowInsetsAnimationControllerCompat a = null;
            final /* synthetic */ WindowInsetsAnimationControlListenerCompat b;

            a(d dVar, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
                this.b = windowInsetsAnimationControlListenerCompat;
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onCancelled(@Nullable WindowInsetsAnimationController windowInsetsAnimationController) {
                this.b.onCancelled(windowInsetsAnimationController == null ? null : this.a);
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onFinished(@NonNull WindowInsetsAnimationController windowInsetsAnimationController) {
                this.b.onFinished(this.a);
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onReady(@NonNull WindowInsetsAnimationController windowInsetsAnimationController, int i2) {
                WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = new WindowInsetsAnimationControllerCompat(windowInsetsAnimationController);
                this.a = windowInsetsAnimationControllerCompat;
                this.b.onReady(windowInsetsAnimationControllerCompat, i2);
            }
        }

        /* loaded from: classes.dex */
        class b implements WindowInsetsController.OnControllableInsetsChangedListener {
            final /* synthetic */ OnControllableInsetsChangedListener a;

            b(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
                this.a = onControllableInsetsChangedListener;
            }

            @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
            public void onControllableInsetsChanged(@NonNull WindowInsetsController windowInsetsController, int i2) {
                d dVar = d.this;
                if (dVar.b == windowInsetsController) {
                    this.a.onControllableInsetsChanged(dVar.a, i2);
                }
            }
        }

        d(@NonNull Window window, @NonNull WindowInsetsControllerCompat windowInsetsControllerCompat) {
            this(window.getInsetsController(), windowInsetsControllerCompat);
        }

        d(@NonNull WindowInsetsController windowInsetsController, @NonNull WindowInsetsControllerCompat windowInsetsControllerCompat) {
            this.f652c = new SimpleArrayMap<>();
            this.b = windowInsetsController;
            this.a = windowInsetsControllerCompat;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void a(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
            if (this.f652c.containsKey(onControllableInsetsChangedListener)) {
                return;
            }
            b bVar = new b(onControllableInsetsChangedListener);
            this.f652c.put(onControllableInsetsChangedListener, bVar);
            this.b.addOnControllableInsetsChangedListener(bVar);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void b(int i2, long j, @Nullable Interpolator interpolator, @Nullable CancellationSignal cancellationSignal, @NonNull WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
            this.b.controlWindowInsetsAnimation(i2, j, interpolator, cancellationSignal, new a(this, windowInsetsAnimationControlListenerCompat));
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        int c() {
            return this.b.getSystemBarsBehavior();
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void d(int i2) {
            this.b.hide(i2);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public boolean e() {
            return (this.b.getSystemBarsAppearance() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public boolean f() {
            return (this.b.getSystemBarsAppearance() & 8) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void g(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
            WindowInsetsController.OnControllableInsetsChangedListener remove = this.f652c.remove(onControllableInsetsChangedListener);
            if (remove != null) {
                this.b.removeOnControllableInsetsChangedListener(remove);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void h(boolean z) {
            if (z) {
                this.b.setSystemBarsAppearance(16, 16);
            } else {
                this.b.setSystemBarsAppearance(0, 16);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void i(boolean z) {
            if (z) {
                this.b.setSystemBarsAppearance(8, 8);
            } else {
                this.b.setSystemBarsAppearance(0, 8);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void j(int i2) {
            this.b.setSystemBarsBehavior(i2);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        void k(int i2) {
            this.b.show(i2);
        }
    }

    /* loaded from: classes.dex */
    private static class e {
        e() {
        }

        void a(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        void b(int i2, long j, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        }

        int c() {
            return 0;
        }

        void d(int i2) {
        }

        public boolean e() {
            return false;
        }

        public boolean f() {
            return false;
        }

        void g(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        public void h(boolean z) {
        }

        public void i(boolean z) {
        }

        void j(int i2) {
        }

        void k(int i2) {
        }
    }

    public WindowInsetsControllerCompat(@NonNull Window window, @NonNull View view) {
        e aVar;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.a = new d(window, this);
            return;
        }
        if (i2 >= 26) {
            aVar = new c(window, view);
        } else if (i2 >= 23) {
            aVar = new b(window, view);
        } else {
            if (i2 < 20) {
                this.a = new e();
                return;
            }
            aVar = new a(window, view);
        }
        this.a = aVar;
    }

    @RequiresApi(30)
    private WindowInsetsControllerCompat(@NonNull WindowInsetsController windowInsetsController) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.a = new d(windowInsetsController, this);
        } else {
            this.a = new e();
        }
    }

    @NonNull
    @RequiresApi(30)
    public static WindowInsetsControllerCompat toWindowInsetsControllerCompat(@NonNull WindowInsetsController windowInsetsController) {
        return new WindowInsetsControllerCompat(windowInsetsController);
    }

    public void addOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        this.a.a(onControllableInsetsChangedListener);
    }

    public void controlWindowInsetsAnimation(int i2, long j, @Nullable Interpolator interpolator, @Nullable CancellationSignal cancellationSignal, @NonNull WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        this.a.b(i2, j, interpolator, cancellationSignal, windowInsetsAnimationControlListenerCompat);
    }

    public int getSystemBarsBehavior() {
        return this.a.c();
    }

    public void hide(int i2) {
        this.a.d(i2);
    }

    public boolean isAppearanceLightNavigationBars() {
        return this.a.e();
    }

    public boolean isAppearanceLightStatusBars() {
        return this.a.f();
    }

    public void removeOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        this.a.g(onControllableInsetsChangedListener);
    }

    public void setAppearanceLightNavigationBars(boolean z) {
        this.a.h(z);
    }

    public void setAppearanceLightStatusBars(boolean z) {
        this.a.i(z);
    }

    public void setSystemBarsBehavior(int i2) {
        this.a.j(i2);
    }

    public void show(int i2) {
        this.a.k(i2);
    }
}
