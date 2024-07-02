package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import com.google.android.material.R;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;

@RequiresApi(21)
/* loaded from: classes.dex */
public class e extends d {

    /* loaded from: classes.dex */
    public static class a extends MaterialShapeDrawable {
        a(ShapeAppearanceModel shapeAppearanceModel) {
            super(shapeAppearanceModel);
        }

        @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public e(FloatingActionButton floatingActionButton, ShadowViewDelegate shadowViewDelegate) {
        super(floatingActionButton, shadowViewDelegate);
    }

    @NonNull
    private Animator m0(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.y, "elevation", f).setDuration(0L)).with(ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(d.F);
        return animatorSet;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void A() {
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void C() {
        i0();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void E(int[] iArr) {
        FloatingActionButton floatingActionButton;
        if (Build.VERSION.SDK_INT == 21) {
            float f = 0.0f;
            if (this.y.isEnabled()) {
                this.y.setElevation(this.h);
                if (this.y.isPressed()) {
                    floatingActionButton = this.y;
                    f = this.j;
                } else if (this.y.isFocused() || this.y.isHovered()) {
                    floatingActionButton = this.y;
                    f = this.f1580i;
                }
                floatingActionButton.setTranslationZ(f);
            }
            this.y.setElevation(0.0f);
            floatingActionButton = this.y;
            floatingActionButton.setTranslationZ(f);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void F(float f, float f2, float f3) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 == 21) {
            this.y.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(d.G, m0(f, f3));
            stateListAnimator.addState(d.H, m0(f, f2));
            stateListAnimator.addState(d.I, m0(f, f2));
            stateListAnimator.addState(d.J, m0(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.y, "elevation", f).setDuration(0L));
            if (i2 >= 22 && i2 <= 24) {
                FloatingActionButton floatingActionButton = this.y;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(d.F);
            stateListAnimator.addState(d.K, animatorSet);
            stateListAnimator.addState(d.L, m0(0.0f, 0.0f));
            this.y.setStateListAnimator(stateListAnimator);
        }
        if (c0()) {
            i0();
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    boolean N() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void Y(@Nullable ColorStateList colorStateList) {
        Drawable drawable = this.f1578c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(RippleUtils.sanitizeRippleDrawableColor(colorStateList));
        } else {
            super.Y(colorStateList);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    boolean c0() {
        return this.z.isCompatPaddingEnabled() || !e0();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void g0() {
    }

    @Override // com.google.android.material.floatingactionbutton.d
    @NonNull
    MaterialShapeDrawable j() {
        return new a((ShapeAppearanceModel) Preconditions.checkNotNull(this.a));
    }

    @NonNull
    c l0(int i2, ColorStateList colorStateList) {
        Context context = this.y.getContext();
        c cVar = new c((ShapeAppearanceModel) Preconditions.checkNotNull(this.a));
        cVar.e(ContextCompat.getColor(context, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_outer_color));
        cVar.d(i2);
        cVar.c(colorStateList);
        return cVar;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public float n() {
        return this.y.getElevation();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void s(@NonNull Rect rect) {
        if (this.z.isCompatPaddingEnabled()) {
            super.s(rect);
        } else {
            int sizeDimension = !e0() ? (this.k - this.y.getSizeDimension()) / 2 : 0;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public void x(ColorStateList colorStateList, @Nullable PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        Drawable drawable;
        MaterialShapeDrawable j = j();
        this.b = j;
        j.setTintList(colorStateList);
        if (mode != null) {
            this.b.setTintMode(mode);
        }
        this.b.initializeElevationOverlay(this.y.getContext());
        if (i2 > 0) {
            this.f1579d = l0(i2, colorStateList);
            drawable = new LayerDrawable(new Drawable[]{(Drawable) Preconditions.checkNotNull(this.f1579d), (Drawable) Preconditions.checkNotNull(this.b)});
        } else {
            this.f1579d = null;
            drawable = this.b;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(colorStateList2), drawable, null);
        this.f1578c = rippleDrawable;
        this.e = rippleDrawable;
    }
}
