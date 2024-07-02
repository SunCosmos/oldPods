package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();

    @NonNull
    private final Month a;

    @NonNull
    private final Month b;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    private final DateValidator f1528c;

    /* renamed from: d, reason: collision with root package name */
    @Nullable
    private Month f1529d;
    private final int e;
    private final int f;

    /* loaded from: classes.dex */
    public static final class Builder {
        static final long e = j.a(Month.b(1900, 0).f);
        static final long f = j.a(Month.b(2100, 11).f);
        private long a;
        private long b;

        /* renamed from: c, reason: collision with root package name */
        private Long f1530c;

        /* renamed from: d, reason: collision with root package name */
        private DateValidator f1531d;

        public Builder() {
            this.a = e;
            this.b = f;
            this.f1531d = DateValidatorPointForward.from(Long.MIN_VALUE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(@NonNull CalendarConstraints calendarConstraints) {
            this.a = e;
            this.b = f;
            this.f1531d = DateValidatorPointForward.from(Long.MIN_VALUE);
            this.a = calendarConstraints.a.f;
            this.b = calendarConstraints.b.f;
            this.f1530c = Long.valueOf(calendarConstraints.f1529d.f);
            this.f1531d = calendarConstraints.f1528c;
        }

        @NonNull
        public CalendarConstraints build() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.f1531d);
            Month c2 = Month.c(this.a);
            Month c3 = Month.c(this.b);
            DateValidator dateValidator = (DateValidator) bundle.getParcelable("DEEP_COPY_VALIDATOR_KEY");
            Long l = this.f1530c;
            return new CalendarConstraints(c2, c3, dateValidator, l == null ? null : Month.c(l.longValue()), null);
        }

        @NonNull
        public Builder setEnd(long j) {
            this.b = j;
            return this;
        }

        @NonNull
        public Builder setOpenAt(long j) {
            this.f1530c = Long.valueOf(j);
            return this;
        }

        @NonNull
        public Builder setStart(long j) {
            this.a = j;
            return this;
        }

        @NonNull
        public Builder setValidator(@NonNull DateValidator dateValidator) {
            this.f1531d = dateValidator;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface DateValidator extends Parcelable {
        boolean isValid(long j);
    }

    /* loaded from: classes.dex */
    static class a implements Parcelable.Creator<CalendarConstraints> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints createFromParcel(@NonNull Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), null);
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CalendarConstraints[] newArray(int i2) {
            return new CalendarConstraints[i2];
        }
    }

    private CalendarConstraints(@NonNull Month month, @NonNull Month month2, @NonNull DateValidator dateValidator, @Nullable Month month3) {
        this.a = month;
        this.b = month2;
        this.f1529d = month3;
        this.f1528c = dateValidator;
        if (month3 != null && month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.f = month.k(month2) + 1;
        this.e = (month2.f1541c - month.f1541c) + 1;
    }

    /* synthetic */ CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, a aVar) {
        this(month, month2, dateValidator, month3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Month e(Month month) {
        return month.compareTo(this.a) < 0 ? this.a : month.compareTo(this.b) > 0 ? this.b : month;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.a.equals(calendarConstraints.a) && this.b.equals(calendarConstraints.b) && ObjectsCompat.equals(this.f1529d, calendarConstraints.f1529d) && this.f1528c.equals(calendarConstraints.f1528c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Month f() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.f;
    }

    public DateValidator getDateValidator() {
        return this.f1528c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Month h() {
        return this.f1529d;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.f1529d, this.f1528c});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Month i() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k(long j) {
        if (this.a.f(1) <= j) {
            Month month = this.b;
            if (j <= month.f(month.e)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(@Nullable Month month) {
        this.f1529d = month;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.a, 0);
        parcel.writeParcelable(this.b, 0);
        parcel.writeParcelable(this.f1529d, 0);
        parcel.writeParcelable(this.f1528c, 0);
    }
}
