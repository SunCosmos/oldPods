package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class NotificationChannelGroupCompat {
    final String a;
    CharSequence b;

    /* renamed from: c, reason: collision with root package name */
    String f467c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f468d;
    private List<NotificationChannelCompat> e;

    /* loaded from: classes.dex */
    public static class Builder {
        final NotificationChannelGroupCompat a;

        public Builder(@NonNull String str) {
            this.a = new NotificationChannelGroupCompat(str);
        }

        @NonNull
        public NotificationChannelGroupCompat build() {
            return this.a;
        }

        @NonNull
        public Builder setDescription(@Nullable String str) {
            this.a.f467c = str;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence) {
            this.a.b = charSequence;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(28)
    public NotificationChannelGroupCompat(@NonNull NotificationChannelGroup notificationChannelGroup) {
        this(notificationChannelGroup, Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(26)
    public NotificationChannelGroupCompat(@NonNull NotificationChannelGroup notificationChannelGroup, @NonNull List<NotificationChannel> list) {
        this(notificationChannelGroup.getId());
        List<NotificationChannelCompat> a;
        this.b = notificationChannelGroup.getName();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.f467c = notificationChannelGroup.getDescription();
        }
        if (i2 >= 28) {
            this.f468d = notificationChannelGroup.isBlocked();
            a = a(notificationChannelGroup.getChannels());
        } else {
            a = a(list);
        }
        this.e = a;
    }

    NotificationChannelGroupCompat(@NonNull String str) {
        this.e = Collections.emptyList();
        this.a = (String) Preconditions.checkNotNull(str);
    }

    @RequiresApi(26)
    private List<NotificationChannelCompat> a(List<NotificationChannel> list) {
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel notificationChannel : list) {
            if (this.a.equals(notificationChannel.getGroup())) {
                arrayList.add(new NotificationChannelCompat(notificationChannel));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationChannelGroup b() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 26) {
            return null;
        }
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(this.a, this.b);
        if (i2 >= 28) {
            notificationChannelGroup.setDescription(this.f467c);
        }
        return notificationChannelGroup;
    }

    @NonNull
    public List<NotificationChannelCompat> getChannels() {
        return this.e;
    }

    @Nullable
    public String getDescription() {
        return this.f467c;
    }

    @NonNull
    public String getId() {
        return this.a;
    }

    @Nullable
    public CharSequence getName() {
        return this.b;
    }

    public boolean isBlocked() {
        return this.f468d;
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this.a).setName(this.b).setDescription(this.f467c);
    }
}
