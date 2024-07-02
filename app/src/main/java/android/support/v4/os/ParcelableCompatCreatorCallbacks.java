package android.support.v4.os;

import android.os.Parcel;

@Deprecated
/* loaded from: lib/Mus.dex */
public interface ParcelableCompatCreatorCallbacks<T> {
    T createFromParcel(Parcel parcel, ClassLoader classLoader);

    T[] newArray(int i2);
}
