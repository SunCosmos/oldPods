package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;

/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class a {
    private static final boolean t;
    private final MaterialButton a;

    @NonNull
    private ShapeAppearanceModel b;

    /* renamed from: c, reason: collision with root package name */
    private int f1512c;

    /* renamed from: d, reason: collision with root package name */
    private int f1513d;
    private int e;
    private int f;
    private int g;
    private int h;

    /* renamed from: i, reason: collision with root package name */
    @Nullable
    private PorterDuff.Mode f1514i;

    @Nullable
    private ColorStateList j;

    @Nullable
    private ColorStateList k;

    @Nullable
    private ColorStateList l;

    @Nullable
    private Drawable m;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q;
    private LayerDrawable r;
    private int s;

    static {
        t = Build.VERSION.SDK_INT >= 21;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(MaterialButton materialButton, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.a = materialButton;
        this.b = shapeAppearanceModel;
    }

    private void E(@Dimension int i2, @Dimension int i3) {
        int paddingStart = ViewCompat.getPaddingStart(this.a);
        int paddingTop = this.a.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.a);
        int paddingBottom = this.a.getPaddingBottom();
        int i4 = this.e;
        int i5 = this.f;
        this.f = i3;
        this.e = i2;
        if (!this.o) {
            F();
        }
        ViewCompat.setPaddingRelative(this.a, paddingStart, (paddingTop + i2) - i4, paddingEnd, (paddingBottom + i3) - i5);
    }

    private void F() {
        this.a.setInternalBackground(a());
        MaterialShapeDrawable f = f();
        if (f != null) {
            f.setElevation(this.s);
        }
    }

    private void G(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        if (f() != null) {
            f().setShapeAppearanceModel(shapeAppearanceModel);
        }
        if (n() != null) {
            n().setShapeAppearanceModel(shapeAppearanceModel);
        }
        if (e() != null) {
            e().setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    private void I() {
        MaterialShapeDrawable f = f();
        MaterialShapeDrawable n = n();
        if (f != null) {
            f.setStroke(this.h, this.k);
            if (n != null) {
                n.setStroke(this.h, this.n ? MaterialColors.getColor(this.a, R.attr.colorSurface) : 0);
            }
        }
    }

    @NonNull
    private InsetDrawable J(Drawable drawable) {
        return new InsetDrawable(drawable, this.f1512c, this.e, this.f1513d, this.f);
    }

    private Drawable a() {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.b);
        materialShapeDrawable.initializeElevationOverlay(this.a.getContext());
        DrawableCompat.setTintList(materialShapeDrawable, this.j);
        PorterDuff.Mode mode = this.f1514i;
        if (mode != null) {
            DrawableCompat.setTintMode(materialShapeDrawable, mode);
        }
        materialShapeDrawable.setStroke(this.h, this.k);
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(this.b);
        materialShapeDrawable2.setTint(0);
        materialShapeDrawable2.setStroke(this.h, this.n ? MaterialColors.getColor(this.a, R.attr.colorSurface) : 0);
        if (t) {
            MaterialShapeDrawable materialShapeDrawable3 = new MaterialShapeDrawable(this.b);
            this.m = materialShapeDrawable3;
            DrawableCompat.setTint(materialShapeDrawable3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.l), J(new LayerDrawable(new Drawable[]{materialShapeDrawable2, materialShapeDrawable})), this.m);
            this.r = rippleDrawable;
            return rippleDrawable;
        }
        RippleDrawableCompat rippleDrawableCompat = new RippleDrawableCompat(this.b);
        this.m = rippleDrawableCompat;
        DrawableCompat.setTintList(rippleDrawableCompat, RippleUtils.sanitizeRippleDrawableColor(this.l));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{materialShapeDrawable2, materialShapeDrawable, this.m});
        this.r = layerDrawable;
        return J(layerDrawable);
    }

    @Nullable
    private MaterialShapeDrawable g(boolean z) {
        LayerDrawable layerDrawable = this.r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (MaterialShapeDrawable) (t ? (LayerDrawable) ((InsetDrawable) this.r.getDrawable(0)).getDrawable() : this.r).getDrawable(!z ? 1 : 0);
    }

    @Nullable
    private MaterialShapeDrawable n() {
        return g(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(@Nullable ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            I();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B(int i2) {
        if (this.h != i2) {
            this.h = i2;
            I();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void C(@Nullable ColorStateList colorStateList) {
        if (this.j != colorStateList) {
            this.j = colorStateList;
            if (f() != null) {
                DrawableCompat.setTintList(f(), this.j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D(@Nullable PorterDuff.Mode mode) {
        if (this.f1514i != mode) {
            this.f1514i = mode;
            if (f() == null || this.f1514i == null) {
                return;
            }
            DrawableCompat.setTintMode(f(), this.f1514i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H(int i2, int i3) {
        Drawable drawable = this.m;
        if (drawable != null) {
            drawable.setBounds(this.f1512c, this.e, i3 - this.f1513d, i2 - this.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.g;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.e;
    }

    @Nullable
    public Shapeable e() {
        LayerDrawable layerDrawable = this.r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return (Shapeable) (this.r.getNumberOfLayers() > 2 ? this.r.getDrawable(2) : this.r.getDrawable(1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public MaterialShapeDrawable f() {
        return g(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ColorStateList h() {
        return this.l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ShapeAppearanceModel i() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ColorStateList j() {
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList l() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode m() {
        return this.f1514i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean o() {
        return this.o;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean p() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(@NonNull TypedArray typedArray) {
        this.f1512c = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetLeft, 0);
        this.f1513d = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
        this.e = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
        this.f = typedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
        int i2 = R.styleable.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i2)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i2, -1);
            this.g = dimensionPixelSize;
            y(this.b.withCornerSize(dimensionPixelSize));
            this.p = true;
        }
        this.h = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
        this.f1514i = ViewUtils.parseTintMode(typedArray.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.j = MaterialResources.getColorStateList(this.a.getContext(), typedArray, R.styleable.MaterialButton_backgroundTint);
        this.k = MaterialResources.getColorStateList(this.a.getContext(), typedArray, R.styleable.MaterialButton_strokeColor);
        this.l = MaterialResources.getColorStateList(this.a.getContext(), typedArray, R.styleable.MaterialButton_rippleColor);
        this.q = typedArray.getBoolean(R.styleable.MaterialButton_android_checkable, false);
        this.s = typedArray.getDimensionPixelSize(R.styleable.MaterialButton_elevation, 0);
        int paddingStart = ViewCompat.getPaddingStart(this.a);
        int paddingTop = this.a.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.a);
        int paddingBottom = this.a.getPaddingBottom();
        if (typedArray.hasValue(R.styleable.MaterialButton_android_background)) {
            s();
        } else {
            F();
        }
        ViewCompat.setPaddingRelative(this.a, paddingStart + this.f1512c, paddingTop + this.e, paddingEnd + this.f1513d, paddingBottom + this.f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(int i2) {
        if (f() != null) {
            f().setTint(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s() {
        this.o = true;
        this.a.setSupportBackgroundTintList(this.j);
        this.a.setSupportBackgroundTintMode(this.f1514i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(boolean z) {
        this.q = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u(int i2) {
        if (this.p && this.g == i2) {
            return;
        }
        this.g = i2;
        this.p = true;
        y(this.b.withCornerSize(i2));
    }

    public void v(@Dimension int i2) {
        E(this.e, i2);
    }

    public void w(@Dimension int i2) {
        E(i2, this.f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x(@Nullable ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            boolean z = t;
            if (z && (this.a.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.a.getBackground()).setColor(RippleUtils.sanitizeRippleDrawableColor(colorStateList));
            } else {
                if (z || !(this.a.getBackground() instanceof RippleDrawableCompat)) {
                    return;
                }
                ((RippleDrawableCompat) this.a.getBackground()).setTintList(RippleUtils.sanitizeRippleDrawableColor(colorStateList));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.b = shapeAppearanceModel;
        G(shapeAppearanceModel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(boolean z) {
        this.n = z;
        I();
    }
}
