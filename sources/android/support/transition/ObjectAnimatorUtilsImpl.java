package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

/* loaded from: lib/Mus.dex */
interface ObjectAnimatorUtilsImpl {
    <T> ObjectAnimator ofPointF(T t, Property<T, PointF> property, Path path);
}
