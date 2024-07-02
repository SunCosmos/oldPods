package d.a;

/* loaded from: classes.dex */
public class b extends d {
    private final a a;
    private d b;

    /* renamed from: c, reason: collision with root package name */
    private d f2028c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2029d = false;

    /* loaded from: classes.dex */
    public enum a {
        ADD(3),
        SUBTRACT(3),
        MULTIPLY(4),
        DIVIDE(4),
        MODULO(4),
        POWER(5),
        LT(2),
        LT_EQ(2),
        EQ(2),
        GT_EQ(2),
        GT(2),
        NEQ(2),
        AND(1),
        OR(1);

        private final int a;

        a(int i2) {
            this.a = i2;
        }

        public int a() {
            return this.a;
        }
    }

    public b(a aVar, d dVar, d dVar2) {
        this.a = aVar;
        this.b = dVar;
        this.f2028c = dVar2;
    }

    @Override // d.a.d
    public double a() {
        double a2 = this.b.a();
        double a3 = this.f2028c.a();
        a aVar = this.a;
        if (aVar == a.ADD) {
            return a2 + a3;
        }
        if (aVar == a.SUBTRACT) {
            return a2 - a3;
        }
        if (aVar == a.MULTIPLY) {
            return a2 * a3;
        }
        if (aVar == a.DIVIDE) {
            return a2 / a3;
        }
        if (aVar == a.POWER) {
            return Math.pow(a2, a3);
        }
        if (aVar == a.MODULO) {
            return a2 % a3;
        }
        if (aVar == a.LT) {
            return a2 < a3 ? 1.0d : 0.0d;
        }
        if (aVar == a.LT_EQ) {
            return (a2 < a3 || Math.abs(a2 - a3) < 1.0E-10d) ? 1.0d : 0.0d;
        }
        if (aVar == a.GT) {
            return a2 > a3 ? 1.0d : 0.0d;
        }
        if (aVar == a.GT_EQ) {
            return (a2 > a3 || Math.abs(a2 - a3) < 1.0E-10d) ? 1.0d : 0.0d;
        }
        if (aVar == a.EQ) {
            return Math.abs(a2 - a3) < 1.0E-10d ? 1.0d : 0.0d;
        }
        if (aVar == a.NEQ) {
            return Math.abs(a2 - a3) > 1.0E-10d ? 1.0d : 0.0d;
        }
        if (aVar == a.AND) {
            return (a2 == 1.0d && a3 == 1.0d) ? 1.0d : 0.0d;
        }
        if (aVar == a.OR) {
            return (a2 == 1.0d || a3 == 1.0d) ? 1.0d : 0.0d;
        }
        throw new UnsupportedOperationException(String.valueOf(this.a));
    }

    @Override // d.a.d
    public d c() {
        this.b = this.b.c();
        this.f2028c = this.f2028c.c();
        if (this.b.b() && this.f2028c.b()) {
            return new c(a());
        }
        a aVar = this.a;
        a aVar2 = a.ADD;
        if (aVar == aVar2 || aVar == a.MULTIPLY) {
            if (this.f2028c.b()) {
                d dVar = this.f2028c;
                this.f2028c = this.b;
                this.b = dVar;
            }
            d dVar2 = this.f2028c;
            if (dVar2 instanceof b) {
                b bVar = (b) dVar2;
                if (this.a == bVar.a) {
                    if (this.b.b()) {
                        if (bVar.b.b()) {
                            a aVar3 = this.a;
                            if (aVar3 == aVar2) {
                                return new b(aVar3, new c(this.b.a() + bVar.b.a()), bVar.f2028c);
                            }
                            if (aVar3 == a.MULTIPLY) {
                                return new b(aVar3, new c(this.b.a() * bVar.b.a()), bVar.f2028c);
                            }
                        }
                    } else if (bVar.b.b()) {
                        a aVar4 = this.a;
                        return new b(aVar4, bVar.b, new b(aVar4, this.b, bVar.f2028c));
                    }
                }
            }
        }
        super.c();
        return this;
    }

    public d d() {
        return this.b;
    }

    public a e() {
        return this.a;
    }

    public boolean f() {
        return this.f2029d;
    }

    public void g() {
        this.f2029d = true;
    }

    public void h(d dVar) {
        this.b = dVar;
    }

    public String toString() {
        return "(" + this.b.toString() + " " + this.a + " " + this.f2028c + ")";
    }
}
