package d.a;

import java.util.List;

/* loaded from: classes.dex */
public abstract class a implements e {
    @Override // d.a.e
    public double a(List<d> list) {
        double a = list.get(0).a();
        if (Double.isNaN(a)) {
            return a;
        }
        double a2 = list.get(1).a();
        return Double.isNaN(a2) ? a2 : d(a, a2);
    }

    @Override // d.a.e
    public boolean b() {
        return true;
    }

    @Override // d.a.e
    public int c() {
        return 2;
    }

    protected abstract double d(double d2, double d3);
}
