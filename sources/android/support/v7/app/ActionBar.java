package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionMode;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: lib/Mus.dex */
public abstract class ActionBar {
    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_CUSTOM = 16;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_USE_LOGO = 1;

    @Deprecated
    public static final int NAVIGATION_MODE_LIST = 1;

    @Deprecated
    public static final int NAVIGATION_MODE_STANDARD = 0;

    @Deprecated
    public static final int NAVIGATION_MODE_TABS = 2;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface DisplayOptions {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface NavigationMode {
    }

    /* loaded from: lib/Mus.dex */
    public interface OnMenuVisibilityListener {
        void onMenuVisibilityChanged(boolean z);
    }

    @Deprecated
    /* loaded from: lib/Mus.dex */
    public interface OnNavigationListener {
        boolean onNavigationItemSelected(int i2, long j);
    }

    @Deprecated
    /* loaded from: lib/Mus.dex */
    public static abstract class Tab {
        public static final int INVALID_POSITION = -1;

        public abstract CharSequence getContentDescription();

        public abstract View getCustomView();

        public abstract Drawable getIcon();

        public abstract int getPosition();

        public abstract Object getTag();

        public abstract CharSequence getText();

        public abstract void select();

        public abstract Tab setContentDescription(@StringRes int i2);

        public abstract Tab setContentDescription(CharSequence charSequence);

        public abstract Tab setCustomView(int i2);

        public abstract Tab setCustomView(View view);

        public abstract Tab setIcon(@DrawableRes int i2);

        public abstract Tab setIcon(Drawable drawable);

        public abstract Tab setTabListener(TabListener tabListener);

        public abstract Tab setTag(Object obj);

        public abstract Tab setText(int i2);

        public abstract Tab setText(CharSequence charSequence);
    }

    @Deprecated
    /* loaded from: lib/Mus.dex */
    public interface TabListener {
        void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction);

        void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction);

        void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction);
    }

    public abstract void addOnMenuVisibilityListener(OnMenuVisibilityListener onMenuVisibilityListener);

    @Deprecated
    public abstract void addTab(Tab tab);

    @Deprecated
    public abstract void addTab(Tab tab, int i2);

    @Deprecated
    public abstract void addTab(Tab tab, int i2, boolean z);

    @Deprecated
    public abstract void addTab(Tab tab, boolean z);

    public abstract View getCustomView();

    public abstract int getDisplayOptions();

    public abstract int getHeight();

    @Deprecated
    public abstract int getNavigationItemCount();

    @Deprecated
    public abstract int getNavigationMode();

    @Deprecated
    public abstract int getSelectedNavigationIndex();

    @Nullable
    @Deprecated
    public abstract Tab getSelectedTab();

    @Nullable
    public abstract CharSequence getSubtitle();

    @Deprecated
    public abstract Tab getTabAt(int i2);

    @Deprecated
    public abstract int getTabCount();

    @Nullable
    public abstract CharSequence getTitle();

    public abstract void hide();

    public abstract boolean isShowing();

    @Deprecated
    public abstract Tab newTab();

    @Deprecated
    public abstract void removeAllTabs();

    public abstract void removeOnMenuVisibilityListener(OnMenuVisibilityListener onMenuVisibilityListener);

    @Deprecated
    public abstract void removeTab(Tab tab);

    @Deprecated
    public abstract void removeTabAt(int i2);

    @Deprecated
    public abstract void selectTab(Tab tab);

    public abstract void setBackgroundDrawable(@Nullable Drawable drawable);

    public abstract void setCustomView(int i2);

    public abstract void setCustomView(View view);

    public abstract void setCustomView(View view, LayoutParams layoutParams);

    public abstract void setDisplayHomeAsUpEnabled(boolean z);

    public abstract void setDisplayOptions(int i2);

    public abstract void setDisplayOptions(int i2, int i3);

    public abstract void setDisplayShowCustomEnabled(boolean z);

    public abstract void setDisplayShowHomeEnabled(boolean z);

    public abstract void setDisplayShowTitleEnabled(boolean z);

    public abstract void setDisplayUseLogoEnabled(boolean z);

    public abstract void setIcon(@DrawableRes int i2);

    public abstract void setIcon(Drawable drawable);

    @Deprecated
    public abstract void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, OnNavigationListener onNavigationListener);

    public abstract void setLogo(@DrawableRes int i2);

    public abstract void setLogo(Drawable drawable);

    @Deprecated
    public abstract void setNavigationMode(int i2);

    @Deprecated
    public abstract void setSelectedNavigationItem(int i2);

    public abstract void setSubtitle(int i2);

    public abstract void setSubtitle(CharSequence charSequence);

    public abstract void setTitle(@StringRes int i2);

    public abstract void setTitle(CharSequence charSequence);

    public abstract void show();

    public void setStackedBackgroundDrawable(Drawable drawable) {
    }

    public void setSplitBackgroundDrawable(Drawable drawable) {
    }

    public void setHomeButtonEnabled(boolean z) {
    }

    public Context getThemedContext() {
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isTitleTruncated() {
        return false;
    }

    public void setHomeAsUpIndicator(@Nullable Drawable drawable) {
    }

    public void setHomeAsUpIndicator(@DrawableRes int i2) {
    }

    public void setHomeActionContentDescription(@Nullable CharSequence charSequence) {
    }

    public void setHomeActionContentDescription(@StringRes int i2) {
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    public boolean isHideOnContentScrollEnabled() {
        return false;
    }

    public int getHideOffset() {
        return 0;
    }

    public void setHideOffset(int i2) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Setting an explicit action bar hide offset is not supported in this action bar configuration.");
        }
    }

    public void setElevation(float f) {
        if (f != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    public float getElevation() {
        return 0.0f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setShowHideAnimationEnabled(boolean z) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onConfigurationChanged(Configuration configuration) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void dispatchMenuVisibilityChanged(boolean z) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean openOptionsMenu() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean closeOptionsMenu() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean invalidateOptionsMenu() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean onMenuKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean onKeyShortcut(int i2, KeyEvent keyEvent) {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean collapseActionView() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setWindowTitle(CharSequence charSequence) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    boolean requestFocus() {
        return false;
    }

    public void onDestroy() {
    }

    /* loaded from: lib/Mus.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;

        public LayoutParams(@NonNull Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBarLayout);
            this.gravity = obtainStyledAttributes.getInt(R.styleable.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.gravity = 0;
            this.gravity = 8388627;
        }

        public LayoutParams(int i2, int i3, int i4) {
            super(i2, i3);
            this.gravity = 0;
            this.gravity = i4;
        }

        public LayoutParams(int i2) {
            this(-2, -1, i2);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
        }
    }
}
