package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public final class d extends c implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener {
    private static final int v = R.layout.abc_popup_menu_item_layout;
    private final Context b;

    /* renamed from: c */
    private final MenuBuilder f129c;

    /* renamed from: d */
    private final MenuAdapter f130d;
    private final boolean e;
    private final int f;
    private final int g;
    private final int h;

    /* renamed from: i */
    final MenuPopupWindow f131i;
    private PopupWindow.OnDismissListener l;
    private View m;
    View n;
    private MenuPresenter.Callback o;
    ViewTreeObserver p;
    private boolean q;
    private boolean r;
    private int s;
    private boolean u;
    final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    private final View.OnAttachStateChangeListener k = new b();
    private int t = 0;

    /* loaded from: classes.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.isShowing() || d.this.f131i.isModal()) {
                return;
            }
            View view = d.this.n;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
            } else {
                d.this.f131i.show();
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = d.this.p;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.p = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.p.removeGlobalOnLayoutListener(dVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public d(Context context, MenuBuilder menuBuilder, View view, int i2, int i3, boolean z) {
        this.b = context;
        this.f129c = menuBuilder;
        this.e = z;
        this.f130d = new MenuAdapter(menuBuilder, LayoutInflater.from(context), z, v);
        this.g = i2;
        this.h = i3;
        Resources resources = context.getResources();
        this.f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.m = view;
        this.f131i = new MenuPopupWindow(context, null, i2, i3);
        menuBuilder.addMenuPresenter(this, context);
    }

    private boolean o() {
        View view;
        if (isShowing()) {
            return true;
        }
        if (this.q || (view = this.m) == null) {
            return false;
        }
        this.n = view;
        this.f131i.setOnDismissListener(this);
        this.f131i.setOnItemClickListener(this);
        this.f131i.setModal(true);
        View view2 = this.n;
        boolean z = this.p == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.p = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.j);
        }
        view2.addOnAttachStateChangeListener(this.k);
        this.f131i.setAnchorView(view2);
        this.f131i.setDropDownGravity(this.t);
        if (!this.r) {
            this.s = c.d(this.f130d, null, this.b, this.f);
            this.r = true;
        }
        this.f131i.setContentWidth(this.s);
        this.f131i.setInputMethodMode(2);
        this.f131i.setEpicenterBounds(c());
        this.f131i.show();
        ListView listView = this.f131i.getListView();
        listView.setOnKeyListener(this);
        if (this.u && this.f129c.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.b).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listView, false);
            TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
            if (textView != null) {
                textView.setText(this.f129c.getHeaderTitle());
            }
            frameLayout.setEnabled(false);
            listView.addHeaderView(frameLayout, null, false);
        }
        this.f131i.setAdapter(this.f130d);
        this.f131i.show();
        return true;
    }

    @Override // androidx.appcompat.view.menu.c
    public void a(MenuBuilder menuBuilder) {
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        if (isShowing()) {
            this.f131i.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.c
    public void e(View view) {
        this.m = view;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.c
    public void g(boolean z) {
        this.f130d.setForceShowIcon(z);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.f131i.getListView();
    }

    @Override // androidx.appcompat.view.menu.c
    public void h(int i2) {
        this.t = i2;
    }

    @Override // androidx.appcompat.view.menu.c
    public void i(int i2) {
        this.f131i.setHorizontalOffset(i2);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return !this.q && this.f131i.isShowing();
    }

    @Override // androidx.appcompat.view.menu.c
    public void j(PopupWindow.OnDismissListener onDismissListener) {
        this.l = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.c
    public void k(boolean z) {
        this.u = z;
    }

    @Override // androidx.appcompat.view.menu.c
    public void l(int i2) {
        this.f131i.setVerticalOffset(i2);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder != this.f129c) {
            return;
        }
        dismiss();
        MenuPresenter.Callback callback = this.o;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.q = true;
        this.f129c.close();
        ViewTreeObserver viewTreeObserver = this.p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.p = this.n.getViewTreeObserver();
            }
            this.p.removeGlobalOnLayoutListener(this.j);
            this.p = null;
        }
        this.n.removeOnAttachStateChangeListener(this.k);
        PopupWindow.OnDismissListener onDismissListener = this.l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.b, subMenuBuilder, this.n, this.e, this.g, this.h);
            menuPopupHelper.setPresenterCallback(this.o);
            menuPopupHelper.setForceShowIcon(c.m(subMenuBuilder));
            menuPopupHelper.setOnDismissListener(this.l);
            this.l = null;
            this.f129c.close(false);
            int horizontalOffset = this.f131i.getHorizontalOffset();
            int verticalOffset = this.f131i.getVerticalOffset();
            if ((Gravity.getAbsoluteGravity(this.t, ViewCompat.getLayoutDirection(this.m)) & 7) == 5) {
                horizontalOffset += this.m.getWidth();
            }
            if (menuPopupHelper.tryShow(horizontalOffset, verticalOffset)) {
                MenuPresenter.Callback callback = this.o;
                if (callback == null) {
                    return true;
                }
                callback.onOpenSubMenu(subMenuBuilder);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.o = callback;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if (!o()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        this.r = false;
        MenuAdapter menuAdapter = this.f130d;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }
}
