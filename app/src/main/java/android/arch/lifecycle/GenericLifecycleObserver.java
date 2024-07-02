package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;

/* loaded from: lib/Mus.dex */
public interface GenericLifecycleObserver extends LifecycleObserver {
    void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event);
}
