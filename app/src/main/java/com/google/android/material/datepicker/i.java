package com.google.android.material.datepicker;

import androidx.annotation.Nullable;
import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: classes.dex */
class i {

    /* renamed from: c, reason: collision with root package name */
    private static final i f1560c = new i(null, null);

    @Nullable
    private final Long a;

    @Nullable
    private final TimeZone b;

    private i(@Nullable Long l, @Nullable TimeZone timeZone) {
        this.a = l;
        this.b = timeZone;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i c() {
        return f1560c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Calendar a() {
        return b(this.b);
    }

    Calendar b(@Nullable TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.a;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
