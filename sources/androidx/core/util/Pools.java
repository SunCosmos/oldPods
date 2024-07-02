package androidx.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public final class Pools {

    /* loaded from: classes.dex */
    public interface Pool<T> {
        @Nullable
        T acquire();

        boolean release(@NonNull T t);
    }

    /* loaded from: classes.dex */
    public static class SimplePool<T> implements Pool<T> {
        private final Object[] a;
        private int b;

        public SimplePool(int i2) {
            if (i2 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.a = new Object[i2];
        }

        private boolean a(@NonNull T t) {
            for (int i2 = 0; i2 < this.b; i2++) {
                if (this.a[i2] == t) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.core.util.Pools.Pool
        public T acquire() {
            int i2 = this.b;
            if (i2 <= 0) {
                return null;
            }
            int i3 = i2 - 1;
            Object[] objArr = this.a;
            T t = (T) objArr[i3];
            objArr[i3] = null;
            this.b = i2 - 1;
            return t;
        }

        @Override // androidx.core.util.Pools.Pool
        public boolean release(@NonNull T t) {
            if (a(t)) {
                throw new IllegalStateException("Already in the pool!");
            }
            int i2 = this.b;
            Object[] objArr = this.a;
            if (i2 >= objArr.length) {
                return false;
            }
            objArr[i2] = t;
            this.b = i2 + 1;
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class SynchronizedPool<T> extends SimplePool<T> {

        /* renamed from: c, reason: collision with root package name */
        private final Object f608c;

        public SynchronizedPool(int i2) {
            super(i2);
            this.f608c = new Object();
        }

        @Override // androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
        public T acquire() {
            T t;
            synchronized (this.f608c) {
                t = (T) super.acquire();
            }
            return t;
        }

        @Override // androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
        public boolean release(@NonNull T t) {
            boolean release;
            synchronized (this.f608c) {
                release = super.release(t);
            }
            return release;
        }
    }

    private Pools() {
    }
}
