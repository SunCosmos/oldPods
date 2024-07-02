package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.collection.LongSparseArray;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class FragmentStateAdapter extends RecyclerView.Adapter<FragmentViewHolder> implements StatefulAdapter {

    /* renamed from: c, reason: collision with root package name */
    final Lifecycle f1138c;

    /* renamed from: d, reason: collision with root package name */
    final FragmentManager f1139d;
    final LongSparseArray<Fragment> e;
    private final LongSparseArray<Fragment.SavedState> f;
    private final LongSparseArray<Integer> g;
    private FragmentMaxLifecycleEnforcer h;

    /* renamed from: i, reason: collision with root package name */
    boolean f1140i;
    private boolean j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class FragmentMaxLifecycleEnforcer {
        private ViewPager2.OnPageChangeCallback a;
        private RecyclerView.AdapterDataObserver b;

        /* renamed from: c, reason: collision with root package name */
        private LifecycleEventObserver f1141c;

        /* renamed from: d, reason: collision with root package name */
        private ViewPager2 f1142d;
        private long e = -1;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a extends ViewPager2.OnPageChangeCallback {
            a() {
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i2) {
                FragmentMaxLifecycleEnforcer.this.d(false);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i2) {
                FragmentMaxLifecycleEnforcer.this.d(false);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b extends d {
            b() {
                super(null);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                FragmentMaxLifecycleEnforcer.this.d(true);
            }
        }

        FragmentMaxLifecycleEnforcer() {
        }

        @NonNull
        private ViewPager2 a(@NonNull RecyclerView recyclerView) {
            ViewParent parent = recyclerView.getParent();
            if (parent instanceof ViewPager2) {
                return (ViewPager2) parent;
            }
            throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
        }

        void b(@NonNull RecyclerView recyclerView) {
            this.f1142d = a(recyclerView);
            a aVar = new a();
            this.a = aVar;
            this.f1142d.registerOnPageChangeCallback(aVar);
            b bVar = new b();
            this.b = bVar;
            FragmentStateAdapter.this.registerAdapterDataObserver(bVar);
            LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.3
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                    FragmentMaxLifecycleEnforcer.this.d(false);
                }
            };
            this.f1141c = lifecycleEventObserver;
            FragmentStateAdapter.this.f1138c.addObserver(lifecycleEventObserver);
        }

        void c(@NonNull RecyclerView recyclerView) {
            a(recyclerView).unregisterOnPageChangeCallback(this.a);
            FragmentStateAdapter.this.unregisterAdapterDataObserver(this.b);
            FragmentStateAdapter.this.f1138c.removeObserver(this.f1141c);
            this.f1142d = null;
        }

        void d(boolean z) {
            int currentItem;
            Fragment fragment;
            if (FragmentStateAdapter.this.m() || this.f1142d.getScrollState() != 0 || FragmentStateAdapter.this.e.isEmpty() || FragmentStateAdapter.this.getItemCount() == 0 || (currentItem = this.f1142d.getCurrentItem()) >= FragmentStateAdapter.this.getItemCount()) {
                return;
            }
            long itemId = FragmentStateAdapter.this.getItemId(currentItem);
            if ((itemId != this.e || z) && (fragment = FragmentStateAdapter.this.e.get(itemId)) != null && fragment.isAdded()) {
                this.e = itemId;
                FragmentTransaction beginTransaction = FragmentStateAdapter.this.f1139d.beginTransaction();
                Fragment fragment2 = null;
                for (int i2 = 0; i2 < FragmentStateAdapter.this.e.size(); i2++) {
                    long keyAt = FragmentStateAdapter.this.e.keyAt(i2);
                    Fragment valueAt = FragmentStateAdapter.this.e.valueAt(i2);
                    if (valueAt.isAdded()) {
                        if (keyAt != this.e) {
                            beginTransaction.setMaxLifecycle(valueAt, Lifecycle.State.STARTED);
                        } else {
                            fragment2 = valueAt;
                        }
                        valueAt.setMenuVisibility(keyAt == this.e);
                    }
                }
                if (fragment2 != null) {
                    beginTransaction.setMaxLifecycle(fragment2, Lifecycle.State.RESUMED);
                }
                if (beginTransaction.isEmpty()) {
                    return;
                }
                beginTransaction.commitNow();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnLayoutChangeListener {
        final /* synthetic */ FrameLayout a;
        final /* synthetic */ FragmentViewHolder b;

        a(FrameLayout frameLayout, FragmentViewHolder fragmentViewHolder) {
            this.a = frameLayout;
            this.b = fragmentViewHolder;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (this.a.getParent() != null) {
                this.a.removeOnLayoutChangeListener(this);
                FragmentStateAdapter.this.i(this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends FragmentManager.FragmentLifecycleCallbacks {
        final /* synthetic */ Fragment a;
        final /* synthetic */ FrameLayout b;

        b(Fragment fragment, FrameLayout frameLayout) {
            this.a = fragment;
            this.b = frameLayout;
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle) {
            if (fragment == this.a) {
                fragmentManager.unregisterFragmentLifecycleCallbacks(this);
                FragmentStateAdapter.this.a(view, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
            fragmentStateAdapter.f1140i = false;
            fragmentStateAdapter.d();
        }
    }

    /* loaded from: classes.dex */
    private static abstract class d extends RecyclerView.AdapterDataObserver {
        private d() {
        }

        /* synthetic */ d(a aVar) {
            this();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i2, int i3, @Nullable Object obj) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i2, int i3, int i4) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i2, int i3) {
            onChanged();
        }
    }

    public FragmentStateAdapter(@NonNull Fragment fragment) {
        this(fragment.getChildFragmentManager(), fragment.getLifecycle());
    }

    public FragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        this(fragmentActivity.getSupportFragmentManager(), fragmentActivity.getLifecycle());
    }

    public FragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        this.e = new LongSparseArray<>();
        this.f = new LongSparseArray<>();
        this.g = new LongSparseArray<>();
        this.f1140i = false;
        this.j = false;
        this.f1139d = fragmentManager;
        this.f1138c = lifecycle;
        super.setHasStableIds(true);
    }

    @NonNull
    private static String b(@NonNull String str, long j) {
        return str + j;
    }

    private void c(int i2) {
        long itemId = getItemId(i2);
        if (this.e.containsKey(itemId)) {
            return;
        }
        Fragment createFragment = createFragment(i2);
        createFragment.setInitialSavedState(this.f.get(itemId));
        this.e.put(itemId, createFragment);
    }

    private boolean e(long j) {
        View view;
        if (this.g.containsKey(j)) {
            return true;
        }
        Fragment fragment = this.e.get(j);
        return (fragment == null || (view = fragment.getView()) == null || view.getParent() == null) ? false : true;
    }

    private static boolean f(@NonNull String str, @NonNull String str2) {
        return str.startsWith(str2) && str.length() > str2.length();
    }

    private Long g(int i2) {
        Long l = null;
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            if (this.g.valueAt(i3).intValue() == i2) {
                if (l != null) {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
                l = Long.valueOf(this.g.keyAt(i3));
            }
        }
        return l;
    }

    private static long h(@NonNull String str, @NonNull String str2) {
        return Long.parseLong(str.substring(str2.length()));
    }

    private void j(long j) {
        ViewParent parent;
        Fragment fragment = this.e.get(j);
        if (fragment == null) {
            return;
        }
        if (fragment.getView() != null && (parent = fragment.getView().getParent()) != null) {
            ((FrameLayout) parent).removeAllViews();
        }
        if (!containsItem(j)) {
            this.f.remove(j);
        }
        if (!fragment.isAdded()) {
            this.e.remove(j);
            return;
        }
        if (m()) {
            this.j = true;
            return;
        }
        if (fragment.isAdded() && containsItem(j)) {
            this.f.put(j, this.f1139d.saveFragmentInstanceState(fragment));
        }
        this.f1139d.beginTransaction().remove(fragment).commitNow();
        this.e.remove(j);
    }

    private void k() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final c cVar = new c();
        this.f1138c.addObserver(new LifecycleEventObserver(this) { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.5
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    handler.removeCallbacks(cVar);
                    lifecycleOwner.getLifecycle().removeObserver(this);
                }
            }
        });
        handler.postDelayed(cVar, 10000L);
    }

    private void l(Fragment fragment, @NonNull FrameLayout frameLayout) {
        this.f1139d.registerFragmentLifecycleCallbacks(new b(fragment, frameLayout), false);
    }

    void a(@NonNull View view, @NonNull FrameLayout frameLayout) {
        if (frameLayout.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (view.getParent() == frameLayout) {
            return;
        }
        if (frameLayout.getChildCount() > 0) {
            frameLayout.removeAllViews();
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        frameLayout.addView(view);
    }

    public boolean containsItem(long j) {
        return j >= 0 && j < ((long) getItemCount());
    }

    @NonNull
    public abstract Fragment createFragment(int i2);

    void d() {
        if (!this.j || m()) {
            return;
        }
        ArraySet arraySet = new ArraySet();
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            long keyAt = this.e.keyAt(i2);
            if (!containsItem(keyAt)) {
                arraySet.add(Long.valueOf(keyAt));
                this.g.remove(keyAt);
            }
        }
        if (!this.f1140i) {
            this.j = false;
            for (int i3 = 0; i3 < this.e.size(); i3++) {
                long keyAt2 = this.e.keyAt(i3);
                if (!e(keyAt2)) {
                    arraySet.add(Long.valueOf(keyAt2));
                }
            }
        }
        Iterator<E> it = arraySet.iterator();
        while (it.hasNext()) {
            j(((Long) it.next()).longValue());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    void i(@NonNull final FragmentViewHolder fragmentViewHolder) {
        Fragment fragment = this.e.get(fragmentViewHolder.getItemId());
        if (fragment == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        FrameLayout H = fragmentViewHolder.H();
        View view = fragment.getView();
        if (!fragment.isAdded() && view != null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (fragment.isAdded() && view == null) {
            l(fragment, H);
            return;
        }
        if (fragment.isAdded() && view.getParent() != null) {
            if (view.getParent() != H) {
                a(view, H);
                return;
            }
            return;
        }
        if (fragment.isAdded()) {
            a(view, H);
            return;
        }
        if (m()) {
            if (this.f1139d.isDestroyed()) {
                return;
            }
            this.f1138c.addObserver(new LifecycleEventObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.2
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                    if (FragmentStateAdapter.this.m()) {
                        return;
                    }
                    lifecycleOwner.getLifecycle().removeObserver(this);
                    if (ViewCompat.isAttachedToWindow(fragmentViewHolder.H())) {
                        FragmentStateAdapter.this.i(fragmentViewHolder);
                    }
                }
            });
            return;
        }
        l(fragment, H);
        this.f1139d.beginTransaction().add(fragment, "f" + fragmentViewHolder.getItemId()).setMaxLifecycle(fragment, Lifecycle.State.STARTED).commitNow();
        this.h.d(false);
    }

    boolean m() {
        return this.f1139d.isStateSaved();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @CallSuper
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Preconditions.checkArgument(this.h == null);
        FragmentMaxLifecycleEnforcer fragmentMaxLifecycleEnforcer = new FragmentMaxLifecycleEnforcer();
        this.h = fragmentMaxLifecycleEnforcer;
        fragmentMaxLifecycleEnforcer.b(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull FragmentViewHolder fragmentViewHolder, int i2) {
        long itemId = fragmentViewHolder.getItemId();
        int id = fragmentViewHolder.H().getId();
        Long g = g(id);
        if (g != null && g.longValue() != itemId) {
            j(g.longValue());
            this.g.remove(g.longValue());
        }
        this.g.put(itemId, Integer.valueOf(id));
        c(i2);
        FrameLayout H = fragmentViewHolder.H();
        if (ViewCompat.isAttachedToWindow(H)) {
            if (H.getParent() != null) {
                throw new IllegalStateException("Design assumption violated.");
            }
            H.addOnLayoutChangeListener(new a(H, fragmentViewHolder));
        }
        d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final FragmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return FragmentViewHolder.G(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @CallSuper
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        this.h.c(recyclerView);
        this.h = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final boolean onFailedToRecycleView(@NonNull FragmentViewHolder fragmentViewHolder) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewAttachedToWindow(@NonNull FragmentViewHolder fragmentViewHolder) {
        i(fragmentViewHolder);
        d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewRecycled(@NonNull FragmentViewHolder fragmentViewHolder) {
        Long g = g(fragmentViewHolder.H().getId());
        if (g != null) {
            j(g.longValue());
            this.g.remove(g.longValue());
        }
    }

    @Override // androidx.viewpager2.adapter.StatefulAdapter
    public final void restoreState(@NonNull Parcelable parcelable) {
        long h;
        Object fragment;
        LongSparseArray longSparseArray;
        if (!this.f.isEmpty() || !this.e.isEmpty()) {
            throw new IllegalStateException("Expected the adapter to be 'fresh' while restoring state.");
        }
        Bundle bundle = (Bundle) parcelable;
        if (bundle.getClassLoader() == null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        for (String str : bundle.keySet()) {
            if (f(str, "f#")) {
                h = h(str, "f#");
                fragment = this.f1139d.getFragment(bundle, str);
                longSparseArray = this.e;
            } else {
                if (!f(str, "s#")) {
                    throw new IllegalArgumentException("Unexpected key in savedState: " + str);
                }
                h = h(str, "s#");
                fragment = (Fragment.SavedState) bundle.getParcelable(str);
                if (containsItem(h)) {
                    longSparseArray = this.f;
                }
            }
            longSparseArray.put(h, fragment);
        }
        if (this.e.isEmpty()) {
            return;
        }
        this.j = true;
        this.f1140i = true;
        d();
        k();
    }

    @Override // androidx.viewpager2.adapter.StatefulAdapter
    @NonNull
    public final Parcelable saveState() {
        Bundle bundle = new Bundle(this.e.size() + this.f.size());
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            long keyAt = this.e.keyAt(i2);
            Fragment fragment = this.e.get(keyAt);
            if (fragment != null && fragment.isAdded()) {
                this.f1139d.putFragment(bundle, b("f#", keyAt), fragment);
            }
        }
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            long keyAt2 = this.f.keyAt(i3);
            if (containsItem(keyAt2)) {
                bundle.putParcelable(b("s#", keyAt2), this.f.get(keyAt2));
            }
        }
        return bundle;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Stable Ids are required for the adapter to function properly, and the adapter takes care of setting the flag.");
    }
}
