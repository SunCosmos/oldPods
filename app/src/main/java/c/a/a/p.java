package c.a.a;

import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class p {
    private final String a;
    private final byte[] b;

    /* renamed from: c, reason: collision with root package name */
    private r[] f1256c;

    /* renamed from: d, reason: collision with root package name */
    private final a f1257d;
    private Map<q, Object> e;

    public p(String str, byte[] bArr, r[] rVarArr, a aVar) {
        this(str, bArr, rVarArr, aVar, System.currentTimeMillis());
    }

    public p(String str, byte[] bArr, r[] rVarArr, a aVar, long j) {
        this.a = str;
        this.b = bArr;
        this.f1256c = rVarArr;
        this.f1257d = aVar;
        this.e = null;
    }

    public void a(r[] rVarArr) {
        r[] rVarArr2 = this.f1256c;
        if (rVarArr2 == null) {
            this.f1256c = rVarArr;
            return;
        }
        if (rVarArr == null || rVarArr.length <= 0) {
            return;
        }
        r[] rVarArr3 = new r[rVarArr2.length + rVarArr.length];
        System.arraycopy(rVarArr2, 0, rVarArr3, 0, rVarArr2.length);
        System.arraycopy(rVarArr, 0, rVarArr3, rVarArr2.length, rVarArr.length);
        this.f1256c = rVarArr3;
    }

    public a b() {
        return this.f1257d;
    }

    public byte[] c() {
        return this.b;
    }

    public Map<q, Object> d() {
        return this.e;
    }

    public r[] e() {
        return this.f1256c;
    }

    public String f() {
        return this.a;
    }

    public void g(Map<q, Object> map) {
        if (map != null) {
            Map<q, Object> map2 = this.e;
            if (map2 == null) {
                this.e = map;
            } else {
                map2.putAll(map);
            }
        }
    }

    public void h(q qVar, Object obj) {
        if (this.e == null) {
            this.e = new EnumMap(q.class);
        }
        this.e.put(qVar, obj);
    }

    public String toString() {
        return this.a;
    }
}
