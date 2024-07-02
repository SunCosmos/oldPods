package androidx.media;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements MediaSessionManager.a {

    /* renamed from: c, reason: collision with root package name */
    private static final boolean f881c = MediaSessionManager.b;
    Context a;
    ContentResolver b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements MediaSessionManager.b {
        private String a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private int f882c;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(String str, int i2, int i3) {
            this.a = str;
            this.b = i2;
            this.f882c = i3;
        }

        @Override // androidx.media.MediaSessionManager.b
        public int a() {
            return this.f882c;
        }

        @Override // androidx.media.MediaSessionManager.b
        public int b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return TextUtils.equals(this.a, aVar.a) && this.b == aVar.b && this.f882c == aVar.f882c;
        }

        @Override // androidx.media.MediaSessionManager.b
        public String getPackageName() {
            return this.a;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.a, Integer.valueOf(this.b), Integer.valueOf(this.f882c));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context) {
        this.a = context;
        this.b = context.getContentResolver();
    }

    private boolean d(MediaSessionManager.b bVar, String str) {
        return bVar.b() < 0 ? this.a.getPackageManager().checkPermission(str, bVar.getPackageName()) == 0 : this.a.checkPermission(str, bVar.b(), bVar.a()) == 0;
    }

    @Override // androidx.media.MediaSessionManager.a
    public boolean a(@NonNull MediaSessionManager.b bVar) {
        try {
            if (this.a.getPackageManager().getApplicationInfo(bVar.getPackageName(), 0).uid == bVar.a()) {
                return d(bVar, "android.permission.STATUS_BAR_SERVICE") || d(bVar, "android.permission.MEDIA_CONTENT_CONTROL") || bVar.a() == 1000 || c(bVar);
            }
            if (f881c) {
                Log.d("MediaSessionManager", "Package name " + bVar.getPackageName() + " doesn't match with the uid " + bVar.a());
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            if (f881c) {
                Log.d("MediaSessionManager", "Package " + bVar.getPackageName() + " doesn't exist");
            }
            return false;
        }
    }

    public Context b() {
        return this.a;
    }

    boolean c(@NonNull MediaSessionManager.b bVar) {
        String string = Settings.Secure.getString(this.b, "enabled_notification_listeners");
        if (string != null) {
            for (String str : string.split(":")) {
                ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                if (unflattenFromString != null && unflattenFromString.getPackageName().equals(bVar.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
