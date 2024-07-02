package com.google.android.material.transition.platform;

import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* loaded from: classes.dex */
class b {
    private static final com.google.android.material.transition.platform.a a = new a();
    private static final com.google.android.material.transition.platform.a b = new C0065b();

    /* renamed from: c, reason: collision with root package name */
    private static final com.google.android.material.transition.platform.a f1805c = new c();

    /* renamed from: d, reason: collision with root package name */
    private static final com.google.android.material.transition.platform.a f1806d = new d();

    /* loaded from: classes.dex */
    static class a implements com.google.android.material.transition.platform.a {
        a() {
        }

        @Override // com.google.android.material.transition.platform.a
        public com.google.android.material.transition.platform.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.platform.c.a(255, j.q(0, 255, f2, f3, f));
        }
    }

    /* renamed from: com.google.android.material.transition.platform.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0065b implements com.google.android.material.transition.platform.a {
        C0065b() {
        }

        @Override // com.google.android.material.transition.platform.a
        public com.google.android.material.transition.platform.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.platform.c.b(j.q(255, 0, f2, f3, f), 255);
        }
    }

    /* loaded from: classes.dex */
    static class c implements com.google.android.material.transition.platform.a {
        c() {
        }

        @Override // com.google.android.material.transition.platform.a
        public com.google.android.material.transition.platform.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.platform.c.b(j.q(255, 0, f2, f3, f), j.q(0, 255, f2, f3, f));
        }
    }

    /* loaded from: classes.dex */
    static class d implements com.google.android.material.transition.platform.a {
        d() {
        }

        @Override // com.google.android.material.transition.platform.a
        public com.google.android.material.transition.platform.c a(float f, float f2, float f3, float f4) {
            float f5 = ((f3 - f2) * f4) + f2;
            return com.google.android.material.transition.platform.c.b(j.q(255, 0, f2, f5, f), j.q(0, 255, f5, f3, f));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static com.google.android.material.transition.platform.a a(int i2, boolean z) {
        if (i2 == 0) {
            return z ? a : b;
        }
        if (i2 == 1) {
            return z ? b : a;
        }
        if (i2 == 2) {
            return f1805c;
        }
        if (i2 == 3) {
            return f1806d;
        }
        throw new IllegalArgumentException("Invalid fade mode: " + i2);
    }
}
