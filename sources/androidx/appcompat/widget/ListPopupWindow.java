package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.ViewCompat;
import androidx.core.widget.PopupWindowCompat;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ListPopupWindow implements ShowableListMenu {
    private static Method G = null;
    private static Method H = null;
    private static Method I = null;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    public static final int WRAP_CONTENT = -2;
    private Runnable A;
    final Handler B;
    private final Rect C;
    private Rect D;
    private boolean E;
    PopupWindow F;
    private Context a;
    private ListAdapter b;

    /* renamed from: c */
    k f176c;

    /* renamed from: d */
    private int f177d;
    private int e;
    private int f;
    private int g;
    private int h;

    /* renamed from: i */
    private boolean f178i;
    private boolean j;
    private boolean k;
    private int l;
    private boolean m;
    private boolean n;
    int o;
    private View p;
    private int q;
    private DataSetObserver r;
    private View s;
    private Drawable t;
    private AdapterView.OnItemClickListener u;
    private AdapterView.OnItemSelectedListener v;
    final h w;
    private final g x;
    private final f y;
    private final d z;

    /* loaded from: classes.dex */
    class a extends ForwardingListener {
        a(View view) {
            super(view);
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        /* renamed from: j */
        public ListPopupWindow getPopup() {
            return ListPopupWindow.this;
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View anchorView = ListPopupWindow.this.getAnchorView();
            if (anchorView == null || anchorView.getWindowToken() == null) {
                return;
            }
            ListPopupWindow.this.show();
        }
    }

    /* loaded from: classes.dex */
    public class c implements AdapterView.OnItemSelectedListener {
        c() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            k kVar;
            if (i2 == -1 || (kVar = ListPopupWindow.this.f176c) == null) {
                return;
            }
            kVar.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    /* loaded from: classes.dex */
    public class e extends DataSetObserver {
        e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class f implements AbsListView.OnScrollListener {
        f() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 != 1 || ListPopupWindow.this.isInputMethodNotNeeded() || ListPopupWindow.this.F.getContentView() == null) {
                return;
            }
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            listPopupWindow.B.removeCallbacks(listPopupWindow.w);
            ListPopupWindow.this.w.run();
        }
    }

    /* loaded from: classes.dex */
    public class g implements View.OnTouchListener {
        g() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.F) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.F.getWidth() && y >= 0 && y < ListPopupWindow.this.F.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.B.postDelayed(listPopupWindow.w, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
            listPopupWindow2.B.removeCallbacks(listPopupWindow2.w);
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class h implements Runnable {
        h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k kVar = ListPopupWindow.this.f176c;
            if (kVar == null || !ViewCompat.isAttachedToWindow(kVar) || ListPopupWindow.this.f176c.getCount() <= ListPopupWindow.this.f176c.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.f176c.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.o) {
                listPopupWindow.F.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    static {
        Class cls = Boolean.TYPE;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 <= 28) {
            try {
                G = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", cls);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                I = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (i2 <= 23) {
            try {
                H = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, cls);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(@NonNull Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        this.f177d = -2;
        this.e = -2;
        this.h = 1002;
        this.l = 0;
        this.m = false;
        this.n = false;
        this.o = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.q = 0;
        this.w = new h();
        this.x = new g();
        this.y = new f();
        this.z = new d();
        this.C = new Rect();
        this.a = context;
        this.B = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i2, i3);
        this.f = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f178i = true;
        }
        obtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i2, i3);
        this.F = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0151  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int e() {
        /*
            Method dump skipped, instructions count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ListPopupWindow.e():int");
    }

    private int g(View view, int i2, boolean z) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.F.getMaxAvailableHeight(view, i2, z);
        }
        Method method = H;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.F, view, Integer.valueOf(i2), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.F.getMaxAvailableHeight(view, i2);
    }

    private static boolean h(int i2) {
        return i2 == 66 || i2 == 23;
    }

    private void i() {
        View view = this.p;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.p);
            }
        }
    }

    private void j(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.F.setIsClippedToScreen(z);
            return;
        }
        Method method = G;
        if (method != null) {
            try {
                method.invoke(this.F, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void clearListSelection() {
        k kVar = this.f176c;
        if (kVar != null) {
            kVar.setListSelectionHidden(true);
            kVar.requestLayout();
        }
    }

    public View.OnTouchListener createDragToOpenListener(View view) {
        return new a(view);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        this.F.dismiss();
        i();
        this.F.setContentView(null);
        this.f176c = null;
        this.B.removeCallbacks(this.w);
    }

    @NonNull
    k f(Context context, boolean z) {
        return new k(context, z);
    }

    @Nullable
    public View getAnchorView() {
        return this.s;
    }

    @StyleRes
    public int getAnimationStyle() {
        return this.F.getAnimationStyle();
    }

    @Nullable
    public Drawable getBackground() {
        return this.F.getBackground();
    }

    @Nullable
    public Rect getEpicenterBounds() {
        if (this.D != null) {
            return new Rect(this.D);
        }
        return null;
    }

    public int getHeight() {
        return this.f177d;
    }

    public int getHorizontalOffset() {
        return this.f;
    }

    public int getInputMethodMode() {
        return this.F.getInputMethodMode();
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    @Nullable
    public ListView getListView() {
        return this.f176c;
    }

    public int getPromptPosition() {
        return this.q;
    }

    @Nullable
    public Object getSelectedItem() {
        if (isShowing()) {
            return this.f176c.getSelectedItem();
        }
        return null;
    }

    public long getSelectedItemId() {
        if (isShowing()) {
            return this.f176c.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int getSelectedItemPosition() {
        if (isShowing()) {
            return this.f176c.getSelectedItemPosition();
        }
        return -1;
    }

    @Nullable
    public View getSelectedView() {
        if (isShowing()) {
            return this.f176c.getSelectedView();
        }
        return null;
    }

    public int getSoftInputMode() {
        return this.F.getSoftInputMode();
    }

    public int getVerticalOffset() {
        if (this.f178i) {
            return this.g;
        }
        return 0;
    }

    public int getWidth() {
        return this.e;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean isDropDownAlwaysVisible() {
        return this.m;
    }

    public boolean isInputMethodNotNeeded() {
        return this.F.getInputMethodMode() == 2;
    }

    public boolean isModal() {
        return this.E;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.F.isShowing();
    }

    public boolean onKeyDown(int i2, @NonNull KeyEvent keyEvent) {
        if (isShowing() && i2 != 62 && (this.f176c.getSelectedItemPosition() >= 0 || !h(i2))) {
            int selectedItemPosition = this.f176c.getSelectedItemPosition();
            boolean z = !this.F.isAboveAnchor();
            ListAdapter listAdapter = this.b;
            int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            int i4 = Integer.MIN_VALUE;
            if (listAdapter != null) {
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                int lookForSelectablePosition = areAllItemsEnabled ? 0 : this.f176c.lookForSelectablePosition(0, true);
                int count = areAllItemsEnabled ? listAdapter.getCount() - 1 : this.f176c.lookForSelectablePosition(listAdapter.getCount() - 1, false);
                i3 = lookForSelectablePosition;
                i4 = count;
            }
            if ((z && i2 == 19 && selectedItemPosition <= i3) || (!z && i2 == 20 && selectedItemPosition >= i4)) {
                clearListSelection();
                this.F.setInputMethodMode(1);
                show();
                return true;
            }
            this.f176c.setListSelectionHidden(false);
            if (this.f176c.onKeyDown(i2, keyEvent)) {
                this.F.setInputMethodMode(2);
                this.f176c.requestFocusFromTouch();
                show();
                if (i2 == 19 || i2 == 20 || i2 == 23 || i2 == 66) {
                    return true;
                }
            } else if (z && i2 == 20) {
                if (selectedItemPosition == i4) {
                    return true;
                }
            } else if (!z && i2 == 19 && selectedItemPosition == i3) {
                return true;
            }
        }
        return false;
    }

    public boolean onKeyPreIme(int i2, @NonNull KeyEvent keyEvent) {
        if (i2 != 4 || !isShowing()) {
            return false;
        }
        View view = this.s;
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            KeyEvent.DispatcherState keyDispatcherState = view.getKeyDispatcherState();
            if (keyDispatcherState != null) {
                keyDispatcherState.startTracking(keyEvent, this);
            }
            return true;
        }
        if (keyEvent.getAction() != 1) {
            return false;
        }
        KeyEvent.DispatcherState keyDispatcherState2 = view.getKeyDispatcherState();
        if (keyDispatcherState2 != null) {
            keyDispatcherState2.handleUpEvent(keyEvent);
        }
        if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
            return false;
        }
        dismiss();
        return true;
    }

    public boolean onKeyUp(int i2, @NonNull KeyEvent keyEvent) {
        if (!isShowing() || this.f176c.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean onKeyUp = this.f176c.onKeyUp(i2, keyEvent);
        if (onKeyUp && h(i2)) {
            dismiss();
        }
        return onKeyUp;
    }

    public boolean performItemClick(int i2) {
        if (!isShowing()) {
            return false;
        }
        if (this.u == null) {
            return true;
        }
        k kVar = this.f176c;
        this.u.onItemClick(kVar, kVar.getChildAt(i2 - kVar.getFirstVisiblePosition()), i2, kVar.getAdapter().getItemId(i2));
        return true;
    }

    public void postShow() {
        this.B.post(this.A);
    }

    public void setAdapter(@Nullable ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.r;
        if (dataSetObserver == null) {
            this.r = new e();
        } else {
            ListAdapter listAdapter2 = this.b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.r);
        }
        k kVar = this.f176c;
        if (kVar != null) {
            kVar.setAdapter(this.b);
        }
    }

    public void setAnchorView(@Nullable View view) {
        this.s = view;
    }

    public void setAnimationStyle(@StyleRes int i2) {
        this.F.setAnimationStyle(i2);
    }

    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        this.F.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i2) {
        Drawable background = this.F.getBackground();
        if (background == null) {
            setWidth(i2);
            return;
        }
        background.getPadding(this.C);
        Rect rect = this.C;
        this.e = rect.left + rect.right + i2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setDropDownAlwaysVisible(boolean z) {
        this.m = z;
    }

    public void setDropDownGravity(int i2) {
        this.l = i2;
    }

    public void setEpicenterBounds(@Nullable Rect rect) {
        this.D = rect != null ? new Rect(rect) : null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setForceIgnoreOutsideTouch(boolean z) {
        this.n = z;
    }

    public void setHeight(int i2) {
        if (i2 < 0 && -2 != i2 && -1 != i2) {
            throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
        }
        this.f177d = i2;
    }

    public void setHorizontalOffset(int i2) {
        this.f = i2;
    }

    public void setInputMethodMode(int i2) {
        this.F.setInputMethodMode(i2);
    }

    public void setListSelector(Drawable drawable) {
        this.t = drawable;
    }

    public void setModal(boolean z) {
        this.E = z;
        this.F.setFocusable(z);
    }

    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        this.F.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(@Nullable AdapterView.OnItemClickListener onItemClickListener) {
        this.u = onItemClickListener;
    }

    public void setOnItemSelectedListener(@Nullable AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.v = onItemSelectedListener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setOverlapAnchor(boolean z) {
        this.k = true;
        this.j = z;
    }

    public void setPromptPosition(int i2) {
        this.q = i2;
    }

    public void setPromptView(@Nullable View view) {
        boolean isShowing = isShowing();
        if (isShowing) {
            i();
        }
        this.p = view;
        if (isShowing) {
            show();
        }
    }

    public void setSelection(int i2) {
        k kVar = this.f176c;
        if (!isShowing() || kVar == null) {
            return;
        }
        kVar.setListSelectionHidden(false);
        kVar.setSelection(i2);
        if (kVar.getChoiceMode() != 0) {
            kVar.setItemChecked(i2, true);
        }
    }

    public void setSoftInputMode(int i2) {
        this.F.setSoftInputMode(i2);
    }

    public void setVerticalOffset(int i2) {
        this.g = i2;
        this.f178i = true;
    }

    public void setWidth(int i2) {
        this.e = i2;
    }

    public void setWindowLayoutType(int i2) {
        this.h = i2;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        int e2 = e();
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.F, this.h);
        if (this.F.isShowing()) {
            if (ViewCompat.isAttachedToWindow(getAnchorView())) {
                int i2 = this.e;
                if (i2 == -1) {
                    i2 = -1;
                } else if (i2 == -2) {
                    i2 = getAnchorView().getWidth();
                }
                int i3 = this.f177d;
                if (i3 == -1) {
                    if (!isInputMethodNotNeeded) {
                        e2 = -1;
                    }
                    if (isInputMethodNotNeeded) {
                        this.F.setWidth(this.e == -1 ? -1 : 0);
                        this.F.setHeight(0);
                    } else {
                        this.F.setWidth(this.e == -1 ? -1 : 0);
                        this.F.setHeight(-1);
                    }
                } else if (i3 != -2) {
                    e2 = i3;
                }
                this.F.setOutsideTouchable((this.n || this.m) ? false : true);
                this.F.update(getAnchorView(), this.f, this.g, i2 < 0 ? -1 : i2, e2 < 0 ? -1 : e2);
                return;
            }
            return;
        }
        int i4 = this.e;
        if (i4 == -1) {
            i4 = -1;
        } else if (i4 == -2) {
            i4 = getAnchorView().getWidth();
        }
        int i5 = this.f177d;
        if (i5 == -1) {
            e2 = -1;
        } else if (i5 != -2) {
            e2 = i5;
        }
        this.F.setWidth(i4);
        this.F.setHeight(e2);
        j(true);
        this.F.setOutsideTouchable((this.n || this.m) ? false : true);
        this.F.setTouchInterceptor(this.x);
        if (this.k) {
            PopupWindowCompat.setOverlapAnchor(this.F, this.j);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = I;
            if (method != null) {
                try {
                    method.invoke(this.F, this.D);
                } catch (Exception e3) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e3);
                }
            }
        } else {
            this.F.setEpicenterBounds(this.D);
        }
        PopupWindowCompat.showAsDropDown(this.F, getAnchorView(), this.f, this.g, this.l);
        this.f176c.setSelection(-1);
        if (!this.E || this.f176c.isInTouchMode()) {
            clearListSelection();
        }
        if (this.E) {
            return;
        }
        this.B.post(this.z);
    }
}
