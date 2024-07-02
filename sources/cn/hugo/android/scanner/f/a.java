package cn.hugo.android.scanner.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class a implements Camera.AutoFocusCallback {
    private static final String e = a.class.getSimpleName();
    private static final Collection<String> f;
    private boolean a;
    private final boolean b;

    /* renamed from: c, reason: collision with root package name */
    private final Camera f1424c;

    /* renamed from: d, reason: collision with root package name */
    private AsyncTask<?, ?, ?> f1425d;

    /* loaded from: classes.dex */
    private final class b extends AsyncTask<Object, Object, Object> {
        private b() {
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException unused) {
            }
            synchronized (a.this) {
                if (a.this.a) {
                    a.this.b();
                }
            }
            return null;
        }
    }

    static {
        ArrayList arrayList = new ArrayList(2);
        f = arrayList;
        arrayList.add("auto");
        arrayList.add("macro");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, Camera camera) {
        this.f1424c = camera;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String focusMode = camera.getParameters().getFocusMode();
        boolean z = defaultSharedPreferences.getBoolean("preferences_auto_focus", true) && f.contains(focusMode);
        this.b = z;
        Log.i(e, "Current focus mode '" + focusMode + "'; use auto focus? " + z);
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void b() {
        if (this.b) {
            this.a = true;
            try {
                this.f1424c.autoFocus(this);
            } catch (RuntimeException e2) {
                Log.w(e, "Unexpected exception while focusing", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c() {
        if (this.b) {
            try {
                this.f1424c.cancelAutoFocus();
            } catch (RuntimeException e2) {
                Log.w(e, "Unexpected exception while cancelling focusing", e2);
            }
        }
        AsyncTask<?, ?, ?> asyncTask = this.f1425d;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.f1425d = null;
        }
        this.a = false;
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public synchronized void onAutoFocus(boolean z, Camera camera) {
        if (this.a) {
            b bVar = new b();
            this.f1425d = bVar;
            cn.hugo.android.scanner.g.b.a(bVar);
        }
    }
}
