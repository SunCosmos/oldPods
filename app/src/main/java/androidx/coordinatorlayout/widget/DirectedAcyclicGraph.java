package androidx.coordinatorlayout.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class DirectedAcyclicGraph<T> {
    private final Pools.Pool<ArrayList<T>> a = new Pools.SimplePool(10);
    private final SimpleArrayMap<T, ArrayList<T>> b = new SimpleArrayMap<>();

    /* renamed from: c, reason: collision with root package name */
    private final ArrayList<T> f448c = new ArrayList<>();

    /* renamed from: d, reason: collision with root package name */
    private final HashSet<T> f449d = new HashSet<>();

    private void a(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (arrayList.contains(t)) {
            return;
        }
        if (hashSet.contains(t)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(t);
        ArrayList<T> arrayList2 = this.b.get(t);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                a(arrayList2.get(i2), arrayList, hashSet);
            }
        }
        hashSet.remove(t);
        arrayList.add(t);
    }

    @NonNull
    private ArrayList<T> b() {
        ArrayList<T> acquire = this.a.acquire();
        return acquire == null ? new ArrayList<>() : acquire;
    }

    private void c(@NonNull ArrayList<T> arrayList) {
        arrayList.clear();
        this.a.release(arrayList);
    }

    public void addEdge(@NonNull T t, @NonNull T t2) {
        if (!this.b.containsKey(t) || !this.b.containsKey(t2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList<T> arrayList = this.b.get(t);
        if (arrayList == null) {
            arrayList = b();
            this.b.put(t, arrayList);
        }
        arrayList.add(t2);
    }

    public void addNode(@NonNull T t) {
        if (this.b.containsKey(t)) {
            return;
        }
        this.b.put(t, null);
    }

    public void clear() {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> valueAt = this.b.valueAt(i2);
            if (valueAt != null) {
                c(valueAt);
            }
        }
        this.b.clear();
    }

    public boolean contains(@NonNull T t) {
        return this.b.containsKey(t);
    }

    @Nullable
    public List getIncomingEdges(@NonNull T t) {
        return this.b.get(t);
    }

    @Nullable
    public List<T> getOutgoingEdges(@NonNull T t) {
        int size = this.b.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> valueAt = this.b.valueAt(i2);
            if (valueAt != null && valueAt.contains(t)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.b.keyAt(i2));
            }
        }
        return arrayList;
    }

    @NonNull
    public ArrayList<T> getSortedList() {
        this.f448c.clear();
        this.f449d.clear();
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(this.b.keyAt(i2), this.f448c, this.f449d);
        }
        return this.f448c;
    }

    public boolean hasOutgoingEdges(@NonNull T t) {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<T> valueAt = this.b.valueAt(i2);
            if (valueAt != null && valueAt.contains(t)) {
                return true;
            }
        }
        return false;
    }
}
