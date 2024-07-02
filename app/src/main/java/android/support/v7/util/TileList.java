package android.support.v7.util;

import android.util.SparseArray;
import java.lang.reflect.Array;

/* loaded from: lib/Mus.dex */
class TileList<T> {
    Tile<T> mLastAccessedTile;
    final int mTileSize;
    private final SparseArray<Tile<T>> mTiles = new SparseArray<>(10);

    public TileList(int i2) {
        this.mTileSize = i2;
    }

    public T getItemAt(int i2) {
        if (this.mLastAccessedTile == null || !this.mLastAccessedTile.containsPosition(i2)) {
            int indexOfKey = this.mTiles.indexOfKey(i2 - (i2 % this.mTileSize));
            if (indexOfKey < 0) {
                return null;
            }
            this.mLastAccessedTile = this.mTiles.valueAt(indexOfKey);
        }
        return this.mLastAccessedTile.getByPosition(i2);
    }

    public int size() {
        return this.mTiles.size();
    }

    public void clear() {
        this.mTiles.clear();
    }

    public Tile<T> getAtIndex(int i2) {
        return this.mTiles.valueAt(i2);
    }

    public Tile<T> addOrReplace(Tile<T> tile) {
        int indexOfKey = this.mTiles.indexOfKey(tile.mStartPosition);
        if (indexOfKey < 0) {
            this.mTiles.put(tile.mStartPosition, tile);
            return null;
        }
        Tile<T> valueAt = this.mTiles.valueAt(indexOfKey);
        this.mTiles.setValueAt(indexOfKey, tile);
        if (this.mLastAccessedTile == valueAt) {
            this.mLastAccessedTile = tile;
        }
        return valueAt;
    }

    public Tile<T> removeAtPos(int i2) {
        Tile<T> tile = this.mTiles.get(i2);
        if (this.mLastAccessedTile == tile) {
            this.mLastAccessedTile = null;
        }
        this.mTiles.delete(i2);
        return tile;
    }

    /* loaded from: lib/Mus.dex */
    public static class Tile<T> {
        public int mItemCount;
        public final T[] mItems;
        Tile<T> mNext;
        public int mStartPosition;

        public Tile(Class<T> cls, int i2) {
            this.mItems = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
        }

        boolean containsPosition(int i2) {
            return this.mStartPosition <= i2 && i2 < this.mStartPosition + this.mItemCount;
        }

        T getByPosition(int i2) {
            return this.mItems[i2 - this.mStartPosition];
        }
    }
}
