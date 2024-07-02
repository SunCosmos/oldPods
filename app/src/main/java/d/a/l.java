package d.a;

/* loaded from: classes.dex */
public class l extends d {
    private k a;

    public l(k kVar) {
        this.a = kVar;
    }

    @Override // d.a.d
    public double a() {
        return this.a.b();
    }

    @Override // d.a.d
    public boolean b() {
        return this.a.c();
    }

    @Override // d.a.d
    public d c() {
        return b() ? new c(a()) : this;
    }

    public String toString() {
        return this.a.a();
    }
}
