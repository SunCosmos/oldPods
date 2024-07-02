package android.support.v7.app;

/* loaded from: lib/Mus.dex */
class TwilightCalculator {
    private static final float ALTIDUTE_CORRECTION_CIVIL_TWILIGHT = -0.10471976f;
    private static final float C1 = 0.0334196f;
    private static final float C2 = 3.49066E-4f;
    private static final float C3 = 5.236E-6f;
    public static final int DAY = 0;
    private static final float DEGREES_TO_RADIANS = 0.017453292f;
    private static final float J0 = 9.0E-4f;
    public static final int NIGHT = 1;
    private static final float OBLIQUITY = 0.4092797f;
    private static final long UTC_2000 = 946728000000L;
    private static TwilightCalculator sInstance;
    public int state;
    public long sunrise;
    public long sunset;

    TwilightCalculator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TwilightCalculator getInstance() {
        if (sInstance == null) {
            sInstance = new TwilightCalculator();
        }
        return sInstance;
    }

    public void calculateTwilight(long j, double d2, double d3) {
        float f = 6.24006f + ((((float) (j - UTC_2000)) / 8.64E7f) * 0.01720197f);
        double sin = f + (0.03341960161924362d * Math.sin(f)) + (3.4906598739326E-4d * Math.sin(2.0f * f)) + (5.236000106378924E-6d * Math.sin(3.0f * f)) + 1.796593063d + 3.141592653589793d;
        double round = ((float) Math.round((r28 - J0) - r28)) + J0 + ((-d3) / 360.0d) + (0.0053d * Math.sin(f)) + ((-0.0069d) * Math.sin(2.0d * sin));
        double asin = Math.asin(Math.sin(sin) * Math.sin(0.4092797040939331d));
        double d4 = d2 * 0.01745329238474369d;
        double sin2 = (Math.sin(-0.10471975803375244d) - (Math.sin(d4) * Math.sin(asin))) / (Math.cos(d4) * Math.cos(asin));
        if (sin2 >= 1.0d) {
            this.state = 1;
            this.sunset = -1L;
            this.sunrise = -1L;
        } else {
            if (sin2 <= -1.0d) {
                this.state = 0;
                this.sunset = -1L;
                this.sunrise = -1L;
                return;
            }
            float acos = (float) (Math.acos(sin2) / 6.283185307179586d);
            this.sunset = Math.round((round + acos) * 8.64E7d) + UTC_2000;
            this.sunrise = Math.round((round - acos) * 8.64E7d) + UTC_2000;
            if (this.sunrise < j && this.sunset > j) {
                this.state = 0;
            } else {
                this.state = 1;
            }
        }
    }
}
