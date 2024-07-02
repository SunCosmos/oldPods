package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* loaded from: classes.dex */
class n implements TypeEvaluator<Rect> {
    private Rect a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Rect rect) {
        this.a = rect;
    }

    @Override // android.animation.TypeEvaluator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Rect evaluate(float f, Rect rect, Rect rect2) {
        int i2 = rect.left + ((int) ((rect2.left - r0) * f));
        int i3 = rect.top + ((int) ((rect2.top - r1) * f));
        int i4 = rect.right + ((int) ((rect2.right - r2) * f));
        int i5 = rect.bottom + ((int) ((rect2.bottom - r6) * f));
        Rect rect3 = this.a;
        if (rect3 == null) {
            return new Rect(i2, i3, i4, i5);
        }
        rect3.set(i2, i3, i4, i5);
        return this.a;
    }
}
