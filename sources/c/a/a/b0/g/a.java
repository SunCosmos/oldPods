package c.a.a.b0.g;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public final class a {
    private final b[] a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1212c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1213d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(int i2, int i3) {
        b[] bVarArr = new b[i2];
        this.a = bVarArr;
        int length = bVarArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            this.a[i4] = new b(((i3 + 4) * 17) + 1);
        }
        this.f1213d = i3 * 17;
        this.f1212c = i2;
        this.b = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b a() {
        return this.a[this.b];
    }

    public byte[][] b(int i2, int i3) {
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, this.f1212c * i3, this.f1213d * i2);
        int i4 = this.f1212c * i3;
        for (int i5 = 0; i5 < i4; i5++) {
            bArr[(i4 - i5) - 1] = this.a[i5 / i3].b(i2);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        this.b++;
    }
}
