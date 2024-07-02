package d.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class f extends d {
    private List<d> a = new ArrayList();
    private e b;

    @Override // d.a.d
    public double a() {
        return this.b.a(this.a);
    }

    @Override // d.a.d
    public d c() {
        if (!this.b.b()) {
            return this;
        }
        Iterator<d> it = this.a.iterator();
        while (it.hasNext()) {
            if (!it.next().b()) {
                return this;
            }
        }
        return new c(a());
    }

    public void d(d dVar) {
        this.a.add(dVar);
    }

    public List<d> e() {
        return this.a;
    }

    public void f(e eVar) {
        this.b = eVar;
    }
}
