package android.support.v4.util;

/* loaded from: lib/Mus.dex */
public class SparseArrayCompat<E> implements Cloneable {
    private static final Object DELETED = new Object();
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;

    public SparseArrayCompat() {
        this(10);
    }

    public SparseArrayCompat(int i2) {
        this.mGarbage = false;
        if (i2 == 0) {
            this.mKeys = ContainerHelpers.EMPTY_INTS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            int idealIntArraySize = ContainerHelpers.idealIntArraySize(i2);
            this.mKeys = new int[idealIntArraySize];
            this.mValues = new Object[idealIntArraySize];
        }
        this.mSize = 0;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SparseArrayCompat<E> m4clone() {
        SparseArrayCompat<E> sparseArrayCompat = null;
        try {
            sparseArrayCompat = (SparseArrayCompat) super.clone();
            sparseArrayCompat.mKeys = (int[]) this.mKeys.clone();
            sparseArrayCompat.mValues = (Object[]) this.mValues.clone();
        } catch (CloneNotSupportedException e) {
        }
        return sparseArrayCompat;
    }

    public E get(int i2) {
        return get(i2, null);
    }

    public E get(int i2, E e) {
        int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i2);
        if (binarySearch >= 0 && this.mValues[binarySearch] != DELETED) {
            return (E) this.mValues[binarySearch];
        }
        return e;
    }

    public void delete(int i2) {
        int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i2);
        if (binarySearch >= 0 && this.mValues[binarySearch] != DELETED) {
            this.mValues[binarySearch] = DELETED;
            this.mGarbage = true;
        }
    }

    public void remove(int i2) {
        delete(i2);
    }

    public void removeAt(int i2) {
        if (this.mValues[i2] != DELETED) {
            this.mValues[i2] = DELETED;
            this.mGarbage = true;
        }
    }

    public void removeAtRange(int i2, int i3) {
        int min = Math.min(this.mSize, i2 + i3);
        for (int i4 = i2; i4 < min; i4++) {
            removeAt(i4);
        }
    }

    private void gc() {
        int i2 = this.mSize;
        int i3 = 0;
        int[] iArr = this.mKeys;
        Object[] objArr = this.mValues;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != DELETED) {
                if (i4 != i3) {
                    iArr[i3] = iArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.mGarbage = false;
        this.mSize = i3;
    }

    public void put(int i2, E e) {
        int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i2);
        if (binarySearch >= 0) {
            this.mValues[binarySearch] = e;
            return;
        }
        int i3 = binarySearch ^ (-1);
        if (i3 < this.mSize && this.mValues[i3] == DELETED) {
            this.mKeys[i3] = i2;
            this.mValues[i3] = e;
            return;
        }
        if (this.mGarbage && this.mSize >= this.mKeys.length) {
            gc();
            i3 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, i2) ^ (-1);
        }
        if (this.mSize >= this.mKeys.length) {
            int idealIntArraySize = ContainerHelpers.idealIntArraySize(this.mSize + 1);
            int[] iArr = new int[idealIntArraySize];
            Object[] objArr = new Object[idealIntArraySize];
            System.arraycopy(this.mKeys, 0, iArr, 0, this.mKeys.length);
            System.arraycopy(this.mValues, 0, objArr, 0, this.mValues.length);
            this.mKeys = iArr;
            this.mValues = objArr;
        }
        if (this.mSize - i3 != 0) {
            System.arraycopy(this.mKeys, i3, this.mKeys, i3 + 1, this.mSize - i3);
            System.arraycopy(this.mValues, i3, this.mValues, i3 + 1, this.mSize - i3);
        }
        this.mKeys[i3] = i2;
        this.mValues[i3] = e;
        this.mSize++;
    }

    public int size() {
        if (this.mGarbage) {
            gc();
        }
        return this.mSize;
    }

    public int keyAt(int i2) {
        if (this.mGarbage) {
            gc();
        }
        return this.mKeys[i2];
    }

    public E valueAt(int i2) {
        if (this.mGarbage) {
            gc();
        }
        return (E) this.mValues[i2];
    }

    public void setValueAt(int i2, E e) {
        if (this.mGarbage) {
            gc();
        }
        this.mValues[i2] = e;
    }

    public int indexOfKey(int i2) {
        if (this.mGarbage) {
            gc();
        }
        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, i2);
    }

    public int indexOfValue(E e) {
        if (this.mGarbage) {
            gc();
        }
        for (int i2 = 0; i2 < this.mSize; i2++) {
            if (this.mValues[i2] == e) {
                return i2;
            }
        }
        return -1;
    }

    public void clear() {
        int i2 = this.mSize;
        Object[] objArr = this.mValues;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }

    public void append(int i2, E e) {
        if (this.mSize != 0 && i2 <= this.mKeys[this.mSize - 1]) {
            put(i2, e);
            return;
        }
        if (this.mGarbage && this.mSize >= this.mKeys.length) {
            gc();
        }
        int i3 = this.mSize;
        if (i3 >= this.mKeys.length) {
            int idealIntArraySize = ContainerHelpers.idealIntArraySize(i3 + 1);
            int[] iArr = new int[idealIntArraySize];
            Object[] objArr = new Object[idealIntArraySize];
            System.arraycopy(this.mKeys, 0, iArr, 0, this.mKeys.length);
            System.arraycopy(this.mValues, 0, objArr, 0, this.mValues.length);
            this.mKeys = iArr;
            this.mValues = objArr;
        }
        this.mKeys[i3] = i2;
        this.mValues[i3] = e;
        this.mSize = i3 + 1;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.mSize; i2++) {
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
}
