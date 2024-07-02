package c.a.a.a0.a0.g.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class e extends i {

    /* renamed from: c, reason: collision with root package name */
    private final String f1171c;

    /* renamed from: d, reason: collision with root package name */
    private final String f1172d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(c.a.a.x.a aVar, String str, String str2) {
        super(aVar);
        this.f1171c = str2;
        this.f1172d = str;
    }

    private void k(StringBuilder sb, int i2) {
        int f = b().f(i2, 16);
        if (f == 38400) {
            return;
        }
        sb.append('(');
        sb.append(this.f1171c);
        sb.append(')');
        int i3 = f % 32;
        int i4 = f / 32;
        int i5 = (i4 % 12) + 1;
        int i6 = i4 / 12;
        if (i6 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i6);
        if (i5 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i5);
        if (i3 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i3);
    }

    @Override // c.a.a.a0.a0.g.e.j
    public String d() {
        if (c().k() != 84) {
            throw c.a.a.l.a();
        }
        StringBuilder sb = new StringBuilder();
        f(sb, 8);
        j(sb, 48, 20);
        k(sb, 68);
        return sb.toString();
    }

    @Override // c.a.a.a0.a0.g.e.i
    protected void h(StringBuilder sb, int i2) {
        sb.append('(');
        sb.append(this.f1172d);
        sb.append(i2 / 100000);
        sb.append(')');
    }

    @Override // c.a.a.a0.a0.g.e.i
    protected int i(int i2) {
        return i2 % 100000;
    }
}
