package androidx.core.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
@Deprecated
/* loaded from: classes.dex */
public class SelfDestructiveThread {

    @GuardedBy("mLock")
    private HandlerThread b;

    /* renamed from: c, reason: collision with root package name */
    @GuardedBy("mLock")
    private Handler f576c;
    private final int f;
    private final int g;
    private final String h;
    private final Object a = new Object();
    private Handler.Callback e = new a();

    /* renamed from: d, reason: collision with root package name */
    @GuardedBy("mLock")
    private int f577d = 0;

    /* loaded from: classes.dex */
    public interface ReplyCallback<T> {
        void onReply(T t);
    }

    /* loaded from: classes.dex */
    class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                SelfDestructiveThread.this.a();
                return true;
            }
            if (i2 != 1) {
                return true;
            }
            SelfDestructiveThread.this.b((Runnable) message.obj);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        final /* synthetic */ Callable a;
        final /* synthetic */ Handler b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ReplyCallback f578c;

        /* loaded from: classes.dex */
        class a implements Runnable {
            final /* synthetic */ Object a;

            a(Object obj) {
                this.a = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f578c.onReply(this.a);
            }
        }

        b(SelfDestructiveThread selfDestructiveThread, Callable callable, Handler handler, ReplyCallback replyCallback) {
            this.a = callable;
            this.b = handler;
            this.f578c = replyCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            try {
                obj = this.a.call();
            } catch (Exception unused) {
                obj = null;
            }
            this.b.post(new a(obj));
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        final /* synthetic */ AtomicReference a;
        final /* synthetic */ Callable b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f579c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f580d;
        final /* synthetic */ Condition e;

        c(SelfDestructiveThread selfDestructiveThread, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.a = atomicReference;
            this.b = callable;
            this.f579c = reentrantLock;
            this.f580d = atomicBoolean;
            this.e = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.a.set(this.b.call());
            } catch (Exception unused) {
            }
            this.f579c.lock();
            try {
                this.f580d.set(false);
                this.e.signal();
            } finally {
                this.f579c.unlock();
            }
        }
    }

    public SelfDestructiveThread(String str, int i2, int i3) {
        this.h = str;
        this.g = i2;
        this.f = i3;
    }

    private void c(Runnable runnable) {
        synchronized (this.a) {
            if (this.b == null) {
                HandlerThread handlerThread = new HandlerThread(this.h, this.g);
                this.b = handlerThread;
                handlerThread.start();
                this.f576c = new Handler(this.b.getLooper(), this.e);
                this.f577d++;
            }
            this.f576c.removeMessages(0);
            Handler handler = this.f576c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    void a() {
        synchronized (this.a) {
            if (this.f576c.hasMessages(1)) {
                return;
            }
            this.b.quit();
            this.b = null;
            this.f576c = null;
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.a) {
            this.f576c.removeMessages(0);
            Handler handler = this.f576c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f);
        }
    }

    @VisibleForTesting
    public int getGeneration() {
        int i2;
        synchronized (this.a) {
            i2 = this.f577d;
        }
        return i2;
    }

    @VisibleForTesting
    public boolean isRunning() {
        boolean z;
        synchronized (this.a) {
            z = this.b != null;
        }
        return z;
    }

    public <T> void postAndReply(Callable<T> callable, ReplyCallback<T> replyCallback) {
        c(new b(this, callable, androidx.core.provider.b.a(), replyCallback));
    }

    public <T> T postAndWait(Callable<T> callable, int i2) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition newCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new c(this, atomicReference, callable, reentrantLock, atomicBoolean, newCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i2);
            do {
                try {
                    nanos = newCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    return (T) atomicReference.get();
                }
            } while (nanos > 0);
            throw new InterruptedException("timeout");
        } finally {
            reentrantLock.unlock();
        }
    }
}
