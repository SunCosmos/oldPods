package androidx.appcompat.widget;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.ShowableListMenu;

/* loaded from: classes.dex */
public class PopupMenu {
    private final Context a;
    private final MenuBuilder b;

    /* renamed from: c, reason: collision with root package name */
    private final View f179c;

    /* renamed from: d, reason: collision with root package name */
    final MenuPopupHelper f180d;
    OnMenuItemClickListener e;
    OnDismissListener f;
    private View.OnTouchListener g;

    /* loaded from: classes.dex */
    public interface OnDismissListener {
        void onDismiss(PopupMenu popupMenu);
    }

    /* loaded from: classes.dex */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    /* loaded from: classes.dex */
    class a implements MenuBuilder.Callback {
        a() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            OnMenuItemClickListener onMenuItemClickListener = PopupMenu.this.e;
            if (onMenuItemClickListener != null) {
                return onMenuItemClickListener.onMenuItemClick(menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(@NonNull MenuBuilder menuBuilder) {
        }
    }

    /* loaded from: classes.dex */
    class b implements PopupWindow.OnDismissListener {
        b() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            PopupMenu popupMenu = PopupMenu.this;
            OnDismissListener onDismissListener = popupMenu.f;
            if (onDismissListener != null) {
                onDismissListener.onDismiss(popupMenu);
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends ForwardingListener {
        c(View view) {
            super(view);
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        protected boolean b() {
            PopupMenu.this.show();
            return true;
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        protected boolean c() {
            PopupMenu.this.dismiss();
            return true;
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public ShowableListMenu getPopup() {
            return PopupMenu.this.f180d.getPopup();
        }
    }

    public PopupMenu(@NonNull Context context, @NonNull View view) {
        this(context, view, 0);
    }

    public PopupMenu(@NonNull Context context, @NonNull View view, int i2) {
        this(context, view, i2, R.attr.popupMenuStyle, 0);
    }

    public PopupMenu(@NonNull Context context, @NonNull View view, int i2, @AttrRes int i3, @StyleRes int i4) {
        this.a = context;
        this.f179c = view;
        MenuBuilder menuBuilder = new MenuBuilder(context);
        this.b = menuBuilder;
        menuBuilder.setCallback(new a());
        MenuPopupHelper menuPopupHelper = new MenuPopupHelper(context, menuBuilder, view, false, i3, i4);
        this.f180d = menuPopupHelper;
        menuPopupHelper.setGravity(i2);
        menuPopupHelper.setOnDismissListener(new b());
    }

    public void dismiss() {
        this.f180d.dismiss();
    }

    @NonNull
    public View.OnTouchListener getDragToOpenListener() {
        if (this.g == null) {
            this.g = new c(this.f179c);
        }
        return this.g;
    }

    public int getGravity() {
        return this.f180d.getGravity();
    }

    @NonNull
    public Menu getMenu() {
        return this.b;
    }

    @NonNull
    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.a);
    }

    public void inflate(@MenuRes int i2) {
        getMenuInflater().inflate(i2, this.b);
    }

    public void setGravity(int i2) {
        this.f180d.setGravity(i2);
    }

    public void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        this.f = onDismissListener;
    }

    public void setOnMenuItemClickListener(@Nullable OnMenuItemClickListener onMenuItemClickListener) {
        this.e = onMenuItemClickListener;
    }

    public void show() {
        this.f180d.show();
    }
}
