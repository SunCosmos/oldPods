package android.support.transition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(14)
/* loaded from: lib/Mus.dex */
public class ViewGroupOverlayApi14 extends ViewOverlayApi14 implements ViewGroupOverlayImpl {
    public ViewGroupOverlayApi14(Context context, ViewGroup viewGroup, View view) {
        super(context, viewGroup, view);
    }

    public static ViewGroupOverlayApi14 createFrom(ViewGroup viewGroup) {
        return (ViewGroupOverlayApi14) ViewOverlayApi14.createFrom(viewGroup);
    }

    @Override // android.support.transition.ViewGroupOverlayImpl
    public void add(@NonNull View view) {
        this.mOverlayViewGroup.add(view);
    }

    @Override // android.support.transition.ViewGroupOverlayImpl
    public void remove(@NonNull View view) {
        this.mOverlayViewGroup.remove(view);
    }
}
