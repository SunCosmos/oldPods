package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class LoaderManagerImpl extends LoaderManager {

    /* renamed from: c, reason: collision with root package name */
    static boolean f827c = false;

    @NonNull
    private final LifecycleOwner a;

    @NonNull
    private final b b;

    /* loaded from: classes.dex */
    public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {
        private final int l;

        @Nullable
        private final Bundle m;

        @NonNull
        private final Loader<D> n;
        private LifecycleOwner o;
        private a<D> p;
        private Loader<D> q;

        LoaderInfo(int i2, @Nullable Bundle bundle, @NonNull Loader<D> loader, @Nullable Loader<D> loader2) {
            this.l = i2;
            this.m = bundle;
            this.n = loader;
            this.q = loader2;
            loader.registerListener(i2, this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.l);
            printWriter.print(" mArgs=");
            printWriter.println(this.m);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.n);
            this.n.dump(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.p != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.p);
                this.p.a(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(i().dataToString(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }

        @Override // androidx.lifecycle.LiveData
        protected void f() {
            if (LoaderManagerImpl.f827c) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.n.startLoading();
        }

        @Override // androidx.lifecycle.LiveData
        protected void g() {
            if (LoaderManagerImpl.f827c) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.n.stopLoading();
        }

        @MainThread
        Loader<D> h(boolean z) {
            if (LoaderManagerImpl.f827c) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.n.cancelLoad();
            this.n.abandon();
            a<D> aVar = this.p;
            if (aVar != null) {
                removeObserver(aVar);
                if (z) {
                    aVar.c();
                }
            }
            this.n.unregisterListener(this);
            if ((aVar == null || aVar.b()) && !z) {
                return this.n;
            }
            this.n.reset();
            return this.q;
        }

        @NonNull
        Loader<D> i() {
            return this.n;
        }

        boolean j() {
            a<D> aVar;
            return (!hasActiveObservers() || (aVar = this.p) == null || aVar.b()) ? false : true;
        }

        void k() {
            LifecycleOwner lifecycleOwner = this.o;
            a<D> aVar = this.p;
            if (lifecycleOwner == null || aVar == null) {
                return;
            }
            super.removeObserver(aVar);
            observe(lifecycleOwner, aVar);
        }

        @NonNull
        @MainThread
        Loader<D> l(@NonNull LifecycleOwner lifecycleOwner, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            a<D> aVar = new a<>(this.n, loaderCallbacks);
            observe(lifecycleOwner, aVar);
            a<D> aVar2 = this.p;
            if (aVar2 != null) {
                removeObserver(aVar2);
            }
            this.o = lifecycleOwner;
            this.p = aVar;
            return this.n;
        }

        @Override // androidx.loader.content.Loader.OnLoadCompleteListener
        public void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d2) {
            if (LoaderManagerImpl.f827c) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(d2);
                return;
            }
            if (LoaderManagerImpl.f827c) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            postValue(d2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void removeObserver(@NonNull Observer<? super D> observer) {
            super.removeObserver(observer);
            this.o = null;
            this.p = null;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(D d2) {
            super.setValue(d2);
            Loader<D> loader = this.q;
            if (loader != null) {
                loader.reset();
                this.q = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.l);
            sb.append(" : ");
            DebugUtils.buildShortClassTag(this.n, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a<D> implements Observer<D> {

        @NonNull
        private final Loader<D> a;

        @NonNull
        private final LoaderManager.LoaderCallbacks<D> b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f828c = false;

        a(@NonNull Loader<D> loader, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            this.a = loader;
            this.b = loaderCallbacks;
        }

        public void a(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.f828c);
        }

        boolean b() {
            return this.f828c;
        }

        @MainThread
        void c() {
            if (this.f828c) {
                if (LoaderManagerImpl.f827c) {
                    Log.v("LoaderManager", "  Resetting: " + this.a);
                }
                this.b.onLoaderReset(this.a);
            }
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable D d2) {
            if (LoaderManagerImpl.f827c) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.a + ": " + this.a.dataToString(d2));
            }
            this.b.onLoadFinished(this.a, d2);
            this.f828c = true;
        }

        public String toString() {
            return this.b.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends ViewModel {
        private static final ViewModelProvider.Factory e = new a();

        /* renamed from: c, reason: collision with root package name */
        private SparseArrayCompat<LoaderInfo> f829c = new SparseArrayCompat<>();

        /* renamed from: d, reason: collision with root package name */
        private boolean f830d = false;

        /* loaded from: classes.dex */
        static class a implements ViewModelProvider.Factory {
            a() {
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            @NonNull
            public <T extends ViewModel> T create(@NonNull Class<T> cls) {
                return new b();
            }
        }

        b() {
        }

        @NonNull
        static b h(ViewModelStore viewModelStore) {
            return (b) new ViewModelProvider(viewModelStore, e).get(b.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.lifecycle.ViewModel
        public void d() {
            super.d();
            int size = this.f829c.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f829c.valueAt(i2).h(true);
            }
            this.f829c.clear();
        }

        public void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f829c.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i2 = 0; i2 < this.f829c.size(); i2++) {
                    LoaderInfo valueAt = this.f829c.valueAt(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f829c.keyAt(i2));
                    printWriter.print(": ");
                    printWriter.println(valueAt.toString());
                    valueAt.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        void g() {
            this.f830d = false;
        }

        <D> LoaderInfo<D> i(int i2) {
            return this.f829c.get(i2);
        }

        boolean j() {
            int size = this.f829c.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.f829c.valueAt(i2).j()) {
                    return true;
                }
            }
            return false;
        }

        boolean k() {
            return this.f830d;
        }

        void l() {
            int size = this.f829c.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f829c.valueAt(i2).k();
            }
        }

        void m(int i2, @NonNull LoaderInfo loaderInfo) {
            this.f829c.put(i2, loaderInfo);
        }

        void n(int i2) {
            this.f829c.remove(i2);
        }

        void o() {
            this.f830d = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoaderManagerImpl(@NonNull LifecycleOwner lifecycleOwner, @NonNull ViewModelStore viewModelStore) {
        this.a = lifecycleOwner;
        this.b = b.h(viewModelStore);
    }

    @NonNull
    @MainThread
    private <D> Loader<D> a(int i2, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks, @Nullable Loader<D> loader) {
        try {
            this.b.o();
            Loader<D> onCreateLoader = loaderCallbacks.onCreateLoader(i2, bundle);
            if (onCreateLoader == null) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
            }
            if (onCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(onCreateLoader.getClass().getModifiers())) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + onCreateLoader);
            }
            LoaderInfo loaderInfo = new LoaderInfo(i2, bundle, onCreateLoader, loader);
            if (f827c) {
                Log.v("LoaderManager", "  Created new loader " + loaderInfo);
            }
            this.b.m(i2, loaderInfo);
            this.b.g();
            return loaderInfo.l(this.a, loaderCallbacks);
        } catch (Throwable th) {
            this.b.g();
            throw th;
        }
    }

    @Override // androidx.loader.app.LoaderManager
    @MainThread
    public void destroyLoader(int i2) {
        if (this.b.k()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        if (f827c) {
            Log.v("LoaderManager", "destroyLoader in " + this + " of " + i2);
        }
        LoaderInfo i3 = this.b.i(i2);
        if (i3 != null) {
            i3.h(true);
            this.b.n(i2);
        }
    }

    @Override // androidx.loader.app.LoaderManager
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.b.f(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.loader.app.LoaderManager
    @Nullable
    public <D> Loader<D> getLoader(int i2) {
        if (this.b.k()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        LoaderInfo<D> i3 = this.b.i(i2);
        if (i3 != null) {
            return i3.i();
        }
        return null;
    }

    @Override // androidx.loader.app.LoaderManager
    public boolean hasRunningLoaders() {
        return this.b.j();
    }

    @Override // androidx.loader.app.LoaderManager
    @NonNull
    @MainThread
    public <D> Loader<D> initLoader(int i2, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (this.b.k()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        LoaderInfo<D> i3 = this.b.i(i2);
        if (f827c) {
            Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
        }
        if (i3 == null) {
            return a(i2, bundle, loaderCallbacks, null);
        }
        if (f827c) {
            Log.v("LoaderManager", "  Re-using existing loader " + i3);
        }
        return i3.l(this.a, loaderCallbacks);
    }

    @Override // androidx.loader.app.LoaderManager
    public void markForRedelivery() {
        this.b.l();
    }

    @Override // androidx.loader.app.LoaderManager
    @NonNull
    @MainThread
    public <D> Loader<D> restartLoader(int i2, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (this.b.k()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
        if (f827c) {
            Log.v("LoaderManager", "restartLoader in " + this + ": args=" + bundle);
        }
        LoaderInfo<D> i3 = this.b.i(i2);
        return a(i2, bundle, loaderCallbacks, i3 != null ? i3.h(false) : null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        DebugUtils.buildShortClassTag(this.a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
