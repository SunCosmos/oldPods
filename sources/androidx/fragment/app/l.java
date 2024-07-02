package androidx.fragment.app;

import android.app.Application;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l implements HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ViewModelStoreOwner {
    private final Fragment a;
    private final ViewModelStore b;

    /* renamed from: c, reason: collision with root package name */
    private ViewModelProvider.Factory f788c;

    /* renamed from: d, reason: collision with root package name */
    private LifecycleRegistry f789d = null;
    private SavedStateRegistryController e = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(@NonNull Fragment fragment, @NonNull ViewModelStore viewModelStore) {
        this.a = fragment;
        this.b = viewModelStore;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@NonNull Lifecycle.Event event) {
        this.f789d.handleLifecycleEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.f789d == null) {
            this.f789d = new LifecycleRegistry(this);
            this.e = SavedStateRegistryController.create(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.f789d != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(@Nullable Bundle bundle) {
        this.e.performRestore(bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@NonNull Bundle bundle) {
        this.e.performSave(bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(@NonNull Lifecycle.State state) {
        this.f789d.setCurrentState(state);
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    @NonNull
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        ViewModelProvider.Factory defaultViewModelProviderFactory = this.a.getDefaultViewModelProviderFactory();
        if (!defaultViewModelProviderFactory.equals(this.a.V)) {
            this.f788c = defaultViewModelProviderFactory;
            return defaultViewModelProviderFactory;
        }
        if (this.f788c == null) {
            Application application = null;
            Object applicationContext = this.a.requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            this.f788c = new SavedStateViewModelFactory(application, this, this.a.getArguments());
        }
        return this.f788c;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        b();
        return this.f789d;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    @NonNull
    public SavedStateRegistry getSavedStateRegistry() {
        b();
        return this.e.getSavedStateRegistry();
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    @NonNull
    public ViewModelStore getViewModelStore() {
        b();
        return this.b;
    }
}
