package androidx.recyclerview.widget;

import androidx.core.util.Pools;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.h;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a implements h.a {
    private Pools.Pool<b> a;
    final ArrayList<b> b;

    /* renamed from: c, reason: collision with root package name */
    final ArrayList<b> f994c;

    /* renamed from: d, reason: collision with root package name */
    final InterfaceC0034a f995d;
    Runnable e;
    final boolean f;
    final h g;
    private int h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.recyclerview.widget.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0034a {
        void a(int i2, int i3);

        void b(b bVar);

        RecyclerView.ViewHolder c(int i2);

        void d(int i2, int i3);

        void e(int i2, int i3);

        void f(b bVar);

        void g(int i2, int i3);

        void h(int i2, int i3, Object obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        int a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        Object f996c;

        /* renamed from: d, reason: collision with root package name */
        int f997d;

        b(int i2, int i3, int i4, Object obj) {
            this.a = i2;
            this.b = i3;
            this.f997d = i4;
            this.f996c = obj;
        }

        String a() {
            int i2 = this.a;
            return i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i2 = this.a;
            if (i2 != bVar.a) {
                return false;
            }
            if (i2 == 8 && Math.abs(this.f997d - this.b) == 1 && this.f997d == bVar.b && this.b == bVar.f997d) {
                return true;
            }
            if (this.f997d != bVar.f997d || this.b != bVar.b) {
                return false;
            }
            Object obj2 = this.f996c;
            Object obj3 = bVar.f996c;
            if (obj2 != null) {
                if (!obj2.equals(obj3)) {
                    return false;
                }
            } else if (obj3 != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.a * 31) + this.b) * 31) + this.f997d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.b + "c:" + this.f997d + ",p:" + this.f996c + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(InterfaceC0034a interfaceC0034a) {
        this(interfaceC0034a, false);
    }

    a(InterfaceC0034a interfaceC0034a, boolean z) {
        this.a = new Pools.SimplePool(30);
        this.b = new ArrayList<>();
        this.f994c = new ArrayList<>();
        this.h = 0;
        this.f995d = interfaceC0034a;
        this.f = z;
        this.g = new h(this);
    }

    private void c(b bVar) {
        v(bVar);
    }

    private void d(b bVar) {
        v(bVar);
    }

    private void f(b bVar) {
        boolean z;
        char c2;
        int i2 = bVar.b;
        int i3 = bVar.f997d + i2;
        char c3 = 65535;
        int i4 = i2;
        int i5 = 0;
        while (i4 < i3) {
            if (this.f995d.c(i4) != null || h(i4)) {
                if (c3 == 0) {
                    k(b(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                c2 = 1;
            } else {
                if (c3 == 1) {
                    v(b(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                c2 = 0;
            }
            if (z) {
                i4 -= i5;
                i3 -= i5;
                i5 = 1;
            } else {
                i5++;
            }
            i4++;
            c3 = c2;
        }
        if (i5 != bVar.f997d) {
            a(bVar);
            bVar = b(2, i2, i5, null);
        }
        if (c3 == 0) {
            k(bVar);
        } else {
            v(bVar);
        }
    }

    private void g(b bVar) {
        int i2 = bVar.b;
        int i3 = bVar.f997d + i2;
        int i4 = i2;
        char c2 = 65535;
        int i5 = 0;
        while (i2 < i3) {
            if (this.f995d.c(i2) != null || h(i2)) {
                if (c2 == 0) {
                    k(b(4, i4, i5, bVar.f996c));
                    i4 = i2;
                    i5 = 0;
                }
                c2 = 1;
            } else {
                if (c2 == 1) {
                    v(b(4, i4, i5, bVar.f996c));
                    i4 = i2;
                    i5 = 0;
                }
                c2 = 0;
            }
            i5++;
            i2++;
        }
        if (i5 != bVar.f997d) {
            Object obj = bVar.f996c;
            a(bVar);
            bVar = b(4, i4, i5, obj);
        }
        if (c2 == 0) {
            k(bVar);
        } else {
            v(bVar);
        }
    }

    private boolean h(int i2) {
        int size = this.f994c.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f994c.get(i3);
            int i4 = bVar.a;
            if (i4 == 8) {
                if (n(bVar.f997d, i3 + 1) == i2) {
                    return true;
                }
            } else if (i4 == 1) {
                int i5 = bVar.b;
                int i6 = bVar.f997d + i5;
                while (i5 < i6) {
                    if (n(i5, i3 + 1) == i2) {
                        return true;
                    }
                    i5++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private void k(b bVar) {
        int i2;
        int i3 = bVar.a;
        if (i3 == 1 || i3 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int z = z(bVar.b, i3);
        int i4 = bVar.b;
        int i5 = bVar.a;
        if (i5 == 2) {
            i2 = 0;
        } else {
            if (i5 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i2 = 1;
        }
        int i6 = 1;
        for (int i7 = 1; i7 < bVar.f997d; i7++) {
            int z2 = z(bVar.b + (i2 * i7), bVar.a);
            int i8 = bVar.a;
            if (i8 == 2 ? z2 == z : i8 == 4 && z2 == z + 1) {
                i6++;
            } else {
                b b2 = b(i8, z, i6, bVar.f996c);
                l(b2, i4);
                a(b2);
                if (bVar.a == 4) {
                    i4 += i6;
                }
                z = z2;
                i6 = 1;
            }
        }
        Object obj = bVar.f996c;
        a(bVar);
        if (i6 > 0) {
            b b3 = b(bVar.a, z, i6, obj);
            l(b3, i4);
            a(b3);
        }
    }

    private void v(b bVar) {
        this.f994c.add(bVar);
        int i2 = bVar.a;
        if (i2 == 1) {
            this.f995d.g(bVar.b, bVar.f997d);
            return;
        }
        if (i2 == 2) {
            this.f995d.e(bVar.b, bVar.f997d);
            return;
        }
        if (i2 == 4) {
            this.f995d.h(bVar.b, bVar.f997d, bVar.f996c);
        } else {
            if (i2 == 8) {
                this.f995d.a(bVar.b, bVar.f997d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    private int z(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        for (int size = this.f994c.size() - 1; size >= 0; size--) {
            b bVar = this.f994c.get(size);
            int i10 = bVar.a;
            if (i10 == 8) {
                int i11 = bVar.b;
                int i12 = bVar.f997d;
                if (i11 < i12) {
                    i6 = i11;
                    i5 = i12;
                } else {
                    i5 = i11;
                    i6 = i12;
                }
                if (i2 < i6 || i2 > i5) {
                    if (i2 < i11) {
                        if (i3 == 1) {
                            bVar.b = i11 + 1;
                            i7 = i12 + 1;
                        } else if (i3 == 2) {
                            bVar.b = i11 - 1;
                            i7 = i12 - 1;
                        }
                        bVar.f997d = i7;
                    }
                } else if (i6 == i11) {
                    if (i3 == 1) {
                        i9 = i12 + 1;
                    } else {
                        if (i3 == 2) {
                            i9 = i12 - 1;
                        }
                        i2++;
                    }
                    bVar.f997d = i9;
                    i2++;
                } else {
                    if (i3 == 1) {
                        i8 = i11 + 1;
                    } else {
                        if (i3 == 2) {
                            i8 = i11 - 1;
                        }
                        i2--;
                    }
                    bVar.b = i8;
                    i2--;
                }
            } else {
                int i13 = bVar.b;
                if (i13 > i2) {
                    if (i3 == 1) {
                        i4 = i13 + 1;
                    } else if (i3 == 2) {
                        i4 = i13 - 1;
                    }
                    bVar.b = i4;
                } else if (i10 == 1) {
                    i2 -= bVar.f997d;
                } else if (i10 == 2) {
                    i2 += bVar.f997d;
                }
            }
        }
        for (int size2 = this.f994c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f994c.get(size2);
            if (bVar2.a == 8) {
                int i14 = bVar2.f997d;
                if (i14 != bVar2.b && i14 >= 0) {
                }
                this.f994c.remove(size2);
                a(bVar2);
            } else {
                if (bVar2.f997d > 0) {
                }
                this.f994c.remove(size2);
                a(bVar2);
            }
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.h.a
    public void a(b bVar) {
        if (this.f) {
            return;
        }
        bVar.f996c = null;
        this.a.release(bVar);
    }

    @Override // androidx.recyclerview.widget.h.a
    public b b(int i2, int i3, int i4, Object obj) {
        b acquire = this.a.acquire();
        if (acquire == null) {
            return new b(i2, i3, i4, obj);
        }
        acquire.a = i2;
        acquire.b = i3;
        acquire.f997d = i4;
        acquire.f996c = obj;
        return acquire;
    }

    public int e(int i2) {
        int size = this.b.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.b.get(i3);
            int i4 = bVar.a;
            if (i4 != 1) {
                if (i4 == 2) {
                    int i5 = bVar.b;
                    if (i5 <= i2) {
                        int i6 = bVar.f997d;
                        if (i5 + i6 > i2) {
                            return -1;
                        }
                        i2 -= i6;
                    } else {
                        continue;
                    }
                } else if (i4 == 8) {
                    int i7 = bVar.b;
                    if (i7 == i2) {
                        i2 = bVar.f997d;
                    } else {
                        if (i7 < i2) {
                            i2--;
                        }
                        if (bVar.f997d <= i2) {
                            i2++;
                        }
                    }
                }
            } else if (bVar.b <= i2) {
                i2 += bVar.f997d;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i() {
        int size = this.f994c.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f995d.b(this.f994c.get(i2));
        }
        x(this.f994c);
        this.h = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        i();
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.b.get(i2);
            int i3 = bVar.a;
            if (i3 == 1) {
                this.f995d.b(bVar);
                this.f995d.g(bVar.b, bVar.f997d);
            } else if (i3 == 2) {
                this.f995d.b(bVar);
                this.f995d.d(bVar.b, bVar.f997d);
            } else if (i3 == 4) {
                this.f995d.b(bVar);
                this.f995d.h(bVar.b, bVar.f997d, bVar.f996c);
            } else if (i3 == 8) {
                this.f995d.b(bVar);
                this.f995d.a(bVar.b, bVar.f997d);
            }
            Runnable runnable = this.e;
            if (runnable != null) {
                runnable.run();
            }
        }
        x(this.b);
        this.h = 0;
    }

    void l(b bVar, int i2) {
        this.f995d.f(bVar);
        int i3 = bVar.a;
        if (i3 == 2) {
            this.f995d.d(i2, bVar.f997d);
        } else {
            if (i3 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f995d.h(i2, bVar.f997d, bVar.f996c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int m(int i2) {
        return n(i2, 0);
    }

    int n(int i2, int i3) {
        int size = this.f994c.size();
        while (i3 < size) {
            b bVar = this.f994c.get(i3);
            int i4 = bVar.a;
            if (i4 == 8) {
                int i5 = bVar.b;
                if (i5 == i2) {
                    i2 = bVar.f997d;
                } else {
                    if (i5 < i2) {
                        i2--;
                    }
                    if (bVar.f997d <= i2) {
                        i2++;
                    }
                }
            } else {
                int i6 = bVar.b;
                if (i6 > i2) {
                    continue;
                } else if (i4 == 2) {
                    int i7 = bVar.f997d;
                    if (i2 < i6 + i7) {
                        return -1;
                    }
                    i2 -= i7;
                } else if (i4 == 1) {
                    i2 += bVar.f997d;
                }
            }
            i3++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean o(int i2) {
        return (i2 & this.h) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean p() {
        return this.b.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean q() {
        return (this.f994c.isEmpty() || this.b.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean r(int i2, int i3, Object obj) {
        if (i3 < 1) {
            return false;
        }
        this.b.add(b(4, i2, i3, obj));
        this.h |= 4;
        return this.b.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean s(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.b.add(b(1, i2, i3, null));
        this.h |= 1;
        return this.b.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean t(int i2, int i3, int i4) {
        if (i2 == i3) {
            return false;
        }
        if (i4 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.b.add(b(8, i2, i3, null));
        this.h |= 8;
        return this.b.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean u(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.b.add(b(2, i2, i3, null));
        this.h |= 2;
        return this.b.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w() {
        this.g.b(this.b);
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.b.get(i2);
            int i3 = bVar.a;
            if (i3 == 1) {
                c(bVar);
            } else if (i3 == 2) {
                f(bVar);
            } else if (i3 == 4) {
                g(bVar);
            } else if (i3 == 8) {
                d(bVar);
            }
            Runnable runnable = this.e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.b.clear();
    }

    void x(List<b> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(list.get(i2));
        }
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y() {
        x(this.b);
        x(this.f994c);
        this.h = 0;
    }
}
