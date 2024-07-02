package d.b;

/* loaded from: classes.dex */
public class d {
    private f a;
    private String b;

    /* renamed from: c, reason: collision with root package name */
    private final a f2043c;

    /* loaded from: classes.dex */
    public enum a {
        WARNING,
        ERROR
    }

    protected d(f fVar, String str, a aVar) {
        this.a = fVar;
        this.b = str;
        this.f2043c = aVar;
    }

    public static d a(f fVar, String str) {
        if (fVar.a() > 0) {
            str = String.format("%3d:%2d: %s", Integer.valueOf(fVar.a()), Integer.valueOf(fVar.b()), str);
        }
        return new d(fVar, str, a.ERROR);
    }

    public String b() {
        return this.b;
    }

    public String toString() {
        return String.format("%s %s", this.f2043c, this.b);
    }
}
