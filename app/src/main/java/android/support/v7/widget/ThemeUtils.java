package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

/* loaded from: lib/Mus.dex */
class ThemeUtils {
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();
    static final int[] DISABLED_STATE_SET = {-16842910};
    static final int[] FOCUSED_STATE_SET = {R.attr.state_focused};
    static final int[] ACTIVATED_STATE_SET = {R.attr.state_activated};
    static final int[] PRESSED_STATE_SET = {R.attr.state_pressed};
    static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    static final int[] SELECTED_STATE_SET = {R.attr.state_selected};
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = {-16842919, -16842908};
    static final int[] EMPTY_STATE_SET = new int[0];
    private static final int[] TEMP_ARRAY = new int[1];

    ThemeUtils() {
    }

    public static ColorStateList createDisabledStateList(int i2, int i3) {
        int[][] iArr = new int[2];
        int[] iArr2 = new int[2];
        iArr[0] = DISABLED_STATE_SET;
        iArr2[0] = i3;
        int i4 = 0 + 1;
        iArr[i4] = EMPTY_STATE_SET;
        iArr2[i4] = i2;
        int i5 = i4 + 1;
        return new ColorStateList(iArr, iArr2);
    }

    public static int getThemeAttrColor(Context context, int i2) {
        TEMP_ARRAY[0] = i2;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, TEMP_ARRAY);
        try {
            return obtainStyledAttributes.getColor(0, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static ColorStateList getThemeAttrColorStateList(Context context, int i2) {
        TEMP_ARRAY[0] = i2;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, TEMP_ARRAY);
        try {
            return obtainStyledAttributes.getColorStateList(0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int getDisabledThemeAttrColor(Context context, int i2) {
        ColorStateList themeAttrColorStateList = getThemeAttrColorStateList(context, i2);
        if (themeAttrColorStateList != null && themeAttrColorStateList.isStateful()) {
            return themeAttrColorStateList.getColorForState(DISABLED_STATE_SET, themeAttrColorStateList.getDefaultColor());
        }
        TypedValue typedValue = getTypedValue();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValue, true);
        return getThemeAttrColor(context, i2, typedValue.getFloat());
    }

    private static TypedValue getTypedValue() {
        TypedValue typedValue = TL_TYPED_VALUE.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            TL_TYPED_VALUE.set(typedValue);
        }
        return typedValue;
    }

    static int getThemeAttrColor(Context context, int i2, float f) {
        return ColorUtils.setAlphaComponent(getThemeAttrColor(context, i2), Math.round(Color.alpha(r5) * f));
    }
}
