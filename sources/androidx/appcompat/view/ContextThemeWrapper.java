package androidx.appcompat.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;

/* loaded from: classes.dex */
public class ContextThemeWrapper extends ContextWrapper {
    private int a;
    private Resources.Theme b;

    /* renamed from: c, reason: collision with root package name */
    private LayoutInflater f76c;

    /* renamed from: d, reason: collision with root package name */
    private Configuration f77d;
    private Resources e;

    public ContextThemeWrapper() {
        super(null);
    }

    public ContextThemeWrapper(Context context, @StyleRes int i2) {
        super(context);
        this.a = i2;
    }

    public ContextThemeWrapper(Context context, Resources.Theme theme) {
        super(context);
        this.b = theme;
    }

    private Resources a() {
        Resources resources;
        if (this.e == null) {
            Configuration configuration = this.f77d;
            if (configuration == null) {
                resources = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                resources = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources2 = super.getResources();
                Configuration configuration2 = new Configuration(resources2.getConfiguration());
                configuration2.updateFrom(this.f77d);
                this.e = new Resources(resources2.getAssets(), resources2.getDisplayMetrics(), configuration2);
            }
            this.e = resources;
        }
        return this.e;
    }

    private void b() {
        boolean z = this.b == null;
        if (z) {
            this.b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.b.setTo(theme);
            }
        }
        c(this.b, this.a, z);
    }

    public void applyOverrideConfiguration(Configuration configuration) {
        if (this.e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f77d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f77d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    protected void c(Resources.Theme theme, int i2, boolean z) {
        theme.applyStyle(i2, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return a();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f76c == null) {
            this.f76c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f76c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.b;
        if (theme != null) {
            return theme;
        }
        if (this.a == 0) {
            this.a = R.style.Theme_AppCompat_Light;
        }
        b();
        return this.b;
    }

    public int getThemeResId() {
        return this.a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        if (this.a != i2) {
            this.a = i2;
            b();
        }
    }
}
