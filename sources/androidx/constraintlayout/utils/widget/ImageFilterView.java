package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.R;

/* loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {
    private c a;
    private boolean b;

    /* renamed from: c, reason: collision with root package name */
    private float f410c;

    /* renamed from: d, reason: collision with root package name */
    private float f411d;
    private float e;
    private Path f;
    ViewOutlineProvider g;
    RectF h;

    /* renamed from: i, reason: collision with root package name */
    Drawable[] f412i;
    LayerDrawable j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends ViewOutlineProvider {
        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), (Math.min(r3, r4) * ImageFilterView.this.f411d) / 2.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        float[] a = new float[20];
        ColorMatrix b = new ColorMatrix();

        /* renamed from: c, reason: collision with root package name */
        ColorMatrix f413c = new ColorMatrix();

        /* renamed from: d, reason: collision with root package name */
        float f414d = 1.0f;
        float e = 1.0f;
        float f = 1.0f;
        float g = 1.0f;

        private void a(float f) {
            float[] fArr = this.a;
            fArr[0] = f;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = f;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = f;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        private void b(float f) {
            float f2 = 1.0f - f;
            float f3 = 0.2999f * f2;
            float f4 = 0.587f * f2;
            float f5 = f2 * 0.114f;
            float[] fArr = this.a;
            fArr[0] = f3 + f;
            fArr[1] = f4;
            fArr[2] = f5;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = f3;
            fArr[6] = f4 + f;
            fArr[7] = f5;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = f3;
            fArr[11] = f4;
            fArr[12] = f5 + f;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        private void d(float f) {
            float log;
            float f2;
            if (f <= 0.0f) {
                f = 0.01f;
            }
            float f3 = (5000.0f / f) / 100.0f;
            if (f3 > 66.0f) {
                double d2 = f3 - 60.0f;
                f2 = ((float) Math.pow(d2, -0.13320475816726685d)) * 329.69873f;
                log = ((float) Math.pow(d2, 0.07551484555006027d)) * 288.12216f;
            } else {
                log = (((float) Math.log(f3)) * 99.4708f) - 161.11957f;
                f2 = 255.0f;
            }
            float log2 = f3 < 66.0f ? f3 > 19.0f ? (((float) Math.log(f3 - 10.0f)) * 138.51773f) - 305.0448f : 0.0f : 255.0f;
            float min = Math.min(255.0f, Math.max(f2, 0.0f));
            float min2 = Math.min(255.0f, Math.max(log, 0.0f));
            float min3 = Math.min(255.0f, Math.max(log2, 0.0f));
            float log3 = (((float) Math.log(50.0f)) * 99.4708f) - 161.11957f;
            float log4 = (((float) Math.log(40.0f)) * 138.51773f) - 305.0448f;
            float min4 = Math.min(255.0f, Math.max(255.0f, 0.0f));
            float min5 = Math.min(255.0f, Math.max(log3, 0.0f));
            float min6 = min3 / Math.min(255.0f, Math.max(log4, 0.0f));
            float[] fArr = this.a;
            fArr[0] = min / min4;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = min2 / min5;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = min6;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void c(ImageView imageView) {
            boolean z;
            this.b.reset();
            float f = this.e;
            boolean z2 = true;
            if (f != 1.0f) {
                b(f);
                this.b.set(this.a);
                z = true;
            } else {
                z = false;
            }
            float f2 = this.f;
            if (f2 != 1.0f) {
                this.f413c.setScale(f2, f2, f2, 1.0f);
                this.b.postConcat(this.f413c);
                z = true;
            }
            float f3 = this.g;
            if (f3 != 1.0f) {
                d(f3);
                this.f413c.set(this.a);
                this.b.postConcat(this.f413c);
                z = true;
            }
            float f4 = this.f414d;
            if (f4 != 1.0f) {
                a(f4);
                this.f413c.set(this.a);
                this.b.postConcat(this.f413c);
            } else {
                z2 = z;
            }
            if (z2) {
                imageView.setColorFilter(new ColorMatrixColorFilter(this.b));
            } else {
                imageView.clearColorFilter();
            }
        }
    }

    public ImageFilterView(Context context) {
        super(context);
        this.a = new c();
        this.b = true;
        this.f410c = 0.0f;
        this.f411d = 0.0f;
        this.e = Float.NaN;
        c(context, null);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new c();
        this.b = true;
        this.f410c = 0.0f;
        this.f411d = 0.0f;
        this.e = Float.NaN;
        c(context, attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = new c();
        this.b = true;
        this.f410c = 0.0f;
        this.f411d = 0.0f;
        this.e = Float.NaN;
        c(context, attributeSet);
    }

    private void c(Context context, AttributeSet attributeSet) {
        int i2 = Build.VERSION.SDK_INT;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.ImageFilterView_altSrc);
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == R.styleable.ImageFilterView_crossfade) {
                    this.f410c = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.ImageFilterView_warmth) {
                    setWarmth(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_saturation) {
                    setSaturation(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_contrast) {
                    setContrast(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_round) {
                    if (i2 >= 21) {
                        setRound(obtainStyledAttributes.getDimension(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_roundPercent) {
                    if (i2 >= 21) {
                        setRoundPercent(obtainStyledAttributes.getFloat(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_overlay) {
                    setOverlay(obtainStyledAttributes.getBoolean(index, this.b));
                }
            }
            obtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.f412i = drawableArr;
                drawableArr[0] = getDrawable();
                this.f412i[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(this.f412i);
                this.j = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.f410c * 255.0f));
                super.setImageDrawable(this.j);
            }
        }
    }

    private void setOverlay(boolean z) {
        this.b = z;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.f411d == 0.0f || this.f == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.f);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public float getBrightness() {
        return this.a.f414d;
    }

    public float getContrast() {
        return this.a.f;
    }

    public float getCrossfade() {
        return this.f410c;
    }

    public float getRound() {
        return this.e;
    }

    public float getRoundPercent() {
        return this.f411d;
    }

    public float getSaturation() {
        return this.a.e;
    }

    public float getWarmth() {
        return this.a.g;
    }

    public void setBrightness(float f) {
        c cVar = this.a;
        cVar.f414d = f;
        cVar.c(this);
    }

    public void setContrast(float f) {
        c cVar = this.a;
        cVar.f = f;
        cVar.c(this);
    }

    public void setCrossfade(float f) {
        this.f410c = f;
        if (this.f412i != null) {
            if (!this.b) {
                this.j.getDrawable(0).setAlpha((int) ((1.0f - this.f410c) * 255.0f));
            }
            this.j.getDrawable(1).setAlpha((int) (this.f410c * 255.0f));
            super.setImageDrawable(this.j);
        }
    }

    @RequiresApi(21)
    public void setRound(float f) {
        int i2 = Build.VERSION.SDK_INT;
        if (Float.isNaN(f)) {
            this.e = f;
            float f2 = this.f411d;
            this.f411d = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.e != f;
        this.e = f;
        if (f != 0.0f) {
            if (this.f == null) {
                this.f = new Path();
            }
            if (this.h == null) {
                this.h = new RectF();
            }
            if (i2 >= 21) {
                if (this.g == null) {
                    b bVar = new b();
                    this.g = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.h.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f.reset();
            Path path = this.f;
            RectF rectF = this.h;
            float f3 = this.e;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else if (i2 >= 21) {
            setClipToOutline(false);
        }
        if (!z || i2 < 21) {
            return;
        }
        invalidateOutline();
    }

    @RequiresApi(21)
    public void setRoundPercent(float f) {
        int i2 = Build.VERSION.SDK_INT;
        boolean z = this.f411d != f;
        this.f411d = f;
        if (f != 0.0f) {
            if (this.f == null) {
                this.f = new Path();
            }
            if (this.h == null) {
                this.h = new RectF();
            }
            if (i2 >= 21) {
                if (this.g == null) {
                    a aVar = new a();
                    this.g = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.f411d) / 2.0f;
            this.h.set(0.0f, 0.0f, width, height);
            this.f.reset();
            this.f.addRoundRect(this.h, min, min, Path.Direction.CW);
        } else if (i2 >= 21) {
            setClipToOutline(false);
        }
        if (!z || i2 < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f) {
        c cVar = this.a;
        cVar.e = f;
        cVar.c(this);
    }

    public void setWarmth(float f) {
        c cVar = this.a;
        cVar.g = f;
        cVar.c(this);
    }
}
