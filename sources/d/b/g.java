package d.b;

/* loaded from: classes.dex */
public class g implements f {
    private a a;
    private String b = "";

    /* renamed from: c, reason: collision with root package name */
    private String f2045c = null;

    /* renamed from: d, reason: collision with root package name */
    private String f2046d = "";
    private String e = "";
    private int f;
    protected int g;

    /* loaded from: classes.dex */
    public enum a {
        ID,
        SPECIAL_ID,
        STRING,
        DECIMAL,
        INTEGER,
        SYMBOL,
        KEYWORD,
        EOI
    }

    private g() {
    }

    public static g g(a aVar, f fVar) {
        g gVar = new g();
        gVar.a = aVar;
        gVar.f = fVar.a();
        gVar.g = fVar.b();
        return gVar;
    }

    public static g h(a aVar, d.b.a aVar2) {
        g gVar = new g();
        gVar.a = aVar;
        gVar.f = aVar2.a();
        gVar.g = aVar2.b();
        gVar.f2046d = aVar2.c();
        gVar.b = aVar2.c();
        gVar.e = aVar2.toString();
        return gVar;
    }

    @Override // d.b.f
    public int a() {
        return this.f;
    }

    @Override // d.b.f
    public int b() {
        return this.g;
    }

    public g c(char c2) {
        this.f2046d += c2;
        this.e += c2;
        return this;
    }

    public g d(d.b.a aVar) {
        c(aVar.d());
        return this;
    }

    public g e(d.b.a aVar) {
        this.e += aVar.d();
        return this;
    }

    public g f(d.b.a aVar) {
        this.b += aVar.d();
        this.f2045c = null;
        this.e += aVar.d();
        return this;
    }

    public String i() {
        return this.f2046d;
    }

    public String j() {
        return this.e;
    }

    public String k() {
        if (this.f2045c == null) {
            this.f2045c = this.b.intern();
        }
        return this.f2045c;
    }

    public a l() {
        return this.a;
    }

    public boolean m(a aVar) {
        return this.a == aVar;
    }

    public boolean n() {
        return m(a.DECIMAL);
    }

    public boolean o(String... strArr) {
        if (strArr.length == 0) {
            return m(a.ID);
        }
        for (String str : strArr) {
            if (t(a.ID, str)) {
                return true;
            }
        }
        return false;
    }

    public boolean p() {
        return m(a.INTEGER);
    }

    public boolean q() {
        return this.a != a.EOI;
    }

    public boolean r() {
        return p() || n();
    }

    public boolean s(String... strArr) {
        if (strArr.length == 0) {
            return m(a.SYMBOL);
        }
        for (String str : strArr) {
            if (t(a.SYMBOL, str)) {
                return true;
            }
        }
        return false;
    }

    public boolean t(a aVar, String str) {
        if (!m(aVar)) {
            return false;
        }
        if (str != null) {
            return k() == str.intern();
        }
        throw new IllegalArgumentException("trigger must not be null");
    }

    public String toString() {
        return l().toString() + ":" + j() + " (" + this.f + ":" + this.g + ")";
    }

    public void u(String str) {
        this.f2046d = str;
    }

    public void v(String str) {
        this.e = str;
    }

    public void w(String str) {
        this.b = str;
        this.f2045c = null;
    }

    public g x(char c2) {
        this.f2046d += c2;
        return this;
    }
}
