package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.color.MaterialColors;

/* loaded from: classes.dex */
public final class a extends d<CircularProgressIndicatorSpec> {

    /* renamed from: c */
    private int f1632c;

    /* renamed from: d */
    private float f1633d;
    private float e;
    private float f;

    public a(@NonNull CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
        this.f1632c = 1;
    }

    private void h(Canvas canvas, Paint paint, float f, float f2, float f3, boolean z, RectF rectF) {
        float f4 = z ? -1.0f : 1.0f;
        canvas.save();
        canvas.rotate(f3);
        float f5 = f / 2.0f;
        float f6 = f4 * f2;
        canvas.drawRect((this.f - f5) + f2, Math.min(0.0f, this.f1632c * f6), (this.f + f5) - f2, Math.max(0.0f, f6 * this.f1632c), paint);
        canvas.translate((this.f - f5) + f2, 0.0f);
        canvas.drawArc(rectF, 180.0f, (-f4) * 90.0f * this.f1632c, true, paint);
        canvas.translate(f - (f2 * 2.0f), 0.0f);
        canvas.drawArc(rectF, 0.0f, f4 * 90.0f * this.f1632c, true, paint);
        canvas.restore();
    }

    private int i() {
        S s = this.a;
        return ((CircularProgressIndicatorSpec) s).indicatorSize + (((CircularProgressIndicatorSpec) s).indicatorInset * 2);
    }

    @Override // com.google.android.material.progressindicator.d
    public void a(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        float f2;
        S s = this.a;
        float f3 = (((CircularProgressIndicatorSpec) s).indicatorSize / 2.0f) + ((CircularProgressIndicatorSpec) s).indicatorInset;
        canvas.translate(f3, f3);
        canvas.rotate(-90.0f);
        float f4 = -f3;
        canvas.clipRect(f4, f4, f3, f3);
        this.f1632c = ((CircularProgressIndicatorSpec) this.a).indicatorDirection == 0 ? 1 : -1;
        this.f1633d = ((CircularProgressIndicatorSpec) r5).trackThickness * f;
        this.e = ((CircularProgressIndicatorSpec) r5).trackCornerRadius * f;
        this.f = (((CircularProgressIndicatorSpec) r5).indicatorSize - ((CircularProgressIndicatorSpec) r5).trackThickness) / 2.0f;
        if ((this.b.isShowing() && ((CircularProgressIndicatorSpec) this.a).showAnimationBehavior == 2) || (this.b.isHiding() && ((CircularProgressIndicatorSpec) this.a).hideAnimationBehavior == 1)) {
            f2 = this.f + (((1.0f - f) * ((CircularProgressIndicatorSpec) this.a).trackThickness) / 2.0f);
        } else if ((!this.b.isShowing() || ((CircularProgressIndicatorSpec) this.a).showAnimationBehavior != 1) && (!this.b.isHiding() || ((CircularProgressIndicatorSpec) this.a).hideAnimationBehavior != 2)) {
            return;
        } else {
            f2 = this.f - (((1.0f - f) * ((CircularProgressIndicatorSpec) this.a).trackThickness) / 2.0f);
        }
        this.f = f2;
    }

    @Override // com.google.android.material.progressindicator.d
    public void b(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @ColorInt int i2) {
        if (f == f2) {
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(i2);
        paint.setStrokeWidth(this.f1633d);
        int i3 = this.f1632c;
        float f3 = f * 360.0f * i3;
        float f4 = (f2 >= f ? f2 - f : (f2 + 1.0f) - f) * 360.0f * i3;
        float f5 = this.f;
        canvas.drawArc(new RectF(-f5, -f5, f5, f5), f3, f4, false, paint);
        if (this.e <= 0.0f || Math.abs(f4) >= 360.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        float f6 = this.e;
        RectF rectF = new RectF(-f6, -f6, f6, f6);
        h(canvas, paint, this.f1633d, this.e, f3, true, rectF);
        h(canvas, paint, this.f1633d, this.e, f3 + f4, false, rectF);
    }

    @Override // com.google.android.material.progressindicator.d
    public void c(@NonNull Canvas canvas, @NonNull Paint paint) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((CircularProgressIndicatorSpec) this.a).trackColor, this.b.getAlpha());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        paint.setStrokeWidth(this.f1633d);
        float f = this.f;
        canvas.drawArc(new RectF(-f, -f, f, f), 0.0f, 360.0f, false, paint);
    }

    @Override // com.google.android.material.progressindicator.d
    public int d() {
        return i();
    }

    @Override // com.google.android.material.progressindicator.d
    public int e() {
        return i();
    }
}
