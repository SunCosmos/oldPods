package androidx.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import androidx.annotation.RequiresApi;
import androidx.media.MediaBrowserServiceCompatApi21;

@RequiresApi(23)
/* loaded from: classes.dex */
class MediaBrowserServiceCompatApi23 {

    /* loaded from: classes.dex */
    public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi21.ServiceCompatProxy {
        void onLoadItem(String str, MediaBrowserServiceCompatApi21.c<Parcel> cVar);
    }

    /* loaded from: classes.dex */
    static class a extends MediaBrowserServiceCompatApi21.b {
        /* JADX INFO: Access modifiers changed from: package-private */
        public a(Context context, ServiceCompatProxy serviceCompatProxy) {
            super(context, serviceCompatProxy);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadItem(String str, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
            ((ServiceCompatProxy) this.a).onLoadItem(str, new MediaBrowserServiceCompatApi21.c<>(result));
        }
    }

    public static Object a(Context context, ServiceCompatProxy serviceCompatProxy) {
        return new a(context, serviceCompatProxy);
    }
}
