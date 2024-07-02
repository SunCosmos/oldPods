package c.a.a.w.a;

/* loaded from: classes.dex */
public final class h extends q {
    private final String a;
    private final String b;

    /* renamed from: c, reason: collision with root package name */
    private final String f1284c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(String str, String str2, String str3, String str4) {
        super(r.EMAIL_ADDRESS);
        this.a = str;
        this.b = str2;
        this.f1284c = str3;
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(30);
        q.b(this.a, sb);
        q.b(this.b, sb);
        q.b(this.f1284c, sb);
        return sb.toString();
    }
}
