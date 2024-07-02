package cn.hugo.android.scanner.h;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import c.a.a.j;
import c.a.a.m;
import cn.hugo.android.scanner.CaptureActivity;
import com.oldpods.app.R;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/* loaded from: classes.dex */
final class e extends Handler {

    /* renamed from: d, reason: collision with root package name */
    private static final String f1441d = e.class.getSimpleName();
    private final CaptureActivity a;
    private final j b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1442c = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(CaptureActivity captureActivity, Map<c.a.a.e, Object> map) {
        j jVar = new j();
        this.b = jVar;
        jVar.e(map);
        this.a = captureActivity;
    }

    private static void a(m mVar, Bundle bundle) {
        int[] i2 = mVar.i();
        int h = mVar.h();
        Bitmap createBitmap = Bitmap.createBitmap(i2, 0, h, h, mVar.g(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        bundle.putByteArray("barcode_bitmap", byteArrayOutputStream.toByteArray());
        bundle.putFloat("barcode_scaled_factor", h / mVar.d());
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(byte[] r9, int r10, int r11) {
        /*
            r8 = this;
            long r0 = java.lang.System.currentTimeMillis()
            int r2 = r9.length
            byte[] r2 = new byte[r2]
            r3 = 0
            r4 = 0
        L9:
            if (r4 >= r11) goto L21
            r5 = 0
        Lc:
            if (r5 >= r10) goto L1e
            int r6 = r5 * r11
            int r6 = r6 + r11
            int r6 = r6 - r4
            int r6 = r6 + (-1)
            int r7 = r4 * r10
            int r7 = r7 + r5
            r7 = r9[r7]
            r2[r6] = r7
            int r5 = r5 + 1
            goto Lc
        L1e:
            int r4 = r4 + 1
            goto L9
        L21:
            cn.hugo.android.scanner.CaptureActivity r9 = r8.a
            cn.hugo.android.scanner.f.c r9 = r9.getCameraManager()
            c.a.a.m r9 = r9.a(r2, r11, r10)
            if (r9 == 0) goto L4f
            c.a.a.c r10 = new c.a.a.c
            c.a.a.x.j r11 = new c.a.a.x.j
            r11.<init>(r9)
            r10.<init>(r11)
            c.a.a.j r11 = r8.b     // Catch: java.lang.Throwable -> L43 c.a.a.o -> L4a
            c.a.a.p r10 = r11.d(r10)     // Catch: java.lang.Throwable -> L43 c.a.a.o -> L4a
            c.a.a.j r11 = r8.b
            r11.b()
            goto L50
        L43:
            r9 = move-exception
            c.a.a.j r10 = r8.b
            r10.b()
            throw r9
        L4a:
            c.a.a.j r10 = r8.b
            r10.b()
        L4f:
            r10 = 0
        L50:
            cn.hugo.android.scanner.CaptureActivity r11 = r8.a
            android.os.Handler r11 = r11.getHandler()
            if (r10 == 0) goto L90
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r4 = cn.hugo.android.scanner.h.e.f1441d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Found barcode in "
            r5.append(r6)
            long r2 = r2 - r0
            r5.append(r2)
            java.lang.String r0 = " ms"
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            android.util.Log.d(r4, r0)
            if (r11 == 0) goto L9c
            r0 = 2131230855(0x7f080087, float:1.8077775E38)
            android.os.Message r10 = android.os.Message.obtain(r11, r0, r10)
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
            a(r9, r11)
            r10.setData(r11)
            r10.sendToTarget()
            goto L9c
        L90:
            if (r11 == 0) goto L9c
            r9 = 2131230854(0x7f080086, float:1.8077773E38)
            android.os.Message r9 = android.os.Message.obtain(r11, r9)
            r9.sendToTarget()
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.hugo.android.scanner.h.e.b(byte[], int, int):void");
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (this.f1442c) {
            int i2 = message.what;
            if (i2 == R.id.decode) {
                b((byte[]) message.obj, message.arg1, message.arg2);
            } else {
                if (i2 != R.id.quit) {
                    return;
                }
                this.f1442c = false;
                Looper.myLooper().quit();
            }
        }
    }
}
