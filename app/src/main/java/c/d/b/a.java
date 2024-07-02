package c.d.b;

/* loaded from: classes.dex */
public abstract class a<T> extends c<T, Float> {
    public a(String str) {
        super(Float.class, str);
    }

    @Override // c.d.b.c
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final void c(T t, Float f) {
        e(t, f.floatValue());
    }

    public abstract void e(T t, float f);
}
