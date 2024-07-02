package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.color.MaterialColors;

/* loaded from: classes.dex */
public final class f extends d<LinearProgressIndicatorSpec> {

    /* renamed from: c */
    private float f1640c;

    /* renamed from: d */
    private float f1641d;
    private float e;

    public f(@NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
        this.f1640c = 300.0f;
    }

    private static void h(Canvas canvas, Paint paint, float f, float f2, float f3, boolean z, RectF rectF) {
        canvas.save();
        canvas.translate(f3, 0.0f);
        if (!z) {
            canvas.rotate(180.0f);
        }
        float f4 = ((-f) / 2.0f) + f2;
        float f5 = (f / 2.0f) - f2;
        canvas.drawRect(-f2, f4, 0.0f, f5, paint);
        canvas.save();
        canvas.translate(0.0f, f4);
        canvas.drawArc(rectF, 180.0f, 90.0f, true, paint);
        canvas.restore();
        canvas.translate(0.0f, f5);
        canvas.drawArc(rectF, 180.0f, -90.0f, true, paint);
        canvas.restore();
    }

    @Override // com.google.android.material.progressindicator.d
    public void a(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        Rect clipBounds = canvas.getClipBounds();
        this.f1640c = clipBounds.width();
        float f2 = ((LinearProgressIndicatorSpec) this.a).trackThickness;
        canvas.translate(clipBounds.left + (clipBounds.width() / 2.0f), clipBounds.top + (clipBounds.height() / 2.0f) + Math.max(0.0f, (clipBounds.height() - ((LinearProgressIndicatorSpec) this.a).trackThickness) / 2.0f));
        if (((LinearProgressIndicatorSpec) this.a).a) {
            canvas.scale(-1.0f, 1.0f);
        }
        if ((this.b.isShowing() && ((LinearProgressIndicatorSpec) this.a).showAnimationBehavior == 1) || (this.b.isHiding() && ((LinearProgressIndicatorSpec) this.a).hideAnimationBehavior == 2)) {
            canvas.scale(1.0f, -1.0f);
        }
        if (this.b.isShowing() || this.b.isHiding()) {
            canvas.translate(0.0f, (((LinearProgressIndicatorSpec) this.a).trackThickness * (f - 1.0f)) / 2.0f);
        }
        float f3 = this.f1640c;
        canvas.clipRect((-f3) / 2.0f, (-f2) / 2.0f, f3 / 2.0f, f2 / 2.0f);
        S s = this.a;
        this.f1641d = ((LinearProgressIndicatorSpec) s).trackThickness * f;
        this.e = ((LinearProgressIndicatorSpec) s).trackCornerRadius * f;
    }

    @Override // com.google.android.material.progressindicator.d
    public void b(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @ColorInt int i2) {
        if (f == f2) {
            return;
        }
        float f3 = this.f1640c;
        float f4 = this.e;
        float f5 = ((-f3) / 2.0f) + f4 + ((f3 - (f4 * 2.0f)) * f);
        float f6 = ((-f3) / 2.0f) + f4 + ((f3 - (f4 * 2.0f)) * f2);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(i2);
        float f7 = this.f1641d;
        canvas.drawRect(f5, (-f7) / 2.0f, f6, f7 / 2.0f, paint);
        float f8 = this.e;
        RectF rectF = new RectF(-f8, -f8, f8, f8);
        h(canvas, paint, this.f1641d, this.e, f5, true, rectF);
        h(canvas, paint, this.f1641d, this.e, f6, false, rectF);
    }

    @Override // com.google.android.material.progressindicator.d
    public void c(@NonNull Canvas canvas, @NonNull Paint paint) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((LinearProgressIndicatorSpec) this.a).trackColor, this.b.getAlpha());
        float f = ((-this.f1640c) / 2.0f) + this.e;
        float f2 = -f;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        float f3 = this.f1641d;
        canvas.drawRect(f, (-f3) / 2.0f, f2, f3 / 2.0f, paint);
        float f4 = this.e;
        RectF rectF = new RectF(-f4, -f4, f4, f4);
        h(canvas, paint, this.f1641d, this.e, f, true, rectF);
        h(canvas, paint, this.f1641d, this.e, f2, false, rectF);
    }

    @Override // com.google.android.material.progressindicator.d
    public int d() {
        return ((LinearProgressIndicatorSpec) this.a).trackThickness;
    }

    @Override // com.google.android.material.progressindicator.d
    public int e() {
        return -1;
    }
}
