package android.support.v4.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: lib/Mus.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i2;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    public void resize(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            this.maxSize = i2;
        }
        trimToSize(i2);
    }

    public final V get(K k) {
        V v;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v2 = this.map.get(k);
            if (v2 != null) {
                this.hitCount++;
                return v2;
            }
            this.missCount++;
            V create = create(k);
            if (create == null) {
                return null;
            }
            synchronized (this) {
                this.createCount++;
                v = (V) this.map.put(k, create);
                if (v != null) {
                    this.map.put(k, v);
                } else {
                    this.size += safeSizeOf(k, create);
                }
            }
            if (v != null) {
                entryRemoved(false, k, create, v);
                return v;
            }
            trimToSize(this.maxSize);
            return create;
        }
    }

    public final V put(K k, V v) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(k, v);
            put = this.map.put(k, v);
            if (put != null) {
                this.size -= safeSizeOf(k, put);
            }
        }
        if (put != null) {
            entryRemoved(false, k, put, v);
        }
        trimToSize(this.maxSize);
        return put;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005a, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void trimToSize(int r14) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
        L2:
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            r4 = r8
            monitor-enter(r7)
            r7 = r0
            int r7 = r7.size     // Catch: java.lang.Throwable -> L43
            if (r7 < 0) goto L1b
            r7 = r0
            java.util.LinkedHashMap<K, V> r7 = r7.map     // Catch: java.lang.Throwable -> L43
            boolean r7 = r7.isEmpty()     // Catch: java.lang.Throwable -> L43
            if (r7 == 0) goto L49
            r7 = r0
            int r7 = r7.size     // Catch: java.lang.Throwable -> L43
            if (r7 == 0) goto L49
        L1b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L43
            r12 = r7
            r7 = r12
            r8 = r12
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L43
            r12 = r9
            r9 = r12
            r10 = r12
            r10.<init>()     // Catch: java.lang.Throwable -> L43
            r10 = r0
            java.lang.Class r10 = r10.getClass()     // Catch: java.lang.Throwable -> L43
            java.lang.String r10 = r10.getName()     // Catch: java.lang.Throwable -> L43
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch: java.lang.Throwable -> L43
            java.lang.String r10 = ".sizeOf() is reporting inconsistent results!"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch: java.lang.Throwable -> L43
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L43
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L43
            throw r7     // Catch: java.lang.Throwable -> L43
        L43:
            r7 = move-exception
            r6 = r7
            r7 = r4
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L43
            r7 = r6
            throw r7
        L49:
            r7 = r0
            int r7 = r7.size     // Catch: java.lang.Throwable -> L43
            r8 = r1
            if (r7 <= r8) goto L58
            r7 = r0
            java.util.LinkedHashMap<K, V> r7 = r7.map     // Catch: java.lang.Throwable -> L43
            boolean r7 = r7.isEmpty()     // Catch: java.lang.Throwable -> L43
            if (r7 == 0) goto L5b
        L58:
            r7 = r4
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L43
            return
        L5b:
            r7 = r0
            java.util.LinkedHashMap<K, V> r7 = r7.map     // Catch: java.lang.Throwable -> L43
            java.util.Set r7 = r7.entrySet()     // Catch: java.lang.Throwable -> L43
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> L43
            java.lang.Object r7 = r7.next()     // Catch: java.lang.Throwable -> L43
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch: java.lang.Throwable -> L43
            r5 = r7
            r7 = r5
            java.lang.Object r7 = r7.getKey()     // Catch: java.lang.Throwable -> L43
            r2 = r7
            r7 = r5
            java.lang.Object r7 = r7.getValue()     // Catch: java.lang.Throwable -> L43
            r3 = r7
            r7 = r0
            java.util.LinkedHashMap<K, V> r7 = r7.map     // Catch: java.lang.Throwable -> L43
            r8 = r2
            java.lang.Object r7 = r7.remove(r8)     // Catch: java.lang.Throwable -> L43
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            int r8 = r8.size     // Catch: java.lang.Throwable -> L43
            r9 = r0
            r10 = r2
            r11 = r3
            int r9 = r9.safeSizeOf(r10, r11)     // Catch: java.lang.Throwable -> L43
            int r8 = r8 - r9
            r7.size = r8     // Catch: java.lang.Throwable -> L43
            r7 = r0
            r12 = r7
            r7 = r12
            r8 = r12
            int r8 = r8.evictionCount     // Catch: java.lang.Throwable -> L43
            r9 = 1
            int r8 = r8 + 1
            r7.evictionCount = r8     // Catch: java.lang.Throwable -> L43
            r7 = r4
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L43
            r7 = r0
            r8 = 1
            r9 = r2
            r10 = r3
            r11 = 0
            r7.entryRemoved(r8, r9, r10, r11)
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.trimToSize(int):void");
    }

    public final V remove(K k) {
        V remove;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            remove = this.map.remove(k);
            if (remove != null) {
                this.size -= safeSizeOf(k, remove);
            }
        }
        if (remove != null) {
            entryRemoved(false, k, remove, null);
        }
        return remove;
    }

    protected void entryRemoved(boolean z, K k, V v, V v2) {
    }

    protected V create(K k) {
        return null;
    }

    private int safeSizeOf(K k, V v) {
        int sizeOf = sizeOf(k, v);
        if (sizeOf < 0) {
            throw new IllegalStateException("Negative size: " + k + "=" + v);
        }
        return sizeOf;
    }

    protected int sizeOf(K k, V v) {
        return 1;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int i2;
        int i3 = this.hitCount + this.missCount;
        if (i3 != 0) {
            i2 = (100 * this.hitCount) / i3;
        } else {
            i2 = 0;
        }
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i2));
    }
}
