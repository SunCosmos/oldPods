package com.iapp.app;

import android.view.animation.Animation;
import c.d.a.a;

/* loaded from: classes.dex */
public class l {
    private c.b.a.a.t a;
    private Object[] b;

    /* renamed from: c, reason: collision with root package name */
    private int f1940c;

    /* renamed from: d, reason: collision with root package name */
    private Aid_YuCodeX f1941d;

    /* loaded from: classes.dex */
    class a implements Animation.AnimationListener {
        a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            l.this.a.f(l.this.b[0].toString());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            if (l.this.f1940c > 1) {
                l.this.a.f(l.this.b[1].toString());
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            if (l.this.f1940c > 2) {
                l.this.a.f(l.this.b[2].toString());
            }
        }
    }

    /* loaded from: classes.dex */
    class b extends c.d.a.b {
        b() {
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            l.this.a.f(l.this.b[0].toString());
        }
    }

    /* loaded from: classes.dex */
    class c implements a.InterfaceC0049a {
        c() {
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            l.this.a.f(l.this.b[0].toString());
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void b(c.d.a.a aVar) {
            if (l.this.f1940c > 1) {
                l.this.a.f(l.this.b[1].toString());
            }
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void c(c.d.a.a aVar) {
            if (l.this.f1940c > 2) {
                l.this.a.f(l.this.b[2].toString());
            }
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void d(c.d.a.a aVar) {
            if (l.this.f1940c > 3) {
                l.this.a.f(l.this.b[3].toString());
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements Animation.AnimationListener {
        d() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            l.this.f1941d.YuGoX(l.this.b[0].toString());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            if (l.this.f1940c > 1) {
                l.this.f1941d.YuGoX(l.this.b[1].toString());
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            if (l.this.f1940c > 2) {
                l.this.f1941d.YuGoX(l.this.b[2].toString());
            }
        }
    }

    /* loaded from: classes.dex */
    class e extends c.d.a.b {
        e() {
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            l.this.f1941d.YuGoX(l.this.b[0].toString());
        }
    }

    /* loaded from: classes.dex */
    class f implements a.InterfaceC0049a {
        f() {
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            l.this.f1941d.YuGoX(l.this.b[0].toString());
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void b(c.d.a.a aVar) {
            if (l.this.f1940c > 1) {
                l.this.f1941d.YuGoX(l.this.b[1].toString());
            }
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void c(c.d.a.a aVar) {
            if (l.this.f1940c > 2) {
                l.this.f1941d.YuGoX(l.this.b[2].toString());
            }
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void d(c.d.a.a aVar) {
            if (l.this.f1940c > 3) {
                l.this.f1941d.YuGoX(l.this.b[3].toString());
            }
        }
    }

    public l(c.b.a.a.t tVar, Animation animation, Object[] objArr) {
        this.a = null;
        this.b = null;
        this.f1940c = -1;
        this.f1941d = null;
        this.a = tVar;
        this.b = objArr;
        this.f1940c = objArr.length;
        animation.setAnimationListener(new a());
    }

    public l(c.b.a.a.t tVar, c.d.a.a aVar, Object[] objArr) {
        this.a = null;
        this.b = null;
        this.f1940c = -1;
        this.f1941d = null;
        this.a = tVar;
        this.b = objArr;
        int length = objArr.length;
        this.f1940c = length;
        aVar.a(length == 1 ? new b() : new c());
    }

    public l(Aid_YuCodeX aid_YuCodeX, Animation animation, Object[] objArr) {
        this.a = null;
        this.b = null;
        this.f1940c = -1;
        this.f1941d = null;
        this.f1941d = aid_YuCodeX;
        this.b = objArr;
        this.f1940c = objArr.length;
        animation.setAnimationListener(new d());
    }

    public l(Aid_YuCodeX aid_YuCodeX, c.d.a.a aVar, Object[] objArr) {
        this.a = null;
        this.b = null;
        this.f1940c = -1;
        this.f1941d = null;
        this.f1941d = aid_YuCodeX;
        this.b = objArr;
        int length = objArr.length;
        this.f1940c = length;
        aVar.a(length == 1 ? new e() : new f());
    }
}
