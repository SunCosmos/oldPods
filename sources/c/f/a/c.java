package c.f.a;

/* loaded from: classes.dex */
public class c {
    private b a;
    private b b;

    public b a() {
        return this.b;
    }

    public boolean b() {
        return this.a != null;
    }

    public b c() {
        b bVar = this.a;
        if (bVar != null) {
            this.a = bVar.b();
        }
        return bVar;
    }

    public void d(b bVar) {
        if (this.a == null) {
            this.a = bVar;
        } else {
            this.b.d(bVar);
        }
        this.b = bVar;
    }
}
