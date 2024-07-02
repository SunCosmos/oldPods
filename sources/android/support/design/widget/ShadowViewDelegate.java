package android.support.design.widget;

import android.graphics.drawable.Drawable;

/* loaded from: lib/Mus.dex */
interface ShadowViewDelegate {
    float getRadius();

    boolean isCompatPaddingEnabled();

    void setBackgroundDrawable(Drawable drawable);

    void setShadowPadding(int i2, int i3, int i4, int i5);
}
