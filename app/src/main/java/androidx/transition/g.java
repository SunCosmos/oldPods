package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class g extends ViewGroup implements d {
    ViewGroup a;
    View b;

    /* renamed from: c, reason: collision with root package name */
    final View f1077c;

    /* renamed from: d, reason: collision with root package name */
    int f1078d;

    @Nullable
    private Matrix e;
    private final ViewTreeObserver.OnPreDrawListener f;

    /* loaded from: classes.dex */
    class a implements ViewTreeObserver.OnPreDrawListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            View view;
            ViewCompat.postInvalidateOnAnimation(g.this);
            g gVar = g.this;
            ViewGroup viewGroup = gVar.a;
            if (viewGroup == null || (view = gVar.b) == null) {
                return true;
            }
            viewGroup.endViewTransition(view);
            ViewCompat.postInvalidateOnAnimation(g.this.a);
            g gVar2 = g.this;
            gVar2.a = null;
            gVar2.b = null;
            return true;
        }
    }

    g(View view) {
        super(view.getContext());
        this.f = new a();
        this.f1077c = view;
        setWillNotDraw(false);
        setLayerType(2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g b(View view, ViewGroup viewGroup, Matrix matrix) {
        e eVar;
        if (!(view.getParent() instanceof ViewGroup)) {
            throw new IllegalArgumentException("Ghosted views must be parented by a ViewGroup");
        }
        e b = e.b(viewGroup);
        g e = e(view);
        int i2 = 0;
        if (e != null && (eVar = (e) e.getParent()) != b) {
            i2 = e.f1078d;
            eVar.removeView(e);
            e = null;
        }
        if (e == null) {
            if (matrix == null) {
                matrix = new Matrix();
                c(view, viewGroup, matrix);
            }
            e = new g(view);
            e.h(matrix);
            if (b == null) {
                b = new e(viewGroup);
            } else {
                b.g();
            }
            d(viewGroup, b);
            d(viewGroup, e);
            b.a(e);
            e.f1078d = i2;
        } else if (matrix != null) {
            e.h(matrix);
        }
        e.f1078d++;
        return e;
    }

    static void c(View view, ViewGroup viewGroup, Matrix matrix) {
        ViewGroup viewGroup2 = (ViewGroup) view.getParent();
        matrix.reset();
        b0.j(viewGroup2, matrix);
        matrix.preTranslate(-viewGroup2.getScrollX(), -viewGroup2.getScrollY());
        b0.k(viewGroup, matrix);
    }

    static void d(View view, View view2) {
        b0.g(view2, view2.getLeft(), view2.getTop(), view2.getLeft() + view.getWidth(), view2.getTop() + view.getHeight());
    }

    static g e(View view) {
        return (g) view.getTag(R.id.ghost_view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(View view) {
        g e = e(view);
        if (e != null) {
            int i2 = e.f1078d - 1;
            e.f1078d = i2;
            if (i2 <= 0) {
                ((e) e.getParent()).removeView(e);
            }
        }
    }

    static void g(@NonNull View view, @Nullable g gVar) {
        view.setTag(R.id.ghost_view, gVar);
    }

    @Override // androidx.transition.d
    public void a(ViewGroup viewGroup, View view) {
        this.a = viewGroup;
        this.b = view;
    }

    void h(@NonNull Matrix matrix) {
        this.e = matrix;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        g(this.f1077c, this);
        this.f1077c.getViewTreeObserver().addOnPreDrawListener(this.f);
        b0.i(this.f1077c, 4);
        if (this.f1077c.getParent() != null) {
            ((View) this.f1077c.getParent()).invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.f1077c.getViewTreeObserver().removeOnPreDrawListener(this.f);
        b0.i(this.f1077c, 0);
        g(this.f1077c, null);
        if (this.f1077c.getParent() != null) {
            ((View) this.f1077c.getParent()).invalidate();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        b.a(canvas, true);
        canvas.setMatrix(this.e);
        b0.i(this.f1077c, 0);
        this.f1077c.invalidate();
        b0.i(this.f1077c, 4);
        drawChild(canvas, this.f1077c, getDrawingTime());
        b.a(canvas, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View, androidx.transition.d
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (e(this.f1077c) == this) {
            b0.i(this.f1077c, i2 == 0 ? 4 : 0);
        }
    }
}
