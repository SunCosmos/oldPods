package com.iapp.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.iapp.app.run.mian;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public class m {
    private Activity a;
    private Context b;

    /* renamed from: c, reason: collision with root package name */
    private SurfaceView f1943c;

    /* renamed from: d, reason: collision with root package name */
    private SurfaceHolder f1944d;
    private int e;
    private int f;
    private Camera g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1945i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private String n;
    private String o;
    Camera.AutoFocusCallback p;
    Camera.PictureCallback q;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements SurfaceHolder.Callback {
        a() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            m.this.t();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (m.this.g != null) {
                if (m.this.h) {
                    m.this.g.stopPreview();
                }
                m.this.g.release();
                m.this.g = null;
            }
            m.this.h = false;
        }
    }

    /* loaded from: classes.dex */
    class b implements Camera.AutoFocusCallback {

        /* loaded from: classes.dex */
        class a implements Camera.ShutterCallback {
            a(b bVar) {
            }

            @Override // android.hardware.Camera.ShutterCallback
            public void onShutter() {
            }
        }

        /* renamed from: com.iapp.app.m$b$b, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0067b implements Camera.PictureCallback {
            C0067b(b bVar) {
            }

            @Override // android.hardware.Camera.PictureCallback
            public void onPictureTaken(byte[] bArr, Camera camera) {
            }
        }

        b() {
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, Camera camera) {
            if (z) {
                camera.takePicture(new a(this), new C0067b(this), m.this.q);
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements Camera.PictureCallback {
        c() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            Bitmap decodeByteArray;
            if (m.this.l != 0) {
                m mVar = m.this;
                decodeByteArray = mVar.v(mVar.l, BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            } else {
                decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(m.this.n));
                decodeByteArray.compress(Bitmap.CompressFormat.JPEG, m.this.m, fileOutputStream);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean z = m.this.f1945i;
            camera.stopPreview();
            if (z) {
                m.this.h = false;
            } else {
                camera.startPreview();
                m.this.h = true;
            }
            if (m.this.n == null || m.this.o == null) {
                return;
            }
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(m.this.b, m.this.a);
                tVar.T("st_vId", Integer.valueOf(m.this.f1943c.getId()));
                tVar.T("st_vW", m.this.f1943c);
                tVar.T("st_oS", this);
                tVar.T("st_fN", m.this.n);
                tVar.f(m.this.o);
                return;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(m.this.b, m.this.a);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(m.this.f1943c.getId()));
            aid_YuCodeX.dim("st_vW", m.this.f1943c);
            aid_YuCodeX.dim("st_oS", this);
            aid_YuCodeX.dim("st_fN", m.this.n);
            aid_YuCodeX.YuGoX(m.this.o);
        }
    }

    public m(Context context, Activity activity, SurfaceView surfaceView, boolean z, int i2) {
        this.a = null;
        this.b = null;
        this.e = -1;
        this.f = -1;
        this.g = null;
        this.h = false;
        this.f1945i = false;
        this.j = false;
        this.k = 90;
        this.l = 90;
        this.m = 95;
        this.n = null;
        this.p = new b();
        this.q = new c();
        this.b = context;
        this.a = activity;
        this.f1943c = surfaceView;
        this.j = z;
        this.k = i2;
        this.o = c.b.a.a.p.c(((Object[]) surfaceView.getTag())[2].toString(), "<eventItme type=\"onpicturecallback\">", "</eventItme>");
        s();
    }

    public m(Context context, Activity activity, SurfaceView surfaceView, boolean z, int i2, int i3, int i4, int i5) {
        this.a = null;
        this.b = null;
        this.e = -1;
        this.f = -1;
        this.g = null;
        this.h = false;
        this.f1945i = false;
        this.j = false;
        this.k = 90;
        this.l = 90;
        this.m = 95;
        this.n = null;
        this.p = new b();
        this.q = new c();
        this.b = context;
        this.a = activity;
        this.f1943c = surfaceView;
        this.j = z;
        this.k = i2;
        this.e = i3;
        this.f = i4;
        this.m = i5;
        this.o = c.b.a.a.p.c(((Object[]) surfaceView.getTag())[2].toString(), "<eventItme type=\"onpicturecallback\">", "</eventItme>");
        s();
    }

    private void A(Camera camera) {
        Camera.Parameters parameters;
        if (camera == null || (parameters = camera.getParameters()) == null) {
            return;
        }
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        String flashMode = parameters.getFlashMode();
        if (supportedFlashModes == null || "off".equals(flashMode) || !supportedFlashModes.contains("off")) {
            return;
        }
        parameters.setFlashMode("off");
        camera.setParameters(parameters);
    }

    private void B(Camera camera) {
        Camera.Parameters parameters;
        List<String> supportedFlashModes;
        if (camera == null || (parameters = camera.getParameters()) == null || (supportedFlashModes = parameters.getSupportedFlashModes()) == null || "torch".equals(parameters.getFlashMode()) || !supportedFlashModes.contains("torch")) {
            return;
        }
        parameters.setFlashMode("torch");
        camera.setParameters(parameters);
    }

    @TargetApi(9)
    private int a() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 0) {
                return i2;
            }
        }
        return -1;
    }

    @TargetApi(9)
    private int b() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 1) {
                return i2;
            }
        }
        return -1;
    }

    private void s() {
        SurfaceHolder holder = this.f1943c.getHolder();
        this.f1944d = holder;
        holder.addCallback(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(9)
    public void t() {
        int i2;
        int i3;
        if (!this.h) {
            int b2 = this.j ? b() : a();
            if (b2 == -1) {
                return;
            }
            Camera open = Camera.open(b2);
            this.g = open;
            open.setDisplayOrientation(this.k);
        }
        Camera camera = this.g;
        if (camera == null || this.h) {
            return;
        }
        try {
            Camera.Parameters parameters = camera.getParameters();
            int i4 = this.e;
            if (i4 != -1 && (i3 = this.f) != -1) {
                parameters.setPreviewSize(i4, i3);
            }
            parameters.setPreviewFpsRange(4, 10);
            parameters.setPictureFormat(256);
            parameters.set("jpeg-quality", this.m);
            int i5 = this.e;
            if (i5 != -1 && (i2 = this.f) != -1) {
                parameters.setPictureSize(i5, i2);
            }
            this.g.setPreviewDisplay(this.f1944d);
            this.g.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.h = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap v(int i2, Bitmap bitmap) {
        Bitmap bitmap2;
        Matrix matrix = new Matrix();
        matrix.postRotate(i2);
        try {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError unused) {
            bitmap2 = null;
        }
        if (bitmap2 == null) {
            bitmap2 = bitmap;
        }
        if (bitmap != bitmap2) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    public void q(String str, int i2, boolean z) {
        if (this.g != null) {
            c.b.a.a.d.b(str, false);
            this.n = str;
            this.l = i2;
            this.f1945i = z;
            this.g.autoFocus(this.p);
        }
    }

    public int r() {
        return this.k;
    }

    public void u() {
        Camera camera = this.g;
        if (camera != null) {
            if (this.h) {
                camera.stopPreview();
            }
            this.g.release();
            this.g = null;
        }
        this.h = false;
    }

    public void w(int i2) {
        this.k = i2;
        this.g.setDisplayOrientation(i2);
    }

    public void x(boolean z) {
        if (z) {
            B(this.g);
        } else {
            A(this.g);
        }
    }

    public void y() {
        this.g.startPreview();
        this.h = true;
    }

    public void z() {
        this.g.stopPreview();
        this.h = false;
    }
}
