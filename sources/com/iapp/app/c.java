package com.iapp.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* loaded from: classes.dex */
public class c {

    /* loaded from: classes.dex */
    public class a extends WebChromeClient {
        final /* synthetic */ Activity a;

        a(Activity activity) {
            this.a = activity;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(this.a, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* loaded from: classes.dex */
    public class b extends WebChromeClient {
        final /* synthetic */ Context a;

        b(Context context) {
            this.a = context;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(this.a, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    @SuppressLint({"NewApi"})
    public static WebView a(Activity activity, int i2) {
        WebView webView = new WebView(activity);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(activity.getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(activity.getApplicationContext().getDir("database", 0).getPath());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setLightTouchEnabled(true);
        webView.getSettings().setCacheMode(-1);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebChromeClient(new a(activity));
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
        webView.addJavascriptInterface(new Aid_jsCode(activity, webView, i2), "I");
        return webView;
    }

    @SuppressLint({"NewApi"})
    public static WebView b(Context context, Activity activity, int i2) {
        WebView webView = new WebView(context);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(context.getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(context.getApplicationContext().getDir("database", 0).getPath());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setLightTouchEnabled(true);
        webView.getSettings().setCacheMode(-1);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebChromeClient(new b(context));
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
        webView.addJavascriptInterface(new Aid_jsCode(context, activity, webView, i2), "I");
        return webView;
    }

    public static void c(Context context, WebView webView, String str) {
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><head><script type='text/javascript'>");
        stringBuffer.append(c.b.a.a.f.e(context, "import.mjs"));
        stringBuffer.append('\n');
        stringBuffer.append(str.replace("&lt;", "<").replace("&gt;", ">"));
        stringBuffer.append("</script></head></html>");
        webView.loadDataWithBaseURL(null, stringBuffer.toString(), "text/html", "utf-8", null);
    }

    public static void d(WebView webView, String str) {
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
    }
}
