package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public class NotificationChannelCompat {
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";

    @NonNull
    final String a;
    CharSequence b;

    /* renamed from: c, reason: collision with root package name */
    int f464c;

    /* renamed from: d, reason: collision with root package name */
    String f465d;
    String e;
    boolean f;
    Uri g;
    AudioAttributes h;

    /* renamed from: i, reason: collision with root package name */
    boolean f466i;
    int j;
    boolean k;
    long[] l;
    String m;
    String n;
    private boolean o;
    private int p;
    private boolean q;
    private boolean r;

    /* loaded from: classes.dex */
    public static class Builder {
        private final NotificationChannelCompat a;

        public Builder(@NonNull String str, int i2) {
            this.a = new NotificationChannelCompat(str, i2);
        }

        @NonNull
        public NotificationChannelCompat build() {
            return this.a;
        }

        @NonNull
        public Builder setConversationId(@NonNull String str, @NonNull String str2) {
            if (Build.VERSION.SDK_INT >= 30) {
                NotificationChannelCompat notificationChannelCompat = this.a;
                notificationChannelCompat.m = str;
                notificationChannelCompat.n = str2;
            }
            return this;
        }

        @NonNull
        public Builder setDescription(@Nullable String str) {
            this.a.f465d = str;
            return this;
        }

        @NonNull
        public Builder setGroup(@Nullable String str) {
            this.a.e = str;
            return this;
        }

        @NonNull
        public Builder setImportance(int i2) {
            this.a.f464c = i2;
            return this;
        }

        @NonNull
        public Builder setLightColor(int i2) {
            this.a.j = i2;
            return this;
        }

        @NonNull
        public Builder setLightsEnabled(boolean z) {
            this.a.f466i = z;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence charSequence) {
            this.a.b = charSequence;
            return this;
        }

        @NonNull
        public Builder setShowBadge(boolean z) {
            this.a.f = z;
            return this;
        }

        @NonNull
        public Builder setSound(@Nullable Uri uri, @Nullable AudioAttributes audioAttributes) {
            NotificationChannelCompat notificationChannelCompat = this.a;
            notificationChannelCompat.g = uri;
            notificationChannelCompat.h = audioAttributes;
            return this;
        }

        @NonNull
        public Builder setVibrationEnabled(boolean z) {
            this.a.k = z;
            return this;
        }

        @NonNull
        public Builder setVibrationPattern(@Nullable long[] jArr) {
            NotificationChannelCompat notificationChannelCompat = this.a;
            notificationChannelCompat.k = jArr != null && jArr.length > 0;
            notificationChannelCompat.l = jArr;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(26)
    public NotificationChannelCompat(@NonNull NotificationChannel notificationChannel) {
        this(notificationChannel.getId(), notificationChannel.getImportance());
        this.b = notificationChannel.getName();
        this.f465d = notificationChannel.getDescription();
        this.e = notificationChannel.getGroup();
        this.f = notificationChannel.canShowBadge();
        this.g = notificationChannel.getSound();
        this.h = notificationChannel.getAudioAttributes();
        this.f466i = notificationChannel.shouldShowLights();
        this.j = notificationChannel.getLightColor();
        this.k = notificationChannel.shouldVibrate();
        this.l = notificationChannel.getVibrationPattern();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.m = notificationChannel.getParentChannelId();
            this.n = notificationChannel.getConversationId();
        }
        this.o = notificationChannel.canBypassDnd();
        this.p = notificationChannel.getLockscreenVisibility();
        if (i2 >= 29) {
            this.q = notificationChannel.canBubble();
        }
        if (i2 >= 30) {
            this.r = notificationChannel.isImportantConversation();
        }
    }

    NotificationChannelCompat(@NonNull String str, int i2) {
        this.f = true;
        this.g = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.j = 0;
        this.a = (String) Preconditions.checkNotNull(str);
        this.f464c = i2;
        if (Build.VERSION.SDK_INT >= 21) {
            this.h = Notification.AUDIO_ATTRIBUTES_DEFAULT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationChannel a() {
        String str;
        String str2;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 26) {
            return null;
        }
        NotificationChannel notificationChannel = new NotificationChannel(this.a, this.b, this.f464c);
        notificationChannel.setDescription(this.f465d);
        notificationChannel.setGroup(this.e);
        notificationChannel.setShowBadge(this.f);
        notificationChannel.setSound(this.g, this.h);
        notificationChannel.enableLights(this.f466i);
        notificationChannel.setLightColor(this.j);
        notificationChannel.setVibrationPattern(this.l);
        notificationChannel.enableVibration(this.k);
        if (i2 >= 30 && (str = this.m) != null && (str2 = this.n) != null) {
            notificationChannel.setConversationId(str, str2);
        }
        return notificationChannel;
    }

    public boolean canBubble() {
        return this.q;
    }

    public boolean canBypassDnd() {
        return this.o;
    }

    public boolean canShowBadge() {
        return this.f;
    }

    @Nullable
    public AudioAttributes getAudioAttributes() {
        return this.h;
    }

    @Nullable
    public String getConversationId() {
        return this.n;
    }

    @Nullable
    public String getDescription() {
        return this.f465d;
    }

    @Nullable
    public String getGroup() {
        return this.e;
    }

    @NonNull
    public String getId() {
        return this.a;
    }

    public int getImportance() {
        return this.f464c;
    }

    public int getLightColor() {
        return this.j;
    }

    public int getLockscreenVisibility() {
        return this.p;
    }

    @Nullable
    public CharSequence getName() {
        return this.b;
    }

    @Nullable
    public String getParentChannelId() {
        return this.m;
    }

    @Nullable
    public Uri getSound() {
        return this.g;
    }

    @Nullable
    public long[] getVibrationPattern() {
        return this.l;
    }

    public boolean isImportantConversation() {
        return this.r;
    }

    public boolean shouldShowLights() {
        return this.f466i;
    }

    public boolean shouldVibrate() {
        return this.k;
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this.a, this.f464c).setName(this.b).setDescription(this.f465d).setGroup(this.e).setShowBadge(this.f).setSound(this.g, this.h).setLightsEnabled(this.f466i).setLightColor(this.j).setVibrationEnabled(this.k).setVibrationPattern(this.l).setConversationId(this.m, this.n);
    }
}
