package d.b;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class b<T> {
    protected List<T> a = new ArrayList();
    protected boolean b = false;

    /* renamed from: c, reason: collision with root package name */
    protected List<d> f2041c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    protected T f2042d;

    public T a() {
        T c2 = c();
        b(1);
        return c2;
    }

    public void b(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("numberOfItems < 0");
        }
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return;
            }
            if (!this.a.isEmpty()) {
                this.a.remove(0);
            } else {
                if (this.b) {
                    return;
                }
                if (e() == null) {
                    this.b = true;
                }
            }
            i2 = i3;
        }
    }

    public T c() {
        return g(0);
    }

    protected abstract T d();

    protected abstract T e();

    public T f() {
        return g(1);
    }

    public T g(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("offset < 0");
        }
        while (this.a.size() <= i2 && !this.b) {
            T e = e();
            if (e != null) {
                this.a.add(e);
            } else {
                this.b = true;
            }
        }
        if (i2 < this.a.size()) {
            return this.a.get(i2);
        }
        if (this.f2042d == null) {
            this.f2042d = d();
        }
        return this.f2042d;
    }

    public void h(List<d> list) {
        this.f2041c = list;
    }
}
