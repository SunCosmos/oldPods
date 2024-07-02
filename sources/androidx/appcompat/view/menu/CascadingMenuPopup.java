package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class CascadingMenuPopup extends androidx.appcompat.view.menu.c implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int B = R.layout.abc_cascading_menu_item_layout;
    boolean A;
    private final Context b;

    /* renamed from: c */
    private final int f99c;

    /* renamed from: d */
    private final int f100d;
    private final int e;
    private final boolean f;
    final Handler g;
    private View o;
    View p;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private boolean w;
    private MenuPresenter.Callback x;
    ViewTreeObserver y;
    private PopupWindow.OnDismissListener z;
    private final List<MenuBuilder> h = new ArrayList();

    /* renamed from: i */
    final List<d> f101i = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    private final View.OnAttachStateChangeListener k = new b();
    private final MenuItemHoverListener l = new c();
    private int m = 0;
    private int n = 0;
    private boolean v = false;
    private int q = s();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface HorizPosition {
    }

    /* loaded from: classes.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!CascadingMenuPopup.this.isShowing() || CascadingMenuPopup.this.f101i.size() <= 0 || CascadingMenuPopup.this.f101i.get(0).a.isModal()) {
                return;
            }
            View view = CascadingMenuPopup.this.p;
            if (view == null || !view.isShown()) {
                CascadingMenuPopup.this.dismiss();
                return;
            }
            Iterator<d> it = CascadingMenuPopup.this.f101i.iterator();
            while (it.hasNext()) {
                it.next().a.show();
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
            ViewTreeObserver viewTreeObserver = CascadingMenuPopup.this.y;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    CascadingMenuPopup.this.y = view.getViewTreeObserver();
                }
                CascadingMenuPopup cascadingMenuPopup = CascadingMenuPopup.this;
                cascadingMenuPopup.y.removeGlobalOnLayoutListener(cascadingMenuPopup.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public class c implements MenuItemHoverListener {

        /* loaded from: classes.dex */
        class a implements Runnable {
            final /* synthetic */ d a;
            final /* synthetic */ MenuItem b;

            /* renamed from: c */
            final /* synthetic */ MenuBuilder f102c;

            a(d dVar, MenuItem menuItem, MenuBuilder menuBuilder) {
                this.a = dVar;
                this.b = menuItem;
                this.f102c = menuBuilder;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = this.a;
                if (dVar != null) {
                    CascadingMenuPopup.this.A = true;
                    dVar.b.close(false);
                    CascadingMenuPopup.this.A = false;
                }
                if (this.b.isEnabled() && this.b.hasSubMenu()) {
                    this.f102c.performItemAction(this.b, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public void onItemHoverEnter(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            CascadingMenuPopup.this.g.removeCallbacksAndMessages(null);
            int size = CascadingMenuPopup.this.f101i.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (menuBuilder == CascadingMenuPopup.this.f101i.get(i2).b) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                return;
            }
            int i3 = i2 + 1;
            CascadingMenuPopup.this.g.postAtTime(new a(i3 < CascadingMenuPopup.this.f101i.size() ? CascadingMenuPopup.this.f101i.get(i3) : null, menuItem, menuBuilder), menuBuilder, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public void onItemHoverExit(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            CascadingMenuPopup.this.g.removeCallbacksAndMessages(menuBuilder);
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        public final MenuPopupWindow a;
        public final MenuBuilder b;

        /* renamed from: c */
        public final int f104c;

        public d(@NonNull MenuPopupWindow menuPopupWindow, @NonNull MenuBuilder menuBuilder, int i2) {
            this.a = menuPopupWindow;
            this.b = menuBuilder;
            this.f104c = i2;
        }

        public ListView a() {
            return this.a.getListView();
        }
    }

    public CascadingMenuPopup(@NonNull Context context, @NonNull View view, @AttrRes int i2, @StyleRes int i3, boolean z) {
        this.b = context;
        this.o = view;
        this.f100d = i2;
        this.e = i3;
        this.f = z;
        Resources resources = context.getResources();
        this.f99c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.g = new Handler();
    }

    private MenuPopupWindow o() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.b, null, this.f100d, this.e);
        menuPopupWindow.setHoverListener(this.l);
        menuPopupWindow.setOnItemClickListener(this);
        menuPopupWindow.setOnDismissListener(this);
        menuPopupWindow.setAnchorView(this.o);
        menuPopupWindow.setDropDownGravity(this.n);
        menuPopupWindow.setModal(true);
        menuPopupWindow.setInputMethodMode(2);
        return menuPopupWindow;
    }

    private int p(@NonNull MenuBuilder menuBuilder) {
        int size = this.f101i.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (menuBuilder == this.f101i.get(i2).b) {
                return i2;
            }
        }
        return -1;
    }

    private MenuItem q(@NonNull MenuBuilder menuBuilder, @NonNull MenuBuilder menuBuilder2) {
        int size = menuBuilder.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = menuBuilder.getItem(i2);
            if (item.hasSubMenu() && menuBuilder2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Nullable
    private View r(@NonNull d dVar, @NonNull MenuBuilder menuBuilder) {
        MenuAdapter menuAdapter;
        int i2;
        int firstVisiblePosition;
        MenuItem q = q(dVar.b, menuBuilder);
        if (q == null) {
            return null;
        }
        ListView a2 = dVar.a();
        ListAdapter adapter = a2.getAdapter();
        int i3 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i2 = headerViewListAdapter.getHeadersCount();
            menuAdapter = (MenuAdapter) headerViewListAdapter.getWrappedAdapter();
        } else {
            menuAdapter = (MenuAdapter) adapter;
            i2 = 0;
        }
        int count = menuAdapter.getCount();
        while (true) {
            if (i3 >= count) {
                i3 = -1;
                break;
            }
            if (q == menuAdapter.getItem(i3)) {
                break;
            }
            i3++;
        }
        if (i3 != -1 && (firstVisiblePosition = (i3 + i2) - a2.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a2.getChildCount()) {
            return a2.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int s() {
        return ViewCompat.getLayoutDirection(this.o) == 1 ? 0 : 1;
    }

    private int t(int i2) {
        List<d> list = this.f101i;
        ListView a2 = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        a2.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.p.getWindowVisibleDisplayFrame(rect);
        return this.q == 1 ? (iArr[0] + a2.getWidth()) + i2 > rect.right ? 0 : 1 : iArr[0] - i2 < 0 ? 1 : 0;
    }

    private void u(@NonNull MenuBuilder menuBuilder) {
        d dVar;
        View view;
        int i2;
        int i3;
        int i4;
        LayoutInflater from = LayoutInflater.from(this.b);
        MenuAdapter menuAdapter = new MenuAdapter(menuBuilder, from, this.f, B);
        if (!isShowing() && this.v) {
            menuAdapter.setForceShowIcon(true);
        } else if (isShowing()) {
            menuAdapter.setForceShowIcon(androidx.appcompat.view.menu.c.m(menuBuilder));
        }
        int d2 = androidx.appcompat.view.menu.c.d(menuAdapter, null, this.b, this.f99c);
        MenuPopupWindow o = o();
        o.setAdapter(menuAdapter);
        o.setContentWidth(d2);
        o.setDropDownGravity(this.n);
        if (this.f101i.size() > 0) {
            List<d> list = this.f101i;
            dVar = list.get(list.size() - 1);
            view = r(dVar, menuBuilder);
        } else {
            dVar = null;
            view = null;
        }
        if (view != null) {
            o.setTouchModal(false);
            o.setEnterTransition(null);
            int t = t(d2);
            boolean z = t == 1;
            this.q = t;
            if (Build.VERSION.SDK_INT >= 26) {
                o.setAnchorView(view);
                i3 = 0;
                i2 = 0;
            } else {
                int[] iArr = new int[2];
                this.o.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.n & 7) == 5) {
                    iArr[0] = iArr[0] + this.o.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i2 = iArr2[0] - iArr[0];
                i3 = iArr2[1] - iArr[1];
            }
            if ((this.n & 5) == 5) {
                if (!z) {
                    d2 = view.getWidth();
                    i4 = i2 - d2;
                }
                i4 = i2 + d2;
            } else {
                if (z) {
                    d2 = view.getWidth();
                    i4 = i2 + d2;
                }
                i4 = i2 - d2;
            }
            o.setHorizontalOffset(i4);
            o.setOverlapAnchor(true);
            o.setVerticalOffset(i3);
        } else {
            if (this.r) {
                o.setHorizontalOffset(this.t);
            }
            if (this.s) {
                o.setVerticalOffset(this.u);
            }
            o.setEpicenterBounds(c());
        }
        this.f101i.add(new d(o, menuBuilder, this.q));
        o.show();
        ListView listView = o.getListView();
        listView.setOnKeyListener(this);
        if (dVar == null && this.w && menuBuilder.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listView, false);
            TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(menuBuilder.getHeaderTitle());
            listView.addHeaderView(frameLayout, null, false);
            o.show();
        }
    }

    @Override // androidx.appcompat.view.menu.c
    public void a(MenuBuilder menuBuilder) {
        menuBuilder.addMenuPresenter(this, this.b);
        if (isShowing()) {
            u(menuBuilder);
        } else {
            this.h.add(menuBuilder);
        }
    }

    @Override // androidx.appcompat.view.menu.c
    protected boolean b() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        int size = this.f101i.size();
        if (size > 0) {
            d[] dVarArr = (d[]) this.f101i.toArray(new d[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                d dVar = dVarArr[i2];
                if (dVar.a.isShowing()) {
                    dVar.a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.c
    public void e(@NonNull View view) {
        if (this.o != view) {
            this.o = view;
            this.n = GravityCompat.getAbsoluteGravity(this.m, ViewCompat.getLayoutDirection(view));
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.c
    public void g(boolean z) {
        this.v = z;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        if (this.f101i.isEmpty()) {
            return null;
        }
        return this.f101i.get(r0.size() - 1).a();
    }

    @Override // androidx.appcompat.view.menu.c
    public void h(int i2) {
        if (this.m != i2) {
            this.m = i2;
            this.n = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this.o));
        }
    }

    @Override // androidx.appcompat.view.menu.c
    public void i(int i2) {
        this.r = true;
        this.t = i2;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.f101i.size() > 0 && this.f101i.get(0).a.isShowing();
    }

    @Override // androidx.appcompat.view.menu.c
    public void j(PopupWindow.OnDismissListener onDismissListener) {
        this.z = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.c
    public void k(boolean z) {
        this.w = z;
    }

    @Override // androidx.appcompat.view.menu.c
    public void l(int i2) {
        this.s = true;
        this.u = i2;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        int p = p(menuBuilder);
        if (p < 0) {
            return;
        }
        int i2 = p + 1;
        if (i2 < this.f101i.size()) {
            this.f101i.get(i2).b.close(false);
        }
        d remove = this.f101i.remove(p);
        remove.b.removeMenuPresenter(this);
        if (this.A) {
            remove.a.setExitTransition(null);
            remove.a.setAnimationStyle(0);
        }
        remove.a.dismiss();
        int size = this.f101i.size();
        this.q = size > 0 ? this.f101i.get(size - 1).f104c : s();
        if (size != 0) {
            if (z) {
                this.f101i.get(0).b.close(false);
                return;
            }
            return;
        }
        dismiss();
        MenuPresenter.Callback callback = this.x;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, true);
        }
        ViewTreeObserver viewTreeObserver = this.y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.y.removeGlobalOnLayoutListener(this.j);
            }
            this.y = null;
        }
        this.p.removeOnAttachStateChangeListener(this.k);
        this.z.onDismiss();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        d dVar;
        int size = this.f101i.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                dVar = null;
                break;
            }
            dVar = this.f101i.get(i2);
            if (!dVar.a.isShowing()) {
                break;
            } else {
                i2++;
            }
        }
        if (dVar != null) {
            dVar.b.close(false);
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
        for (d dVar : this.f101i) {
            if (subMenuBuilder == dVar.b) {
                dVar.a().requestFocus();
                return true;
            }
        }
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        a(subMenuBuilder);
        MenuPresenter.Callback callback = this.x;
        if (callback != null) {
            callback.onOpenSubMenu(subMenuBuilder);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.x = callback;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if (isShowing()) {
            return;
        }
        Iterator<MenuBuilder> it = this.h.iterator();
        while (it.hasNext()) {
            u(it.next());
        }
        this.h.clear();
        View view = this.o;
        this.p = view;
        if (view != null) {
            boolean z = this.y == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.y = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.j);
            }
            this.p.addOnAttachStateChangeListener(this.k);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        Iterator<d> it = this.f101i.iterator();
        while (it.hasNext()) {
            androidx.appcompat.view.menu.c.n(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }
}
