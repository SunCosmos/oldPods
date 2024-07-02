package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j {

    @VisibleForTesting
    final SimpleArrayMap<RecyclerView.ViewHolder, a> a = new SimpleArrayMap<>();

    @VisibleForTesting
    final LongSparseArray<RecyclerView.ViewHolder> b = new LongSparseArray<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: d, reason: collision with root package name */
        static Pools.Pool<a> f1018d = new Pools.SimplePool(20);
        int a;

        @Nullable
        RecyclerView.ItemAnimator.ItemHolderInfo b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        RecyclerView.ItemAnimator.ItemHolderInfo f1019c;

        private a() {
        }

        static void a() {
            do {
            } while (f1018d.acquire() != null);
        }

        static a b() {
            a acquire = f1018d.acquire();
            return acquire == null ? new a() : acquire;
        }

        static void c(a aVar) {
            aVar.a = 0;
            aVar.b = null;
            aVar.f1019c = null;
            f1018d.release(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b {
        void a(RecyclerView.ViewHolder viewHolder);

        void b(RecyclerView.ViewHolder viewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void c(RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void d(RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);
    }

    private RecyclerView.ItemAnimator.ItemHolderInfo l(RecyclerView.ViewHolder viewHolder, int i2) {
        a valueAt;
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo;
        int indexOfKey = this.a.indexOfKey(viewHolder);
        if (indexOfKey >= 0 && (valueAt = this.a.valueAt(indexOfKey)) != null) {
            int i3 = valueAt.a;
            if ((i3 & i2) != 0) {
                int i4 = (i2 ^ (-1)) & i3;
                valueAt.a = i4;
                if (i2 == 4) {
                    itemHolderInfo = valueAt.b;
                } else {
                    if (i2 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    itemHolderInfo = valueAt.f1019c;
                }
                if ((i4 & 12) == 0) {
                    this.a.removeAt(indexOfKey);
                    a.c(valueAt);
                }
                return itemHolderInfo;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        a aVar = this.a.get(viewHolder);
        if (aVar == null) {
            aVar = a.b();
            this.a.put(viewHolder, aVar);
        }
        aVar.a |= 2;
        aVar.b = itemHolderInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(RecyclerView.ViewHolder viewHolder) {
        a aVar = this.a.get(viewHolder);
        if (aVar == null) {
            aVar = a.b();
            this.a.put(viewHolder, aVar);
        }
        aVar.a |= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(long j, RecyclerView.ViewHolder viewHolder) {
        this.b.put(j, viewHolder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        a aVar = this.a.get(viewHolder);
        if (aVar == null) {
            aVar = a.b();
            this.a.put(viewHolder, aVar);
        }
        aVar.f1019c = itemHolderInfo;
        aVar.a |= 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        a aVar = this.a.get(viewHolder);
        if (aVar == null) {
            aVar = a.b();
            this.a.put(viewHolder, aVar);
        }
        aVar.b = itemHolderInfo;
        aVar.a |= 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.a.clear();
        this.b.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerView.ViewHolder g(long j) {
        return this.b.get(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h(RecyclerView.ViewHolder viewHolder) {
        a aVar = this.a.get(viewHolder);
        return (aVar == null || (aVar.a & 1) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i(RecyclerView.ViewHolder viewHolder) {
        a aVar = this.a.get(viewHolder);
        return (aVar == null || (aVar.a & 4) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        a.a();
    }

    public void k(RecyclerView.ViewHolder viewHolder) {
        p(viewHolder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public RecyclerView.ItemAnimator.ItemHolderInfo m(RecyclerView.ViewHolder viewHolder) {
        return l(viewHolder, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public RecyclerView.ItemAnimator.ItemHolderInfo n(RecyclerView.ViewHolder viewHolder) {
        return l(viewHolder, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(b bVar) {
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo;
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2;
        for (int size = this.a.size() - 1; size >= 0; size--) {
            RecyclerView.ViewHolder keyAt = this.a.keyAt(size);
            a removeAt = this.a.removeAt(size);
            int i2 = removeAt.a;
            if ((i2 & 3) != 3) {
                if ((i2 & 1) != 0) {
                    itemHolderInfo = removeAt.b;
                    itemHolderInfo2 = itemHolderInfo != null ? removeAt.f1019c : null;
                } else {
                    if ((i2 & 14) != 14) {
                        if ((i2 & 12) == 12) {
                            bVar.d(keyAt, removeAt.b, removeAt.f1019c);
                        } else if ((i2 & 4) != 0) {
                            itemHolderInfo = removeAt.b;
                        } else if ((i2 & 8) == 0) {
                        }
                        a.c(removeAt);
                    }
                    bVar.b(keyAt, removeAt.b, removeAt.f1019c);
                    a.c(removeAt);
                }
                bVar.c(keyAt, itemHolderInfo, itemHolderInfo2);
                a.c(removeAt);
            }
            bVar.a(keyAt);
            a.c(removeAt);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(RecyclerView.ViewHolder viewHolder) {
        a aVar = this.a.get(viewHolder);
        if (aVar == null) {
            return;
        }
        aVar.a &= -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(RecyclerView.ViewHolder viewHolder) {
        int size = this.b.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            if (viewHolder == this.b.valueAt(size)) {
                this.b.removeAt(size);
                break;
            }
            size--;
        }
        a remove = this.a.remove(viewHolder);
        if (remove != null) {
            a.c(remove);
        }
    }
}
