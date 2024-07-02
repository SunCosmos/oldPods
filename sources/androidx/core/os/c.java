package androidx.core.os;

import android.os.LocaleList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Locale;

@RequiresApi(24)
/* loaded from: classes.dex */
final class c implements b {
    private final LocaleList a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(LocaleList localeList) {
        this.a = localeList;
    }

    @Override // androidx.core.os.b
    public Object a() {
        return this.a;
    }

    @Override // androidx.core.os.b
    public String b() {
        return this.a.toLanguageTags();
    }

    @Override // androidx.core.os.b
    public int c(Locale locale) {
        return this.a.indexOf(locale);
    }

    @Override // androidx.core.os.b
    @Nullable
    public Locale d(@NonNull String[] strArr) {
        return this.a.getFirstMatch(strArr);
    }

    public boolean equals(Object obj) {
        return this.a.equals(((b) obj).a());
    }

    @Override // androidx.core.os.b
    public Locale get(int i2) {
        return this.a.get(i2);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // androidx.core.os.b
    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    @Override // androidx.core.os.b
    public int size() {
        return this.a.size();
    }

    public String toString() {
        return this.a.toString();
    }
}
