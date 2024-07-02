package c.a.a.w.a;

/* loaded from: classes.dex */
public final class z extends q {
    private final String a;
    private final String b;

    public z(String str, String str2, String str3) {
        super(r.TEL);
        this.a = str;
        this.b = str3;
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(20);
        q.b(this.a, sb);
        q.b(this.b, sb);
        return sb.toString();
    }
}
