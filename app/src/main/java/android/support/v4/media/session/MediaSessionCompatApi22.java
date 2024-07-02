package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.support.annotation.RequiresApi;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
@RequiresApi(22)
/* loaded from: lib/Mus.dex */
class MediaSessionCompatApi22 {
    MediaSessionCompatApi22() {
    }

    public static void setRatingType(Object obj, int i2) {
        ((MediaSession) obj).setRatingType(i2);
    }
}
