package com.iapp.app;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.iapp.app.run.main2;
import com.iapp.app.run.mian;
import com.oldpods.app.R;

/* loaded from: classes.dex */
public class o {
    private Activity a;
    private DrawerLayout b;

    /* renamed from: c, reason: collision with root package name */
    private ActionBarDrawerToggle f1948c;

    /* renamed from: d, reason: collision with root package name */
    private String f1949d;
    private String e;
    private String f;
    private String g;
    private boolean h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1950i;
    private boolean j;
    private com.iapp.app.d k;
    private int l;
    private j m;
    private int n;
    private WebView o;
    private String p;

    /* loaded from: classes.dex */
    class a extends ActionBarDrawerToggle {
        a(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3) {
            super(activity, drawerLayout, toolbar, i2, i3);
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (o.this.f1949d != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(o.this.a);
                tVar.T("st_vId", Integer.valueOf(o.this.b.getId()));
                tVar.T("st_vW", o.this.b);
                tVar.T("st_dW", view);
                tVar.f(o.this.f1949d);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (o.this.e != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(o.this.a);
                tVar.T("st_vId", Integer.valueOf(o.this.b.getId()));
                tVar.T("st_vW", o.this.b);
                tVar.T("st_dW", view);
                tVar.f(o.this.e);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (o.this.f == null) {
                return false;
            }
            c.b.a.a.t tVar = new c.b.a.a.t(o.this.a);
            tVar.T("st_vId", Integer.valueOf(o.this.b.getId()));
            tVar.T("st_vW", o.this.b);
            tVar.T("st_iM", menuItem);
            tVar.f(o.this.f);
            return false;
        }
    }

    /* loaded from: classes.dex */
    class b extends ActionBarDrawerToggle {
        b(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3) {
            super(activity, drawerLayout, toolbar, i2, i3);
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (o.this.f1949d != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(o.this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(o.this.b.getId()));
                aid_YuCodeX.dim("st_vW", o.this.b);
                aid_YuCodeX.dim("st_dW", view);
                mian.c(o.this.g, "ondrawerclosed" + o.this.f1949d, aid_YuCodeX);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (o.this.e != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(o.this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(o.this.b.getId()));
                aid_YuCodeX.dim("st_vW", o.this.b);
                aid_YuCodeX.dim("st_dW", view);
                mian.c(o.this.g, "ondraweropened" + o.this.e, aid_YuCodeX);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (o.this.f == null) {
                return false;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(o.this.a);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(o.this.b.getId()));
            aid_YuCodeX.dim("st_vW", o.this.b);
            aid_YuCodeX.dim("st_iM", menuItem);
            mian.c(o.this.g, "onoptionsitemselected" + o.this.f, aid_YuCodeX);
            return false;
        }
    }

    /* loaded from: classes.dex */
    class c extends ActionBarDrawerToggle {
        c(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3) {
            super(activity, drawerLayout, toolbar, i2, i3);
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (o.this.h) {
                o.this.k.d("ondrawerclosed" + o.this.l, Integer.valueOf(o.this.l), o.this.b, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (o.this.f1950i) {
                o.this.k.d("ondraweropened" + o.this.l, Integer.valueOf(o.this.l), o.this.b, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (!o.this.j) {
                return false;
            }
            return o.this.k.e("onoptionsitemselected" + o.this.l, Integer.valueOf(o.this.l), o.this.b, menuItem);
        }
    }

    /* loaded from: classes.dex */
    class d extends ActionBarDrawerToggle {
        d(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3) {
            super(activity, drawerLayout, toolbar, i2, i3);
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (o.this.h) {
                o.this.m.b("ondrawerclosed" + o.this.l, Integer.valueOf(o.this.l), o.this.b, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (o.this.f1950i) {
                o.this.m.b("ondraweropened" + o.this.l, Integer.valueOf(o.this.l), o.this.b, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (!o.this.j) {
                return false;
            }
            return o.this.m.c("onoptionsitemselected" + o.this.l, Integer.valueOf(o.this.l), o.this.b, menuItem);
        }
    }

    /* loaded from: classes.dex */
    class e extends ActionBarDrawerToggle {
        e(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3) {
            super(activity, drawerLayout, toolbar, i2, i3);
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (o.this.h) {
                o oVar = o.this;
                String str = "ondrawerclosed" + o.this.l;
                StringBuilder sb = new StringBuilder();
                sb.append(o.this.l);
                sb.append(", '");
                sb.append(o.this.p);
                sb.append("', '");
                sb.append(main2.set("^ondrawerclosed" + o.this.n + "st_dW" + o.this.l, view));
                sb.append("'");
                oVar.p(str, sb.toString());
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (o.this.f1950i) {
                o oVar = o.this;
                String str = "ondraweropened" + o.this.l;
                StringBuilder sb = new StringBuilder();
                sb.append(o.this.l);
                sb.append(", '");
                sb.append(o.this.p);
                sb.append("', '");
                sb.append(main2.set("^ondraweropened" + o.this.n + "st_dW" + o.this.l, view));
                sb.append("'");
                oVar.p(str, sb.toString());
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (!o.this.j) {
                return false;
            }
            o oVar = o.this;
            String str = "onoptionsitemselected" + o.this.l;
            StringBuilder sb = new StringBuilder();
            sb.append(o.this.l);
            sb.append(", '");
            sb.append(o.this.p);
            sb.append("', '");
            sb.append(main2.set("^onoptionsitemselected" + o.this.n + "st_iM" + o.this.l, menuItem));
            sb.append("'");
            oVar.p(str, sb.toString());
            return false;
        }
    }

    public o(DrawerLayout drawerLayout, Activity activity) {
        this.a = null;
        this.b = null;
        this.f1948c = null;
        this.g = null;
        this.m = null;
        this.b = drawerLayout;
        this.a = activity;
        String obj = ((Object[]) drawerLayout.getTag())[2].toString();
        this.f1949d = c.b.a.a.p.c(obj, "<eventItme type=\"ondrawerclosed\">", "</eventItme>");
        this.e = c.b.a.a.p.c(obj, "<eventItme type=\"ondraweropened\">", "</eventItme>");
        this.f = c.b.a.a.p.c(obj, "<eventItme type=\"onoptionsitemselected\">", "</eventItme>");
        a aVar = new a(activity, drawerLayout, null, R.string.app_name, R.string.app_name);
        this.f1948c = aVar;
        drawerLayout.setDrawerListener(aVar);
    }

    public o(DrawerLayout drawerLayout, Activity activity, String str) {
        this.a = null;
        this.b = null;
        this.f1948c = null;
        this.g = null;
        this.m = null;
        this.b = drawerLayout;
        this.a = activity;
        this.g = str;
        String obj = ((Object[]) drawerLayout.getTag())[2].toString();
        this.f1949d = c.b.a.a.p.c(obj, "<eventItme type=\"ondrawerclosed\">", "</eventItme>");
        this.e = c.b.a.a.p.c(obj, "<eventItme type=\"ondraweropened\">", "</eventItme>");
        this.f = c.b.a.a.p.c(obj, "<eventItme type=\"onoptionsitemselected\">", "</eventItme>");
        b bVar = new b(activity, drawerLayout, null, R.string.app_name, R.string.app_name);
        this.f1948c = bVar;
        drawerLayout.setDrawerListener(bVar);
    }

    public o(DrawerLayout drawerLayout, WebView webView, String str, int i2, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.f1948c = null;
        this.g = null;
        this.m = null;
        this.b = drawerLayout;
        this.l = drawerLayout.getId();
        this.o = webView;
        this.p = str;
        this.n = i2;
        this.h = z;
        this.f1950i = z2;
        this.j = z3;
        e eVar = new e(this.a, drawerLayout, null, R.string.app_name, R.string.app_name);
        this.f1948c = eVar;
        drawerLayout.setDrawerListener(eVar);
    }

    public o(DrawerLayout drawerLayout, com.iapp.app.d dVar, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.f1948c = null;
        this.g = null;
        this.m = null;
        this.b = drawerLayout;
        this.l = drawerLayout.getId();
        this.k = dVar;
        this.h = z;
        this.f1950i = z2;
        this.j = z3;
        c cVar = new c(this.a, drawerLayout, null, R.string.app_name, R.string.app_name);
        this.f1948c = cVar;
        drawerLayout.setDrawerListener(cVar);
    }

    public o(DrawerLayout drawerLayout, j jVar, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.f1948c = null;
        this.g = null;
        this.m = null;
        this.b = drawerLayout;
        this.l = drawerLayout.getId();
        this.m = jVar;
        this.h = z;
        this.f1950i = z2;
        this.j = z3;
        d dVar = new d(this.a, drawerLayout, null, R.string.app_name, R.string.app_name);
        this.f1948c = dVar;
        drawerLayout.setDrawerListener(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(String str, String str2) {
        this.o.loadUrl("javascript:" + str + "(" + str2 + ")");
    }
}
