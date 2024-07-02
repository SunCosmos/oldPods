package com.google.android.material.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;

@Deprecated
/* loaded from: classes.dex */
public class ShadowDrawableWrapper extends DrawableWrapper {
    static final double q = Math.cos(Math.toRadians(45.0d));

    @NonNull
    final Paint b;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    final Paint f1653c;

    /* renamed from: d, reason: collision with root package name */
    @NonNull
    final RectF f1654d;
    float e;
    Path f;
    float g;
    float h;

    /* renamed from: i, reason: collision with root package name */
    float f1655i;
    private boolean j;
    private final int k;
    private final int l;
    private final int m;
    private boolean n;
    private float o;
    private boolean p;

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f, float f2, float f3) {
        super(drawable);
        this.j = true;
        this.n = true;
        this.p = false;
        this.k = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.l = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.m = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        Paint paint = new Paint(5);
        this.b = paint;
        paint.setStyle(Paint.Style.FILL);
        this.e = Math.round(f);
        this.f1654d = new RectF();
        Paint paint2 = new Paint(paint);
        this.f1653c = paint2;
        paint2.setAntiAlias(false);
        setShadowSize(f2, f3);
    }

    private void a(@NonNull Rect rect) {
        float f = this.g;
        float f2 = 1.5f * f;
        this.f1654d.set(rect.left + f, rect.top + f2, rect.right - f, rect.bottom - f2);
        Drawable wrappedDrawable = getWrappedDrawable();
        RectF rectF = this.f1654d;
        wrappedDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        b();
    }

    private void b() {
        float f = this.e;
        RectF rectF = new RectF(-f, -f, f, f);
        RectF rectF2 = new RectF(rectF);
        float f2 = this.h;
        rectF2.inset(-f2, -f2);
        Path path = this.f;
        if (path == null) {
            this.f = new Path();
        } else {
            path.reset();
        }
        this.f.setFillType(Path.FillType.EVEN_ODD);
        this.f.moveTo(-this.e, 0.0f);
        this.f.rLineTo(-this.h, 0.0f);
        this.f.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f.arcTo(rectF, 270.0f, -90.0f, false);
        this.f.close();
        float f3 = -rectF2.top;
        if (f3 > 0.0f) {
            float f4 = this.e / f3;
            this.b.setShader(new RadialGradient(0.0f, 0.0f, f3, new int[]{0, this.k, this.l, this.m}, new float[]{0.0f, f4, ((1.0f - f4) / 2.0f) + f4, 1.0f}, Shader.TileMode.CLAMP));
        }
        this.f1653c.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.k, this.l, this.m}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f1653c.setAntiAlias(false);
    }

    private void c(@NonNull Canvas canvas) {
        int i2;
        float f;
        int i3;
        float f2;
        float f3;
        float f4;
        int save = canvas.save();
        canvas.rotate(this.o, this.f1654d.centerX(), this.f1654d.centerY());
        float f5 = this.e;
        float f6 = (-f5) - this.h;
        float f7 = f5 * 2.0f;
        boolean z = this.f1654d.width() - f7 > 0.0f;
        boolean z2 = this.f1654d.height() - f7 > 0.0f;
        float f8 = this.f1655i;
        float f9 = f5 / ((f8 - (0.5f * f8)) + f5);
        float f10 = f5 / ((f8 - (0.25f * f8)) + f5);
        float f11 = f5 / ((f8 - (f8 * 1.0f)) + f5);
        int save2 = canvas.save();
        RectF rectF = this.f1654d;
        canvas.translate(rectF.left + f5, rectF.top + f5);
        canvas.scale(f9, f10);
        canvas.drawPath(this.f, this.b);
        if (z) {
            canvas.scale(1.0f / f9, 1.0f);
            i2 = save2;
            f = f11;
            i3 = save;
            f2 = f10;
            canvas.drawRect(0.0f, f6, this.f1654d.width() - f7, -this.e, this.f1653c);
        } else {
            i2 = save2;
            f = f11;
            i3 = save;
            f2 = f10;
        }
        canvas.restoreToCount(i2);
        int save3 = canvas.save();
        RectF rectF2 = this.f1654d;
        canvas.translate(rectF2.right - f5, rectF2.bottom - f5);
        float f12 = f;
        canvas.scale(f9, f12);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f, this.b);
        if (z) {
            canvas.scale(1.0f / f9, 1.0f);
            f3 = f2;
            f4 = f12;
            canvas.drawRect(0.0f, f6, this.f1654d.width() - f7, (-this.e) + this.h, this.f1653c);
        } else {
            f3 = f2;
            f4 = f12;
        }
        canvas.restoreToCount(save3);
        int save4 = canvas.save();
        RectF rectF3 = this.f1654d;
        canvas.translate(rectF3.left + f5, rectF3.bottom - f5);
        canvas.scale(f9, f4);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f, this.b);
        if (z2) {
            canvas.scale(1.0f / f4, 1.0f);
            canvas.drawRect(0.0f, f6, this.f1654d.height() - f7, -this.e, this.f1653c);
        }
        canvas.restoreToCount(save4);
        int save5 = canvas.save();
        RectF rectF4 = this.f1654d;
        canvas.translate(rectF4.right - f5, rectF4.top + f5);
        float f13 = f3;
        canvas.scale(f9, f13);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f, this.b);
        if (z2) {
            canvas.scale(1.0f / f13, 1.0f);
            canvas.drawRect(0.0f, f6, this.f1654d.height() - f7, -this.e, this.f1653c);
        }
        canvas.restoreToCount(save5);
        canvas.restoreToCount(i3);
    }

    public static float calculateHorizontalPadding(float f, float f2, boolean z) {
        if (!z) {
            return f;
        }
        double d2 = f;
        double d3 = 1.0d - q;
        double d4 = f2;
        Double.isNaN(d4);
        Double.isNaN(d2);
        return (float) (d2 + (d3 * d4));
    }

    public static float calculateVerticalPadding(float f, float f2, boolean z) {
        float f3 = f * 1.5f;
        if (!z) {
            return f3;
        }
        double d2 = f3;
        double d3 = 1.0d - q;
        double d4 = f2;
        Double.isNaN(d4);
        Double.isNaN(d2);
        return (float) (d2 + (d3 * d4));
    }

    private static int d(float f) {
        int round = Math.round(f);
        return round % 2 == 1 ? round - 1 : round;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (this.j) {
            a(getBounds());
            this.j = false;
        }
        c(canvas);
        super.draw(canvas);
    }

    public float getCornerRadius() {
        return this.e;
    }

    public float getMaxShadowSize() {
        return this.g;
    }

    public float getMinHeight() {
        float f = this.g;
        return (Math.max(f, this.e + ((f * 1.5f) / 2.0f)) * 2.0f) + (this.g * 1.5f * 2.0f);
    }

    public float getMinWidth() {
        float f = this.g;
        return (Math.max(f, this.e + (f / 2.0f)) * 2.0f) + (this.g * 2.0f);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean getPadding(@NonNull Rect rect) {
        int ceil = (int) Math.ceil(calculateVerticalPadding(this.g, this.e, this.n));
        int ceil2 = (int) Math.ceil(calculateHorizontalPadding(this.g, this.e, this.n));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    public float getShadowSize() {
        return this.f1655i;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.j = true;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.n = z;
        invalidateSelf();
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        super.setAlpha(i2);
        this.b.setAlpha(i2);
        this.f1653c.setAlpha(i2);
    }

    public void setCornerRadius(float f) {
        float round = Math.round(f);
        if (this.e == round) {
            return;
        }
        this.e = round;
        this.j = true;
        invalidateSelf();
    }

    public void setMaxShadowSize(float f) {
        setShadowSize(this.f1655i, f);
    }

    public final void setRotation(float f) {
        if (this.o != f) {
            this.o = f;
            invalidateSelf();
        }
    }

    public void setShadowSize(float f) {
        setShadowSize(f, this.g);
    }

    public void setShadowSize(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float d2 = d(f);
        float d3 = d(f2);
        if (d2 > d3) {
            if (!this.p) {
                this.p = true;
            }
            d2 = d3;
        }
        if (this.f1655i == d2 && this.g == d3) {
            return;
        }
        this.f1655i = d2;
        this.g = d3;
        this.h = Math.round(d2 * 1.5f);
        this.j = true;
        invalidateSelf();
    }
}
