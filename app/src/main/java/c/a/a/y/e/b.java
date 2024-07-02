package c.a.a.y.e;

import bsh.org.objectweb.asm.Constants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class b implements g {
    private static char c(char c2, int i2) {
        int i3 = c2 + ((i2 * Constants.FCMPL) % 255) + 1;
        return i3 <= 255 ? (char) i3 : (char) (i3 - 256);
    }

    @Override // c.a.a.y.e.g
    public void a(h hVar) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) 0);
        while (true) {
            if (!hVar.i()) {
                break;
            }
            sb.append(hVar.c());
            hVar.f++;
            int n = j.n(hVar.d(), hVar.f, b());
            if (n != b()) {
                hVar.o(n);
                break;
            }
        }
        int length = sb.length() - 1;
        int a = hVar.a() + length + 1;
        hVar.q(a);
        boolean z = hVar.g().a() - a > 0;
        if (hVar.i() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else {
                if (length <= 249 || length > 1555) {
                    throw new IllegalStateException("Message length not in valid ranges: " + length);
                }
                sb.setCharAt(0, (char) ((length / 250) + 249));
                sb.insert(1, (char) (length % 250));
            }
        }
        int length2 = sb.length();
        for (int i2 = 0; i2 < length2; i2++) {
            hVar.r(c(sb.charAt(i2), hVar.a() + 1));
        }
    }

    public int b() {
        return 5;
    }
}
