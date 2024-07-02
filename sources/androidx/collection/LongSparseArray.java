package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class LongSparseArray<E> implements Cloneable {
    private static final Object e = new Object();
    private boolean a;
    private long[] b;

    /* renamed from: c, reason: collision with root package name */
    private Object[] f266c;

    /* renamed from: d, reason: collision with root package name */
    private int f267d;

    public LongSparseArray() {
        this(10);
    }

    public LongSparseArray(int i2) {
        this.a = false;
        if (i2 == 0) {
            this.b = a.b;
            this.f266c = a.f274c;
        } else {
            int f = a.f(i2);
            this.b = new long[f];
            this.f266c = new Object[f];
        }
    }

    private void a() {
        int i2 = this.f267d;
        long[] jArr = this.b;
        Object[] objArr = this.f266c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != e) {
                if (i4 != i3) {
                    jArr[i3] = jArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.a = false;
        this.f267d = i3;
    }

    public void append(long j, E e2) {
        int i2 = this.f267d;
        if (i2 != 0 && j <= this.b[i2 - 1]) {
            put(j, e2);
            return;
        }
        if (this.a && i2 >= this.b.length) {
            a();
        }
        int i3 = this.f267d;
        if (i3 >= this.b.length) {
            int f = a.f(i3 + 1);
            long[] jArr = new long[f];
            Object[] objArr = new Object[f];
            long[] jArr2 = this.b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f266c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = jArr;
            this.f266c = objArr;
        }
        this.b[i3] = j;
        this.f266c[i3] = e2;
        this.f267d = i3 + 1;
    }

    public void clear() {
        int i2 = this.f267d;
        Object[] objArr = this.f266c;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f267d = 0;
        this.a = false;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LongSparseArray<E> m6clone() {
        try {
            LongSparseArray<E> longSparseArray = (LongSparseArray) super.clone();
            longSparseArray.b = (long[]) this.b.clone();
            longSparseArray.f266c = (Object[]) this.f266c.clone();
            return longSparseArray;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean containsKey(long j) {
        return indexOfKey(j) >= 0;
    }

    public boolean containsValue(E e2) {
        return indexOfValue(e2) >= 0;
    }

    @Deprecated
    public void delete(long j) {
        remove(j);
    }

    @Nullable
    public E get(long j) {
        return get(j, null);
    }

    public E get(long j, E e2) {
        int b = a.b(this.b, this.f267d, j);
        if (b >= 0) {
            Object[] objArr = this.f266c;
            if (objArr[b] != e) {
                return (E) objArr[b];
            }
        }
        return e2;
    }

    public int indexOfKey(long j) {
        if (this.a) {
            a();
        }
        return a.b(this.b, this.f267d, j);
    }

    public int indexOfValue(E e2) {
        if (this.a) {
            a();
        }
        for (int i2 = 0; i2 < this.f267d; i2++) {
            if (this.f266c[i2] == e2) {
                return i2;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public long keyAt(int i2) {
        if (this.a) {
            a();
        }
        return this.b[i2];
    }

    public void put(long j, E e2) {
        int b = a.b(this.b, this.f267d, j);
        if (b >= 0) {
            this.f266c[b] = e2;
            return;
        }
        int i2 = b ^ (-1);
        int i3 = this.f267d;
        if (i2 < i3) {
            Object[] objArr = this.f266c;
            if (objArr[i2] == e) {
                this.b[i2] = j;
                objArr[i2] = e2;
                return;
            }
        }
        if (this.a && i3 >= this.b.length) {
            a();
            i2 = a.b(this.b, this.f267d, j) ^ (-1);
        }
        int i4 = this.f267d;
        if (i4 >= this.b.length) {
            int f = a.f(i4 + 1);
            long[] jArr = new long[f];
            Object[] objArr2 = new Object[f];
            long[] jArr2 = this.b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f266c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = jArr;
            this.f266c = objArr2;
        }
        int i5 = this.f267d;
        if (i5 - i2 != 0) {
            long[] jArr3 = this.b;
            int i6 = i2 + 1;
            System.arraycopy(jArr3, i2, jArr3, i6, i5 - i2);
            Object[] objArr4 = this.f266c;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f267d - i2);
        }
        this.b[i2] = j;
        this.f266c[i2] = e2;
        this.f267d++;
    }

    public void putAll(@NonNull LongSparseArray<? extends E> longSparseArray) {
        int size = longSparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            put(longSparseArray.keyAt(i2), longSparseArray.valueAt(i2));
        }
    }

    @Nullable
    public E putIfAbsent(long j, E e2) {
        E e3 = get(j);
        if (e3 == null) {
            put(j, e2);
        }
        return e3;
    }

    public void remove(long j) {
        int b = a.b(this.b, this.f267d, j);
        if (b >= 0) {
            Object[] objArr = this.f266c;
            Object obj = objArr[b];
            Object obj2 = e;
            if (obj != obj2) {
                objArr[b] = obj2;
                this.a = true;
            }
        }
    }

    public boolean remove(long j, Object obj) {
        int indexOfKey = indexOfKey(j);
        if (indexOfKey < 0) {
            return false;
        }
        E valueAt = valueAt(indexOfKey);
        if (obj != valueAt && (obj == null || !obj.equals(valueAt))) {
            return false;
        }
        removeAt(indexOfKey);
        return true;
    }

    public void removeAt(int i2) {
        Object[] objArr = this.f266c;
        Object obj = objArr[i2];
        Object obj2 = e;
        if (obj != obj2) {
            objArr[i2] = obj2;
            this.a = true;
        }
    }

    @Nullable
    public E replace(long j, E e2) {
        int indexOfKey = indexOfKey(j);
        if (indexOfKey < 0) {
            return null;
        }
        Object[] objArr = this.f266c;
        E e3 = (E) objArr[indexOfKey];
        objArr[indexOfKey] = e2;
        return e3;
    }

    public boolean replace(long j, E e2, E e3) {
        int indexOfKey = indexOfKey(j);
        if (indexOfKey < 0) {
            return false;
        }
        Object obj = this.f266c[indexOfKey];
        if (obj != e2 && (e2 == null || !e2.equals(obj))) {
            return false;
        }
        this.f266c[indexOfKey] = e3;
        return true;
    }

    public void setValueAt(int i2, E e2) {
        if (this.a) {
            a();
        }
        this.f266c[i2] = e2;
    }

    public int size() {
        if (this.a) {
            a();
        }
        return this.f267d;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f267d * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f267d; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i2));
            sb.append('=');
            E valueAt = valueAt(i2);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public E valueAt(int i2) {
        if (this.a) {
            a();
        }
        return (E) this.f266c[i2];
    }
}
