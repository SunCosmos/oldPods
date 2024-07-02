package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class MenuAdapter extends BaseAdapter {
    MenuBuilder a;
    private int b = -1;

    /* renamed from: c, reason: collision with root package name */
    private boolean f112c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f113d;
    private final LayoutInflater e;
    private final int f;

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i2) {
        this.f113d = z;
        this.e = layoutInflater;
        this.a = menuBuilder;
        this.f = i2;
        a();
    }

    void a() {
        MenuItemImpl expandedItem = this.a.getExpandedItem();
        if (expandedItem != null) {
            ArrayList<MenuItemImpl> nonActionItems = this.a.getNonActionItems();
            int size = nonActionItems.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (nonActionItems.get(i2) == expandedItem) {
                    this.b = i2;
                    return;
                }
            }
        }
        this.b = -1;
    }

    public MenuBuilder getAdapterMenu() {
        return this.a;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<MenuItemImpl> nonActionItems = this.f113d ? this.a.getNonActionItems() : this.a.getVisibleItems();
        int i2 = this.b;
        int size = nonActionItems.size();
        return i2 < 0 ? size : size - 1;
    }

    public boolean getForceShowIcon() {
        return this.f112c;
    }

    @Override // android.widget.Adapter
    public MenuItemImpl getItem(int i2) {
        ArrayList<MenuItemImpl> nonActionItems = this.f113d ? this.a.getNonActionItems() : this.a.getVisibleItems();
        int i3 = this.b;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return nonActionItems.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.e.inflate(this.f, viewGroup, false);
        }
        int groupId = getItem(i2).getGroupId();
        int i3 = i2 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.a.isGroupDividerEnabled() && groupId != (i3 >= 0 ? getItem(i3).getGroupId() : groupId));
        MenuView.ItemView itemView = (MenuView.ItemView) view;
        if (this.f112c) {
            listMenuItemView.setForceShowIcon(true);
        }
        itemView.initialize(getItem(i2), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }

    public void setForceShowIcon(boolean z) {
        this.f112c = z;
    }
}
