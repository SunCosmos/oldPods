package d.b;

/* loaded from: classes.dex */
public class a implements f {
    private char a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private int f2040c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(char c2, int i2, int i3) {
        this.a = c2;
        this.b = i2;
        this.f2040c = i3;
    }

    @Override // d.b.f
    public int a() {
        return this.b;
    }

    @Override // d.b.f
    public int b() {
        return this.f2040c;
    }

    public String c() {
        return g() ? "" : String.valueOf(this.a);
    }

    public char d() {
        return this.a;
    }

    public boolean e(char... cArr) {
        for (char c2 : cArr) {
            if (c2 == this.a && c2 != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean f() {
        return Character.isDigit(this.a);
    }

    public boolean g() {
        return this.a == 0;
    }

    public boolean h() {
        return Character.isLetter(this.a);
    }

    public boolean i() {
        return this.a == '\n';
    }

    public boolean j() {
        return Character.isWhitespace(this.a) && !g();
    }

    public String toString() {
        return g() ? "<End Of Input>" : String.valueOf(this.a);
    }
}
