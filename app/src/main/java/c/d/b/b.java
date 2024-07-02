package c.d.b;

/* loaded from: classes.dex */
public abstract class b<T> extends c<T, Integer> {
    public b(String str) {
        super(Integer.class, str);
    }

    @Override // c.d.b.c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final void c(T t, Integer num) {
        c(t, Integer.valueOf(num.intValue()));
    }
}
