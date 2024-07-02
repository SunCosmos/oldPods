package android.support.v4.content.res;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

/* loaded from: lib/Mus.dex */
public final class ConfigurationHelper {
    private ConfigurationHelper() {
    }

    public static int getDensityDpi(@NonNull Resources resources) {
        if (Build.VERSION.SDK_INT < 17) {
            return resources.getDisplayMetrics().densityDpi;
        }
        return resources.getConfiguration().densityDpi;
    }
}
