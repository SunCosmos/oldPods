package com.iapp.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.oldpods.app.R;
import java.io.File;
import java.io.FileNotFoundException;

/* loaded from: classes.dex */
public class Webview extends AppCompatActivity {
    private WebView b;
    private ImageView e;
    private Toolbar f;
    private String g;
    private String a = null;

    /* renamed from: c, reason: collision with root package name */
    private String f1928c = null;

    /* renamed from: d, reason: collision with root package name */
    private RotateAnimation f1929d = null;
    private ValueCallback<Uri> h = null;

    /* renamed from: i, reason: collision with root package name */
    private ValueCallback<Uri[]> f1930i = null;
    public String urlXX = null;
    public String userAgentXX = null;
    public String contentDispositionXX = null;
    public String mimetypeXX = null;
    public String uid = null;
    public long contentLengthXX = 0;
    public int chongzhi_i = 0;
    public int chongzhi_q = 0;
    public String chongzhi_type = null;
    public String chongzhi_id = null;
    private Handler j = new f();

    /* loaded from: classes.dex */
    public class a implements DownloadListener {
        public a() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            String lowerCase = str4.toLowerCase();
            String c2 = str3 != null ? c.b.a.a.p.c(str3, "filename=\"", "\"") : null;
            if (c2 == null) {
                if (str.contains("?")) {
                    str = str.substring(0, str.indexOf(63));
                }
                c2 = c.b.a.a.p.b(str);
                String lowerCase2 = c2.toLowerCase();
                if (lowerCase.equals("application/vnd.android.package-archive") && !lowerCase2.endsWith(".apk")) {
                    c2 = c2 + ".apk";
                }
            }
            if (c2 != null) {
                Toast.makeText(Webview.this, "开始下载 " + c2, 1).show();
                com.iapp.app.a.b.d(str, c2, null);
            }
            if (Webview.this.f1928c == null) {
                Webview.this.finish();
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Webview.this.b.canGoBack()) {
                Webview.this.b.goBack();
            } else {
                Webview.this.finish();
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends WebChromeClient {
        c() {
        }

        @Override // android.webkit.WebChromeClient
        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j2 * 2);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i2) {
            String title = webView.getTitle();
            if (i2 == 100) {
                Webview.this.f1928c = title;
                Webview.this.f.setTitle(title);
                Webview.this.e.clearAnimation();
                Webview.this.e.setVisibility(8);
                return;
            }
            if (title != null) {
                if (title.length() > 16) {
                    title = title.substring(0, 15);
                }
                Webview.this.f.setTitle("[" + i2 + "%] " + title + "..");
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(j * 2);
        }

        @Override // android.webkit.WebChromeClient
        @SuppressLint({"NewApi"})
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (Webview.this.f1930i != null) {
                return true;
            }
            Webview.this.f1930i = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            Webview.this.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class d extends WebViewClient {
        d() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            com.iapp.app.x5.WebView.a(webView.getContext(), sslErrorHandler, sslError.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String lowerCase = str.toLowerCase();
            if (str.endsWith("DYBJLLQ")) {
                Webview.this.m(str);
                return true;
            }
            if (c.b.a.a.p.v(lowerCase)) {
                Webview.this.loadurl(webView, str);
            } else if (lowerCase.startsWith("iappcopy://")) {
                c.b.a.a.p.k(str.substring(11), Webview.this);
                Toast.makeText(Webview.this, "已复制", 1).show();
            } else if (lowerCase.startsWith("iappoay://iapp.yx93.com:")) {
                Webview.this.n("http://" + str.substring(10));
            } else if (lowerCase.startsWith("iappopenapp://")) {
                c.b.a.a.d.s(Webview.this, str.substring(14));
            } else {
                try {
                    Webview.this.startActivity(new Intent("android.intent.action.VIEW", c.b.a.a.k.c(Webview.this, str)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends Thread {
        final /* synthetic */ String a;

        e(String str) {
            this.a = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String str = c.b.a.a.d.m(Webview.this) + "/img/" + c.b.a.a.p.b(this.a);
            try {
                if (c.b.a.a.g.a(this.a, str, true) != -1) {
                    Webview.this.g = str;
                    Webview.this.a("已保存至:" + str);
                    Webview.saveImageToGallery(Webview.this, new File(str));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    class f extends Handler {
        f() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ImageView imageView;
            int i2;
            int i3 = message.what;
            if (i3 == 1) {
                Toast.makeText(Webview.this, message.obj.toString(), 1).show();
                return;
            }
            if (i3 == 2) {
                imageView = Webview.this.e;
                i2 = 8;
            } else {
                if (i3 != 3) {
                    return;
                }
                imageView = Webview.this.e;
                i2 = 0;
            }
            imageView.setVisibility(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Message message = new Message();
        message.what = 1;
        message.obj = str;
        this.j.sendMessage(message);
    }

    public static String getFilePathByContentResolver(Context context, Uri uri) {
        String str = null;
        if (uri == null) {
            return null;
        }
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        if (query == null) {
            throw new IllegalArgumentException("Query on " + uri + " returns null result.");
        }
        try {
            if (query.getCount() == 1 && query.moveToFirst()) {
                str = query.getString(query.getColumnIndexOrThrow("_data"));
            }
            return str;
        } finally {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        if (str.toLowerCase().startsWith("file://")) {
            c.b.a.a.k.a(this, intent, new File(str), "text/html");
        } else {
            intent.setData(c.b.a.a.k.c(this, str));
        }
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(String str) {
        new e(str).start();
    }

    @TargetApi(11)
    private void o(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    public static void saveImageToGallery(Context context, File file) {
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), (String) null);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(c.b.a.a.k.b(context, file));
        context.sendBroadcast(intent);
    }

    public void loadurl(WebView webView, String str) {
        this.e.startAnimation(this.f1929d);
        this.e.setVisibility(0);
        webView.loadUrl(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @SuppressLint({"NewApi"})
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        Uri data = (intent == null || i3 != -1) ? null : intent.getData();
        try {
            ValueCallback<Uri> valueCallback = this.h;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(data);
            } else {
                ValueCallback<Uri[]> valueCallback2 = this.f1930i;
                if (valueCallback2 != null) {
                    valueCallback2.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(i3, intent));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.h = null;
        this.f1930i = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        this.a = extras.getString("WebURL");
        setContentView(R.layout.ui_webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ui_webview_top);
        this.f = toolbar;
        setSupportActionBar(toolbar);
        String string = extras.getString("background");
        String string2 = extras.getString("backgroundShadow");
        if (string != null && string2 != null) {
            this.f.setBackgroundColor(Color.parseColor(string));
            c.b.a.a.r.c(this, Color.parseColor(string2), true, findViewById(R.id.ui_web_view));
        }
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        this.f.setNavigationOnClickListener(new b());
        this.e = (ImageView) findViewById(R.id.ui_webview_imageView1);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 350.0f, 1, 0.5f, 1, 0.5f);
        this.f1929d = rotateAnimation;
        rotateAnimation.setDuration(1000L);
        this.f1929d.setRepeatCount(100);
        this.e.startAnimation(this.f1929d);
        WebView webView = (WebView) findViewById(R.id.ui_web_wv);
        this.b = webView;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            webView.getSettings().setMixedContentMode(0);
        }
        this.b.getSettings().setAllowFileAccess(true);
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.getSettings().setAppCacheEnabled(true);
        this.b.getSettings().setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
        this.b.getSettings().setAppCacheMaxSize(8388608L);
        this.b.getSettings().setDatabaseEnabled(true);
        this.b.getSettings().setDatabasePath(getApplicationContext().getDir("database", 0).getPath());
        this.b.getSettings().setDomStorageEnabled(true);
        this.b.getSettings().setGeolocationEnabled(true);
        this.b.getSettings().setLightTouchEnabled(true);
        this.b.getSettings().setCacheMode(-1);
        this.b.getSettings().setPluginState(WebSettings.PluginState.ON);
        this.b.getSettings().setSupportZoom(true);
        this.b.getSettings().setBuiltInZoomControls(true);
        this.b.getSettings().setUseWideViewPort(true);
        this.b.getSettings().setLoadWithOverviewMode(true);
        if (i2 >= 16) {
            this.b.getSettings().setAllowUniversalAccessFromFileURLs(true);
            this.b.getSettings().setAllowFileAccessFromFileURLs(true);
        }
        this.b.setScrollBarStyle(0);
        this.b.loadUrl(this.a);
        this.b.setDownloadListener(new a());
        this.b.setWebChromeClient(new c());
        this.b.setWebViewClient(new d());
        o(this.b);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "后退");
        menu.add(0, 1, 1, "前进");
        menu.add(0, 2, 2, "刷新");
        menu.add(0, 3, 3, "下载");
        menu.add(0, 4, 4, "默认浏览器打开");
        menu.add(0, 5, 5, "关闭");
        return super.onCreateOptionsMenu(menu);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f1929d.cancel();
        this.f1929d = null;
        this.e.clearAnimation();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return i2 == 82;
        }
        if (keyEvent.getRepeatCount() == 0) {
            if (this.b.canGoBack()) {
                this.b.goBack();
            } else {
                finish();
            }
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 0) {
            this.b.goBack();
        } else if (itemId == 1) {
            this.b.goForward();
        } else if (itemId == 2) {
            WebView webView = this.b;
            loadurl(webView, webView.getUrl());
        } else if (itemId == 3) {
            Bundle extras = getIntent().getExtras();
            String string = extras.getString("background");
            String string2 = extras.getString("backgroundShadow");
            if (string == null || string2 == null) {
                startActivity(new Intent().setClass(this, DownList.class));
            } else {
                Intent intent = new Intent(this, (Class<?>) DownList.class);
                Bundle bundle = new Bundle();
                bundle.putString("background", string);
                bundle.putString("backgroundShadow", string2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else if (itemId == 4) {
            m(this.b.getUrl());
        } else if (itemId == 5) {
            finish();
        }
        return true;
    }

    public void synCookies(Context context, String str, String str2) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        cookieManager.setCookie(str, str2);
        CookieSyncManager.getInstance().sync();
    }
}
