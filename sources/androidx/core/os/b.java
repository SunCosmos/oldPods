package androidx.core.os;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;

/* loaded from: classes.dex */
interface b {
    Object a();

    String b();

    @IntRange(from = -1)
    int c(Locale locale);

    @Nullable
    Locale d(@NonNull String[] strArr);

    Locale get(int i2);

    boolean isEmpty();

    @IntRange(from = 0)
    int size();
}
