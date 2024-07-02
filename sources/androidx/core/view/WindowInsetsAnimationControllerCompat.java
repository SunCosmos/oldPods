package androidx.core.view;

import android.os.Build;
import android.view.WindowInsetsAnimationController;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.Insets;

/* loaded from: classes.dex */
public final class WindowInsetsAnimationControllerCompat {
    private final b a;

    @RequiresApi(30)
    /* loaded from: classes.dex */
    private static class a extends b {
        private final WindowInsetsAnimationController a;

        a(@NonNull WindowInsetsAnimationController windowInsetsAnimationController) {
            this.a = windowInsetsAnimationController;
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        void a(boolean z) {
            this.a.finish(z);
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public float b() {
            return this.a.getCurrentAlpha();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public float c() {
            return this.a.getCurrentFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        @NonNull
        public Insets d() {
            return Insets.toCompatInsets(this.a.getCurrentInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        @NonNull
        public Insets e() {
            return Insets.toCompatInsets(this.a.getHiddenStateInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        @NonNull
        public Insets f() {
            return Insets.toCompatInsets(this.a.getShownStateInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public int g() {
            return this.a.getTypes();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        boolean h() {
            return this.a.isCancelled();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        boolean i() {
            return this.a.isFinished();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public void j(@Nullable Insets insets, float f, float f2) {
            this.a.setInsetsAndAlpha(insets == null ? null : insets.toPlatformInsets(), f, f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        b() {
        }

        void a(boolean z) {
        }

        public float b() {
            return 0.0f;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float c() {
            return 0.0f;
        }

        @NonNull
        public Insets d() {
            return Insets.NONE;
        }

        @NonNull
        public Insets e() {
            return Insets.NONE;
        }

        @NonNull
        public Insets f() {
            return Insets.NONE;
        }

        public int g() {
            return 0;
        }

        boolean h() {
            return true;
        }

        boolean i() {
            return false;
        }

        public void j(@Nullable Insets insets, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        }
    }

    WindowInsetsAnimationControllerCompat() {
        if (Build.VERSION.SDK_INT < 30) {
            this.a = new b();
            return;
        }
        throw new UnsupportedOperationException("On API 30+, the constructor taking a " + WindowInsetsAnimationController.class.getSimpleName() + " as parameter");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(30)
    public WindowInsetsAnimationControllerCompat(@NonNull WindowInsetsAnimationController windowInsetsAnimationController) {
        this.a = new a(windowInsetsAnimationController);
    }

    public void finish(boolean z) {
        this.a.a(z);
    }

    public float getCurrentAlpha() {
        return this.a.b();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getCurrentFraction() {
        return this.a.c();
    }

    @NonNull
    public Insets getCurrentInsets() {
        return this.a.d();
    }

    @NonNull
    public Insets getHiddenStateInsets() {
        return this.a.e();
    }

    @NonNull
    public Insets getShownStateInsets() {
        return this.a.f();
    }

    public int getTypes() {
        return this.a.g();
    }

    public boolean isCancelled() {
        return this.a.h();
    }

    public boolean isFinished() {
        return this.a.i();
    }

    public boolean isReady() {
        return (isFinished() || isCancelled()) ? false : true;
    }

    public void setInsetsAndAlpha(@Nullable Insets insets, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.a.j(insets, f, f2);
    }
}
