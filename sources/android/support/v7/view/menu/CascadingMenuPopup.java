package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.MenuPopupWindow;
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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: lib/Mus.dex */
public final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
    static final int HORIZ_POSITION_LEFT = 0;
    static final int HORIZ_POSITION_RIGHT = 1;
    static final int SUBMENU_TIMEOUT_MS = 200;
    private View mAnchorView;
    private final Context mContext;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private final int mMenuMaxWidth;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    View mShownAnchorView;
    final Handler mSubMenuHoverHandler;
    private ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;
    private final List<MenuBuilder> mPendingMenus = new ArrayList();
    final List<CascadingMenuInfo> mShowingMenus = new ArrayList();
    private final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: android.support.v7.view.menu.CascadingMenuPopup.1
        AnonymousClass1() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (CascadingMenuPopup.this.isShowing() && CascadingMenuPopup.this.mShowingMenus.size() > 0 && !CascadingMenuPopup.this.mShowingMenus.get(0).window.isModal()) {
                View view = CascadingMenuPopup.this.mShownAnchorView;
                if (view == null || !view.isShown()) {
                    CascadingMenuPopup.this.dismiss();
                    return;
                }
                Iterator<CascadingMenuInfo> it = CascadingMenuPopup.this.mShowingMenus.iterator();
                while (it.hasNext()) {
                    it.next().window.show();
                }
            }
        }
    };
    private final View.OnAttachStateChangeListener mAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: android.support.v7.view.menu.CascadingMenuPopup.2
        AnonymousClass2() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (CascadingMenuPopup.this.mTreeObserver != null) {
                if (!CascadingMenuPopup.this.mTreeObserver.isAlive()) {
                    CascadingMenuPopup.this.mTreeObserver = view.getViewTreeObserver();
                }
                CascadingMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(CascadingMenuPopup.this.mGlobalLayoutListener);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    };
    private final MenuItemHoverListener mMenuItemHoverListener = new MenuItemHoverListener() { // from class: android.support.v7.view.menu.CascadingMenuPopup.3
        AnonymousClass3() {
        }

        @Override // android.support.v7.widget.MenuItemHoverListener
        public void onItemHoverExit(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(menuBuilder);
        }

        @Override // android.support.v7.widget.MenuItemHoverListener
        public void onItemHoverEnter(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            CascadingMenuInfo cascadingMenuInfo;
            CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(null);
            int i2 = -1;
            int i3 = 0;
            int size = CascadingMenuPopup.this.mShowingMenus.size();
            while (true) {
                if (i3 >= size) {
                    break;
                }
                if (menuBuilder != CascadingMenuPopup.this.mShowingMenus.get(i3).menu) {
                    i3++;
                } else {
                    i2 = i3;
                    break;
                }
            }
            if (i2 != -1) {
                int i4 = i2 + 1;
                if (i4 < CascadingMenuPopup.this.mShowingMenus.size()) {
                    cascadingMenuInfo = CascadingMenuPopup.this.mShowingMenus.get(i4);
                } else {
                    cascadingMenuInfo = null;
                }
                CascadingMenuPopup.this.mSubMenuHoverHandler.postAtTime(new Runnable() { // from class: android.support.v7.view.menu.CascadingMenuPopup.3.1
                    final /* synthetic */ MenuItem val$item;
                    final /* synthetic */ MenuBuilder val$menu;
                    final /* synthetic */ CascadingMenuInfo val$nextInfo;

                    AnonymousClass1(CascadingMenuInfo cascadingMenuInfo2, MenuItem menuItem2, MenuBuilder menuBuilder2) {
                        cascadingMenuInfo2 = cascadingMenuInfo2;
                        menuItem = menuItem2;
                        menuBuilder = menuBuilder2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (cascadingMenuInfo2 != null) {
                            CascadingMenuPopup.this.mShouldCloseImmediately = true;
                            cascadingMenuInfo2.menu.close(false);
                            CascadingMenuPopup.this.mShouldCloseImmediately = false;
                        }
                        if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                            menuBuilder.performItemAction(menuItem, 4);
                        }
                    }
                }, menuBuilder2, SystemClock.uptimeMillis() + 200);
            }
        }

        /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$3$1 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ MenuItem val$item;
            final /* synthetic */ MenuBuilder val$menu;
            final /* synthetic */ CascadingMenuInfo val$nextInfo;

            AnonymousClass1(CascadingMenuInfo cascadingMenuInfo2, MenuItem menuItem2, MenuBuilder menuBuilder2) {
                cascadingMenuInfo2 = cascadingMenuInfo2;
                menuItem = menuItem2;
                menuBuilder = menuBuilder2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (cascadingMenuInfo2 != null) {
                    CascadingMenuPopup.this.mShouldCloseImmediately = true;
                    cascadingMenuInfo2.menu.close(false);
                    CascadingMenuPopup.this.mShouldCloseImmediately = false;
                }
                if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                    menuBuilder.performItemAction(menuItem, 4);
                }
            }
        }
    };
    private int mRawDropDownGravity = 0;
    private int mDropDownGravity = 0;
    private boolean mForceShowIcon = false;
    private int mLastPosition = getInitialMenuPosition();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    public @interface HorizPosition {
    }

    /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$1 */
    /* loaded from: lib/Mus.dex */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        AnonymousClass1() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (CascadingMenuPopup.this.isShowing() && CascadingMenuPopup.this.mShowingMenus.size() > 0 && !CascadingMenuPopup.this.mShowingMenus.get(0).window.isModal()) {
                View view = CascadingMenuPopup.this.mShownAnchorView;
                if (view == null || !view.isShown()) {
                    CascadingMenuPopup.this.dismiss();
                    return;
                }
                Iterator<CascadingMenuInfo> it = CascadingMenuPopup.this.mShowingMenus.iterator();
                while (it.hasNext()) {
                    it.next().window.show();
                }
            }
        }
    }

    /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$2 */
    /* loaded from: lib/Mus.dex */
    public class AnonymousClass2 implements View.OnAttachStateChangeListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (CascadingMenuPopup.this.mTreeObserver != null) {
                if (!CascadingMenuPopup.this.mTreeObserver.isAlive()) {
                    CascadingMenuPopup.this.mTreeObserver = view.getViewTreeObserver();
                }
                CascadingMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(CascadingMenuPopup.this.mGlobalLayoutListener);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$3 */
    /* loaded from: lib/Mus.dex */
    public class AnonymousClass3 implements MenuItemHoverListener {
        AnonymousClass3() {
        }

        @Override // android.support.v7.widget.MenuItemHoverListener
        public void onItemHoverExit(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(menuBuilder);
        }

        @Override // android.support.v7.widget.MenuItemHoverListener
        public void onItemHoverEnter(@NonNull MenuBuilder menuBuilder2, @NonNull MenuItem menuItem2) {
            CascadingMenuInfo cascadingMenuInfo2;
            CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(null);
            int i2 = -1;
            int i3 = 0;
            int size = CascadingMenuPopup.this.mShowingMenus.size();
            while (true) {
                if (i3 >= size) {
                    break;
                }
                if (menuBuilder2 != CascadingMenuPopup.this.mShowingMenus.get(i3).menu) {
                    i3++;
                } else {
                    i2 = i3;
                    break;
                }
            }
            if (i2 != -1) {
                int i4 = i2 + 1;
                if (i4 < CascadingMenuPopup.this.mShowingMenus.size()) {
                    cascadingMenuInfo2 = CascadingMenuPopup.this.mShowingMenus.get(i4);
                } else {
                    cascadingMenuInfo2 = null;
                }
                CascadingMenuPopup.this.mSubMenuHoverHandler.postAtTime(new Runnable() { // from class: android.support.v7.view.menu.CascadingMenuPopup.3.1
                    final /* synthetic */ MenuItem val$item;
                    final /* synthetic */ MenuBuilder val$menu;
                    final /* synthetic */ CascadingMenuInfo val$nextInfo;

                    AnonymousClass1(CascadingMenuInfo cascadingMenuInfo22, MenuItem menuItem22, MenuBuilder menuBuilder22) {
                        cascadingMenuInfo2 = cascadingMenuInfo22;
                        menuItem = menuItem22;
                        menuBuilder = menuBuilder22;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (cascadingMenuInfo2 != null) {
                            CascadingMenuPopup.this.mShouldCloseImmediately = true;
                            cascadingMenuInfo2.menu.close(false);
                            CascadingMenuPopup.this.mShouldCloseImmediately = false;
                        }
                        if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                            menuBuilder.performItemAction(menuItem, 4);
                        }
                    }
                }, menuBuilder22, SystemClock.uptimeMillis() + 200);
            }
        }

        /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$3$1 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ MenuItem val$item;
            final /* synthetic */ MenuBuilder val$menu;
            final /* synthetic */ CascadingMenuInfo val$nextInfo;

            AnonymousClass1(CascadingMenuInfo cascadingMenuInfo22, MenuItem menuItem22, MenuBuilder menuBuilder22) {
                cascadingMenuInfo2 = cascadingMenuInfo22;
                menuItem = menuItem22;
                menuBuilder = menuBuilder22;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (cascadingMenuInfo2 != null) {
                    CascadingMenuPopup.this.mShouldCloseImmediately = true;
                    cascadingMenuInfo2.menu.close(false);
                    CascadingMenuPopup.this.mShouldCloseImmediately = false;
                }
                if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                    menuBuilder.performItemAction(menuItem, 4);
                }
            }
        }
    }

    public CascadingMenuPopup(@NonNull Context context, @NonNull View view, @AttrRes int i2, @StyleRes int i3, boolean z) {
        this.mContext = context;
        this.mAnchorView = view;
        this.mPopupStyleAttr = i2;
        this.mPopupStyleRes = i3;
        this.mOverflowOnly = z;
        Resources resources = context.getResources();
        this.mMenuMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.mSubMenuHoverHandler = new Handler();
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    private MenuPopupWindow createPopupWindow() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
        menuPopupWindow.setHoverListener(this.mMenuItemHoverListener);
        menuPopupWindow.setOnItemClickListener(this);
        menuPopupWindow.setOnDismissListener(this);
        menuPopupWindow.setAnchorView(this.mAnchorView);
        menuPopupWindow.setDropDownGravity(this.mDropDownGravity);
        menuPopupWindow.setModal(true);
        menuPopupWindow.setInputMethodMode(2);
        return menuPopupWindow;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public void show() {
        if (!isShowing()) {
            Iterator<MenuBuilder> it = this.mPendingMenus.iterator();
            while (it.hasNext()) {
                showMenu(it.next());
            }
            this.mPendingMenus.clear();
            this.mShownAnchorView = this.mAnchorView;
            if (this.mShownAnchorView != null) {
                boolean z = this.mTreeObserver == null;
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
                if (z) {
                    this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
                }
                this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
            }
        }
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public void dismiss() {
        int size = this.mShowingMenus.size();
        if (size > 0) {
            CascadingMenuInfo[] cascadingMenuInfoArr = (CascadingMenuInfo[]) this.mShowingMenus.toArray(new CascadingMenuInfo[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                CascadingMenuInfo cascadingMenuInfo = cascadingMenuInfoArr[i2];
                if (cascadingMenuInfo.window.isShowing()) {
                    cascadingMenuInfo.window.dismiss();
                }
            }
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

    private int getInitialMenuPosition() {
        return ViewCompat.getLayoutDirection(this.mAnchorView) == 1 ? 0 : 1;
    }

    private int getNextMenuPosition(int i2) {
        ListView listView = this.mShowingMenus.get(this.mShowingMenus.size() - 1).getListView();
        int[] iArr = new int[2];
        listView.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.mShownAnchorView.getWindowVisibleDisplayFrame(rect);
        if (this.mLastPosition == 1) {
            if (iArr[0] + listView.getWidth() + i2 > rect.right) {
                return 0;
            }
            return 1;
        }
        if (iArr[0] - i2 < 0) {
            return 1;
        }
        return 0;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public void addMenu(MenuBuilder menuBuilder) {
        menuBuilder.addMenuPresenter(this, this.mContext);
        if (isShowing()) {
            showMenu(menuBuilder);
        } else {
            this.mPendingMenus.add(menuBuilder);
        }
    }

    private void showMenu(@NonNull MenuBuilder menuBuilder) {
        CascadingMenuInfo cascadingMenuInfo;
        View view;
        int i2;
        int i3;
        int i4;
        LayoutInflater from = LayoutInflater.from(this.mContext);
        MenuAdapter menuAdapter = new MenuAdapter(menuBuilder, from, this.mOverflowOnly);
        if (!isShowing() && this.mForceShowIcon) {
            menuAdapter.setForceShowIcon(true);
        } else if (isShowing()) {
            menuAdapter.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(menuBuilder));
        }
        int measureIndividualMenuWidth = measureIndividualMenuWidth(menuAdapter, null, this.mContext, this.mMenuMaxWidth);
        MenuPopupWindow createPopupWindow = createPopupWindow();
        createPopupWindow.setAdapter(menuAdapter);
        createPopupWindow.setContentWidth(measureIndividualMenuWidth);
        createPopupWindow.setDropDownGravity(this.mDropDownGravity);
        if (this.mShowingMenus.size() > 0) {
            cascadingMenuInfo = this.mShowingMenus.get(this.mShowingMenus.size() - 1);
            view = findParentViewForSubmenu(cascadingMenuInfo, menuBuilder);
        } else {
            cascadingMenuInfo = null;
            view = null;
        }
        if (view != null) {
            createPopupWindow.setTouchModal(false);
            createPopupWindow.setEnterTransition(null);
            int nextMenuPosition = getNextMenuPosition(measureIndividualMenuWidth);
            boolean z = nextMenuPosition == 1;
            this.mLastPosition = nextMenuPosition;
            if (Build.VERSION.SDK_INT >= 26) {
                createPopupWindow.setAnchorView(view);
                i2 = 0;
                i3 = 0;
            } else {
                int[] iArr = new int[2];
                this.mAnchorView.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.mDropDownGravity & 7) == 5) {
                    iArr[0] = iArr[0] + this.mAnchorView.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i2 = iArr2[0] - iArr[0];
                i3 = iArr2[1] - iArr[1];
            }
            if ((this.mDropDownGravity & 5) == 5) {
                if (z) {
                    i4 = i2 + measureIndividualMenuWidth;
                } else {
                    i4 = i2 - view.getWidth();
                }
            } else if (z) {
                i4 = i2 + view.getWidth();
            } else {
                i4 = i2 - measureIndividualMenuWidth;
            }
            createPopupWindow.setHorizontalOffset(i4);
            createPopupWindow.setOverlapAnchor(true);
            createPopupWindow.setVerticalOffset(i3);
        } else {
            if (this.mHasXOffset) {
                createPopupWindow.setHorizontalOffset(this.mXOffset);
            }
            if (this.mHasYOffset) {
                createPopupWindow.setVerticalOffset(this.mYOffset);
            }
            createPopupWindow.setEpicenterBounds(getEpicenterBounds());
        }
        this.mShowingMenus.add(new CascadingMenuInfo(createPopupWindow, menuBuilder, this.mLastPosition));
        createPopupWindow.show();
        ListView listView = createPopupWindow.getListView();
        listView.setOnKeyListener(this);
        if (cascadingMenuInfo == null && this.mShowTitle && menuBuilder.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) listView, false);
            TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(menuBuilder.getHeaderTitle());
            listView.addHeaderView(frameLayout, null, false);
            createPopupWindow.show();
        }
    }

    private MenuItem findMenuItemForSubmenu(@NonNull MenuBuilder menuBuilder, @NonNull MenuBuilder menuBuilder2) {
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
    private View findParentViewForSubmenu(@NonNull CascadingMenuInfo cascadingMenuInfo, @NonNull MenuBuilder menuBuilder) {
        int i2;
        MenuAdapter menuAdapter;
        MenuItem findMenuItemForSubmenu = findMenuItemForSubmenu(cascadingMenuInfo.menu, menuBuilder);
        if (findMenuItemForSubmenu == null) {
            return null;
        }
        ListView listView = cascadingMenuInfo.getListView();
        ListAdapter adapter = listView.getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i2 = headerViewListAdapter.getHeadersCount();
            menuAdapter = (MenuAdapter) headerViewListAdapter.getWrappedAdapter();
        } else {
            i2 = 0;
            menuAdapter = (MenuAdapter) adapter;
        }
        int i3 = -1;
        int i4 = 0;
        int count = menuAdapter.getCount();
        while (true) {
            if (i4 >= count) {
                break;
            }
            if (findMenuItemForSubmenu != menuAdapter.getItem(i4)) {
                i4++;
            } else {
                i3 = i4;
                break;
            }
        }
        if (i3 == -1) {
            return null;
        }
        int firstVisiblePosition = (i3 + i2) - listView.getFirstVisiblePosition();
        if (firstVisiblePosition < 0 || firstVisiblePosition >= listView.getChildCount()) {
            return null;
        }
        return listView.getChildAt(firstVisiblePosition);
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.mShowingMenus.size() > 0 && this.mShowingMenus.get(0).window.isShowing();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        CascadingMenuInfo cascadingMenuInfo = null;
        int i2 = 0;
        int size = this.mShowingMenus.size();
        while (true) {
            if (i2 >= size) {
                break;
            }
            CascadingMenuInfo cascadingMenuInfo2 = this.mShowingMenus.get(i2);
            if (cascadingMenuInfo2.window.isShowing()) {
                i2++;
            } else {
                cascadingMenuInfo = cascadingMenuInfo2;
                break;
            }
        }
        if (cascadingMenuInfo != null) {
            cascadingMenuInfo.menu.close(false);
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        Iterator<CascadingMenuInfo> it = this.mShowingMenus.iterator();
        while (it.hasNext()) {
            toMenuAdapter(it.next().getListView().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        for (CascadingMenuInfo cascadingMenuInfo : this.mShowingMenus) {
            if (subMenuBuilder == cascadingMenuInfo.menu) {
                cascadingMenuInfo.getListView().requestFocus();
                return true;
            }
        }
        if (subMenuBuilder.hasVisibleItems()) {
            addMenu(subMenuBuilder);
            if (this.mPresenterCallback != null) {
                this.mPresenterCallback.onOpenSubMenu(subMenuBuilder);
            }
            return true;
        }
        return false;
    }

    private int findIndexOfAddedMenu(@NonNull MenuBuilder menuBuilder) {
        int size = this.mShowingMenus.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (menuBuilder == this.mShowingMenus.get(i2).menu) {
                return i2;
            }
        }
        return -1;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        int findIndexOfAddedMenu = findIndexOfAddedMenu(menuBuilder);
        if (findIndexOfAddedMenu >= 0) {
            int i2 = findIndexOfAddedMenu + 1;
            if (i2 < this.mShowingMenus.size()) {
                this.mShowingMenus.get(i2).menu.close(false);
            }
            CascadingMenuInfo remove = this.mShowingMenus.remove(findIndexOfAddedMenu);
            remove.menu.removeMenuPresenter(this);
            if (this.mShouldCloseImmediately) {
                remove.window.setExitTransition(null);
                remove.window.setAnimationStyle(0);
            }
            remove.window.dismiss();
            int size = this.mShowingMenus.size();
            if (size > 0) {
                this.mLastPosition = this.mShowingMenus.get(size - 1).position;
            } else {
                this.mLastPosition = getInitialMenuPosition();
            }
            if (size == 0) {
                dismiss();
                if (this.mPresenterCallback != null) {
                    this.mPresenterCallback.onCloseMenu(menuBuilder, true);
                }
                if (this.mTreeObserver != null) {
                    if (this.mTreeObserver.isAlive()) {
                        this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
                    }
                    this.mTreeObserver = null;
                }
                this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
                this.mOnDismissListener.onDismiss();
                return;
            }
            if (z) {
                this.mShowingMenus.get(0).menu.close(false);
            }
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public void setGravity(int i2) {
        if (this.mRawDropDownGravity != i2) {
            this.mRawDropDownGravity = i2;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public void setAnchorView(@NonNull View view) {
        if (this.mAnchorView != view) {
            this.mAnchorView = view;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
        }
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.mShowingMenus.isEmpty() ? null : this.mShowingMenus.get(this.mShowingMenus.size() - 1).getListView();
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public void setHorizontalOffset(int i2) {
        this.mHasXOffset = true;
        this.mXOffset = i2;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public void setVerticalOffset(int i2) {
        this.mHasYOffset = true;
        this.mYOffset = i2;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public void setShowTitle(boolean z) {
        this.mShowTitle = z;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    protected boolean closeMenuOnSubMenuOpened() {
        return false;
    }

    /* loaded from: lib/Mus.dex */
    public static class CascadingMenuInfo {
        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(@NonNull MenuPopupWindow menuPopupWindow, @NonNull MenuBuilder menuBuilder, int i2) {
            this.window = menuPopupWindow;
            this.menu = menuBuilder;
            this.position = i2;
        }

        public ListView getListView() {
            return this.window.getListView();
        }
    }
}
