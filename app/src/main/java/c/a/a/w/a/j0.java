package c.a.a.w.a;

/* loaded from: classes.dex */
public final class j0 extends q {
    private final String a;
    private final String b;

    /* renamed from: c, reason: collision with root package name */
    private final String f1287c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f1288d;

    public j0(String str, String str2, String str3, boolean z) {
        super(r.WIFI);
        this.a = str2;
        this.b = str;
        this.f1287c = str3;
        this.f1288d = z;
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(80);
        q.b(this.a, sb);
        q.b(this.b, sb);
        q.b(this.f1287c, sb);
        q.b(Boolean.toString(this.f1288d), sb);
        return sb.toString();
    }
}
