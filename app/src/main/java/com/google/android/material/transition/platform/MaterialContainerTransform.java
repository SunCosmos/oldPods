package com.google.android.material.transition.platform;

import android.R;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.ArcMotion;
import android.transition.PathMotion;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.transition.platform.j;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresApi(21)
/* loaded from: classes.dex */
public final class MaterialContainerTransform extends Transition {
    private static final c C;
    private static final c E;
    public static final int FADE_MODE_CROSS = 2;
    public static final int FADE_MODE_IN = 0;
    public static final int FADE_MODE_OUT = 1;
    public static final int FADE_MODE_THROUGH = 3;
    public static final int FIT_MODE_AUTO = 0;
    public static final int FIT_MODE_HEIGHT = 2;
    public static final int FIT_MODE_WIDTH = 1;
    public static final int TRANSITION_DIRECTION_AUTO = 0;
    public static final int TRANSITION_DIRECTION_ENTER = 1;
    public static final int TRANSITION_DIRECTION_RETURN = 2;
    private boolean a;
    private boolean b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1786c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1787d;

    @IdRes
    private int e;

    @IdRes
    private int f;

    @IdRes
    private int g;

    @ColorInt
    private int h;

    /* renamed from: i, reason: collision with root package name */
    @ColorInt
    private int f1788i;

    @ColorInt
    private int j;

    @ColorInt
    private int k;
    private int l;
    private int m;
    private int n;

    @Nullable
    private View o;

    @Nullable
    private View p;

    @Nullable
    private ShapeAppearanceModel q;

    @Nullable
    private ShapeAppearanceModel r;

    @Nullable
    private ProgressThresholds s;

    @Nullable
    private ProgressThresholds t;

    @Nullable
    private ProgressThresholds u;

    @Nullable
    private ProgressThresholds v;
    private boolean w;
    private float x;
    private float y;
    private static final String z = MaterialContainerTransform.class.getSimpleName();
    private static final String[] A = {"materialContainerTransition:bounds", "materialContainerTransition:shapeAppearance"};
    private static final c B = new c(new ProgressThresholds(0.0f, 0.25f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.75f), null);
    private static final c D = new c(new ProgressThresholds(0.1f, 0.4f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 0.9f), null);

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface FadeMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface FitMode {
    }

    /* loaded from: classes.dex */
    public static class ProgressThresholds {

        @FloatRange(from = 0.0d, to = 1.0d)
        private final float a;

        @FloatRange(from = 0.0d, to = 1.0d)
        private final float b;

        public ProgressThresholds(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
            this.a = f;
            this.b = f2;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float getEnd() {
            return this.b;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float getStart() {
            return this.a;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface TransitionDirection {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ d a;

        a(MaterialContainerTransform materialContainerTransform, d dVar) {
            this.a = dVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.o(valueAnimator.getAnimatedFraction());
        }
    }

    /* loaded from: classes.dex */
    class b extends i {
        final /* synthetic */ View a;
        final /* synthetic */ d b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1789c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ View f1790d;

        b(View view, d dVar, View view2, View view3) {
            this.a = view;
            this.b = dVar;
            this.f1789c = view2;
            this.f1790d = view3;
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            MaterialContainerTransform.this.removeListener(this);
            if (MaterialContainerTransform.this.b) {
                return;
            }
            this.f1789c.setAlpha(1.0f);
            this.f1790d.setAlpha(1.0f);
            ViewUtils.getOverlay(this.a).remove(this.b);
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
            ViewUtils.getOverlay(this.a).add(this.b);
            this.f1789c.setAlpha(0.0f);
            this.f1790d.setAlpha(0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        @NonNull
        private final ProgressThresholds a;

        @NonNull
        private final ProgressThresholds b;

        /* renamed from: c, reason: collision with root package name */
        @NonNull
        private final ProgressThresholds f1791c;

        /* renamed from: d, reason: collision with root package name */
        @NonNull
        private final ProgressThresholds f1792d;

        private c(@NonNull ProgressThresholds progressThresholds, @NonNull ProgressThresholds progressThresholds2, @NonNull ProgressThresholds progressThresholds3, @NonNull ProgressThresholds progressThresholds4) {
            this.a = progressThresholds;
            this.b = progressThresholds2;
            this.f1791c = progressThresholds3;
            this.f1792d = progressThresholds4;
        }

        /* synthetic */ c(ProgressThresholds progressThresholds, ProgressThresholds progressThresholds2, ProgressThresholds progressThresholds3, ProgressThresholds progressThresholds4, a aVar) {
            this(progressThresholds, progressThresholds2, progressThresholds3, progressThresholds4);
        }
    }

    /* loaded from: classes.dex */
    private static final class d extends Drawable {
        private final c A;
        private final com.google.android.material.transition.platform.a B;
        private final com.google.android.material.transition.platform.d C;
        private final boolean D;
        private final Paint E;
        private final Path F;
        private com.google.android.material.transition.platform.c G;
        private f H;
        private RectF I;
        private float J;
        private float K;
        private float L;
        private final View a;
        private final RectF b;

        /* renamed from: c, reason: collision with root package name */
        private final ShapeAppearanceModel f1793c;

        /* renamed from: d, reason: collision with root package name */
        private final float f1794d;
        private final View e;
        private final RectF f;
        private final ShapeAppearanceModel g;
        private final float h;

        /* renamed from: i, reason: collision with root package name */
        private final Paint f1795i;
        private final Paint j;
        private final Paint k;
        private final Paint l;
        private final Paint m;
        private final g n;
        private final PathMeasure o;
        private final float p;
        private final float[] q;
        private final boolean r;
        private final float s;
        private final float t;
        private final boolean u;
        private final MaterialShapeDrawable v;
        private final RectF w;
        private final RectF x;
        private final RectF y;
        private final RectF z;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements j.c {
            a() {
            }

            @Override // com.google.android.material.transition.platform.j.c
            public void a(Canvas canvas) {
                d.this.a.draw(canvas);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b implements j.c {
            b() {
            }

            @Override // com.google.android.material.transition.platform.j.c
            public void a(Canvas canvas) {
                d.this.e.draw(canvas);
            }
        }

        private d(PathMotion pathMotion, View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel, float f, View view2, RectF rectF2, ShapeAppearanceModel shapeAppearanceModel2, float f2, @ColorInt int i2, @ColorInt int i3, @ColorInt int i4, int i5, boolean z, boolean z2, com.google.android.material.transition.platform.a aVar, com.google.android.material.transition.platform.d dVar, c cVar, boolean z3) {
            Paint paint = new Paint();
            this.f1795i = paint;
            Paint paint2 = new Paint();
            this.j = paint2;
            Paint paint3 = new Paint();
            this.k = paint3;
            this.l = new Paint();
            Paint paint4 = new Paint();
            this.m = paint4;
            this.n = new g();
            this.q = r7;
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            this.v = materialShapeDrawable;
            Paint paint5 = new Paint();
            this.E = paint5;
            this.F = new Path();
            this.a = view;
            this.b = rectF;
            this.f1793c = shapeAppearanceModel;
            this.f1794d = f;
            this.e = view2;
            this.f = rectF2;
            this.g = shapeAppearanceModel2;
            this.h = f2;
            this.r = z;
            this.u = z2;
            this.B = aVar;
            this.C = dVar;
            this.A = cVar;
            this.D = z3;
            WindowManager windowManager = (WindowManager) view.getContext().getSystemService("window");
            windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
            this.s = r12.widthPixels;
            this.t = r12.heightPixels;
            paint.setColor(i2);
            paint2.setColor(i3);
            paint3.setColor(i4);
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(0));
            materialShapeDrawable.setShadowCompatibilityMode(2);
            materialShapeDrawable.setShadowBitmapDrawingEnable(false);
            materialShapeDrawable.setShadowColor(-7829368);
            RectF rectF3 = new RectF(rectF);
            this.w = rectF3;
            this.x = new RectF(rectF3);
            RectF rectF4 = new RectF(rectF3);
            this.y = rectF4;
            this.z = new RectF(rectF4);
            PointF m = m(rectF);
            PointF m2 = m(rectF2);
            PathMeasure pathMeasure = new PathMeasure(pathMotion.getPath(m.x, m.y, m2.x, m2.y), false);
            this.o = pathMeasure;
            this.p = pathMeasure.getLength();
            float[] fArr = {rectF.centerX(), rectF.top};
            paint4.setStyle(Paint.Style.FILL);
            paint4.setShader(j.c(i5));
            paint5.setStyle(Paint.Style.STROKE);
            paint5.setStrokeWidth(10.0f);
            p(0.0f);
        }

        /* synthetic */ d(PathMotion pathMotion, View view, RectF rectF, ShapeAppearanceModel shapeAppearanceModel, float f, View view2, RectF rectF2, ShapeAppearanceModel shapeAppearanceModel2, float f2, int i2, int i3, int i4, int i5, boolean z, boolean z2, com.google.android.material.transition.platform.a aVar, com.google.android.material.transition.platform.d dVar, c cVar, boolean z3, a aVar2) {
            this(pathMotion, view, rectF, shapeAppearanceModel, f, view2, rectF2, shapeAppearanceModel2, f2, i2, i3, i4, i5, z, z2, aVar, dVar, cVar, z3);
        }

        private static float d(RectF rectF, float f) {
            return ((rectF.centerX() / (f / 2.0f)) - 1.0f) * 0.3f;
        }

        private static float e(RectF rectF, float f) {
            return (rectF.centerY() / f) * 1.5f;
        }

        private void f(Canvas canvas, RectF rectF, Path path, @ColorInt int i2) {
            PointF m = m(rectF);
            if (this.L == 0.0f) {
                path.reset();
                path.moveTo(m.x, m.y);
            } else {
                path.lineTo(m.x, m.y);
                this.E.setColor(i2);
                canvas.drawPath(path, this.E);
            }
        }

        private void g(Canvas canvas, RectF rectF, @ColorInt int i2) {
            this.E.setColor(i2);
            canvas.drawRect(rectF, this.E);
        }

        private void h(Canvas canvas) {
            canvas.save();
            canvas.clipPath(this.n.d(), Region.Op.DIFFERENCE);
            if (Build.VERSION.SDK_INT > 28) {
                j(canvas);
            } else {
                i(canvas);
            }
            canvas.restore();
        }

        private void i(Canvas canvas) {
            MaterialShapeDrawable materialShapeDrawable = this.v;
            RectF rectF = this.I;
            materialShapeDrawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            this.v.setElevation(this.J);
            this.v.setShadowVerticalOffset((int) this.K);
            this.v.setShapeAppearanceModel(this.n.c());
            this.v.draw(canvas);
        }

        private void j(Canvas canvas) {
            ShapeAppearanceModel c2 = this.n.c();
            if (!c2.isRoundRect(this.I)) {
                canvas.drawPath(this.n.d(), this.l);
            } else {
                float cornerSize = c2.getTopLeftCornerSize().getCornerSize(this.I);
                canvas.drawRoundRect(this.I, cornerSize, cornerSize, this.l);
            }
        }

        private void k(Canvas canvas) {
            n(canvas, this.k);
            Rect bounds = getBounds();
            RectF rectF = this.y;
            j.y(canvas, bounds, rectF.left, rectF.top, this.H.b, this.G.b, new b());
        }

        private void l(Canvas canvas) {
            n(canvas, this.j);
            Rect bounds = getBounds();
            RectF rectF = this.w;
            j.y(canvas, bounds, rectF.left, rectF.top, this.H.a, this.G.a, new a());
        }

        private static PointF m(RectF rectF) {
            return new PointF(rectF.centerX(), rectF.top);
        }

        private void n(Canvas canvas, Paint paint) {
            if (paint.getColor() == 0 || paint.getAlpha() <= 0) {
                return;
            }
            canvas.drawRect(getBounds(), paint);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void o(float f) {
            if (this.L != f) {
                p(f);
            }
        }

        private void p(float f) {
            float f2;
            float f3;
            this.L = f;
            this.m.setAlpha((int) (this.r ? j.n(0.0f, 255.0f, f) : j.n(255.0f, 0.0f, f)));
            this.o.getPosTan(this.p * f, this.q, null);
            float[] fArr = this.q;
            float f4 = fArr[0];
            float f5 = fArr[1];
            if (f > 1.0f || f < 0.0f) {
                if (f > 1.0f) {
                    f2 = 0.99f;
                    f3 = (f - 1.0f) / 0.00999999f;
                } else {
                    f2 = 0.01f;
                    f3 = (f / 0.01f) * (-1.0f);
                }
                this.o.getPosTan(this.p * f2, fArr, null);
                float[] fArr2 = this.q;
                f4 += (f4 - fArr2[0]) * f3;
                f5 += (f5 - fArr2[1]) * f3;
            }
            float f6 = f4;
            float f7 = f5;
            f a2 = this.C.a(f, ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.b.a))).floatValue(), ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.b.b))).floatValue(), this.b.width(), this.b.height(), this.f.width(), this.f.height());
            this.H = a2;
            RectF rectF = this.w;
            float f8 = a2.f1808c;
            rectF.set(f6 - (f8 / 2.0f), f7, (f8 / 2.0f) + f6, a2.f1809d + f7);
            RectF rectF2 = this.y;
            f fVar = this.H;
            float f9 = fVar.e;
            rectF2.set(f6 - (f9 / 2.0f), f7, f6 + (f9 / 2.0f), fVar.f + f7);
            this.x.set(this.w);
            this.z.set(this.y);
            float floatValue = ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.f1791c.a))).floatValue();
            float floatValue2 = ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.f1791c.b))).floatValue();
            boolean c2 = this.C.c(this.H);
            RectF rectF3 = c2 ? this.x : this.z;
            float o = j.o(0.0f, 1.0f, floatValue, floatValue2, f);
            if (!c2) {
                o = 1.0f - o;
            }
            this.C.b(rectF3, o, this.H);
            this.I = new RectF(Math.min(this.x.left, this.z.left), Math.min(this.x.top, this.z.top), Math.max(this.x.right, this.z.right), Math.max(this.x.bottom, this.z.bottom));
            this.n.b(f, this.f1793c, this.g, this.w, this.x, this.z, this.A.f1792d);
            this.J = j.n(this.f1794d, this.h, f);
            float d2 = d(this.I, this.s);
            float e = e(this.I, this.t);
            float f10 = this.J;
            float f11 = (int) (e * f10);
            this.K = f11;
            this.l.setShadowLayer(f10, (int) (d2 * f10), f11, 754974720);
            this.G = this.B.a(f, ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.a.a))).floatValue(), ((Float) Preconditions.checkNotNull(Float.valueOf(this.A.a.b))).floatValue(), 0.35f);
            if (this.j.getColor() != 0) {
                this.j.setAlpha(this.G.a);
            }
            if (this.k.getColor() != 0) {
                this.k.setAlpha(this.G.b);
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            if (this.m.getAlpha() > 0) {
                canvas.drawRect(getBounds(), this.m);
            }
            int save = this.D ? canvas.save() : -1;
            if (this.u && this.J > 0.0f) {
                h(canvas);
            }
            this.n.a(canvas);
            n(canvas, this.f1795i);
            if (this.G.f1807c) {
                l(canvas);
                k(canvas);
            } else {
                k(canvas);
                l(canvas);
            }
            if (this.D) {
                canvas.restoreToCount(save);
                f(canvas, this.w, this.F, -65281);
                g(canvas, this.x, -256);
                g(canvas, this.w, -16711936);
                g(canvas, this.z, -16711681);
                g(canvas, this.y, -16776961);
            }
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i2) {
            throw new UnsupportedOperationException("Setting alpha on is not supported");
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            throw new UnsupportedOperationException("Setting a color filter is not supported");
        }
    }

    static {
        a aVar = null;
        C = new c(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.3f, 0.9f), aVar);
        E = new c(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.2f, 0.9f), aVar);
    }

    public MaterialContainerTransform() {
        this.a = false;
        this.b = false;
        this.f1786c = false;
        this.f1787d = false;
        this.e = R.id.content;
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.f1788i = 0;
        this.j = 0;
        this.k = 1375731712;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.w = Build.VERSION.SDK_INT >= 28;
        this.x = -1.0f;
        this.y = -1.0f;
    }

    public MaterialContainerTransform(@NonNull Context context, boolean z2) {
        this.a = false;
        this.b = false;
        this.f1786c = false;
        this.f1787d = false;
        this.e = R.id.content;
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.f1788i = 0;
        this.j = 0;
        this.k = 1375731712;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.w = Build.VERSION.SDK_INT >= 28;
        this.x = -1.0f;
        this.y = -1.0f;
        k(context, z2);
        this.f1787d = true;
    }

    private c b(boolean z2) {
        c cVar;
        c cVar2;
        PathMotion pathMotion = getPathMotion();
        if ((pathMotion instanceof ArcMotion) || (pathMotion instanceof MaterialArcMotion)) {
            cVar = D;
            cVar2 = E;
        } else {
            cVar = B;
            cVar2 = C;
        }
        return h(z2, cVar, cVar2);
    }

    private static RectF c(View view, @Nullable View view2, float f, float f2) {
        if (view2 == null) {
            return new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
        }
        RectF i2 = j.i(view2);
        i2.offset(f, f2);
        return i2;
    }

    private static ShapeAppearanceModel d(@NonNull View view, @NonNull RectF rectF, @Nullable ShapeAppearanceModel shapeAppearanceModel) {
        return j.b(g(view, shapeAppearanceModel), rectF);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void e(@androidx.annotation.NonNull android.transition.TransitionValues r2, @androidx.annotation.Nullable android.view.View r3, @androidx.annotation.IdRes int r4, @androidx.annotation.Nullable com.google.android.material.shape.ShapeAppearanceModel r5) {
        /*
            r0 = -1
            if (r4 == r0) goto Lc
            android.view.View r3 = r2.view
            android.view.View r3 = com.google.android.material.transition.platform.j.f(r3, r4)
        L9:
            r2.view = r3
            goto L2a
        Lc:
            if (r3 == 0) goto Lf
            goto L9
        Lf:
            android.view.View r3 = r2.view
            int r4 = com.google.android.material.R.id.mtrl_motion_snapshot_view
            java.lang.Object r3 = r3.getTag(r4)
            boolean r3 = r3 instanceof android.view.View
            if (r3 == 0) goto L2a
            android.view.View r3 = r2.view
            java.lang.Object r3 = r3.getTag(r4)
            android.view.View r3 = (android.view.View) r3
            android.view.View r0 = r2.view
            r1 = 0
            r0.setTag(r4, r1)
            goto L9
        L2a:
            android.view.View r3 = r2.view
            boolean r4 = androidx.core.view.ViewCompat.isLaidOut(r3)
            if (r4 != 0) goto L3e
            int r4 = r3.getWidth()
            if (r4 != 0) goto L3e
            int r4 = r3.getHeight()
            if (r4 == 0) goto L5f
        L3e:
            android.view.ViewParent r4 = r3.getParent()
            if (r4 != 0) goto L49
            android.graphics.RectF r4 = com.google.android.material.transition.platform.j.j(r3)
            goto L4d
        L49:
            android.graphics.RectF r4 = com.google.android.material.transition.platform.j.i(r3)
        L4d:
            java.util.Map r0 = r2.values
            java.lang.String r1 = "materialContainerTransition:bounds"
            r0.put(r1, r4)
            java.util.Map r2 = r2.values
            com.google.android.material.shape.ShapeAppearanceModel r3 = d(r3, r4, r5)
            java.lang.String r4 = "materialContainerTransition:shapeAppearance"
            r2.put(r4, r3)
        L5f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.transition.platform.MaterialContainerTransform.e(android.transition.TransitionValues, android.view.View, int, com.google.android.material.shape.ShapeAppearanceModel):void");
    }

    private static float f(float f, View view) {
        return f != -1.0f ? f : ViewCompat.getElevation(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static ShapeAppearanceModel g(@NonNull View view, @Nullable ShapeAppearanceModel shapeAppearanceModel) {
        if (shapeAppearanceModel != null) {
            return shapeAppearanceModel;
        }
        int i2 = com.google.android.material.R.id.mtrl_motion_snapshot_view;
        if (view.getTag(i2) instanceof ShapeAppearanceModel) {
            return (ShapeAppearanceModel) view.getTag(i2);
        }
        Context context = view.getContext();
        int i3 = i(context);
        return i3 != -1 ? ShapeAppearanceModel.builder(context, i3, 0).build() : view instanceof Shapeable ? ((Shapeable) view).getShapeAppearanceModel() : ShapeAppearanceModel.builder().build();
    }

    private c h(boolean z2, c cVar, c cVar2) {
        if (!z2) {
            cVar = cVar2;
        }
        return new c((ProgressThresholds) j.d(this.s, cVar.a), (ProgressThresholds) j.d(this.t, cVar.b), (ProgressThresholds) j.d(this.u, cVar.f1791c), (ProgressThresholds) j.d(this.v, cVar.f1792d), null);
    }

    @StyleRes
    private static int i(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{com.google.android.material.R.attr.transitionShapeAppearance});
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private boolean j(@NonNull RectF rectF, @NonNull RectF rectF2) {
        int i2 = this.l;
        if (i2 == 0) {
            return j.a(rectF2) > j.a(rectF);
        }
        if (i2 == 1) {
            return true;
        }
        if (i2 == 2) {
            return false;
        }
        throw new IllegalArgumentException("Invalid transition direction: " + this.l);
    }

    private void k(Context context, boolean z2) {
        j.t(this, context, com.google.android.material.R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        j.s(this, context, z2 ? com.google.android.material.R.attr.motionDurationLong1 : com.google.android.material.R.attr.motionDurationMedium2);
        if (this.f1786c) {
            return;
        }
        j.u(this, context, com.google.android.material.R.attr.motionPath);
    }

    @Override // android.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        e(transitionValues, this.p, this.g, this.r);
    }

    @Override // android.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        e(transitionValues, this.o, this.f, this.q);
    }

    @Override // android.transition.Transition
    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        String str;
        String str2;
        View e;
        View view;
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        RectF rectF = (RectF) transitionValues.values.get("materialContainerTransition:bounds");
        ShapeAppearanceModel shapeAppearanceModel = (ShapeAppearanceModel) transitionValues.values.get("materialContainerTransition:shapeAppearance");
        if (rectF == null || shapeAppearanceModel == null) {
            str = z;
            str2 = "Skipping due to null start bounds. Ensure start view is laid out and measured.";
        } else {
            RectF rectF2 = (RectF) transitionValues2.values.get("materialContainerTransition:bounds");
            ShapeAppearanceModel shapeAppearanceModel2 = (ShapeAppearanceModel) transitionValues2.values.get("materialContainerTransition:shapeAppearance");
            if (rectF2 != null && shapeAppearanceModel2 != null) {
                View view2 = transitionValues.view;
                View view3 = transitionValues2.view;
                View view4 = view3.getParent() != null ? view3 : view2;
                if (this.e == view4.getId()) {
                    e = (View) view4.getParent();
                    view = view4;
                } else {
                    e = j.e(view4, this.e);
                    view = null;
                }
                RectF i2 = j.i(e);
                float f = -i2.left;
                float f2 = -i2.top;
                RectF c2 = c(e, view, f, f2);
                rectF.offset(f, f2);
                rectF2.offset(f, f2);
                boolean j = j(rectF, rectF2);
                if (!this.f1787d) {
                    k(view4.getContext(), j);
                }
                d dVar = new d(getPathMotion(), view2, rectF, shapeAppearanceModel, f(this.x, view2), view3, rectF2, shapeAppearanceModel2, f(this.y, view3), this.h, this.f1788i, this.j, this.k, j, this.w, com.google.android.material.transition.platform.b.a(this.m, j), e.a(this.n, j, rectF, rectF2), b(j), this.a, null);
                dVar.setBounds(Math.round(c2.left), Math.round(c2.top), Math.round(c2.right), Math.round(c2.bottom));
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                ofFloat.addUpdateListener(new a(this, dVar));
                addListener(new b(e, dVar, view2, view3));
                return ofFloat;
            }
            str = z;
            str2 = "Skipping due to null end bounds. Ensure end view is laid out and measured.";
        }
        Log.w(str, str2);
        return null;
    }

    @ColorInt
    public int getContainerColor() {
        return this.h;
    }

    @IdRes
    public int getDrawingViewId() {
        return this.e;
    }

    @ColorInt
    public int getEndContainerColor() {
        return this.j;
    }

    public float getEndElevation() {
        return this.y;
    }

    @Nullable
    public ShapeAppearanceModel getEndShapeAppearanceModel() {
        return this.r;
    }

    @Nullable
    public View getEndView() {
        return this.p;
    }

    @IdRes
    public int getEndViewId() {
        return this.g;
    }

    public int getFadeMode() {
        return this.m;
    }

    @Nullable
    public ProgressThresholds getFadeProgressThresholds() {
        return this.s;
    }

    public int getFitMode() {
        return this.n;
    }

    @Nullable
    public ProgressThresholds getScaleMaskProgressThresholds() {
        return this.u;
    }

    @Nullable
    public ProgressThresholds getScaleProgressThresholds() {
        return this.t;
    }

    @ColorInt
    public int getScrimColor() {
        return this.k;
    }

    @Nullable
    public ProgressThresholds getShapeMaskProgressThresholds() {
        return this.v;
    }

    @ColorInt
    public int getStartContainerColor() {
        return this.f1788i;
    }

    public float getStartElevation() {
        return this.x;
    }

    @Nullable
    public ShapeAppearanceModel getStartShapeAppearanceModel() {
        return this.q;
    }

    @Nullable
    public View getStartView() {
        return this.o;
    }

    @IdRes
    public int getStartViewId() {
        return this.f;
    }

    public int getTransitionDirection() {
        return this.l;
    }

    @Override // android.transition.Transition
    @Nullable
    public String[] getTransitionProperties() {
        return A;
    }

    public boolean isDrawDebugEnabled() {
        return this.a;
    }

    public boolean isElevationShadowEnabled() {
        return this.w;
    }

    public boolean isHoldAtEndEnabled() {
        return this.b;
    }

    public void setAllContainerColors(@ColorInt int i2) {
        this.h = i2;
        this.f1788i = i2;
        this.j = i2;
    }

    public void setContainerColor(@ColorInt int i2) {
        this.h = i2;
    }

    public void setDrawDebugEnabled(boolean z2) {
        this.a = z2;
    }

    public void setDrawingViewId(@IdRes int i2) {
        this.e = i2;
    }

    public void setElevationShadowEnabled(boolean z2) {
        this.w = z2;
    }

    public void setEndContainerColor(@ColorInt int i2) {
        this.j = i2;
    }

    public void setEndElevation(float f) {
        this.y = f;
    }

    public void setEndShapeAppearanceModel(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.r = shapeAppearanceModel;
    }

    public void setEndView(@Nullable View view) {
        this.p = view;
    }

    public void setEndViewId(@IdRes int i2) {
        this.g = i2;
    }

    public void setFadeMode(int i2) {
        this.m = i2;
    }

    public void setFadeProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.s = progressThresholds;
    }

    public void setFitMode(int i2) {
        this.n = i2;
    }

    public void setHoldAtEndEnabled(boolean z2) {
        this.b = z2;
    }

    @Override // android.transition.Transition
    public void setPathMotion(@Nullable PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        this.f1786c = true;
    }

    public void setScaleMaskProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.u = progressThresholds;
    }

    public void setScaleProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.t = progressThresholds;
    }

    public void setScrimColor(@ColorInt int i2) {
        this.k = i2;
    }

    public void setShapeMaskProgressThresholds(@Nullable ProgressThresholds progressThresholds) {
        this.v = progressThresholds;
    }

    public void setStartContainerColor(@ColorInt int i2) {
        this.f1788i = i2;
    }

    public void setStartElevation(float f) {
        this.x = f;
    }

    public void setStartShapeAppearanceModel(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        this.q = shapeAppearanceModel;
    }

    public void setStartView(@Nullable View view) {
        this.o = view;
    }

    public void setStartViewId(@IdRes int i2) {
        this.f = i2;
    }

    public void setTransitionDirection(int i2) {
        this.l = i2;
    }
}
