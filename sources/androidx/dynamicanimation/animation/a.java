package androidx.dynamicanimation.animation;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Choreographer;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a {
    public static final ThreadLocal<a> g = new ThreadLocal<>();

    /* renamed from: d, reason: collision with root package name */
    private c f698d;
    private final SimpleArrayMap<b, Long> a = new SimpleArrayMap<>();
    final ArrayList<b> b = new ArrayList<>();

    /* renamed from: c, reason: collision with root package name */
    private final C0026a f697c = new C0026a();
    long e = 0;
    private boolean f = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.dynamicanimation.animation.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0026a {
        C0026a() {
        }

        void a() {
            a.this.e = SystemClock.uptimeMillis();
            a aVar = a.this;
            aVar.c(aVar.e);
            if (a.this.b.size() > 0) {
                a.this.e().a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        boolean doAnimationFrame(long j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class c {
        final C0026a a;

        c(C0026a c0026a) {
            this.a = c0026a;
        }

        abstract void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d extends c {
        private final Runnable b;

        /* renamed from: c, reason: collision with root package name */
        private final Handler f699c;

        /* renamed from: d, reason: collision with root package name */
        long f700d;

        /* renamed from: androidx.dynamicanimation.animation.a$d$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0027a implements Runnable {
            RunnableC0027a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.f700d = SystemClock.uptimeMillis();
                d.this.a.a();
            }
        }

        d(C0026a c0026a) {
            super(c0026a);
            this.f700d = -1L;
            this.b = new RunnableC0027a();
            this.f699c = new Handler(Looper.myLooper());
        }

        @Override // androidx.dynamicanimation.animation.a.c
        void a() {
            this.f699c.postDelayed(this.b, Math.max(10 - (SystemClock.uptimeMillis() - this.f700d), 0L));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(16)
    /* loaded from: classes.dex */
    public static class e extends c {
        private final Choreographer b;

        /* renamed from: c, reason: collision with root package name */
        private final Choreographer.FrameCallback f701c;

        /* renamed from: androidx.dynamicanimation.animation.a$e$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class ChoreographerFrameCallbackC0028a implements Choreographer.FrameCallback {
            ChoreographerFrameCallbackC0028a() {
            }

            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                e.this.a.a();
            }
        }

        e(C0026a c0026a) {
            super(c0026a);
            this.b = Choreographer.getInstance();
            this.f701c = new ChoreographerFrameCallbackC0028a();
        }

        @Override // androidx.dynamicanimation.animation.a.c
        void a() {
            this.b.postFrameCallback(this.f701c);
        }
    }

    a() {
    }

    private void b() {
        if (this.f) {
            for (int size = this.b.size() - 1; size >= 0; size--) {
                if (this.b.get(size) == null) {
                    this.b.remove(size);
                }
            }
            this.f = false;
        }
    }

    public static a d() {
        ThreadLocal<a> threadLocal = g;
        if (threadLocal.get() == null) {
            threadLocal.set(new a());
        }
        return threadLocal.get();
    }

    private boolean f(b bVar, long j) {
        Long l = this.a.get(bVar);
        if (l == null) {
            return true;
        }
        if (l.longValue() >= j) {
            return false;
        }
        this.a.remove(bVar);
        return true;
    }

    public void a(b bVar, long j) {
        if (this.b.size() == 0) {
            e().a();
        }
        if (!this.b.contains(bVar)) {
            this.b.add(bVar);
        }
        if (j > 0) {
            this.a.put(bVar, Long.valueOf(SystemClock.uptimeMillis() + j));
        }
    }

    void c(long j) {
        long uptimeMillis = SystemClock.uptimeMillis();
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            b bVar = this.b.get(i2);
            if (bVar != null && f(bVar, uptimeMillis)) {
                bVar.doAnimationFrame(j);
            }
        }
        b();
    }

    c e() {
        if (this.f698d == null) {
            this.f698d = Build.VERSION.SDK_INT >= 16 ? new e(this.f697c) : new d(this.f697c);
        }
        return this.f698d;
    }

    public void g(b bVar) {
        this.a.remove(bVar);
        int indexOf = this.b.indexOf(bVar);
        if (indexOf >= 0) {
            this.b.set(indexOf, null);
            this.f = true;
        }
    }
}
