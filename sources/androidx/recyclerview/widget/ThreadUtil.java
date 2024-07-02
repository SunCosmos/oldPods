package androidx.recyclerview.widget;

import androidx.recyclerview.widget.TileList;

/* loaded from: classes.dex */
interface ThreadUtil<T> {

    /* loaded from: classes.dex */
    public interface BackgroundCallback<T> {
        void loadTile(int i2, int i3);

        void recycleTile(TileList.Tile<T> tile);

        void refresh(int i2);

        void updateRange(int i2, int i3, int i4, int i5, int i6);
    }

    /* loaded from: classes.dex */
    public interface MainThreadCallback<T> {
        void addTile(int i2, TileList.Tile<T> tile);

        void removeTile(int i2, int i3);

        void updateItemCount(int i2, int i3);
    }

    BackgroundCallback<T> a(BackgroundCallback<T> backgroundCallback);

    MainThreadCallback<T> b(MainThreadCallback<T> mainThreadCallback);
}
