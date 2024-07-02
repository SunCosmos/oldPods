package androidx.transition;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(23)
/* loaded from: classes.dex */
class f0 extends e0 {
    private static boolean k = true;

    @Override // androidx.transition.h0
    @SuppressLint({"NewApi"})
    public void h(@NonNull View view, int i2) {
        if (Build.VERSION.SDK_INT == 28) {
            super.h(view, i2);
        } else if (k) {
            try {
                view.setTransitionVisibility(i2);
            } catch (NoSuchMethodError unused) {
                k = false;
            }
        }
    }
}
