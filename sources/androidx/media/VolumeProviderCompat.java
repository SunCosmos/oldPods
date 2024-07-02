package androidx.media;

import android.os.Build;
import androidx.annotation.RestrictTo;
import androidx.media.VolumeProviderCompatApi21;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private final int a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private int f878c;

    /* renamed from: d, reason: collision with root package name */
    private Callback f879d;
    private Object e;

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat volumeProviderCompat);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ControlType {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements VolumeProviderCompatApi21.Delegate {
        a() {
        }

        @Override // androidx.media.VolumeProviderCompatApi21.Delegate
        public void onAdjustVolume(int i2) {
            VolumeProviderCompat.this.onAdjustVolume(i2);
        }

        @Override // androidx.media.VolumeProviderCompatApi21.Delegate
        public void onSetVolumeTo(int i2) {
            VolumeProviderCompat.this.onSetVolumeTo(i2);
        }
    }

    public VolumeProviderCompat(int i2, int i3, int i4) {
        this.a = i2;
        this.b = i3;
        this.f878c = i4;
    }

    public final int getCurrentVolume() {
        return this.f878c;
    }

    public final int getMaxVolume() {
        return this.b;
    }

    public final int getVolumeControl() {
        return this.a;
    }

    public Object getVolumeProvider() {
        if (this.e == null && Build.VERSION.SDK_INT >= 21) {
            this.e = VolumeProviderCompatApi21.a(this.a, this.b, this.f878c, new a());
        }
        return this.e;
    }

    public void onAdjustVolume(int i2) {
    }

    public void onSetVolumeTo(int i2) {
    }

    public void setCallback(Callback callback) {
        this.f879d = callback;
    }

    public final void setCurrentVolume(int i2) {
        this.f878c = i2;
        Object volumeProvider = getVolumeProvider();
        if (volumeProvider != null && Build.VERSION.SDK_INT >= 21) {
            VolumeProviderCompatApi21.b(volumeProvider, i2);
        }
        Callback callback = this.f879d;
        if (callback != null) {
            callback.onVolumeChanged(this);
        }
    }
}
