package android.support.v7.util;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.util.ThreadUtil;
import android.support.v7.util.TileList;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

/* loaded from: lib/Mus.dex */
public class AsyncListUtil<T> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncListUtil";
    boolean mAllowScrollHints;
    final ThreadUtil.BackgroundCallback<T> mBackgroundProxy;
    final DataCallback<T> mDataCallback;
    final ThreadUtil.MainThreadCallback<T> mMainThreadProxy;
    final Class<T> mTClass;
    final TileList<T> mTileList;
    final int mTileSize;
    final ViewCallback mViewCallback;
    final int[] mTmpRange = new int[2];
    final int[] mPrevRange = new int[2];
    final int[] mTmpRangeExtended = new int[2];
    private int mScrollHint = 0;
    int mItemCount = 0;
    int mDisplayedGeneration = 0;
    int mRequestedGeneration = this.mDisplayedGeneration;
    final SparseIntArray mMissingPositions = new SparseIntArray();
    private final ThreadUtil.MainThreadCallback<T> mMainThreadCallback = new ThreadUtil.MainThreadCallback<T>() { // from class: android.support.v7.util.AsyncListUtil.1
        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void updateItemCount(int i2, int i3) {
            if (isRequestedGeneration(i2)) {
                AsyncListUtil.this.mItemCount = i3;
                AsyncListUtil.this.mViewCallback.onDataRefresh();
                AsyncListUtil.this.mDisplayedGeneration = AsyncListUtil.this.mRequestedGeneration;
                recycleAllTiles();
                AsyncListUtil.this.mAllowScrollHints = false;
                AsyncListUtil.this.updateRange();
            }
        }

        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void addTile(int i2, TileList.Tile<T> tile) {
            if (!isRequestedGeneration(i2)) {
                AsyncListUtil.this.mBackgroundProxy.recycleTile(tile);
                return;
            }
            TileList.Tile<T> addOrReplace = AsyncListUtil.this.mTileList.addOrReplace(tile);
            if (addOrReplace != null) {
                Log.e(AsyncListUtil.TAG, "duplicate tile @" + addOrReplace.mStartPosition);
                AsyncListUtil.this.mBackgroundProxy.recycleTile(addOrReplace);
            }
            int i3 = tile.mStartPosition + tile.mItemCount;
            int i4 = 0;
            while (i4 < AsyncListUtil.this.mMissingPositions.size()) {
                int keyAt = AsyncListUtil.this.mMissingPositions.keyAt(i4);
                if (tile.mStartPosition <= keyAt && keyAt < i3) {
                    AsyncListUtil.this.mMissingPositions.removeAt(i4);
                    AsyncListUtil.this.mViewCallback.onItemLoaded(keyAt);
                } else {
                    i4++;
                }
            }
        }

        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void removeTile(int i2, int i3) {
            if (isRequestedGeneration(i2)) {
                TileList.Tile<T> removeAtPos = AsyncListUtil.this.mTileList.removeAtPos(i3);
                if (removeAtPos == null) {
                    Log.e(AsyncListUtil.TAG, "tile not found @" + i3);
                } else {
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(removeAtPos);
                }
            }
        }

        private void recycleAllTiles() {
            for (int i2 = 0; i2 < AsyncListUtil.this.mTileList.size(); i2++) {
                AsyncListUtil.this.mBackgroundProxy.recycleTile(AsyncListUtil.this.mTileList.getAtIndex(i2));
            }
            AsyncListUtil.this.mTileList.clear();
        }

        private boolean isRequestedGeneration(int i2) {
            return i2 == AsyncListUtil.this.mRequestedGeneration;
        }
    };
    private final ThreadUtil.BackgroundCallback<T> mBackgroundCallback = new ThreadUtil.BackgroundCallback<T>() { // from class: android.support.v7.util.AsyncListUtil.2
        private int mFirstRequiredTileStart;
        private int mGeneration;
        private int mItemCount;
        private int mLastRequiredTileStart;
        final SparseBooleanArray mLoadedTiles = new SparseBooleanArray();
        private TileList.Tile<T> mRecycledRoot;

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void refresh(int i2) {
            this.mGeneration = i2;
            this.mLoadedTiles.clear();
            this.mItemCount = AsyncListUtil.this.mDataCallback.refreshData();
            AsyncListUtil.this.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void updateRange(int i2, int i3, int i4, int i5, int i6) {
            if (i2 <= i3) {
                int tileStart = getTileStart(i2);
                int tileStart2 = getTileStart(i3);
                this.mFirstRequiredTileStart = getTileStart(i4);
                this.mLastRequiredTileStart = getTileStart(i5);
                if (i6 == 1) {
                    requestTiles(this.mFirstRequiredTileStart, tileStart2, i6, true);
                    requestTiles(tileStart2 + AsyncListUtil.this.mTileSize, this.mLastRequiredTileStart, i6, false);
                } else {
                    requestTiles(tileStart, this.mLastRequiredTileStart, i6, false);
                    requestTiles(this.mFirstRequiredTileStart, tileStart - AsyncListUtil.this.mTileSize, i6, true);
                }
            }
        }

        private int getTileStart(int i2) {
            return i2 - (i2 % AsyncListUtil.this.mTileSize);
        }

        private void requestTiles(int i2, int i3, int i4, boolean z) {
            int i5 = i2;
            while (true) {
                int i6 = i5;
                if (i6 <= i3) {
                    AsyncListUtil.this.mBackgroundProxy.loadTile(z ? (i3 + i2) - i6 : i6, i4);
                    i5 = i6 + AsyncListUtil.this.mTileSize;
                } else {
                    return;
                }
            }
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void loadTile(int i2, int i3) {
            if (!isTileLoaded(i2)) {
                TileList.Tile<T> acquireTile = acquireTile();
                acquireTile.mStartPosition = i2;
                acquireTile.mItemCount = Math.min(AsyncListUtil.this.mTileSize, this.mItemCount - acquireTile.mStartPosition);
                AsyncListUtil.this.mDataCallback.fillData(acquireTile.mItems, acquireTile.mStartPosition, acquireTile.mItemCount);
                flushTileCache(i3);
                addTile(acquireTile);
            }
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void recycleTile(TileList.Tile<T> tile) {
            AsyncListUtil.this.mDataCallback.recycleData(tile.mItems, tile.mItemCount);
            tile.mNext = this.mRecycledRoot;
            this.mRecycledRoot = tile;
        }

        private TileList.Tile<T> acquireTile() {
            if (this.mRecycledRoot == null) {
                return new TileList.Tile<>(AsyncListUtil.this.mTClass, AsyncListUtil.this.mTileSize);
            }
            TileList.Tile<T> tile = this.mRecycledRoot;
            this.mRecycledRoot = this.mRecycledRoot.mNext;
            return tile;
        }

        private boolean isTileLoaded(int i2) {
            return this.mLoadedTiles.get(i2);
        }

        private void addTile(TileList.Tile<T> tile) {
            this.mLoadedTiles.put(tile.mStartPosition, true);
            AsyncListUtil.this.mMainThreadProxy.addTile(this.mGeneration, tile);
        }

        private void removeTile(int i2) {
            this.mLoadedTiles.delete(i2);
            AsyncListUtil.this.mMainThreadProxy.removeTile(this.mGeneration, i2);
        }

        private void flushTileCache(int i2) {
            int maxCachedTiles = AsyncListUtil.this.mDataCallback.getMaxCachedTiles();
            while (this.mLoadedTiles.size() >= maxCachedTiles) {
                int keyAt = this.mLoadedTiles.keyAt(0);
                int keyAt2 = this.mLoadedTiles.keyAt(this.mLoadedTiles.size() - 1);
                int i3 = this.mFirstRequiredTileStart - keyAt;
                int i4 = keyAt2 - this.mLastRequiredTileStart;
                if (i3 > 0 && (i3 >= i4 || i2 == 2)) {
                    removeTile(keyAt);
                } else {
                    if (i4 <= 0) {
                        return;
                    }
                    if (i3 < i4 || i2 == 1) {
                        removeTile(keyAt2);
                    } else {
                        return;
                    }
                }
            }
        }

        private void log(String str, Object... objArr) {
            Log.d(AsyncListUtil.TAG, "[BKGR] " + String.format(str, objArr));
        }
    };

    void log(String str, Object... objArr) {
        Log.d(TAG, "[MAIN] " + String.format(str, objArr));
    }

    public AsyncListUtil(Class<T> cls, int i2, DataCallback<T> dataCallback, ViewCallback viewCallback) {
        this.mTClass = cls;
        this.mTileSize = i2;
        this.mDataCallback = dataCallback;
        this.mViewCallback = viewCallback;
        this.mTileList = new TileList<>(this.mTileSize);
        MessageThreadUtil messageThreadUtil = new MessageThreadUtil();
        this.mMainThreadProxy = messageThreadUtil.getMainThreadProxy(this.mMainThreadCallback);
        this.mBackgroundProxy = messageThreadUtil.getBackgroundProxy(this.mBackgroundCallback);
        refresh();
    }

    private boolean isRefreshPending() {
        return this.mRequestedGeneration != this.mDisplayedGeneration;
    }

    public void onRangeChanged() {
        if (!isRefreshPending()) {
            updateRange();
            this.mAllowScrollHints = true;
        }
    }

    public void refresh() {
        this.mMissingPositions.clear();
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.mBackgroundProxy;
        int i2 = this.mRequestedGeneration + 1;
        this.mRequestedGeneration = i2;
        backgroundCallback.refresh(i2);
    }

    public T getItem(int i2) {
        if (i2 < 0 || i2 >= this.mItemCount) {
            throw new IndexOutOfBoundsException(i2 + " is not within 0 and " + this.mItemCount);
        }
        T itemAt = this.mTileList.getItemAt(i2);
        if (itemAt == null && !isRefreshPending()) {
            this.mMissingPositions.put(i2, 0);
        }
        return itemAt;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    void updateRange() {
        this.mViewCallback.getItemRangeInto(this.mTmpRange);
        if (this.mTmpRange[0] <= this.mTmpRange[1] && this.mTmpRange[0] >= 0 && this.mTmpRange[1] < this.mItemCount) {
            if (this.mAllowScrollHints) {
                if (this.mTmpRange[0] <= this.mPrevRange[1] && this.mPrevRange[0] <= this.mTmpRange[1]) {
                    if (this.mTmpRange[0] >= this.mPrevRange[0]) {
                        if (this.mTmpRange[0] > this.mPrevRange[0]) {
                            this.mScrollHint = 2;
                        }
                    } else {
                        this.mScrollHint = 1;
                    }
                } else {
                    this.mScrollHint = 0;
                }
            } else {
                this.mScrollHint = 0;
            }
            this.mPrevRange[0] = this.mTmpRange[0];
            this.mPrevRange[1] = this.mTmpRange[1];
            this.mViewCallback.extendRangeInto(this.mTmpRange, this.mTmpRangeExtended, this.mScrollHint);
            this.mTmpRangeExtended[0] = Math.min(this.mTmpRange[0], Math.max(this.mTmpRangeExtended[0], 0));
            this.mTmpRangeExtended[1] = Math.max(this.mTmpRange[1], Math.min(this.mTmpRangeExtended[1], this.mItemCount - 1));
            this.mBackgroundProxy.updateRange(this.mTmpRange[0], this.mTmpRange[1], this.mTmpRangeExtended[0], this.mTmpRangeExtended[1], this.mScrollHint);
        }
    }

    /* loaded from: lib/Mus.dex */
    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(T[] tArr, int i2, int i3);

        @WorkerThread
        public abstract int refreshData();

        @WorkerThread
        public void recycleData(T[] tArr, int i2) {
        }

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        @UiThread
        public abstract void getItemRangeInto(int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i2);

        @UiThread
        public void extendRangeInto(int[] iArr, int[] iArr2, int i2) {
            int i3 = (iArr[1] - iArr[0]) + 1;
            int i4 = i3 / 2;
            iArr2[0] = iArr[0] - (i2 == 1 ? i3 : i4);
            iArr2[1] = iArr[1] + (i2 == 2 ? i3 : i4);
        }
    }
}
