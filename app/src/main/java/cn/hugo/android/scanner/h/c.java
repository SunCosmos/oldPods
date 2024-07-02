package cn.hugo.android.scanner.h;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import c.a.a.p;
import c.b.a.a.k;
import cn.hugo.android.scanner.CaptureActivity;
import com.oldpods.app.R;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class c extends Handler {
    private static final String e = c.class.getSimpleName();
    private final CaptureActivity a;
    private final f b;

    /* renamed from: c, reason: collision with root package name */
    private a f1435c;

    /* renamed from: d, reason: collision with root package name */
    private final cn.hugo.android.scanner.f.c f1436d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum a {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public c(CaptureActivity captureActivity, Collection<c.a.a.a> collection, Map<c.a.a.e, ?> map, String str, cn.hugo.android.scanner.f.c cVar) {
        this.a = captureActivity;
        f fVar = new f(captureActivity, collection, map, str, new cn.hugo.android.scanner.view.a(captureActivity.getViewfinderView()));
        this.b = fVar;
        fVar.start();
        this.f1435c = a.SUCCESS;
        this.f1436d = cVar;
        cVar.k();
        b();
    }

    private void b() {
        if (this.f1435c == a.SUCCESS) {
            this.f1435c = a.PREVIEW;
            this.f1436d.h(this.b.a(), R.id.decode);
            this.a.drawViewfinder();
        }
    }

    public void a() {
        this.f1435c = a.DONE;
        this.f1436d.l();
        Message.obtain(this.b.a(), R.id.quit).sendToTarget();
        try {
            this.b.join(500L);
        } catch (InterruptedException unused) {
        }
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        ActivityInfo activityInfo;
        Bitmap bitmap = null;
        r1 = null;
        String str = null;
        switch (message.what) {
            case R.id.decode_failed /* 2131230854 */:
                this.f1435c = a.PREVIEW;
                this.f1436d.h(this.b.a(), R.id.decode);
                return;
            case R.id.decode_succeeded /* 2131230855 */:
                Log.d(e, "Got decode succeeded message");
                this.f1435c = a.SUCCESS;
                Bundle data = message.getData();
                float f = 1.0f;
                if (data != null) {
                    byte[] byteArray = data.getByteArray("barcode_bitmap");
                    bitmap = byteArray != null ? BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, null).copy(Bitmap.Config.ARGB_8888, true) : null;
                    f = data.getFloat("barcode_scaled_factor");
                }
                this.a.handleDecode((p) message.obj, bitmap, f);
                return;
            case R.id.launch_product_query /* 2131230933 */:
                String str2 = e;
                Log.d(str2, "Got product query message");
                String str3 = (String) message.obj;
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(524288);
                intent.setData(k.c(this.a, str3));
                ResolveInfo resolveActivity = this.a.getPackageManager().resolveActivity(intent, 65536);
                if (resolveActivity != null && (activityInfo = resolveActivity.activityInfo) != null) {
                    str = activityInfo.packageName;
                    Log.d(str2, "Using browser in package " + str);
                }
                if ("com.android.browser".equals(str) || "com.android.chrome".equals(str)) {
                    intent.setPackage(str);
                    intent.addFlags(268435456);
                    intent.putExtra("com.android.browser.application_id", str);
                }
                try {
                    this.a.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                    Log.w(e, "Can't find anything to handle VIEW of URI " + str3);
                    return;
                }
            case R.id.restart_preview /* 2131231033 */:
                Log.d(e, "Got restart preview message");
                b();
                return;
            case R.id.return_scan_result /* 2131231034 */:
                Log.d(e, "Got return scan result message");
                this.a.setResult(-1, (Intent) message.obj);
                this.a.finish();
                return;
            default:
                return;
        }
    }
}
