package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.PermissionChecker;
import java.util.Calendar;

/* loaded from: classes.dex */
class g {

    /* renamed from: d, reason: collision with root package name */
    private static g f61d;
    private final Context a;
    private final LocationManager b;

    /* renamed from: c, reason: collision with root package name */
    private final a f62c = new a();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a {
        boolean a;
        long b;

        /* renamed from: c, reason: collision with root package name */
        long f63c;

        /* renamed from: d, reason: collision with root package name */
        long f64d;
        long e;
        long f;

        a() {
        }
    }

    @VisibleForTesting
    g(@NonNull Context context, @NonNull LocationManager locationManager) {
        this.a = context;
        this.b = locationManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g a(@NonNull Context context) {
        if (f61d == null) {
            Context applicationContext = context.getApplicationContext();
            f61d = new g(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f61d;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location c2 = PermissionChecker.checkSelfPermission(this.a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location c3 = PermissionChecker.checkSelfPermission(this.a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (c3 == null || c2 == null) ? c3 != null ? c3 : c2 : c3.getTime() > c2.getTime() ? c3 : c2;
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    private Location c(String str) {
        try {
            if (this.b.isProviderEnabled(str)) {
                return this.b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    private boolean e() {
        return this.f62c.f > System.currentTimeMillis();
    }

    private void f(@NonNull Location location) {
        long j;
        a aVar = this.f62c;
        long currentTimeMillis = System.currentTimeMillis();
        f b = f.b();
        b.a(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = b.a;
        b.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = b.f60c == 1;
        long j3 = b.b;
        long j4 = b.a;
        boolean z2 = z;
        b.a(86400000 + currentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = b.b;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            j = (currentTimeMillis > j4 ? 0 + j5 : currentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.a = z2;
        aVar.b = j2;
        aVar.f63c = j3;
        aVar.f64d = j4;
        aVar.e = j5;
        aVar.f = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        a aVar = this.f62c;
        if (e()) {
            return aVar.a;
        }
        Location b = b();
        if (b != null) {
            f(b);
            return aVar.a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i2 = Calendar.getInstance().get(11);
        return i2 < 6 || i2 >= 22;
    }
}
