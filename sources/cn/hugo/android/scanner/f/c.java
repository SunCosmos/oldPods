package cn.hugo.android.scanner.f;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import c.a.a.m;
import java.io.IOException;

/* loaded from: classes.dex */
public final class c {
    private static final String l = "c";
    private final Context a;
    private final b b;

    /* renamed from: c, reason: collision with root package name */
    private Camera f1427c;

    /* renamed from: d, reason: collision with root package name */
    private a f1428d;
    private Rect e;
    private Rect f;
    private boolean g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private int f1429i;
    private int j;
    private final f k;

    public c(Context context) {
        this.a = context;
        b bVar = new b(context);
        this.b = bVar;
        this.k = new f(bVar);
    }

    private static int c(int i2, int i3, int i4) {
        int i5 = (i2 * 5) / 8;
        return i5 < i3 ? i3 : i5 > i4 ? i4 : i5;
    }

    public m a(byte[] bArr, int i2, int i3) {
        Rect e = e();
        if (e == null) {
            return null;
        }
        return new m(bArr, i2, i3, e.left, e.top, e.width(), e.height(), false);
    }

    public synchronized void b() {
        Camera camera = this.f1427c;
        if (camera != null) {
            camera.release();
            this.f1427c = null;
            this.e = null;
            this.f = null;
        }
    }

    public synchronized Rect d() {
        if (this.e == null) {
            if (this.f1427c == null) {
                return null;
            }
            Point f = this.b.f();
            if (f == null) {
                return null;
            }
            int c2 = c(f.x, 240, 1200);
            int i2 = (f.x - c2) / 2;
            int i3 = (f.y - c2) / 2;
            this.e = new Rect(i2, i3, i2 + c2, c2 + i3);
            Log.d(l, "Calculated framing rect: " + this.e);
        }
        return this.e;
    }

    public synchronized Rect e() {
        if (this.f == null) {
            Rect d2 = d();
            if (d2 == null) {
                return null;
            }
            Rect rect = new Rect(d2);
            Point d3 = this.b.d();
            Point f = this.b.f();
            if (d3 != null && f != null) {
                int i2 = rect.left;
                int i3 = d3.y;
                int i4 = f.x;
                rect.left = (i2 * i3) / i4;
                rect.right = (rect.right * i3) / i4;
                int i5 = rect.top;
                int i6 = d3.x;
                int i7 = f.y;
                rect.top = (i5 * i6) / i7;
                rect.bottom = (rect.bottom * i6) / i7;
                this.f = rect;
                String str = l;
                Log.d(str, "Calculated framingRectInPreview rect: " + this.f);
                Log.d(str, "cameraResolution: " + d3);
                Log.d(str, "screenResolution: " + f);
            }
            return null;
        }
        return this.f;
    }

    public synchronized boolean f() {
        return this.f1427c != null;
    }

    public synchronized void g(SurfaceHolder surfaceHolder) {
        int i2;
        Camera camera = this.f1427c;
        if (camera == null) {
            camera = e.a();
            if (camera == null) {
                throw new IOException();
            }
            this.f1427c = camera;
        }
        camera.setPreviewDisplay(surfaceHolder);
        if (!this.g) {
            this.g = true;
            this.b.h(camera);
            int i3 = this.f1429i;
            if (i3 > 0 && (i2 = this.j) > 0) {
                i(i3, i2);
                this.f1429i = 0;
                this.j = 0;
            }
        }
        Camera.Parameters parameters = camera.getParameters();
        String flatten = parameters == null ? null : parameters.flatten();
        try {
            this.b.j(camera, false);
        } catch (RuntimeException unused) {
            String str = l;
            Log.w(str, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(str, "Resetting to saved camera params: " + flatten);
            if (flatten != null) {
                Camera.Parameters parameters2 = camera.getParameters();
                parameters2.unflatten(flatten);
                try {
                    camera.setParameters(parameters2);
                    this.b.j(camera, true);
                } catch (RuntimeException unused2) {
                    Log.w(l, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
    }

    public synchronized void h(Handler handler, int i2) {
        Camera camera = this.f1427c;
        if (camera != null && this.h) {
            this.k.a(handler, i2);
            camera.setOneShotPreviewCallback(this.k);
        }
    }

    public synchronized void i(int i2, int i3) {
        if (this.g) {
            Point f = this.b.f();
            int i4 = f.x;
            if (i2 > i4) {
                i2 = i4;
            }
            int i5 = f.y;
            if (i3 > i5) {
                i3 = i5;
            }
            int i6 = (i4 - i2) / 2;
            int i7 = (i5 - i3) / 2;
            this.e = new Rect(i6, i7, i2 + i6, i3 + i7);
            Log.d(l, "Calculated manual framing rect: " + this.e);
            this.f = null;
        } else {
            this.f1429i = i2;
            this.j = i3;
        }
    }

    public synchronized void j(boolean z) {
        if (z != this.b.g(this.f1427c) && this.f1427c != null) {
            a aVar = this.f1428d;
            if (aVar != null) {
                aVar.c();
            }
            this.b.k(this.f1427c, z);
            a aVar2 = this.f1428d;
            if (aVar2 != null) {
                aVar2.b();
            }
        }
    }

    public synchronized void k() {
        Camera camera = this.f1427c;
        if (camera != null && !this.h) {
            camera.startPreview();
            this.h = true;
            this.f1428d = new a(this.a, this.f1427c);
        }
    }

    public synchronized void l() {
        a aVar = this.f1428d;
        if (aVar != null) {
            aVar.c();
            this.f1428d = null;
        }
        Camera camera = this.f1427c;
        if (camera != null && this.h) {
            camera.stopPreview();
            this.k.a(null, 0);
            this.h = false;
        }
    }

    public void m() {
        Camera camera = this.f1427c;
        if (camera == null || !camera.getParameters().isZoomSupported()) {
            return;
        }
        Camera.Parameters parameters = this.f1427c.getParameters();
        if (parameters.getZoom() >= parameters.getMaxZoom()) {
            return;
        }
        parameters.setZoom(parameters.getZoom() + 1);
        this.f1427c.setParameters(parameters);
    }

    public void n() {
        Camera camera = this.f1427c;
        if (camera == null || !camera.getParameters().isZoomSupported()) {
            return;
        }
        Camera.Parameters parameters = this.f1427c.getParameters();
        if (parameters.getZoom() <= 0) {
            return;
        }
        parameters.setZoom(parameters.getZoom() - 1);
        this.f1427c.setParameters(parameters);
    }
}
