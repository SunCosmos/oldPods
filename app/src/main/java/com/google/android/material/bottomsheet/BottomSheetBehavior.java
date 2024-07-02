package com.google.android.material.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int SAVE_ALL = -1;
    public static final int SAVE_FIT_TO_CONTENTS = 2;
    public static final int SAVE_HIDEABLE = 4;
    public static final int SAVE_NONE = 0;
    public static final int SAVE_PEEK_HEIGHT = 1;
    public static final int SAVE_SKIP_COLLAPSED = 8;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    private static final int Y = R.style.Widget_Design_BottomSheet_Modal;
    float A;
    int B;
    float C;
    boolean D;
    private boolean E;
    private boolean F;
    int G;

    @Nullable
    ViewDragHelper H;
    private boolean I;
    private int J;
    private boolean K;
    private int L;
    int M;
    int N;

    @Nullable
    WeakReference<V> O;

    @Nullable
    WeakReference<View> P;

    @NonNull
    private final ArrayList<BottomSheetCallback> Q;

    @Nullable
    private VelocityTracker R;
    int S;
    private int T;
    boolean U;

    @Nullable
    private Map<View, Integer> V;
    private int W;
    private final ViewDragHelper.Callback X;
    private int a;
    private boolean b;

    /* renamed from: c */
    private boolean f1490c;

    /* renamed from: d */
    private float f1491d;
    private int e;
    private boolean f;
    private int g;
    private int h;

    /* renamed from: i */
    private boolean f1492i;
    private MaterialShapeDrawable j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private ShapeAppearanceModel t;
    private boolean u;
    private BottomSheetBehavior<V>.g v;

    @Nullable
    private ValueAnimator w;
    int x;
    int y;
    int z;

    /* loaded from: classes.dex */
    public static abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f);

        public abstract void onStateChanged(@NonNull View view, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface SaveFlags {
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        final int b;

        /* renamed from: c */
        int f1493c;

        /* renamed from: d */
        boolean f1494d;
        boolean e;
        boolean f;

        /* loaded from: classes.dex */
        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            @Nullable
            /* renamed from: a */
            public SavedState createFromParcel(@NonNull Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            @NonNull
            /* renamed from: b */
            public SavedState createFromParcel(@NonNull Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: c */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(@NonNull Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public SavedState(@NonNull Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.b = parcel.readInt();
            this.f1493c = parcel.readInt();
            this.f1494d = parcel.readInt() == 1;
            this.e = parcel.readInt() == 1;
            this.f = parcel.readInt() == 1;
        }

        @Deprecated
        public SavedState(Parcelable parcelable, int i2) {
            super(parcelable);
            this.b = i2;
        }

        public SavedState(Parcelable parcelable, @NonNull BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.b = bottomSheetBehavior.G;
            this.f1493c = ((BottomSheetBehavior) bottomSheetBehavior).e;
            this.f1494d = ((BottomSheetBehavior) bottomSheetBehavior).b;
            this.e = bottomSheetBehavior.D;
            this.f = ((BottomSheetBehavior) bottomSheetBehavior).E;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.b);
            parcel.writeInt(this.f1493c);
            parcel.writeInt(this.f1494d ? 1 : 0);
            parcel.writeInt(this.e ? 1 : 0);
            parcel.writeInt(this.f ? 1 : 0);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface State {
    }

    /* loaded from: classes.dex */
    class a implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ ViewGroup.LayoutParams b;

        a(BottomSheetBehavior bottomSheetBehavior, View view, ViewGroup.LayoutParams layoutParams) {
            this.a = view;
            this.b = layoutParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setLayoutParams(this.b);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        b(View view, int i2) {
            this.a = view;
            this.b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.this.E(this.a, this.b);
        }
    }

    /* loaded from: classes.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener {
        c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.this.j != null) {
                BottomSheetBehavior.this.j.setInterpolation(floatValue);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements ViewUtils.OnApplyWindowInsetsListener {
        final /* synthetic */ boolean a;

        d(boolean z) {
            this.a = z;
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
            BottomSheetBehavior.this.s = windowInsetsCompat.getSystemWindowInsetTop();
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(view);
            int paddingBottom = view.getPaddingBottom();
            int paddingLeft = view.getPaddingLeft();
            int paddingRight = view.getPaddingRight();
            if (BottomSheetBehavior.this.n) {
                BottomSheetBehavior.this.r = windowInsetsCompat.getSystemWindowInsetBottom();
                paddingBottom = relativePadding.bottom + BottomSheetBehavior.this.r;
            }
            if (BottomSheetBehavior.this.o) {
                paddingLeft = (isLayoutRtl ? relativePadding.end : relativePadding.start) + windowInsetsCompat.getSystemWindowInsetLeft();
            }
            if (BottomSheetBehavior.this.p) {
                paddingRight = (isLayoutRtl ? relativePadding.start : relativePadding.end) + windowInsetsCompat.getSystemWindowInsetRight();
            }
            view.setPadding(paddingLeft, view.getPaddingTop(), paddingRight, paddingBottom);
            if (this.a) {
                BottomSheetBehavior.this.l = windowInsetsCompat.getMandatorySystemGestureInsets().bottom;
            }
            if (BottomSheetBehavior.this.n || this.a) {
                BottomSheetBehavior.this.L(false);
            }
            return windowInsetsCompat;
        }
    }

    /* loaded from: classes.dex */
    class e extends ViewDragHelper.Callback {
        e() {
        }

        private boolean a(@NonNull View view) {
            int top = view.getTop();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return top > (bottomSheetBehavior.N + bottomSheetBehavior.getExpandedOffset()) / 2;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(@NonNull View view, int i2, int i3) {
            return view.getLeft();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(@NonNull View view, int i2, int i3) {
            int expandedOffset = BottomSheetBehavior.this.getExpandedOffset();
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return MathUtils.clamp(i2, expandedOffset, bottomSheetBehavior.D ? bottomSheetBehavior.N : bottomSheetBehavior.B);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(@NonNull View view) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            return bottomSheetBehavior.D ? bottomSheetBehavior.N : bottomSheetBehavior.B;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            if (i2 == 1 && BottomSheetBehavior.this.F) {
                BottomSheetBehavior.this.C(1);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(@NonNull View view, int i2, int i3, int i4, int i5) {
            BottomSheetBehavior.this.v(i3);
        }

        /* JADX WARN: Code restructure failed: missing block: B:29:0x0079, code lost:
        
            if (java.lang.Math.abs(r7.getTop() - com.google.android.material.bottomsheet.BottomSheetBehavior.this.getExpandedOffset()) < java.lang.Math.abs(r7.getTop() - com.google.android.material.bottomsheet.BottomSheetBehavior.this.z)) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x007b, code lost:
        
            r8 = com.google.android.material.bottomsheet.BottomSheetBehavior.this.getExpandedOffset();
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00b7, code lost:
        
            if (java.lang.Math.abs(r8 - com.google.android.material.bottomsheet.BottomSheetBehavior.this.z) < java.lang.Math.abs(r8 - com.google.android.material.bottomsheet.BottomSheetBehavior.this.B)) goto L95;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00de, code lost:
        
            if (java.lang.Math.abs(r8 - com.google.android.material.bottomsheet.BottomSheetBehavior.this.y) < java.lang.Math.abs(r8 - com.google.android.material.bottomsheet.BottomSheetBehavior.this.B)) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00f0, code lost:
        
            if (r8 < java.lang.Math.abs(r8 - r9.B)) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0102, code lost:
        
            if (java.lang.Math.abs(r8 - r0) < java.lang.Math.abs(r8 - com.google.android.material.bottomsheet.BottomSheetBehavior.this.B)) goto L95;
         */
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onViewReleased(@androidx.annotation.NonNull android.view.View r7, float r8, float r9) {
            /*
                Method dump skipped, instructions count: 268
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.e.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(@NonNull View view, int i2) {
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
            int i3 = bottomSheetBehavior.G;
            if (i3 == 1 || bottomSheetBehavior.U) {
                return false;
            }
            if (i3 == 3 && bottomSheetBehavior.S == i2) {
                WeakReference<View> weakReference = bottomSheetBehavior.P;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = BottomSheetBehavior.this.O;
            return weakReference2 != null && weakReference2.get() == view;
        }
    }

    /* loaded from: classes.dex */
    public class f implements AccessibilityViewCommand {
        final /* synthetic */ int a;

        f(int i2) {
            this.a = i2;
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
            BottomSheetBehavior.this.setState(this.a);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class g implements Runnable {
        private final View a;
        private boolean b;

        /* renamed from: c */
        int f1496c;

        g(View view, int i2) {
            this.a = view;
            this.f1496c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper viewDragHelper = BottomSheetBehavior.this.H;
            if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                BottomSheetBehavior.this.C(this.f1496c);
            } else {
                ViewCompat.postOnAnimation(this.a, this);
            }
            this.b = false;
        }
    }

    public BottomSheetBehavior() {
        this.a = 0;
        this.b = true;
        this.f1490c = false;
        this.k = -1;
        this.v = null;
        this.A = 0.5f;
        this.C = -1.0f;
        this.F = true;
        this.G = 4;
        this.Q = new ArrayList<>();
        this.W = -1;
        this.X = new e();
    }

    public BottomSheetBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        int i2;
        this.a = 0;
        this.b = true;
        this.f1490c = false;
        this.k = -1;
        this.v = null;
        this.A = 0.5f;
        this.C = -1.0f;
        this.F = true;
        this.G = 4;
        this.Q = new ArrayList<>();
        this.W = -1;
        this.X = new e();
        this.h = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        this.f1492i = obtainStyledAttributes.hasValue(R.styleable.BottomSheetBehavior_Layout_shapeAppearance);
        int i3 = R.styleable.BottomSheetBehavior_Layout_backgroundTint;
        boolean hasValue = obtainStyledAttributes.hasValue(i3);
        if (hasValue) {
            t(context, attributeSet, hasValue, MaterialResources.getColorStateList(context, obtainStyledAttributes, i3));
        } else {
            s(context, attributeSet, hasValue);
        }
        u();
        if (Build.VERSION.SDK_INT >= 21) {
            this.C = obtainStyledAttributes.getDimension(R.styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        int i4 = R.styleable.BottomSheetBehavior_Layout_android_maxWidth;
        if (obtainStyledAttributes.hasValue(i4)) {
            setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(i4, -1));
        }
        int i5 = R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue peekValue = obtainStyledAttributes.peekValue(i5);
        if (peekValue == null || (i2 = peekValue.data) != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(i5, -1));
        } else {
            setPeekHeight(i2);
        }
        setHideable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setGestureInsetBottomIgnored(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        setFitToContents(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        setDraggable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        setSaveFlags(obtainStyledAttributes.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        setHalfExpandedRatio(obtainStyledAttributes.getFloat(R.styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i6 = R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(i6);
        setExpandedOffset((peekValue2 == null || peekValue2.type != 16) ? obtainStyledAttributes.getDimensionPixelOffset(i6, 0) : peekValue2.data);
        this.n = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.o = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.p = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.q = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        obtainStyledAttributes.recycle();
        this.f1491d = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    private void A() {
        this.S = -1;
        VelocityTracker velocityTracker = this.R;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.R = null;
        }
    }

    private void B(@NonNull SavedState savedState) {
        int i2 = this.a;
        if (i2 == 0) {
            return;
        }
        if (i2 == -1 || (i2 & 1) == 1) {
            this.e = savedState.f1493c;
        }
        if (i2 == -1 || (i2 & 2) == 2) {
            this.b = savedState.f1494d;
        }
        if (i2 == -1 || (i2 & 4) == 4) {
            this.D = savedState.e;
        }
        if (i2 == -1 || (i2 & 8) == 8) {
            this.E = savedState.f;
        }
    }

    private void D(@NonNull View view) {
        boolean z = (Build.VERSION.SDK_INT < 29 || isGestureInsetBottomIgnored() || this.f) ? false : true;
        if (this.n || this.o || this.p || z) {
            ViewUtils.doOnApplyWindowInsets(view, new d(z));
        }
    }

    private void F(int i2) {
        V v = this.O.get();
        if (v == null) {
            return;
        }
        ViewParent parent = v.getParent();
        if (parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(v)) {
            v.post(new b(v, i2));
        } else {
            E(v, i2);
        }
    }

    private void I() {
        V v;
        int i2;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        ViewCompat.removeAccessibilityAction(v, 524288);
        ViewCompat.removeAccessibilityAction(v, 262144);
        ViewCompat.removeAccessibilityAction(v, 1048576);
        int i3 = this.W;
        if (i3 != -1) {
            ViewCompat.removeAccessibilityAction(v, i3);
        }
        if (!this.b && this.G != 6) {
            this.W = n(v, R.string.bottomsheet_action_expand_halfway, 6);
        }
        if (this.D && this.G != 5) {
            z(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
        }
        int i4 = this.G;
        if (i4 == 3) {
            i2 = this.b ? 4 : 6;
            accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE;
        } else {
            if (i4 != 4) {
                if (i4 != 6) {
                    return;
                }
                z(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 4);
                z(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
                return;
            }
            i2 = this.b ? 3 : 6;
            accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND;
        }
        z(v, accessibilityActionCompat, i2);
    }

    private void J(int i2) {
        ValueAnimator valueAnimator;
        if (i2 == 2) {
            return;
        }
        boolean z = i2 == 3;
        if (this.u != z) {
            this.u = z;
            if (this.j == null || (valueAnimator = this.w) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.w.reverse();
                return;
            }
            float f2 = z ? 0.0f : 1.0f;
            this.w.setFloatValues(1.0f - f2, f2);
            this.w.start();
        }
    }

    private void K(boolean z) {
        Map<View, Integer> map;
        int intValue;
        int i2 = Build.VERSION.SDK_INT;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (i2 >= 16 && z) {
                if (this.V != null) {
                    return;
                } else {
                    this.V = new HashMap(childCount);
                }
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = coordinatorLayout.getChildAt(i3);
                if (childAt != this.O.get()) {
                    if (z) {
                        if (i2 >= 16) {
                            this.V.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        if (this.f1490c) {
                            intValue = 4;
                            ViewCompat.setImportantForAccessibility(childAt, intValue);
                        }
                    } else if (this.f1490c && (map = this.V) != null && map.containsKey(childAt)) {
                        intValue = this.V.get(childAt).intValue();
                        ViewCompat.setImportantForAccessibility(childAt, intValue);
                    }
                }
            }
            if (!z) {
                this.V = null;
            } else if (this.f1490c) {
                this.O.get().sendAccessibilityEvent(8);
            }
        }
    }

    public void L(boolean z) {
        V v;
        if (this.O != null) {
            o();
            if (this.G != 4 || (v = this.O.get()) == null) {
                return;
            }
            if (z) {
                F(this.G);
            } else {
                v.requestLayout();
            }
        }
    }

    @NonNull
    public static <V extends View> BottomSheetBehavior<V> from(@NonNull V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (behavior instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) behavior;
        }
        throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }

    private int n(V v, @StringRes int i2, int i3) {
        return ViewCompat.addAccessibilityAction(v, v.getResources().getString(i2), r(i3));
    }

    private void o() {
        int q = q();
        if (this.b) {
            this.B = Math.max(this.N - q, this.y);
        } else {
            this.B = this.N - q;
        }
    }

    private void p() {
        this.z = (int) (this.N * (1.0f - this.A));
    }

    private int q() {
        int i2;
        return this.f ? Math.min(Math.max(this.g, this.N - ((this.M * 9) / 16)), this.L) + this.r : (this.m || this.n || (i2 = this.l) <= 0) ? this.e + this.r : Math.max(this.e, i2 + this.h);
    }

    private AccessibilityViewCommand r(int i2) {
        return new f(i2);
    }

    private void s(@NonNull Context context, AttributeSet attributeSet, boolean z) {
        t(context, attributeSet, z, null);
    }

    private void t(@NonNull Context context, AttributeSet attributeSet, boolean z, @Nullable ColorStateList colorStateList) {
        if (this.f1492i) {
            this.t = ShapeAppearanceModel.builder(context, attributeSet, R.attr.bottomSheetStyle, Y).build();
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.t);
            this.j = materialShapeDrawable;
            materialShapeDrawable.initializeElevationOverlay(context);
            if (z && colorStateList != null) {
                this.j.setFillColor(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
            this.j.setTint(typedValue.data);
        }
    }

    private void u() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.w = ofFloat;
        ofFloat.setDuration(500L);
        this.w.addUpdateListener(new c());
    }

    private float y() {
        VelocityTracker velocityTracker = this.R;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.f1491d);
        return this.R.getYVelocity(this.S);
    }

    private void z(V v, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i2) {
        ViewCompat.replaceAccessibilityAction(v, accessibilityActionCompat, null, r(i2));
    }

    void C(int i2) {
        V v;
        if (this.G == i2) {
            return;
        }
        this.G = i2;
        WeakReference<V> weakReference = this.O;
        if (weakReference == null || (v = weakReference.get()) == null) {
            return;
        }
        if (i2 == 3) {
            K(true);
        } else if (i2 == 6 || i2 == 5 || i2 == 4) {
            K(false);
        }
        J(i2);
        for (int i3 = 0; i3 < this.Q.size(); i3++) {
            this.Q.get(i3).onStateChanged(v, i2);
        }
        I();
    }

    void E(@NonNull View view, int i2) {
        int i3;
        int i4;
        if (i2 == 4) {
            i3 = this.B;
        } else if (i2 == 6) {
            int i5 = this.z;
            if (!this.b || i5 > (i4 = this.y)) {
                i3 = i5;
            } else {
                i3 = i4;
                i2 = 3;
            }
        } else if (i2 == 3) {
            i3 = getExpandedOffset();
        } else {
            if (!this.D || i2 != 5) {
                throw new IllegalArgumentException("Illegal state argument: " + i2);
            }
            i3 = this.N;
        }
        H(view, i2, i3, false);
    }

    boolean G(@NonNull View view, float f2) {
        if (this.E) {
            return true;
        }
        if (view.getTop() < this.B) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f2 * 0.1f)) - ((float) this.B)) / ((float) q()) > 0.5f;
    }

    void H(View view, int i2, int i3, boolean z) {
        ViewDragHelper viewDragHelper = this.H;
        if (!(viewDragHelper != null && (!z ? !viewDragHelper.smoothSlideViewTo(view, view.getLeft(), i3) : !viewDragHelper.settleCapturedViewAt(view.getLeft(), i3)))) {
            C(i2);
            return;
        }
        C(2);
        J(i2);
        if (this.v == null) {
            this.v = new g(view, i2);
        }
        if (((g) this.v).b) {
            this.v.f1496c = i2;
            return;
        }
        BottomSheetBehavior<V>.g gVar = this.v;
        gVar.f1496c = i2;
        ViewCompat.postOnAnimation(view, gVar);
        ((g) this.v).b = true;
    }

    public void addBottomSheetCallback(@NonNull BottomSheetCallback bottomSheetCallback) {
        if (this.Q.contains(bottomSheetCallback)) {
            return;
        }
        this.Q.add(bottomSheetCallback);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public void disableShapeAnimations() {
        this.w = null;
    }

    public int getExpandedOffset() {
        if (this.b) {
            return this.y;
        }
        return Math.max(this.x, this.q ? 0 : this.s);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getHalfExpandedRatio() {
        return this.A;
    }

    @Px
    public int getMaxWidth() {
        return this.k;
    }

    public int getPeekHeight() {
        if (this.f) {
            return -1;
        }
        return this.e;
    }

    public int getSaveFlags() {
        return this.a;
    }

    public boolean getSkipCollapsed() {
        return this.E;
    }

    public int getState() {
        return this.G;
    }

    public boolean isDraggable() {
        return this.F;
    }

    public boolean isFitToContents() {
        return this.b;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.m;
    }

    public boolean isHideable() {
        return this.D;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.O = null;
        this.H = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.O = null;
        this.H = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        if (!v.isShown() || !this.F) {
            this.I = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            A();
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.T = (int) motionEvent.getY();
            if (this.G != 2) {
                WeakReference<View> weakReference = this.P;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.isPointInChildBounds(view, x, this.T)) {
                    this.S = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.U = true;
                }
            }
            this.I = this.S == -1 && !coordinatorLayout.isPointInChildBounds(v, x, this.T);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.U = false;
            this.S = -1;
            if (this.I) {
                this.I = false;
                return false;
            }
        }
        if (!this.I && (viewDragHelper = this.H) != null && viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.P;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.I || this.G == 1 || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.H == null || Math.abs(((float) this.T) - motionEvent.getY()) <= ((float) this.H.getTouchSlop())) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, int i2) {
        int i3;
        MaterialShapeDrawable materialShapeDrawable;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.O == null) {
            this.g = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            D(v);
            this.O = new WeakReference<>(v);
            if (this.f1492i && (materialShapeDrawable = this.j) != null) {
                ViewCompat.setBackground(v, materialShapeDrawable);
            }
            MaterialShapeDrawable materialShapeDrawable2 = this.j;
            if (materialShapeDrawable2 != null) {
                float f2 = this.C;
                if (f2 == -1.0f) {
                    f2 = ViewCompat.getElevation(v);
                }
                materialShapeDrawable2.setElevation(f2);
                boolean z = this.G == 3;
                this.u = z;
                this.j.setInterpolation(z ? 0.0f : 1.0f);
            }
            I();
            if (ViewCompat.getImportantForAccessibility(v) == 0) {
                ViewCompat.setImportantForAccessibility(v, 1);
            }
            int measuredWidth = v.getMeasuredWidth();
            int i4 = this.k;
            if (measuredWidth > i4 && i4 != -1) {
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.width = this.k;
                v.post(new a(this, v, layoutParams));
            }
        }
        if (this.H == null) {
            this.H = ViewDragHelper.create(coordinatorLayout, this.X);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i2);
        this.M = coordinatorLayout.getWidth();
        this.N = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.L = height;
        int i5 = this.N;
        int i6 = i5 - height;
        int i7 = this.s;
        if (i6 < i7) {
            if (this.q) {
                this.L = i5;
            } else {
                this.L = i5 - i7;
            }
        }
        this.y = Math.max(0, i5 - this.L);
        p();
        o();
        int i8 = this.G;
        if (i8 == 3) {
            i3 = getExpandedOffset();
        } else if (i8 == 6) {
            i3 = this.z;
        } else if (this.D && i8 == 5) {
            i3 = this.N;
        } else {
            if (i8 != 4) {
                if (i8 == 1 || i8 == 2) {
                    ViewCompat.offsetTopAndBottom(v, top - v.getTop());
                }
                this.P = new WeakReference<>(w(v));
                return true;
            }
            i3 = this.B;
        }
        ViewCompat.offsetTopAndBottom(v, i3);
        this.P = new WeakReference<>(w(v));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, float f2, float f3) {
        WeakReference<View> weakReference = this.P;
        if (weakReference == null || view != weakReference.get()) {
            return false;
        }
        return this.G != 3 || super.onNestedPreFling(coordinatorLayout, v, view, f2, f3);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i2, int i3, @NonNull int[] iArr, int i4) {
        int i5;
        if (i4 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.P;
        if (view != (weakReference != null ? weakReference.get() : null)) {
            return;
        }
        int top = v.getTop();
        int i6 = top - i3;
        if (i3 > 0) {
            if (i6 < getExpandedOffset()) {
                iArr[1] = top - getExpandedOffset();
                ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                i5 = 3;
                C(i5);
            } else {
                if (!this.F) {
                    return;
                }
                iArr[1] = i3;
                ViewCompat.offsetTopAndBottom(v, -i3);
                C(1);
            }
        } else if (i3 < 0 && !view.canScrollVertically(-1)) {
            int i7 = this.B;
            if (i6 > i7 && !this.D) {
                iArr[1] = top - i7;
                ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                i5 = 4;
                C(i5);
            } else {
                if (!this.F) {
                    return;
                }
                iArr[1] = i3;
                ViewCompat.offsetTopAndBottom(v, -i3);
                C(1);
            }
        }
        v(v.getTop());
        this.J = i3;
        this.K = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i2, int i3, int i4, int i5, int i6, @NonNull int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        B(savedState);
        int i2 = savedState.b;
        if (i2 == 1 || i2 == 2) {
            i2 = 4;
        }
        this.G = i2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    @NonNull
    public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), (BottomSheetBehavior<?>) this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, @NonNull View view2, int i2, int i3) {
        this.J = 0;
        this.K = false;
        return (i2 & 2) != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x006b, code lost:
    
        if (java.lang.Math.abs(r3 - r2.y) < java.lang.Math.abs(r3 - r2.B)) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007a, code lost:
    
        if (r3 < java.lang.Math.abs(r3 - r2.B)) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008a, code lost:
    
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.B)) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a8, code lost:
    
        if (java.lang.Math.abs(r3 - r2.z) < java.lang.Math.abs(r3 - r2.B)) goto L101;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onStopNestedScroll(@androidx.annotation.NonNull androidx.coordinatorlayout.widget.CoordinatorLayout r3, @androidx.annotation.NonNull V r4, @androidx.annotation.NonNull android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.getExpandedOffset()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.C(r0)
            return
        Lf:
            java.lang.ref.WeakReference<android.view.View> r3 = r2.P
            if (r3 == 0) goto Lb3
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto Lb3
            boolean r3 = r2.K
            if (r3 != 0) goto L1f
            goto Lb3
        L1f:
            int r3 = r2.J
            r5 = 4
            r6 = 6
            if (r3 <= 0) goto L3e
            boolean r3 = r2.b
            if (r3 == 0) goto L2d
        L29:
            int r3 = r2.y
            goto Lad
        L2d:
            int r3 = r4.getTop()
            int r5 = r2.z
            if (r3 <= r5) goto L38
            r3 = r5
            goto Lac
        L38:
            int r3 = r2.getExpandedOffset()
            goto Lad
        L3e:
            boolean r3 = r2.D
            if (r3 == 0) goto L50
            float r3 = r2.y()
            boolean r3 = r2.G(r4, r3)
            if (r3 == 0) goto L50
            int r3 = r2.N
            r0 = 5
            goto Lad
        L50:
            int r3 = r2.J
            if (r3 != 0) goto L8d
            int r3 = r4.getTop()
            boolean r1 = r2.b
            if (r1 == 0) goto L6e
            int r6 = r2.y
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            int r1 = r2.B
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r6 >= r3) goto L91
            goto L29
        L6e:
            int r1 = r2.z
            if (r3 >= r1) goto L7d
            int r5 = r2.B
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            if (r3 >= r5) goto Laa
            goto L38
        L7d:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.B
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L91
            goto Laa
        L8d:
            boolean r3 = r2.b
            if (r3 == 0) goto L95
        L91:
            int r3 = r2.B
            r0 = 4
            goto Lad
        L95:
            int r3 = r4.getTop()
            int r0 = r2.z
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.B
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L91
        Laa:
            int r3 = r2.z
        Lac:
            r0 = 6
        Lad:
            r5 = 0
            r2.H(r4, r0, r3, r5)
            r2.K = r5
        Lb3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.G == 1 && actionMasked == 0) {
            return true;
        }
        ViewDragHelper viewDragHelper = this.H;
        if (viewDragHelper != null) {
            viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            A();
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        if (this.H != null && actionMasked == 2 && !this.I && Math.abs(this.T - motionEvent.getY()) > this.H.getTouchSlop()) {
            this.H.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.I;
    }

    public void removeBottomSheetCallback(@NonNull BottomSheetCallback bottomSheetCallback) {
        this.Q.remove(bottomSheetCallback);
    }

    @Deprecated
    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        Log.w("BottomSheetBehavior", "BottomSheetBehavior now supports multiple callbacks. `setBottomSheetCallback()` removes all existing callbacks, including ones set internally by library authors, which may result in unintended behavior. This may change in the future. Please use `addBottomSheetCallback()` and `removeBottomSheetCallback()` instead to set your own callbacks.");
        this.Q.clear();
        if (bottomSheetCallback != null) {
            this.Q.add(bottomSheetCallback);
        }
    }

    public void setDraggable(boolean z) {
        this.F = z;
    }

    public void setExpandedOffset(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.x = i2;
    }

    public void setFitToContents(boolean z) {
        if (this.b == z) {
            return;
        }
        this.b = z;
        if (this.O != null) {
            o();
        }
        C((this.b && this.G == 6) ? 3 : this.G);
        I();
    }

    public void setGestureInsetBottomIgnored(boolean z) {
        this.m = z;
    }

    public void setHalfExpandedRatio(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.A = f2;
        if (this.O != null) {
            p();
        }
    }

    public void setHideable(boolean z) {
        if (this.D != z) {
            this.D = z;
            if (!z && this.G == 5) {
                setState(4);
            }
            I();
        }
    }

    public void setMaxWidth(@Px int i2) {
        this.k = i2;
    }

    public void setPeekHeight(int i2) {
        setPeekHeight(i2, false);
    }

    public final void setPeekHeight(int i2, boolean z) {
        boolean z2 = true;
        if (i2 == -1) {
            if (!this.f) {
                this.f = true;
            }
            z2 = false;
        } else {
            if (this.f || this.e != i2) {
                this.f = false;
                this.e = Math.max(0, i2);
            }
            z2 = false;
        }
        if (z2) {
            L(z);
        }
    }

    public void setSaveFlags(int i2) {
        this.a = i2;
    }

    public void setSkipCollapsed(boolean z) {
        this.E = z;
    }

    public void setState(int i2) {
        if (i2 == this.G) {
            return;
        }
        if (this.O != null) {
            F(i2);
            return;
        }
        if (i2 == 4 || i2 == 3 || i2 == 6 || (this.D && i2 == 5)) {
            this.G = i2;
        }
    }

    public void setUpdateImportantForAccessibilityOnSiblings(boolean z) {
        this.f1490c = z;
    }

    void v(int i2) {
        float f2;
        float f3;
        V v = this.O.get();
        if (v == null || this.Q.isEmpty()) {
            return;
        }
        int i3 = this.B;
        if (i2 > i3 || i3 == getExpandedOffset()) {
            int i4 = this.B;
            f2 = i4 - i2;
            f3 = this.N - i4;
        } else {
            int i5 = this.B;
            f2 = i5 - i2;
            f3 = i5 - getExpandedOffset();
        }
        float f4 = f2 / f3;
        for (int i6 = 0; i6 < this.Q.size(); i6++) {
            this.Q.get(i6).onSlide(v, f4);
        }
    }

    @Nullable
    @VisibleForTesting
    View w(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View w = w(viewGroup.getChildAt(i2));
            if (w != null) {
                return w;
            }
        }
        return null;
    }

    public MaterialShapeDrawable x() {
        return this.j;
    }
}
