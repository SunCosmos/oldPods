package androidx.core.view;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public final class DisplayCompat {

    /* loaded from: classes.dex */
    public static final class ModeCompat {
        private final Display.Mode a;
        private final Point b;

        /* renamed from: c, reason: collision with root package name */
        private final boolean f615c;

        ModeCompat(@NonNull Point point) {
            Preconditions.checkNotNull(point, "physicalSize == null");
            this.b = point;
            this.a = null;
            this.f615c = true;
        }

        @RequiresApi(23)
        ModeCompat(@NonNull Display.Mode mode, @NonNull Point point) {
            Preconditions.checkNotNull(mode, "mode == null, can't wrap a null reference");
            Preconditions.checkNotNull(point, "physicalSize == null");
            this.b = point;
            this.a = mode;
            this.f615c = true;
        }

        @RequiresApi(23)
        ModeCompat(@NonNull Display.Mode mode, boolean z) {
            Preconditions.checkNotNull(mode, "mode == null, can't wrap a null reference");
            this.b = new Point(mode.getPhysicalWidth(), mode.getPhysicalHeight());
            this.a = mode;
            this.f615c = z;
        }

        public int getPhysicalHeight() {
            return this.b.y;
        }

        public int getPhysicalWidth() {
            return this.b.x;
        }

        public boolean isNative() {
            return this.f615c;
        }

        @Nullable
        @RequiresApi(23)
        public Display.Mode toMode() {
            return this.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(17)
    /* loaded from: classes.dex */
    public static class a {
        static void a(Display display, Point point) {
            display.getRealSize(point);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static class b {
        @NonNull
        static ModeCompat a(@NonNull Context context, @NonNull Display display) {
            Display.Mode mode = display.getMode();
            Point a = DisplayCompat.a(context, display);
            return (a == null || d(mode, a)) ? new ModeCompat(mode, true) : new ModeCompat(mode, a);
        }

        @NonNull
        @SuppressLint({"ArrayReturn"})
        public static ModeCompat[] b(@NonNull Context context, @NonNull Display display) {
            Display.Mode[] supportedModes = display.getSupportedModes();
            ModeCompat[] modeCompatArr = new ModeCompat[supportedModes.length];
            Display.Mode mode = display.getMode();
            Point a = DisplayCompat.a(context, display);
            if (a == null || d(mode, a)) {
                for (int i2 = 0; i2 < supportedModes.length; i2++) {
                    modeCompatArr[i2] = new ModeCompat(supportedModes[i2], e(supportedModes[i2], mode));
                }
            } else {
                for (int i3 = 0; i3 < supportedModes.length; i3++) {
                    modeCompatArr[i3] = e(supportedModes[i3], mode) ? new ModeCompat(supportedModes[i3], a) : new ModeCompat(supportedModes[i3], false);
                }
            }
            return modeCompatArr;
        }

        static boolean c(@NonNull Display display) {
            Display.Mode mode = display.getMode();
            Display.Mode[] supportedModes = display.getSupportedModes();
            for (int i2 = 0; i2 < supportedModes.length; i2++) {
                if (mode.getPhysicalHeight() < supportedModes[i2].getPhysicalHeight() || mode.getPhysicalWidth() < supportedModes[i2].getPhysicalWidth()) {
                    return false;
                }
            }
            return true;
        }

        static boolean d(Display.Mode mode, Point point) {
            return (mode.getPhysicalWidth() == point.x && mode.getPhysicalHeight() == point.y) || (mode.getPhysicalWidth() == point.y && mode.getPhysicalHeight() == point.x);
        }

        static boolean e(Display.Mode mode, Display.Mode mode2) {
            return mode.getPhysicalWidth() == mode2.getPhysicalWidth() && mode.getPhysicalHeight() == mode2.getPhysicalHeight();
        }
    }

    private DisplayCompat() {
    }

    static Point a(@NonNull Context context, @NonNull Display display) {
        Point i2 = i(Build.VERSION.SDK_INT < 28 ? "sys.display-size" : "vendor.display-size", display);
        if (i2 != null) {
            return i2;
        }
        if (f(context) && e(display)) {
            return new Point(3840, 2160);
        }
        return null;
    }

    @NonNull
    private static Point b(@NonNull Context context, @NonNull Display display) {
        Point a2 = a(context, display);
        if (a2 != null) {
            return a2;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            a.a(display, point);
        } else {
            display.getSize(point);
        }
        return point;
    }

    @NonNull
    @VisibleForTesting
    static ModeCompat c(@NonNull Context context, @NonNull Display display) {
        return Build.VERSION.SDK_INT >= 23 ? b.a(context, display) : new ModeCompat(b(context, display));
    }

    @Nullable
    private static String d(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (Exception unused) {
            return null;
        }
    }

    static boolean e(@NonNull Display display) {
        if (Build.VERSION.SDK_INT >= 23) {
            return b.c(display);
        }
        return true;
    }

    private static boolean f(@NonNull Context context) {
        return g(context) && "Sony".equals(Build.MANUFACTURER) && Build.MODEL.startsWith("BRAVIA") && context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd");
    }

    private static boolean g(@NonNull Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        return uiModeManager != null && uiModeManager.getCurrentModeType() == 4;
    }

    @NonNull
    @SuppressLint({"ArrayReturn"})
    public static ModeCompat[] getSupportedModes(@NonNull Context context, @NonNull Display display) {
        return Build.VERSION.SDK_INT >= 23 ? b.b(context, display) : new ModeCompat[]{c(context, display)};
    }

    private static Point h(@NonNull String str) {
        String[] split = str.trim().split("x", -1);
        if (split.length == 2) {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            if (parseInt > 0 && parseInt2 > 0) {
                return new Point(parseInt, parseInt2);
            }
        }
        throw new NumberFormatException();
    }

    @Nullable
    private static Point i(@NonNull String str, @NonNull Display display) {
        if (display.getDisplayId() != 0) {
            return null;
        }
        String d2 = d(str);
        if (TextUtils.isEmpty(d2)) {
            return null;
        }
        try {
            return h(d2);
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
