package android.support.v4.view;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: lib/Mus.dex */
public final class ViewParentCompat {
    static final ViewParentCompatBaseImpl IMPL;
    private static final String TAG = "ViewParentCompat";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public static class ViewParentCompatBaseImpl {
        ViewParentCompatBaseImpl() {
        }

        public boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i2) {
            if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onStartNestedScroll(view, view2, i2);
            }
            return false;
        }

        public void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i2) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedScrollAccepted(view, view2, i2);
            }
        }

        public void onStopNestedScroll(ViewParent viewParent, View view) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onStopNestedScroll(view);
            }
        }

        public void onNestedScroll(ViewParent viewParent, View view, int i2, int i3, int i4, int i5) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedScroll(view, i2, i3, i4, i5);
            }
        }

        public void onNestedPreScroll(ViewParent viewParent, View view, int i2, int i3, int[] iArr) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedPreScroll(view, i2, i3, iArr);
            }
        }

        public boolean onNestedFling(ViewParent viewParent, View view, float f, float f2, boolean z) {
            if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onNestedFling(view, f, f2, z);
            }
            return false;
        }

        public boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f2) {
            if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onNestedPreFling(view, f, f2);
            }
            return false;
        }

        public void notifySubtreeAccessibilityStateChanged(ViewParent viewParent, View view, View view2, int i2) {
        }
    }

    @RequiresApi(19)
    /* loaded from: lib/Mus.dex */
    static class ViewParentCompatApi19Impl extends ViewParentCompatBaseImpl {
        ViewParentCompatApi19Impl() {
        }

        @Override // android.support.v4.view.ViewParentCompat.ViewParentCompatBaseImpl
        public void notifySubtreeAccessibilityStateChanged(ViewParent viewParent, View view, View view2, int i2) {
            viewParent.notifySubtreeAccessibilityStateChanged(view, view2, i2);
        }
    }

    @RequiresApi(21)
    /* loaded from: lib/Mus.dex */
    static class ViewParentCompatApi21Impl extends ViewParentCompatApi19Impl {
        ViewParentCompatApi21Impl() {
        }

        @Override // android.support.v4.view.ViewParentCompat.ViewParentCompatBaseImpl
        public boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i2) {
            try {
                return viewParent.onStartNestedScroll(view, view2, i2);
            } catch (AbstractMethodError e) {
                Log.e(ViewParentCompat.TAG, "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e);
                return false;
            }
        }

        @Override // android.support.v4.view.ViewParentCompat.ViewParentCompatBaseImpl
        public void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i2) {
            try {
                viewParent.onNestedScrollAccepted(view, view2, i2);
            } catch (AbstractMethodError e) {
                Log.e(ViewParentCompat.TAG, "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e);
            }
        }

        @Override // android.support.v4.view.ViewParentCompat.ViewParentCompatBaseImpl
        public void onStopNestedScroll(ViewParent viewParent, View view) {
            try {
                viewParent.onStopNestedScroll(view);
            } catch (AbstractMethodError e) {
                Log.e(ViewParentCompat.TAG, "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e);
            }
        }

        @Override // android.support.v4.view.ViewParentCompat.ViewParentCompatBaseImpl
        public void onNestedScroll(ViewParent viewParent, View view, int i2, int i3, int i4, int i5) {
            try {
                viewParent.onNestedScroll(view, i2, i3, i4, i5);
            } catch (AbstractMethodError e) {
                Log.e(ViewParentCompat.TAG, "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e);
            }
        }

        @Override // android.support.v4.view.ViewParentCompat.ViewParentCompatBaseImpl
        public void onNestedPreScroll(ViewParent viewParent, View view, int i2, int i3, int[] iArr) {
            try {
                viewParent.onNestedPreScroll(view, i2, i3, iArr);
            } catch (AbstractMethodError e) {
                Log.e(ViewParentCompat.TAG, "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e);
            }
        }

        @Override // android.support.v4.view.ViewParentCompat.ViewParentCompatBaseImpl
        public boolean onNestedFling(ViewParent viewParent, View view, float f, float f2, boolean z) {
            try {
                return viewParent.onNestedFling(view, f, f2, z);
            } catch (AbstractMethodError e) {
                Log.e(ViewParentCompat.TAG, "ViewParent " + viewParent + " does not implement interface method onNestedFling", e);
                return false;
            }
        }

        @Override // android.support.v4.view.ViewParentCompat.ViewParentCompatBaseImpl
        public boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f2) {
            try {
                return viewParent.onNestedPreFling(view, f, f2);
            } catch (AbstractMethodError e) {
                Log.e(ViewParentCompat.TAG, "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e);
                return false;
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new ViewParentCompatApi21Impl();
        } else if (Build.VERSION.SDK_INT >= 19) {
            IMPL = new ViewParentCompatApi19Impl();
        } else {
            IMPL = new ViewParentCompatBaseImpl();
        }
    }

    private ViewParentCompat() {
    }

    @Deprecated
    public static boolean requestSendAccessibilityEvent(ViewParent viewParent, View view, AccessibilityEvent accessibilityEvent) {
        return viewParent.requestSendAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i2) {
        return onStartNestedScroll(viewParent, view, view2, i2, 0);
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i2) {
        onNestedScrollAccepted(viewParent, view, view2, i2, 0);
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view) {
        onStopNestedScroll(viewParent, view, 0);
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i2, int i3, int i4, int i5) {
        onNestedScroll(viewParent, view, i2, i3, i4, i5, 0);
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i2, int i3, int[] iArr) {
        onNestedPreScroll(viewParent, view, i2, i3, iArr, 0);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i2, int i3) {
        if (viewParent instanceof NestedScrollingParent2) {
            return ((NestedScrollingParent2) viewParent).onStartNestedScroll(view, view2, i2, i3);
        }
        if (i3 == 0) {
            return IMPL.onStartNestedScroll(viewParent, view, view2, i2);
        }
        return false;
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i2, int i3) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScrollAccepted(view, view2, i2, i3);
        } else if (i3 == 0) {
            IMPL.onNestedScrollAccepted(viewParent, view, view2, i2);
        }
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view, int i2) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onStopNestedScroll(view, i2);
        } else if (i2 == 0) {
            IMPL.onStopNestedScroll(viewParent, view);
        }
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i2, int i3, int i4, int i5, int i6) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScroll(view, i2, i3, i4, i5, i6);
        } else if (i6 == 0) {
            IMPL.onNestedScroll(viewParent, view, i2, i3, i4, i5);
        }
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i2, int i3, int[] iArr, int i4) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedPreScroll(view, i2, i3, iArr, i4);
        } else if (i4 == 0) {
            IMPL.onNestedPreScroll(viewParent, view, i2, i3, iArr);
        }
    }

    public static boolean onNestedFling(ViewParent viewParent, View view, float f, float f2, boolean z) {
        return IMPL.onNestedFling(viewParent, view, f, f2, z);
    }

    public static boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f2) {
        return IMPL.onNestedPreFling(viewParent, view, f, f2);
    }

    public static void notifySubtreeAccessibilityStateChanged(ViewParent viewParent, View view, View view2, int i2) {
        IMPL.notifySubtreeAccessibilityStateChanged(viewParent, view, view2, i2);
    }
}
