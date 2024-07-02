package androidx.media;

import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class AudioAttributesCompat implements VersionedParcelable {
    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    public static final int FLAG_HW_AV_SYNC = 16;
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_UNKNOWN = 0;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    private static final SparseIntArray b;

    /* renamed from: c, reason: collision with root package name */
    static boolean f841c;
    AudioAttributesImpl a;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface AttributeContentType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface AttributeUsage {
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private int a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private int f842c;

        /* renamed from: d, reason: collision with root package name */
        private int f843d;

        public Builder() {
            this.a = 0;
            this.b = 0;
            this.f842c = 0;
            this.f843d = -1;
        }

        public Builder(AudioAttributesCompat audioAttributesCompat) {
            this.a = 0;
            this.b = 0;
            this.f842c = 0;
            this.f843d = -1;
            this.a = audioAttributesCompat.getUsage();
            this.b = audioAttributesCompat.getContentType();
            this.f842c = audioAttributesCompat.getFlags();
            this.f843d = audioAttributesCompat.a();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        Builder a(int i2) {
            int i3 = 1;
            switch (i2) {
                case 0:
                case 10:
                    this.b = i3;
                    break;
                case 1:
                case 2:
                case 4:
                case 5:
                case 8:
                case 9:
                    this.b = 4;
                    break;
                case 3:
                    i3 = 2;
                    this.b = i3;
                    break;
                case 6:
                    this.b = 1;
                    this.f842c |= 4;
                    break;
                case 7:
                    this.f842c = 1 | this.f842c;
                    this.b = 4;
                    break;
                default:
                    Log.e("AudioAttributesCompat", "Invalid stream type " + i2 + " for AudioAttributesCompat");
                    break;
            }
            this.a = AudioAttributesCompat.d(i2);
            return this;
        }

        public AudioAttributesCompat build() {
            AudioAttributesImpl audioAttributesImplBase;
            if (AudioAttributesCompat.f841c || Build.VERSION.SDK_INT < 21) {
                audioAttributesImplBase = new AudioAttributesImplBase(this.b, this.f842c, this.a, this.f843d);
            } else {
                AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(this.b).setFlags(this.f842c).setUsage(this.a);
                int i2 = this.f843d;
                if (i2 != -1) {
                    usage.setLegacyStreamType(i2);
                }
                audioAttributesImplBase = new AudioAttributesImplApi21(usage.build(), this.f843d);
            }
            return new AudioAttributesCompat(audioAttributesImplBase);
        }

        public Builder setContentType(int i2) {
            if (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
                this.b = i2;
            } else {
                this.a = 0;
            }
            return this;
        }

        public Builder setFlags(int i2) {
            this.f842c = (i2 & 1023) | this.f842c;
            return this;
        }

        public Builder setLegacyStreamType(int i2) {
            if (i2 == 10) {
                throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
            }
            this.f843d = i2;
            if (Build.VERSION.SDK_INT >= 21) {
                a(i2);
            }
            return this;
        }

        public Builder setUsage(int i2) {
            switch (i2) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    break;
                case 16:
                    if (AudioAttributesCompat.f841c || Build.VERSION.SDK_INT <= 25) {
                        i2 = 12;
                        break;
                    }
                    break;
                default:
                    i2 = 0;
                    break;
            }
            this.a = i2;
            return this;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        b = sparseIntArray;
        sparseIntArray.put(5, 1);
        sparseIntArray.put(6, 2);
        sparseIntArray.put(7, 2);
        sparseIntArray.put(8, 1);
        sparseIntArray.put(9, 1);
        sparseIntArray.put(10, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesCompat() {
    }

    AudioAttributesCompat(AudioAttributesImpl audioAttributesImpl) {
        this.a = audioAttributesImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(boolean z, int i2, int i3) {
        if ((i2 & 1) == 1) {
            return z ? 1 : 7;
        }
        if ((i2 & 4) == 4) {
            return z ? 0 : 6;
        }
        switch (i3) {
            case 0:
                return z ? Integer.MIN_VALUE : 3;
            case 1:
            case 12:
            case 14:
            case 16:
                return 3;
            case 2:
                return 0;
            case 3:
                return z ? 0 : 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            case 11:
                return 10;
            case 13:
                return 1;
            case 15:
            default:
                if (!z) {
                    return 3;
                }
                throw new IllegalArgumentException("Unknown usage value " + i3 + " in audio attributes");
        }
    }

    static int d(int i2) {
        switch (i2) {
            case 0:
                return 2;
            case 1:
            case 7:
                return 13;
            case 2:
                return 6;
            case 3:
                return 1;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 2;
            case 8:
                return 3;
            case 9:
            default:
                return 0;
            case 10:
                return 11;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e(int i2) {
        switch (i2) {
            case 0:
                return "USAGE_UNKNOWN";
            case 1:
                return "USAGE_MEDIA";
            case 2:
                return "USAGE_VOICE_COMMUNICATION";
            case 3:
                return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
            case 4:
                return "USAGE_ALARM";
            case 5:
                return "USAGE_NOTIFICATION";
            case 6:
                return "USAGE_NOTIFICATION_RINGTONE";
            case 7:
                return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
            case 8:
                return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
            case 9:
                return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
            case 10:
                return "USAGE_NOTIFICATION_EVENT";
            case 11:
                return "USAGE_ASSISTANCE_ACCESSIBILITY";
            case 12:
                return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
            case 13:
                return "USAGE_ASSISTANCE_SONIFICATION";
            case 14:
                return "USAGE_GAME";
            case 15:
            default:
                return "unknown usage " + i2;
            case 16:
                return "USAGE_ASSISTANT";
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static AudioAttributesCompat fromBundle(Bundle bundle) {
        AudioAttributesImpl c2 = Build.VERSION.SDK_INT >= 21 ? AudioAttributesImplApi21.c(bundle) : AudioAttributesImplBase.c(bundle);
        if (c2 == null) {
            return null;
        }
        return new AudioAttributesCompat(c2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setForceLegacyBehavior(boolean z) {
        f841c = z;
    }

    @Nullable
    public static AudioAttributesCompat wrap(@NonNull Object obj) {
        if (Build.VERSION.SDK_INT < 21 || f841c) {
            return null;
        }
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21((AudioAttributes) obj);
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        audioAttributesCompat.a = audioAttributesImplApi21;
        return audioAttributesCompat;
    }

    int a() {
        return this.a.a();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesImpl audioAttributesImpl = this.a;
        AudioAttributesImpl audioAttributesImpl2 = ((AudioAttributesCompat) obj).a;
        return audioAttributesImpl == null ? audioAttributesImpl2 == null : audioAttributesImpl.equals(audioAttributesImpl2);
    }

    public int getContentType() {
        return this.a.getContentType();
    }

    public int getFlags() {
        return this.a.getFlags();
    }

    public int getLegacyStreamType() {
        return this.a.getLegacyStreamType();
    }

    public int getUsage() {
        return this.a.getUsage();
    }

    public int getVolumeControlStream() {
        return this.a.getVolumeControlStream();
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Bundle toBundle() {
        return this.a.toBundle();
    }

    public String toString() {
        return this.a.toString();
    }

    @Nullable
    public Object unwrap() {
        return this.a.b();
    }
}
