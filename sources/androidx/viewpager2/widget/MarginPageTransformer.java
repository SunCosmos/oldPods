package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* loaded from: classes.dex */
public final class MarginPageTransformer implements ViewPager2.PageTransformer {
    private final int a;

    public MarginPageTransformer(@Px int i2) {
        Preconditions.checkArgumentNonnegative(i2, "Margin must be non-negative");
        this.a = i2;
    }

    private ViewPager2 a(@NonNull View view) {
        ViewParent parent = view.getParent();
        ViewParent parent2 = parent.getParent();
        if ((parent instanceof RecyclerView) && (parent2 instanceof ViewPager2)) {
            return (ViewPager2) parent2;
        }
        throw new IllegalStateException("Expected the page view to be managed by a ViewPager2 instance.");
    }

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(@NonNull View view, float f) {
        ViewPager2 a = a(view);
        float f2 = this.a * f;
        if (a.getOrientation() != 0) {
            view.setTranslationY(f2);
            return;
        }
        if (a.c()) {
            f2 = -f2;
        }
        view.setTranslationX(f2);
    }
}
