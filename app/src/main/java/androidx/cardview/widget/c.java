package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import androidx.cardview.widget.g;

/* loaded from: classes.dex */
class c implements e {
    final RectF a = new RectF();

    /* loaded from: classes.dex */
    class a implements g.a {
        a() {
        }

        @Override // androidx.cardview.widget.g.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            float f2 = 2.0f * f;
            float width = (rectF.width() - f2) - 1.0f;
            float height = (rectF.height() - f2) - 1.0f;
            if (f >= 1.0f) {
                float f3 = f + 0.5f;
                float f4 = -f3;
                c.this.a.set(f4, f4, f3, f3);
                int save = canvas.save();
                canvas.translate(rectF.left + f3, rectF.top + f3);
                canvas.drawArc(c.this.a, 180.0f, 90.0f, true, paint);
                canvas.translate(width, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.a, 180.0f, 90.0f, true, paint);
                canvas.translate(height, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.a, 180.0f, 90.0f, true, paint);
                canvas.translate(width, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(c.this.a, 180.0f, 90.0f, true, paint);
                canvas.restoreToCount(save);
                float f5 = (rectF.left + f3) - 1.0f;
                float f6 = rectF.top;
                canvas.drawRect(f5, f6, (rectF.right - f3) + 1.0f, f6 + f3, paint);
                float f7 = (rectF.left + f3) - 1.0f;
                float f8 = rectF.bottom;
                canvas.drawRect(f7, f8 - f3, (rectF.right - f3) + 1.0f, f8, paint);
            }
            canvas.drawRect(rectF.left, rectF.top + f, rectF.right, rectF.bottom - f, paint);
        }
    }

    private g p(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new g(context.getResources(), colorStateList, f, f2, f3);
    }

    private g q(d dVar) {
        return (g) dVar.c();
    }

    @Override // androidx.cardview.widget.e
    public float a(d dVar) {
        return q(dVar).i();
    }

    @Override // androidx.cardview.widget.e
    public float b(d dVar) {
        return q(dVar).g();
    }

    @Override // androidx.cardview.widget.e
    public float c(d dVar) {
        return q(dVar).j();
    }

    @Override // androidx.cardview.widget.e
    public float d(d dVar) {
        return q(dVar).k();
    }

    @Override // androidx.cardview.widget.e
    public void e(d dVar) {
    }

    @Override // androidx.cardview.widget.e
    public ColorStateList f(d dVar) {
        return q(dVar).f();
    }

    @Override // androidx.cardview.widget.e
    public void g(d dVar, float f) {
        q(dVar).r(f);
    }

    @Override // androidx.cardview.widget.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        g p = p(context, colorStateList, f, f2, f3);
        p.m(dVar.f());
        dVar.e(p);
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void i(d dVar, float f) {
        q(dVar).p(f);
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void j(d dVar) {
        q(dVar).m(dVar.f());
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void k(d dVar, @Nullable ColorStateList colorStateList) {
        q(dVar).o(colorStateList);
    }

    @Override // androidx.cardview.widget.e
    public float l(d dVar) {
        return q(dVar).l();
    }

    @Override // androidx.cardview.widget.e
    public void m(d dVar, float f) {
        q(dVar).q(f);
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void n(d dVar) {
        Rect rect = new Rect();
        q(dVar).h(rect);
        dVar.d((int) Math.ceil(d(dVar)), (int) Math.ceil(c(dVar)));
        dVar.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // androidx.cardview.widget.e
    public void o() {
        g.r = new a();
    }
}
