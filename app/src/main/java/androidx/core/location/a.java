package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;

@RequiresApi(24)
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class a extends GnssStatusCompat {
    private final GnssStatus a;

    public a(GnssStatus gnssStatus) {
        this.a = (GnssStatus) Preconditions.checkNotNull(gnssStatus);
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

    @Override // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int i2) {
        return this.a.getAzimuthDegrees(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int i2) {
        if (Build.VERSION.SDK_INT >= 30) {
            return this.a.getBasebandCn0DbHz(i2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.a.getCarrierFrequencyHz(i2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int i2) {
        return this.a.getCn0DbHz(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int i2) {
        return this.a.getConstellationType(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int i2) {
        return this.a.getElevationDegrees(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        return this.a.getSatelliteCount();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSvid(int i2) {
        return this.a.getSvid(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int i2) {
        return this.a.hasAlmanacData(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int i2) {
        if (Build.VERSION.SDK_INT >= 30) {
            return this.a.hasBasebandCn0DbHz(i2);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.a.hasCarrierFrequencyHz(i2);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int i2) {
        return this.a.hasEphemerisData(i2);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int i2) {
        return this.a.usedInFix(i2);
    }
}
