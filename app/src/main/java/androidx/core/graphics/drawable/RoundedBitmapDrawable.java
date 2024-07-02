package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bsh.org.objectweb.asm.Constants;

/* loaded from: classes.dex */
public abstract class RoundedBitmapDrawable extends Drawable {
    final Bitmap a;
    private int b;
    private final BitmapShader e;
    private float g;
    private boolean k;
    private int l;
    private int m;

    /* renamed from: c, reason: collision with root package name */
    private int f549c = 119;

    /* renamed from: d, reason: collision with root package name */
    private final Paint f550d = new Paint(3);
    private final Matrix f = new Matrix();
    final Rect h = new Rect();

    /* renamed from: i, reason: collision with root package name */
    private final RectF f551i = new RectF();
    private boolean j = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
        BitmapShader bitmapShader;
        this.b = Constants.IF_ICMPNE;
        if (resources != null) {
            this.b = resources.getDisplayMetrics().densityDpi;
        }
        this.a = bitmap;
        if (bitmap != null) {
            a();
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        } else {
            this.m = -1;
            this.l = -1;
            bitmapShader = null;
        }
        this.e = bitmapShader;
    }

    private void a() {
        this.l = this.a.getScaledWidth(this.b);
        this.m = this.a.getScaledHeight(this.b);
    }

    private static boolean c(float f) {
        return f > 0.05f;
    }

    private void d() {
        this.g = Math.min(this.m, this.l) / 2;
    }

    void b(int i2, int i3, int i4, Rect rect, Rect rect2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        Bitmap bitmap = this.a;
        if (bitmap == null) {
            return;
        }
        e();
        if (this.f550d.getShader() == null) {
            canvas.drawBitmap(bitmap, (Rect) null, this.h, this.f550d);
            return;
        }
        RectF rectF = this.f551i;
        float f = this.g;
        canvas.drawRoundRect(rectF, f, f, this.f550d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        if (this.j) {
            if (this.k) {
                int min = Math.min(this.l, this.m);
                b(this.f549c, min, min, getBounds(), this.h);
                int min2 = Math.min(this.h.width(), this.h.height());
                this.h.inset(Math.max(0, (this.h.width() - min2) / 2), Math.max(0, (this.h.height() - min2) / 2));
                this.g = min2 * 0.5f;
            } else {
                b(this.f549c, this.l, this.m, getBounds(), this.h);
            }
            this.f551i.set(this.h);
            if (this.e != null) {
                Matrix matrix = this.f;
                RectF rectF = this.f551i;
                matrix.setTranslate(rectF.left, rectF.top);
                this.f.preScale(this.f551i.width() / this.a.getWidth(), this.f551i.height() / this.a.getHeight());
                this.e.setLocalMatrix(this.f);
                this.f550d.setShader(this.e);
            }
            this.j = false;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f550d.getAlpha();
    }

    @Nullable
    public final Bitmap getBitmap() {
        return this.a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.f550d.getColorFilter();
    }

    public float getCornerRadius() {
        return this.g;
    }

    public int getGravity() {
        return this.f549c;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.m;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.l;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Bitmap bitmap;
        return (this.f549c != 119 || this.k || (bitmap = this.a) == null || bitmap.hasAlpha() || this.f550d.getAlpha() < 255 || c(this.g)) ? -3 : -1;
    }

    @NonNull
    public final Paint getPaint() {
        return this.f550d;
    }

    public boolean hasAntiAlias() {
        return this.f550d.isAntiAlias();
    }

    public boolean hasMipMap() {
        throw new UnsupportedOperationException();
    }

    public boolean isCircular() {
        return this.k;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.k) {
            d();
        }
        this.j = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (i2 != this.f550d.getAlpha()) {
            this.f550d.setAlpha(i2);
            invalidateSelf();
        }
    }

    public void setAntiAlias(boolean z) {
        this.f550d.setAntiAlias(z);
        invalidateSelf();
    }

    public void setCircular(boolean z) {
        this.k = z;
        this.j = true;
        if (!z) {
            setCornerRadius(0.0f);
            return;
        }
        d();
        this.f550d.setShader(this.e);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f550d.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setCornerRadius(float f) {
        Paint paint;
        BitmapShader bitmapShader;
        if (this.g == f) {
            return;
        }
        this.k = false;
        if (c(f)) {
            paint = this.f550d;
            bitmapShader = this.e;
        } else {
            paint = this.f550d;
            bitmapShader = null;
        }
        paint.setShader(bitmapShader);
        this.g = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.f550d.setDither(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f550d.setFilterBitmap(z);
        invalidateSelf();
    }

    public void setGravity(int i2) {
        if (this.f549c != i2) {
            this.f549c = i2;
            this.j = true;
            invalidateSelf();
        }
    }

    public void setMipMap(boolean z) {
        throw new UnsupportedOperationException();
    }

    public void setTargetDensity(int i2) {
        if (this.b != i2) {
            if (i2 == 0) {
                i2 = Constants.IF_ICMPNE;
            }
            this.b = i2;
            if (this.a != null) {
                a();
            }
            invalidateSelf();
        }
    }

    public void setTargetDensity(@NonNull Canvas canvas) {
        setTargetDensity(canvas.getDensity());
    }

    public void setTargetDensity(@NonNull DisplayMetrics displayMetrics) {
        setTargetDensity(displayMetrics.densityDpi);
    }
}
