package c.a.a.b0.e;

/* loaded from: classes.dex */
final class d {
    private final int a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1200c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1201d;
    private int e = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(int i2, int i3, int i4, int i5) {
        this.a = i2;
        this.b = i3;
        this.f1200c = i4;
        this.f1201d = i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f1200c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.f1201d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.b - this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        return h(this.e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h(int i2) {
        return i2 != -1 && this.f1200c == (i2 % 3) * 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(int i2) {
        this.e = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        this.e = ((this.f1201d / 30) * 3) + (this.f1200c / 3);
    }

    public String toString() {
        return this.e + "|" + this.f1201d;
    }
}
