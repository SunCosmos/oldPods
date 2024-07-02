package com.iapp.app;

import android.app.Activity;
import android.webkit.WebView;
import androidx.viewpager.widget.ViewPager;
import com.iapp.app.run.mian;

/* loaded from: classes.dex */
public class q {
    private Activity a;
    private ViewPager b;

    /* renamed from: c, reason: collision with root package name */
    private String f1954c;

    /* renamed from: d, reason: collision with root package name */
    private String f1955d;
    private String e;
    private String f;
    private boolean g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1956i;
    private com.iapp.app.d j;
    private int k;
    private j l;
    private WebView m;
    private String n;

    /* loaded from: classes.dex */
    class a implements ViewPager.OnPageChangeListener {
        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (q.this.e != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(q.this.a);
                tVar.T("st_vId", Integer.valueOf(q.this.b.getId()));
                tVar.T("st_vW", q.this.b);
                tVar.T("st_sE", Integer.valueOf(i2));
                tVar.f(q.this.e);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (q.this.f1955d != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(q.this.a);
                tVar.T("st_vId", Integer.valueOf(q.this.b.getId()));
                tVar.T("st_vW", q.this.b);
                tVar.T("st_pN", Integer.valueOf(i2));
                tVar.T("st_pT", Float.valueOf(f));
                tVar.T("st_pS", Integer.valueOf(i3));
                tVar.f(q.this.f1955d);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (q.this.f1954c != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(q.this.a);
                tVar.T("st_vId", Integer.valueOf(q.this.b.getId()));
                tVar.T("st_vW", q.this.b);
                tVar.T("st_pN", Integer.valueOf(i2));
                tVar.f(q.this.f1954c);
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements ViewPager.OnPageChangeListener {
        b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (q.this.e != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(q.this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(q.this.b.getId()));
                aid_YuCodeX.dim("st_vW", q.this.b);
                aid_YuCodeX.dim("st_sE", Integer.valueOf(i2));
                mian.c(q.this.f, "onpagescrollstatechanged" + q.this.e, aid_YuCodeX);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (q.this.f1955d != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(q.this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(q.this.b.getId()));
                aid_YuCodeX.dim("st_vW", q.this.b);
                aid_YuCodeX.dim("st_pN", Integer.valueOf(i2));
                aid_YuCodeX.dim("st_pT", Float.valueOf(f));
                aid_YuCodeX.dim("st_pS", Integer.valueOf(i3));
                mian.c(q.this.f, "onpagescrolled" + q.this.f1955d, aid_YuCodeX);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (q.this.f1954c != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(q.this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(q.this.b.getId()));
                aid_YuCodeX.dim("st_vW", q.this.b);
                aid_YuCodeX.dim("st_pN", Integer.valueOf(i2));
                mian.c(q.this.f, "onpageselected" + q.this.f1954c, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements ViewPager.OnPageChangeListener {
        c() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (q.this.f1956i) {
                q.this.j.d("onpagescrollstatechanged" + q.this.k, Integer.valueOf(q.this.k), q.this.b, Integer.valueOf(i2));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (q.this.h) {
                q.this.j.d("onpagescrolled" + q.this.k, Integer.valueOf(q.this.k), q.this.b, Integer.valueOf(i2), Float.valueOf(f), Integer.valueOf(i3));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (q.this.g) {
                q.this.j.d("onpageselected" + q.this.k, Integer.valueOf(q.this.k), q.this.b, Integer.valueOf(i2));
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements ViewPager.OnPageChangeListener {
        d() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (q.this.f1956i) {
                q.this.l.b("onpagescrollstatechanged" + q.this.k, Integer.valueOf(q.this.k), q.this.b, Integer.valueOf(i2));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (q.this.h) {
                q.this.l.b("onpagescrolled" + q.this.k, Integer.valueOf(q.this.k), q.this.b, Integer.valueOf(i2), Float.valueOf(f), Integer.valueOf(i3));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (q.this.g) {
                q.this.l.b("onpageselected" + q.this.k, Integer.valueOf(q.this.k), q.this.b, Integer.valueOf(i2));
            }
        }
    }

    /* loaded from: classes.dex */
    class e implements ViewPager.OnPageChangeListener {
        e() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (q.this.f1956i) {
                q.this.j.d("onpagescrollstatechanged" + q.this.k, q.this.k + ", '" + q.this.n + "', " + i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (q.this.h) {
                q.this.j.d("onpagescrolled" + q.this.k, q.this.k + ", '" + q.this.n + "', " + i2 + ", " + f + ", " + i3);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (q.this.g) {
                q.this.o("onpageselected" + q.this.k, q.this.k + ", '" + q.this.n + "', " + i2);
            }
        }
    }

    public q(ViewPager viewPager, Activity activity) {
        this.a = null;
        this.b = null;
        this.f = null;
        this.l = null;
        this.a = activity;
        this.b = viewPager;
        String obj = ((Object[]) viewPager.getTag())[2].toString();
        this.f1954c = c.b.a.a.p.c(obj, "<eventItme type=\"onpageselected\">", "</eventItme>");
        this.f1955d = c.b.a.a.p.c(obj, "<eventItme type=\"onpagescrolled\">", "</eventItme>");
        this.e = c.b.a.a.p.c(obj, "<eventItme type=\"onpagescrollstatechanged\">", "</eventItme>");
        viewPager.setOnPageChangeListener(new a());
    }

    public q(ViewPager viewPager, Activity activity, String str) {
        this.a = null;
        this.b = null;
        this.f = null;
        this.l = null;
        this.a = activity;
        this.b = viewPager;
        this.f = str;
        String obj = ((Object[]) viewPager.getTag())[2].toString();
        this.f1954c = c.b.a.a.p.c(obj, "<eventItme type=\"onpageselected\">", "</eventItme>");
        this.f1955d = c.b.a.a.p.c(obj, "<eventItme type=\"onpagescrolled\">", "</eventItme>");
        this.e = c.b.a.a.p.c(obj, "<eventItme type=\"onpagescrollstatechanged\">", "</eventItme>");
        viewPager.setOnPageChangeListener(new b());
    }

    public q(ViewPager viewPager, WebView webView, String str, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.f = null;
        this.l = null;
        this.b = viewPager;
        this.m = webView;
        this.n = str;
        this.k = viewPager.getId();
        this.g = z;
        this.h = z2;
        this.f1956i = z3;
        viewPager.setOnPageChangeListener(new e());
    }

    public q(ViewPager viewPager, com.iapp.app.d dVar, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.f = null;
        this.l = null;
        this.b = viewPager;
        this.j = dVar;
        this.k = viewPager.getId();
        this.g = z;
        this.h = z2;
        this.f1956i = z3;
        viewPager.setOnPageChangeListener(new c());
    }

    public q(ViewPager viewPager, j jVar, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.f = null;
        this.l = null;
        this.b = viewPager;
        this.l = jVar;
        this.k = viewPager.getId();
        this.g = z;
        this.h = z2;
        this.f1956i = z3;
        viewPager.setOnPageChangeListener(new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(String str, String str2) {
        this.m.loadUrl("javascript:" + str + "(" + str2 + ")");
    }
}
