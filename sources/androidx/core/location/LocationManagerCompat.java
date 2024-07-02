package androidx.core.location;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.Preconditions;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
public final class LocationManagerCompat {
    private static Field a;

    @GuardedBy("sGnssStatusListeners")
    private static final SimpleArrayMap<Object, Object> b = new SimpleArrayMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Callable<Boolean> {
        final /* synthetic */ LocationManager a;
        final /* synthetic */ d b;

        a(LocationManager locationManager, d dVar) {
            this.a = locationManager;
            this.b = dVar;
        }

        @Override // java.util.concurrent.Callable
        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            return Boolean.valueOf(this.a.addGpsStatusListener(this.b));
        }
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    private static class b {
        public static boolean a(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class c extends GnssStatus.Callback {
        final GnssStatusCompat.Callback a;

        c(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.a = callback;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i2) {
            this.a.onFirstFix(i2);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            this.a.onSatelliteStatusChanged(GnssStatusCompat.wrap(gnssStatus));
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            this.a.onStarted();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            this.a.onStopped();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d implements GpsStatus.Listener {
        private final LocationManager a;
        final GnssStatusCompat.Callback b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        volatile Executor f557c;

        /* loaded from: classes.dex */
        class a implements Runnable {
            final /* synthetic */ Executor a;

            a(Executor executor) {
                this.a = executor;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (d.this.f557c != this.a) {
                    return;
                }
                d.this.b.onStarted();
            }
        }

        /* loaded from: classes.dex */
        class b implements Runnable {
            final /* synthetic */ Executor a;

            b(Executor executor) {
                this.a = executor;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (d.this.f557c != this.a) {
                    return;
                }
                d.this.b.onStopped();
            }
        }

        /* loaded from: classes.dex */
        class c implements Runnable {
            final /* synthetic */ Executor a;
            final /* synthetic */ int b;

            c(Executor executor, int i2) {
                this.a = executor;
                this.b = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (d.this.f557c != this.a) {
                    return;
                }
                d.this.b.onFirstFix(this.b);
            }
        }

        /* renamed from: androidx.core.location.LocationManagerCompat$d$d, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0015d implements Runnable {
            final /* synthetic */ Executor a;
            final /* synthetic */ GnssStatusCompat b;

            RunnableC0015d(Executor executor, GnssStatusCompat gnssStatusCompat) {
                this.a = executor;
                this.b = gnssStatusCompat;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (d.this.f557c != this.a) {
                    return;
                }
                d.this.b.onSatelliteStatusChanged(this.b);
            }
        }

        d(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.a = locationManager;
            this.b = callback;
        }

        public void a(Executor executor) {
            Preconditions.checkState(this.f557c == null);
            this.f557c = executor;
        }

        public void b() {
            this.f557c = null;
        }

        @Override // android.location.GpsStatus.Listener
        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public void onGpsStatusChanged(int i2) {
            Runnable aVar;
            Runnable cVar;
            GpsStatus gpsStatus;
            Executor executor = this.f557c;
            if (executor == null) {
                return;
            }
            if (i2 == 1) {
                aVar = new a(executor);
            } else {
                if (i2 != 2) {
                    if (i2 == 3) {
                        GpsStatus gpsStatus2 = this.a.getGpsStatus(null);
                        if (gpsStatus2 == null) {
                            return;
                        } else {
                            cVar = new c(executor, gpsStatus2.getTimeToFirstFix());
                        }
                    } else if (i2 != 4 || (gpsStatus = this.a.getGpsStatus(null)) == null) {
                        return;
                    } else {
                        cVar = new RunnableC0015d(executor, GnssStatusCompat.wrap(gpsStatus));
                    }
                    executor.execute(cVar);
                    return;
                }
                aVar = new b(executor);
            }
            executor.execute(aVar);
        }
    }

    /* loaded from: classes.dex */
    private static class e implements Executor {
        private final Handler a;

        e(@NonNull Handler handler) {
            this.a = (Handler) Preconditions.checkNotNull(handler);
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            if (Looper.myLooper() == this.a.getLooper()) {
                runnable.run();
            } else {
                if (this.a.post((Runnable) Preconditions.checkNotNull(runnable))) {
                    return;
                }
                throw new RejectedExecutionException(this.a + " is shutting down");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class f extends GnssStatus.Callback {
        final GnssStatusCompat.Callback a;

        @Nullable
        volatile Executor b;

        /* loaded from: classes.dex */
        class a implements Runnable {
            final /* synthetic */ Executor a;

            a(Executor executor) {
                this.a = executor;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (f.this.b != this.a) {
                    return;
                }
                f.this.a.onStarted();
            }
        }

        /* loaded from: classes.dex */
        class b implements Runnable {
            final /* synthetic */ Executor a;

            b(Executor executor) {
                this.a = executor;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (f.this.b != this.a) {
                    return;
                }
                f.this.a.onStopped();
            }
        }

        /* loaded from: classes.dex */
        class c implements Runnable {
            final /* synthetic */ Executor a;
            final /* synthetic */ int b;

            c(Executor executor, int i2) {
                this.a = executor;
                this.b = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (f.this.b != this.a) {
                    return;
                }
                f.this.a.onFirstFix(this.b);
            }
        }

        /* loaded from: classes.dex */
        class d implements Runnable {
            final /* synthetic */ Executor a;
            final /* synthetic */ GnssStatus b;

            d(Executor executor, GnssStatus gnssStatus) {
                this.a = executor;
                this.b = gnssStatus;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (f.this.b != this.a) {
                    return;
                }
                f.this.a.onSatelliteStatusChanged(GnssStatusCompat.wrap(this.b));
            }
        }

        f(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.a = callback;
        }

        public void a(Executor executor) {
            Preconditions.checkArgument(executor != null, "invalid null executor");
            Preconditions.checkState(this.b == null);
            this.b = executor;
        }

        public void b() {
            this.b = null;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i2) {
            Executor executor = this.b;
            if (executor == null) {
                return;
            }
            executor.execute(new c(executor, i2));
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            Executor executor = this.b;
            if (executor == null) {
                return;
            }
            executor.execute(new d(executor, gnssStatus));
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            Executor executor = this.b;
            if (executor == null) {
                return;
            }
            executor.execute(new a(executor));
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            Executor executor = this.b;
            if (executor == null) {
                return;
            }
            executor.execute(new b(executor));
        }
    }

    private LocationManagerCompat() {
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x010a A[Catch: all -> 0x0126, TryCatch #2 {all -> 0x0126, blocks: (B:88:0x00e9, B:89:0x00ff, B:76:0x0102, B:78:0x010a, B:80:0x0112, B:81:0x0118, B:82:0x0119, B:83:0x011e, B:84:0x011f, B:85:0x0125, B:71:0x00d8), top: B:53:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x011f A[Catch: all -> 0x0126, TryCatch #2 {all -> 0x0126, blocks: (B:88:0x00e9, B:89:0x00ff, B:76:0x0102, B:78:0x010a, B:80:0x0112, B:81:0x0118, B:82:0x0119, B:83:0x011e, B:84:0x011f, B:85:0x0125, B:71:0x00d8), top: B:53:0x0098 }] */
    @androidx.annotation.RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(android.location.LocationManager r9, android.os.Handler r10, java.util.concurrent.Executor r11, androidx.core.location.GnssStatusCompat.Callback r12) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.location.LocationManagerCompat.a(android.location.LocationManager, android.os.Handler, java.util.concurrent.Executor, androidx.core.location.GnssStatusCompat$Callback):boolean");
    }

    public static boolean isLocationEnabled(@NonNull LocationManager locationManager) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            return b.a(locationManager);
        }
        if (i2 <= 19) {
            try {
                if (a == null) {
                    a = LocationManager.class.getDeclaredField("mContext");
                }
                a.setAccessible(true);
                return i2 == 19 ? Settings.Secure.getInt(((Context) a.get(locationManager)).getContentResolver(), "location_mode", 0) != 0 : !TextUtils.isEmpty(Settings.Secure.getString(r4.getContentResolver(), "location_providers_allowed"));
            } catch (ClassCastException | IllegalAccessException | NoSuchFieldException | SecurityException unused) {
            }
        }
        return locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps");
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback, @NonNull Handler handler) {
        return Build.VERSION.SDK_INT >= 30 ? registerGnssStatusCallback(locationManager, ExecutorCompat.create(handler), callback) : registerGnssStatusCallback(locationManager, new e(handler), callback);
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return a(locationManager, null, executor, callback);
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            myLooper = Looper.getMainLooper();
        }
        return a(locationManager, new Handler(myLooper), executor, callback);
    }

    public static void unregisterGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            SimpleArrayMap<Object, Object> simpleArrayMap = b;
            synchronized (simpleArrayMap) {
                GnssStatus.Callback callback2 = (c) simpleArrayMap.remove(callback);
                if (callback2 != null) {
                    locationManager.unregisterGnssStatusCallback(callback2);
                }
            }
            return;
        }
        if (i2 >= 24) {
            SimpleArrayMap<Object, Object> simpleArrayMap2 = b;
            synchronized (simpleArrayMap2) {
                f fVar = (f) simpleArrayMap2.remove(callback);
                if (fVar != null) {
                    fVar.b();
                    locationManager.unregisterGnssStatusCallback(fVar);
                }
            }
            return;
        }
        SimpleArrayMap<Object, Object> simpleArrayMap3 = b;
        synchronized (simpleArrayMap3) {
            d dVar = (d) simpleArrayMap3.remove(callback);
            if (dVar != null) {
                dVar.b();
                locationManager.removeGpsStatusListener(dVar);
            }
        }
    }
}
