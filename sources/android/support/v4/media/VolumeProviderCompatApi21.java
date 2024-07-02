package android.support.v4.media;

import android.media.VolumeProvider;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
/* loaded from: lib/Mus.dex */
class VolumeProviderCompatApi21 {

    /* loaded from: lib/Mus.dex */
    public interface Delegate {
        void onAdjustVolume(int i2);

        void onSetVolumeTo(int i2);
    }

    VolumeProviderCompatApi21() {
    }

    public static Object createVolumeProvider(int i2, int i3, int i4, final Delegate delegate) {
        return new VolumeProvider(i2, i3, i4) { // from class: android.support.v4.media.VolumeProviderCompatApi21.1
            @Override // android.media.VolumeProvider
            public void onSetVolumeTo(int i5) {
                delegate.onSetVolumeTo(i5);
            }

            @Override // android.media.VolumeProvider
            public void onAdjustVolume(int i5) {
                delegate.onAdjustVolume(i5);
            }
        };
    }

    public static void setCurrentVolume(Object obj, int i2) {
        ((VolumeProvider) obj).setCurrentVolume(i2);
    }
}
