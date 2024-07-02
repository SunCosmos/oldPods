package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

/* loaded from: lib/Mus.dex */
public final class MenuItemCompat {
    static final MenuVersionImpl IMPL;

    @Deprecated
    public static final int SHOW_AS_ACTION_ALWAYS = 2;

    @Deprecated
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;

    @Deprecated
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;

    @Deprecated
    public static final int SHOW_AS_ACTION_NEVER = 0;

    @Deprecated
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    /* loaded from: lib/Mus.dex */
    interface MenuVersionImpl {
        int getAlphabeticModifiers(MenuItem menuItem);

        CharSequence getContentDescription(MenuItem menuItem);

        ColorStateList getIconTintList(MenuItem menuItem);

        PorterDuff.Mode getIconTintMode(MenuItem menuItem);

        int getNumericModifiers(MenuItem menuItem);

        CharSequence getTooltipText(MenuItem menuItem);

        void setAlphabeticShortcut(MenuItem menuItem, char c2, int i2);

        void setContentDescription(MenuItem menuItem, CharSequence charSequence);

        void setIconTintList(MenuItem menuItem, ColorStateList colorStateList);

        void setIconTintMode(MenuItem menuItem, PorterDuff.Mode mode);

        void setNumericShortcut(MenuItem menuItem, char c2, int i2);

        void setShortcut(MenuItem menuItem, char c2, char c3, int i2, int i3);

        void setTooltipText(MenuItem menuItem, CharSequence charSequence);
    }

    @Deprecated
    /* loaded from: lib/Mus.dex */
    public interface OnActionExpandListener {
        boolean onMenuItemActionCollapse(MenuItem menuItem);

        boolean onMenuItemActionExpand(MenuItem menuItem);
    }

    /* loaded from: lib/Mus.dex */
    static class MenuItemCompatBaseImpl implements MenuVersionImpl {
        MenuItemCompatBaseImpl() {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setContentDescription(MenuItem menuItem, CharSequence charSequence) {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public CharSequence getContentDescription(MenuItem menuItem) {
            return null;
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setTooltipText(MenuItem menuItem, CharSequence charSequence) {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public CharSequence getTooltipText(MenuItem menuItem) {
            return null;
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setShortcut(MenuItem menuItem, char c2, char c3, int i2, int i3) {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setAlphabeticShortcut(MenuItem menuItem, char c2, int i2) {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public int getAlphabeticModifiers(MenuItem menuItem) {
            return 0;
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setNumericShortcut(MenuItem menuItem, char c2, int i2) {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public int getNumericModifiers(MenuItem menuItem) {
            return 0;
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setIconTintList(MenuItem menuItem, ColorStateList colorStateList) {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public ColorStateList getIconTintList(MenuItem menuItem) {
            return null;
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setIconTintMode(MenuItem menuItem, PorterDuff.Mode mode) {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public PorterDuff.Mode getIconTintMode(MenuItem menuItem) {
            return null;
        }
    }

    @RequiresApi(26)
    /* loaded from: lib/Mus.dex */
    static class MenuItemCompatApi26Impl extends MenuItemCompatBaseImpl {
        MenuItemCompatApi26Impl() {
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setContentDescription(MenuItem menuItem, CharSequence charSequence) {
            menuItem.setContentDescription(charSequence);
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public CharSequence getContentDescription(MenuItem menuItem) {
            return menuItem.getContentDescription();
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setTooltipText(MenuItem menuItem, CharSequence charSequence) {
            menuItem.setTooltipText(charSequence);
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public CharSequence getTooltipText(MenuItem menuItem) {
            return menuItem.getTooltipText();
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setShortcut(MenuItem menuItem, char c2, char c3, int i2, int i3) {
            menuItem.setShortcut(c2, c3, i2, i3);
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setAlphabeticShortcut(MenuItem menuItem, char c2, int i2) {
            menuItem.setAlphabeticShortcut(c2, i2);
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public int getAlphabeticModifiers(MenuItem menuItem) {
            return menuItem.getAlphabeticModifiers();
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setNumericShortcut(MenuItem menuItem, char c2, int i2) {
            menuItem.setNumericShortcut(c2, i2);
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public int getNumericModifiers(MenuItem menuItem) {
            return menuItem.getNumericModifiers();
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setIconTintList(MenuItem menuItem, ColorStateList colorStateList) {
            menuItem.setIconTintList(colorStateList);
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public ColorStateList getIconTintList(MenuItem menuItem) {
            return menuItem.getIconTintList();
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public void setIconTintMode(MenuItem menuItem, PorterDuff.Mode mode) {
            menuItem.setIconTintMode(mode);
        }

        @Override // android.support.v4.view.MenuItemCompat.MenuItemCompatBaseImpl, android.support.v4.view.MenuItemCompat.MenuVersionImpl
        public PorterDuff.Mode getIconTintMode(MenuItem menuItem) {
            return menuItem.getIconTintMode();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            IMPL = new MenuItemCompatApi26Impl();
        } else {
            IMPL = new MenuItemCompatBaseImpl();
        }
    }

    @Deprecated
    public static void setShowAsAction(MenuItem menuItem, int i2) {
        menuItem.setShowAsAction(i2);
    }

    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem, View view) {
        return menuItem.setActionView(view);
    }

    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem, int i2) {
        return menuItem.setActionView(i2);
    }

    @Deprecated
    public static View getActionView(MenuItem menuItem) {
        return menuItem.getActionView();
    }

    public static MenuItem setActionProvider(MenuItem menuItem, ActionProvider actionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).setSupportActionProvider(actionProvider);
        }
        Log.w(TAG, "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static ActionProvider getActionProvider(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).getSupportActionProvider();
        }
        Log.w(TAG, "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    @Deprecated
    public static boolean expandActionView(MenuItem menuItem) {
        return menuItem.expandActionView();
    }

    @Deprecated
    public static boolean collapseActionView(MenuItem menuItem) {
        return menuItem.collapseActionView();
    }

    @Deprecated
    public static boolean isActionViewExpanded(MenuItem menuItem) {
        return menuItem.isActionViewExpanded();
    }

    @Deprecated
    public static MenuItem setOnActionExpandListener(MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
        return menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() { // from class: android.support.v4.view.MenuItemCompat.1
            @Override // android.view.MenuItem.OnActionExpandListener
            public boolean onMenuItemActionExpand(MenuItem menuItem2) {
                return OnActionExpandListener.this.onMenuItemActionExpand(menuItem2);
            }

            @Override // android.view.MenuItem.OnActionExpandListener
            public boolean onMenuItemActionCollapse(MenuItem menuItem2) {
                return OnActionExpandListener.this.onMenuItemActionCollapse(menuItem2);
            }
        });
    }

    public static void setContentDescription(MenuItem menuItem, CharSequence charSequence) {
        if (!(menuItem instanceof SupportMenuItem)) {
            IMPL.setContentDescription(menuItem, charSequence);
        } else {
            ((SupportMenuItem) menuItem).setContentDescription(charSequence);
        }
    }

    public static CharSequence getContentDescription(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return IMPL.getContentDescription(menuItem);
        }
        return ((SupportMenuItem) menuItem).getContentDescription();
    }

    public static void setTooltipText(MenuItem menuItem, CharSequence charSequence) {
        if (!(menuItem instanceof SupportMenuItem)) {
            IMPL.setTooltipText(menuItem, charSequence);
        } else {
            ((SupportMenuItem) menuItem).setTooltipText(charSequence);
        }
    }

    public static CharSequence getTooltipText(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return IMPL.getTooltipText(menuItem);
        }
        return ((SupportMenuItem) menuItem).getTooltipText();
    }

    public static void setShortcut(MenuItem menuItem, char c2, char c3, int i2, int i3) {
        if (!(menuItem instanceof SupportMenuItem)) {
            IMPL.setShortcut(menuItem, c2, c3, i2, i3);
        } else {
            ((SupportMenuItem) menuItem).setShortcut(c2, c3, i2, i3);
        }
    }

    public static void setNumericShortcut(MenuItem menuItem, char c2, int i2) {
        if (!(menuItem instanceof SupportMenuItem)) {
            IMPL.setNumericShortcut(menuItem, c2, i2);
        } else {
            ((SupportMenuItem) menuItem).setNumericShortcut(c2, i2);
        }
    }

    public static int getNumericModifiers(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return IMPL.getNumericModifiers(menuItem);
        }
        return ((SupportMenuItem) menuItem).getNumericModifiers();
    }

    public static void setAlphabeticShortcut(MenuItem menuItem, char c2, int i2) {
        if (!(menuItem instanceof SupportMenuItem)) {
            IMPL.setAlphabeticShortcut(menuItem, c2, i2);
        } else {
            ((SupportMenuItem) menuItem).setAlphabeticShortcut(c2, i2);
        }
    }

    public static int getAlphabeticModifiers(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return IMPL.getAlphabeticModifiers(menuItem);
        }
        return ((SupportMenuItem) menuItem).getAlphabeticModifiers();
    }

    public static void setIconTintList(MenuItem menuItem, ColorStateList colorStateList) {
        if (!(menuItem instanceof SupportMenuItem)) {
            IMPL.setIconTintList(menuItem, colorStateList);
        } else {
            ((SupportMenuItem) menuItem).setIconTintList(colorStateList);
        }
    }

    public static ColorStateList getIconTintList(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return IMPL.getIconTintList(menuItem);
        }
        return ((SupportMenuItem) menuItem).getIconTintList();
    }

    public static void setIconTintMode(MenuItem menuItem, PorterDuff.Mode mode) {
        if (!(menuItem instanceof SupportMenuItem)) {
            IMPL.setIconTintMode(menuItem, mode);
        } else {
            ((SupportMenuItem) menuItem).setIconTintMode(mode);
        }
    }

    public static PorterDuff.Mode getIconTintMode(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return IMPL.getIconTintMode(menuItem);
        }
        return ((SupportMenuItem) menuItem).getIconTintMode();
    }

    private MenuItemCompat() {
    }
}
