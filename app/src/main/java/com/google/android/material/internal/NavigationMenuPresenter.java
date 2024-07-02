package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Dimension;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import com.google.android.material.R;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class NavigationMenuPresenter implements MenuPresenter {
    private NavigationMenuView a;
    LinearLayout b;

    /* renamed from: c, reason: collision with root package name */
    private MenuPresenter.Callback f1599c;

    /* renamed from: d, reason: collision with root package name */
    MenuBuilder f1600d;
    private int e;
    c f;
    LayoutInflater g;
    int h;

    /* renamed from: i, reason: collision with root package name */
    boolean f1601i;
    ColorStateList j;
    ColorStateList k;
    Drawable l;
    int m;
    int n;
    int o;
    boolean p;
    private int r;
    private int s;
    int t;
    boolean q = true;
    private int u = -1;
    final View.OnClickListener v = new a();

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z = true;
            NavigationMenuPresenter.this.setUpdateSuspended(true);
            MenuItemImpl itemData = ((NavigationMenuItemView) view).getItemData();
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            boolean performItemAction = navigationMenuPresenter.f1600d.performItemAction(itemData, navigationMenuPresenter, 0);
            if (itemData != null && itemData.isCheckable() && performItemAction) {
                NavigationMenuPresenter.this.f.j(itemData);
            } else {
                z = false;
            }
            NavigationMenuPresenter.this.setUpdateSuspended(false);
            if (z) {
                NavigationMenuPresenter.this.updateMenuView(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends l {
        public b(View view) {
            super(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c extends RecyclerView.Adapter<l> {

        /* renamed from: c, reason: collision with root package name */
        private final ArrayList<e> f1602c = new ArrayList<>();

        /* renamed from: d, reason: collision with root package name */
        private MenuItemImpl f1603d;
        private boolean e;

        c() {
            h();
        }

        private void a(int i2, int i3) {
            while (i2 < i3) {
                ((g) this.f1602c.get(i2)).b = true;
                i2++;
            }
        }

        private void h() {
            if (this.e) {
                return;
            }
            this.e = true;
            this.f1602c.clear();
            this.f1602c.add(new d());
            int i2 = -1;
            int size = NavigationMenuPresenter.this.f1600d.getVisibleItems().size();
            boolean z = false;
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                MenuItemImpl menuItemImpl = NavigationMenuPresenter.this.f1600d.getVisibleItems().get(i4);
                if (menuItemImpl.isChecked()) {
                    j(menuItemImpl);
                }
                if (menuItemImpl.isCheckable()) {
                    menuItemImpl.setExclusiveCheckable(false);
                }
                if (menuItemImpl.hasSubMenu()) {
                    SubMenu subMenu = menuItemImpl.getSubMenu();
                    if (subMenu.hasVisibleItems()) {
                        if (i4 != 0) {
                            this.f1602c.add(new f(NavigationMenuPresenter.this.t, 0));
                        }
                        this.f1602c.add(new g(menuItemImpl));
                        int size2 = this.f1602c.size();
                        int size3 = subMenu.size();
                        boolean z2 = false;
                        for (int i5 = 0; i5 < size3; i5++) {
                            MenuItemImpl menuItemImpl2 = (MenuItemImpl) subMenu.getItem(i5);
                            if (menuItemImpl2.isVisible()) {
                                if (!z2 && menuItemImpl2.getIcon() != null) {
                                    z2 = true;
                                }
                                if (menuItemImpl2.isCheckable()) {
                                    menuItemImpl2.setExclusiveCheckable(false);
                                }
                                if (menuItemImpl.isChecked()) {
                                    j(menuItemImpl);
                                }
                                this.f1602c.add(new g(menuItemImpl2));
                            }
                        }
                        if (z2) {
                            a(size2, this.f1602c.size());
                        }
                    }
                } else {
                    int groupId = menuItemImpl.getGroupId();
                    if (groupId != i2) {
                        i3 = this.f1602c.size();
                        z = menuItemImpl.getIcon() != null;
                        if (i4 != 0) {
                            i3++;
                            ArrayList<e> arrayList = this.f1602c;
                            int i6 = NavigationMenuPresenter.this.t;
                            arrayList.add(new f(i6, i6));
                        }
                    } else if (!z && menuItemImpl.getIcon() != null) {
                        a(i3, this.f1602c.size());
                        z = true;
                    }
                    g gVar = new g(menuItemImpl);
                    gVar.b = z;
                    this.f1602c.add(gVar);
                    i2 = groupId;
                }
            }
            this.e = false;
        }

        @NonNull
        public Bundle b() {
            Bundle bundle = new Bundle();
            MenuItemImpl menuItemImpl = this.f1603d;
            if (menuItemImpl != null) {
                bundle.putInt("android:menu:checked", menuItemImpl.getItemId());
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.f1602c.size();
            for (int i2 = 0; i2 < size; i2++) {
                e eVar = this.f1602c.get(i2);
                if (eVar instanceof g) {
                    MenuItemImpl a = ((g) eVar).a();
                    View actionView = a != null ? a.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(a.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public MenuItemImpl c() {
            return this.f1603d;
        }

        int d() {
            int i2 = NavigationMenuPresenter.this.b.getChildCount() == 0 ? 0 : 1;
            for (int i3 = 0; i3 < NavigationMenuPresenter.this.f.getItemCount(); i3++) {
                if (NavigationMenuPresenter.this.f.getItemViewType(i3) == 0) {
                    i2++;
                }
            }
            return i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(@NonNull l lVar, int i2) {
            int itemViewType = getItemViewType(i2);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    ((TextView) lVar.itemView).setText(((g) this.f1602c.get(i2)).a().getTitle());
                    return;
                } else {
                    if (itemViewType != 2) {
                        return;
                    }
                    f fVar = (f) this.f1602c.get(i2);
                    lVar.itemView.setPadding(0, fVar.b(), 0, fVar.a());
                    return;
                }
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) lVar.itemView;
            navigationMenuItemView.setIconTintList(NavigationMenuPresenter.this.k);
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            if (navigationMenuPresenter.f1601i) {
                navigationMenuItemView.setTextAppearance(navigationMenuPresenter.h);
            }
            ColorStateList colorStateList = NavigationMenuPresenter.this.j;
            if (colorStateList != null) {
                navigationMenuItemView.setTextColor(colorStateList);
            }
            Drawable drawable = NavigationMenuPresenter.this.l;
            ViewCompat.setBackground(navigationMenuItemView, drawable != null ? drawable.getConstantState().newDrawable() : null);
            g gVar = (g) this.f1602c.get(i2);
            navigationMenuItemView.setNeedsEmptyIcon(gVar.b);
            navigationMenuItemView.setHorizontalPadding(NavigationMenuPresenter.this.m);
            navigationMenuItemView.setIconPadding(NavigationMenuPresenter.this.n);
            NavigationMenuPresenter navigationMenuPresenter2 = NavigationMenuPresenter.this;
            if (navigationMenuPresenter2.p) {
                navigationMenuItemView.setIconSize(navigationMenuPresenter2.o);
            }
            navigationMenuItemView.setMaxLines(NavigationMenuPresenter.this.r);
            navigationMenuItemView.initialize(gVar.a(), 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @Nullable
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public l onCreateViewHolder(ViewGroup viewGroup, int i2) {
            if (i2 == 0) {
                NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
                return new i(navigationMenuPresenter.g, viewGroup, navigationMenuPresenter.v);
            }
            if (i2 == 1) {
                return new k(NavigationMenuPresenter.this.g, viewGroup);
            }
            if (i2 == 2) {
                return new j(NavigationMenuPresenter.this.g, viewGroup);
            }
            if (i2 != 3) {
                return null;
            }
            return new b(NavigationMenuPresenter.this.b);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public void onViewRecycled(l lVar) {
            if (lVar instanceof i) {
                ((NavigationMenuItemView) lVar.itemView).recycle();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.f1602c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            e eVar = this.f1602c.get(i2);
            if (eVar instanceof f) {
                return 2;
            }
            if (eVar instanceof d) {
                return 3;
            }
            if (eVar instanceof g) {
                return ((g) eVar).a().hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }

        public void i(@NonNull Bundle bundle) {
            MenuItemImpl a;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            MenuItemImpl a2;
            int i2 = bundle.getInt("android:menu:checked", 0);
            if (i2 != 0) {
                this.e = true;
                int size = this.f1602c.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        break;
                    }
                    e eVar = this.f1602c.get(i3);
                    if ((eVar instanceof g) && (a2 = ((g) eVar).a()) != null && a2.getItemId() == i2) {
                        j(a2);
                        break;
                    }
                    i3++;
                }
                this.e = false;
                h();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                int size2 = this.f1602c.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    e eVar2 = this.f1602c.get(i4);
                    if ((eVar2 instanceof g) && (a = ((g) eVar2).a()) != null && (actionView = a.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(a.getItemId())) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void j(@NonNull MenuItemImpl menuItemImpl) {
            if (this.f1603d == menuItemImpl || !menuItemImpl.isCheckable()) {
                return;
            }
            MenuItemImpl menuItemImpl2 = this.f1603d;
            if (menuItemImpl2 != null) {
                menuItemImpl2.setChecked(false);
            }
            this.f1603d = menuItemImpl;
            menuItemImpl.setChecked(true);
        }

        public void k(boolean z) {
            this.e = z;
        }

        public void l() {
            h();
            notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d implements e {
        d() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface e {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f implements e {
        private final int a;
        private final int b;

        public f(int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class g implements e {
        private final MenuItemImpl a;
        boolean b;

        g(MenuItemImpl menuItemImpl) {
            this.a = menuItemImpl;
        }

        public MenuItemImpl a() {
            return this.a;
        }
    }

    /* loaded from: classes.dex */
    private class h extends RecyclerViewAccessibilityDelegate {
        h(@NonNull RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(NavigationMenuPresenter.this.f.d(), 0, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class i extends l {
        public i(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class j extends l {
        public j(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class k extends l {
        public k(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    /* loaded from: classes.dex */
    private static abstract class l extends RecyclerView.ViewHolder {
        public l(View view) {
            super(view);
        }
    }

    private void b() {
        int i2 = (this.b.getChildCount() == 0 && this.q) ? this.s : 0;
        NavigationMenuView navigationMenuView = this.a;
        navigationMenuView.setPadding(0, i2, 0, navigationMenuView.getPaddingBottom());
    }

    public void addHeaderView(@NonNull View view) {
        this.b.addView(view);
        NavigationMenuView navigationMenuView = this.a;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void dispatchApplyWindowInsets(@NonNull WindowInsetsCompat windowInsetsCompat) {
        int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
        if (this.s != systemWindowInsetTop) {
            this.s = systemWindowInsetTop;
            b();
        }
        NavigationMenuView navigationMenuView = this.a;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, windowInsetsCompat.getSystemWindowInsetBottom());
        ViewCompat.dispatchApplyWindowInsets(this.b, windowInsetsCompat);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Nullable
    public MenuItemImpl getCheckedItem() {
        return this.f.c();
    }

    public int getHeaderCount() {
        return this.b.getChildCount();
    }

    public View getHeaderView(int i2) {
        return this.b.getChildAt(i2);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.e;
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.l;
    }

    public int getItemHorizontalPadding() {
        return this.m;
    }

    public int getItemIconPadding() {
        return this.n;
    }

    public int getItemMaxLines() {
        return this.r;
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.j;
    }

    @Nullable
    public ColorStateList getItemTintList() {
        return this.k;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.a == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.g.inflate(R.layout.design_navigation_menu, viewGroup, false);
            this.a = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new h(this.a));
            if (this.f == null) {
                this.f = new c();
            }
            int i2 = this.u;
            if (i2 != -1) {
                this.a.setOverScrollMode(i2);
            }
            this.b = (LinearLayout) this.g.inflate(R.layout.design_navigation_item_header, (ViewGroup) this.a, false);
            this.a.setAdapter(this.f);
        }
        return this.a;
    }

    public View inflateHeaderView(@LayoutRes int i2) {
        View inflate = this.g.inflate(i2, (ViewGroup) this.b, false);
        addHeaderView(inflate);
        return inflate;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(@NonNull Context context, @NonNull MenuBuilder menuBuilder) {
        this.g = LayoutInflater.from(context);
        this.f1600d = menuBuilder;
        this.t = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    public boolean isBehindStatusBar() {
        return this.q;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.f1599c;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.a.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.f.i(bundle2);
            }
            SparseArray sparseParcelableArray2 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray2 != null) {
                this.b.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    @NonNull
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (this.a != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.a.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        c cVar = this.f;
        if (cVar != null) {
            bundle.putBundle("android:menu:adapter", cVar.b());
        }
        if (this.b != null) {
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            this.b.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray2);
        }
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public void removeHeaderView(@NonNull View view) {
        this.b.removeView(view);
        if (this.b.getChildCount() == 0) {
            NavigationMenuView navigationMenuView = this.a;
            navigationMenuView.setPadding(0, this.s, 0, navigationMenuView.getPaddingBottom());
        }
    }

    public void setBehindStatusBar(boolean z) {
        if (this.q != z) {
            this.q = z;
            b();
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.f1599c = callback;
    }

    public void setCheckedItem(@NonNull MenuItemImpl menuItemImpl) {
        this.f.j(menuItemImpl);
    }

    public void setId(int i2) {
        this.e = i2;
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.l = drawable;
        updateMenuView(false);
    }

    public void setItemHorizontalPadding(int i2) {
        this.m = i2;
        updateMenuView(false);
    }

    public void setItemIconPadding(int i2) {
        this.n = i2;
        updateMenuView(false);
    }

    public void setItemIconSize(@Dimension int i2) {
        if (this.o != i2) {
            this.o = i2;
            this.p = true;
            updateMenuView(false);
        }
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.k = colorStateList;
        updateMenuView(false);
    }

    public void setItemMaxLines(int i2) {
        this.r = i2;
        updateMenuView(false);
    }

    public void setItemTextAppearance(@StyleRes int i2) {
        this.h = i2;
        this.f1601i = true;
        updateMenuView(false);
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.j = colorStateList;
        updateMenuView(false);
    }

    public void setOverScrollMode(int i2) {
        this.u = i2;
        NavigationMenuView navigationMenuView = this.a;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i2);
        }
    }

    public void setUpdateSuspended(boolean z) {
        c cVar = this.f;
        if (cVar != null) {
            cVar.k(z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        c cVar = this.f;
        if (cVar != null) {
            cVar.l();
        }
    }
}
