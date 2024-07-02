package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class NavigationBarItemView extends FrameLayout implements MenuView.ItemView {
    private static final int[] q = {R.attr.state_checked};
    private final int a;
    private float b;

    /* renamed from: c, reason: collision with root package name */
    private float f1617c;

    /* renamed from: d, reason: collision with root package name */
    private float f1618d;
    private int e;
    private boolean f;
    private ImageView g;
    private final ViewGroup h;

    /* renamed from: i, reason: collision with root package name */
    private final TextView f1619i;
    private final TextView j;
    private int k;

    @Nullable
    private MenuItemImpl l;

    @Nullable
    private ColorStateList m;

    @Nullable
    private Drawable n;

    @Nullable
    private Drawable o;

    @Nullable
    private BadgeDrawable p;

    /* loaded from: classes.dex */
    class a implements View.OnLayoutChangeListener {
        a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (NavigationBarItemView.this.g.getVisibility() == 0) {
                NavigationBarItemView navigationBarItemView = NavigationBarItemView.this;
                navigationBarItemView.k(navigationBarItemView.g);
            }
        }
    }

    public NavigationBarItemView(@NonNull Context context) {
        super(context);
        this.k = -1;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) this, true);
        this.g = (ImageView) findViewById(com.google.android.material.R.id.navigation_bar_item_icon_view);
        ViewGroup viewGroup = (ViewGroup) findViewById(com.google.android.material.R.id.navigation_bar_item_labels_group);
        this.h = viewGroup;
        TextView textView = (TextView) findViewById(com.google.android.material.R.id.navigation_bar_item_small_label_view);
        this.f1619i = textView;
        TextView textView2 = (TextView) findViewById(com.google.android.material.R.id.navigation_bar_item_large_label_view);
        this.j = textView2;
        setBackgroundResource(getItemBackgroundResId());
        this.a = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        viewGroup.setTag(com.google.android.material.R.id.mtrl_view_tag_bottom_padding, Integer.valueOf(viewGroup.getPaddingBottom()));
        ViewCompat.setImportantForAccessibility(textView, 2);
        ViewCompat.setImportantForAccessibility(textView2, 2);
        setFocusable(true);
        c(textView.getTextSize(), textView2.getTextSize());
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new a());
        }
    }

    private void c(float f, float f2) {
        this.b = f - f2;
        this.f1617c = (f2 * 1.0f) / f;
        this.f1618d = (f * 1.0f) / f2;
    }

    @Nullable
    private FrameLayout d(View view) {
        ImageView imageView = this.g;
        if (view == imageView && BadgeUtils.USE_COMPAT_PARENT) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    private boolean e() {
        return this.p != null;
    }

    private static void g(@NonNull View view, int i2, int i3) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i2;
        layoutParams.gravity = i3;
        view.setLayoutParams(layoutParams);
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int indexOfChild = viewGroup.indexOfChild(this);
        int i2 = 0;
        for (int i3 = 0; i3 < indexOfChild; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i2++;
            }
        }
        return i2;
    }

    private int getSuggestedIconHeight() {
        BadgeDrawable badgeDrawable = this.p;
        int minimumHeight = badgeDrawable != null ? badgeDrawable.getMinimumHeight() / 2 : 0;
        return Math.max(minimumHeight, ((FrameLayout.LayoutParams) this.g.getLayoutParams()).topMargin) + this.g.getMeasuredWidth() + minimumHeight;
    }

    private int getSuggestedIconWidth() {
        BadgeDrawable badgeDrawable = this.p;
        int minimumWidth = badgeDrawable == null ? 0 : badgeDrawable.getMinimumWidth() - this.p.getHorizontalOffset();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.g.getLayoutParams();
        return Math.max(minimumWidth, layoutParams.leftMargin) + this.g.getMeasuredWidth() + Math.max(minimumWidth, layoutParams.rightMargin);
    }

    private static void h(@NonNull View view, float f, float f2, int i2) {
        view.setScaleX(f);
        view.setScaleY(f2);
        view.setVisibility(i2);
    }

    private void i(@Nullable View view) {
        if (e() && view != null) {
            setClipChildren(false);
            setClipToPadding(false);
            BadgeUtils.attachBadgeDrawable(this.p, view, d(view));
        }
    }

    private void j(@Nullable View view) {
        if (e()) {
            if (view != null) {
                setClipChildren(true);
                setClipToPadding(true);
                BadgeUtils.detachBadgeDrawable(this.p, view);
            }
            this.p = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(View view) {
        if (e()) {
            BadgeUtils.setBadgeDrawableBounds(this.p, view, d(view));
        }
    }

    private static void l(@NonNull View view, int i2) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        j(this.g);
    }

    @Nullable
    public BadgeDrawable getBadge() {
        return this.p;
    }

    @DrawableRes
    protected int getItemBackgroundResId() {
        return com.google.android.material.R.drawable.mtrl_navigation_bar_item_background;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    @Nullable
    public MenuItemImpl getItemData() {
        return this.l;
    }

    @DimenRes
    protected int getItemDefaultMarginResId() {
        return com.google.android.material.R.dimen.mtrl_navigation_bar_item_default_margin;
    }

    @LayoutRes
    protected abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.k;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.h.getLayoutParams();
        return getSuggestedIconHeight() + layoutParams.topMargin + this.h.getMeasuredHeight() + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.h.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), layoutParams.leftMargin + this.h.getMeasuredWidth() + layoutParams.rightMargin);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(@NonNull MenuItemImpl menuItemImpl, int i2) {
        this.l = menuItemImpl;
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitle());
        setId(menuItemImpl.getItemId());
        if (!TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(menuItemImpl.getContentDescription());
        }
        CharSequence tooltipText = !TextUtils.isEmpty(menuItemImpl.getTooltipText()) ? menuItemImpl.getTooltipText() : menuItemImpl.getTitle();
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 21 || i3 > 23) {
            TooltipCompat.setTooltipText(this, tooltipText);
        }
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
    }

    @Override // android.view.ViewGroup, android.view.View
    @NonNull
    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        MenuItemImpl menuItemImpl = this.l;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.l.isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, q);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        BadgeDrawable badgeDrawable = this.p;
        if (badgeDrawable != null && badgeDrawable.isVisible()) {
            CharSequence title = this.l.getTitle();
            if (!TextUtils.isEmpty(this.l.getContentDescription())) {
                title = this.l.getContentDescription();
            }
            accessibilityNodeInfo.setContentDescription(((Object) title) + ", " + ((Object) this.p.getContentDescription()));
        }
        AccessibilityNodeInfoCompat wrap = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo);
        wrap.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, getItemVisiblePosition(), 1, false, isSelected()));
        if (isSelected()) {
            wrap.setClickable(false);
            wrap.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }
        wrap.setRoleDescription(getResources().getString(com.google.android.material.R.string.item_view_role_description));
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBadge(@NonNull BadgeDrawable badgeDrawable) {
        this.p = badgeDrawable;
        ImageView imageView = this.g;
        if (imageView != null) {
            i(imageView);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0067, code lost:
    
        if (r9 != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0069, code lost:
    
        g(r8.g, (int) (r8.a + r8.b), 49);
        h(r8.j, 1.0f, 1.0f, 0);
        r0 = r8.f1619i;
        r1 = r8.f1617c;
        h(r0, r1, r1, 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0082, code lost:
    
        g(r8.g, r8.a, 49);
        r0 = r8.j;
        r1 = r8.f1618d;
        h(r0, r1, r1, 4);
        h(r8.f1619i, 1.0f, 1.0f, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x009a, code lost:
    
        if (r9 != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x009c, code lost:
    
        g(r0, r1, 49);
        r0 = r8.h;
        l(r0, ((java.lang.Integer) r0.getTag(com.google.android.material.R.id.mtrl_view_tag_bottom_padding)).intValue());
        r8.j.setVisibility(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00c3, code lost:
    
        r8.f1619i.setVisibility(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b6, code lost:
    
        g(r0, r1, 17);
        l(r8.h, 0);
        r8.j.setVisibility(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00d1, code lost:
    
        if (r9 != false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00e5, code lost:
    
        if (r9 != false) goto L12;
     */
    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setChecked(boolean r9) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setChecked(boolean):void");
    }

    @Override // android.view.View, androidx.appcompat.view.menu.MenuView.ItemView
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.f1619i.setEnabled(z);
        this.j.setEnabled(z);
        this.g.setEnabled(z);
        ViewCompat.setPointerIcon(this, z ? PointerIconCompat.getSystemIcon(getContext(), 1002) : null);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setIcon(@Nullable Drawable drawable) {
        if (drawable == this.n) {
            return;
        }
        this.n = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = DrawableCompat.wrap(drawable).mutate();
            this.o = drawable;
            ColorStateList colorStateList = this.m;
            if (colorStateList != null) {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
        }
        this.g.setImageDrawable(drawable);
    }

    public void setIconSize(int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.g.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.g.setLayoutParams(layoutParams);
    }

    public void setIconTintList(@Nullable ColorStateList colorStateList) {
        Drawable drawable;
        this.m = colorStateList;
        if (this.l == null || (drawable = this.o) == null) {
            return;
        }
        DrawableCompat.setTintList(drawable, colorStateList);
        this.o.invalidateSelf();
    }

    public void setItemBackground(int i2) {
        setItemBackground(i2 == 0 ? null : ContextCompat.getDrawable(getContext(), i2));
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        ViewCompat.setBackground(this, drawable);
    }

    public void setItemPosition(int i2) {
        this.k = i2;
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.e != i2) {
            this.e = i2;
            MenuItemImpl menuItemImpl = this.l;
            if (menuItemImpl != null) {
                setChecked(menuItemImpl.isChecked());
            }
        }
    }

    public void setShifting(boolean z) {
        if (this.f != z) {
            this.f = z;
            MenuItemImpl menuItemImpl = this.l;
            if (menuItemImpl != null) {
                setChecked(menuItemImpl.isChecked());
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setShortcut(boolean z, char c2) {
    }

    public void setTextAppearanceActive(@StyleRes int i2) {
        TextViewCompat.setTextAppearance(this.j, i2);
        c(this.f1619i.getTextSize(), this.j.getTextSize());
    }

    public void setTextAppearanceInactive(@StyleRes int i2) {
        TextViewCompat.setTextAppearance(this.f1619i, i2);
        c(this.f1619i.getTextSize(), this.j.getTextSize());
    }

    public void setTextColor(@Nullable ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.f1619i.setTextColor(colorStateList);
            this.j.setTextColor(colorStateList);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setTitle(@Nullable CharSequence charSequence) {
        this.f1619i.setText(charSequence);
        this.j.setText(charSequence);
        MenuItemImpl menuItemImpl = this.l;
        if (menuItemImpl == null || TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(charSequence);
        }
        MenuItemImpl menuItemImpl2 = this.l;
        if (menuItemImpl2 != null && !TextUtils.isEmpty(menuItemImpl2.getTooltipText())) {
            charSequence = this.l.getTooltipText();
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 21 || i2 > 23) {
            TooltipCompat.setTooltipText(this, charSequence);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean showsIcon() {
        return true;
    }
}
