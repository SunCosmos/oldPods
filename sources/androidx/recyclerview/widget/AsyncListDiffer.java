package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class AsyncListDiffer<T> {
    private static final Executor h = new b();
    private final ListUpdateCallback a;
    final AsyncDifferConfig<T> b;

    /* renamed from: c, reason: collision with root package name */
    Executor f898c;

    /* renamed from: d, reason: collision with root package name */
    private final List<ListListener<T>> f899d;

    @Nullable
    private List<T> e;

    @NonNull
    private List<T> f;
    int g;

    /* loaded from: classes.dex */
    public interface ListListener<T> {
        void onCurrentListChanged(@NonNull List<T> list, @NonNull List<T> list2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ List a;
        final /* synthetic */ List b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f900c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ Runnable f901d;

        /* renamed from: androidx.recyclerview.widget.AsyncListDiffer$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0033a extends DiffUtil.Callback {
            C0033a() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int i2, int i3) {
                Object obj = a.this.a.get(i2);
                Object obj2 = a.this.b.get(i3);
                if (obj != null && obj2 != null) {
                    return AsyncListDiffer.this.b.getDiffCallback().areContentsTheSame(obj, obj2);
                }
                if (obj == null && obj2 == null) {
                    return true;
                }
                throw new AssertionError();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int i2, int i3) {
                Object obj = a.this.a.get(i2);
                Object obj2 = a.this.b.get(i3);
                return (obj == null || obj2 == null) ? obj == null && obj2 == null : AsyncListDiffer.this.b.getDiffCallback().areItemsTheSame(obj, obj2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            @Nullable
            public Object getChangePayload(int i2, int i3) {
                Object obj = a.this.a.get(i2);
                Object obj2 = a.this.b.get(i3);
                if (obj == null || obj2 == null) {
                    throw new AssertionError();
                }
                return AsyncListDiffer.this.b.getDiffCallback().getChangePayload(obj, obj2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                return a.this.b.size();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return a.this.a.size();
            }
        }

        /* loaded from: classes.dex */
        class b implements Runnable {
            final /* synthetic */ DiffUtil.DiffResult a;

            b(DiffUtil.DiffResult diffResult) {
                this.a = diffResult;
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                AsyncListDiffer asyncListDiffer = AsyncListDiffer.this;
                if (asyncListDiffer.g == aVar.f900c) {
                    asyncListDiffer.a(aVar.b, this.a, aVar.f901d);
                }
            }
        }

        a(List list, List list2, int i2, Runnable runnable) {
            this.a = list;
            this.b = list2;
            this.f900c = i2;
            this.f901d = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsyncListDiffer.this.f898c.execute(new b(DiffUtil.calculateDiff(new C0033a())));
        }
    }

    /* loaded from: classes.dex */
    private static class b implements Executor {
        final Handler a = new Handler(Looper.getMainLooper());

        b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.a.post(runnable);
        }
    }

    public AsyncListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.f899d = new CopyOnWriteArrayList();
        this.f = Collections.emptyList();
        this.a = listUpdateCallback;
        this.b = asyncDifferConfig;
        this.f898c = asyncDifferConfig.getMainThreadExecutor() != null ? asyncDifferConfig.getMainThreadExecutor() : h;
    }

    public AsyncListDiffer(@NonNull RecyclerView.Adapter adapter, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this(new AdapterListUpdateCallback(adapter), new AsyncDifferConfig.Builder(itemCallback).build());
    }

    private void b(@NonNull List<T> list, @Nullable Runnable runnable) {
        Iterator<ListListener<T>> it = this.f899d.iterator();
        while (it.hasNext()) {
            it.next().onCurrentListChanged(list, this.f);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    void a(@NonNull List<T> list, @NonNull DiffUtil.DiffResult diffResult, @Nullable Runnable runnable) {
        List<T> list2 = this.f;
        this.e = list;
        this.f = Collections.unmodifiableList(list);
        diffResult.dispatchUpdatesTo(this.a);
        b(list2, runnable);
    }

    public void addListListener(@NonNull ListListener<T> listListener) {
        this.f899d.add(listListener);
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.f;
    }

    public void removeListListener(@NonNull ListListener<T> listListener) {
        this.f899d.remove(listListener);
    }

    public void submitList(@Nullable List<T> list) {
        submitList(list, null);
    }

    public void submitList(@Nullable List<T> list, @Nullable Runnable runnable) {
        int i2 = this.g + 1;
        this.g = i2;
        List<T> list2 = this.e;
        if (list == list2) {
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        List<T> list3 = this.f;
        if (list == null) {
            int size = list2.size();
            this.e = null;
            this.f = Collections.emptyList();
            this.a.onRemoved(0, size);
            b(list3, runnable);
            return;
        }
        if (list2 != null) {
            this.b.getBackgroundThreadExecutor().execute(new a(list2, list, i2, runnable));
            return;
        }
        this.e = list;
        this.f = Collections.unmodifiableList(list);
        this.a.onInserted(0, list.size());
        b(list3, runnable);
    }
}
