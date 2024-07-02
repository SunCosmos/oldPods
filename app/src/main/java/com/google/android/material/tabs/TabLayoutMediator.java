package com.google.android.material.tabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class TabLayoutMediator {

    @NonNull
    private final TabLayout a;

    @NonNull
    private final ViewPager2 b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f1714c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f1715d;
    private final TabConfigurationStrategy e;

    @Nullable
    private RecyclerView.Adapter<?> f;
    private boolean g;

    @Nullable
    private b h;

    /* renamed from: i, reason: collision with root package name */
    @Nullable
    private TabLayout.OnTabSelectedListener f1716i;

    @Nullable
    private RecyclerView.AdapterDataObserver j;

    /* loaded from: classes.dex */
    public interface TabConfigurationStrategy {
        void onConfigureTab(@NonNull TabLayout.Tab tab, int i2);
    }

    /* loaded from: classes.dex */
    private class a extends RecyclerView.AdapterDataObserver {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i2, int i3) {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i2, int i3, @Nullable Object obj) {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i2, int i3) {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i2, int i3, int i4) {
            TabLayoutMediator.this.a();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i2, int i3) {
            TabLayoutMediator.this.a();
        }
    }

    /* loaded from: classes.dex */
    private static class b extends ViewPager2.OnPageChangeCallback {

        @NonNull
        private final WeakReference<TabLayout> a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private int f1717c;

        b(TabLayout tabLayout) {
            this.a = new WeakReference<>(tabLayout);
            a();
        }

        void a() {
            this.f1717c = 0;
            this.b = 0;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int i2) {
            this.b = this.f1717c;
            this.f1717c = i2;
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrolled(int i2, float f, int i3) {
            TabLayout tabLayout = this.a.get();
            if (tabLayout != null) {
                int i4 = this.f1717c;
                tabLayout.setScrollPosition(i2, f, i4 != 2 || this.b == 1, (i4 == 2 && this.b == 0) ? false : true);
            }
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int i2) {
            TabLayout tabLayout = this.a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i2 || i2 >= tabLayout.getTabCount()) {
                return;
            }
            int i3 = this.f1717c;
            tabLayout.selectTab(tabLayout.getTabAt(i2), i3 == 0 || (i3 == 2 && this.b == 0));
        }
    }

    /* loaded from: classes.dex */
    private static class c implements TabLayout.OnTabSelectedListener {
        private final ViewPager2 a;
        private final boolean b;

        c(ViewPager2 viewPager2, boolean z) {
            this.a = viewPager2;
            this.b = z;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(@NonNull TabLayout.Tab tab) {
            this.a.setCurrentItem(tab.getPosition(), this.b);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }
    }

    public TabLayoutMediator(@NonNull TabLayout tabLayout, @NonNull ViewPager2 viewPager2, @NonNull TabConfigurationStrategy tabConfigurationStrategy) {
        this(tabLayout, viewPager2, true, tabConfigurationStrategy);
    }

    public TabLayoutMediator(@NonNull TabLayout tabLayout, @NonNull ViewPager2 viewPager2, boolean z, @NonNull TabConfigurationStrategy tabConfigurationStrategy) {
        this(tabLayout, viewPager2, z, true, tabConfigurationStrategy);
    }

    public TabLayoutMediator(@NonNull TabLayout tabLayout, @NonNull ViewPager2 viewPager2, boolean z, boolean z2, @NonNull TabConfigurationStrategy tabConfigurationStrategy) {
        this.a = tabLayout;
        this.b = viewPager2;
        this.f1714c = z;
        this.f1715d = z2;
        this.e = tabConfigurationStrategy;
    }

    void a() {
        this.a.removeAllTabs();
        RecyclerView.Adapter<?> adapter = this.f;
        if (adapter != null) {
            int itemCount = adapter.getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                TabLayout.Tab newTab = this.a.newTab();
                this.e.onConfigureTab(newTab, i2);
                this.a.addTab(newTab, false);
            }
            if (itemCount > 0) {
                int min = Math.min(this.b.getCurrentItem(), this.a.getTabCount() - 1);
                if (min != this.a.getSelectedTabPosition()) {
                    TabLayout tabLayout = this.a;
                    tabLayout.selectTab(tabLayout.getTabAt(min));
                }
            }
        }
    }

    public void attach() {
        if (this.g) {
            throw new IllegalStateException("TabLayoutMediator is already attached");
        }
        RecyclerView.Adapter<?> adapter = this.b.getAdapter();
        this.f = adapter;
        if (adapter == null) {
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        this.g = true;
        b bVar = new b(this.a);
        this.h = bVar;
        this.b.registerOnPageChangeCallback(bVar);
        c cVar = new c(this.b, this.f1715d);
        this.f1716i = cVar;
        this.a.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) cVar);
        if (this.f1714c) {
            a aVar = new a();
            this.j = aVar;
            this.f.registerAdapterDataObserver(aVar);
        }
        a();
        this.a.setScrollPosition(this.b.getCurrentItem(), 0.0f, true);
    }

    public void detach() {
        RecyclerView.Adapter<?> adapter;
        if (this.f1714c && (adapter = this.f) != null) {
            adapter.unregisterAdapterDataObserver(this.j);
            this.j = null;
        }
        this.a.removeOnTabSelectedListener(this.f1716i);
        this.b.unregisterOnPageChangeCallback(this.h);
        this.f1716i = null;
        this.h = null;
        this.f = null;
        this.g = false;
    }

    public boolean isAttached() {
        return this.g;
    }
}
