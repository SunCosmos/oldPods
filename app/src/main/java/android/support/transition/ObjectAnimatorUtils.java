package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;

/* loaded from: lib/Mus.dex */
class ObjectAnimatorUtils {
    private static final ObjectAnimatorUtilsImpl IMPL;

    ObjectAnimatorUtils() {
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new ObjectAnimatorUtilsApi21();
        } else {
            IMPL = new ObjectAnimatorUtilsApi14();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> ObjectAnimator ofPointF(T t, Property<T, PointF> property, Path path) {
        return IMPL.ofPointF(t, property, path);
    }
}
