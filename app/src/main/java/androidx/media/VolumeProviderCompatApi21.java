package androidx.media;

import android.media.VolumeProvider;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* loaded from: classes.dex */
class VolumeProviderCompatApi21 {

    /* loaded from: classes.dex */
    public interface Delegate {
        void onAdjustVolume(int i2);

        void onSetVolumeTo(int i2);
    }

    /* loaded from: classes.dex */
    static class a extends VolumeProvider {
        final /* synthetic */ Delegate a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i2, int i3, int i4, Delegate delegate) {
            super(i2, i3, i4);
            this.a = delegate;
        }

        @Override // android.media.VolumeProvider
        public void onAdjustVolume(int i2) {
            this.a.onAdjustVolume(i2);
        }

        @Override // android.media.VolumeProvider
        public void onSetVolumeTo(int i2) {
            this.a.onSetVolumeTo(i2);
        }
    }

    public static Object a(int i2, int i3, int i4, Delegate delegate) {
        return new a(i2, i3, i4, delegate);
    }

    public static void b(Object obj, int i2) {
        ((VolumeProvider) obj).setCurrentVolume(i2);
    }
}
