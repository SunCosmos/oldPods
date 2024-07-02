package androidx.recyclerview.widget;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class g<T> implements ThreadUtil<T> {

    /* loaded from: classes.dex */
    class a implements ThreadUtil.MainThreadCallback<T> {
        final c a = new c();
        private final Handler b = new Handler(Looper.getMainLooper());

        /* renamed from: c, reason: collision with root package name */
        private Runnable f1011c = new RunnableC0037a();

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ThreadUtil.MainThreadCallback f1012d;

        /* renamed from: androidx.recyclerview.widget.g$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0037a implements Runnable {
            RunnableC0037a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    d a = a.this.a.a();
                    if (a == null) {
                        return;
                    }
                    int i2 = a.b;
                    if (i2 == 1) {
                        a.this.f1012d.updateItemCount(a.f1016c, a.f1017d);
                    } else if (i2 == 2) {
                        a.this.f1012d.addTile(a.f1016c, (TileList.Tile) a.h);
                    } else if (i2 != 3) {
                        Log.e("ThreadUtil", "Unsupported message, what=" + a.b);
                    } else {
                        a.this.f1012d.removeTile(a.f1016c, a.f1017d);
                    }
                }
            }
        }

        a(g gVar, ThreadUtil.MainThreadCallback mainThreadCallback) {
            this.f1012d = mainThreadCallback;
        }

        private void a(d dVar) {
            this.a.c(dVar);
            this.b.post(this.f1011c);
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void addTile(int i2, TileList.Tile<T> tile) {
            a(d.c(2, i2, tile));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void removeTile(int i2, int i3) {
            a(d.a(3, i2, i3));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void updateItemCount(int i2, int i3) {
            a(d.a(1, i2, i3));
        }
    }

    /* loaded from: classes.dex */
    class b implements ThreadUtil.BackgroundCallback<T> {
        final c a = new c();
        private final Executor b = AsyncTask.THREAD_POOL_EXECUTOR;

        /* renamed from: c, reason: collision with root package name */
        AtomicBoolean f1013c = new AtomicBoolean(false);

        /* renamed from: d, reason: collision with root package name */
        private Runnable f1014d = new a();
        final /* synthetic */ ThreadUtil.BackgroundCallback e;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    d a = b.this.a.a();
                    if (a == null) {
                        b.this.f1013c.set(false);
                        return;
                    }
                    int i2 = a.b;
                    if (i2 == 1) {
                        b.this.a.b(1);
                        b.this.e.refresh(a.f1016c);
                    } else if (i2 == 2) {
                        b.this.a.b(2);
                        b.this.a.b(3);
                        b.this.e.updateRange(a.f1016c, a.f1017d, a.e, a.f, a.g);
                    } else if (i2 == 3) {
                        b.this.e.loadTile(a.f1016c, a.f1017d);
                    } else if (i2 != 4) {
                        Log.e("ThreadUtil", "Unsupported message, what=" + a.b);
                    } else {
                        b.this.e.recycleTile((TileList.Tile) a.h);
                    }
                }
            }
        }

        b(g gVar, ThreadUtil.BackgroundCallback backgroundCallback) {
            this.e = backgroundCallback;
        }

        private void a() {
            if (this.f1013c.compareAndSet(false, true)) {
                this.b.execute(this.f1014d);
            }
        }

        private void b(d dVar) {
            this.a.c(dVar);
            a();
        }

        private void c(d dVar) {
            this.a.d(dVar);
            a();
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void loadTile(int i2, int i3) {
            b(d.a(3, i2, i3));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void recycleTile(TileList.Tile<T> tile) {
            b(d.c(4, 0, tile));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void refresh(int i2) {
            c(d.c(1, i2, null));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void updateRange(int i2, int i3, int i4, int i5, int i6) {
            c(d.b(2, i2, i3, i4, i5, i6, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        private d a;

        c() {
        }

        synchronized d a() {
            d dVar = this.a;
            if (dVar == null) {
                return null;
            }
            this.a = dVar.a;
            return dVar;
        }

        synchronized void b(int i2) {
            d dVar;
            while (true) {
                dVar = this.a;
                if (dVar == null || dVar.b != i2) {
                    break;
                }
                this.a = dVar.a;
                dVar.d();
            }
            if (dVar != null) {
                d dVar2 = dVar.a;
                while (dVar2 != null) {
                    d dVar3 = dVar2.a;
                    if (dVar2.b == i2) {
                        dVar.a = dVar3;
                        dVar2.d();
                    } else {
                        dVar = dVar2;
                    }
                    dVar2 = dVar3;
                }
            }
        }

        synchronized void c(d dVar) {
            d dVar2 = this.a;
            if (dVar2 == null) {
                this.a = dVar;
                return;
            }
            while (true) {
                d dVar3 = dVar2.a;
                if (dVar3 == null) {
                    dVar2.a = dVar;
                    return;
                }
                dVar2 = dVar3;
            }
        }

        synchronized void d(d dVar) {
            dVar.a = this.a;
            this.a = dVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: i, reason: collision with root package name */
        private static d f1015i;
        private static final Object j = new Object();
        d a;
        public int b;

        /* renamed from: c, reason: collision with root package name */
        public int f1016c;

        /* renamed from: d, reason: collision with root package name */
        public int f1017d;
        public int e;
        public int f;
        public int g;
        public Object h;

        d() {
        }

        static d a(int i2, int i3, int i4) {
            return b(i2, i3, i4, 0, 0, 0, null);
        }

        static d b(int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
            d dVar;
            synchronized (j) {
                dVar = f1015i;
                if (dVar == null) {
                    dVar = new d();
                } else {
                    f1015i = dVar.a;
                    dVar.a = null;
                }
                dVar.b = i2;
                dVar.f1016c = i3;
                dVar.f1017d = i4;
                dVar.e = i5;
                dVar.f = i6;
                dVar.g = i7;
                dVar.h = obj;
            }
            return dVar;
        }

        static d c(int i2, int i3, Object obj) {
            return b(i2, i3, 0, 0, 0, 0, obj);
        }

        void d() {
            this.a = null;
            this.g = 0;
            this.f = 0;
            this.e = 0;
            this.f1017d = 0;
            this.f1016c = 0;
            this.b = 0;
            this.h = null;
            synchronized (j) {
                d dVar = f1015i;
                if (dVar != null) {
                    this.a = dVar;
                }
                f1015i = this;
            }
        }
    }

    @Override // androidx.recyclerview.widget.ThreadUtil
    public ThreadUtil.BackgroundCallback<T> a(ThreadUtil.BackgroundCallback<T> backgroundCallback) {
        return new b(this, backgroundCallback);
    }

    @Override // androidx.recyclerview.widget.ThreadUtil
    public ThreadUtil.MainThreadCallback<T> b(ThreadUtil.MainThreadCallback<T> mainThreadCallback) {
        return new a(this, mainThreadCallback);
    }
}
