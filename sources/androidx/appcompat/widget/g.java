package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.appcompat.R;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
class g extends e {

    /* renamed from: d, reason: collision with root package name */
    private final SeekBar f215d;
    private Drawable e;
    private ColorStateList f;
    private PorterDuff.Mode g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f216i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.f216i = false;
        this.f215d = seekBar;
    }

    private void f() {
        Drawable drawable = this.e;
        if (drawable != null) {
            if (this.h || this.f216i) {
                Drawable wrap = DrawableCompat.wrap(drawable.mutate());
                this.e = wrap;
                if (this.h) {
                    DrawableCompat.setTintList(wrap, this.f);
                }
                if (this.f216i) {
                    DrawableCompat.setTintMode(this.e, this.g);
                }
                if (this.e.isStateful()) {
                    this.e.setState(this.f215d.getDrawableState());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.widget.e
    public void c(AttributeSet attributeSet, int i2) {
        super.c(attributeSet, i2);
        Context context = this.f215d.getContext();
        int[] iArr = R.styleable.AppCompatSeekBar;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i2, 0);
        SeekBar seekBar = this.f215d;
        ViewCompat.saveAttributeDataForStyleable(seekBar, seekBar.getContext(), iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i2, 0);
        Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(R.styleable.AppCompatSeekBar_android_thumb);
        if (drawableIfKnown != null) {
            this.f215d.setThumb(drawableIfKnown);
        }
        j(obtainStyledAttributes.getDrawable(R.styleable.AppCompatSeekBar_tickMark));
        int i3 = R.styleable.AppCompatSeekBar_tickMarkTintMode;
        if (obtainStyledAttributes.hasValue(i3)) {
            this.g = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i3, -1), this.g);
            this.f216i = true;
        }
        int i4 = R.styleable.AppCompatSeekBar_tickMarkTint;
        if (obtainStyledAttributes.hasValue(i4)) {
            this.f = obtainStyledAttributes.getColorStateList(i4);
            this.h = true;
        }
        obtainStyledAttributes.recycle();
        f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(Canvas canvas) {
        if (this.e != null) {
            int max = this.f215d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.e.getIntrinsicWidth();
                int intrinsicHeight = this.e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.e.setBounds(-i2, -i3, i2, i3);
                float width = ((this.f215d.getWidth() - this.f215d.getPaddingLeft()) - this.f215d.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(this.f215d.getPaddingLeft(), this.f215d.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        Drawable drawable = this.e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f215d.getDrawableState())) {
            this.f215d.invalidateDrawable(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(@Nullable Drawable drawable) {
        Drawable drawable2 = this.e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f215d);
            DrawableCompat.setLayoutDirection(drawable, ViewCompat.getLayoutDirection(this.f215d));
            if (drawable.isStateful()) {
                drawable.setState(this.f215d.getDrawableState());
            }
            f();
        }
        this.f215d.invalidate();
    }
}
