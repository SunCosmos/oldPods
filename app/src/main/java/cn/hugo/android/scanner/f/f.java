package cn.hugo.android.scanner.f;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

/* loaded from: classes.dex */
final class f implements Camera.PreviewCallback {

    /* renamed from: d, reason: collision with root package name */
    private static final String f1432d = f.class.getSimpleName();
    private final b a;
    private Handler b;

    /* renamed from: c, reason: collision with root package name */
    private int f1433c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(b bVar) {
        this.a = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Handler handler, int i2) {
        this.b = handler;
        this.f1433c = i2;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Point d2 = this.a.d();
        Handler handler = this.b;
        if (d2 == null || handler == null) {
            Log.d(f1432d, "Got preview callback, but no handler or resolution available");
        } else {
            handler.obtainMessage(this.f1433c, d2.x, d2.y, bArr).sendToTarget();
            this.b = null;
        }
    }
}
