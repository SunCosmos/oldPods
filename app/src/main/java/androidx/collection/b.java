package androidx.collection;

import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class b<K, V> {

    @Nullable
    b<K, V>.C0009b a;

    @Nullable
    b<K, V>.c b;

    /* renamed from: c, reason: collision with root package name */
    @Nullable
    b<K, V>.e f275c;

    /* loaded from: classes.dex */
    final class a<T> implements Iterator<T> {
        final int a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        int f276c;

        /* renamed from: d, reason: collision with root package name */
        boolean f277d = false;

        a(int i2) {
            this.a = i2;
            this.b = b.this.d();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f276c < this.b;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T t = (T) b.this.b(this.f276c, this.a);
            this.f276c++;
            this.f277d = true;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f277d) {
                throw new IllegalStateException();
            }
            int i2 = this.f276c - 1;
            this.f276c = i2;
            this.b--;
            this.f277d = false;
            b.this.h(i2);
        }
    }

    /* renamed from: androidx.collection.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    final class C0009b implements Set<Map.Entry<K, V>> {
        C0009b() {
        }

        public boolean a(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            a((Map.Entry) obj);
            throw null;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int d2 = b.this.d();
            for (Map.Entry<K, V> entry : collection) {
                b.this.g(entry.getKey(), entry.getValue());
            }
            return d2 != b.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            b.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int e = b.this.e(entry.getKey());
            if (e < 0) {
                return false;
            }
            return androidx.collection.a.c(b.this.b(e, 1), entry.getValue());
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return b.k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i2 = 0;
            for (int d2 = b.this.d() - 1; d2 >= 0; d2--) {
                Object b = b.this.b(d2, 0);
                Object b2 = b.this.b(d2, 1);
                i2 += (b == null ? 0 : b.hashCode()) ^ (b2 == null ? 0 : b2.hashCode());
            }
            return i2;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return b.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return b.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes.dex */
    final class c implements Set<K> {
        c() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            b.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return b.this.e(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return b.j(b.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return b.k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i2 = 0;
            for (int d2 = b.this.d() - 1; d2 >= 0; d2--) {
                Object b = b.this.b(d2, 0);
                i2 += b == null ? 0 : b.hashCode();
            }
            return i2;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return b.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<K> iterator() {
            return new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            int e = b.this.e(obj);
            if (e < 0) {
                return false;
            }
            b.this.h(e);
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            return b.o(b.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            return b.p(b.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return b.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return b.this.q(0);
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) b.this.r(tArr, 0);
        }
    }

    /* loaded from: classes.dex */
    final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        int a;

        /* renamed from: c, reason: collision with root package name */
        boolean f278c = false;
        int b = -1;

        d() {
            this.a = b.this.d() - 1;
        }

        public Map.Entry<K, V> a() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.b++;
            this.f278c = true;
            return this;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!this.f278c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return androidx.collection.a.c(entry.getKey(), b.this.b(this.b, 0)) && androidx.collection.a.c(entry.getValue(), b.this.b(this.b, 1));
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (this.f278c) {
                return (K) b.this.b(this.b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (this.f278c) {
                return (V) b.this.b(this.b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b < this.a;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (!this.f278c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            Object b = b.this.b(this.b, 0);
            Object b2 = b.this.b(this.b, 1);
            return (b == null ? 0 : b.hashCode()) ^ (b2 != null ? b2.hashCode() : 0);
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            a();
            return this;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f278c) {
                throw new IllegalStateException();
            }
            b.this.h(this.b);
            this.b--;
            this.a--;
            this.f278c = false;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            if (this.f278c) {
                return (V) b.this.i(this.b, v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* loaded from: classes.dex */
    final class e implements Collection<V> {
        e() {
        }

        @Override // java.util.Collection
        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            b.this.a();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return b.this.f(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return b.this.d() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new a(1);
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            int f = b.this.f(obj);
            if (f < 0) {
                return false;
            }
            b.this.h(f);
            return true;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            int d2 = b.this.d();
            int i2 = 0;
            boolean z = false;
            while (i2 < d2) {
                if (collection.contains(b.this.b(i2, 1))) {
                    b.this.h(i2);
                    i2--;
                    d2--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            int d2 = b.this.d();
            int i2 = 0;
            boolean z = false;
            while (i2 < d2) {
                if (!collection.contains(b.this.b(i2, 1))) {
                    b.this.h(i2);
                    i2--;
                    d2--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        @Override // java.util.Collection
        public int size() {
            return b.this.d();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return b.this.q(1);
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) b.this.r(tArr, 1);
        }
    }

    public static <K, V> boolean j(Map<K, V> map, Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean k(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static <K, V> boolean o(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        return size != map.size();
    }

    public static <K, V> boolean p(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    protected abstract void a();

    protected abstract Object b(int i2, int i3);

    protected abstract Map<K, V> c();

    protected abstract int d();

    protected abstract int e(Object obj);

    protected abstract int f(Object obj);

    protected abstract void g(K k, V v);

    protected abstract void h(int i2);

    protected abstract V i(int i2, V v);

    public Set<Map.Entry<K, V>> l() {
        if (this.a == null) {
            this.a = new C0009b();
        }
        return this.a;
    }

    public Set<K> m() {
        if (this.b == null) {
            this.b = new c();
        }
        return this.b;
    }

    public Collection<V> n() {
        if (this.f275c == null) {
            this.f275c = new e();
        }
        return this.f275c;
    }

    public Object[] q(int i2) {
        int d2 = d();
        Object[] objArr = new Object[d2];
        for (int i3 = 0; i3 < d2; i3++) {
            objArr[i3] = b(i3, i2);
        }
        return objArr;
    }

    public <T> T[] r(T[] tArr, int i2) {
        int d2 = d();
        if (tArr.length < d2) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), d2));
        }
        for (int i3 = 0; i3 < d2; i3++) {
            tArr[i3] = b(i3, i2);
        }
        if (tArr.length > d2) {
            tArr[d2] = null;
        }
        return tArr;
    }
}
