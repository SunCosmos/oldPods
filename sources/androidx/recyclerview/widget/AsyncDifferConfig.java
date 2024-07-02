package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.DiffUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public final class AsyncDifferConfig<T> {

    @Nullable
    private final Executor a;

    @NonNull
    private final Executor b;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    private final DiffUtil.ItemCallback<T> f895c;

    /* loaded from: classes.dex */
    public static final class Builder<T> {

        /* renamed from: d, reason: collision with root package name */
        private static final Object f896d = new Object();
        private static Executor e;

        @Nullable
        private Executor a;
        private Executor b;

        /* renamed from: c, reason: collision with root package name */
        private final DiffUtil.ItemCallback<T> f897c;

        public Builder(@NonNull DiffUtil.ItemCallback<T> itemCallback) {
            this.f897c = itemCallback;
        }

        @NonNull
        public AsyncDifferConfig<T> build() {
            if (this.b == null) {
                synchronized (f896d) {
                    if (e == null) {
                        e = Executors.newFixedThreadPool(2);
                    }
                }
                this.b = e;
            }
            return new AsyncDifferConfig<>(this.a, this.b, this.f897c);
        }

        @NonNull
        public Builder<T> setBackgroundThreadExecutor(Executor executor) {
            this.b = executor;
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public Builder<T> setMainThreadExecutor(Executor executor) {
            this.a = executor;
            return this;
        }
    }

    AsyncDifferConfig(@Nullable Executor executor, @NonNull Executor executor2, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.a = executor;
        this.b = executor2;
        this.f895c = itemCallback;
    }

    @NonNull
    public Executor getBackgroundThreadExecutor() {
        return this.b;
    }

    @NonNull
    public DiffUtil.ItemCallback<T> getDiffCallback() {
        return this.f895c;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Executor getMainThreadExecutor() {
        return this.a;
    }
}
