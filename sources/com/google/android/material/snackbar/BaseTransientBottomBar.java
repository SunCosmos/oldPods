package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import bsh.org.objectweb.asm.Constants;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.a;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    public static final int ANIMATION_MODE_FADE = 1;
    public static final int ANIMATION_MODE_SLIDE = 0;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;

    @NonNull
    static final Handler u;
    private static final boolean v;
    private static final int[] w;
    private static final String x;

    @NonNull
    private final ViewGroup a;
    private final Context b;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    protected final SnackbarBaseLayout f1691c;

    /* renamed from: d, reason: collision with root package name */
    @NonNull
    private final com.google.android.material.snackbar.ContentViewCallback f1692d;
    private int e;
    private boolean f;

    @Nullable
    private View g;

    @Nullable
    private Rect k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private List<BaseCallback<B>> q;
    private Behavior r;

    @Nullable
    private final AccessibilityManager s;
    private boolean h = false;

    /* renamed from: i, reason: collision with root package name */
    private final ViewTreeObserver.OnGlobalLayoutListener f1693i = new k();

    @RequiresApi(29)
    private final Runnable j = new l();

    @NonNull
    a.b t = new o();

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface AnimationMode {
    }

    /* loaded from: classes.dex */
    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes.dex */
        public @interface DismissEvent {
        }

        public void onDismissed(B b, int i2) {
        }

        public void onShown(B b) {
        }
    }

    /* loaded from: classes.dex */
    public static class Behavior extends SwipeDismissBehavior<View> {

        @NonNull
        private final BehaviorDelegate k = new BehaviorDelegate(this);

        /* JADX INFO: Access modifiers changed from: private */
        public void g(@NonNull BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.k.setBaseTransientBottomBar(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View view) {
            return this.k.canSwipeDismissView(view);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull MotionEvent motionEvent) {
            this.k.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static class BehaviorDelegate {
        private a.b a;

        public BehaviorDelegate(@NonNull SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.setStartAlphaSwipeDistance(0.1f);
            swipeDismissBehavior.setEndAlphaSwipeDistance(0.6f);
            swipeDismissBehavior.setSwipeDirection(0);
        }

        public boolean canSwipeDismissView(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    com.google.android.material.snackbar.a.c().k(this.a);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                com.google.android.material.snackbar.a.c().l(this.a);
            }
        }

        public void setBaseTransientBottomBar(@NonNull BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.a = baseTransientBottomBar.t;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface ContentViewCallback extends com.google.android.material.snackbar.ContentViewCallback {
    }

    @IntRange(from = 1)
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface Duration {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public interface OnAttachStateChangeListener {
        void onViewAttachedToWindow(View view);

        void onViewDetachedFromWindow(View view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public interface OnLayoutChangeListener {
        void onLayoutChange(View view, int i2, int i3, int i4, int i5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static class SnackbarBaseLayout extends FrameLayout {
        private static final View.OnTouchListener h = new a();
        private OnLayoutChangeListener a;
        private OnAttachStateChangeListener b;

        /* renamed from: c, reason: collision with root package name */
        private int f1694c;

        /* renamed from: d, reason: collision with root package name */
        private final float f1695d;
        private final float e;
        private ColorStateList f;
        private PorterDuff.Mode g;

        /* loaded from: classes.dex */
        static class a implements View.OnTouchListener {
            a() {
            }

            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public SnackbarBaseLayout(@NonNull Context context) {
            this(context, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public SnackbarBaseLayout(@NonNull Context context, AttributeSet attributeSet) {
            super(MaterialThemeOverlay.wrap(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, obtainStyledAttributes.getDimensionPixelSize(r1, 0));
            }
            this.f1694c = obtainStyledAttributes.getInt(R.styleable.SnackbarLayout_animationMode, 0);
            this.f1695d = obtainStyledAttributes.getFloat(R.styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            setBackgroundTintList(MaterialResources.getColorStateList(context2, obtainStyledAttributes, R.styleable.SnackbarLayout_backgroundTint));
            setBackgroundTintMode(ViewUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.e = obtainStyledAttributes.getFloat(R.styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            obtainStyledAttributes.recycle();
            setOnTouchListener(h);
            setFocusable(true);
            if (getBackground() == null) {
                ViewCompat.setBackground(this, a());
            }
        }

        @NonNull
        private Drawable a() {
            float dimension = getResources().getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dimension);
            gradientDrawable.setColor(MaterialColors.layer(this, R.attr.colorSurface, R.attr.colorOnSurface, getBackgroundOverlayColorAlpha()));
            if (this.f == null) {
                return DrawableCompat.wrap(gradientDrawable);
            }
            Drawable wrap = DrawableCompat.wrap(gradientDrawable);
            DrawableCompat.setTintList(wrap, this.f);
            return wrap;
        }

        float getActionTextColorAlpha() {
            return this.e;
        }

        int getAnimationMode() {
            return this.f1694c;
        }

        float getBackgroundOverlayColorAlpha() {
            return this.f1695d;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            OnAttachStateChangeListener onAttachStateChangeListener = this.b;
            if (onAttachStateChangeListener != null) {
                onAttachStateChangeListener.onViewAttachedToWindow(this);
            }
            ViewCompat.requestApplyInsets(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            OnAttachStateChangeListener onAttachStateChangeListener = this.b;
            if (onAttachStateChangeListener != null) {
                onAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            OnLayoutChangeListener onLayoutChangeListener = this.a;
            if (onLayoutChangeListener != null) {
                onLayoutChangeListener.onLayoutChange(this, i2, i3, i4, i5);
            }
        }

        void setAnimationMode(int i2) {
            this.f1694c = i2;
        }

        @Override // android.view.View
        public void setBackground(@Nullable Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(@Nullable Drawable drawable) {
            if (drawable != null && this.f != null) {
                drawable = DrawableCompat.wrap(drawable.mutate());
                DrawableCompat.setTintList(drawable, this.f);
                DrawableCompat.setTintMode(drawable, this.g);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
            this.f = colorStateList;
            if (getBackground() != null) {
                Drawable wrap = DrawableCompat.wrap(getBackground().mutate());
                DrawableCompat.setTintList(wrap, colorStateList);
                DrawableCompat.setTintMode(wrap, this.g);
                if (wrap != getBackground()) {
                    super.setBackgroundDrawable(wrap);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
            this.g = mode;
            if (getBackground() != null) {
                Drawable wrap = DrawableCompat.wrap(getBackground().mutate());
                DrawableCompat.setTintMode(wrap, mode);
                if (wrap != getBackground()) {
                    super.setBackgroundDrawable(wrap);
                }
            }
        }

        void setOnAttachStateChangeListener(OnAttachStateChangeListener onAttachStateChangeListener) {
            this.b = onAttachStateChangeListener;
        }

        @Override // android.view.View
        public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : h);
            super.setOnClickListener(onClickListener);
        }

        void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener) {
            this.a = onLayoutChangeListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SnackbarBaseLayout snackbarBaseLayout = BaseTransientBottomBar.this.f1691c;
            if (snackbarBaseLayout == null) {
                return;
            }
            if (snackbarBaseLayout.getParent() != null) {
                BaseTransientBottomBar.this.f1691c.setVisibility(0);
            }
            if (BaseTransientBottomBar.this.f1691c.getAnimationMode() == 1) {
                BaseTransientBottomBar.this.N();
            } else {
                BaseTransientBottomBar.this.P();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.H();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        final /* synthetic */ int a;

        c(int i2) {
            this.a = i2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.G(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            BaseTransientBottomBar.this.f1691c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements ValueAnimator.AnimatorUpdateListener {
        e() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            BaseTransientBottomBar.this.f1691c.setScaleX(floatValue);
            BaseTransientBottomBar.this.f1691c.setScaleY(floatValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f extends AnimatorListenerAdapter {
        f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.H();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BaseTransientBottomBar.this.f1692d.animateContentIn(70, Constants.GETFIELD);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements ValueAnimator.AnimatorUpdateListener {
        private int a;
        final /* synthetic */ int b;

        g(int i2) {
            this.b = i2;
            this.a = i2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBottomBar.v) {
                ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.f1691c, intValue - this.a);
            } else {
                BaseTransientBottomBar.this.f1691c.setTranslationY(intValue);
            }
            this.a = intValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h extends AnimatorListenerAdapter {
        final /* synthetic */ int a;

        h(int i2) {
            this.a = i2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            BaseTransientBottomBar.this.G(this.a);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            BaseTransientBottomBar.this.f1692d.animateContentOut(0, Constants.GETFIELD);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements ValueAnimator.AnimatorUpdateListener {
        private int a = 0;

        i() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (BaseTransientBottomBar.v) {
                ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.f1691c, intValue - this.a);
            } else {
                BaseTransientBottomBar.this.f1691c.setTranslationY(intValue);
            }
            this.a = intValue;
        }
    }

    /* loaded from: classes.dex */
    static class j implements Handler.Callback {
        j() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                ((BaseTransientBottomBar) message.obj).L();
                return true;
            }
            if (i2 != 1) {
                return false;
            }
            ((BaseTransientBottomBar) message.obj).E(message.arg1);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class k implements ViewTreeObserver.OnGlobalLayoutListener {
        k() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (BaseTransientBottomBar.this.h) {
                BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
                baseTransientBottomBar.p = baseTransientBottomBar.u();
                BaseTransientBottomBar.this.R();
            }
        }
    }

    /* loaded from: classes.dex */
    class l implements Runnable {
        l() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int z;
            BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
            if (baseTransientBottomBar.f1691c == null || baseTransientBottomBar.b == null || (z = (BaseTransientBottomBar.this.z() - BaseTransientBottomBar.this.C()) + ((int) BaseTransientBottomBar.this.f1691c.getTranslationY())) >= BaseTransientBottomBar.this.o) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = BaseTransientBottomBar.this.f1691c.getLayoutParams();
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                Log.w(BaseTransientBottomBar.x, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                return;
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += BaseTransientBottomBar.this.o - z;
            BaseTransientBottomBar.this.f1691c.requestLayout();
        }
    }

    /* loaded from: classes.dex */
    class m implements OnApplyWindowInsetsListener {
        m() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        @NonNull
        public WindowInsetsCompat onApplyWindowInsets(View view, @NonNull WindowInsetsCompat windowInsetsCompat) {
            BaseTransientBottomBar.this.l = windowInsetsCompat.getSystemWindowInsetBottom();
            BaseTransientBottomBar.this.m = windowInsetsCompat.getSystemWindowInsetLeft();
            BaseTransientBottomBar.this.n = windowInsetsCompat.getSystemWindowInsetRight();
            BaseTransientBottomBar.this.R();
            return windowInsetsCompat;
        }
    }

    /* loaded from: classes.dex */
    class n extends AccessibilityDelegateCompat {
        n() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.addAction(1048576);
            accessibilityNodeInfoCompat.setDismissable(true);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            if (i2 != 1048576) {
                return super.performAccessibilityAction(view, i2, bundle);
            }
            BaseTransientBottomBar.this.dismiss();
            return true;
        }
    }

    /* loaded from: classes.dex */
    class o implements a.b {
        o() {
        }

        @Override // com.google.android.material.snackbar.a.b
        public void a(int i2) {
            Handler handler = BaseTransientBottomBar.u;
            handler.sendMessage(handler.obtainMessage(1, i2, 0, BaseTransientBottomBar.this));
        }

        @Override // com.google.android.material.snackbar.a.b
        public void show() {
            Handler handler = BaseTransientBottomBar.u;
            handler.sendMessage(handler.obtainMessage(0, BaseTransientBottomBar.this));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p implements OnAttachStateChangeListener {

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseTransientBottomBar.this.G(3);
            }
        }

        p() {
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            WindowInsets rootWindowInsets;
            if (Build.VERSION.SDK_INT < 29 || (rootWindowInsets = BaseTransientBottomBar.this.f1691c.getRootWindowInsets()) == null) {
                return;
            }
            BaseTransientBottomBar.this.o = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
            BaseTransientBottomBar.this.R();
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (BaseTransientBottomBar.this.isShownOrQueued()) {
                BaseTransientBottomBar.u.post(new a());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q implements OnLayoutChangeListener {
        q() {
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5) {
            BaseTransientBottomBar.this.f1691c.setOnLayoutChangeListener(null);
            BaseTransientBottomBar.this.M();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class r implements SwipeDismissBehavior.OnDismissListener {
        r() {
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
        public void onDismiss(@NonNull View view) {
            if (view.getParent() != null) {
                view.setVisibility(8);
            }
            BaseTransientBottomBar.this.v(0);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
        public void onDragStateChanged(int i2) {
            if (i2 == 0) {
                com.google.android.material.snackbar.a.c().l(BaseTransientBottomBar.this.t);
            } else if (i2 == 1 || i2 == 2) {
                com.google.android.material.snackbar.a.c().k(BaseTransientBottomBar.this.t);
            }
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        v = i2 >= 16 && i2 <= 19;
        w = new int[]{R.attr.snackbarStyle};
        x = BaseTransientBottomBar.class.getSimpleName();
        u = new Handler(Looper.getMainLooper(), new j());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseTransientBottomBar(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull View view, @NonNull com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (contentViewCallback == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
        this.a = viewGroup;
        this.f1692d = contentViewCallback;
        this.b = context;
        ThemeEnforcement.checkAppCompatTheme(context);
        SnackbarBaseLayout snackbarBaseLayout = (SnackbarBaseLayout) LayoutInflater.from(context).inflate(A(), viewGroup, false);
        this.f1691c = snackbarBaseLayout;
        if (view instanceof SnackbarContentLayout) {
            ((SnackbarContentLayout) view).a(snackbarBaseLayout.getActionTextColorAlpha());
        }
        snackbarBaseLayout.addView(view);
        ViewGroup.LayoutParams layoutParams = snackbarBaseLayout.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            this.k = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }
        ViewCompat.setAccessibilityLiveRegion(snackbarBaseLayout, 1);
        ViewCompat.setImportantForAccessibility(snackbarBaseLayout, 1);
        ViewCompat.setFitsSystemWindows(snackbarBaseLayout, true);
        ViewCompat.setOnApplyWindowInsetsListener(snackbarBaseLayout, new m());
        ViewCompat.setAccessibilityDelegate(snackbarBaseLayout, new n());
        this.s = (AccessibilityManager) context.getSystemService("accessibility");
    }

    private int B() {
        int height = this.f1691c.getHeight();
        ViewGroup.LayoutParams layoutParams = this.f1691c.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int C() {
        int[] iArr = new int[2];
        this.f1691c.getLocationOnScreen(iArr);
        return iArr[1] + this.f1691c.getHeight();
    }

    private boolean F() {
        ViewGroup.LayoutParams layoutParams = this.f1691c.getLayoutParams();
        return (layoutParams instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof SwipeDismissBehavior);
    }

    private void I(CoordinatorLayout.LayoutParams layoutParams) {
        SwipeDismissBehavior<? extends View> swipeDismissBehavior = this.r;
        if (swipeDismissBehavior == null) {
            swipeDismissBehavior = x();
        }
        if (swipeDismissBehavior instanceof Behavior) {
            ((Behavior) swipeDismissBehavior).g(this);
        }
        swipeDismissBehavior.setListener(new r());
        layoutParams.setBehavior(swipeDismissBehavior);
        if (this.g == null) {
            layoutParams.insetEdge = 80;
        }
    }

    private boolean K() {
        return this.o > 0 && !this.f && F();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        if (J()) {
            s();
            return;
        }
        if (this.f1691c.getParent() != null) {
            this.f1691c.setVisibility(0);
        }
        H();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        ValueAnimator w2 = w(0.0f, 1.0f);
        ValueAnimator y = y(0.8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(w2, y);
        animatorSet.setDuration(150L);
        animatorSet.addListener(new b());
        animatorSet.start();
    }

    private void O(int i2) {
        ValueAnimator w2 = w(1.0f, 0.0f);
        w2.setDuration(75L);
        w2.addListener(new c(i2));
        w2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        int B = B();
        if (v) {
            ViewCompat.offsetTopAndBottom(this.f1691c, B);
        } else {
            this.f1691c.setTranslationY(B);
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(B, 0);
        valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        valueAnimator.setDuration(250L);
        valueAnimator.addListener(new f());
        valueAnimator.addUpdateListener(new g(B));
        valueAnimator.start();
    }

    private void Q(int i2) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, B());
        valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        valueAnimator.setDuration(250L);
        valueAnimator.addListener(new h(i2));
        valueAnimator.addUpdateListener(new i());
        valueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        Rect rect;
        ViewGroup.LayoutParams layoutParams = this.f1691c.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams) || (rect = this.k) == null) {
            Log.w(x, "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.bottomMargin = rect.bottom + (this.g != null ? this.p : this.l);
        marginLayoutParams.leftMargin = rect.left + this.m;
        marginLayoutParams.rightMargin = rect.right + this.n;
        this.f1691c.requestLayout();
        if (Build.VERSION.SDK_INT < 29 || !K()) {
            return;
        }
        this.f1691c.removeCallbacks(this.j);
        this.f1691c.post(this.j);
    }

    private void t(int i2) {
        if (this.f1691c.getAnimationMode() == 1) {
            O(i2);
        } else {
            Q(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int u() {
        View view = this.g;
        if (view == null) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[1];
        int[] iArr2 = new int[2];
        this.a.getLocationOnScreen(iArr2);
        return (iArr2[1] + this.a.getHeight()) - i2;
    }

    private ValueAnimator w(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ofFloat.addUpdateListener(new d());
        return ofFloat;
    }

    private ValueAnimator y(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        ofFloat.addUpdateListener(new e());
        return ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(17)
    public int z() {
        WindowManager windowManager = (WindowManager) this.b.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @LayoutRes
    protected int A() {
        return D() ? R.layout.mtrl_layout_snackbar : R.layout.design_layout_snackbar;
    }

    protected boolean D() {
        TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(w);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        return resourceId != -1;
    }

    final void E(int i2) {
        if (J() && this.f1691c.getVisibility() == 0) {
            t(i2);
        } else {
            G(i2);
        }
    }

    void G(int i2) {
        com.google.android.material.snackbar.a.c().i(this.t);
        List<BaseCallback<B>> list = this.q;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.q.get(size).onDismissed(this, i2);
            }
        }
        ViewParent parent = this.f1691c.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.f1691c);
        }
    }

    void H() {
        com.google.android.material.snackbar.a.c().j(this.t);
        List<BaseCallback<B>> list = this.q;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.q.get(size).onShown(this);
            }
        }
    }

    boolean J() {
        AccessibilityManager accessibilityManager = this.s;
        if (accessibilityManager == null) {
            return true;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    final void L() {
        this.f1691c.setOnAttachStateChangeListener(new p());
        if (this.f1691c.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.f1691c.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                I((CoordinatorLayout.LayoutParams) layoutParams);
            }
            this.p = u();
            R();
            this.f1691c.setVisibility(4);
            this.a.addView(this.f1691c);
        }
        if (ViewCompat.isLaidOut(this.f1691c)) {
            M();
        } else {
            this.f1691c.setOnLayoutChangeListener(new q());
        }
    }

    @NonNull
    public B addCallback(@Nullable BaseCallback<B> baseCallback) {
        if (baseCallback == null) {
            return this;
        }
        if (this.q == null) {
            this.q = new ArrayList();
        }
        this.q.add(baseCallback);
        return this;
    }

    public void dismiss() {
        v(3);
    }

    @Nullable
    public View getAnchorView() {
        return this.g;
    }

    public int getAnimationMode() {
        return this.f1691c.getAnimationMode();
    }

    public Behavior getBehavior() {
        return this.r;
    }

    @NonNull
    public Context getContext() {
        return this.b;
    }

    public int getDuration() {
        return this.e;
    }

    @NonNull
    public View getView() {
        return this.f1691c;
    }

    public boolean isAnchorViewLayoutListenerEnabled() {
        return this.h;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.f;
    }

    public boolean isShown() {
        return com.google.android.material.snackbar.a.c().e(this.t);
    }

    public boolean isShownOrQueued() {
        return com.google.android.material.snackbar.a.c().f(this.t);
    }

    @NonNull
    public B removeCallback(@Nullable BaseCallback<B> baseCallback) {
        List<BaseCallback<B>> list;
        if (baseCallback == null || (list = this.q) == null) {
            return this;
        }
        list.remove(baseCallback);
        return this;
    }

    void s() {
        this.f1691c.post(new a());
    }

    @NonNull
    public B setAnchorView(@IdRes int i2) {
        View findViewById = this.a.findViewById(i2);
        if (findViewById != null) {
            return setAnchorView(findViewById);
        }
        throw new IllegalArgumentException("Unable to find anchor view with id: " + i2);
    }

    @NonNull
    public B setAnchorView(@Nullable View view) {
        ViewUtils.removeOnGlobalLayoutListener(this.g, this.f1693i);
        this.g = view;
        ViewUtils.addOnGlobalLayoutListener(view, this.f1693i);
        return this;
    }

    public void setAnchorViewLayoutListenerEnabled(boolean z) {
        this.h = z;
    }

    @NonNull
    public B setAnimationMode(int i2) {
        this.f1691c.setAnimationMode(i2);
        return this;
    }

    @NonNull
    public B setBehavior(Behavior behavior) {
        this.r = behavior;
        return this;
    }

    @NonNull
    public B setDuration(int i2) {
        this.e = i2;
        return this;
    }

    @NonNull
    public B setGestureInsetBottomIgnored(boolean z) {
        this.f = z;
        return this;
    }

    public void show() {
        com.google.android.material.snackbar.a.c().n(getDuration(), this.t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void v(int i2) {
        com.google.android.material.snackbar.a.c().b(this.t, i2);
    }

    @NonNull
    protected SwipeDismissBehavior<? extends View> x() {
        return new Behavior();
    }
}
