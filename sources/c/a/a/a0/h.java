package c.a.a.a0;

/* loaded from: classes.dex */
public final class h extends x {

    /* renamed from: i */
    static final int[] f1186i = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    private final int[] h = new int[4];

    private static void r(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < 10; i3++) {
            if (i2 == f1186i[i3]) {
                sb.insert(0, (char) (i3 + 48));
                return;
            }
        }
        throw c.a.a.l.a();
    }

    @Override // c.a.a.a0.x
    public int l(c.a.a.x.a aVar, int[] iArr, StringBuilder sb) {
        int[] iArr2 = this.h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int k = aVar.k();
        int i2 = iArr[1];
        int i3 = 0;
        for (int i4 = 0; i4 < 6 && i2 < k; i4++) {
            int j = x.j(aVar, iArr2, i2, x.g);
            sb.append((char) ((j % 10) + 48));
            for (int i5 : iArr2) {
                i2 += i5;
            }
            if (j >= 10) {
                i3 |= 1 << (5 - i4);
            }
        }
        r(sb, i3);
        int i6 = x.n(aVar, i2, true, x.e)[1];
        for (int i7 = 0; i7 < 6 && i6 < k; i7++) {
            sb.append((char) (x.j(aVar, iArr2, i6, x.f) + 48));
            for (int i8 : iArr2) {
                i6 += i8;
            }
        }
        return i6;
    }

    @Override // c.a.a.a0.x
    c.a.a.a q() {
        return c.a.a.a.EAN_13;
    }
}
