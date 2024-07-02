package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* loaded from: lib/Mus.dex */
class VisibilityAwareImageButton extends ImageButton {
    private int mUserSetVisibility;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mUserSetVisibility = getVisibility();
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i2) {
        internalSetVisibility(i2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void internalSetVisibility(int i2, boolean z) {
        super.setVisibility(i2);
        if (z) {
            this.mUserSetVisibility = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getUserSetVisibility() {
        return this.mUserSetVisibility;
    }
}
