package com.iapp.app;

import android.content.Context;
import android.os.Build;

/* loaded from: classes.dex */
public class a {
    private static String a;
    public static c.b.a.a.w.b b;

    /* renamed from: c, reason: collision with root package name */
    public static String f1931c;

    static {
        String str = Build.MODEL;
    }

    public static String a(Context context) {
        if (a == null) {
            a = context.getFilesDir().getPath() + '/';
        }
        return a;
    }
}
