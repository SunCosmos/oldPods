package androidx.tracing;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class Trace {
    private static long a;
    private static Method b;

    /* renamed from: c, reason: collision with root package name */
    private static Method f1036c;

    /* renamed from: d, reason: collision with root package name */
    private static Method f1037d;
    private static Method e;

    private Trace() {
    }

    private static void a(@NonNull String str, int i2) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (f1036c == null) {
                    f1036c = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
                }
                f1036c.invoke(null, Long.valueOf(a), str, Integer.valueOf(i2));
            } catch (Exception e2) {
                c("asyncTraceBegin", e2);
            }
        }
    }

    private static void b(@NonNull String str, int i2) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (f1037d == null) {
                    f1037d = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                }
                f1037d.invoke(null, Long.valueOf(a), str, Integer.valueOf(i2));
            } catch (Exception e2) {
                c("asyncTraceEnd", e2);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void beginAsyncSection(@NonNull String str, int i2) {
        try {
            if (f1036c == null) {
                b.a(str, i2);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        a(str, i2);
    }

    public static void beginSection(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            a.a(str);
        }
    }

    private static void c(@NonNull String str, @NonNull Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof RuntimeException)) {
                throw new RuntimeException(cause);
            }
            throw ((RuntimeException) cause);
        }
        Log.v("Trace", "Unable to call " + str + " via reflection", exc);
    }

    private static boolean d() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (b == null) {
                    a = android.os.Trace.class.getField("TRACE_TAG_APP").getLong(null);
                    b = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
                }
                return ((Boolean) b.invoke(null, Long.valueOf(a))).booleanValue();
            } catch (Exception e2) {
                c("isTagEnabled", e2);
            }
        }
        return false;
    }

    private static void e(@NonNull String str, int i2) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (e == null) {
                    e = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
                }
                e.invoke(null, Long.valueOf(a), str, Integer.valueOf(i2));
            } catch (Exception e2) {
                c("traceCounter", e2);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void endAsyncSection(@NonNull String str, int i2) {
        try {
            if (f1037d == null) {
                b.b(str, i2);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        b(str, i2);
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            a.b();
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean isEnabled() {
        try {
            if (b == null) {
                return android.os.Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        return d();
    }

    @SuppressLint({"NewApi"})
    public static void setCounter(@NonNull String str, int i2) {
        try {
            if (e == null) {
                b.c(str, i2);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        e(str, i2);
    }
}
