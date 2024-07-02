package pl.droidsonroids.gif;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Locale;

/* loaded from: classes.dex */
public class GifAnimationMetaData implements Serializable, Parcelable {
    public static final Parcelable.Creator<GifAnimationMetaData> CREATOR = new a();
    private final int a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f2060c;

    /* renamed from: d, reason: collision with root package name */
    private final int f2061d;
    private final int e;
    private final long f;
    private final long g;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<GifAnimationMetaData> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public GifAnimationMetaData createFromParcel(Parcel parcel) {
            return new GifAnimationMetaData(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public GifAnimationMetaData[] newArray(int i2) {
            return new GifAnimationMetaData[i2];
        }
    }

    private GifAnimationMetaData(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.f2060c = parcel.readInt();
        this.f2061d = parcel.readInt();
        this.e = parcel.readInt();
        this.g = parcel.readLong();
        this.f = parcel.readLong();
    }

    /* synthetic */ GifAnimationMetaData(Parcel parcel, a aVar) {
        this(parcel);
    }

    public boolean a() {
        return this.e > 1 && this.b > 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        int i2 = this.a;
        String format = String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, loops: %s, duration: %d", Integer.valueOf(this.f2061d), Integer.valueOf(this.f2060c), Integer.valueOf(this.e), i2 == 0 ? "Infinity" : Integer.toString(i2), Integer.valueOf(this.b));
        if (!a()) {
            return format;
        }
        return "Animated " + format;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.f2060c);
        parcel.writeInt(this.f2061d);
        parcel.writeInt(this.e);
        parcel.writeLong(this.g);
        parcel.writeLong(this.f);
    }
}
