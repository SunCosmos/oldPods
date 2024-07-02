package android.support.v7.recyclerview.extensions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import java.util.Collections;
import java.util.List;

/* loaded from: lib/Mus.dex */
public class AsyncListDiffer<T> {
    private final AsyncDifferConfig<T> mConfig;

    @Nullable
    private List<T> mList;
    private int mMaxScheduledGeneration;

    @NonNull
    private List<T> mReadOnlyList = Collections.emptyList();
    private final ListUpdateCallback mUpdateCallback;

    public AsyncListDiffer(@NonNull RecyclerView.Adapter adapter, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.mUpdateCallback = new AdapterListUpdateCallback(adapter);
        this.mConfig = new AsyncDifferConfig.Builder(itemCallback).build();
    }

    public AsyncListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = asyncDifferConfig;
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.mReadOnlyList;
    }

    public void submitList(final List<T> list) {
        if (list != this.mList) {
            final int i2 = this.mMaxScheduledGeneration + 1;
            this.mMaxScheduledGeneration = i2;
            if (list == null) {
                int size = this.mList.size();
                this.mList = null;
                this.mReadOnlyList = Collections.emptyList();
                this.mUpdateCallback.onRemoved(0, size);
                return;
            }
            if (this.mList == null) {
                this.mList = list;
                this.mReadOnlyList = Collections.unmodifiableList(list);
                this.mUpdateCallback.onInserted(0, list.size());
            } else {
                final List<T> list2 = this.mList;
                this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1
                    @Override // java.lang.Runnable
                    public void run() {
                        final DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1.1
                            @Override // android.support.v7.util.DiffUtil.Callback
                            public int getOldListSize() {
                                return list2.size();
                            }

                            @Override // android.support.v7.util.DiffUtil.Callback
                            public int getNewListSize() {
                                return list.size();
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // android.support.v7.util.DiffUtil.Callback
                            public boolean areItemsTheSame(int i3, int i4) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areItemsTheSame(list2.get(i3), list.get(i4));
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // android.support.v7.util.DiffUtil.Callback
                            public boolean areContentsTheSame(int i3, int i4) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areContentsTheSame(list2.get(i3), list.get(i4));
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // android.support.v7.util.DiffUtil.Callback
                            @Nullable
                            public Object getChangePayload(int i3, int i4) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().getChangePayload(list2.get(i3), list.get(i4));
                            }
                        });
                        AsyncListDiffer.this.mConfig.getMainThreadExecutor().execute(new Runnable() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (AsyncListDiffer.this.mMaxScheduledGeneration == i2) {
                                    AsyncListDiffer.this.latchList(list, calculateDiff);
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void latchList(@NonNull List<T> list, @NonNull DiffUtil.DiffResult diffResult) {
        this.mList = list;
        this.mReadOnlyList = Collections.unmodifiableList(list);
        diffResult.dispatchUpdatesTo(this.mUpdateCallback);
    }
}
