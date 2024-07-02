package c.a.a.y.e;

import java.nio.charset.Charset;

/* loaded from: classes.dex */
final class h {
    private final String a;
    private l b;

    /* renamed from: c, reason: collision with root package name */
    private c.a.a.f f1328c;

    /* renamed from: d, reason: collision with root package name */
    private c.a.a.f f1329d;
    private final StringBuilder e;
    int f;
    private int g;
    private k h;

    /* renamed from: i, reason: collision with root package name */
    private int f1330i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(String str) {
        byte[] bytes = str.getBytes(Charset.forName("ISO-8859-1"));
        StringBuilder sb = new StringBuilder(bytes.length);
        int length = bytes.length;
        for (int i2 = 0; i2 < length; i2++) {
            char c2 = (char) (bytes[i2] & 255);
            if (c2 == '?' && str.charAt(i2) != '?') {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
            sb.append(c2);
        }
        this.a = sb.toString();
        this.b = l.FORCE_NONE;
        this.e = new StringBuilder(str.length());
        this.g = -1;
    }

    private int h() {
        return this.a.length() - this.f1330i;
    }

    public int a() {
        return this.e.length();
    }

    public StringBuilder b() {
        return this.e;
    }

    public char c() {
        return this.a.charAt(this.f);
    }

    public String d() {
        return this.a;
    }

    public int e() {
        return this.g;
    }

    public int f() {
        return h() - this.f;
    }

    public k g() {
        return this.h;
    }

    public boolean i() {
        return this.f < h();
    }

    public void j() {
        this.g = -1;
    }

    public void k() {
        this.h = null;
    }

    public void l(c.a.a.f fVar, c.a.a.f fVar2) {
        this.f1328c = fVar;
        this.f1329d = fVar2;
    }

    public void m(int i2) {
        this.f1330i = i2;
    }

    public void n(l lVar) {
        this.b = lVar;
    }

    public void o(int i2) {
        this.g = i2;
    }

    public void p() {
        q(a());
    }

    public void q(int i2) {
        k kVar = this.h;
        if (kVar == null || i2 > kVar.a()) {
            this.h = k.l(i2, this.b, this.f1328c, this.f1329d, true);
        }
    }

    public void r(char c2) {
        this.e.append(c2);
    }

    public void s(String str) {
        this.e.append(str);
    }
}
