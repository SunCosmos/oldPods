package com.iapp.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.io.File;
import java.util.Date;

/* loaded from: classes.dex */
public class e {
    private static String a;

    public static String a(String str, String str2) {
        return c.b.a.a.p.j(str, str2);
    }

    public static String aa() {
        return ab().substring(6);
    }

    public static String ab() {
        return c.b.a.a.p.d(String.valueOf(ac()));
    }

    public static int ac() {
        return 0;
    }

    public static String ad(byte[] bArr) {
        return c.b.a.a.p.e(bArr);
    }

    public static boolean ae(Context context) {
        return c.b.a.a.p.z(context);
    }

    public static String af(Context context) {
        if (a == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(c.b.a.a.d.h(context.getFilesDir().getPath() + "/config/userencryption.xml"));
            sb.append("");
            a = c.b.a.a.p.c(sb.toString(), "<s>", "</s>");
        }
        return a;
    }

    public static String ag(byte[] bArr) {
        return c.b.a.a.p.f(bArr);
    }

    public static void ah(Object[] objArr, String str) {
        if (objArr[0] instanceof c.b.a.a.t) {
            ((c.b.a.a.t) objArr[0]).K1(str, (ViewGroup) objArr[1], Integer.parseInt(objArr[2].toString()), objArr[3], (c.c.a.b) objArr[4]);
        }
        if (objArr[0] instanceof Aid_YuCodeX) {
            ((Aid_YuCodeX) objArr[0]).openRestoreinterfaceX(str, (ViewGroup) objArr[1], Integer.parseInt(objArr[2].toString()), objArr[3], (c.c.a.b) objArr[4], objArr[5].toString());
            return;
        }
        if (objArr[0] instanceof Aid_luaCode) {
            ((Aid_luaCode) objArr[0]).openRestoreinterfaceX(str, (ViewGroup) objArr[1], Integer.parseInt(objArr[2].toString()), objArr[3], (c.c.a.b) objArr[4], (StringBuffer) objArr[5], (d) objArr[6]);
        } else if (objArr[0] instanceof Aid_javaCode) {
            ((Aid_javaCode) objArr[0]).openRestoreinterfaceX(str, (ViewGroup) objArr[1], Integer.parseInt(objArr[2].toString()), objArr[3], (c.c.a.b) objArr[4], (StringBuffer) objArr[5], (j) objArr[6]);
        } else if (objArr[0] instanceof Aid_jsCode) {
            ((Aid_jsCode) objArr[0]).openRestoreinterfaceX(str, (ViewGroup) objArr[1], Integer.parseInt(objArr[2].toString()), objArr[3], (c.c.a.b) objArr[4], (StringBuffer) objArr[5], (WebView) objArr[6]);
        }
    }

    public static void ai(Object obj) {
        c.b.a.a.f.A(null, obj);
    }

    public static String b(String str) {
        return c.b.a.a.d.h(str);
    }

    public static String c(String str, String str2) {
        return c.b.a.a.p.h(str, str2);
    }

    public static String d(String str) {
        return c.b.a.a.p.d(str);
    }

    public static int e(int i2, int i3) {
        return c.b.a.a.p.g(i2, i3);
    }

    public static long f() {
        return new Date().getTime();
    }

    public static String[] g(String str) {
        return new File(str).list();
    }

    public static byte[] h(String str) {
        return c.b.a.a.d.x(str);
    }

    public static void i(String str, byte[] bArr) {
        c.b.a.a.d.j(str, bArr);
    }

    public static byte j(byte[] bArr) {
        return c.b.a.a.p.t(bArr);
    }

    public static byte[] k(byte[] bArr, byte[] bArr2) {
        return c.b.a.a.a.c(bArr, bArr2);
    }

    public static byte[] m(byte[] bArr, int i2, int i3) {
        return c.b.a.a.a.a(bArr, i2, i3);
    }

    public static int n(byte[] bArr, byte[] bArr2, int i2) {
        return c.b.a.a.a.d(bArr, bArr2, i2);
    }

    public static String o(String str) {
        return str;
    }

    public static void p(String str, String str2) {
        c.b.a.a.p.n(str, str2.replace("&lt;", "<").replace("&gt;", ">"));
    }

    public static byte[] q(byte[] bArr, byte[] bArr2) {
        return c.b.a.a.a.c(bArr, bArr2);
    }

    public static int r(String str, int i2) {
        return c.b.a.a.p.i(str, i2);
    }

    public static String s(String str, String str2, String str3) {
        return c.b.a.a.p.c(str, str2, str3);
    }

    public static byte[] s(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return c.b.a.a.a.b(bArr, bArr2, bArr3, 0);
    }

    public static View t(int i2, String str) {
        return null;
    }

    public static View t(int i2, String str, String str2) {
        return null;
    }

    public static View u(int i2, View view) {
        return c.b.a.a.p.m(i2, view);
    }

    public static void v(View view, View view2) {
        c.b.a.a.p.y(view, view2);
    }

    public static boolean w(View view, int i2, String str) {
        return true;
    }

    public static boolean w(View view, String str) {
        return true;
    }

    public static void x(View view, String str, String str2, String str3) {
    }

    public static void x(View view, String str, String str2, String str3, Object obj) {
    }

    public static void y(View view, View view2) {
    }

    public static void z(View view, String str) {
    }
}
