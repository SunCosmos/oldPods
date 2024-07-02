package com.google.android.material.transition;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.transition.MaterialContainerTransform;

/* loaded from: classes.dex */
public class g {
    private final Path a = new Path();
    private final Path b = new Path();

    /* renamed from: c */
    private final Path f1778c = new Path();

    /* renamed from: d */
    private final ShapeAppearancePathProvider f1779d = ShapeAppearancePathProvider.getInstance();
    private ShapeAppearanceModel e;

    public void a(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 23) {
            canvas.clipPath(this.a);
        } else {
            canvas.clipPath(this.b);
            canvas.clipPath(this.f1778c, Region.Op.UNION);
        }
    }

    public void b(float f, ShapeAppearanceModel shapeAppearanceModel, ShapeAppearanceModel shapeAppearanceModel2, RectF rectF, RectF rectF2, RectF rectF3, MaterialContainerTransform.ProgressThresholds progressThresholds) {
        ShapeAppearanceModel q = j.q(shapeAppearanceModel, shapeAppearanceModel2, rectF, rectF3, progressThresholds.getStart(), progressThresholds.getEnd(), f);
        this.e = q;
        this.f1779d.calculatePath(q, 1.0f, rectF2, this.b);
        this.f1779d.calculatePath(this.e, 1.0f, rectF3, this.f1778c);
        if (Build.VERSION.SDK_INT >= 23) {
            this.a.op(this.b, this.f1778c, Path.Op.UNION);
        }
    }

    public ShapeAppearanceModel c() {
        return this.e;
    }

    public Path d() {
        return this.a;
    }
}
