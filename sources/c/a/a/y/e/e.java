package c.a.a.y.e;

import java.util.Arrays;

/* loaded from: classes.dex */
public class e {
    private final CharSequence a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1326c;

    /* renamed from: d, reason: collision with root package name */
    private final byte[] f1327d;

    public e(CharSequence charSequence, int i2, int i3) {
        this.a = charSequence;
        this.f1326c = i2;
        this.b = i3;
        byte[] bArr = new byte[i2 * i3];
        this.f1327d = bArr;
        Arrays.fill(bArr, (byte) -1);
    }

    private void a(int i2) {
        g(this.b - 1, 0, i2, 1);
        g(this.b - 1, 1, i2, 2);
        g(this.b - 1, 2, i2, 3);
        g(0, this.f1326c - 2, i2, 4);
        g(0, this.f1326c - 1, i2, 5);
        g(1, this.f1326c - 1, i2, 6);
        g(2, this.f1326c - 1, i2, 7);
        g(3, this.f1326c - 1, i2, 8);
    }

    private void b(int i2) {
        g(this.b - 3, 0, i2, 1);
        g(this.b - 2, 0, i2, 2);
        g(this.b - 1, 0, i2, 3);
        g(0, this.f1326c - 4, i2, 4);
        g(0, this.f1326c - 3, i2, 5);
        g(0, this.f1326c - 2, i2, 6);
        g(0, this.f1326c - 1, i2, 7);
        g(1, this.f1326c - 1, i2, 8);
    }

    private void c(int i2) {
        g(this.b - 3, 0, i2, 1);
        g(this.b - 2, 0, i2, 2);
        g(this.b - 1, 0, i2, 3);
        g(0, this.f1326c - 2, i2, 4);
        g(0, this.f1326c - 1, i2, 5);
        g(1, this.f1326c - 1, i2, 6);
        g(2, this.f1326c - 1, i2, 7);
        g(3, this.f1326c - 1, i2, 8);
    }

    private void d(int i2) {
        g(this.b - 1, 0, i2, 1);
        g(this.b - 1, this.f1326c - 1, i2, 2);
        g(0, this.f1326c - 3, i2, 3);
        g(0, this.f1326c - 2, i2, 4);
        g(0, this.f1326c - 1, i2, 5);
        g(1, this.f1326c - 3, i2, 6);
        g(1, this.f1326c - 2, i2, 7);
        g(1, this.f1326c - 1, i2, 8);
    }

    private void g(int i2, int i3, int i4, int i5) {
        if (i2 < 0) {
            int i6 = this.b;
            i2 += i6;
            i3 += 4 - ((i6 + 4) % 8);
        }
        if (i3 < 0) {
            int i7 = this.f1326c;
            i3 += i7;
            i2 += 4 - ((i7 + 4) % 8);
        }
        i(i3, i2, (this.a.charAt(i4) & (1 << (8 - i5))) != 0);
    }

    private void j(int i2, int i3, int i4) {
        int i5 = i2 - 2;
        int i6 = i3 - 2;
        g(i5, i6, i4, 1);
        int i7 = i3 - 1;
        g(i5, i7, i4, 2);
        int i8 = i2 - 1;
        g(i8, i6, i4, 3);
        g(i8, i7, i4, 4);
        g(i8, i3, i4, 5);
        g(i2, i6, i4, 6);
        g(i2, i7, i4, 7);
        g(i2, i3, i4, 8);
    }

    public final boolean e(int i2, int i3) {
        return this.f1327d[(i3 * this.f1326c) + i2] == 1;
    }

    final boolean f(int i2, int i3) {
        return this.f1327d[(i3 * this.f1326c) + i2] >= 0;
    }

    public final void h() {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 4;
        while (true) {
            if (i6 == this.b && i4 == 0) {
                a(i5);
                i5++;
            }
            if (i6 == this.b - 2 && i4 == 0 && this.f1326c % 4 != 0) {
                b(i5);
                i5++;
            }
            if (i6 == this.b - 2 && i4 == 0 && this.f1326c % 8 == 4) {
                c(i5);
                i5++;
            }
            if (i6 == this.b + 4 && i4 == 2 && this.f1326c % 8 == 0) {
                d(i5);
                i5++;
            }
            do {
                if (i6 < this.b && i4 >= 0 && !f(i4, i6)) {
                    j(i6, i4, i5);
                    i5++;
                }
                i6 -= 2;
                i4 += 2;
                if (i6 < 0) {
                    break;
                }
            } while (i4 < this.f1326c);
            int i7 = i6 + 1;
            int i8 = i4 + 3;
            do {
                if (i7 >= 0 && i8 < this.f1326c && !f(i8, i7)) {
                    j(i7, i8, i5);
                    i5++;
                }
                i7 += 2;
                i8 -= 2;
                i2 = this.b;
                if (i7 >= i2) {
                    break;
                }
            } while (i8 >= 0);
            i6 = i7 + 3;
            i4 = i8 + 1;
            if (i6 >= i2 && i4 >= (i3 = this.f1326c)) {
                break;
            }
        }
        if (f(i3 - 1, i2 - 1)) {
            return;
        }
        i(this.f1326c - 1, this.b - 1, true);
        i(this.f1326c - 2, this.b - 2, true);
    }

    final void i(int i2, int i3, boolean z) {
        this.f1327d[(i3 * this.f1326c) + i2] = z ? (byte) 1 : (byte) 0;
    }
}
