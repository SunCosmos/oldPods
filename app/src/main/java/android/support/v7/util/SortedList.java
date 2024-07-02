package android.support.v7.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/* loaded from: lib/Mus.dex */
public class SortedList<T> {
    private static final int CAPACITY_GROWTH = 10;
    private static final int DELETION = 2;
    private static final int INSERTION = 1;
    public static final int INVALID_POSITION = -1;
    private static final int LOOKUP = 4;
    private static final int MIN_CAPACITY = 10;
    private BatchedCallback mBatchedCallback;
    private Callback mCallback;
    T[] mData;
    private int mNewDataStart;
    private T[] mOldData;
    private int mOldDataSize;
    private int mOldDataStart;
    private int mSize;
    private final Class<T> mTClass;

    public SortedList(Class<T> cls, Callback<T> callback) {
        this(cls, callback, 10);
    }

    public SortedList(Class<T> cls, Callback<T> callback, int i2) {
        this.mTClass = cls;
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
        this.mCallback = callback;
        this.mSize = 0;
    }

    public int size() {
        return this.mSize;
    }

    public int add(T t) {
        throwIfInMutationOperation();
        return add(t, true);
    }

    public void addAll(T[] tArr, boolean z) {
        throwIfInMutationOperation();
        if (tArr.length != 0) {
            if (z) {
                addAllInternal(tArr);
            } else {
                addAllInternal(copyArray(tArr));
            }
        }
    }

    public void addAll(T... tArr) {
        addAll(tArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addAll(Collection<T> collection) {
        addAll(collection.toArray((Object[]) Array.newInstance((Class<?>) this.mTClass, collection.size())), true);
    }

    public void replaceAll(@NonNull T[] tArr, boolean z) {
        throwIfInMutationOperation();
        if (z) {
            replaceAllInternal(tArr);
        } else {
            replaceAllInternal(copyArray(tArr));
        }
    }

    public void replaceAll(@NonNull T... tArr) {
        replaceAll(tArr, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void replaceAll(@NonNull Collection<T> collection) {
        replaceAll(collection.toArray((Object[]) Array.newInstance((Class<?>) this.mTClass, collection.size())), true);
    }

    private void addAllInternal(T[] tArr) {
        if (tArr.length >= 1) {
            int sortAndDedup = sortAndDedup(tArr);
            if (this.mSize == 0) {
                this.mData = tArr;
                this.mSize = sortAndDedup;
                this.mCallback.onInserted(0, sortAndDedup);
                return;
            }
            merge(tArr, sortAndDedup);
        }
    }

    private void replaceAllInternal(@NonNull T[] tArr) {
        boolean z = !(this.mCallback instanceof BatchedCallback);
        if (z) {
            beginBatchedUpdates();
        }
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mOldData = this.mData;
        this.mNewDataStart = 0;
        int sortAndDedup = sortAndDedup(tArr);
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, sortAndDedup));
        while (true) {
            if (this.mNewDataStart >= sortAndDedup && this.mOldDataStart >= this.mOldDataSize) {
                break;
            }
            if (this.mOldDataStart >= this.mOldDataSize) {
                int i2 = this.mNewDataStart;
                int i3 = sortAndDedup - this.mNewDataStart;
                System.arraycopy(tArr, i2, this.mData, i2, i3);
                this.mNewDataStart += i3;
                this.mSize += i3;
                this.mCallback.onInserted(i2, i3);
                break;
            }
            if (this.mNewDataStart >= sortAndDedup) {
                int i4 = this.mOldDataSize - this.mOldDataStart;
                this.mSize -= i4;
                this.mCallback.onRemoved(this.mNewDataStart, i4);
                break;
            }
            T t = this.mOldData[this.mOldDataStart];
            T t2 = tArr[this.mNewDataStart];
            int compare = this.mCallback.compare(t, t2);
            if (compare < 0) {
                replaceAllRemove();
            } else if (compare > 0) {
                replaceAllInsert(t2);
            } else if (this.mCallback.areItemsTheSame(t, t2)) {
                this.mData[this.mNewDataStart] = t2;
                this.mOldDataStart++;
                this.mNewDataStart++;
                if (!this.mCallback.areContentsTheSame(t, t2)) {
                    this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(t, t2));
                }
            } else {
                replaceAllRemove();
                replaceAllInsert(t2);
            }
        }
        this.mOldData = null;
        if (z) {
            endBatchedUpdates();
        }
    }

    private void replaceAllInsert(T t) {
        this.mData[this.mNewDataStart] = t;
        this.mNewDataStart++;
        this.mSize++;
        this.mCallback.onInserted(this.mNewDataStart - 1, 1);
    }

    private void replaceAllRemove() {
        this.mSize--;
        this.mOldDataStart++;
        this.mCallback.onRemoved(this.mNewDataStart, 1);
    }

    private int sortAndDedup(@NonNull T[] tArr) {
        if (tArr.length == 0) {
            return 0;
        }
        Arrays.sort(tArr, this.mCallback);
        int i2 = 0;
        int i3 = 1;
        for (int i4 = 1; i4 < tArr.length; i4++) {
            T t = tArr[i4];
            if (this.mCallback.compare(tArr[i2], t) == 0) {
                int findSameItem = findSameItem(t, tArr, i2, i3);
                if (findSameItem != -1) {
                    tArr[findSameItem] = t;
                } else {
                    if (i3 != i4) {
                        tArr[i3] = t;
                    }
                    i3++;
                }
            } else {
                if (i3 != i4) {
                    tArr[i3] = t;
                }
                int i5 = i3;
                i3++;
                i2 = i5;
            }
        }
        return i3;
    }

    private int findSameItem(T t, T[] tArr, int i2, int i3) {
        for (int i4 = i2; i4 < i3; i4++) {
            if (this.mCallback.areItemsTheSame(tArr[i4], t)) {
                return i4;
            }
        }
        return -1;
    }

    private void merge(T[] tArr, int i2) {
        boolean z = !(this.mCallback instanceof BatchedCallback);
        if (z) {
            beginBatchedUpdates();
        }
        this.mOldData = this.mData;
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, this.mSize + i2 + 10));
        this.mNewDataStart = 0;
        int i3 = 0;
        while (true) {
            if (this.mOldDataStart >= this.mOldDataSize && i3 >= i2) {
                break;
            }
            if (this.mOldDataStart == this.mOldDataSize) {
                int i4 = i2 - i3;
                System.arraycopy(tArr, i3, this.mData, this.mNewDataStart, i4);
                this.mNewDataStart += i4;
                this.mSize += i4;
                this.mCallback.onInserted(this.mNewDataStart - i4, i4);
                break;
            }
            if (i3 == i2) {
                int i5 = this.mOldDataSize - this.mOldDataStart;
                System.arraycopy(this.mOldData, this.mOldDataStart, this.mData, this.mNewDataStart, i5);
                this.mNewDataStart += i5;
                break;
            }
            T t = this.mOldData[this.mOldDataStart];
            T t2 = tArr[i3];
            int compare = this.mCallback.compare(t, t2);
            if (compare > 0) {
                T[] tArr2 = this.mData;
                int i6 = this.mNewDataStart;
                this.mNewDataStart = i6 + 1;
                tArr2[i6] = t2;
                this.mSize++;
                i3++;
                this.mCallback.onInserted(this.mNewDataStart - 1, 1);
            } else if (compare == 0 && this.mCallback.areItemsTheSame(t, t2)) {
                T[] tArr3 = this.mData;
                int i7 = this.mNewDataStart;
                this.mNewDataStart = i7 + 1;
                tArr3[i7] = t2;
                i3++;
                this.mOldDataStart++;
                if (!this.mCallback.areContentsTheSame(t, t2)) {
                    this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(t, t2));
                }
            } else {
                T[] tArr4 = this.mData;
                int i8 = this.mNewDataStart;
                this.mNewDataStart = i8 + 1;
                tArr4[i8] = t;
                this.mOldDataStart++;
            }
        }
        this.mOldData = null;
        if (z) {
            endBatchedUpdates();
        }
    }

    private void throwIfInMutationOperation() {
        if (this.mOldData != null) {
            throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
        }
    }

    public void beginBatchedUpdates() {
        throwIfInMutationOperation();
        if (!(this.mCallback instanceof BatchedCallback)) {
            if (this.mBatchedCallback == null) {
                this.mBatchedCallback = new BatchedCallback(this.mCallback);
            }
            this.mCallback = this.mBatchedCallback;
        }
    }

    public void endBatchedUpdates() {
        throwIfInMutationOperation();
        if (this.mCallback instanceof BatchedCallback) {
            ((BatchedCallback) this.mCallback).dispatchLastEvent();
        }
        if (this.mCallback == this.mBatchedCallback) {
            this.mCallback = this.mBatchedCallback.mWrappedCallback;
        }
    }

    private int add(T t, boolean z) {
        int findIndexOf = findIndexOf(t, this.mData, 0, this.mSize, 1);
        if (findIndexOf == -1) {
            findIndexOf = 0;
        } else if (findIndexOf < this.mSize) {
            T t2 = this.mData[findIndexOf];
            if (this.mCallback.areItemsTheSame(t2, t)) {
                if (this.mCallback.areContentsTheSame(t2, t)) {
                    this.mData[findIndexOf] = t;
                    return findIndexOf;
                }
                this.mData[findIndexOf] = t;
                this.mCallback.onChanged(findIndexOf, 1, this.mCallback.getChangePayload(t2, t));
                return findIndexOf;
            }
        }
        addToData(findIndexOf, t);
        if (z) {
            this.mCallback.onInserted(findIndexOf, 1);
        }
        return findIndexOf;
    }

    public boolean remove(T t) {
        throwIfInMutationOperation();
        return remove(t, true);
    }

    public T removeItemAt(int i2) {
        throwIfInMutationOperation();
        T t = get(i2);
        removeItemAtIndex(i2, true);
        return t;
    }

    private boolean remove(T t, boolean z) {
        int findIndexOf = findIndexOf(t, this.mData, 0, this.mSize, 2);
        if (findIndexOf == -1) {
            return false;
        }
        removeItemAtIndex(findIndexOf, z);
        return true;
    }

    private void removeItemAtIndex(int i2, boolean z) {
        System.arraycopy(this.mData, i2 + 1, this.mData, i2, (this.mSize - i2) - 1);
        this.mSize--;
        this.mData[this.mSize] = null;
        if (z) {
            this.mCallback.onRemoved(i2, 1);
        }
    }

    public void updateItemAt(int i2, T t) {
        throwIfInMutationOperation();
        T t2 = get(i2);
        boolean z = t2 == t || !this.mCallback.areContentsTheSame(t2, t);
        if (t2 != t && this.mCallback.compare(t2, t) == 0) {
            this.mData[i2] = t;
            if (z) {
                this.mCallback.onChanged(i2, 1, this.mCallback.getChangePayload(t2, t));
                return;
            }
            return;
        }
        if (z) {
            this.mCallback.onChanged(i2, 1, this.mCallback.getChangePayload(t2, t));
        }
        removeItemAtIndex(i2, false);
        int add = add(t, false);
        if (i2 != add) {
            this.mCallback.onMoved(i2, add);
        }
    }

    public void recalculatePositionOfItemAt(int i2) {
        throwIfInMutationOperation();
        T t = get(i2);
        removeItemAtIndex(i2, false);
        int add = add(t, false);
        if (i2 != add) {
            this.mCallback.onMoved(i2, add);
        }
    }

    public T get(int i2) throws IndexOutOfBoundsException {
        if (i2 >= this.mSize || i2 < 0) {
            throw new IndexOutOfBoundsException("Asked to get item at " + i2 + " but size is " + this.mSize);
        }
        return (this.mOldData == null || i2 < this.mNewDataStart) ? this.mData[i2] : this.mOldData[(i2 - this.mNewDataStart) + this.mOldDataStart];
    }

    public int indexOf(T t) {
        if (this.mOldData != null) {
            int findIndexOf = findIndexOf(t, this.mData, 0, this.mNewDataStart, 4);
            if (findIndexOf != -1) {
                return findIndexOf;
            }
            int findIndexOf2 = findIndexOf(t, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
            if (findIndexOf2 != -1) {
                return (findIndexOf2 - this.mOldDataStart) + this.mNewDataStart;
            }
            return -1;
        }
        return findIndexOf(t, this.mData, 0, this.mSize, 4);
    }

    private int findIndexOf(T t, T[] tArr, int i2, int i3, int i4) {
        int i5 = i2;
        int i6 = i3;
        while (i5 < i6) {
            int i7 = (i5 + i6) / 2;
            T t2 = tArr[i7];
            int compare = this.mCallback.compare(t2, t);
            if (compare < 0) {
                i5 = i7 + 1;
            } else {
                if (compare == 0) {
                    if (this.mCallback.areItemsTheSame(t2, t)) {
                        return i7;
                    }
                    int linearEqualitySearch = linearEqualitySearch(t, i7, i5, i6);
                    if (i4 == 1) {
                        return linearEqualitySearch == -1 ? i7 : linearEqualitySearch;
                    }
                    return linearEqualitySearch;
                }
                i6 = i7;
            }
        }
        return i4 == 1 ? i5 : -1;
    }

    private int linearEqualitySearch(T t, int i2, int i3, int i4) {
        for (int i5 = i2 - 1; i5 >= i3; i5--) {
            T t2 = this.mData[i5];
            if (this.mCallback.compare(t2, t) != 0) {
                break;
            }
            if (this.mCallback.areItemsTheSame(t2, t)) {
                return i5;
            }
        }
        for (int i6 = i2 + 1; i6 < i4; i6++) {
            T t3 = this.mData[i6];
            if (this.mCallback.compare(t3, t) != 0) {
                break;
            }
            if (this.mCallback.areItemsTheSame(t3, t)) {
                return i6;
            }
        }
        return -1;
    }

    private void addToData(int i2, T t) {
        if (i2 <= this.mSize) {
            if (this.mSize == this.mData.length) {
                T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, this.mData.length + 10));
                System.arraycopy(this.mData, 0, tArr, 0, i2);
                tArr[i2] = t;
                System.arraycopy(this.mData, i2, tArr, i2 + 1, this.mSize - i2);
                this.mData = tArr;
            } else {
                System.arraycopy(this.mData, i2, this.mData, i2 + 1, this.mSize - i2);
                this.mData[i2] = t;
            }
            this.mSize++;
            return;
        }
        throw new IndexOutOfBoundsException("cannot add item to " + i2 + " because size is " + this.mSize);
    }

    private T[] copyArray(T[] tArr) {
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, tArr.length));
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        return tArr2;
    }

    public void clear() {
        throwIfInMutationOperation();
        if (this.mSize != 0) {
            int i2 = this.mSize;
            Arrays.fill(this.mData, 0, i2, (Object) null);
            this.mSize = 0;
            this.mCallback.onRemoved(0, i2);
        }
    }

    /* loaded from: lib/Mus.dex */
    public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
        public abstract boolean areContentsTheSame(T2 t2, T2 t22);

        public abstract boolean areItemsTheSame(T2 t2, T2 t22);

        @Override // java.util.Comparator
        public abstract int compare(T2 t2, T2 t22);

        public abstract void onChanged(int i2, int i3);

        public void onChanged(int i2, int i3, Object obj) {
            onChanged(i2, i3);
        }

        @Nullable
        public Object getChangePayload(T2 t2, T2 t22) {
            return null;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static class BatchedCallback<T2> extends Callback<T2> {
        private final BatchingListUpdateCallback mBatchingListUpdateCallback;
        final Callback<T2> mWrappedCallback;

        public BatchedCallback(Callback<T2> callback) {
            this.mWrappedCallback = callback;
            this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(this.mWrappedCallback);
        }

        @Override // android.support.v7.util.SortedList.Callback, java.util.Comparator
        public int compare(T2 t2, T2 t22) {
            return this.mWrappedCallback.compare(t2, t22);
        }

        @Override // android.support.v7.util.ListUpdateCallback
        public void onInserted(int i2, int i3) {
            this.mBatchingListUpdateCallback.onInserted(i2, i3);
        }

        @Override // android.support.v7.util.ListUpdateCallback
        public void onRemoved(int i2, int i3) {
            this.mBatchingListUpdateCallback.onRemoved(i2, i3);
        }

        @Override // android.support.v7.util.ListUpdateCallback
        public void onMoved(int i2, int i3) {
            this.mBatchingListUpdateCallback.onMoved(i2, i3);
        }

        @Override // android.support.v7.util.SortedList.Callback
        public void onChanged(int i2, int i3) {
            this.mBatchingListUpdateCallback.onChanged(i2, i3, null);
        }

        @Override // android.support.v7.util.SortedList.Callback, android.support.v7.util.ListUpdateCallback
        public void onChanged(int i2, int i3, Object obj) {
            this.mBatchingListUpdateCallback.onChanged(i2, i3, obj);
        }

        @Override // android.support.v7.util.SortedList.Callback
        public boolean areContentsTheSame(T2 t2, T2 t22) {
            return this.mWrappedCallback.areContentsTheSame(t2, t22);
        }

        @Override // android.support.v7.util.SortedList.Callback
        public boolean areItemsTheSame(T2 t2, T2 t22) {
            return this.mWrappedCallback.areItemsTheSame(t2, t22);
        }

        @Override // android.support.v7.util.SortedList.Callback
        @Nullable
        public Object getChangePayload(T2 t2, T2 t22) {
            return this.mWrappedCallback.getChangePayload(t2, t22);
        }

        public void dispatchLastEvent() {
            this.mBatchingListUpdateCallback.dispatchLastEvent();
        }
    }
}
