package com.google.android.material.transition;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.transition.PathMotion;

/* loaded from: classes.dex */
public final class MaterialArcMotion extends PathMotion {
    private static PointF a(float f, float f2, float f3, float f4) {
        return f2 > f4 ? new PointF(f3, f2) : new PointF(f, f4);
    }

    @Override // androidx.transition.PathMotion
    @NonNull
    public Path getPath(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(f, f2);
        PointF a = a(f, f2, f3, f4);
        path.quadTo(a.x, a.y, f3, f4);
        return path;
    }
}
