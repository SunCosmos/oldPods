package c.a.a.y.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class a implements g {
    private static char b(char c2, char c3) {
        if (j.f(c2) && j.f(c3)) {
            return (char) (((c2 - '0') * 10) + (c3 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + c2 + c3);
    }

    @Override // c.a.a.y.e.g
    public void a(h hVar) {
        int i2;
        char c2;
        if (j.a(hVar.d(), hVar.f) >= 2) {
            hVar.r(b(hVar.d().charAt(hVar.f), hVar.d().charAt(hVar.f + 1)));
            i2 = hVar.f + 2;
        } else {
            char c3 = hVar.c();
            int n = j.n(hVar.d(), hVar.f, c());
            if (n != c()) {
                if (n == 1) {
                    hVar.r((char) 230);
                    hVar.o(1);
                    return;
                }
                if (n == 2) {
                    hVar.r((char) 239);
                    hVar.o(2);
                    return;
                }
                int i3 = 3;
                if (n != 3) {
                    i3 = 4;
                    if (n != 4) {
                        if (n == 5) {
                            hVar.r((char) 231);
                            hVar.o(5);
                            return;
                        } else {
                            throw new IllegalStateException("Illegal mode: " + n);
                        }
                    }
                    c2 = 240;
                } else {
                    c2 = 238;
                }
                hVar.r(c2);
                hVar.o(i3);
                return;
            }
            boolean g = j.g(c3);
            int i4 = c3;
            if (g) {
                hVar.r((char) 235);
                i4 = c3 - 128;
            }
            hVar.r((char) (i4 + 1));
            i2 = hVar.f + 1;
        }
        hVar.f = i2;
    }

    public int c() {
        return 0;
    }
}
