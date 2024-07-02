package androidx.asynclayoutinflater.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.core.util.Pools;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes.dex */
public final class AsyncLayoutInflater {
    LayoutInflater a;

    /* renamed from: d, reason: collision with root package name */
    private Handler.Callback f247d = new a();
    Handler b = new Handler(this.f247d);

    /* renamed from: c, reason: collision with root package name */
    d f246c = d.b();

    /* loaded from: classes.dex */
    public interface OnInflateFinishedListener {
        void onInflateFinished(@NonNull View view, @LayoutRes int i2, @Nullable ViewGroup viewGroup);
    }

    /* loaded from: classes.dex */
    class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            c cVar = (c) message.obj;
            if (cVar.f249d == null) {
                cVar.f249d = AsyncLayoutInflater.this.a.inflate(cVar.f248c, cVar.b, false);
            }
            cVar.e.onInflateFinished(cVar.f249d, cVar.f248c, cVar.b);
            AsyncLayoutInflater.this.f246c.d(cVar);
            return true;
        }
    }

    /* loaded from: classes.dex */
    private static class b extends LayoutInflater {
        private static final String[] a = {"android.widget.", "android.webkit.", "android.app."};

        b(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public LayoutInflater cloneInContext(Context context) {
            return new b(context);
        }

        @Override // android.view.LayoutInflater
        protected View onCreateView(String str, AttributeSet attributeSet) {
            View createView;
            for (String str2 : a) {
                try {
                    createView = createView(str, str2, attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {
        AsyncLayoutInflater a;
        ViewGroup b;

        /* renamed from: c, reason: collision with root package name */
        int f248c;

        /* renamed from: d, reason: collision with root package name */
        View f249d;
        OnInflateFinishedListener e;

        c() {
        }
    }

    /* loaded from: classes.dex */
    private static class d extends Thread {

        /* renamed from: c, reason: collision with root package name */
        private static final d f250c;
        private ArrayBlockingQueue<c> a = new ArrayBlockingQueue<>(10);
        private Pools.SynchronizedPool<c> b = new Pools.SynchronizedPool<>(10);

        static {
            d dVar = new d();
            f250c = dVar;
            dVar.start();
        }

        private d() {
        }

        public static d b() {
            return f250c;
        }

        public void a(c cVar) {
            try {
                this.a.put(cVar);
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to enqueue async inflate request", e);
            }
        }

        public c c() {
            c acquire = this.b.acquire();
            return acquire == null ? new c() : acquire;
        }

        public void d(c cVar) {
            cVar.e = null;
            cVar.a = null;
            cVar.b = null;
            cVar.f248c = 0;
            cVar.f249d = null;
            this.b.release(cVar);
        }

        public void e() {
            try {
                c take = this.a.take();
                try {
                    take.f249d = take.a.a.inflate(take.f248c, take.b, false);
                } catch (RuntimeException e) {
                    Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", e);
                }
                Message.obtain(take.a.b, 0, take).sendToTarget();
            } catch (InterruptedException e2) {
                Log.w("AsyncLayoutInflater", e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                e();
            }
        }
    }

    public AsyncLayoutInflater(@NonNull Context context) {
        this.a = new b(context);
    }

    @UiThread
    public void inflate(@LayoutRes int i2, @Nullable ViewGroup viewGroup, @NonNull OnInflateFinishedListener onInflateFinishedListener) {
        if (onInflateFinishedListener == null) {
            throw new NullPointerException("callback argument may not be null!");
        }
        c c2 = this.f246c.c();
        c2.a = this;
        c2.f248c = i2;
        c2.b = viewGroup;
        c2.e = onInflateFinishedListener;
        this.f246c.a(c2);
    }
}
