package c.a.a.a0.a0.g.e;

/* loaded from: classes.dex */
final class m {
    private int a = 0;
    private a b = a.NUMERIC;

    /* loaded from: classes.dex */
    private enum a {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i2) {
        this.a += i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.b == a.ALPHA;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.b == a.ISO_IEC_646;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.b = a.ALPHA;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.b = a.ISO_IEC_646;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        this.b = a.NUMERIC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(int i2) {
        this.a = i2;
    }
}
