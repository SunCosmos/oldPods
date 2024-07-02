package androidx.media;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.Arrays;

/* loaded from: classes.dex */
class AudioAttributesImplBase implements AudioAttributesImpl {
    int a;
    int b;

    /* renamed from: c, reason: collision with root package name */
    int f845c;

    /* renamed from: d, reason: collision with root package name */
    int f846d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplBase() {
        this.a = 0;
        this.b = 0;
        this.f845c = 0;
        this.f846d = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplBase(int i2, int i3, int i4, int i5) {
        this.a = 0;
        this.b = 0;
        this.f845c = 0;
        this.f846d = -1;
        this.b = i2;
        this.f845c = i3;
        this.a = i4;
        this.f846d = i5;
    }

    public static AudioAttributesImpl c(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new AudioAttributesImplBase(bundle.getInt("androidx.media.audio_attrs.CONTENT_TYPE", 0), bundle.getInt("androidx.media.audio_attrs.FLAGS", 0), bundle.getInt("androidx.media.audio_attrs.USAGE", 0), bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }

    @Override // androidx.media.AudioAttributesImpl
    public int a() {
        return this.f846d;
    }

    @Override // androidx.media.AudioAttributesImpl
    public Object b() {
        return null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        return this.b == audioAttributesImplBase.getContentType() && this.f845c == audioAttributesImplBase.getFlags() && this.a == audioAttributesImplBase.getUsage() && this.f846d == audioAttributesImplBase.f846d;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.b;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        int i2 = this.f845c;
        int legacyStreamType = getLegacyStreamType();
        if (legacyStreamType == 6) {
            i2 |= 4;
        } else if (legacyStreamType == 7) {
            i2 |= 1;
        }
        return i2 & 273;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i2 = this.f846d;
        return i2 != -1 ? i2 : AudioAttributesCompat.c(false, this.f845c, this.a);
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.a;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return AudioAttributesCompat.c(true, this.f845c, this.a);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.f845c), Integer.valueOf(this.a), Integer.valueOf(this.f846d)});
    }

    @Override // androidx.media.AudioAttributesImpl
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("androidx.media.audio_attrs.USAGE", this.a);
        bundle.putInt("androidx.media.audio_attrs.CONTENT_TYPE", this.b);
        bundle.putInt("androidx.media.audio_attrs.FLAGS", this.f845c);
        int i2 = this.f846d;
        if (i2 != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i2);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f846d != -1) {
            sb.append(" stream=");
            sb.append(this.f846d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.e(this.a));
        sb.append(" content=");
        sb.append(this.b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f845c).toUpperCase());
        return sb.toString();
    }
}
