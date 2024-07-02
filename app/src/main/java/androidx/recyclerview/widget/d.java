package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import androidx.core.os.TraceCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d implements Runnable {
    static final ThreadLocal<d> e = new ThreadLocal<>();
    static Comparator<c> f = new a();
    long b;

    /* renamed from: c, reason: collision with root package name */
    long f1002c;
    ArrayList<RecyclerView> a = new ArrayList<>();

    /* renamed from: d, reason: collision with root package name */
    private ArrayList<c> f1003d = new ArrayList<>();

    /* loaded from: classes.dex */
    static class a implements Comparator<c> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            RecyclerView recyclerView = cVar.f1007d;
            if ((recyclerView == null) != (cVar2.f1007d == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = cVar.a;
            if (z != cVar2.a) {
                return z ? -1 : 1;
            }
            int i2 = cVar2.b - cVar.b;
            if (i2 != 0) {
                return i2;
            }
            int i3 = cVar.f1006c - cVar2.f1006c;
            if (i3 != 0) {
                return i3;
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"VisibleForTests"})
    /* loaded from: classes.dex */
    public static class b implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {
        int a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        int[] f1004c;

        /* renamed from: d, reason: collision with root package name */
        int f1005d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() {
            int[] iArr = this.f1004c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f1005d = 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry
        public void addPosition(int i2, int i3) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i3 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i4 = this.f1005d * 2;
            int[] iArr = this.f1004c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f1004c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i4 >= iArr.length) {
                int[] iArr3 = new int[i4 * 2];
                this.f1004c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f1004c;
            iArr4[i4] = i2;
            iArr4[i4 + 1] = i3;
            this.f1005d++;
        }

        void b(RecyclerView recyclerView, boolean z) {
            this.f1005d = 0;
            int[] iArr = this.f1004c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.mLayout;
            if (recyclerView.mAdapter == null || layoutManager == null || !layoutManager.isItemPrefetchEnabled()) {
                return;
            }
            if (z) {
                if (!recyclerView.mAdapterHelper.p()) {
                    layoutManager.collectInitialPrefetchPositions(recyclerView.mAdapter.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                layoutManager.collectAdjacentPrefetchPositions(this.a, this.b, recyclerView.mState, this);
            }
            int i2 = this.f1005d;
            if (i2 > layoutManager.mPrefetchMaxCountObserved) {
                layoutManager.mPrefetchMaxCountObserved = i2;
                layoutManager.mPrefetchMaxObservedInInitialPrefetch = z;
                recyclerView.mRecycler.E();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean c(int i2) {
            if (this.f1004c != null) {
                int i3 = this.f1005d * 2;
                for (int i4 = 0; i4 < i3; i4 += 2) {
                    if (this.f1004c[i4] == i2) {
                        return true;
                    }
                }
            }
            return false;
        }

        void d(int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        public boolean a;
        public int b;

        /* renamed from: c, reason: collision with root package name */
        public int f1006c;

        /* renamed from: d, reason: collision with root package name */
        public RecyclerView f1007d;
        public int e;

        c() {
        }

        public void a() {
            this.a = false;
            this.b = 0;
            this.f1006c = 0;
            this.f1007d = null;
            this.e = 0;
        }
    }

    private void b() {
        c cVar;
        int size = this.a.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView recyclerView = this.a.get(i3);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.b(recyclerView, false);
                i2 += recyclerView.mPrefetchRegistry.f1005d;
            }
        }
        this.f1003d.ensureCapacity(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView recyclerView2 = this.a.get(i5);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.mPrefetchRegistry;
                int abs = Math.abs(bVar.a) + Math.abs(bVar.b);
                for (int i6 = 0; i6 < bVar.f1005d * 2; i6 += 2) {
                    if (i4 >= this.f1003d.size()) {
                        cVar = new c();
                        this.f1003d.add(cVar);
                    } else {
                        cVar = this.f1003d.get(i4);
                    }
                    int[] iArr = bVar.f1004c;
                    int i7 = iArr[i6 + 1];
                    cVar.a = i7 <= abs;
                    cVar.b = abs;
                    cVar.f1006c = i7;
                    cVar.f1007d = recyclerView2;
                    cVar.e = iArr[i6];
                    i4++;
                }
            }
        }
        Collections.sort(this.f1003d, f);
    }

    private void c(c cVar, long j) {
        RecyclerView.ViewHolder i2 = i(cVar.f1007d, cVar.e, cVar.a ? Long.MAX_VALUE : j);
        if (i2 == null || i2.a == null || !i2.n() || i2.o()) {
            return;
        }
        h(i2.a.get(), j);
    }

    private void d(long j) {
        for (int i2 = 0; i2 < this.f1003d.size(); i2++) {
            c cVar = this.f1003d.get(i2);
            if (cVar.f1007d == null) {
                return;
            }
            c(cVar, j);
            cVar.a();
        }
    }

    static boolean e(RecyclerView recyclerView, int i2) {
        int j = recyclerView.mChildHelper.j();
        for (int i3 = 0; i3 < j; i3++) {
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.i(i3));
            if (childViewHolderInt.b == i2 && !childViewHolderInt.o()) {
                return true;
            }
        }
        return false;
    }

    private void h(@Nullable RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.j() != 0) {
            recyclerView.removeAndRecycleViews();
        }
        b bVar = recyclerView.mPrefetchRegistry;
        bVar.b(recyclerView, true);
        if (bVar.f1005d != 0) {
            try {
                TraceCompat.beginSection("RV Nested Prefetch");
                recyclerView.mState.b(recyclerView.mAdapter);
                for (int i2 = 0; i2 < bVar.f1005d * 2; i2 += 2) {
                    i(recyclerView, bVar.f1004c[i2], j);
                }
            } finally {
                TraceCompat.endSection();
            }
        }
    }

    private RecyclerView.ViewHolder i(RecyclerView recyclerView, int i2, long j) {
        if (e(recyclerView, i2)) {
            return null;
        }
        RecyclerView.Recycler recycler = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.ViewHolder C = recycler.C(i2, false, j);
            if (C != null) {
                if (!C.n() || C.o()) {
                    recycler.a(C, false);
                } else {
                    recycler.recycleView(C.itemView);
                }
            }
            return C;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    public void a(RecyclerView recyclerView) {
        this.a.add(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(RecyclerView recyclerView, int i2, int i3) {
        if (recyclerView.isAttachedToWindow() && this.b == 0) {
            this.b = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.mPrefetchRegistry.d(i2, i3);
    }

    void g(long j) {
        b();
        d(j);
    }

    public void j(RecyclerView recyclerView) {
        this.a.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (!this.a.isEmpty()) {
                int size = this.a.size();
                long j = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = this.a.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j = Math.max(recyclerView.getDrawingTime(), j);
                    }
                }
                if (j != 0) {
                    g(TimeUnit.MILLISECONDS.toNanos(j) + this.f1002c);
                }
            }
        } finally {
            this.b = 0L;
            TraceCompat.endSection();
        }
    }
}
