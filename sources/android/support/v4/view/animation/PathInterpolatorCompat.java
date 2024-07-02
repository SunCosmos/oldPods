package android.support.v4.view.animation;

import android.graphics.Path;
import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* loaded from: lib/Mus.dex */
public final class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(Path path) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(path) : new PathInterpolatorApi14(path);
    }

    public static Interpolator create(float f, float f2) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(f, f2) : new PathInterpolatorApi14(f, f2);
    }

    public static Interpolator create(float f, float f2, float f3, float f4) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(f, f2, f3, f4) : new PathInterpolatorApi14(f, f2, f3, f4);
    }
}
