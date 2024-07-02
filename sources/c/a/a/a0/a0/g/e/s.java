package c.a.a.a0.a0.g.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class s {
    private final c.a.a.x.a a;
    private final m b = new m();

    /* renamed from: c, reason: collision with root package name */
    private final StringBuilder f1180c = new StringBuilder();

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(c.a.a.x.a aVar) {
        this.a = aVar;
    }

    private n b(int i2) {
        char c2;
        int f = f(i2, 5);
        if (f == 15) {
            return new n(i2 + 5, '$');
        }
        if (f >= 5 && f < 15) {
            return new n(i2 + 5, (char) ((f + 48) - 5));
        }
        int f2 = f(i2, 6);
        if (f2 >= 32 && f2 < 58) {
            return new n(i2 + 6, (char) (f2 + 33));
        }
        switch (f2) {
            case 58:
                c2 = '*';
                break;
            case 59:
                c2 = ',';
                break;
            case 60:
                c2 = '-';
                break;
            case 61:
                c2 = '.';
                break;
            case 62:
                c2 = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: " + f2);
        }
        return new n(i2 + 6, c2);
    }

    private n d(int i2) {
        char c2;
        int f = f(i2, 5);
        if (f == 15) {
            return new n(i2 + 5, '$');
        }
        if (f >= 5 && f < 15) {
            return new n(i2 + 5, (char) ((f + 48) - 5));
        }
        int f2 = f(i2, 7);
        if (f2 >= 64 && f2 < 90) {
            return new n(i2 + 7, (char) (f2 + 1));
        }
        if (f2 >= 90 && f2 < 116) {
            return new n(i2 + 7, (char) (f2 + 7));
        }
        switch (f(i2, 8)) {
            case 232:
                c2 = '!';
                break;
            case 233:
                c2 = '\"';
                break;
            case 234:
                c2 = '%';
                break;
            case 235:
                c2 = '&';
                break;
            case 236:
                c2 = '\'';
                break;
            case 237:
                c2 = '(';
                break;
            case 238:
                c2 = ')';
                break;
            case 239:
                c2 = '*';
                break;
            case 240:
                c2 = '+';
                break;
            case 241:
                c2 = ',';
                break;
            case 242:
                c2 = '-';
                break;
            case 243:
                c2 = '.';
                break;
            case 244:
                c2 = '/';
                break;
            case 245:
                c2 = ':';
                break;
            case 246:
                c2 = ';';
                break;
            case 247:
                c2 = '<';
                break;
            case 248:
                c2 = '=';
                break;
            case 249:
                c2 = '>';
                break;
            case 250:
                c2 = '?';
                break;
            case 251:
                c2 = '_';
                break;
            case 252:
                c2 = ' ';
                break;
            default:
                throw c.a.a.h.a();
        }
        return new n(i2 + 8, c2);
    }

    private p e(int i2) {
        int i3 = i2 + 7;
        if (i3 > this.a.k()) {
            int f = f(i2, 4);
            return f == 0 ? new p(this.a.k(), 10, 10) : new p(this.a.k(), f - 1, 10);
        }
        int f2 = f(i2, 7) - 8;
        return new p(i3, f2 / 11, f2 % 11);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(c.a.a.x.a aVar, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            if (aVar.g(i2 + i5)) {
                i4 |= 1 << ((i3 - i5) - 1);
            }
        }
        return i4;
    }

    private boolean h(int i2) {
        int i3 = i2 + 3;
        if (i3 > this.a.k()) {
            return false;
        }
        while (i2 < i3) {
            if (this.a.g(i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private boolean i(int i2) {
        int i3;
        if (i2 + 1 > this.a.k()) {
            return false;
        }
        for (int i4 = 0; i4 < 5 && (i3 = i4 + i2) < this.a.k(); i4++) {
            if (i4 == 2) {
                if (!this.a.g(i2 + 2)) {
                    return false;
                }
            } else if (this.a.g(i3)) {
                return false;
            }
        }
        return true;
    }

    private boolean j(int i2) {
        int i3;
        if (i2 + 1 > this.a.k()) {
            return false;
        }
        for (int i4 = 0; i4 < 4 && (i3 = i4 + i2) < this.a.k(); i4++) {
            if (this.a.g(i3)) {
                return false;
            }
        }
        return true;
    }

    private boolean k(int i2) {
        int f;
        if (i2 + 5 > this.a.k()) {
            return false;
        }
        int f2 = f(i2, 5);
        if (f2 < 5 || f2 >= 16) {
            return i2 + 6 <= this.a.k() && (f = f(i2, 6)) >= 16 && f < 63;
        }
        return true;
    }

    private boolean l(int i2) {
        int f;
        if (i2 + 5 > this.a.k()) {
            return false;
        }
        int f2 = f(i2, 5);
        if (f2 >= 5 && f2 < 16) {
            return true;
        }
        if (i2 + 7 > this.a.k()) {
            return false;
        }
        int f3 = f(i2, 7);
        if (f3 < 64 || f3 >= 116) {
            return i2 + 8 <= this.a.k() && (f = f(i2, 8)) >= 232 && f < 253;
        }
        return true;
    }

    private boolean m(int i2) {
        if (i2 + 7 > this.a.k()) {
            return i2 + 4 <= this.a.k();
        }
        int i3 = i2;
        while (true) {
            int i4 = i2 + 3;
            if (i3 >= i4) {
                return this.a.g(i4);
            }
            if (this.a.g(i3)) {
                return true;
            }
            i3++;
        }
    }

    private l n() {
        while (k(this.b.a())) {
            n b = b(this.b.a());
            this.b.h(b.a());
            if (b.c()) {
                return new l(new o(this.b.a(), this.f1180c.toString()), true);
            }
            this.f1180c.append(b.b());
        }
        if (h(this.b.a())) {
            this.b.b(3);
            this.b.g();
        } else if (i(this.b.a())) {
            if (this.b.a() + 5 < this.a.k()) {
                this.b.b(5);
            } else {
                this.b.h(this.a.k());
            }
            this.b.f();
        }
        return new l(false);
    }

    private o o() {
        l n;
        boolean b;
        do {
            int a = this.b.a();
            n = this.b.c() ? n() : this.b.d() ? p() : q();
            b = n.b();
            if (!(a != this.b.a()) && !b) {
                break;
            }
        } while (!b);
        return n.a();
    }

    private l p() {
        while (l(this.b.a())) {
            n d2 = d(this.b.a());
            this.b.h(d2.a());
            if (d2.c()) {
                return new l(new o(this.b.a(), this.f1180c.toString()), true);
            }
            this.f1180c.append(d2.b());
        }
        if (h(this.b.a())) {
            this.b.b(3);
            this.b.g();
        } else if (i(this.b.a())) {
            if (this.b.a() + 5 < this.a.k()) {
                this.b.b(5);
            } else {
                this.b.h(this.a.k());
            }
            this.b.e();
        }
        return new l(false);
    }

    private l q() {
        while (m(this.b.a())) {
            p e = e(this.b.a());
            this.b.h(e.a());
            if (e.d()) {
                return new l(e.e() ? new o(this.b.a(), this.f1180c.toString()) : new o(this.b.a(), this.f1180c.toString(), e.c()), true);
            }
            this.f1180c.append(e.b());
            if (e.e()) {
                return new l(new o(this.b.a(), this.f1180c.toString()), true);
            }
            this.f1180c.append(e.c());
        }
        if (j(this.b.a())) {
            this.b.e();
            this.b.b(4);
        }
        return new l(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a(StringBuilder sb, int i2) {
        String str = null;
        while (true) {
            o c2 = c(i2, str);
            String a = r.a(c2.b());
            if (a != null) {
                sb.append(a);
            }
            String valueOf = c2.d() ? String.valueOf(c2.c()) : null;
            if (i2 == c2.a()) {
                return sb.toString();
            }
            i2 = c2.a();
            str = valueOf;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o c(int i2, String str) {
        this.f1180c.setLength(0);
        if (str != null) {
            this.f1180c.append(str);
        }
        this.b.h(i2);
        o o = o();
        return (o == null || !o.d()) ? new o(this.b.a(), this.f1180c.toString()) : new o(this.b.a(), this.f1180c.toString(), o.c());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(int i2, int i3) {
        return g(this.a, i2, i3);
    }
}
