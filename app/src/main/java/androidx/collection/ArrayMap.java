package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {

    @Nullable
    b<K, V> h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends b<K, V> {
        a() {
        }

        @Override // androidx.collection.b
        protected void a() {
            ArrayMap.this.clear();
        }

        @Override // androidx.collection.b
        protected Object b(int i2, int i3) {
            return ArrayMap.this.b[(i2 << 1) + i3];
        }

        @Override // androidx.collection.b
        protected Map<K, V> c() {
            return ArrayMap.this;
        }

        @Override // androidx.collection.b
        protected int d() {
            return ArrayMap.this.f271c;
        }

        @Override // androidx.collection.b
        protected int e(Object obj) {
            return ArrayMap.this.indexOfKey(obj);
        }

        @Override // androidx.collection.b
        protected int f(Object obj) {
            return ArrayMap.this.f(obj);
        }

        @Override // androidx.collection.b
        protected void g(K k, V v) {
            ArrayMap.this.put(k, v);
        }

        @Override // androidx.collection.b
        protected void h(int i2) {
            ArrayMap.this.removeAt(i2);
        }

        @Override // androidx.collection.b
        protected V i(int i2, V v) {
            return ArrayMap.this.setValueAt(i2, v);
        }
    }

    public ArrayMap() {
    }

    public ArrayMap(int i2) {
        super(i2);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }

    private b<K, V> g() {
        if (this.h == null) {
            this.h = new a();
        }
        return this.h;
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        return b.j(this, collection);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return g().l();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return g().m();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.f271c + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        return b.o(this, collection);
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        return b.p(this, collection);
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return g().n();
    }
}
