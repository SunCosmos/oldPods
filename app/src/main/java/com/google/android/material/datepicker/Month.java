package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();

    @NonNull
    private final Calendar a;
    final int b;

    /* renamed from: c, reason: collision with root package name */
    final int f1541c;

    /* renamed from: d, reason: collision with root package name */
    final int f1542d;
    final int e;
    final long f;

    @Nullable
    private String g;

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<Month> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Month createFromParcel(@NonNull Parcel parcel) {
            return Month.b(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Month[] newArray(int i2) {
            return new Month[i2];
        }
    }

    private Month(@NonNull Calendar calendar) {
        calendar.set(5, 1);
        Calendar f = j.f(calendar);
        this.a = f;
        this.b = f.get(2);
        this.f1541c = f.get(1);
        this.f1542d = f.getMaximum(7);
        this.e = f.getActualMaximum(5);
        this.f = f.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static Month b(int i2, int i3) {
        Calendar q = j.q();
        q.set(1, i2);
        q.set(2, i3);
        return new Month(q);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static Month c(long j) {
        Calendar q = j.q();
        q.setTimeInMillis(j);
        return new Month(q);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static Month d() {
        return new Month(j.o());
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull Month month) {
        return this.a.compareTo(month.a);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        int firstDayOfWeek = this.a.get(7) - this.a.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f1542d : firstDayOfWeek;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.b == month.b && this.f1541c == month.f1541c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long f(int i2) {
        Calendar f = j.f(this.a);
        f.set(5, i2);
        return f.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g(long j) {
        Calendar f = j.f(this.a);
        f.setTimeInMillis(j);
        return f.get(5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String h(Context context) {
        if (this.g == null) {
            this.g = d.i(context, this.a.getTimeInMillis());
        }
        return this.g;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.f1541c)});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long i() {
        return this.a.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Month j(int i2) {
        Calendar f = j.f(this.a);
        f.add(2, i2);
        return new Month(f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k(@NonNull Month month) {
        if (this.a instanceof GregorianCalendar) {
            return ((month.f1541c - this.f1541c) * 12) + (month.b - this.b);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        parcel.writeInt(this.f1541c);
        parcel.writeInt(this.b);
    }
}
