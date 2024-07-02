package androidx.core.view;

import android.view.View;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public interface NestedScrollingParent2 extends NestedScrollingParent {
    void onNestedPreScroll(@NonNull View view, int i2, int i3, @NonNull int[] iArr, int i4);

    void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6);

    void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i2, int i3);

    boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i2, int i3);

    void onStopNestedScroll(@NonNull View view, int i2);
}
