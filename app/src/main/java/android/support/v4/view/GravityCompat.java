package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;

/* loaded from: lib/Mus.dex */
public final class GravityCompat {
    public static final int END = 8388613;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;

    public static void apply(int i2, int i3, int i4, Rect rect, Rect rect2, int i5) {
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.apply(i2, i3, i4, rect, rect2, i5);
        } else {
            Gravity.apply(i2, i3, i4, rect, rect2);
        }
    }

    public static void apply(int i2, int i3, int i4, Rect rect, int i5, int i6, Rect rect2, int i7) {
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.apply(i2, i3, i4, rect, i5, i6, rect2, i7);
        } else {
            Gravity.apply(i2, i3, i4, rect, i5, i6, rect2);
        }
    }

    public static void applyDisplay(int i2, Rect rect, Rect rect2, int i3) {
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.applyDisplay(i2, rect, rect2, i3);
        } else {
            Gravity.applyDisplay(i2, rect, rect2);
        }
    }

    public static int getAbsoluteGravity(int i2, int i3) {
        if (Build.VERSION.SDK_INT < 17) {
            return i2 & (-8388609);
        }
        return Gravity.getAbsoluteGravity(i2, i3);
    }

    private GravityCompat() {
    }
}
