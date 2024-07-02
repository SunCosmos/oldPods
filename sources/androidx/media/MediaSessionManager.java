package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.media.b;
import androidx.media.c;

/* loaded from: classes.dex */
public final class MediaSessionManager {
    static final boolean b = Log.isLoggable("MediaSessionManager", 3);

    /* renamed from: c */
    private static final Object f876c = new Object();

    /* renamed from: d */
    private static volatile MediaSessionManager f877d;
    a a;

    /* loaded from: classes.dex */
    public static final class RemoteUserInfo {
        public static final String LEGACY_CONTROLLER = "android.media.session.MediaController";
        b a;

        @RequiresApi(28)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteUserInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.a = new b.a(remoteUserInfo);
        }

        public RemoteUserInfo(@NonNull String str, int i2, int i3) {
            this.a = Build.VERSION.SDK_INT >= 28 ? new b.a(str, i2, i3) : new c.a(str, i2, i3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RemoteUserInfo) {
                return this.a.equals(((RemoteUserInfo) obj).a);
            }
            return false;
        }

        @NonNull
        public String getPackageName() {
            return this.a.getPackageName();
        }

        public int getPid() {
            return this.a.b();
        }

        public int getUid() {
            return this.a.a();
        }

        public int hashCode() {
            return this.a.hashCode();
        }
    }

    /* loaded from: classes.dex */
    public interface a {
        boolean a(b bVar);
    }

    /* loaded from: classes.dex */
    public interface b {
        int a();

        int b();

        String getPackageName();
    }

    private MediaSessionManager(Context context) {
        int i2 = Build.VERSION.SDK_INT;
        this.a = i2 >= 28 ? new androidx.media.b(context) : i2 >= 21 ? new androidx.media.a(context) : new c(context);
    }

    @NonNull
    public static MediaSessionManager getSessionManager(@NonNull Context context) {
        MediaSessionManager mediaSessionManager = f877d;
        if (mediaSessionManager == null) {
            synchronized (f876c) {
                mediaSessionManager = f877d;
                if (mediaSessionManager == null) {
                    f877d = new MediaSessionManager(context.getApplicationContext());
                    mediaSessionManager = f877d;
                }
            }
        }
        return mediaSessionManager;
    }

    public boolean isTrustedForMediaControl(@NonNull RemoteUserInfo remoteUserInfo) {
        if (remoteUserInfo != null) {
            return this.a.a(remoteUserInfo.a);
        }
        throw new IllegalArgumentException("userInfo should not be null");
    }
}
