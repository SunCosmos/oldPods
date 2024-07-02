package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class BaseProgressIndicator<S extends BaseProgressIndicatorSpec> extends ProgressBar {
    public static final int HIDE_INWARD = 2;
    public static final int HIDE_NONE = 0;
    public static final int HIDE_OUTWARD = 1;
    public static final int SHOW_INWARD = 2;
    public static final int SHOW_NONE = 0;
    public static final int SHOW_OUTWARD = 1;
    static final int o = R.style.Widget_MaterialComponents_ProgressIndicator;
    S a;
    private int b;

    /* renamed from: c */
    private boolean f1629c;

    /* renamed from: d */
    private boolean f1630d;
    private final int e;
    private final int f;
    private long g;
    AnimatorDurationScaleProvider h;

    /* renamed from: i */
    private boolean f1631i;
    private int j;
    private final Runnable k;
    private final Runnable l;
    private final Animatable2Compat.AnimationCallback m;
    private final Animatable2Compat.AnimationCallback n;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface HideAnimationBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ShowAnimationBehavior {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseProgressIndicator.this.k();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseProgressIndicator.this.j();
            BaseProgressIndicator.this.g = -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends Animatable2Compat.AnimationCallback {
        c() {
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            BaseProgressIndicator.this.setIndeterminate(false);
            BaseProgressIndicator.this.setProgressCompat(0, false);
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            baseProgressIndicator.setProgressCompat(baseProgressIndicator.b, BaseProgressIndicator.this.f1629c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends Animatable2Compat.AnimationCallback {
        d() {
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            if (BaseProgressIndicator.this.f1631i) {
                return;
            }
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            baseProgressIndicator.setVisibility(baseProgressIndicator.j);
        }
    }

    public BaseProgressIndicator(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i2, o), attributeSet, i2);
        this.g = -1L;
        this.f1631i = false;
        this.j = 4;
        this.k = new a();
        this.l = new b();
        this.m = new c();
        this.n = new d();
        Context context2 = getContext();
        this.a = i(context2, attributeSet);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R.styleable.BaseProgressIndicator, i2, i3, new int[0]);
        this.e = obtainStyledAttributes.getInt(R.styleable.BaseProgressIndicator_showDelay, -1);
        this.f = Math.min(obtainStyledAttributes.getInt(R.styleable.BaseProgressIndicator_minHideDelay, -1), 1000);
        obtainStyledAttributes.recycle();
        this.h = new AnimatorDurationScaleProvider();
        this.f1630d = true;
    }

    @Nullable
    private com.google.android.material.progressindicator.d<S> getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().n();
        }
        if (getProgressDrawable() == null) {
            return null;
        }
        return getProgressDrawable().o();
    }

    public void j() {
        ((com.google.android.material.progressindicator.c) getCurrentDrawable()).setVisible(false, false, true);
        if (m()) {
            setVisibility(4);
        }
    }

    public void k() {
        if (this.f > 0) {
            this.g = SystemClock.uptimeMillis();
        }
        setVisibility(0);
    }

    private boolean m() {
        return (getProgressDrawable() == null || !getProgressDrawable().isVisible()) && (getIndeterminateDrawable() == null || !getIndeterminateDrawable().isVisible());
    }

    private void n() {
        if (getProgressDrawable() != null && getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().m().d(this.m);
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().registerAnimationCallback(this.n);
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().registerAnimationCallback(this.n);
        }
    }

    private void o() {
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().unregisterAnimationCallback(this.n);
            getIndeterminateDrawable().m().h();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().unregisterAnimationCallback(this.n);
        }
    }

    @Override // android.widget.ProgressBar
    @Nullable
    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.a.hideAnimationBehavior;
    }

    @Override // android.widget.ProgressBar
    @Nullable
    public IndeterminateDrawable<S> getIndeterminateDrawable() {
        return (IndeterminateDrawable) super.getIndeterminateDrawable();
    }

    @NonNull
    public int[] getIndicatorColor() {
        return this.a.indicatorColors;
    }

    @Override // android.widget.ProgressBar
    @Nullable
    public DeterminateDrawable<S> getProgressDrawable() {
        return (DeterminateDrawable) super.getProgressDrawable();
    }

    public int getShowAnimationBehavior() {
        return this.a.showAnimationBehavior;
    }

    @ColorInt
    public int getTrackColor() {
        return this.a.trackColor;
    }

    @Px
    public int getTrackCornerRadius() {
        return this.a.trackCornerRadius;
    }

    @Px
    public int getTrackThickness() {
        return this.a.trackThickness;
    }

    protected void h(boolean z) {
        if (this.f1630d) {
            ((com.google.android.material.progressindicator.c) getCurrentDrawable()).setVisible(p(), false, z);
        }
    }

    public void hide() {
        if (getVisibility() != 0) {
            removeCallbacks(this.k);
            return;
        }
        removeCallbacks(this.l);
        long uptimeMillis = SystemClock.uptimeMillis() - this.g;
        int i2 = this.f;
        if (uptimeMillis >= ((long) i2)) {
            this.l.run();
        } else {
            postDelayed(this.l, i2 - uptimeMillis);
        }
    }

    abstract S i(@NonNull Context context, @NonNull AttributeSet attributeSet);

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    boolean l() {
        View view = this;
        while (view.getVisibility() == 0) {
            Object parent = view.getParent();
            if (parent == null) {
                return getWindowVisibility() == 0;
            }
            if (!(parent instanceof View)) {
                return true;
            }
            view = (View) parent;
        }
        return false;
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        n();
        if (p()) {
            k();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.l);
        removeCallbacks(this.k);
        ((com.google.android.material.progressindicator.c) getCurrentDrawable()).hideNow();
        o();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(@NonNull Canvas canvas) {
        int save = canvas.save();
        if (getPaddingLeft() != 0 || getPaddingTop() != 0) {
            canvas.translate(getPaddingLeft(), getPaddingTop());
        }
        if (getPaddingRight() != 0 || getPaddingBottom() != 0) {
            canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
        }
        getCurrentDrawable().draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        com.google.android.material.progressindicator.d<S> currentDrawingDelegate = getCurrentDrawingDelegate();
        if (currentDrawingDelegate == null) {
            return;
        }
        int e = currentDrawingDelegate.e();
        int d2 = currentDrawingDelegate.d();
        setMeasuredDimension(e < 0 ? getMeasuredWidth() : e + getPaddingLeft() + getPaddingRight(), d2 < 0 ? getMeasuredHeight() : d2 + getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i2) {
        super.onVisibilityChanged(view, i2);
        h(i2 == 0);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        h(false);
    }

    public boolean p() {
        return ViewCompat.isAttachedToWindow(this) && getWindowVisibility() == 0 && l();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public void setAnimatorDurationScaleProvider(@NonNull AnimatorDurationScaleProvider animatorDurationScaleProvider) {
        this.h = animatorDurationScaleProvider;
        if (getProgressDrawable() != null) {
            getProgressDrawable().f1636c = animatorDurationScaleProvider;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().f1636c = animatorDurationScaleProvider;
        }
    }

    public void setHideAnimationBehavior(int i2) {
        this.a.hideAnimationBehavior = i2;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        if (z == isIndeterminate()) {
            return;
        }
        if (p() && z) {
            throw new IllegalStateException("Cannot switch to indeterminate mode while the progress indicator is visible.");
        }
        com.google.android.material.progressindicator.c cVar = (com.google.android.material.progressindicator.c) getCurrentDrawable();
        if (cVar != null) {
            cVar.hideNow();
        }
        super.setIndeterminate(z);
        com.google.android.material.progressindicator.c cVar2 = (com.google.android.material.progressindicator.c) getCurrentDrawable();
        if (cVar2 != null) {
            cVar2.setVisible(p(), false, false);
        }
        this.f1631i = false;
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(@Nullable Drawable drawable) {
        if (drawable == null) {
            super.setIndeterminateDrawable(null);
        } else {
            if (!(drawable instanceof IndeterminateDrawable)) {
                throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
            }
            ((com.google.android.material.progressindicator.c) drawable).hideNow();
            super.setIndeterminateDrawable(drawable);
        }
    }

    public void setIndicatorColor(@ColorInt int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{MaterialColors.getColor(getContext(), R.attr.colorPrimary, -1)};
        }
        if (Arrays.equals(getIndicatorColor(), iArr)) {
            return;
        }
        this.a.indicatorColors = iArr;
        getIndeterminateDrawable().m().c();
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i2) {
        if (isIndeterminate()) {
            return;
        }
        setProgressCompat(i2, false);
    }

    public void setProgressCompat(int i2, boolean z) {
        if (!isIndeterminate()) {
            super.setProgress(i2);
            if (getProgressDrawable() == null || z) {
                return;
            }
            getProgressDrawable().jumpToCurrentState();
            return;
        }
        if (getProgressDrawable() != null) {
            this.b = i2;
            this.f1629c = z;
            this.f1631i = true;
            if (!getIndeterminateDrawable().isVisible() || this.h.getSystemAnimatorDurationScale(getContext().getContentResolver()) == 0.0f) {
                this.m.onAnimationEnd(getIndeterminateDrawable());
            } else {
                getIndeterminateDrawable().m().f();
            }
        }
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(@Nullable Drawable drawable) {
        if (drawable == null) {
            super.setProgressDrawable(null);
        } else {
            if (!(drawable instanceof DeterminateDrawable)) {
                throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
            }
            DeterminateDrawable determinateDrawable = (DeterminateDrawable) drawable;
            determinateDrawable.hideNow();
            super.setProgressDrawable(determinateDrawable);
            determinateDrawable.s(getProgress() / getMax());
        }
    }

    public void setShowAnimationBehavior(int i2) {
        this.a.showAnimationBehavior = i2;
        invalidate();
    }

    public void setTrackColor(@ColorInt int i2) {
        S s = this.a;
        if (s.trackColor != i2) {
            s.trackColor = i2;
            invalidate();
        }
    }

    public void setTrackCornerRadius(@Px int i2) {
        S s = this.a;
        if (s.trackCornerRadius != i2) {
            s.trackCornerRadius = Math.min(i2, s.trackThickness / 2);
        }
    }

    public void setTrackThickness(@Px int i2) {
        S s = this.a;
        if (s.trackThickness != i2) {
            s.trackThickness = i2;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i2) {
        if (i2 != 0 && i2 != 4 && i2 != 8) {
            throw new IllegalArgumentException("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
        this.j = i2;
    }

    public void show() {
        if (this.e <= 0) {
            this.k.run();
        } else {
            removeCallbacks(this.k);
            postDelayed(this.k, this.e);
        }
    }
}
