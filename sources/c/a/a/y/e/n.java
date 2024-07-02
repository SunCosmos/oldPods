package c.a.a.y.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n extends c {
    @Override // c.a.a.y.e.c, c.a.a.y.e.g
    public void a(h hVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!hVar.i()) {
                break;
            }
            char c2 = hVar.c();
            hVar.f++;
            c(c2, sb);
            if (sb.length() % 3 == 0) {
                c.g(hVar, sb);
                int n = j.n(hVar.d(), hVar.f, e());
                if (n != e()) {
                    hVar.o(n);
                    break;
                }
            }
        }
        f(hVar, sb);
    }

    @Override // c.a.a.y.e.c
    int c(char c2, StringBuilder sb) {
        int i2;
        char c3;
        if (c2 == '\r') {
            c3 = 0;
        } else {
            if (c2 == '*') {
                sb.append((char) 1);
                return 1;
            }
            if (c2 == '>') {
                c3 = 2;
            } else if (c2 == ' ') {
                c3 = 3;
            } else {
                if (c2 >= '0' && c2 <= '9') {
                    i2 = (c2 - '0') + 4;
                } else {
                    if (c2 < 'A' || c2 > 'Z') {
                        j.e(c2);
                        throw null;
                    }
                    i2 = (c2 - 'A') + 14;
                }
                c3 = (char) i2;
            }
        }
        sb.append(c3);
        return 1;
    }

    @Override // c.a.a.y.e.c
    public int e() {
        return 3;
    }

    @Override // c.a.a.y.e.c
    void f(h hVar, StringBuilder sb) {
        hVar.p();
        int a = hVar.g().a() - hVar.a();
        int length = sb.length();
        if (length == 2) {
            hVar.r((char) 254);
            hVar.f -= 2;
        } else {
            if (length != 1) {
                return;
            }
            hVar.f--;
            if (a > 1) {
                hVar.r((char) 254);
            }
        }
        hVar.o(0);
    }
}
