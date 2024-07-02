package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.support.v4.internal.view.SupportMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(14)
/* loaded from: lib/Mus.dex */
public class MenuWrapperICS extends BaseMenuWrapper<SupportMenu> implements Menu {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MenuWrapperICS(Context context, SupportMenu supportMenu) {
        super(context, supportMenu);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i2));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i2, i3, i4, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, int i5) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i2, i3, i4, i5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i2, i3, i4, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i2, i3, i4, i5));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int addIntentOptions = ((SupportMenu) this.mWrappedObject).addIntentOptions(i2, i3, i4, componentName, intentArr, intent, i5, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i6 = 0; i6 < length; i6++) {
                menuItemArr[i6] = getMenuItemWrapper(menuItemArr2[i6]);
            }
        }
        return addIntentOptions;
    }

    @Override // android.view.Menu
    public void removeItem(int i2) {
        internalRemoveItem(i2);
        ((SupportMenu) this.mWrappedObject).removeItem(i2);
    }

    @Override // android.view.Menu
    public void removeGroup(int i2) {
        internalRemoveGroup(i2);
        ((SupportMenu) this.mWrappedObject).removeGroup(i2);
    }

    @Override // android.view.Menu
    public void clear() {
        internalClear();
        ((SupportMenu) this.mWrappedObject).clear();
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i2, boolean z, boolean z2) {
        ((SupportMenu) this.mWrappedObject).setGroupCheckable(i2, z, z2);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i2, boolean z) {
        ((SupportMenu) this.mWrappedObject).setGroupVisible(i2, z);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i2, boolean z) {
        ((SupportMenu) this.mWrappedObject).setGroupEnabled(i2, z);
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return ((SupportMenu) this.mWrappedObject).hasVisibleItems();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i2) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).findItem(i2));
    }

    @Override // android.view.Menu
    public int size() {
        return ((SupportMenu) this.mWrappedObject).size();
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i2) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).getItem(i2));
    }

    @Override // android.view.Menu
    public void close() {
        ((SupportMenu) this.mWrappedObject).close();
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        return ((SupportMenu) this.mWrappedObject).performShortcut(i2, keyEvent, i3);
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return ((SupportMenu) this.mWrappedObject).isShortcutKey(i2, keyEvent);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i2, int i3) {
        return ((SupportMenu) this.mWrappedObject).performIdentifierAction(i2, i3);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        ((SupportMenu) this.mWrappedObject).setQwertyMode(z);
    }
}
