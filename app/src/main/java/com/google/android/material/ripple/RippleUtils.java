package com.google.android.material.ripple;

import android.R;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.StateSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.ColorUtils;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class RippleUtils {
    public static final boolean USE_FRAMEWORK_RIPPLE;
    private static final int[] a;
    private static final int[] b;

    /* renamed from: c, reason: collision with root package name */
    private static final int[] f1650c;

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f1651d;
    private static final int[] e;
    private static final int[] f;
    private static final int[] g;
    private static final int[] h;

    /* renamed from: i, reason: collision with root package name */
    private static final int[] f1652i;
    private static final int[] j;

    @VisibleForTesting
    static final String k;

    static {
        USE_FRAMEWORK_RIPPLE = Build.VERSION.SDK_INT >= 21;
        a = new int[]{R.attr.state_pressed};
        b = new int[]{R.attr.state_hovered, R.attr.state_focused};
        f1650c = new int[]{R.attr.state_focused};
        f1651d = new int[]{R.attr.state_hovered};
        e = new int[]{R.attr.state_selected, R.attr.state_pressed};
        f = new int[]{R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};
        g = new int[]{R.attr.state_selected, R.attr.state_focused};
        h = new int[]{R.attr.state_selected, R.attr.state_hovered};
        f1652i = new int[]{R.attr.state_selected};
        j = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        k = RippleUtils.class.getSimpleName();
    }

    private RippleUtils() {
    }

    @ColorInt
    @TargetApi(21)
    private static int a(@ColorInt int i2) {
        return ColorUtils.setAlphaComponent(i2, Math.min(Color.alpha(i2) * 2, 255));
    }

    @ColorInt
    private static int b(@Nullable ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return USE_FRAMEWORK_RIPPLE ? a(colorForState) : colorForState;
    }

    @NonNull
    public static ColorStateList convertToRippleDrawableColor(@Nullable ColorStateList colorStateList) {
        if (USE_FRAMEWORK_RIPPLE) {
            return new ColorStateList(new int[][]{f1652i, StateSet.NOTHING}, new int[]{b(colorStateList, e), b(colorStateList, a)});
        }
        int[] iArr = e;
        int[] iArr2 = f;
        int[] iArr3 = g;
        int[] iArr4 = h;
        int[] iArr5 = a;
        int[] iArr6 = b;
        int[] iArr7 = f1650c;
        int[] iArr8 = f1651d;
        return new ColorStateList(new int[][]{iArr, iArr2, iArr3, iArr4, f1652i, iArr5, iArr6, iArr7, iArr8, StateSet.NOTHING}, new int[]{b(colorStateList, iArr), b(colorStateList, iArr2), b(colorStateList, iArr3), b(colorStateList, iArr4), 0, b(colorStateList, iArr5), b(colorStateList, iArr6), b(colorStateList, iArr7), b(colorStateList, iArr8), 0});
    }

    @NonNull
    public static ColorStateList sanitizeRippleDrawableColor(@Nullable ColorStateList colorStateList) {
        if (colorStateList == null) {
            return ColorStateList.valueOf(0);
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 22 && i2 <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(j, 0)) != 0) {
            Log.w(k, "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
        }
        return colorStateList;
    }

    public static boolean shouldDrawRippleCompat(@NonNull int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                z = true;
            } else if (i2 == 16842908 || i2 == 16842919 || i2 == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }
}
