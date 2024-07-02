package com.iapp.app.x5;

import android.app.Application;
import android.os.Build;
import c.b.a.a.v;

/* loaded from: classes.dex */
public class APPAplication extends Application {
    public static boolean isSsl = true;

    @Override // android.app.Application
    public void onCreate() {
        if (Build.VERSION.SDK_INT < 21) {
            new v().m(this);
        }
        super.onCreate();
    }
}
