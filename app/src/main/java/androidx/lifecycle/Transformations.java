package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;

/* loaded from: classes.dex */
public class Transformations {

    /* JADX INFO: Add missing generic type declarations: [X] */
    /* loaded from: classes.dex */
    static class a<X> implements Observer<X> {
        final /* synthetic */ MediatorLiveData a;
        final /* synthetic */ Function b;

        a(MediatorLiveData mediatorLiveData, Function function) {
            this.a = mediatorLiveData;
            this.b = function;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable X x) {
            this.a.setValue(this.b.apply(x));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [X] */
    /* loaded from: classes.dex */
    static class b<X> implements Observer<X> {
        LiveData<Y> a;
        final /* synthetic */ Function b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ MediatorLiveData f824c;

        /* JADX INFO: Add missing generic type declarations: [Y] */
        /* loaded from: classes.dex */
        class a<Y> implements Observer<Y> {
            a() {
            }

            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Y y) {
                b.this.f824c.setValue(y);
            }
        }

        b(Function function, MediatorLiveData mediatorLiveData) {
            this.b = function;
            this.f824c = mediatorLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable X x) {
            LiveData<Y> liveData = (LiveData) this.b.apply(x);
            Object obj = this.a;
            if (obj == liveData) {
                return;
            }
            if (obj != null) {
                this.f824c.removeSource(obj);
            }
            this.a = liveData;
            if (liveData != 0) {
                this.f824c.addSource(liveData, new a());
            }
        }
    }

    private Transformations() {
    }

    @MainThread
    public static <X, Y> LiveData<Y> map(@NonNull LiveData<X> liveData, @NonNull Function<X, Y> function) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new a(mediatorLiveData, function));
        return mediatorLiveData;
    }

    @MainThread
    public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> liveData, @NonNull Function<X, LiveData<Y>> function) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new b(function, mediatorLiveData));
        return mediatorLiveData;
    }
}
