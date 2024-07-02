package android.support.v4.view;

import android.view.VelocityTracker;

@Deprecated
/* loaded from: lib/Mus.dex */
public final class VelocityTrackerCompat {
    @Deprecated
    public static float getXVelocity(VelocityTracker velocityTracker, int i2) {
        return velocityTracker.getXVelocity(i2);
    }

    @Deprecated
    public static float getYVelocity(VelocityTracker velocityTracker, int i2) {
        return velocityTracker.getYVelocity(i2);
    }

    private VelocityTrackerCompat() {
    }
}
