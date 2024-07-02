package com.google.android.material.floatingactionbutton;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ImageMatrixProperty;
import com.google.android.material.animation.MatrixEvaluator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.internal.StateListAnimator;
import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shadow.ShadowViewDelegate;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class d {
    static final TimeInterpolator F = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    static final int[] G = {R.attr.state_pressed, R.attr.state_enabled};
    static final int[] H = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    static final int[] I = {R.attr.state_focused, R.attr.state_enabled};
    static final int[] J = {R.attr.state_hovered, R.attr.state_enabled};
    static final int[] K = {R.attr.state_enabled};
    static final int[] L = new int[0];

    @Nullable
    private ViewTreeObserver.OnPreDrawListener E;

    @Nullable
    ShapeAppearanceModel a;

    @Nullable
    MaterialShapeDrawable b;

    /* renamed from: c */
    @Nullable
    Drawable f1578c;

    /* renamed from: d */
    @Nullable
    com.google.android.material.floatingactionbutton.c f1579d;

    @Nullable
    Drawable e;
    boolean f;
    float h;

    /* renamed from: i */
    float f1580i;
    float j;
    int k;

    @NonNull
    private final StateListAnimator l;

    @Nullable
    private MotionSpec m;

    @Nullable
    private MotionSpec n;

    @Nullable
    private Animator o;

    @Nullable
    private MotionSpec p;

    @Nullable
    private MotionSpec q;
    private float r;
    private int t;
    private ArrayList<Animator.AnimatorListener> v;
    private ArrayList<Animator.AnimatorListener> w;
    private ArrayList<i> x;
    final FloatingActionButton y;
    final ShadowViewDelegate z;
    boolean g = true;
    private float s = 1.0f;
    private int u = 0;
    private final Rect A = new Rect();
    private final RectF B = new RectF();
    private final RectF C = new RectF();
    private final Matrix D = new Matrix();

    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        private boolean a;
        final /* synthetic */ boolean b;

        /* renamed from: c */
        final /* synthetic */ j f1581c;

        a(boolean z, j jVar) {
            this.b = z;
            this.f1581c = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.u = 0;
            d.this.o = null;
            if (this.a) {
                return;
            }
            FloatingActionButton floatingActionButton = d.this.y;
            boolean z = this.b;
            floatingActionButton.internalSetVisibility(z ? 8 : 4, z);
            j jVar = this.f1581c;
            if (jVar != null) {
                jVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.y.internalSetVisibility(0, this.b);
            d.this.u = 1;
            d.this.o = animator;
            this.a = false;
        }
    }

    /* loaded from: classes.dex */
    public class b extends AnimatorListenerAdapter {
        final /* synthetic */ boolean a;
        final /* synthetic */ j b;

        b(boolean z, j jVar) {
            this.a = z;
            this.b = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.u = 0;
            d.this.o = null;
            j jVar = this.b;
            if (jVar != null) {
                jVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.y.internalSetVisibility(0, this.a);
            d.this.u = 2;
            d.this.o = animator;
        }
    }

    /* loaded from: classes.dex */
    public class c extends MatrixEvaluator {
        c() {
        }

        @Override // com.google.android.material.animation.MatrixEvaluator, android.animation.TypeEvaluator
        public Matrix evaluate(float f, @NonNull Matrix matrix, @NonNull Matrix matrix2) {
            d.this.s = f;
            return super.evaluate(f, matrix, matrix2);
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.d$d */
    /* loaded from: classes.dex */
    public class C0055d implements TypeEvaluator<Float> {
        FloatEvaluator a = new FloatEvaluator();

        C0055d(d dVar) {
        }

        @Override // android.animation.TypeEvaluator
        /* renamed from: a */
        public Float evaluate(float f, Float f2, Float f3) {
            float floatValue = this.a.evaluate(f, (Number) f2, (Number) f3).floatValue();
            if (floatValue < 0.1f) {
                floatValue = 0.0f;
            }
            return Float.valueOf(floatValue);
        }
    }

    /* loaded from: classes.dex */
    public class e implements ViewTreeObserver.OnPreDrawListener {
        e() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            d.this.H();
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class f extends l {
        f(d dVar) {
            super(dVar, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.l
        protected float a() {
            return 0.0f;
        }
    }

    /* loaded from: classes.dex */
    public class g extends l {
        g() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.l
        protected float a() {
            d dVar = d.this;
            return dVar.h + dVar.f1580i;
        }
    }

    /* loaded from: classes.dex */
    public class h extends l {
        h() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.l
        protected float a() {
            d dVar = d.this;
            return dVar.h + dVar.j;
        }
    }

    /* loaded from: classes.dex */
    public interface i {
        void a();

        void b();
    }

    /* loaded from: classes.dex */
    public interface j {
        void a();

        void b();
    }

    /* loaded from: classes.dex */
    public class k extends l {
        k() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.l
        protected float a() {
            return d.this.h;
        }
    }

    /* loaded from: classes.dex */
    public abstract class l extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private boolean a;
        private float b;

        /* renamed from: c */
        private float f1585c;

        private l() {
        }

        /* synthetic */ l(d dVar, a aVar) {
            this();
        }

        protected abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.j0((int) this.f1585c);
            this.a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            if (!this.a) {
                MaterialShapeDrawable materialShapeDrawable = d.this.b;
                this.b = materialShapeDrawable == null ? 0.0f : materialShapeDrawable.getElevation();
                this.f1585c = a();
                this.a = true;
            }
            d dVar = d.this;
            float f = this.b;
            dVar.j0((int) (f + ((this.f1585c - f) * valueAnimator.getAnimatedFraction())));
        }
    }

    public d(FloatingActionButton floatingActionButton, ShadowViewDelegate shadowViewDelegate) {
        this.y = floatingActionButton;
        this.z = shadowViewDelegate;
        StateListAnimator stateListAnimator = new StateListAnimator();
        this.l = stateListAnimator;
        stateListAnimator.addState(G, i(new h()));
        stateListAnimator.addState(H, i(new g()));
        stateListAnimator.addState(I, i(new g()));
        stateListAnimator.addState(J, i(new g()));
        stateListAnimator.addState(K, i(new k()));
        stateListAnimator.addState(L, i(new f(this)));
        this.r = floatingActionButton.getRotation();
    }

    private boolean d0() {
        return ViewCompat.isLaidOut(this.y) && !this.y.isInEditMode();
    }

    private void g(float f2, @NonNull Matrix matrix) {
        matrix.reset();
        if (this.y.getDrawable() == null || this.t == 0) {
            return;
        }
        RectF rectF = this.B;
        RectF rectF2 = this.C;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i2 = this.t;
        rectF2.set(0.0f, 0.0f, i2, i2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i3 = this.t;
        matrix.postScale(f2, f2, i3 / 2.0f, i3 / 2.0f);
    }

    @NonNull
    private AnimatorSet h(@NonNull MotionSpec motionSpec, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.ALPHA, f2);
        motionSpec.getTiming("opacity").apply(ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        motionSpec.getTiming("scale").apply(ofFloat2);
        k0(ofFloat2);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        motionSpec.getTiming("scale").apply(ofFloat3);
        k0(ofFloat3);
        arrayList.add(ofFloat3);
        g(f4, this.D);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.y, new ImageMatrixProperty(), new c(), new Matrix(this.D));
        motionSpec.getTiming("iconScale").apply(ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    @NonNull
    private ValueAnimator i(@NonNull l lVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(F);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(lVar);
        valueAnimator.addUpdateListener(lVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    private void k0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new C0055d(this));
    }

    private MotionSpec l() {
        if (this.n == null) {
            this.n = MotionSpec.createFromResource(this.y.getContext(), com.google.android.material.R.animator.design_fab_hide_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.n);
    }

    private MotionSpec m() {
        if (this.m == null) {
            this.m = MotionSpec.createFromResource(this.y.getContext(), com.google.android.material.R.animator.design_fab_show_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.m);
    }

    @NonNull
    private ViewTreeObserver.OnPreDrawListener r() {
        if (this.E == null) {
            this.E = new e();
        }
        return this.E;
    }

    public void A() {
        this.l.jumpToCurrentState();
    }

    public void B() {
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            MaterialShapeUtils.setParentAbsoluteElevation(this.y, materialShapeDrawable);
        }
        if (N()) {
            this.y.getViewTreeObserver().addOnPreDrawListener(r());
        }
    }

    public void C() {
    }

    public void D() {
        ViewTreeObserver viewTreeObserver = this.y.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.E;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.E = null;
        }
    }

    public void E(int[] iArr) {
        this.l.setState(iArr);
    }

    void F(float f2, float f3, float f4) {
        i0();
        j0(f2);
    }

    void G(@NonNull Rect rect) {
        ShadowViewDelegate shadowViewDelegate;
        Drawable drawable;
        Preconditions.checkNotNull(this.e, "Didn't initialize content background");
        if (c0()) {
            drawable = new InsetDrawable(this.e, rect.left, rect.top, rect.right, rect.bottom);
            shadowViewDelegate = this.z;
        } else {
            shadowViewDelegate = this.z;
            drawable = this.e;
        }
        shadowViewDelegate.setBackgroundDrawable(drawable);
    }

    void H() {
        float rotation = this.y.getRotation();
        if (this.r != rotation) {
            this.r = rotation;
            g0();
        }
    }

    public void I() {
        ArrayList<i> arrayList = this.x;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public void J() {
        ArrayList<i> arrayList = this.x;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    public void K(@NonNull Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.w;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
    }

    public void L(@NonNull Animator.AnimatorListener animatorListener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.v;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(animatorListener);
    }

    public void M(@NonNull i iVar) {
        ArrayList<i> arrayList = this.x;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(iVar);
    }

    boolean N() {
        return true;
    }

    public void O(@Nullable ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setTintList(colorStateList);
        }
        com.google.android.material.floatingactionbutton.c cVar = this.f1579d;
        if (cVar != null) {
            cVar.c(colorStateList);
        }
    }

    public void P(@Nullable PorterDuff.Mode mode) {
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setTintMode(mode);
        }
    }

    public final void Q(float f2) {
        if (this.h != f2) {
            this.h = f2;
            F(f2, this.f1580i, this.j);
        }
    }

    public void R(boolean z) {
        this.f = z;
    }

    public final void S(@Nullable MotionSpec motionSpec) {
        this.q = motionSpec;
    }

    public final void T(float f2) {
        if (this.f1580i != f2) {
            this.f1580i = f2;
            F(this.h, f2, this.j);
        }
    }

    final void U(float f2) {
        this.s = f2;
        Matrix matrix = this.D;
        g(f2, matrix);
        this.y.setImageMatrix(matrix);
    }

    public final void V(int i2) {
        if (this.t != i2) {
            this.t = i2;
            h0();
        }
    }

    public void W(int i2) {
        this.k = i2;
    }

    public final void X(float f2) {
        if (this.j != f2) {
            this.j = f2;
            F(this.h, this.f1580i, f2);
        }
    }

    public void Y(@Nullable ColorStateList colorStateList) {
        Drawable drawable = this.f1578c;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, RippleUtils.sanitizeRippleDrawableColor(colorStateList));
        }
    }

    public void Z(boolean z) {
        this.g = z;
        i0();
    }

    public final void a0(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.a = shapeAppearanceModel;
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
        Object obj = this.f1578c;
        if (obj instanceof Shapeable) {
            ((Shapeable) obj).setShapeAppearanceModel(shapeAppearanceModel);
        }
        com.google.android.material.floatingactionbutton.c cVar = this.f1579d;
        if (cVar != null) {
            cVar.f(shapeAppearanceModel);
        }
    }

    public final void b0(@Nullable MotionSpec motionSpec) {
        this.p = motionSpec;
    }

    boolean c0() {
        return true;
    }

    public void d(@NonNull Animator.AnimatorListener animatorListener) {
        if (this.w == null) {
            this.w = new ArrayList<>();
        }
        this.w.add(animatorListener);
    }

    public void e(@NonNull Animator.AnimatorListener animatorListener) {
        if (this.v == null) {
            this.v = new ArrayList<>();
        }
        this.v.add(animatorListener);
    }

    public final boolean e0() {
        return !this.f || this.y.getSizeDimension() >= this.k;
    }

    public void f(@NonNull i iVar) {
        if (this.x == null) {
            this.x = new ArrayList<>();
        }
        this.x.add(iVar);
    }

    public void f0(@Nullable j jVar, boolean z) {
        if (z()) {
            return;
        }
        Animator animator = this.o;
        if (animator != null) {
            animator.cancel();
        }
        if (!d0()) {
            this.y.internalSetVisibility(0, z);
            this.y.setAlpha(1.0f);
            this.y.setScaleY(1.0f);
            this.y.setScaleX(1.0f);
            U(1.0f);
            if (jVar != null) {
                jVar.a();
                return;
            }
            return;
        }
        if (this.y.getVisibility() != 0) {
            this.y.setAlpha(0.0f);
            this.y.setScaleY(0.0f);
            this.y.setScaleX(0.0f);
            U(0.0f);
        }
        MotionSpec motionSpec = this.p;
        if (motionSpec == null) {
            motionSpec = m();
        }
        AnimatorSet h2 = h(motionSpec, 1.0f, 1.0f, 1.0f);
        h2.addListener(new b(z, jVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.v;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                h2.addListener(it.next());
            }
        }
        h2.start();
    }

    void g0() {
        FloatingActionButton floatingActionButton;
        int i2;
        if (Build.VERSION.SDK_INT == 19) {
            if (this.r % 90.0f != 0.0f) {
                i2 = 1;
                if (this.y.getLayerType() != 1) {
                    floatingActionButton = this.y;
                    floatingActionButton.setLayerType(i2, null);
                }
            } else if (this.y.getLayerType() != 0) {
                floatingActionButton = this.y;
                i2 = 0;
                floatingActionButton.setLayerType(i2, null);
            }
        }
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShadowCompatRotation((int) this.r);
        }
    }

    public final void h0() {
        U(this.s);
    }

    public final void i0() {
        Rect rect = this.A;
        s(rect);
        G(rect);
        this.z.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    MaterialShapeDrawable j() {
        return new MaterialShapeDrawable((ShapeAppearanceModel) Preconditions.checkNotNull(this.a));
    }

    public void j0(float f2) {
        MaterialShapeDrawable materialShapeDrawable = this.b;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setElevation(f2);
        }
    }

    @Nullable
    public final Drawable k() {
        return this.e;
    }

    public float n() {
        return this.h;
    }

    public boolean o() {
        return this.f;
    }

    @Nullable
    public final MotionSpec p() {
        return this.q;
    }

    public float q() {
        return this.f1580i;
    }

    public void s(@NonNull Rect rect) {
        int sizeDimension = this.f ? (this.k - this.y.getSizeDimension()) / 2 : 0;
        int max = Math.max(sizeDimension, (int) Math.ceil(this.g ? n() + this.j : 0.0f));
        int max2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(max, max2, max, max2);
    }

    public float t() {
        return this.j;
    }

    @Nullable
    public final ShapeAppearanceModel u() {
        return this.a;
    }

    @Nullable
    public final MotionSpec v() {
        return this.p;
    }

    public void w(@Nullable j jVar, boolean z) {
        if (y()) {
            return;
        }
        Animator animator = this.o;
        if (animator != null) {
            animator.cancel();
        }
        if (!d0()) {
            this.y.internalSetVisibility(z ? 8 : 4, z);
            if (jVar != null) {
                jVar.b();
                return;
            }
            return;
        }
        MotionSpec motionSpec = this.q;
        if (motionSpec == null) {
            motionSpec = l();
        }
        AnimatorSet h2 = h(motionSpec, 0.0f, 0.0f, 0.0f);
        h2.addListener(new a(z, jVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.w;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                h2.addListener(it.next());
            }
        }
        h2.start();
    }

    public void x(ColorStateList colorStateList, @Nullable PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        MaterialShapeDrawable j2 = j();
        this.b = j2;
        j2.setTintList(colorStateList);
        if (mode != null) {
            this.b.setTintMode(mode);
        }
        this.b.setShadowColor(-12303292);
        this.b.initializeElevationOverlay(this.y.getContext());
        RippleDrawableCompat rippleDrawableCompat = new RippleDrawableCompat(this.b.getShapeAppearanceModel());
        rippleDrawableCompat.setTintList(RippleUtils.sanitizeRippleDrawableColor(colorStateList2));
        this.f1578c = rippleDrawableCompat;
        this.e = new LayerDrawable(new Drawable[]{(Drawable) Preconditions.checkNotNull(this.b), rippleDrawableCompat});
    }

    public boolean y() {
        return this.y.getVisibility() == 0 ? this.u == 1 : this.u != 2;
    }

    public boolean z() {
        return this.y.getVisibility() != 0 ? this.u == 2 : this.u != 1;
    }
}
