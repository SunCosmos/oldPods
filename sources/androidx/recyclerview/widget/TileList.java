package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
class TileList<T> {
    final int a;
    private final SparseArray<Tile<T>> b = new SparseArray<>(10);

    /* renamed from: c, reason: collision with root package name */
    Tile<T> f991c;

    /* loaded from: classes.dex */
    public static class Tile<T> {
        Tile<T> a;
        public int mItemCount;
        public final T[] mItems;
        public int mStartPosition;

        public Tile(Class<T> cls, int i2) {
            this.mItems = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
        }

        boolean a(int i2) {
            int i3 = this.mStartPosition;
            return i3 <= i2 && i2 < i3 + this.mItemCount;
        }

        T b(int i2) {
            return this.mItems[i2 - this.mStartPosition];
        }
    }

    public TileList(int i2) {
        this.a = i2;
    }

    public Tile<T> a(Tile<T> tile) {
        int indexOfKey = this.b.indexOfKey(tile.mStartPosition);
        if (indexOfKey < 0) {
            this.b.put(tile.mStartPosition, tile);
            return null;
        }
        Tile<T> valueAt = this.b.valueAt(indexOfKey);
        this.b.setValueAt(indexOfKey, tile);
        if (this.f991c == valueAt) {
            this.f991c = tile;
        }
        return valueAt;
    }

    public void b() {
        this.b.clear();
    }

    public Tile<T> c(int i2) {
        return this.b.valueAt(i2);
    }

    public T d(int i2) {
        Tile<T> tile = this.f991c;
        if (tile == null || !tile.a(i2)) {
            int indexOfKey = this.b.indexOfKey(i2 - (i2 % this.a));
            if (indexOfKey < 0) {
                return null;
            }
            this.f991c = this.b.valueAt(indexOfKey);
        }
        return this.f991c.b(i2);
    }

    public Tile<T> e(int i2) {
        Tile<T> tile = this.b.get(i2);
        if (this.f991c == tile) {
            this.f991c = null;
        }
        this.b.delete(i2);
        return tile;
    }

    public int f() {
        return this.b.size();
    }
}
