package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(21)
/* loaded from: classes.dex */
public class b implements e {
    private f p(d dVar) {
        return (f) dVar.c();
    }

    @Override // androidx.cardview.widget.e
    public float a(d dVar) {
        return p(dVar).c();
    }

    @Override // androidx.cardview.widget.e
    public float b(d dVar) {
        return p(dVar).d();
    }

    @Override // androidx.cardview.widget.e
    public float c(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public float d(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public void e(d dVar) {
        m(dVar, a(dVar));
    }

    @Override // androidx.cardview.widget.e
    public ColorStateList f(d dVar) {
        return p(dVar).b();
    }

    @Override // androidx.cardview.widget.e
    public void g(d dVar, float f) {
        dVar.a().setElevation(f);
    }

    @Override // androidx.cardview.widget.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        dVar.e(new f(colorStateList, f));
        View a = dVar.a();
        a.setClipToOutline(true);
        a.setElevation(f2);
        m(dVar, f3);
    }

    @Override // androidx.cardview.widget.e
    public void i(d dVar, float f) {
        p(dVar).h(f);
    }

    @Override // androidx.cardview.widget.e
    public void j(d dVar) {
        m(dVar, a(dVar));
    }

    @Override // androidx.cardview.widget.e
    public void k(d dVar, @Nullable ColorStateList colorStateList) {
        p(dVar).f(colorStateList);
    }

    @Override // androidx.cardview.widget.e
    public float l(d dVar) {
        return dVar.a().getElevation();
    }

    @Override // androidx.cardview.widget.e
    public void m(d dVar, float f) {
        p(dVar).g(f, dVar.b(), dVar.f());
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void n(d dVar) {
        if (!dVar.b()) {
            dVar.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float a = a(dVar);
        float b = b(dVar);
        int ceil = (int) Math.ceil(g.c(a, b, dVar.f()));
        int ceil2 = (int) Math.ceil(g.d(a, b, dVar.f()));
        dVar.setShadowPadding(ceil, ceil2, ceil, ceil2);
    }

    @Override // androidx.cardview.widget.e
    public void o() {
    }
}
