package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();
    final int[] a;
    final ArrayList<String> b;

    /* renamed from: c, reason: collision with root package name */
    final int[] f702c;

    /* renamed from: d, reason: collision with root package name */
    final int[] f703d;
    final int e;
    final String f;
    final int g;
    final int h;

    /* renamed from: i, reason: collision with root package name */
    final CharSequence f704i;
    final int j;
    final CharSequence k;
    final ArrayList<String> l;
    final ArrayList<String> m;
    final boolean n;

    /* loaded from: classes.dex */
    class a implements Parcelable.Creator<BackStackState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i2) {
            return new BackStackState[i2];
        }
    }

    public BackStackState(Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.createStringArrayList();
        this.f702c = parcel.createIntArray();
        this.f703d = parcel.createIntArray();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.f704i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.readInt();
        this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.l = parcel.createStringArrayList();
        this.m = parcel.createStringArrayList();
        this.n = parcel.readInt() != 0;
    }

    public BackStackState(androidx.fragment.app.a aVar) {
        int size = aVar.f743c.size();
        this.a = new int[size * 5];
        if (!aVar.f745i) {
            throw new IllegalStateException("Not on back stack");
        }
        this.b = new ArrayList<>(size);
        this.f702c = new int[size];
        this.f703d = new int[size];
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            FragmentTransaction.a aVar2 = aVar.f743c.get(i2);
            int i4 = i3 + 1;
            this.a[i3] = aVar2.a;
            ArrayList<String> arrayList = this.b;
            Fragment fragment = aVar2.b;
            arrayList.add(fragment != null ? fragment.f : null);
            int[] iArr = this.a;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f746c;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f747d;
            int i7 = i6 + 1;
            iArr[i6] = aVar2.e;
            iArr[i7] = aVar2.f;
            this.f702c[i2] = aVar2.g.ordinal();
            this.f703d[i2] = aVar2.h.ordinal();
            i2++;
            i3 = i7 + 1;
        }
        this.e = aVar.h;
        this.f = aVar.k;
        this.g = aVar.v;
        this.h = aVar.l;
        this.f704i = aVar.m;
        this.j = aVar.n;
        this.k = aVar.o;
        this.l = aVar.p;
        this.m = aVar.q;
        this.n = aVar.r;
    }

    public androidx.fragment.app.a a(FragmentManager fragmentManager) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.a;
            if (i2 >= iArr.length) {
                aVar.h = this.e;
                aVar.k = this.f;
                aVar.v = this.g;
                aVar.f745i = true;
                aVar.l = this.h;
                aVar.m = this.f704i;
                aVar.n = this.j;
                aVar.o = this.k;
                aVar.p = this.l;
                aVar.q = this.m;
                aVar.r = this.n;
                aVar.f(1);
                return aVar;
            }
            FragmentTransaction.a aVar2 = new FragmentTransaction.a();
            int i4 = i2 + 1;
            aVar2.a = iArr[i2];
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i3 + " base fragment #" + this.a[i4]);
            }
            String str = this.b.get(i3);
            aVar2.b = str != null ? fragmentManager.c0(str) : null;
            aVar2.g = Lifecycle.State.values()[this.f702c[i3]];
            aVar2.h = Lifecycle.State.values()[this.f703d[i3]];
            int[] iArr2 = this.a;
            int i5 = i4 + 1;
            int i6 = iArr2[i4];
            aVar2.f746c = i6;
            int i7 = i5 + 1;
            int i8 = iArr2[i5];
            aVar2.f747d = i8;
            int i9 = i7 + 1;
            int i10 = iArr2[i7];
            aVar2.e = i10;
            int i11 = iArr2[i9];
            aVar2.f = i11;
            aVar.f744d = i6;
            aVar.e = i8;
            aVar.f = i10;
            aVar.g = i11;
            aVar.c(aVar2);
            i3++;
            i2 = i9 + 1;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.a);
        parcel.writeStringList(this.b);
        parcel.writeIntArray(this.f702c);
        parcel.writeIntArray(this.f703d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.f704i, parcel, 0);
        parcel.writeInt(this.j);
        TextUtils.writeToParcel(this.k, parcel, 0);
        parcel.writeStringList(this.l);
        parcel.writeStringList(this.m);
        parcel.writeInt(this.n ? 1 : 0);
    }
}
