package com.google.android.material.floatingactionbutton;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class c extends Drawable {

    @NonNull
    private final Paint b;

    @Dimension
    float h;

    /* renamed from: i, reason: collision with root package name */
    @ColorInt
    private int f1577i;

    @ColorInt
    private int j;

    @ColorInt
    private int k;

    @ColorInt
    private int l;

    @ColorInt
    private int m;
    private ShapeAppearanceModel o;

    @Nullable
    private ColorStateList p;
    private final ShapeAppearancePathProvider a = ShapeAppearancePathProvider.getInstance();

    /* renamed from: c, reason: collision with root package name */
    private final Path f1575c = new Path();

    /* renamed from: d, reason: collision with root package name */
    private final Rect f1576d = new Rect();
    private final RectF e = new RectF();
    private final RectF f = new RectF();
    private final b g = new b();
    private boolean n = true;

    /* loaded from: classes.dex */
    private class b extends Drawable.ConstantState {
        private b() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return c.this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(ShapeAppearanceModel shapeAppearanceModel) {
        this.o = shapeAppearanceModel;
        Paint paint = new Paint(1);
        this.b = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    @NonNull
    private Shader a() {
        copyBounds(this.f1576d);
        float height = this.h / r0.height();
        return new LinearGradient(0.0f, r0.top, 0.0f, r0.bottom, new int[]{ColorUtils.compositeColors(this.f1577i, this.m), ColorUtils.compositeColors(this.j, this.m), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.j, 0), this.m), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.l, 0), this.m), ColorUtils.compositeColors(this.l, this.m), ColorUtils.compositeColors(this.k, this.m)}, new float[]{0.0f, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, Shader.TileMode.CLAMP);
    }

    @NonNull
    protected RectF b() {
        this.f.set(getBounds());
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(@Nullable ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.m = colorStateList.getColorForState(getState(), this.m);
        }
        this.p = colorStateList;
        this.n = true;
        invalidateSelf();
    }

    public void d(@Dimension float f) {
        if (this.h != f) {
            this.h = f;
            this.b.setStrokeWidth(f * 1.3333f);
            this.n = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (this.n) {
            this.b.setShader(a());
            this.n = false;
        }
        float strokeWidth = this.b.getStrokeWidth() / 2.0f;
        copyBounds(this.f1576d);
        this.e.set(this.f1576d);
        float min = Math.min(this.o.getTopLeftCornerSize().getCornerSize(b()), this.e.width() / 2.0f);
        if (this.o.isRoundRect(b())) {
            this.e.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(this.e, min, min, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@ColorInt int i2, @ColorInt int i3, @ColorInt int i4, @ColorInt int i5) {
        this.f1577i = i2;
        this.j = i3;
        this.k = i4;
        this.l = i5;
    }

    public void f(ShapeAppearanceModel shapeAppearanceModel) {
        this.o = shapeAppearanceModel;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public Drawable.ConstantState getConstantState() {
        return this.g;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.h > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(@NonNull Outline outline) {
        if (this.o.isRoundRect(b())) {
            outline.setRoundRect(getBounds(), this.o.getTopLeftCornerSize().getCornerSize(b()));
            return;
        }
        copyBounds(this.f1576d);
        this.e.set(this.f1576d);
        this.a.calculatePath(this.o, 1.0f, this.e, this.f1575c);
        if (this.f1575c.isConvex()) {
            outline.setConvexPath(this.f1575c);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(@NonNull Rect rect) {
        if (!this.o.isRoundRect(b())) {
            return true;
        }
        int round = Math.round(this.h);
        rect.set(round, round, round, round);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.n = true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.p;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.m)) != this.m) {
            this.n = true;
            this.m = colorForState;
        }
        if (this.n) {
            invalidateSelf();
        }
        return this.n;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = 0, to = 255) int i2) {
        this.b.setAlpha(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
