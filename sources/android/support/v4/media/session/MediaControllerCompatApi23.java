package android.support.v4.media.session;

import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.media.session.MediaControllerCompatApi21;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
@RequiresApi(23)
/* loaded from: lib/Mus.dex */
class MediaControllerCompatApi23 {
    MediaControllerCompatApi23() {
    }

    /* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
    /* loaded from: lib/Mus.dex */
    public static class TransportControls extends MediaControllerCompatApi21.TransportControls {
        public static void playFromUri(Object obj, Uri uri, Bundle bundle) {
            ((MediaController.TransportControls) obj).playFromUri(uri, bundle);
        }
    }
}
