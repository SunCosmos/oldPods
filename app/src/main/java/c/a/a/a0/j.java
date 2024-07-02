package c.a.a.a0;

/* loaded from: classes.dex */
public final class j extends x {
    private final int[] h = new int[4];

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // c.a.a.a0.x
    public int l(c.a.a.x.a aVar, int[] iArr, StringBuilder sb) {
        int[] iArr2 = this.h;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int k = aVar.k();
        int i2 = iArr[1];
        for (int i3 = 0; i3 < 4 && i2 < k; i3++) {
            sb.append((char) (x.j(aVar, iArr2, i2, x.f) + 48));
            for (int i4 : iArr2) {
                i2 += i4;
            }
        }
        int i5 = x.n(aVar, i2, true, x.e)[1];
        for (int i6 = 0; i6 < 4 && i5 < k; i6++) {
            sb.append((char) (x.j(aVar, iArr2, i5, x.f) + 48));
            for (int i7 : iArr2) {
                i5 += i7;
            }
        }
        return i5;
    }

    @Override // c.a.a.a0.x
    c.a.a.a q() {
        return c.a.a.a.EAN_8;
    }
}
