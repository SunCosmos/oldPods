package com.google.android.material.color;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.resources.MaterialAttributes;

/* loaded from: classes.dex */
public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;

    private MaterialColors() {
    }

    @ColorInt
    public static int compositeARGBWithAlpha(@ColorInt int i2, @IntRange(from = 0, to = 255) int i3) {
        return ColorUtils.setAlphaComponent(i2, (Color.alpha(i2) * i3) / 255);
    }

    @ColorInt
    public static int getColor(@NonNull Context context, @AttrRes int i2, @ColorInt int i3) {
        TypedValue resolve = MaterialAttributes.resolve(context, i2);
        return resolve != null ? resolve.data : i3;
    }

    @ColorInt
    public static int getColor(Context context, @AttrRes int i2, String str) {
        return MaterialAttributes.resolveOrThrow(context, i2, str);
    }

    @ColorInt
    public static int getColor(@NonNull View view, @AttrRes int i2) {
        return MaterialAttributes.resolveOrThrow(view, i2);
    }

    @ColorInt
    public static int getColor(@NonNull View view, @AttrRes int i2, @ColorInt int i3) {
        return getColor(view.getContext(), i2, i3);
    }

    public static boolean isColorLight(@ColorInt int i2) {
        return i2 != 0 && ColorUtils.calculateLuminance(i2) > 0.5d;
    }

    @ColorInt
    public static int layer(@ColorInt int i2, @ColorInt int i3) {
        return ColorUtils.compositeColors(i3, i2);
    }

    @ColorInt
    public static int layer(@ColorInt int i2, @ColorInt int i3, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return layer(i2, ColorUtils.setAlphaComponent(i3, Math.round(Color.alpha(i3) * f)));
    }

    @ColorInt
    public static int layer(@NonNull View view, @AttrRes int i2, @AttrRes int i3) {
        return layer(view, i2, i3, 1.0f);
    }

    @ColorInt
    public static int layer(@NonNull View view, @AttrRes int i2, @AttrRes int i3, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return layer(getColor(view, i2), getColor(view, i3), f);
    }
}
