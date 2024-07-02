package c.a.a.c0.e;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public final class b {
    private final byte[][] a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1243c;

    public b(int i2, int i3) {
        this.a = (byte[][]) Array.newInstance((Class<?>) byte.class, i3, i2);
        this.b = i2;
        this.f1243c = i3;
    }

    public void a(byte b) {
        for (int i2 = 0; i2 < this.f1243c; i2++) {
            for (int i3 = 0; i3 < this.b; i3++) {
                this.a[i2][i3] = b;
            }
        }
    }

    public byte b(int i2, int i3) {
        return this.a[i3][i2];
    }

    public byte[][] c() {
        return this.a;
    }

    public int d() {
        return this.f1243c;
    }

    public int e() {
        return this.b;
    }

    public void f(int i2, int i3, int i4) {
        this.a[i3][i2] = (byte) i4;
    }

    public void g(int i2, int i3, boolean z) {
        this.a[i3][i2] = z ? (byte) 1 : (byte) 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.b * 2 * this.f1243c) + 2);
        for (int i2 = 0; i2 < this.f1243c; i2++) {
            for (int i3 = 0; i3 < this.b; i3++) {
                byte b = this.a[i2][i3];
                sb.append(b != 0 ? b != 1 ? "  " : " 1" : " 0");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
