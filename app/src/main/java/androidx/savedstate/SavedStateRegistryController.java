package androidx.savedstate;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

/* loaded from: classes.dex */
public final class SavedStateRegistryController {
    private final SavedStateRegistryOwner a;
    private final SavedStateRegistry b = new SavedStateRegistry();

    private SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner) {
        this.a = savedStateRegistryOwner;
    }

    @NonNull
    public static SavedStateRegistryController create(@NonNull SavedStateRegistryOwner savedStateRegistryOwner) {
        return new SavedStateRegistryController(savedStateRegistryOwner);
    }

    @NonNull
    public SavedStateRegistry getSavedStateRegistry() {
        return this.b;
    }

    @MainThread
    public void performRestore(@Nullable Bundle bundle) {
        Lifecycle lifecycle = this.a.getLifecycle();
        if (lifecycle.getCurrentState() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.addObserver(new Recreator(this.a));
        this.b.a(lifecycle, bundle);
    }

    @MainThread
    public void performSave(@NonNull Bundle bundle) {
        this.b.b(bundle);
    }
}
