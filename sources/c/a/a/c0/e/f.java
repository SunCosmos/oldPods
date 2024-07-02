package c.a.a.c0.e;

import c.a.a.c0.c.h;
import c.a.a.c0.c.j;

/* loaded from: classes.dex */
public final class f {
    private h a;
    private c.a.a.c0.c.f b;

    /* renamed from: c, reason: collision with root package name */
    private j f1246c;

    /* renamed from: d, reason: collision with root package name */
    private int f1247d = -1;
    private b e;

    public static boolean b(int i2) {
        return i2 >= 0 && i2 < 8;
    }

    public b a() {
        return this.e;
    }

    public void c(c.a.a.c0.c.f fVar) {
        this.b = fVar;
    }

    public void d(int i2) {
        this.f1247d = i2;
    }

    public void e(b bVar) {
        this.e = bVar;
    }

    public void f(h hVar) {
        this.a = hVar;
    }

    public void g(j jVar) {
        this.f1246c = jVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.a);
        sb.append("\n ecLevel: ");
        sb.append(this.b);
        sb.append("\n version: ");
        sb.append(this.f1246c);
        sb.append("\n maskPattern: ");
        sb.append(this.f1247d);
        if (this.e == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.e);
        }
        sb.append(">>\n");
        return sb.toString();
    }
}
