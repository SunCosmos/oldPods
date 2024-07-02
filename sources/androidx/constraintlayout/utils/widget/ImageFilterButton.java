package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.R;

/* loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {

    /* renamed from: c, reason: collision with root package name */
    private ImageFilterView.c f407c;

    /* renamed from: d, reason: collision with root package name */
    private float f408d;
    private float e;
    private float f;
    private Path g;
    ViewOutlineProvider h;

    /* renamed from: i, reason: collision with root package name */
    RectF f409i;
    Drawable[] j;
    LayerDrawable k;
    private boolean l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends ViewOutlineProvider {
        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), (Math.min(r3, r4) * ImageFilterButton.this.e) / 2.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.f);
        }
    }

    public ImageFilterButton(Context context) {
        super(context);
        this.f407c = new ImageFilterView.c();
        this.f408d = 0.0f;
        this.e = 0.0f;
        this.f = Float.NaN;
        this.l = true;
        c(context, null);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f407c = new ImageFilterView.c();
        this.f408d = 0.0f;
        this.e = 0.0f;
        this.f = Float.NaN;
        this.l = true;
        c(context, attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f407c = new ImageFilterView.c();
        this.f408d = 0.0f;
        this.e = 0.0f;
        this.f = Float.NaN;
        this.l = true;
        c(context, attributeSet);
    }

    private void c(Context context, AttributeSet attributeSet) {
        int i2 = Build.VERSION.SDK_INT;
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.ImageFilterView_altSrc);
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == R.styleable.ImageFilterView_crossfade) {
                    this.f408d = obtainStyledAttributes.getFloat(index, 0.0f);
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
                    setOverlay(obtainStyledAttributes.getBoolean(index, this.l));
                }
            }
            obtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.j = drawableArr;
                drawableArr[0] = getDrawable();
                this.j[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(this.j);
                this.k = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.f408d * 255.0f));
                super.setImageDrawable(this.k);
            }
        }
    }

    private void setOverlay(boolean z) {
        this.l = z;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.f == 0.0f || this.g == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.g);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public float getContrast() {
        return this.f407c.f;
    }

    public float getCrossfade() {
        return this.f408d;
    }

    public float getRound() {
        return this.f;
    }

    public float getRoundPercent() {
        return this.e;
    }

    public float getSaturation() {
        return this.f407c.e;
    }

    public float getWarmth() {
        return this.f407c.g;
    }

    public void setBrightness(float f) {
        ImageFilterView.c cVar = this.f407c;
        cVar.f414d = f;
        cVar.c(this);
    }

    public void setContrast(float f) {
        ImageFilterView.c cVar = this.f407c;
        cVar.f = f;
        cVar.c(this);
    }

    public void setCrossfade(float f) {
        this.f408d = f;
        if (this.j != null) {
            if (!this.l) {
                this.k.getDrawable(0).setAlpha((int) ((1.0f - this.f408d) * 255.0f));
            }
            this.k.getDrawable(1).setAlpha((int) (this.f408d * 255.0f));
            super.setImageDrawable(this.k);
        }
    }

    @RequiresApi(21)
    public void setRound(float f) {
        int i2 = Build.VERSION.SDK_INT;
        if (Float.isNaN(f)) {
            this.f = f;
            float f2 = this.e;
            this.e = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.f != f;
        this.f = f;
        if (f != 0.0f) {
            if (this.g == null) {
                this.g = new Path();
            }
            if (this.f409i == null) {
                this.f409i = new RectF();
            }
            if (i2 >= 21) {
                if (this.h == null) {
                    b bVar = new b();
                    this.h = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.f409i.set(0.0f, 0.0f, getWidth(), getHeight());
            this.g.reset();
            Path path = this.g;
            RectF rectF = this.f409i;
            float f3 = this.f;
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
        boolean z = this.e != f;
        this.e = f;
        if (f != 0.0f) {
            if (this.g == null) {
                this.g = new Path();
            }
            if (this.f409i == null) {
                this.f409i = new RectF();
            }
            if (i2 >= 21) {
                if (this.h == null) {
                    a aVar = new a();
                    this.h = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.e) / 2.0f;
            this.f409i.set(0.0f, 0.0f, width, height);
            this.g.reset();
            this.g.addRoundRect(this.f409i, min, min, Path.Direction.CW);
        } else if (i2 >= 21) {
            setClipToOutline(false);
        }
        if (!z || i2 < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f) {
        ImageFilterView.c cVar = this.f407c;
        cVar.e = f;
        cVar.c(this);
    }

    public void setWarmth(float f) {
        ImageFilterView.c cVar = this.f407c;
        cVar.g = f;
        cVar.c(this);
    }
}
