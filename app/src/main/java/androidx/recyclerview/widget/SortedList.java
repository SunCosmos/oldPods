package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/* loaded from: classes.dex */
public class SortedList<T> {
    public static final int INVALID_POSITION = -1;
    T[] a;
    private T[] b;

    /* renamed from: c, reason: collision with root package name */
    private int f979c;

    /* renamed from: d, reason: collision with root package name */
    private int f980d;
    private int e;
    private Callback f;
    private BatchedCallback g;
    private int h;

    /* renamed from: i, reason: collision with root package name */
    private final Class<T> f981i;

    /* loaded from: classes.dex */
    public static class BatchedCallback<T2> extends Callback<T2> {
        final Callback<T2> a;
        private final BatchingListUpdateCallback b;

        public BatchedCallback(Callback<T2> callback) {
            this.a = callback;
            this.b = new BatchingListUpdateCallback(callback);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public boolean areContentsTheSame(T2 t2, T2 t22) {
            return this.a.areContentsTheSame(t2, t22);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public boolean areItemsTheSame(T2 t2, T2 t22) {
            return this.a.areItemsTheSame(t2, t22);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback, java.util.Comparator
        public int compare(T2 t2, T2 t22) {
            return this.a.compare(t2, t22);
        }

        public void dispatchLastEvent() {
            this.b.dispatchLastEvent();
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        @Nullable
        public Object getChangePayload(T2 t2, T2 t22) {
            return this.a.getChangePayload(t2, t22);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback
        public void onChanged(int i2, int i3) {
            this.b.onChanged(i2, i3, null);
        }

        @Override // androidx.recyclerview.widget.SortedList.Callback, androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int i2, int i3, Object obj) {
            this.b.onChanged(i2, i3, obj);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int i2, int i3) {
            this.b.onInserted(i2, i3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int i2, int i3) {
            this.b.onMoved(i2, i3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int i2, int i3) {
            this.b.onRemoved(i2, i3);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
        public abstract boolean areContentsTheSame(T2 t2, T2 t22);

        public abstract boolean areItemsTheSame(T2 t2, T2 t22);

        @Override // java.util.Comparator
        public abstract int compare(T2 t2, T2 t22);

        @Nullable
        public Object getChangePayload(T2 t2, T2 t22) {
            return null;
        }

        public abstract void onChanged(int i2, int i3);

        public void onChanged(int i2, int i3, Object obj) {
            onChanged(i2, i3);
        }
    }

    public SortedList(@NonNull Class<T> cls, @NonNull Callback<T> callback) {
        this(cls, callback, 10);
    }

    public SortedList(@NonNull Class<T> cls, @NonNull Callback<T> callback, int i2) {
        this.f981i = cls;
        this.a = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
        this.f = callback;
        this.h = 0;
    }

    private int a(T t, boolean z) {
        int e = e(t, this.a, 0, this.h, 1);
        if (e == -1) {
            e = 0;
        } else if (e < this.h) {
            T t2 = this.a[e];
            if (this.f.areItemsTheSame(t2, t)) {
                if (this.f.areContentsTheSame(t2, t)) {
                    this.a[e] = t;
                    return e;
                }
                this.a[e] = t;
                Callback callback = this.f;
                callback.onChanged(e, 1, callback.getChangePayload(t2, t));
                return e;
            }
        }
        c(e, t);
        if (z) {
            this.f.onInserted(e, 1);
        }
        return e;
    }

    private void b(T[] tArr) {
        if (tArr.length < 1) {
            return;
        }
        int n = n(tArr);
        if (this.h != 0) {
            h(tArr, n);
            return;
        }
        this.a = tArr;
        this.h = n;
        this.f.onInserted(0, n);
    }

    private void c(int i2, T t) {
        int i3 = this.h;
        if (i2 > i3) {
            throw new IndexOutOfBoundsException("cannot add item to " + i2 + " because size is " + this.h);
        }
        T[] tArr = this.a;
        if (i3 == tArr.length) {
            T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.f981i, tArr.length + 10));
            System.arraycopy(this.a, 0, tArr2, 0, i2);
            tArr2[i2] = t;
            System.arraycopy(this.a, i2, tArr2, i2 + 1, this.h - i2);
            this.a = tArr2;
        } else {
            System.arraycopy(tArr, i2, tArr, i2 + 1, i3 - i2);
            this.a[i2] = t;
        }
        this.h++;
    }

    private T[] d(T[] tArr) {
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance((Class<?>) this.f981i, tArr.length));
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        return tArr2;
    }

    private int e(T t, T[] tArr, int i2, int i3, int i4) {
        while (i2 < i3) {
            int i5 = (i2 + i3) / 2;
            T t2 = tArr[i5];
            int compare = this.f.compare(t2, t);
            if (compare < 0) {
                i2 = i5 + 1;
            } else {
                if (compare == 0) {
                    if (this.f.areItemsTheSame(t2, t)) {
                        return i5;
                    }
                    int g = g(t, i5, i2, i3);
                    return (i4 == 1 && g == -1) ? i5 : g;
                }
                i3 = i5;
            }
        }
        if (i4 == 1) {
            return i2;
        }
        return -1;
    }

    private int f(T t, T[] tArr, int i2, int i3) {
        while (i2 < i3) {
            if (this.f.areItemsTheSame(tArr[i2], t)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    private int g(T t, int i2, int i3, int i4) {
        T t2;
        for (int i5 = i2 - 1; i5 >= i3; i5--) {
            T t3 = this.a[i5];
            if (this.f.compare(t3, t) != 0) {
                break;
            }
            if (this.f.areItemsTheSame(t3, t)) {
                return i5;
            }
        }
        do {
            i2++;
            if (i2 >= i4) {
                return -1;
            }
            t2 = this.a[i2];
            if (this.f.compare(t2, t) != 0) {
                return -1;
            }
        } while (!this.f.areItemsTheSame(t2, t));
        return i2;
    }

    private void h(T[] tArr, int i2) {
        boolean z = !(this.f instanceof BatchedCallback);
        if (z) {
            beginBatchedUpdates();
        }
        this.b = this.a;
        int i3 = 0;
        this.f979c = 0;
        int i4 = this.h;
        this.f980d = i4;
        this.a = (T[]) ((Object[]) Array.newInstance((Class<?>) this.f981i, i4 + i2 + 10));
        this.e = 0;
        while (true) {
            int i5 = this.f979c;
            int i6 = this.f980d;
            if (i5 >= i6 && i3 >= i2) {
                break;
            }
            if (i5 == i6) {
                int i7 = i2 - i3;
                System.arraycopy(tArr, i3, this.a, this.e, i7);
                int i8 = this.e + i7;
                this.e = i8;
                this.h += i7;
                this.f.onInserted(i8 - i7, i7);
                break;
            }
            if (i3 == i2) {
                int i9 = i6 - i5;
                System.arraycopy(this.b, i5, this.a, this.e, i9);
                this.e += i9;
                break;
            }
            T t = this.b[i5];
            T t2 = tArr[i3];
            int compare = this.f.compare(t, t2);
            if (compare > 0) {
                T[] tArr2 = this.a;
                int i10 = this.e;
                int i11 = i10 + 1;
                this.e = i11;
                tArr2[i10] = t2;
                this.h++;
                i3++;
                this.f.onInserted(i11 - 1, 1);
            } else if (compare == 0 && this.f.areItemsTheSame(t, t2)) {
                T[] tArr3 = this.a;
                int i12 = this.e;
                this.e = i12 + 1;
                tArr3[i12] = t2;
                i3++;
                this.f979c++;
                if (!this.f.areContentsTheSame(t, t2)) {
                    Callback callback = this.f;
                    callback.onChanged(this.e - 1, 1, callback.getChangePayload(t, t2));
                }
            } else {
                T[] tArr4 = this.a;
                int i13 = this.e;
                this.e = i13 + 1;
                tArr4[i13] = t;
                this.f979c++;
            }
        }
        this.b = null;
        if (z) {
            endBatchedUpdates();
        }
    }

    private boolean i(T t, boolean z) {
        int e = e(t, this.a, 0, this.h, 2);
        if (e == -1) {
            return false;
        }
        j(e, z);
        return true;
    }

    private void j(int i2, boolean z) {
        T[] tArr = this.a;
        System.arraycopy(tArr, i2 + 1, tArr, i2, (this.h - i2) - 1);
        int i3 = this.h - 1;
        this.h = i3;
        this.a[i3] = null;
        if (z) {
            this.f.onRemoved(i2, 1);
        }
    }

    private void k(T t) {
        T[] tArr = this.a;
        int i2 = this.e;
        tArr[i2] = t;
        int i3 = i2 + 1;
        this.e = i3;
        this.h++;
        this.f.onInserted(i3 - 1, 1);
    }

    private void l(@NonNull T[] tArr) {
        boolean z = !(this.f instanceof BatchedCallback);
        if (z) {
            beginBatchedUpdates();
        }
        this.f979c = 0;
        this.f980d = this.h;
        this.b = this.a;
        this.e = 0;
        int n = n(tArr);
        this.a = (T[]) ((Object[]) Array.newInstance((Class<?>) this.f981i, n));
        while (true) {
            int i2 = this.e;
            if (i2 >= n && this.f979c >= this.f980d) {
                break;
            }
            int i3 = this.f979c;
            int i4 = this.f980d;
            if (i3 >= i4) {
                int i5 = n - i2;
                System.arraycopy(tArr, i2, this.a, i2, i5);
                this.e += i5;
                this.h += i5;
                this.f.onInserted(i2, i5);
                break;
            }
            if (i2 >= n) {
                int i6 = i4 - i3;
                this.h -= i6;
                this.f.onRemoved(i2, i6);
                break;
            }
            T t = this.b[i3];
            T t2 = tArr[i2];
            int compare = this.f.compare(t, t2);
            if (compare < 0) {
                m();
            } else {
                if (compare <= 0) {
                    if (this.f.areItemsTheSame(t, t2)) {
                        T[] tArr2 = this.a;
                        int i7 = this.e;
                        tArr2[i7] = t2;
                        this.f979c++;
                        this.e = i7 + 1;
                        if (!this.f.areContentsTheSame(t, t2)) {
                            Callback callback = this.f;
                            callback.onChanged(this.e - 1, 1, callback.getChangePayload(t, t2));
                        }
                    } else {
                        m();
                    }
                }
                k(t2);
            }
        }
        this.b = null;
        if (z) {
            endBatchedUpdates();
        }
    }

    private void m() {
        this.h--;
        this.f979c++;
        this.f.onRemoved(this.e, 1);
    }

    private int n(@NonNull T[] tArr) {
        if (tArr.length == 0) {
            return 0;
        }
        Arrays.sort(tArr, this.f);
        int i2 = 1;
        int i3 = 0;
        for (int i4 = 1; i4 < tArr.length; i4++) {
            T t = tArr[i4];
            if (this.f.compare(tArr[i3], t) == 0) {
                int f = f(t, tArr, i3, i2);
                if (f != -1) {
                    tArr[f] = t;
                } else {
                    if (i2 != i4) {
                        tArr[i2] = t;
                    }
                    i2++;
                }
            } else {
                if (i2 != i4) {
                    tArr[i2] = t;
                }
                i3 = i2;
                i2++;
            }
        }
        return i2;
    }

    private void o() {
        if (this.b != null) {
            throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
        }
    }

    public int add(T t) {
        o();
        return a(t, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addAll(@NonNull Collection<T> collection) {
        addAll(collection.toArray((Object[]) Array.newInstance((Class<?>) this.f981i, collection.size())), true);
    }

    public void addAll(@NonNull T... tArr) {
        addAll(tArr, false);
    }

    public void addAll(@NonNull T[] tArr, boolean z) {
        o();
        if (tArr.length == 0) {
            return;
        }
        if (z) {
            b(tArr);
        } else {
            b(d(tArr));
        }
    }

    public void beginBatchedUpdates() {
        o();
        Callback callback = this.f;
        if (callback instanceof BatchedCallback) {
            return;
        }
        if (this.g == null) {
            this.g = new BatchedCallback(callback);
        }
        this.f = this.g;
    }

    public void clear() {
        o();
        int i2 = this.h;
        if (i2 == 0) {
            return;
        }
        Arrays.fill(this.a, 0, i2, (Object) null);
        this.h = 0;
        this.f.onRemoved(0, i2);
    }

    public void endBatchedUpdates() {
        o();
        Callback callback = this.f;
        if (callback instanceof BatchedCallback) {
            ((BatchedCallback) callback).dispatchLastEvent();
        }
        Callback callback2 = this.f;
        BatchedCallback batchedCallback = this.g;
        if (callback2 == batchedCallback) {
            this.f = batchedCallback.a;
        }
    }

    public T get(int i2) {
        int i3;
        if (i2 < this.h && i2 >= 0) {
            T[] tArr = this.b;
            return (tArr == null || i2 < (i3 = this.e)) ? this.a[i2] : tArr[(i2 - i3) + this.f979c];
        }
        throw new IndexOutOfBoundsException("Asked to get item at " + i2 + " but size is " + this.h);
    }

    public int indexOf(T t) {
        if (this.b == null) {
            return e(t, this.a, 0, this.h, 4);
        }
        int e = e(t, this.a, 0, this.e, 4);
        if (e != -1) {
            return e;
        }
        int e2 = e(t, this.b, this.f979c, this.f980d, 4);
        if (e2 != -1) {
            return (e2 - this.f979c) + this.e;
        }
        return -1;
    }

    public void recalculatePositionOfItemAt(int i2) {
        o();
        T t = get(i2);
        j(i2, false);
        int a = a(t, false);
        if (i2 != a) {
            this.f.onMoved(i2, a);
        }
    }

    public boolean remove(T t) {
        o();
        return i(t, true);
    }

    public T removeItemAt(int i2) {
        o();
        T t = get(i2);
        j(i2, true);
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void replaceAll(@NonNull Collection<T> collection) {
        replaceAll(collection.toArray((Object[]) Array.newInstance((Class<?>) this.f981i, collection.size())), true);
    }

    public void replaceAll(@NonNull T... tArr) {
        replaceAll(tArr, false);
    }

    public void replaceAll(@NonNull T[] tArr, boolean z) {
        o();
        if (z) {
            l(tArr);
        } else {
            l(d(tArr));
        }
    }

    public int size() {
        return this.h;
    }

    public void updateItemAt(int i2, T t) {
        o();
        T t2 = get(i2);
        boolean z = t2 == t || !this.f.areContentsTheSame(t2, t);
        if (t2 != t && this.f.compare(t2, t) == 0) {
            this.a[i2] = t;
            if (z) {
                Callback callback = this.f;
                callback.onChanged(i2, 1, callback.getChangePayload(t2, t));
                return;
            }
            return;
        }
        if (z) {
            Callback callback2 = this.f;
            callback2.onChanged(i2, 1, callback2.getChangePayload(t2, t));
        }
        j(i2, false);
        int a = a(t, false);
        if (i2 != a) {
            this.f.onMoved(i2, a);
        }
    }
}
