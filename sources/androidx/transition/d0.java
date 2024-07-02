package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* loaded from: classes.dex */
class d0 extends c0 {
    private static boolean g = true;
    private static boolean h = true;

    /* renamed from: i, reason: collision with root package name */
    private static boolean f1074i = true;

    @Override // androidx.transition.h0
    @SuppressLint({"NewApi"})
    public void e(@NonNull View view, @Nullable Matrix matrix) {
        if (g) {
            try {
                view.setAnimationMatrix(matrix);
            } catch (NoSuchMethodError unused) {
                g = false;
            }
        }
    }

    @Override // androidx.transition.h0
    @SuppressLint({"NewApi"})
    public void i(@NonNull View view, @NonNull Matrix matrix) {
        if (h) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                h = false;
            }
        }
    }

    @Override // androidx.transition.h0
    @SuppressLint({"NewApi"})
    public void j(@NonNull View view, @NonNull Matrix matrix) {
        if (f1074i) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                f1074i = false;
            }
        }
    }
}
