package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;

/* loaded from: classes.dex */
public class ElevationOverlayProvider {
    private final boolean a;
    private final int b;

    /* renamed from: c */
    private final int f1565c;

    /* renamed from: d */
    private final float f1566d;

    public ElevationOverlayProvider(@NonNull Context context) {
        this.a = MaterialAttributes.resolveBoolean(context, R.attr.elevationOverlayEnabled, false);
        this.b = MaterialColors.getColor(context, R.attr.elevationOverlayColor, 0);
        this.f1565c = MaterialColors.getColor(context, R.attr.colorSurface, 0);
        this.f1566d = context.getResources().getDisplayMetrics().density;
    }

    private boolean a(@ColorInt int i2) {
        return ColorUtils.setAlphaComponent(i2, 255) == this.f1565c;
    }

    public int calculateOverlayAlpha(float f) {
        return Math.round(calculateOverlayAlphaFraction(f) * 255.0f);
    }

    public float calculateOverlayAlphaFraction(float f) {
        if (this.f1566d <= 0.0f || f <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
    }

    @ColorInt
    public int compositeOverlay(@ColorInt int i2, float f) {
        float calculateOverlayAlphaFraction = calculateOverlayAlphaFraction(f);
        return ColorUtils.setAlphaComponent(MaterialColors.layer(ColorUtils.setAlphaComponent(i2, 255), this.b, calculateOverlayAlphaFraction), Color.alpha(i2));
    }

    @ColorInt
    public int compositeOverlay(@ColorInt int i2, float f, @NonNull View view) {
        return compositeOverlay(i2, f + getParentAbsoluteElevation(view));
    }

    @ColorInt
    public int compositeOverlayIfNeeded(@ColorInt int i2, float f) {
        return (this.a && a(i2)) ? compositeOverlay(i2, f) : i2;
    }

    @ColorInt
    public int compositeOverlayIfNeeded(@ColorInt int i2, float f, @NonNull View view) {
        return compositeOverlayIfNeeded(i2, f + getParentAbsoluteElevation(view));
    }

    @ColorInt
    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f) {
        return compositeOverlayIfNeeded(this.f1565c, f);
    }

    @ColorInt
    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f, @NonNull View view) {
        return compositeOverlayWithThemeSurfaceColorIfNeeded(f + getParentAbsoluteElevation(view));
    }

    public float getParentAbsoluteElevation(@NonNull View view) {
        return ViewUtils.getParentAbsoluteElevation(view);
    }

    @ColorInt
    public int getThemeElevationOverlayColor() {
        return this.b;
    }

    @ColorInt
    public int getThemeSurfaceColor() {
        return this.f1565c;
    }

    public boolean isThemeElevationOverlayEnabled() {
        return this.a;
    }
}
