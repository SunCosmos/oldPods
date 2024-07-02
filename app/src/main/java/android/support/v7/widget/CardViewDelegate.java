package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: lib/Mus.dex */
interface CardViewDelegate {
    Drawable getCardBackground();

    View getCardView();

    boolean getPreventCornerOverlap();

    boolean getUseCompatPadding();

    void setCardBackground(Drawable drawable);

    void setMinWidthHeightInternal(int i2, int i3);

    void setShadowPadding(int i2, int i3, int i4, int i5);
}
