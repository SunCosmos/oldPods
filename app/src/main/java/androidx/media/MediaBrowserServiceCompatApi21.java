package androidx.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(21)
/* loaded from: classes.dex */
class MediaBrowserServiceCompatApi21 {

    /* loaded from: classes.dex */
    public interface ServiceCompatProxy {
        a onGetRoot(String str, int i2, Bundle bundle);

        void onLoadChildren(String str, c<List<Parcel>> cVar);
    }

    /* loaded from: classes.dex */
    static class a {
        final String a;
        final Bundle b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(String str, Bundle bundle) {
            this.a = str;
            this.b = bundle;
        }
    }

    /* loaded from: classes.dex */
    static class b extends MediaBrowserService {
        final ServiceCompatProxy a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b(Context context, ServiceCompatProxy serviceCompatProxy) {
            attachBaseContext(context);
            this.a = serviceCompatProxy;
        }

        @Override // android.service.media.MediaBrowserService
        public MediaBrowserService.BrowserRoot onGetRoot(String str, int i2, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            a onGetRoot = this.a.onGetRoot(str, i2, bundle == null ? null : new Bundle(bundle));
            if (onGetRoot == null) {
                return null;
            }
            return new MediaBrowserService.BrowserRoot(onGetRoot.a, onGetRoot.b);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
            this.a.onLoadChildren(str, new c<>(result));
        }
    }

    /* loaded from: classes.dex */
    static class c<T> {
        MediaBrowserService.Result a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public c(MediaBrowserService.Result result) {
            this.a = result;
        }

        public void a() {
            this.a.detach();
        }

        List<MediaBrowser.MediaItem> b(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void c(T t) {
            if (t instanceof List) {
                this.a.sendResult(b((List) t));
                return;
            }
            if (!(t instanceof Parcel)) {
                this.a.sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) t;
            parcel.setDataPosition(0);
            this.a.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
        }
    }

    public static Object a(Context context, ServiceCompatProxy serviceCompatProxy) {
        return new b(context, serviceCompatProxy);
    }

    public static void b(Object obj, String str) {
        ((MediaBrowserService) obj).notifyChildrenChanged(str);
    }

    public static IBinder c(Object obj, Intent intent) {
        return ((MediaBrowserService) obj).onBind(intent);
    }

    public static void d(Object obj) {
        ((MediaBrowserService) obj).onCreate();
    }

    public static void e(Object obj, Object obj2) {
        ((MediaBrowserService) obj).setSessionToken((MediaSession.Token) obj2);
    }
}
