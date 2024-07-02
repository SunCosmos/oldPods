package c.a.a.w.a;

/* loaded from: classes.dex */
public final class h0 extends q {
    private final String a;
    private final String b;

    /* renamed from: c, reason: collision with root package name */
    private final String f1285c;

    /* renamed from: d, reason: collision with root package name */
    private final String f1286d;
    private final int e;
    private final char f;
    private final String g;

    public h0(String str, String str2, String str3, String str4, String str5, String str6, int i2, char c2, String str7) {
        super(r.VIN);
        this.a = str2;
        this.b = str3;
        this.f1285c = str4;
        this.f1286d = str5;
        this.e = i2;
        this.f = c2;
        this.g = str7;
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(50);
        sb.append(this.a);
        sb.append(' ');
        sb.append(this.b);
        sb.append(' ');
        sb.append(this.f1285c);
        sb.append('\n');
        String str = this.f1286d;
        if (str != null) {
            sb.append(str);
            sb.append(' ');
        }
        sb.append(this.e);
        sb.append(' ');
        sb.append(this.f);
        sb.append(' ');
        sb.append(this.g);
        sb.append('\n');
        return sb.toString();
    }
}
