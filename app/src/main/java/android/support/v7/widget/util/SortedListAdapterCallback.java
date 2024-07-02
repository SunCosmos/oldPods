package android.support.v7.widget.util;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

/* loaded from: lib/Mus.dex */
public abstract class SortedListAdapterCallback<T2> extends SortedList.Callback<T2> {
    final RecyclerView.Adapter mAdapter;

    public SortedListAdapterCallback(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onInserted(int i2, int i3) {
        this.mAdapter.notifyItemRangeInserted(i2, i3);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onRemoved(int i2, int i3) {
        this.mAdapter.notifyItemRangeRemoved(i2, i3);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onMoved(int i2, int i3) {
        this.mAdapter.notifyItemMoved(i2, i3);
    }

    @Override // android.support.v7.util.SortedList.Callback
    public void onChanged(int i2, int i3) {
        this.mAdapter.notifyItemRangeChanged(i2, i3);
    }

    @Override // android.support.v7.util.SortedList.Callback, android.support.v7.util.ListUpdateCallback
    public void onChanged(int i2, int i3, Object obj) {
        this.mAdapter.notifyItemRangeChanged(i2, i3, obj);
    }
}
