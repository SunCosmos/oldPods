package androidx.core.provider;

import android.util.Base64;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.util.List;

/* loaded from: classes.dex */
public final class FontRequest {
    private final String a;
    private final String b;

    /* renamed from: c, reason: collision with root package name */
    private final String f572c;

    /* renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f573d;
    private final int e;
    private final String f;

    public FontRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @ArrayRes int i2) {
        this.a = (String) Preconditions.checkNotNull(str);
        this.b = (String) Preconditions.checkNotNull(str2);
        this.f572c = (String) Preconditions.checkNotNull(str3);
        this.f573d = null;
        Preconditions.checkArgument(i2 != 0);
        this.e = i2;
        this.f = a(str, str2, str3);
    }

    public FontRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<List<byte[]>> list) {
        this.a = (String) Preconditions.checkNotNull(str);
        this.b = (String) Preconditions.checkNotNull(str2);
        this.f572c = (String) Preconditions.checkNotNull(str3);
        this.f573d = (List) Preconditions.checkNotNull(list);
        this.e = 0;
        this.f = a(str, str2, str3);
    }

    private String a(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return str + "-" + str2 + "-" + str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String b() {
        return this.f;
    }

    @Nullable
    public List<List<byte[]>> getCertificates() {
        return this.f573d;
    }

    @ArrayRes
    public int getCertificatesArrayResId() {
        return this.e;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public String getIdentifier() {
        return this.f;
    }

    @NonNull
    public String getProviderAuthority() {
        return this.a;
    }

    @NonNull
    public String getProviderPackage() {
        return this.b;
    }

    @NonNull
    public String getQuery() {
        return this.f572c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.a + ", mProviderPackage: " + this.b + ", mQuery: " + this.f572c + ", mCertificates:");
        for (int i2 = 0; i2 < this.f573d.size(); i2++) {
            sb.append(" [");
            List<byte[]> list = this.f573d.get(i2);
            for (int i3 = 0; i3 < list.size(); i3++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i3), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.e);
        return sb.toString();
    }
}
