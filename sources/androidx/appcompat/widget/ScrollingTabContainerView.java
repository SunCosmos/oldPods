package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    private static final Interpolator l = new DecelerateInterpolator();
    Runnable a;
    private c b;

    /* renamed from: c */
    LinearLayoutCompat f184c;

    /* renamed from: d */
    private Spinner f185d;
    private boolean e;
    int f;
    int g;
    private int h;

    /* renamed from: i */
    private int f186i;
    protected ViewPropertyAnimator j;
    protected final VisibilityAnimListener k;

    /* loaded from: classes.dex */
    public class VisibilityAnimListener extends AnimatorListenerAdapter {
        private boolean a = false;
        private int b;

        protected VisibilityAnimListener() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.a) {
                return;
            }
            ScrollingTabContainerView scrollingTabContainerView = ScrollingTabContainerView.this;
            scrollingTabContainerView.j = null;
            scrollingTabContainerView.setVisibility(this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.a = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimator viewPropertyAnimator, int i2) {
            this.b = i2;
            ScrollingTabContainerView.this.j = viewPropertyAnimator;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ View a;

        a(View view) {
            this.a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollingTabContainerView.this.smoothScrollTo(this.a.getLeft() - ((ScrollingTabContainerView.this.getWidth() - this.a.getWidth()) / 2), 0);
            ScrollingTabContainerView.this.a = null;
        }
    }

    /* loaded from: classes.dex */
    public class b extends BaseAdapter {
        b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.f184c.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return ((d) ScrollingTabContainerView.this.f184c.getChildAt(i2)).b();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                return ScrollingTabContainerView.this.c((ActionBar.Tab) getItem(i2), true);
            }
            ((d) view).a((ActionBar.Tab) getItem(i2));
            return view;
        }
    }

    /* loaded from: classes.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).b().select();
            int childCount = ScrollingTabContainerView.this.f184c.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = ScrollingTabContainerView.this.f184c.getChildAt(i2);
                childAt.setSelected(childAt == view);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d extends LinearLayout {
        private final int[] a;
        private ActionBar.Tab b;

        /* renamed from: c */
        private TextView f188c;

        /* renamed from: d */
        private ImageView f189d;
        private View e;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public d(android.content.Context r6, androidx.appcompat.app.ActionBar.Tab r7, boolean r8) {
            /*
                r4 = this;
                androidx.appcompat.widget.ScrollingTabContainerView.this = r5
                int r5 = androidx.appcompat.R.attr.actionBarTabStyle
                r0 = 0
                r4.<init>(r6, r0, r5)
                r1 = 1
                int[] r1 = new int[r1]
                r2 = 16842964(0x10100d4, float:2.3694152E-38)
                r3 = 0
                r1[r3] = r2
                r4.a = r1
                r4.b = r7
                androidx.appcompat.widget.TintTypedArray r5 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r6, r0, r1, r5, r3)
                boolean r6 = r5.hasValue(r3)
                if (r6 == 0) goto L26
                android.graphics.drawable.Drawable r6 = r5.getDrawable(r3)
                r4.setBackgroundDrawable(r6)
            L26:
                r5.recycle()
                if (r8 == 0) goto L31
                r5 = 8388627(0x800013, float:1.175497E-38)
                r4.setGravity(r5)
            L31:
                r4.c()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ScrollingTabContainerView.d.<init>(androidx.appcompat.widget.ScrollingTabContainerView, android.content.Context, androidx.appcompat.app.ActionBar$Tab, boolean):void");
        }

        public void a(ActionBar.Tab tab) {
            this.b = tab;
            c();
        }

        public ActionBar.Tab b() {
            return this.b;
        }

        public void c() {
            ActionBar.Tab tab = this.b;
            View customView = tab.getCustomView();
            if (customView != null) {
                ViewParent parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(customView);
                    }
                    addView(customView);
                }
                this.e = customView;
                TextView textView = this.f188c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f189d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f189d.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.e;
            if (view != null) {
                removeView(view);
                this.e = null;
            }
            Drawable icon = tab.getIcon();
            CharSequence text = tab.getText();
            if (icon != null) {
                if (this.f189d == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f189d = appCompatImageView;
                }
                this.f189d.setImageDrawable(icon);
                this.f189d.setVisibility(0);
            } else {
                ImageView imageView2 = this.f189d;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f189d.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(text);
            if (z) {
                if (this.f188c == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, R.attr.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f188c = appCompatTextView;
                }
                this.f188c.setText(text);
                this.f188c.setVisibility(0);
            } else {
                TextView textView2 = this.f188c;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f188c.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f189d;
            if (imageView3 != null) {
                imageView3.setContentDescription(tab.getContentDescription());
            }
            TooltipCompat.setTooltipText(this, z ? null : tab.getContentDescription());
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            if (ScrollingTabContainerView.this.f > 0) {
                int measuredWidth = getMeasuredWidth();
                int i4 = ScrollingTabContainerView.this.f;
                if (measuredWidth > i4) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i4, BasicMeasure.EXACTLY), i3);
                }
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }
    }

    public ScrollingTabContainerView(@NonNull Context context) {
        super(context);
        this.k = new VisibilityAnimListener();
        setHorizontalScrollBarEnabled(false);
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(context);
        setContentHeight(actionBarPolicy.getTabContainerHeight());
        this.g = actionBarPolicy.getStackedTabMaxWidth();
        LinearLayoutCompat b2 = b();
        this.f184c = b2;
        addView(b2, new ViewGroup.LayoutParams(-2, -1));
    }

    private Spinner a() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, R.attr.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    private LinearLayoutCompat b() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, R.attr.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        return linearLayoutCompat;
    }

    private boolean d() {
        Spinner spinner = this.f185d;
        return spinner != null && spinner.getParent() == this;
    }

    private void e() {
        if (d()) {
            return;
        }
        if (this.f185d == null) {
            this.f185d = a();
        }
        removeView(this.f184c);
        addView(this.f185d, new ViewGroup.LayoutParams(-2, -1));
        if (this.f185d.getAdapter() == null) {
            this.f185d.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.a;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.a = null;
        }
        this.f185d.setSelection(this.f186i);
    }

    private boolean f() {
        if (!d()) {
            return false;
        }
        removeView(this.f185d);
        addView(this.f184c, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f185d.getSelectedItemPosition());
        return false;
    }

    public void addTab(ActionBar.Tab tab, int i2, boolean z) {
        d c2 = c(tab, false);
        this.f184c.addView(c2, i2, new LinearLayoutCompat.LayoutParams(0, -1, 1.0f));
        Spinner spinner = this.f185d;
        if (spinner != null) {
            ((b) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (z) {
            c2.setSelected(true);
        }
        if (this.e) {
            requestLayout();
        }
    }

    public void addTab(ActionBar.Tab tab, boolean z) {
        d c2 = c(tab, false);
        this.f184c.addView(c2, new LinearLayoutCompat.LayoutParams(0, -1, 1.0f));
        Spinner spinner = this.f185d;
        if (spinner != null) {
            ((b) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (z) {
            c2.setSelected(true);
        }
        if (this.e) {
            requestLayout();
        }
    }

    public void animateToTab(int i2) {
        View childAt = this.f184c.getChildAt(i2);
        Runnable runnable = this.a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.a = aVar;
        post(aVar);
    }

    public void animateToVisibility(int i2) {
        ViewPropertyAnimator alpha;
        ViewPropertyAnimator viewPropertyAnimator = this.j;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        if (i2 == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            alpha = animate().alpha(1.0f);
        } else {
            alpha = animate().alpha(0.0f);
        }
        alpha.setDuration(200L);
        alpha.setInterpolator(l);
        alpha.setListener(this.k.withFinalVisibility(alpha, i2));
        alpha.start();
    }

    d c(ActionBar.Tab tab, boolean z) {
        d dVar = new d(this, getContext(), tab, z);
        if (z) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.h));
        } else {
            dVar.setFocusable(true);
            if (this.b == null) {
                this.b = new c();
            }
            dVar.setOnClickListener(this.b);
        }
        return dVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.a;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(getContext());
        setContentHeight(actionBarPolicy.getTabContainerHeight());
        this.g = actionBarPolicy.getStackedTabMaxWidth();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.a;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
        ((d) view).b().select();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        int mode = View.MeasureSpec.getMode(i2);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.f184c.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            i4 = -1;
        } else {
            if (childCount > 2) {
                this.f = (int) (View.MeasureSpec.getSize(i2) * 0.4f);
            } else {
                this.f = View.MeasureSpec.getSize(i2) / 2;
            }
            i4 = Math.min(this.f, this.g);
        }
        this.f = i4;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.h, BasicMeasure.EXACTLY);
        if (!z && this.e) {
            this.f184c.measure(0, makeMeasureSpec);
            if (this.f184c.getMeasuredWidth() > View.MeasureSpec.getSize(i2)) {
                e();
                int measuredWidth = getMeasuredWidth();
                super.onMeasure(i2, makeMeasureSpec);
                int measuredWidth2 = getMeasuredWidth();
                if (z || measuredWidth == measuredWidth2) {
                }
                setTabSelected(this.f186i);
                return;
            }
        }
        f();
        int measuredWidth3 = getMeasuredWidth();
        super.onMeasure(i2, makeMeasureSpec);
        int measuredWidth22 = getMeasuredWidth();
        if (z) {
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void removeAllTabs() {
        this.f184c.removeAllViews();
        Spinner spinner = this.f185d;
        if (spinner != null) {
            ((b) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.e) {
            requestLayout();
        }
    }

    public void removeTabAt(int i2) {
        this.f184c.removeViewAt(i2);
        Spinner spinner = this.f185d;
        if (spinner != null) {
            ((b) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.e) {
            requestLayout();
        }
    }

    public void setAllowCollapse(boolean z) {
        this.e = z;
    }

    public void setContentHeight(int i2) {
        this.h = i2;
        requestLayout();
    }

    public void setTabSelected(int i2) {
        this.f186i = i2;
        int childCount = this.f184c.getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = this.f184c.getChildAt(i3);
            boolean z = i3 == i2;
            childAt.setSelected(z);
            if (z) {
                animateToTab(i2);
            }
            i3++;
        }
        Spinner spinner = this.f185d;
        if (spinner == null || i2 < 0) {
            return;
        }
        spinner.setSelection(i2);
    }

    public void updateTab(int i2) {
        ((d) this.f184c.getChildAt(i2)).c();
        Spinner spinner = this.f185d;
        if (spinner != null) {
            ((b) spinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.e) {
            requestLayout();
        }
    }
}
