package c.a.a.a0.a0.g.e;

/* loaded from: classes.dex */
public abstract class j {
    private final c.a.a.x.a a;
    private final s b;

    public j(c.a.a.x.a aVar) {
        this.a = aVar;
        this.b = new s(aVar);
    }

    public static j a(c.a.a.x.a aVar) {
        if (aVar.g(1)) {
            return new g(aVar);
        }
        if (!aVar.g(2)) {
            return new k(aVar);
        }
        int g = s.g(aVar, 1, 4);
        if (g == 4) {
            return new a(aVar);
        }
        if (g == 5) {
            return new b(aVar);
        }
        int g2 = s.g(aVar, 1, 5);
        if (g2 == 12) {
            return new c(aVar);
        }
        if (g2 == 13) {
            return new d(aVar);
        }
        switch (s.g(aVar, 1, 7)) {
            case 56:
                return new e(aVar, "310", "11");
            case 57:
                return new e(aVar, "320", "11");
            case 58:
                return new e(aVar, "310", "13");
            case 59:
                return new e(aVar, "320", "13");
            case 60:
                return new e(aVar, "310", "15");
            case 61:
                return new e(aVar, "320", "15");
            case 62:
                return new e(aVar, "310", "17");
            case 63:
                return new e(aVar, "320", "17");
            default:
                throw new IllegalStateException("unknown decoder: " + aVar);
        }
    }

    public final s b() {
        return this.b;
    }

    public final c.a.a.x.a c() {
        return this.a;
    }

    public abstract String d();
}
