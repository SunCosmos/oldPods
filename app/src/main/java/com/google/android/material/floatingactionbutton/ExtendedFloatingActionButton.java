package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int E = R.style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    static final Property<View, Float> F = new d(Float.class, "width");
    static final Property<View, Float> G = new e(Float.class, "height");
    static final Property<View, Float> H = new f(Float.class, "paddingStart");
    static final Property<View, Float> I = new g(Float.class, "paddingEnd");
    private boolean A;
    private boolean B;
    private boolean C;

    @NonNull
    protected ColorStateList D;
    private int q;
    private final com.google.android.material.floatingactionbutton.a r;

    @NonNull
    private final com.google.android.material.floatingactionbutton.f s;

    @NonNull
    private final com.google.android.material.floatingactionbutton.f t;
    private final com.google.android.material.floatingactionbutton.f u;
    private final com.google.android.material.floatingactionbutton.f v;
    private final int w;
    private int x;
    private int y;

    @NonNull
    private final CoordinatorLayout.Behavior<ExtendedFloatingActionButton> z;

    /* loaded from: classes.dex */
    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private Rect a;

        @Nullable
        private OnChangedCallback b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        private OnChangedCallback f1568c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f1569d;
        private boolean e;

        public ExtendedFloatingActionButtonBehavior() {
            this.f1569d = false;
            this.e = true;
        }

        public ExtendedFloatingActionButtonBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.f1569d = obtainStyledAttributes.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.e = obtainStyledAttributes.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            obtainStyledAttributes.recycle();
        }

        private static boolean b(@NonNull View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean c(@NonNull View view, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f1569d || this.e) && ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams()).getAnchorId() == view.getId();
        }

        private boolean e(CoordinatorLayout coordinatorLayout, @NonNull AppBarLayout appBarLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!c(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.a == null) {
                this.a = new Rect();
            }
            Rect rect = this.a;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                d(extendedFloatingActionButton);
                return true;
            }
            a(extendedFloatingActionButton);
            return true;
        }

        private boolean f(@NonNull View view, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!c(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                d(extendedFloatingActionButton);
                return true;
            }
            a(extendedFloatingActionButton);
            return true;
        }

        protected void a(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.e;
            extendedFloatingActionButton.x(z ? extendedFloatingActionButton.t : extendedFloatingActionButton.u, z ? this.f1568c : this.b);
        }

        protected void d(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.e;
            extendedFloatingActionButton.x(z ? extendedFloatingActionButton.s : extendedFloatingActionButton.v, z ? this.f1568c : this.b);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean getInsetDodgeRect(@NonNull CoordinatorLayout coordinatorLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton, @NonNull Rect rect) {
            return super.getInsetDodgeRect(coordinatorLayout, (CoordinatorLayout) extendedFloatingActionButton, rect);
        }

        public boolean isAutoHideEnabled() {
            return this.f1569d;
        }

        public boolean isAutoShrinkEnabled() {
            return this.e;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                e(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            }
            if (!b(view)) {
                return false;
            }
            f(view, extendedFloatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton, int i2) {
            List<View> dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i3 = 0; i3 < size; i3++) {
                View view = dependencies.get(i3);
                if (!(view instanceof AppBarLayout)) {
                    if (b(view) && f(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (e(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i2);
            return true;
        }

        public void setAutoHideEnabled(boolean z) {
            this.f1569d = z;
        }

        public void setAutoShrinkEnabled(boolean z) {
            this.e = z;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OnChangedCallback {
        public void onExtended(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onHidden(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onShown(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onShrunken(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }
    }

    /* loaded from: classes.dex */
    class a implements k {
        a() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public int a() {
            return (ExtendedFloatingActionButton.this.getMeasuredWidth() - (ExtendedFloatingActionButton.this.getCollapsedPadding() * 2)) + ExtendedFloatingActionButton.this.x + ExtendedFloatingActionButton.this.y;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public int b() {
            return ExtendedFloatingActionButton.this.y;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public int c() {
            return ExtendedFloatingActionButton.this.x;
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public ViewGroup.LayoutParams d() {
            return new ViewGroup.LayoutParams(-2, -2);
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public int getHeight() {
            return ExtendedFloatingActionButton.this.getMeasuredHeight();
        }
    }

    /* loaded from: classes.dex */
    class b implements k {
        b() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public int a() {
            return ExtendedFloatingActionButton.this.getCollapsedSize();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public int b() {
            return ExtendedFloatingActionButton.this.getCollapsedPadding();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public int c() {
            return ExtendedFloatingActionButton.this.getCollapsedPadding();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public ViewGroup.LayoutParams d() {
            return new ViewGroup.LayoutParams(a(), getHeight());
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.k
        public int getHeight() {
            return ExtendedFloatingActionButton.this.getCollapsedSize();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        private boolean a;
        final /* synthetic */ com.google.android.material.floatingactionbutton.f b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ OnChangedCallback f1570c;

        c(ExtendedFloatingActionButton extendedFloatingActionButton, com.google.android.material.floatingactionbutton.f fVar, OnChangedCallback onChangedCallback) {
            this.b = fVar;
            this.f1570c = onChangedCallback;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
            this.b.e();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.d();
            if (this.a) {
                return;
            }
            this.b.g(this.f1570c);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.b.onAnimationStart(animator);
            this.a = false;
        }
    }

    /* loaded from: classes.dex */
    static class d extends Property<View, Float> {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        @NonNull
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(@NonNull View view) {
            return Float.valueOf(view.getLayoutParams().width);
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(@NonNull View view, @NonNull Float f) {
            view.getLayoutParams().width = f.intValue();
            view.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    static class e extends Property<View, Float> {
        e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        @NonNull
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(@NonNull View view) {
            return Float.valueOf(view.getLayoutParams().height);
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(@NonNull View view, @NonNull Float f) {
            view.getLayoutParams().height = f.intValue();
            view.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    static class f extends Property<View, Float> {
        f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        @NonNull
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(@NonNull View view) {
            return Float.valueOf(ViewCompat.getPaddingStart(view));
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(@NonNull View view, @NonNull Float f) {
            ViewCompat.setPaddingRelative(view, f.intValue(), view.getPaddingTop(), ViewCompat.getPaddingEnd(view), view.getPaddingBottom());
        }
    }

    /* loaded from: classes.dex */
    static class g extends Property<View, Float> {
        g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        @NonNull
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(@NonNull View view) {
            return Float.valueOf(ViewCompat.getPaddingEnd(view));
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(@NonNull View view, @NonNull Float f) {
            ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
        }
    }

    /* loaded from: classes.dex */
    class h extends com.google.android.material.floatingactionbutton.b {
        private final k g;
        private final boolean h;

        h(com.google.android.material.floatingactionbutton.a aVar, k kVar, boolean z) {
            super(ExtendedFloatingActionButton.this, aVar);
            this.g = kVar;
            this.h = z;
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        @NonNull
        public AnimatorSet c() {
            MotionSpec o = o();
            if (o.hasPropertyValues("width")) {
                PropertyValuesHolder[] propertyValues = o.getPropertyValues("width");
                propertyValues[0].setFloatValues(ExtendedFloatingActionButton.this.getWidth(), this.g.a());
                o.setPropertyValues("width", propertyValues);
            }
            if (o.hasPropertyValues("height")) {
                PropertyValuesHolder[] propertyValues2 = o.getPropertyValues("height");
                propertyValues2[0].setFloatValues(ExtendedFloatingActionButton.this.getHeight(), this.g.getHeight());
                o.setPropertyValues("height", propertyValues2);
            }
            if (o.hasPropertyValues("paddingStart")) {
                PropertyValuesHolder[] propertyValues3 = o.getPropertyValues("paddingStart");
                propertyValues3[0].setFloatValues(ViewCompat.getPaddingStart(ExtendedFloatingActionButton.this), this.g.c());
                o.setPropertyValues("paddingStart", propertyValues3);
            }
            if (o.hasPropertyValues("paddingEnd")) {
                PropertyValuesHolder[] propertyValues4 = o.getPropertyValues("paddingEnd");
                propertyValues4[0].setFloatValues(ViewCompat.getPaddingEnd(ExtendedFloatingActionButton.this), this.g.b());
                o.setPropertyValues("paddingEnd", propertyValues4);
            }
            if (o.hasPropertyValues("labelOpacity")) {
                PropertyValuesHolder[] propertyValues5 = o.getPropertyValues("labelOpacity");
                boolean z = this.h;
                propertyValues5[0].setFloatValues(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
                o.setPropertyValues("labelOpacity", propertyValues5);
            }
            return super.n(o);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void d() {
            super.d();
            ExtendedFloatingActionButton.this.B = false;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.g.d().width;
            layoutParams.height = this.g.d().height;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void g(@Nullable OnChangedCallback onChangedCallback) {
            if (onChangedCallback == null) {
                return;
            }
            if (this.h) {
                onChangedCallback.onExtended(ExtendedFloatingActionButton.this);
            } else {
                onChangedCallback.onShrunken(ExtendedFloatingActionButton.this);
            }
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public boolean h() {
            return this.h == ExtendedFloatingActionButton.this.A || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public int j() {
            return this.h ? R.animator.mtrl_extended_fab_change_size_expand_motion_spec : R.animator.mtrl_extended_fab_change_size_collapse_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void l() {
            ExtendedFloatingActionButton.this.A = this.h;
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.g.d().width;
            layoutParams.height = this.g.d().height;
            ViewCompat.setPaddingRelative(ExtendedFloatingActionButton.this, this.g.c(), ExtendedFloatingActionButton.this.getPaddingTop(), this.g.b(), ExtendedFloatingActionButton.this.getPaddingBottom());
            ExtendedFloatingActionButton.this.requestLayout();
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.A = this.h;
            ExtendedFloatingActionButton.this.B = true;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }
    }

    /* loaded from: classes.dex */
    class i extends com.google.android.material.floatingactionbutton.b {
        private boolean g;

        public i(com.google.android.material.floatingactionbutton.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void d() {
            super.d();
            ExtendedFloatingActionButton.this.q = 0;
            if (this.g) {
                return;
            }
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void e() {
            super.e();
            this.g = true;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void g(@Nullable OnChangedCallback onChangedCallback) {
            if (onChangedCallback != null) {
                onChangedCallback.onHidden(ExtendedFloatingActionButton.this);
            }
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public boolean h() {
            return ExtendedFloatingActionButton.this.v();
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public int j() {
            return R.animator.mtrl_extended_fab_hide_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void l() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.g = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.q = 1;
        }
    }

    /* loaded from: classes.dex */
    class j extends com.google.android.material.floatingactionbutton.b {
        public j(com.google.android.material.floatingactionbutton.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void d() {
            super.d();
            ExtendedFloatingActionButton.this.q = 0;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void g(@Nullable OnChangedCallback onChangedCallback) {
            if (onChangedCallback != null) {
                onChangedCallback.onShown(ExtendedFloatingActionButton.this);
            }
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public boolean h() {
            return ExtendedFloatingActionButton.this.w();
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public int j() {
            return R.animator.mtrl_extended_fab_show_motion_spec;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void l() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.q = 2;
        }
    }

    /* loaded from: classes.dex */
    interface k {
        int a();

        int b();

        int c();

        ViewGroup.LayoutParams d();

        int getHeight();
    }

    public ExtendedFloatingActionButton(@NonNull Context context) {
        this(context, null);
    }

    public ExtendedFloatingActionButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.extendedFloatingActionButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ExtendedFloatingActionButton(@androidx.annotation.NonNull android.content.Context r17, @androidx.annotation.Nullable android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = r19
            int r9 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.E
            r1 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            r10 = 0
            r0.q = r10
            com.google.android.material.floatingactionbutton.a r1 = new com.google.android.material.floatingactionbutton.a
            r1.<init>()
            r0.r = r1
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$j r11 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$j
            r11.<init>(r1)
            r0.u = r11
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$i r12 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$i
            r12.<init>(r1)
            r0.v = r12
            r13 = 1
            r0.A = r13
            r0.B = r10
            r0.C = r10
            android.content.Context r14 = r16.getContext()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior r1 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior
            r1.<init>(r14, r7)
            r0.z = r1
            int[] r3 = com.google.android.material.R.styleable.ExtendedFloatingActionButton
            int[] r6 = new int[r10]
            r1 = r14
            r2 = r18
            r4 = r19
            r5 = r9
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            int r2 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_showMotionSpec
            com.google.android.material.animation.MotionSpec r2 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r2)
            int r3 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_hideMotionSpec
            com.google.android.material.animation.MotionSpec r3 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r3)
            int r4 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_extendMotionSpec
            com.google.android.material.animation.MotionSpec r4 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r4)
            int r5 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_shrinkMotionSpec
            com.google.android.material.animation.MotionSpec r5 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r5)
            int r6 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_collapsedSize
            r15 = -1
            int r6 = r1.getDimensionPixelSize(r6, r15)
            r0.w = r6
            int r6 = androidx.core.view.ViewCompat.getPaddingStart(r16)
            r0.x = r6
            int r6 = androidx.core.view.ViewCompat.getPaddingEnd(r16)
            r0.y = r6
            com.google.android.material.floatingactionbutton.a r6 = new com.google.android.material.floatingactionbutton.a
            r6.<init>()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$h r15 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$h
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$a r10 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$a
            r10.<init>()
            r15.<init>(r6, r10, r13)
            r0.t = r15
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$h r10 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$h
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$b r13 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$b
            r13.<init>()
            r7 = 0
            r10.<init>(r6, r13, r7)
            r0.s = r10
            r11.i(r2)
            r12.i(r3)
            r15.i(r4)
            r10.i(r5)
            r1.recycle()
            com.google.android.material.shape.CornerSize r1 = com.google.android.material.shape.ShapeAppearanceModel.PILL
            r2 = r18
            com.google.android.material.shape.ShapeAppearanceModel$Builder r1 = com.google.android.material.shape.ShapeAppearanceModel.builder(r14, r2, r8, r9, r1)
            com.google.android.material.shape.ShapeAppearanceModel r1 = r1.build()
            r0.setShapeAppearanceModel(r1)
            r16.y()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean v() {
        return getVisibility() == 0 ? this.q == 1 : this.q != 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean w() {
        return getVisibility() != 0 ? this.q == 2 : this.q != 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(@NonNull com.google.android.material.floatingactionbutton.f fVar, @Nullable OnChangedCallback onChangedCallback) {
        if (fVar.h()) {
            return;
        }
        if (!z()) {
            fVar.l();
            fVar.g(onChangedCallback);
            return;
        }
        measure(0, 0);
        AnimatorSet c2 = fVar.c();
        c2.addListener(new c(this, fVar, onChangedCallback));
        Iterator<Animator.AnimatorListener> it = fVar.f().iterator();
        while (it.hasNext()) {
            c2.addListener(it.next());
        }
        c2.start();
    }

    private void y() {
        this.D = getTextColors();
    }

    private boolean z() {
        return (ViewCompat.isLaidOut(this) || (!w() && this.C)) && !isInEditMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void A(@NonNull ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
    }

    public void addOnExtendAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.t.a(animatorListener);
    }

    public void addOnHideAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.v.a(animatorListener);
    }

    public void addOnShowAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.u.a(animatorListener);
    }

    public void addOnShrinkAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.s.a(animatorListener);
    }

    public void extend() {
        x(this.t, null);
    }

    public void extend(@NonNull OnChangedCallback onChangedCallback) {
        x(this.t, onChangedCallback);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    @NonNull
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.z;
    }

    int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    @VisibleForTesting
    int getCollapsedSize() {
        int i2 = this.w;
        return i2 < 0 ? (Math.min(ViewCompat.getPaddingStart(this), ViewCompat.getPaddingEnd(this)) * 2) + getIconSize() : i2;
    }

    @Nullable
    public MotionSpec getExtendMotionSpec() {
        return this.t.b();
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.v.b();
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.u.b();
    }

    @Nullable
    public MotionSpec getShrinkMotionSpec() {
        return this.s.b();
    }

    public void hide() {
        x(this.v, null);
    }

    public void hide(@NonNull OnChangedCallback onChangedCallback) {
        x(this.v, onChangedCallback);
    }

    public final boolean isExtended() {
        return this.A;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.A && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.A = false;
            this.s.l();
        }
    }

    public void removeOnExtendAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.t.k(animatorListener);
    }

    public void removeOnHideAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.v.k(animatorListener);
    }

    public void removeOnShowAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.u.k(animatorListener);
    }

    public void removeOnShrinkAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.s.k(animatorListener);
    }

    public void setAnimateShowBeforeLayout(boolean z) {
        this.C = z;
    }

    public void setExtendMotionSpec(@Nullable MotionSpec motionSpec) {
        this.t.i(motionSpec);
    }

    public void setExtendMotionSpecResource(@AnimatorRes int i2) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), i2));
    }

    public void setExtended(boolean z) {
        if (this.A == z) {
            return;
        }
        com.google.android.material.floatingactionbutton.f fVar = z ? this.t : this.s;
        if (fVar.h()) {
            return;
        }
        fVar.l();
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        this.v.i(motionSpec);
    }

    public void setHideMotionSpecResource(@AnimatorRes int i2) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), i2));
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        super.setPadding(i2, i3, i4, i5);
        if (!this.A || this.B) {
            return;
        }
        this.x = ViewCompat.getPaddingStart(this);
        this.y = ViewCompat.getPaddingEnd(this);
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
        super.setPaddingRelative(i2, i3, i4, i5);
        if (!this.A || this.B) {
            return;
        }
        this.x = i2;
        this.y = i4;
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        this.u.i(motionSpec);
    }

    public void setShowMotionSpecResource(@AnimatorRes int i2) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), i2));
    }

    public void setShrinkMotionSpec(@Nullable MotionSpec motionSpec) {
        this.s.i(motionSpec);
    }

    public void setShrinkMotionSpecResource(@AnimatorRes int i2) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), i2));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i2) {
        super.setTextColor(i2);
        y();
    }

    @Override // android.widget.TextView
    public void setTextColor(@NonNull ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        y();
    }

    public void show() {
        x(this.u, null);
    }

    public void show(@NonNull OnChangedCallback onChangedCallback) {
        x(this.u, onChangedCallback);
    }

    public void shrink() {
        x(this.s, null);
    }

    public void shrink(@NonNull OnChangedCallback onChangedCallback) {
        x(this.s, onChangedCallback);
    }
}
