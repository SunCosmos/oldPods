package com.google.android.material.imageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.Shapeable;

/* loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements Shapeable {
    private static final int s = R.style.Widget_MaterialComponents_ShapeableImageView;
    private final ShapeAppearancePathProvider a;
    private final RectF b;

    /* renamed from: c, reason: collision with root package name */
    private final RectF f1587c;

    /* renamed from: d, reason: collision with root package name */
    private final Paint f1588d;
    private final Paint e;
    private final Path f;

    @Nullable
    private ColorStateList g;

    @Nullable
    private MaterialShapeDrawable h;

    /* renamed from: i, reason: collision with root package name */
    private ShapeAppearanceModel f1589i;

    @Dimension
    private float j;
    private Path k;

    @Dimension
    private int l;

    @Dimension
    private int m;

    @Dimension
    private int n;

    @Dimension
    private int o;

    @Dimension
    private int p;

    @Dimension
    private int q;
    private boolean r;

    @TargetApi(21)
    /* loaded from: classes.dex */
    class a extends ViewOutlineProvider {
        private final Rect a = new Rect();

        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.f1589i == null) {
                return;
            }
            if (ShapeableImageView.this.h == null) {
                ShapeableImageView.this.h = new MaterialShapeDrawable(ShapeableImageView.this.f1589i);
            }
            ShapeableImageView.this.b.round(this.a);
            ShapeableImageView.this.h.setBounds(this.a);
            ShapeableImageView.this.h.getOutline(outline);
        }
    }

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public ShapeableImageView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ShapeableImageView(android.content.Context r7, @androidx.annotation.Nullable android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            int r0 = com.google.android.material.imageview.ShapeableImageView.s
            android.content.Context r7 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r7, r8, r9, r0)
            r6.<init>(r7, r8, r9)
            com.google.android.material.shape.ShapeAppearancePathProvider r7 = com.google.android.material.shape.ShapeAppearancePathProvider.getInstance()
            r6.a = r7
            android.graphics.Path r7 = new android.graphics.Path
            r7.<init>()
            r6.f = r7
            r7 = 0
            r6.r = r7
            android.content.Context r1 = r6.getContext()
            android.graphics.Paint r2 = new android.graphics.Paint
            r2.<init>()
            r6.e = r2
            r3 = 1
            r2.setAntiAlias(r3)
            r4 = -1
            r2.setColor(r4)
            android.graphics.PorterDuffXfermode r4 = new android.graphics.PorterDuffXfermode
            android.graphics.PorterDuff$Mode r5 = android.graphics.PorterDuff.Mode.DST_OUT
            r4.<init>(r5)
            r2.setXfermode(r4)
            android.graphics.RectF r2 = new android.graphics.RectF
            r2.<init>()
            r6.b = r2
            android.graphics.RectF r2 = new android.graphics.RectF
            r2.<init>()
            r6.f1587c = r2
            android.graphics.Path r2 = new android.graphics.Path
            r2.<init>()
            r6.k = r2
            int[] r2 = com.google.android.material.R.styleable.ShapeableImageView
            android.content.res.TypedArray r2 = r1.obtainStyledAttributes(r8, r2, r9, r0)
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_strokeColor
            android.content.res.ColorStateList r4 = com.google.android.material.resources.MaterialResources.getColorStateList(r1, r2, r4)
            r6.g = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_strokeWidth
            int r4 = r2.getDimensionPixelSize(r4, r7)
            float r4 = (float) r4
            r6.j = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPadding
            int r7 = r2.getDimensionPixelSize(r4, r7)
            r6.l = r7
            r6.m = r7
            r6.n = r7
            r6.o = r7
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingLeft
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.l = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingTop
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.m = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingRight
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.n = r4
            int r4 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingBottom
            int r7 = r2.getDimensionPixelSize(r4, r7)
            r6.o = r7
            int r7 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingStart
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            int r7 = r2.getDimensionPixelSize(r7, r4)
            r6.p = r7
            int r7 = com.google.android.material.R.styleable.ShapeableImageView_contentPaddingEnd
            int r7 = r2.getDimensionPixelSize(r7, r4)
            r6.q = r7
            r2.recycle()
            android.graphics.Paint r7 = new android.graphics.Paint
            r7.<init>()
            r6.f1588d = r7
            android.graphics.Paint$Style r2 = android.graphics.Paint.Style.STROKE
            r7.setStyle(r2)
            r7.setAntiAlias(r3)
            com.google.android.material.shape.ShapeAppearanceModel$Builder r7 = com.google.android.material.shape.ShapeAppearanceModel.builder(r1, r8, r9, r0)
            com.google.android.material.shape.ShapeAppearanceModel r7 = r7.build()
            r6.f1589i = r7
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 21
            if (r7 < r8) goto Lcc
            com.google.android.material.imageview.ShapeableImageView$a r7 = new com.google.android.material.imageview.ShapeableImageView$a
            r7.<init>()
            r6.setOutlineProvider(r7)
        Lcc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.imageview.ShapeableImageView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private void e(Canvas canvas) {
        if (this.g == null) {
            return;
        }
        this.f1588d.setStrokeWidth(this.j);
        int colorForState = this.g.getColorForState(getDrawableState(), this.g.getDefaultColor());
        if (this.j <= 0.0f || colorForState == 0) {
            return;
        }
        this.f1588d.setColor(colorForState);
        canvas.drawPath(this.f, this.f1588d);
    }

    private boolean f() {
        return (this.p == Integer.MIN_VALUE && this.q == Integer.MIN_VALUE) ? false : true;
    }

    private boolean g() {
        return Build.VERSION.SDK_INT >= 17 && getLayoutDirection() == 1;
    }

    private void h(int i2, int i3) {
        this.b.set(getPaddingLeft(), getPaddingTop(), i2 - getPaddingRight(), i3 - getPaddingBottom());
        this.a.calculatePath(this.f1589i, 1.0f, this.b, this.f);
        this.k.rewind();
        this.k.addPath(this.f);
        this.f1587c.set(0.0f, 0.0f, i2, i3);
        this.k.addRect(this.f1587c, Path.Direction.CCW);
    }

    @Dimension
    public int getContentPaddingBottom() {
        return this.o;
    }

    @Dimension
    public final int getContentPaddingEnd() {
        int i2 = this.q;
        return i2 != Integer.MIN_VALUE ? i2 : g() ? this.l : this.n;
    }

    @Dimension
    public int getContentPaddingLeft() {
        int i2;
        int i3;
        if (f()) {
            if (g() && (i3 = this.q) != Integer.MIN_VALUE) {
                return i3;
            }
            if (!g() && (i2 = this.p) != Integer.MIN_VALUE) {
                return i2;
            }
        }
        return this.l;
    }

    @Dimension
    public int getContentPaddingRight() {
        int i2;
        int i3;
        if (f()) {
            if (g() && (i3 = this.p) != Integer.MIN_VALUE) {
                return i3;
            }
            if (!g() && (i2 = this.q) != Integer.MIN_VALUE) {
                return i2;
            }
        }
        return this.n;
    }

    @Dimension
    public final int getContentPaddingStart() {
        int i2 = this.p;
        return i2 != Integer.MIN_VALUE ? i2 : g() ? this.n : this.l;
    }

    @Dimension
    public int getContentPaddingTop() {
        return this.m;
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingBottom() {
        return super.getPaddingBottom() - getContentPaddingBottom();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingEnd() {
        return super.getPaddingEnd() - getContentPaddingEnd();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingLeft() {
        return super.getPaddingLeft() - getContentPaddingLeft();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingRight() {
        return super.getPaddingRight() - getContentPaddingRight();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingStart() {
        return super.getPaddingStart() - getContentPaddingStart();
    }

    @Override // android.view.View
    @Dimension
    public int getPaddingTop() {
        return super.getPaddingTop() - getContentPaddingTop();
    }

    @Override // com.google.android.material.shape.Shapeable
    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.f1589i;
    }

    @Nullable
    public ColorStateList getStrokeColor() {
        return this.g;
    }

    @Dimension
    public float getStrokeWidth() {
        return this.j;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLayerType(2, null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        setLayerType(0, null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.k, this.e);
        e(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.r) {
            return;
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 <= 19 || isLayoutDirectionResolved()) {
            this.r = true;
            if (i4 < 21 || !(isPaddingRelative() || f())) {
                setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
            } else {
                setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        h(i2, i3);
    }

    public void setContentPadding(@Dimension int i2, @Dimension int i3, @Dimension int i4, @Dimension int i5) {
        this.p = Integer.MIN_VALUE;
        this.q = Integer.MIN_VALUE;
        super.setPadding((super.getPaddingLeft() - this.l) + i2, (super.getPaddingTop() - this.m) + i3, (super.getPaddingRight() - this.n) + i4, (super.getPaddingBottom() - this.o) + i5);
        this.l = i2;
        this.m = i3;
        this.n = i4;
        this.o = i5;
    }

    @RequiresApi(17)
    public void setContentPaddingRelative(@Dimension int i2, @Dimension int i3, @Dimension int i4, @Dimension int i5) {
        super.setPaddingRelative((super.getPaddingStart() - getContentPaddingStart()) + i2, (super.getPaddingTop() - this.m) + i3, (super.getPaddingEnd() - getContentPaddingEnd()) + i4, (super.getPaddingBottom() - this.o) + i5);
        this.l = g() ? i4 : i2;
        this.m = i3;
        if (!g()) {
            i2 = i4;
        }
        this.n = i2;
        this.o = i5;
    }

    @Override // android.view.View
    public void setPadding(@Dimension int i2, @Dimension int i3, @Dimension int i4, @Dimension int i5) {
        super.setPadding(i2 + getContentPaddingLeft(), i3 + getContentPaddingTop(), i4 + getContentPaddingRight(), i5 + getContentPaddingBottom());
    }

    @Override // android.view.View
    public void setPaddingRelative(@Dimension int i2, @Dimension int i3, @Dimension int i4, @Dimension int i5) {
        super.setPaddingRelative(i2 + getContentPaddingStart(), i3 + getContentPaddingTop(), i4 + getContentPaddingEnd(), i5 + getContentPaddingBottom());
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.f1589i = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.h;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        h(getWidth(), getHeight());
        invalidate();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        this.g = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(@ColorRes int i2) {
        setStrokeColor(AppCompatResources.getColorStateList(getContext(), i2));
    }

    public void setStrokeWidth(@Dimension float f) {
        if (this.j != f) {
            this.j = f;
            invalidate();
        }
    }

    public void setStrokeWidthResource(@DimenRes int i2) {
        setStrokeWidth(getResources().getDimensionPixelSize(i2));
    }
}
