package androidx.core.os;

import android.os.Build;
import android.os.LocaleList;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.Size;
import java.util.Locale;

/* loaded from: classes.dex */
public final class LocaleListCompat {
    private static final LocaleListCompat b = create(new Locale[0]);
    private b a;

    private LocaleListCompat(b bVar) {
        this.a = bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Locale a(String str) {
        if (str.contains("-")) {
            String[] split = str.split("-", -1);
            if (split.length > 2) {
                return new Locale(split[0], split[1], split[2]);
            }
            if (split.length > 1) {
                return new Locale(split[0], split[1]);
            }
            if (split.length == 1) {
                return new Locale(split[0]);
            }
        } else {
            if (!str.contains("_")) {
                return new Locale(str);
            }
            String[] split2 = str.split("_", -1);
            if (split2.length > 2) {
                return new Locale(split2[0], split2[1], split2[2]);
            }
            if (split2.length > 1) {
                return new Locale(split2[0], split2[1]);
            }
            if (split2.length == 1) {
                return new Locale(split2[0]);
            }
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + "]");
    }

    @NonNull
    public static LocaleListCompat create(@NonNull Locale... localeArr) {
        return Build.VERSION.SDK_INT >= 24 ? wrap(new LocaleList(localeArr)) : new LocaleListCompat(new a(localeArr));
    }

    @NonNull
    public static LocaleListCompat forLanguageTags(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] split = str.split(",", -1);
        int length = split.length;
        Locale[] localeArr = new Locale[length];
        for (int i2 = 0; i2 < length; i2++) {
            localeArr[i2] = Build.VERSION.SDK_INT >= 21 ? Locale.forLanguageTag(split[i2]) : a(split[i2]);
        }
        return create(localeArr);
    }

    @NonNull
    @Size(min = 1)
    public static LocaleListCompat getAdjustedDefault() {
        return Build.VERSION.SDK_INT >= 24 ? wrap(LocaleList.getAdjustedDefault()) : create(Locale.getDefault());
    }

    @NonNull
    @Size(min = 1)
    public static LocaleListCompat getDefault() {
        return Build.VERSION.SDK_INT >= 24 ? wrap(LocaleList.getDefault()) : create(Locale.getDefault());
    }

    @NonNull
    public static LocaleListCompat getEmptyLocaleList() {
        return b;
    }

    @NonNull
    @RequiresApi(24)
    public static LocaleListCompat wrap(@NonNull LocaleList localeList) {
        return new LocaleListCompat(new c(localeList));
    }

    @RequiresApi(24)
    @Deprecated
    public static LocaleListCompat wrap(Object obj) {
        return wrap((LocaleList) obj);
    }

    public boolean equals(Object obj) {
        return (obj instanceof LocaleListCompat) && this.a.equals(((LocaleListCompat) obj).a);
    }

    public Locale get(int i2) {
        return this.a.get(i2);
    }

    @Nullable
    public Locale getFirstMatch(@NonNull String[] strArr) {
        return this.a.d(strArr);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @IntRange(from = -1)
    public int indexOf(Locale locale) {
        return this.a.c(locale);
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    @IntRange(from = 0)
    public int size() {
        return this.a.size();
    }

    @NonNull
    public String toLanguageTags() {
        return this.a.b();
    }

    public String toString() {
        return this.a.toString();
    }

    @Nullable
    public Object unwrap() {
        return this.a.a();
    }
}
