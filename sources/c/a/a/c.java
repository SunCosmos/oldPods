package c.a.a;

/* loaded from: classes.dex */
public final class c {
    private final b a;
    private c.a.a.x.b b;

    public c(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.a = bVar;
    }

    public c.a.a.x.b a() {
        if (this.b == null) {
            this.b = this.a.a();
        }
        return this.b;
    }

    public c.a.a.x.a b(int i2, c.a.a.x.a aVar) {
        return this.a.b(i2, aVar);
    }

    public int c() {
        return this.a.c();
    }

    public int d() {
        return this.a.e();
    }

    public boolean e() {
        return this.a.d().e();
    }

    public c f() {
        this.a.d().f();
        throw null;
    }

    public String toString() {
        try {
            return a().toString();
        } catch (l unused) {
            return "";
        }
    }
}
