package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.ViewPager;
import java.lang.ref.WeakReference;
import java.util.Locale;

@ViewPager.DecorView
/* loaded from: classes.dex */
public class PagerTitleStrip extends ViewGroup {
    private static final int[] o = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};
    private static final int[] p = {R.attr.textAllCaps};
    ViewPager a;
    TextView b;

    /* renamed from: c, reason: collision with root package name */
    TextView f1128c;

    /* renamed from: d, reason: collision with root package name */
    TextView f1129d;
    private int e;
    float f;
    private int g;
    private int h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1130i;
    private boolean j;
    private final a k;
    private WeakReference<PagerAdapter> l;
    private int m;
    int n;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a extends DataSetObserver implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
        private int a;

        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            PagerTitleStrip.this.a(pagerAdapter, pagerAdapter2);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.b(pagerTitleStrip.a.getCurrentItem(), PagerTitleStrip.this.a.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f = pagerTitleStrip2.f;
            if (f < 0.0f) {
                f = 0.0f;
            }
            pagerTitleStrip2.c(pagerTitleStrip2.a.getCurrentItem(), f, true);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            this.a = i2;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (f > 0.5f) {
                i2++;
            }
            PagerTitleStrip.this.c(i2, f, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.a == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.b(pagerTitleStrip.a.getCurrentItem(), PagerTitleStrip.this.a.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f = pagerTitleStrip2.f;
                if (f < 0.0f) {
                    f = 0.0f;
                }
                pagerTitleStrip2.c(pagerTitleStrip2.a.getCurrentItem(), f, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends SingleLineTransformationMethod {
        private Locale a;

        b(Context context) {
            this.a = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.a);
            }
            return null;
        }
    }

    public PagerTitleStrip(@NonNull Context context) {
        this(context, null);
    }

    public PagerTitleStrip(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = -1;
        this.f = -1.0f;
        this.k = new a();
        TextView textView = new TextView(context);
        this.b = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f1128c = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.f1129d = textView3;
        addView(textView3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o);
        boolean z = false;
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            TextViewCompat.setTextAppearance(this.b, resourceId);
            TextViewCompat.setTextAppearance(this.f1128c, resourceId);
            TextViewCompat.setTextAppearance(this.f1129d, resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            int color = obtainStyledAttributes.getColor(2, 0);
            this.b.setTextColor(color);
            this.f1128c.setTextColor(color);
            this.f1129d.setTextColor(color);
        }
        this.h = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.n = this.f1128c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        this.f1128c.setEllipsize(TextUtils.TruncateAt.END);
        this.f1129d.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, p);
            z = obtainStyledAttributes2.getBoolean(0, false);
            obtainStyledAttributes2.recycle();
        }
        TextView textView4 = this.b;
        if (z) {
            setSingleLineAllCaps(textView4);
            setSingleLineAllCaps(this.f1128c);
            setSingleLineAllCaps(this.f1129d);
        } else {
            textView4.setSingleLine();
            this.f1128c.setSingleLine();
            this.f1129d.setSingleLine();
        }
        this.g = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        textView.setTransformationMethod(new b(textView.getContext()));
    }

    void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
        if (pagerAdapter != null) {
            pagerAdapter.unregisterDataSetObserver(this.k);
            this.l = null;
        }
        if (pagerAdapter2 != null) {
            pagerAdapter2.registerDataSetObserver(this.k);
            this.l = new WeakReference<>(pagerAdapter2);
        }
        ViewPager viewPager = this.a;
        if (viewPager != null) {
            this.e = -1;
            this.f = -1.0f;
            b(viewPager.getCurrentItem(), pagerAdapter2);
            requestLayout();
        }
    }

    void b(int i2, PagerAdapter pagerAdapter) {
        int count = pagerAdapter != null ? pagerAdapter.getCount() : 0;
        this.f1130i = true;
        CharSequence charSequence = null;
        this.b.setText((i2 < 1 || pagerAdapter == null) ? null : pagerAdapter.getPageTitle(i2 - 1));
        this.f1128c.setText((pagerAdapter == null || i2 >= count) ? null : pagerAdapter.getPageTitle(i2));
        int i3 = i2 + 1;
        if (i3 < count && pagerAdapter != null) {
            charSequence = pagerAdapter.getPageTitle(i3);
        }
        this.f1129d.setText(charSequence);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.b.measure(makeMeasureSpec, makeMeasureSpec2);
        this.f1128c.measure(makeMeasureSpec, makeMeasureSpec2);
        this.f1129d.measure(makeMeasureSpec, makeMeasureSpec2);
        this.e = i2;
        if (!this.j) {
            c(i2, this.f, false);
        }
        this.f1130i = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i2, float f, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (i2 != this.e) {
            b(i2, this.a.getAdapter());
        } else if (!z && f == this.f) {
            return;
        }
        this.j = true;
        int measuredWidth = this.b.getMeasuredWidth();
        int measuredWidth2 = this.f1128c.getMeasuredWidth();
        int measuredWidth3 = this.f1129d.getMeasuredWidth();
        int i7 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i8 = paddingRight + i7;
        int i9 = (width - (paddingLeft + i7)) - i8;
        float f2 = 0.5f + f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        int i10 = ((width - i8) - ((int) (i9 * f2))) - i7;
        int i11 = measuredWidth2 + i10;
        int baseline = this.b.getBaseline();
        int baseline2 = this.f1128c.getBaseline();
        int baseline3 = this.f1129d.getBaseline();
        int max = Math.max(Math.max(baseline, baseline2), baseline3);
        int i12 = max - baseline;
        int i13 = max - baseline2;
        int i14 = max - baseline3;
        int max2 = Math.max(Math.max(this.b.getMeasuredHeight() + i12, this.f1128c.getMeasuredHeight() + i13), this.f1129d.getMeasuredHeight() + i14);
        int i15 = this.h & 112;
        if (i15 == 16) {
            i3 = (((height - paddingTop) - paddingBottom) - max2) / 2;
        } else {
            if (i15 != 80) {
                i4 = i12 + paddingTop;
                i5 = i13 + paddingTop;
                i6 = paddingTop + i14;
                TextView textView = this.f1128c;
                textView.layout(i10, i5, i11, textView.getMeasuredHeight() + i5);
                int min = Math.min(paddingLeft, (i10 - this.g) - measuredWidth);
                TextView textView2 = this.b;
                textView2.layout(min, i4, measuredWidth + min, textView2.getMeasuredHeight() + i4);
                int max3 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.g);
                TextView textView3 = this.f1129d;
                textView3.layout(max3, i6, max3 + measuredWidth3, textView3.getMeasuredHeight() + i6);
                this.f = f;
                this.j = false;
            }
            i3 = (height - paddingBottom) - max2;
        }
        i4 = i12 + i3;
        i5 = i13 + i3;
        i6 = i3 + i14;
        TextView textView4 = this.f1128c;
        textView4.layout(i10, i5, i11, textView4.getMeasuredHeight() + i5);
        int min2 = Math.min(paddingLeft, (i10 - this.g) - measuredWidth);
        TextView textView22 = this.b;
        textView22.layout(min2, i4, measuredWidth + min2, textView22.getMeasuredHeight() + i4);
        int max32 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.g);
        TextView textView32 = this.f1129d;
        textView32.layout(max32, i6, max32 + measuredWidth3, textView32.getMeasuredHeight() + i6);
        this.f = f;
        this.j = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.g;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        PagerAdapter adapter = viewPager.getAdapter();
        viewPager.setInternalPageChangeListener(this.k);
        viewPager.addOnAdapterChangeListener(this.k);
        this.a = viewPager;
        WeakReference<PagerAdapter> weakReference = this.l;
        a(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.a;
        if (viewPager != null) {
            a(viewPager.getAdapter(), null);
            this.a.setInternalPageChangeListener(null);
            this.a.removeOnAdapterChangeListener(this.k);
            this.a = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.a != null) {
            float f = this.f;
            if (f < 0.0f) {
                f = 0.0f;
            }
            c(this.e, f, true);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int max;
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i2);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, (int) (size * 0.2f), -2);
        this.b.measure(childMeasureSpec2, childMeasureSpec);
        this.f1128c.measure(childMeasureSpec2, childMeasureSpec);
        this.f1129d.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i3) == 1073741824) {
            max = View.MeasureSpec.getSize(i3);
        } else {
            max = Math.max(getMinHeight(), this.f1128c.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(max, i3, this.f1128c.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f1130i) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i2) {
        this.h = i2;
        requestLayout();
    }

    public void setNonPrimaryAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        int i2 = ((int) (f * 255.0f)) & 255;
        this.m = i2;
        int i3 = (i2 << 24) | (this.n & 16777215);
        this.b.setTextColor(i3);
        this.f1129d.setTextColor(i3);
    }

    public void setTextColor(@ColorInt int i2) {
        this.n = i2;
        this.f1128c.setTextColor(i2);
        int i3 = (this.m << 24) | (this.n & 16777215);
        this.b.setTextColor(i3);
        this.f1129d.setTextColor(i3);
    }

    public void setTextSize(int i2, float f) {
        this.b.setTextSize(i2, f);
        this.f1128c.setTextSize(i2, f);
        this.f1129d.setTextSize(i2, f);
    }

    public void setTextSpacing(int i2) {
        this.g = i2;
        requestLayout();
    }
}
