package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* loaded from: classes.dex */
public class SimpleArrayMap<K, V> {

    /* renamed from: d, reason: collision with root package name */
    @Nullable
    static Object[] f270d;
    static int e;

    @Nullable
    static Object[] f;
    static int g;
    int[] a;
    Object[] b;

    /* renamed from: c, reason: collision with root package name */
    int f271c;

    public SimpleArrayMap() {
        this.a = a.a;
        this.b = a.f274c;
        this.f271c = 0;
    }

    public SimpleArrayMap(int i2) {
        if (i2 == 0) {
            this.a = a.a;
            this.b = a.f274c;
        } else {
            a(i2);
        }
        this.f271c = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleArrayMap(SimpleArrayMap<K, V> simpleArrayMap) {
        this();
        if (simpleArrayMap != 0) {
            putAll(simpleArrayMap);
        }
    }

    private void a(int i2) {
        if (i2 == 8) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr = f;
                if (objArr != null) {
                    this.b = objArr;
                    f = (Object[]) objArr[0];
                    this.a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    g--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr2 = f270d;
                if (objArr2 != null) {
                    this.b = objArr2;
                    f270d = (Object[]) objArr2[0];
                    this.a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    e--;
                    return;
                }
            }
        }
        this.a = new int[i2];
        this.b = new Object[i2 << 1];
    }

    private static int b(int[] iArr, int i2, int i3) {
        try {
            return a.a(iArr, i2, i3);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void c(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (SimpleArrayMap.class) {
                if (g < 10) {
                    objArr[0] = f;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f = objArr;
                    g++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (SimpleArrayMap.class) {
                if (e < 10) {
                    objArr[0] = f270d;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    f270d = objArr;
                    e++;
                }
            }
        }
    }

    public void clear() {
        int i2 = this.f271c;
        if (i2 > 0) {
            int[] iArr = this.a;
            Object[] objArr = this.b;
            this.a = a.a;
            this.b = a.f274c;
            this.f271c = 0;
            c(iArr, objArr, i2);
        }
        if (this.f271c > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(@Nullable Object obj) {
        return indexOfKey(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return f(obj) >= 0;
    }

    int d(Object obj, int i2) {
        int i3 = this.f271c;
        if (i3 == 0) {
            return -1;
        }
        int b = b(this.a, i3, i2);
        if (b < 0 || obj.equals(this.b[b << 1])) {
            return b;
        }
        int i4 = b + 1;
        while (i4 < i3 && this.a[i4] == i2) {
            if (obj.equals(this.b[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = b - 1; i5 >= 0 && this.a[i5] == i2; i5--) {
            if (obj.equals(this.b[i5 << 1])) {
                return i5;
            }
        }
        return i4 ^ (-1);
    }

    int e() {
        int i2 = this.f271c;
        if (i2 == 0) {
            return -1;
        }
        int b = b(this.a, i2, 0);
        if (b < 0 || this.b[b << 1] == null) {
            return b;
        }
        int i3 = b + 1;
        while (i3 < i2 && this.a[i3] == 0) {
            if (this.b[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = b - 1; i4 >= 0 && this.a[i4] == 0; i4--) {
            if (this.b[i4 << 1] == null) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    public void ensureCapacity(int i2) {
        int i3 = this.f271c;
        int[] iArr = this.a;
        if (iArr.length < i2) {
            Object[] objArr = this.b;
            a(i2);
            if (this.f271c > 0) {
                System.arraycopy(iArr, 0, this.a, 0, i3);
                System.arraycopy(objArr, 0, this.b, 0, i3 << 1);
            }
            c(iArr, objArr, i3);
        }
        if (this.f271c != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SimpleArrayMap) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
            if (size() != simpleArrayMap.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f271c; i2++) {
                try {
                    K keyAt = keyAt(i2);
                    V valueAt = valueAt(i2);
                    Object obj2 = simpleArrayMap.get(keyAt);
                    if (valueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(keyAt)) {
                            return false;
                        }
                    } else if (!valueAt.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i3 = 0; i3 < this.f271c; i3++) {
                try {
                    K keyAt2 = keyAt(i3);
                    V valueAt2 = valueAt(i3);
                    Object obj3 = map.get(keyAt2);
                    if (valueAt2 == null) {
                        if (obj3 != null || !map.containsKey(keyAt2)) {
                            return false;
                        }
                    } else if (!valueAt2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(Object obj) {
        int i2 = this.f271c * 2;
        Object[] objArr = this.b;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    @Nullable
    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v) {
        int indexOfKey = indexOfKey(obj);
        return indexOfKey >= 0 ? (V) this.b[(indexOfKey << 1) + 1] : v;
    }

    public int hashCode() {
        int[] iArr = this.a;
        Object[] objArr = this.b;
        int i2 = this.f271c;
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            i5 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return i5;
    }

    public int indexOfKey(@Nullable Object obj) {
        return obj == null ? e() : d(obj, obj.hashCode());
    }

    public boolean isEmpty() {
        return this.f271c <= 0;
    }

    public K keyAt(int i2) {
        return (K) this.b[i2 << 1];
    }

    @Nullable
    public V put(K k, V v) {
        int i2;
        int d2;
        int i3 = this.f271c;
        if (k == null) {
            d2 = e();
            i2 = 0;
        } else {
            int hashCode = k.hashCode();
            i2 = hashCode;
            d2 = d(k, hashCode);
        }
        if (d2 >= 0) {
            int i4 = (d2 << 1) + 1;
            Object[] objArr = this.b;
            V v2 = (V) objArr[i4];
            objArr[i4] = v;
            return v2;
        }
        int i5 = d2 ^ (-1);
        int[] iArr = this.a;
        if (i3 >= iArr.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            Object[] objArr2 = this.b;
            a(i6);
            if (i3 != this.f271c) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.b, 0, objArr2.length);
            }
            c(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.a;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.b;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.f271c - i5) << 1);
        }
        int i8 = this.f271c;
        if (i3 == i8) {
            int[] iArr4 = this.a;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.b;
                int i9 = i5 << 1;
                objArr4[i9] = k;
                objArr4[i9 + 1] = v;
                this.f271c = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(@NonNull SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        int i2 = simpleArrayMap.f271c;
        ensureCapacity(this.f271c + i2);
        if (this.f271c != 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                put(simpleArrayMap.keyAt(i3), simpleArrayMap.valueAt(i3));
            }
        } else if (i2 > 0) {
            System.arraycopy(simpleArrayMap.a, 0, this.a, 0, i2);
            System.arraycopy(simpleArrayMap.b, 0, this.b, 0, i2 << 1);
            this.f271c = i2;
        }
    }

    @Nullable
    public V putIfAbsent(K k, V v) {
        V v2 = get(k);
        return v2 == null ? put(k, v) : v2;
    }

    @Nullable
    public V remove(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public boolean remove(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey < 0) {
            return false;
        }
        V valueAt = valueAt(indexOfKey);
        if (obj2 != valueAt && (obj2 == null || !obj2.equals(valueAt))) {
            return false;
        }
        removeAt(indexOfKey);
        return true;
    }

    public V removeAt(int i2) {
        Object[] objArr = this.b;
        int i3 = i2 << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.f271c;
        int i5 = 0;
        if (i4 <= 1) {
            c(this.a, objArr, i4);
            this.a = a.a;
            this.b = a.f274c;
        } else {
            int i6 = i4 - 1;
            int[] iArr = this.a;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    int i8 = i6 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.b;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.b;
                int i9 = i6 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                if (i4 != this.f271c) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.a, 0, i2);
                    System.arraycopy(objArr, 0, this.b, 0, i3);
                }
                if (i2 < i6) {
                    int i10 = i2 + 1;
                    int i11 = i6 - i2;
                    System.arraycopy(iArr, i10, this.a, i2, i11);
                    System.arraycopy(objArr, i10 << 1, this.b, i3, i11 << 1);
                }
            }
            i5 = i6;
        }
        if (i4 != this.f271c) {
            throw new ConcurrentModificationException();
        }
        this.f271c = i5;
        return v;
    }

    @Nullable
    public V replace(K k, V v) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey >= 0) {
            return setValueAt(indexOfKey, v);
        }
        return null;
    }

    public boolean replace(K k, V v, V v2) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey < 0) {
            return false;
        }
        V valueAt = valueAt(indexOfKey);
        if (valueAt != v && (v == null || !v.equals(valueAt))) {
            return false;
        }
        setValueAt(indexOfKey, v2);
        return true;
    }

    public V setValueAt(int i2, V v) {
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.b;
        V v2 = (V) objArr[i3];
        objArr[i3] = v;
        return v2;
    }

    public int size() {
        return this.f271c;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f271c * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f271c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            K keyAt = keyAt(i2);
            if (keyAt != this) {
                sb.append(keyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V valueAt = valueAt(i2);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public V valueAt(int i2) {
        return (V) this.b[(i2 << 1) + 1];
    }
}
