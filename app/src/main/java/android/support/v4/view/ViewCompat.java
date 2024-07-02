package android.support.v4.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.util.Log;
import android.view.Display;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: lib/Mus.dex */
public class ViewCompat {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    static final ViewCompatBaseImpl IMPL;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;

    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;

    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;

    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;

    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;

    @Deprecated
    public static final int MEASURED_SIZE_MASK = 16777215;

    @Deprecated
    public static final int MEASURED_STATE_MASK = -16777216;

    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;

    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;

    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;

    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    private static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface AccessibilityLiveRegion {
    }

    @Retention(RetentionPolicy.SOURCE)
    @TargetApi(26)
    /* loaded from: lib/Mus.dex */
    private @interface AutofillImportance {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface FocusDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface FocusRealDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface FocusRelativeDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface ImportantForAccessibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface LayerType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface LayoutDirectionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface NestedScrollType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface OverScroll {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface ResolvedLayoutDirectionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface ScrollAxis {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface ScrollIndicators {
    }

    /* loaded from: lib/Mus.dex */
    public static class ViewCompatBaseImpl {
        static Field sAccessibilityDelegateField;
        private static Method sChildrenDrawingOrderMethod;
        private static Field sMinHeightField;
        private static boolean sMinHeightFieldFetched;
        private static Field sMinWidthField;
        private static boolean sMinWidthFieldFetched;
        private static WeakHashMap<View, String> sTransitionNameMap;
        private Method mDispatchFinishTemporaryDetach;
        private Method mDispatchStartTemporaryDetach;
        private boolean mTempDetachBound;
        WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap = null;
        private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
        static boolean sAccessibilityDelegateCheckFailed = false;

        ViewCompatBaseImpl() {
        }

        public void setAutofillHints(@NonNull View view, @Nullable String... strArr) {
        }

        public void setAccessibilityDelegate(View view, @Nullable AccessibilityDelegateCompat accessibilityDelegateCompat) {
            view.setAccessibilityDelegate(accessibilityDelegateCompat == null ? null : accessibilityDelegateCompat.getBridge());
        }

        public boolean hasAccessibilityDelegate(View view) {
            if (sAccessibilityDelegateCheckFailed) {
                return false;
            }
            if (sAccessibilityDelegateField == null) {
                try {
                    sAccessibilityDelegateField = View.class.getDeclaredField("mAccessibilityDelegate");
                    sAccessibilityDelegateField.setAccessible(true);
                } catch (Throwable th) {
                    sAccessibilityDelegateCheckFailed = true;
                    return false;
                }
            }
            try {
                return sAccessibilityDelegateField.get(view) != null;
            } catch (Throwable th2) {
                sAccessibilityDelegateCheckFailed = true;
                return false;
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat.unwrap());
        }

        public boolean startDragAndDrop(View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i2) {
            return view.startDrag(clipData, dragShadowBuilder, obj, i2);
        }

        public void cancelDragAndDrop(View view) {
        }

        public void updateDragShadow(View view, View.DragShadowBuilder dragShadowBuilder) {
        }

        public boolean hasTransientState(View view) {
            return false;
        }

        public void setHasTransientState(View view, boolean z) {
        }

        public void postInvalidateOnAnimation(View view) {
            view.postInvalidate();
        }

        public void postInvalidateOnAnimation(View view, int i2, int i3, int i4, int i5) {
            view.postInvalidate(i2, i3, i4, i5);
        }

        public void postOnAnimation(View view, Runnable runnable) {
            view.postDelayed(runnable, getFrameTime());
        }

        public void postOnAnimationDelayed(View view, Runnable runnable, long j) {
            view.postDelayed(runnable, getFrameTime() + j);
        }

        long getFrameTime() {
            return ValueAnimator.getFrameDelay();
        }

        public int getImportantForAccessibility(View view) {
            return 0;
        }

        public void setImportantForAccessibility(View view, int i2) {
        }

        public boolean isImportantForAccessibility(View view) {
            return true;
        }

        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            return false;
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
            return null;
        }

        public int getLabelFor(View view) {
            return 0;
        }

        public void setLabelFor(View view, int i2) {
        }

        public void setLayerPaint(View view, Paint paint) {
            view.setLayerType(view.getLayerType(), paint);
            view.invalidate();
        }

        public int getLayoutDirection(View view) {
            return 0;
        }

        public void setLayoutDirection(View view, int i2) {
        }

        public ViewParent getParentForAccessibility(View view) {
            return view.getParent();
        }

        public int getAccessibilityLiveRegion(View view) {
            return 0;
        }

        public void setAccessibilityLiveRegion(View view, int i2) {
        }

        public int getPaddingStart(View view) {
            return view.getPaddingLeft();
        }

        public int getPaddingEnd(View view) {
            return view.getPaddingRight();
        }

        public void setPaddingRelative(View view, int i2, int i3, int i4, int i5) {
            view.setPadding(i2, i3, i4, i5);
        }

        public void dispatchStartTemporaryDetach(View view) {
            if (!this.mTempDetachBound) {
                bindTempDetach();
            }
            if (this.mDispatchStartTemporaryDetach != null) {
                try {
                    this.mDispatchStartTemporaryDetach.invoke(view, new Object[0]);
                    return;
                } catch (Exception e) {
                    Log.d(ViewCompat.TAG, "Error calling dispatchStartTemporaryDetach", e);
                    return;
                }
            }
            view.onStartTemporaryDetach();
        }

        public void dispatchFinishTemporaryDetach(View view) {
            if (!this.mTempDetachBound) {
                bindTempDetach();
            }
            if (this.mDispatchFinishTemporaryDetach != null) {
                try {
                    this.mDispatchFinishTemporaryDetach.invoke(view, new Object[0]);
                    return;
                } catch (Exception e) {
                    Log.d(ViewCompat.TAG, "Error calling dispatchFinishTemporaryDetach", e);
                    return;
                }
            }
            view.onFinishTemporaryDetach();
        }

        public boolean hasOverlappingRendering(View view) {
            return true;
        }

        private void bindTempDetach() {
            try {
                this.mDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
                this.mDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
            } catch (NoSuchMethodException e) {
                Log.e(ViewCompat.TAG, "Couldn't find method", e);
            }
            this.mTempDetachBound = true;
        }

        public int getMinimumWidth(View view) {
            if (!sMinWidthFieldFetched) {
                try {
                    sMinWidthField = View.class.getDeclaredField("mMinWidth");
                    sMinWidthField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                }
                sMinWidthFieldFetched = true;
            }
            if (sMinWidthField != null) {
                try {
                    return ((Integer) sMinWidthField.get(view)).intValue();
                } catch (Exception e2) {
                }
            }
            return 0;
        }

        public int getMinimumHeight(View view) {
            if (!sMinHeightFieldFetched) {
                try {
                    sMinHeightField = View.class.getDeclaredField("mMinHeight");
                    sMinHeightField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                }
                sMinHeightFieldFetched = true;
            }
            if (sMinHeightField != null) {
                try {
                    return ((Integer) sMinHeightField.get(view)).intValue();
                } catch (Exception e2) {
                }
            }
            return 0;
        }

        public ViewPropertyAnimatorCompat animate(View view) {
            if (this.mViewPropertyAnimatorCompatMap == null) {
                this.mViewPropertyAnimatorCompatMap = new WeakHashMap<>();
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mViewPropertyAnimatorCompatMap.get(view);
            if (viewPropertyAnimatorCompat == null) {
                viewPropertyAnimatorCompat = new ViewPropertyAnimatorCompat(view);
                this.mViewPropertyAnimatorCompatMap.put(view, viewPropertyAnimatorCompat);
            }
            return viewPropertyAnimatorCompat;
        }

        public void setTransitionName(View view, String str) {
            if (sTransitionNameMap == null) {
                sTransitionNameMap = new WeakHashMap<>();
            }
            sTransitionNameMap.put(view, str);
        }

        public String getTransitionName(View view) {
            if (sTransitionNameMap == null) {
                return null;
            }
            return sTransitionNameMap.get(view);
        }

        public int getWindowSystemUiVisibility(View view) {
            return 0;
        }

        public void requestApplyInsets(View view) {
        }

        public void setElevation(View view, float f) {
        }

        public float getElevation(View view) {
            return 0.0f;
        }

        public void setTranslationZ(View view, float f) {
        }

        public float getTranslationZ(View view) {
            return 0.0f;
        }

        public void setClipBounds(View view, Rect rect) {
        }

        public Rect getClipBounds(View view) {
            return null;
        }

        public void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean z) {
            if (sChildrenDrawingOrderMethod == null) {
                try {
                    sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
                } catch (NoSuchMethodException e) {
                    Log.e(ViewCompat.TAG, "Unable to find childrenDrawingOrderEnabled", e);
                }
                sChildrenDrawingOrderMethod.setAccessible(true);
            }
            try {
                sChildrenDrawingOrderMethod.invoke(viewGroup, Boolean.valueOf(z));
            } catch (IllegalAccessException e2) {
                Log.e(ViewCompat.TAG, "Unable to invoke childrenDrawingOrderEnabled", e2);
            } catch (IllegalArgumentException e3) {
                Log.e(ViewCompat.TAG, "Unable to invoke childrenDrawingOrderEnabled", e3);
            } catch (InvocationTargetException e4) {
                Log.e(ViewCompat.TAG, "Unable to invoke childrenDrawingOrderEnabled", e4);
            }
        }

        public boolean getFitsSystemWindows(View view) {
            return false;
        }

        public void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        }

        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        public WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        public boolean isPaddingRelative(View view) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setNestedScrollingEnabled(View view, boolean z) {
            if (view instanceof NestedScrollingChild) {
                ((NestedScrollingChild) view).setNestedScrollingEnabled(z);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean isNestedScrollingEnabled(View view) {
            if (view instanceof NestedScrollingChild) {
                return ((NestedScrollingChild) view).isNestedScrollingEnabled();
            }
            return false;
        }

        public void setBackground(View view, Drawable drawable) {
            view.setBackgroundDrawable(drawable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ColorStateList getBackgroundTintList(View view) {
            return view instanceof TintableBackgroundView ? ((TintableBackgroundView) view).getSupportBackgroundTintList() : null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setBackgroundTintList(View view, ColorStateList colorStateList) {
            if (view instanceof TintableBackgroundView) {
                ((TintableBackgroundView) view).setSupportBackgroundTintList(colorStateList);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
            if (view instanceof TintableBackgroundView) {
                ((TintableBackgroundView) view).setSupportBackgroundTintMode(mode);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public PorterDuff.Mode getBackgroundTintMode(View view) {
            return view instanceof TintableBackgroundView ? ((TintableBackgroundView) view).getSupportBackgroundTintMode() : null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean startNestedScroll(View view, int i2) {
            if (view instanceof NestedScrollingChild) {
                return ((NestedScrollingChild) view).startNestedScroll(i2);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void stopNestedScroll(View view) {
            if (view instanceof NestedScrollingChild) {
                ((NestedScrollingChild) view).stopNestedScroll();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean hasNestedScrollingParent(View view) {
            if (view instanceof NestedScrollingChild) {
                return ((NestedScrollingChild) view).hasNestedScrollingParent();
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean dispatchNestedScroll(View view, int i2, int i3, int i4, int i5, int[] iArr) {
            if (view instanceof NestedScrollingChild) {
                return ((NestedScrollingChild) view).dispatchNestedScroll(i2, i3, i4, i5, iArr);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean dispatchNestedPreScroll(View view, int i2, int i3, int[] iArr, int[] iArr2) {
            if (view instanceof NestedScrollingChild) {
                return ((NestedScrollingChild) view).dispatchNestedPreScroll(i2, i3, iArr, iArr2);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean dispatchNestedFling(View view, float f, float f2, boolean z) {
            if (view instanceof NestedScrollingChild) {
                return ((NestedScrollingChild) view).dispatchNestedFling(f, f2, z);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean dispatchNestedPreFling(View view, float f, float f2) {
            if (view instanceof NestedScrollingChild) {
                return ((NestedScrollingChild) view).dispatchNestedPreFling(f, f2);
            }
            return false;
        }

        public boolean isInLayout(View view) {
            return false;
        }

        public boolean isLaidOut(View view) {
            return view.getWidth() > 0 && view.getHeight() > 0;
        }

        public boolean isLayoutDirectionResolved(View view) {
            return false;
        }

        public float getZ(View view) {
            return getTranslationZ(view) + getElevation(view);
        }

        public void setZ(View view, float f) {
        }

        public boolean isAttachedToWindow(View view) {
            return view.getWindowToken() != null;
        }

        public boolean hasOnClickListeners(View view) {
            return false;
        }

        public int getScrollIndicators(View view) {
            return 0;
        }

        public void setScrollIndicators(View view, int i2) {
        }

        public void setScrollIndicators(View view, int i2, int i3) {
        }

        public void offsetLeftAndRight(View view, int i2) {
            view.offsetLeftAndRight(i2);
            if (view.getVisibility() == 0) {
                tickleInvalidationFlag(view);
                Object parent = view.getParent();
                if (parent instanceof View) {
                    tickleInvalidationFlag((View) parent);
                }
            }
        }

        public void offsetTopAndBottom(View view, int i2) {
            view.offsetTopAndBottom(i2);
            if (view.getVisibility() == 0) {
                tickleInvalidationFlag(view);
                Object parent = view.getParent();
                if (parent instanceof View) {
                    tickleInvalidationFlag((View) parent);
                }
            }
        }

        private static void tickleInvalidationFlag(View view) {
            float translationY = view.getTranslationY();
            view.setTranslationY(translationY + 1.0f);
            view.setTranslationY(translationY);
        }

        public void setPointerIcon(View view, PointerIconCompat pointerIconCompat) {
        }

        public Display getDisplay(View view) {
            if (isAttachedToWindow(view)) {
                return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
            }
            return null;
        }

        public void setTooltipText(View view, CharSequence charSequence) {
        }

        public int getNextClusterForwardId(@NonNull View view) {
            return -1;
        }

        public void setNextClusterForwardId(@NonNull View view, int i2) {
        }

        public boolean isKeyboardNavigationCluster(@NonNull View view) {
            return false;
        }

        public void setKeyboardNavigationCluster(@NonNull View view, boolean z) {
        }

        public boolean isFocusedByDefault(@NonNull View view) {
            return false;
        }

        public void setFocusedByDefault(@NonNull View view, boolean z) {
        }

        public View keyboardNavigationClusterSearch(@NonNull View view, View view2, int i2) {
            return null;
        }

        public void addKeyboardNavigationClusters(@NonNull View view, @NonNull Collection<View> collection, int i2) {
        }

        public boolean restoreDefaultFocus(@NonNull View view) {
            return view.requestFocus();
        }

        public boolean hasExplicitFocusable(@NonNull View view) {
            return view.hasFocusable();
        }

        @TargetApi(26)
        public int getImportantForAutofill(@NonNull View view) {
            return 0;
        }

        public void setImportantForAutofill(@NonNull View view, int i2) {
        }

        public boolean isImportantForAutofill(@NonNull View view) {
            return true;
        }

        public int generateViewId() {
            int i2;
            int i3;
            do {
                i2 = sNextGeneratedId.get();
                i3 = i2 + 1;
                if (i3 > 16777215) {
                    i3 = 1;
                }
            } while (!sNextGeneratedId.compareAndSet(i2, i3));
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(15)
    /* loaded from: lib/Mus.dex */
    public static class ViewCompatApi15Impl extends ViewCompatBaseImpl {
        ViewCompatApi15Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean hasOnClickListeners(View view) {
            return view.hasOnClickListeners();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(16)
    /* loaded from: lib/Mus.dex */
    public static class ViewCompatApi16Impl extends ViewCompatApi15Impl {
        ViewCompatApi16Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean hasTransientState(View view) {
            return view.hasTransientState();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setHasTransientState(View view, boolean z) {
            view.setHasTransientState(z);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void postInvalidateOnAnimation(View view) {
            view.postInvalidateOnAnimation();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void postInvalidateOnAnimation(View view, int i2, int i3, int i4, int i5) {
            view.postInvalidateOnAnimation(i2, i3, i4, i5);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void postOnAnimation(View view, Runnable runnable) {
            view.postOnAnimation(runnable);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void postOnAnimationDelayed(View view, Runnable runnable, long j) {
            view.postOnAnimationDelayed(runnable, j);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getImportantForAccessibility(View view) {
            return view.getImportantForAccessibility();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setImportantForAccessibility(View view, int i2) {
            int i3 = i2;
            if (i3 == 4) {
                i3 = 2;
            }
            view.setImportantForAccessibility(i3);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            return view.performAccessibilityAction(i2, bundle);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
            AccessibilityNodeProvider accessibilityNodeProvider = view.getAccessibilityNodeProvider();
            if (accessibilityNodeProvider != null) {
                return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
            }
            return null;
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public ViewParent getParentForAccessibility(View view) {
            return view.getParentForAccessibility();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getMinimumWidth(View view) {
            return view.getMinimumWidth();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getMinimumHeight(View view) {
            return view.getMinimumHeight();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void requestApplyInsets(View view) {
            view.requestFitSystemWindows();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean getFitsSystemWindows(View view) {
            return view.getFitsSystemWindows();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean hasOverlappingRendering(View view) {
            return view.hasOverlappingRendering();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setBackground(View view, Drawable drawable) {
            view.setBackground(drawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(17)
    /* loaded from: lib/Mus.dex */
    public static class ViewCompatApi17Impl extends ViewCompatApi16Impl {
        ViewCompatApi17Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getLabelFor(View view) {
            return view.getLabelFor();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setLabelFor(View view, int i2) {
            view.setLabelFor(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setLayerPaint(View view, Paint paint) {
            view.setLayerPaint(paint);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getLayoutDirection(View view) {
            return view.getLayoutDirection();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setLayoutDirection(View view, int i2) {
            view.setLayoutDirection(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getPaddingStart(View view) {
            return view.getPaddingStart();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getPaddingEnd(View view) {
            return view.getPaddingEnd();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setPaddingRelative(View view, int i2, int i3, int i4, int i5) {
            view.setPaddingRelative(i2, i3, i4, i5);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getWindowSystemUiVisibility(View view) {
            return view.getWindowSystemUiVisibility();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isPaddingRelative(View view) {
            return view.isPaddingRelative();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public Display getDisplay(View view) {
            return view.getDisplay();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int generateViewId() {
            return View.generateViewId();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(18)
    /* loaded from: lib/Mus.dex */
    public static class ViewCompatApi18Impl extends ViewCompatApi17Impl {
        ViewCompatApi18Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setClipBounds(View view, Rect rect) {
            view.setClipBounds(rect);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public Rect getClipBounds(View view) {
            return view.getClipBounds();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isInLayout(View view) {
            return view.isInLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(19)
    /* loaded from: lib/Mus.dex */
    public static class ViewCompatApi19Impl extends ViewCompatApi18Impl {
        ViewCompatApi19Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getAccessibilityLiveRegion(View view) {
            return view.getAccessibilityLiveRegion();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setAccessibilityLiveRegion(View view, int i2) {
            view.setAccessibilityLiveRegion(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatApi16Impl, android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setImportantForAccessibility(View view, int i2) {
            view.setImportantForAccessibility(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isLaidOut(View view) {
            return view.isLaidOut();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isLayoutDirectionResolved(View view) {
            return view.isLayoutDirectionResolved();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isAttachedToWindow(View view) {
            return view.isAttachedToWindow();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(21)
    /* loaded from: lib/Mus.dex */
    public static class ViewCompatApi21Impl extends ViewCompatApi19Impl {
        private static ThreadLocal<Rect> sThreadLocalRect;

        ViewCompatApi21Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setTransitionName(View view, String str) {
            view.setTransitionName(str);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public String getTransitionName(View view) {
            return view.getTransitionName();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatApi16Impl, android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void requestApplyInsets(View view) {
            view.requestApplyInsets();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setElevation(View view, float f) {
            view.setElevation(f);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public float getElevation(View view) {
            return view.getElevation();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setTranslationZ(View view, float f) {
            view.setTranslationZ(f);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public float getTranslationZ(View view) {
            return view.getTranslationZ();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            if (onApplyWindowInsetsListener == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: android.support.v4.view.ViewCompat.ViewCompatApi21Impl.1
                    final /* synthetic */ OnApplyWindowInsetsListener val$listener;

                    AnonymousClass1(OnApplyWindowInsetsListener onApplyWindowInsetsListener2) {
                        onApplyWindowInsetsListener = onApplyWindowInsetsListener2;
                    }

                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                        return (WindowInsets) WindowInsetsCompat.unwrap(onApplyWindowInsetsListener.onApplyWindowInsets(view2, WindowInsetsCompat.wrap(windowInsets)));
                    }
                });
            }
        }

        /* renamed from: android.support.v4.view.ViewCompat$ViewCompatApi21Impl$1 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass1 implements View.OnApplyWindowInsetsListener {
            final /* synthetic */ OnApplyWindowInsetsListener val$listener;

            AnonymousClass1(OnApplyWindowInsetsListener onApplyWindowInsetsListener2) {
                onApplyWindowInsetsListener = onApplyWindowInsetsListener2;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                return (WindowInsets) WindowInsetsCompat.unwrap(onApplyWindowInsetsListener.onApplyWindowInsets(view2, WindowInsetsCompat.wrap(windowInsets)));
            }
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setNestedScrollingEnabled(View view, boolean z) {
            view.setNestedScrollingEnabled(z);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isNestedScrollingEnabled(View view) {
            return view.isNestedScrollingEnabled();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean startNestedScroll(View view, int i2) {
            return view.startNestedScroll(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void stopNestedScroll(View view) {
            view.stopNestedScroll();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean hasNestedScrollingParent(View view) {
            return view.hasNestedScrollingParent();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean dispatchNestedScroll(View view, int i2, int i3, int i4, int i5, int[] iArr) {
            return view.dispatchNestedScroll(i2, i3, i4, i5, iArr);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean dispatchNestedPreScroll(View view, int i2, int i3, int[] iArr, int[] iArr2) {
            return view.dispatchNestedPreScroll(i2, i3, iArr, iArr2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean dispatchNestedFling(View view, float f, float f2, boolean z) {
            return view.dispatchNestedFling(f, f2, z);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean dispatchNestedPreFling(View view, float f, float f2) {
            return view.dispatchNestedPreFling(f, f2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isImportantForAccessibility(View view) {
            return view.isImportantForAccessibility();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public ColorStateList getBackgroundTintList(View view) {
            return view.getBackgroundTintList();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setBackgroundTintList(View view, ColorStateList colorStateList) {
            view.setBackgroundTintList(colorStateList);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && z) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
            view.setBackgroundTintMode(mode);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean z = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && z) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public PorterDuff.Mode getBackgroundTintMode(View view) {
            return view.getBackgroundTintMode();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            WindowInsets windowInsets = (WindowInsets) WindowInsetsCompat.unwrap(windowInsetsCompat);
            WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
            if (onApplyWindowInsets != windowInsets) {
                windowInsets = new WindowInsets(onApplyWindowInsets);
            }
            return WindowInsetsCompat.wrap(windowInsets);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            WindowInsets windowInsets = (WindowInsets) WindowInsetsCompat.unwrap(windowInsetsCompat);
            WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsets);
            if (dispatchApplyWindowInsets != windowInsets) {
                windowInsets = new WindowInsets(dispatchApplyWindowInsets);
            }
            return WindowInsetsCompat.wrap(windowInsets);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public float getZ(View view) {
            return view.getZ();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setZ(View view, float f) {
            view.setZ(f);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void offsetLeftAndRight(View view, int i2) {
            Rect emptyTempRect = getEmptyTempRect();
            boolean z = false;
            Object parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z = !emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            super.offsetLeftAndRight(view, i2);
            if (z && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(emptyTempRect);
            }
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void offsetTopAndBottom(View view, int i2) {
            Rect emptyTempRect = getEmptyTempRect();
            boolean z = false;
            Object parent = view.getParent();
            if (parent instanceof View) {
                View view2 = (View) parent;
                emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
                z = !emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            super.offsetTopAndBottom(view, i2);
            if (z && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(emptyTempRect);
            }
        }

        private static Rect getEmptyTempRect() {
            if (sThreadLocalRect == null) {
                sThreadLocalRect = new ThreadLocal<>();
            }
            Rect rect = sThreadLocalRect.get();
            if (rect == null) {
                rect = new Rect();
                sThreadLocalRect.set(rect);
            }
            rect.setEmpty();
            return rect;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(23)
    /* loaded from: lib/Mus.dex */
    public static class ViewCompatApi23Impl extends ViewCompatApi21Impl {
        ViewCompatApi23Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setScrollIndicators(View view, int i2) {
            view.setScrollIndicators(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setScrollIndicators(View view, int i2, int i3) {
            view.setScrollIndicators(i2, i3);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getScrollIndicators(View view) {
            return view.getScrollIndicators();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatApi21Impl, android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void offsetLeftAndRight(View view, int i2) {
            view.offsetLeftAndRight(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatApi21Impl, android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void offsetTopAndBottom(View view, int i2) {
            view.offsetTopAndBottom(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(24)
    /* loaded from: lib/Mus.dex */
    public static class ViewCompatApi24Impl extends ViewCompatApi23Impl {
        ViewCompatApi24Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void dispatchStartTemporaryDetach(View view) {
            view.dispatchStartTemporaryDetach();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void dispatchFinishTemporaryDetach(View view) {
            view.dispatchFinishTemporaryDetach();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setPointerIcon(View view, PointerIconCompat pointerIconCompat) {
            view.setPointerIcon((PointerIcon) (pointerIconCompat != null ? pointerIconCompat.getPointerIcon() : null));
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean startDragAndDrop(View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i2) {
            return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void cancelDragAndDrop(View view) {
            view.cancelDragAndDrop();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void updateDragShadow(View view, View.DragShadowBuilder dragShadowBuilder) {
            view.updateDragShadow(dragShadowBuilder);
        }
    }

    @RequiresApi(26)
    /* loaded from: lib/Mus.dex */
    static class ViewCompatApi26Impl extends ViewCompatApi24Impl {
        ViewCompatApi26Impl() {
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setAutofillHints(@NonNull View view, @Nullable String... strArr) {
            view.setAutofillHints(strArr);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getImportantForAutofill(@NonNull View view) {
            return view.getImportantForAutofill();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setImportantForAutofill(@NonNull View view, int i2) {
            view.setImportantForAutofill(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isImportantForAutofill(@NonNull View view) {
            return view.isImportantForAutofill();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setTooltipText(View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public int getNextClusterForwardId(@NonNull View view) {
            return view.getNextClusterForwardId();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setNextClusterForwardId(@NonNull View view, int i2) {
            view.setNextClusterForwardId(i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isKeyboardNavigationCluster(@NonNull View view) {
            return view.isKeyboardNavigationCluster();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setKeyboardNavigationCluster(@NonNull View view, boolean z) {
            view.setKeyboardNavigationCluster(z);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean isFocusedByDefault(@NonNull View view) {
            return view.isFocusedByDefault();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void setFocusedByDefault(@NonNull View view, boolean z) {
            view.setFocusedByDefault(z);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public View keyboardNavigationClusterSearch(@NonNull View view, View view2, int i2) {
            return view.keyboardNavigationClusterSearch(view2, i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public void addKeyboardNavigationClusters(@NonNull View view, @NonNull Collection<View> collection, int i2) {
            view.addKeyboardNavigationClusters(collection, i2);
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean restoreDefaultFocus(@NonNull View view) {
            return view.restoreDefaultFocus();
        }

        @Override // android.support.v4.view.ViewCompat.ViewCompatBaseImpl
        public boolean hasExplicitFocusable(@NonNull View view) {
            return view.hasExplicitFocusable();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            IMPL = new ViewCompatApi26Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            IMPL = new ViewCompatApi24Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            IMPL = new ViewCompatApi23Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new ViewCompatApi21Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            IMPL = new ViewCompatApi19Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            IMPL = new ViewCompatApi18Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            IMPL = new ViewCompatApi17Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            IMPL = new ViewCompatApi16Impl();
        } else if (Build.VERSION.SDK_INT >= 15) {
            IMPL = new ViewCompatApi15Impl();
        } else {
            IMPL = new ViewCompatBaseImpl();
        }
    }

    @Deprecated
    public static boolean canScrollHorizontally(View view, int i2) {
        return view.canScrollHorizontally(i2);
    }

    @Deprecated
    public static boolean canScrollVertically(View view, int i2) {
        return view.canScrollVertically(i2);
    }

    @Deprecated
    public static int getOverScrollMode(View view) {
        return view.getOverScrollMode();
    }

    @Deprecated
    public static void setOverScrollMode(View view, int i2) {
        view.setOverScrollMode(i2);
    }

    @Deprecated
    public static void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        view.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Deprecated
    public static void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        view.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public static void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        IMPL.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
    }

    public static void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        IMPL.setAccessibilityDelegate(view, accessibilityDelegateCompat);
    }

    public static void setAutofillHints(@NonNull View view, @Nullable String... strArr) {
        IMPL.setAutofillHints(view, strArr);
    }

    public static int getImportantForAutofill(@NonNull View view) {
        return IMPL.getImportantForAutofill(view);
    }

    public static void setImportantForAutofill(@NonNull View view, int i2) {
        IMPL.setImportantForAutofill(view, i2);
    }

    public static boolean isImportantForAutofill(@NonNull View view) {
        return IMPL.isImportantForAutofill(view);
    }

    public static boolean hasAccessibilityDelegate(View view) {
        return IMPL.hasAccessibilityDelegate(view);
    }

    public static boolean hasTransientState(View view) {
        return IMPL.hasTransientState(view);
    }

    public static void setHasTransientState(View view, boolean z) {
        IMPL.setHasTransientState(view, z);
    }

    public static void postInvalidateOnAnimation(View view) {
        IMPL.postInvalidateOnAnimation(view);
    }

    public static void postInvalidateOnAnimation(View view, int i2, int i3, int i4, int i5) {
        IMPL.postInvalidateOnAnimation(view, i2, i3, i4, i5);
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        IMPL.postOnAnimation(view, runnable);
    }

    public static void postOnAnimationDelayed(View view, Runnable runnable, long j) {
        IMPL.postOnAnimationDelayed(view, runnable, j);
    }

    public static int getImportantForAccessibility(View view) {
        return IMPL.getImportantForAccessibility(view);
    }

    public static void setImportantForAccessibility(View view, int i2) {
        IMPL.setImportantForAccessibility(view, i2);
    }

    public static boolean isImportantForAccessibility(View view) {
        return IMPL.isImportantForAccessibility(view);
    }

    public static boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
        return IMPL.performAccessibilityAction(view, i2, bundle);
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        return IMPL.getAccessibilityNodeProvider(view);
    }

    @Deprecated
    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    @Deprecated
    public static void setLayerType(View view, int i2, Paint paint) {
        view.setLayerType(i2, paint);
    }

    @Deprecated
    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    public static int getLabelFor(View view) {
        return IMPL.getLabelFor(view);
    }

    public static void setLabelFor(View view, @IdRes int i2) {
        IMPL.setLabelFor(view, i2);
    }

    public static void setLayerPaint(View view, Paint paint) {
        IMPL.setLayerPaint(view, paint);
    }

    public static int getLayoutDirection(View view) {
        return IMPL.getLayoutDirection(view);
    }

    public static void setLayoutDirection(View view, int i2) {
        IMPL.setLayoutDirection(view, i2);
    }

    public static ViewParent getParentForAccessibility(View view) {
        return IMPL.getParentForAccessibility(view);
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull View view, @IdRes int i2) {
        T t = (T) view.findViewById(i2);
        if (t == null) {
            throw new IllegalArgumentException("ID does not reference a View inside this View");
        }
        return t;
    }

    @Deprecated
    public static boolean isOpaque(View view) {
        return view.isOpaque();
    }

    @Deprecated
    public static int resolveSizeAndState(int i2, int i3, int i4) {
        return View.resolveSizeAndState(i2, i3, i4);
    }

    @Deprecated
    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    @Deprecated
    public static int getMeasuredHeightAndState(View view) {
        return view.getMeasuredHeightAndState();
    }

    @Deprecated
    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    @Deprecated
    public static int combineMeasuredStates(int i2, int i3) {
        return View.combineMeasuredStates(i2, i3);
    }

    public static int getAccessibilityLiveRegion(View view) {
        return IMPL.getAccessibilityLiveRegion(view);
    }

    public static void setAccessibilityLiveRegion(View view, int i2) {
        IMPL.setAccessibilityLiveRegion(view, i2);
    }

    public static int getPaddingStart(View view) {
        return IMPL.getPaddingStart(view);
    }

    public static int getPaddingEnd(View view) {
        return IMPL.getPaddingEnd(view);
    }

    public static void setPaddingRelative(View view, int i2, int i3, int i4, int i5) {
        IMPL.setPaddingRelative(view, i2, i3, i4, i5);
    }

    public static void dispatchStartTemporaryDetach(View view) {
        IMPL.dispatchStartTemporaryDetach(view);
    }

    public static void dispatchFinishTemporaryDetach(View view) {
        IMPL.dispatchFinishTemporaryDetach(view);
    }

    @Deprecated
    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    @Nullable
    @Deprecated
    public static Matrix getMatrix(View view) {
        return view.getMatrix();
    }

    public static int getMinimumWidth(View view) {
        return IMPL.getMinimumWidth(view);
    }

    public static int getMinimumHeight(View view) {
        return IMPL.getMinimumHeight(view);
    }

    public static ViewPropertyAnimatorCompat animate(View view) {
        return IMPL.animate(view);
    }

    @Deprecated
    public static void setTranslationX(View view, float f) {
        view.setTranslationX(f);
    }

    @Deprecated
    public static void setTranslationY(View view, float f) {
        view.setTranslationY(f);
    }

    @Deprecated
    public static void setAlpha(View view, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        view.setAlpha(f);
    }

    @Deprecated
    public static void setX(View view, float f) {
        view.setX(f);
    }

    @Deprecated
    public static void setY(View view, float f) {
        view.setY(f);
    }

    @Deprecated
    public static void setRotation(View view, float f) {
        view.setRotation(f);
    }

    @Deprecated
    public static void setRotationX(View view, float f) {
        view.setRotationX(f);
    }

    @Deprecated
    public static void setRotationY(View view, float f) {
        view.setRotationY(f);
    }

    @Deprecated
    public static void setScaleX(View view, float f) {
        view.setScaleX(f);
    }

    @Deprecated
    public static void setScaleY(View view, float f) {
        view.setScaleY(f);
    }

    @Deprecated
    public static float getPivotX(View view) {
        return view.getPivotX();
    }

    @Deprecated
    public static void setPivotX(View view, float f) {
        view.setPivotX(f);
    }

    @Deprecated
    public static float getPivotY(View view) {
        return view.getPivotY();
    }

    @Deprecated
    public static void setPivotY(View view, float f) {
        view.setPivotY(f);
    }

    @Deprecated
    public static float getRotation(View view) {
        return view.getRotation();
    }

    @Deprecated
    public static float getRotationX(View view) {
        return view.getRotationX();
    }

    @Deprecated
    public static float getRotationY(View view) {
        return view.getRotationY();
    }

    @Deprecated
    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    @Deprecated
    public static float getScaleY(View view) {
        return view.getScaleY();
    }

    @Deprecated
    public static float getX(View view) {
        return view.getX();
    }

    @Deprecated
    public static float getY(View view) {
        return view.getY();
    }

    public static void setElevation(View view, float f) {
        IMPL.setElevation(view, f);
    }

    public static float getElevation(View view) {
        return IMPL.getElevation(view);
    }

    public static void setTranslationZ(View view, float f) {
        IMPL.setTranslationZ(view, f);
    }

    public static float getTranslationZ(View view) {
        return IMPL.getTranslationZ(view);
    }

    public static void setTransitionName(View view, String str) {
        IMPL.setTransitionName(view, str);
    }

    public static String getTransitionName(View view) {
        return IMPL.getTransitionName(view);
    }

    public static int getWindowSystemUiVisibility(View view) {
        return IMPL.getWindowSystemUiVisibility(view);
    }

    public static void requestApplyInsets(View view) {
        IMPL.requestApplyInsets(view);
    }

    @Deprecated
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean z) {
        IMPL.setChildrenDrawingOrderEnabled(viewGroup, z);
    }

    public static boolean getFitsSystemWindows(View view) {
        return IMPL.getFitsSystemWindows(view);
    }

    @Deprecated
    public static void setFitsSystemWindows(View view, boolean z) {
        view.setFitsSystemWindows(z);
    }

    @Deprecated
    public static void jumpDrawablesToCurrentState(View view) {
        view.jumpDrawablesToCurrentState();
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        IMPL.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return IMPL.onApplyWindowInsets(view, windowInsetsCompat);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return IMPL.dispatchApplyWindowInsets(view, windowInsetsCompat);
    }

    @Deprecated
    public static void setSaveFromParentEnabled(View view, boolean z) {
        view.setSaveFromParentEnabled(z);
    }

    @Deprecated
    public static void setActivated(View view, boolean z) {
        view.setActivated(z);
    }

    public static boolean hasOverlappingRendering(View view) {
        return IMPL.hasOverlappingRendering(view);
    }

    public static boolean isPaddingRelative(View view) {
        return IMPL.isPaddingRelative(view);
    }

    public static void setBackground(View view, Drawable drawable) {
        IMPL.setBackground(view, drawable);
    }

    public static ColorStateList getBackgroundTintList(View view) {
        return IMPL.getBackgroundTintList(view);
    }

    public static void setBackgroundTintList(View view, ColorStateList colorStateList) {
        IMPL.setBackgroundTintList(view, colorStateList);
    }

    public static PorterDuff.Mode getBackgroundTintMode(View view) {
        return IMPL.getBackgroundTintMode(view);
    }

    public static void setBackgroundTintMode(View view, PorterDuff.Mode mode) {
        IMPL.setBackgroundTintMode(view, mode);
    }

    public static void setNestedScrollingEnabled(@NonNull View view, boolean z) {
        IMPL.setNestedScrollingEnabled(view, z);
    }

    public static boolean isNestedScrollingEnabled(@NonNull View view) {
        return IMPL.isNestedScrollingEnabled(view);
    }

    public static boolean startNestedScroll(@NonNull View view, int i2) {
        return IMPL.startNestedScroll(view, i2);
    }

    public static void stopNestedScroll(@NonNull View view) {
        IMPL.stopNestedScroll(view);
    }

    public static boolean hasNestedScrollingParent(@NonNull View view) {
        return IMPL.hasNestedScrollingParent(view);
    }

    public static boolean dispatchNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, @Nullable int[] iArr) {
        return IMPL.dispatchNestedScroll(view, i2, i3, i4, i5, iArr);
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view, int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return IMPL.dispatchNestedPreScroll(view, i2, i3, iArr, iArr2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean startNestedScroll(@NonNull View view, int i2, int i3) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).startNestedScroll(i2, i3);
        }
        if (i3 == 0) {
            return IMPL.startNestedScroll(view, i2);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void stopNestedScroll(@NonNull View view, int i2) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).stopNestedScroll(i2);
        } else if (i2 == 0) {
            IMPL.stopNestedScroll(view);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean hasNestedScrollingParent(@NonNull View view, int i2) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).hasNestedScrollingParent(i2);
        } else if (i2 == 0) {
            return IMPL.hasNestedScrollingParent(view);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedScroll(i2, i3, i4, i5, iArr, i6);
        }
        if (i6 == 0) {
            return IMPL.dispatchNestedScroll(view, i2, i3, i4, i5, iArr);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedPreScroll(@NonNull View view, int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2, int i4) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
        }
        if (i4 == 0) {
            return IMPL.dispatchNestedPreScroll(view, i2, i3, iArr, iArr2);
        }
        return false;
    }

    public static boolean dispatchNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return IMPL.dispatchNestedFling(view, f, f2, z);
    }

    public static boolean dispatchNestedPreFling(@NonNull View view, float f, float f2) {
        return IMPL.dispatchNestedPreFling(view, f, f2);
    }

    public static boolean isInLayout(View view) {
        return IMPL.isInLayout(view);
    }

    public static boolean isLaidOut(View view) {
        return IMPL.isLaidOut(view);
    }

    public static boolean isLayoutDirectionResolved(View view) {
        return IMPL.isLayoutDirectionResolved(view);
    }

    public static float getZ(View view) {
        return IMPL.getZ(view);
    }

    public static void setZ(View view, float f) {
        IMPL.setZ(view, f);
    }

    public static void offsetTopAndBottom(View view, int i2) {
        IMPL.offsetTopAndBottom(view, i2);
    }

    public static void offsetLeftAndRight(View view, int i2) {
        IMPL.offsetLeftAndRight(view, i2);
    }

    public static void setClipBounds(View view, Rect rect) {
        IMPL.setClipBounds(view, rect);
    }

    public static Rect getClipBounds(View view) {
        return IMPL.getClipBounds(view);
    }

    public static boolean isAttachedToWindow(View view) {
        return IMPL.isAttachedToWindow(view);
    }

    public static boolean hasOnClickListeners(View view) {
        return IMPL.hasOnClickListeners(view);
    }

    public static void setScrollIndicators(@NonNull View view, int i2) {
        IMPL.setScrollIndicators(view, i2);
    }

    public static void setScrollIndicators(@NonNull View view, int i2, int i3) {
        IMPL.setScrollIndicators(view, i2, i3);
    }

    public static int getScrollIndicators(@NonNull View view) {
        return IMPL.getScrollIndicators(view);
    }

    public static void setPointerIcon(@NonNull View view, PointerIconCompat pointerIconCompat) {
        IMPL.setPointerIcon(view, pointerIconCompat);
    }

    public static Display getDisplay(@NonNull View view) {
        return IMPL.getDisplay(view);
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        IMPL.setTooltipText(view, charSequence);
    }

    public static boolean startDragAndDrop(View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i2) {
        return IMPL.startDragAndDrop(view, clipData, dragShadowBuilder, obj, i2);
    }

    public static void cancelDragAndDrop(View view) {
        IMPL.cancelDragAndDrop(view);
    }

    public static void updateDragShadow(View view, View.DragShadowBuilder dragShadowBuilder) {
        IMPL.updateDragShadow(view, dragShadowBuilder);
    }

    public static int getNextClusterForwardId(@NonNull View view) {
        return IMPL.getNextClusterForwardId(view);
    }

    public static void setNextClusterForwardId(@NonNull View view, int i2) {
        IMPL.setNextClusterForwardId(view, i2);
    }

    public static boolean isKeyboardNavigationCluster(@NonNull View view) {
        return IMPL.isKeyboardNavigationCluster(view);
    }

    public static void setKeyboardNavigationCluster(@NonNull View view, boolean z) {
        IMPL.setKeyboardNavigationCluster(view, z);
    }

    public static boolean isFocusedByDefault(@NonNull View view) {
        return IMPL.isFocusedByDefault(view);
    }

    public static void setFocusedByDefault(@NonNull View view, boolean z) {
        IMPL.setFocusedByDefault(view, z);
    }

    public static View keyboardNavigationClusterSearch(@NonNull View view, View view2, int i2) {
        return IMPL.keyboardNavigationClusterSearch(view, view2, i2);
    }

    public static void addKeyboardNavigationClusters(@NonNull View view, @NonNull Collection<View> collection, int i2) {
        IMPL.addKeyboardNavigationClusters(view, collection, i2);
    }

    public static boolean restoreDefaultFocus(@NonNull View view) {
        return IMPL.restoreDefaultFocus(view);
    }

    public static boolean hasExplicitFocusable(@NonNull View view) {
        return IMPL.hasExplicitFocusable(view);
    }

    public static int generateViewId() {
        return IMPL.generateViewId();
    }

    protected ViewCompat() {
    }
}
