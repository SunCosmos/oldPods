package android.support.transition;

import android.graphics.Matrix;
import android.os.Build;
import android.support.transition.GhostViewApi14;
import android.support.transition.GhostViewApi21;
import android.support.transition.GhostViewImpl;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: lib/Mus.dex */
class GhostViewUtils {
    private static final GhostViewImpl.Creator CREATOR;

    GhostViewUtils() {
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            CREATOR = new GhostViewApi21.Creator();
        } else {
            CREATOR = new GhostViewApi14.Creator();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
        return CREATOR.addGhost(view, viewGroup, matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeGhost(View view) {
        CREATOR.removeGhost(view);
    }
}
