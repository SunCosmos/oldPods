package com.google.android.material.shape;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import com.google.android.material.internal.ViewUtils;

/* loaded from: classes.dex */
public class MaterialShapeUtils {
    private MaterialShapeUtils() {
    }

    @NonNull
    public static CornerTreatment a(int i2) {
        return i2 != 0 ? i2 != 1 ? b() : new CutCornerTreatment() : new RoundedCornerTreatment();
    }

    @NonNull
    public static CornerTreatment b() {
        return new RoundedCornerTreatment();
    }

    @NonNull
    public static EdgeTreatment c() {
        return new EdgeTreatment();
    }

    public static void setElevation(@NonNull View view, float f) {
        Drawable background = view.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) background).setElevation(f);
        }
    }

    public static void setParentAbsoluteElevation(@NonNull View view) {
        Drawable background = view.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            setParentAbsoluteElevation(view, (MaterialShapeDrawable) background);
        }
    }

    public static void setParentAbsoluteElevation(@NonNull View view, @NonNull MaterialShapeDrawable materialShapeDrawable) {
        if (materialShapeDrawable.isElevationOverlayEnabled()) {
            materialShapeDrawable.setParentAbsoluteElevation(ViewUtils.getParentAbsoluteElevation(view));
        }
    }
}
