package androidx.core.location;

import android.annotation.SuppressLint;
import android.location.GnssStatus;
import android.location.GpsStatus;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class GnssStatusCompat {

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_BEIDOU = 5;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_GALILEO = 6;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_GLONASS = 3;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_GPS = 1;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_IRNSS = 7;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_QZSS = 4;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_SBAS = 2;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_UNKNOWN = 0;

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public void onFirstFix(@IntRange(from = 0) int i2) {
        }

        public void onSatelliteStatusChanged(@NonNull GnssStatusCompat gnssStatusCompat) {
        }

        public void onStarted() {
        }

        public void onStopped() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface ConstellationType {
    }

    @NonNull
    @RequiresApi(24)
    public static GnssStatusCompat wrap(@NonNull GnssStatus gnssStatus) {
        return new a(gnssStatus);
    }

    @NonNull
    @SuppressLint({"ReferencesDeprecated"})
    public static GnssStatusCompat wrap(@NonNull GpsStatus gpsStatus) {
        return new b(gpsStatus);
    }

    @FloatRange(from = 0.0d, to = 360.0d)
    public abstract float getAzimuthDegrees(@IntRange(from = 0) int i2);

    @FloatRange(from = 0.0d, to = 63.0d)
    public abstract float getBasebandCn0DbHz(@IntRange(from = 0) int i2);

    @FloatRange(from = 0.0d)
    public abstract float getCarrierFrequencyHz(@IntRange(from = 0) int i2);

    @FloatRange(from = 0.0d, to = 63.0d)
    public abstract float getCn0DbHz(@IntRange(from = 0) int i2);

    public abstract int getConstellationType(@IntRange(from = 0) int i2);

    @FloatRange(from = -90.0d, to = 90.0d)
    public abstract float getElevationDegrees(@IntRange(from = 0) int i2);

    @IntRange(from = 0)
    public abstract int getSatelliteCount();

    @IntRange(from = 1, to = 200)
    public abstract int getSvid(@IntRange(from = 0) int i2);

    public abstract boolean hasAlmanacData(@IntRange(from = 0) int i2);

    public abstract boolean hasBasebandCn0DbHz(@IntRange(from = 0) int i2);

    public abstract boolean hasCarrierFrequencyHz(@IntRange(from = 0) int i2);

    public abstract boolean hasEphemerisData(@IntRange(from = 0) int i2);

    public abstract boolean usedInFix(@IntRange(from = 0) int i2);
}
