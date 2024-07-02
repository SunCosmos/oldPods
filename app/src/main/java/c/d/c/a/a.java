package c.d.c.a;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class a extends Animation {
    public static final boolean q;
    private static final WeakHashMap<View, a> r;
    private final WeakReference<View> a;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1407c;
    private float e;
    private float f;
    private float g;
    private float h;

    /* renamed from: i, reason: collision with root package name */
    private float f1409i;
    private float l;
    private float m;
    private final Camera b = new Camera();

    /* renamed from: d, reason: collision with root package name */
    private float f1408d = 1.0f;
    private float j = 1.0f;
    private float k = 1.0f;
    private final RectF n = new RectF();
    private final RectF o = new RectF();
    private final Matrix p = new Matrix();

    static {
        q = Integer.valueOf(Build.VERSION.SDK).intValue() < 11;
        r = new WeakHashMap<>();
    }

    private a(View view) {
        setDuration(0L);
        setFillAfter(true);
        view.setAnimation(this);
        this.a = new WeakReference<>(view);
    }

    private void D(Matrix matrix, View view) {
        float width = view.getWidth();
        float height = view.getHeight();
        boolean z = this.f1407c;
        float f = z ? this.e : width / 2.0f;
        float f2 = z ? this.f : height / 2.0f;
        float f3 = this.g;
        float f4 = this.h;
        float f5 = this.f1409i;
        if (f3 != 0.0f || f4 != 0.0f || f5 != 0.0f) {
            Camera camera = this.b;
            camera.save();
            camera.rotateX(f3);
            camera.rotateY(f4);
            camera.rotateZ(-f5);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f, -f2);
            matrix.postTranslate(f, f2);
        }
        float f6 = this.j;
        float f7 = this.k;
        if (f6 != 1.0f || f7 != 1.0f) {
            matrix.postScale(f6, f7);
            matrix.postTranslate((-(f / width)) * ((f6 * width) - width), (-(f2 / height)) * ((f7 * height) - height));
        }
        matrix.postTranslate(this.l, this.m);
    }

    public static a E(View view) {
        WeakHashMap<View, a> weakHashMap = r;
        a aVar = weakHashMap.get(view);
        if (aVar != null && aVar == view.getAnimation()) {
            return aVar;
        }
        a aVar2 = new a(view);
        weakHashMap.put(view, aVar2);
        return aVar2;
    }

    private void a(RectF rectF, View view) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        Matrix matrix = this.p;
        matrix.reset();
        D(matrix, view);
        this.p.mapRect(rectF);
        rectF.offset(view.getLeft(), view.getTop());
        float f = rectF.right;
        float f2 = rectF.left;
        if (f < f2) {
            rectF.right = f2;
            rectF.left = f;
        }
        float f3 = rectF.bottom;
        float f4 = rectF.top;
        if (f3 < f4) {
            rectF.top = f3;
            rectF.bottom = f4;
        }
    }

    private void p() {
        View view = this.a.get();
        if (view == null || view.getParent() == null) {
            return;
        }
        RectF rectF = this.o;
        a(rectF, view);
        rectF.union(this.n);
        ((View) view.getParent()).invalidate((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
    }

    private void q() {
        View view = this.a.get();
        if (view != null) {
            a(this.n, view);
        }
    }

    public void A(float f) {
        if (this.m != f) {
            q();
            this.m = f;
            p();
        }
    }

    public void B(float f) {
        if (this.a.get() != null) {
            z(f - r0.getLeft());
        }
    }

    public void C(float f) {
        if (this.a.get() != null) {
            A(f - r0.getTop());
        }
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        View view = this.a.get();
        if (view != null) {
            transformation.setAlpha(this.f1408d);
            D(transformation.getMatrix(), view);
        }
    }

    public float b() {
        return this.f1408d;
    }

    public float c() {
        return this.e;
    }

    public float d() {
        return this.f;
    }

    public float e() {
        return this.f1409i;
    }

    public float f() {
        return this.g;
    }

    public float g() {
        return this.h;
    }

    public float h() {
        return this.j;
    }

    public float i() {
        return this.k;
    }

    public int j() {
        View view = this.a.get();
        if (view == null) {
            return 0;
        }
        return view.getScrollX();
    }

    public int k() {
        View view = this.a.get();
        if (view == null) {
            return 0;
        }
        return view.getScrollY();
    }

    public float l() {
        return this.l;
    }

    public float m() {
        return this.m;
    }

    public float n() {
        if (this.a.get() == null) {
            return 0.0f;
        }
        return r0.getLeft() + this.l;
    }

    public float o() {
        if (this.a.get() == null) {
            return 0.0f;
        }
        return r0.getTop() + this.m;
    }

    public void r(float f) {
        if (this.f1408d != f) {
            this.f1408d = f;
            View view = this.a.get();
            if (view != null) {
                view.invalidate();
            }
        }
    }

    public void s(float f) {
        if (this.f1407c && this.e == f) {
            return;
        }
        q();
        this.f1407c = true;
        this.e = f;
        p();
    }

    public void t(float f) {
        if (this.f1407c && this.f == f) {
            return;
        }
        q();
        this.f1407c = true;
        this.f = f;
        p();
    }

    public void u(float f) {
        if (this.f1409i != f) {
            q();
            this.f1409i = f;
            p();
        }
    }

    public void v(float f) {
        if (this.g != f) {
            q();
            this.g = f;
            p();
        }
    }

    public void w(float f) {
        if (this.h != f) {
            q();
            this.h = f;
            p();
        }
    }

    public void x(float f) {
        if (this.j != f) {
            q();
            this.j = f;
            p();
        }
    }

    public void y(float f) {
        if (this.k != f) {
            q();
            this.k = f;
            p();
        }
    }

    public void z(float f) {
        if (this.l != f) {
            q();
            this.l = f;
            p();
        }
    }
}
