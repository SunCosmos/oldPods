package androidx.core.provider;

import android.graphics.Typeface;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.provider.FontsContractCompat;
import androidx.core.provider.d;

/* loaded from: classes.dex */
public class a {

    @NonNull
    private final FontsContractCompat.FontRequestCallback a;

    @NonNull
    private final Handler b;

    /* renamed from: androidx.core.provider.a$a */
    /* loaded from: classes.dex */
    public class RunnableC0017a implements Runnable {
        final /* synthetic */ FontsContractCompat.FontRequestCallback a;
        final /* synthetic */ Typeface b;

        RunnableC0017a(a aVar, FontsContractCompat.FontRequestCallback fontRequestCallback, Typeface typeface) {
            this.a = fontRequestCallback;
            this.b = typeface;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.onTypefaceRetrieved(this.b);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        final /* synthetic */ FontsContractCompat.FontRequestCallback a;
        final /* synthetic */ int b;

        b(a aVar, FontsContractCompat.FontRequestCallback fontRequestCallback, int i2) {
            this.a = fontRequestCallback;
            this.b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.onTypefaceRequestFailed(this.b);
        }
    }

    public a(@NonNull FontsContractCompat.FontRequestCallback fontRequestCallback) {
        this.a = fontRequestCallback;
        this.b = androidx.core.provider.b.a();
    }

    public a(@NonNull FontsContractCompat.FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        this.a = fontRequestCallback;
        this.b = handler;
    }

    private void a(int i2) {
        this.b.post(new b(this, this.a, i2));
    }

    private void c(@NonNull Typeface typeface) {
        this.b.post(new RunnableC0017a(this, this.a, typeface));
    }

    public void b(@NonNull d.e eVar) {
        if (eVar.a()) {
            c(eVar.a);
        } else {
            a(eVar.b);
        }
    }
}
