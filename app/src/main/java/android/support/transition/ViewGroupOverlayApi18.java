package android.support.transition;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

@RequiresApi(18)
/* loaded from: lib/Mus.dex */
class ViewGroupOverlayApi18 implements ViewGroupOverlayImpl {
    private final ViewGroupOverlay mViewGroupOverlay;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewGroupOverlayApi18(@NonNull ViewGroup viewGroup) {
        this.mViewGroupOverlay = viewGroup.getOverlay();
    }

    @Override // android.support.transition.ViewOverlayImpl
    public void add(@NonNull Drawable drawable) {
        this.mViewGroupOverlay.add(drawable);
    }

    @Override // android.support.transition.ViewOverlayImpl
    public void clear() {
        this.mViewGroupOverlay.clear();
    }

    @Override // android.support.transition.ViewOverlayImpl
    public void remove(@NonNull Drawable drawable) {
        this.mViewGroupOverlay.remove(drawable);
    }

    @Override // android.support.transition.ViewGroupOverlayImpl
    public void add(@NonNull View view) {
        this.mViewGroupOverlay.add(view);
    }

    @Override // android.support.transition.ViewGroupOverlayImpl
    public void remove(@NonNull View view) {
        this.mViewGroupOverlay.remove(view);
    }
}
