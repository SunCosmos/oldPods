package c.b.a.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import cn.hugo.android.scanner.CaptureActivity;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class m {
    private Activity a;
    private Context b;

    public m(Context context, Activity activity) {
        this.a = null;
        this.b = null;
        this.b = context;
        this.a = activity;
    }

    public void a() {
        this.a.startActivityForResult(new Intent(this.b, (Class<?>) CaptureActivity.class), 1102);
    }

    public void b(int i2) {
        this.a.startActivityForResult(new Intent(this.b, (Class<?>) CaptureActivity.class), i2);
    }

    public Bitmap c(String str, int i2) {
        Hashtable hashtable = new Hashtable();
        hashtable.put(c.a.a.g.CHARACTER_SET, "utf-8");
        try {
            c.a.a.x.b a = new c.a.a.k().a(str, c.a.a.a.QR_CODE, i2, i2, hashtable);
            int j = a.j();
            int g = a.g();
            int[] iArr = new int[j * g];
            for (int i3 = 0; i3 < g; i3++) {
                for (int i4 = 0; i4 < j; i4++) {
                    if (a.d(i4, i3)) {
                        iArr[(i3 * j) + i4] = -16777216;
                    } else {
                        iArr[(i3 * j) + i4] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(j, g, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, j, 0, 0, j, g);
            return createBitmap;
        } catch (c.a.a.u e) {
            e.printStackTrace();
            return null;
        }
    }

    public c.a.a.p d(Bitmap bitmap) {
        return new cn.hugo.android.scanner.h.a(this.b).a(bitmap);
    }

    public c.a.a.p e(String str) {
        return new cn.hugo.android.scanner.h.a(this.b).a(cn.hugo.android.scanner.g.a.b(str));
    }
}
