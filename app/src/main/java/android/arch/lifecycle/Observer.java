package android.arch.lifecycle;

import android.support.annotation.Nullable;

/* loaded from: lib/Mus.dex */
public interface Observer<T> {
    void onChanged(@Nullable T t);
}
