package com.google.android.material.navigation;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class NavigationBarMenu extends MenuBuilder {

    @NonNull
    private final Class<?> B;
    private final int C;

    public NavigationBarMenu(@NonNull Context context, @NonNull Class<?> cls, int i2) {
        super(context);
        this.B = cls;
        this.C = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.view.menu.MenuBuilder
    @NonNull
    public MenuItem a(int i2, int i3, int i4, @NonNull CharSequence charSequence) {
        if (size() + 1 <= this.C) {
            stopDispatchingItemsChanged();
            MenuItem a = super.a(i2, i3, i4, charSequence);
            if (a instanceof MenuItemImpl) {
                ((MenuItemImpl) a).setExclusiveCheckable(true);
            }
            startDispatchingItemsChanged();
            return a;
        }
        String simpleName = this.B.getSimpleName();
        throw new IllegalArgumentException("Maximum number of items supported by " + simpleName + " is " + this.C + ". Limit can be checked with " + simpleName + "#getMaxItemCount()");
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    @NonNull
    public SubMenu addSubMenu(int i2, int i3, int i4, @NonNull CharSequence charSequence) {
        throw new UnsupportedOperationException(this.B.getSimpleName() + " does not support submenus");
    }

    public int getMaxItemCount() {
        return this.C;
    }
}
