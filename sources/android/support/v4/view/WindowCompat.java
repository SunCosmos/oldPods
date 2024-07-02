package android.support.v4.view;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

/* loaded from: lib/Mus.dex */
public final class WindowCompat {
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    private WindowCompat() {
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull Window window, @IdRes int i2) {
        T t = (T) window.findViewById(i2);
        if (t == null) {
            throw new IllegalArgumentException("ID does not reference a View inside this Window");
        }
        return t;
    }
}
