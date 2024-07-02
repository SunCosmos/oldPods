package com.iapp.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Environment;
import android.util.DisplayMetrics;
import java.io.IOException;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class p {
    public static p n;
    private Activity b;
    private MediaProjectionManager h;

    /* renamed from: i, reason: collision with root package name */
    private MediaProjection f1953i;
    private VirtualDisplay j;
    private b k;
    private int l;
    private MediaRecorder m;
    private boolean a = false;

    /* renamed from: c, reason: collision with root package name */
    private String f1951c = Environment.getExternalStorageDirectory().toString() + "/iapp.mp4";

    /* renamed from: d, reason: collision with root package name */
    private int f1952d = 1280;
    private int e = 720;
    private int f = 1024000;
    private int g = 30;

    /* loaded from: classes.dex */
    private final class b extends MediaProjection.Callback {
        private b() {
        }

        @Override // android.media.projection.MediaProjection.Callback
        public void onStop() {
            super.onStop();
            if (p.this.a) {
                p.this.a = false;
                p.this.r();
                p.this.j.release();
                p.this.f1953i.stop();
                p.this.f1953i.unregisterCallback(p.this.k);
                p.this.f1953i = null;
            }
        }
    }

    public p(Activity activity) {
        this.b = activity;
    }

    private VirtualDisplay h() {
        return this.f1953i.createVirtualDisplay("MainActivity", this.f1952d, this.e, this.l, 16, this.m.getSurface(), null, null);
    }

    private void k() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.l = displayMetrics.densityDpi;
    }

    private void l(String str) {
        this.m.setAudioSource(1);
        this.m.setVideoSource(2);
        this.m.setOutputFormat(2);
        this.m.setVideoEncoder(2);
        this.m.setAudioEncoder(1);
        this.m.setVideoEncodingBitRate(this.f);
        this.m.setVideoFrameRate(this.g);
        this.m.setVideoSize(this.f1952d, this.e);
        this.m.setOutputFile(str);
    }

    private void q() {
        try {
            this.m.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            this.b.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.m.stop();
        this.m.reset();
    }

    public boolean i() {
        return this.a;
    }

    public void j(String str, int i2, int i3, int i4, int i5) {
        this.f1951c = str;
        this.f1952d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
    }

    public void m(int i2, int i3, Intent intent) {
        if (i2 != 1103) {
            this.a = false;
            return;
        }
        if (i3 != -1) {
            this.a = false;
            return;
        }
        MediaProjection mediaProjection = this.h.getMediaProjection(i3, intent);
        this.f1953i = mediaProjection;
        mediaProjection.registerCallback(this.k, null);
        this.j = h();
        this.m.start();
    }

    public void n(String str, int i2, int i3, int i4, int i5) {
        this.f1951c = str;
        this.f1952d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        k();
        this.h = (MediaProjectionManager) this.b.getSystemService("media_projection");
        this.m = new MediaRecorder();
        this.k = new b();
    }

    public void o() {
        if (this.a) {
            this.a = false;
        }
        MediaRecorder mediaRecorder = this.m;
        if (mediaRecorder != null) {
            mediaRecorder.release();
            this.m = null;
        }
        VirtualDisplay virtualDisplay = this.j;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            this.j = null;
        }
        MediaProjection mediaProjection = this.f1953i;
        if (mediaProjection != null) {
            mediaProjection.unregisterCallback(this.k);
            this.f1953i.stop();
            this.f1953i = null;
        }
    }

    public void p(boolean z) {
        this.a = z;
        if (!z) {
            r();
            this.j.release();
            return;
        }
        l(this.f1951c);
        q();
        if (this.f1953i == null) {
            this.b.startActivityForResult(new Intent(this.h.createScreenCaptureIntent()), 1103);
        } else {
            this.j = h();
            this.m.start();
        }
    }
}
