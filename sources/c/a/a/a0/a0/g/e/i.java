package c.a.a.a0.a0.g.e;

/* loaded from: classes.dex */
public abstract class i extends h {
    public i(c.a.a.x.a aVar) {
        super(aVar);
    }

    protected abstract void h(StringBuilder sb, int i2);

    protected abstract int i(int i2);

    public final void j(StringBuilder sb, int i2, int i3) {
        int f = b().f(i2, i3);
        h(sb, f);
        int i4 = i(f);
        int i5 = 100000;
        for (int i6 = 0; i6 < 5; i6++) {
            if (i4 / i5 == 0) {
                sb.append('0');
            }
            i5 /= 10;
        }
        sb.append(i4);
    }
}
