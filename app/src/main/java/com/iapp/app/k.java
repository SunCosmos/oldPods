package com.iapp.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class k {
    private Context a;
    private Resources b;

    /* renamed from: c, reason: collision with root package name */
    private AssetManager f1938c;

    /* renamed from: d, reason: collision with root package name */
    private String f1939d;

    public k(Context context) {
        this.a = null;
        this.b = null;
        this.f1938c = null;
        this.f1939d = null;
        this.a = context;
        this.f1938c = context.getAssets();
        this.b = context.getResources();
    }

    public k(Context context, String str) {
        this.a = null;
        this.b = null;
        this.f1938c = null;
        this.f1939d = null;
        this.a = context;
        try {
            this.f1938c = (AssetManager) AssetManager.class.newInstance();
            AssetManager.class.getMethod("addAssetPath", String.class).invoke(this.f1938c, str);
        } catch (Exception unused) {
        }
        this.b = new Resources(this.f1938c, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
        if (packageArchiveInfo != null) {
            this.f1939d = packageArchiveInfo.packageName;
        }
    }

    public AssetManager a() {
        return this.f1938c;
    }

    public int b(String str, String str2) {
        return this.b.getIdentifier(str, str2, this.f1939d);
    }

    public Resources c() {
        return this.b;
    }

    public Object d(String str, String str2) {
        int identifier = this.b.getIdentifier(str, str2, this.f1939d);
        if (str2.equals("drawable")) {
            return this.b.getDrawable(identifier);
        }
        if (str2.equals("string")) {
            return this.b.getString(identifier);
        }
        if (str2.equals("color")) {
            return Integer.valueOf(this.b.getColor(identifier));
        }
        if (str2.equals("stringarray")) {
            return this.b.getStringArray(identifier);
        }
        if (str2.equals("layout")) {
            return LayoutInflater.from(this.a).inflate(this.b.getLayout(identifier), (ViewGroup) null);
        }
        return null;
    }
}
