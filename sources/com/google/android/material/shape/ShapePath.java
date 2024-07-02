package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ShapePath {
    private final List<PathOperation> a = new ArrayList();
    private final List<d> b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private boolean f1678c;

    @Deprecated
    public float currentShadowAngle;

    @Deprecated
    public float endShadowAngle;

    @Deprecated
    public float endX;

    @Deprecated
    public float endY;

    @Deprecated
    public float startX;

    @Deprecated
    public float startY;

    /* loaded from: classes.dex */
    public static class PathArcOperation extends PathOperation {
        private static final RectF b = new RectF();

        @Deprecated
        public float bottom;

        @Deprecated
        public float left;

        @Deprecated
        public float right;

        @Deprecated
        public float startAngle;

        @Deprecated
        public float sweepAngle;

        @Deprecated
        public float top;

        public PathArcOperation(float f, float f2, float f3, float f4) {
            p(f);
            t(f2);
            q(f3);
            o(f4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float i() {
            return this.bottom;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float j() {
            return this.left;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float k() {
            return this.right;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float l() {
            return this.startAngle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float m() {
            return this.sweepAngle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float n() {
            return this.top;
        }

        private void o(float f) {
            this.bottom = f;
        }

        private void p(float f) {
            this.left = f;
        }

        private void q(float f) {
            this.right = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void r(float f) {
            this.startAngle = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(float f) {
            this.sweepAngle = f;
        }

        private void t(float f) {
            this.top = f;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = b;
            rectF.set(j(), n(), k(), i());
            path.arcTo(rectF, l(), m(), false);
            path.transform(matrix);
        }
    }

    /* loaded from: classes.dex */
    public static class PathCubicOperation extends PathOperation {
        private float b;

        /* renamed from: c, reason: collision with root package name */
        private float f1679c;

        /* renamed from: d, reason: collision with root package name */
        private float f1680d;
        private float e;
        private float f;
        private float g;

        public PathCubicOperation(float f, float f2, float f3, float f4, float f5, float f6) {
            a(f);
            c(f2);
            b(f3);
            d(f4);
            e(f5);
            f(f6);
        }

        private void a(float f) {
            this.b = f;
        }

        private void b(float f) {
            this.f1680d = f;
        }

        private void c(float f) {
            this.f1679c = f;
        }

        private void d(float f) {
            this.e = f;
        }

        private void e(float f) {
            this.f = f;
        }

        private void f(float f) {
            this.g = f;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.cubicTo(this.b, this.f1679c, this.f1680d, this.e, this.f, this.g);
            path.transform(matrix);
        }
    }

    /* loaded from: classes.dex */
    public static class PathLineOperation extends PathOperation {
        private float b;

        /* renamed from: c, reason: collision with root package name */
        private float f1681c;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.b, this.f1681c);
            path.transform(matrix);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class PathOperation {
        protected final Matrix a = new Matrix();

        public abstract void applyToPath(Matrix matrix, Path path);
    }

    /* loaded from: classes.dex */
    public static class PathQuadOperation extends PathOperation {

        @Deprecated
        public float controlX;

        @Deprecated
        public float controlY;

        @Deprecated
        public float endX;

        @Deprecated
        public float endY;

        private float e() {
            return this.controlX;
        }

        private float f() {
            return this.controlY;
        }

        private float g() {
            return this.endX;
        }

        private float h() {
            return this.endY;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i(float f) {
            this.controlX = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void j(float f) {
            this.controlY = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void k(float f) {
            this.endX = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void l(float f) {
            this.endY = f;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.quadTo(e(), f(), g(), h());
            path.transform(matrix);
        }
    }

    /* loaded from: classes.dex */
    class a extends d {
        final /* synthetic */ List b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Matrix f1682c;

        a(ShapePath shapePath, List list, Matrix matrix) {
            this.b = list;
            this.f1682c = matrix;
        }

        @Override // com.google.android.material.shape.ShapePath.d
        public void a(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((d) it.next()).a(this.f1682c, shadowRenderer, i2, canvas);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends d {
        private final PathArcOperation b;

        public b(PathArcOperation pathArcOperation) {
            this.b = pathArcOperation;
        }

        @Override // com.google.android.material.shape.ShapePath.d
        public void a(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i2, @NonNull Canvas canvas) {
            shadowRenderer.drawCornerShadow(canvas, matrix, new RectF(this.b.j(), this.b.n(), this.b.k(), this.b.i()), i2, this.b.l(), this.b.m());
        }
    }

    /* loaded from: classes.dex */
    static class c extends d {
        private final PathLineOperation b;

        /* renamed from: c, reason: collision with root package name */
        private final float f1683c;

        /* renamed from: d, reason: collision with root package name */
        private final float f1684d;

        public c(PathLineOperation pathLineOperation, float f, float f2) {
            this.b = pathLineOperation;
            this.f1683c = f;
            this.f1684d = f2;
        }

        @Override // com.google.android.material.shape.ShapePath.d
        public void a(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i2, @NonNull Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.b.f1681c - this.f1684d, this.b.b - this.f1683c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f1683c, this.f1684d);
            matrix2.preRotate(c());
            shadowRenderer.drawEdgeShadow(canvas, matrix2, rectF, i2);
        }

        float c() {
            return (float) Math.toDegrees(Math.atan((this.b.f1681c - this.f1684d) / (this.b.b - this.f1683c)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class d {
        static final Matrix a = new Matrix();

        d() {
        }

        public abstract void a(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas);

        public final void b(ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            a(a, shadowRenderer, i2, canvas);
        }
    }

    public ShapePath() {
        reset(0.0f, 0.0f);
    }

    public ShapePath(float f, float f2) {
        reset(f, f2);
    }

    private void a(float f) {
        if (e() == f) {
            return;
        }
        float e = ((f - e()) + 360.0f) % 360.0f;
        if (e > 180.0f) {
            return;
        }
        PathArcOperation pathArcOperation = new PathArcOperation(g(), h(), g(), h());
        pathArcOperation.r(e());
        pathArcOperation.s(e);
        this.b.add(new b(pathArcOperation));
        k(f);
    }

    private void b(d dVar, float f, float f2) {
        a(f);
        this.b.add(dVar);
        k(f2);
    }

    private float e() {
        return this.currentShadowAngle;
    }

    private float f() {
        return this.endShadowAngle;
    }

    private void k(float f) {
        this.currentShadowAngle = f;
    }

    private void l(float f) {
        this.endShadowAngle = f;
    }

    private void m(float f) {
        this.endX = f;
    }

    private void n(float f) {
        this.endY = f;
    }

    private void o(float f) {
        this.startX = f;
    }

    private void p(float f) {
        this.startY = f;
    }

    public void addArc(float f, float f2, float f3, float f4, float f5, float f6) {
        PathArcOperation pathArcOperation = new PathArcOperation(f, f2, f3, f4);
        pathArcOperation.r(f5);
        pathArcOperation.s(f6);
        this.a.add(pathArcOperation);
        b bVar = new b(pathArcOperation);
        float f7 = f5 + f6;
        boolean z = f6 < 0.0f;
        if (z) {
            f5 = (f5 + 180.0f) % 360.0f;
        }
        b(bVar, f5, z ? (180.0f + f7) % 360.0f : f7);
        double d2 = f7;
        m(((f + f3) * 0.5f) + (((f3 - f) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))));
        n(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))));
    }

    public void applyToPath(Matrix matrix, Path path) {
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.a.get(i2).applyToPath(matrix, path);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.f1678c;
    }

    @RequiresApi(21)
    public void cubicToPoint(float f, float f2, float f3, float f4, float f5, float f6) {
        this.a.add(new PathCubicOperation(f, f2, f3, f4, f5, f6));
        this.f1678c = true;
        m(f5);
        n(f6);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public d d(Matrix matrix) {
        a(f());
        return new a(this, new ArrayList(this.b), new Matrix(matrix));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float g() {
        return this.endX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float h() {
        return this.endY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float i() {
        return this.startX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float j() {
        return this.startY;
    }

    public void lineTo(float f, float f2) {
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.b = f;
        pathLineOperation.f1681c = f2;
        this.a.add(pathLineOperation);
        c cVar = new c(pathLineOperation, g(), h());
        b(cVar, cVar.c() + 270.0f, cVar.c() + 270.0f);
        m(f);
        n(f2);
    }

    @RequiresApi(21)
    public void quadToPoint(float f, float f2, float f3, float f4) {
        PathQuadOperation pathQuadOperation = new PathQuadOperation();
        pathQuadOperation.i(f);
        pathQuadOperation.j(f2);
        pathQuadOperation.k(f3);
        pathQuadOperation.l(f4);
        this.a.add(pathQuadOperation);
        this.f1678c = true;
        m(f3);
        n(f4);
    }

    public void reset(float f, float f2) {
        reset(f, f2, 270.0f, 0.0f);
    }

    public void reset(float f, float f2, float f3, float f4) {
        o(f);
        p(f2);
        m(f);
        n(f2);
        k(f3);
        l((f3 + f4) % 360.0f);
        this.a.clear();
        this.b.clear();
        this.f1678c = false;
    }
}
