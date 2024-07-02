package c.a.a.v.f;

/* loaded from: classes.dex */
public final class b extends g {

    /* renamed from: c */
    private final short f1269c;

    /* renamed from: d */
    private final short f1270d;

    public b(g gVar, int i2, int i3) {
        super(gVar);
        this.f1269c = (short) i2;
        this.f1270d = (short) i3;
    }

    @Override // c.a.a.v.f.g
    public void c(c.a.a.x.a aVar, byte[] bArr) {
        int i2;
        int i3 = 0;
        while (true) {
            short s = this.f1270d;
            if (i3 >= s) {
                return;
            }
            if (i3 == 0 || (i3 == 31 && s <= 62)) {
                int i4 = 5;
                aVar.c(31, 5);
                short s2 = this.f1270d;
                if (s2 > 62) {
                    i2 = s2 - 31;
                    i4 = 16;
                } else if (i3 == 0) {
                    aVar.c(Math.min((int) s2, 31), 5);
                } else {
                    i2 = s2 - 31;
                }
                aVar.c(i2, i4);
            }
            aVar.c(bArr[this.f1269c + i3], 8);
            i3++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append((int) this.f1269c);
        sb.append("::");
        sb.append((this.f1269c + this.f1270d) - 1);
        sb.append('>');
        return sb.toString();
    }
}
