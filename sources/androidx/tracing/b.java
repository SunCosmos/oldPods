package androidx.tracing;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(29)
/* loaded from: classes.dex */
final class b {
    public static void a(@NonNull String str, int i2) {
        android.os.Trace.beginAsyncSection(str, i2);
    }

    public static void b(@NonNull String str, int i2) {
        android.os.Trace.endAsyncSection(str, i2);
    }

    public static void c(@NonNull String str, int i2) {
        android.os.Trace.setCounter(str, i2);
    }
}
