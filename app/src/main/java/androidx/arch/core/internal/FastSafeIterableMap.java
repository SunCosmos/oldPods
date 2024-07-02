package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {
    private HashMap<K, SafeIterableMap.c<K, V>> e = new HashMap<>();

    @Override // androidx.arch.core.internal.SafeIterableMap
    protected SafeIterableMap.c<K, V> a(K k) {
        return this.e.get(k);
    }

    public Map.Entry<K, V> ceil(K k) {
        if (contains(k)) {
            return this.e.get(k).f244d;
        }
        return null;
    }

    public boolean contains(K k) {
        return this.e.containsKey(k);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        SafeIterableMap.c<K, V> a = a(k);
        if (a != null) {
            return a.b;
        }
        this.e.put(k, b(k, v));
        return null;
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public V remove(@NonNull K k) {
        V v = (V) super.remove(k);
        this.e.remove(k);
        return v;
    }
}
