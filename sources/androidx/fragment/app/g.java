package androidx.fragment.app;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class g extends ViewModel {
    private static final ViewModelProvider.Factory j = new a();
    private final boolean f;

    /* renamed from: c, reason: collision with root package name */
    private final HashMap<String, Fragment> f768c = new HashMap<>();

    /* renamed from: d, reason: collision with root package name */
    private final HashMap<String, g> f769d = new HashMap<>();
    private final HashMap<String, ViewModelStore> e = new HashMap<>();
    private boolean g = false;
    private boolean h = false;

    /* renamed from: i, reason: collision with root package name */
    private boolean f770i = false;

    /* loaded from: classes.dex */
    class a implements ViewModelProvider.Factory {
        a() {
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> cls) {
            return new g(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(boolean z) {
        this.f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static g j(ViewModelStore viewModelStore) {
        return (g) new ViewModelProvider(viewModelStore, j).get(g.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public void d() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.g = true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g.class != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        return this.f768c.equals(gVar.f768c) && this.f769d.equals(gVar.f769d) && this.e.equals(gVar.e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(@NonNull Fragment fragment) {
        if (this.f770i) {
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else {
            if (this.f768c.containsKey(fragment.f)) {
                return;
            }
            this.f768c.put(fragment.f, fragment);
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(@NonNull Fragment fragment) {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        g gVar = this.f769d.get(fragment.f);
        if (gVar != null) {
            gVar.d();
            this.f769d.remove(fragment.f);
        }
        ViewModelStore viewModelStore = this.e.get(fragment.f);
        if (viewModelStore != null) {
            viewModelStore.clear();
            this.e.remove(fragment.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Fragment h(String str) {
        return this.f768c.get(str);
    }

    public int hashCode() {
        return (((this.f768c.hashCode() * 31) + this.f769d.hashCode()) * 31) + this.e.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public g i(@NonNull Fragment fragment) {
        g gVar = this.f769d.get(fragment.f);
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(this.f);
        this.f769d.put(fragment.f, gVar2);
        return gVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Collection<Fragment> k() {
        return new ArrayList(this.f768c.values());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @Deprecated
    public FragmentManagerNonConfig l() {
        if (this.f768c.isEmpty() && this.f769d.isEmpty() && this.e.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, g> entry : this.f769d.entrySet()) {
            FragmentManagerNonConfig l = entry.getValue().l();
            if (l != null) {
                hashMap.put(entry.getKey(), l);
            }
        }
        this.h = true;
        if (this.f768c.isEmpty() && hashMap.isEmpty() && this.e.isEmpty()) {
            return null;
        }
        return new FragmentManagerNonConfig(new ArrayList(this.f768c.values()), hashMap, new HashMap(this.e));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ViewModelStore m(@NonNull Fragment fragment) {
        ViewModelStore viewModelStore = this.e.get(fragment.f);
        if (viewModelStore != null) {
            return viewModelStore;
        }
        ViewModelStore viewModelStore2 = new ViewModelStore();
        this.e.put(fragment.f, viewModelStore2);
        return viewModelStore2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean n() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(@NonNull Fragment fragment) {
        if (this.f770i) {
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
                return;
            }
            return;
        }
        if ((this.f768c.remove(fragment.f) != null) && FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public void p(@Nullable FragmentManagerNonConfig fragmentManagerNonConfig) {
        this.f768c.clear();
        this.f769d.clear();
        this.e.clear();
        if (fragmentManagerNonConfig != null) {
            Collection<Fragment> b = fragmentManagerNonConfig.b();
            if (b != null) {
                for (Fragment fragment : b) {
                    if (fragment != null) {
                        this.f768c.put(fragment.f, fragment);
                    }
                }
            }
            Map<String, FragmentManagerNonConfig> a2 = fragmentManagerNonConfig.a();
            if (a2 != null) {
                for (Map.Entry<String, FragmentManagerNonConfig> entry : a2.entrySet()) {
                    g gVar = new g(this.f);
                    gVar.p(entry.getValue());
                    this.f769d.put(entry.getKey(), gVar);
                }
            }
            Map<String, ViewModelStore> c2 = fragmentManagerNonConfig.c();
            if (c2 != null) {
                this.e.putAll(c2);
            }
        }
        this.h = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(boolean z) {
        this.f770i = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean r(@NonNull Fragment fragment) {
        if (this.f768c.containsKey(fragment.f)) {
            return this.f ? this.g : !this.h;
        }
        return true;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f768c.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f769d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.e.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
