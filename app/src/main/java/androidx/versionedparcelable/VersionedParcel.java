package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class VersionedParcel {
    protected final ArrayMap<String, Method> a;
    protected final ArrayMap<String, Method> b;

    /* renamed from: c, reason: collision with root package name */
    protected final ArrayMap<String, Class> f1121c;

    /* loaded from: classes.dex */
    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends ObjectInputStream {
        a(VersionedParcel versionedParcel, InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
            Class<?> cls = Class.forName(objectStreamClass.getName(), false, a.class.getClassLoader());
            return cls != null ? cls : super.resolveClass(objectStreamClass);
        }
    }

    public VersionedParcel(ArrayMap<String, Method> arrayMap, ArrayMap<String, Method> arrayMap2, ArrayMap<String, Class> arrayMap3) {
        this.a = arrayMap;
        this.b = arrayMap2;
        this.f1121c = arrayMap3;
    }

    private <T> void N(Collection<T> collection) {
        if (collection == null) {
            T(-1);
            return;
        }
        int size = collection.size();
        T(size);
        if (size > 0) {
            int f = f(collection.iterator().next());
            T(f);
            switch (f) {
                case 1:
                    Iterator<T> it = collection.iterator();
                    while (it.hasNext()) {
                        e0((VersionedParcelable) it.next());
                    }
                    return;
                case 2:
                    Iterator<T> it2 = collection.iterator();
                    while (it2.hasNext()) {
                        Y((Parcelable) it2.next());
                    }
                    return;
                case 3:
                    Iterator<T> it3 = collection.iterator();
                    while (it3.hasNext()) {
                        Z((Serializable) it3.next());
                    }
                    return;
                case 4:
                    Iterator<T> it4 = collection.iterator();
                    while (it4.hasNext()) {
                        a0((String) it4.next());
                    }
                    return;
                case 5:
                    Iterator<T> it5 = collection.iterator();
                    while (it5.hasNext()) {
                        b0((IBinder) it5.next());
                    }
                    return;
                case 6:
                default:
                    return;
                case 7:
                    Iterator<T> it6 = collection.iterator();
                    while (it6.hasNext()) {
                        T(((Integer) it6.next()).intValue());
                    }
                    return;
                case 8:
                    Iterator<T> it7 = collection.iterator();
                    while (it7.hasNext()) {
                        R(((Float) it7.next()).floatValue());
                    }
                    return;
            }
        }
    }

    private <T> void O(Collection<T> collection, int i2) {
        F(i2);
        N(collection);
    }

    private void Z(Serializable serializable) {
        if (serializable == null) {
            a0(null);
            return;
        }
        String name = serializable.getClass().getName();
        a0(name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            K(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + name + ")", e);
        }
    }

    private Exception b(int i2, String str) {
        switch (i2) {
            case -9:
                return (Exception) A();
            case -8:
            default:
                return new RuntimeException("Unknown exception code: " + i2 + " msg " + str);
            case -7:
                return new UnsupportedOperationException(str);
            case -6:
                return new NetworkOnMainThreadException();
            case -5:
                return new IllegalStateException(str);
            case -4:
                return new NullPointerException(str);
            case -3:
                return new IllegalArgumentException(str);
            case -2:
                return new BadParcelableException(str);
            case -1:
                return new SecurityException(str);
        }
    }

    private Class d(Class<? extends VersionedParcelable> cls) {
        Class cls2 = this.f1121c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.f1121c.put(cls.getName(), cls3);
        return cls3;
    }

    private Method e(String str) {
        Method method = this.a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
        this.a.put(str, declaredMethod);
        return declaredMethod;
    }

    private <T> int f(T t) {
        if (t instanceof String) {
            return 4;
        }
        if (t instanceof Parcelable) {
            return 2;
        }
        if (t instanceof VersionedParcelable) {
            return 1;
        }
        if (t instanceof Serializable) {
            return 3;
        }
        if (t instanceof IBinder) {
            return 5;
        }
        if (t instanceof Integer) {
            return 7;
        }
        if (t instanceof Float) {
            return 8;
        }
        throw new IllegalArgumentException(t.getClass().getName() + " cannot be VersionedParcelled");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void f0(VersionedParcelable versionedParcelable) {
        try {
            a0(d(versionedParcelable.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName() + " does not have a Parcelizer", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Method g(Class cls) {
        Method method = this.b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class d2 = d(cls);
        System.currentTimeMillis();
        Method declaredMethod = d2.getDeclaredMethod("write", cls, VersionedParcel.class);
        this.b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    private <T, S extends Collection<T>> S n(S s) {
        int w = w();
        if (w < 0) {
            return null;
        }
        if (w != 0) {
            int w2 = w();
            if (w < 0) {
                return null;
            }
            if (w2 == 1) {
                while (w > 0) {
                    s.add(E());
                    w--;
                }
            } else if (w2 == 2) {
                while (w > 0) {
                    s.add(A());
                    w--;
                }
            } else if (w2 == 3) {
                while (w > 0) {
                    s.add(B());
                    w--;
                }
            } else if (w2 == 4) {
                while (w > 0) {
                    s.add(C());
                    w--;
                }
            } else if (w2 == 5) {
                while (w > 0) {
                    s.add(D());
                    w--;
                }
            }
        }
        return s;
    }

    private Exception q(int i2, String str) {
        return b(i2, str);
    }

    private int r() {
        return w();
    }

    protected abstract <T extends Parcelable> T A();

    protected Serializable B() {
        String C = C();
        if (C == null) {
            return null;
        }
        try {
            return (Serializable) new a(this, new ByteArrayInputStream(l())).readObject();
        } catch (IOException e) {
            throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + C + ")", e);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + C + ")", e2);
        }
    }

    protected abstract String C();

    protected abstract IBinder D();

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends VersionedParcelable> T E() {
        String C = C();
        if (C == null) {
            return null;
        }
        return (T) v(C, c());
    }

    protected abstract void F(int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> void G(T[] tArr) {
        if (tArr == 0) {
            T(-1);
            return;
        }
        int length = tArr.length;
        T(length);
        if (length > 0) {
            int i2 = 0;
            int f = f(tArr[0]);
            T(f);
            if (f == 1) {
                while (i2 < length) {
                    e0((VersionedParcelable) tArr[i2]);
                    i2++;
                }
                return;
            }
            if (f == 2) {
                while (i2 < length) {
                    Y((Parcelable) tArr[i2]);
                    i2++;
                }
                return;
            }
            if (f == 3) {
                while (i2 < length) {
                    Z((Serializable) tArr[i2]);
                    i2++;
                }
            } else if (f == 4) {
                while (i2 < length) {
                    a0((String) tArr[i2]);
                    i2++;
                }
            } else {
                if (f != 5) {
                    return;
                }
                while (i2 < length) {
                    b0((IBinder) tArr[i2]);
                    i2++;
                }
            }
        }
    }

    protected abstract void H(boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    public void I(boolean[] zArr) {
        if (zArr == null) {
            T(-1);
            return;
        }
        T(zArr.length);
        for (boolean z : zArr) {
            T(z ? 1 : 0);
        }
    }

    protected abstract void J(Bundle bundle);

    protected abstract void K(byte[] bArr);

    protected abstract void L(byte[] bArr, int i2, int i3);

    protected abstract void M(CharSequence charSequence);

    protected abstract void P(double d2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void Q(double[] dArr) {
        if (dArr == null) {
            T(-1);
            return;
        }
        T(dArr.length);
        for (double d2 : dArr) {
            P(d2);
        }
    }

    protected abstract void R(float f);

    /* JADX INFO: Access modifiers changed from: protected */
    public void S(float[] fArr) {
        if (fArr == null) {
            T(-1);
            return;
        }
        T(fArr.length);
        for (float f : fArr) {
            R(f);
        }
    }

    protected abstract void T(int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void U(int[] iArr) {
        if (iArr == null) {
            T(-1);
            return;
        }
        T(iArr.length);
        for (int i2 : iArr) {
            T(i2);
        }
    }

    protected abstract void V(long j);

    /* JADX INFO: Access modifiers changed from: protected */
    public void W(long[] jArr) {
        if (jArr == null) {
            T(-1);
            return;
        }
        T(jArr.length);
        for (long j : jArr) {
            V(j);
        }
    }

    protected void X() {
        T(0);
    }

    protected abstract void Y(Parcelable parcelable);

    protected abstract void a();

    protected abstract void a0(String str);

    protected abstract void b0(IBinder iBinder);

    protected abstract VersionedParcel c();

    protected abstract void c0(IInterface iInterface);

    protected <T extends VersionedParcelable> void d0(T t, VersionedParcel versionedParcel) {
        try {
            g(t.getClass()).invoke(null, t, versionedParcel);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (!(e4.getCause() instanceof RuntimeException)) {
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
            }
            throw ((RuntimeException) e4.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e0(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            a0(null);
            return;
        }
        f0(versionedParcelable);
        VersionedParcel c2 = c();
        d0(versionedParcelable, c2);
        c2.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T[] h(T[] tArr) {
        int w = w();
        if (w < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(w);
        if (w != 0) {
            int w2 = w();
            if (w < 0) {
                return null;
            }
            if (w2 == 1) {
                while (w > 0) {
                    arrayList.add(E());
                    w--;
                }
            } else if (w2 == 2) {
                while (w > 0) {
                    arrayList.add(A());
                    w--;
                }
            } else if (w2 == 3) {
                while (w > 0) {
                    arrayList.add(B());
                    w--;
                }
            } else if (w2 == 4) {
                while (w > 0) {
                    arrayList.add(C());
                    w--;
                }
            } else if (w2 == 5) {
                while (w > 0) {
                    arrayList.add(D());
                    w--;
                }
            }
        }
        return (T[]) arrayList.toArray(tArr);
    }

    protected abstract boolean i();

    public boolean isStream() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean[] j() {
        int w = w();
        if (w < 0) {
            return null;
        }
        boolean[] zArr = new boolean[w];
        for (int i2 = 0; i2 < w; i2++) {
            zArr[i2] = w() != 0;
        }
        return zArr;
    }

    protected abstract Bundle k();

    protected abstract byte[] l();

    protected abstract CharSequence m();

    protected abstract double o();

    /* JADX INFO: Access modifiers changed from: protected */
    public double[] p() {
        int w = w();
        if (w < 0) {
            return null;
        }
        double[] dArr = new double[w];
        for (int i2 = 0; i2 < w; i2++) {
            dArr[i2] = o();
        }
        return dArr;
    }

    public <T> T[] readArray(T[] tArr, int i2) {
        return !s(i2) ? tArr : (T[]) h(tArr);
    }

    public boolean readBoolean(boolean z, int i2) {
        return !s(i2) ? z : i();
    }

    public boolean[] readBooleanArray(boolean[] zArr, int i2) {
        return !s(i2) ? zArr : j();
    }

    public Bundle readBundle(Bundle bundle, int i2) {
        return !s(i2) ? bundle : k();
    }

    public byte readByte(byte b, int i2) {
        return !s(i2) ? b : (byte) (w() & 255);
    }

    public byte[] readByteArray(byte[] bArr, int i2) {
        return !s(i2) ? bArr : l();
    }

    public char[] readCharArray(char[] cArr, int i2) {
        if (!s(i2)) {
            return cArr;
        }
        int w = w();
        if (w < 0) {
            return null;
        }
        char[] cArr2 = new char[w];
        for (int i3 = 0; i3 < w; i3++) {
            cArr2[i3] = (char) w();
        }
        return cArr2;
    }

    public CharSequence readCharSequence(CharSequence charSequence, int i2) {
        return !s(i2) ? charSequence : m();
    }

    public double readDouble(double d2, int i2) {
        return !s(i2) ? d2 : o();
    }

    public double[] readDoubleArray(double[] dArr, int i2) {
        return !s(i2) ? dArr : p();
    }

    public Exception readException(Exception exc, int i2) {
        int r;
        return (s(i2) && (r = r()) != 0) ? q(r, C()) : exc;
    }

    public float readFloat(float f, int i2) {
        return !s(i2) ? f : t();
    }

    public float[] readFloatArray(float[] fArr, int i2) {
        return !s(i2) ? fArr : u();
    }

    public int readInt(int i2, int i3) {
        return !s(i3) ? i2 : w();
    }

    public int[] readIntArray(int[] iArr, int i2) {
        return !s(i2) ? iArr : x();
    }

    public <T> List<T> readList(List<T> list, int i2) {
        return !s(i2) ? list : (List) n(new ArrayList());
    }

    public long readLong(long j, int i2) {
        return !s(i2) ? j : y();
    }

    public long[] readLongArray(long[] jArr, int i2) {
        return !s(i2) ? jArr : z();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <K, V> Map<K, V> readMap(Map<K, V> map, int i2) {
        if (!s(i2)) {
            return map;
        }
        int w = w();
        if (w < 0) {
            return null;
        }
        ArrayMap arrayMap = new ArrayMap();
        if (w == 0) {
            return arrayMap;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        n(arrayList);
        n(arrayList2);
        for (int i3 = 0; i3 < w; i3++) {
            arrayMap.put(arrayList.get(i3), arrayList2.get(i3));
        }
        return arrayMap;
    }

    public <T extends Parcelable> T readParcelable(T t, int i2) {
        return !s(i2) ? t : (T) A();
    }

    public <T> Set<T> readSet(Set<T> set, int i2) {
        return !s(i2) ? set : (Set) n(new ArraySet());
    }

    @RequiresApi(api = 21)
    public Size readSize(Size size, int i2) {
        if (!s(i2)) {
            return size;
        }
        if (i()) {
            return new Size(w(), w());
        }
        return null;
    }

    @RequiresApi(api = 21)
    public SizeF readSizeF(SizeF sizeF, int i2) {
        if (!s(i2)) {
            return sizeF;
        }
        if (i()) {
            return new SizeF(t(), t());
        }
        return null;
    }

    public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int i2) {
        if (!s(i2)) {
            return sparseBooleanArray;
        }
        int w = w();
        if (w < 0) {
            return null;
        }
        SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray(w);
        for (int i3 = 0; i3 < w; i3++) {
            sparseBooleanArray2.put(w(), i());
        }
        return sparseBooleanArray2;
    }

    public String readString(String str, int i2) {
        return !s(i2) ? str : C();
    }

    public IBinder readStrongBinder(IBinder iBinder, int i2) {
        return !s(i2) ? iBinder : D();
    }

    public <T extends VersionedParcelable> T readVersionedParcelable(T t, int i2) {
        return !s(i2) ? t : (T) E();
    }

    protected abstract boolean s(int i2);

    public void setSerializationFlags(boolean z, boolean z2) {
    }

    protected abstract float t();

    /* JADX INFO: Access modifiers changed from: protected */
    public float[] u() {
        int w = w();
        if (w < 0) {
            return null;
        }
        float[] fArr = new float[w];
        for (int i2 = 0; i2 < w; i2++) {
            fArr[i2] = t();
        }
        return fArr;
    }

    protected <T extends VersionedParcelable> T v(String str, VersionedParcel versionedParcel) {
        try {
            return (T) e(str).invoke(null, versionedParcel);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    protected abstract int w();

    public <T> void writeArray(T[] tArr, int i2) {
        F(i2);
        G(tArr);
    }

    public void writeBoolean(boolean z, int i2) {
        F(i2);
        H(z);
    }

    public void writeBooleanArray(boolean[] zArr, int i2) {
        F(i2);
        I(zArr);
    }

    public void writeBundle(Bundle bundle, int i2) {
        F(i2);
        J(bundle);
    }

    public void writeByte(byte b, int i2) {
        F(i2);
        T(b);
    }

    public void writeByteArray(byte[] bArr, int i2) {
        F(i2);
        K(bArr);
    }

    public void writeByteArray(byte[] bArr, int i2, int i3, int i4) {
        F(i4);
        L(bArr, i2, i3);
    }

    public void writeCharArray(char[] cArr, int i2) {
        F(i2);
        if (cArr == null) {
            T(-1);
            return;
        }
        T(cArr.length);
        for (char c2 : cArr) {
            T(c2);
        }
    }

    public void writeCharSequence(CharSequence charSequence, int i2) {
        F(i2);
        M(charSequence);
    }

    public void writeDouble(double d2, int i2) {
        F(i2);
        P(d2);
    }

    public void writeDoubleArray(double[] dArr, int i2) {
        F(i2);
        Q(dArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeException(Exception exc, int i2) {
        F(i2);
        if (exc == 0) {
            X();
            return;
        }
        int i3 = 0;
        if ((exc instanceof Parcelable) && exc.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            i3 = -9;
        } else if (exc instanceof SecurityException) {
            i3 = -1;
        } else if (exc instanceof BadParcelableException) {
            i3 = -2;
        } else if (exc instanceof IllegalArgumentException) {
            i3 = -3;
        } else if (exc instanceof NullPointerException) {
            i3 = -4;
        } else if (exc instanceof IllegalStateException) {
            i3 = -5;
        } else if (exc instanceof NetworkOnMainThreadException) {
            i3 = -6;
        } else if (exc instanceof UnsupportedOperationException) {
            i3 = -7;
        }
        T(i3);
        if (i3 == 0) {
            if (!(exc instanceof RuntimeException)) {
                throw new RuntimeException(exc);
            }
            throw ((RuntimeException) exc);
        }
        a0(exc.getMessage());
        if (i3 != -9) {
            return;
        }
        Y((Parcelable) exc);
    }

    public void writeFloat(float f, int i2) {
        F(i2);
        R(f);
    }

    public void writeFloatArray(float[] fArr, int i2) {
        F(i2);
        S(fArr);
    }

    public void writeInt(int i2, int i3) {
        F(i3);
        T(i2);
    }

    public void writeIntArray(int[] iArr, int i2) {
        F(i2);
        U(iArr);
    }

    public <T> void writeList(List<T> list, int i2) {
        O(list, i2);
    }

    public void writeLong(long j, int i2) {
        F(i2);
        V(j);
    }

    public void writeLongArray(long[] jArr, int i2) {
        F(i2);
        W(jArr);
    }

    public <K, V> void writeMap(Map<K, V> map, int i2) {
        F(i2);
        if (map == null) {
            T(-1);
            return;
        }
        int size = map.size();
        T(size);
        if (size == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        N(arrayList);
        N(arrayList2);
    }

    public void writeParcelable(Parcelable parcelable, int i2) {
        F(i2);
        Y(parcelable);
    }

    public void writeSerializable(Serializable serializable, int i2) {
        F(i2);
        Z(serializable);
    }

    public <T> void writeSet(Set<T> set, int i2) {
        O(set, i2);
    }

    @RequiresApi(api = 21)
    public void writeSize(Size size, int i2) {
        F(i2);
        H(size != null);
        if (size != null) {
            T(size.getWidth());
            T(size.getHeight());
        }
    }

    @RequiresApi(api = 21)
    public void writeSizeF(SizeF sizeF, int i2) {
        F(i2);
        H(sizeF != null);
        if (sizeF != null) {
            R(sizeF.getWidth());
            R(sizeF.getHeight());
        }
    }

    public void writeSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int i2) {
        F(i2);
        if (sparseBooleanArray == null) {
            T(-1);
            return;
        }
        int size = sparseBooleanArray.size();
        T(size);
        for (int i3 = 0; i3 < size; i3++) {
            T(sparseBooleanArray.keyAt(i3));
            H(sparseBooleanArray.valueAt(i3));
        }
    }

    public void writeString(String str, int i2) {
        F(i2);
        a0(str);
    }

    public void writeStrongBinder(IBinder iBinder, int i2) {
        F(i2);
        b0(iBinder);
    }

    public void writeStrongInterface(IInterface iInterface, int i2) {
        F(i2);
        c0(iInterface);
    }

    public void writeVersionedParcelable(VersionedParcelable versionedParcelable, int i2) {
        F(i2);
        e0(versionedParcelable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] x() {
        int w = w();
        if (w < 0) {
            return null;
        }
        int[] iArr = new int[w];
        for (int i2 = 0; i2 < w; i2++) {
            iArr[i2] = w();
        }
        return iArr;
    }

    protected abstract long y();

    /* JADX INFO: Access modifiers changed from: protected */
    public long[] z() {
        int w = w();
        if (w < 0) {
            return null;
        }
        long[] jArr = new long[w];
        for (int i2 = 0; i2 < w; i2++) {
            jArr[i2] = y();
        }
        return jArr;
    }
}
