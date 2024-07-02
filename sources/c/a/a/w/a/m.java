package c.a.a.w.a;

/* loaded from: classes.dex */
public final class m extends q {
    private final double a;
    private final double b;

    /* renamed from: c, reason: collision with root package name */
    private final double f1292c;

    /* renamed from: d, reason: collision with root package name */
    private final String f1293d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(double d2, double d3, double d4, String str) {
        super(r.GEO);
        this.a = d2;
        this.b = d3;
        this.f1292c = d4;
        this.f1293d = str;
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.a);
        sb.append(", ");
        sb.append(this.b);
        if (this.f1292c > 0.0d) {
            sb.append(", ");
            sb.append(this.f1292c);
            sb.append('m');
        }
        if (this.f1293d != null) {
            sb.append(" (");
            sb.append(this.f1293d);
            sb.append(')');
        }
        return sb.toString();
    }
}
