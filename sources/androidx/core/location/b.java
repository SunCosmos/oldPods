package androidx.core.location;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.os.Build;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class b extends GnssStatusCompat {
    private final GpsStatus a;

    @GuardedBy("mWrapped")
    private int b;

    /* renamed from: c, reason: collision with root package name */
    @GuardedBy("mWrapped")
    private Iterator<GpsSatellite> f562c;

    /* renamed from: d, reason: collision with root package name */
    @GuardedBy("mWrapped")
    private int f563d;

    @GuardedBy("mWrapped")
    private GpsSatellite e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(GpsStatus gpsStatus) {
        GpsStatus gpsStatus2 = (GpsStatus) Preconditions.checkNotNull(gpsStatus);
        this.a = gpsStatus2;
        this.b = -1;
        this.f562c = gpsStatus2.getSatellites().iterator();
        this.f563d = -1;
        this.e = null;
    }

    private static int a(int i2) {
        if (i2 > 0 && i2 <= 32) {
            return 1;
        }
        if (i2 >= 33 && i2 <= 64) {
            return 2;
        }
        if (i2 > 64 && i2 <= 88) {
            return 3;
        }
        if (i2 <= 200 || i2 > 235) {
            return (i2 < 193 || i2 > 200) ? 0 : 4;
        }
        return 5;
    }

    private GpsSatellite b(int i2) {
        GpsSatellite gpsSatellite;
        synchronized (this.a) {
            if (i2 < this.f563d) {
                this.f562c = this.a.getSatellites().iterator();
                this.f563d = -1;
            }
            while (true) {
                int i3 = this.f563d;
                if (i3 >= i2) {
                    break;
                }
                this.f563d = i3 + 1;
                if (!this.f562c.hasNext()) {
                    this.e = null;
                    break;
                }
                this.e = this.f562c.next();
            }
            gpsSatellite = this.e;
        }
        return (GpsSatellite) Preconditions.checkNotNull(gpsSatellite);
    }

    private static int c(int i2) {
        int a = a(i2);
        return a != 2 ? a != 3 ? a != 5 ? i2 : i2 - 200 : i2 - 64 : i2 + 87;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            return this.a.equals(((b) obj).a);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int i2) {
        return b(i2).getAzimuth();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int i2) {
        return b(i2).getSnr();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int i2) {
        if (Build.VERSION.SDK_INT < 24) {
            return 1;
        }
        return a(b(i2).getPrn());
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int i2) {
        return b(i2).getElevation();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        int i2;
        synchronized (this.a) {
            if (this.b == -1) {
                for (GpsSatellite gpsSatellite : this.a.getSatellites()) {
                    this.b++;
                }
                this.b++;
            }
            i2 = this.b;
        }
        return i2;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSvid(int i2) {
        int i3 = Build.VERSION.SDK_INT;
        int prn = b(i2).getPrn();
        return i3 < 24 ? prn : c(prn);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int i2) {
        return b(i2).hasAlmanac();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int i2) {
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int i2) {
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int i2) {
        return b(i2).hasEphemeris();
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int i2) {
        return b(i2).usedInFix();
    }
}
