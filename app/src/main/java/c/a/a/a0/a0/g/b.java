package c.a.a.a0.a0.g;

/* loaded from: classes.dex */
final class b {
    private final c.a.a.a0.a0.b a;
    private final c.a.a.a0.a0.b b;

    /* renamed from: c, reason: collision with root package name */
    private final c.a.a.a0.a0.c f1168c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(c.a.a.a0.a0.b bVar, c.a.a.a0.a0.b bVar2, c.a.a.a0.a0.c cVar, boolean z) {
        this.a = bVar;
        this.b = bVar2;
        this.f1168c = cVar;
    }

    private static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    private static int e(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c.a.a.a0.a0.c b() {
        return this.f1168c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c.a.a.a0.a0.b c() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c.a.a.a0.a0.b d() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return a(this.a, bVar.a) && a(this.b, bVar.b) && a(this.f1168c, bVar.f1168c);
    }

    public boolean f() {
        return this.b == null;
    }

    public int hashCode() {
        return (e(this.a) ^ e(this.b)) ^ e(this.f1168c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(this.a);
        sb.append(" , ");
        sb.append(this.b);
        sb.append(" : ");
        c.a.a.a0.a0.c cVar = this.f1168c;
        sb.append(cVar == null ? "null" : Integer.valueOf(cVar.c()));
        sb.append(" ]");
        return sb.toString();
    }
}
