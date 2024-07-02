package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;

@Deprecated
/* loaded from: classes.dex */
public final class TraceCompat {
    private static long a;
    private static Method b;

    /* renamed from: c, reason: collision with root package name */
    private static Method f568c;

    /* renamed from: d, reason: collision with root package name */
    private static Method f569d;
    private static Method e;

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 18 || i2 >= 29) {
            return;
        }
        try {
            a = Trace.class.getField("TRACE_TAG_APP").getLong(null);
            Class cls = Long.TYPE;
            b = Trace.class.getMethod("isTagEnabled", cls);
            Class cls2 = Integer.TYPE;
            f568c = Trace.class.getMethod("asyncTraceBegin", cls, String.class, cls2);
            f569d = Trace.class.getMethod("asyncTraceEnd", cls, String.class, cls2);
            e = Trace.class.getMethod("traceCounter", cls, String.class, cls2);
        } catch (Exception e2) {
            Log.i("TraceCompat", "Unable to initialize via reflection.", e2);
        }
    }

    private TraceCompat() {
    }

    public static void beginAsyncSection(@NonNull String str, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            Trace.beginAsyncSection(str, i2);
        } else if (i3 >= 18) {
            try {
                f568c.invoke(null, Long.valueOf(a), str, Integer.valueOf(i2));
            } catch (Exception unused) {
                Log.v("TraceCompat", "Unable to invoke asyncTraceBegin() via reflection.");
            }
        }
    }

    public static void beginSection(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void endAsyncSection(@NonNull String str, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            Trace.endAsyncSection(str, i2);
        } else if (i3 >= 18) {
            try {
                f569d.invoke(null, Long.valueOf(a), str, Integer.valueOf(i2));
            } catch (Exception unused) {
                Log.v("TraceCompat", "Unable to invoke endAsyncSection() via reflection.");
            }
        }
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }

    public static boolean isEnabled() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            return Trace.isEnabled();
        }
        if (i2 >= 18) {
            try {
                return ((Boolean) b.invoke(null, Long.valueOf(a))).booleanValue();
            } catch (Exception unused) {
                Log.v("TraceCompat", "Unable to invoke isTagEnabled() via reflection.");
            }
        }
        return false;
    }

    public static void setCounter(@NonNull String str, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            Trace.setCounter(str, i2);
        } else if (i3 >= 18) {
            try {
                e.invoke(null, Long.valueOf(a), str, Integer.valueOf(i2));
            } catch (Exception unused) {
                Log.v("TraceCompat", "Unable to invoke traceCounter() via reflection.");
            }
        }
    }
}
