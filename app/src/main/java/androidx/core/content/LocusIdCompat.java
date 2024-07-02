package androidx.core.content;

import android.content.LocusId;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public final class LocusIdCompat {
    private final String a;
    private final LocusId b;

    @RequiresApi(29)
    /* loaded from: classes.dex */
    private static class a {
        @NonNull
        static LocusId a(@NonNull String str) {
            return new LocusId(str);
        }

        @NonNull
        static String b(@NonNull LocusId locusId) {
            return locusId.getId();
        }
    }

    public LocusIdCompat(@NonNull String str) {
        this.a = (String) Preconditions.checkStringNotEmpty(str, "id cannot be empty");
        this.b = Build.VERSION.SDK_INT >= 29 ? a.a(str) : null;
    }

    @NonNull
    private String a() {
        return this.a.length() + "_chars";
    }

    @NonNull
    @RequiresApi(29)
    public static LocusIdCompat toLocusIdCompat(@NonNull LocusId locusId) {
        Preconditions.checkNotNull(locusId, "locusId cannot be null");
        return new LocusIdCompat((String) Preconditions.checkStringNotEmpty(a.b(locusId), "id cannot be empty"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocusIdCompat.class != obj.getClass()) {
            return false;
        }
        String str = this.a;
        String str2 = ((LocusIdCompat) obj).a;
        return str == null ? str2 == null : str.equals(str2);
    }

    @NonNull
    public String getId() {
        return this.a;
    }

    public int hashCode() {
        String str = this.a;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    @NonNull
    @RequiresApi(29)
    public LocusId toLocusId() {
        return this.b;
    }

    @NonNull
    public String toString() {
        return "LocusIdCompat[" + a() + "]";
    }
}
