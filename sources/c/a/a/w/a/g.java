package c.a.a.w.a;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class g extends q {
    private static final Pattern j = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
    private static final long[] k = {604800000, 86400000, 3600000, 60000, 1000};
    private static final Pattern l = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");
    private final String a;
    private final Date b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f1281c;

    /* renamed from: d, reason: collision with root package name */
    private final Date f1282d;
    private final boolean e;
    private final String f;
    private final String g;
    private final String[] h;

    /* renamed from: i, reason: collision with root package name */
    private final String f1283i;

    public g(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr, String str7, double d2, double d3) {
        super(r.CALENDAR);
        this.a = str;
        try {
            Date g = g(str2);
            this.b = g;
            if (str3 == null) {
                long h = h(str4);
                this.f1282d = h < 0 ? null : new Date(g.getTime() + h);
            } else {
                try {
                    this.f1282d = g(str3);
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e.toString());
                }
            }
            this.f1281c = str2.length() == 8;
            this.e = str3 != null && str3.length() == 8;
            this.f = str5;
            this.g = str6;
            this.h = strArr;
            this.f1283i = str7;
        } catch (ParseException e2) {
            throw new IllegalArgumentException(e2.toString());
        }
    }

    private static DateFormat d() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    private static DateFormat e() {
        return new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH);
    }

    private static String f(boolean z, Date date) {
        if (date == null) {
            return null;
        }
        return (z ? DateFormat.getDateInstance(2) : DateFormat.getDateTimeInstance(2, 2)).format(date);
    }

    private static Date g(String str) {
        if (!l.matcher(str).matches()) {
            throw new ParseException(str, 0);
        }
        if (str.length() == 8) {
            return d().parse(str);
        }
        if (str.length() != 16 || str.charAt(15) != 'Z') {
            return e().parse(str);
        }
        Date parse = e().parse(str.substring(0, 15));
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        long time = parse.getTime() + gregorianCalendar.get(15);
        gregorianCalendar.setTime(new Date(time));
        return new Date(time + gregorianCalendar.get(16));
    }

    private static long h(CharSequence charSequence) {
        if (charSequence == null) {
            return -1L;
        }
        Matcher matcher = j.matcher(charSequence);
        if (!matcher.matches()) {
            return -1L;
        }
        long j2 = 0;
        int i2 = 0;
        while (true) {
            long[] jArr = k;
            if (i2 >= jArr.length) {
                return j2;
            }
            int i3 = i2 + 1;
            if (matcher.group(i3) != null) {
                j2 += jArr[i2] * Integer.parseInt(r5);
            }
            i2 = i3;
        }
    }

    @Override // c.a.a.w.a.q
    public String a() {
        StringBuilder sb = new StringBuilder(100);
        q.b(this.a, sb);
        q.b(f(this.f1281c, this.b), sb);
        q.b(f(this.e, this.f1282d), sb);
        q.b(this.f, sb);
        q.b(this.g, sb);
        q.c(this.h, sb);
        q.b(this.f1283i, sb);
        return sb.toString();
    }
}
