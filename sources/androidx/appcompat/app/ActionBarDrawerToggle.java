package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.a;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

/* loaded from: classes.dex */
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {
    private final Delegate a;
    private final DrawerLayout b;

    /* renamed from: c, reason: collision with root package name */
    private DrawerArrowDrawable f19c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f20d;
    private Drawable e;
    boolean f;
    private boolean g;
    private final int h;

    /* renamed from: i, reason: collision with root package name */
    private final int f21i;
    View.OnClickListener j;
    private boolean k;

    /* loaded from: classes.dex */
    public interface Delegate {
        Context getActionBarThemedContext();

        Drawable getThemeUpIndicator();

        boolean isNavigationVisible();

        void setActionBarDescription(@StringRes int i2);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i2);
    }

    /* loaded from: classes.dex */
    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActionBarDrawerToggle actionBarDrawerToggle = ActionBarDrawerToggle.this;
            if (actionBarDrawerToggle.f) {
                actionBarDrawerToggle.e();
                return;
            }
            View.OnClickListener onClickListener = actionBarDrawerToggle.j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes.dex */
    private static class b implements Delegate {
        private final Activity a;
        private a.C0005a b;

        b(Activity activity) {
            this.a = activity;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Context getActionBarThemedContext() {
            android.app.ActionBar actionBar = this.a.getActionBar();
            return actionBar != null ? actionBar.getThemedContext() : this.a;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Drawable getThemeUpIndicator() {
            if (Build.VERSION.SDK_INT < 18) {
                return androidx.appcompat.app.a.a(this.a);
            }
            TypedArray obtainStyledAttributes = getActionBarThemedContext().obtainStyledAttributes(null, new int[]{R.attr.homeAsUpIndicator}, R.attr.actionBarStyle, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public boolean isNavigationVisible() {
            android.app.ActionBar actionBar = this.a.getActionBar();
            return (actionBar == null || (actionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarDescription(int i2) {
            if (Build.VERSION.SDK_INT < 18) {
                this.b = androidx.appcompat.app.a.b(this.b, this.a, i2);
                return;
            }
            android.app.ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(i2);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarUpIndicator(Drawable drawable, int i2) {
            android.app.ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    actionBar.setHomeAsUpIndicator(drawable);
                    actionBar.setHomeActionContentDescription(i2);
                } else {
                    actionBar.setDisplayShowHomeEnabled(true);
                    this.b = androidx.appcompat.app.a.c(this.a, drawable, i2);
                    actionBar.setDisplayShowHomeEnabled(false);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    static class c implements Delegate {
        final Toolbar a;
        final Drawable b;

        /* renamed from: c, reason: collision with root package name */
        final CharSequence f22c;

        c(Toolbar toolbar) {
            this.a = toolbar;
            this.b = toolbar.getNavigationIcon();
            this.f22c = toolbar.getNavigationContentDescription();
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Context getActionBarThemedContext() {
            return this.a.getContext();
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public Drawable getThemeUpIndicator() {
            return this.b;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public boolean isNavigationVisible() {
            return true;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarDescription(@StringRes int i2) {
            if (i2 == 0) {
                this.a.setNavigationContentDescription(this.f22c);
            } else {
                this.a.setNavigationContentDescription(i2);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle.Delegate
        public void setActionBarUpIndicator(Drawable drawable, @StringRes int i2) {
            this.a.setNavigationIcon(drawable);
            setActionBarDescription(i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    ActionBarDrawerToggle(Activity activity, Toolbar toolbar, DrawerLayout drawerLayout, DrawerArrowDrawable drawerArrowDrawable, @StringRes int i2, @StringRes int i3) {
        this.f20d = true;
        this.f = true;
        this.k = false;
        if (toolbar != null) {
            this.a = new c(toolbar);
            toolbar.setNavigationOnClickListener(new a());
        } else if (activity instanceof DelegateProvider) {
            this.a = ((DelegateProvider) activity).getDrawerToggleDelegate();
        } else {
            this.a = new b(activity);
        }
        this.b = drawerLayout;
        this.h = i2;
        this.f21i = i3;
        if (drawerArrowDrawable == null) {
            this.f19c = new DrawerArrowDrawable(this.a.getActionBarThemedContext());
        } else {
            this.f19c = drawerArrowDrawable;
        }
        this.e = a();
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @StringRes int i2, @StringRes int i3) {
        this(activity, null, drawerLayout, null, i2, i3);
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int i2, @StringRes int i3) {
        this(activity, toolbar, drawerLayout, null, i2, i3);
    }

    private void d(float f) {
        DrawerArrowDrawable drawerArrowDrawable;
        boolean z;
        if (f != 1.0f) {
            if (f == 0.0f) {
                drawerArrowDrawable = this.f19c;
                z = false;
            }
            this.f19c.setProgress(f);
        }
        drawerArrowDrawable = this.f19c;
        z = true;
        drawerArrowDrawable.setVerticalMirror(z);
        this.f19c.setProgress(f);
    }

    Drawable a() {
        return this.a.getThemeUpIndicator();
    }

    void b(int i2) {
        this.a.setActionBarDescription(i2);
    }

    void c(Drawable drawable, int i2) {
        if (!this.k && !this.a.isNavigationVisible()) {
            Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
            this.k = true;
        }
        this.a.setActionBarUpIndicator(drawable, i2);
    }

    void e() {
        int drawerLockMode = this.b.getDrawerLockMode(8388611);
        if (this.b.isDrawerVisible(8388611) && drawerLockMode != 2) {
            this.b.closeDrawer(8388611);
        } else if (drawerLockMode != 1) {
            this.b.openDrawer(8388611);
        }
    }

    @NonNull
    public DrawerArrowDrawable getDrawerArrowDrawable() {
        return this.f19c;
    }

    public View.OnClickListener getToolbarNavigationClickListener() {
        return this.j;
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.f;
    }

    public boolean isDrawerSlideAnimationEnabled() {
        return this.f20d;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.g) {
            this.e = a();
        }
        syncState();
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(View view) {
        d(0.0f);
        if (this.f) {
            b(this.h);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(View view) {
        d(1.0f);
        if (this.f) {
            b(this.f21i);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerSlide(View view, float f) {
        if (this.f20d) {
            d(Math.min(1.0f, Math.max(0.0f, f)));
        } else {
            d(0.0f);
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerStateChanged(int i2) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332 || !this.f) {
            return false;
        }
        e();
        return true;
    }

    public void setDrawerArrowDrawable(@NonNull DrawerArrowDrawable drawerArrowDrawable) {
        this.f19c = drawerArrowDrawable;
        syncState();
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        Drawable drawable;
        int i2;
        if (z != this.f) {
            if (z) {
                drawable = this.f19c;
                i2 = this.b.isDrawerOpen(8388611) ? this.f21i : this.h;
            } else {
                drawable = this.e;
                i2 = 0;
            }
            c(drawable, i2);
            this.f = z;
        }
    }

    public void setDrawerSlideAnimationEnabled(boolean z) {
        this.f20d = z;
        if (z) {
            return;
        }
        d(0.0f);
    }

    public void setHomeAsUpIndicator(int i2) {
        setHomeAsUpIndicator(i2 != 0 ? this.b.getResources().getDrawable(i2) : null);
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.e = a();
            this.g = false;
        } else {
            this.e = drawable;
            this.g = true;
        }
        if (this.f) {
            return;
        }
        c(this.e, 0);
    }

    public void setToolbarNavigationClickListener(View.OnClickListener onClickListener) {
        this.j = onClickListener;
    }

    public void syncState() {
        d(this.b.isDrawerOpen(8388611) ? 1.0f : 0.0f);
        if (this.f) {
            c(this.f19c, this.b.isDrawerOpen(8388611) ? this.f21i : this.h);
        }
    }
}
