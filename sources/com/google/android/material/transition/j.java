package com.google.android.material.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;
import androidx.core.view.animation.PathInterpolatorCompat;
import androidx.transition.PathMotion;
import androidx.transition.PatternPathMotion;
import androidx.transition.Transition;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;

/* loaded from: classes.dex */
class j {
    private static final RectF a = new RectF();

    /* loaded from: classes.dex */
    static class a implements ShapeAppearanceModel.CornerSizeUnaryOperator {
        final /* synthetic */ RectF a;

        a(RectF rectF) {
            this.a = rectF;
        }

        @Override // com.google.android.material.shape.ShapeAppearanceModel.CornerSizeUnaryOperator
        @NonNull
        public CornerSize apply(@NonNull CornerSize cornerSize) {
            return cornerSize instanceof RelativeCornerSize ? cornerSize : new RelativeCornerSize(cornerSize.getCornerSize(this.a) / this.a.height());
        }
    }

    /* loaded from: classes.dex */
    static class b implements d {
        final /* synthetic */ RectF a;
        final /* synthetic */ RectF b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f1780c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ float f1781d;
        final /* synthetic */ float e;

        b(RectF rectF, RectF rectF2, float f, float f2, float f3) {
            this.a = rectF;
            this.b = rectF2;
            this.f1780c = f;
            this.f1781d = f2;
            this.e = f3;
        }

        @Override // com.google.android.material.transition.j.d
        @NonNull
        public CornerSize a(@NonNull CornerSize cornerSize, @NonNull CornerSize cornerSize2) {
            return new AbsoluteCornerSize(j.n(cornerSize.getCornerSize(this.a), cornerSize2.getCornerSize(this.b), this.f1780c, this.f1781d, this.e));
        }
    }

    /* loaded from: classes.dex */
    interface c {
        void a(Canvas canvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface d {
        @NonNull
        CornerSize a(@NonNull CornerSize cornerSize, @NonNull CornerSize cornerSize2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float a(@NonNull RectF rectF) {
        return rectF.width() * rectF.height();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ShapeAppearanceModel b(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        return shapeAppearanceModel.withTransformedCornerSizes(new a(rectF));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Shader c(@ColorInt int i2) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, 0.0f, i2, i2, Shader.TileMode.CLAMP);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static <T> T d(@Nullable T t, @NonNull T t2) {
        return t != null ? t : t2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View e(View view, @IdRes int i2) {
        String resourceName = view.getResources().getResourceName(i2);
        while (view != null) {
            if (view.getId() != i2) {
                Object parent = view.getParent();
                if (!(parent instanceof View)) {
                    break;
                }
                view = (View) parent;
            } else {
                return view;
            }
        }
        throw new IllegalArgumentException(resourceName + " is not a valid ancestor");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View f(View view, @IdRes int i2) {
        View findViewById = view.findViewById(i2);
        return findViewById != null ? findViewById : e(view, i2);
    }

    private static float g(String[] strArr, int i2) {
        float parseFloat = Float.parseFloat(strArr[i2]);
        if (parseFloat >= 0.0f && parseFloat <= 1.0f) {
            return parseFloat;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
    }

    private static String h(String str, String str2) {
        return str.substring(str2.length() + 1, str.length() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RectF i(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new RectF(iArr[0], iArr[1], view.getWidth() + r1, view.getHeight() + r0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RectF j(View view) {
        return new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    private static boolean k(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("(");
        return str.startsWith(sb.toString()) && str.endsWith(")");
    }

    private static boolean l(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        return (shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getTopRightCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(rectF) == 0.0f && shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(rectF) == 0.0f) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float m(float f, float f2, float f3) {
        return f + (f3 * (f2 - f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float n(float f, float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @FloatRange(from = 0.0d, to = 1.0d) float f4, @FloatRange(from = 0.0d, to = 1.0d) float f5) {
        return o(f, f2, f3, f4, f5, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float o(float f, float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @FloatRange(from = 0.0d, to = 1.0d) float f4, @FloatRange(from = 0.0d) float f5, boolean z) {
        return (!z || (f5 >= 0.0f && f5 <= 1.0f)) ? f5 < f3 ? f : f5 > f4 ? f2 : m(f, f2, (f5 - f3) / (f4 - f3)) : m(f, f2, f5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int p(int i2, int i3, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3) {
        return f3 < f ? i2 : f3 > f2 ? i3 : (int) m(i2, i3, (f3 - f) / (f2 - f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ShapeAppearanceModel q(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, RectF rectF2, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3) {
        return f3 < f ? shapeAppearanceModel : f3 > f2 ? shapeAppearanceModel2 : y(shapeAppearanceModel, shapeAppearanceModel2, rectF, new b(rectF, rectF2, f, f2, f3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean r(Transition transition, Context context, @AttrRes int i2) {
        int resolveInteger;
        if (i2 == 0 || transition.getDuration() != -1 || (resolveInteger = MaterialAttributes.resolveInteger(context, i2, -1)) == -1) {
            return false;
        }
        transition.setDuration(resolveInteger);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean s(Transition transition, Context context, @AttrRes int i2, TimeInterpolator timeInterpolator) {
        if (i2 == 0 || transition.getInterpolator() != null) {
            return false;
        }
        transition.setInterpolator(u(context, i2, timeInterpolator));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean t(Transition transition, Context context, @AttrRes int i2) {
        PathMotion v;
        if (i2 == 0 || (v = v(context, i2)) == null) {
            return false;
        }
        transition.setPathMotion(v);
        return true;
    }

    static TimeInterpolator u(Context context, @AttrRes int i2, @NonNull TimeInterpolator timeInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return timeInterpolator;
        }
        if (typedValue.type != 3) {
            throw new IllegalArgumentException("Motion easing theme attribute must be a string");
        }
        String valueOf = String.valueOf(typedValue.string);
        if (!k(valueOf, "cubic-bezier")) {
            if (k(valueOf, "path")) {
                return PathInterpolatorCompat.create(PathParser.createPathFromPathData(h(valueOf, "path")));
            }
            throw new IllegalArgumentException("Invalid motion easing type: " + valueOf);
        }
        String[] split = h(valueOf, "cubic-bezier").split(",");
        if (split.length == 4) {
            return PathInterpolatorCompat.create(g(split, 0), g(split, 1), g(split, 2), g(split, 3));
        }
        throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + split.length);
    }

    @Nullable
    static PathMotion v(Context context, @AttrRes int i2) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.type;
        if (i3 != 16) {
            if (i3 == 3) {
                return new PatternPathMotion(PathParser.createPathFromPathData(String.valueOf(typedValue.string)));
            }
            throw new IllegalArgumentException("Motion path theme attribute must either be an enum value or path data string");
        }
        int i4 = typedValue.data;
        if (i4 == 0) {
            return null;
        }
        if (i4 == 1) {
            return new MaterialArcMotion();
        }
        throw new IllegalArgumentException("Invalid motion path type: " + i4);
    }

    private static int w(Canvas canvas, Rect rect, int i2) {
        RectF rectF = a;
        rectF.set(rect);
        return Build.VERSION.SDK_INT >= 21 ? canvas.saveLayerAlpha(rectF, i2) : canvas.saveLayerAlpha(rectF.left, rectF.top, rectF.right, rectF.bottom, i2, 31);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void x(Canvas canvas, Rect rect, float f, float f2, float f3, int i2, c cVar) {
        if (i2 <= 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(f, f2);
        canvas.scale(f3, f3);
        if (i2 < 255) {
            w(canvas, rect, i2);
        }
        cVar.a(canvas);
        canvas.restoreToCount(save);
    }

    static ShapeAppearanceModel y(ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, d dVar) {
        return (l(shapeAppearanceModel, rectF) ? shapeAppearanceModel : shapeAppearanceModel2).toBuilder().setTopLeftCornerSize(dVar.a(shapeAppearanceModel.getTopLeftCornerSize(), shapeAppearanceModel2.getTopLeftCornerSize())).setTopRightCornerSize(dVar.a(shapeAppearanceModel.getTopRightCornerSize(), shapeAppearanceModel2.getTopRightCornerSize())).setBottomLeftCornerSize(dVar.a(shapeAppearanceModel.getBottomLeftCornerSize(), shapeAppearanceModel2.getBottomLeftCornerSize())).setBottomRightCornerSize(dVar.a(shapeAppearanceModel.getBottomRightCornerSize(), shapeAppearanceModel2.getBottomRightCornerSize())).build();
    }
}
