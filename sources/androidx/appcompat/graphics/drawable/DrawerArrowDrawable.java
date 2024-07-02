package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.graphics.drawable.DrawableCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class DrawerArrowDrawable extends Drawable {
    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    private static final float m = (float) Math.toRadians(45.0d);
    private final Paint a;
    private float b;

    /* renamed from: c, reason: collision with root package name */
    private float f67c;

    /* renamed from: d, reason: collision with root package name */
    private float f68d;
    private float e;
    private boolean f;
    private final Path g;
    private final int h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f69i;
    private float j;
    private float k;
    private int l;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface ArrowDirection {
    }

    public DrawerArrowDrawable(Context context) {
        Paint paint = new Paint();
        this.a = paint;
        this.g = new Path();
        this.f69i = false;
        this.l = 2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        setColor(obtainStyledAttributes.getColor(R.styleable.DrawerArrowToggle_color, 0));
        setBarThickness(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0f));
        setSpinEnabled(obtainStyledAttributes.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true));
        setGapSize(Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.f67c = Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.b = Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.f68d = obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private static float a(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i2 = this.l;
        boolean z = false;
        if (i2 != 0 && (i2 == 1 || (i2 == 3 ? DrawableCompat.getLayoutDirection(this) == 0 : DrawableCompat.getLayoutDirection(this) == 1))) {
            z = true;
        }
        float f = this.b;
        float a = a(this.f67c, (float) Math.sqrt(f * f * 2.0f), this.j);
        float a2 = a(this.f67c, this.f68d, this.j);
        float round = Math.round(a(0.0f, this.k, this.j));
        float a3 = a(0.0f, m, this.j);
        float a4 = a(z ? 0.0f : -180.0f, z ? 180.0f : 0.0f, this.j);
        double d2 = a;
        double d3 = a3;
        double cos = Math.cos(d3);
        Double.isNaN(d2);
        boolean z2 = z;
        float round2 = (float) Math.round(cos * d2);
        double sin = Math.sin(d3);
        Double.isNaN(d2);
        float round3 = (float) Math.round(d2 * sin);
        this.g.rewind();
        float a5 = a(this.e + this.a.getStrokeWidth(), -this.k, this.j);
        float f2 = (-a2) / 2.0f;
        this.g.moveTo(f2 + round, 0.0f);
        this.g.rLineTo(a2 - (round * 2.0f), 0.0f);
        this.g.moveTo(f2, a5);
        this.g.rLineTo(round2, round3);
        this.g.moveTo(f2, -a5);
        this.g.rLineTo(round2, -round3);
        this.g.close();
        canvas.save();
        float strokeWidth = this.a.getStrokeWidth();
        float height = bounds.height() - (3.0f * strokeWidth);
        canvas.translate(bounds.centerX(), ((((int) (height - (2.0f * r5))) / 4) * 2) + (strokeWidth * 1.5f) + this.e);
        if (this.f) {
            canvas.rotate(a4 * (this.f69i ^ z2 ? -1 : 1));
        } else if (z2) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.g, this.a);
        canvas.restore();
    }

    public float getArrowHeadLength() {
        return this.b;
    }

    public float getArrowShaftLength() {
        return this.f68d;
    }

    public float getBarLength() {
        return this.f67c;
    }

    public float getBarThickness() {
        return this.a.getStrokeWidth();
    }

    @ColorInt
    public int getColor() {
        return this.a.getColor();
    }

    public int getDirection() {
        return this.l;
    }

    public float getGapSize() {
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final Paint getPaint() {
        return this.a;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.j;
    }

    public boolean isSpinEnabled() {
        return this.f;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (i2 != this.a.getAlpha()) {
            this.a.setAlpha(i2);
            invalidateSelf();
        }
    }

    public void setArrowHeadLength(float f) {
        if (this.b != f) {
            this.b = f;
            invalidateSelf();
        }
    }

    public void setArrowShaftLength(float f) {
        if (this.f68d != f) {
            this.f68d = f;
            invalidateSelf();
        }
    }

    public void setBarLength(float f) {
        if (this.f67c != f) {
            this.f67c = f;
            invalidateSelf();
        }
    }

    public void setBarThickness(float f) {
        if (this.a.getStrokeWidth() != f) {
            this.a.setStrokeWidth(f);
            double d2 = f / 2.0f;
            double cos = Math.cos(m);
            Double.isNaN(d2);
            this.k = (float) (d2 * cos);
            invalidateSelf();
        }
    }

    public void setColor(@ColorInt int i2) {
        if (i2 != this.a.getColor()) {
            this.a.setColor(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDirection(int i2) {
        if (i2 != this.l) {
            this.l = i2;
            invalidateSelf();
        }
    }

    public void setGapSize(float f) {
        if (f != this.e) {
            this.e = f;
            invalidateSelf();
        }
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.j != f) {
            this.j = f;
            invalidateSelf();
        }
    }

    public void setSpinEnabled(boolean z) {
        if (this.f != z) {
            this.f = z;
            invalidateSelf();
        }
    }

    public void setVerticalMirror(boolean z) {
        if (this.f69i != z) {
            this.f69i = z;
            invalidateSelf();
        }
    }
}
