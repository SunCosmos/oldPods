package androidx.transition;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
class h {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static d a(@NonNull View view, @NonNull ViewGroup viewGroup, @Nullable Matrix matrix) {
        return Build.VERSION.SDK_INT == 28 ? f.b(view, viewGroup, matrix) : g.b(view, viewGroup, matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(View view) {
        if (Build.VERSION.SDK_INT == 28) {
            f.f(view);
        } else {
            g.f(view);
        }
    }
}
