package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(18)
/* loaded from: lib/Mus.dex */
public class ViewUtilsApi18 extends ViewUtilsApi14 {
    @Override // android.support.transition.ViewUtilsApi14, android.support.transition.ViewUtilsImpl
    public ViewOverlayImpl getOverlay(@NonNull View view) {
        return new ViewOverlayApi18(view);
    }

    @Override // android.support.transition.ViewUtilsApi14, android.support.transition.ViewUtilsImpl
    public WindowIdImpl getWindowId(@NonNull View view) {
        return new WindowIdApi18(view);
    }
}
