package pl.droidsonroids.gif;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Surface;
import android.view.TextureView;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.lang.ref.WeakReference;
import pl.droidsonroids.gif.f;
import pl.droidsonroids.gif.g;

/* loaded from: classes.dex */
public class GifTextureView extends TextureView {
    private static final ImageView.ScaleType[] g = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private ImageView.ScaleType a;
    private final Matrix b;

    /* renamed from: c, reason: collision with root package name */
    private g f2062c;

    /* renamed from: d, reason: collision with root package name */
    private c f2063d;
    private float e;
    private f.b f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            a = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(Canvas canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c extends Thread implements TextureView.SurfaceTextureListener {
        final pl.droidsonroids.gif.b a;
        private GifInfoHandle b;

        /* renamed from: c, reason: collision with root package name */
        private IOException f2064c;

        /* renamed from: d, reason: collision with root package name */
        long[] f2065d;
        private final WeakReference<GifTextureView> e;

        /* loaded from: classes.dex */
        class a implements Runnable {
            final /* synthetic */ GifTextureView a;

            a(GifTextureView gifTextureView) {
                this.a = gifTextureView;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.a.i(c.this.b);
            }
        }

        c(GifTextureView gifTextureView) {
            super("GifRenderThread");
            this.a = new pl.droidsonroids.gif.b();
            this.b = new GifInfoHandle();
            this.e = new WeakReference<>(gifTextureView);
        }

        void c(@NonNull GifTextureView gifTextureView, @Nullable b bVar) {
            this.a.b();
            gifTextureView.setSuperSurfaceTextureListener(bVar != null ? new j(bVar) : null);
            this.b.q();
            interrupt();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
            GifTextureView gifTextureView = this.e.get();
            if (gifTextureView != null) {
                gifTextureView.i(this.b);
            }
            this.a.c();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.a.b();
            this.b.q();
            interrupt();
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                GifTextureView gifTextureView = this.e.get();
                if (gifTextureView == null) {
                    return;
                }
                GifInfoHandle a2 = gifTextureView.f2062c.a();
                this.b = a2;
                a2.z((char) 1, gifTextureView.isOpaque());
                if (gifTextureView.f.b >= 0) {
                    this.b.y(gifTextureView.f.b);
                }
                GifTextureView gifTextureView2 = this.e.get();
                if (gifTextureView2 == null) {
                    this.b.r();
                    return;
                }
                gifTextureView2.setSuperSurfaceTextureListener(this);
                boolean isAvailable = gifTextureView2.isAvailable();
                this.a.d(isAvailable);
                if (isAvailable) {
                    gifTextureView2.post(new a(gifTextureView2));
                }
                this.b.A(gifTextureView2.e);
                while (!isInterrupted()) {
                    try {
                        this.a.a();
                        GifTextureView gifTextureView3 = this.e.get();
                        if (gifTextureView3 == null) {
                            break;
                        }
                        SurfaceTexture surfaceTexture = gifTextureView3.getSurfaceTexture();
                        if (surfaceTexture != null) {
                            Surface surface = new Surface(surfaceTexture);
                            try {
                                this.b.a(surface, this.f2065d);
                            } finally {
                                surface.release();
                            }
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                this.b.r();
                this.b = new GifInfoHandle();
            } catch (IOException e) {
                this.f2064c = e;
            }
        }
    }

    public GifTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = ImageView.ScaleType.FIT_CENTER;
        this.b = new Matrix();
        this.e = 1.0f;
        g(attributeSet, 0, 0);
    }

    public GifTextureView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = ImageView.ScaleType.FIT_CENTER;
        this.b = new Matrix();
        this.e = 1.0f;
        g(attributeSet, i2, 0);
    }

    private static g f(TypedArray typedArray) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(k.b, typedValue)) {
            return null;
        }
        if (typedValue.resourceId != 0) {
            String resourceTypeName = typedArray.getResources().getResourceTypeName(typedValue.resourceId);
            if (f.a.contains(resourceTypeName)) {
                return new g.c(typedArray.getResources(), typedValue.resourceId);
            }
            if (!"string".equals(resourceTypeName)) {
                throw new IllegalArgumentException("Expected string, drawable, mipmap or raw resource type. '" + resourceTypeName + "' is not supported");
            }
        }
        return new g.b(typedArray.getResources().getAssets(), typedValue.string.toString());
    }

    private void g(AttributeSet attributeSet, int i2, int i3) {
        if (attributeSet != null) {
            int attributeIntValue = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "scaleType", -1);
            if (attributeIntValue >= 0) {
                ImageView.ScaleType[] scaleTypeArr = g;
                if (attributeIntValue < scaleTypeArr.length) {
                    this.a = scaleTypeArr[attributeIntValue];
                }
            }
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, k.a, i2, i3);
            this.f2062c = f(obtainStyledAttributes);
            super.setOpaque(obtainStyledAttributes.getBoolean(k.f2075c, false));
            obtainStyledAttributes.recycle();
            this.f = new f.b(this, attributeSet, i2, i3);
        } else {
            super.setOpaque(false);
            this.f = new f.b();
        }
        if (isInEditMode()) {
            return;
        }
        c cVar = new c(this);
        this.f2063d = cVar;
        if (this.f2062c != null) {
            cVar.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x003e. Please report as an issue. */
    public void i(GifInfoHandle gifInfoHandle) {
        Matrix.ScaleToFit scaleToFit;
        Matrix matrix = new Matrix();
        float width = getWidth();
        float height = getHeight();
        float l = gifInfoHandle.l() / width;
        float f = gifInfoHandle.f() / height;
        RectF rectF = new RectF(0.0f, 0.0f, gifInfoHandle.l(), gifInfoHandle.f());
        RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
        float f2 = 1.0f;
        switch (a.a[this.a.ordinal()]) {
            case 1:
                matrix.setScale(l, f, width / 2.0f, height / 2.0f);
                super.setTransform(matrix);
                return;
            case 2:
                f2 = 1.0f / Math.min(l, f);
                matrix.setScale(l * f2, f2 * f, width / 2.0f, height / 2.0f);
                super.setTransform(matrix);
                return;
            case 3:
                if (gifInfoHandle.l() > width || gifInfoHandle.f() > height) {
                    f2 = Math.min(1.0f / l, 1.0f / f);
                }
                matrix.setScale(l * f2, f2 * f, width / 2.0f, height / 2.0f);
                super.setTransform(matrix);
                return;
            case 4:
                scaleToFit = Matrix.ScaleToFit.CENTER;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(l, f);
                super.setTransform(matrix);
                return;
            case 5:
                scaleToFit = Matrix.ScaleToFit.END;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(l, f);
                super.setTransform(matrix);
                return;
            case 6:
                scaleToFit = Matrix.ScaleToFit.START;
                matrix.setRectToRect(rectF, rectF2, scaleToFit);
                matrix.preScale(l, f);
                super.setTransform(matrix);
                return;
            case 7:
                return;
            case 8:
                matrix.set(this.b);
                matrix.preScale(l, f);
                super.setTransform(matrix);
                return;
            default:
                super.setTransform(matrix);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSuperSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        super.setSurfaceTextureListener(surfaceTextureListener);
    }

    @Nullable
    public IOException getIOException() {
        return this.f2063d.f2064c != null ? this.f2063d.f2064c : GifIOException.a(this.f2063d.b.h());
    }

    public ImageView.ScaleType getScaleType() {
        return this.a;
    }

    @Override // android.view.TextureView
    public TextureView.SurfaceTextureListener getSurfaceTextureListener() {
        return null;
    }

    @Override // android.view.TextureView
    public Matrix getTransform(Matrix matrix) {
        if (matrix == null) {
            matrix = new Matrix();
        }
        matrix.set(this.b);
        return matrix;
    }

    public synchronized void h(@Nullable g gVar, @Nullable b bVar) {
        this.f2063d.c(this, bVar);
        try {
            this.f2063d.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.f2062c = gVar;
        c cVar = new c(this);
        this.f2063d = cVar;
        if (gVar != null) {
            cVar.start();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.f2063d.c(this, null);
        super.onDetachedFromWindow();
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        GifViewSavedState gifViewSavedState = (GifViewSavedState) parcelable;
        super.onRestoreInstanceState(gifViewSavedState.getSuperState());
        this.f2063d.f2065d = gifViewSavedState.a[0];
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        c cVar = this.f2063d;
        cVar.f2065d = cVar.b.k();
        return new GifViewSavedState(super.onSaveInstanceState(), this.f.a ? this.f2063d.f2065d : null);
    }

    public void setFreezesAnimation(boolean z) {
        this.f.a = z;
    }

    public void setImageMatrix(Matrix matrix) {
        setTransform(matrix);
    }

    public synchronized void setInputSource(@Nullable g gVar) {
        h(gVar, null);
    }

    @Override // android.view.TextureView
    public void setOpaque(boolean z) {
        if (z != isOpaque()) {
            super.setOpaque(z);
            setInputSource(this.f2062c);
        }
    }

    public void setScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.a = scaleType;
        i(this.f2063d.b);
    }

    public void setSpeed(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        this.e = f;
        this.f2063d.b.A(f);
    }

    @Override // android.view.TextureView
    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        throw new UnsupportedOperationException("Changing SurfaceTexture is not supported");
    }

    @Override // android.view.TextureView
    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        throw new UnsupportedOperationException("Changing SurfaceTextureListener is not supported");
    }

    @Override // android.view.TextureView
    public void setTransform(Matrix matrix) {
        this.b.set(matrix);
        i(this.f2063d.b);
    }
}
