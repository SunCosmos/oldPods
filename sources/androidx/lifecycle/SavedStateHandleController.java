package androidx.lifecycle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.Iterator;

/* loaded from: classes.dex */
final class SavedStateHandleController implements LifecycleEventObserver {
    private final String a;
    private boolean b = false;

    /* renamed from: c, reason: collision with root package name */
    private final SavedStateHandle f821c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a implements SavedStateRegistry.AutoRecreated {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
        public void onRecreated(@NonNull SavedStateRegistryOwner savedStateRegistryOwner) {
            if (!(savedStateRegistryOwner instanceof ViewModelStoreOwner)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
            }
            ViewModelStore viewModelStore = ((ViewModelStoreOwner) savedStateRegistryOwner).getViewModelStore();
            SavedStateRegistry savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
            Iterator<String> it = viewModelStore.b().iterator();
            while (it.hasNext()) {
                SavedStateHandleController.g(viewModelStore.a(it.next()), savedStateRegistry, savedStateRegistryOwner.getLifecycle());
            }
            if (viewModelStore.b().isEmpty()) {
                return;
            }
            savedStateRegistry.runOnNextRecreation(a.class);
        }
    }

    SavedStateHandleController(String str, SavedStateHandle savedStateHandle) {
        this.a = str;
        this.f821c = savedStateHandle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(ViewModel viewModel, SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) viewModel.c("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.k()) {
            return;
        }
        savedStateHandleController.h(savedStateRegistry, lifecycle);
        l(savedStateRegistry, lifecycle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SavedStateHandleController i(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle, String str, Bundle bundle) {
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, SavedStateHandle.a(savedStateRegistry.consumeRestoredStateForKey(str), bundle));
        savedStateHandleController.h(savedStateRegistry, lifecycle);
        l(savedStateRegistry, lifecycle);
        return savedStateHandleController;
    }

    private static void l(final SavedStateRegistry savedStateRegistry, final Lifecycle lifecycle) {
        Lifecycle.State currentState = lifecycle.getCurrentState();
        if (currentState == Lifecycle.State.INITIALIZED || currentState.isAtLeast(Lifecycle.State.STARTED)) {
            savedStateRegistry.runOnNextRecreation(a.class);
        } else {
            lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.lifecycle.SavedStateHandleController.1
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_START) {
                        Lifecycle.this.removeObserver(this);
                        savedStateRegistry.runOnNextRecreation(a.class);
                    }
                }
            });
        }
    }

    void h(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        if (this.b) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.b = true;
        lifecycle.addObserver(this);
        savedStateRegistry.registerSavedStateProvider(this.a, this.f821c.c());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SavedStateHandle j() {
        return this.f821c;
    }

    boolean k() {
        return this.b;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.b = false;
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
