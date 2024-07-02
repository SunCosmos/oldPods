package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {
    c<K, V> a;
    private c<K, V> b;

    /* renamed from: c, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f241c = new WeakHashMap<>();

    /* renamed from: d, reason: collision with root package name */
    private int f242d = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f244d;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f243c;
        }
    }

    /* loaded from: classes.dex */
    private static class b<K, V> extends e<K, V> {
        b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f243c;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f244d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c<K, V> implements Map.Entry<K, V> {

        @NonNull
        final K a;

        @NonNull
        final V b;

        /* renamed from: c, reason: collision with root package name */
        c<K, V> f243c;

        /* renamed from: d, reason: collision with root package name */
        c<K, V> f244d;

        c(@NonNull K k, @NonNull V v) {
            this.a = k;
            this.b = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.a.equals(cVar.a) && this.b.equals(cVar.b);
        }

        @Override // java.util.Map.Entry
        @NonNull
        public K getKey() {
            return this.a;
        }

        @Override // java.util.Map.Entry
        @NonNull
        public V getValue() {
            return this.b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.a.hashCode() ^ this.b.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.a + "=" + this.b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {
        private c<K, V> a;
        private boolean b = true;

        d() {
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.f
        public void a(@NonNull c<K, V> cVar) {
            c<K, V> cVar2 = this.a;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f244d;
                this.a = cVar3;
                this.b = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar;
            if (this.b) {
                this.b = false;
                cVar = SafeIterableMap.this.a;
            } else {
                c<K, V> cVar2 = this.a;
                cVar = cVar2 != null ? cVar2.f243c : null;
            }
            this.a = cVar;
            return this.a;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.b) {
                return SafeIterableMap.this.a != null;
            }
            c<K, V> cVar = this.a;
            return (cVar == null || cVar.f243c == null) ? false : true;
        }
    }

    /* loaded from: classes.dex */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {
        c<K, V> a;
        c<K, V> b;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.a = cVar2;
            this.b = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.b;
            c<K, V> cVar2 = this.a;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.f
        public void a(@NonNull c<K, V> cVar) {
            if (this.a == cVar && cVar == this.b) {
                this.b = null;
                this.a = null;
            }
            c<K, V> cVar2 = this.a;
            if (cVar2 == cVar) {
                this.a = b(cVar2);
            }
            if (this.b == cVar) {
                this.b = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.b;
            this.b = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface f<K, V> {
        void a(@NonNull c<K, V> cVar);
    }

    protected c<K, V> a(K k) {
        c<K, V> cVar = this.a;
        while (cVar != null && !cVar.a.equals(k)) {
            cVar = cVar.f243c;
        }
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c<K, V> b(@NonNull K k, @NonNull V v) {
        c<K, V> cVar = new c<>(k, v);
        this.f242d++;
        c<K, V> cVar2 = this.b;
        if (cVar2 == null) {
            this.a = cVar;
        } else {
            cVar2.f243c = cVar;
            cVar.f244d = cVar2;
        }
        this.b = cVar;
        return cVar;
    }

    public Iterator<Map.Entry<K, V>> descendingIterator() {
        b bVar = new b(this.b, this.a);
        this.f241c.put(bVar, Boolean.FALSE);
        return bVar;
    }

    public Map.Entry<K, V> eldest() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
        if (size() != safeIterableMap.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> it = iterator();
        Iterator<Map.Entry<K, V>> it2 = safeIterableMap.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry<K, V> next = it.next();
            Map.Entry<K, V> next2 = it2.next();
            if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().hashCode();
        }
        return i2;
    }

    @Override // java.lang.Iterable
    @NonNull
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.a, this.b);
        this.f241c.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public SafeIterableMap<K, V>.d iteratorWithAdditions() {
        SafeIterableMap<K, V>.d dVar = new d();
        this.f241c.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> newest() {
        return this.b;
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        c<K, V> a2 = a(k);
        if (a2 != null) {
            return a2.b;
        }
        b(k, v);
        return null;
    }

    public V remove(@NonNull K k) {
        c<K, V> a2 = a(k);
        if (a2 == null) {
            return null;
        }
        this.f242d--;
        if (!this.f241c.isEmpty()) {
            Iterator<f<K, V>> it = this.f241c.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(a2);
            }
        }
        c<K, V> cVar = a2.f244d;
        c<K, V> cVar2 = a2.f243c;
        if (cVar != null) {
            cVar.f243c = cVar2;
        } else {
            this.a = cVar2;
        }
        c<K, V> cVar3 = a2.f243c;
        if (cVar3 != null) {
            cVar3.f244d = cVar;
        } else {
            this.b = cVar;
        }
        a2.f243c = null;
        a2.f244d = null;
        return a2.b;
    }

    public int size() {
        return this.f242d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
