package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.system.Os;
import android.view.Surface;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;
import java.io.FileDescriptor;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GifInfoHandle {
    private volatile long a;

    static {
        i.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle(AssetFileDescriptor assetFileDescriptor) {
        try {
            this.a = o(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset());
        } finally {
            try {
                assetFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifInfoHandle(String str) {
        this.a = openFile(str);
    }

    private static native void bindSurface(long j, Surface surface, long[] jArr);

    static native int createTempNativeFileDescriptor();

    static native int extractNativeFileDescriptor(FileDescriptor fileDescriptor);

    private static native void free(long j);

    private static native int getCurrentFrameIndex(long j);

    private static native int getCurrentLoop(long j);

    private static native int getCurrentPosition(long j);

    private static native int getDuration(long j);

    private static native int getHeight(long j);

    private static native int getLoopCount(long j);

    private static native int getNativeErrorCode(long j);

    private static native int getNumberOfFrames(long j);

    private static native long[] getSavedState(long j);

    private static native int getWidth(long j);

    @RequiresApi(21)
    private static int i(FileDescriptor fileDescriptor) {
        try {
            int createTempNativeFileDescriptor = createTempNativeFileDescriptor();
            Os.dup2(fileDescriptor, createTempNativeFileDescriptor);
            return createTempNativeFileDescriptor;
        } finally {
            Os.close(fileDescriptor);
        }
    }

    private static native boolean isOpaque(long j);

    private static long o(FileDescriptor fileDescriptor, long j) {
        int i2;
        if (Build.VERSION.SDK_INT > 27) {
            try {
                i2 = i(fileDescriptor);
            } catch (Exception e) {
                throw new GifIOException(d.OPEN_FAILED.b, e.getMessage());
            }
        } else {
            i2 = extractNativeFileDescriptor(fileDescriptor);
        }
        return openNativeFileDescriptor(i2, j);
    }

    static native long openFile(String str);

    static native long openNativeFileDescriptor(int i2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GifInfoHandle p(ContentResolver contentResolver, Uri uri) {
        if ("file".equals(uri.getScheme())) {
            return new GifInfoHandle(uri.getPath());
        }
        AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
        if (openAssetFileDescriptor != null) {
            return new GifInfoHandle(openAssetFileDescriptor);
        }
        throw new IOException("Could not open AssetFileDescriptor for " + uri);
    }

    private static native void postUnbindSurface(long j);

    private static native long renderFrame(long j, Bitmap bitmap);

    private static native boolean reset(long j);

    private static native long restoreRemainder(long j);

    private static native int restoreSavedState(long j, long[] jArr, Bitmap bitmap);

    private static native void saveRemainder(long j);

    private static native void seekToTime(long j, int i2, Bitmap bitmap);

    private static native void setLoopCount(long j, char c2);

    private static native void setOptions(long j, char c2, boolean z);

    private static native void setSpeedFactor(long j, float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f <= 0.0f || Float.isNaN(f)) {
            throw new IllegalArgumentException("Speed factor is not positive");
        }
        if (f < 4.656613E-10f) {
            f = 4.656613E-10f;
        }
        synchronized (this) {
            setSpeedFactor(this.a, f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Surface surface, long[] jArr) {
        bindSurface(this.a, surface, jArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int b() {
        return getCurrentFrameIndex(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int c() {
        return getCurrentLoop(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int d() {
        return getCurrentPosition(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int e() {
        return getDuration(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int f() {
        return getHeight(this.a);
    }

    protected void finalize() {
        try {
            r();
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int g() {
        return getLoopCount(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int h() {
        return getNativeErrorCode(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int j() {
        return getNumberOfFrames(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long[] k() {
        return getSavedState(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int l() {
        return getWidth(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean m() {
        return isOpaque(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean n() {
        return this.a == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void q() {
        postUnbindSurface(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void r() {
        free(this.a);
        this.a = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long s(Bitmap bitmap) {
        return renderFrame(this.a, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean t() {
        return reset(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long u() {
        return restoreRemainder(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int v(long[] jArr, Bitmap bitmap) {
        return restoreSavedState(this.a, jArr, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void w() {
        saveRemainder(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void x(@IntRange(from = 0, to = 2147483647L) int i2, Bitmap bitmap) {
        seekToTime(this.a, i2, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(@IntRange(from = 0, to = 65535) int i2) {
        if (i2 < 0 || i2 > 65535) {
            throw new IllegalArgumentException("Loop count of range <0, 65535>");
        }
        synchronized (this) {
            setLoopCount(this.a, (char) i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(char c2, boolean z) {
        setOptions(this.a, c2, z);
    }
}
