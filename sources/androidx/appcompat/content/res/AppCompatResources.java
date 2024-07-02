package androidx.appcompat.content.res;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ColorStateListInflaterCompat;
import java.util.WeakHashMap;

@SuppressLint({"RestrictedAPI"})
/* loaded from: classes.dex */
public final class AppCompatResources {
    private static final ThreadLocal<TypedValue> a = new ThreadLocal<>();
    private static final WeakHashMap<Context, SparseArray<a>> b = new WeakHashMap<>(0);

    /* renamed from: c, reason: collision with root package name */
    private static final Object f65c = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        final ColorStateList a;
        final Configuration b;

        a(@NonNull ColorStateList colorStateList, @NonNull Configuration configuration) {
            this.a = colorStateList;
            this.b = configuration;
        }
    }

    private AppCompatResources() {
    }

    private static void a(@NonNull Context context, @ColorRes int i2, @NonNull ColorStateList colorStateList) {
        synchronized (f65c) {
            WeakHashMap<Context, SparseArray<a>> weakHashMap = b;
            SparseArray<a> sparseArray = weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i2, new a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    @Nullable
    private static ColorStateList b(@NonNull Context context, @ColorRes int i2) {
        a aVar;
        synchronized (f65c) {
            SparseArray<a> sparseArray = b.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (aVar = sparseArray.get(i2)) != null) {
                if (aVar.b.equals(context.getResources().getConfiguration())) {
                    return aVar.a;
                }
                sparseArray.remove(i2);
            }
            return null;
        }
    }

    @NonNull
    private static TypedValue c() {
        ThreadLocal<TypedValue> threadLocal = a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    @Nullable
    private static ColorStateList d(Context context, int i2) {
        if (e(context, i2)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return ColorStateListInflaterCompat.createFromXml(resources, resources.getXml(i2), context.getTheme());
        } catch (Exception e) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    private static boolean e(@NonNull Context context, @ColorRes int i2) {
        Resources resources = context.getResources();
        TypedValue c2 = c();
        resources.getValue(i2, c2, true);
        int i3 = c2.type;
        return i3 >= 28 && i3 <= 31;
    }

    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i2);
        }
        ColorStateList b2 = b(context, i2);
        if (b2 != null) {
            return b2;
        }
        ColorStateList d2 = d(context, i2);
        if (d2 == null) {
            return ContextCompat.getColorStateList(context, i2);
        }
        a(context, i2, d2);
        return d2;
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int i2) {
        return ResourceManagerInternal.get().getDrawable(context, i2);
    }
}
