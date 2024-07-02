package android.arch.lifecycle;

import android.support.annotation.NonNull;

@Deprecated
/* loaded from: lib/Mus.dex */
public interface LifecycleRegistryOwner extends LifecycleOwner {
    @Override // android.arch.lifecycle.LifecycleOwner
    @NonNull
    LifecycleRegistry getLifecycle();
}
