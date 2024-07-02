package android.arch.lifecycle;

import android.support.annotation.NonNull;

/* loaded from: lib/Mus.dex */
public interface LifecycleOwner {
    @NonNull
    Lifecycle getLifecycle();
}
