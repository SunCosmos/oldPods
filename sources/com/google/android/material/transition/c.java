package com.google.android.material.transition;

/* loaded from: classes.dex */
class c {
    final int a;
    final int b;

    /* renamed from: c, reason: collision with root package name */
    final boolean f1775c;

    private c(int i2, int i3, boolean z) {
        this.a = i2;
        this.b = i3;
        this.f1775c = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c a(int i2, int i3) {
        return new c(i2, i3, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c b(int i2, int i3) {
        return new c(i2, i3, false);
    }
}
