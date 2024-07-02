package c.a.a.a0.a0.g.e;

/* loaded from: classes.dex */
final class p extends q {
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1177c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(int i2, int i3, int i4) {
        super(i2);
        if (i3 < 0 || i3 > 10 || i4 < 0 || i4 > 10) {
            throw c.a.a.h.a();
        }
        this.b = i3;
        this.f1177c = i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.f1177c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.b == 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e() {
        return this.f1177c == 10;
    }
}
