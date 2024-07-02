package d.a;

/* loaded from: classes.dex */
public class c extends d {
    public static final c b = new c(Double.NaN);
    private double a;

    public c(double d2) {
        this.a = d2;
    }

    @Override // d.a.d
    public double a() {
        return this.a;
    }

    @Override // d.a.d
    public boolean b() {
        return true;
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
