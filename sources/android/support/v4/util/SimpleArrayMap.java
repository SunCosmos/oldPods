package android.support.v4.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* loaded from: lib/Mus.dex */
public class SimpleArrayMap<K, V> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;

    private static int binarySearchHashes(int[] iArr, int i2, int i3) {
        try {
            return ContainerHelpers.binarySearch(iArr, i2, i3);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    int indexOf(Object obj, int i2) {
        int i3 = this.mSize;
        if (i3 == 0) {
            return -1;
        }
        int binarySearchHashes = binarySearchHashes(this.mHashes, i3, i2);
        if (binarySearchHashes < 0) {
            return binarySearchHashes;
        }
        if (obj.equals(this.mArray[binarySearchHashes << 1])) {
            return binarySearchHashes;
        }
        int i4 = binarySearchHashes + 1;
        while (i4 < i3 && this.mHashes[i4] == i2) {
            if (obj.equals(this.mArray[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = binarySearchHashes - 1; i5 >= 0 && this.mHashes[i5] == i2; i5--) {
            if (obj.equals(this.mArray[i5 << 1])) {
                return i5;
            }
        }
        return i4 ^ (-1);
    }

    int indexOfNull() {
        int i2 = this.mSize;
        if (i2 == 0) {
            return -1;
        }
        int binarySearchHashes = binarySearchHashes(this.mHashes, i2, 0);
        if (binarySearchHashes < 0) {
            return binarySearchHashes;
        }
        if (null == this.mArray[binarySearchHashes << 1]) {
            return binarySearchHashes;
        }
        int i3 = binarySearchHashes + 1;
        while (i3 < i2 && this.mHashes[i3] == 0) {
            if (null == this.mArray[i3 << 1]) {
                return i3;
            }
            i3++;
        }
        for (int i4 = binarySearchHashes - 1; i4 >= 0 && this.mHashes[i4] == 0; i4--) {
            if (null == this.mArray[i4 << 1]) {
                return i4;
            }
        }
        return i3 ^ (-1);
    }

    private void allocArrays(int i2) {
        if (i2 == 8) {
            synchronized (ArrayMap.class) {
                if (mTwiceBaseCache != null) {
                    Object[] objArr = mTwiceBaseCache;
                    this.mArray = objArr;
                    mTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    mTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (ArrayMap.class) {
                if (mBaseCache != null) {
                    Object[] objArr2 = mBaseCache;
                    this.mArray = objArr2;
                    mBaseCache = (Object[]) objArr2[0];
                    this.mHashes = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    mBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i2];
        this.mArray = new Object[i2 << 1];
    }

    private static void freeArrays(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (ArrayMap.class) {
                if (mTwiceBaseCacheSize < 10) {
                    objArr[0] = mTwiceBaseCache;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    mTwiceBaseCache = objArr;
                    mTwiceBaseCacheSize++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (ArrayMap.class) {
                if (mBaseCacheSize < 10) {
                    objArr[0] = mBaseCache;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    mBaseCache = objArr;
                    mBaseCacheSize++;
                }
            }
        }
    }

    public SimpleArrayMap() {
        this.mHashes = ContainerHelpers.EMPTY_INTS;
        this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        this.mSize = 0;
    }

    public SimpleArrayMap(int i2) {
        if (i2 == 0) {
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            allocArrays(i2);
        }
        this.mSize = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleArrayMap(SimpleArrayMap<K, V> simpleArrayMap) {
        this();
        if (simpleArrayMap != 0) {
            putAll(simpleArrayMap);
        }
    }

    public void clear() {
        if (this.mSize > 0) {
            int[] iArr = this.mHashes;
            Object[] objArr = this.mArray;
            int i2 = this.mSize;
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
            freeArrays(iArr, objArr, i2);
        }
        if (this.mSize > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public void ensureCapacity(int i2) {
        int i3 = this.mSize;
        if (this.mHashes.length < i2) {
            int[] iArr = this.mHashes;
            Object[] objArr = this.mArray;
            allocArrays(i2);
            if (this.mSize > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, i3);
                System.arraycopy(objArr, 0, this.mArray, 0, i3 << 1);
            }
            freeArrays(iArr, objArr, i3);
        }
        if (this.mSize != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return indexOfKey(obj) >= 0;
    }

    public int indexOfKey(Object obj) {
        return obj == null ? indexOfNull() : indexOf(obj, obj.hashCode());
    }

    public int indexOfValue(Object obj) {
        int i2 = this.mSize * 2;
        Object[] objArr = this.mArray;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
        } else {
            for (int i4 = 1; i4 < i2; i4 += 2) {
                if (obj.equals(objArr[i4])) {
                    return i4 >> 1;
                }
            }
        }
        return -1;
    }

    public boolean containsValue(Object obj) {
        return indexOfValue(obj) >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v7 */
    public V get(Object obj) {
        int indexOfKey = indexOfKey(obj);
        return indexOfKey >= 0 ? this.mArray[(indexOfKey << 1) + 1] : null;
    }

    public K keyAt(int i2) {
        return (K) this.mArray[i2 << 1];
    }

    public V valueAt(int i2) {
        return (V) this.mArray[(i2 << 1) + 1];
    }

    public V setValueAt(int i2, V v) {
        int i3 = (i2 << 1) + 1;
        V v2 = (V) this.mArray[i3];
        this.mArray[i3] = v;
        return v2;
    }

    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    public V put(K k, V v) {
        int hashCode;
        int indexOf;
        int i2;
        int i3 = this.mSize;
        if (k == null) {
            hashCode = 0;
            indexOf = indexOfNull();
        } else {
            hashCode = k.hashCode();
            indexOf = indexOf(k, hashCode);
        }
        if (indexOf >= 0) {
            int i4 = (indexOf << 1) + 1;
            V v2 = (V) this.mArray[i4];
            this.mArray[i4] = v;
            return v2;
        }
        int i5 = indexOf ^ (-1);
        if (i3 >= this.mHashes.length) {
            if (i3 >= 8) {
                i2 = i3 + (i3 >> 1);
            } else {
                i2 = i3 >= 4 ? 8 : 4;
            }
            int i6 = i2;
            int[] iArr = this.mHashes;
            Object[] objArr = this.mArray;
            allocArrays(i6);
            if (i3 != this.mSize) {
                throw new ConcurrentModificationException();
            }
            if (this.mHashes.length > 0) {
                System.arraycopy(iArr, 0, this.mHashes, 0, iArr.length);
                System.arraycopy(objArr, 0, this.mArray, 0, objArr.length);
            }
            freeArrays(iArr, objArr, i3);
        }
        if (i5 < i3) {
            System.arraycopy(this.mHashes, i5, this.mHashes, i5 + 1, i3 - i5);
            System.arraycopy(this.mArray, i5 << 1, this.mArray, (i5 + 1) << 1, (this.mSize - i5) << 1);
        }
        if (i3 != this.mSize || i5 >= this.mHashes.length) {
            throw new ConcurrentModificationException();
        }
        this.mHashes[i5] = hashCode;
        this.mArray[i5 << 1] = k;
        this.mArray[(i5 << 1) + 1] = v;
        this.mSize++;
        return null;
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        int i2 = simpleArrayMap.mSize;
        ensureCapacity(this.mSize + i2);
        if (this.mSize == 0) {
            if (i2 > 0) {
                System.arraycopy(simpleArrayMap.mHashes, 0, this.mHashes, 0, i2);
                System.arraycopy(simpleArrayMap.mArray, 0, this.mArray, 0, i2 << 1);
                this.mSize = i2;
                return;
            }
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            put(simpleArrayMap.keyAt(i3), simpleArrayMap.valueAt(i3));
        }
    }

    public V remove(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public V removeAt(int i2) {
        int i3;
        V v = (V) this.mArray[(i2 << 1) + 1];
        int i4 = this.mSize;
        if (i4 <= 1) {
            freeArrays(this.mHashes, this.mArray, i4);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            i3 = 0;
        } else {
            i3 = i4 - 1;
            if (this.mHashes.length > 8 && this.mSize < this.mHashes.length / 3) {
                int i5 = i4 > 8 ? i4 + (i4 >> 1) : 8;
                int[] iArr = this.mHashes;
                Object[] objArr = this.mArray;
                allocArrays(i5);
                if (i4 != this.mSize) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.mHashes, 0, i2);
                    System.arraycopy(objArr, 0, this.mArray, 0, i2 << 1);
                }
                if (i2 < i3) {
                    System.arraycopy(iArr, i2 + 1, this.mHashes, i2, i3 - i2);
                    System.arraycopy(objArr, (i2 + 1) << 1, this.mArray, i2 << 1, (i3 - i2) << 1);
                }
            } else {
                if (i2 < i3) {
                    System.arraycopy(this.mHashes, i2 + 1, this.mHashes, i2, i3 - i2);
                    System.arraycopy(this.mArray, (i2 + 1) << 1, this.mArray, i2 << 1, (i3 - i2) << 1);
                }
                this.mArray[i3 << 1] = null;
                this.mArray[(i3 << 1) + 1] = null;
            }
        }
        if (i4 != this.mSize) {
            throw new ConcurrentModificationException();
        }
        this.mSize = i3;
        return v;
    }

    public int size() {
        return this.mSize;
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
            for (int i2 = 0; i2 < this.mSize; i2++) {
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
                } catch (ClassCastException e) {
                    return false;
                } catch (NullPointerException e2) {
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
            for (int i3 = 0; i3 < this.mSize; i3++) {
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
                } catch (ClassCastException e3) {
                    return false;
                } catch (NullPointerException e4) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int[] iArr = this.mHashes;
        Object[] objArr = this.mArray;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        int i5 = this.mSize;
        while (i3 < i5) {
            Object obj = objArr[i4];
            i2 += iArr[i3] ^ (obj == null ? 0 : obj.hashCode());
            i3++;
            i4 += 2;
        }
        return i2;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.mSize; i2++) {
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
}
