package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(14)
/* loaded from: lib/Mus.dex */
interface GhostViewImpl {

    /* loaded from: lib/Mus.dex */
    public interface Creator {
        GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix);

        void removeGhost(View view);
    }

    void reserveEndViewTransition(ViewGroup viewGroup, View view);

    void setVisibility(int i2);
}
