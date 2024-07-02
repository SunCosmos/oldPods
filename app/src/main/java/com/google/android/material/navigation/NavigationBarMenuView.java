package com.google.android.material.navigation;

import android.R;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.util.Pools;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.TextScale;
import java.util.HashSet;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements MenuView {
    private static final int[] t = {R.attr.state_checked};
    private static final int[] u = {-16842910};

    @NonNull
    private final TransitionSet a;

    @NonNull
    private final View.OnClickListener b;

    /* renamed from: c, reason: collision with root package name */
    private final Pools.Pool<NavigationBarItemView> f1620c;

    /* renamed from: d, reason: collision with root package name */
    @NonNull
    private final SparseArray<View.OnTouchListener> f1621d;
    private int e;

    @Nullable
    private NavigationBarItemView[] f;
    private int g;
    private int h;

    /* renamed from: i, reason: collision with root package name */
    @Nullable
    private ColorStateList f1622i;

    @Dimension
    private int j;
    private ColorStateList k;

    @Nullable
    private final ColorStateList l;

    @StyleRes
    private int m;

    @StyleRes
    private int n;
    private Drawable o;
    private int p;

    @NonNull
    private SparseArray<BadgeDrawable> q;
    private NavigationBarPresenter r;
    private MenuBuilder s;

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MenuItemImpl itemData = ((NavigationBarItemView) view).getItemData();
            if (NavigationBarMenuView.this.s.performItemAction(itemData, NavigationBarMenuView.this.r, 0)) {
                return;
            }
            itemData.setChecked(true);
        }
    }

    public NavigationBarMenuView(@NonNull Context context) {
        super(context);
        this.f1620c = new Pools.SynchronizedPool(5);
        this.f1621d = new SparseArray<>(5);
        this.g = 0;
        this.h = 0;
        this.q = new SparseArray<>(5);
        this.l = createDefaultColorStateList(R.attr.textColorSecondary);
        AutoTransition autoTransition = new AutoTransition();
        this.a = autoTransition;
        autoTransition.setOrdering(0);
        autoTransition.setDuration(115L);
        autoTransition.setInterpolator((TimeInterpolator) new FastOutSlowInInterpolator());
        autoTransition.addTransition(new TextScale());
        this.b = new a();
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    private boolean f(int i2) {
        return i2 != -1;
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView acquire = this.f1620c.acquire();
        return acquire == null ? c(getContext()) : acquire;
    }

    private void h() {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < this.s.size(); i2++) {
            hashSet.add(Integer.valueOf(this.s.getItem(i2).getItemId()));
        }
        for (int i3 = 0; i3 < this.q.size(); i3++) {
            int keyAt = this.q.keyAt(i3);
            if (!hashSet.contains(Integer.valueOf(keyAt))) {
                this.q.delete(keyAt);
            }
        }
    }

    private void j(int i2) {
        if (f(i2)) {
            return;
        }
        throw new IllegalArgumentException(i2 + " is not a valid view id");
    }

    private void setBadgeIfNeeded(@NonNull NavigationBarItemView navigationBarItemView) {
        BadgeDrawable badgeDrawable;
        int id = navigationBarItemView.getId();
        if (f(id) && (badgeDrawable = this.q.get(id)) != null) {
            navigationBarItemView.setBadge(badgeDrawable);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void buildMenuView() {
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.f1620c.release(navigationBarItemView);
                    navigationBarItemView.f();
                }
            }
        }
        if (this.s.size() == 0) {
            this.g = 0;
            this.h = 0;
            this.f = null;
            return;
        }
        h();
        this.f = new NavigationBarItemView[this.s.size()];
        boolean e = e(this.e, this.s.getVisibleItems().size());
        for (int i2 = 0; i2 < this.s.size(); i2++) {
            this.r.setUpdateSuspended(true);
            this.s.getItem(i2).setCheckable(true);
            this.r.setUpdateSuspended(false);
            NavigationBarItemView newItem = getNewItem();
            this.f[i2] = newItem;
            newItem.setIconTintList(this.f1622i);
            newItem.setIconSize(this.j);
            newItem.setTextColor(this.l);
            newItem.setTextAppearanceInactive(this.m);
            newItem.setTextAppearanceActive(this.n);
            newItem.setTextColor(this.k);
            Drawable drawable = this.o;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.p);
            }
            newItem.setShifting(e);
            newItem.setLabelVisibilityMode(this.e);
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.s.getItem(i2);
            newItem.initialize(menuItemImpl, 0);
            newItem.setItemPosition(i2);
            int itemId = menuItemImpl.getItemId();
            newItem.setOnTouchListener(this.f1621d.get(itemId));
            newItem.setOnClickListener(this.b);
            int i3 = this.g;
            if (i3 != 0 && itemId == i3) {
                this.h = i2;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int min = Math.min(this.s.size() - 1, this.h);
        this.h = min;
        this.s.getItem(min).setChecked(true);
    }

    @NonNull
    protected abstract NavigationBarItemView c(@NonNull Context context);

    @Nullable
    public ColorStateList createDefaultColorStateList(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        int[] iArr = u;
        return new ColorStateList(new int[][]{iArr, t, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i3, defaultColor});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BadgeDrawable d(int i2) {
        j(i2);
        BadgeDrawable badgeDrawable = this.q.get(i2);
        if (badgeDrawable == null) {
            badgeDrawable = BadgeDrawable.create(getContext());
            this.q.put(i2, badgeDrawable);
        }
        NavigationBarItemView findItemView = findItemView(i2);
        if (findItemView != null) {
            findItemView.setBadge(badgeDrawable);
        }
        return badgeDrawable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean e(int i2, int i3) {
        if (i2 == -1) {
            if (i3 > 3) {
                return true;
            }
        } else if (i2 == 0) {
            return true;
        }
        return false;
    }

    @Nullable
    public NavigationBarItemView findItemView(int i2) {
        j(i2);
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr == null) {
            return null;
        }
        for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
            if (navigationBarItemView.getId() == i2) {
                return navigationBarItemView;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(int i2) {
        j(i2);
        BadgeDrawable badgeDrawable = this.q.get(i2);
        NavigationBarItemView findItemView = findItemView(i2);
        if (findItemView != null) {
            findItemView.f();
        }
        if (badgeDrawable != null) {
            this.q.remove(i2);
        }
    }

    @Nullable
    public BadgeDrawable getBadge(int i2) {
        return this.q.get(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.q;
    }

    @Nullable
    public ColorStateList getIconTintList() {
        return this.f1622i;
    }

    @Nullable
    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        return (navigationBarItemViewArr == null || navigationBarItemViewArr.length <= 0) ? this.o : navigationBarItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.p;
    }

    @Dimension
    public int getItemIconSize() {
        return this.j;
    }

    @StyleRes
    public int getItemTextAppearanceActive() {
        return this.n;
    }

    @StyleRes
    public int getItemTextAppearanceInactive() {
        return this.m;
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.k;
    }

    public int getLabelVisibilityMode() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public MenuBuilder getMenu() {
        return this.s;
    }

    public int getSelectedItemId() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getSelectedItemPosition() {
        return this.h;
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public int getWindowAnimations() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(int i2) {
        int size = this.s.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.s.getItem(i3);
            if (i2 == item.getItemId()) {
                this.g = i2;
                this.h = i3;
                item.setChecked(true);
                return;
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void initialize(@NonNull MenuBuilder menuBuilder) {
        this.s = menuBuilder;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.s.getVisibleItems().size(), false, 1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBadgeDrawables(SparseArray<BadgeDrawable> sparseArray) {
        this.q = sparseArray;
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setBadge(sparseArray.get(navigationBarItemView.getId()));
            }
        }
    }

    public void setIconTintList(@Nullable ColorStateList colorStateList) {
        this.f1622i = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.o = drawable;
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i2) {
        this.p = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(i2);
            }
        }
    }

    public void setItemIconSize(@Dimension int i2) {
        this.j = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconSize(i2);
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void setItemOnTouchListener(int i2, @Nullable View.OnTouchListener onTouchListener) {
        SparseArray<View.OnTouchListener> sparseArray = this.f1621d;
        if (onTouchListener == null) {
            sparseArray.remove(i2);
        } else {
            sparseArray.put(i2, onTouchListener);
        }
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView.getItemData().getItemId() == i2) {
                    navigationBarItemView.setOnTouchListener(onTouchListener);
                }
            }
        }
    }

    public void setItemTextAppearanceActive(@StyleRes int i2) {
        this.n = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActive(i2);
                ColorStateList colorStateList = this.k;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceInactive(@StyleRes int i2) {
        this.m = i2;
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceInactive(i2);
                ColorStateList colorStateList = this.k;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.k = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i2) {
        this.e = i2;
    }

    public void setPresenter(@NonNull NavigationBarPresenter navigationBarPresenter) {
        this.r = navigationBarPresenter;
    }

    public void updateMenuView() {
        MenuBuilder menuBuilder = this.s;
        if (menuBuilder == null || this.f == null) {
            return;
        }
        int size = menuBuilder.size();
        if (size != this.f.length) {
            buildMenuView();
            return;
        }
        int i2 = this.g;
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.s.getItem(i3);
            if (item.isChecked()) {
                this.g = item.getItemId();
                this.h = i3;
            }
        }
        if (i2 != this.g) {
            TransitionManager.beginDelayedTransition(this, this.a);
        }
        boolean e = e(this.e, this.s.getVisibleItems().size());
        for (int i4 = 0; i4 < size; i4++) {
            this.r.setUpdateSuspended(true);
            this.f[i4].setLabelVisibilityMode(this.e);
            this.f[i4].setShifting(e);
            this.f[i4].initialize((MenuItemImpl) this.s.getItem(i4), 0);
            this.r.setUpdateSuspended(false);
        }
    }
}
