package androidx.lifecycle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.ViewModelProvider;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;

/* loaded from: classes.dex */
public abstract class AbstractSavedStateViewModelFactory extends ViewModelProvider.a {
    private final SavedStateRegistry a;
    private final Lifecycle b;

    /* renamed from: c, reason: collision with root package name */
    private final Bundle f807c;

    public AbstractSavedStateViewModelFactory(@NonNull SavedStateRegistryOwner savedStateRegistryOwner, @Nullable Bundle bundle) {
        this.a = savedStateRegistryOwner.getSavedStateRegistry();
        this.b = savedStateRegistryOwner.getLifecycle();
        this.f807c = bundle;
    }

    @Override // androidx.lifecycle.ViewModelProvider.b
    void a(@NonNull ViewModel viewModel) {
        SavedStateHandleController.g(viewModel, this.a, this.b);
    }

    @NonNull
    protected abstract <T extends ViewModel> T b(@NonNull String str, @NonNull Class<T> cls, @NonNull SavedStateHandle savedStateHandle);

    @Override // androidx.lifecycle.ViewModelProvider.a, androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public final <T extends ViewModel> T create(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) create(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.ViewModelProvider.a
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls) {
        SavedStateHandleController i2 = SavedStateHandleController.i(this.a, this.b, str, this.f807c);
        T t = (T) b(str, cls, i2.j());
        t.e("androidx.lifecycle.savedstate.vm.tag", i2);
        return t;
    }
}
