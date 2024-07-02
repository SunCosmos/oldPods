package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class ContentInfoCompat {
    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
    public static final int SOURCE_APP = 0;
    public static final int SOURCE_CLIPBOARD = 1;
    public static final int SOURCE_DRAG_AND_DROP = 3;
    public static final int SOURCE_INPUT_METHOD = 2;

    @NonNull
    final ClipData a;
    final int b;

    /* renamed from: c, reason: collision with root package name */
    final int f611c;

    /* renamed from: d, reason: collision with root package name */
    @Nullable
    final Uri f612d;

    @Nullable
    final Bundle e;

    /* loaded from: classes.dex */
    public static final class Builder {

        @NonNull
        ClipData a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        int f613c;

        /* renamed from: d, reason: collision with root package name */
        @Nullable
        Uri f614d;

        @Nullable
        Bundle e;

        public Builder(@NonNull ClipData clipData, int i2) {
            this.a = clipData;
            this.b = i2;
        }

        public Builder(@NonNull ContentInfoCompat contentInfoCompat) {
            this.a = contentInfoCompat.a;
            this.b = contentInfoCompat.b;
            this.f613c = contentInfoCompat.f611c;
            this.f614d = contentInfoCompat.f612d;
            this.e = contentInfoCompat.e;
        }

        @NonNull
        public ContentInfoCompat build() {
            return new ContentInfoCompat(this);
        }

        @NonNull
        public Builder setClip(@NonNull ClipData clipData) {
            this.a = clipData;
            return this;
        }

        @NonNull
        public Builder setExtras(@Nullable Bundle bundle) {
            this.e = bundle;
            return this;
        }

        @NonNull
        public Builder setFlags(int i2) {
            this.f613c = i2;
            return this;
        }

        @NonNull
        public Builder setLinkUri(@Nullable Uri uri) {
            this.f614d = uri;
            return this;
        }

        @NonNull
        public Builder setSource(int i2) {
            this.b = i2;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Source {
    }

    ContentInfoCompat(Builder builder) {
        this.a = (ClipData) Preconditions.checkNotNull(builder.a);
        this.b = Preconditions.checkArgumentInRange(builder.b, 0, 3, "source");
        this.f611c = Preconditions.checkFlagsArgument(builder.f613c, 1);
        this.f612d = builder.f614d;
        this.e = builder.e;
    }

    private static ClipData a(ClipDescription clipDescription, List<ClipData.Item> list) {
        ClipData clipData = new ClipData(new ClipDescription(clipDescription), list.get(0));
        for (int i2 = 1; i2 < list.size(); i2++) {
            clipData.addItem(list.get(i2));
        }
        return clipData;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    static String b(int i2) {
        return (i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    static String c(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? String.valueOf(i2) : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP";
    }

    @NonNull
    public ClipData getClip() {
        return this.a;
    }

    @Nullable
    public Bundle getExtras() {
        return this.e;
    }

    public int getFlags() {
        return this.f611c;
    }

    @Nullable
    public Uri getLinkUri() {
        return this.f612d;
    }

    public int getSource() {
        return this.b;
    }

    @NonNull
    public Pair<ContentInfoCompat, ContentInfoCompat> partition(@NonNull Predicate<ClipData.Item> predicate) {
        if (this.a.getItemCount() == 1) {
            boolean test = predicate.test(this.a.getItemAt(0));
            return Pair.create(test ? this : null, test ? null : this);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < this.a.getItemCount(); i2++) {
            ClipData.Item itemAt = this.a.getItemAt(i2);
            if (predicate.test(itemAt)) {
                arrayList.add(itemAt);
            } else {
                arrayList2.add(itemAt);
            }
        }
        return arrayList.isEmpty() ? Pair.create(null, this) : arrayList2.isEmpty() ? Pair.create(this, null) : Pair.create(new Builder(this).setClip(a(this.a.getDescription(), arrayList)).build(), new Builder(this).setClip(a(this.a.getDescription(), arrayList2)).build());
    }

    @NonNull
    public String toString() {
        return "ContentInfoCompat{clip=" + this.a + ", source=" + c(this.b) + ", flags=" + b(this.f611c) + ", linkUri=" + this.f612d + ", extras=" + this.e + "}";
    }
}
