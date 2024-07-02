package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.shape.MaterialShapeDrawable;

/* loaded from: classes.dex */
public class BottomSheetDialog extends AppCompatDialog {
    private BottomSheetBehavior<FrameLayout> a;
    private FrameLayout b;

    /* renamed from: c, reason: collision with root package name */
    private CoordinatorLayout f1498c;

    /* renamed from: d, reason: collision with root package name */
    private FrameLayout f1499d;
    boolean e;
    boolean f;
    private boolean g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private BottomSheetBehavior.BottomSheetCallback f1500i;
    private boolean j;

    @NonNull
    private BottomSheetBehavior.BottomSheetCallback k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements OnApplyWindowInsetsListener {
        a() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            if (BottomSheetDialog.this.f1500i != null) {
                BottomSheetDialog.this.a.removeBottomSheetCallback(BottomSheetDialog.this.f1500i);
            }
            if (windowInsetsCompat != null) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                bottomSheetDialog.f1500i = new f(bottomSheetDialog.f1499d, windowInsetsCompat, null);
                BottomSheetDialog.this.a.addBottomSheetCallback(BottomSheetDialog.this.f1500i);
            }
            return windowInsetsCompat;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
            if (bottomSheetDialog.f && bottomSheetDialog.isShowing() && BottomSheetDialog.this.g()) {
                BottomSheetDialog.this.cancel();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends AccessibilityDelegateCompat {
        c() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            boolean z;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (BottomSheetDialog.this.f) {
                accessibilityNodeInfoCompat.addAction(1048576);
                z = true;
            } else {
                z = false;
            }
            accessibilityNodeInfoCompat.setDismissable(z);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            if (i2 == 1048576) {
                BottomSheetDialog bottomSheetDialog = BottomSheetDialog.this;
                if (bottomSheetDialog.f) {
                    bottomSheetDialog.cancel();
                    return true;
                }
            }
            return super.performAccessibilityAction(view, i2, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements View.OnTouchListener {
        d(BottomSheetDialog bottomSheetDialog) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* loaded from: classes.dex */
    class e extends BottomSheetBehavior.BottomSheetCallback {
        e() {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onSlide(@NonNull View view, float f) {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onStateChanged(@NonNull View view, int i2) {
            if (i2 == 5) {
                BottomSheetDialog.this.cancel();
            }
        }
    }

    /* loaded from: classes.dex */
    private static class f extends BottomSheetBehavior.BottomSheetCallback {
        private final boolean a;
        private final boolean b;

        /* renamed from: c, reason: collision with root package name */
        private final WindowInsetsCompat f1502c;

        private f(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat) {
            int color;
            this.f1502c = windowInsetsCompat;
            boolean z = Build.VERSION.SDK_INT >= 23 && (view.getSystemUiVisibility() & 8192) != 0;
            this.b = z;
            MaterialShapeDrawable x = BottomSheetBehavior.from(view).x();
            ColorStateList fillColor = x != null ? x.getFillColor() : ViewCompat.getBackgroundTintList(view);
            if (fillColor != null) {
                color = fillColor.getDefaultColor();
            } else {
                if (!(view.getBackground() instanceof ColorDrawable)) {
                    this.a = z;
                    return;
                }
                color = ((ColorDrawable) view.getBackground()).getColor();
            }
            this.a = MaterialColors.isColorLight(color);
        }

        /* synthetic */ f(View view, WindowInsetsCompat windowInsetsCompat, a aVar) {
            this(view, windowInsetsCompat);
        }

        private void a(View view) {
            int paddingLeft;
            int i2;
            if (view.getTop() < this.f1502c.getSystemWindowInsetTop()) {
                BottomSheetDialog.setLightStatusBar(view, this.a);
                paddingLeft = view.getPaddingLeft();
                i2 = this.f1502c.getSystemWindowInsetTop() - view.getTop();
            } else {
                if (view.getTop() == 0) {
                    return;
                }
                BottomSheetDialog.setLightStatusBar(view, this.b);
                paddingLeft = view.getPaddingLeft();
                i2 = 0;
            }
            view.setPadding(paddingLeft, i2, view.getPaddingRight(), view.getPaddingBottom());
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onSlide(@NonNull View view, float f) {
            a(view);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onStateChanged(@NonNull View view, int i2) {
            a(view);
        }
    }

    public BottomSheetDialog(@NonNull Context context) {
        this(context, 0);
        this.j = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.enableEdgeToEdge}).getBoolean(0, false);
    }

    public BottomSheetDialog(@NonNull Context context, @StyleRes int i2) {
        super(context, getThemeResId(context, i2));
        this.f = true;
        this.g = true;
        this.k = new e();
        supportRequestWindowFeature(1);
        this.j = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.enableEdgeToEdge}).getBoolean(0, false);
    }

    private FrameLayout e() {
        if (this.b == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, null);
            this.b = frameLayout;
            this.f1498c = (CoordinatorLayout) frameLayout.findViewById(R.id.coordinator);
            FrameLayout frameLayout2 = (FrameLayout) this.b.findViewById(R.id.design_bottom_sheet);
            this.f1499d = frameLayout2;
            BottomSheetBehavior<FrameLayout> from = BottomSheetBehavior.from(frameLayout2);
            this.a = from;
            from.addBottomSheetCallback(this.k);
            this.a.setHideable(this.f);
        }
        return this.b;
    }

    private static int getThemeResId(@NonNull Context context, int i2) {
        if (i2 != 0) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, typedValue, true) ? typedValue.resourceId : R.style.Theme_Design_Light_BottomSheetDialog;
    }

    private View h(int i2, @Nullable View view, @Nullable ViewGroup.LayoutParams layoutParams) {
        e();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.b.findViewById(R.id.coordinator);
        if (i2 != 0 && view == null) {
            view = getLayoutInflater().inflate(i2, (ViewGroup) coordinatorLayout, false);
        }
        if (this.j) {
            ViewCompat.setOnApplyWindowInsetsListener(this.f1499d, new a());
        }
        this.f1499d.removeAllViews();
        FrameLayout frameLayout = this.f1499d;
        if (layoutParams == null) {
            frameLayout.addView(view);
        } else {
            frameLayout.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener(new b());
        ViewCompat.setAccessibilityDelegate(this.f1499d, new c());
        this.f1499d.setOnTouchListener(new d(this));
        return this.b;
    }

    public static void setLightStatusBar(@NonNull View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            int systemUiVisibility = view.getSystemUiVisibility();
            view.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        BottomSheetBehavior<FrameLayout> behavior = getBehavior();
        if (!this.e || behavior.getState() == 5) {
            super.cancel();
        } else {
            behavior.setState(5);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.a.removeBottomSheetCallback(this.k);
    }

    boolean g() {
        if (!this.h) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{android.R.attr.windowCloseOnTouchOutside});
            this.g = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
            this.h = true;
        }
        return this.g;
    }

    @NonNull
    public BottomSheetBehavior<FrameLayout> getBehavior() {
        if (this.a == null) {
            e();
        }
        return this.a;
    }

    public boolean getDismissWithAnimation() {
        return this.e;
    }

    public boolean getEdgeToEdgeEnabled() {
        return this.j;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        if (window == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        boolean z = this.j && Color.alpha(window.getNavigationBarColor()) < 255;
        FrameLayout frameLayout = this.b;
        if (frameLayout != null) {
            frameLayout.setFitsSystemWindows(!z);
        }
        CoordinatorLayout coordinatorLayout = this.f1498c;
        if (coordinatorLayout != null) {
            coordinatorLayout.setFitsSystemWindows(!z);
        }
        if (z) {
            window.getDecorView().setSystemUiVisibility(768);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 21) {
                window.setStatusBarColor(0);
                window.addFlags(Integer.MIN_VALUE);
                if (i2 < 23) {
                    window.addFlags(67108864);
                }
            }
            window.setLayout(-1, -1);
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.a;
        if (bottomSheetBehavior == null || bottomSheetBehavior.getState() != 5) {
            return;
        }
        this.a.setState(4);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.f != z) {
            this.f = z;
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = this.a;
            if (bottomSheetBehavior != null) {
                bottomSheetBehavior.setHideable(z);
            }
        }
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.f) {
            this.f = true;
        }
        this.g = z;
        this.h = true;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(@LayoutRes int i2) {
        super.setContentView(h(i2, null, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(h(0, view, null));
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(h(0, view, layoutParams));
    }

    public void setDismissWithAnimation(boolean z) {
        this.e = z;
    }
}
