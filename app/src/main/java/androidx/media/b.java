package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;

/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(28)
/* loaded from: classes.dex */
public class b extends androidx.media.a {

    /* renamed from: d, reason: collision with root package name */
    android.media.session.MediaSessionManager f880d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a implements MediaSessionManager.b {
        final MediaSessionManager.RemoteUserInfo a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.a = remoteUserInfo;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(String str, int i2, int i3) {
            this.a = new MediaSessionManager.RemoteUserInfo(str, i2, i3);
        }

        @Override // androidx.media.MediaSessionManager.b
        public int a() {
            return this.a.getUid();
        }

        @Override // androidx.media.MediaSessionManager.b
        public int b() {
            return this.a.getPid();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof a) {
                return this.a.equals(((a) obj).a);
            }
            return false;
        }

        @Override // androidx.media.MediaSessionManager.b
        public String getPackageName() {
            return this.a.getPackageName();
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context) {
        super(context);
        this.f880d = (android.media.session.MediaSessionManager) context.getSystemService("media_session");
    }

    @Override // androidx.media.a, androidx.media.c, androidx.media.MediaSessionManager.a
    public boolean a(MediaSessionManager.b bVar) {
        if (bVar instanceof a) {
            return this.f880d.isTrustedForMediaControl(((a) bVar).a);
        }
        return false;
    }
}
