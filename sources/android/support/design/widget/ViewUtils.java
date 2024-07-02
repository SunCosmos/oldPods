package android.support.design.widget;

import android.graphics.PorterDuff;

/* loaded from: lib/Mus.dex */
class ViewUtils {
    ViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PorterDuff.Mode parseTintMode(int i2, PorterDuff.Mode mode) {
        switch (i2) {
            case 3:
                return PorterDuff.Mode.SRC_OVER;
            case 5:
                return PorterDuff.Mode.SRC_IN;
            case 9:
                return PorterDuff.Mode.SRC_ATOP;
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            default:
                return mode;
        }
    }
}
