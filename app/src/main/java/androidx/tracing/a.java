package androidx.tracing;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(18)
/* loaded from: classes.dex */
final class a {
    public static void a(@NonNull String str) {
        android.os.Trace.beginSection(str);
    }

    public static void b() {
        android.os.Trace.endSection();
    }
}
