package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
class a extends VersionedParcel {

    /* renamed from: d, reason: collision with root package name */
    private final SparseIntArray f1122d;
    private final Parcel e;
    private final int f;
    private final int g;
    private final String h;

    /* renamed from: i, reason: collision with root package name */
    private int f1123i;
    private int j;
    private int k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new ArrayMap(), new ArrayMap(), new ArrayMap());
    }

    private a(Parcel parcel, int i2, int i3, String str, ArrayMap<String, Method> arrayMap, ArrayMap<String, Method> arrayMap2, ArrayMap<String, Class> arrayMap3) {
        super(arrayMap, arrayMap2, arrayMap3);
        this.f1122d = new SparseIntArray();
        this.f1123i = -1;
        this.j = 0;
        this.k = -1;
        this.e = parcel;
        this.f = i2;
        this.g = i3;
        this.j = i2;
        this.h = str;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public <T extends Parcelable> T A() {
        return (T) this.e.readParcelable(a.class.getClassLoader());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public String C() {
        return this.e.readString();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public IBinder D() {
        return this.e.readStrongBinder();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void F(int i2) {
        a();
        this.f1123i = i2;
        this.f1122d.put(i2, this.e.dataPosition());
        T(0);
        T(i2);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void H(boolean z) {
        this.e.writeInt(z ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void J(Bundle bundle) {
        this.e.writeBundle(bundle);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void K(byte[] bArr) {
        if (bArr == null) {
            this.e.writeInt(-1);
        } else {
            this.e.writeInt(bArr.length);
            this.e.writeByteArray(bArr);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void L(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            this.e.writeInt(-1);
        } else {
            this.e.writeInt(bArr.length);
            this.e.writeByteArray(bArr, i2, i3);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected void M(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.e, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void P(double d2) {
        this.e.writeDouble(d2);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void R(float f) {
        this.e.writeFloat(f);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void T(int i2) {
        this.e.writeInt(i2);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void V(long j) {
        this.e.writeLong(j);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void Y(Parcelable parcelable) {
        this.e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a() {
        int i2 = this.f1123i;
        if (i2 >= 0) {
            int i3 = this.f1122d.get(i2);
            int dataPosition = this.e.dataPosition();
            this.e.setDataPosition(i3);
            this.e.writeInt(dataPosition - i3);
            this.e.setDataPosition(dataPosition);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a0(String str) {
        this.e.writeString(str);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void b0(IBinder iBinder) {
        this.e.writeStrongBinder(iBinder);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected VersionedParcel c() {
        Parcel parcel = this.e;
        int dataPosition = parcel.dataPosition();
        int i2 = this.j;
        if (i2 == this.f) {
            i2 = this.g;
        }
        return new a(parcel, dataPosition, i2, this.h + "  ", this.a, this.b, this.f1121c);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void c0(IInterface iInterface) {
        this.e.writeStrongInterface(iInterface);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean i() {
        return this.e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public Bundle k() {
        return this.e.readBundle(a.class.getClassLoader());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public byte[] l() {
        int readInt = this.e.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected CharSequence m() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.e);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public double o() {
        return this.e.readDouble();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean s(int i2) {
        while (this.j < this.g) {
            int i3 = this.k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            this.e.setDataPosition(this.j);
            int readInt = this.e.readInt();
            this.k = this.e.readInt();
            this.j += readInt;
        }
        return this.k == i2;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public float t() {
        return this.e.readFloat();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public int w() {
        return this.e.readInt();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public long y() {
        return this.e.readLong();
    }
}
