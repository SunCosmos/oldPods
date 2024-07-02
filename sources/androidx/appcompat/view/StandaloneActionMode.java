package androidx.appcompat.view;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class StandaloneActionMode extends ActionMode implements MenuBuilder.Callback {

    /* renamed from: c, reason: collision with root package name */
    private Context f78c;

    /* renamed from: d, reason: collision with root package name */
    private ActionBarContextView f79d;
    private ActionMode.Callback e;
    private WeakReference<View> f;
    private boolean g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private MenuBuilder f80i;

    public StandaloneActionMode(Context context, ActionBarContextView actionBarContextView, ActionMode.Callback callback, boolean z) {
        this.f78c = context;
        this.f79d = actionBarContextView;
        this.e = callback;
        MenuBuilder defaultShowAsAction = new MenuBuilder(actionBarContextView.getContext()).setDefaultShowAsAction(1);
        this.f80i = defaultShowAsAction;
        defaultShowAsAction.setCallback(this);
        this.h = z;
    }

    @Override // androidx.appcompat.view.ActionMode
    public void finish() {
        if (this.g) {
            return;
        }
        this.g = true;
        this.f79d.sendAccessibilityEvent(32);
        this.e.onDestroyActionMode(this);
    }

    @Override // androidx.appcompat.view.ActionMode
    public View getCustomView() {
        WeakReference<View> weakReference = this.f;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // androidx.appcompat.view.ActionMode
    public Menu getMenu() {
        return this.f80i;
    }

    @Override // androidx.appcompat.view.ActionMode
    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.f79d.getContext());
    }

    @Override // androidx.appcompat.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f79d.getSubtitle();
    }

    @Override // androidx.appcompat.view.ActionMode
    public CharSequence getTitle() {
        return this.f79d.getTitle();
    }

    @Override // androidx.appcompat.view.ActionMode
    public void invalidate() {
        this.e.onPrepareActionMode(this, this.f80i);
    }

    @Override // androidx.appcompat.view.ActionMode
    public boolean isTitleOptional() {
        return this.f79d.isTitleOptional();
    }

    @Override // androidx.appcompat.view.ActionMode
    public boolean isUiFocusable() {
        return this.h;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
    }

    public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public boolean onMenuItemSelected(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
        return this.e.onActionItemClicked(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public void onMenuModeChange(@NonNull MenuBuilder menuBuilder) {
        invalidate();
        this.f79d.showOverflowMenu();
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return true;
        }
        new MenuPopupHelper(this.f79d.getContext(), subMenuBuilder).show();
        return true;
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setCustomView(View view) {
        this.f79d.setCustomView(view);
        this.f = view != null ? new WeakReference<>(view) : null;
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setSubtitle(int i2) {
        setSubtitle(this.f78c.getString(i2));
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f79d.setSubtitle(charSequence);
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setTitle(int i2) {
        setTitle(this.f78c.getString(i2));
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f79d.setTitle(charSequence);
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        super.setTitleOptionalHint(z);
        this.f79d.setTitleOptional(z);
    }
}
