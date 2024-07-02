package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.Recreator;
import java.util.Map;

@SuppressLint({"RestrictedApi"})
/* loaded from: classes.dex */
public final class SavedStateRegistry {

    @Nullable
    private Bundle b;

    /* renamed from: c */
    private boolean f1020c;

    /* renamed from: d */
    private Recreator.a f1021d;
    private SafeIterableMap<String, SavedStateProvider> a = new SafeIterableMap<>();
    boolean e = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.savedstate.SavedStateRegistry$1 */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements GenericLifecycleObserver {
        AnonymousClass1() {
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            SavedStateRegistry savedStateRegistry;
            boolean z;
            if (event == Lifecycle.Event.ON_START) {
                savedStateRegistry = SavedStateRegistry.this;
                z = true;
            } else {
                if (event != Lifecycle.Event.ON_STOP) {
                    return;
                }
                savedStateRegistry = SavedStateRegistry.this;
                z = false;
            }
            savedStateRegistry.e = z;
        }
    }

    /* loaded from: classes.dex */
    public interface AutoRecreated {
        void onRecreated(@NonNull SavedStateRegistryOwner savedStateRegistryOwner);
    }

    /* loaded from: classes.dex */
    public interface SavedStateProvider {
        @NonNull
        Bundle saveState();
    }

    @MainThread
    public void a(@NonNull Lifecycle lifecycle, @Nullable Bundle bundle) {
        if (this.f1020c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        lifecycle.addObserver(new GenericLifecycleObserver() { // from class: androidx.savedstate.SavedStateRegistry.1
            AnonymousClass1() {
            }

            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                SavedStateRegistry savedStateRegistry;
                boolean z;
                if (event == Lifecycle.Event.ON_START) {
                    savedStateRegistry = SavedStateRegistry.this;
                    z = true;
                } else {
                    if (event != Lifecycle.Event.ON_STOP) {
                        return;
                    }
                    savedStateRegistry = SavedStateRegistry.this;
                    z = false;
                }
                savedStateRegistry.e = z;
            }
        });
        this.f1020c = true;
    }

    @MainThread
    public void b(@NonNull Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        SafeIterableMap<String, SavedStateProvider>.d iteratorWithAdditions = this.a.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext()) {
            Map.Entry next = iteratorWithAdditions.next();
            bundle2.putBundle((String) next.getKey(), ((SavedStateProvider) next.getValue()).saveState());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    @Nullable
    @MainThread
    public Bundle consumeRestoredStateForKey(@NonNull String str) {
        if (!this.f1020c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.b.remove(str);
        if (this.b.isEmpty()) {
            this.b = null;
        }
        return bundle2;
    }

    @MainThread
    public boolean isRestored() {
        return this.f1020c;
    }

    @MainThread
    public void registerSavedStateProvider(@NonNull String str, @NonNull SavedStateProvider savedStateProvider) {
        if (this.a.putIfAbsent(str, savedStateProvider) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    @MainThread
    public void runOnNextRecreation(@NonNull Class<? extends AutoRecreated> cls) {
        if (!this.e) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.f1021d == null) {
            this.f1021d = new Recreator.a(this);
        }
        try {
            cls.getDeclaredConstructor(new Class[0]);
            this.f1021d.a(cls.getName());
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class" + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
        }
    }

    @MainThread
    public void unregisterSavedStateProvider(@NonNull String str) {
        this.a.remove(str);
    }
}
