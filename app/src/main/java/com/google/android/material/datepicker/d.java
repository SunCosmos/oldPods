package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
class d {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Pair<String, String> a(@Nullable Long l, @Nullable Long l2) {
        return b(l, l2, null);
    }

    static Pair<String, String> b(@Nullable Long l, @Nullable Long l2, @Nullable SimpleDateFormat simpleDateFormat) {
        if (l == null && l2 == null) {
            return Pair.create(null, null);
        }
        if (l == null) {
            return Pair.create(null, d(l2.longValue(), simpleDateFormat));
        }
        if (l2 == null) {
            return Pair.create(d(l.longValue(), simpleDateFormat), null);
        }
        Calendar o = j.o();
        Calendar q = j.q();
        q.setTimeInMillis(l.longValue());
        Calendar q2 = j.q();
        q2.setTimeInMillis(l2.longValue());
        if (simpleDateFormat != null) {
            return Pair.create(simpleDateFormat.format(new Date(l.longValue())), simpleDateFormat.format(new Date(l2.longValue())));
        }
        return q.get(1) == q2.get(1) ? q.get(1) == o.get(1) ? Pair.create(f(l.longValue(), Locale.getDefault()), f(l2.longValue(), Locale.getDefault())) : Pair.create(f(l.longValue(), Locale.getDefault()), k(l2.longValue(), Locale.getDefault())) : Pair.create(k(l.longValue(), Locale.getDefault()), k(l2.longValue(), Locale.getDefault()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(long j) {
        return d(j, null);
    }

    static String d(long j, @Nullable SimpleDateFormat simpleDateFormat) {
        Calendar o = j.o();
        Calendar q = j.q();
        q.setTimeInMillis(j);
        return simpleDateFormat != null ? simpleDateFormat.format(new Date(j)) : o.get(1) == q.get(1) ? e(j) : j(j);
    }

    static String e(long j) {
        return f(j, Locale.getDefault());
    }

    static String f(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? j.c(locale).format(new Date(j)) : j.j(locale).format(new Date(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g(long j) {
        return h(j, Locale.getDefault());
    }

    static String h(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? j.d(locale).format(new Date(j)) : j.h(locale).format(new Date(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String i(Context context, long j) {
        return DateUtils.formatDateTime(context, j - TimeZone.getDefault().getOffset(j), 36);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String j(long j) {
        return k(j, Locale.getDefault());
    }

    static String k(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? j.s(locale).format(new Date(j)) : j.i(locale).format(new Date(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String l(long j) {
        return m(j, Locale.getDefault());
    }

    static String m(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? j.t(locale).format(new Date(j)) : j.h(locale).format(new Date(j));
    }
}
