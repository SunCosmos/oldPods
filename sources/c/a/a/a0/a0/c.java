package c.a.a.a0.a0;

import c.a.a.r;

/* loaded from: classes.dex */
public final class c {
    private final int a;
    private final int[] b;

    /* renamed from: c, reason: collision with root package name */
    private final r[] f1164c;

    public c(int i2, int[] iArr, int i3, int i4, int i5) {
        this.a = i2;
        this.b = iArr;
        float f = i5;
        this.f1164c = new r[]{new r(i3, f), new r(i4, f)};
    }

    public r[] a() {
        return this.f1164c;
    }

    public int[] b() {
        return this.b;
    }

    public int c() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof c) && this.a == ((c) obj).a;
    }

    public int hashCode() {
        return this.a;
    }
}
