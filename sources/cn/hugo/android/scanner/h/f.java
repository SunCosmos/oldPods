package cn.hugo.android.scanner.h;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import c.a.a.s;
import cn.hugo.android.scanner.CaptureActivity;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f extends Thread {
    private final CaptureActivity a;
    private final Map<c.a.a.e, Object> b;

    /* renamed from: c, reason: collision with root package name */
    private Handler f1443c;

    /* renamed from: d, reason: collision with root package name */
    private final CountDownLatch f1444d = new CountDownLatch(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(CaptureActivity captureActivity, Collection<c.a.a.a> collection, Map<c.a.a.e, ?> map, String str, s sVar) {
        this.a = captureActivity;
        EnumMap enumMap = new EnumMap(c.a.a.e.class);
        this.b = enumMap;
        if (map != null) {
            enumMap.putAll(map);
        }
        if (collection == null || collection.isEmpty()) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(captureActivity);
            collection = EnumSet.noneOf(c.a.a.a.class);
            if (defaultSharedPreferences.getBoolean("preferences_decode_1D", false)) {
                collection.addAll(d.b);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_QR", false)) {
                collection.addAll(d.f1439c);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_Data_Matrix", false)) {
                collection.addAll(d.f1440d);
            }
        }
        enumMap.put((EnumMap) c.a.a.e.POSSIBLE_FORMATS, (c.a.a.e) collection);
        if (str != null) {
            enumMap.put((EnumMap) c.a.a.e.CHARACTER_SET, (c.a.a.e) str);
        }
        enumMap.put((EnumMap) c.a.a.e.NEED_RESULT_POINT_CALLBACK, (c.a.a.e) sVar);
        Log.i("DecodeThread", "Hints: " + enumMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Handler a() {
        try {
            this.f1444d.await();
        } catch (InterruptedException unused) {
        }
        return this.f1443c;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.f1443c = new e(this.a, this.b);
        this.f1444d.countDown();
        Looper.loop();
    }
}
