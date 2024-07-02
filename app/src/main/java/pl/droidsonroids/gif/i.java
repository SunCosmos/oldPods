package pl.droidsonroids.gif;

import android.annotation.SuppressLint;
import android.content.Context;

/* loaded from: classes.dex */
public class i {

    @SuppressLint({"StaticFieldLeak"})
    private static Context a;

    private static Context a() {
        if (a == null) {
            try {
                a = (Context) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e) {
                throw new IllegalStateException("LibraryLoader not initialized. Call LibraryLoader.initialize() before using library classes.", e);
            }
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b() {
        try {
            System.loadLibrary("pl_droidsonroids_gif");
        } catch (UnsatisfiedLinkError unused) {
            l.h(a());
        }
    }
}
