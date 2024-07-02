package androidx.media;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.versionedparcelable.VersionedParcelable;

/* loaded from: classes.dex */
interface AudioAttributesImpl extends VersionedParcelable {
    int a();

    Object b();

    int getContentType();

    int getFlags();

    int getLegacyStreamType();

    int getUsage();

    int getVolumeControlStream();

    @NonNull
    Bundle toBundle();
}
