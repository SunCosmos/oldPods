package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.RoundRectDrawableWithShadow;

/* loaded from: lib/Mus.dex */
class CardViewBaseImpl implements CardViewImpl {
    private final RectF mCornerRect = new RectF();

    @Override // android.support.v7.widget.CardViewImpl
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() { // from class: android.support.v7.widget.CardViewBaseImpl.1
            @Override // android.support.v7.widget.RoundRectDrawableWithShadow.RoundRectHelper
            public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
                float f2 = f * 2.0f;
                float width = (rectF.width() - f2) - 1.0f;
                float height = (rectF.height() - f2) - 1.0f;
                if (f >= 1.0f) {
                    float f3 = f + 0.5f;
                    CardViewBaseImpl.this.mCornerRect.set(-f3, -f3, f3, f3);
                    int save = canvas.save();
                    canvas.translate(rectF.left + f3, rectF.top + f3);
                    canvas.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(width, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(height, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(width, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.restoreToCount(save);
                    canvas.drawRect((rectF.left + f3) - 1.0f, rectF.top, (rectF.right - f3) + 1.0f, rectF.top + f3, paint);
                    canvas.drawRect((rectF.left + f3) - 1.0f, rectF.bottom - f3, (rectF.right - f3) + 1.0f, rectF.bottom, paint);
                }
                canvas.drawRect(rectF.left, rectF.top + f, rectF.right, rectF.bottom - f, paint);
            }
        };
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void initialize(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        RoundRectDrawableWithShadow createBackground = createBackground(context, colorStateList, f, f2, f3);
        createBackground.setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        cardViewDelegate.setCardBackground(createBackground);
        updatePadding(cardViewDelegate);
    }

    private RoundRectDrawableWithShadow createBackground(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new RoundRectDrawableWithShadow(context.getResources(), colorStateList, f, f2, f3);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void updatePadding(CardViewDelegate cardViewDelegate) {
        Rect rect = new Rect();
        getShadowBackground(cardViewDelegate).getMaxShadowAndCornerPadding(rect);
        cardViewDelegate.setMinWidthHeightInternal((int) Math.ceil(getMinWidth(cardViewDelegate)), (int) Math.ceil(getMinHeight(cardViewDelegate)));
        cardViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate) {
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate) {
        getShadowBackground(cardViewDelegate).setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        updatePadding(cardViewDelegate);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void setBackgroundColor(CardViewDelegate cardViewDelegate, @Nullable ColorStateList colorStateList) {
        getShadowBackground(cardViewDelegate).setColor(colorStateList);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public ColorStateList getBackgroundColor(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getColor();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void setRadius(CardViewDelegate cardViewDelegate, float f) {
        getShadowBackground(cardViewDelegate).setCornerRadius(f);
        updatePadding(cardViewDelegate);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getRadius(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getCornerRadius();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void setElevation(CardViewDelegate cardViewDelegate, float f) {
        getShadowBackground(cardViewDelegate).setShadowSize(f);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getElevation(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getShadowSize();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void setMaxElevation(CardViewDelegate cardViewDelegate, float f) {
        getShadowBackground(cardViewDelegate).setMaxShadowSize(f);
        updatePadding(cardViewDelegate);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getMaxElevation(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMaxShadowSize();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getMinWidth(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMinWidth();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getMinHeight(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMinHeight();
    }

    private RoundRectDrawableWithShadow getShadowBackground(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawableWithShadow) cardViewDelegate.getCardBackground();
    }
}
