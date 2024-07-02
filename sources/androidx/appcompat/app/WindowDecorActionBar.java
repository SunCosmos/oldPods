package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.ViewPropertyAnimatorCompatSet;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    private static final Interpolator F = new AccelerateInterpolator();
    private static final Interpolator G = new DecelerateInterpolator();
    private boolean A;
    boolean B;
    Context a;
    private Context b;

    /* renamed from: c */
    private Activity f47c;

    /* renamed from: d */
    ActionBarOverlayLayout f48d;
    ActionBarContainer e;
    DecorToolbar f;
    ActionBarContextView g;
    View h;

    /* renamed from: i */
    ScrollingTabContainerView f49i;
    private TabImpl k;
    private boolean m;
    ActionModeImpl n;
    ActionMode o;
    ActionMode.Callback p;
    private boolean q;
    private boolean s;
    boolean v;
    boolean w;
    private boolean x;
    ViewPropertyAnimatorCompatSet z;
    private ArrayList<TabImpl> j = new ArrayList<>();
    private int l = -1;
    private ArrayList<ActionBar.OnMenuVisibilityListener> r = new ArrayList<>();
    private int t = 0;
    boolean u = true;
    private boolean y = true;
    final ViewPropertyAnimatorListener C = new a();
    final ViewPropertyAnimatorListener D = new b();
    final ViewPropertyAnimatorUpdateListener E = new c();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {

        /* renamed from: c */
        private final Context f50c;

        /* renamed from: d */
        private final MenuBuilder f51d;
        private ActionMode.Callback e;
        private WeakReference<View> f;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.f50c = context;
            this.e = callback;
            MenuBuilder defaultShowAsAction = new MenuBuilder(context).setDefaultShowAsAction(1);
            this.f51d = defaultShowAsAction;
            defaultShowAsAction.setCallback(this);
        }

        public boolean dispatchOnCreate() {
            this.f51d.stopDispatchingItemsChanged();
            try {
                return this.e.onCreateActionMode(this, this.f51d);
            } finally {
                this.f51d.startDispatchingItemsChanged();
            }
        }

        @Override // androidx.appcompat.view.ActionMode
        public void finish() {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.n != this) {
                return;
            }
            if (WindowDecorActionBar.b(windowDecorActionBar.v, windowDecorActionBar.w, false)) {
                this.e.onDestroyActionMode(this);
            } else {
                WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
                windowDecorActionBar2.o = this;
                windowDecorActionBar2.p = this.e;
            }
            this.e = null;
            WindowDecorActionBar.this.animateToMode(false);
            WindowDecorActionBar.this.g.closeMode();
            WindowDecorActionBar.this.f.getViewGroup().sendAccessibilityEvent(32);
            WindowDecorActionBar windowDecorActionBar3 = WindowDecorActionBar.this;
            windowDecorActionBar3.f48d.setHideOnContentScrollEnabled(windowDecorActionBar3.B);
            WindowDecorActionBar.this.n = null;
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
            return this.f51d;
        }

        @Override // androidx.appcompat.view.ActionMode
        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.f50c);
        }

        @Override // androidx.appcompat.view.ActionMode
        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.g.getSubtitle();
        }

        @Override // androidx.appcompat.view.ActionMode
        public CharSequence getTitle() {
            return WindowDecorActionBar.this.g.getTitle();
        }

        @Override // androidx.appcompat.view.ActionMode
        public void invalidate() {
            if (WindowDecorActionBar.this.n != this) {
                return;
            }
            this.f51d.stopDispatchingItemsChanged();
            try {
                this.e.onPrepareActionMode(this, this.f51d);
            } finally {
                this.f51d.startDispatchingItemsChanged();
            }
        }

        @Override // androidx.appcompat.view.ActionMode
        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.g.isTitleOptional();
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
            ActionMode.Callback callback = this.e;
            if (callback != null) {
                return callback.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(@NonNull MenuBuilder menuBuilder) {
            if (this.e == null) {
                return;
            }
            invalidate();
            WindowDecorActionBar.this.g.showOverflowMenu();
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            if (this.e == null) {
                return false;
            }
            if (!subMenuBuilder.hasVisibleItems()) {
                return true;
            }
            new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), subMenuBuilder).show();
            return true;
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setCustomView(View view) {
            WindowDecorActionBar.this.g.setCustomView(view);
            this.f = new WeakReference<>(view);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setSubtitle(int i2) {
            setSubtitle(WindowDecorActionBar.this.a.getResources().getString(i2));
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setSubtitle(CharSequence charSequence) {
            WindowDecorActionBar.this.g.setSubtitle(charSequence);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setTitle(int i2) {
            setTitle(WindowDecorActionBar.this.a.getResources().getString(i2));
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setTitle(CharSequence charSequence) {
            WindowDecorActionBar.this.g.setTitle(charSequence);
        }

        @Override // androidx.appcompat.view.ActionMode
        public void setTitleOptionalHint(boolean z) {
            super.setTitleOptionalHint(z);
            WindowDecorActionBar.this.g.setTitleOptional(z);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public class TabImpl extends ActionBar.Tab {
        private ActionBar.TabListener a;
        private Object b;

        /* renamed from: c */
        private Drawable f52c;

        /* renamed from: d */
        private CharSequence f53d;
        private CharSequence e;
        private int f = -1;
        private View g;

        public TabImpl() {
        }

        public ActionBar.TabListener getCallback() {
            return this.a;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public CharSequence getContentDescription() {
            return this.e;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public View getCustomView() {
            return this.g;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public Drawable getIcon() {
            return this.f52c;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public int getPosition() {
            return this.f;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public Object getTag() {
            return this.b;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public CharSequence getText() {
            return this.f53d;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public void select() {
            WindowDecorActionBar.this.selectTab(this);
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(int i2) {
            return setContentDescription(WindowDecorActionBar.this.a.getResources().getText(i2));
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(CharSequence charSequence) {
            this.e = charSequence;
            int i2 = this.f;
            if (i2 >= 0) {
                WindowDecorActionBar.this.f49i.updateTab(i2);
            }
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(int i2) {
            return setCustomView(LayoutInflater.from(WindowDecorActionBar.this.getThemedContext()).inflate(i2, (ViewGroup) null));
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(View view) {
            this.g = view;
            int i2 = this.f;
            if (i2 >= 0) {
                WindowDecorActionBar.this.f49i.updateTab(i2);
            }
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setIcon(int i2) {
            return setIcon(AppCompatResources.getDrawable(WindowDecorActionBar.this.a, i2));
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setIcon(Drawable drawable) {
            this.f52c = drawable;
            int i2 = this.f;
            if (i2 >= 0) {
                WindowDecorActionBar.this.f49i.updateTab(i2);
            }
            return this;
        }

        public void setPosition(int i2) {
            this.f = i2;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setTabListener(ActionBar.TabListener tabListener) {
            this.a = tabListener;
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setTag(Object obj) {
            this.b = obj;
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setText(int i2) {
            return setText(WindowDecorActionBar.this.a.getResources().getText(i2));
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setText(CharSequence charSequence) {
            this.f53d = charSequence;
            int i2 = this.f;
            if (i2 >= 0) {
                WindowDecorActionBar.this.f49i.updateTab(i2);
            }
            return this;
        }
    }

    /* loaded from: classes.dex */
    public class a extends ViewPropertyAnimatorListenerAdapter {
        a() {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            View view2;
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.u && (view2 = windowDecorActionBar.h) != null) {
                view2.setTranslationY(0.0f);
                WindowDecorActionBar.this.e.setTranslationY(0.0f);
            }
            WindowDecorActionBar.this.e.setVisibility(8);
            WindowDecorActionBar.this.e.setTransitioning(false);
            WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
            windowDecorActionBar2.z = null;
            windowDecorActionBar2.d();
            ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.f48d;
            if (actionBarOverlayLayout != null) {
                ViewCompat.requestApplyInsets(actionBarOverlayLayout);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends ViewPropertyAnimatorListenerAdapter {
        b() {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            windowDecorActionBar.z = null;
            windowDecorActionBar.e.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    public class c implements ViewPropertyAnimatorUpdateListener {
        c() {
        }

        @Override // androidx.core.view.ViewPropertyAnimatorUpdateListener
        public void onAnimationUpdate(View view) {
            ((View) WindowDecorActionBar.this.e.getParent()).invalidate();
        }
    }

    public WindowDecorActionBar(Activity activity, boolean z) {
        this.f47c = activity;
        View decorView = activity.getWindow().getDecorView();
        i(decorView);
        if (z) {
            return;
        }
        this.h = decorView.findViewById(R.id.content);
    }

    public WindowDecorActionBar(Dialog dialog) {
        i(dialog.getWindow().getDecorView());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public WindowDecorActionBar(View view) {
        i(view);
    }

    static boolean b(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    private void c() {
        if (this.k != null) {
            selectTab(null);
        }
        this.j.clear();
        ScrollingTabContainerView scrollingTabContainerView = this.f49i;
        if (scrollingTabContainerView != null) {
            scrollingTabContainerView.removeAllTabs();
        }
        this.l = -1;
    }

    private void e(ActionBar.Tab tab, int i2) {
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        tabImpl.setPosition(i2);
        this.j.add(i2, tabImpl);
        int size = this.j.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            } else {
                this.j.get(i2).setPosition(i2);
            }
        }
    }

    private void f() {
        if (this.f49i != null) {
            return;
        }
        ScrollingTabContainerView scrollingTabContainerView = new ScrollingTabContainerView(this.a);
        if (this.s) {
            scrollingTabContainerView.setVisibility(0);
            this.f.setEmbeddedTabView(scrollingTabContainerView);
        } else {
            if (getNavigationMode() == 2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f48d;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
            this.e.setTabContainer(scrollingTabContainerView);
        }
        this.f49i = scrollingTabContainerView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DecorToolbar g(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    private void h() {
        if (this.x) {
            this.x = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f48d;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            m(false);
        }
    }

    private void i(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(androidx.appcompat.R.id.decor_content_parent);
        this.f48d = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f = g(view.findViewById(androidx.appcompat.R.id.action_bar));
        this.g = (ActionBarContextView) view.findViewById(androidx.appcompat.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(androidx.appcompat.R.id.action_bar_container);
        this.e = actionBarContainer;
        DecorToolbar decorToolbar = this.f;
        if (decorToolbar == null || this.g == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.a = decorToolbar.getContext();
        boolean z = (this.f.getDisplayOptions() & 4) != 0;
        if (z) {
            this.m = true;
        }
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(this.a);
        setHomeButtonEnabled(actionBarPolicy.enableHomeButtonByDefault() || z);
        j(actionBarPolicy.hasEmbeddedTabs());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(null, androidx.appcompat.R.styleable.ActionBar, androidx.appcompat.R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.ActionBar_hideOnContentScroll, false)) {
            setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            setElevation(dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private void j(boolean z) {
        this.s = z;
        if (z) {
            this.e.setTabContainer(null);
            this.f.setEmbeddedTabView(this.f49i);
        } else {
            this.f.setEmbeddedTabView(null);
            this.e.setTabContainer(this.f49i);
        }
        boolean z2 = getNavigationMode() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.f49i;
        if (scrollingTabContainerView != null) {
            if (z2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f48d;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.f.setCollapsible(!this.s && z2);
        this.f48d.setHasNonEmbeddedTabs(!this.s && z2);
    }

    private boolean k() {
        return ViewCompat.isLaidOut(this.e);
    }

    private void l() {
        if (this.x) {
            return;
        }
        this.x = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f48d;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        m(false);
    }

    private void m(boolean z) {
        if (b(this.v, this.w, this.x)) {
            if (this.y) {
                return;
            }
            this.y = true;
            doShow(z);
            return;
        }
        if (this.y) {
            this.y = false;
            doHide(z);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.r.add(onMenuVisibilityListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addTab(ActionBar.Tab tab) {
        addTab(tab, this.j.isEmpty());
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i2) {
        addTab(tab, i2, this.j.isEmpty());
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i2, boolean z) {
        f();
        this.f49i.addTab(tab, i2, z);
        e(tab, i2);
        if (z) {
            selectTab(tab);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addTab(ActionBar.Tab tab, boolean z) {
        f();
        this.f49i.addTab(tab, z);
        e(tab, this.j.size());
        if (z) {
            selectTab(tab);
        }
    }

    public void animateToMode(boolean z) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2;
        if (z) {
            l();
        } else {
            h();
        }
        if (!k()) {
            if (z) {
                this.f.setVisibility(4);
                this.g.setVisibility(0);
                return;
            } else {
                this.f.setVisibility(0);
                this.g.setVisibility(8);
                return;
            }
        }
        if (z) {
            viewPropertyAnimatorCompat2 = this.f.setupAnimatorToVisibility(4, 100L);
            viewPropertyAnimatorCompat = this.g.setupAnimatorToVisibility(0, 200L);
        } else {
            viewPropertyAnimatorCompat = this.f.setupAnimatorToVisibility(0, 200L);
            viewPropertyAnimatorCompat2 = this.g.setupAnimatorToVisibility(8, 100L);
        }
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
        viewPropertyAnimatorCompatSet.playSequentially(viewPropertyAnimatorCompat2, viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompatSet.start();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean collapseActionView() {
        DecorToolbar decorToolbar = this.f;
        if (decorToolbar == null || !decorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.f.collapseActionView();
        return true;
    }

    void d() {
        ActionMode.Callback callback = this.p;
        if (callback != null) {
            callback.onDestroyActionMode(this.o);
            this.o = null;
            this.p = null;
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z == this.q) {
            return;
        }
        this.q = z;
        int size = this.r.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.r.get(i2).onMenuVisibilityChanged(z);
        }
    }

    public void doHide(boolean z) {
        View view;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.z;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
        if (this.t != 0 || (!this.A && !z)) {
            this.C.onAnimationEnd(null);
            return;
        }
        this.e.setAlpha(1.0f);
        this.e.setTransitioning(true);
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
        float f = -this.e.getHeight();
        if (z) {
            this.e.getLocationInWindow(new int[]{0, 0});
            f -= r5[1];
        }
        ViewPropertyAnimatorCompat translationY = ViewCompat.animate(this.e).translationY(f);
        translationY.setUpdateListener(this.E);
        viewPropertyAnimatorCompatSet2.play(translationY);
        if (this.u && (view = this.h) != null) {
            viewPropertyAnimatorCompatSet2.play(ViewCompat.animate(view).translationY(f));
        }
        viewPropertyAnimatorCompatSet2.setInterpolator(F);
        viewPropertyAnimatorCompatSet2.setDuration(250L);
        viewPropertyAnimatorCompatSet2.setListener(this.C);
        this.z = viewPropertyAnimatorCompatSet2;
        viewPropertyAnimatorCompatSet2.start();
    }

    public void doShow(boolean z) {
        View view;
        View view2;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.z;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
        this.e.setVisibility(0);
        if (this.t == 0 && (this.A || z)) {
            this.e.setTranslationY(0.0f);
            float f = -this.e.getHeight();
            if (z) {
                this.e.getLocationInWindow(new int[]{0, 0});
                f -= r5[1];
            }
            this.e.setTranslationY(f);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat translationY = ViewCompat.animate(this.e).translationY(0.0f);
            translationY.setUpdateListener(this.E);
            viewPropertyAnimatorCompatSet2.play(translationY);
            if (this.u && (view2 = this.h) != null) {
                view2.setTranslationY(f);
                viewPropertyAnimatorCompatSet2.play(ViewCompat.animate(this.h).translationY(0.0f));
            }
            viewPropertyAnimatorCompatSet2.setInterpolator(G);
            viewPropertyAnimatorCompatSet2.setDuration(250L);
            viewPropertyAnimatorCompatSet2.setListener(this.D);
            this.z = viewPropertyAnimatorCompatSet2;
            viewPropertyAnimatorCompatSet2.start();
        } else {
            this.e.setAlpha(1.0f);
            this.e.setTranslationY(0.0f);
            if (this.u && (view = this.h) != null) {
                view.setTranslationY(0.0f);
            }
            this.D.onAnimationEnd(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f48d;
        if (actionBarOverlayLayout != null) {
            ViewCompat.requestApplyInsets(actionBarOverlayLayout);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void enableContentAnimations(boolean z) {
        this.u = z;
    }

    @Override // androidx.appcompat.app.ActionBar
    public View getCustomView() {
        return this.f.getCustomView();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getDisplayOptions() {
        return this.f.getDisplayOptions();
    }

    @Override // androidx.appcompat.app.ActionBar
    public float getElevation() {
        return ViewCompat.getElevation(this.e);
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getHeight() {
        return this.e.getHeight();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getHideOffset() {
        return this.f48d.getActionBarHideOffset();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getNavigationItemCount() {
        int navigationMode = this.f.getNavigationMode();
        if (navigationMode == 1) {
            return this.f.getDropdownItemCount();
        }
        if (navigationMode != 2) {
            return 0;
        }
        return this.j.size();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getNavigationMode() {
        return this.f.getNavigationMode();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getSelectedNavigationIndex() {
        TabImpl tabImpl;
        int navigationMode = this.f.getNavigationMode();
        if (navigationMode == 1) {
            return this.f.getDropdownSelectedPosition();
        }
        if (navigationMode == 2 && (tabImpl = this.k) != null) {
            return tabImpl.getPosition();
        }
        return -1;
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionBar.Tab getSelectedTab() {
        return this.k;
    }

    @Override // androidx.appcompat.app.ActionBar
    public CharSequence getSubtitle() {
        return this.f.getSubtitle();
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionBar.Tab getTabAt(int i2) {
        return this.j.get(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getTabCount() {
        return this.j.size();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context getThemedContext() {
        if (this.b == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.b = new ContextThemeWrapper(this.a, i2);
            } else {
                this.b = this.a;
            }
        }
        return this.b;
    }

    @Override // androidx.appcompat.app.ActionBar
    public CharSequence getTitle() {
        return this.f.getTitle();
    }

    public boolean hasIcon() {
        return this.f.hasIcon();
    }

    public boolean hasLogo() {
        return this.f.hasLogo();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void hide() {
        if (this.v) {
            return;
        }
        this.v = true;
        m(false);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void hideForSystem() {
        if (this.w) {
            return;
        }
        this.w = true;
        m(true);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean isHideOnContentScrollEnabled() {
        return this.f48d.isHideOnContentScrollEnabled();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean isShowing() {
        int height = getHeight();
        return this.y && (height == 0 || getHideOffset() < height);
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean isTitleTruncated() {
        DecorToolbar decorToolbar = this.f;
        return decorToolbar != null && decorToolbar.isTitleTruncated();
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionBar.Tab newTab() {
        return new TabImpl();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void onConfigurationChanged(Configuration configuration) {
        j(ActionBarPolicy.get(this.a).hasEmbeddedTabs());
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onContentScrollStarted() {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.z;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
            this.z = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onContentScrollStopped() {
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean onKeyShortcut(int i2, KeyEvent keyEvent) {
        Menu menu;
        ActionModeImpl actionModeImpl = this.n;
        if (actionModeImpl == null || (menu = actionModeImpl.getMenu()) == null) {
            return false;
        }
        menu.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menu.performShortcut(i2, keyEvent, 0);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onWindowVisibilityChanged(int i2) {
        this.t = i2;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeAllTabs() {
        c();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.r.remove(onMenuVisibilityListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeTab(ActionBar.Tab tab) {
        removeTabAt(tab.getPosition());
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeTabAt(int i2) {
        if (this.f49i == null) {
            return;
        }
        TabImpl tabImpl = this.k;
        int position = tabImpl != null ? tabImpl.getPosition() : this.l;
        this.f49i.removeTabAt(i2);
        TabImpl remove = this.j.remove(i2);
        if (remove != null) {
            remove.setPosition(-1);
        }
        int size = this.j.size();
        for (int i3 = i2; i3 < size; i3++) {
            this.j.get(i3).setPosition(i3);
        }
        if (position == i2) {
            selectTab(this.j.isEmpty() ? null : this.j.get(Math.max(0, i2 - 1)));
        }
    }

    public boolean requestFocus() {
        ViewGroup viewGroup = this.f.getViewGroup();
        if (viewGroup == null || viewGroup.hasFocus()) {
            return false;
        }
        viewGroup.requestFocus();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void selectTab(ActionBar.Tab tab) {
        if (getNavigationMode() != 2) {
            this.l = tab != null ? tab.getPosition() : -1;
            return;
        }
        FragmentTransaction disallowAddToBackStack = (!(this.f47c instanceof FragmentActivity) || this.f.getViewGroup().isInEditMode()) ? null : ((FragmentActivity) this.f47c).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        TabImpl tabImpl = this.k;
        if (tabImpl != tab) {
            this.f49i.setTabSelected(tab != null ? tab.getPosition() : -1);
            TabImpl tabImpl2 = this.k;
            if (tabImpl2 != null) {
                tabImpl2.getCallback().onTabUnselected(this.k, disallowAddToBackStack);
            }
            TabImpl tabImpl3 = (TabImpl) tab;
            this.k = tabImpl3;
            if (tabImpl3 != null) {
                tabImpl3.getCallback().onTabSelected(this.k, disallowAddToBackStack);
            }
        } else if (tabImpl != null) {
            tabImpl.getCallback().onTabReselected(this.k, disallowAddToBackStack);
            this.f49i.animateToTab(tab.getPosition());
        }
        if (disallowAddToBackStack == null || disallowAddToBackStack.isEmpty()) {
            return;
        }
        disallowAddToBackStack.commit();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setBackgroundDrawable(Drawable drawable) {
        this.e.setPrimaryBackground(drawable);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setCustomView(int i2) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(i2, this.f.getViewGroup(), false));
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setCustomView(View view) {
        this.f.setCustomView(view);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        this.f.setCustomView(view);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        if (this.m) {
            return;
        }
        setDisplayHomeAsUpEnabled(z);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean z) {
        setDisplayOptions(z ? 4 : 0, 4);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayOptions(int i2) {
        if ((i2 & 4) != 0) {
            this.m = true;
        }
        this.f.setDisplayOptions(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayOptions(int i2, int i3) {
        int displayOptions = this.f.getDisplayOptions();
        if ((i3 & 4) != 0) {
            this.m = true;
        }
        this.f.setDisplayOptions((i2 & i3) | ((i3 ^ (-1)) & displayOptions));
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayShowCustomEnabled(boolean z) {
        setDisplayOptions(z ? 16 : 0, 16);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayShowHomeEnabled(boolean z) {
        setDisplayOptions(z ? 2 : 0, 2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayShowTitleEnabled(boolean z) {
        setDisplayOptions(z ? 8 : 0, 8);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayUseLogoEnabled(boolean z) {
        setDisplayOptions(z ? 1 : 0, 1);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setElevation(float f) {
        ViewCompat.setElevation(this.e, f);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHideOffset(int i2) {
        if (i2 != 0 && !this.f48d.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        }
        this.f48d.setActionBarHideOffset(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHideOnContentScrollEnabled(boolean z) {
        if (z && !this.f48d.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.B = z;
        this.f48d.setHideOnContentScrollEnabled(z);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeActionContentDescription(int i2) {
        this.f.setNavigationContentDescription(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeActionContentDescription(CharSequence charSequence) {
        this.f.setNavigationContentDescription(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeAsUpIndicator(int i2) {
        this.f.setNavigationIcon(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeAsUpIndicator(Drawable drawable) {
        this.f.setNavigationIcon(drawable);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeButtonEnabled(boolean z) {
        this.f.setHomeButtonEnabled(z);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setIcon(int i2) {
        this.f.setIcon(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setIcon(Drawable drawable) {
        this.f.setIcon(drawable);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.f.setDropdownParams(spinnerAdapter, new androidx.appcompat.app.c(onNavigationListener));
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setLogo(int i2) {
        this.f.setLogo(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setLogo(Drawable drawable) {
        this.f.setLogo(drawable);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setNavigationMode(int i2) {
        ActionBarOverlayLayout actionBarOverlayLayout;
        int navigationMode = this.f.getNavigationMode();
        if (navigationMode == 2) {
            this.l = getSelectedNavigationIndex();
            selectTab(null);
            this.f49i.setVisibility(8);
        }
        if (navigationMode != i2 && !this.s && (actionBarOverlayLayout = this.f48d) != null) {
            ViewCompat.requestApplyInsets(actionBarOverlayLayout);
        }
        this.f.setNavigationMode(i2);
        boolean z = false;
        if (i2 == 2) {
            f();
            this.f49i.setVisibility(0);
            int i3 = this.l;
            if (i3 != -1) {
                setSelectedNavigationItem(i3);
                this.l = -1;
            }
        }
        this.f.setCollapsible(i2 == 2 && !this.s);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.f48d;
        if (i2 == 2 && !this.s) {
            z = true;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSelectedNavigationItem(int i2) {
        int navigationMode = this.f.getNavigationMode();
        if (navigationMode == 1) {
            this.f.setDropdownSelectedPosition(i2);
        } else {
            if (navigationMode != 2) {
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
            }
            selectTab(this.j.get(i2));
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setShowHideAnimationEnabled(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        this.A = z;
        if (z || (viewPropertyAnimatorCompatSet = this.z) == null) {
            return;
        }
        viewPropertyAnimatorCompatSet.cancel();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSplitBackgroundDrawable(Drawable drawable) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setStackedBackgroundDrawable(Drawable drawable) {
        this.e.setStackedBackground(drawable);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSubtitle(int i2) {
        setSubtitle(this.a.getString(i2));
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSubtitle(CharSequence charSequence) {
        this.f.setSubtitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setTitle(int i2) {
        setTitle(this.a.getString(i2));
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setTitle(CharSequence charSequence) {
        this.f.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setWindowTitle(CharSequence charSequence) {
        this.f.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void show() {
        if (this.v) {
            this.v = false;
            m(false);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void showForSystem() {
        if (this.w) {
            this.w = false;
            m(true);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionModeImpl actionModeImpl = this.n;
        if (actionModeImpl != null) {
            actionModeImpl.finish();
        }
        this.f48d.setHideOnContentScrollEnabled(false);
        this.g.killMode();
        ActionModeImpl actionModeImpl2 = new ActionModeImpl(this.g.getContext(), callback);
        if (!actionModeImpl2.dispatchOnCreate()) {
            return null;
        }
        this.n = actionModeImpl2;
        actionModeImpl2.invalidate();
        this.g.initForMode(actionModeImpl2);
        animateToMode(true);
        this.g.sendAccessibilityEvent(32);
        return actionModeImpl2;
    }
}
