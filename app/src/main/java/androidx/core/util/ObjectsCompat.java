package androidx.core.util;

import android.os.Build;
import androidx.annotation.Nullable;
import defpackage.b;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ObjectsCompat {
    private ObjectsCompat() {
    }

    public static boolean equals(@Nullable Object obj, @Nullable Object obj2) {
        return Build.VERSION.SDK_INT >= 19 ? b.a(obj, obj2) : obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hash(@Nullable Object... objArr) {
        int i2 = Build.VERSION.SDK_INT;
        return Arrays.hashCode(objArr);
    }

    public static int hashCode(@Nullable Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    @Nullable
    public static String toString(@Nullable Object obj, @Nullable String str) {
        return obj != null ? obj.toString() : str;
    }
}
