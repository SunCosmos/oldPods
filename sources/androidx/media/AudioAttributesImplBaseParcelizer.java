package androidx.media;

import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(VersionedParcel versionedParcel) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.a = versionedParcel.readInt(audioAttributesImplBase.a, 1);
        audioAttributesImplBase.b = versionedParcel.readInt(audioAttributesImplBase.b, 2);
        audioAttributesImplBase.f845c = versionedParcel.readInt(audioAttributesImplBase.f845c, 3);
        audioAttributesImplBase.f846d = versionedParcel.readInt(audioAttributesImplBase.f846d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeInt(audioAttributesImplBase.a, 1);
        versionedParcel.writeInt(audioAttributesImplBase.b, 2);
        versionedParcel.writeInt(audioAttributesImplBase.f845c, 3);
        versionedParcel.writeInt(audioAttributesImplBase.f846d, 4);
    }
}
