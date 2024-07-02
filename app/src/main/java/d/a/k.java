package d.a;

/* loaded from: classes.dex */
public class k {
    private String b;
    private double a = 0.0d;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2039c = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public k(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public double b() {
        return this.a;
    }

    public boolean c() {
        return this.f2039c;
    }

    public void d(double d2) {
        e(d2);
        this.f2039c = true;
    }

    public void e(double d2) {
        if (this.f2039c) {
            throw new IllegalStateException(String.format("%s is constant!", this.b));
        }
        this.a = d2;
    }

    public String toString() {
        return this.b + ": " + String.valueOf(this.a);
    }
}
