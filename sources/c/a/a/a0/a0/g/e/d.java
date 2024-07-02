package c.a.a.a0.a0.g.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends h {
    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c.a.a.x.a aVar) {
        super(aVar);
    }

    @Override // c.a.a.a0.a0.g.e.j
    public String d() {
        if (c().k() < 48) {
            throw c.a.a.l.a();
        }
        StringBuilder sb = new StringBuilder();
        f(sb, 8);
        int f = b().f(48, 2);
        sb.append("(393");
        sb.append(f);
        sb.append(')');
        int f2 = b().f(50, 10);
        if (f2 / 100 == 0) {
            sb.append('0');
        }
        if (f2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(f2);
        sb.append(b().c(60, null).b());
        return sb.toString();
    }
}
