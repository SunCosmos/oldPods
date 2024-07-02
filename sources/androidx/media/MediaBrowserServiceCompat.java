package androidx.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.core.util.Pair;
import androidx.media.MediaBrowserServiceCompatApi21;
import androidx.media.MediaBrowserServiceCompatApi23;
import androidx.media.MediaBrowserServiceCompatApi26;
import androidx.media.MediaSessionManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_MEDIA_ITEM = "media_item";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_SEARCH_RESULTS = "search_results";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_ERROR = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_OK = 0;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_PROGRESS_UPDATE = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    static final boolean f = Log.isLoggable("MBServiceCompat", 3);
    private f a;

    /* renamed from: c, reason: collision with root package name */
    e f847c;
    MediaSessionCompat.Token e;
    final ArrayMap<IBinder, e> b = new ArrayMap<>();

    /* renamed from: d, reason: collision with root package name */
    final o f848d = new o();

    /* loaded from: classes.dex */
    public static final class BrowserRoot {
        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";

        @Deprecated
        public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
        private final String a;
        private final Bundle b;

        public BrowserRoot(@NonNull String str, @Nullable Bundle bundle) {
            if (str == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
            }
            this.a = str;
            this.b = bundle;
        }

        public Bundle getExtras() {
            return this.b;
        }

        public String getRootId() {
            return this.a;
        }
    }

    /* loaded from: classes.dex */
    public static class Result<T> {
        private final Object a;
        private boolean b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f849c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f850d;
        private int e;

        Result(Object obj) {
            this.a = obj;
        }

        private void a(Bundle bundle) {
            if (bundle != null && bundle.containsKey(MediaBrowserCompat.EXTRA_DOWNLOAD_PROGRESS)) {
                float f = bundle.getFloat(MediaBrowserCompat.EXTRA_DOWNLOAD_PROGRESS);
                if (f < -1.0E-5f || f > 1.00001f) {
                    throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
                }
            }
        }

        int b() {
            return this.e;
        }

        boolean c() {
            return this.b || this.f849c || this.f850d;
        }

        void d(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.a);
        }

        public void detach() {
            if (this.b) {
                throw new IllegalStateException("detach() called when detach() had already been called for: " + this.a);
            }
            if (this.f849c) {
                throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.a);
            }
            if (!this.f850d) {
                this.b = true;
                return;
            }
            throw new IllegalStateException("detach() called when sendError() had already been called for: " + this.a);
        }

        void e(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an interim update for " + this.a);
        }

        void f(T t) {
        }

        void g(int i2) {
            this.e = i2;
        }

        public void sendError(Bundle bundle) {
            if (!this.f849c && !this.f850d) {
                this.f850d = true;
                d(bundle);
            } else {
                throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.a);
            }
        }

        public void sendProgressUpdate(Bundle bundle) {
            if (!this.f849c && !this.f850d) {
                a(bundle);
                e(bundle);
            } else {
                throw new IllegalStateException("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: " + this.a);
            }
        }

        public void sendResult(T t) {
            if (!this.f849c && !this.f850d) {
                this.f849c = true;
                f(t);
            } else {
                throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends Result<List<MediaBrowserCompat.MediaItem>> {
        final /* synthetic */ e f;
        final /* synthetic */ String g;
        final /* synthetic */ Bundle h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ Bundle f851i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Object obj, e eVar, String str, Bundle bundle, Bundle bundle2) {
            super(obj);
            this.f = eVar;
            this.g = str;
            this.h = bundle;
            this.f851i = bundle2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.Result
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void f(List<MediaBrowserCompat.MediaItem> list) {
            if (MediaBrowserServiceCompat.this.b.get(this.f.f853d.asBinder()) != this.f) {
                if (MediaBrowserServiceCompat.f) {
                    Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.f.a + " id=" + this.g);
                    return;
                }
                return;
            }
            if ((b() & 1) != 0) {
                list = MediaBrowserServiceCompat.this.b(list, this.h);
            }
            try {
                this.f.f853d.c(this.g, list, this.h, this.f851i);
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.g + " package=" + this.f.a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends Result<MediaBrowserCompat.MediaItem> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.Result
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void f(MediaBrowserCompat.MediaItem mediaItem) {
            if ((b() & 2) != 0) {
                this.f.send(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("media_item", mediaItem);
            this.f.send(0, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends Result<List<MediaBrowserCompat.MediaItem>> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.Result
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void f(List<MediaBrowserCompat.MediaItem> list) {
            if ((b() & 4) != 0 || list == null) {
                this.f.send(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("search_results", (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
            this.f.send(0, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends Result<Bundle> {
        final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.Result
        void d(Bundle bundle) {
            this.f.send(-1, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.Result
        void e(Bundle bundle) {
            this.f.send(1, bundle);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.media.MediaBrowserServiceCompat.Result
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public void f(Bundle bundle) {
            this.f.send(0, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class e implements IBinder.DeathRecipient {
        public final String a;
        public final MediaSessionManager.RemoteUserInfo b;

        /* renamed from: c, reason: collision with root package name */
        public final Bundle f852c;

        /* renamed from: d, reason: collision with root package name */
        public final m f853d;
        public final HashMap<String, List<Pair<IBinder, Bundle>>> e = new HashMap<>();
        public BrowserRoot f;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                MediaBrowserServiceCompat.this.b.remove(eVar.f853d.asBinder());
            }
        }

        e(String str, int i2, int i3, Bundle bundle, m mVar) {
            this.a = str;
            this.b = new MediaSessionManager.RemoteUserInfo(str, i2, i3);
            this.f852c = bundle;
            this.f853d = mVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.f848d.post(new a());
        }
    }

    /* loaded from: classes.dex */
    interface f {
        void a();

        MediaSessionManager.RemoteUserInfo b();

        IBinder c(Intent intent);

        Bundle d();

        void e(String str, Bundle bundle);

        void f(MediaSessionCompat.Token token);

        void g(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle);
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    class g implements f, MediaBrowserServiceCompatApi21.ServiceCompatProxy {
        final List<Bundle> a = new ArrayList();
        Object b;

        /* renamed from: c, reason: collision with root package name */
        Messenger f854c;

        /* loaded from: classes.dex */
        class a implements Runnable {
            final /* synthetic */ MediaSessionCompat.Token a;

            a(MediaSessionCompat.Token token) {
                this.a = token;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!g.this.a.isEmpty()) {
                    IMediaSession extraBinder = this.a.getExtraBinder();
                    if (extraBinder != null) {
                        Iterator<Bundle> it = g.this.a.iterator();
                        while (it.hasNext()) {
                            BundleCompat.putBinder(it.next(), "extra_session_binder", extraBinder.asBinder());
                        }
                    }
                    g.this.a.clear();
                }
                MediaBrowserServiceCompatApi21.e(g.this.b, this.a.getToken());
            }
        }

        /* loaded from: classes.dex */
        class b extends Result<List<MediaBrowserCompat.MediaItem>> {
            final /* synthetic */ MediaBrowserServiceCompatApi21.c f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(g gVar, Object obj, MediaBrowserServiceCompatApi21.c cVar) {
                super(obj);
                this.f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void detach() {
                this.f.a();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void f(List<MediaBrowserCompat.MediaItem> list) {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel obtain = Parcel.obtain();
                        mediaItem.writeToParcel(obtain, 0);
                        arrayList.add(obtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f.c(arrayList);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class c implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ Bundle b;

            c(String str, Bundle bundle) {
                this.a = str;
                this.b = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                Iterator<IBinder> it = MediaBrowserServiceCompat.this.b.keySet().iterator();
                while (it.hasNext()) {
                    g.this.j(MediaBrowserServiceCompat.this.b.get(it.next()), this.a, this.b);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class d implements Runnable {
            final /* synthetic */ MediaSessionManager.RemoteUserInfo a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ Bundle f857c;

            d(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle) {
                this.a = remoteUserInfo;
                this.b = str;
                this.f857c = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < MediaBrowserServiceCompat.this.b.size(); i2++) {
                    e valueAt = MediaBrowserServiceCompat.this.b.valueAt(i2);
                    if (valueAt.b.equals(this.a)) {
                        g.this.j(valueAt, this.b, this.f857c);
                    }
                }
            }
        }

        g() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void a() {
            Object a2 = MediaBrowserServiceCompatApi21.a(MediaBrowserServiceCompat.this, this);
            this.b = a2;
            MediaBrowserServiceCompatApi21.d(a2);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public MediaSessionManager.RemoteUserInfo b() {
            e eVar = MediaBrowserServiceCompat.this.f847c;
            if (eVar != null) {
                return eVar.b;
            }
            throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public IBinder c(Intent intent) {
            return MediaBrowserServiceCompatApi21.c(this.b, intent);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public Bundle d() {
            if (this.f854c == null) {
                return null;
            }
            e eVar = MediaBrowserServiceCompat.this.f847c;
            if (eVar == null) {
                throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
            }
            if (eVar.f852c == null) {
                return null;
            }
            return new Bundle(MediaBrowserServiceCompat.this.f847c.f852c);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void e(String str, Bundle bundle) {
            k(str, bundle);
            i(str, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void f(MediaSessionCompat.Token token) {
            MediaBrowserServiceCompat.this.f848d.a(new a(token));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void g(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle) {
            h(remoteUserInfo, str, bundle);
        }

        void h(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle) {
            MediaBrowserServiceCompat.this.f848d.post(new d(remoteUserInfo, str, bundle));
        }

        void i(String str, Bundle bundle) {
            MediaBrowserServiceCompat.this.f848d.post(new c(str, bundle));
        }

        void j(e eVar, String str, Bundle bundle) {
            List<Pair<IBinder, Bundle>> list = eVar.e.get(str);
            if (list != null) {
                for (Pair<IBinder, Bundle> pair : list) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(bundle, pair.second)) {
                        MediaBrowserServiceCompat.this.e(str, eVar, pair.second, bundle);
                    }
                }
            }
        }

        void k(String str, Bundle bundle) {
            MediaBrowserServiceCompatApi21.b(this.b, str);
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi21.ServiceCompatProxy
        public MediaBrowserServiceCompatApi21.a onGetRoot(String str, int i2, Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || bundle.getInt("extra_client_version", 0) == 0) {
                bundle2 = null;
            } else {
                bundle.remove("extra_client_version");
                this.f854c = new Messenger(MediaBrowserServiceCompat.this.f848d);
                bundle2 = new Bundle();
                bundle2.putInt("extra_service_version", 2);
                BundleCompat.putBinder(bundle2, "extra_messenger", this.f854c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.e;
                if (token != null) {
                    IMediaSession extraBinder = token.getExtraBinder();
                    BundleCompat.putBinder(bundle2, "extra_session_binder", extraBinder == null ? null : extraBinder.asBinder());
                } else {
                    this.a.add(bundle2);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.f847c = new e(str, -1, i2, bundle, null);
            BrowserRoot onGetRoot = MediaBrowserServiceCompat.this.onGetRoot(str, i2, bundle);
            MediaBrowserServiceCompat.this.f847c = null;
            if (onGetRoot == null) {
                return null;
            }
            if (bundle2 == null) {
                bundle2 = onGetRoot.getExtras();
            } else if (onGetRoot.getExtras() != null) {
                bundle2.putAll(onGetRoot.getExtras());
            }
            return new MediaBrowserServiceCompatApi21.a(onGetRoot.getRootId(), bundle2);
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi21.ServiceCompatProxy
        public void onLoadChildren(String str, MediaBrowserServiceCompatApi21.c<List<Parcel>> cVar) {
            MediaBrowserServiceCompat.this.onLoadChildren(str, new b(this, str, cVar));
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    class h extends g implements MediaBrowserServiceCompatApi23.ServiceCompatProxy {

        /* loaded from: classes.dex */
        class a extends Result<MediaBrowserCompat.MediaItem> {
            final /* synthetic */ MediaBrowserServiceCompatApi21.c f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(h hVar, Object obj, MediaBrowserServiceCompatApi21.c cVar) {
                super(obj);
                this.f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void detach() {
                this.f.a();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void f(MediaBrowserCompat.MediaItem mediaItem) {
                Parcel obtain;
                MediaBrowserServiceCompatApi21.c cVar;
                if (mediaItem == null) {
                    cVar = this.f;
                    obtain = null;
                } else {
                    obtain = Parcel.obtain();
                    mediaItem.writeToParcel(obtain, 0);
                    cVar = this.f;
                }
                cVar.c(obtain);
            }
        }

        h() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g, androidx.media.MediaBrowserServiceCompat.f
        public void a() {
            Object a2 = MediaBrowserServiceCompatApi23.a(MediaBrowserServiceCompat.this, this);
            this.b = a2;
            MediaBrowserServiceCompatApi21.d(a2);
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi23.ServiceCompatProxy
        public void onLoadItem(String str, MediaBrowserServiceCompatApi21.c<Parcel> cVar) {
            MediaBrowserServiceCompat.this.onLoadItem(str, new a(this, str, cVar));
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    class i extends h implements MediaBrowserServiceCompatApi26.ServiceCompatProxy {

        /* loaded from: classes.dex */
        class a extends Result<List<MediaBrowserCompat.MediaItem>> {
            final /* synthetic */ MediaBrowserServiceCompatApi26.b f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(i iVar, Object obj, MediaBrowserServiceCompatApi26.b bVar) {
                super(obj);
                this.f = bVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void detach() {
                this.f.a();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h, reason: merged with bridge method [inline-methods] */
            public void f(List<MediaBrowserCompat.MediaItem> list) {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel obtain = Parcel.obtain();
                        mediaItem.writeToParcel(obtain, 0);
                        arrayList.add(obtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f.c(arrayList, b());
            }
        }

        i() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g, androidx.media.MediaBrowserServiceCompat.f
        public void a() {
            Object a2 = MediaBrowserServiceCompatApi26.a(MediaBrowserServiceCompat.this, this);
            this.b = a2;
            MediaBrowserServiceCompatApi21.d(a2);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g, androidx.media.MediaBrowserServiceCompat.f
        public Bundle d() {
            e eVar = MediaBrowserServiceCompat.this.f847c;
            if (eVar == null) {
                return MediaBrowserServiceCompatApi26.b(this.b);
            }
            if (eVar.f852c == null) {
                return null;
            }
            return new Bundle(MediaBrowserServiceCompat.this.f847c.f852c);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        void k(String str, Bundle bundle) {
            if (bundle != null) {
                MediaBrowserServiceCompatApi26.c(this.b, str, bundle);
            } else {
                super.k(str, bundle);
            }
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi26.ServiceCompatProxy
        public void onLoadChildren(String str, MediaBrowserServiceCompatApi26.b bVar, Bundle bundle) {
            MediaBrowserServiceCompat.this.onLoadChildren(str, new a(this, str, bVar), bundle);
        }
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    class j extends i {
        j() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g, androidx.media.MediaBrowserServiceCompat.f
        public MediaSessionManager.RemoteUserInfo b() {
            e eVar = MediaBrowserServiceCompat.this.f847c;
            return eVar != null ? eVar.b : new MediaSessionManager.RemoteUserInfo(((MediaBrowserService) this.b).getCurrentBrowserInfo());
        }
    }

    /* loaded from: classes.dex */
    class k implements f {
        private Messenger a;

        /* loaded from: classes.dex */
        class a implements Runnable {
            final /* synthetic */ MediaSessionCompat.Token a;

            a(MediaSessionCompat.Token token) {
                this.a = token;
            }

            @Override // java.lang.Runnable
            public void run() {
                Iterator<e> it = MediaBrowserServiceCompat.this.b.values().iterator();
                while (it.hasNext()) {
                    e next = it.next();
                    try {
                        next.f853d.b(next.f.getRootId(), this.a, next.f.getExtras());
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Connection for " + next.a + " is no longer valid.");
                        it.remove();
                    }
                }
            }
        }

        /* loaded from: classes.dex */
        class b implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ Bundle b;

            b(String str, Bundle bundle) {
                this.a = str;
                this.b = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                Iterator<IBinder> it = MediaBrowserServiceCompat.this.b.keySet().iterator();
                while (it.hasNext()) {
                    k.this.h(MediaBrowserServiceCompat.this.b.get(it.next()), this.a, this.b);
                }
            }
        }

        /* loaded from: classes.dex */
        class c implements Runnable {
            final /* synthetic */ MediaSessionManager.RemoteUserInfo a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ Bundle f860c;

            c(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle) {
                this.a = remoteUserInfo;
                this.b = str;
                this.f860c = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < MediaBrowserServiceCompat.this.b.size(); i2++) {
                    e valueAt = MediaBrowserServiceCompat.this.b.valueAt(i2);
                    if (valueAt.b.equals(this.a)) {
                        k.this.h(valueAt, this.b, this.f860c);
                        return;
                    }
                }
            }
        }

        k() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void a() {
            this.a = new Messenger(MediaBrowserServiceCompat.this.f848d);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public MediaSessionManager.RemoteUserInfo b() {
            e eVar = MediaBrowserServiceCompat.this.f847c;
            if (eVar != null) {
                return eVar.b;
            }
            throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public IBinder c(Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.a.getBinder();
            }
            return null;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public Bundle d() {
            e eVar = MediaBrowserServiceCompat.this.f847c;
            if (eVar == null) {
                throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
            }
            if (eVar.f852c == null) {
                return null;
            }
            return new Bundle(MediaBrowserServiceCompat.this.f847c.f852c);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void e(@NonNull String str, Bundle bundle) {
            MediaBrowserServiceCompat.this.f848d.post(new b(str, bundle));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void f(MediaSessionCompat.Token token) {
            MediaBrowserServiceCompat.this.f848d.post(new a(token));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void g(@NonNull MediaSessionManager.RemoteUserInfo remoteUserInfo, @NonNull String str, Bundle bundle) {
            MediaBrowserServiceCompat.this.f848d.post(new c(remoteUserInfo, str, bundle));
        }

        void h(e eVar, String str, Bundle bundle) {
            List<Pair<IBinder, Bundle>> list = eVar.e.get(str);
            if (list != null) {
                for (Pair<IBinder, Bundle> pair : list) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(bundle, pair.second)) {
                        MediaBrowserServiceCompat.this.e(str, eVar, pair.second, bundle);
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private class l {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a implements Runnable {
            final /* synthetic */ m a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f862c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f863d;
            final /* synthetic */ Bundle e;

            a(m mVar, String str, int i2, int i3, Bundle bundle) {
                this.a = mVar;
                this.b = str;
                this.f862c = i2;
                this.f863d = i3;
                this.e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder asBinder = this.a.asBinder();
                MediaBrowserServiceCompat.this.b.remove(asBinder);
                e eVar = new e(this.b, this.f862c, this.f863d, this.e, this.a);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.f847c = eVar;
                BrowserRoot onGetRoot = mediaBrowserServiceCompat.onGetRoot(this.b, this.f863d, this.e);
                eVar.f = onGetRoot;
                MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat2.f847c = null;
                if (onGetRoot != null) {
                    try {
                        mediaBrowserServiceCompat2.b.put(asBinder, eVar);
                        asBinder.linkToDeath(eVar, 0);
                        if (MediaBrowserServiceCompat.this.e != null) {
                            this.a.b(eVar.f.getRootId(), MediaBrowserServiceCompat.this.e, eVar.f.getExtras());
                            return;
                        }
                        return;
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.b);
                        MediaBrowserServiceCompat.this.b.remove(asBinder);
                        return;
                    }
                }
                Log.i("MBServiceCompat", "No root for client " + this.b + " from service " + a.class.getName());
                try {
                    this.a.a();
                } catch (RemoteException unused2) {
                    Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.b);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class b implements Runnable {
            final /* synthetic */ m a;

            b(m mVar) {
                this.a = mVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                e remove = MediaBrowserServiceCompat.this.b.remove(this.a.asBinder());
                if (remove != null) {
                    remove.f853d.asBinder().unlinkToDeath(remove, 0);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class c implements Runnable {
            final /* synthetic */ m a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ IBinder f864c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ Bundle f865d;

            c(m mVar, String str, IBinder iBinder, Bundle bundle) {
                this.a = mVar;
                this.b = str;
                this.f864c = iBinder;
                this.f865d = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.b.get(this.a.asBinder());
                if (eVar != null) {
                    MediaBrowserServiceCompat.this.a(this.b, eVar, this.f864c, this.f865d);
                    return;
                }
                Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.b);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class d implements Runnable {
            final /* synthetic */ m a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ IBinder f866c;

            d(m mVar, String str, IBinder iBinder) {
                this.a = mVar;
                this.b = str;
                this.f866c = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.b.get(this.a.asBinder());
                if (eVar == null) {
                    Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.b);
                    return;
                }
                if (MediaBrowserServiceCompat.this.h(this.b, eVar, this.f866c)) {
                    return;
                }
                Log.w("MBServiceCompat", "removeSubscription called for " + this.b + " which is not subscribed");
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class e implements Runnable {
            final /* synthetic */ m a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ ResultReceiver f868c;

            e(m mVar, String str, ResultReceiver resultReceiver) {
                this.a = mVar;
                this.b = str;
                this.f868c = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.b.get(this.a.asBinder());
                if (eVar != null) {
                    MediaBrowserServiceCompat.this.f(this.b, eVar, this.f868c);
                    return;
                }
                Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.b);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class f implements Runnable {
            final /* synthetic */ m a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f870c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ int f871d;
            final /* synthetic */ Bundle e;

            f(m mVar, String str, int i2, int i3, Bundle bundle) {
                this.a = mVar;
                this.b = str;
                this.f870c = i2;
                this.f871d = i3;
                this.e = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder asBinder = this.a.asBinder();
                MediaBrowserServiceCompat.this.b.remove(asBinder);
                e eVar = new e(this.b, this.f870c, this.f871d, this.e, this.a);
                MediaBrowserServiceCompat.this.b.put(asBinder, eVar);
                try {
                    asBinder.linkToDeath(eVar, 0);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "IBinder is already dead.");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class g implements Runnable {
            final /* synthetic */ m a;

            g(m mVar) {
                this.a = mVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder asBinder = this.a.asBinder();
                e remove = MediaBrowserServiceCompat.this.b.remove(asBinder);
                if (remove != null) {
                    asBinder.unlinkToDeath(remove, 0);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class h implements Runnable {
            final /* synthetic */ m a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ Bundle f872c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ ResultReceiver f873d;

            h(m mVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.a = mVar;
                this.b = str;
                this.f872c = bundle;
                this.f873d = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.b.get(this.a.asBinder());
                if (eVar != null) {
                    MediaBrowserServiceCompat.this.g(this.b, this.f872c, eVar, this.f873d);
                    return;
                }
                Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.b);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class i implements Runnable {
            final /* synthetic */ m a;
            final /* synthetic */ String b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ Bundle f874c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ ResultReceiver f875d;

            i(m mVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.a = mVar;
                this.b = str;
                this.f874c = bundle;
                this.f875d = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.b.get(this.a.asBinder());
                if (eVar != null) {
                    MediaBrowserServiceCompat.this.d(this.b, this.f874c, eVar, this.f875d);
                    return;
                }
                Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.b + ", extras=" + this.f874c);
            }
        }

        l() {
        }

        public void a(String str, IBinder iBinder, Bundle bundle, m mVar) {
            MediaBrowserServiceCompat.this.f848d.a(new c(mVar, str, iBinder, bundle));
        }

        public void b(String str, int i2, int i3, Bundle bundle, m mVar) {
            if (MediaBrowserServiceCompat.this.c(str, i3)) {
                MediaBrowserServiceCompat.this.f848d.a(new a(mVar, str, i2, i3, bundle));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + str);
        }

        public void c(m mVar) {
            MediaBrowserServiceCompat.this.f848d.a(new b(mVar));
        }

        public void d(String str, ResultReceiver resultReceiver, m mVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f848d.a(new e(mVar, str, resultReceiver));
        }

        public void e(m mVar, String str, int i2, int i3, Bundle bundle) {
            MediaBrowserServiceCompat.this.f848d.a(new f(mVar, str, i2, i3, bundle));
        }

        public void f(String str, IBinder iBinder, m mVar) {
            MediaBrowserServiceCompat.this.f848d.a(new d(mVar, str, iBinder));
        }

        public void g(String str, Bundle bundle, ResultReceiver resultReceiver, m mVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f848d.a(new h(mVar, str, bundle, resultReceiver));
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver, m mVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.f848d.a(new i(mVar, str, bundle, resultReceiver));
        }

        public void i(m mVar) {
            MediaBrowserServiceCompat.this.f848d.a(new g(mVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface m {
        void a();

        IBinder asBinder();

        void b(String str, MediaSessionCompat.Token token, Bundle bundle);

        void c(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2);
    }

    /* loaded from: classes.dex */
    private static class n implements m {
        final Messenger a;

        n(Messenger messenger) {
            this.a = messenger;
        }

        private void d(int i2, Bundle bundle) {
            Message obtain = Message.obtain();
            obtain.what = i2;
            obtain.arg1 = 2;
            obtain.setData(bundle);
            this.a.send(obtain);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void a() {
            d(2, null);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public IBinder asBinder() {
            return this.a.getBinder();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void b(String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_service_version", 2);
            Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", str);
            bundle2.putParcelable("data_media_session_token", token);
            bundle2.putBundle("data_root_hints", bundle);
            d(1, bundle2);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void c(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("data_media_item_id", str);
            bundle3.putBundle("data_options", bundle);
            bundle3.putBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS, bundle2);
            if (list != null) {
                bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            d(3, bundle3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class o extends Handler {
        private final l a;

        o() {
            this.a = new l();
        }

        public void a(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle);
                    this.a.b(data.getString("data_package_name"), data.getInt(MediaBrowserProtocol.DATA_CALLING_PID), data.getInt("data_calling_uid"), bundle, new n(message.replyTo));
                    return;
                case 2:
                    this.a.c(new n(message.replyTo));
                    return;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    this.a.a(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), bundle2, new n(message.replyTo));
                    return;
                case 4:
                    this.a.f(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), new n(message.replyTo));
                    return;
                case 5:
                    this.a.d(data.getString("data_media_item_id"), (ResultReceiver) data.getParcelable("data_result_receiver"), new n(message.replyTo));
                    return;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    this.a.e(new n(message.replyTo), data.getString("data_package_name"), data.getInt(MediaBrowserProtocol.DATA_CALLING_PID), data.getInt("data_calling_uid"), bundle3);
                    return;
                case 7:
                    this.a.i(new n(message.replyTo));
                    return;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.ensureClassLoader(bundle4);
                    this.a.g(data.getString("data_search_query"), bundle4, (ResultReceiver) data.getParcelable("data_result_receiver"), new n(message.replyTo));
                    return;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.ensureClassLoader(bundle5);
                    this.a.h(data.getString("data_custom_action"), bundle5, (ResultReceiver) data.getParcelable("data_result_receiver"), new n(message.replyTo));
                    return;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    return;
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            data.putInt(MediaBrowserProtocol.DATA_CALLING_PID, Binder.getCallingPid());
            return super.sendMessageAtTime(message, j);
        }
    }

    void a(String str, e eVar, IBinder iBinder, Bundle bundle) {
        List<Pair<IBinder, Bundle>> list = eVar.e.get(str);
        if (list == null) {
            list = new ArrayList<>();
        }
        for (Pair<IBinder, Bundle> pair : list) {
            if (iBinder == pair.first && MediaBrowserCompatUtils.areSameOptions(bundle, pair.second)) {
                return;
            }
        }
        list.add(new Pair<>(iBinder, bundle));
        eVar.e.put(str, list);
        e(str, eVar, bundle, null);
        this.f847c = eVar;
        onSubscribe(str, bundle);
        this.f847c = null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void attachToBaseContext(Context context) {
        attachBaseContext(context);
    }

    List<MediaBrowserCompat.MediaItem> b(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
        if (list == null) {
            return null;
        }
        int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i3 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        if (i2 == -1 && i3 == -1) {
            return list;
        }
        int i4 = i3 * i2;
        int i5 = i4 + i3;
        if (i2 < 0 || i3 < 1 || i4 >= list.size()) {
            return Collections.emptyList();
        }
        if (i5 > list.size()) {
            i5 = list.size();
        }
        return list.subList(i4, i5);
    }

    boolean c(String str, int i2) {
        if (str == null) {
            return false;
        }
        for (String str2 : getPackageManager().getPackagesForUid(i2)) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    void d(String str, Bundle bundle, e eVar, ResultReceiver resultReceiver) {
        d dVar = new d(this, str, resultReceiver);
        this.f847c = eVar;
        onCustomAction(str, bundle, dVar);
        this.f847c = null;
        if (dVar.c()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    void e(String str, e eVar, Bundle bundle, Bundle bundle2) {
        a aVar = new a(str, eVar, str, bundle, bundle2);
        this.f847c = eVar;
        if (bundle == null) {
            onLoadChildren(str, aVar);
        } else {
            onLoadChildren(str, aVar, bundle);
        }
        this.f847c = null;
        if (aVar.c()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + eVar.a + " id=" + str);
    }

    void f(String str, e eVar, ResultReceiver resultReceiver) {
        b bVar = new b(this, str, resultReceiver);
        this.f847c = eVar;
        onLoadItem(str, bVar);
        this.f847c = null;
        if (bVar.c()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    void g(String str, Bundle bundle, e eVar, ResultReceiver resultReceiver) {
        c cVar = new c(this, str, resultReceiver);
        this.f847c = eVar;
        onSearch(str, bundle, cVar);
        this.f847c = null;
        if (cVar.c()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    public final Bundle getBrowserRootHints() {
        return this.a.d();
    }

    @NonNull
    public final MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
        return this.a.b();
    }

    @Nullable
    public MediaSessionCompat.Token getSessionToken() {
        return this.e;
    }

    boolean h(String str, e eVar, IBinder iBinder) {
        boolean z = false;
        try {
            if (iBinder == null) {
                return eVar.e.remove(str) != null;
            }
            List<Pair<IBinder, Bundle>> list = eVar.e.get(str);
            if (list != null) {
                Iterator<Pair<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().first) {
                        it.remove();
                        z = true;
                    }
                }
                if (list.size() == 0) {
                    eVar.e.remove(str);
                }
            }
            return z;
        } finally {
            this.f847c = eVar;
            onUnsubscribe(str);
            this.f847c = null;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void notifyChildrenChanged(@NonNull MediaSessionManager.RemoteUserInfo remoteUserInfo, @NonNull String str, @NonNull Bundle bundle) {
        if (remoteUserInfo == null) {
            throw new IllegalArgumentException("remoteUserInfo cannot be null in notifyChildrenChanged");
        }
        if (str == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        }
        this.a.g(remoteUserInfo, str, bundle);
    }

    public void notifyChildrenChanged(@NonNull String str) {
        if (str == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        this.a.e(str, null);
    }

    public void notifyChildrenChanged(@NonNull String str, @NonNull Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        }
        this.a.e(str, bundle);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.a.c(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i2 = Build.VERSION.SDK_INT;
        this.a = i2 >= 28 ? new j() : i2 >= 26 ? new i() : i2 >= 23 ? new h() : i2 >= 21 ? new g() : new k();
        this.a.a();
    }

    public void onCustomAction(@NonNull String str, Bundle bundle, @NonNull Result<Bundle> result) {
        result.sendError(null);
    }

    @Nullable
    public abstract BrowserRoot onGetRoot(@NonNull String str, int i2, @Nullable Bundle bundle);

    public abstract void onLoadChildren(@NonNull String str, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result);

    public void onLoadChildren(@NonNull String str, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result, @NonNull Bundle bundle) {
        result.g(1);
        onLoadChildren(str, result);
    }

    public void onLoadItem(String str, @NonNull Result<MediaBrowserCompat.MediaItem> result) {
        result.g(2);
        result.sendResult(null);
    }

    public void onSearch(@NonNull String str, Bundle bundle, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.g(4);
        result.sendResult(null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onSubscribe(String str, Bundle bundle) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onUnsubscribe(String str) {
    }

    public void setSessionToken(MediaSessionCompat.Token token) {
        if (token == null) {
            throw new IllegalArgumentException("Session token may not be null.");
        }
        if (this.e != null) {
            throw new IllegalStateException("The session token has already been set.");
        }
        this.e = token;
        this.a.f(token);
    }
}
