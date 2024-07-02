package com.iapp.app.x5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebViewClient;
import com.iapp.app.Aid_YuCodeX;
import com.iapp.app.Aid_javaCode;
import com.iapp.app.Aid_jsCode;
import com.iapp.app.Aid_luaCode;
import com.iapp.app.run.main;
import com.iapp.app.run.main2;
import com.iapp.app.run.main3;
import com.iapp.app.run.mian;
import org.keplerproject.luajava.LuaObject;

/* loaded from: classes.dex */
public class b {
    public static ValueCallback<Uri> a;
    public static ValueCallback<Uri[]> b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends WebChromeClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ Aid_jsCode b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f2009c;

        a(b bVar, boolean z, Aid_jsCode aid_jsCode, Activity activity) {
            this.a = z;
            this.b = aid_jsCode;
            this.f2009c = activity;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            if (this.a) {
                int id = webView.getId();
                this.b.callMethod("onprogresschanged" + id, id + ", '^view" + this.b.TaskId + "st_vW" + id + "', " + i2);
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.f2009c.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iapp.app.x5.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0068b extends WebViewClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ Aid_jsCode b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f2010c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f2011d;
        final /* synthetic */ boolean e;
        final /* synthetic */ boolean f;

        C0068b(b bVar, boolean z, Aid_jsCode aid_jsCode, Activity activity, boolean z2, boolean z3, boolean z4) {
            this.a = z;
            this.b = aid_jsCode;
            this.f2010c = activity;
            this.f2011d = z2;
            this.e = z3;
            this.f = z4;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.e) {
                int id = webView.getId();
                this.b.callMethod("onpagefinished" + id, id + ", '^view" + this.b.TaskId + "st_vW" + id + "', '" + str + "'");
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.f2011d) {
                int id = webView.getId();
                StringBuilder sb = new StringBuilder();
                sb.append(id);
                sb.append(", '^view");
                sb.append(this.b.TaskId);
                sb.append("st_vW");
                sb.append(id);
                sb.append("', '");
                sb.append(str);
                sb.append("', '");
                sb.append(main2.set("^onpagestarted" + this.b.TaskId + "st_bP" + id, bitmap));
                sb.append("'");
                this.b.callMethod("onpagestarted" + id, sb.toString());
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            if (this.f) {
                int id = webView.getId();
                this.b.callMethod("onreceivederror" + id, id + ", '^view" + this.b.TaskId + "st_vW" + id + "', '" + str2 + "', '" + str + "', " + i2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            if (!this.a) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.f2010c.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.f2010c, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            int id = webView.getId();
            this.b.callMethod("shouldoverrideurlloading" + id, id + ", '^view" + this.b.TaskId + "st_vW" + id + "', '" + str + "'");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends WebViewClient {
        final /* synthetic */ String[] a;
        final /* synthetic */ Aid_jsCode b;

        c(b bVar, String[] strArr, Aid_jsCode aid_jsCode) {
            this.a = strArr;
            this.b = aid_jsCode;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.a[2] != null) {
                int id = webView.getId();
                this.b.TheCallbackString(this.a[2], id + ", '^view" + this.b.TaskId + "st_vW" + id + "', '" + str + "'");
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.a[1] != null) {
                int id = webView.getId();
                Aid_jsCode aid_jsCode = this.b;
                String str2 = this.a[1];
                StringBuilder sb = new StringBuilder();
                sb.append(id);
                sb.append(", '^view");
                sb.append(this.b.TaskId);
                sb.append("st_vW");
                sb.append(id);
                sb.append("', '");
                sb.append(str);
                sb.append("', '");
                sb.append(main2.set("^onpagestarted" + this.b.TaskId + "st_bP" + id, bitmap));
                sb.append("'");
                aid_jsCode.TheCallbackString(str2, sb.toString());
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            if (this.a[3] != null) {
                int id = webView.getId();
                this.b.TheCallbackString(this.a[3], id + ", '^view" + this.b.TaskId + "st_vW" + id + "', '" + str2 + "', '" + str + "', " + i2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }
    }

    /* loaded from: classes.dex */
    class d extends WebChromeClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.d b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f2012c;

        d(b bVar, boolean z, com.iapp.app.d dVar, Activity activity) {
            this.a = z;
            this.b = dVar;
            this.f2012c = activity;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            if (this.a) {
                int id = webView.getId();
                this.b.d("onprogresschanged" + id, Integer.valueOf(id), webView, Integer.valueOf(i2));
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.f2012c.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class e extends WebViewClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.d b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f2013c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f2014d;
        final /* synthetic */ boolean e;
        final /* synthetic */ boolean f;

        e(b bVar, boolean z, com.iapp.app.d dVar, Activity activity, boolean z2, boolean z3, boolean z4) {
            this.a = z;
            this.b = dVar;
            this.f2013c = activity;
            this.f2014d = z2;
            this.e = z3;
            this.f = z4;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.e) {
                int id = webView.getId();
                this.b.d("onpagefinished" + id, Integer.valueOf(id), webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.f2014d) {
                int id = webView.getId();
                this.b.d("onpagestarted" + id, Integer.valueOf(id), webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            if (this.f) {
                int id = webView.getId();
                this.b.d("onreceivederror" + id, Integer.valueOf(id), webView, str2, str, Integer.valueOf(i2));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            if (!this.a) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.f2013c.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.f2013c, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            int id = webView.getId();
            return this.b.e("shouldoverrideurlloading" + id, Integer.valueOf(id), webView, str);
        }
    }

    /* loaded from: classes.dex */
    class f extends WebViewClient {
        final /* synthetic */ LuaObject[] a;

        f(b bVar, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.a[2] != null) {
                this.a[2].callNoErr(Integer.valueOf(webView.getId()), webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.a[1] != null) {
                this.a[1].callNoErr(Integer.valueOf(webView.getId()), webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            if (this.a[3] != null) {
                this.a[3].callNoErr(Integer.valueOf(webView.getId()), webView, str2, str, Integer.valueOf(i2));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            if (this.a[0] != null) {
                return this.a[0].callBoolNoErr(Integer.valueOf(webView.getId()), webView, str);
            }
            webView.loadUrl(str);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g extends WebChromeClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ main b;

        g(b bVar, boolean z, main mainVar) {
            this.a = z;
            this.b = mainVar;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            if (this.a) {
                int id = webView.getId();
                this.b.luaj.d("onprogresschanged" + id, Integer.valueOf(id), webView, Integer.valueOf(i2));
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.b.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h extends WebViewClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ main b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f2015c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f2016d;
        final /* synthetic */ boolean e;

        h(b bVar, boolean z, main mainVar, boolean z2, boolean z3, boolean z4) {
            this.a = z;
            this.b = mainVar;
            this.f2015c = z2;
            this.f2016d = z3;
            this.e = z4;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.f2016d) {
                int id = webView.getId();
                this.b.luaj.d("onpagefinished" + id, Integer.valueOf(id), webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.f2015c) {
                int id = webView.getId();
                this.b.luaj.d("onpagestarted" + id, Integer.valueOf(id), webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            if (this.e) {
                int id = webView.getId();
                this.b.luaj.d("onreceivederror" + id, Integer.valueOf(id), webView, str2, str, Integer.valueOf(i2));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            if (!this.a) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.b.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.b, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            int id = webView.getId();
            return this.b.luaj.e("shouldoverrideurlloading" + id, Integer.valueOf(id), webView, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i extends WebChromeClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ main2 b;

        i(b bVar, boolean z, main2 main2Var) {
            this.a = z;
            this.b = main2Var;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            if (this.a) {
                int id = webView.getId();
                this.b.callMethod("onprogresschanged" + id, id + ", '^view" + this.b.TaskId + "st_vW" + id + "', " + i2);
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.b.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j extends WebViewClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ main2 b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f2017c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f2018d;
        final /* synthetic */ boolean e;

        j(b bVar, boolean z, main2 main2Var, boolean z2, boolean z3, boolean z4) {
            this.a = z;
            this.b = main2Var;
            this.f2017c = z2;
            this.f2018d = z3;
            this.e = z4;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.f2018d) {
                int id = webView.getId();
                this.b.callMethod("onpagefinished" + id, id + ", '^view" + this.b.TaskId + "st_vW" + id + "', '" + str + "'");
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.f2017c) {
                int id = webView.getId();
                StringBuilder sb = new StringBuilder();
                sb.append(id);
                sb.append(", '^view");
                sb.append(this.b.TaskId);
                sb.append("st_vW");
                sb.append(id);
                sb.append("', '");
                sb.append(str);
                sb.append("', '");
                sb.append(main2.set("^onpagestarted" + this.b.TaskId + "st_bP" + id, bitmap));
                sb.append("'");
                this.b.callMethod("onpagestarted" + id, sb.toString());
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            if (this.e) {
                int id = webView.getId();
                this.b.callMethod("onreceivederror" + id, id + ", '^view" + this.b.TaskId + "st_vW" + id + "', '" + str2 + "', '" + str + "', " + i2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            if (!this.a) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.b.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.b, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            int id = webView.getId();
            this.b.callMethod("shouldoverrideurlloading" + id, id + ", '^view" + this.b.TaskId + "st_vW" + id + "', '" + str + "'");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k extends WebChromeClient {
        final /* synthetic */ Context a;
        final /* synthetic */ Activity b;

        k(b bVar, Context context, Activity activity) {
            this.a = context;
            this.b = activity;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onprogresschanged\">", "</eventItme>");
            if (c2 != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(this.a, this.b);
                tVar.T("st_vId", Integer.valueOf(webView.getId()));
                tVar.T("st_vW", webView);
                tVar.T("st_nS", Integer.valueOf(i2));
                tVar.f(c2);
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.b.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class l extends WebChromeClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ main3 b;

        l(b bVar, boolean z, main3 main3Var) {
            this.a = z;
            this.b = main3Var;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            if (this.a) {
                int id = webView.getId();
                this.b.javaj.b("onprogresschanged" + id, Integer.valueOf(id), webView, Integer.valueOf(i2));
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.b.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class m extends WebViewClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ main3 b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f2019c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f2020d;
        final /* synthetic */ boolean e;

        m(b bVar, boolean z, main3 main3Var, boolean z2, boolean z3, boolean z4) {
            this.a = z;
            this.b = main3Var;
            this.f2019c = z2;
            this.f2020d = z3;
            this.e = z4;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.f2020d) {
                int id = webView.getId();
                this.b.javaj.b("onpagefinished" + id, Integer.valueOf(id), webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.f2019c) {
                int id = webView.getId();
                this.b.javaj.b("onpagestarted" + id, Integer.valueOf(id), webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            if (this.e) {
                int id = webView.getId();
                this.b.javaj.b("onreceivederror" + id, Integer.valueOf(id), webView, str2, str, Integer.valueOf(i2));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            if (!this.a) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.b.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.b, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            int id = webView.getId();
            return this.b.javaj.c("shouldoverrideurlloading" + id, Integer.valueOf(id), webView, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class n extends WebChromeClient {
        final /* synthetic */ mian a;

        n(b bVar, mian mianVar) {
            this.a = mianVar;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onprogresschanged\">", "</eventItme>");
            if (c2 != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(this.a);
                tVar.T("st_vId", Integer.valueOf(webView.getId()));
                tVar.T("st_vW", webView);
                tVar.T("st_nS", Integer.valueOf(i2));
                tVar.f(c2);
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.a.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o extends WebViewClient {
        final /* synthetic */ mian a;

        o(b bVar, mian mianVar) {
            this.a = mianVar;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagefinished\">", "</eventItme>");
            if (c2 != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(this.a);
                tVar.T("st_vId", Integer.valueOf(webView.getId()));
                tVar.T("st_vW", webView);
                tVar.T("st_url", str);
                tVar.f(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagestarted\">", "</eventItme>");
            if (c2 != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(this.a);
                tVar.T("st_vId", Integer.valueOf(webView.getId()));
                tVar.T("st_vW", webView);
                tVar.T("st_url", str);
                tVar.T("st_bP", bitmap);
                tVar.f(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onreceivederror\">", "</eventItme>");
            if (c2 != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(this.a);
                tVar.T("st_vId", Integer.valueOf(webView.getId()));
                tVar.T("st_vW", webView);
                tVar.T("st_url", str2);
                tVar.T("st_dN", str);
                tVar.T("st_eE", Integer.valueOf(i2));
                tVar.f(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"shouldoverrideurlloading\">", "</eventItme>");
            if (c2 == null) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.a.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.a, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            c.b.a.a.t tVar = new c.b.a.a.t(this.a);
            tVar.T("st_vId", Integer.valueOf(webView.getId()));
            tVar.T("st_vW", webView);
            tVar.T("st_url", str);
            tVar.f(c2);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p extends WebChromeClient {
        final /* synthetic */ mian a;

        p(b bVar, mian mianVar) {
            this.a = mianVar;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onprogresschanged\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_nS", Integer.valueOf(i2));
                this.a.goUIEventset("onprogresschanged" + c2, aid_YuCodeX);
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q extends WebViewClient {
        final /* synthetic */ mian a;

        q(b bVar, mian mianVar) {
            this.a = mianVar;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagefinished\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str);
                this.a.goUIEventset("onpagefinished" + c2, aid_YuCodeX);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagestarted\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str);
                aid_YuCodeX.dim("st_bP", bitmap);
                this.a.goUIEventset("onpagestarted" + c2, aid_YuCodeX);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onreceivederror\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str2);
                aid_YuCodeX.dim("st_dN", str);
                aid_YuCodeX.dim("st_eE", Integer.valueOf(i2));
                this.a.goUIEventset("onreceivederror" + c2, aid_YuCodeX);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"shouldoverrideurlloading\">", "</eventItme>");
            if (c2 == null) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.a.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.a, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
            aid_YuCodeX.dim("st_vW", webView);
            aid_YuCodeX.dim("st_url", str);
            this.a.goUIEventset("shouldoverrideurlloading" + c2, aid_YuCodeX);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class r extends WebViewClient {
        final /* synthetic */ Context a;
        final /* synthetic */ Activity b;

        r(b bVar, Context context, Activity activity) {
            this.a = context;
            this.b = activity;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagefinished\">", "</eventItme>");
            if (c2 != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(this.a, this.b);
                tVar.T("st_vId", Integer.valueOf(webView.getId()));
                tVar.T("st_vW", webView);
                tVar.T("st_url", str);
                tVar.f(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagestarted\">", "</eventItme>");
            if (c2 != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(this.a, this.b);
                tVar.T("st_vId", Integer.valueOf(webView.getId()));
                tVar.T("st_vW", webView);
                tVar.T("st_url", str);
                tVar.T("st_bP", bitmap);
                tVar.f(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onreceivederror\">", "</eventItme>");
            if (c2 != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(this.a, this.b);
                tVar.T("st_vId", Integer.valueOf(webView.getId()));
                tVar.T("st_vW", webView);
                tVar.T("st_url", str2);
                tVar.T("st_dN", str);
                tVar.T("st_eE", Integer.valueOf(i2));
                tVar.f(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"shouldoverrideurlloading\">", "</eventItme>");
            if (c2 == null) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.a.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.a, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            c.b.a.a.t tVar = new c.b.a.a.t(this.a, this.b);
            tVar.T("st_vId", Integer.valueOf(webView.getId()));
            tVar.T("st_vW", webView);
            tVar.T("st_url", str);
            tVar.f(c2);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class s extends WebChromeClient {
        final /* synthetic */ Activity a;

        s(b bVar, Context context, Activity activity) {
            this.a = activity;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.a.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class t extends WebViewClient {
        final /* synthetic */ Context a;
        final /* synthetic */ Activity b;

        t(b bVar, Context context, Activity activity) {
            this.a = context;
            this.b = activity;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagefinished\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str);
                aid_YuCodeX.YuGoX(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagestarted\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str);
                aid_YuCodeX.dim("st_bP", bitmap);
                aid_YuCodeX.YuGoX(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onreceivederror\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str2);
                aid_YuCodeX.dim("st_dN", str);
                aid_YuCodeX.dim("st_eE", Integer.valueOf(i2));
                aid_YuCodeX.YuGoX(c2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"shouldoverrideurlloading\">", "</eventItme>");
            if (c2 == null) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.a.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.a, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
            aid_YuCodeX.dim("st_vW", webView);
            aid_YuCodeX.dim("st_url", str);
            aid_YuCodeX.YuGoX(c2);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class u extends WebChromeClient {
        final /* synthetic */ Context a;
        final /* synthetic */ Activity b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Aid_YuCodeX f2021c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f2022d;

        u(b bVar, Context context, Activity activity, Aid_YuCodeX aid_YuCodeX, String str) {
            this.a = context;
            this.b = activity;
            this.f2021c = aid_YuCodeX;
            this.f2022d = str;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onprogresschanged\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_nS", Integer.valueOf(i2));
                this.f2021c.goUIEventset(this.f2022d, "onprogresschanged" + c2, aid_YuCodeX);
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class v extends WebViewClient {
        final /* synthetic */ Context a;
        final /* synthetic */ Activity b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Aid_YuCodeX f2023c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f2024d;

        v(b bVar, Context context, Activity activity, Aid_YuCodeX aid_YuCodeX, String str) {
            this.a = context;
            this.b = activity;
            this.f2023c = aid_YuCodeX;
            this.f2024d = str;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagefinished\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str);
                this.f2023c.goUIEventset(this.f2024d, "onpagefinished" + c2, aid_YuCodeX);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onpagestarted\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str);
                aid_YuCodeX.dim("st_bP", bitmap);
                this.f2023c.goUIEventset(this.f2024d, "onpagestarted" + c2, aid_YuCodeX);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"onreceivederror\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
                aid_YuCodeX.dim("st_vW", webView);
                aid_YuCodeX.dim("st_url", str2);
                aid_YuCodeX.dim("st_dN", str);
                aid_YuCodeX.dim("st_eE", Integer.valueOf(i2));
                this.f2023c.goUIEventset(this.f2024d, "onreceivederror" + c2, aid_YuCodeX);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            String c2 = c.b.a.a.p.c(((Object[]) webView.getTag())[2].toString(), "<eventItme type=\"shouldoverrideurlloading\">", "</eventItme>");
            if (c2 == null) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.a.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.a, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this.a, this.b);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(webView.getId()));
            aid_YuCodeX.dim("st_vW", webView);
            aid_YuCodeX.dim("st_url", str);
            this.f2023c.goUIEventset(this.f2024d, "shouldoverrideurlloading" + c2, aid_YuCodeX);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class w extends WebViewClient {
        w() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class x extends WebChromeClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.j b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f2025c;

        x(b bVar, boolean z, com.iapp.app.j jVar, Activity activity) {
            this.a = z;
            this.b = jVar;
            this.f2025c = activity;
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(android.webkit.WebView webView, int i2) {
            if (this.a) {
                int id = webView.getId();
                this.b.b("onprogresschanged" + id, Integer.valueOf(id), webView, Integer.valueOf(i2));
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (b.b != null) {
                return true;
            }
            b.b = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            this.f2025c.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1101);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class y extends WebViewClient {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.j b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Activity f2026c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f2027d;
        final /* synthetic */ boolean e;
        final /* synthetic */ boolean f;

        y(b bVar, boolean z, com.iapp.app.j jVar, Activity activity, boolean z2, boolean z3, boolean z4) {
            this.a = z;
            this.b = jVar;
            this.f2026c = activity;
            this.f2027d = z2;
            this.e = z3;
            this.f = z4;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(android.webkit.WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.e) {
                int id = webView.getId();
                this.b.b("onpagefinished" + id, Integer.valueOf(id), webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(android.webkit.WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.f2027d) {
                int id = webView.getId();
                this.b.b("onpagestarted" + id, Integer.valueOf(id), webView, str, bitmap);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(android.webkit.WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            if (this.f) {
                int id = webView.getId();
                this.b.b("onreceivederror" + id, Integer.valueOf(id), webView, str2, str, Integer.valueOf(i2));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(android.webkit.WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(android.webkit.WebView webView, String str) {
            if (!this.a) {
                if (c.b.a.a.p.v(str.toLowerCase())) {
                    webView.loadUrl(str);
                } else {
                    try {
                        this.f2026c.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(this.f2026c, str)));
                    } catch (Exception unused) {
                    }
                }
                return true;
            }
            int id = webView.getId();
            return this.b.c("shouldoverrideurlloading" + id, Integer.valueOf(id), webView, str);
        }
    }

    public static void a(android.webkit.WebView webView) {
        webView.setWebViewClient(new w());
    }

    public static void c(android.webkit.WebView webView, Object obj) {
        webView.setWebViewClient((WebViewClient) obj);
    }

    public void b(android.webkit.WebView webView, String str, StringBuffer stringBuffer, com.iapp.app.j jVar, Activity activity, Aid_javaCode aid_javaCode) {
        webView.setWebChromeClient(new x(this, aid_javaCode.addViewEventItme(stringBuffer, str, webView.getId(), "onprogresschanged", "int st_vId,android.webkit.WebView st_vW,int st_nS"), jVar, activity));
        webView.setWebViewClient(new y(this, aid_javaCode.addViewEventItme(stringBuffer, str, webView.getId(), "shouldoverrideurlloading", "int st_vId,android.webkit.WebView st_vW,String st_url"), jVar, activity, aid_javaCode.addViewEventItme(stringBuffer, str, webView.getId(), "onpagestarted", "int st_vId,android.webkit.WebView st_vW,String st_url,android.graphics.Bitmap st_bP"), aid_javaCode.addViewEventItme(stringBuffer, str, webView.getId(), "onpagefinished", "int st_vId,android.webkit.WebView st_vW,String st_url"), aid_javaCode.addViewEventItme(stringBuffer, str, webView.getId(), "onreceivederror", "int st_vId,android.webkit.WebView st_vW,String st_url,String st_dN,int st_eE")));
    }

    public void d(android.webkit.WebView webView, String str, StringBuffer stringBuffer, Activity activity, Aid_jsCode aid_jsCode) {
        webView.setWebChromeClient(new a(this, aid_jsCode.addViewEventItme(stringBuffer, str, webView.getId(), "onprogresschanged", "st_vId,st_vW,st_nS"), aid_jsCode, activity));
        webView.setWebViewClient(new C0068b(this, aid_jsCode.addViewEventItme(stringBuffer, str, webView.getId(), "shouldoverrideurlloading", "st_vId,st_vW,st_url"), aid_jsCode, activity, aid_jsCode.addViewEventItme(stringBuffer, str, webView.getId(), "onpagestarted", "st_vId,st_vW,st_url,st_bP"), aid_jsCode.addViewEventItme(stringBuffer, str, webView.getId(), "onpagefinished", "st_vId,st_vW,st_url"), aid_jsCode.addViewEventItme(stringBuffer, str, webView.getId(), "onreceivederror", "st_vId,st_vW,st_url,st_dN,st_eE")));
    }

    public void e(View view, String[] strArr, Aid_jsCode aid_jsCode) {
        ((android.webkit.WebView) view).setWebViewClient(new c(this, strArr, aid_jsCode));
    }

    public void f(android.webkit.WebView webView, String str, StringBuffer stringBuffer, Activity activity, com.iapp.app.d dVar, Aid_luaCode aid_luaCode) {
        webView.setWebChromeClient(new d(this, aid_luaCode.addViewEventItme(stringBuffer, str, webView.getId(), "onprogresschanged", "st_vId,st_vW,st_nS"), dVar, activity));
        webView.setWebViewClient(new e(this, aid_luaCode.addViewEventItme(stringBuffer, str, webView.getId(), "shouldoverrideurlloading", "st_vId,st_vW,st_url"), dVar, activity, aid_luaCode.addViewEventItme(stringBuffer, str, webView.getId(), "onpagestarted", "st_vId,st_vW,st_url,st_bP"), aid_luaCode.addViewEventItme(stringBuffer, str, webView.getId(), "onpagefinished", "st_vId,st_vW,st_url"), aid_luaCode.addViewEventItme(stringBuffer, str, webView.getId(), "onreceivederror", "st_vId,st_vW,st_url,st_dN,st_eE")));
    }

    public void g(View view, LuaObject[] luaObjectArr) {
        ((android.webkit.WebView) view).setWebViewClient(new f(this, luaObjectArr));
    }

    public void h(android.webkit.WebView webView, String str, main mainVar) {
        webView.setWebChromeClient(new g(this, mainVar.addViewEventItme(str, webView.getId(), "onprogresschanged", "st_vId,st_vW,st_nS"), mainVar));
        webView.setWebViewClient(new h(this, mainVar.addViewEventItme(str, webView.getId(), "shouldoverrideurlloading", "st_vId,st_vW,st_url"), mainVar, mainVar.addViewEventItme(str, webView.getId(), "onpagestarted", "st_vId,st_vW,st_url,st_bP"), mainVar.addViewEventItme(str, webView.getId(), "onpagefinished", "st_vId,st_vW,st_url"), mainVar.addViewEventItme(str, webView.getId(), "onreceivederror", "st_vId,st_vW,st_url,st_dN,st_eE")));
    }

    public void i(android.webkit.WebView webView, String str, main2 main2Var) {
        webView.setWebChromeClient(new i(this, main2Var.addViewEventItme(str, webView.getId(), "onprogresschanged", "st_vId,st_vW,st_nS"), main2Var));
        webView.setWebViewClient(new j(this, main2Var.addViewEventItme(str, webView.getId(), "shouldoverrideurlloading", "st_vId,st_vW,st_url"), main2Var, main2Var.addViewEventItme(str, webView.getId(), "onpagestarted", "st_vId,st_vW,st_url,st_bP"), main2Var.addViewEventItme(str, webView.getId(), "onpagefinished", "st_vId,st_vW,st_url"), main2Var.addViewEventItme(str, webView.getId(), "onreceivederror", "st_vId,st_vW,st_url,st_dN,st_eE")));
    }

    public void j(android.webkit.WebView webView, String str, main3 main3Var) {
        webView.setWebChromeClient(new l(this, main3Var.addViewEventItme(str, webView.getId(), "onprogresschanged", "int st_vId,android.webkit.WebView st_vW,int st_nS"), main3Var));
        webView.setWebViewClient(new m(this, main3Var.addViewEventItme(str, webView.getId(), "shouldoverrideurlloading", "int st_vId,android.webkit.WebView st_vW,String st_url"), main3Var, main3Var.addViewEventItme(str, webView.getId(), "onpagestarted", "int st_vId,android.webkit.WebView st_vW,String st_url,android.graphics.Bitmap st_bP"), main3Var.addViewEventItme(str, webView.getId(), "onpagefinished", "int st_vId,android.webkit.WebView st_vW,String st_url"), main3Var.addViewEventItme(str, webView.getId(), "onreceivederror", "int st_vId,android.webkit.WebView st_vW,String st_url,String st_dN,int st_eE")));
    }

    public void k(android.webkit.WebView webView, String str, mian mianVar) {
        webView.setWebChromeClient(new n(this, mianVar));
        webView.setWebViewClient(new o(this, mianVar));
    }

    public void l(android.webkit.WebView webView, String str, String str2, mian mianVar) {
        webView.setWebChromeClient(new p(this, mianVar));
        webView.setWebViewClient(new q(this, mianVar));
    }

    public void m(android.webkit.WebView webView, Context context, Activity activity) {
        webView.setWebChromeClient(new k(this, context, activity));
        webView.setWebViewClient(new r(this, context, activity));
    }

    public void n(android.webkit.WebView webView, Context context, Activity activity) {
        webView.setWebChromeClient(new s(this, context, activity));
        webView.setWebViewClient(new t(this, context, activity));
    }

    public void o(android.webkit.WebView webView, Context context, Activity activity, Aid_YuCodeX aid_YuCodeX, String str) {
        webView.setWebChromeClient(new u(this, context, activity, aid_YuCodeX, str));
        webView.setWebViewClient(new v(this, context, activity, aid_YuCodeX, str));
    }
}
