package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class ComputableLiveData<T> {
    final Executor a;
    final LiveData<T> b;

    /* renamed from: c, reason: collision with root package name */
    final AtomicBoolean f809c;

    /* renamed from: d, reason: collision with root package name */
    final AtomicBoolean f810d;

    @VisibleForTesting
    final Runnable e;

    @VisibleForTesting
    final Runnable f;

    /* loaded from: classes.dex */
    class a extends LiveData<T> {
        a() {
        }

        @Override // androidx.lifecycle.LiveData
        protected void f() {
            ComputableLiveData computableLiveData = ComputableLiveData.this;
            computableLiveData.a.execute(computableLiveData.e);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        @WorkerThread
        public void run() {
            do {
                boolean z = false;
                if (ComputableLiveData.this.f810d.compareAndSet(false, true)) {
                    Object obj = null;
                    boolean z2 = false;
                    while (ComputableLiveData.this.f809c.compareAndSet(true, false)) {
                        try {
                            obj = ComputableLiveData.this.a();
                            z2 = true;
                        } catch (Throwable th) {
                            ComputableLiveData.this.f810d.set(false);
                            throw th;
                        }
                    }
                    if (z2) {
                        ComputableLiveData.this.b.postValue(obj);
                    }
                    ComputableLiveData.this.f810d.set(false);
                    z = z2;
                }
                if (!z) {
                    return;
                }
            } while (ComputableLiveData.this.f809c.get());
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        @MainThread
        public void run() {
            boolean hasActiveObservers = ComputableLiveData.this.b.hasActiveObservers();
            if (ComputableLiveData.this.f809c.compareAndSet(false, true) && hasActiveObservers) {
                ComputableLiveData computableLiveData = ComputableLiveData.this;
                computableLiveData.a.execute(computableLiveData.e);
            }
        }
    }

    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    public ComputableLiveData(@NonNull Executor executor) {
        this.f809c = new AtomicBoolean(true);
        this.f810d = new AtomicBoolean(false);
        this.e = new b();
        this.f = new c();
        this.a = executor;
        this.b = new a();
    }

    @WorkerThread
    protected abstract T a();

    @NonNull
    public LiveData<T> getLiveData() {
        return this.b;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.f);
    }
}
