package com.google.android.material.card;

import android.R;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class a {
    private static final int[] t = {R.attr.state_checked};
    private static final double u = Math.cos(Math.toRadians(45.0d));

    @NonNull
    private final MaterialCardView a;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    private final MaterialShapeDrawable f1518c;

    /* renamed from: d, reason: collision with root package name */
    @NonNull
    private final MaterialShapeDrawable f1519d;

    @Dimension
    private int e;

    @Dimension
    private int f;

    @Dimension
    private int g;

    @Nullable
    private Drawable h;

    /* renamed from: i, reason: collision with root package name */
    @Nullable
    private Drawable f1520i;

    @Nullable
    private ColorStateList j;

    @Nullable
    private ColorStateList k;

    @Nullable
    private ShapeAppearanceModel l;

    @Nullable
    private ColorStateList m;

    @Nullable
    private Drawable n;

    @Nullable
    private LayerDrawable o;

    @Nullable
    private MaterialShapeDrawable p;

    @Nullable
    private MaterialShapeDrawable q;
    private boolean s;

    @NonNull
    private final Rect b = new Rect();
    private boolean r = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.android.material.card.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0054a extends InsetDrawable {
        C0054a(a aVar, Drawable drawable, int i2, int i3, int i4, int i5) {
            super(drawable, i2, i3, i4, i5);
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return -1;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return -1;
        }

        @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public boolean getPadding(Rect rect) {
            return false;
        }
    }

    public a(@NonNull MaterialCardView materialCardView, AttributeSet attributeSet, int i2, @StyleRes int i3) {
        this.a = materialCardView;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(materialCardView.getContext(), attributeSet, i2, i3);
        this.f1518c = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(materialCardView.getContext());
        materialShapeDrawable.setShadowColor(-12303292);
        ShapeAppearanceModel.Builder builder = materialShapeDrawable.getShapeAppearanceModel().toBuilder();
        TypedArray obtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, com.google.android.material.R.styleable.CardView, i2, com.google.android.material.R.style.CardView);
        int i4 = com.google.android.material.R.styleable.CardView_cardCornerRadius;
        if (obtainStyledAttributes.hasValue(i4)) {
            builder.setAllCornerSizes(obtainStyledAttributes.getDimension(i4, 0.0f));
        }
        this.f1519d = new MaterialShapeDrawable();
        R(builder.build());
        obtainStyledAttributes.recycle();
    }

    @NonNull
    private Drawable B(Drawable drawable) {
        int ceil;
        int i2;
        if ((Build.VERSION.SDK_INT < 21) || this.a.getUseCompatPadding()) {
            int ceil2 = (int) Math.ceil(d());
            ceil = (int) Math.ceil(c());
            i2 = ceil2;
        } else {
            ceil = 0;
            i2 = 0;
        }
        return new C0054a(this, drawable, ceil, i2, ceil, i2);
    }

    private boolean V() {
        return this.a.getPreventCornerOverlap() && !e();
    }

    private boolean W() {
        return this.a.getPreventCornerOverlap() && e() && this.a.getUseCompatPadding();
    }

    private float a() {
        return Math.max(Math.max(b(this.l.getTopLeftCorner(), this.f1518c.getTopLeftCornerResolvedSize()), b(this.l.getTopRightCorner(), this.f1518c.getTopRightCornerResolvedSize())), Math.max(b(this.l.getBottomRightCorner(), this.f1518c.getBottomRightCornerResolvedSize()), b(this.l.getBottomLeftCorner(), this.f1518c.getBottomLeftCornerResolvedSize())));
    }

    private void a0(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 23 || !(this.a.getForeground() instanceof InsetDrawable)) {
            this.a.setForeground(B(drawable));
        } else {
            ((InsetDrawable) this.a.getForeground()).setDrawable(drawable);
        }
    }

    private float b(CornerTreatment cornerTreatment, float f) {
        if (!(cornerTreatment instanceof RoundedCornerTreatment)) {
            if (cornerTreatment instanceof CutCornerTreatment) {
                return f / 2.0f;
            }
            return 0.0f;
        }
        double d2 = 1.0d - u;
        double d3 = f;
        Double.isNaN(d3);
        return (float) (d2 * d3);
    }

    private float c() {
        return this.a.getMaxCardElevation() + (W() ? a() : 0.0f);
    }

    private void c0() {
        Drawable drawable;
        if (RippleUtils.USE_FRAMEWORK_RIPPLE && (drawable = this.n) != null) {
            ((RippleDrawable) drawable).setColor(this.j);
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = this.p;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setFillColor(this.j);
        }
    }

    private float d() {
        return (this.a.getMaxCardElevation() * 1.5f) + (W() ? a() : 0.0f);
    }

    private boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.f1518c.isRoundRect();
    }

    @NonNull
    private Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.f1520i;
        if (drawable != null) {
            stateListDrawable.addState(t, drawable);
        }
        return stateListDrawable;
    }

    @NonNull
    private Drawable g() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        MaterialShapeDrawable i2 = i();
        this.p = i2;
        i2.setFillColor(this.j);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, this.p);
        return stateListDrawable;
    }

    @NonNull
    private Drawable h() {
        if (!RippleUtils.USE_FRAMEWORK_RIPPLE) {
            return g();
        }
        this.q = i();
        return new RippleDrawable(this.j, null, this.q);
    }

    @NonNull
    private MaterialShapeDrawable i() {
        return new MaterialShapeDrawable(this.l);
    }

    @NonNull
    private Drawable r() {
        if (this.n == null) {
            this.n = h();
        }
        if (this.o == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.n, this.f1519d, f()});
            this.o = layerDrawable;
            layerDrawable.setId(2, com.google.android.material.R.id.mtrl_card_checked_layer_id);
        }
        return this.o;
    }

    private float t() {
        if (!this.a.getPreventCornerOverlap()) {
            return 0.0f;
        }
        if (Build.VERSION.SDK_INT >= 21 && !this.a.getUseCompatPadding()) {
            return 0.0f;
        }
        double d2 = 1.0d - u;
        double cardViewRadius = this.a.getCardViewRadius();
        Double.isNaN(cardViewRadius);
        return (float) (d2 * cardViewRadius);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Rect A() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean C() {
        return this.r;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean D() {
        return this.s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void E(@NonNull TypedArray typedArray) {
        ColorStateList colorStateList = MaterialResources.getColorStateList(this.a.getContext(), typedArray, com.google.android.material.R.styleable.MaterialCardView_strokeColor);
        this.m = colorStateList;
        if (colorStateList == null) {
            this.m = ColorStateList.valueOf(-1);
        }
        this.g = typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.MaterialCardView_strokeWidth, 0);
        boolean z = typedArray.getBoolean(com.google.android.material.R.styleable.MaterialCardView_android_checkable, false);
        this.s = z;
        this.a.setLongClickable(z);
        this.k = MaterialResources.getColorStateList(this.a.getContext(), typedArray, com.google.android.material.R.styleable.MaterialCardView_checkedIconTint);
        K(MaterialResources.getDrawable(this.a.getContext(), typedArray, com.google.android.material.R.styleable.MaterialCardView_checkedIcon));
        M(typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.MaterialCardView_checkedIconSize, 0));
        L(typedArray.getDimensionPixelSize(com.google.android.material.R.styleable.MaterialCardView_checkedIconMargin, 0));
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(this.a.getContext(), typedArray, com.google.android.material.R.styleable.MaterialCardView_rippleColor);
        this.j = colorStateList2;
        if (colorStateList2 == null) {
            this.j = ColorStateList.valueOf(MaterialColors.getColor(this.a, com.google.android.material.R.attr.colorControlHighlight));
        }
        I(MaterialResources.getColorStateList(this.a.getContext(), typedArray, com.google.android.material.R.styleable.MaterialCardView_cardForegroundColor));
        c0();
        Z();
        d0();
        this.a.setBackgroundInternal(B(this.f1518c));
        Drawable r = this.a.isClickable() ? r() : this.f1519d;
        this.h = r;
        this.a.setForeground(B(r));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F(int i2, int i3) {
        int i4;
        int i5;
        if (this.o != null) {
            int i6 = this.e;
            int i7 = this.f;
            int i8 = (i2 - i6) - i7;
            int i9 = (i3 - i6) - i7;
            if ((Build.VERSION.SDK_INT < 21) || this.a.getUseCompatPadding()) {
                i9 -= (int) Math.ceil(d() * 2.0f);
                i8 -= (int) Math.ceil(c() * 2.0f);
            }
            int i10 = i9;
            int i11 = this.e;
            if (ViewCompat.getLayoutDirection(this.a) == 1) {
                i5 = i8;
                i4 = i11;
            } else {
                i4 = i8;
                i5 = i11;
            }
            this.o.setLayerInset(2, i4, this.e, i5, i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G(boolean z) {
        this.r = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H(ColorStateList colorStateList) {
        this.f1518c.setFillColor(colorStateList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I(@Nullable ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = this.f1519d;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        materialShapeDrawable.setFillColor(colorStateList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(boolean z) {
        this.s = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void K(@Nullable Drawable drawable) {
        this.f1520i = drawable;
        if (drawable != null) {
            Drawable wrap = DrawableCompat.wrap(drawable.mutate());
            this.f1520i = wrap;
            DrawableCompat.setTintList(wrap, this.k);
        }
        if (this.o != null) {
            this.o.setDrawableByLayerId(com.google.android.material.R.id.mtrl_card_checked_layer_id, f());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void L(@Dimension int i2) {
        this.e = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void M(@Dimension int i2) {
        this.f = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void N(@Nullable ColorStateList colorStateList) {
        this.k = colorStateList;
        Drawable drawable = this.f1520i;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, colorStateList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void O(float f) {
        R(this.l.withCornerSize(f));
        this.h.invalidateSelf();
        if (W() || V()) {
            Y();
        }
        if (W()) {
            b0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void P(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.f1518c.setInterpolation(f);
        MaterialShapeDrawable materialShapeDrawable = this.f1519d;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setInterpolation(f);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.q;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setInterpolation(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Q(@Nullable ColorStateList colorStateList) {
        this.j = colorStateList;
        c0();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void R(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.l = shapeAppearanceModel;
        this.f1518c.setShapeAppearanceModel(shapeAppearanceModel);
        this.f1518c.setShadowBitmapDrawingEnable(!r0.isRoundRect());
        MaterialShapeDrawable materialShapeDrawable = this.f1519d;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.q;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel);
        }
        MaterialShapeDrawable materialShapeDrawable3 = this.p;
        if (materialShapeDrawable3 != null) {
            materialShapeDrawable3.setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void S(ColorStateList colorStateList) {
        if (this.m == colorStateList) {
            return;
        }
        this.m = colorStateList;
        d0();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void T(@Dimension int i2) {
        if (i2 == this.g) {
            return;
        }
        this.g = i2;
        d0();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void U(int i2, int i3, int i4, int i5) {
        this.b.set(i2, i3, i4, i5);
        Y();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void X() {
        Drawable drawable = this.h;
        Drawable r = this.a.isClickable() ? r() : this.f1519d;
        this.h = r;
        if (drawable != r) {
            a0(r);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Y() {
        int a = (int) ((V() || W() ? a() : 0.0f) - t());
        MaterialCardView materialCardView = this.a;
        Rect rect = this.b;
        materialCardView.c(rect.left + a, rect.top + a, rect.right + a, rect.bottom + a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Z() {
        this.f1518c.setElevation(this.a.getCardElevation());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b0() {
        if (!C()) {
            this.a.setBackgroundInternal(B(this.f1518c));
        }
        this.a.setForeground(B(this.h));
    }

    void d0() {
        this.f1519d.setStroke(this.g, this.m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 23)
    public void j() {
        Drawable drawable = this.n;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i2 = bounds.bottom;
            this.n.setBounds(bounds.left, bounds.top, bounds.right, i2 - 1);
            this.n.setBounds(bounds.left, bounds.top, bounds.right, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public MaterialShapeDrawable k() {
        return this.f1518c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList l() {
        return this.f1518c.getFillColor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList m() {
        return this.f1519d.getFillColor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Drawable n() {
        return this.f1520i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Dimension
    public int o() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Dimension
    public int p() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ColorStateList q() {
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float s() {
        return this.f1518c.getTopLeftCornerResolvedSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FloatRange(from = 0.0d, to = 1.0d)
    public float u() {
        return this.f1518c.getInterpolation();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ColorStateList v() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShapeAppearanceModel w() {
        return this.l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ColorInt
    public int x() {
        ColorStateList colorStateList = this.m;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ColorStateList y() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Dimension
    public int z() {
        return this.g;
    }
}
