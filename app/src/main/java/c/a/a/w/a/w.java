package c.a.a.w.a;

/* loaded from: classes.dex */
public final class w extends q {
    private final String[] a;
    private final String b;

    /* renamed from: c, reason: collision with root package name */
    private final String f1299c;

    public w(String str, String str2, String str3, String str4) {
        super(r.SMS);
        this.a = new String[]{str};
        this.b = str3;
        this.f1299c = str4;
    }

    public w(String[] strArr, String[] strArr2, String str, String str2) {
        super(r.SMS);
        this.a = strArr;
        this.b = str;
        this.f1299c = str2;
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(100);
        q.c(this.a, sb);
        q.b(this.b, sb);
        q.b(this.f1299c, sb);
        return sb.toString();
    }
}
