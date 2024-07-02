package androidx.transition;

import android.graphics.Matrix;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(29)
/* loaded from: classes.dex */
class g0 extends f0 {
    @Override // androidx.transition.c0, androidx.transition.h0
    public float c(@NonNull View view) {
        return view.getTransitionAlpha();
    }

    @Override // androidx.transition.d0, androidx.transition.h0
    public void e(@NonNull View view, @Nullable Matrix matrix) {
        view.setAnimationMatrix(matrix);
    }

    @Override // androidx.transition.e0, androidx.transition.h0
    public void f(@NonNull View view, int i2, int i3, int i4, int i5) {
        view.setLeftTopRightBottom(i2, i3, i4, i5);
    }

    @Override // androidx.transition.c0, androidx.transition.h0
    public void g(@NonNull View view, float f) {
        view.setTransitionAlpha(f);
    }

    @Override // androidx.transition.f0, androidx.transition.h0
    public void h(@NonNull View view, int i2) {
        view.setTransitionVisibility(i2);
    }

    @Override // androidx.transition.d0, androidx.transition.h0
    public void i(@NonNull View view, @NonNull Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // androidx.transition.d0, androidx.transition.h0
    public void j(@NonNull View view, @NonNull Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }
}
