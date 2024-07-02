package androidx.arch.core.executor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class ArchTaskExecutor extends TaskExecutor {

    /* renamed from: c, reason: collision with root package name */
    private static volatile ArchTaskExecutor f238c;

    /* renamed from: d, reason: collision with root package name */
    @NonNull
    private static final Executor f239d = new a();

    @NonNull
    private static final Executor e = new b();

    @NonNull
    private TaskExecutor a;

    @NonNull
    private TaskExecutor b;

    /* loaded from: classes.dex */
    static class a implements Executor {
        a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            ArchTaskExecutor.getInstance().postToMainThread(runnable);
        }
    }

    /* loaded from: classes.dex */
    static class b implements Executor {
        b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            ArchTaskExecutor.getInstance().executeOnDiskIO(runnable);
        }
    }

    private ArchTaskExecutor() {
        DefaultTaskExecutor defaultTaskExecutor = new DefaultTaskExecutor();
        this.b = defaultTaskExecutor;
        this.a = defaultTaskExecutor;
    }

    @NonNull
    public static Executor getIOThreadExecutor() {
        return e;
    }

    @NonNull
    public static ArchTaskExecutor getInstance() {
        if (f238c != null) {
            return f238c;
        }
        synchronized (ArchTaskExecutor.class) {
            if (f238c == null) {
                f238c = new ArchTaskExecutor();
            }
        }
        return f238c;
    }

    @NonNull
    public static Executor getMainThreadExecutor() {
        return f239d;
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void executeOnDiskIO(Runnable runnable) {
        this.a.executeOnDiskIO(runnable);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public boolean isMainThread() {
        return this.a.isMainThread();
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void postToMainThread(Runnable runnable) {
        this.a.postToMainThread(runnable);
    }

    public void setDelegate(@Nullable TaskExecutor taskExecutor) {
        if (taskExecutor == null) {
            taskExecutor = this.b;
        }
        this.a = taskExecutor;
    }
}
