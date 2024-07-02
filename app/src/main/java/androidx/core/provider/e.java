package androidx.core.provider;

import android.os.Handler;
import android.os.Process;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
class e {

    /* loaded from: classes.dex */
    private static class a implements ThreadFactory {
        private String a;
        private int b;

        /* renamed from: androidx.core.provider.e$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C0019a extends Thread {
            private final int a;

            C0019a(Runnable runnable, String str, int i2) {
                super(runnable, str);
                this.a = i2;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(this.a);
                super.run();
            }
        }

        a(@NonNull String str, int i2) {
            this.a = str;
            this.b = i2;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new C0019a(runnable, this.a, this.b);
        }
    }

    /* loaded from: classes.dex */
    private static class b implements Executor {
        private final Handler a;

        b(@NonNull Handler handler) {
            this.a = (Handler) Preconditions.checkNotNull(handler);
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            if (this.a.post((Runnable) Preconditions.checkNotNull(runnable))) {
                return;
            }
            throw new RejectedExecutionException(this.a + " is shutting down");
        }
    }

    /* loaded from: classes.dex */
    private static class c<T> implements Runnable {

        @NonNull
        private Callable<T> a;

        @NonNull
        private Consumer<T> b;

        /* renamed from: c, reason: collision with root package name */
        @NonNull
        private Handler f587c;

        /* loaded from: classes.dex */
        class a implements Runnable {
            final /* synthetic */ Consumer a;
            final /* synthetic */ Object b;

            a(c cVar, Consumer consumer, Object obj) {
                this.a = consumer;
                this.b = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                this.a.accept(this.b);
            }
        }

        c(@NonNull Handler handler, @NonNull Callable<T> callable, @NonNull Consumer<T> consumer) {
            this.a = callable;
            this.b = consumer;
            this.f587c = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            T t;
            try {
                t = this.a.call();
            } catch (Exception unused) {
                t = null;
            }
            this.f587c.post(new a(this, this.b, t));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ThreadPoolExecutor a(@NonNull String str, int i2, @IntRange(from = 0) int i3) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, i3, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new a(str, i2));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor b(@NonNull Handler handler) {
        return new b(handler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void c(@NonNull Executor executor, @NonNull Callable<T> callable, @NonNull Consumer<T> consumer) {
        executor.execute(new c(androidx.core.provider.b.a(), callable, consumer));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T d(@NonNull ExecutorService executorService, @NonNull Callable<T> callable, @IntRange(from = 0) int i2) {
        try {
            return executorService.submit(callable).get(i2, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e2) {
            throw new RuntimeException(e2);
        } catch (TimeoutException unused) {
            throw new InterruptedException("timeout");
        }
    }
}
