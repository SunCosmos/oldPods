package androidx.core.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class DisplayManagerCompat {
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap<Context, DisplayManagerCompat> b = new WeakHashMap<>();
    private final Context a;

    private DisplayManagerCompat(Context context) {
        this.a = context;
    }

    @NonNull
    public static DisplayManagerCompat getInstance(@NonNull Context context) {
        DisplayManagerCompat displayManagerCompat;
        WeakHashMap<Context, DisplayManagerCompat> weakHashMap = b;
        synchronized (weakHashMap) {
            displayManagerCompat = weakHashMap.get(context);
            if (displayManagerCompat == null) {
                displayManagerCompat = new DisplayManagerCompat(context);
                weakHashMap.put(context, displayManagerCompat);
            }
        }
        return displayManagerCompat;
    }

    @Nullable
    public Display getDisplay(int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            return ((DisplayManager) this.a.getSystemService("display")).getDisplay(i2);
        }
        Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay.getDisplayId() == i2) {
            return defaultDisplay;
        }
        return null;
    }

    @NonNull
    public Display[] getDisplays() {
        return Build.VERSION.SDK_INT >= 17 ? ((DisplayManager) this.a.getSystemService("display")).getDisplays() : new Display[]{((WindowManager) this.a.getSystemService("window")).getDefaultDisplay()};
    }

    @NonNull
    public Display[] getDisplays(@Nullable String str) {
        return Build.VERSION.SDK_INT >= 17 ? ((DisplayManager) this.a.getSystemService("display")).getDisplays(str) : str == null ? new Display[0] : new Display[]{((WindowManager) this.a.getSystemService("window")).getDefaultDisplay()};
    }
}
