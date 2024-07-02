package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.media.session.MediaSessionCompatApi23;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
@RequiresApi(24)
/* loaded from: lib/Mus.dex */
class MediaSessionCompatApi24 {
    private static final String TAG = "MediaSessionCompatApi24";

    /* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
    /* loaded from: lib/Mus.dex */
    public interface Callback extends MediaSessionCompatApi23.Callback {
        void onPrepare();

        void onPrepareFromMediaId(String str, Bundle bundle);

        void onPrepareFromSearch(String str, Bundle bundle);

        void onPrepareFromUri(Uri uri, Bundle bundle);
    }

    MediaSessionCompatApi24() {
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    public static String getCallingPackage(Object obj) {
        MediaSession mediaSession = (MediaSession) obj;
        try {
            return (String) mediaSession.getClass().getMethod("getCallingPackage", new Class[0]).invoke(mediaSession, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(TAG, "Cannot execute MediaSession.getCallingPackage()", e);
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
    /* loaded from: lib/Mus.dex */
    static class CallbackProxy<T extends Callback> extends MediaSessionCompatApi23.CallbackProxy<T> {
        public CallbackProxy(T t) {
            super(t);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPrepare() {
            ((Callback) this.mCallback).onPrepare();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPrepareFromMediaId(String str, Bundle bundle) {
            ((Callback) this.mCallback).onPrepareFromMediaId(str, bundle);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPrepareFromSearch(String str, Bundle bundle) {
            ((Callback) this.mCallback).onPrepareFromSearch(str, bundle);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPrepareFromUri(Uri uri, Bundle bundle) {
            ((Callback) this.mCallback).onPrepareFromUri(uri, bundle);
        }
    }
}
