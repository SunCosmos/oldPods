package com.google.android.material.shape;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.util.ObjectsCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.BitSet;

/* loaded from: classes.dex */
public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable, Shapeable {
    public static final int SHADOW_COMPAT_MODE_ALWAYS = 2;
    public static final int SHADOW_COMPAT_MODE_DEFAULT = 0;
    public static final int SHADOW_COMPAT_MODE_NEVER = 1;
    private static final String w = MaterialShapeDrawable.class.getSimpleName();
    private static final Paint x = new Paint(1);
    private c a;
    private final ShapePath.d[] b;

    /* renamed from: c, reason: collision with root package name */
    private final ShapePath.d[] f1661c;

    /* renamed from: d, reason: collision with root package name */
    private final BitSet f1662d;
    private boolean e;
    private final Matrix f;
    private final Path g;
    private final Path h;

    /* renamed from: i, reason: collision with root package name */
    private final RectF f1663i;
    private final RectF j;
    private final Region k;
    private final Region l;
    private ShapeAppearanceModel m;
    private final Paint n;
    private final Paint o;
    private final ShadowRenderer p;

    @NonNull
    private final ShapeAppearancePathProvider.PathListener q;
    private final ShapeAppearancePathProvider r;

    @Nullable
    private PorterDuffColorFilter s;

    @Nullable
    private PorterDuffColorFilter t;

    @NonNull
    private final RectF u;
    private boolean v;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface CompatibilityShadowMode {
    }

    /* loaded from: classes.dex */
    class a implements ShapeAppearancePathProvider.PathListener {
        a() {
        }

        @Override // com.google.android.material.shape.ShapeAppearancePathProvider.PathListener
        public void onCornerPathCreated(@NonNull ShapePath shapePath, Matrix matrix, int i2) {
            MaterialShapeDrawable.this.f1662d.set(i2, shapePath.c());
            MaterialShapeDrawable.this.b[i2] = shapePath.d(matrix);
        }

        @Override // com.google.android.material.shape.ShapeAppearancePathProvider.PathListener
        public void onEdgePathCreated(@NonNull ShapePath shapePath, Matrix matrix, int i2) {
            MaterialShapeDrawable.this.f1662d.set(i2 + 4, shapePath.c());
            MaterialShapeDrawable.this.f1661c[i2] = shapePath.d(matrix);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ShapeAppearanceModel.CornerSizeUnaryOperator {
        final /* synthetic */ float a;

        b(MaterialShapeDrawable materialShapeDrawable, float f) {
            this.a = f;
        }

        @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
        @NonNull
        public CornerSize apply(@NonNull CornerSize cornerSize) {
            return cornerSize instanceof RelativeCornerSize ? cornerSize : new AdjustedCornerSize(this.a, cornerSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class c extends Drawable.ConstantState {

        @NonNull
        public ShapeAppearanceModel a;

        @Nullable
        public ElevationOverlayProvider b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        public ColorFilter f1664c;

        /* renamed from: d, reason: collision with root package name */
        @Nullable
        public ColorStateList f1665d;

        @Nullable
        public ColorStateList e;

        @Nullable
        public ColorStateList f;

        @Nullable
        public ColorStateList g;

        @Nullable
        public PorterDuff.Mode h;

        /* renamed from: i, reason: collision with root package name */
        @Nullable
        public Rect f1666i;
        public float j;
        public float k;
        public float l;
        public int m;
        public float n;
        public float o;
        public float p;
        public int q;
        public int r;
        public int s;
        public int t;
        public boolean u;
        public Paint.Style v;

        public c(@NonNull c cVar) {
            this.f1665d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = PorterDuff.Mode.SRC_IN;
            this.f1666i = null;
            this.j = 1.0f;
            this.k = 1.0f;
            this.m = 255;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.a = cVar.a;
            this.b = cVar.b;
            this.l = cVar.l;
            this.f1664c = cVar.f1664c;
            this.f1665d = cVar.f1665d;
            this.e = cVar.e;
            this.h = cVar.h;
            this.g = cVar.g;
            this.m = cVar.m;
            this.j = cVar.j;
            this.s = cVar.s;
            this.q = cVar.q;
            this.u = cVar.u;
            this.k = cVar.k;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
            this.r = cVar.r;
            this.t = cVar.t;
            this.f = cVar.f;
            this.v = cVar.v;
            if (cVar.f1666i != null) {
                this.f1666i = new Rect(cVar.f1666i);
            }
        }

        public c(ShapeAppearanceModel shapeAppearanceModel, ElevationOverlayProvider elevationOverlayProvider) {
            this.f1665d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = PorterDuff.Mode.SRC_IN;
            this.f1666i = null;
            this.j = 1.0f;
            this.k = 1.0f;
            this.m = 255;
            this.n = 0.0f;
            this.o = 0.0f;
            this.p = 0.0f;
            this.q = 0;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = false;
            this.v = Paint.Style.FILL_AND_STROKE;
            this.a = shapeAppearanceModel;
            this.b = elevationOverlayProvider;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this, null);
            materialShapeDrawable.e = true;
            return materialShapeDrawable;
        }
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    public MaterialShapeDrawable(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        this(ShapeAppearanceModel.builder(context, attributeSet, i2, i3).build());
    }

    private MaterialShapeDrawable(@NonNull c cVar) {
        this.b = new ShapePath.d[4];
        this.f1661c = new ShapePath.d[4];
        this.f1662d = new BitSet(8);
        this.f = new Matrix();
        this.g = new Path();
        this.h = new Path();
        this.f1663i = new RectF();
        this.j = new RectF();
        this.k = new Region();
        this.l = new Region();
        Paint paint = new Paint(1);
        this.n = paint;
        Paint paint2 = new Paint(1);
        this.o = paint2;
        this.p = new ShadowRenderer();
        this.r = Looper.getMainLooper().getThread() == Thread.currentThread() ? ShapeAppearancePathProvider.getInstance() : new ShapeAppearancePathProvider();
        this.u = new RectF();
        this.v = true;
        this.a = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = x;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        B();
        A(getState());
        this.q = new a();
    }

    /* synthetic */ MaterialShapeDrawable(c cVar, a aVar) {
        this(cVar);
    }

    public MaterialShapeDrawable(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this(new c(shapeAppearanceModel, null));
    }

    @Deprecated
    public MaterialShapeDrawable(@NonNull ShapePathModel shapePathModel) {
        this((ShapeAppearanceModel) shapePathModel);
    }

    private boolean A(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.a.f1665d == null || color2 == (colorForState2 = this.a.f1665d.getColorForState(iArr, (color2 = this.n.getColor())))) {
            z = false;
        } else {
            this.n.setColor(colorForState2);
            z = true;
        }
        if (this.a.e == null || color == (colorForState = this.a.e.getColorForState(iArr, (color = this.o.getColor())))) {
            return z;
        }
        this.o.setColor(colorForState);
        return true;
    }

    private boolean B() {
        PorterDuffColorFilter porterDuffColorFilter = this.s;
        PorterDuffColorFilter porterDuffColorFilter2 = this.t;
        c cVar = this.a;
        this.s = j(cVar.g, cVar.h, this.n, true);
        c cVar2 = this.a;
        this.t = j(cVar2.f, cVar2.h, this.o, false);
        c cVar3 = this.a;
        if (cVar3.u) {
            this.p.setShadowColor(cVar3.g.getColorForState(getState(), 0));
        }
        return (ObjectsCompat.equals(porterDuffColorFilter, this.s) && ObjectsCompat.equals(porterDuffColorFilter2, this.t)) ? false : true;
    }

    private void C() {
        float z = getZ();
        this.a.r = (int) Math.ceil(0.75f * z);
        this.a.s = (int) Math.ceil(z * 0.25f);
        B();
        w();
    }

    @NonNull
    public static MaterialShapeDrawable createWithElevationOverlay(Context context) {
        return createWithElevationOverlay(context, 0.0f);
    }

    @NonNull
    public static MaterialShapeDrawable createWithElevationOverlay(Context context, float f) {
        int color = MaterialColors.getColor(context, R.attr.colorSurface, MaterialShapeDrawable.class.getSimpleName());
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(color));
        materialShapeDrawable.setElevation(f);
        return materialShapeDrawable;
    }

    @Nullable
    private PorterDuffColorFilter e(@NonNull Paint paint, boolean z) {
        int color;
        int k;
        if (!z || (k = k((color = paint.getColor()))) == color) {
            return null;
        }
        return new PorterDuffColorFilter(k, PorterDuff.Mode.SRC_IN);
    }

    private void f(@NonNull RectF rectF, @NonNull Path path) {
        g(rectF, path);
        if (this.a.j != 1.0f) {
            this.f.reset();
            Matrix matrix = this.f;
            float f = this.a.j;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f);
        }
        path.computeBounds(this.u, true);
    }

    private void h() {
        ShapeAppearanceModel withTransformedCornerSizes = getShapeAppearanceModel().withTransformedCornerSizes(new b(this, -s()));
        this.m = withTransformedCornerSizes;
        this.r.calculatePath(withTransformedCornerSizes, this.a.k, r(), this.h);
    }

    @NonNull
    private PorterDuffColorFilter i(@NonNull ColorStateList colorStateList, @NonNull PorterDuff.Mode mode, boolean z) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = k(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    @NonNull
    private PorterDuffColorFilter j(@Nullable ColorStateList colorStateList, @Nullable PorterDuff.Mode mode, @NonNull Paint paint, boolean z) {
        return (colorStateList == null || mode == null) ? e(paint, z) : i(colorStateList, mode, z);
    }

    private void l(@NonNull Canvas canvas) {
        if (this.f1662d.cardinality() > 0) {
            Log.w(w, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.a.s != 0) {
            canvas.drawPath(this.g, this.p.getShadowPaint());
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.b[i2].b(this.p, this.a.r, canvas);
            this.f1661c[i2].b(this.p, this.a.r, canvas);
        }
        if (this.v) {
            int shadowOffsetX = getShadowOffsetX();
            int shadowOffsetY = getShadowOffsetY();
            canvas.translate(-shadowOffsetX, -shadowOffsetY);
            canvas.drawPath(this.g, x);
            canvas.translate(shadowOffsetX, shadowOffsetY);
        }
    }

    private void m(@NonNull Canvas canvas) {
        o(canvas, this.n, this.g, this.a.a, q());
    }

    private void o(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull Path path, @NonNull ShapeAppearanceModel shapeAppearanceModel, @NonNull RectF rectF) {
        if (!shapeAppearanceModel.isRoundRect(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float cornerSize = shapeAppearanceModel.getTopRightCornerSize().getCornerSize(rectF) * this.a.k;
            canvas.drawRoundRect(rectF, cornerSize, cornerSize, paint);
        }
    }

    private void p(@NonNull Canvas canvas) {
        o(canvas, this.o, this.h, this.m, r());
    }

    @NonNull
    private RectF r() {
        this.j.set(q());
        float s = s();
        this.j.inset(s, s);
        return this.j;
    }

    private float s() {
        if (v()) {
            return this.o.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private boolean t() {
        c cVar = this.a;
        int i2 = cVar.q;
        return i2 != 1 && cVar.r > 0 && (i2 == 2 || requiresCompatShadow());
    }

    private boolean u() {
        Paint.Style style = this.a.v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    private boolean v() {
        Paint.Style style = this.a.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.o.getStrokeWidth() > 0.0f;
    }

    private void w() {
        super.invalidateSelf();
    }

    private void x(@NonNull Canvas canvas) {
        if (t()) {
            canvas.save();
            z(canvas);
            if (this.v) {
                int width = (int) (this.u.width() - getBounds().width());
                int height = (int) (this.u.height() - getBounds().height());
                if (width < 0 || height < 0) {
                    throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
                }
                Bitmap createBitmap = Bitmap.createBitmap(((int) this.u.width()) + (this.a.r * 2) + width, ((int) this.u.height()) + (this.a.r * 2) + height, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                float f = (getBounds().left - this.a.r) - width;
                float f2 = (getBounds().top - this.a.r) - height;
                canvas2.translate(-f, -f2);
                l(canvas2);
                canvas.drawBitmap(createBitmap, f, f2, (Paint) null);
                createBitmap.recycle();
            } else {
                l(canvas);
            }
            canvas.restore();
        }
    }

    private static int y(int i2, int i3) {
        return (i2 * (i3 + (i3 >>> 7))) >>> 8;
    }

    private void z(@NonNull Canvas canvas) {
        int shadowOffsetX = getShadowOffsetX();
        int shadowOffsetY = getShadowOffsetY();
        if (Build.VERSION.SDK_INT < 21 && this.v) {
            Rect clipBounds = canvas.getClipBounds();
            int i2 = this.a.r;
            clipBounds.inset(-i2, -i2);
            clipBounds.offset(shadowOffsetX, shadowOffsetY);
            canvas.clipRect(clipBounds, Region.Op.REPLACE);
        }
        canvas.translate(shadowOffsetX, shadowOffsetY);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        this.n.setColorFilter(this.s);
        int alpha = this.n.getAlpha();
        this.n.setAlpha(y(alpha, this.a.m));
        this.o.setColorFilter(this.t);
        this.o.setStrokeWidth(this.a.l);
        int alpha2 = this.o.getAlpha();
        this.o.setAlpha(y(alpha2, this.a.m));
        if (this.e) {
            h();
            f(q(), this.g);
            this.e = false;
        }
        x(canvas);
        if (u()) {
            m(canvas);
        }
        if (v()) {
            p(canvas);
        }
        this.n.setAlpha(alpha);
        this.o.setAlpha(alpha2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void g(@NonNull RectF rectF, @NonNull Path path) {
        ShapeAppearancePathProvider shapeAppearancePathProvider = this.r;
        c cVar = this.a;
        shapeAppearancePathProvider.calculatePath(cVar.a, cVar.k, rectF, this.q, path);
    }

    public float getBottomLeftCornerResolvedSize() {
        return this.a.a.getBottomLeftCornerSize().getCornerSize(q());
    }

    public float getBottomRightCornerResolvedSize() {
        return this.a.a.getBottomRightCornerSize().getCornerSize(q());
    }

    @Override // android.graphics.drawable.Drawable
    @Nullable
    public Drawable.ConstantState getConstantState() {
        return this.a;
    }

    public float getElevation() {
        return this.a.o;
    }

    @Nullable
    public ColorStateList getFillColor() {
        return this.a.f1665d;
    }

    public float getInterpolation() {
        return this.a.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(@NonNull Outline outline) {
        if (this.a.q == 2) {
            return;
        }
        if (isRoundRect()) {
            outline.setRoundRect(getBounds(), getTopLeftCornerResolvedSize() * this.a.k);
            return;
        }
        f(q(), this.g);
        if (this.g.isConvex() || Build.VERSION.SDK_INT >= 29) {
            try {
                outline.setConvexPath(this.g);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(@NonNull Rect rect) {
        Rect rect2 = this.a.f1666i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    public Paint.Style getPaintStyle() {
        return this.a.v;
    }

    public float getParentAbsoluteElevation() {
        return this.a.n;
    }

    @Deprecated
    public void getPathForSize(int i2, int i3, @NonNull Path path) {
        g(new RectF(0.0f, 0.0f, i2, i3), path);
    }

    public float getScale() {
        return this.a.j;
    }

    public int getShadowCompatRotation() {
        return this.a.t;
    }

    public int getShadowCompatibilityMode() {
        return this.a.q;
    }

    @Deprecated
    public int getShadowElevation() {
        return (int) getElevation();
    }

    public int getShadowOffsetX() {
        double d2 = this.a.s;
        double sin = Math.sin(Math.toRadians(r0.t));
        Double.isNaN(d2);
        return (int) (d2 * sin);
    }

    public int getShadowOffsetY() {
        double d2 = this.a.s;
        double cos = Math.cos(Math.toRadians(r0.t));
        Double.isNaN(d2);
        return (int) (d2 * cos);
    }

    public int getShadowRadius() {
        return this.a.r;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getShadowVerticalOffset() {
        return this.a.s;
    }

    @Override // com.google.android.material.shape.Shapeable
    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.a.a;
    }

    @Nullable
    @Deprecated
    public ShapePathModel getShapedViewModel() {
        ShapeAppearanceModel shapeAppearanceModel = getShapeAppearanceModel();
        if (shapeAppearanceModel instanceof ShapePathModel) {
            return (ShapePathModel) shapeAppearanceModel;
        }
        return null;
    }

    @Nullable
    public ColorStateList getStrokeColor() {
        return this.a.e;
    }

    @Nullable
    public ColorStateList getStrokeTintList() {
        return this.a.f;
    }

    public float getStrokeWidth() {
        return this.a.l;
    }

    @Nullable
    public ColorStateList getTintList() {
        return this.a.g;
    }

    public float getTopLeftCornerResolvedSize() {
        return this.a.a.getTopLeftCornerSize().getCornerSize(q());
    }

    public float getTopRightCornerResolvedSize() {
        return this.a.a.getTopRightCornerSize().getCornerSize(q());
    }

    public float getTranslationZ() {
        return this.a.p;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.k.set(getBounds());
        f(q(), this.g);
        this.l.setPath(this.g, this.k);
        this.k.op(this.l, Region.Op.DIFFERENCE);
        return this.k;
    }

    public float getZ() {
        return getElevation() + getTranslationZ();
    }

    public void initializeElevationOverlay(Context context) {
        this.a.b = new ElevationOverlayProvider(context);
        C();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.e = true;
        super.invalidateSelf();
    }

    public boolean isElevationOverlayEnabled() {
        ElevationOverlayProvider elevationOverlayProvider = this.a.b;
        return elevationOverlayProvider != null && elevationOverlayProvider.isThemeElevationOverlayEnabled();
    }

    public boolean isElevationOverlayInitialized() {
        return this.a.b != null;
    }

    public boolean isPointInTransparentRegion(int i2, int i3) {
        return getTransparentRegion().contains(i2, i3);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isRoundRect() {
        return this.a.a.isRoundRect(q());
    }

    @Deprecated
    public boolean isShadowEnabled() {
        int i2 = this.a.q;
        return i2 == 0 || i2 == 2;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.a.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.a.f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.a.e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.a.f1665d) != null && colorStateList4.isStateful())));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @ColorInt
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int k(@ColorInt int i2) {
        float z = getZ() + getParentAbsoluteElevation();
        ElevationOverlayProvider elevationOverlayProvider = this.a.b;
        return elevationOverlayProvider != null ? elevationOverlayProvider.compositeOverlayIfNeeded(i2, z) : i2;
    }

    @Override // android.graphics.drawable.Drawable
    @NonNull
    public Drawable mutate() {
        this.a = new c(this.a);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void n(@NonNull Canvas canvas, @NonNull Paint paint, @NonNull Path path, @NonNull RectF rectF) {
        o(canvas, paint, path, this.a.a, rectF);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.e = true;
        super.onBoundsChange(rect);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        boolean z = A(iArr) || B();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public RectF q() {
        this.f1663i.set(getBounds());
        return this.f1663i;
    }

    public boolean requiresCompatShadow() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 < 21 || !(isRoundRect() || this.g.isConvex() || i2 >= 29);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = 0, to = 255) int i2) {
        c cVar = this.a;
        if (cVar.m != i2) {
            cVar.m = i2;
            w();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.a.f1664c = colorFilter;
        w();
    }

    public void setCornerSize(float f) {
        setShapeAppearanceModel(this.a.a.withCornerSize(f));
    }

    public void setCornerSize(@NonNull CornerSize cornerSize) {
        setShapeAppearanceModel(this.a.a.withCornerSize(cornerSize));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setEdgeIntersectionCheckEnable(boolean z) {
        this.r.k(z);
    }

    public void setElevation(float f) {
        c cVar = this.a;
        if (cVar.o != f) {
            cVar.o = f;
            C();
        }
    }

    public void setFillColor(@Nullable ColorStateList colorStateList) {
        c cVar = this.a;
        if (cVar.f1665d != colorStateList) {
            cVar.f1665d = colorStateList;
            onStateChange(getState());
        }
    }

    public void setInterpolation(float f) {
        c cVar = this.a;
        if (cVar.k != f) {
            cVar.k = f;
            this.e = true;
            invalidateSelf();
        }
    }

    public void setPadding(int i2, int i3, int i4, int i5) {
        c cVar = this.a;
        if (cVar.f1666i == null) {
            cVar.f1666i = new Rect();
        }
        this.a.f1666i.set(i2, i3, i4, i5);
        invalidateSelf();
    }

    public void setPaintStyle(Paint.Style style) {
        this.a.v = style;
        w();
    }

    public void setParentAbsoluteElevation(float f) {
        c cVar = this.a;
        if (cVar.n != f) {
            cVar.n = f;
            C();
        }
    }

    public void setScale(float f) {
        c cVar = this.a;
        if (cVar.j != f) {
            cVar.j = f;
            invalidateSelf();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setShadowBitmapDrawingEnable(boolean z) {
        this.v = z;
    }

    public void setShadowColor(int i2) {
        this.p.setShadowColor(i2);
        this.a.u = false;
        w();
    }

    public void setShadowCompatRotation(int i2) {
        c cVar = this.a;
        if (cVar.t != i2) {
            cVar.t = i2;
            w();
        }
    }

    public void setShadowCompatibilityMode(int i2) {
        c cVar = this.a;
        if (cVar.q != i2) {
            cVar.q = i2;
            w();
        }
    }

    @Deprecated
    public void setShadowElevation(int i2) {
        setElevation(i2);
    }

    @Deprecated
    public void setShadowEnabled(boolean z) {
        setShadowCompatibilityMode(!z ? 1 : 0);
    }

    @Deprecated
    public void setShadowRadius(int i2) {
        this.a.r = i2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setShadowVerticalOffset(int i2) {
        c cVar = this.a;
        if (cVar.s != i2) {
            cVar.s = i2;
            w();
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        this.a.a = shapeAppearanceModel;
        invalidateSelf();
    }

    @Deprecated
    public void setShapedViewModel(@NonNull ShapePathModel shapePathModel) {
        setShapeAppearanceModel(shapePathModel);
    }

    public void setStroke(float f, @ColorInt int i2) {
        setStrokeWidth(f);
        setStrokeColor(ColorStateList.valueOf(i2));
    }

    public void setStroke(float f, @Nullable ColorStateList colorStateList) {
        setStrokeWidth(f);
        setStrokeColor(colorStateList);
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        c cVar = this.a;
        if (cVar.e != colorStateList) {
            cVar.e = colorStateList;
            onStateChange(getState());
        }
    }

    public void setStrokeTint(@ColorInt int i2) {
        setStrokeTint(ColorStateList.valueOf(i2));
    }

    public void setStrokeTint(ColorStateList colorStateList) {
        this.a.f = colorStateList;
        B();
        w();
    }

    public void setStrokeWidth(float f) {
        this.a.l = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(@ColorInt int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(@Nullable ColorStateList colorStateList) {
        this.a.g = colorStateList;
        B();
        w();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(@Nullable PorterDuff.Mode mode) {
        c cVar = this.a;
        if (cVar.h != mode) {
            cVar.h = mode;
            B();
            w();
        }
    }

    public void setTranslationZ(float f) {
        c cVar = this.a;
        if (cVar.p != f) {
            cVar.p = f;
            C();
        }
    }

    public void setUseTintColorForShadow(boolean z) {
        c cVar = this.a;
        if (cVar.u != z) {
            cVar.u = z;
            invalidateSelf();
        }
    }

    public void setZ(float f) {
        setTranslationZ(f - getElevation());
    }
}
