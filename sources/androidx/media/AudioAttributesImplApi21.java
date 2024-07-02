package androidx.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TargetApi(21)
/* loaded from: classes.dex */
class AudioAttributesImplApi21 implements AudioAttributesImpl {

    /* renamed from: c */
    static Method f844c;
    AudioAttributes a;
    int b;

    public AudioAttributesImplApi21() {
        this.b = -1;
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    public AudioAttributesImplApi21(AudioAttributes audioAttributes, int i2) {
        this.b = -1;
        this.a = audioAttributes;
        this.b = i2;
    }

    public static AudioAttributesImpl c(Bundle bundle) {
        AudioAttributes audioAttributes;
        if (bundle == null || (audioAttributes = (AudioAttributes) bundle.getParcelable("androidx.media.audio_attrs.FRAMEWORKS")) == null) {
            return null;
        }
        return new AudioAttributesImplApi21(audioAttributes, bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }

    static Method d() {
        try {
            if (f844c == null) {
                f844c = AudioAttributes.class.getMethod("toLegacyStreamType", AudioAttributes.class);
            }
            return f844c;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // androidx.media.AudioAttributesImpl
    public int a() {
        return this.b;
    }

    @Override // androidx.media.AudioAttributesImpl
    public Object b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.a.equals(((AudioAttributesImplApi21) obj).a);
        }
        return false;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.a.getContentType();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        return this.a.getFlags();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i2 = Build.VERSION.SDK_INT;
        int i3 = this.b;
        if (i3 != -1) {
            return i3;
        }
        Method d2 = d();
        if (d2 == null) {
            Log.w("AudioAttributesCompat21", "No AudioAttributes#toLegacyStreamType() on API: " + i2);
            return -1;
        }
        try {
            return ((Integer) d2.invoke(null, this.a)).intValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.w("AudioAttributesCompat21", "getLegacyStreamType() failed on API: " + i2, e);
            return -1;
        }
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.a.getUsage();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return Build.VERSION.SDK_INT >= 26 ? this.a.getVolumeControlStream() : AudioAttributesCompat.c(true, getFlags(), getUsage());
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // androidx.media.AudioAttributesImpl
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("androidx.media.audio_attrs.FRAMEWORKS", this.a);
        int i2 = this.b;
        if (i2 != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i2);
        }
        return bundle;
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.a;
    }
}
