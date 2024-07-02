package com.iapp.app.x5;

import android.os.Build;
import android.webkit.WebSettings;

/* loaded from: classes.dex */
public class c {
    public static void a(android.webkit.WebView webView) {
        webView.getSettings().setCacheMode(-1);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode(0);
        }
    }
}
