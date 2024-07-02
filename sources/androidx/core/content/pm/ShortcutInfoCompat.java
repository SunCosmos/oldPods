package androidx.core.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class ShortcutInfoCompat {
    Context a;
    String b;

    /* renamed from: c, reason: collision with root package name */
    String f530c;

    /* renamed from: d, reason: collision with root package name */
    Intent[] f531d;
    ComponentName e;
    CharSequence f;
    CharSequence g;
    CharSequence h;

    /* renamed from: i, reason: collision with root package name */
    IconCompat f532i;
    boolean j;
    Person[] k;
    Set<String> l;

    @Nullable
    LocusIdCompat m;
    boolean n;
    int o;
    PersistableBundle p;
    long q;
    UserHandle r;
    boolean s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    boolean x = true;
    boolean y;
    int z;

    /* loaded from: classes.dex */
    public static class Builder {
        private final ShortcutInfoCompat a;
        private boolean b;

        @RequiresApi(25)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder(@NonNull Context context, @NonNull ShortcutInfo shortcutInfo) {
            ShortcutInfoCompat shortcutInfoCompat = new ShortcutInfoCompat();
            this.a = shortcutInfoCompat;
            shortcutInfoCompat.a = context;
            shortcutInfoCompat.b = shortcutInfo.getId();
            shortcutInfoCompat.f530c = shortcutInfo.getPackage();
            Intent[] intents = shortcutInfo.getIntents();
            shortcutInfoCompat.f531d = (Intent[]) Arrays.copyOf(intents, intents.length);
            shortcutInfoCompat.e = shortcutInfo.getActivity();
            shortcutInfoCompat.f = shortcutInfo.getShortLabel();
            shortcutInfoCompat.g = shortcutInfo.getLongLabel();
            shortcutInfoCompat.h = shortcutInfo.getDisabledMessage();
            int i2 = Build.VERSION.SDK_INT;
            shortcutInfoCompat.z = i2 >= 28 ? shortcutInfo.getDisabledReason() : shortcutInfo.isEnabled() ? 0 : 3;
            shortcutInfoCompat.l = shortcutInfo.getCategories();
            shortcutInfoCompat.k = ShortcutInfoCompat.f(shortcutInfo.getExtras());
            shortcutInfoCompat.r = shortcutInfo.getUserHandle();
            shortcutInfoCompat.q = shortcutInfo.getLastChangedTimestamp();
            if (i2 >= 30) {
                shortcutInfoCompat.s = shortcutInfo.isCached();
            }
            shortcutInfoCompat.t = shortcutInfo.isDynamic();
            shortcutInfoCompat.u = shortcutInfo.isPinned();
            shortcutInfoCompat.v = shortcutInfo.isDeclaredInManifest();
            shortcutInfoCompat.w = shortcutInfo.isImmutable();
            shortcutInfoCompat.x = shortcutInfo.isEnabled();
            shortcutInfoCompat.y = shortcutInfo.hasKeyFieldsOnly();
            shortcutInfoCompat.m = ShortcutInfoCompat.d(shortcutInfo);
            shortcutInfoCompat.o = shortcutInfo.getRank();
            shortcutInfoCompat.p = shortcutInfo.getExtras();
        }

        public Builder(@NonNull Context context, @NonNull String str) {
            ShortcutInfoCompat shortcutInfoCompat = new ShortcutInfoCompat();
            this.a = shortcutInfoCompat;
            shortcutInfoCompat.a = context;
            shortcutInfoCompat.b = str;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder(@NonNull ShortcutInfoCompat shortcutInfoCompat) {
            ShortcutInfoCompat shortcutInfoCompat2 = new ShortcutInfoCompat();
            this.a = shortcutInfoCompat2;
            shortcutInfoCompat2.a = shortcutInfoCompat.a;
            shortcutInfoCompat2.b = shortcutInfoCompat.b;
            shortcutInfoCompat2.f530c = shortcutInfoCompat.f530c;
            Intent[] intentArr = shortcutInfoCompat.f531d;
            shortcutInfoCompat2.f531d = (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
            shortcutInfoCompat2.e = shortcutInfoCompat.e;
            shortcutInfoCompat2.f = shortcutInfoCompat.f;
            shortcutInfoCompat2.g = shortcutInfoCompat.g;
            shortcutInfoCompat2.h = shortcutInfoCompat.h;
            shortcutInfoCompat2.z = shortcutInfoCompat.z;
            shortcutInfoCompat2.f532i = shortcutInfoCompat.f532i;
            shortcutInfoCompat2.j = shortcutInfoCompat.j;
            shortcutInfoCompat2.r = shortcutInfoCompat.r;
            shortcutInfoCompat2.q = shortcutInfoCompat.q;
            shortcutInfoCompat2.s = shortcutInfoCompat.s;
            shortcutInfoCompat2.t = shortcutInfoCompat.t;
            shortcutInfoCompat2.u = shortcutInfoCompat.u;
            shortcutInfoCompat2.v = shortcutInfoCompat.v;
            shortcutInfoCompat2.w = shortcutInfoCompat.w;
            shortcutInfoCompat2.x = shortcutInfoCompat.x;
            shortcutInfoCompat2.m = shortcutInfoCompat.m;
            shortcutInfoCompat2.n = shortcutInfoCompat.n;
            shortcutInfoCompat2.y = shortcutInfoCompat.y;
            shortcutInfoCompat2.o = shortcutInfoCompat.o;
            Person[] personArr = shortcutInfoCompat.k;
            if (personArr != null) {
                shortcutInfoCompat2.k = (Person[]) Arrays.copyOf(personArr, personArr.length);
            }
            if (shortcutInfoCompat.l != null) {
                shortcutInfoCompat2.l = new HashSet(shortcutInfoCompat.l);
            }
            PersistableBundle persistableBundle = shortcutInfoCompat.p;
            if (persistableBundle != null) {
                shortcutInfoCompat2.p = persistableBundle;
            }
        }

        @NonNull
        public ShortcutInfoCompat build() {
            if (TextUtils.isEmpty(this.a.f)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            }
            ShortcutInfoCompat shortcutInfoCompat = this.a;
            Intent[] intentArr = shortcutInfoCompat.f531d;
            if (intentArr == null || intentArr.length == 0) {
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
            if (this.b) {
                if (shortcutInfoCompat.m == null) {
                    shortcutInfoCompat.m = new LocusIdCompat(shortcutInfoCompat.b);
                }
                this.a.n = true;
            }
            return this.a;
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName componentName) {
            this.a.e = componentName;
            return this;
        }

        @NonNull
        public Builder setAlwaysBadged() {
            this.a.j = true;
            return this;
        }

        @NonNull
        public Builder setCategories(@NonNull Set<String> set) {
            this.a.l = set;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence charSequence) {
            this.a.h = charSequence;
            return this;
        }

        @NonNull
        public Builder setExtras(@NonNull PersistableBundle persistableBundle) {
            this.a.p = persistableBundle;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat iconCompat) {
            this.a.f532i = iconCompat;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] intentArr) {
            this.a.f531d = intentArr;
            return this;
        }

        @NonNull
        public Builder setIsConversation() {
            this.b = true;
            return this;
        }

        @NonNull
        public Builder setLocusId(@Nullable LocusIdCompat locusIdCompat) {
            this.a.m = locusIdCompat;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence charSequence) {
            this.a.g = charSequence;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setLongLived() {
            this.a.n = true;
            return this;
        }

        @NonNull
        public Builder setLongLived(boolean z) {
            this.a.n = z;
            return this;
        }

        @NonNull
        public Builder setPerson(@NonNull Person person) {
            return setPersons(new Person[]{person});
        }

        @NonNull
        public Builder setPersons(@NonNull Person[] personArr) {
            this.a.k = personArr;
            return this;
        }

        @NonNull
        public Builder setRank(int i2) {
            this.a.o = i2;
            return this;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }
    }

    ShortcutInfoCompat() {
    }

    @RequiresApi(22)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    private PersistableBundle b() {
        if (this.p == null) {
            this.p = new PersistableBundle();
        }
        Person[] personArr = this.k;
        if (personArr != null && personArr.length > 0) {
            this.p.putInt("extraPersonCount", personArr.length);
            int i2 = 0;
            while (i2 < this.k.length) {
                PersistableBundle persistableBundle = this.p;
                StringBuilder sb = new StringBuilder();
                sb.append("extraPerson_");
                int i3 = i2 + 1;
                sb.append(i3);
                persistableBundle.putPersistableBundle(sb.toString(), this.k[i2].toPersistableBundle());
                i2 = i3;
            }
        }
        LocusIdCompat locusIdCompat = this.m;
        if (locusIdCompat != null) {
            this.p.putString("extraLocusId", locusIdCompat.getId());
        }
        this.p.putBoolean("extraLongLived", this.n);
        return this.p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static List<ShortcutInfoCompat> c(@NonNull Context context, @NonNull List<ShortcutInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<ShortcutInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new Builder(context, it.next()).build());
        }
        return arrayList;
    }

    @Nullable
    @RequiresApi(25)
    static LocusIdCompat d(@NonNull ShortcutInfo shortcutInfo) {
        if (Build.VERSION.SDK_INT < 29) {
            return e(shortcutInfo.getExtras());
        }
        if (shortcutInfo.getLocusId() == null) {
            return null;
        }
        return LocusIdCompat.toLocusIdCompat(shortcutInfo.getLocusId());
    }

    @Nullable
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    private static LocusIdCompat e(@Nullable PersistableBundle persistableBundle) {
        String string;
        if (persistableBundle == null || (string = persistableBundle.getString("extraLocusId")) == null) {
            return null;
        }
        return new LocusIdCompat(string);
    }

    @VisibleForTesting
    @Nullable
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    static Person[] f(@NonNull PersistableBundle persistableBundle) {
        if (persistableBundle == null || !persistableBundle.containsKey("extraPersonCount")) {
            return null;
        }
        int i2 = persistableBundle.getInt("extraPersonCount");
        Person[] personArr = new Person[i2];
        int i3 = 0;
        while (i3 < i2) {
            StringBuilder sb = new StringBuilder();
            sb.append("extraPerson_");
            int i4 = i3 + 1;
            sb.append(i4);
            personArr[i3] = Person.fromPersistableBundle(persistableBundle.getPersistableBundle(sb.toString()));
            i3 = i4;
        }
        return personArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Intent a(Intent intent) {
        intent.putExtra("android.intent.extra.shortcut.INTENT", this.f531d[r0.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.f.toString());
        if (this.f532i != null) {
            Drawable drawable = null;
            if (this.j) {
                PackageManager packageManager = this.a.getPackageManager();
                ComponentName componentName = this.e;
                if (componentName != null) {
                    try {
                        drawable = packageManager.getActivityIcon(componentName);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                if (drawable == null) {
                    drawable = this.a.getApplicationInfo().loadIcon(packageManager);
                }
            }
            this.f532i.addToShortcutIntent(intent, drawable, this.a);
        }
        return intent;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.e;
    }

    @Nullable
    public Set<String> getCategories() {
        return this.l;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.h;
    }

    public int getDisabledReason() {
        return this.z;
    }

    @Nullable
    public PersistableBundle getExtras() {
        return this.p;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public IconCompat getIcon() {
        return this.f532i;
    }

    @NonNull
    public String getId() {
        return this.b;
    }

    @NonNull
    public Intent getIntent() {
        return this.f531d[r0.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        Intent[] intentArr = this.f531d;
        return (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
    }

    public long getLastChangedTimestamp() {
        return this.q;
    }

    @Nullable
    public LocusIdCompat getLocusId() {
        return this.m;
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.g;
    }

    @NonNull
    public String getPackage() {
        return this.f530c;
    }

    public int getRank() {
        return this.o;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.f;
    }

    @Nullable
    public UserHandle getUserHandle() {
        return this.r;
    }

    public boolean hasKeyFieldsOnly() {
        return this.y;
    }

    public boolean isCached() {
        return this.s;
    }

    public boolean isDeclaredInManifest() {
        return this.v;
    }

    public boolean isDynamic() {
        return this.t;
    }

    public boolean isEnabled() {
        return this.x;
    }

    public boolean isImmutable() {
        return this.w;
    }

    public boolean isPinned() {
        return this.u;
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder intents = new ShortcutInfo.Builder(this.a, this.b).setShortLabel(this.f).setIntents(this.f531d);
        IconCompat iconCompat = this.f532i;
        if (iconCompat != null) {
            intents.setIcon(iconCompat.toIcon(this.a));
        }
        if (!TextUtils.isEmpty(this.g)) {
            intents.setLongLabel(this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            intents.setDisabledMessage(this.h);
        }
        ComponentName componentName = this.e;
        if (componentName != null) {
            intents.setActivity(componentName);
        }
        Set<String> set = this.l;
        if (set != null) {
            intents.setCategories(set);
        }
        intents.setRank(this.o);
        PersistableBundle persistableBundle = this.p;
        if (persistableBundle != null) {
            intents.setExtras(persistableBundle);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Person[] personArr = this.k;
            if (personArr != null && personArr.length > 0) {
                int length = personArr.length;
                android.app.Person[] personArr2 = new android.app.Person[length];
                for (int i2 = 0; i2 < length; i2++) {
                    personArr2[i2] = this.k[i2].toAndroidPerson();
                }
                intents.setPersons(personArr2);
            }
            LocusIdCompat locusIdCompat = this.m;
            if (locusIdCompat != null) {
                intents.setLocusId(locusIdCompat.toLocusId());
            }
            intents.setLongLived(this.n);
        } else {
            intents.setExtras(b());
        }
        return intents.build();
    }
}
