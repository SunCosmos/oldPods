package android.support.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
/* loaded from: lib/Mus.dex */
class RectEvaluator implements TypeEvaluator<Rect> {
    private Rect mRect;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectEvaluator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectEvaluator(Rect rect) {
        this.mRect = rect;
    }

    @Override // android.animation.TypeEvaluator
    public Rect evaluate(float f, Rect rect, Rect rect2) {
        int i2 = rect.left + ((int) ((rect2.left - rect.left) * f));
        int i3 = rect.top + ((int) ((rect2.top - rect.top) * f));
        int i4 = rect.right + ((int) ((rect2.right - rect.right) * f));
        int i5 = rect.bottom + ((int) ((rect2.bottom - rect.bottom) * f));
        if (this.mRect == null) {
            return new Rect(i2, i3, i4, i5);
        }
        this.mRect.set(i2, i3, i4, i5);
        return this.mRect;
    }
}
