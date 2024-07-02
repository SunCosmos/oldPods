package com.google.android.material.tooltip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.graphics.ColorUtils;
import bsh.org.objectweb.asm.Constants;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MarkerEdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.OffsetEdgeTreatment;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TooltipDrawable extends MaterialShapeDrawable implements TextDrawableHelper.TextDrawableDelegate {

    @StyleRes
    private static final int O = R.style.Widget_MaterialComponents_Tooltip;

    @AttrRes
    private static final int P = R.attr.tooltipStyle;

    @Nullable
    private final Paint.FontMetrics A;

    @NonNull
    private final TextDrawableHelper B;

    @NonNull
    private final View.OnLayoutChangeListener C;

    @NonNull
    private final Rect D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private float K;
    private float L;
    private float M;
    private float N;

    @Nullable
    private CharSequence y;

    @NonNull
    private final Context z;

    /* loaded from: classes.dex */
    public class a implements View.OnLayoutChangeListener {
        a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            TooltipDrawable.this.L(view);
        }
    }

    private TooltipDrawable(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(context, attributeSet, i2, i3);
        this.A = new Paint.FontMetrics();
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.B = textDrawableHelper;
        this.C = new a();
        this.D = new Rect();
        this.K = 1.0f;
        this.L = 1.0f;
        this.M = 0.5f;
        this.N = 1.0f;
        this.z = context;
        textDrawableHelper.getTextPaint().density = context.getResources().getDisplayMetrics().density;
        textDrawableHelper.getTextPaint().setTextAlign(Paint.Align.CENTER);
    }

    private float E() {
        int i2;
        if (((this.D.right - getBounds().right) - this.J) - this.H < 0) {
            i2 = ((this.D.right - getBounds().right) - this.J) - this.H;
        } else {
            if (((this.D.left - getBounds().left) - this.J) + this.H <= 0) {
                return 0.0f;
            }
            i2 = ((this.D.left - getBounds().left) - this.J) + this.H;
        }
        return i2;
    }

    private float F() {
        this.B.getTextPaint().getFontMetrics(this.A);
        Paint.FontMetrics fontMetrics = this.A;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private float G(@NonNull Rect rect) {
        return rect.centerY() - F();
    }

    private EdgeTreatment H() {
        float f = -E();
        double width = getBounds().width();
        double d2 = this.I;
        double sqrt = Math.sqrt(2.0d);
        Double.isNaN(d2);
        Double.isNaN(width);
        float f2 = ((float) (width - (d2 * sqrt))) / 2.0f;
        return new OffsetEdgeTreatment(new MarkerEdgeTreatment(this.I), Math.min(Math.max(f, -f2), f2));
    }

    private void I(@NonNull Canvas canvas) {
        if (this.y == null) {
            return;
        }
        int G = (int) G(getBounds());
        if (this.B.getTextAppearance() != null) {
            this.B.getTextPaint().drawableState = getState();
            this.B.updateTextPaintDrawState(this.z);
            this.B.getTextPaint().setAlpha((int) (this.N * 255.0f));
        }
        CharSequence charSequence = this.y;
        canvas.drawText(charSequence, 0, charSequence.length(), r0.centerX(), G, this.B.getTextPaint());
    }

    private float J() {
        CharSequence charSequence = this.y;
        if (charSequence == null) {
            return 0.0f;
        }
        return this.B.getTextWidth(charSequence.toString());
    }

    private void K(@Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(this.z, attributeSet, R.styleable.Tooltip, i2, i3, new int[0]);
        this.I = this.z.getResources().getDimensionPixelSize(R.dimen.mtrl_tooltip_arrowSize);
        setShapeAppearanceModel(getShapeAppearanceModel().toBuilder().setBottomEdge(H()).build());
        setText(obtainStyledAttributes.getText(R.styleable.Tooltip_android_text));
        setTextAppearance(MaterialResources.getTextAppearance(this.z, obtainStyledAttributes, R.styleable.Tooltip_android_textAppearance));
        setFillColor(ColorStateList.valueOf(obtainStyledAttributes.getColor(R.styleable.Tooltip_backgroundTint, MaterialColors.layer(ColorUtils.setAlphaComponent(MaterialColors.getColor(this.z, android.R.attr.colorBackground, TooltipDrawable.class.getCanonicalName()), 229), ColorUtils.setAlphaComponent(MaterialColors.getColor(this.z, R.attr.colorOnBackground, TooltipDrawable.class.getCanonicalName()), Constants.IFEQ)))));
        setStrokeColor(ColorStateList.valueOf(MaterialColors.getColor(this.z, R.attr.colorSurface, TooltipDrawable.class.getCanonicalName())));
        this.E = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_padding, 0);
        this.F = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_minWidth, 0);
        this.G = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_minHeight, 0);
        this.H = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Tooltip_android_layout_margin, 0);
        obtainStyledAttributes.recycle();
    }

    public void L(@NonNull View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.J = iArr[0];
        view.getWindowVisibleDisplayFrame(this.D);
    }

    @NonNull
    public static TooltipDrawable create(@NonNull Context context) {
        return createFromAttributes(context, null, P, O);
    }

    @NonNull
    public static TooltipDrawable createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        return createFromAttributes(context, attributeSet, P, O);
    }

    @NonNull
    public static TooltipDrawable createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        TooltipDrawable tooltipDrawable = new TooltipDrawable(context, attributeSet, i2, i3);
        tooltipDrawable.K(attributeSet, i2, i3);
        return tooltipDrawable;
    }

    public void detachView(@Nullable View view) {
        if (view == null) {
            return;
        }
        view.removeOnLayoutChangeListener(this.C);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        float E = E();
        double d2 = this.I;
        double sqrt = Math.sqrt(2.0d);
        Double.isNaN(d2);
        double d3 = d2 * sqrt;
        double d4 = this.I;
        Double.isNaN(d4);
        canvas.scale(this.K, this.L, getBounds().left + (getBounds().width() * 0.5f), getBounds().top + (getBounds().height() * this.M));
        canvas.translate(E, (float) (-(d3 - d4)));
        super.draw(canvas);
        I(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) Math.max(this.B.getTextPaint().getTextSize(), this.G);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) Math.max((this.E * 2) + J(), this.F);
    }

    public int getLayoutMargin() {
        return this.H;
    }

    public int getMinHeight() {
        return this.G;
    }

    public int getMinWidth() {
        return this.F;
    }

    @Nullable
    public CharSequence getText() {
        return this.y;
    }

    @Nullable
    public TextAppearance getTextAppearance() {
        return this.B.getTextAppearance();
    }

    public int getTextPadding() {
        return this.E;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setShapeAppearanceModel(getShapeAppearanceModel().toBuilder().setBottomEdge(H()).build());
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        invalidateSelf();
    }

    public void setLayoutMargin(@Px int i2) {
        this.H = i2;
        invalidateSelf();
    }

    public void setMinHeight(@Px int i2) {
        this.G = i2;
        invalidateSelf();
    }

    public void setMinWidth(@Px int i2) {
        this.F = i2;
        invalidateSelf();
    }

    public void setRelativeToView(@Nullable View view) {
        if (view == null) {
            return;
        }
        L(view);
        view.addOnLayoutChangeListener(this.C);
    }

    public void setRevealFraction(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.M = 1.2f;
        this.K = f;
        this.L = f;
        this.N = AnimationUtils.lerp(0.0f, 1.0f, 0.19f, 1.0f, f);
        invalidateSelf();
    }

    public void setText(@Nullable CharSequence charSequence) {
        if (TextUtils.equals(this.y, charSequence)) {
            return;
        }
        this.y = charSequence;
        this.B.setTextWidthDirty(true);
        invalidateSelf();
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance) {
        this.B.setTextAppearance(textAppearance, this.z);
    }

    public void setTextAppearanceResource(@StyleRes int i2) {
        setTextAppearance(new TextAppearance(this.z, i2));
    }

    public void setTextPadding(@Px int i2) {
        this.E = i2;
        invalidateSelf();
    }

    public void setTextResource(@StringRes int i2) {
        setText(this.z.getResources().getString(i2));
    }
}
