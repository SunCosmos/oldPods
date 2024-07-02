package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;

/* loaded from: classes.dex */
public class ShapeAppearancePathProvider {
    private final ShapePath[] a = new ShapePath[4];
    private final Matrix[] b = new Matrix[4];

    /* renamed from: c */
    private final Matrix[] f1673c = new Matrix[4];

    /* renamed from: d */
    private final PointF f1674d = new PointF();
    private final Path e = new Path();
    private final Path f = new Path();
    private final ShapePath g = new ShapePath();
    private final float[] h = new float[2];

    /* renamed from: i */
    private final float[] f1675i = new float[2];
    private final Path j = new Path();
    private final Path k = new Path();
    private boolean l = true;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public interface PathListener {
        void onCornerPathCreated(ShapePath shapePath, Matrix matrix, int i2);

        void onEdgePathCreated(ShapePath shapePath, Matrix matrix, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        static final ShapeAppearancePathProvider a = new ShapeAppearancePathProvider();
    }

    /* loaded from: classes.dex */
    public static final class b {

        @NonNull
        public final ShapeAppearanceModel a;

        @NonNull
        public final Path b;

        /* renamed from: c */
        @NonNull
        public final RectF f1676c;

        /* renamed from: d */
        @Nullable
        public final PathListener f1677d;
        public final float e;

        b(@NonNull ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, @Nullable PathListener pathListener, Path path) {
            this.f1677d = pathListener;
            this.a = shapeAppearanceModel;
            this.e = f;
            this.f1676c = rectF;
            this.b = path;
        }
    }

    public ShapeAppearancePathProvider() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.a[i2] = new ShapePath();
            this.b[i2] = new Matrix();
            this.f1673c[i2] = new Matrix();
        }
    }

    private float a(int i2) {
        return (i2 + 1) * 90;
    }

    private void b(@NonNull b bVar, int i2) {
        this.h[0] = this.a[i2].i();
        this.h[1] = this.a[i2].j();
        this.b[i2].mapPoints(this.h);
        Path path = bVar.b;
        float[] fArr = this.h;
        if (i2 == 0) {
            path.moveTo(fArr[0], fArr[1]);
        } else {
            path.lineTo(fArr[0], fArr[1]);
        }
        this.a[i2].applyToPath(this.b[i2], bVar.b);
        PathListener pathListener = bVar.f1677d;
        if (pathListener != null) {
            pathListener.onCornerPathCreated(this.a[i2], this.b[i2], i2);
        }
    }

    private void c(@NonNull b bVar, int i2) {
        ShapePath shapePath;
        Matrix matrix;
        Path path;
        int i3 = (i2 + 1) % 4;
        this.h[0] = this.a[i2].g();
        this.h[1] = this.a[i2].h();
        this.b[i2].mapPoints(this.h);
        this.f1675i[0] = this.a[i3].i();
        this.f1675i[1] = this.a[i3].j();
        this.b[i3].mapPoints(this.f1675i);
        float f = this.h[0];
        float[] fArr = this.f1675i;
        float max = Math.max(((float) Math.hypot(f - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float g = g(bVar.f1676c, i2);
        this.g.reset(0.0f, 0.0f);
        EdgeTreatment h = h(i2, bVar.a);
        h.getEdgePath(max, g, bVar.e, this.g);
        this.j.reset();
        this.g.applyToPath(this.f1673c[i2], this.j);
        if (this.l && Build.VERSION.SDK_INT >= 19 && (h.a() || i(this.j, i2) || i(this.j, i3))) {
            Path path2 = this.j;
            path2.op(path2, this.f, Path.Op.DIFFERENCE);
            this.h[0] = this.g.i();
            this.h[1] = this.g.j();
            this.f1673c[i2].mapPoints(this.h);
            Path path3 = this.e;
            float[] fArr2 = this.h;
            path3.moveTo(fArr2[0], fArr2[1]);
            shapePath = this.g;
            matrix = this.f1673c[i2];
            path = this.e;
        } else {
            shapePath = this.g;
            matrix = this.f1673c[i2];
            path = bVar.b;
        }
        shapePath.applyToPath(matrix, path);
        PathListener pathListener = bVar.f1677d;
        if (pathListener != null) {
            pathListener.onEdgePathCreated(this.g, this.f1673c[i2], i2);
        }
    }

    private void d(int i2, @NonNull RectF rectF, @NonNull PointF pointF) {
        float f;
        float f2;
        if (i2 == 1) {
            f = rectF.right;
        } else {
            if (i2 != 2) {
                f = i2 != 3 ? rectF.right : rectF.left;
                f2 = rectF.top;
                pointF.set(f, f2);
            }
            f = rectF.left;
        }
        f2 = rectF.bottom;
        pointF.set(f, f2);
    }

    private CornerSize e(int i2, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? shapeAppearanceModel.getTopRightCornerSize() : shapeAppearanceModel.getTopLeftCornerSize() : shapeAppearanceModel.getBottomLeftCornerSize() : shapeAppearanceModel.getBottomRightCornerSize();
    }

    private CornerTreatment f(int i2, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? shapeAppearanceModel.getTopRightCorner() : shapeAppearanceModel.getTopLeftCorner() : shapeAppearanceModel.getBottomLeftCorner() : shapeAppearanceModel.getBottomRightCorner();
    }

    private float g(@NonNull RectF rectF, int i2) {
        float centerX;
        float f;
        float[] fArr = this.h;
        ShapePath[] shapePathArr = this.a;
        fArr[0] = shapePathArr[i2].endX;
        fArr[1] = shapePathArr[i2].endY;
        this.b[i2].mapPoints(fArr);
        if (i2 == 1 || i2 == 3) {
            centerX = rectF.centerX();
            f = this.h[0];
        } else {
            centerX = rectF.centerY();
            f = this.h[1];
        }
        return Math.abs(centerX - f);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public static ShapeAppearancePathProvider getInstance() {
        return a.a;
    }

    private EdgeTreatment h(int i2, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? shapeAppearanceModel.getRightEdge() : shapeAppearanceModel.getTopEdge() : shapeAppearanceModel.getLeftEdge() : shapeAppearanceModel.getBottomEdge();
    }

    @RequiresApi(19)
    private boolean i(Path path, int i2) {
        this.k.reset();
        this.a[i2].applyToPath(this.b[i2], this.k);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.k.computeBounds(rectF, true);
        path.op(this.k, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    private void j(@NonNull b bVar, int i2) {
        f(i2, bVar.a).getCornerPath(this.a[i2], 90.0f, bVar.e, bVar.f1676c, e(i2, bVar.a));
        float a2 = a(i2);
        this.b[i2].reset();
        d(i2, bVar.f1676c, this.f1674d);
        Matrix matrix = this.b[i2];
        PointF pointF = this.f1674d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.b[i2].preRotate(a2);
    }

    private void l(int i2) {
        this.h[0] = this.a[i2].g();
        this.h[1] = this.a[i2].h();
        this.b[i2].mapPoints(this.h);
        float a2 = a(i2);
        this.f1673c[i2].reset();
        Matrix matrix = this.f1673c[i2];
        float[] fArr = this.h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f1673c[i2].preRotate(a2);
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, @NonNull Path path) {
        calculatePath(shapeAppearanceModel, f, rectF, null, path);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f, RectF rectF, PathListener pathListener, @NonNull Path path) {
        path.rewind();
        this.e.rewind();
        this.f.rewind();
        this.f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(shapeAppearanceModel, f, rectF, pathListener, path);
        for (int i2 = 0; i2 < 4; i2++) {
            j(bVar, i2);
            l(i2);
        }
        for (int i3 = 0; i3 < 4; i3++) {
            b(bVar, i3);
            c(bVar, i3);
        }
        path.close();
        this.e.close();
        if (Build.VERSION.SDK_INT < 19 || this.e.isEmpty()) {
            return;
        }
        path.op(this.e, Path.Op.UNION);
    }

    public void k(boolean z) {
        this.l = z;
    }
}
