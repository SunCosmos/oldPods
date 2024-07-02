package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class MenuPopupHelper {
    private final Context a;
    private final MenuBuilder b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f122c;

    /* renamed from: d, reason: collision with root package name */
    private final int f123d;
    private final int e;
    private View f;
    private int g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private MenuPresenter.Callback f124i;
    private c j;
    private PopupWindow.OnDismissListener k;
    private final PopupWindow.OnDismissListener l;

    /* loaded from: classes.dex */
    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            MenuPopupHelper.this.b();
        }
    }

    public MenuPopupHelper(@NonNull Context context, @NonNull MenuBuilder menuBuilder) {
        this(context, menuBuilder, null, false, R.attr.popupMenuStyle, 0);
    }

    public MenuPopupHelper(@NonNull Context context, @NonNull MenuBuilder menuBuilder, @NonNull View view) {
        this(context, menuBuilder, view, false, R.attr.popupMenuStyle, 0);
    }

    public MenuPopupHelper(@NonNull Context context, @NonNull MenuBuilder menuBuilder, @NonNull View view, boolean z, @AttrRes int i2) {
        this(context, menuBuilder, view, z, i2, 0);
    }

    public MenuPopupHelper(@NonNull Context context, @NonNull MenuBuilder menuBuilder, @NonNull View view, boolean z, @AttrRes int i2, @StyleRes int i3) {
        this.g = 8388611;
        this.l = new a();
        this.a = context;
        this.b = menuBuilder;
        this.f = view;
        this.f122c = z;
        this.f123d = i2;
        this.e = i3;
    }

    @NonNull
    private c a() {
        Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        c cascadingMenuPopup = Math.min(point.x, point.y) >= this.a.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width) ? new CascadingMenuPopup(this.a, this.f, this.f123d, this.e, this.f122c) : new d(this.a, this.b, this.f, this.f123d, this.e, this.f122c);
        cascadingMenuPopup.a(this.b);
        cascadingMenuPopup.j(this.l);
        cascadingMenuPopup.e(this.f);
        cascadingMenuPopup.setCallback(this.f124i);
        cascadingMenuPopup.g(this.h);
        cascadingMenuPopup.h(this.g);
        return cascadingMenuPopup;
    }

    private void c(int i2, int i3, boolean z, boolean z2) {
        c popup = getPopup();
        popup.k(z2);
        if (z) {
            if ((GravityCompat.getAbsoluteGravity(this.g, ViewCompat.getLayoutDirection(this.f)) & 7) == 5) {
                i2 -= this.f.getWidth();
            }
            popup.i(i2);
            popup.l(i3);
            int i4 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            popup.f(new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4));
        }
        popup.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void dismiss() {
        if (isShowing()) {
            this.j.dismiss();
        }
    }

    public int getGravity() {
        return this.g;
    }

    public ListView getListView() {
        return getPopup().getListView();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public c getPopup() {
        if (this.j == null) {
            this.j = a();
        }
        return this.j;
    }

    public boolean isShowing() {
        c cVar = this.j;
        return cVar != null && cVar.isShowing();
    }

    public void setAnchorView(@NonNull View view) {
        this.f = view;
    }

    public void setForceShowIcon(boolean z) {
        this.h = z;
        c cVar = this.j;
        if (cVar != null) {
            cVar.g(z);
        }
    }

    public void setGravity(int i2) {
        this.g = i2;
    }

    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void setPresenterCallback(@Nullable MenuPresenter.Callback callback) {
        this.f124i = callback;
        c cVar = this.j;
        if (cVar != null) {
            cVar.setCallback(callback);
        }
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public void show(int i2, int i3) {
        if (!tryShow(i2, i3)) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean tryShow() {
        if (isShowing()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        c(0, 0, false, false);
        return true;
    }

    public boolean tryShow(int i2, int i3) {
        if (isShowing()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        c(i2, i3, true, true);
        return true;
    }
}
