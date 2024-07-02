package c.e.a;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class a {
    private static String f;
    private final b a;
    private boolean b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1410c;

    /* renamed from: d, reason: collision with root package name */
    private View f1411d;
    private View e;

    /* loaded from: classes.dex */
    public static class b {
        private final int a;
        private final boolean b;

        /* renamed from: c, reason: collision with root package name */
        private final int f1412c;

        /* renamed from: d, reason: collision with root package name */
        private final int f1413d;
        private final boolean e;
        private final float f;

        private b(Activity activity, boolean z, boolean z2) {
            Resources resources = activity.getResources();
            this.e = resources.getConfiguration().orientation == 1;
            this.f = g(activity);
            this.a = b(resources, "status_bar_height");
            a(activity);
            int d2 = d(activity);
            this.f1412c = d2;
            this.f1413d = f(activity);
            this.b = d2 > 0;
        }

        @TargetApi(14)
        private int a(Context context) {
            if (Build.VERSION.SDK_INT < 14) {
                return 0;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }

        private int b(Resources resources, String str) {
            int identifier = resources.getIdentifier(str, "dimen", "android");
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        @TargetApi(14)
        private int d(Context context) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !i(context)) {
                return 0;
            }
            return b(resources, this.e ? "navigation_bar_height" : "navigation_bar_height_landscape");
        }

        @TargetApi(14)
        private int f(Context context) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !i(context)) {
                return 0;
            }
            return b(resources, "navigation_bar_width");
        }

        @SuppressLint({"NewApi"})
        private float g(Activity activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            int i2 = Build.VERSION.SDK_INT;
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            if (i2 >= 16) {
                defaultDisplay.getRealMetrics(displayMetrics);
            } else {
                defaultDisplay.getMetrics(displayMetrics);
            }
            float f = displayMetrics.widthPixels;
            float f2 = displayMetrics.density;
            return Math.min(f / f2, displayMetrics.heightPixels / f2);
        }

        @TargetApi(14)
        private boolean i(Context context) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (identifier == 0) {
                return !ViewConfiguration.get(context).hasPermanentMenuKey();
            }
            boolean z = resources.getBoolean(identifier);
            if ("1".equals(a.f)) {
                return false;
            }
            if ("0".equals(a.f)) {
                return true;
            }
            return z;
        }

        public int c() {
            return this.f1412c;
        }

        public int e() {
            return this.f1413d;
        }

        public int h() {
            return this.a;
        }

        public boolean j() {
            return this.b;
        }

        public boolean k() {
            return this.f >= 600.0f || this.e;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
                declaredMethod.setAccessible(true);
                f = (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable unused) {
                f = null;
            }
        }
    }

    @TargetApi(19)
    public a(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (Build.VERSION.SDK_INT >= 19) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{R.attr.windowTranslucentStatus, R.attr.windowTranslucentNavigation});
            try {
                this.b = obtainStyledAttributes.getBoolean(0, false);
                this.f1410c = obtainStyledAttributes.getBoolean(1, false);
                obtainStyledAttributes.recycle();
                int i2 = window.getAttributes().flags;
                if ((67108864 & i2) != 0) {
                    this.b = true;
                }
                if ((i2 & 134217728) != 0) {
                    this.f1410c = true;
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
        b bVar = new b(activity, this.b, this.f1410c);
        this.a = bVar;
        if (!bVar.j()) {
            this.f1410c = false;
        }
        if (this.b) {
            h(activity, viewGroup);
        }
        if (this.f1410c) {
            g(activity, viewGroup);
        }
    }

    private void g(Context context, ViewGroup viewGroup) {
        FrameLayout.LayoutParams layoutParams;
        int i2;
        this.e = new View(context);
        if (this.a.k()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.a.c());
            i2 = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.a.e(), -1);
            i2 = 5;
        }
        layoutParams.gravity = i2;
        this.e.setLayoutParams(layoutParams);
        this.e.setBackgroundColor(-1728053248);
        this.e.setVisibility(8);
        viewGroup.addView(this.e);
    }

    private void h(Context context, ViewGroup viewGroup) {
        this.f1411d = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.a.h());
        layoutParams.gravity = 48;
        if (this.f1410c && !this.a.k()) {
            layoutParams.rightMargin = this.a.e();
        }
        this.f1411d.setLayoutParams(layoutParams);
        this.f1411d.setBackgroundColor(-1728053248);
        this.f1411d.setVisibility(8);
        viewGroup.addView(this.f1411d);
    }

    public void b(int i2) {
        if (this.f1410c) {
            this.e.setBackgroundColor(i2);
        }
    }

    public void c(boolean z) {
        if (this.f1410c) {
            this.e.setVisibility(z ? 0 : 8);
        }
    }

    public void d(int i2) {
        if (this.b) {
            this.f1411d.setBackgroundColor(i2);
        }
    }

    public void e(boolean z) {
        if (this.b) {
            this.f1411d.setVisibility(z ? 0 : 8);
        }
    }

    public void f(int i2) {
        d(i2);
        b(i2);
    }
}
