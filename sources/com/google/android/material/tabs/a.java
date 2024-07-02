package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes.dex */
public class a extends b {
    private static float e(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        double d2 = f;
        Double.isNaN(d2);
        return (float) (1.0d - Math.cos((d2 * 3.141592653589793d) / 2.0d));
    }

    private static float f(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        double d2 = f;
        Double.isNaN(d2);
        return (float) Math.sin((d2 * 3.141592653589793d) / 2.0d);
    }

    @Override // com.google.android.material.tabs.b
    public void c(TabLayout tabLayout, View view, View view2, float f, @NonNull Drawable drawable) {
        float f2;
        float e;
        RectF a = b.a(tabLayout, view);
        RectF a2 = b.a(tabLayout, view2);
        if (a.left < a2.left) {
            f2 = e(f);
            e = f(f);
        } else {
            f2 = f(f);
            e = e(f);
        }
        drawable.setBounds(AnimationUtils.lerp((int) a.left, (int) a2.left, f2), drawable.getBounds().top, AnimationUtils.lerp((int) a.right, (int) a2.right, e), drawable.getBounds().bottom);
    }
}
