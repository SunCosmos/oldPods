package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.internal.view.SupportSubMenu;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class a {
    final Context a;
    private SimpleArrayMap<SupportMenuItem, MenuItem> b;

    /* renamed from: c, reason: collision with root package name */
    private SimpleArrayMap<SupportSubMenu, SubMenu> f126c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context) {
        this.a = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final MenuItem a(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return menuItem;
        }
        SupportMenuItem supportMenuItem = (SupportMenuItem) menuItem;
        if (this.b == null) {
            this.b = new SimpleArrayMap<>();
        }
        MenuItem menuItem2 = this.b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemWrapperICS menuItemWrapperICS = new MenuItemWrapperICS(this.a, supportMenuItem);
        this.b.put(supportMenuItem, menuItemWrapperICS);
        return menuItemWrapperICS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SubMenu b(SubMenu subMenu) {
        if (!(subMenu instanceof SupportSubMenu)) {
            return subMenu;
        }
        SupportSubMenu supportSubMenu = (SupportSubMenu) subMenu;
        if (this.f126c == null) {
            this.f126c = new SimpleArrayMap<>();
        }
        SubMenu subMenu2 = this.f126c.get(supportSubMenu);
        if (subMenu2 != null) {
            return subMenu2;
        }
        e eVar = new e(this.a, supportSubMenu);
        this.f126c.put(supportSubMenu, eVar);
        return eVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c() {
        SimpleArrayMap<SupportMenuItem, MenuItem> simpleArrayMap = this.b;
        if (simpleArrayMap != null) {
            simpleArrayMap.clear();
        }
        SimpleArrayMap<SupportSubMenu, SubMenu> simpleArrayMap2 = this.f126c;
        if (simpleArrayMap2 != null) {
            simpleArrayMap2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(int i2) {
        if (this.b == null) {
            return;
        }
        int i3 = 0;
        while (i3 < this.b.size()) {
            if (this.b.keyAt(i3).getGroupId() == i2) {
                this.b.removeAt(i3);
                i3--;
            }
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(int i2) {
        if (this.b == null) {
            return;
        }
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            if (this.b.keyAt(i3).getItemId() == i2) {
                this.b.removeAt(i3);
                return;
            }
        }
    }
}
