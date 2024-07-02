package d.a;

import java.util.List;

/* loaded from: classes.dex */
public abstract class j implements e {
    @Override // d.a.e
    public double a(List<d> list) {
        double a = list.get(0).a();
        return Double.isNaN(a) ? a : d(a);
    }

    @Override // d.a.e
    public boolean b() {
        return true;
    }

    @Override // d.a.e
    public int c() {
        return 1;
    }

    protected abstract double d(double d2);
}
