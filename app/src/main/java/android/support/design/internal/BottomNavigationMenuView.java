package android.support.design.internal;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.design.R;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.util.Pools;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: lib/Mus.dex */
public class BottomNavigationMenuView extends ViewGroup implements MenuView {
    private static final long ACTIVE_ANIMATION_DURATION_MS = 115;
    private final int mActiveItemMaxWidth;
    private BottomNavigationItemView[] mButtons;
    private final int mInactiveItemMaxWidth;
    private final int mInactiveItemMinWidth;
    private int mItemBackgroundRes;
    private final int mItemHeight;
    private ColorStateList mItemIconTint;
    private final Pools.Pool<BottomNavigationItemView> mItemPool;
    private ColorStateList mItemTextColor;
    private MenuBuilder mMenu;
    private final View.OnClickListener mOnClickListener;
    private BottomNavigationPresenter mPresenter;
    private int mSelectedItemId;
    private int mSelectedItemPosition;
    private final TransitionSet mSet;
    private boolean mShiftingMode;
    private int[] mTempChildWidths;

    public BottomNavigationMenuView(Context context) {
        this(context, null);
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItemPool = new Pools.SynchronizedPool(5);
        this.mShiftingMode = true;
        this.mSelectedItemId = 0;
        this.mSelectedItemPosition = 0;
        Resources resources = getResources();
        this.mInactiveItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.mInactiveItemMinWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.mActiveItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.mItemHeight = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_height);
        this.mSet = new AutoTransition();
        this.mSet.setOrdering(0);
        this.mSet.setDuration(ACTIVE_ANIMATION_DURATION_MS);
        this.mSet.setInterpolator((TimeInterpolator) new FastOutSlowInInterpolator());
        this.mSet.addTransition(new TextScale());
        this.mOnClickListener = new View.OnClickListener() { // from class: android.support.design.internal.BottomNavigationMenuView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MenuItemImpl itemData = ((BottomNavigationItemView) view).getItemData();
                if (!BottomNavigationMenuView.this.mMenu.performItemAction(itemData, BottomNavigationMenuView.this.mPresenter, 0)) {
                    itemData.setChecked(true);
                }
            }
        };
        this.mTempChildWidths = new int[5];
    }

    @Override // android.support.v7.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int childCount = getChildCount();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mItemHeight, BasicMeasure.EXACTLY);
        if (this.mShiftingMode) {
            int i4 = childCount - 1;
            int min = Math.min(size - (i4 * this.mInactiveItemMinWidth), this.mActiveItemMaxWidth);
            int min2 = Math.min((size - min) / i4, this.mInactiveItemMaxWidth);
            int i5 = (size - min) - (min2 * i4);
            int i6 = 0;
            while (i6 < childCount) {
                this.mTempChildWidths[i6] = i6 == this.mSelectedItemPosition ? min : min2;
                if (i5 > 0) {
                    int[] iArr = this.mTempChildWidths;
                    int i7 = i6;
                    iArr[i7] = iArr[i7] + 1;
                    i5--;
                }
                i6++;
            }
        } else {
            int min3 = Math.min(size / (childCount == 0 ? 1 : childCount), this.mActiveItemMaxWidth);
            int i8 = size - (min3 * childCount);
            for (int i9 = 0; i9 < childCount; i9++) {
                this.mTempChildWidths[i9] = min3;
                if (i8 > 0) {
                    int[] iArr2 = this.mTempChildWidths;
                    int i10 = i9;
                    iArr2[i10] = iArr2[i10] + 1;
                    i8--;
                }
            }
        }
        int i11 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.mTempChildWidths[i12], BasicMeasure.EXACTLY), makeMeasureSpec);
                childAt.getLayoutParams().width = childAt.getMeasuredWidth();
                i11 += childAt.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(i11, View.MeasureSpec.makeMeasureSpec(i11, BasicMeasure.EXACTLY), 0), View.resolveSizeAndState(this.mItemHeight, makeMeasureSpec, 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    childAt.layout((i6 - i8) - childAt.getMeasuredWidth(), 0, i6 - i8, i7);
                } else {
                    childAt.layout(i8, 0, childAt.getMeasuredWidth() + i8, i7);
                }
                i8 += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.support.v7.view.menu.MenuView
    public int getWindowAnimations() {
        return 0;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.mItemIconTint = colorStateList;
        if (this.mButtons != null) {
            for (BottomNavigationItemView bottomNavigationItemView : this.mButtons) {
                bottomNavigationItemView.setIconTintList(colorStateList);
            }
        }
    }

    @Nullable
    public ColorStateList getIconTintList() {
        return this.mItemIconTint;
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.mItemTextColor = colorStateList;
        if (this.mButtons != null) {
            for (BottomNavigationItemView bottomNavigationItemView : this.mButtons) {
                bottomNavigationItemView.setTextColor(colorStateList);
            }
        }
    }

    public ColorStateList getItemTextColor() {
        return this.mItemTextColor;
    }

    public void setItemBackgroundRes(int i2) {
        this.mItemBackgroundRes = i2;
        if (this.mButtons != null) {
            for (BottomNavigationItemView bottomNavigationItemView : this.mButtons) {
                bottomNavigationItemView.setItemBackground(i2);
            }
        }
    }

    public int getItemBackgroundRes() {
        return this.mItemBackgroundRes;
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.mPresenter = bottomNavigationPresenter;
    }

    public void buildMenuView() {
        removeAllViews();
        if (this.mButtons != null) {
            for (BottomNavigationItemView bottomNavigationItemView : this.mButtons) {
                this.mItemPool.release(bottomNavigationItemView);
            }
        }
        if (this.mMenu.size() == 0) {
            this.mSelectedItemId = 0;
            this.mSelectedItemPosition = 0;
            this.mButtons = null;
            return;
        }
        this.mButtons = new BottomNavigationItemView[this.mMenu.size()];
        this.mShiftingMode = this.mMenu.size() > 3;
        for (int i2 = 0; i2 < this.mMenu.size(); i2++) {
            this.mPresenter.setUpdateSuspended(true);
            this.mMenu.getItem(i2).setCheckable(true);
            this.mPresenter.setUpdateSuspended(false);
            BottomNavigationItemView newItem = getNewItem();
            this.mButtons[i2] = newItem;
            newItem.setIconTintList(this.mItemIconTint);
            newItem.setTextColor(this.mItemTextColor);
            newItem.setItemBackground(this.mItemBackgroundRes);
            newItem.setShiftingMode(this.mShiftingMode);
            newItem.initialize((MenuItemImpl) this.mMenu.getItem(i2), 0);
            newItem.setItemPosition(i2);
            newItem.setOnClickListener(this.mOnClickListener);
            addView(newItem);
        }
        this.mSelectedItemPosition = Math.min(this.mMenu.size() - 1, this.mSelectedItemPosition);
        this.mMenu.getItem(this.mSelectedItemPosition).setChecked(true);
    }

    public void updateMenuView() {
        int size = this.mMenu.size();
        if (size != this.mButtons.length) {
            buildMenuView();
            return;
        }
        int i2 = this.mSelectedItemId;
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.mMenu.getItem(i3);
            if (item.isChecked()) {
                this.mSelectedItemId = item.getItemId();
                this.mSelectedItemPosition = i3;
            }
        }
        if (i2 != this.mSelectedItemId) {
            TransitionManager.beginDelayedTransition(this, this.mSet);
        }
        for (int i4 = 0; i4 < size; i4++) {
            this.mPresenter.setUpdateSuspended(true);
            this.mButtons[i4].initialize((MenuItemImpl) this.mMenu.getItem(i4), 0);
            this.mPresenter.setUpdateSuspended(false);
        }
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView acquire = this.mItemPool.acquire();
        if (acquire == null) {
            acquire = new BottomNavigationItemView(getContext());
        }
        return acquire;
    }

    public int getSelectedItemId() {
        return this.mSelectedItemId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void tryRestoreSelectedItemId(int i2) {
        int size = this.mMenu.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.mMenu.getItem(i3);
            if (i2 == item.getItemId()) {
                this.mSelectedItemId = i2;
                this.mSelectedItemPosition = i3;
                item.setChecked(true);
                return;
            }
        }
    }
}
