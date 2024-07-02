package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class NavigationBarView extends FrameLayout {
    public static final int LABEL_VISIBILITY_AUTO = -1;
    public static final int LABEL_VISIBILITY_LABELED = 1;
    public static final int LABEL_VISIBILITY_SELECTED = 0;
    public static final int LABEL_VISIBILITY_UNLABELED = 2;

    @NonNull
    private final NavigationBarMenu a;

    @NonNull
    private final NavigationBarMenuView b;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    private final NavigationBarPresenter f1625c;

    /* renamed from: d, reason: collision with root package name */
    @Nullable
    private ColorStateList f1626d;
    private MenuInflater e;
    private OnItemSelectedListener f;
    private OnItemReselectedListener g;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface LabelVisibility {
    }

    /* loaded from: classes.dex */
    public interface OnItemReselectedListener {
        void onNavigationItemReselected(@NonNull MenuItem menuItem);
    }

    /* loaded from: classes.dex */
    public interface OnItemSelectedListener {
        boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        @Nullable
        Bundle b;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            @Nullable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(@NonNull Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            @NonNull
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(@NonNull Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(@NonNull Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            a(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void a(@NonNull Parcel parcel, ClassLoader classLoader) {
            this.b = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeBundle(this.b);
        }
    }

    /* loaded from: classes.dex */
    class a implements MenuBuilder.Callback {
        a() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            if (NavigationBarView.this.g == null || menuItem.getItemId() != NavigationBarView.this.getSelectedItemId()) {
                return (NavigationBarView.this.f == null || NavigationBarView.this.f.onNavigationItemSelected(menuItem)) ? false : true;
            }
            NavigationBarView.this.g.onNavigationItemReselected(menuItem);
            return true;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ViewUtils.OnApplyWindowInsetsListener {
        b(NavigationBarView navigationBarView) {
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        @NonNull
        public WindowInsetsCompat onApplyWindowInsets(View view, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull ViewUtils.RelativePadding relativePadding) {
            relativePadding.bottom += windowInsetsCompat.getSystemWindowInsetBottom();
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            int systemWindowInsetLeft = windowInsetsCompat.getSystemWindowInsetLeft();
            int systemWindowInsetRight = windowInsetsCompat.getSystemWindowInsetRight();
            relativePadding.start += z ? systemWindowInsetRight : systemWindowInsetLeft;
            int i2 = relativePadding.end;
            if (!z) {
                systemWindowInsetLeft = systemWindowInsetRight;
            }
            relativePadding.end = i2 + systemWindowInsetLeft;
            relativePadding.applyToView(view);
            return windowInsetsCompat;
        }
    }

    public NavigationBarView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i2, i3), attributeSet, i2);
        NavigationBarPresenter navigationBarPresenter = new NavigationBarPresenter();
        this.f1625c = navigationBarPresenter;
        Context context2 = getContext();
        int[] iArr = R.styleable.NavigationBarView;
        int i4 = R.styleable.NavigationBarView_itemTextAppearanceInactive;
        int i5 = R.styleable.NavigationBarView_itemTextAppearanceActive;
        TintTypedArray obtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, iArr, i2, i3, i4, i5);
        NavigationBarMenu navigationBarMenu = new NavigationBarMenu(context2, getClass(), getMaxItemCount());
        this.a = navigationBarMenu;
        NavigationBarMenuView e = e(context2);
        this.b = e;
        navigationBarPresenter.setMenuView(e);
        navigationBarPresenter.setId(1);
        e.setPresenter(navigationBarPresenter);
        navigationBarMenu.addMenuPresenter(navigationBarPresenter);
        navigationBarPresenter.initForMenu(getContext(), navigationBarMenu);
        int i6 = R.styleable.NavigationBarView_itemIconTint;
        e.setIconTintList(obtainTintedStyledAttributes.hasValue(i6) ? obtainTintedStyledAttributes.getColorStateList(i6) : e.createDefaultColorStateList(android.R.attr.textColorSecondary));
        setItemIconSize(obtainTintedStyledAttributes.getDimensionPixelSize(R.styleable.NavigationBarView_itemIconSize, getResources().getDimensionPixelSize(R.dimen.mtrl_navigation_bar_item_default_icon_size)));
        if (obtainTintedStyledAttributes.hasValue(i4)) {
            setItemTextAppearanceInactive(obtainTintedStyledAttributes.getResourceId(i4, 0));
        }
        if (obtainTintedStyledAttributes.hasValue(i5)) {
            setItemTextAppearanceActive(obtainTintedStyledAttributes.getResourceId(i5, 0));
        }
        int i7 = R.styleable.NavigationBarView_itemTextColor;
        if (obtainTintedStyledAttributes.hasValue(i7)) {
            setItemTextColor(obtainTintedStyledAttributes.getColorStateList(i7));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            ViewCompat.setBackground(this, d(context2));
        }
        if (obtainTintedStyledAttributes.hasValue(R.styleable.NavigationBarView_elevation)) {
            setElevation(obtainTintedStyledAttributes.getDimensionPixelSize(r12, 0));
        }
        DrawableCompat.setTintList(getBackground().mutate(), MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, R.styleable.NavigationBarView_backgroundTint));
        setLabelVisibilityMode(obtainTintedStyledAttributes.getInteger(R.styleable.NavigationBarView_labelVisibilityMode, -1));
        int resourceId = obtainTintedStyledAttributes.getResourceId(R.styleable.NavigationBarView_itemBackground, 0);
        if (resourceId != 0) {
            e.setItemBackgroundRes(resourceId);
        } else {
            setItemRippleColor(MaterialResources.getColorStateList(context2, obtainTintedStyledAttributes, R.styleable.NavigationBarView_itemRippleColor));
        }
        int i8 = R.styleable.NavigationBarView_menu;
        if (obtainTintedStyledAttributes.hasValue(i8)) {
            inflateMenu(obtainTintedStyledAttributes.getResourceId(i8, 0));
        }
        obtainTintedStyledAttributes.recycle();
        addView(e);
        navigationBarMenu.setCallback(new a());
        c();
    }

    private void c() {
        ViewUtils.doOnApplyWindowInsets(this, new b(this));
    }

    @NonNull
    private MaterialShapeDrawable d(Context context) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
        }
        materialShapeDrawable.initializeElevationOverlay(context);
        return materialShapeDrawable;
    }

    private MenuInflater getMenuInflater() {
        if (this.e == null) {
            this.e = new SupportMenuInflater(getContext());
        }
        return this.e;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected abstract NavigationBarMenuView e(@NonNull Context context);

    @Nullable
    public BadgeDrawable getBadge(int i2) {
        return this.b.getBadge(i2);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.b.getItemBackground();
    }

    @DrawableRes
    @Deprecated
    public int getItemBackgroundResource() {
        return this.b.getItemBackgroundRes();
    }

    @Dimension
    public int getItemIconSize() {
        return this.b.getItemIconSize();
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.b.getIconTintList();
    }

    @Nullable
    public ColorStateList getItemRippleColor() {
        return this.f1626d;
    }

    @StyleRes
    public int getItemTextAppearanceActive() {
        return this.b.getItemTextAppearanceActive();
    }

    @StyleRes
    public int getItemTextAppearanceInactive() {
        return this.b.getItemTextAppearanceInactive();
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.b.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.b.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    @NonNull
    public Menu getMenu() {
        return this.a;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MenuView getMenuView() {
        return this.b;
    }

    @NonNull
    public BadgeDrawable getOrCreateBadge(int i2) {
        return this.b.d(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public NavigationBarPresenter getPresenter() {
        return this.f1625c;
    }

    @IdRes
    public int getSelectedItemId() {
        return this.b.getSelectedItemId();
    }

    public void inflateMenu(int i2) {
        this.f1625c.setUpdateSuspended(true);
        getMenuInflater().inflate(i2, this.a);
        this.f1625c.setUpdateSuspended(false);
        this.f1625c.updateMenuView(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(@Nullable Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.a.restorePresenterStates(savedState.b);
    }

    @Override // android.view.View
    @NonNull
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.b = bundle;
        this.a.savePresenterStates(bundle);
        return savedState;
    }

    public void removeBadge(int i2) {
        this.b.g(i2);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f);
        }
        MaterialShapeUtils.setElevation(this, f);
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.b.setItemBackground(drawable);
        this.f1626d = null;
    }

    public void setItemBackgroundResource(@DrawableRes int i2) {
        this.b.setItemBackgroundRes(i2);
        this.f1626d = null;
    }

    public void setItemIconSize(@Dimension int i2) {
        this.b.setItemIconSize(i2);
    }

    public void setItemIconSizeRes(@DimenRes int i2) {
        setItemIconSize(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.b.setIconTintList(colorStateList);
    }

    public void setItemOnTouchListener(int i2, @Nullable View.OnTouchListener onTouchListener) {
        this.b.setItemOnTouchListener(i2, onTouchListener);
    }

    public void setItemRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.f1626d == colorStateList) {
            if (colorStateList != null || this.b.getItemBackground() == null) {
                return;
            }
            this.b.setItemBackground(null);
            return;
        }
        this.f1626d = colorStateList;
        if (colorStateList == null) {
            this.b.setItemBackground(null);
            return;
        }
        ColorStateList convertToRippleDrawableColor = RippleUtils.convertToRippleDrawableColor(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.b.setItemBackground(new RippleDrawable(convertToRippleDrawableColor, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable wrap = DrawableCompat.wrap(gradientDrawable);
        DrawableCompat.setTintList(wrap, convertToRippleDrawableColor);
        this.b.setItemBackground(wrap);
    }

    public void setItemTextAppearanceActive(@StyleRes int i2) {
        this.b.setItemTextAppearanceActive(i2);
    }

    public void setItemTextAppearanceInactive(@StyleRes int i2) {
        this.b.setItemTextAppearanceInactive(i2);
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.b.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.b.getLabelVisibilityMode() != i2) {
            this.b.setLabelVisibilityMode(i2);
            this.f1625c.updateMenuView(false);
        }
    }

    public void setOnItemReselectedListener(@Nullable OnItemReselectedListener onItemReselectedListener) {
        this.g = onItemReselectedListener;
    }

    public void setOnItemSelectedListener(@Nullable OnItemSelectedListener onItemSelectedListener) {
        this.f = onItemSelectedListener;
    }

    public void setSelectedItemId(@IdRes int i2) {
        MenuItem findItem = this.a.findItem(i2);
        if (findItem == null || this.a.performItemAction(findItem, this.f1625c, 0)) {
            return;
        }
        findItem.setChecked(true);
    }
}
