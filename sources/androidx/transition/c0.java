package androidx.transition;

import android.annotation.SuppressLint;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(19)
/* loaded from: classes.dex */
class c0 extends h0 {
    private static boolean f = true;

    @Override // androidx.transition.h0
    public void a(@NonNull View view) {
    }

    @Override // androidx.transition.h0
    @SuppressLint({"NewApi"})
    public float c(@NonNull View view) {
        if (f) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f = false;
            }
        }
        return view.getAlpha();
    }

    @Override // androidx.transition.h0
    public void d(@NonNull View view) {
    }

    @Override // androidx.transition.h0
    @SuppressLint({"NewApi"})
    public void g(@NonNull View view, float f2) {
        if (f) {
            try {
                view.setTransitionAlpha(f2);
                return;
            } catch (NoSuchMethodError unused) {
                f = false;
            }
        }
        view.setAlpha(f2);
    }
}
