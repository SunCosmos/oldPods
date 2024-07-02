package c.a.a.w.a;

/* loaded from: classes.dex */
public final class d extends q {
    private final String[] a;
    private final String[] b;

    /* renamed from: c, reason: collision with root package name */
    private final String f1277c;

    /* renamed from: d, reason: collision with root package name */
    private final String[] f1278d;
    private final String[] e;
    private final String f;
    private final String g;
    private final String[] h;

    /* renamed from: i, reason: collision with root package name */
    private final String f1279i;
    private final String j;
    private final String k;
    private final String[] l;
    private final String[] m;

    public d(String[] strArr, String[] strArr2, String str, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String str2, String str3, String[] strArr7, String[] strArr8, String str4, String str5, String str6, String[] strArr9, String[] strArr10) {
        super(r.ADDRESSBOOK);
        this.a = strArr;
        this.b = strArr2;
        this.f1277c = str;
        this.f1278d = strArr3;
        this.e = strArr5;
        this.f = str2;
        this.g = str3;
        this.h = strArr7;
        this.f1279i = str4;
        this.j = str5;
        this.k = str6;
        this.l = strArr9;
        this.m = strArr10;
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(100);
        q.c(this.a, sb);
        q.c(this.b, sb);
        q.b(this.f1277c, sb);
        q.b(this.k, sb);
        q.b(this.f1279i, sb);
        q.c(this.h, sb);
        q.c(this.f1278d, sb);
        q.c(this.e, sb);
        q.b(this.f, sb);
        q.c(this.l, sb);
        q.b(this.j, sb);
        q.c(this.m, sb);
        q.b(this.g, sb);
        return sb.toString();
    }
}
