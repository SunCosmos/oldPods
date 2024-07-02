package c.d.a;

import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class a implements Cloneable {
    ArrayList<InterfaceC0049a> a = null;

    /* renamed from: c.d.a.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0049a {
        void a(a aVar);

        void b(a aVar);

        void c(a aVar);

        void d(a aVar);
    }

    public void a(InterfaceC0049a interfaceC0049a) {
        if (this.a == null) {
            this.a = new ArrayList<>();
        }
        this.a.add(interfaceC0049a);
    }

    public void b() {
    }

    @Override // 
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public a clone() {
        try {
            a aVar = (a) super.clone();
            ArrayList<InterfaceC0049a> arrayList = this.a;
            if (arrayList != null) {
                aVar.a = new ArrayList<>();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    aVar.a.add(arrayList.get(i2));
                }
            }
            return aVar;
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public ArrayList<InterfaceC0049a> d() {
        return this.a;
    }

    public abstract boolean e();

    public void f(InterfaceC0049a interfaceC0049a) {
        ArrayList<InterfaceC0049a> arrayList = this.a;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(interfaceC0049a);
        if (this.a.size() == 0) {
            this.a = null;
        }
    }

    public abstract a g(long j);

    public abstract void h(long j);

    public void i(Object obj) {
    }

    public void j() {
    }
}
