package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class a {
    private static a e;

    @NonNull
    private final Object a = new Object();

    @NonNull
    private final Handler b = new Handler(Looper.getMainLooper(), new C0059a());

    /* renamed from: c, reason: collision with root package name */
    @Nullable
    private c f1700c;

    /* renamed from: d, reason: collision with root package name */
    @Nullable
    private c f1701d;

    /* renamed from: com.google.android.material.snackbar.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0059a implements Handler.Callback {
        C0059a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            if (message.what != 0) {
                return false;
            }
            a.this.d((c) message.obj);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        void a(int i2);

        void show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        @NonNull
        final WeakReference<b> a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        boolean f1702c;

        c(int i2, b bVar) {
            this.a = new WeakReference<>(bVar);
            this.b = i2;
        }

        boolean a(@Nullable b bVar) {
            return bVar != null && this.a.get() == bVar;
        }
    }

    private a() {
    }

    private boolean a(@NonNull c cVar, int i2) {
        b bVar = cVar.a.get();
        if (bVar == null) {
            return false;
        }
        this.b.removeCallbacksAndMessages(cVar);
        bVar.a(i2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a c() {
        if (e == null) {
            e = new a();
        }
        return e;
    }

    private boolean g(b bVar) {
        c cVar = this.f1700c;
        return cVar != null && cVar.a(bVar);
    }

    private boolean h(b bVar) {
        c cVar = this.f1701d;
        return cVar != null && cVar.a(bVar);
    }

    private void m(@NonNull c cVar) {
        int i2 = cVar.b;
        if (i2 == -2) {
            return;
        }
        if (i2 <= 0) {
            i2 = i2 == -1 ? 1500 : 2750;
        }
        this.b.removeCallbacksAndMessages(cVar);
        Handler handler = this.b;
        handler.sendMessageDelayed(Message.obtain(handler, 0, cVar), i2);
    }

    private void o() {
        c cVar = this.f1701d;
        if (cVar != null) {
            this.f1700c = cVar;
            this.f1701d = null;
            b bVar = cVar.a.get();
            if (bVar != null) {
                bVar.show();
            } else {
                this.f1700c = null;
            }
        }
    }

    public void b(b bVar, int i2) {
        c cVar;
        synchronized (this.a) {
            if (g(bVar)) {
                cVar = this.f1700c;
            } else if (h(bVar)) {
                cVar = this.f1701d;
            }
            a(cVar, i2);
        }
    }

    void d(@NonNull c cVar) {
        synchronized (this.a) {
            if (this.f1700c == cVar || this.f1701d == cVar) {
                a(cVar, 2);
            }
        }
    }

    public boolean e(b bVar) {
        boolean g;
        synchronized (this.a) {
            g = g(bVar);
        }
        return g;
    }

    public boolean f(b bVar) {
        boolean z;
        synchronized (this.a) {
            z = g(bVar) || h(bVar);
        }
        return z;
    }

    public void i(b bVar) {
        synchronized (this.a) {
            if (g(bVar)) {
                this.f1700c = null;
                if (this.f1701d != null) {
                    o();
                }
            }
        }
    }

    public void j(b bVar) {
        synchronized (this.a) {
            if (g(bVar)) {
                m(this.f1700c);
            }
        }
    }

    public void k(b bVar) {
        synchronized (this.a) {
            if (g(bVar)) {
                c cVar = this.f1700c;
                if (!cVar.f1702c) {
                    cVar.f1702c = true;
                    this.b.removeCallbacksAndMessages(cVar);
                }
            }
        }
    }

    public void l(b bVar) {
        synchronized (this.a) {
            if (g(bVar)) {
                c cVar = this.f1700c;
                if (cVar.f1702c) {
                    cVar.f1702c = false;
                    m(cVar);
                }
            }
        }
    }

    public void n(int i2, b bVar) {
        synchronized (this.a) {
            if (g(bVar)) {
                c cVar = this.f1700c;
                cVar.b = i2;
                this.b.removeCallbacksAndMessages(cVar);
                m(this.f1700c);
                return;
            }
            if (h(bVar)) {
                this.f1701d.b = i2;
            } else {
                this.f1701d = new c(i2, bVar);
            }
            c cVar2 = this.f1700c;
            if (cVar2 == null || !a(cVar2, 4)) {
                this.f1700c = null;
                o();
            }
        }
    }
}
