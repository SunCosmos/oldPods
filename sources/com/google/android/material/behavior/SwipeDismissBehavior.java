package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;

/* loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    ViewDragHelper a;
    OnDismissListener b;

    /* renamed from: c */
    private boolean f1475c;
    private boolean e;

    /* renamed from: d */
    private float f1476d = 0.0f;
    int f = 2;
    float g = 0.5f;
    float h = 0.0f;

    /* renamed from: i */
    float f1477i = 0.5f;
    private final ViewDragHelper.Callback j = new a();

    /* loaded from: classes.dex */
    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i2);
    }

    /* loaded from: classes.dex */
    public class a extends ViewDragHelper.Callback {
        private int a;
        private int b = -1;

        a() {
        }

        private boolean a(@NonNull View view, float f) {
            if (f == 0.0f) {
                return Math.abs(view.getLeft() - this.a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.g);
            }
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            int i2 = SwipeDismissBehavior.this.f;
            if (i2 == 2) {
                return true;
            }
            if (i2 == 0) {
                if (z) {
                    if (f >= 0.0f) {
                        return false;
                    }
                } else if (f <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i2 != 1) {
                return false;
            }
            if (z) {
                if (f <= 0.0f) {
                    return false;
                }
            } else if (f >= 0.0f) {
                return false;
            }
            return true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x001c, code lost:
        
            r5 = r2.a;
            r3 = r3.getWidth() + r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0026, code lost:
        
            if (r5 != false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
        
            if (r5 != false) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x0012, code lost:
        
            r5 = r2.a - r3.getWidth();
            r3 = r2.a;
         */
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int clampViewPositionHorizontal(@androidx.annotation.NonNull android.view.View r3, int r4, int r5) {
            /*
                r2 = this;
                int r5 = androidx.core.view.ViewCompat.getLayoutDirection(r3)
                r0 = 1
                if (r5 != r0) goto L9
                r5 = 1
                goto La
            L9:
                r5 = 0
            La:
                com.google.android.material.behavior.SwipeDismissBehavior r1 = com.google.android.material.behavior.SwipeDismissBehavior.this
                int r1 = r1.f
                if (r1 != 0) goto L24
                if (r5 == 0) goto L1c
            L12:
                int r5 = r2.a
                int r3 = r3.getWidth()
                int r5 = r5 - r3
                int r3 = r2.a
                goto L37
            L1c:
                int r5 = r2.a
                int r3 = r3.getWidth()
                int r3 = r3 + r5
                goto L37
            L24:
                if (r1 != r0) goto L29
                if (r5 == 0) goto L12
                goto L1c
            L29:
                int r5 = r2.a
                int r0 = r3.getWidth()
                int r5 = r5 - r0
                int r0 = r2.a
                int r3 = r3.getWidth()
                int r3 = r3 + r0
            L37:
                int r3 = com.google.android.material.behavior.SwipeDismissBehavior.b(r5, r4, r3)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.a.clampViewPositionHorizontal(android.view.View, int, int):int");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(@NonNull View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(@NonNull View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewCaptured(@NonNull View view, int i2) {
            this.b = i2;
            this.a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            OnDismissListener onDismissListener = SwipeDismissBehavior.this.b;
            if (onDismissListener != null) {
                onDismissListener.onDragStateChanged(i2);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(@NonNull View view, int i2, int i3, int i4, int i5) {
            float width = this.a + (view.getWidth() * SwipeDismissBehavior.this.h);
            float width2 = this.a + (view.getWidth() * SwipeDismissBehavior.this.f1477i);
            float f = i2;
            if (f <= width) {
                view.setAlpha(1.0f);
            } else if (f >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.a(0.0f, 1.0f - SwipeDismissBehavior.d(width, width2, f), 1.0f));
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(@NonNull View view, float f, float f2) {
            int i2;
            boolean z;
            OnDismissListener onDismissListener;
            this.b = -1;
            int width = view.getWidth();
            if (a(view, f)) {
                int left = view.getLeft();
                int i3 = this.a;
                i2 = left < i3 ? i3 - width : i3 + width;
                z = true;
            } else {
                i2 = this.a;
                z = false;
            }
            if (SwipeDismissBehavior.this.a.settleCapturedViewAt(i2, view.getTop())) {
                ViewCompat.postOnAnimation(view, new c(view, z));
            } else {
                if (!z || (onDismissListener = SwipeDismissBehavior.this.b) == null) {
                    return;
                }
                onDismissListener.onDismiss(view);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            int i3 = this.b;
            return (i3 == -1 || i3 == i2) && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }
    }

    /* loaded from: classes.dex */
    public class b implements AccessibilityViewCommand {
        b() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
            boolean z = false;
            if (!SwipeDismissBehavior.this.canSwipeDismissView(view)) {
                return false;
            }
            boolean z2 = ViewCompat.getLayoutDirection(view) == 1;
            int i2 = SwipeDismissBehavior.this.f;
            if ((i2 == 0 && z2) || (i2 == 1 && !z2)) {
                z = true;
            }
            int width = view.getWidth();
            if (z) {
                width = -width;
            }
            ViewCompat.offsetLeftAndRight(view, width);
            view.setAlpha(0.0f);
            OnDismissListener onDismissListener = SwipeDismissBehavior.this.b;
            if (onDismissListener != null) {
                onDismissListener.onDismiss(view);
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    private class c implements Runnable {
        private final View a;
        private final boolean b;

        c(View view, boolean z) {
            this.a = view;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            OnDismissListener onDismissListener;
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.a;
            if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.a, this);
            } else {
                if (!this.b || (onDismissListener = SwipeDismissBehavior.this.b) == null) {
                    return;
                }
                onDismissListener.onDismiss(this.a);
            }
        }
    }

    static float a(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    static int b(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    private void c(ViewGroup viewGroup) {
        if (this.a == null) {
            this.a = this.e ? ViewDragHelper.create(viewGroup, this.f1476d, this.j) : ViewDragHelper.create(viewGroup, this.j);
        }
    }

    static float d(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    private void e(View view) {
        ViewCompat.removeAccessibilityAction(view, 1048576);
        if (canSwipeDismissView(view)) {
            ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, null, new b());
        }
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    public int getDragState() {
        ViewDragHelper viewDragHelper = this.a;
        if (viewDragHelper != null) {
            return viewDragHelper.getViewDragState();
        }
        return 0;
    }

    @Nullable
    @VisibleForTesting
    public OnDismissListener getListener() {
        return this.b;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        boolean z = this.f1475c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z = coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f1475c = z;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f1475c = false;
        }
        if (!z) {
            return false;
        }
        c(coordinatorLayout);
        return this.a.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i2) {
        boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, v, i2);
        if (ViewCompat.getImportantForAccessibility(v) == 0) {
            ViewCompat.setImportantForAccessibility(v, 1);
            e(v);
        }
        return onLayoutChild;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper = this.a;
        if (viewDragHelper == null) {
            return false;
        }
        viewDragHelper.processTouchEvent(motionEvent);
        return true;
    }

    public void setDragDismissDistance(float f) {
        this.g = a(0.0f, f, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.f1477i = a(0.0f, f, 1.0f);
    }

    public void setListener(@Nullable OnDismissListener onDismissListener) {
        this.b = onDismissListener;
    }

    public void setSensitivity(float f) {
        this.f1476d = f;
        this.e = true;
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.h = a(0.0f, f, 1.0f);
    }

    public void setSwipeDirection(int i2) {
        this.f = i2;
    }
}
