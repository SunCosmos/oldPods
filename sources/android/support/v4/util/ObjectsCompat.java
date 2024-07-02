package android.support.v4.util;

import android.os.Build;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: lib/Mus.dex */
public class ObjectsCompat {
    private ObjectsCompat() {
    }

    public static boolean equals(@Nullable Object obj, @Nullable Object obj2) {
        if (Build.VERSION.SDK_INT < 19) {
            return obj == obj2 || (obj != null && obj.equals(obj2));
        }
        return Objects.equals(obj, obj2);
    }

    public static int hashCode(@Nullable Object obj) {
        return obj != null ? obj.hashCode() : 0;
    }

    public static int hash(@Nullable Object... objArr) {
        if (Build.VERSION.SDK_INT < 19) {
            return Arrays.hashCode(objArr);
        }
        return Objects.hash(objArr);
    }
}
