package cn.hugo.android.scanner;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d {
    private static final String e = "d";
    private final Activity a;
    private final BroadcastReceiver b = new c();

    /* renamed from: c, reason: collision with root package name */
    private boolean f1420c = false;

    /* renamed from: d, reason: collision with root package name */
    private AsyncTask<?, ?, ?> f1421d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class b extends AsyncTask<Object, Object, Object> {
        private b() {
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(300000L);
                Log.i(d.e, "Finishing activity due to inactivity");
                d.this.a.finish();
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    private final class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                if (intent.getIntExtra("plugged", -1) <= 0) {
                    d.this.e();
                } else {
                    d.this.d();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Activity activity) {
        this.a = activity;
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void d() {
        AsyncTask<?, ?, ?> asyncTask = this.f1421d;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.f1421d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void e() {
        d();
        b bVar = new b();
        this.f1421d = bVar;
        cn.hugo.android.scanner.g.b.a(bVar);
    }

    public synchronized void f() {
        d();
        if (this.f1420c) {
            this.a.unregisterReceiver(this.b);
            this.f1420c = false;
        } else {
            Log.w(e, "PowerStatusReceiver was never registered?");
        }
    }

    public synchronized void g() {
        if (this.f1420c) {
            Log.w(e, "PowerStatusReceiver was already registered?");
        } else {
            this.a.registerReceiver(this.b, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.f1420c = true;
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        d();
    }
}
