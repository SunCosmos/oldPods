package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b {
    final InterfaceC0035b a;
    final a b = new a();

    /* renamed from: c, reason: collision with root package name */
    final List<View> f998c = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        long a = 0;
        a b;

        a() {
        }

        private void c() {
            if (this.b == null) {
                this.b = new a();
            }
        }

        void a(int i2) {
            if (i2 < 64) {
                this.a &= (1 << i2) ^ (-1);
                return;
            }
            a aVar = this.b;
            if (aVar != null) {
                aVar.a(i2 - 64);
            }
        }

        int b(int i2) {
            a aVar = this.b;
            return aVar == null ? i2 >= 64 ? Long.bitCount(this.a) : Long.bitCount(this.a & ((1 << i2) - 1)) : i2 < 64 ? Long.bitCount(this.a & ((1 << i2) - 1)) : aVar.b(i2 - 64) + Long.bitCount(this.a);
        }

        boolean d(int i2) {
            if (i2 < 64) {
                return (this.a & (1 << i2)) != 0;
            }
            c();
            return this.b.d(i2 - 64);
        }

        void e(int i2, boolean z) {
            if (i2 >= 64) {
                c();
                this.b.e(i2 - 64, z);
                return;
            }
            long j = this.a;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << i2) - 1;
            this.a = ((j & (j2 ^ (-1))) << 1) | (j & j2);
            if (z) {
                h(i2);
            } else {
                a(i2);
            }
            if (z2 || this.b != null) {
                c();
                this.b.e(0, z2);
            }
        }

        boolean f(int i2) {
            if (i2 >= 64) {
                c();
                return this.b.f(i2 - 64);
            }
            long j = 1 << i2;
            long j2 = this.a;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (j ^ (-1));
            this.a = j3;
            long j4 = j - 1;
            this.a = (j3 & j4) | Long.rotateRight((j4 ^ (-1)) & j3, 1);
            a aVar = this.b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.b.f(0);
            }
            return z;
        }

        void g() {
            this.a = 0L;
            a aVar = this.b;
            if (aVar != null) {
                aVar.g();
            }
        }

        void h(int i2) {
            if (i2 < 64) {
                this.a |= 1 << i2;
            } else {
                c();
                this.b.h(i2 - 64);
            }
        }

        public String toString() {
            if (this.b == null) {
                return Long.toBinaryString(this.a);
            }
            return this.b.toString() + "xx" + Long.toBinaryString(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.recyclerview.widget.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0035b {
        View a(int i2);

        void b(View view);

        void c(int i2);

        void d();

        RecyclerView.ViewHolder e(View view);

        void f(int i2);

        void g(View view);

        void h(View view, int i2, ViewGroup.LayoutParams layoutParams);

        void i(View view, int i2);

        int j(View view);

        int k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(InterfaceC0035b interfaceC0035b) {
        this.a = interfaceC0035b;
    }

    private int h(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int k = this.a.k();
        int i3 = i2;
        while (i3 < k) {
            int b = i2 - (i3 - this.b.b(i3));
            if (b == 0) {
                while (this.b.d(i3)) {
                    i3++;
                }
                return i3;
            }
            i3 += b;
        }
        return -1;
    }

    private void l(View view) {
        this.f998c.add(view);
        this.a.b(view);
    }

    private boolean t(View view) {
        if (!this.f998c.remove(view)) {
            return false;
        }
        this.a.g(view);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, int i2, boolean z) {
        int k = i2 < 0 ? this.a.k() : h(i2);
        this.b.e(k, z);
        if (z) {
            l(view);
        }
        this.a.i(view, k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(View view, boolean z) {
        a(view, -1, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z) {
        int k = i2 < 0 ? this.a.k() : h(i2);
        this.b.e(k, z);
        if (z) {
            l(view);
        }
        this.a.h(view, k, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i2) {
        int h = h(i2);
        this.b.f(h);
        this.a.f(h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View e(int i2) {
        int size = this.f998c.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view = this.f998c.get(i3);
            RecyclerView.ViewHolder e = this.a.e(view);
            if (e.getLayoutPosition() == i2 && !e.o() && !e.p()) {
                return view;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View f(int i2) {
        return this.a.a(h(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.a.k() - this.f998c.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View i(int i2) {
        return this.a.a(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j() {
        return this.a.k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(View view) {
        int j = this.a.j(view);
        if (j >= 0) {
            this.b.h(j);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int m(View view) {
        int j = this.a.j(view);
        if (j == -1 || this.b.d(j)) {
            return -1;
        }
        return j - this.b.b(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean n(View view) {
        return this.f998c.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o() {
        this.b.g();
        for (int size = this.f998c.size() - 1; size >= 0; size--) {
            this.a.g(this.f998c.get(size));
            this.f998c.remove(size);
        }
        this.a.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(View view) {
        int j = this.a.j(view);
        if (j < 0) {
            return;
        }
        if (this.b.f(j)) {
            t(view);
        }
        this.a.c(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(int i2) {
        int h = h(i2);
        View a2 = this.a.a(h);
        if (a2 == null) {
            return;
        }
        if (this.b.f(h)) {
            t(a2);
        }
        this.a.c(h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean r(View view) {
        int j = this.a.j(view);
        if (j == -1) {
            t(view);
            return true;
        }
        if (!this.b.d(j)) {
            return false;
        }
        this.b.f(j);
        t(view);
        this.a.c(j);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(View view) {
        int j = this.a.j(view);
        if (j < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.b.d(j)) {
            this.b.a(j);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.f998c.size();
    }
}
