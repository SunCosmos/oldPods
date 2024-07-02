package c.a.a.v.f;

/* loaded from: classes.dex */
public final class e extends g {

    /* renamed from: c */
    private final short f1273c;

    /* renamed from: d */
    private final short f1274d;

    public e(g gVar, int i2, int i3) {
        super(gVar);
        this.f1273c = (short) i2;
        this.f1274d = (short) i3;
    }

    @Override // c.a.a.v.f.g
    public void c(c.a.a.x.a aVar, byte[] bArr) {
        aVar.c(this.f1273c, this.f1274d);
    }

    public String toString() {
        short s = this.f1273c;
        short s2 = this.f1274d;
        return '<' + Integer.toBinaryString((s & ((1 << s2) - 1)) | (1 << s2) | (1 << this.f1274d)).substring(1) + '>';
    }
}
