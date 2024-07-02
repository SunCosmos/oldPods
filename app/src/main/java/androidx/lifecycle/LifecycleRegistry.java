package androidx.lifecycle;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class LifecycleRegistry extends Lifecycle {
    private FastSafeIterableMap<LifecycleObserver, a> a;
    private Lifecycle.State b;

    /* renamed from: c, reason: collision with root package name */
    private final WeakReference<LifecycleOwner> f811c;

    /* renamed from: d, reason: collision with root package name */
    private int f812d;
    private boolean e;
    private boolean f;
    private ArrayList<Lifecycle.State> g;
    private final boolean h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        Lifecycle.State a;
        LifecycleEventObserver b;

        a(LifecycleObserver lifecycleObserver, Lifecycle.State state) {
            this.b = Lifecycling.e(lifecycleObserver);
            this.a = state;
        }

        void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State targetState = event.getTargetState();
            this.a = LifecycleRegistry.f(this.a, targetState);
            this.b.onStateChanged(lifecycleOwner, event);
            this.a = targetState;
        }
    }

    public LifecycleRegistry(@NonNull LifecycleOwner lifecycleOwner) {
        this(lifecycleOwner, true);
    }

    private LifecycleRegistry(@NonNull LifecycleOwner lifecycleOwner, boolean z) {
        this.a = new FastSafeIterableMap<>();
        this.f812d = 0;
        this.e = false;
        this.f = false;
        this.g = new ArrayList<>();
        this.f811c = new WeakReference<>(lifecycleOwner);
        this.b = Lifecycle.State.INITIALIZED;
        this.h = z;
    }

    private void a(LifecycleOwner lifecycleOwner) {
        Iterator<Map.Entry<LifecycleObserver, a>> descendingIterator = this.a.descendingIterator();
        while (descendingIterator.hasNext() && !this.f) {
            Map.Entry<LifecycleObserver, a> next = descendingIterator.next();
            a value = next.getValue();
            while (value.a.compareTo(this.b) > 0 && !this.f && this.a.contains(next.getKey())) {
                Lifecycle.Event downFrom = Lifecycle.Event.downFrom(value.a);
                if (downFrom == null) {
                    throw new IllegalStateException("no event down from " + value.a);
                }
                i(downFrom.getTargetState());
                value.a(lifecycleOwner, downFrom);
                h();
            }
        }
    }

    private Lifecycle.State b(LifecycleObserver lifecycleObserver) {
        Map.Entry<LifecycleObserver, a> ceil = this.a.ceil(lifecycleObserver);
        Lifecycle.State state = null;
        Lifecycle.State state2 = ceil != null ? ceil.getValue().a : null;
        if (!this.g.isEmpty()) {
            state = this.g.get(r0.size() - 1);
        }
        return f(f(this.b, state2), state);
    }

    @SuppressLint({"RestrictedApi"})
    private void c(String str) {
        if (!this.h || ArchTaskExecutor.getInstance().isMainThread()) {
            return;
        }
        throw new IllegalStateException("Method " + str + " must be called on the main thread");
    }

    @NonNull
    @VisibleForTesting
    public static LifecycleRegistry createUnsafe(@NonNull LifecycleOwner lifecycleOwner) {
        return new LifecycleRegistry(lifecycleOwner, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d(LifecycleOwner lifecycleOwner) {
        SafeIterableMap<LifecycleObserver, a>.d iteratorWithAdditions = this.a.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext() && !this.f) {
            Map.Entry next = iteratorWithAdditions.next();
            a aVar = (a) next.getValue();
            while (aVar.a.compareTo(this.b) < 0 && !this.f && this.a.contains(next.getKey())) {
                i(aVar.a);
                Lifecycle.Event upFrom = Lifecycle.Event.upFrom(aVar.a);
                if (upFrom == null) {
                    throw new IllegalStateException("no event up from " + aVar.a);
                }
                aVar.a(lifecycleOwner, upFrom);
                h();
            }
        }
    }

    private boolean e() {
        if (this.a.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.a.eldest().getValue().a;
        Lifecycle.State state2 = this.a.newest().getValue().a;
        return state == state2 && this.b == state2;
    }

    static Lifecycle.State f(@NonNull Lifecycle.State state, @Nullable Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    private void g(Lifecycle.State state) {
        if (this.b == state) {
            return;
        }
        this.b = state;
        if (this.e || this.f812d != 0) {
            this.f = true;
            return;
        }
        this.e = true;
        j();
        this.e = false;
    }

    private void h() {
        this.g.remove(r0.size() - 1);
    }

    private void i(Lifecycle.State state) {
        this.g.add(state);
    }

    private void j() {
        LifecycleOwner lifecycleOwner = this.f811c.get();
        if (lifecycleOwner == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            boolean e = e();
            this.f = false;
            if (e) {
                return;
            }
            if (this.b.compareTo(this.a.eldest().getValue().a) < 0) {
                a(lifecycleOwner);
            }
            Map.Entry<LifecycleObserver, a> newest = this.a.newest();
            if (!this.f && newest != null && this.b.compareTo(newest.getValue().a) > 0) {
                d(lifecycleOwner);
            }
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public void addObserver(@NonNull LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner;
        c("addObserver");
        Lifecycle.State state = this.b;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        a aVar = new a(lifecycleObserver, state2);
        if (this.a.putIfAbsent(lifecycleObserver, aVar) == null && (lifecycleOwner = this.f811c.get()) != null) {
            boolean z = this.f812d != 0 || this.e;
            Lifecycle.State b = b(lifecycleObserver);
            this.f812d++;
            while (aVar.a.compareTo(b) < 0 && this.a.contains(lifecycleObserver)) {
                i(aVar.a);
                Lifecycle.Event upFrom = Lifecycle.Event.upFrom(aVar.a);
                if (upFrom == null) {
                    throw new IllegalStateException("no event up from " + aVar.a);
                }
                aVar.a(lifecycleOwner, upFrom);
                h();
                b = b(lifecycleObserver);
            }
            if (!z) {
                j();
            }
            this.f812d--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    @NonNull
    public Lifecycle.State getCurrentState() {
        return this.b;
    }

    public int getObserverCount() {
        c("getObserverCount");
        return this.a.size();
    }

    public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        c("handleLifecycleEvent");
        g(event.getTargetState());
    }

    @MainThread
    @Deprecated
    public void markState(@NonNull Lifecycle.State state) {
        c("markState");
        setCurrentState(state);
    }

    @Override // androidx.lifecycle.Lifecycle
    public void removeObserver(@NonNull LifecycleObserver lifecycleObserver) {
        c("removeObserver");
        this.a.remove(lifecycleObserver);
    }

    @MainThread
    public void setCurrentState(@NonNull Lifecycle.State state) {
        c("setCurrentState");
        g(state);
    }
}
