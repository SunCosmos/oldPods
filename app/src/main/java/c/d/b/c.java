package c.d.b;

/* loaded from: classes.dex */
public abstract class c<T, V> {
    private final String a;

    public c(Class<V> cls, String str) {
        this.a = str;
    }

    public abstract V a(T t);

    public String b() {
        return this.a;
    }

    public void c(T t, V v) {
        throw new UnsupportedOperationException("Property " + b() + " is read-only");
    }
}
