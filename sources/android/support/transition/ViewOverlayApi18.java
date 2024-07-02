package android.support.transition;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOverlay;

@RequiresApi(18)
/* loaded from: lib/Mus.dex */
class ViewOverlayApi18 implements ViewOverlayImpl {
    private final ViewOverlay mViewOverlay;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewOverlayApi18(@NonNull View view) {
        this.mViewOverlay = view.getOverlay();
    }

    @Override // android.support.transition.ViewOverlayImpl
    public void add(@NonNull Drawable drawable) {
        this.mViewOverlay.add(drawable);
    }

    @Override // android.support.transition.ViewOverlayImpl
    public void clear() {
        this.mViewOverlay.clear();
    }

    @Override // android.support.transition.ViewOverlayImpl
    public void remove(@NonNull Drawable drawable) {
        this.mViewOverlay.remove(drawable);
    }
}
