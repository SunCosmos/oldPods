package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class LiveData<T> {
    static final Object k = new Object();
    final Object a;
    private SafeIterableMap<Observer<? super T>, LiveData<T>.c> b;

    /* renamed from: c, reason: collision with root package name */
    int f813c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f814d;
    private volatile Object e;
    volatile Object f;
    private int g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f815i;
    private final Runnable j;

    /* loaded from: classes.dex */
    class LifecycleBoundObserver extends LiveData<T>.c implements LifecycleEventObserver {

        @NonNull
        final LifecycleOwner e;

        LifecycleBoundObserver(@NonNull LifecycleOwner lifecycleOwner, Observer<? super T> observer) {
            super(observer);
            this.e = lifecycleOwner;
        }

        @Override // androidx.lifecycle.LiveData.c
        void h() {
            this.e.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean i(LifecycleOwner lifecycleOwner) {
            return this.e == lifecycleOwner;
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean j() {
            return this.e.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            Lifecycle.State currentState = this.e.getLifecycle().getCurrentState();
            if (currentState == Lifecycle.State.DESTROYED) {
                LiveData.this.removeObserver(this.a);
                return;
            }
            Lifecycle.State state = null;
            while (state != currentState) {
                g(j());
                state = currentState;
                currentState = this.e.getLifecycle().getCurrentState();
            }
        }
    }

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.a) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.k;
            }
            LiveData.this.setValue(obj);
        }
    }

    /* loaded from: classes.dex */
    private class b extends LiveData<T>.c {
        b(LiveData liveData, Observer<? super T> observer) {
            super(observer);
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean j() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class c {
        final Observer<? super T> a;
        boolean b;

        /* renamed from: c, reason: collision with root package name */
        int f816c = -1;

        c(Observer<? super T> observer) {
            this.a = observer;
        }

        void g(boolean z) {
            if (z == this.b) {
                return;
            }
            this.b = z;
            LiveData.this.b(z ? 1 : -1);
            if (this.b) {
                LiveData.this.d(this);
            }
        }

        void h() {
        }

        boolean i(LifecycleOwner lifecycleOwner) {
            return false;
        }

        abstract boolean j();
    }

    public LiveData() {
        this.a = new Object();
        this.b = new SafeIterableMap<>();
        this.f813c = 0;
        Object obj = k;
        this.f = obj;
        this.j = new a();
        this.e = obj;
        this.g = -1;
    }

    public LiveData(T t) {
        this.a = new Object();
        this.b = new SafeIterableMap<>();
        this.f813c = 0;
        this.f = k;
        this.j = new a();
        this.e = t;
        this.g = 0;
    }

    static void a(String str) {
        if (ArchTaskExecutor.getInstance().isMainThread()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void c(LiveData<T>.c cVar) {
        if (cVar.b) {
            if (!cVar.j()) {
                cVar.g(false);
                return;
            }
            int i2 = cVar.f816c;
            int i3 = this.g;
            if (i2 >= i3) {
                return;
            }
            cVar.f816c = i3;
            cVar.a.onChanged((Object) this.e);
        }
    }

    @MainThread
    void b(int i2) {
        int i3 = this.f813c;
        this.f813c = i2 + i3;
        if (this.f814d) {
            return;
        }
        this.f814d = true;
        while (true) {
            try {
                int i4 = this.f813c;
                if (i3 == i4) {
                    return;
                }
                boolean z = i3 == 0 && i4 > 0;
                boolean z2 = i3 > 0 && i4 == 0;
                if (z) {
                    f();
                } else if (z2) {
                    g();
                }
                i3 = i4;
            } finally {
                this.f814d = false;
            }
        }
    }

    void d(@Nullable LiveData<T>.c cVar) {
        if (this.h) {
            this.f815i = true;
            return;
        }
        this.h = true;
        do {
            this.f815i = false;
            if (cVar != null) {
                c(cVar);
                cVar = null;
            } else {
                SafeIterableMap<Observer<? super T>, LiveData<T>.c>.d iteratorWithAdditions = this.b.iteratorWithAdditions();
                while (iteratorWithAdditions.hasNext()) {
                    c((c) iteratorWithAdditions.next().getValue());
                    if (this.f815i) {
                        break;
                    }
                }
            }
        } while (this.f815i);
        this.h = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.g;
    }

    protected void f() {
    }

    protected void g() {
    }

    @Nullable
    public T getValue() {
        T t = (T) this.e;
        if (t != k) {
            return t;
        }
        return null;
    }

    public boolean hasActiveObservers() {
        return this.f813c > 0;
    }

    public boolean hasObservers() {
        return this.b.size() > 0;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner lifecycleOwner, @NonNull Observer<? super T> observer) {
        a("observe");
        if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(lifecycleOwner, observer);
        LiveData<T>.c putIfAbsent = this.b.putIfAbsent(observer, lifecycleBoundObserver);
        if (putIfAbsent != null && !putIfAbsent.i(lifecycleOwner)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (putIfAbsent != null) {
            return;
        }
        lifecycleOwner.getLifecycle().addObserver(lifecycleBoundObserver);
    }

    @MainThread
    public void observeForever(@NonNull Observer<? super T> observer) {
        a("observeForever");
        b bVar = new b(this, observer);
        LiveData<T>.c putIfAbsent = this.b.putIfAbsent(observer, bVar);
        if (putIfAbsent instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (putIfAbsent != null) {
            return;
        }
        bVar.g(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void postValue(T t) {
        boolean z;
        synchronized (this.a) {
            z = this.f == k;
            this.f = t;
        }
        if (z) {
            ArchTaskExecutor.getInstance().postToMainThread(this.j);
        }
    }

    @MainThread
    public void removeObserver(@NonNull Observer<? super T> observer) {
        a("removeObserver");
        LiveData<T>.c remove = this.b.remove(observer);
        if (remove == null) {
            return;
        }
        remove.h();
        remove.g(false);
    }

    @MainThread
    public void removeObservers(@NonNull LifecycleOwner lifecycleOwner) {
        a("removeObservers");
        Iterator<Map.Entry<Observer<? super T>, LiveData<T>.c>> it = this.b.iterator();
        while (it.hasNext()) {
            Map.Entry<Observer<? super T>, LiveData<T>.c> next = it.next();
            if (next.getValue().i(lifecycleOwner)) {
                removeObserver(next.getKey());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @MainThread
    public void setValue(T t) {
        a("setValue");
        this.g++;
        this.e = t;
        d(null);
    }
}
