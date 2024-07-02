package d.a;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class i {

    /* renamed from: c, reason: collision with root package name */
    private static i f2038c;
    private i a;
    private Map<String, k> b = new ConcurrentHashMap();

    private i() {
    }

    public static i a() {
        i iVar = new i();
        iVar.a = d();
        return iVar;
    }

    private static i d() {
        if (f2038c == null) {
            synchronized (i.class) {
                i iVar = new i();
                f2038c = iVar;
                iVar.e("pi").d(3.141592653589793d);
                f2038c.e("euler").d(2.718281828459045d);
            }
        }
        return f2038c;
    }

    public k b(String str) {
        if (this.b.containsKey(str)) {
            return this.b.get(str);
        }
        k kVar = new k(str);
        this.b.put(str, kVar);
        return kVar;
    }

    public k c(String str) {
        if (this.b.containsKey(str)) {
            return this.b.get(str);
        }
        i iVar = this.a;
        if (iVar != null) {
            return iVar.c(str);
        }
        return null;
    }

    public k e(String str) {
        k c2 = c(str);
        return c2 != null ? c2 : b(str);
    }
}
