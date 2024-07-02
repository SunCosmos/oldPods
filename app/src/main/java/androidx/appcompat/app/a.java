package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class a {
    private static final int[] a = {R.attr.homeAsUpIndicator};

    /* renamed from: androidx.appcompat.app.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0005a {
        public Method a;
        public Method b;

        /* renamed from: c, reason: collision with root package name */
        public ImageView f54c;

        C0005a(Activity activity) {
            try {
                this.a = android.app.ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.b = android.app.ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            } catch (NoSuchMethodException unused) {
                View findViewById = activity.findViewById(R.id.home);
                if (findViewById == null) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
                if (viewGroup.getChildCount() != 2) {
                    return;
                }
                View childAt = viewGroup.getChildAt(0);
                childAt = childAt.getId() == 16908332 ? viewGroup.getChildAt(1) : childAt;
                if (childAt instanceof ImageView) {
                    this.f54c = (ImageView) childAt;
                }
            }
        }
    }

    public static Drawable a(Activity activity) {
        TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(a);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    public static C0005a b(C0005a c0005a, Activity activity, int i2) {
        if (c0005a == null) {
            c0005a = new C0005a(activity);
        }
        if (c0005a.a != null) {
            try {
                android.app.ActionBar actionBar = activity.getActionBar();
                c0005a.b.invoke(actionBar, Integer.valueOf(i2));
                if (Build.VERSION.SDK_INT <= 19) {
                    actionBar.setSubtitle(actionBar.getSubtitle());
                }
            } catch (Exception e) {
                Log.w("ActionBarDrawerToggleHC", "Couldn't set content description via JB-MR2 API", e);
            }
        }
        return c0005a;
    }

    public static C0005a c(Activity activity, Drawable drawable, int i2) {
        C0005a c0005a = new C0005a(activity);
        if (c0005a.a != null) {
            try {
                android.app.ActionBar actionBar = activity.getActionBar();
                c0005a.a.invoke(actionBar, drawable);
                c0005a.b.invoke(actionBar, Integer.valueOf(i2));
            } catch (Exception e) {
                Log.w("ActionBarDrawerToggleHC", "Couldn't set home-as-up indicator via JB-MR2 API", e);
            }
        } else {
            ImageView imageView = c0005a.f54c;
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
            } else {
                Log.w("ActionBarDrawerToggleHC", "Couldn't set home-as-up indicator");
            }
        }
        return c0005a;
    }
}
