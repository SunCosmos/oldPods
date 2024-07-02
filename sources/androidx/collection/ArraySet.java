package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class ArraySet<E> implements Collection<E>, Set<E> {
    private static final int[] e = new int[0];
    private static final Object[] f = new Object[0];

    @Nullable
    private static Object[] g;
    private static int h;

    /* renamed from: i, reason: collision with root package name */
    @Nullable
    private static Object[] f258i;
    private static int j;
    private int[] a;
    Object[] b;

    /* renamed from: c, reason: collision with root package name */
    int f259c;

    /* renamed from: d, reason: collision with root package name */
    private b<E, E> f260d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends b<E, E> {
        a() {
        }

        @Override // androidx.collection.b
        protected void a() {
            ArraySet.this.clear();
        }

        @Override // androidx.collection.b
        protected Object b(int i2, int i3) {
            return ArraySet.this.b[i2];
        }

        @Override // androidx.collection.b
        protected Map<E, E> c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // androidx.collection.b
        protected int d() {
            return ArraySet.this.f259c;
        }

        @Override // androidx.collection.b
        protected int e(Object obj) {
            return ArraySet.this.indexOf(obj);
        }

        @Override // androidx.collection.b
        protected int f(Object obj) {
            return ArraySet.this.indexOf(obj);
        }

        @Override // androidx.collection.b
        protected void g(E e, E e2) {
            ArraySet.this.add(e);
        }

        @Override // androidx.collection.b
        protected void h(int i2) {
            ArraySet.this.removeAt(i2);
        }

        @Override // androidx.collection.b
        protected E i(int i2, E e) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public ArraySet() {
        this(0);
    }

    public ArraySet(int i2) {
        if (i2 == 0) {
            this.a = e;
            this.b = f;
        } else {
            a(i2);
        }
        this.f259c = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(@Nullable ArraySet<E> arraySet) {
        this();
        if (arraySet != 0) {
            addAll((ArraySet) arraySet);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(@Nullable Collection<E> collection) {
        this();
        if (collection != 0) {
            addAll(collection);
        }
    }

    private void a(int i2) {
        if (i2 == 8) {
            synchronized (ArraySet.class) {
                Object[] objArr = f258i;
                if (objArr != null) {
                    this.b = objArr;
                    f258i = (Object[]) objArr[0];
                    this.a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    j--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (ArraySet.class) {
                Object[] objArr2 = g;
                if (objArr2 != null) {
                    this.b = objArr2;
                    g = (Object[]) objArr2[0];
                    this.a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    h--;
                    return;
                }
            }
        }
        this.a = new int[i2];
        this.b = new Object[i2];
    }

    private static void b(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (ArraySet.class) {
                if (j < 10) {
                    objArr[0] = f258i;
                    objArr[1] = iArr;
                    for (int i3 = i2 - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f258i = objArr;
                    j++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (ArraySet.class) {
                if (h < 10) {
                    objArr[0] = g;
                    objArr[1] = iArr;
                    for (int i4 = i2 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    g = objArr;
                    h++;
                }
            }
        }
    }

    private b<E, E> c() {
        if (this.f260d == null) {
            this.f260d = new a();
        }
        return this.f260d;
    }

    private int d(Object obj, int i2) {
        int i3 = this.f259c;
        if (i3 == 0) {
            return -1;
        }
        int a2 = androidx.collection.a.a(this.a, i3, i2);
        if (a2 < 0 || obj.equals(this.b[a2])) {
            return a2;
        }
        int i4 = a2 + 1;
        while (i4 < i3 && this.a[i4] == i2) {
            if (obj.equals(this.b[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = a2 - 1; i5 >= 0 && this.a[i5] == i2; i5--) {
            if (obj.equals(this.b[i5])) {
                return i5;
            }
        }
        return i4 ^ (-1);
    }

    private int e() {
        int i2 = this.f259c;
        if (i2 == 0) {
            return -1;
        }
        int a2 = androidx.collection.a.a(this.a, i2, 0);
        if (a2 < 0 || this.b[a2] == null) {
            return a2;
        }
        int i3 = a2 + 1;
        while (i3 < i2 && this.a[i3] == 0) {
            if (this.b[i3] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = a2 - 1; i4 >= 0 && this.a[i4] == 0; i4--) {
            if (this.b[i4] == null) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(@Nullable E e2) {
        int i2;
        int d2;
        if (e2 == null) {
            d2 = e();
            i2 = 0;
        } else {
            int hashCode = e2.hashCode();
            i2 = hashCode;
            d2 = d(e2, hashCode);
        }
        if (d2 >= 0) {
            return false;
        }
        int i3 = d2 ^ (-1);
        int i4 = this.f259c;
        int[] iArr = this.a;
        if (i4 >= iArr.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            Object[] objArr = this.b;
            a(i5);
            int[] iArr2 = this.a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.b, 0, objArr.length);
            }
            b(iArr, objArr, this.f259c);
        }
        int i6 = this.f259c;
        if (i3 < i6) {
            int[] iArr3 = this.a;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.b;
            System.arraycopy(objArr2, i3, objArr2, i7, this.f259c - i3);
        }
        this.a[i3] = i2;
        this.b[i3] = e2;
        this.f259c++;
        return true;
    }

    public void addAll(@NonNull ArraySet<? extends E> arraySet) {
        int i2 = arraySet.f259c;
        ensureCapacity(this.f259c + i2);
        if (this.f259c != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                add(arraySet.valueAt(i3));
            }
        } else if (i2 > 0) {
            System.arraycopy(arraySet.a, 0, this.a, 0, i2);
            System.arraycopy(arraySet.b, 0, this.b, 0, i2);
            this.f259c = i2;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(@NonNull Collection<? extends E> collection) {
        ensureCapacity(this.f259c + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= add(it.next());
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i2 = this.f259c;
        if (i2 != 0) {
            b(this.a, this.b, i2);
            this.a = e;
            this.b = f;
            this.f259c = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(@Nullable Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public void ensureCapacity(int i2) {
        int[] iArr = this.a;
        if (iArr.length < i2) {
            Object[] objArr = this.b;
            a(i2);
            int i3 = this.f259c;
            if (i3 > 0) {
                System.arraycopy(iArr, 0, this.a, 0, i3);
                System.arraycopy(objArr, 0, this.b, 0, this.f259c);
            }
            b(iArr, objArr, this.f259c);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f259c; i2++) {
                try {
                    if (!set.contains(valueAt(i2))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.a;
        int i2 = this.f259c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    public int indexOf(@Nullable Object obj) {
        return obj == null ? e() : d(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f259c <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return c().m().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(@Nullable Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    public boolean removeAll(@NonNull ArraySet<? extends E> arraySet) {
        int i2 = arraySet.f259c;
        int i3 = this.f259c;
        for (int i4 = 0; i4 < i2; i4++) {
            remove(arraySet.valueAt(i4));
        }
        return i3 != this.f259c;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    public E removeAt(int i2) {
        Object[] objArr = this.b;
        E e2 = (E) objArr[i2];
        int i3 = this.f259c;
        if (i3 <= 1) {
            b(this.a, objArr, i3);
            this.a = e;
            this.b = f;
            this.f259c = 0;
        } else {
            int[] iArr = this.a;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                int i4 = i3 - 1;
                this.f259c = i4;
                if (i2 < i4) {
                    int i5 = i2 + 1;
                    System.arraycopy(iArr, i5, iArr, i2, i4 - i2);
                    Object[] objArr2 = this.b;
                    System.arraycopy(objArr2, i5, objArr2, i2, this.f259c - i2);
                }
                this.b[this.f259c] = null;
            } else {
                a(i3 > 8 ? i3 + (i3 >> 1) : 8);
                this.f259c--;
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.a, 0, i2);
                    System.arraycopy(objArr, 0, this.b, 0, i2);
                }
                int i6 = this.f259c;
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    System.arraycopy(iArr, i7, this.a, i2, i6 - i2);
                    System.arraycopy(objArr, i7, this.b, i2, this.f259c - i2);
                }
            }
        }
        return e2;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(@NonNull Collection<?> collection) {
        boolean z = false;
        for (int i2 = this.f259c - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.b[i2])) {
                removeAt(i2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.f259c;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public Object[] toArray() {
        int i2 = this.f259c;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.b, 0, objArr, 0, i2);
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public <T> T[] toArray(@NonNull T[] tArr) {
        if (tArr.length < this.f259c) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f259c));
        }
        System.arraycopy(this.b, 0, tArr, 0, this.f259c);
        int length = tArr.length;
        int i2 = this.f259c;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f259c * 14);
        sb.append('{');
        for (int i2 = 0; i2 < this.f259c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            E valueAt = valueAt(i2);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Nullable
    public E valueAt(int i2) {
        return (E) this.b[i2];
    }
}
