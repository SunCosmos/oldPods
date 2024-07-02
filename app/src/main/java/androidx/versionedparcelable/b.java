package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.versionedparcelable.VersionedParcel;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
class b extends VersionedParcel {
    private static final Charset m = Charset.forName("UTF-16");

    /* renamed from: d, reason: collision with root package name */
    private final DataInputStream f1124d;
    private final DataOutputStream e;
    private DataInputStream f;
    private DataOutputStream g;
    private C0041b h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1125i;
    int j;
    private int k;
    int l;

    /* loaded from: classes.dex */
    class a extends FilterInputStream {
        a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() {
            b bVar = b.this;
            int i2 = bVar.l;
            if (i2 != -1 && bVar.j >= i2) {
                throw new IOException();
            }
            int read = super.read();
            b.this.j++;
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            b bVar = b.this;
            int i4 = bVar.l;
            if (i4 != -1 && bVar.j >= i4) {
                throw new IOException();
            }
            int read = super.read(bArr, i2, i3);
            if (read > 0) {
                b.this.j += read;
            }
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) {
            b bVar = b.this;
            int i2 = bVar.l;
            if (i2 != -1 && bVar.j >= i2) {
                throw new IOException();
            }
            long skip = super.skip(j);
            if (skip > 0) {
                b.this.j += (int) skip;
            }
            return skip;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.versionedparcelable.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0041b {
        final ByteArrayOutputStream a;
        final DataOutputStream b;

        /* renamed from: c, reason: collision with root package name */
        private final int f1126c;

        /* renamed from: d, reason: collision with root package name */
        private final DataOutputStream f1127d;

        C0041b(int i2, DataOutputStream dataOutputStream) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.a = byteArrayOutputStream;
            this.b = new DataOutputStream(byteArrayOutputStream);
            this.f1126c = i2;
            this.f1127d = dataOutputStream;
        }

        void a() {
            this.b.flush();
            int size = this.a.size();
            this.f1127d.writeInt((this.f1126c << 16) | (size >= 65535 ? 65535 : size));
            if (size >= 65535) {
                this.f1127d.writeInt(size);
            }
            this.a.writeTo(this.f1127d);
        }
    }

    public b(InputStream inputStream, OutputStream outputStream) {
        this(inputStream, outputStream, new ArrayMap(), new ArrayMap(), new ArrayMap());
    }

    private b(InputStream inputStream, OutputStream outputStream, ArrayMap<String, Method> arrayMap, ArrayMap<String, Method> arrayMap2, ArrayMap<String, Class> arrayMap3) {
        super(arrayMap, arrayMap2, arrayMap3);
        this.j = 0;
        this.k = -1;
        this.l = -1;
        DataInputStream dataInputStream = inputStream != null ? new DataInputStream(new a(inputStream)) : null;
        this.f1124d = dataInputStream;
        DataOutputStream dataOutputStream = outputStream != null ? new DataOutputStream(outputStream) : null;
        this.e = dataOutputStream;
        this.f = dataInputStream;
        this.g = dataOutputStream;
    }

    private void g0(int i2, String str, Bundle bundle) {
        switch (i2) {
            case 0:
                bundle.putParcelable(str, null);
                return;
            case 1:
            case 2:
                bundle.putBundle(str, k());
                return;
            case 3:
                bundle.putString(str, C());
                return;
            case 4:
                bundle.putStringArray(str, (String[]) h(new String[0]));
                return;
            case 5:
                bundle.putBoolean(str, i());
                return;
            case 6:
                bundle.putBooleanArray(str, j());
                return;
            case 7:
                bundle.putDouble(str, o());
                return;
            case 8:
                bundle.putDoubleArray(str, p());
                return;
            case 9:
                bundle.putInt(str, w());
                return;
            case 10:
                bundle.putIntArray(str, x());
                return;
            case 11:
                bundle.putLong(str, y());
                return;
            case 12:
                bundle.putLongArray(str, z());
                return;
            case 13:
                bundle.putFloat(str, t());
                return;
            case 14:
                bundle.putFloatArray(str, u());
                return;
            default:
                throw new RuntimeException("Unknown type " + i2);
        }
    }

    private void h0(Object obj) {
        int intValue;
        if (obj == null) {
            intValue = 0;
        } else {
            if (obj instanceof Bundle) {
                T(1);
                J((Bundle) obj);
                return;
            }
            if (obj instanceof String) {
                T(3);
                a0((String) obj);
                return;
            }
            if (obj instanceof String[]) {
                T(4);
                G((String[]) obj);
                return;
            }
            if (obj instanceof Boolean) {
                T(5);
                H(((Boolean) obj).booleanValue());
                return;
            }
            if (obj instanceof boolean[]) {
                T(6);
                I((boolean[]) obj);
                return;
            }
            if (obj instanceof Double) {
                T(7);
                P(((Double) obj).doubleValue());
                return;
            }
            if (obj instanceof double[]) {
                T(8);
                Q((double[]) obj);
                return;
            }
            if (!(obj instanceof Integer)) {
                if (obj instanceof int[]) {
                    T(10);
                    U((int[]) obj);
                    return;
                }
                if (obj instanceof Long) {
                    T(11);
                    V(((Long) obj).longValue());
                    return;
                }
                if (obj instanceof long[]) {
                    T(12);
                    W((long[]) obj);
                    return;
                }
                if (obj instanceof Float) {
                    T(13);
                    R(((Float) obj).floatValue());
                    return;
                } else if (obj instanceof float[]) {
                    T(14);
                    S((float[]) obj);
                    return;
                } else {
                    throw new IllegalArgumentException("Unsupported type " + obj.getClass());
                }
            }
            T(9);
            intValue = ((Integer) obj).intValue();
        }
        T(intValue);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public <T extends Parcelable> T A() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public String C() {
        try {
            int readInt = this.f.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.f.readFully(bArr);
            return new String(bArr, m);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public IBinder D() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void F(int i2) {
        a();
        C0041b c0041b = new C0041b(i2, this.e);
        this.h = c0041b;
        this.g = c0041b.b;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void H(boolean z) {
        try {
            this.g.writeBoolean(z);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void J(Bundle bundle) {
        try {
            if (bundle == null) {
                this.g.writeInt(-1);
                return;
            }
            Set<String> keySet = bundle.keySet();
            this.g.writeInt(keySet.size());
            for (String str : keySet) {
                a0(str);
                h0(bundle.get(str));
            }
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void K(byte[] bArr) {
        try {
            if (bArr != null) {
                this.g.writeInt(bArr.length);
                this.g.write(bArr);
            } else {
                this.g.writeInt(-1);
            }
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void L(byte[] bArr, int i2, int i3) {
        try {
            if (bArr != null) {
                this.g.writeInt(i3);
                this.g.write(bArr, i2, i3);
            } else {
                this.g.writeInt(-1);
            }
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected void M(CharSequence charSequence) {
        if (!this.f1125i) {
            throw new RuntimeException("CharSequence cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void P(double d2) {
        try {
            this.g.writeDouble(d2);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void R(float f) {
        try {
            this.g.writeFloat(f);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void T(int i2) {
        try {
            this.g.writeInt(i2);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void V(long j) {
        try {
            this.g.writeLong(j);
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void Y(Parcelable parcelable) {
        if (!this.f1125i) {
            throw new RuntimeException("Parcelables cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a() {
        C0041b c0041b = this.h;
        if (c0041b != null) {
            try {
                if (c0041b.a.size() != 0) {
                    this.h.a();
                }
                this.h = null;
            } catch (IOException e) {
                throw new VersionedParcel.ParcelException(e);
            }
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void a0(String str) {
        try {
            if (str != null) {
                byte[] bytes = str.getBytes(m);
                this.g.writeInt(bytes.length);
                this.g.write(bytes);
            } else {
                this.g.writeInt(-1);
            }
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void b0(IBinder iBinder) {
        if (!this.f1125i) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected VersionedParcel c() {
        return new b(this.f, this.g, this.a, this.b, this.f1121c);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void c0(IInterface iInterface) {
        if (!this.f1125i) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean i() {
        try {
            return this.f.readBoolean();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean isStream() {
        return true;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public Bundle k() {
        int w = w();
        if (w < 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (int i2 = 0; i2 < w; i2++) {
            g0(w(), C(), bundle);
        }
        return bundle;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public byte[] l() {
        try {
            int readInt = this.f.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.f.readFully(bArr);
            return bArr;
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    protected CharSequence m() {
        return null;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public double o() {
        try {
            return this.f.readDouble();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean s(int i2) {
        while (true) {
            try {
                int i3 = this.k;
                if (i3 == i2) {
                    return true;
                }
                if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                    return false;
                }
                if (this.j < this.l) {
                    this.f1124d.skip(r2 - r1);
                }
                this.l = -1;
                int readInt = this.f1124d.readInt();
                this.j = 0;
                int i4 = readInt & 65535;
                if (i4 == 65535) {
                    i4 = this.f1124d.readInt();
                }
                this.k = (readInt >> 16) & 65535;
                this.l = i4;
            } catch (IOException unused) {
                return false;
            }
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void setSerializationFlags(boolean z, boolean z2) {
        if (!z) {
            throw new RuntimeException("Serialization of this object is not allowed");
        }
        this.f1125i = z2;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public float t() {
        try {
            return this.f.readFloat();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public int w() {
        try {
            return this.f.readInt();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public long y() {
        try {
            return this.f.readLong();
        } catch (IOException e) {
            throw new VersionedParcel.ParcelException(e);
        }
    }
}
