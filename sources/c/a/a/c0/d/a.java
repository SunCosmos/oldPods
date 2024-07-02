package c.a.a.c0.d;

import c.a.a.r;

/* loaded from: classes.dex */
public final class a extends r {

    /* renamed from: c, reason: collision with root package name */
    private final float f1234c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(float f, float f2, float f3) {
        super(f, f2);
        this.f1234c = f3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f(float f, float f2, float f3) {
        if (Math.abs(f2 - d()) > f || Math.abs(f3 - c()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.f1234c);
        return abs <= 1.0f || abs <= this.f1234c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a g(float f, float f2, float f3) {
        return new a((c() + f2) / 2.0f, (d() + f) / 2.0f, (this.f1234c + f3) / 2.0f);
    }
}
