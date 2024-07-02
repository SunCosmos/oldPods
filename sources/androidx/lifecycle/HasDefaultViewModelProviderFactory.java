package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

/* loaded from: classes.dex */
public interface HasDefaultViewModelProviderFactory {
    @NonNull
    ViewModelProvider.Factory getDefaultViewModelProviderFactory();
}
