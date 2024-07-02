package cn.hugo.android.scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;
import c.a.a.p;
import c.a.a.w.a.u;
import cn.hugo.android.scanner.view.ViewfinderView;
import com.oldpods.app.R;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class CaptureActivity extends Activity implements SurfaceHolder.Callback, View.OnClickListener {
    private static final String o = CaptureActivity.class.getSimpleName();
    private boolean a;
    private d b;

    /* renamed from: c */
    private b f1414c;

    /* renamed from: d */
    private cn.hugo.android.scanner.a f1415d;
    private cn.hugo.android.scanner.f.c e;
    private ViewfinderView f;
    private cn.hugo.android.scanner.h.c g;
    private p h;

    /* renamed from: i */
    private boolean f1416i;
    private Collection<c.a.a.a> j;
    private Map<c.a.a.e, ?> k;
    private String l;
    private p m;
    private e n;

    /* loaded from: classes.dex */
    static class a extends Handler {
        private WeakReference<Activity> a;

        public a(Activity activity) {
            this.a = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Activity activity;
            String str;
            int i2 = message.what;
            if (i2 != 200) {
                if (i2 == 300) {
                    activity = this.a.get();
                    str = "解析图片失败";
                }
                super.handleMessage(message);
            }
            activity = this.a.get();
            str = "解析成功，结果为：" + message.obj;
            Toast.makeText(activity, str, 0).show();
            super.handleMessage(message);
        }
    }

    public CaptureActivity() {
        new a(this);
    }

    private void a(Bitmap bitmap, p pVar) {
        cn.hugo.android.scanner.h.c cVar = this.g;
        if (cVar == null) {
            this.m = pVar;
            return;
        }
        if (pVar != null) {
            this.m = pVar;
        }
        p pVar2 = this.m;
        if (pVar2 != null) {
            this.g.sendMessage(Message.obtain(cVar, R.id.decode_succeeded, pVar2));
        }
        this.m = null;
    }

    private void b() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("抱歉，Android相机出现问题。您可能需要重启设备。");
        builder.setPositiveButton("确定", new c(this));
        builder.setOnCancelListener(new c(this));
        builder.show();
    }

    private void c(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (this.e.f()) {
            Log.w(o, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            this.e.g(surfaceHolder);
            if (this.g == null) {
                this.g = new cn.hugo.android.scanner.h.c(this, this.j, this.k, this.l, this.e);
            }
            a(null, null);
        } catch (IOException e) {
            Log.w(o, e);
            b();
        } catch (RuntimeException e2) {
            Log.w(o, "Unexpected error initializing camera", e2);
            b();
        }
    }

    private void d() {
        this.f.setVisibility(0);
        this.h = null;
    }

    public void drawViewfinder() {
        this.f.f();
    }

    public cn.hugo.android.scanner.f.c getCameraManager() {
        return this.e;
    }

    public Handler getHandler() {
        return this.g;
    }

    public ViewfinderView getViewfinderView() {
        return this.f;
    }

    public void handleDecode(p pVar, Bitmap bitmap, float f) {
        this.b.e();
        this.h = pVar;
        this.f1414c.c();
        String qVar = u.k(pVar).toString();
        if (qVar.equals("")) {
            Toast.makeText(this, "扫描失败!", 0).show();
        } else {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", qVar);
            intent.putExtras(bundle);
            setResult(-1, intent);
        }
        finish();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        cn.hugo.android.scanner.f.c cVar;
        boolean z;
        int id = view.getId();
        if (id != R.id.capture_flashlight) {
            if (id != R.id.capture_scan_back) {
                return;
            }
            finish();
            return;
        }
        if (this.f1416i) {
            cVar = this.e;
            z = false;
        } else {
            cVar = this.e;
            z = true;
        }
        cVar.j(z);
        this.f1416i = z;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.ui_capture);
        this.a = false;
        this.b = new d(this);
        this.f1414c = new b(this);
        this.f1415d = new cn.hugo.android.scanner.a(this);
        findViewById(R.id.capture_scan_back).setOnClickListener(this);
        findViewById(R.id.capture_flashlight).setOnClickListener(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.b.h();
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            if (i2 != 27 && i2 != 80) {
                if (i2 == 24) {
                    this.e.m();
                } else if (i2 == 25) {
                    this.e.n();
                    return true;
                }
            }
            return true;
        }
        if (this.n == e.NONE && this.h != null) {
            restartPreviewAfterDelay(0L);
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity
    protected void onPause() {
        cn.hugo.android.scanner.h.c cVar = this.g;
        if (cVar != null) {
            cVar.a();
            this.g = null;
        }
        this.b.f();
        this.f1415d.b();
        this.f1414c.b();
        this.e.b();
        if (!this.a) {
            ((SurfaceView) findViewById(R.id.capture_preview_view)).getHolder().removeCallback(this);
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.e = new cn.hugo.android.scanner.f.c(getApplication());
        ViewfinderView viewfinderView = (ViewfinderView) findViewById(R.id.capture_viewfinder_view);
        this.f = viewfinderView;
        viewfinderView.setCameraManager(this.e);
        this.g = null;
        this.h = null;
        SurfaceHolder holder = ((SurfaceView) findViewById(R.id.capture_preview_view)).getHolder();
        if (this.a) {
            c(holder);
        } else {
            holder.setType(3);
            holder.addCallback(this);
        }
        this.f1414c.e();
        this.f1415d.a(this.e);
        this.b.g();
        this.n = e.NONE;
        this.j = null;
        this.l = null;
    }

    public void restartPreviewAfterDelay(long j) {
        cn.hugo.android.scanner.h.c cVar = this.g;
        if (cVar != null) {
            cVar.sendEmptyMessageDelayed(R.id.restart_preview, j);
        }
        d();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(o, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (this.a) {
            return;
        }
        this.a = true;
        c(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.a = false;
    }
}
