package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class ProcessCompat {

    @RequiresApi(16)
    /* loaded from: classes.dex */
    static class a {
        private static final Object a = new Object();
        private static Method b;

        /* renamed from: c, reason: collision with root package name */
        private static boolean f566c;

        @SuppressLint({"PrivateApi"})
        static boolean a(int i2) {
            try {
                synchronized (a) {
                    if (!f566c) {
                        f566c = true;
                        b = Class.forName("android.os.UserId").getDeclaredMethod("isApp", Integer.TYPE);
                    }
                }
                Method method = b;
                if (method != null) {
                    Boolean bool = (Boolean) method.invoke(null, Integer.valueOf(i2));
                    if (bool != null) {
                        return bool.booleanValue();
                    }
                    throw new NullPointerException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    @RequiresApi(17)
    /* loaded from: classes.dex */
    static class b {
        private static final Object a = new Object();
        private static Method b;

        /* renamed from: c, reason: collision with root package name */
        private static boolean f567c;

        @SuppressLint({"DiscouragedPrivateApi"})
        static boolean a(int i2) {
            try {
                synchronized (a) {
                    if (!f567c) {
                        f567c = true;
                        b = UserHandle.class.getDeclaredMethod("isApp", Integer.TYPE);
                    }
                }
                Method method = b;
                if (method != null && ((Boolean) method.invoke(null, Integer.valueOf(i2))) == null) {
                    throw new NullPointerException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    static class c {
        static boolean a(int i2) {
            return Process.isApplicationUid(i2);
        }
    }

    private ProcessCompat() {
    }

    public static boolean isApplicationUid(int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 24) {
            return c.a(i2);
        }
        if (i3 >= 17) {
            return b.a(i2);
        }
        if (i3 == 16) {
            return a.a(i2);
        }
        return true;
    }
}
