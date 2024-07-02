package androidx.loader.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
abstract class ModernAsyncTask<Params, Progress, Result> {
    private static final ThreadFactory f;
    private static final BlockingQueue<Runnable> g;
    public static final Executor h;

    /* renamed from: i, reason: collision with root package name */
    private static f f834i;
    private final g<Params, Result> a;
    private final FutureTask<Result> b;

    /* renamed from: c, reason: collision with root package name */
    private volatile Status f835c = Status.PENDING;

    /* renamed from: d, reason: collision with root package name */
    final AtomicBoolean f836d = new AtomicBoolean();
    final AtomicBoolean e = new AtomicBoolean();

    /* loaded from: classes.dex */
    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* loaded from: classes.dex */
    static class a implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.a.getAndIncrement());
        }
    }

    /* loaded from: classes.dex */
    class b extends g<Params, Result> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        public Result call() {
            ModernAsyncTask.this.e.set(true);
            Result result = null;
            try {
                Process.setThreadPriority(10);
                result = (Result) ModernAsyncTask.this.b(this.a);
                Binder.flushPendingCommands();
                return result;
            } finally {
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends FutureTask<Result> {
        c(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                ModernAsyncTask.this.m(get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (CancellationException unused) {
                ModernAsyncTask.this.m(null);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    }

    /* loaded from: classes.dex */
    static /* synthetic */ class d {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Status.values().length];
            a = iArr;
            try {
                iArr[Status.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Status.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class e<Data> {
        final ModernAsyncTask a;
        final Data[] b;

        e(ModernAsyncTask modernAsyncTask, Data... dataArr) {
            this.a = modernAsyncTask;
            this.b = dataArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f extends Handler {
        f() {
            super(Looper.getMainLooper());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            e eVar = (e) message.obj;
            int i2 = message.what;
            if (i2 == 1) {
                eVar.a.d(eVar.b[0]);
            } else {
                if (i2 != 2) {
                    return;
                }
                eVar.a.k(eVar.b);
            }
        }
    }

    /* loaded from: classes.dex */
    private static abstract class g<Params, Result> implements Callable<Result> {
        Params[] a;

        g() {
        }
    }

    static {
        a aVar = new a();
        f = aVar;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
        g = linkedBlockingQueue;
        h = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, linkedBlockingQueue, aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModernAsyncTask() {
        b bVar = new b();
        this.a = bVar;
        this.b = new c(bVar);
    }

    private static Handler e() {
        f fVar;
        synchronized (ModernAsyncTask.class) {
            if (f834i == null) {
                f834i = new f();
            }
            fVar = f834i;
        }
        return fVar;
    }

    public final boolean a(boolean z) {
        this.f836d.set(true);
        return this.b.cancel(z);
    }

    protected abstract Result b(Params... paramsArr);

    public final ModernAsyncTask<Params, Progress, Result> c(Executor executor, Params... paramsArr) {
        if (this.f835c == Status.PENDING) {
            this.f835c = Status.RUNNING;
            j();
            this.a.a = paramsArr;
            executor.execute(this.b);
            return this;
        }
        int i2 = d.a[this.f835c.ordinal()];
        if (i2 == 1) {
            throw new IllegalStateException("Cannot execute task: the task is already running.");
        }
        if (i2 != 2) {
            throw new IllegalStateException("We should never reach this state");
        }
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
    }

    void d(Result result) {
        if (f()) {
            h(result);
        } else {
            i(result);
        }
        this.f835c = Status.FINISHED;
    }

    public final boolean f() {
        return this.f836d.get();
    }

    protected void g() {
    }

    protected void h(Result result) {
        g();
    }

    protected void i(Result result) {
    }

    protected void j() {
    }

    protected void k(Progress... progressArr) {
    }

    Result l(Result result) {
        e().obtainMessage(1, new e(this, result)).sendToTarget();
        return result;
    }

    void m(Result result) {
        if (this.e.get()) {
            return;
        }
        l(result);
    }
}
