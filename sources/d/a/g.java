package d.a;

import java.util.List;

/* loaded from: classes.dex */
public class g {
    public static final d.a.e a = new k();
    public static final d.a.e b = new r();

    /* renamed from: c, reason: collision with root package name */
    public static final d.a.e f2033c = new s();

    /* renamed from: d, reason: collision with root package name */
    public static final d.a.e f2034d = new t();
    public static final d.a.e e = new u();
    public static final d.a.e f = new v();
    public static final d.a.e g = new w();
    public static final d.a.e h = new x();

    /* renamed from: i, reason: collision with root package name */
    public static final d.a.e f2035i = new y();
    public static final d.a.e j = new a();
    public static final d.a.e k = new b();
    public static final d.a.e l = new c();
    public static final d.a.e m = new d();
    public static final d.a.e n = new e();
    public static final d.a.e o = new f();
    public static final d.a.e p = new C0069g();
    public static final d.a.e q = new h();
    public static final d.a.e r = new i();
    public static final d.a.e s = new j();
    public static final d.a.e t = new l();
    public static final d.a.e u = new m();
    public static final d.a.e v = new n();
    public static final d.a.e w = new o();
    public static final d.a.e x = new p();
    public static final d.a.e y = new q();

    /* loaded from: classes.dex */
    class a extends d.a.j {
        a() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.atan(d2);
        }
    }

    /* loaded from: classes.dex */
    class b extends d.a.a {
        b() {
        }

        @Override // d.a.a
        protected double d(double d2, double d3) {
            return Math.atan2(d2, d3);
        }
    }

    /* loaded from: classes.dex */
    class c extends d.a.j {
        c() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.round(d2);
        }
    }

    /* loaded from: classes.dex */
    class d extends d.a.j {
        d() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.floor(d2);
        }
    }

    /* loaded from: classes.dex */
    class e extends d.a.j {
        e() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.ceil(d2);
        }
    }

    /* loaded from: classes.dex */
    class f extends d.a.j {
        f() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.sqrt(d2);
        }
    }

    /* renamed from: d.a.g$g, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0069g extends d.a.j {
        C0069g() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.exp(d2);
        }
    }

    /* loaded from: classes.dex */
    class h extends d.a.j {
        h() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.log(d2);
        }
    }

    /* loaded from: classes.dex */
    class i extends d.a.j {
        i() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.log10(d2);
        }
    }

    /* loaded from: classes.dex */
    class j extends d.a.a {
        j() {
        }

        @Override // d.a.a
        protected double d(double d2, double d3) {
            return Math.min(d2, d3);
        }
    }

    /* loaded from: classes.dex */
    class k extends d.a.j {
        k() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.sin(d2);
        }
    }

    /* loaded from: classes.dex */
    class l extends d.a.a {
        l() {
        }

        @Override // d.a.a
        protected double d(double d2, double d3) {
            return Math.max(d2, d3);
        }
    }

    /* loaded from: classes.dex */
    class m extends d.a.j {
        m() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.random() * d2;
        }
    }

    /* loaded from: classes.dex */
    class n extends d.a.j {
        n() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.signum(d2);
        }
    }

    /* loaded from: classes.dex */
    class o extends d.a.j {
        o() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.toDegrees(d2);
        }
    }

    /* loaded from: classes.dex */
    class p extends d.a.j {
        p() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.toRadians(d2);
        }
    }

    /* loaded from: classes.dex */
    class q implements d.a.e {
        q() {
        }

        @Override // d.a.e
        public double a(List<d.a.d> list) {
            double a = list.get(0).a();
            if (Double.isNaN(a)) {
                return a;
            }
            return list.get(a == 1.0d ? 1 : 2).a();
        }

        @Override // d.a.e
        public boolean b() {
            return false;
        }

        @Override // d.a.e
        public int c() {
            return 3;
        }
    }

    /* loaded from: classes.dex */
    class r extends d.a.j {
        r() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.sinh(d2);
        }
    }

    /* loaded from: classes.dex */
    class s extends d.a.j {
        s() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.cos(d2);
        }
    }

    /* loaded from: classes.dex */
    class t extends d.a.j {
        t() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.cosh(d2);
        }
    }

    /* loaded from: classes.dex */
    class u extends d.a.j {
        u() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.tan(d2);
        }
    }

    /* loaded from: classes.dex */
    class v extends d.a.j {
        v() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.tanh(d2);
        }
    }

    /* loaded from: classes.dex */
    class w extends d.a.j {
        w() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.abs(d2);
        }
    }

    /* loaded from: classes.dex */
    class x extends d.a.j {
        x() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.asin(d2);
        }
    }

    /* loaded from: classes.dex */
    class y extends d.a.j {
        y() {
        }

        @Override // d.a.j
        protected double d(double d2) {
            return Math.acos(d2);
        }
    }
}
