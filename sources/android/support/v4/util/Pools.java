package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: lib/Mus.dex */
public final class Pools {

    /* loaded from: lib/Mus.dex */
    public interface Pool<T> {
        @Nullable
        T acquire();

        boolean release(@NonNull T t);
    }

    private Pools() {
    }

    /* loaded from: lib/Mus.dex */
    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int i2) {
            if (i2 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.mPool = new Object[i2];
        }

        @Override // android.support.v4.util.Pools.Pool
        public T acquire() {
            if (this.mPoolSize <= 0) {
                return null;
            }
            int i2 = this.mPoolSize - 1;
            T t = (T) this.mPool[i2];
            this.mPool[i2] = null;
            this.mPoolSize--;
            return t;
        }

        @Override // android.support.v4.util.Pools.Pool
        public boolean release(@NonNull T t) {
            if (!isInPool(t)) {
                if (this.mPoolSize >= this.mPool.length) {
                    return false;
                }
                this.mPool[this.mPoolSize] = t;
                this.mPoolSize++;
                return true;
            }
            throw new IllegalStateException("Already in the pool!");
        }

        private boolean isInPool(@NonNull T t) {
            for (int i2 = 0; i2 < this.mPoolSize; i2++) {
                if (this.mPool[i2] == t) {
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock;

        public SynchronizedPool(int i2) {
            super(i2);
            this.mLock = new Object();
        }

        @Override // android.support.v4.util.Pools.SimplePool, android.support.v4.util.Pools.Pool
        public T acquire() {
            T t;
            synchronized (this.mLock) {
                t = (T) super.acquire();
            }
            return t;
        }

        @Override // android.support.v4.util.Pools.SimplePool, android.support.v4.util.Pools.Pool
        public boolean release(@NonNull T t) {
            boolean release;
            synchronized (this.mLock) {
                release = super.release(t);
            }
            return release;
        }
    }
}
