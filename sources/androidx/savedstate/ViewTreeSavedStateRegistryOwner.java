package androidx.savedstate;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public final class ViewTreeSavedStateRegistryOwner {
    private ViewTreeSavedStateRegistryOwner() {
    }

    @Nullable
    public static SavedStateRegistryOwner get(@NonNull View view) {
        SavedStateRegistryOwner savedStateRegistryOwner = (SavedStateRegistryOwner) view.getTag(R.id.view_tree_saved_state_registry_owner);
        if (savedStateRegistryOwner != null) {
            return savedStateRegistryOwner;
        }
        while (true) {
            Object parent = view.getParent();
            if (savedStateRegistryOwner != null || !(parent instanceof View)) {
                break;
            }
            view = (View) parent;
            savedStateRegistryOwner = (SavedStateRegistryOwner) view.getTag(R.id.view_tree_saved_state_registry_owner);
        }
        return savedStateRegistryOwner;
    }

    public static void set(@NonNull View view, @Nullable SavedStateRegistryOwner savedStateRegistryOwner) {
        view.setTag(R.id.view_tree_saved_state_registry_owner, savedStateRegistryOwner);
    }
}
