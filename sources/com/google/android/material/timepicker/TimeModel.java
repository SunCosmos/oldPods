package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.IntRange;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TimeModel implements Parcelable {
    public static final Parcelable.Creator<TimeModel> CREATOR = new a();
    private final b a;
    private final b b;

    /* renamed from: c, reason: collision with root package name */
    final int f1741c;

    /* renamed from: d, reason: collision with root package name */
    int f1742d;
    int e;
    int f;
    int g;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<TimeModel> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TimeModel createFromParcel(Parcel parcel) {
            return new TimeModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public TimeModel[] newArray(int i2) {
            return new TimeModel[i2];
        }
    }

    public TimeModel() {
        this(0);
    }

    public TimeModel(int i2) {
        this(0, 0, 10, i2);
    }

    public TimeModel(int i2, int i3, int i4, int i5) {
        this.f1742d = i2;
        this.e = i3;
        this.f = i4;
        this.f1741c = i5;
        this.g = f(i2);
        this.a = new b(59);
        this.b = new b(i5 == 1 ? 24 : 12);
    }

    protected TimeModel(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    public static String a(Resources resources, CharSequence charSequence) {
        return b(resources, charSequence, "%02d");
    }

    public static String b(Resources resources, CharSequence charSequence, String str) {
        return String.format(resources.getConfiguration().locale, str, Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
    }

    private static int f(int i2) {
        return i2 >= 12 ? 1 : 0;
    }

    public int c() {
        if (this.f1741c == 1) {
            return this.f1742d % 24;
        }
        int i2 = this.f1742d;
        if (i2 % 12 == 0) {
            return 12;
        }
        return this.g == 1 ? i2 - 12 : i2;
    }

    public b d() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public b e() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeModel)) {
            return false;
        }
        TimeModel timeModel = (TimeModel) obj;
        return this.f1742d == timeModel.f1742d && this.e == timeModel.e && this.f1741c == timeModel.f1741c && this.f == timeModel.f;
    }

    public void g(int i2) {
        if (this.f1741c == 1) {
            this.f1742d = i2;
        } else {
            this.f1742d = (i2 % 12) + (this.g != 1 ? 0 : 12);
        }
    }

    public void h(int i2) {
        this.g = f(i2);
        this.f1742d = i2;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f1741c), Integer.valueOf(this.f1742d), Integer.valueOf(this.e), Integer.valueOf(this.f)});
    }

    public void i(@IntRange(from = 0, to = 60) int i2) {
        this.e = i2 % 60;
    }

    public void j(int i2) {
        int i3;
        if (i2 != this.g) {
            this.g = i2;
            int i4 = this.f1742d;
            if (i4 < 12 && i2 == 1) {
                i3 = i4 + 12;
            } else if (i4 < 12 || i2 != 0) {
                return;
            } else {
                i3 = i4 - 12;
            }
            this.f1742d = i3;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f1742d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.f1741c);
    }
}
