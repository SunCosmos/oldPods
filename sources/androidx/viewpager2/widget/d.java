package androidx.viewpager2.widget;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* loaded from: classes.dex */
public final class d extends ViewPager2.OnPageChangeCallback {
    private final LinearLayoutManager a;
    private ViewPager2.PageTransformer b;

    public d(LinearLayoutManager linearLayoutManager) {
        this.a = linearLayoutManager;
    }

    public ViewPager2.PageTransformer a() {
        return this.b;
    }

    public void b(@Nullable ViewPager2.PageTransformer pageTransformer) {
        this.b = pageTransformer;
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i2, float f, int i3) {
        if (this.b == null) {
            return;
        }
        float f2 = -f;
        for (int i4 = 0; i4 < this.a.getChildCount(); i4++) {
            View childAt = this.a.getChildAt(i4);
            if (childAt == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i4), Integer.valueOf(this.a.getChildCount())));
            }
            this.b.transformPage(childAt, (this.a.getPosition(childAt) - i2) + f2);
        }
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageSelected(int i2) {
    }
}
