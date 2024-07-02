package android.support.v7.util;

import android.support.v7.util.TileList;

/* loaded from: lib/Mus.dex */
interface ThreadUtil<T> {

    /* loaded from: lib/Mus.dex */
    public interface BackgroundCallback<T> {
        void loadTile(int i2, int i3);

        void recycleTile(TileList.Tile<T> tile);

        void refresh(int i2);

        void updateRange(int i2, int i3, int i4, int i5, int i6);
    }

    /* loaded from: lib/Mus.dex */
    public interface MainThreadCallback<T> {
        void addTile(int i2, TileList.Tile<T> tile);

        void removeTile(int i2, int i3);

        void updateItemCount(int i2, int i3);
    }

    BackgroundCallback<T> getBackgroundProxy(BackgroundCallback<T> backgroundCallback);

    MainThreadCallback<T> getMainThreadProxy(MainThreadCallback<T> mainThreadCallback);
}
