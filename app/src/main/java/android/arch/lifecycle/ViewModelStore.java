package android.arch.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* loaded from: lib/Mus.dex */
public class ViewModelStore {
    private final HashMap<String, ViewModel> mMap = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void put(String str, ViewModel viewModel) {
        ViewModel viewModel2 = this.mMap.get(str);
        if (viewModel2 != null) {
            viewModel2.onCleared();
        }
        this.mMap.put(str, viewModel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ViewModel get(String str) {
        return this.mMap.get(str);
    }

    public final void clear() {
        Iterator<ViewModel> it = this.mMap.values().iterator();
        while (it.hasNext()) {
            it.next().onCleared();
        }
        this.mMap.clear();
    }
}
