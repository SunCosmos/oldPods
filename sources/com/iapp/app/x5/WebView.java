package com.iapp.app.x5;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.SslErrorHandler;

/* loaded from: classes.dex */
public class WebView extends android.webkit.WebView {
    public WebView(Context context) {
        super(context);
    }

    public WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void a(Context context, SslErrorHandler sslErrorHandler, String str) {
        if (APPAplication.isSsl) {
            sslErrorHandler.proceed();
        } else {
            sslErrorHandler.cancel();
        }
    }

    public static void b(Context context) {
    }
}
