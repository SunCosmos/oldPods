package cn.hugo.android.scanner.g;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

/* loaded from: classes.dex */
public class b {
    @SuppressLint({"NewApi"})
    public static void a(AsyncTask<?, ?, ?> asyncTask) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        } else {
            asyncTask.execute(new Object[0]);
        }
    }
}
