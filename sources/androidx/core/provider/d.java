package androidx.core.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d {
    static final LruCache<String, Typeface> a = new LruCache<>(16);
    private static final ExecutorService b = androidx.core.provider.e.a("fonts-androidx", 10, 10000);

    /* renamed from: c, reason: collision with root package name */
    static final Object f581c = new Object();

    /* renamed from: d, reason: collision with root package name */
    @GuardedBy("LOCK")
    static final SimpleArrayMap<String, ArrayList<Consumer<e>>> f582d = new SimpleArrayMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Callable<e> {
        final /* synthetic */ String a;
        final /* synthetic */ Context b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ FontRequest f583c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f584d;

        a(String str, Context context, FontRequest fontRequest, int i2) {
            this.a = str;
            this.b = context;
            this.f583c = fontRequest;
            this.f584d = i2;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e call() {
            return d.c(this.a, this.b, this.f583c, this.f584d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Consumer<e> {
        final /* synthetic */ androidx.core.provider.a a;

        b(androidx.core.provider.a aVar) {
            this.a = aVar;
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(e eVar) {
            this.a.b(eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Callable<e> {
        final /* synthetic */ String a;
        final /* synthetic */ Context b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ FontRequest f585c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f586d;

        c(String str, Context context, FontRequest fontRequest, int i2) {
            this.a = str;
            this.b = context;
            this.f585c = fontRequest;
            this.f586d = i2;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e call() {
            return d.c(this.a, this.b, this.f585c, this.f586d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.core.provider.d$d, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0018d implements Consumer<e> {
        final /* synthetic */ String a;

        C0018d(String str) {
            this.a = str;
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(e eVar) {
            synchronized (d.f581c) {
                SimpleArrayMap<String, ArrayList<Consumer<e>>> simpleArrayMap = d.f582d;
                ArrayList<Consumer<e>> arrayList = simpleArrayMap.get(this.a);
                if (arrayList == null) {
                    return;
                }
                simpleArrayMap.remove(this.a);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    arrayList.get(i2).accept(eVar);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class e {
        final Typeface a;
        final int b;

        e(int i2) {
            this.a = null;
            this.b = i2;
        }

        @SuppressLint({"WrongConstant"})
        e(@NonNull Typeface typeface) {
            this.a = typeface;
            this.b = 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @SuppressLint({"WrongConstant"})
        public boolean a() {
            return this.b == 0;
        }
    }

    private static String a(@NonNull FontRequest fontRequest, int i2) {
        return fontRequest.b() + "-" + i2;
    }

    @SuppressLint({"WrongConstant"})
    private static int b(@NonNull FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i2 = 1;
        if (fontFamilyResult.getStatusCode() != 0) {
            return fontFamilyResult.getStatusCode() != 1 ? -3 : -2;
        }
        FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
        if (fonts != null && fonts.length != 0) {
            i2 = 0;
            for (FontsContractCompat.FontInfo fontInfo : fonts) {
                int resultCode = fontInfo.getResultCode();
                if (resultCode != 0) {
                    if (resultCode < 0) {
                        return -3;
                    }
                    return resultCode;
                }
            }
        }
        return i2;
    }

    @NonNull
    static e c(@NonNull String str, @NonNull Context context, @NonNull FontRequest fontRequest, int i2) {
        LruCache<String, Typeface> lruCache = a;
        Typeface typeface = lruCache.get(str);
        if (typeface != null) {
            return new e(typeface);
        }
        try {
            FontsContractCompat.FontFamilyResult d2 = androidx.core.provider.c.d(context, fontRequest, null);
            int b2 = b(d2);
            if (b2 != 0) {
                return new e(b2);
            }
            Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, d2.getFonts(), i2);
            if (createFromFontInfo == null) {
                return new e(-3);
            }
            lruCache.put(str, createFromFontInfo);
            return new e(createFromFontInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            return new e(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface d(@NonNull Context context, @NonNull FontRequest fontRequest, int i2, @Nullable Executor executor, @NonNull androidx.core.provider.a aVar) {
        String a2 = a(fontRequest, i2);
        Typeface typeface = a.get(a2);
        if (typeface != null) {
            aVar.b(new e(typeface));
            return typeface;
        }
        b bVar = new b(aVar);
        synchronized (f581c) {
            SimpleArrayMap<String, ArrayList<Consumer<e>>> simpleArrayMap = f582d;
            ArrayList<Consumer<e>> arrayList = simpleArrayMap.get(a2);
            if (arrayList != null) {
                arrayList.add(bVar);
                return null;
            }
            ArrayList<Consumer<e>> arrayList2 = new ArrayList<>();
            arrayList2.add(bVar);
            simpleArrayMap.put(a2, arrayList2);
            c cVar = new c(a2, context, fontRequest, i2);
            if (executor == null) {
                executor = b;
            }
            androidx.core.provider.e.c(executor, cVar, new C0018d(a2));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface e(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull androidx.core.provider.a aVar, int i2, int i3) {
        String a2 = a(fontRequest, i2);
        Typeface typeface = a.get(a2);
        if (typeface != null) {
            aVar.b(new e(typeface));
            return typeface;
        }
        if (i3 == -1) {
            e c2 = c(a2, context, fontRequest, i2);
            aVar.b(c2);
            return c2.a;
        }
        try {
            e eVar = (e) androidx.core.provider.e.d(b, new a(a2, context, fontRequest, i2), i3);
            aVar.b(eVar);
            return eVar.a;
        } catch (InterruptedException unused) {
            aVar.b(new e(-3));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f() {
        a.evictAll();
    }
}
