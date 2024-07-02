package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.MediaController;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class c extends Drawable implements Animatable, MediaController.MediaPlayerControl {
    final ScheduledThreadPoolExecutor a;
    volatile boolean b;

    /* renamed from: c */
    long f2066c;

    /* renamed from: d */
    private final Rect f2067d;
    protected final Paint e;
    final Bitmap f;
    final GifInfoHandle g;
    final ConcurrentLinkedQueue<pl.droidsonroids.gif.a> h;

    /* renamed from: i */
    private ColorStateList f2068i;
    private PorterDuffColorFilter j;
    private PorterDuff.Mode k;
    final boolean l;
    final h m;
    private final m n;
    private final Rect o;
    ScheduledFuture<?> p;
    private int q;
    private int r;
    private pl.droidsonroids.gif.o.a s;

    /* loaded from: classes.dex */
    public class a extends n {
        a(c cVar) {
            super(cVar);
        }

        @Override // pl.droidsonroids.gif.n
        public void a() {
            if (c.this.g.t()) {
                c.this.start();
            }
        }
    }

    /* loaded from: classes.dex */
    class b extends n {
        final /* synthetic */ int b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(c cVar, int i2) {
            super(cVar);
            this.b = i2;
        }

        @Override // pl.droidsonroids.gif.n
        public void a() {
            c cVar = c.this;
            cVar.g.x(this.b, cVar.f);
            this.a.m.sendEmptyMessageAtTime(-1, 0L);
        }
    }

    public c(@Nullable ContentResolver contentResolver, @NonNull Uri uri) {
        this(GifInfoHandle.p(contentResolver, uri), null, null, true);
    }

    public c(@NonNull AssetFileDescriptor assetFileDescriptor) {
        this(new GifInfoHandle(assetFileDescriptor), null, null, true);
    }

    public c(@NonNull AssetManager assetManager, @NonNull String str) {
        this(assetManager.openFd(str));
    }

    public c(@NonNull Resources resources, @DrawableRes @RawRes int i2) {
        this(resources.openRawResourceFd(i2));
        float b2 = f.b(resources, i2);
        this.r = (int) (this.g.f() * b2);
        this.q = (int) (this.g.l() * b2);
    }

    public c(@NonNull String str) {
        this(new GifInfoHandle(str), null, null, true);
    }

    c(GifInfoHandle gifInfoHandle, c cVar, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z) {
        this.b = true;
        this.f2066c = Long.MIN_VALUE;
        this.f2067d = new Rect();
        this.e = new Paint(6);
        this.h = new ConcurrentLinkedQueue<>();
        m mVar = new m(this);
        this.n = mVar;
        this.l = z;
        this.a = scheduledThreadPoolExecutor == null ? e.a() : scheduledThreadPoolExecutor;
        this.g = gifInfoHandle;
        Bitmap bitmap = null;
        if (cVar != null) {
            synchronized (cVar.g) {
                if (!cVar.g.n() && cVar.g.f() >= gifInfoHandle.f() && cVar.g.l() >= gifInfoHandle.l()) {
                    cVar.i();
                    Bitmap bitmap2 = cVar.f;
                    bitmap2.eraseColor(0);
                    bitmap = bitmap2;
                }
            }
        }
        if (bitmap == null) {
            this.f = Bitmap.createBitmap(gifInfoHandle.l(), gifInfoHandle.f(), Bitmap.Config.ARGB_8888);
        } else {
            this.f = bitmap;
        }
        this.f.setHasAlpha(!gifInfoHandle.m());
        this.o = new Rect(0, 0, gifInfoHandle.l(), gifInfoHandle.f());
        this.m = new h(this);
        mVar.a();
        this.q = gifInfoHandle.l();
        this.r = gifInfoHandle.f();
    }

    private void a() {
        ScheduledFuture<?> scheduledFuture = this.p;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.m.removeMessages(-1);
    }

    private void g() {
        if (this.l && this.b) {
            long j = this.f2066c;
            if (j != Long.MIN_VALUE) {
                long max = Math.max(0L, j - SystemClock.uptimeMillis());
                this.f2066c = Long.MIN_VALUE;
                this.a.remove(this.n);
                this.p = this.a.schedule(this.n, max, TimeUnit.MILLISECONDS);
            }
        }
    }

    private void i() {
        this.b = false;
        this.m.removeMessages(-1);
        this.g.r();
    }

    private PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public int b() {
        return this.g.b();
    }

    public int c() {
        int c2 = this.g.c();
        return (c2 == 0 || c2 < this.g.g()) ? c2 : c2 - 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return d() > 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return d() > 1;
    }

    public int d() {
        return this.g.j();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        boolean z;
        if (this.j == null || this.e.getColorFilter() != null) {
            z = false;
        } else {
            this.e.setColorFilter(this.j);
            z = true;
        }
        pl.droidsonroids.gif.o.a aVar = this.s;
        if (aVar == null) {
            canvas.drawBitmap(this.f, this.o, this.f2067d, this.e);
        } else {
            aVar.b(canvas, this.e, this.f);
        }
        if (z) {
            this.e.setColorFilter(null);
        }
    }

    public boolean e() {
        return this.g.n();
    }

    public void f() {
        this.a.execute(new a(this));
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.e.getAlpha();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        return 100;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.e.getColorFilter();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        return this.g.d();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        return this.g.e();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.r;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.q;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return (!this.g.m() || this.e.getAlpha() < 255) ? -2 : -1;
    }

    public void h(@IntRange(from = 0, to = 65535) int i2) {
        this.g.y(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        g();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return this.b;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.b;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        return super.isStateful() || ((colorStateList = this.f2068i) != null && colorStateList.isStateful());
    }

    public void j(long j) {
        if (this.l) {
            this.f2066c = 0L;
            this.m.sendEmptyMessageAtTime(-1, 0L);
        } else {
            a();
            this.p = this.a.schedule(this.n, Math.max(j, 0L), TimeUnit.MILLISECONDS);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.f2067d.set(rect);
        pl.droidsonroids.gif.o.a aVar = this.s;
        if (aVar != null) {
            aVar.a(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f2068i;
        if (colorStateList == null || (mode = this.k) == null) {
            return false;
        }
        this.j = k(colorStateList, mode);
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        stop();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(@IntRange(from = 0, to = 2147483647L) int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Position is not positive");
        }
        this.a.execute(new b(this, i2));
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = 0, to = 255) int i2) {
        this.e.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated
    public void setDither(boolean z) {
        this.e.setDither(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.e.setFilterBitmap(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f2068i = colorStateList;
        this.j = k(colorStateList, this.k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        this.k = mode;
        this.j = k(this.f2068i, mode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!this.l) {
            if (z) {
                if (z2) {
                    f();
                }
                if (visible) {
                    start();
                }
            } else if (visible) {
                stop();
            }
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable, android.widget.MediaController.MediaPlayerControl
    public void start() {
        synchronized (this) {
            if (this.b) {
                return;
            }
            this.b = true;
            j(this.g.u());
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        synchronized (this) {
            if (this.b) {
                this.b = false;
                a();
                this.g.w();
            }
        }
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, error: %d", Integer.valueOf(this.g.l()), Integer.valueOf(this.g.f()), Integer.valueOf(this.g.j()), Integer.valueOf(this.g.h()));
    }
}
