package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class SparseArrayCompat<E> implements Cloneable {
    private static final Object e = new Object();
    private boolean a;
    private int[] b;

    /* renamed from: c, reason: collision with root package name */
    private Object[] f272c;

    /* renamed from: d, reason: collision with root package name */
    private int f273d;

    public SparseArrayCompat() {
        this(10);
    }

    public SparseArrayCompat(int i2) {
        this.a = false;
        if (i2 == 0) {
            this.b = a.a;
            this.f272c = a.f274c;
        } else {
            int e2 = a.e(i2);
            this.b = new int[e2];
            this.f272c = new Object[e2];
        }
    }

    private void a() {
        int i2 = this.f273d;
        int[] iArr = this.b;
        Object[] objArr = this.f272c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != e) {
                if (i4 != i3) {
                    iArr[i3] = iArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.a = false;
        this.f273d = i3;
    }

    public void append(int i2, E e2) {
        int i3 = this.f273d;
        if (i3 != 0 && i2 <= this.b[i3 - 1]) {
            put(i2, e2);
            return;
        }
        if (this.a && i3 >= this.b.length) {
            a();
        }
        int i4 = this.f273d;
        if (i4 >= this.b.length) {
            int e3 = a.e(i4 + 1);
            int[] iArr = new int[e3];
            Object[] objArr = new Object[e3];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.f272c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = iArr;
            this.f272c = objArr;
        }
        this.b[i4] = i2;
        this.f272c[i4] = e2;
        this.f273d = i4 + 1;
    }

    public void clear() {
        int i2 = this.f273d;
        Object[] objArr = this.f272c;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.f273d = 0;
        this.a = false;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SparseArrayCompat<E> m7clone() {
        try {
            SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat) super.clone();
            sparseArrayCompat.b = (int[]) this.b.clone();
            sparseArrayCompat.f272c = (Object[]) this.f272c.clone();
            return sparseArrayCompat;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean containsKey(int i2) {
        return indexOfKey(i2) >= 0;
    }

    public boolean containsValue(E e2) {
        return indexOfValue(e2) >= 0;
    }

    @Deprecated
    public void delete(int i2) {
        remove(i2);
    }

    @Nullable
    public E get(int i2) {
        return get(i2, null);
    }

    public E get(int i2, E e2) {
        int a = a.a(this.b, this.f273d, i2);
        if (a >= 0) {
            Object[] objArr = this.f272c;
            if (objArr[a] != e) {
                return (E) objArr[a];
            }
        }
        return e2;
    }

    public int indexOfKey(int i2) {
        if (this.a) {
            a();
        }
        return a.a(this.b, this.f273d, i2);
    }

    public int indexOfValue(E e2) {
        if (this.a) {
            a();
        }
        for (int i2 = 0; i2 < this.f273d; i2++) {
            if (this.f272c[i2] == e2) {
                return i2;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int keyAt(int i2) {
        if (this.a) {
            a();
        }
        return this.b[i2];
    }

    public void put(int i2, E e2) {
        int a = a.a(this.b, this.f273d, i2);
        if (a >= 0) {
            this.f272c[a] = e2;
            return;
        }
        int i3 = a ^ (-1);
        int i4 = this.f273d;
        if (i3 < i4) {
            Object[] objArr = this.f272c;
            if (objArr[i3] == e) {
                this.b[i3] = i2;
                objArr[i3] = e2;
                return;
            }
        }
        if (this.a && i4 >= this.b.length) {
            a();
            i3 = a.a(this.b, this.f273d, i2) ^ (-1);
        }
        int i5 = this.f273d;
        if (i5 >= this.b.length) {
            int e3 = a.e(i5 + 1);
            int[] iArr = new int[e3];
            Object[] objArr2 = new Object[e3];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f272c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = iArr;
            this.f272c = objArr2;
        }
        int i6 = this.f273d;
        if (i6 - i3 != 0) {
            int[] iArr3 = this.b;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr4 = this.f272c;
            System.arraycopy(objArr4, i3, objArr4, i7, this.f273d - i3);
        }
        this.b[i3] = i2;
        this.f272c[i3] = e2;
        this.f273d++;
    }

    public void putAll(@NonNull SparseArrayCompat<? extends E> sparseArrayCompat) {
        int size = sparseArrayCompat.size();
        for (int i2 = 0; i2 < size; i2++) {
            put(sparseArrayCompat.keyAt(i2), sparseArrayCompat.valueAt(i2));
        }
    }

    @Nullable
    public E putIfAbsent(int i2, E e2) {
        E e3 = get(i2);
        if (e3 == null) {
            put(i2, e2);
        }
        return e3;
    }

    public void remove(int i2) {
        int a = a.a(this.b, this.f273d, i2);
        if (a >= 0) {
            Object[] objArr = this.f272c;
            Object obj = objArr[a];
            Object obj2 = e;
            if (obj != obj2) {
                objArr[a] = obj2;
                this.a = true;
            }
        }
    }

    public boolean remove(int i2, Object obj) {
        int indexOfKey = indexOfKey(i2);
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
        Object[] objArr = this.f272c;
        Object obj = objArr[i2];
        Object obj2 = e;
        if (obj != obj2) {
            objArr[i2] = obj2;
            this.a = true;
        }
    }

    public void removeAtRange(int i2, int i3) {
        int min = Math.min(this.f273d, i3 + i2);
        while (i2 < min) {
            removeAt(i2);
            i2++;
        }
    }

    @Nullable
    public E replace(int i2, E e2) {
        int indexOfKey = indexOfKey(i2);
        if (indexOfKey < 0) {
            return null;
        }
        Object[] objArr = this.f272c;
        E e3 = (E) objArr[indexOfKey];
        objArr[indexOfKey] = e2;
        return e3;
    }

    public boolean replace(int i2, E e2, E e3) {
        int indexOfKey = indexOfKey(i2);
        if (indexOfKey < 0) {
            return false;
        }
        Object obj = this.f272c[indexOfKey];
        if (obj != e2 && (e2 == null || !e2.equals(obj))) {
            return false;
        }
        this.f272c[indexOfKey] = e3;
        return true;
    }

    public void setValueAt(int i2, E e2) {
        if (this.a) {
            a();
        }
        this.f272c[i2] = e2;
    }

    public int size() {
        if (this.a) {
            a();
        }
        return this.f273d;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f273d * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f273d; i2++) {
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
        return (E) this.f272c[i2];
    }
}
