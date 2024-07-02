package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(18)
/* loaded from: classes.dex */
class z implements a0 {
    private final ViewOverlay a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(@NonNull View view) {
        this.a = view.getOverlay();
    }

    @Override // androidx.transition.a0
    public void add(@NonNull Drawable drawable) {
        this.a.add(drawable);
    }

    @Override // androidx.transition.a0
    public void remove(@NonNull Drawable drawable) {
        this.a.remove(drawable);
    }
}
