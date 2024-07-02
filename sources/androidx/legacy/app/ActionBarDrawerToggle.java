package androidx.legacy.app;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import java.lang.reflect.Method;

@Deprecated
/* loaded from: classes.dex */
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {
    private static final int[] m = {R.attr.homeAsUpIndicator};
    final Activity a;
    private final Delegate b;

    /* renamed from: c, reason: collision with root package name */
    private final DrawerLayout f801c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f802d;
    private boolean e;
    private Drawable f;
    private Drawable g;
    private b h;

    /* renamed from: i, reason: collision with root package name */
    private final int f803i;
    private final int j;
    private final int k;
    private a l;

    @Deprecated
    /* loaded from: classes.dex */
    public interface Delegate {
        @Nullable
        Drawable getThemeUpIndicator();

        void setActionBarDescription(@StringRes int i2);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i2);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        Method a;
        Method b;

        /* renamed from: c, reason: collision with root package name */
        ImageView f804c;

        a(Activity activity) {
            try {
                this.a = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.b = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
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
                    this.f804c = (ImageView) childAt;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b extends InsetDrawable implements Drawable.Callback {
        private final boolean a;
        private final Rect b;

        /* renamed from: c, reason: collision with root package name */
        private float f805c;

        /* renamed from: d, reason: collision with root package name */
        private float f806d;

        b(Drawable drawable) {
            super(drawable, 0);
            this.a = Build.VERSION.SDK_INT > 18;
            this.b = new Rect();
        }

        public float a() {
            return this.f805c;
        }

        public void b(float f) {
            this.f806d = f;
            invalidateSelf();
        }

        public void c(float f) {
            this.f805c = f;
            invalidateSelf();
        }

        @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            copyBounds(this.b);
            canvas.save();
            boolean z = ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.a.getWindow().getDecorView()) == 1;
            int i2 = z ? -1 : 1;
            float width = this.b.width();
            canvas.translate((-this.f806d) * width * this.f805c * i2, 0.0f);
            if (z && !this.a) {
                canvas.translate(width, 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            super.draw(canvas);
            canvas.restore();
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @DrawableRes int i2, @StringRes int i3, @StringRes int i4) {
        this(activity, drawerLayout, !a(activity), i2, i3, i4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, boolean z, @DrawableRes int i2, @StringRes int i3, @StringRes int i4) {
        this.f802d = true;
        this.a = activity;
        this.b = activity instanceof DelegateProvider ? ((DelegateProvider) activity).getDrawerToggleDelegate() : null;
        this.f801c = drawerLayout;
        this.f803i = i2;
        this.j = i3;
        this.k = i4;
        this.f = b();
        this.g = ContextCompat.getDrawable(activity, i2);
        b bVar = new b(this.g);
        this.h = bVar;
        bVar.b(z ? 0.33333334f : 0.0f);
    }

    private static boolean a(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 21 && Build.VERSION.SDK_INT >= 21;
    }

    private Drawable b() {
        TypedArray obtainStyledAttributes;
        Delegate delegate = this.b;
        if (delegate != null) {
            return delegate.getThemeUpIndicator();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.a.getActionBar();
            obtainStyledAttributes = (actionBar != null ? actionBar.getThemedContext() : this.a).obtainStyledAttributes(null, m, R.attr.actionBarStyle, 0);
        } else {
            obtainStyledAttributes = this.a.obtainStyledAttributes(m);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    private void c(int i2) {
        Delegate delegate = this.b;
        if (delegate != null) {
            delegate.setActionBarDescription(i2);
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(i2);
                return;
            }
            return;
        }
        if (this.l == null) {
            this.l = new a(this.a);
        }
        if (this.l.a != null) {
            try {
                ActionBar actionBar2 = this.a.getActionBar();
                this.l.b.invoke(actionBar2, Integer.valueOf(i2));
                actionBar2.setSubtitle(actionBar2.getSubtitle());
            } catch (Exception e) {
                Log.w("ActionBarDrawerToggle", "Couldn't set content description via JB-MR2 API", e);
            }
        }
    }

    private void d(Drawable drawable, int i2) {
        Delegate delegate = this.b;
        if (delegate != null) {
            delegate.setActionBarUpIndicator(drawable, i2);
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(drawable);
                actionBar.setHomeActionContentDescription(i2);
                return;
            }
            return;
        }
        if (this.l == null) {
            this.l = new a(this.a);
        }
        a aVar = this.l;
        if (aVar.a == null) {
            ImageView imageView = aVar.f804c;
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
                return;
            } else {
                Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator");
                return;
            }
        }
        try {
            ActionBar actionBar2 = this.a.getActionBar();
            this.l.a.invoke(actionBar2, drawable);
            this.l.b.invoke(actionBar2, Integer.valueOf(i2));
        } catch (Exception e) {
            Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator via JB-MR2 API", e);
        }
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.f802d;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.e) {
            this.f = b();
        }
        this.g = ContextCompat.getDrawable(this.a, this.f803i);
        syncState();
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(View view) {
        this.h.c(0.0f);
        if (this.f802d) {
            c(this.j);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(View view) {
        this.h.c(1.0f);
        if (this.f802d) {
            c(this.k);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerSlide(View view, float f) {
        float a2 = this.h.a();
        this.h.c(f > 0.5f ? Math.max(a2, Math.max(0.0f, f - 0.5f) * 2.0f) : Math.min(a2, f * 2.0f));
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerStateChanged(int i2) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332 || !this.f802d) {
            return false;
        }
        if (this.f801c.isDrawerVisible(8388611)) {
            this.f801c.closeDrawer(8388611);
            return true;
        }
        this.f801c.openDrawer(8388611);
        return true;
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        Drawable drawable;
        int i2;
        if (z != this.f802d) {
            if (z) {
                drawable = this.h;
                i2 = this.f801c.isDrawerOpen(8388611) ? this.k : this.j;
            } else {
                drawable = this.f;
                i2 = 0;
            }
            d(drawable, i2);
            this.f802d = z;
        }
    }

    public void setHomeAsUpIndicator(int i2) {
        setHomeAsUpIndicator(i2 != 0 ? ContextCompat.getDrawable(this.a, i2) : null);
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.f = b();
            this.e = false;
        } else {
            this.f = drawable;
            this.e = true;
        }
        if (this.f802d) {
            return;
        }
        d(this.f, 0);
    }

    public void syncState() {
        b bVar;
        float f;
        if (this.f801c.isDrawerOpen(8388611)) {
            bVar = this.h;
            f = 1.0f;
        } else {
            bVar = this.h;
            f = 0.0f;
        }
        bVar.c(f);
        if (this.f802d) {
            d(this.h, this.f801c.isDrawerOpen(8388611) ? this.k : this.j);
        }
    }
}
