package d.b;

import d.b.g;
import java.io.Reader;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class h extends b<g> {
    protected c e;
    private char f = '.';
    private char g = '.';
    private char h = '_';

    /* renamed from: i, reason: collision with root package name */
    private String f2050i = "//";
    private String j = "/*";
    private String k = "*/";
    private char[] l = {'(', '[', '{', '}', ']', ')'};
    private boolean m = true;
    private Set<Character> n = new HashSet();
    private Set<Character> o = new HashSet();
    private Map<String, String> p = new IdentityHashMap();
    private boolean q = false;
    private Map<Character, Character> r = new IdentityHashMap();

    public h(Reader reader) {
        c cVar = new c(reader);
        this.e = cVar;
        cVar.h(this.f2041c);
        i('\"', '\\');
        i('\'', (char) 0);
    }

    protected boolean A(a aVar) {
        return aVar.f() || aVar.h() || aVar.e('_');
    }

    protected boolean B(a aVar) {
        return (aVar.g() || aVar.f() || aVar.h() || aVar.j() || Character.isISOControl(aVar.d()) || t(true) || v() || x() || y() || w() || this.r.containsKey(Character.valueOf(aVar.d()))) ? false : true;
    }

    protected void C() {
        while (!this.e.c().g()) {
            if (u()) {
                return;
            } else {
                this.e.a();
            }
        }
        this.f2041c.add(d.a(this.e.c(), "Premature end of block comment"));
    }

    protected void D() {
        while (!this.e.c().g() && !this.e.c().i()) {
            this.e.a();
        }
    }

    @Override // d.b.b
    public void h(List<d> list) {
        super.h(list);
        this.e.h(list);
    }

    public void i(char c2, char c3) {
        this.r.put(Character.valueOf(c2), Character.valueOf(c3));
    }

    protected boolean j(String str) {
        if (str == null) {
            return false;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!this.e.g(i2).e(str.charAt(i2))) {
                return false;
            }
        }
        this.e.b(str.length());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // d.b.b
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public g d() {
        return g.h(g.a.EOI, this.e.c());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // d.b.b
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public g e() {
        while (this.e.c().j()) {
            this.e.a();
        }
        if (this.e.c().g()) {
            return null;
        }
        if (x()) {
            D();
            return e();
        }
        if (v()) {
            C();
            return e();
        }
        if (y()) {
            return n();
        }
        if (w()) {
            return m();
        }
        if (this.r.containsKey(Character.valueOf(this.e.c().d()))) {
            return p();
        }
        if (t(false)) {
            return g.h(g.a.SYMBOL, this.e.a());
        }
        if (z()) {
            return o();
        }
        if (B(this.e.c())) {
            return q();
        }
        this.f2041c.add(d.a(this.e.c(), String.format("Invalid character in input: '%s'", this.e.c().c())));
        this.e.a();
        return e();
    }

    protected g m() {
        g g = g.g(g.a.ID, this.e.c());
        do {
            g.d(this.e.a());
        } while (A(this.e.c()));
        if (this.e.c().g() || !this.o.contains(Character.valueOf(this.e.c().d()))) {
            return r(g);
        }
        g g2 = g.g(g.a.SPECIAL_ID, g);
        g2.w(this.e.c().c());
        g2.u(g.i());
        g2.v(g.i());
        g2.e(this.e.c());
        this.e.a();
        return r(g2);
    }

    protected g n() {
        g g = g.g(g.a.INTEGER, this.e.c());
        loop0: while (true) {
            g.d(this.e.a());
            while (true) {
                if (this.e.c().f() || (this.e.c().e(this.f, this.h) && this.e.f().f())) {
                    if (!this.e.c().e(this.h)) {
                        if (this.e.c().e(this.f)) {
                            g.a aVar = g.a.DECIMAL;
                            if (g.m(aVar)) {
                                this.f2041c.add(d.a(this.e.c(), "Unexpected decimal separators"));
                            } else {
                                g g2 = g.g(aVar, g);
                                g2.u(g.i() + this.g);
                                g2.v(g.j());
                                g = g2;
                            }
                        }
                    }
                    g.e(this.e.a());
                }
            }
        }
        return g;
    }

    protected g o() {
        g g = g.g(g.a.SPECIAL_ID, this.e.c());
        g.f(this.e.a());
        while (A(this.e.c())) {
            g.d(this.e.a());
        }
        return r(g);
    }

    protected g p() {
        char d2 = this.e.c().d();
        char charValue = this.r.get(Character.valueOf(this.e.c().d())).charValue();
        g g = g.g(g.a.STRING, this.e.c());
        g.f(this.e.a());
        while (!this.e.c().i() && !this.e.c().e(d2) && !this.e.c().g()) {
            if (charValue == 0 || !this.e.c().e(charValue)) {
                g.d(this.e.a());
            } else {
                g.e(this.e.a());
                if (!s(d2, charValue, g)) {
                    this.f2041c.add(d.a(this.e.f(), String.format("Cannot use '%s' as escaped character", this.e.f().c())));
                }
            }
        }
        if (this.e.c().e(d2)) {
            g.e(this.e.a());
        } else {
            this.f2041c.add(d.a(this.e.c(), "Premature end of string constant"));
        }
        return g;
    }

    protected g q() {
        g g = g.g(g.a.SYMBOL, this.e.c());
        do {
            g.f(this.e.a());
        } while (B(this.e.c()));
        return g;
    }

    protected g r(g gVar) {
        Map<String, String> map;
        String lowerCase;
        if (this.q) {
            map = this.p;
            lowerCase = gVar.i();
        } else {
            map = this.p;
            lowerCase = gVar.i().toLowerCase();
        }
        String str = map.get(lowerCase.intern());
        if (str == null) {
            return gVar;
        }
        g g = g.g(g.a.KEYWORD, gVar);
        g.w(str);
        g.u(gVar.i());
        g.v(gVar.j());
        return g;
    }

    protected boolean s(char c2, char c3, g gVar) {
        char c4;
        if (this.e.c().e(c2)) {
            gVar.c(c2);
        } else if (this.e.c().e(c3)) {
            gVar.x(c3);
        } else {
            if (this.e.c().e('n')) {
                c4 = '\n';
            } else {
                if (!this.e.c().e('r')) {
                    return false;
                }
                c4 = '\r';
            }
            gVar.x(c4);
        }
        gVar.e(this.e.a());
        return true;
    }

    protected boolean t(boolean z) {
        if (this.e.c().e(this.l)) {
            return true;
        }
        return !z && this.m && this.e.c().e('|') && !this.e.f().e('|');
    }

    public String toString() {
        StringBuilder sb;
        if (this.a.size() == 0) {
            return "No Token fetched...";
        }
        if (this.a.size() < 2) {
            sb = new StringBuilder();
            sb.append("Current: ");
            sb.append(c());
        } else {
            sb = new StringBuilder();
            sb.append("Current: ");
            sb.append(c().toString());
            sb.append(", Next: ");
            sb.append(f().toString());
        }
        return sb.toString();
    }

    protected boolean u() {
        return j(this.k);
    }

    protected boolean v() {
        return j(this.j);
    }

    protected boolean w() {
        return this.e.c().h();
    }

    protected boolean x() {
        String str = this.f2050i;
        if (str != null) {
            return j(str);
        }
        return false;
    }

    protected boolean y() {
        return this.e.c().f() || (this.e.c().e('-') && this.e.f().f());
    }

    protected boolean z() {
        return this.n.contains(Character.valueOf(this.e.c().d()));
    }
}
