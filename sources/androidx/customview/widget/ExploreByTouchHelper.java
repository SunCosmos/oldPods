package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewParentCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.customview.widget.FocusStrategy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect n = new Rect(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> o = new a();
    private static final FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> p = new b();
    private final AccessibilityManager h;

    /* renamed from: i */
    private final View f676i;
    private c j;

    /* renamed from: d */
    private final Rect f675d = new Rect();
    private final Rect e = new Rect();
    private final Rect f = new Rect();
    private final int[] g = new int[2];
    int k = Integer.MIN_VALUE;
    int l = Integer.MIN_VALUE;
    private int m = Integer.MIN_VALUE;

    /* loaded from: classes.dex */
    static class a implements FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> {
        a() {
        }

        @Override // androidx.customview.widget.FocusStrategy.BoundsAdapter
        /* renamed from: a */
        public void obtainBounds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Rect rect) {
            accessibilityNodeInfoCompat.getBoundsInParent(rect);
        }
    }

    /* loaded from: classes.dex */
    static class b implements FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> {
        b() {
        }

        @Override // androidx.customview.widget.FocusStrategy.CollectionAdapter
        /* renamed from: a */
        public AccessibilityNodeInfoCompat get(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat, int i2) {
            return sparseArrayCompat.valueAt(i2);
        }

        @Override // androidx.customview.widget.FocusStrategy.CollectionAdapter
        /* renamed from: b */
        public int size(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat) {
            return sparseArrayCompat.size();
        }
    }

    /* loaded from: classes.dex */
    private class c extends AccessibilityNodeProviderCompat {
        c() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i2) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.t(i2));
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat findFocus(int i2) {
            int i3 = i2 == 2 ? ExploreByTouchHelper.this.k : ExploreByTouchHelper.this.l;
            if (i3 == Integer.MIN_VALUE) {
                return null;
            }
            return createAccessibilityNodeInfo(i3);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public boolean performAction(int i2, int i3, Bundle bundle) {
            return ExploreByTouchHelper.this.A(i2, i3, bundle);
        }
    }

    public ExploreByTouchHelper(@NonNull View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.f676i = view;
        this.h = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (ViewCompat.getImportantForAccessibility(view) == 0) {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
    }

    private boolean B(int i2, int i3, Bundle bundle) {
        return i3 != 1 ? i3 != 2 ? i3 != 64 ? i3 != 128 ? u(i2, i3, bundle) : e(i2) : D(i2) : clearKeyboardFocusForVirtualView(i2) : requestKeyboardFocusForVirtualView(i2);
    }

    private boolean C(int i2, Bundle bundle) {
        return ViewCompat.performAccessibilityAction(this.f676i, i2, bundle);
    }

    private boolean D(int i2) {
        int i3;
        if (!this.h.isEnabled() || !this.h.isTouchExplorationEnabled() || (i3 = this.k) == i2) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            e(i3);
        }
        this.k = i2;
        this.f676i.invalidate();
        sendEventForVirtualView(i2, 32768);
        return true;
    }

    private void E(int i2) {
        int i3 = this.m;
        if (i3 == i2) {
            return;
        }
        this.m = i2;
        sendEventForVirtualView(i2, 128);
        sendEventForVirtualView(i3, 256);
    }

    private boolean e(int i2) {
        if (this.k != i2) {
            return false;
        }
        this.k = Integer.MIN_VALUE;
        this.f676i.invalidate();
        sendEventForVirtualView(i2, 65536);
        return true;
    }

    private boolean f() {
        int i2 = this.l;
        return i2 != Integer.MIN_VALUE && u(i2, 16, null);
    }

    private AccessibilityEvent g(int i2, int i3) {
        return i2 != -1 ? h(i2, i3) : i(i3);
    }

    private AccessibilityEvent h(int i2, int i3) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i3);
        AccessibilityNodeInfoCompat t = t(i2);
        obtain.getText().add(t.getText());
        obtain.setContentDescription(t.getContentDescription());
        obtain.setScrollable(t.isScrollable());
        obtain.setPassword(t.isPassword());
        obtain.setEnabled(t.isEnabled());
        obtain.setChecked(t.isChecked());
        w(i2, obtain);
        if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain.setClassName(t.getClassName());
        AccessibilityRecordCompat.setSource(obtain, this.f676i, i2);
        obtain.setPackageName(this.f676i.getContext().getPackageName());
        return obtain;
    }

    private AccessibilityEvent i(int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        this.f676i.onInitializeAccessibilityEvent(obtain);
        return obtain;
    }

    @NonNull
    private AccessibilityNodeInfoCompat j(int i2) {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        obtain.setClassName("android.view.View");
        Rect rect = n;
        obtain.setBoundsInParent(rect);
        obtain.setBoundsInScreen(rect);
        obtain.setParent(this.f676i);
        y(i2, obtain);
        if (obtain.getText() == null && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.e);
        if (this.e.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int actions = obtain.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((actions & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        obtain.setPackageName(this.f676i.getContext().getPackageName());
        obtain.setSource(this.f676i, i2);
        if (this.k == i2) {
            obtain.setAccessibilityFocused(true);
            obtain.addAction(128);
        } else {
            obtain.setAccessibilityFocused(false);
            obtain.addAction(64);
        }
        boolean z = this.l == i2;
        if (z) {
            obtain.addAction(2);
        } else if (obtain.isFocusable()) {
            obtain.addAction(1);
        }
        obtain.setFocused(z);
        this.f676i.getLocationOnScreen(this.g);
        obtain.getBoundsInScreen(this.f675d);
        if (this.f675d.equals(rect)) {
            obtain.getBoundsInParent(this.f675d);
            if (obtain.mParentVirtualDescendantId != -1) {
                AccessibilityNodeInfoCompat obtain2 = AccessibilityNodeInfoCompat.obtain();
                for (int i3 = obtain.mParentVirtualDescendantId; i3 != -1; i3 = obtain2.mParentVirtualDescendantId) {
                    obtain2.setParent(this.f676i, -1);
                    obtain2.setBoundsInParent(n);
                    y(i3, obtain2);
                    obtain2.getBoundsInParent(this.e);
                    Rect rect2 = this.f675d;
                    Rect rect3 = this.e;
                    rect2.offset(rect3.left, rect3.top);
                }
                obtain2.recycle();
            }
            this.f675d.offset(this.g[0] - this.f676i.getScrollX(), this.g[1] - this.f676i.getScrollY());
        }
        if (this.f676i.getLocalVisibleRect(this.f)) {
            this.f.offset(this.g[0] - this.f676i.getScrollX(), this.g[1] - this.f676i.getScrollY());
            if (this.f675d.intersect(this.f)) {
                obtain.setBoundsInScreen(this.f675d);
                if (q(this.f675d)) {
                    obtain.setVisibleToUser(true);
                }
            }
        }
        return obtain;
    }

    @NonNull
    private AccessibilityNodeInfoCompat k() {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(this.f676i);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.f676i, obtain);
        ArrayList arrayList = new ArrayList();
        o(arrayList);
        if (obtain.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            obtain.addChild(this.f676i, ((Integer) arrayList.get(i2)).intValue());
        }
        return obtain;
    }

    private SparseArrayCompat<AccessibilityNodeInfoCompat> l() {
        ArrayList arrayList = new ArrayList();
        o(arrayList);
        SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat = new SparseArrayCompat<>();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            sparseArrayCompat.put(i2, j(i2));
        }
        return sparseArrayCompat;
    }

    private void m(int i2, Rect rect) {
        t(i2).getBoundsInParent(rect);
    }

    private static Rect p(@NonNull View view, int i2, @NonNull Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i2 == 17) {
            rect.set(width, 0, width, height);
        } else if (i2 == 33) {
            rect.set(0, height, width, height);
        } else if (i2 == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    private boolean q(Rect rect) {
        if (rect == null || rect.isEmpty() || this.f676i.getWindowVisibility() != 0) {
            return false;
        }
        View view = this.f676i;
        do {
            Object parent = view.getParent();
            if (!(parent instanceof View)) {
                return parent != null;
            }
            view = (View) parent;
            if (view.getAlpha() <= 0.0f) {
                break;
            }
        } while (view.getVisibility() == 0);
        return false;
    }

    private static int r(int i2) {
        if (i2 == 19) {
            return 33;
        }
        if (i2 != 21) {
            return i2 != 22 ? 130 : 66;
        }
        return 17;
    }

    private boolean s(int i2, @Nullable Rect rect) {
        Object d2;
        SparseArrayCompat<AccessibilityNodeInfoCompat> l = l();
        int i3 = this.l;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = i3 == Integer.MIN_VALUE ? null : l.get(i3);
        if (i2 == 1 || i2 == 2) {
            d2 = FocusStrategy.d(l, p, o, accessibilityNodeInfoCompat, i2, ViewCompat.getLayoutDirection(this.f676i) == 1, false);
        } else {
            if (i2 != 17 && i2 != 33 && i2 != 66 && i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i4 = this.l;
            if (i4 != Integer.MIN_VALUE) {
                m(i4, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                p(this.f676i, i2, rect2);
            }
            d2 = FocusStrategy.c(l, p, o, accessibilityNodeInfoCompat, rect2, i2);
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) d2;
        return requestKeyboardFocusForVirtualView(accessibilityNodeInfoCompat2 != null ? l.keyAt(l.indexOfValue(accessibilityNodeInfoCompat2)) : Integer.MIN_VALUE);
    }

    boolean A(int i2, int i3, Bundle bundle) {
        return i2 != -1 ? B(i2, i3, bundle) : C(i3, bundle);
    }

    public final boolean clearKeyboardFocusForVirtualView(int i2) {
        if (this.l != i2) {
            return false;
        }
        this.l = Integer.MIN_VALUE;
        z(i2, false);
        sendEventForVirtualView(i2, 8);
        return true;
    }

    public final boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        if (!this.h.isEnabled() || !this.h.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int n2 = n(motionEvent.getX(), motionEvent.getY());
            E(n2);
            return n2 != Integer.MIN_VALUE;
        }
        if (action != 10 || this.m == Integer.MIN_VALUE) {
            return false;
        }
        E(Integer.MIN_VALUE);
        return true;
    }

    public final boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        int i2 = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 61) {
            if (keyEvent.hasNoModifiers()) {
                return s(2, null);
            }
            if (keyEvent.hasModifiers(1)) {
                return s(1, null);
            }
            return false;
        }
        if (keyCode != 66) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    if (!keyEvent.hasNoModifiers()) {
                        return false;
                    }
                    int r = r(keyCode);
                    int repeatCount = keyEvent.getRepeatCount() + 1;
                    boolean z = false;
                    while (i2 < repeatCount && s(r, null)) {
                        i2++;
                        z = true;
                    }
                    return z;
                case 23:
                    break;
                default:
                    return false;
            }
        }
        if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
            return false;
        }
        f();
        return true;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.k;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.j == null) {
            this.j = new c();
        }
        return this.j;
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.l;
    }

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i2) {
        invalidateVirtualView(i2, 0);
    }

    public final void invalidateVirtualView(int i2, int i3) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.h.isEnabled() || (parent = this.f676i.getParent()) == null) {
            return;
        }
        AccessibilityEvent g = g(i2, 2048);
        AccessibilityEventCompat.setContentChangeTypes(g, i3);
        ViewParentCompat.requestSendAccessibilityEvent(parent, this.f676i, g);
    }

    protected abstract int n(float f, float f2);

    protected abstract void o(List<Integer> list);

    public final void onFocusChanged(boolean z, int i2, @Nullable Rect rect) {
        int i3 = this.l;
        if (i3 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i3);
        }
        if (z) {
            s(i2, rect);
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        v(accessibilityEvent);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        x(accessibilityNodeInfoCompat);
    }

    public final boolean requestKeyboardFocusForVirtualView(int i2) {
        int i3;
        if ((!this.f676i.isFocused() && !this.f676i.requestFocus()) || (i3 = this.l) == i2) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i3);
        }
        this.l = i2;
        z(i2, true);
        sendEventForVirtualView(i2, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int i2, int i3) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.h.isEnabled() || (parent = this.f676i.getParent()) == null) {
            return false;
        }
        return ViewParentCompat.requestSendAccessibilityEvent(parent, this.f676i, g(i2, i3));
    }

    @NonNull
    AccessibilityNodeInfoCompat t(int i2) {
        return i2 == -1 ? k() : j(i2);
    }

    protected abstract boolean u(int i2, int i3, @Nullable Bundle bundle);

    protected void v(@NonNull AccessibilityEvent accessibilityEvent) {
    }

    protected void w(int i2, @NonNull AccessibilityEvent accessibilityEvent) {
    }

    protected void x(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    protected abstract void y(int i2, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    protected void z(int i2, boolean z) {
    }
}
