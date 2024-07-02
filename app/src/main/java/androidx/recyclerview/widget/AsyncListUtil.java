package androidx.recyclerview.widget;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;

/* loaded from: classes.dex */
public class AsyncListUtil<T> {
    final Class<T> a;
    final int b;

    /* renamed from: c, reason: collision with root package name */
    final DataCallback<T> f902c;

    /* renamed from: d, reason: collision with root package name */
    final ViewCallback f903d;
    final TileList<T> e;
    final ThreadUtil.MainThreadCallback<T> f;
    final ThreadUtil.BackgroundCallback<T> g;
    boolean k;
    private final ThreadUtil.MainThreadCallback<T> q;
    private final ThreadUtil.BackgroundCallback<T> r;
    final int[] h = new int[2];

    /* renamed from: i, reason: collision with root package name */
    final int[] f904i = new int[2];
    final int[] j = new int[2];
    private int l = 0;
    int m = 0;
    int n = 0;
    int o = 0;
    final SparseIntArray p = new SparseIntArray();

    /* loaded from: classes.dex */
    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(@NonNull T[] tArr, int i2, int i3);

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }

        @WorkerThread
        public void recycleData(@NonNull T[] tArr, int i2) {
        }

        @WorkerThread
        public abstract int refreshData();
    }

    /* loaded from: classes.dex */
    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        @UiThread
        public void extendRangeInto(@NonNull int[] iArr, @NonNull int[] iArr2, int i2) {
            int i3 = (iArr[1] - iArr[0]) + 1;
            int i4 = i3 / 2;
            iArr2[0] = iArr[0] - (i2 == 1 ? i3 : i4);
            int i5 = iArr[1];
            if (i2 != 2) {
                i3 = i4;
            }
            iArr2[1] = i5 + i3;
        }

        @UiThread
        public abstract void getItemRangeInto(@NonNull int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i2);
    }

    /* loaded from: classes.dex */
    class a implements ThreadUtil.MainThreadCallback<T> {
        a() {
        }

        private boolean a(int i2) {
            return i2 == AsyncListUtil.this.o;
        }

        private void b() {
            for (int i2 = 0; i2 < AsyncListUtil.this.e.f(); i2++) {
                AsyncListUtil asyncListUtil = AsyncListUtil.this;
                asyncListUtil.g.recycleTile(asyncListUtil.e.c(i2));
            }
            AsyncListUtil.this.e.b();
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void addTile(int i2, TileList.Tile<T> tile) {
            if (!a(i2)) {
                AsyncListUtil.this.g.recycleTile(tile);
                return;
            }
            TileList.Tile<T> a = AsyncListUtil.this.e.a(tile);
            if (a != null) {
                Log.e("AsyncListUtil", "duplicate tile @" + a.mStartPosition);
                AsyncListUtil.this.g.recycleTile(a);
            }
            int i3 = tile.mStartPosition + tile.mItemCount;
            int i4 = 0;
            while (i4 < AsyncListUtil.this.p.size()) {
                int keyAt = AsyncListUtil.this.p.keyAt(i4);
                if (tile.mStartPosition > keyAt || keyAt >= i3) {
                    i4++;
                } else {
                    AsyncListUtil.this.p.removeAt(i4);
                    AsyncListUtil.this.f903d.onItemLoaded(keyAt);
                }
            }
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void removeTile(int i2, int i3) {
            if (a(i2)) {
                TileList.Tile<T> e = AsyncListUtil.this.e.e(i3);
                if (e != null) {
                    AsyncListUtil.this.g.recycleTile(e);
                    return;
                }
                Log.e("AsyncListUtil", "tile not found @" + i3);
            }
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void updateItemCount(int i2, int i3) {
            if (a(i2)) {
                AsyncListUtil asyncListUtil = AsyncListUtil.this;
                asyncListUtil.m = i3;
                asyncListUtil.f903d.onDataRefresh();
                AsyncListUtil asyncListUtil2 = AsyncListUtil.this;
                asyncListUtil2.n = asyncListUtil2.o;
                b();
                AsyncListUtil asyncListUtil3 = AsyncListUtil.this;
                asyncListUtil3.k = false;
                asyncListUtil3.b();
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements ThreadUtil.BackgroundCallback<T> {
        private TileList.Tile<T> a;
        final SparseBooleanArray b = new SparseBooleanArray();

        /* renamed from: c, reason: collision with root package name */
        private int f905c;

        /* renamed from: d, reason: collision with root package name */
        private int f906d;
        private int e;
        private int f;

        b() {
        }

        private TileList.Tile<T> a() {
            TileList.Tile<T> tile = this.a;
            if (tile != null) {
                this.a = tile.a;
                return tile;
            }
            AsyncListUtil asyncListUtil = AsyncListUtil.this;
            return new TileList.Tile<>(asyncListUtil.a, asyncListUtil.b);
        }

        private void b(TileList.Tile<T> tile) {
            this.b.put(tile.mStartPosition, true);
            AsyncListUtil.this.f.addTile(this.f905c, tile);
        }

        private void c(int i2) {
            int maxCachedTiles = AsyncListUtil.this.f902c.getMaxCachedTiles();
            while (this.b.size() >= maxCachedTiles) {
                int keyAt = this.b.keyAt(0);
                SparseBooleanArray sparseBooleanArray = this.b;
                int keyAt2 = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
                int i3 = this.e - keyAt;
                int i4 = keyAt2 - this.f;
                if (i3 > 0 && (i3 >= i4 || i2 == 2)) {
                    f(keyAt);
                } else {
                    if (i4 <= 0) {
                        return;
                    }
                    if (i3 >= i4 && i2 != 1) {
                        return;
                    } else {
                        f(keyAt2);
                    }
                }
            }
        }

        private int d(int i2) {
            return i2 - (i2 % AsyncListUtil.this.b);
        }

        private boolean e(int i2) {
            return this.b.get(i2);
        }

        private void f(int i2) {
            this.b.delete(i2);
            AsyncListUtil.this.f.removeTile(this.f905c, i2);
        }

        private void g(int i2, int i3, int i4, boolean z) {
            int i5 = i2;
            while (i5 <= i3) {
                AsyncListUtil.this.g.loadTile(z ? (i3 + i2) - i5 : i5, i4);
                i5 += AsyncListUtil.this.b;
            }
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void loadTile(int i2, int i3) {
            if (e(i2)) {
                return;
            }
            TileList.Tile<T> a = a();
            a.mStartPosition = i2;
            int min = Math.min(AsyncListUtil.this.b, this.f906d - i2);
            a.mItemCount = min;
            AsyncListUtil.this.f902c.fillData(a.mItems, a.mStartPosition, min);
            c(i3);
            b(a);
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void recycleTile(TileList.Tile<T> tile) {
            AsyncListUtil.this.f902c.recycleData(tile.mItems, tile.mItemCount);
            tile.a = this.a;
            this.a = tile;
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void refresh(int i2) {
            this.f905c = i2;
            this.b.clear();
            int refreshData = AsyncListUtil.this.f902c.refreshData();
            this.f906d = refreshData;
            AsyncListUtil.this.f.updateItemCount(this.f905c, refreshData);
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void updateRange(int i2, int i3, int i4, int i5, int i6) {
            if (i2 > i3) {
                return;
            }
            int d2 = d(i2);
            int d3 = d(i3);
            this.e = d(i4);
            int d4 = d(i5);
            this.f = d4;
            if (i6 == 1) {
                g(this.e, d3, i6, true);
                g(d3 + AsyncListUtil.this.b, this.f, i6, false);
            } else {
                g(d2, d4, i6, false);
                g(this.e, d2 - AsyncListUtil.this.b, i6, true);
            }
        }
    }

    public AsyncListUtil(@NonNull Class<T> cls, int i2, @NonNull DataCallback<T> dataCallback, @NonNull ViewCallback viewCallback) {
        a aVar = new a();
        this.q = aVar;
        b bVar = new b();
        this.r = bVar;
        this.a = cls;
        this.b = i2;
        this.f902c = dataCallback;
        this.f903d = viewCallback;
        this.e = new TileList<>(i2);
        g gVar = new g();
        this.f = gVar.b(aVar);
        this.g = gVar.a(bVar);
        refresh();
    }

    private boolean a() {
        return this.o != this.n;
    }

    void b() {
        this.f903d.getItemRangeInto(this.h);
        int[] iArr = this.h;
        if (iArr[0] > iArr[1] || iArr[0] < 0 || iArr[1] >= this.m) {
            return;
        }
        if (this.k) {
            int i2 = iArr[0];
            int[] iArr2 = this.f904i;
            if (i2 <= iArr2[1] && iArr2[0] <= iArr[1]) {
                if (iArr[0] < iArr2[0]) {
                    this.l = 1;
                } else if (iArr[0] > iArr2[0]) {
                    this.l = 2;
                }
                int[] iArr3 = this.f904i;
                iArr3[0] = iArr[0];
                iArr3[1] = iArr[1];
                this.f903d.extendRangeInto(iArr, this.j, this.l);
                int[] iArr4 = this.j;
                iArr4[0] = Math.min(this.h[0], Math.max(iArr4[0], 0));
                int[] iArr5 = this.j;
                iArr5[1] = Math.max(this.h[1], Math.min(iArr5[1], this.m - 1));
                ThreadUtil.BackgroundCallback<T> backgroundCallback = this.g;
                int[] iArr6 = this.h;
                int i3 = iArr6[0];
                int i4 = iArr6[1];
                int[] iArr7 = this.j;
                backgroundCallback.updateRange(i3, i4, iArr7[0], iArr7[1], this.l);
            }
        }
        this.l = 0;
        int[] iArr32 = this.f904i;
        iArr32[0] = iArr[0];
        iArr32[1] = iArr[1];
        this.f903d.extendRangeInto(iArr, this.j, this.l);
        int[] iArr42 = this.j;
        iArr42[0] = Math.min(this.h[0], Math.max(iArr42[0], 0));
        int[] iArr52 = this.j;
        iArr52[1] = Math.max(this.h[1], Math.min(iArr52[1], this.m - 1));
        ThreadUtil.BackgroundCallback<T> backgroundCallback2 = this.g;
        int[] iArr62 = this.h;
        int i32 = iArr62[0];
        int i42 = iArr62[1];
        int[] iArr72 = this.j;
        backgroundCallback2.updateRange(i32, i42, iArr72[0], iArr72[1], this.l);
    }

    @Nullable
    public T getItem(int i2) {
        if (i2 < 0 || i2 >= this.m) {
            throw new IndexOutOfBoundsException(i2 + " is not within 0 and " + this.m);
        }
        T d2 = this.e.d(i2);
        if (d2 == null && !a()) {
            this.p.put(i2, 0);
        }
        return d2;
    }

    public int getItemCount() {
        return this.m;
    }

    public void onRangeChanged() {
        if (a()) {
            return;
        }
        b();
        this.k = true;
    }

    public void refresh() {
        this.p.clear();
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.g;
        int i2 = this.o + 1;
        this.o = i2;
        backgroundCallback.refresh(i2);
    }
}
