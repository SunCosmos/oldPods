package com.iapp.app;

import android.app.Activity;
import com.iapp.app.run.main2;
import com.iapp.app.run.mian;
import com.iapp.app.x5.WebView;

/* loaded from: classes.dex */
public class n {
    private Activity a;
    private WebView b;

    /* renamed from: c, reason: collision with root package name */
    private com.iapp.app.d f1946c;

    /* renamed from: d, reason: collision with root package name */
    private String f1947d;
    private String e;
    private j f;
    private android.webkit.WebView g;
    private String h;

    /* loaded from: classes.dex */
    class a implements com.iapp.app.x5.a {
        a() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            c.b.a.a.t tVar = new c.b.a.a.t(n.this.a);
            tVar.T("st_vId", Integer.valueOf(n.this.b.getId()));
            tVar.T("st_vW", n.this.b);
            tVar.T("st_url", str);
            tVar.T("st_uT", str2);
            tVar.T("st_cN", str3);
            tVar.T("st_mE", str4);
            tVar.T("st_cH", Long.valueOf(j));
            tVar.f(n.this.f1947d);
        }
    }

    /* loaded from: classes.dex */
    class b implements com.iapp.app.x5.a {
        b() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(n.this.a);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(n.this.b.getId()));
            aid_YuCodeX.dim("st_vW", n.this.b);
            aid_YuCodeX.dim("st_url", str);
            aid_YuCodeX.dim("st_uT", str2);
            aid_YuCodeX.dim("st_cN", str3);
            aid_YuCodeX.dim("st_mE", str4);
            aid_YuCodeX.dim("st_cH", Long.valueOf(j));
            mian.c(n.this.e, "ondownloadstart" + n.this.f1947d, aid_YuCodeX);
        }
    }

    /* loaded from: classes.dex */
    class c implements com.iapp.app.x5.a {
        c() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            int id = n.this.b.getId();
            n.this.f1946c.d("ondownloadstart" + id, Integer.valueOf(id), n.this.b, str, str2, str3, str4, Long.valueOf(j));
        }
    }

    /* loaded from: classes.dex */
    class d implements com.iapp.app.x5.a {
        d() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            int id = n.this.b.getId();
            n.this.f.b("ondownloadstart" + id, Integer.valueOf(id), n.this.b, str, str2, str3, str4, Long.valueOf(j));
        }
    }

    /* loaded from: classes.dex */
    class e implements com.iapp.app.x5.a {
        e() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            int id = n.this.b.getId();
            n.this.i("ondownloadstart" + id, id + ", '" + n.this.h + "', '" + str + "', '" + str2 + "', '" + str3 + "', '" + str4 + "', " + j);
        }
    }

    public n(WebView webView, Activity activity) {
        this.a = null;
        this.b = null;
        this.f1946c = null;
        this.f1947d = null;
        this.e = null;
        this.f = null;
        this.b = webView;
        this.a = activity;
        String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"ondownloadstart\">", "</eventItme>");
        this.f1947d = c2;
        if (c2 == null) {
            return;
        }
        webView.setDownloadListener(new a());
    }

    public n(WebView webView, Activity activity, String str) {
        this.a = null;
        this.b = null;
        this.f1946c = null;
        this.f1947d = null;
        this.e = null;
        this.f = null;
        this.b = webView;
        this.a = activity;
        this.e = str;
        String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"ondownloadstart\">", "</eventItme>");
        this.f1947d = c2;
        if (c2 == null) {
            return;
        }
        webView.setDownloadListener(new b());
    }

    public n(WebView webView, android.webkit.WebView webView2, int i2) {
        this.a = null;
        this.b = null;
        this.f1946c = null;
        this.f1947d = null;
        this.e = null;
        this.f = null;
        this.b = webView;
        this.g = webView2;
        String str = "^ondownloadstart" + i2 + "st_vW" + webView.getId();
        this.h = str;
        main2.set(str, webView);
        webView.setDownloadListener(new e());
    }

    public n(WebView webView, com.iapp.app.d dVar) {
        this.a = null;
        this.b = null;
        this.f1946c = null;
        this.f1947d = null;
        this.e = null;
        this.f = null;
        this.b = webView;
        this.f1946c = dVar;
        webView.setDownloadListener(new c());
    }

    public n(WebView webView, j jVar) {
        this.a = null;
        this.b = null;
        this.f1946c = null;
        this.f1947d = null;
        this.e = null;
        this.f = null;
        this.b = webView;
        this.f = jVar;
        webView.setDownloadListener(new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(String str, String str2) {
        this.g.loadUrl("javascript:" + str + "(" + str2 + ")");
    }
}
