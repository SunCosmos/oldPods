package c.a.a.c0.d;

import c.a.a.r;

/* loaded from: classes.dex */
public final class d extends r {

    /* renamed from: c, reason: collision with root package name */
    private final float f1238c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1239d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    private d(float f, float f2, float f3, int i2) {
        super(f, f2);
        this.f1238c = f3;
        this.f1239d = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f(float f, float f2, float f3) {
        if (Math.abs(f2 - d()) > f || Math.abs(f3 - c()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.f1238c);
        return abs <= 1.0f || abs <= this.f1238c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d g(float f, float f2, float f3) {
        int i2 = this.f1239d;
        int i3 = i2 + 1;
        float c2 = (i2 * c()) + f2;
        float f4 = i3;
        return new d(c2 / f4, ((this.f1239d * d()) + f) / f4, ((this.f1239d * this.f1238c) + f3) / f4, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h() {
        return this.f1239d;
    }

    public float i() {
        return this.f1238c;
    }
}
