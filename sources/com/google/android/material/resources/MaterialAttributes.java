package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class MaterialAttributes {
    @Nullable
    public static TypedValue resolve(@NonNull Context context, @AttrRes int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean resolveBoolean(@NonNull Context context, @AttrRes int i2, boolean z) {
        TypedValue resolve = resolve(context, i2);
        return (resolve == null || resolve.type != 18) ? z : resolve.data != 0;
    }

    public static boolean resolveBooleanOrThrow(@NonNull Context context, @AttrRes int i2, @NonNull String str) {
        return resolveOrThrow(context, i2, str) != 0;
    }

    @Px
    public static int resolveDimension(@NonNull Context context, @AttrRes int i2, @DimenRes int i3) {
        TypedValue resolve = resolve(context, i2);
        return (int) ((resolve == null || resolve.type != 5) ? context.getResources().getDimension(i3) : resolve.getDimension(context.getResources().getDisplayMetrics()));
    }

    public static int resolveInteger(@NonNull Context context, @AttrRes int i2, int i3) {
        TypedValue resolve = resolve(context, i2);
        return (resolve == null || resolve.type != 16) ? i3 : resolve.data;
    }

    @Px
    public static int resolveMinimumAccessibleTouchTarget(@NonNull Context context) {
        return resolveDimension(context, R.attr.minTouchTargetSize, R.dimen.mtrl_min_touch_target_size);
    }

    public static int resolveOrThrow(@NonNull Context context, @AttrRes int i2, @NonNull String str) {
        TypedValue resolve = resolve(context, i2);
        if (resolve != null) {
            return resolve.data;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i2)));
    }

    public static int resolveOrThrow(@NonNull View view, @AttrRes int i2) {
        return resolveOrThrow(view.getContext(), i2, view.getClass().getCanonicalName());
    }
}
