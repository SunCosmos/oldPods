package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* loaded from: lib/Mus.dex */
class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
    private boolean mHasTickMarkTint;
    private boolean mHasTickMarkTintMode;
    private Drawable mTickMark;
    private ColorStateList mTickMarkTintList;
    private PorterDuff.Mode mTickMarkTintMode;
    private final SeekBar mView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppCompatSeekBarHelper(SeekBar seekBar) {
        super(seekBar);
        this.mTickMarkTintList = null;
        this.mTickMarkTintMode = null;
        this.mHasTickMarkTint = false;
        this.mHasTickMarkTintMode = false;
        this.mView = seekBar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.widget.AppCompatProgressBarHelper
    public void loadFromAttributes(AttributeSet attributeSet, int i2) {
        super.loadFromAttributes(attributeSet, i2);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, R.styleable.AppCompatSeekBar, i2, 0);
        Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(R.styleable.AppCompatSeekBar_android_thumb);
        if (drawableIfKnown != null) {
            this.mView.setThumb(drawableIfKnown);
        }
        setTickMark(obtainStyledAttributes.getDrawable(R.styleable.AppCompatSeekBar_tickMark));
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatSeekBar_tickMarkTintMode)) {
            this.mTickMarkTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.mTickMarkTintMode);
            this.mHasTickMarkTintMode = true;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatSeekBar_tickMarkTint)) {
            this.mTickMarkTintList = obtainStyledAttributes.getColorStateList(R.styleable.AppCompatSeekBar_tickMarkTint);
            this.mHasTickMarkTint = true;
        }
        obtainStyledAttributes.recycle();
        applyTickMarkTint();
    }

    void setTickMark(@Nullable Drawable drawable) {
        if (this.mTickMark != null) {
            this.mTickMark.setCallback(null);
        }
        this.mTickMark = drawable;
        if (drawable != null) {
            drawable.setCallback(this.mView);
            DrawableCompat.setLayoutDirection(drawable, ViewCompat.getLayoutDirection(this.mView));
            if (drawable.isStateful()) {
                drawable.setState(this.mView.getDrawableState());
            }
            applyTickMarkTint();
        }
        this.mView.invalidate();
    }

    @Nullable
    Drawable getTickMark() {
        return this.mTickMark;
    }

    void setTickMarkTintList(@Nullable ColorStateList colorStateList) {
        this.mTickMarkTintList = colorStateList;
        this.mHasTickMarkTint = true;
        applyTickMarkTint();
    }

    @Nullable
    ColorStateList getTickMarkTintList() {
        return this.mTickMarkTintList;
    }

    void setTickMarkTintMode(@Nullable PorterDuff.Mode mode) {
        this.mTickMarkTintMode = mode;
        this.mHasTickMarkTintMode = true;
        applyTickMarkTint();
    }

    @Nullable
    PorterDuff.Mode getTickMarkTintMode() {
        return this.mTickMarkTintMode;
    }

    private void applyTickMarkTint() {
        if (this.mTickMark != null) {
            if (this.mHasTickMarkTint || this.mHasTickMarkTintMode) {
                this.mTickMark = DrawableCompat.wrap(this.mTickMark.mutate());
                if (this.mHasTickMarkTint) {
                    DrawableCompat.setTintList(this.mTickMark, this.mTickMarkTintList);
                }
                if (this.mHasTickMarkTintMode) {
                    DrawableCompat.setTintMode(this.mTickMark, this.mTickMarkTintMode);
                }
                if (this.mTickMark.isStateful()) {
                    this.mTickMark.setState(this.mView.getDrawableState());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(11)
    public void jumpDrawablesToCurrentState() {
        if (this.mTickMark != null) {
            this.mTickMark.jumpToCurrentState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void drawableStateChanged() {
        Drawable drawable = this.mTickMark;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.mView.getDrawableState())) {
            this.mView.invalidateDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void drawTickMarks(Canvas canvas) {
        int max;
        if (this.mTickMark != null && (max = this.mView.getMax()) > 1) {
            int intrinsicWidth = this.mTickMark.getIntrinsicWidth();
            int intrinsicHeight = this.mTickMark.getIntrinsicHeight();
            int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
            int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
            this.mTickMark.setBounds(-i2, -i3, i2, i3);
            float width = ((this.mView.getWidth() - this.mView.getPaddingLeft()) - this.mView.getPaddingRight()) / max;
            int save = canvas.save();
            canvas.translate(this.mView.getPaddingLeft(), this.mView.getHeight() / 2);
            for (int i4 = 0; i4 <= max; i4++) {
                this.mTickMark.draw(canvas);
                canvas.translate(width, 0.0f);
            }
            canvas.restoreToCount(save);
        }
    }
}
