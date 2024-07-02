package androidx.recyclerview.widget;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DiffUtil {
    private static final Comparator<d> a = new a();

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i2, int i3);

        public abstract boolean areItemsTheSame(int i2, int i3);

        @Nullable
        public Object getChangePayload(int i2, int i3) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    /* loaded from: classes.dex */
    public static class DiffResult {
        public static final int NO_POSITION = -1;
        private final List<d> a;
        private final int[] b;

        /* renamed from: c, reason: collision with root package name */
        private final int[] f924c;

        /* renamed from: d, reason: collision with root package name */
        private final Callback f925d;
        private final int e;
        private final int f;
        private final boolean g;

        DiffResult(Callback callback, List<d> list, int[] iArr, int[] iArr2, boolean z) {
            this.a = list;
            this.b = iArr;
            this.f924c = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(iArr2, 0);
            this.f925d = callback;
            this.e = callback.getOldListSize();
            this.f = callback.getNewListSize();
            this.g = z;
            a();
            f();
        }

        private void a() {
            d dVar = this.a.isEmpty() ? null : this.a.get(0);
            if (dVar != null && dVar.a == 0 && dVar.b == 0) {
                return;
            }
            d dVar2 = new d();
            dVar2.a = 0;
            dVar2.b = 0;
            dVar2.f930d = false;
            dVar2.f929c = 0;
            dVar2.e = false;
            this.a.add(0, dVar2);
        }

        private void b(List<b> list, ListUpdateCallback listUpdateCallback, int i2, int i3, int i4) {
            if (!this.g) {
                listUpdateCallback.onInserted(i2, i3);
                return;
            }
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                int[] iArr = this.f924c;
                int i6 = i4 + i5;
                int i7 = iArr[i6] & 31;
                if (i7 == 0) {
                    listUpdateCallback.onInserted(i2, 1);
                    Iterator<b> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().b++;
                    }
                } else if (i7 == 4 || i7 == 8) {
                    int i8 = iArr[i6] >> 5;
                    listUpdateCallback.onMoved(h(list, i8, true).b, i2);
                    if (i7 == 4) {
                        listUpdateCallback.onChanged(i2, 1, this.f925d.getChangePayload(i8, i6));
                    }
                } else {
                    if (i7 != 16) {
                        throw new IllegalStateException("unknown flag for pos " + i6 + " " + Long.toBinaryString(i7));
                    }
                    list.add(new b(i6, i2, false));
                }
            }
        }

        private void c(List<b> list, ListUpdateCallback listUpdateCallback, int i2, int i3, int i4) {
            if (!this.g) {
                listUpdateCallback.onRemoved(i2, i3);
                return;
            }
            for (int i5 = i3 - 1; i5 >= 0; i5--) {
                int[] iArr = this.b;
                int i6 = i4 + i5;
                int i7 = iArr[i6] & 31;
                if (i7 == 0) {
                    listUpdateCallback.onRemoved(i2 + i5, 1);
                    Iterator<b> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().b--;
                    }
                } else if (i7 == 4 || i7 == 8) {
                    int i8 = iArr[i6] >> 5;
                    b h = h(list, i8, false);
                    listUpdateCallback.onMoved(i2 + i5, h.b - 1);
                    if (i7 == 4) {
                        listUpdateCallback.onChanged(h.b - 1, 1, this.f925d.getChangePayload(i6, i8));
                    }
                } else {
                    if (i7 != 16) {
                        throw new IllegalStateException("unknown flag for pos " + i6 + " " + Long.toBinaryString(i7));
                    }
                    list.add(new b(i6, i2 + i5, true));
                }
            }
        }

        private void d(int i2, int i3, int i4) {
            if (this.b[i2 - 1] != 0) {
                return;
            }
            e(i2, i3, i4, false);
        }

        private boolean e(int i2, int i3, int i4, boolean z) {
            int i5;
            int i6;
            int i7;
            if (z) {
                i3--;
                i6 = i2;
                i5 = i3;
            } else {
                i5 = i2 - 1;
                i6 = i5;
            }
            while (i4 >= 0) {
                d dVar = this.a.get(i4);
                int i8 = dVar.a;
                int i9 = dVar.f929c;
                int i10 = i8 + i9;
                int i11 = dVar.b + i9;
                if (z) {
                    for (int i12 = i6 - 1; i12 >= i10; i12--) {
                        if (this.f925d.areItemsTheSame(i12, i5)) {
                            i7 = this.f925d.areContentsTheSame(i12, i5) ? 8 : 4;
                            this.f924c[i5] = (i12 << 5) | 16;
                            this.b[i12] = (i5 << 5) | i7;
                            return true;
                        }
                    }
                } else {
                    for (int i13 = i3 - 1; i13 >= i11; i13--) {
                        if (this.f925d.areItemsTheSame(i5, i13)) {
                            i7 = this.f925d.areContentsTheSame(i5, i13) ? 8 : 4;
                            int i14 = i2 - 1;
                            this.b[i14] = (i13 << 5) | 16;
                            this.f924c[i13] = (i14 << 5) | i7;
                            return true;
                        }
                    }
                }
                i6 = dVar.a;
                i3 = dVar.b;
                i4--;
            }
            return false;
        }

        private void f() {
            int i2 = this.e;
            int i3 = this.f;
            for (int size = this.a.size() - 1; size >= 0; size--) {
                d dVar = this.a.get(size);
                int i4 = dVar.a;
                int i5 = dVar.f929c;
                int i6 = i4 + i5;
                int i7 = dVar.b + i5;
                if (this.g) {
                    while (i2 > i6) {
                        d(i2, i3, size);
                        i2--;
                    }
                    while (i3 > i7) {
                        g(i2, i3, size);
                        i3--;
                    }
                }
                for (int i8 = 0; i8 < dVar.f929c; i8++) {
                    int i9 = dVar.a + i8;
                    int i10 = dVar.b + i8;
                    int i11 = this.f925d.areContentsTheSame(i9, i10) ? 1 : 2;
                    this.b[i9] = (i10 << 5) | i11;
                    this.f924c[i10] = (i9 << 5) | i11;
                }
                i2 = dVar.a;
                i3 = dVar.b;
            }
        }

        private void g(int i2, int i3, int i4) {
            if (this.f924c[i3 - 1] != 0) {
                return;
            }
            e(i2, i3, i4, true);
        }

        private static b h(List<b> list, int i2, boolean z) {
            int size = list.size() - 1;
            while (size >= 0) {
                b bVar = list.get(size);
                if (bVar.a == i2 && bVar.f926c == z) {
                    list.remove(size);
                    while (size < list.size()) {
                        list.get(size).b += z ? 1 : -1;
                        size++;
                    }
                    return bVar;
                }
                size--;
            }
            return null;
        }

        public int convertNewPositionToOld(@IntRange(from = 0) int i2) {
            if (i2 >= 0 && i2 < this.f) {
                int i3 = this.f924c[i2];
                if ((i3 & 31) == 0) {
                    return -1;
                }
                return i3 >> 5;
            }
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i2 + ", new list size = " + this.f);
        }

        public int convertOldPositionToNew(@IntRange(from = 0) int i2) {
            if (i2 >= 0 && i2 < this.e) {
                int i3 = this.b[i2];
                if ((i3 & 31) == 0) {
                    return -1;
                }
                return i3 >> 5;
            }
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i2 + ", old list size = " + this.e);
        }

        public void dispatchUpdatesTo(@NonNull ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback = listUpdateCallback instanceof BatchingListUpdateCallback ? (BatchingListUpdateCallback) listUpdateCallback : new BatchingListUpdateCallback(listUpdateCallback);
            ArrayList arrayList = new ArrayList();
            int i2 = this.e;
            int i3 = this.f;
            for (int size = this.a.size() - 1; size >= 0; size--) {
                d dVar = this.a.get(size);
                int i4 = dVar.f929c;
                int i5 = dVar.a + i4;
                int i6 = dVar.b + i4;
                if (i5 < i2) {
                    c(arrayList, batchingListUpdateCallback, i5, i2 - i5, i5);
                }
                if (i6 < i3) {
                    b(arrayList, batchingListUpdateCallback, i5, i3 - i6, i6);
                }
                for (int i7 = i4 - 1; i7 >= 0; i7--) {
                    int[] iArr = this.b;
                    int i8 = dVar.a;
                    if ((iArr[i8 + i7] & 31) == 2) {
                        batchingListUpdateCallback.onChanged(i8 + i7, 1, this.f925d.getChangePayload(i8 + i7, dVar.b + i7));
                    }
                }
                i2 = dVar.a;
                i3 = dVar.b;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }

        public void dispatchUpdatesTo(@NonNull RecyclerView.Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(@NonNull T t, @NonNull T t2);

        public abstract boolean areItemsTheSame(@NonNull T t, @NonNull T t2);

        @Nullable
        public Object getChangePayload(@NonNull T t, @NonNull T t2) {
            return null;
        }
    }

    /* loaded from: classes.dex */
    static class a implements Comparator<d> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(d dVar, d dVar2) {
            int i2 = dVar.a - dVar2.a;
            return i2 == 0 ? dVar.b - dVar2.b : i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {
        int a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        boolean f926c;

        public b(int i2, int i3, boolean z) {
            this.a = i2;
            this.b = i3;
            this.f926c = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c {
        int a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        int f927c;

        /* renamed from: d, reason: collision with root package name */
        int f928d;

        public c() {
        }

        public c(int i2, int i3, int i4, int i5) {
            this.a = i2;
            this.b = i3;
            this.f927c = i4;
            this.f928d = i5;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {
        int a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        int f929c;

        /* renamed from: d, reason: collision with root package name */
        boolean f930d;
        boolean e;

        d() {
        }
    }

    private DiffUtil() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
    
        if (r24[r13 - 1] < r24[r13 + r5]) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b8, code lost:
    
        if (r25[r12 - 1] < r25[r12 + 1]) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e1 A[LOOP:4: B:54:0x00cd->B:58:0x00e1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ec A[EDGE_INSN: B:59:0x00ec->B:60:0x00ec BREAK  A[LOOP:4: B:54:0x00cd->B:58:0x00e1], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static androidx.recyclerview.widget.DiffUtil.d a(androidx.recyclerview.widget.DiffUtil.Callback r19, int r20, int r21, int r22, int r23, int[] r24, int[] r25, int r26) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.DiffUtil.a(androidx.recyclerview.widget.DiffUtil$Callback, int, int, int, int, int[], int[], int):androidx.recyclerview.widget.DiffUtil$d");
    }

    @NonNull
    public static DiffResult calculateDiff(@NonNull Callback callback) {
        return calculateDiff(callback, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c7  */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.recyclerview.widget.DiffUtil.DiffResult calculateDiff(@androidx.annotation.NonNull androidx.recyclerview.widget.DiffUtil.Callback r15, boolean r16) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.DiffUtil.calculateDiff(androidx.recyclerview.widget.DiffUtil$Callback, boolean):androidx.recyclerview.widget.DiffUtil$DiffResult");
    }
}
