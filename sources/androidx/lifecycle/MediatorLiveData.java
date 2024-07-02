package androidx.lifecycle;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class MediatorLiveData<T> extends MutableLiveData<T> {
    private SafeIterableMap<LiveData<?>, a<?>> l = new SafeIterableMap<>();

    /* loaded from: classes.dex */
    private static class a<V> implements Observer<V> {
        final LiveData<V> a;
        final Observer<? super V> b;

        /* renamed from: c, reason: collision with root package name */
        int f818c = -1;

        a(LiveData<V> liveData, Observer<? super V> observer) {
            this.a = liveData;
            this.b = observer;
        }

        void a() {
            this.a.observeForever(this);
        }

        void b() {
            this.a.removeObserver(this);
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable V v) {
            if (this.f818c != this.a.e()) {
                this.f818c = this.a.e();
                this.b.onChanged(v);
            }
        }
    }

    @MainThread
    public <S> void addSource(@NonNull LiveData<S> liveData, @NonNull Observer<? super S> observer) {
        a<?> aVar = new a<>(liveData, observer);
        a<?> putIfAbsent = this.l.putIfAbsent(liveData, aVar);
        if (putIfAbsent != null && putIfAbsent.b != observer) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (putIfAbsent == null && hasActiveObservers()) {
            aVar.a();
        }
    }

    @Override // androidx.lifecycle.LiveData
    @CallSuper
    protected void f() {
        Iterator<Map.Entry<LiveData<?>, a<?>>> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().getValue().a();
        }
    }

    @Override // androidx.lifecycle.LiveData
    @CallSuper
    protected void g() {
        Iterator<Map.Entry<LiveData<?>, a<?>>> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().getValue().b();
        }
    }

    @MainThread
    public <S> void removeSource(@NonNull LiveData<S> liveData) {
        a<?> remove = this.l.remove(liveData);
        if (remove != null) {
            remove.b();
        }
    }
}
