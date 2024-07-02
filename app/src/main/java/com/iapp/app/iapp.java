package com.iapp.app;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import com.iapp.app.run.mian;

/* loaded from: classes.dex */
public class iapp {
    private c.b.a.a.t ayc;
    private Aid_YuCodeX aycX;

    public iapp(Context context, Activity activity) {
        if (mian.sh) {
            this.ayc = new c.b.a.a.t(context, activity);
        } else {
            this.aycX = new Aid_YuCodeX(context, activity);
        }
    }

    @JavascriptInterface
    public void fn(String str) {
        if (mian.sh) {
            this.ayc.w0(str);
        } else {
            this.aycX.function2(str);
        }
    }

    @JavascriptInterface
    public String fn2(String str, String str2) {
        if (mian.sh) {
            this.ayc.w0(str);
            Object X = this.ayc.X(str2);
            if (X == null) {
                return null;
            }
            return X.toString();
        }
        this.aycX.function2(str);
        Object dimget2 = this.aycX.dimget2(str2);
        if (dimget2 == null) {
            return null;
        }
        return dimget2.toString();
    }

    @JavascriptInterface
    public String g(String str) {
        if (mian.sh) {
            Object X = this.ayc.X(str);
            if (X == null) {
                return null;
            }
            return X.toString();
        }
        Object dimget2 = this.aycX.dimget2(str);
        if (dimget2 == null) {
            return null;
        }
        return dimget2.toString();
    }

    @JavascriptInterface
    public void s(String str, String str2) {
        if (mian.sh) {
            this.ayc.T(str, str2);
        } else {
            this.aycX.dim(str, str2);
        }
    }
}
