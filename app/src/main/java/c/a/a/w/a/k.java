package c.a.a.w.a;

import java.util.Map;

/* loaded from: classes.dex */
public final class k extends q {
    private final String a;
    private final String b;

    /* renamed from: c, reason: collision with root package name */
    private final String f1289c;

    /* renamed from: d, reason: collision with root package name */
    private final String f1290d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;

    /* renamed from: i, reason: collision with root package name */
    private final String f1291i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final Map<String, String> n;

    public k(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, Map<String, String> map) {
        super(r.PRODUCT);
        this.a = str;
        this.b = str2;
        this.f1289c = str3;
        this.f1290d = str4;
        this.e = str5;
        this.f = str7;
        this.g = str8;
        this.h = str9;
        this.f1291i = str10;
        this.j = str11;
        this.k = str12;
        this.l = str13;
        this.m = str14;
        this.n = map;
    }

    private static boolean d(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    private static int e(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Override // c.a.a.w.a.q
    public String a() {
        return String.valueOf(this.a);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return d(this.b, kVar.b) && d(this.f1289c, kVar.f1289c) && d(this.f1290d, kVar.f1290d) && d(this.e, kVar.e) && d(this.f, kVar.f) && d(this.g, kVar.g) && d(this.h, kVar.h) && d(this.f1291i, kVar.f1291i) && d(this.j, kVar.j) && d(this.k, kVar.k) && d(this.l, kVar.l) && d(this.m, kVar.m) && d(this.n, kVar.n);
    }

    public int hashCode() {
        return ((((((((((((e(this.b) ^ 0) ^ e(this.f1289c)) ^ e(this.f1290d)) ^ e(this.e)) ^ e(this.f)) ^ e(this.g)) ^ e(this.h)) ^ e(this.f1291i)) ^ e(this.j)) ^ e(this.k)) ^ e(this.l)) ^ e(this.m)) ^ e(this.n);
    }
}
