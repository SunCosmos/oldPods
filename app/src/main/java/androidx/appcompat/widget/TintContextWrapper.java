package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class TintContextWrapper extends ContextWrapper {

    /* renamed from: c, reason: collision with root package name */
    private static final Object f199c = new Object();

    /* renamed from: d, reason: collision with root package name */
    private static ArrayList<WeakReference<TintContextWrapper>> f200d;
    private final Resources a;
    private final Resources.Theme b;

    private TintContextWrapper(@NonNull Context context) {
        super(context);
        if (!VectorEnabledTintResources.shouldBeUsed()) {
            this.a = new o(this, context.getResources());
            this.b = null;
            return;
        }
        VectorEnabledTintResources vectorEnabledTintResources = new VectorEnabledTintResources(this, context.getResources());
        this.a = vectorEnabledTintResources;
        Resources.Theme newTheme = vectorEnabledTintResources.newTheme();
        this.b = newTheme;
        newTheme.setTo(context.getTheme());
    }

    private static boolean a(@NonNull Context context) {
        if ((context instanceof TintContextWrapper) || (context.getResources() instanceof o) || (context.getResources() instanceof VectorEnabledTintResources)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || VectorEnabledTintResources.shouldBeUsed();
    }

    public static Context wrap(@NonNull Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (f199c) {
            ArrayList<WeakReference<TintContextWrapper>> arrayList = f200d;
            if (arrayList == null) {
                f200d = new ArrayList<>();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference<TintContextWrapper> weakReference = f200d.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f200d.remove(size);
                    }
                }
                for (int size2 = f200d.size() - 1; size2 >= 0; size2--) {
                    WeakReference<TintContextWrapper> weakReference2 = f200d.get(size2);
                    TintContextWrapper tintContextWrapper = weakReference2 != null ? weakReference2.get() : null;
                    if (tintContextWrapper != null && tintContextWrapper.getBaseContext() == context) {
                        return tintContextWrapper;
                    }
                }
            }
            TintContextWrapper tintContextWrapper2 = new TintContextWrapper(context);
            f200d.add(new WeakReference<>(tintContextWrapper2));
            return tintContextWrapper2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        Resources.Theme theme = this.b;
        if (theme == null) {
            super.setTheme(i2);
        } else {
            theme.applyStyle(i2, true);
        }
    }
}
