package androidx.recyclerview.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
class ViewBoundsCheck {
    final b a;
    a b = new a();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ViewBounds {
    }

    /* loaded from: classes.dex */
    static class a {
        int a = 0;
        int b;

        /* renamed from: c, reason: collision with root package name */
        int f992c;

        /* renamed from: d, reason: collision with root package name */
        int f993d;
        int e;

        a() {
        }

        void a(int i2) {
            this.a = i2 | this.a;
        }

        boolean b() {
            int i2 = this.a;
            if ((i2 & 7) != 0 && (i2 & (c(this.f993d, this.b) << 0)) == 0) {
                return false;
            }
            int i3 = this.a;
            if ((i3 & 112) != 0 && (i3 & (c(this.f993d, this.f992c) << 4)) == 0) {
                return false;
            }
            int i4 = this.a;
            if ((i4 & 1792) != 0 && (i4 & (c(this.e, this.b) << 8)) == 0) {
                return false;
            }
            int i5 = this.a;
            return (i5 & 28672) == 0 || (i5 & (c(this.e, this.f992c) << 12)) != 0;
        }

        int c(int i2, int i3) {
            if (i2 > i3) {
                return 1;
            }
            return i2 == i3 ? 2 : 4;
        }

        void d() {
            this.a = 0;
        }

        void e(int i2, int i3, int i4, int i5) {
            this.b = i2;
            this.f992c = i3;
            this.f993d = i4;
            this.e = i5;
        }
    }

    /* loaded from: classes.dex */
    interface b {
        View a(int i2);

        int b();

        int c();

        int d(View view);

        int e(View view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewBoundsCheck(b bVar) {
        this.a = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View a(int i2, int i3, int i4, int i5) {
        int c2 = this.a.c();
        int b2 = this.a.b();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        while (i2 != i3) {
            View a2 = this.a.a(i2);
            this.b.e(c2, b2, this.a.e(a2), this.a.d(a2));
            if (i4 != 0) {
                this.b.d();
                this.b.a(i4);
                if (this.b.b()) {
                    return a2;
                }
            }
            if (i5 != 0) {
                this.b.d();
                this.b.a(i5);
                if (this.b.b()) {
                    view = a2;
                }
            }
            i2 += i6;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(View view, int i2) {
        this.b.e(this.a.c(), this.a.b(), this.a.e(view), this.a.d(view));
        if (i2 == 0) {
            return false;
        }
        this.b.d();
        this.b.a(i2);
        return this.b.b();
    }
}
