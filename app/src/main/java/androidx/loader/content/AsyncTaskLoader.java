package androidx.loader.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.os.OperationCanceledException;
import androidx.core.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class AsyncTaskLoader<D> extends Loader<D> {
    private final Executor j;
    volatile AsyncTaskLoader<D>.a k;
    volatile AsyncTaskLoader<D>.a l;
    long m;
    long n;
    Handler o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class a extends ModernAsyncTask<Void, Void, D> implements Runnable {
        private final CountDownLatch j = new CountDownLatch(1);
        boolean k;

        a() {
        }

        @Override // androidx.loader.content.ModernAsyncTask
        protected void h(D d2) {
            try {
                AsyncTaskLoader.this.g(this, d2);
            } finally {
                this.j.countDown();
            }
        }

        @Override // androidx.loader.content.ModernAsyncTask
        protected void i(D d2) {
            try {
                AsyncTaskLoader.this.h(this, d2);
            } finally {
                this.j.countDown();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.loader.content.ModernAsyncTask
        /* renamed from: n, reason: merged with bridge method [inline-methods] */
        public D b(Void... voidArr) {
            try {
                return (D) AsyncTaskLoader.this.j();
            } catch (OperationCanceledException e) {
                if (f()) {
                    return null;
                }
                throw e;
            }
        }

        public void o() {
            try {
                this.j.await();
            } catch (InterruptedException unused) {
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.k = false;
            AsyncTaskLoader.this.i();
        }
    }

    public AsyncTaskLoader(@NonNull Context context) {
        this(context, ModernAsyncTask.h);
    }

    private AsyncTaskLoader(@NonNull Context context, @NonNull Executor executor) {
        super(context);
        this.n = -10000L;
        this.j = executor;
    }

    @Override // androidx.loader.content.Loader
    protected boolean b() {
        if (this.k == null) {
            return false;
        }
        if (!this.e) {
            this.h = true;
        }
        if (this.l != null) {
            if (this.k.k) {
                this.k.k = false;
                this.o.removeCallbacks(this.k);
            }
            this.k = null;
            return false;
        }
        if (this.k.k) {
            this.k.k = false;
            this.o.removeCallbacks(this.k);
            this.k = null;
            return false;
        }
        boolean a2 = this.k.a(false);
        if (a2) {
            this.l = this.k;
            cancelLoadInBackground();
        }
        this.k = null;
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.loader.content.Loader
    public void c() {
        super.c();
        cancelLoad();
        this.k = new a();
        i();
    }

    public void cancelLoadInBackground() {
    }

    @Override // androidx.loader.content.Loader
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.k != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.k);
            printWriter.print(" waiting=");
            printWriter.println(this.k.k);
        }
        if (this.l != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.l);
            printWriter.print(" waiting=");
            printWriter.println(this.l.k);
        }
        if (this.m != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.m, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.n, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    void g(AsyncTaskLoader<D>.a aVar, D d2) {
        onCanceled(d2);
        if (this.l == aVar) {
            rollbackContentChanged();
            this.n = SystemClock.uptimeMillis();
            this.l = null;
            deliverCancellation();
            i();
        }
    }

    void h(AsyncTaskLoader<D>.a aVar, D d2) {
        if (this.k != aVar) {
            g(aVar, d2);
            return;
        }
        if (isAbandoned()) {
            onCanceled(d2);
            return;
        }
        commitContentChanged();
        this.n = SystemClock.uptimeMillis();
        this.k = null;
        deliverResult(d2);
    }

    void i() {
        if (this.l != null || this.k == null) {
            return;
        }
        if (this.k.k) {
            this.k.k = false;
            this.o.removeCallbacks(this.k);
        }
        if (this.m <= 0 || SystemClock.uptimeMillis() >= this.n + this.m) {
            this.k.c(this.j, null);
        } else {
            this.k.k = true;
            this.o.postAtTime(this.k, this.n + this.m);
        }
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.l != null;
    }

    @Nullable
    protected D j() {
        return loadInBackground();
    }

    @Nullable
    public abstract D loadInBackground();

    public void onCanceled(@Nullable D d2) {
    }

    public void setUpdateThrottle(long j) {
        this.m = j;
        if (j != 0) {
            this.o = new Handler();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void waitForLoader() {
        AsyncTaskLoader<D>.a aVar = this.k;
        if (aVar != null) {
            aVar.o();
        }
    }
}
