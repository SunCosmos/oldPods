package com.google.android.material.circularreveal;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.math.MathUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class CircularRevealHelper {
    public static final int BITMAP_SHADER = 0;
    public static final int CLIP_PATH = 1;
    public static final int REVEAL_ANIMATOR = 2;
    public static final int STRATEGY;
    private final Delegate a;

    @NonNull
    private final View b;

    /* renamed from: c */
    @NonNull
    private final Path f1525c;

    /* renamed from: d */
    @NonNull
    private final Paint f1526d;

    @NonNull
    private final Paint e;

    @Nullable
    private CircularRevealWidget.RevealInfo f;

    @Nullable
    private Drawable g;
    private boolean h;

    /* renamed from: i */
    private boolean f1527i;

    /* loaded from: classes.dex */
    public interface Delegate {
        void actualDraw(Canvas canvas);

        boolean actualIsOpaque();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Strategy {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        STRATEGY = i2 >= 21 ? 2 : i2 >= 18 ? 1 : 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CircularRevealHelper(Delegate delegate) {
        this.a = delegate;
        View view = (View) delegate;
        this.b = view;
        view.setWillNotDraw(false);
        this.f1525c = new Path();
        this.f1526d = new Paint(7);
        Paint paint = new Paint(1);
        this.e = paint;
        paint.setColor(0);
    }

    private void a(@NonNull Canvas canvas) {
        if (e()) {
            Rect bounds = this.g.getBounds();
            float width = this.f.centerX - (bounds.width() / 2.0f);
            float height = this.f.centerY - (bounds.height() / 2.0f);
            canvas.translate(width, height);
            this.g.draw(canvas);
            canvas.translate(-width, -height);
        }
    }

    private float b(@NonNull CircularRevealWidget.RevealInfo revealInfo) {
        return MathUtils.distanceToFurthestCorner(revealInfo.centerX, revealInfo.centerY, 0.0f, 0.0f, this.b.getWidth(), this.b.getHeight());
    }

    private void c() {
        if (STRATEGY == 1) {
            this.f1525c.rewind();
            CircularRevealWidget.RevealInfo revealInfo = this.f;
            if (revealInfo != null) {
                this.f1525c.addCircle(revealInfo.centerX, revealInfo.centerY, revealInfo.radius, Path.Direction.CW);
            }
        }
        this.b.invalidate();
    }

    private boolean d() {
        CircularRevealWidget.RevealInfo revealInfo = this.f;
        boolean z = revealInfo == null || revealInfo.isInvalid();
        return STRATEGY == 0 ? !z && this.f1527i : !z;
    }

    private boolean e() {
        return (this.h || this.g == null || this.f == null) ? false : true;
    }

    private boolean f() {
        return (this.h || Color.alpha(this.e.getColor()) == 0) ? false : true;
    }

    public void buildCircularRevealCache() {
        if (STRATEGY == 0) {
            this.h = true;
            this.f1527i = false;
            this.b.buildDrawingCache();
            Bitmap drawingCache = this.b.getDrawingCache();
            if (drawingCache == null && this.b.getWidth() != 0 && this.b.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.b.getWidth(), this.b.getHeight(), Bitmap.Config.ARGB_8888);
                this.b.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.f1526d;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.h = false;
            this.f1527i = true;
        }
    }

    public void destroyCircularRevealCache() {
        if (STRATEGY == 0) {
            this.f1527i = false;
            this.b.destroyDrawingCache();
            this.f1526d.setShader(null);
            this.b.invalidate();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
    
        if (f() != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x008d, code lost:
    
        r9.drawRect(0.0f, 0.0f, r8.b.getWidth(), r8.b.getHeight(), r8.e);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008b, code lost:
    
        if (f() != false) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(@androidx.annotation.NonNull android.graphics.Canvas r9) {
        /*
            r8 = this;
            boolean r0 = r8.d()
            if (r0 == 0) goto L82
            int r0 = com.google.android.material.circularreveal.CircularRevealHelper.STRATEGY
            if (r0 == 0) goto L61
            r1 = 1
            if (r0 == r1) goto L33
            r1 = 2
            if (r0 != r1) goto L1c
            com.google.android.material.circularreveal.CircularRevealHelper$Delegate r0 = r8.a
            r0.actualDraw(r9)
            boolean r0 = r8.f()
            if (r0 == 0) goto La3
            goto L8d
        L1c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unsupported strategy "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r9.<init>(r0)
            throw r9
        L33:
            int r0 = r9.save()
            android.graphics.Path r1 = r8.f1525c
            r9.clipPath(r1)
            com.google.android.material.circularreveal.CircularRevealHelper$Delegate r1 = r8.a
            r1.actualDraw(r9)
            boolean r1 = r8.f()
            if (r1 == 0) goto L5d
            r3 = 0
            r4 = 0
            android.view.View r1 = r8.b
            int r1 = r1.getWidth()
            float r5 = (float) r1
            android.view.View r1 = r8.b
            int r1 = r1.getHeight()
            float r6 = (float) r1
            android.graphics.Paint r7 = r8.e
            r2 = r9
            r2.drawRect(r3, r4, r5, r6, r7)
        L5d:
            r9.restoreToCount(r0)
            goto La3
        L61:
            com.google.android.material.circularreveal.CircularRevealWidget$RevealInfo r0 = r8.f
            float r1 = r0.centerX
            float r2 = r0.centerY
            float r0 = r0.radius
            android.graphics.Paint r3 = r8.f1526d
            r9.drawCircle(r1, r2, r0, r3)
            boolean r0 = r8.f()
            if (r0 == 0) goto La3
            com.google.android.material.circularreveal.CircularRevealWidget$RevealInfo r0 = r8.f
            float r1 = r0.centerX
            float r2 = r0.centerY
            float r0 = r0.radius
            android.graphics.Paint r3 = r8.e
            r9.drawCircle(r1, r2, r0, r3)
            goto La3
        L82:
            com.google.android.material.circularreveal.CircularRevealHelper$Delegate r0 = r8.a
            r0.actualDraw(r9)
            boolean r0 = r8.f()
            if (r0 == 0) goto La3
        L8d:
            r2 = 0
            r3 = 0
            android.view.View r0 = r8.b
            int r0 = r0.getWidth()
            float r4 = (float) r0
            android.view.View r0 = r8.b
            int r0 = r0.getHeight()
            float r5 = (float) r0
            android.graphics.Paint r6 = r8.e
            r1 = r9
            r1.drawRect(r2, r3, r4, r5, r6)
        La3:
            r8.a(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.circularreveal.CircularRevealHelper.draw(android.graphics.Canvas):void");
    }

    @Nullable
    public Drawable getCircularRevealOverlayDrawable() {
        return this.g;
    }

    @ColorInt
    public int getCircularRevealScrimColor() {
        return this.e.getColor();
    }

    @Nullable
    public CircularRevealWidget.RevealInfo getRevealInfo() {
        CircularRevealWidget.RevealInfo revealInfo = this.f;
        if (revealInfo == null) {
            return null;
        }
        CircularRevealWidget.RevealInfo revealInfo2 = new CircularRevealWidget.RevealInfo(revealInfo);
        if (revealInfo2.isInvalid()) {
            revealInfo2.radius = b(revealInfo2);
        }
        return revealInfo2;
    }

    public boolean isOpaque() {
        return this.a.actualIsOpaque() && !d();
    }

    public void setCircularRevealOverlayDrawable(@Nullable Drawable drawable) {
        this.g = drawable;
        this.b.invalidate();
    }

    public void setCircularRevealScrimColor(@ColorInt int i2) {
        this.e.setColor(i2);
        this.b.invalidate();
    }

    public void setRevealInfo(@Nullable CircularRevealWidget.RevealInfo revealInfo) {
        if (revealInfo == null) {
            this.f = null;
        } else {
            CircularRevealWidget.RevealInfo revealInfo2 = this.f;
            if (revealInfo2 == null) {
                this.f = new CircularRevealWidget.RevealInfo(revealInfo);
            } else {
                revealInfo2.set(revealInfo);
            }
            if (MathUtils.geq(revealInfo.radius, b(revealInfo), 1.0E-4f)) {
                this.f.radius = Float.MAX_VALUE;
            }
        }
        c();
    }
}
