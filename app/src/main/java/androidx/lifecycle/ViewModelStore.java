package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ViewModelStore {
    private final HashMap<String, ViewModel> a = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ViewModel a(String str) {
        return this.a.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> b() {
        return new HashSet(this.a.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(String str, ViewModel viewModel) {
        ViewModel put = this.a.put(str, viewModel);
        if (put != null) {
            put.d();
        }
    }

    public final void clear() {
        Iterator<ViewModel> it = this.a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.a.clear();
    }
}
