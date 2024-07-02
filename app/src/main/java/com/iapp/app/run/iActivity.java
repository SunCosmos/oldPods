package com.iapp.app.run;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import c.b.a.a.d;
import c.b.a.a.k;
import c.b.a.a.w.b;
import com.iapp.app.a;
import com.iapp.app.x5.WebView;

/* loaded from: classes.dex */
public class iActivity extends AppCompatActivity {
    private static int a = 0;
    protected static boolean b = false;

    private void a() {
        WebView.b(getApplicationContext());
    }

    private void b(Activity activity) {
        Bundle bundle;
        int i2 = a;
        if (i2 > 2) {
            d();
            return;
        }
        a = i2 + 1;
        if (Build.VERSION.SDK_INT < 23) {
            d();
            return;
        }
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                String string = bundle.getString("i_app_autoPermission");
                if (!"shi".equals(string) && "fou".equals(string)) {
                    d();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            k.d(activity, activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096).requestedPermissions);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        d();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0021, code lost:
    
        c.b.a.a.t.o = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c() {
        /*
            r6 = this;
            boolean r0 = com.iapp.app.run.iActivity.b
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            com.iapp.app.run.iActivity.b = r0
            android.content.res.AssetManager r0 = r6.getAssets()     // Catch: java.lang.Exception -> L27
            java.lang.String r1 = ""
            java.lang.String[] r0 = r0.list(r1)     // Catch: java.lang.Exception -> L27
            int r1 = r0.length     // Catch: java.lang.Exception -> L27
            r2 = 0
            r3 = 0
        L15:
            if (r3 >= r1) goto L2b
            r4 = r0[r3]     // Catch: java.lang.Exception -> L27
            java.lang.String r5 = "yuv0.xml"
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> L27
            if (r4 == 0) goto L24
            c.b.a.a.t.o = r2     // Catch: java.lang.Exception -> L27
            goto L2b
        L24:
            int r3 = r3 + 1
            goto L15
        L27:
            r0 = move-exception
            r0.printStackTrace()
        L2b:
            java.lang.String r0 = "userencryption.xml"
            java.lang.String r0 = c.b.a.a.d.f(r6, r0)
            java.lang.String r1 = "/config/userencryption.xml"
            if (r0 == 0) goto L80
            java.lang.String r2 = "<ok>true</ok>"
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L80
            java.lang.String r0 = com.iapp.app.e.af(r6)
            if (r0 == 0) goto L6c
            boolean r0 = com.iapp.app.b.h2(r6, r0)
            if (r0 == 0) goto L4d
            r6.a()
            return
        L4d:
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.File r3 = r6.getFilesDir()
            java.lang.String r3 = r3.getPath()
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            r0.delete()
        L6c:
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.iapp.app.logoActivity> r1 = com.iapp.app.logoActivity.class
            r0.<init>(r6, r1)
            r1 = 67108864(0x4000000, float:1.5046328E-36)
            android.content.Intent r0 = r0.setFlags(r1)
            r6.startActivity(r0)
            r6.finish()
            goto La2
        L80:
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.File r3 = r6.getFilesDir()
            java.lang.String r3 = r3.getPath()
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            r0.delete()
            r6.a()
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.run.iActivity.c():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        if (a.b == null) {
            a.b = new b(this, d.m(this) + "/iApp/DownloadFileDir/TempDefaultDownFile", d.m(this) + "/iApp/DownloadFileDir/DefaultDownFile", 3, 3, 2, 12000, true);
        }
        if (a.f1931c == null) {
            a.f1931c = getPackageName();
        }
        if (c.b.a.a.b.a == null) {
            c.b.a.a.b.l();
        }
        c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        d.r(this);
    }
}
