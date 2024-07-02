package d.a;

import d.a.b;
import d.b.g;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class h {

    /* renamed from: d, reason: collision with root package name */
    private static Map<String, e> f2036d = new TreeMap();
    private final i a;
    private List<d.b.d> b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private d.b.h f2037c;

    static {
        j("sin", g.a);
        j("cos", g.f2033c);
        j("tan", g.e);
        j("sinh", g.b);
        j("cosh", g.f2034d);
        j("tanh", g.f);
        j("asin", g.h);
        j("acos", g.f2035i);
        j("atan", g.j);
        j("atan2", g.k);
        j("deg", g.w);
        j("rad", g.x);
        j("abs", g.g);
        j("round", g.l);
        j("ceil", g.n);
        j("floor", g.m);
        j("exp", g.p);
        j("ln", g.q);
        j("log", g.r);
        j("sqrt", g.o);
        j("min", g.s);
        j("max", g.t);
        j("rnd", g.u);
        j("sign", g.v);
        j("if", g.y);
    }

    protected h(Reader reader, i iVar) {
        this.a = iVar;
        d.b.h hVar = new d.b.h(reader);
        this.f2037c = hVar;
        hVar.h(this.b);
    }

    public static d f(String str) {
        return new h(new StringReader(str), i.a()).e();
    }

    public static void j(String str, e eVar) {
        f2036d.put(str, eVar);
    }

    protected d a() {
        if (this.f2037c.c().s("-")) {
            this.f2037c.a();
            b bVar = new b(b.a.SUBTRACT, new c(0.0d), a());
            bVar.g();
            return bVar;
        }
        if (this.f2037c.c().s("(")) {
            this.f2037c.a();
            d c2 = c();
            if (c2 instanceof b) {
                ((b) c2).g();
            }
            b(g.a.SYMBOL, ")");
            return c2;
        }
        if (this.f2037c.c().s("|")) {
            this.f2037c.a();
            f fVar = new f();
            fVar.d(c());
            fVar.f(g.g);
            b(g.a.SYMBOL, "|");
            return fVar;
        }
        if (this.f2037c.c().o(new String[0])) {
            return this.f2037c.f().s("(") ? d() : new l(this.a.e(this.f2037c.a().i()));
        }
        if (!this.f2037c.c().r()) {
            d.b.g a = this.f2037c.a();
            this.b.add(d.b.d.a(a, String.format("Unexpected token: '%s'. Expected an expression.", a.j())));
            return c.b;
        }
        double parseDouble = Double.parseDouble(this.f2037c.a().i());
        if (this.f2037c.c().m(g.a.ID)) {
            String intern = this.f2037c.c().i().intern();
            if ("n" == intern) {
                parseDouble /= 1.0E9d;
            } else if ("u" == intern) {
                parseDouble /= 1000000.0d;
            } else if ("m" == intern) {
                parseDouble /= 1000.0d;
            } else if ("K" == intern || "k" == intern) {
                parseDouble *= 1000.0d;
            } else if ("M" == intern) {
                parseDouble *= 1000000.0d;
            } else if ("G" == intern) {
                parseDouble *= 1.0E9d;
            } else {
                d.b.g a2 = this.f2037c.a();
                this.b.add(d.b.d.a(a2, String.format("Unexpected token: '%s'. Expected a valid quantifier.", a2.j())));
            }
            this.f2037c.a();
        }
        return new c(parseDouble);
    }

    protected void b(g.a aVar, String str) {
        if (this.f2037c.c().t(aVar, str)) {
            this.f2037c.a();
        } else {
            this.b.add(d.b.d.a(this.f2037c.c(), String.format("Unexpected token '%s'. Expected: '%s'", this.f2037c.c().j(), str)));
        }
    }

    protected d c() {
        d c2;
        b.a aVar;
        d k = k();
        if (this.f2037c.c().s("&&")) {
            this.f2037c.a();
            c2 = c();
            aVar = b.a.AND;
        } else {
            if (!this.f2037c.c().s("||")) {
                return k;
            }
            this.f2037c.a();
            c2 = c();
            aVar = b.a.OR;
        }
        return i(k, c2, aVar);
    }

    protected d d() {
        f fVar = new f();
        d.b.g a = this.f2037c.a();
        e eVar = f2036d.get(a.i());
        if (eVar == null) {
            this.b.add(d.b.d.a(a, String.format("Unknown function: '%s'", a.i())));
        }
        fVar.f(eVar);
        this.f2037c.a();
        while (!this.f2037c.c().s(")") && this.f2037c.c().q()) {
            if (!fVar.e().isEmpty()) {
                b(g.a.SYMBOL, ",");
            }
            fVar.d(c());
        }
        b(g.a.SYMBOL, ")");
        if (eVar == null) {
            return c.b;
        }
        if (fVar.e().size() == eVar.c() || eVar.c() < 0) {
            return fVar;
        }
        this.b.add(d.b.d.a(a, String.format("Number of arguments for function '%s' do not match. Expected: %d, Found: %d", a.i(), Integer.valueOf(eVar.c()), Integer.valueOf(fVar.e().size()))));
        return c.b;
    }

    protected d e() {
        d c2 = c().c();
        if (this.f2037c.c().q()) {
            d.b.g a = this.f2037c.a();
            this.b.add(d.b.d.a(a, String.format("Unexpected token: '%s'. Expected an expression.", a.j())));
        }
        if (this.b.size() <= 0) {
            return c2;
        }
        throw d.b.e.a(this.b);
    }

    protected d g() {
        d a = a();
        if (!this.f2037c.c().s("^") && !this.f2037c.c().s("**")) {
            return a;
        }
        this.f2037c.a();
        return i(a, g(), b.a.POWER);
    }

    protected d h() {
        d h;
        b.a aVar;
        d g = g();
        if (this.f2037c.c().s("*")) {
            this.f2037c.a();
            h = h();
            aVar = b.a.MULTIPLY;
        } else if (this.f2037c.c().s("/")) {
            this.f2037c.a();
            h = h();
            aVar = b.a.DIVIDE;
        } else {
            if (!this.f2037c.c().s("%")) {
                return g;
            }
            this.f2037c.a();
            h = h();
            aVar = b.a.MODULO;
        }
        return i(g, h, aVar);
    }

    protected d i(d dVar, d dVar2, b.a aVar) {
        if (dVar2 instanceof b) {
            b bVar = (b) dVar2;
            if (!bVar.f() && bVar.e().a() == aVar.a()) {
                l(bVar, dVar, aVar);
                return dVar2;
            }
        }
        return new b(aVar, dVar, dVar2);
    }

    protected d k() {
        d k;
        b.a aVar;
        d m = m();
        if (this.f2037c.c().s("<")) {
            this.f2037c.a();
            k = k();
            aVar = b.a.LT;
        } else if (this.f2037c.c().s("<=")) {
            this.f2037c.a();
            k = k();
            aVar = b.a.LT_EQ;
        } else if (this.f2037c.c().s("=")) {
            this.f2037c.a();
            k = k();
            aVar = b.a.EQ;
        } else if (this.f2037c.c().s(">=")) {
            this.f2037c.a();
            k = k();
            aVar = b.a.GT_EQ;
        } else if (this.f2037c.c().s(">")) {
            this.f2037c.a();
            k = k();
            aVar = b.a.GT;
        } else {
            if (!this.f2037c.c().s("!=")) {
                return m;
            }
            this.f2037c.a();
            k = k();
            aVar = b.a.NEQ;
        }
        return i(m, k, aVar);
    }

    protected void l(b bVar, d dVar, b.a aVar) {
        if (bVar.d() instanceof b) {
            b bVar2 = (b) bVar.d();
            if (!bVar2.f() && bVar2.e().a() == aVar.a()) {
                l(bVar2, dVar, aVar);
                return;
            }
        }
        bVar.h(new b(aVar, dVar, bVar.d()));
    }

    protected d m() {
        d m;
        b.a aVar;
        d h = h();
        if (this.f2037c.c().s("+")) {
            this.f2037c.a();
        } else {
            if (this.f2037c.c().s("-")) {
                this.f2037c.a();
                m = m();
                aVar = b.a.SUBTRACT;
                return i(h, m, aVar);
            }
            if (!this.f2037c.c().r() || !this.f2037c.c().i().startsWith("-")) {
                return h;
            }
        }
        m = m();
        aVar = b.a.ADD;
        return i(h, m, aVar);
    }
}
