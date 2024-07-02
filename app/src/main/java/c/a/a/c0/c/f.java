package c.a.a.c0.c;

/* loaded from: classes.dex */
public enum f {
    L(1),
    M(0),
    Q(3),
    H(2);

    private static final f[] f;
    private final int a;

    static {
        f fVar = L;
        f fVar2 = M;
        f fVar3 = Q;
        f = new f[]{fVar2, fVar, H, fVar3};
    }

    f(int i2) {
        this.a = i2;
    }

    public static f a(int i2) {
        if (i2 >= 0) {
            f[] fVarArr = f;
            if (i2 < fVarArr.length) {
                return fVarArr[i2];
            }
        }
        throw new IllegalArgumentException();
    }

    public int b() {
        return this.a;
    }
}
