package com.google.android.material.transition;

/* loaded from: classes.dex */
class b {
    private static final com.google.android.material.transition.a a = new a();
    private static final com.google.android.material.transition.a b = new C0064b();

    /* renamed from: c, reason: collision with root package name */
    private static final com.google.android.material.transition.a f1773c = new c();

    /* renamed from: d, reason: collision with root package name */
    private static final com.google.android.material.transition.a f1774d = new d();

    /* loaded from: classes.dex */
    static class a implements com.google.android.material.transition.a {
        a() {
        }

        @Override // com.google.android.material.transition.a
        public com.google.android.material.transition.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.c.a(255, j.p(0, 255, f2, f3, f));
        }
    }

    /* renamed from: com.google.android.material.transition.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0064b implements com.google.android.material.transition.a {
        C0064b() {
        }

        @Override // com.google.android.material.transition.a
        public com.google.android.material.transition.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.c.b(j.p(255, 0, f2, f3, f), 255);
        }
    }

    /* loaded from: classes.dex */
    static class c implements com.google.android.material.transition.a {
        c() {
        }

        @Override // com.google.android.material.transition.a
        public com.google.android.material.transition.c a(float f, float f2, float f3, float f4) {
            return com.google.android.material.transition.c.b(j.p(255, 0, f2, f3, f), j.p(0, 255, f2, f3, f));
        }
    }

    /* loaded from: classes.dex */
    static class d implements com.google.android.material.transition.a {
        d() {
        }

        @Override // com.google.android.material.transition.a
        public com.google.android.material.transition.c a(float f, float f2, float f3, float f4) {
            float f5 = ((f3 - f2) * f4) + f2;
            return com.google.android.material.transition.c.b(j.p(255, 0, f2, f5, f), j.p(0, 255, f5, f3, f));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static com.google.android.material.transition.a a(int i2, boolean z) {
        if (i2 == 0) {
            return z ? a : b;
        }
        if (i2 == 1) {
            return z ? b : a;
        }
        if (i2 == 2) {
            return f1773c;
        }
        if (i2 == 3) {
            return f1774d;
        }
        throw new IllegalArgumentException("Invalid fade mode: " + i2);
    }
}
