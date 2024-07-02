package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.a;
import com.google.android.material.resources.CancelableFontCallback;
import com.google.android.material.resources.TextAppearance;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class CollapsingTextHelper {
    private static final boolean j0;

    @NonNull
    private static final Paint k0;
    private CancelableFontCallback A;

    @Nullable
    private CharSequence B;

    @Nullable
    private CharSequence C;
    private boolean D;
    private boolean F;

    @Nullable
    private Bitmap G;
    private Paint H;
    private float I;
    private float J;
    private int[] K;
    private boolean L;

    @NonNull
    private final TextPaint M;

    @NonNull
    private final TextPaint N;
    private TimeInterpolator O;
    private TimeInterpolator P;
    private float Q;
    private float R;
    private float S;
    private ColorStateList T;
    private float U;
    private float V;
    private float W;
    private ColorStateList X;
    private float Y;
    private float Z;
    private final View a;
    private StaticLayout a0;
    private boolean b;
    private float b0;

    /* renamed from: c, reason: collision with root package name */
    private float f1593c;
    private float c0;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1594d;
    private float d0;
    private float e;
    private CharSequence e0;
    private float f;
    private int g;

    @NonNull
    private final Rect h;

    /* renamed from: i, reason: collision with root package name */
    @NonNull
    private final Rect f1595i;

    @NonNull
    private final RectF j;
    private ColorStateList o;
    private ColorStateList p;
    private float q;
    private float r;
    private float s;
    private float t;
    private float u;
    private float v;
    private Typeface w;
    private Typeface x;
    private Typeface y;
    private CancelableFontCallback z;
    private int k = 16;
    private int l = 16;
    private float m = 15.0f;
    private float n = 15.0f;
    private boolean E = true;
    private int f0 = 1;
    private float g0 = 0.0f;
    private float h0 = 1.0f;
    private int i0 = com.google.android.material.internal.a.n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements CancelableFontCallback.ApplyFont {
        a() {
        }

        @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
        public void apply(Typeface typeface) {
            CollapsingTextHelper.this.setCollapsedTypeface(typeface);
        }
    }

    /* loaded from: classes.dex */
    class b implements CancelableFontCallback.ApplyFont {
        b() {
        }

        @Override // com.google.android.material.resources.CancelableFontCallback.ApplyFont
        public void apply(Typeface typeface) {
            CollapsingTextHelper.this.setExpandedTypeface(typeface);
        }
    }

    static {
        j0 = Build.VERSION.SDK_INT < 18;
        Paint paint = null;
        k0 = null;
        if (0 != 0) {
            paint.setAntiAlias(true);
            paint.setColor(-65281);
        }
    }

    public CollapsingTextHelper(View view) {
        this.a = view;
        TextPaint textPaint = new TextPaint(129);
        this.M = textPaint;
        this.N = new TextPaint(textPaint);
        this.f1595i = new Rect();
        this.h = new Rect();
        this.j = new RectF();
        this.f = e();
    }

    private void A(float f) {
        this.b0 = f;
        ViewCompat.postInvalidateOnAnimation(this.a);
    }

    private boolean B(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.A;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        if (this.w == typeface) {
            return false;
        }
        this.w = typeface;
        return true;
    }

    private void C(float f) {
        this.c0 = f;
        ViewCompat.postInvalidateOnAnimation(this.a);
    }

    private boolean D(Typeface typeface) {
        CancelableFontCallback cancelableFontCallback = this.z;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        if (this.x == typeface) {
            return false;
        }
        this.x = typeface;
        return true;
    }

    private void E(float f) {
        h(f);
        boolean z = j0 && this.I != 1.0f;
        this.F = z;
        if (z) {
            m();
        }
        ViewCompat.postInvalidateOnAnimation(this.a);
    }

    private boolean F() {
        return this.f0 > 1 && (!this.D || this.f1594d) && !this.F;
    }

    private static int a(int i2, int i3, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((Color.alpha(i2) * f2) + (Color.alpha(i3) * f)), (int) ((Color.red(i2) * f2) + (Color.red(i3) * f)), (int) ((Color.green(i2) * f2) + (Color.green(i3) * f)), (int) ((Color.blue(i2) * f2) + (Color.blue(i3) * f)));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(boolean r13) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.internal.CollapsingTextHelper.b(boolean):void");
    }

    private void c() {
        g(this.f1593c);
    }

    private float d(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        float f2 = this.f;
        return f <= f2 ? AnimationUtils.lerp(1.0f, 0.0f, this.e, f2, f) : AnimationUtils.lerp(0.0f, 1.0f, f2, 1.0f, f);
    }

    private float e() {
        float f = this.e;
        return f + ((1.0f - f) * 0.5f);
    }

    private boolean f(@NonNull CharSequence charSequence) {
        boolean v = v();
        return this.E ? w(charSequence, v) : v;
    }

    private void g(float f) {
        float f2;
        t(f);
        if (!this.f1594d) {
            this.u = x(this.s, this.t, f, this.O);
            this.v = x(this.q, this.r, f, this.O);
            E(x(this.m, this.n, f, this.P));
            f2 = f;
        } else if (f < this.f) {
            this.u = this.s;
            this.v = this.q;
            E(this.m);
            f2 = 0.0f;
        } else {
            this.u = this.t;
            this.v = this.r - Math.max(0, this.g);
            E(this.n);
            f2 = 1.0f;
        }
        TimeInterpolator timeInterpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        A(1.0f - x(0.0f, 1.0f, 1.0f - f, timeInterpolator));
        C(x(1.0f, 0.0f, f, timeInterpolator));
        if (this.p != this.o) {
            this.M.setColor(a(q(), getCurrentCollapsedTextColor(), f2));
        } else {
            this.M.setColor(getCurrentCollapsedTextColor());
        }
        if (Build.VERSION.SDK_INT >= 21) {
            float f3 = this.Y;
            float f4 = this.Z;
            if (f3 != f4) {
                this.M.setLetterSpacing(x(f4, f3, f, timeInterpolator));
            } else {
                this.M.setLetterSpacing(f3);
            }
        }
        this.M.setShadowLayer(x(this.U, this.Q, f, null), x(this.V, this.R, f, null), x(this.W, this.S, f, null), a(p(this.X), p(this.T), f));
        if (this.f1594d) {
            this.M.setAlpha((int) (d(f) * 255.0f));
        }
        ViewCompat.postInvalidateOnAnimation(this.a);
    }

    private void h(float f) {
        i(f, false);
    }

    private void i(float f, boolean z) {
        boolean z2;
        float f2;
        boolean z3;
        if (this.B == null) {
            return;
        }
        float width = this.f1595i.width();
        float width2 = this.h.width();
        if (u(f, this.n)) {
            f2 = this.n;
            this.I = 1.0f;
            Typeface typeface = this.y;
            Typeface typeface2 = this.w;
            if (typeface != typeface2) {
                this.y = typeface2;
                z3 = true;
            } else {
                z3 = false;
            }
        } else {
            float f3 = this.m;
            Typeface typeface3 = this.y;
            Typeface typeface4 = this.x;
            if (typeface3 != typeface4) {
                this.y = typeface4;
                z2 = true;
            } else {
                z2 = false;
            }
            if (u(f, f3)) {
                this.I = 1.0f;
            } else {
                this.I = f / this.m;
            }
            float f4 = this.n / this.m;
            width = (!z && width2 * f4 > width) ? Math.min(width / f4, width2) : width2;
            f2 = f3;
            z3 = z2;
        }
        if (width > 0.0f) {
            z3 = this.J != f2 || this.L || z3;
            this.J = f2;
            this.L = false;
        }
        if (this.C == null || z3) {
            this.M.setTextSize(this.J);
            this.M.setTypeface(this.y);
            this.M.setLinearText(this.I != 1.0f);
            this.D = f(this.B);
            StaticLayout k = k(F() ? this.f0 : 1, width, this.D);
            this.a0 = k;
            this.C = k.getText();
        }
    }

    private void j() {
        Bitmap bitmap = this.G;
        if (bitmap != null) {
            bitmap.recycle();
            this.G = null;
        }
    }

    private StaticLayout k(int i2, float f, boolean z) {
        StaticLayout staticLayout;
        try {
            com.google.android.material.internal.a c2 = com.google.android.material.internal.a.c(this.B, this.M, (int) f);
            c2.e(TextUtils.TruncateAt.END);
            c2.h(z);
            c2.d(Layout.Alignment.ALIGN_NORMAL);
            c2.g(false);
            c2.j(i2);
            c2.i(this.g0, this.h0);
            c2.f(this.i0);
            staticLayout = c2.a();
        } catch (a.C0056a e) {
            Log.e("CollapsingTextHelper", e.getCause().getMessage(), e);
            staticLayout = null;
        }
        return (StaticLayout) Preconditions.checkNotNull(staticLayout);
    }

    private void l(@NonNull Canvas canvas, float f, float f2) {
        int alpha = this.M.getAlpha();
        canvas.translate(f, f2);
        float f3 = alpha;
        this.M.setAlpha((int) (this.c0 * f3));
        this.a0.draw(canvas);
        this.M.setAlpha((int) (this.b0 * f3));
        int lineBaseline = this.a0.getLineBaseline(0);
        CharSequence charSequence = this.e0;
        float f4 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f4, this.M);
        if (this.f1594d) {
            return;
        }
        String trim = this.e0.toString().trim();
        if (trim.endsWith("…")) {
            trim = trim.substring(0, trim.length() - 1);
        }
        String str = trim;
        this.M.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.a0.getLineEnd(0), str.length()), 0.0f, f4, (Paint) this.M);
    }

    private void m() {
        if (this.G != null || this.h.isEmpty() || TextUtils.isEmpty(this.C)) {
            return;
        }
        g(0.0f);
        int width = this.a0.getWidth();
        int height = this.a0.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.G = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.a0.draw(new Canvas(this.G));
        if (this.H == null) {
            this.H = new Paint(3);
        }
    }

    private float n(int i2, int i3) {
        return (i3 == 17 || (i3 & 7) == 1) ? (i2 / 2.0f) - (calculateCollapsedTextWidth() / 2.0f) : ((i3 & 8388613) == 8388613 || (i3 & 5) == 5) ? this.D ? this.f1595i.left : this.f1595i.right - calculateCollapsedTextWidth() : this.D ? this.f1595i.right - calculateCollapsedTextWidth() : this.f1595i.left;
    }

    private float o(@NonNull RectF rectF, int i2, int i3) {
        return (i3 == 17 || (i3 & 7) == 1) ? (i2 / 2.0f) + (calculateCollapsedTextWidth() / 2.0f) : ((i3 & 8388613) == 8388613 || (i3 & 5) == 5) ? this.D ? rectF.left + calculateCollapsedTextWidth() : this.f1595i.right : this.D ? this.f1595i.right : rectF.left + calculateCollapsedTextWidth();
    }

    @ColorInt
    private int p(@Nullable ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.K;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    @ColorInt
    private int q() {
        return p(this.o);
    }

    private void r(@NonNull TextPaint textPaint) {
        textPaint.setTextSize(this.n);
        textPaint.setTypeface(this.w);
        if (Build.VERSION.SDK_INT >= 21) {
            textPaint.setLetterSpacing(this.Y);
        }
    }

    private void s(@NonNull TextPaint textPaint) {
        textPaint.setTextSize(this.m);
        textPaint.setTypeface(this.x);
        if (Build.VERSION.SDK_INT >= 21) {
            textPaint.setLetterSpacing(this.Z);
        }
    }

    private void t(float f) {
        if (this.f1594d) {
            this.j.set(f < this.f ? this.h : this.f1595i);
            return;
        }
        this.j.left = x(this.h.left, this.f1595i.left, f, this.O);
        this.j.top = x(this.q, this.r, f, this.O);
        this.j.right = x(this.h.right, this.f1595i.right, f, this.O);
        this.j.bottom = x(this.h.bottom, this.f1595i.bottom, f, this.O);
    }

    private static boolean u(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    private boolean v() {
        return ViewCompat.getLayoutDirection(this.a) == 1;
    }

    private boolean w(@NonNull CharSequence charSequence, boolean z) {
        return (z ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR).isRtl(charSequence, 0, charSequence.length());
    }

    private static float x(float f, float f2, float f3, @Nullable TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f3 = timeInterpolator.getInterpolation(f3);
        }
        return AnimationUtils.lerp(f, f2, f3);
    }

    private static boolean z(@NonNull Rect rect, int i2, int i3, int i4, int i5) {
        return rect.left == i2 && rect.top == i3 && rect.right == i4 && rect.bottom == i5;
    }

    public float calculateCollapsedTextWidth() {
        if (this.B == null) {
            return 0.0f;
        }
        r(this.N);
        TextPaint textPaint = this.N;
        CharSequence charSequence = this.B;
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void draw(@NonNull Canvas canvas) {
        int save = canvas.save();
        if (this.C == null || !this.b) {
            return;
        }
        float lineStart = (this.u + (this.f0 > 1 ? this.a0.getLineStart(0) : this.a0.getLineLeft(0))) - (this.d0 * 2.0f);
        this.M.setTextSize(this.J);
        float f = this.u;
        float f2 = this.v;
        boolean z = this.F && this.G != null;
        float f3 = this.I;
        if (f3 != 1.0f && !this.f1594d) {
            canvas.scale(f3, f3, f, f2);
        }
        if (z) {
            canvas.drawBitmap(this.G, f, f2, this.H);
            canvas.restoreToCount(save);
            return;
        }
        if (!F() || (this.f1594d && this.f1593c <= this.f)) {
            canvas.translate(f, f2);
            this.a0.draw(canvas);
        } else {
            l(canvas, lineStart, f2);
        }
        canvas.restoreToCount(save);
    }

    public void getCollapsedTextActualBounds(@NonNull RectF rectF, int i2, int i3) {
        this.D = f(this.B);
        rectF.left = n(i2, i3);
        rectF.top = this.f1595i.top;
        rectF.right = o(rectF, i2, i3);
        rectF.bottom = this.f1595i.top + getCollapsedTextHeight();
    }

    public ColorStateList getCollapsedTextColor() {
        return this.p;
    }

    public int getCollapsedTextGravity() {
        return this.l;
    }

    public float getCollapsedTextHeight() {
        r(this.N);
        return -this.N.ascent();
    }

    public float getCollapsedTextSize() {
        return this.n;
    }

    public Typeface getCollapsedTypeface() {
        Typeface typeface = this.w;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    @ColorInt
    public int getCurrentCollapsedTextColor() {
        return p(this.p);
    }

    public ColorStateList getExpandedTextColor() {
        return this.o;
    }

    public float getExpandedTextFullHeight() {
        s(this.N);
        return (-this.N.ascent()) + this.N.descent();
    }

    public int getExpandedTextGravity() {
        return this.k;
    }

    public float getExpandedTextHeight() {
        s(this.N);
        return -this.N.ascent();
    }

    public float getExpandedTextSize() {
        return this.m;
    }

    public Typeface getExpandedTypeface() {
        Typeface typeface = this.x;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public float getExpansionFraction() {
        return this.f1593c;
    }

    public float getFadeModeThresholdFraction() {
        return this.f;
    }

    @RequiresApi(23)
    public int getHyphenationFrequency() {
        return this.i0;
    }

    public int getLineCount() {
        StaticLayout staticLayout = this.a0;
        if (staticLayout != null) {
            return staticLayout.getLineCount();
        }
        return 0;
    }

    @RequiresApi(23)
    public float getLineSpacingAdd() {
        return this.a0.getSpacingAdd();
    }

    @RequiresApi(23)
    public float getLineSpacingMultiplier() {
        return this.a0.getSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.f0;
    }

    @Nullable
    public CharSequence getText() {
        return this.B;
    }

    public boolean isRtlTextDirectionHeuristicsEnabled() {
        return this.E;
    }

    public final boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.p;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.o) != null && colorStateList.isStateful());
    }

    public void recalculate() {
        recalculate(false);
    }

    public void recalculate(boolean z) {
        if ((this.a.getHeight() <= 0 || this.a.getWidth() <= 0) && !z) {
            return;
        }
        b(z);
        c();
    }

    public void setCollapsedBounds(int i2, int i3, int i4, int i5) {
        if (z(this.f1595i, i2, i3, i4, i5)) {
            return;
        }
        this.f1595i.set(i2, i3, i4, i5);
        this.L = true;
        y();
    }

    public void setCollapsedBounds(@NonNull Rect rect) {
        setCollapsedBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setCollapsedTextAppearance(int i2) {
        TextAppearance textAppearance = new TextAppearance(this.a.getContext(), i2);
        ColorStateList colorStateList = textAppearance.textColor;
        if (colorStateList != null) {
            this.p = colorStateList;
        }
        float f = textAppearance.textSize;
        if (f != 0.0f) {
            this.n = f;
        }
        ColorStateList colorStateList2 = textAppearance.shadowColor;
        if (colorStateList2 != null) {
            this.T = colorStateList2;
        }
        this.R = textAppearance.shadowDx;
        this.S = textAppearance.shadowDy;
        this.Q = textAppearance.shadowRadius;
        this.Y = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = this.A;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        this.A = new CancelableFontCallback(new a(), textAppearance.getFallbackFont());
        textAppearance.getFontAsync(this.a.getContext(), this.A);
        recalculate();
    }

    public void setCollapsedTextColor(ColorStateList colorStateList) {
        if (this.p != colorStateList) {
            this.p = colorStateList;
            recalculate();
        }
    }

    public void setCollapsedTextGravity(int i2) {
        if (this.l != i2) {
            this.l = i2;
            recalculate();
        }
    }

    public void setCollapsedTextSize(float f) {
        if (this.n != f) {
            this.n = f;
            recalculate();
        }
    }

    public void setCollapsedTypeface(Typeface typeface) {
        if (B(typeface)) {
            recalculate();
        }
    }

    public void setCurrentOffsetY(int i2) {
        this.g = i2;
    }

    public void setExpandedBounds(int i2, int i3, int i4, int i5) {
        if (z(this.h, i2, i3, i4, i5)) {
            return;
        }
        this.h.set(i2, i3, i4, i5);
        this.L = true;
        y();
    }

    public void setExpandedBounds(@NonNull Rect rect) {
        setExpandedBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setExpandedTextAppearance(int i2) {
        TextAppearance textAppearance = new TextAppearance(this.a.getContext(), i2);
        ColorStateList colorStateList = textAppearance.textColor;
        if (colorStateList != null) {
            this.o = colorStateList;
        }
        float f = textAppearance.textSize;
        if (f != 0.0f) {
            this.m = f;
        }
        ColorStateList colorStateList2 = textAppearance.shadowColor;
        if (colorStateList2 != null) {
            this.X = colorStateList2;
        }
        this.V = textAppearance.shadowDx;
        this.W = textAppearance.shadowDy;
        this.U = textAppearance.shadowRadius;
        this.Z = textAppearance.letterSpacing;
        CancelableFontCallback cancelableFontCallback = this.z;
        if (cancelableFontCallback != null) {
            cancelableFontCallback.cancel();
        }
        this.z = new CancelableFontCallback(new b(), textAppearance.getFallbackFont());
        textAppearance.getFontAsync(this.a.getContext(), this.z);
        recalculate();
    }

    public void setExpandedTextColor(ColorStateList colorStateList) {
        if (this.o != colorStateList) {
            this.o = colorStateList;
            recalculate();
        }
    }

    public void setExpandedTextGravity(int i2) {
        if (this.k != i2) {
            this.k = i2;
            recalculate();
        }
    }

    public void setExpandedTextSize(float f) {
        if (this.m != f) {
            this.m = f;
            recalculate();
        }
    }

    public void setExpandedTypeface(Typeface typeface) {
        if (D(typeface)) {
            recalculate();
        }
    }

    public void setExpansionFraction(float f) {
        float clamp = MathUtils.clamp(f, 0.0f, 1.0f);
        if (clamp != this.f1593c) {
            this.f1593c = clamp;
            c();
        }
    }

    public void setFadeModeEnabled(boolean z) {
        this.f1594d = z;
    }

    public void setFadeModeStartFraction(float f) {
        this.e = f;
        this.f = e();
    }

    @RequiresApi(23)
    public void setHyphenationFrequency(int i2) {
        this.i0 = i2;
    }

    @RequiresApi(23)
    public void setLineSpacingAdd(float f) {
        this.g0 = f;
    }

    @RequiresApi(23)
    public void setLineSpacingMultiplier(@FloatRange(from = 0.0d) float f) {
        this.h0 = f;
    }

    public void setMaxLines(int i2) {
        if (i2 != this.f0) {
            this.f0 = i2;
            j();
            recalculate();
        }
    }

    public void setPositionInterpolator(TimeInterpolator timeInterpolator) {
        this.O = timeInterpolator;
        recalculate();
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.E = z;
    }

    public final boolean setState(int[] iArr) {
        this.K = iArr;
        if (!isStateful()) {
            return false;
        }
        recalculate();
        return true;
    }

    public void setText(@Nullable CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.B, charSequence)) {
            this.B = charSequence;
            this.C = null;
            j();
            recalculate();
        }
    }

    public void setTextSizeInterpolator(TimeInterpolator timeInterpolator) {
        this.P = timeInterpolator;
        recalculate();
    }

    public void setTypefaces(Typeface typeface) {
        boolean B = B(typeface);
        boolean D = D(typeface);
        if (B || D) {
            recalculate();
        }
    }

    void y() {
        this.b = this.f1595i.width() > 0 && this.f1595i.height() > 0 && this.h.width() > 0 && this.h.height() > 0;
    }
}
