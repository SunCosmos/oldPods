package androidx.transition;

import android.annotation.SuppressLint;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(22)
/* loaded from: classes.dex */
class e0 extends d0 {
    private static boolean j = true;

    @Override // androidx.transition.h0
    @SuppressLint({"NewApi"})
    public void f(@NonNull View view, int i2, int i3, int i4, int i5) {
        if (j) {
            try {
                view.setLeftTopRightBottom(i2, i3, i4, i5);
            } catch (NoSuchMethodError unused) {
                j = false;
            }
        }
    }
}
