package com.google.android.material.chip;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.AnimatorRes;
import androidx.annotation.AttrRes;
import androidx.annotation.BoolRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.XmlRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.text.BidiFormatter;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ChipDrawable extends MaterialShapeDrawable implements TintAwareDrawable, Drawable.Callback, TextDrawableHelper.TextDrawableDelegate {
    private static final int[] I0 = {R.attr.state_enabled};
    private static final ShapeDrawable J0 = new ShapeDrawable(new OvalShape());
    private float A;
    private int[] A0;
    private float B;
    private boolean B0;

    @Nullable
    private ColorStateList C;

    @Nullable
    private ColorStateList C0;
    private float D;

    @NonNull
    private WeakReference<Delegate> D0;

    @Nullable
    private ColorStateList E;
    private TextUtils.TruncateAt E0;

    @Nullable
    private CharSequence F;
    private boolean F0;
    private boolean G;
    private int G0;

    @Nullable
    private Drawable H;
    private boolean H0;

    @Nullable
    private ColorStateList I;
    private float J;
    private boolean K;
    private boolean L;

    @Nullable
    private Drawable M;

    @Nullable
    private Drawable N;

    @Nullable
    private ColorStateList O;
    private float P;

    @Nullable
    private CharSequence Q;
    private boolean R;
    private boolean S;

    @Nullable
    private Drawable T;

    @Nullable
    private ColorStateList U;

    @Nullable
    private MotionSpec V;

    @Nullable
    private MotionSpec W;
    private float X;
    private float Y;
    private float Z;
    private float a0;
    private float b0;
    private float c0;
    private float d0;
    private float e0;

    @NonNull
    private final Context f0;
    private final Paint g0;

    @Nullable
    private final Paint h0;
    private final Paint.FontMetrics i0;
    private final RectF j0;
    private final PointF k0;
    private final Path l0;

    @NonNull
    private final TextDrawableHelper m0;

    @ColorInt
    private int n0;

    @ColorInt
    private int o0;

    @ColorInt
    private int p0;

    @ColorInt
    private int q0;

    @ColorInt
    private int r0;

    @ColorInt
    private int s0;
    private boolean t0;

    @ColorInt
    private int u0;
    private int v0;

    @Nullable
    private ColorFilter w0;

    @Nullable
    private PorterDuffColorFilter x0;

    @Nullable
    private ColorStateList y;

    @Nullable
    private ColorStateList y0;

    @Nullable
    private ColorStateList z;

    @Nullable
    private PorterDuff.Mode z0;

    /* loaded from: classes.dex */
    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    private ChipDrawable(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(context, attributeSet, i2, i3);
        this.B = -1.0f;
        this.g0 = new Paint(1);
        this.i0 = new Paint.FontMetrics();
        this.j0 = new RectF();
        this.k0 = new PointF();
        this.l0 = new Path();
        this.v0 = 255;
        this.z0 = PorterDuff.Mode.SRC_IN;
        this.D0 = new WeakReference<>(null);
        initializeElevationOverlay(context);
        this.f0 = context;
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.m0 = textDrawableHelper;
        this.F = "";
        textDrawableHelper.getTextPaint().density = context.getResources().getDisplayMetrics().density;
        this.h0 = null;
        int[] iArr = I0;
        setState(iArr);
        setCloseIconState(iArr);
        this.F0 = true;
        if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
            J0.setTint(-1);
        }
    }

    private void D(@Nullable Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.M) {
            if (drawable.isStateful()) {
                drawable.setState(getCloseIconState());
            }
            DrawableCompat.setTintList(drawable, this.O);
            return;
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
        Drawable drawable2 = this.H;
        if (drawable == drawable2 && this.K) {
            DrawableCompat.setTintList(drawable2, this.I);
        }
    }

    private void E(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.setEmpty();
        if (l0() || k0()) {
            float f = this.X + this.Y;
            float Y = Y();
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + Y;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - Y;
            }
            float X = X();
            float exactCenterY = rect.exactCenterY() - (X / 2.0f);
            rectF.top = exactCenterY;
            rectF.bottom = exactCenterY + X;
        }
    }

    private void G(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.set(rect);
        if (m0()) {
            float f = this.e0 + this.d0 + this.P + this.c0 + this.b0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = rect.right - f;
            } else {
                rectF.left = rect.left + f;
            }
        }
    }

    private void H(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.setEmpty();
        if (m0()) {
            float f = this.e0 + this.d0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.right - f;
                rectF.right = f2;
                rectF.left = f2 - this.P;
            } else {
                float f3 = rect.left + f;
                rectF.left = f3;
                rectF.right = f3 + this.P;
            }
            float exactCenterY = rect.exactCenterY();
            float f4 = this.P;
            float f5 = exactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    private void I(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.setEmpty();
        if (m0()) {
            float f = this.e0 + this.d0 + this.P + this.c0 + this.b0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f2 = rect.right;
                rectF.right = f2;
                rectF.left = f2 - f;
            } else {
                int i2 = rect.left;
                rectF.left = i2;
                rectF.right = i2 + f;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private void K(@NonNull Rect rect, @NonNull RectF rectF) {
        rectF.setEmpty();
        if (this.F != null) {
            float F = this.X + F() + this.a0;
            float J = this.e0 + J() + this.b0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = rect.left + F;
                rectF.right = rect.right - J;
            } else {
                rectF.left = rect.left + J;
                rectF.right = rect.right - F;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private float L() {
        this.m0.getTextPaint().getFontMetrics(this.i0);
        Paint.FontMetrics fontMetrics = this.i0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private boolean N() {
        return this.S && this.T != null && this.R;
    }

    private void O(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (k0()) {
            E(rect, this.j0);
            RectF rectF = this.j0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.T.setBounds(0, 0, (int) this.j0.width(), (int) this.j0.height());
            this.T.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void P(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (this.H0) {
            return;
        }
        this.g0.setColor(this.o0);
        this.g0.setStyle(Paint.Style.FILL);
        this.g0.setColorFilter(Z());
        this.j0.set(rect);
        canvas.drawRoundRect(this.j0, getChipCornerRadius(), getChipCornerRadius(), this.g0);
    }

    private void Q(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (l0()) {
            E(rect, this.j0);
            RectF rectF = this.j0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.H.setBounds(0, 0, (int) this.j0.width(), (int) this.j0.height());
            this.H.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void R(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (this.D <= 0.0f || this.H0) {
            return;
        }
        this.g0.setColor(this.q0);
        this.g0.setStyle(Paint.Style.STROKE);
        if (!this.H0) {
            this.g0.setColorFilter(Z());
        }
        RectF rectF = this.j0;
        float f = rect.left;
        float f2 = this.D;
        rectF.set(f + (f2 / 2.0f), rect.top + (f2 / 2.0f), rect.right - (f2 / 2.0f), rect.bottom - (f2 / 2.0f));
        float f3 = this.B - (this.D / 2.0f);
        canvas.drawRoundRect(this.j0, f3, f3, this.g0);
    }

    private void S(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (this.H0) {
            return;
        }
        this.g0.setColor(this.n0);
        this.g0.setStyle(Paint.Style.FILL);
        this.j0.set(rect);
        canvas.drawRoundRect(this.j0, getChipCornerRadius(), getChipCornerRadius(), this.g0);
    }

    private void T(@NonNull Canvas canvas, @NonNull Rect rect) {
        Drawable drawable;
        if (m0()) {
            H(rect, this.j0);
            RectF rectF = this.j0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.M.setBounds(0, 0, (int) this.j0.width(), (int) this.j0.height());
            if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
                this.N.setBounds(this.M.getBounds());
                this.N.jumpToCurrentState();
                drawable = this.N;
            } else {
                drawable = this.M;
            }
            drawable.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void U(@NonNull Canvas canvas, @NonNull Rect rect) {
        this.g0.setColor(this.r0);
        this.g0.setStyle(Paint.Style.FILL);
        this.j0.set(rect);
        if (!this.H0) {
            canvas.drawRoundRect(this.j0, getChipCornerRadius(), getChipCornerRadius(), this.g0);
        } else {
            g(new RectF(rect), this.l0);
            super.n(canvas, this.g0, this.l0, q());
        }
    }

    private void V(@NonNull Canvas canvas, @NonNull Rect rect) {
        Paint paint = this.h0;
        if (paint != null) {
            paint.setColor(ColorUtils.setAlphaComponent(-16777216, 127));
            canvas.drawRect(rect, this.h0);
            if (l0() || k0()) {
                E(rect, this.j0);
                canvas.drawRect(this.j0, this.h0);
            }
            if (this.F != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.h0);
            }
            if (m0()) {
                H(rect, this.j0);
                canvas.drawRect(this.j0, this.h0);
            }
            this.h0.setColor(ColorUtils.setAlphaComponent(-65536, 127));
            G(rect, this.j0);
            canvas.drawRect(this.j0, this.h0);
            this.h0.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
            I(rect, this.j0);
            canvas.drawRect(this.j0, this.h0);
        }
    }

    private void W(@NonNull Canvas canvas, @NonNull Rect rect) {
        if (this.F != null) {
            Paint.Align M = M(rect, this.k0);
            K(rect, this.j0);
            if (this.m0.getTextAppearance() != null) {
                this.m0.getTextPaint().drawableState = getState();
                this.m0.updateTextPaintDrawState(this.f0);
            }
            this.m0.getTextPaint().setTextAlign(M);
            int i2 = 0;
            boolean z = Math.round(this.m0.getTextWidth(getText().toString())) > Math.round(this.j0.width());
            if (z) {
                i2 = canvas.save();
                canvas.clipRect(this.j0);
            }
            CharSequence charSequence = this.F;
            if (z && this.E0 != null) {
                charSequence = TextUtils.ellipsize(charSequence, this.m0.getTextPaint(), this.j0.width(), this.E0);
            }
            CharSequence charSequence2 = charSequence;
            int length = charSequence2.length();
            PointF pointF = this.k0;
            canvas.drawText(charSequence2, 0, length, pointF.x, pointF.y, this.m0.getTextPaint());
            if (z) {
                canvas.restoreToCount(i2);
            }
        }
    }

    private float X() {
        Drawable drawable = this.t0 ? this.T : this.H;
        float f = this.J;
        if (f <= 0.0f && drawable != null) {
            f = (float) Math.ceil(ViewUtils.dpToPx(this.f0, 24));
            if (drawable.getIntrinsicHeight() <= f) {
                return drawable.getIntrinsicHeight();
            }
        }
        return f;
    }

    private float Y() {
        Drawable drawable = this.t0 ? this.T : this.H;
        float f = this.J;
        return (f > 0.0f || drawable == null) ? f : drawable.getIntrinsicWidth();
    }

    @Nullable
    private ColorFilter Z() {
        ColorFilter colorFilter = this.w0;
        return colorFilter != null ? colorFilter : this.x0;
    }

    private static boolean a0(@Nullable int[] iArr, @AttrRes int i2) {
        if (iArr == null) {
            return false;
        }
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    private static boolean b0(@Nullable ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private static boolean c0(@Nullable Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    @NonNull
    public static ChipDrawable createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        ChipDrawable chipDrawable = new ChipDrawable(context, attributeSet, i2, i3);
        chipDrawable.e0(attributeSet, i2, i3);
        return chipDrawable;
    }

    @NonNull
    public static ChipDrawable createFromResource(@NonNull Context context, @XmlRes int i2) {
        AttributeSet parseDrawableXml = DrawableUtils.parseDrawableXml(context, i2, "chip");
        int styleAttribute = parseDrawableXml.getStyleAttribute();
        if (styleAttribute == 0) {
            styleAttribute = com.google.android.material.R.style.Widget_MaterialComponents_Chip_Entry;
        }
        return createFromAttributes(context, parseDrawableXml, com.google.android.material.R.attr.chipStandaloneStyle, styleAttribute);
    }

    private static boolean d0(@Nullable TextAppearance textAppearance) {
        ColorStateList colorStateList;
        return (textAppearance == null || (colorStateList = textAppearance.textColor) == null || !colorStateList.isStateful()) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e0(@androidx.annotation.Nullable android.util.AttributeSet r8, @androidx.annotation.AttrRes int r9, @androidx.annotation.StyleRes int r10) {
        /*
            Method dump skipped, instructions count: 484
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.ChipDrawable.e0(android.util.AttributeSet, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean g0(@androidx.annotation.NonNull int[] r7, @androidx.annotation.NonNull int[] r8) {
        /*
            Method dump skipped, instructions count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.ChipDrawable.g0(int[], int[]):boolean");
    }

    private void h0(@Nullable ColorStateList colorStateList) {
        if (this.y != colorStateList) {
            this.y = colorStateList;
            onStateChange(getState());
        }
    }

    private boolean k0() {
        return this.S && this.T != null && this.t0;
    }

    private boolean l0() {
        return this.G && this.H != null;
    }

    private boolean m0() {
        return this.L && this.M != null;
    }

    private void n0(@Nullable Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void o0() {
        this.C0 = this.B0 ? RippleUtils.sanitizeRippleDrawableColor(this.E) : null;
    }

    @TargetApi(21)
    private void p0() {
        this.N = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(getRippleColor()), this.M, J0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float F() {
        if (l0() || k0()) {
            return this.Y + Y() + this.Z;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float J() {
        if (m0()) {
            return this.c0 + this.P + this.d0;
        }
        return 0.0f;
    }

    @NonNull
    Paint.Align M(@NonNull Rect rect, @NonNull PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.F != null) {
            float F = this.X + F() + this.a0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                pointF.x = rect.left + F;
                align = Paint.Align.LEFT;
            } else {
                pointF.x = rect.right - F;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - L();
        }
        return align;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i2 = this.v0;
        int saveLayerAlpha = i2 < 255 ? CanvasCompat.saveLayerAlpha(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i2) : 0;
        S(canvas, bounds);
        P(canvas, bounds);
        if (this.H0) {
            super.draw(canvas);
        }
        R(canvas, bounds);
        U(canvas, bounds);
        Q(canvas, bounds);
        O(canvas, bounds);
        if (this.F0) {
            W(canvas, bounds);
        }
        T(canvas, bounds);
        V(canvas, bounds);
        if (this.v0 < 255) {
            canvas.restoreToCount(saveLayerAlpha);
        }
    }

    protected void f0() {
        Delegate delegate = this.D0.get();
        if (delegate != null) {
            delegate.onChipDrawableSizeChange();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.v0;
    }

    @Nullable
    public Drawable getCheckedIcon() {
        return this.T;
    }

    @Nullable
    public ColorStateList getCheckedIconTint() {
        return this.U;
    }

    @Nullable
    public ColorStateList getChipBackgroundColor() {
        return this.z;
    }

    public float getChipCornerRadius() {
        return this.H0 ? getTopLeftCornerResolvedSize() : this.B;
    }

    public float getChipEndPadding() {
        return this.e0;
    }

    @Nullable
    public Drawable getChipIcon() {
        Drawable drawable = this.H;
        if (drawable != null) {
            return DrawableCompat.unwrap(drawable);
        }
        return null;
    }

    public float getChipIconSize() {
        return this.J;
    }

    @Nullable
    public ColorStateList getChipIconTint() {
        return this.I;
    }

    public float getChipMinHeight() {
        return this.A;
    }

    public float getChipStartPadding() {
        return this.X;
    }

    @Nullable
    public ColorStateList getChipStrokeColor() {
        return this.C;
    }

    public float getChipStrokeWidth() {
        return this.D;
    }

    public void getChipTouchBounds(@NonNull RectF rectF) {
        G(getBounds(), rectF);
    }

    @Nullable
    public Drawable getCloseIcon() {
        Drawable drawable = this.M;
        if (drawable != null) {
            return DrawableCompat.unwrap(drawable);
        }
        return null;
    }

    @Nullable
    public CharSequence getCloseIconContentDescription() {
        return this.Q;
    }

    public float getCloseIconEndPadding() {
        return this.d0;
    }

    public float getCloseIconSize() {
        return this.P;
    }

    public float getCloseIconStartPadding() {
        return this.c0;
    }

    @NonNull
    public int[] getCloseIconState() {
        return this.A0;
    }

    @Nullable
    public ColorStateList getCloseIconTint() {
        return this.O;
    }

    public void getCloseIconTouchBounds(@NonNull RectF rectF) {
        I(getBounds(), rectF);
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public ColorFilter getColorFilter() {
        return this.w0;
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.E0;
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.W;
    }

    public float getIconEndPadding() {
        return this.Z;
    }

    public float getIconStartPadding() {
        return this.Y;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.A;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.X + F() + this.a0 + this.m0.getTextWidth(getText().toString()) + this.b0 + J() + this.e0), this.G0);
    }

    @Px
    public int getMaxWidth() {
        return this.G0;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(@NonNull Outline outline) {
        if (this.H0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.B);
        } else {
            outline.setRoundRect(bounds, this.B);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    @Nullable
    public ColorStateList getRippleColor() {
        return this.E;
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.V;
    }

    @Nullable
    public CharSequence getText() {
        return this.F;
    }

    @Nullable
    public TextAppearance getTextAppearance() {
        return this.m0.getTextAppearance();
    }

    public float getTextEndPadding() {
        return this.b0;
    }

    public float getTextStartPadding() {
        return this.a0;
    }

    public boolean getUseCompatRipple() {
        return this.B0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i0(boolean z) {
        this.F0 = z;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public boolean isCheckable() {
        return this.R;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public boolean isCheckedIconVisible() {
        return this.S;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public boolean isChipIconVisible() {
        return this.G;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public boolean isCloseIconStateful() {
        return c0(this.M);
    }

    public boolean isCloseIconVisible() {
        return this.L;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return b0(this.y) || b0(this.z) || b0(this.C) || (this.B0 && b0(this.C0)) || d0(this.m0.getTextAppearance()) || N() || c0(this.H) || c0(this.T) || b0(this.y0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j0() {
        return this.F0;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i2);
        if (l0()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.H, i2);
        }
        if (k0()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.T, i2);
        }
        if (m0()) {
            onLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.M, i2);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i2) {
        boolean onLevelChange = super.onLevelChange(i2);
        if (l0()) {
            onLevelChange |= this.H.setLevel(i2);
        }
        if (k0()) {
            onLevelChange |= this.T.setLevel(i2);
        }
        if (m0()) {
            onLevelChange |= this.M.setLevel(i2);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(@NonNull int[] iArr) {
        if (this.H0) {
            super.onStateChange(iArr);
        }
        return g0(iArr, getCloseIconState());
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        f0();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.v0 != i2) {
            this.v0 = i2;
            invalidateSelf();
        }
    }

    public void setCheckable(boolean z) {
        if (this.R != z) {
            this.R = z;
            float F = F();
            if (!z && this.t0) {
                this.t0 = false;
            }
            float F2 = F();
            invalidateSelf();
            if (F != F2) {
                f0();
            }
        }
    }

    public void setCheckableResource(@BoolRes int i2) {
        setCheckable(this.f0.getResources().getBoolean(i2));
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        if (this.T != drawable) {
            float F = F();
            this.T = drawable;
            float F2 = F();
            n0(this.T);
            D(this.T);
            invalidateSelf();
            if (F != F2) {
                f0();
            }
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(@BoolRes int i2) {
        setCheckedIconVisible(this.f0.getResources().getBoolean(i2));
    }

    public void setCheckedIconResource(@DrawableRes int i2) {
        setCheckedIcon(AppCompatResources.getDrawable(this.f0, i2));
    }

    public void setCheckedIconTint(@Nullable ColorStateList colorStateList) {
        if (this.U != colorStateList) {
            this.U = colorStateList;
            if (N()) {
                DrawableCompat.setTintList(this.T, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setCheckedIconTintResource(@ColorRes int i2) {
        setCheckedIconTint(AppCompatResources.getColorStateList(this.f0, i2));
    }

    public void setCheckedIconVisible(@BoolRes int i2) {
        setCheckedIconVisible(this.f0.getResources().getBoolean(i2));
    }

    public void setCheckedIconVisible(boolean z) {
        if (this.S != z) {
            boolean k0 = k0();
            this.S = z;
            boolean k02 = k0();
            if (k0 != k02) {
                if (k02) {
                    D(this.T);
                } else {
                    n0(this.T);
                }
                invalidateSelf();
                f0();
            }
        }
    }

    public void setChipBackgroundColor(@Nullable ColorStateList colorStateList) {
        if (this.z != colorStateList) {
            this.z = colorStateList;
            onStateChange(getState());
        }
    }

    public void setChipBackgroundColorResource(@ColorRes int i2) {
        setChipBackgroundColor(AppCompatResources.getColorStateList(this.f0, i2));
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        if (this.B != f) {
            this.B = f;
            setShapeAppearanceModel(getShapeAppearanceModel().withCornerSize(f));
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(@DimenRes int i2) {
        setChipCornerRadius(this.f0.getResources().getDimension(i2));
    }

    public void setChipEndPadding(float f) {
        if (this.e0 != f) {
            this.e0 = f;
            invalidateSelf();
            f0();
        }
    }

    public void setChipEndPaddingResource(@DimenRes int i2) {
        setChipEndPadding(this.f0.getResources().getDimension(i2));
    }

    public void setChipIcon(@Nullable Drawable drawable) {
        Drawable chipIcon = getChipIcon();
        if (chipIcon != drawable) {
            float F = F();
            this.H = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            float F2 = F();
            n0(chipIcon);
            if (l0()) {
                D(this.H);
            }
            invalidateSelf();
            if (F != F2) {
                f0();
            }
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(@BoolRes int i2) {
        setChipIconVisible(i2);
    }

    public void setChipIconResource(@DrawableRes int i2) {
        setChipIcon(AppCompatResources.getDrawable(this.f0, i2));
    }

    public void setChipIconSize(float f) {
        if (this.J != f) {
            float F = F();
            this.J = f;
            float F2 = F();
            invalidateSelf();
            if (F != F2) {
                f0();
            }
        }
    }

    public void setChipIconSizeResource(@DimenRes int i2) {
        setChipIconSize(this.f0.getResources().getDimension(i2));
    }

    public void setChipIconTint(@Nullable ColorStateList colorStateList) {
        this.K = true;
        if (this.I != colorStateList) {
            this.I = colorStateList;
            if (l0()) {
                DrawableCompat.setTintList(this.H, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setChipIconTintResource(@ColorRes int i2) {
        setChipIconTint(AppCompatResources.getColorStateList(this.f0, i2));
    }

    public void setChipIconVisible(@BoolRes int i2) {
        setChipIconVisible(this.f0.getResources().getBoolean(i2));
    }

    public void setChipIconVisible(boolean z) {
        if (this.G != z) {
            boolean l0 = l0();
            this.G = z;
            boolean l02 = l0();
            if (l0 != l02) {
                if (l02) {
                    D(this.H);
                } else {
                    n0(this.H);
                }
                invalidateSelf();
                f0();
            }
        }
    }

    public void setChipMinHeight(float f) {
        if (this.A != f) {
            this.A = f;
            invalidateSelf();
            f0();
        }
    }

    public void setChipMinHeightResource(@DimenRes int i2) {
        setChipMinHeight(this.f0.getResources().getDimension(i2));
    }

    public void setChipStartPadding(float f) {
        if (this.X != f) {
            this.X = f;
            invalidateSelf();
            f0();
        }
    }

    public void setChipStartPaddingResource(@DimenRes int i2) {
        setChipStartPadding(this.f0.getResources().getDimension(i2));
    }

    public void setChipStrokeColor(@Nullable ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            if (this.H0) {
                setStrokeColor(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setChipStrokeColorResource(@ColorRes int i2) {
        setChipStrokeColor(AppCompatResources.getColorStateList(this.f0, i2));
    }

    public void setChipStrokeWidth(float f) {
        if (this.D != f) {
            this.D = f;
            this.g0.setStrokeWidth(f);
            if (this.H0) {
                super.setStrokeWidth(f);
            }
            invalidateSelf();
        }
    }

    public void setChipStrokeWidthResource(@DimenRes int i2) {
        setChipStrokeWidth(this.f0.getResources().getDimension(i2));
    }

    public void setCloseIcon(@Nullable Drawable drawable) {
        Drawable closeIcon = getCloseIcon();
        if (closeIcon != drawable) {
            float J = J();
            this.M = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
                p0();
            }
            float J2 = J();
            n0(closeIcon);
            if (m0()) {
                D(this.M);
            }
            invalidateSelf();
            if (J != J2) {
                f0();
            }
        }
    }

    public void setCloseIconContentDescription(@Nullable CharSequence charSequence) {
        if (this.Q != charSequence) {
            this.Q = BidiFormatter.getInstance().unicodeWrap(charSequence);
            invalidateSelf();
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(@BoolRes int i2) {
        setCloseIconVisible(i2);
    }

    public void setCloseIconEndPadding(float f) {
        if (this.d0 != f) {
            this.d0 = f;
            invalidateSelf();
            if (m0()) {
                f0();
            }
        }
    }

    public void setCloseIconEndPaddingResource(@DimenRes int i2) {
        setCloseIconEndPadding(this.f0.getResources().getDimension(i2));
    }

    public void setCloseIconResource(@DrawableRes int i2) {
        setCloseIcon(AppCompatResources.getDrawable(this.f0, i2));
    }

    public void setCloseIconSize(float f) {
        if (this.P != f) {
            this.P = f;
            invalidateSelf();
            if (m0()) {
                f0();
            }
        }
    }

    public void setCloseIconSizeResource(@DimenRes int i2) {
        setCloseIconSize(this.f0.getResources().getDimension(i2));
    }

    public void setCloseIconStartPadding(float f) {
        if (this.c0 != f) {
            this.c0 = f;
            invalidateSelf();
            if (m0()) {
                f0();
            }
        }
    }

    public void setCloseIconStartPaddingResource(@DimenRes int i2) {
        setCloseIconStartPadding(this.f0.getResources().getDimension(i2));
    }

    public boolean setCloseIconState(@NonNull int[] iArr) {
        if (Arrays.equals(this.A0, iArr)) {
            return false;
        }
        this.A0 = iArr;
        if (m0()) {
            return g0(getState(), iArr);
        }
        return false;
    }

    public void setCloseIconTint(@Nullable ColorStateList colorStateList) {
        if (this.O != colorStateList) {
            this.O = colorStateList;
            if (m0()) {
                DrawableCompat.setTintList(this.M, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void setCloseIconTintResource(@ColorRes int i2) {
        setCloseIconTint(AppCompatResources.getColorStateList(this.f0, i2));
    }

    public void setCloseIconVisible(@BoolRes int i2) {
        setCloseIconVisible(this.f0.getResources().getBoolean(i2));
    }

    public void setCloseIconVisible(boolean z) {
        if (this.L != z) {
            boolean m0 = m0();
            this.L = z;
            boolean m02 = m0();
            if (m0 != m02) {
                if (m02) {
                    D(this.M);
                } else {
                    n0(this.M);
                }
                invalidateSelf();
                f0();
            }
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (this.w0 != colorFilter) {
            this.w0 = colorFilter;
            invalidateSelf();
        }
    }

    public void setDelegate(@Nullable Delegate delegate) {
        this.D0 = new WeakReference<>(delegate);
    }

    public void setEllipsize(@Nullable TextUtils.TruncateAt truncateAt) {
        this.E0 = truncateAt;
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        this.W = motionSpec;
    }

    public void setHideMotionSpecResource(@AnimatorRes int i2) {
        setHideMotionSpec(MotionSpec.createFromResource(this.f0, i2));
    }

    public void setIconEndPadding(float f) {
        if (this.Z != f) {
            float F = F();
            this.Z = f;
            float F2 = F();
            invalidateSelf();
            if (F != F2) {
                f0();
            }
        }
    }

    public void setIconEndPaddingResource(@DimenRes int i2) {
        setIconEndPadding(this.f0.getResources().getDimension(i2));
    }

    public void setIconStartPadding(float f) {
        if (this.Y != f) {
            float F = F();
            this.Y = f;
            float F2 = F();
            invalidateSelf();
            if (F != F2) {
                f0();
            }
        }
    }

    public void setIconStartPaddingResource(@DimenRes int i2) {
        setIconStartPadding(this.f0.getResources().getDimension(i2));
    }

    public void setMaxWidth(@Px int i2) {
        this.G0 = i2;
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            o0();
            onStateChange(getState());
        }
    }

    public void setRippleColorResource(@ColorRes int i2) {
        setRippleColor(AppCompatResources.getColorStateList(this.f0, i2));
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        this.V = motionSpec;
    }

    public void setShowMotionSpecResource(@AnimatorRes int i2) {
        setShowMotionSpec(MotionSpec.createFromResource(this.f0, i2));
    }

    public void setText(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.F, charSequence)) {
            return;
        }
        this.F = charSequence;
        this.m0.setTextWidthDirty(true);
        invalidateSelf();
        f0();
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance) {
        this.m0.setTextAppearance(textAppearance, this.f0);
    }

    public void setTextAppearanceResource(@StyleRes int i2) {
        setTextAppearance(new TextAppearance(this.f0, i2));
    }

    public void setTextEndPadding(float f) {
        if (this.b0 != f) {
            this.b0 = f;
            invalidateSelf();
            f0();
        }
    }

    public void setTextEndPaddingResource(@DimenRes int i2) {
        setTextEndPadding(this.f0.getResources().getDimension(i2));
    }

    public void setTextResource(@StringRes int i2) {
        setText(this.f0.getResources().getString(i2));
    }

    public void setTextSize(@Dimension float f) {
        TextAppearance textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.textSize = f;
            this.m0.getTextPaint().setTextSize(f);
            onTextSizeChange();
        }
    }

    public void setTextStartPadding(float f) {
        if (this.a0 != f) {
            this.a0 = f;
            invalidateSelf();
            f0();
        }
    }

    public void setTextStartPaddingResource(@DimenRes int i2) {
        setTextStartPadding(this.f0.getResources().getDimension(i2));
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(@Nullable ColorStateList colorStateList) {
        if (this.y0 != colorStateList) {
            this.y0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        if (this.z0 != mode) {
            this.z0 = mode;
            this.x0 = DrawableUtils.updateTintFilter(this, this.y0, mode);
            invalidateSelf();
        }
    }

    public void setUseCompatRipple(boolean z) {
        if (this.B0 != z) {
            this.B0 = z;
            o0();
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (l0()) {
            visible |= this.H.setVisible(z, z2);
        }
        if (k0()) {
            visible |= this.T.setVisible(z, z2);
        }
        if (m0()) {
            visible |= this.M.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }
}
